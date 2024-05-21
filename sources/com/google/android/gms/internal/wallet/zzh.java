package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GetClientTokenResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    @SafeParcelable.Field(id = 2)
    private byte[] zzew;

    zzh() {
        this(new byte[0]);
    }

    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(id = 2) byte[] bArr) {
        this.zzew = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzew, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
