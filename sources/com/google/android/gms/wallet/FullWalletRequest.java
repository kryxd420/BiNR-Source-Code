package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "FullWalletRequestCreator")
@SafeParcelable.Reserved({1})
public final class FullWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FullWalletRequest> CREATOR = new zzm();
    @SafeParcelable.Field(id = 2)
    String zzaw;
    @SafeParcelable.Field(id = 3)
    String zzax;
    @SafeParcelable.Field(id = 4)
    Cart zzbh;

    public final class Builder {
        private Builder() {
        }

        public final FullWalletRequest build() {
            return FullWalletRequest.this;
        }

        public final Builder setCart(Cart cart) {
            FullWalletRequest.this.zzbh = cart;
            return this;
        }

        public final Builder setGoogleTransactionId(String str) {
            FullWalletRequest.this.zzaw = str;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            FullWalletRequest.this.zzax = str;
            return this;
        }
    }

    FullWalletRequest() {
    }

    @SafeParcelable.Constructor
    FullWalletRequest(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) Cart cart) {
        this.zzaw = str;
        this.zzax = str2;
        this.zzbh = cart;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final Cart getCart() {
        return this.zzbh;
    }

    public final String getGoogleTransactionId() {
        return this.zzaw;
    }

    public final String getMerchantTransactionId() {
        return this.zzax;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzax, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
