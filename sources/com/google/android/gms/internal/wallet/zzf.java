package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GetBuyFlowInitializationTokenResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    @SafeParcelable.Field(id = 2)
    private byte[] zzev;

    zzf() {
        this(new byte[0]);
    }

    @SafeParcelable.Constructor
    public zzf(@SafeParcelable.Param(id = 2) byte[] bArr) {
        this.zzev = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzev, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
