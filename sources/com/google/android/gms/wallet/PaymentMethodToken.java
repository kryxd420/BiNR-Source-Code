package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PaymentMethodTokenCreator")
@SafeParcelable.Reserved({1})
public final class PaymentMethodToken extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentMethodToken> CREATOR = new zzaf();
    @SafeParcelable.Field(id = 2)
    private int zzeb;
    @SafeParcelable.Field(id = 3)
    private String zzec;

    private PaymentMethodToken() {
    }

    @SafeParcelable.Constructor
    PaymentMethodToken(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) String str) {
        this.zzeb = i;
        this.zzec = str;
    }

    public final int getPaymentMethodTokenizationType() {
        return this.zzeb;
    }

    public final String getToken() {
        return this.zzec;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzeb);
        SafeParcelWriter.writeString(parcel, 3, this.zzec, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
