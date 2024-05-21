package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GetSaveInstrumentDetailsResponseCreator")
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    @SafeParcelable.Field(id = 1)
    private String[] zzex;
    @SafeParcelable.Field(id = 2)
    private int[] zzey;
    @SafeParcelable.Field(id = 3)
    private RemoteViews zzez;
    @SafeParcelable.Field(id = 4)
    private byte[] zzfa;

    private zzj() {
    }

    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) int[] iArr, @SafeParcelable.Param(id = 3) RemoteViews remoteViews, @SafeParcelable.Param(id = 4) byte[] bArr) {
        this.zzex = strArr;
        this.zzey = iArr;
        this.zzez = remoteViews;
        this.zzfa = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zzex, false);
        SafeParcelWriter.writeIntArray(parcel, 2, this.zzey, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzez, i, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzfa, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
