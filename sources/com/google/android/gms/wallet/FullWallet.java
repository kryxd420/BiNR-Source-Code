package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

@SafeParcelable.Class(creator = "FullWalletCreator")
@SafeParcelable.Reserved({1})
public final class FullWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FullWallet> CREATOR = new zzk();
    @SafeParcelable.Field(id = 2)
    private String zzaw;
    @SafeParcelable.Field(id = 3)
    private String zzax;
    @SafeParcelable.Field(id = 4)
    private ProxyCard zzay;
    @SafeParcelable.Field(id = 5)
    private String zzaz;
    @SafeParcelable.Field(id = 6)
    private zza zzba;
    @SafeParcelable.Field(id = 7)
    private zza zzbb;
    @SafeParcelable.Field(id = 8)
    private String[] zzbc;
    @SafeParcelable.Field(id = 9)
    private UserAddress zzbd;
    @SafeParcelable.Field(id = 10)
    private UserAddress zzbe;
    @SafeParcelable.Field(id = 11)
    private InstrumentInfo[] zzbf;
    @SafeParcelable.Field(id = 12)
    private PaymentMethodToken zzbg;

    private FullWallet() {
    }

    @SafeParcelable.Constructor
    FullWallet(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) ProxyCard proxyCard, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) zza zza, @SafeParcelable.Param(id = 7) zza zza2, @SafeParcelable.Param(id = 8) String[] strArr, @SafeParcelable.Param(id = 9) UserAddress userAddress, @SafeParcelable.Param(id = 10) UserAddress userAddress2, @SafeParcelable.Param(id = 11) InstrumentInfo[] instrumentInfoArr, @SafeParcelable.Param(id = 12) PaymentMethodToken paymentMethodToken) {
        this.zzaw = str;
        this.zzax = str2;
        this.zzay = proxyCard;
        this.zzaz = str3;
        this.zzba = zza;
        this.zzbb = zza2;
        this.zzbc = strArr;
        this.zzbd = userAddress;
        this.zzbe = userAddress2;
        this.zzbf = instrumentInfoArr;
        this.zzbg = paymentMethodToken;
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

    public final PaymentMethodToken getPaymentMethodToken() {
        return this.zzbg;
    }

    public final ProxyCard getProxyCard() {
        return this.zzay;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzax, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzay, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzaz, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzba, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzbb, i, false);
        SafeParcelWriter.writeStringArray(parcel, 8, this.zzbc, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzbd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzbe, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, this.zzbf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzbg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
