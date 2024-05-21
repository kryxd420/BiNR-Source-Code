package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "WebPaymentDataCreator")
@SafeParcelable.Reserved({1})
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    @SafeParcelable.Field(id = 2)
    private String zzeu;

    private zzar() {
    }

    @SafeParcelable.Constructor
    zzar(@SafeParcelable.Param(id = 2) String str) {
        this.zzeu = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzeu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
