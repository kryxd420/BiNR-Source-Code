package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

@SafeParcelable.Class(creator = "MaskedWalletCreator")
@SafeParcelable.Reserved({1})
public final class MaskedWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MaskedWallet> CREATOR = new zzx();
    @SafeParcelable.Field(id = 2)
    String zzaw;
    @SafeParcelable.Field(id = 3)
    String zzax;
    @SafeParcelable.Field(id = 5)
    String zzaz;
    @SafeParcelable.Field(id = 6)
    private zza zzba;
    @SafeParcelable.Field(id = 7)
    private zza zzbb;
    @SafeParcelable.Field(id = 4)
    String[] zzbc;
    @SafeParcelable.Field(id = 10)
    UserAddress zzbd;
    @SafeParcelable.Field(id = 11)
    UserAddress zzbe;
    @SafeParcelable.Field(id = 12)
    InstrumentInfo[] zzbf;
    @SafeParcelable.Field(id = 8)
    private LoyaltyWalletObject[] zzda;
    @SafeParcelable.Field(id = 9)
    private OfferWalletObject[] zzdb;

    public final class Builder {
        private Builder() {
        }

        public final MaskedWallet build() {
            return MaskedWallet.this;
        }

        public final Builder setBuyerBillingAddress(UserAddress userAddress) {
            MaskedWallet.this.zzbd = userAddress;
            return this;
        }

        public final Builder setBuyerShippingAddress(UserAddress userAddress) {
            MaskedWallet.this.zzbe = userAddress;
            return this;
        }

        public final Builder setEmail(String str) {
            MaskedWallet.this.zzaz = str;
            return this;
        }

        public final Builder setGoogleTransactionId(String str) {
            MaskedWallet.this.zzaw = str;
            return this;
        }

        public final Builder setInstrumentInfos(InstrumentInfo[] instrumentInfoArr) {
            MaskedWallet.this.zzbf = instrumentInfoArr;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            MaskedWallet.this.zzax = str;
            return this;
        }

        public final Builder setPaymentDescriptions(String[] strArr) {
            MaskedWallet.this.zzbc = strArr;
            return this;
        }
    }

    private MaskedWallet() {
    }

    @SafeParcelable.Constructor
    MaskedWallet(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String[] strArr, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) zza zza, @SafeParcelable.Param(id = 7) zza zza2, @SafeParcelable.Param(id = 8) LoyaltyWalletObject[] loyaltyWalletObjectArr, @SafeParcelable.Param(id = 9) OfferWalletObject[] offerWalletObjectArr, @SafeParcelable.Param(id = 10) UserAddress userAddress, @SafeParcelable.Param(id = 11) UserAddress userAddress2, @SafeParcelable.Param(id = 12) InstrumentInfo[] instrumentInfoArr) {
        this.zzaw = str;
        this.zzax = str2;
        this.zzbc = strArr;
        this.zzaz = str3;
        this.zzba = zza;
        this.zzbb = zza2;
        this.zzda = loyaltyWalletObjectArr;
        this.zzdb = offerWalletObjectArr;
        this.zzbd = userAddress;
        this.zzbe = userAddress2;
        this.zzbf = instrumentInfoArr;
    }

    public static Builder newBuilderFrom(MaskedWallet maskedWallet) {
        Preconditions.checkNotNull(maskedWallet);
        Builder email = new Builder().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setMerchantTransactionId(maskedWallet.getMerchantTransactionId()).setPaymentDescriptions(maskedWallet.getPaymentDescriptions()).setInstrumentInfos(maskedWallet.getInstrumentInfos()).setEmail(maskedWallet.getEmail());
        MaskedWallet.this.zzda = maskedWallet.zzda;
        MaskedWallet.this.zzdb = maskedWallet.zzdb;
        return email.setBuyerBillingAddress(maskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedWallet.getBuyerShippingAddress());
    }

    public final UserAddress getBuyerBillingAddress() {
        return this.zzbd;
    }

    public final UserAddress getBuyerShippingAddress() {
        return this.zzbe;
    }

    public final String getEmail() {
        return this.zzaz;
    }

    public final String getGoogleTransactionId() {
        return this.zzaw;
    }

    public final InstrumentInfo[] getInstrumentInfos() {
        return this.zzbf;
    }

    public final String getMerchantTransactionId() {
        return this.zzax;
    }

    public final String[] getPaymentDescriptions() {
        return this.zzbc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzax, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.zzbc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzaz, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzba, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzbb, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzda, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzdb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzbd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzbe, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 12, this.zzbf, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
