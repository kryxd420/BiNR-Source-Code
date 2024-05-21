package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class(creator = "CreateWalletObjectsRequestCreator")
@SafeParcelable.Reserved({1})
public final class CreateWalletObjectsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CreateWalletObjectsRequest> CREATOR = new zzj();
    public static final int REQUEST_IMMEDIATE_SAVE = 1;
    public static final int SHOW_SAVE_PROMPT = 0;
    @SafeParcelable.Field(id = 2)
    LoyaltyWalletObject zzar;
    @SafeParcelable.Field(id = 3)
    OfferWalletObject zzas;
    @SafeParcelable.Field(id = 4)
    GiftCardWalletObject zzat;
    @SafeParcelable.Field(id = 5)
    int zzau;

    public final class Builder {
        private Builder() {
        }

        public final CreateWalletObjectsRequest build() {
            boolean z = false;
            if ((CreateWalletObjectsRequest.this.zzat == null ? 0 : 1) + (CreateWalletObjectsRequest.this.zzar == null ? 0 : 1) + (CreateWalletObjectsRequest.this.zzas == null ? 0 : 1) == 1) {
                z = true;
            }
            Preconditions.checkState(z, "CreateWalletObjectsRequest must have exactly one Wallet Object");
            return CreateWalletObjectsRequest.this;
        }

        public final Builder setCreateMode(int i) {
            CreateWalletObjectsRequest.this.zzau = i;
            return this;
        }

        public final Builder setGiftCardWalletObject(GiftCardWalletObject giftCardWalletObject) {
            CreateWalletObjectsRequest.this.zzat = giftCardWalletObject;
            return this;
        }

        public final Builder setLoyaltyWalletObject(LoyaltyWalletObject loyaltyWalletObject) {
            CreateWalletObjectsRequest.this.zzar = loyaltyWalletObject;
            return this;
        }

        public final Builder setOfferWalletObject(OfferWalletObject offerWalletObject) {
            CreateWalletObjectsRequest.this.zzas = offerWalletObject;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CreateMode {
    }

    CreateWalletObjectsRequest() {
    }

    @Deprecated
    public CreateWalletObjectsRequest(GiftCardWalletObject giftCardWalletObject) {
        this.zzat = giftCardWalletObject;
    }

    @Deprecated
    public CreateWalletObjectsRequest(LoyaltyWalletObject loyaltyWalletObject) {
        this.zzar = loyaltyWalletObject;
    }

    @SafeParcelable.Constructor
    CreateWalletObjectsRequest(@SafeParcelable.Param(id = 2) LoyaltyWalletObject loyaltyWalletObject, @SafeParcelable.Param(id = 3) OfferWalletObject offerWalletObject, @SafeParcelable.Param(id = 4) GiftCardWalletObject giftCardWalletObject, @SafeParcelable.Param(id = 5) int i) {
        this.zzar = loyaltyWalletObject;
        this.zzas = offerWalletObject;
        this.zzat = giftCardWalletObject;
        this.zzau = i;
    }

    @Deprecated
    public CreateWalletObjectsRequest(OfferWalletObject offerWalletObject) {
        this.zzas = offerWalletObject;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final int getCreateMode() {
        return this.zzau;
    }

    public final GiftCardWalletObject getGiftCardWalletObject() {
        return this.zzat;
    }

    public final LoyaltyWalletObject getLoyaltyWalletObject() {
        return this.zzar;
    }

    public final OfferWalletObject getOfferWalletObject() {
        return this.zzas;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzar, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzas, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzat, i, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzau);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
