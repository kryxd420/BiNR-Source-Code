package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ProxyCardCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public final class ProxyCard extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ProxyCard> CREATOR = new zzak();
    @SafeParcelable.Field(id = 2)
    private String zzeh;
    @SafeParcelable.Field(id = 3)
    private String zzei;
    @SafeParcelable.Field(id = 4)
    private int zzej;
    @SafeParcelable.Field(id = 5)
    private int zzek;

    @SafeParcelable.Constructor
    public ProxyCard(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) int i2) {
        this.zzeh = str;
        this.zzei = str2;
        this.zzej = i;
        this.zzek = i2;
    }

    public final String getCvn() {
        return this.zzei;
    }

    public final int getExpirationMonth() {
        return this.zzej;
    }

    public final int getExpirationYear() {
        return this.zzek;
    }

    public final String getPan() {
        return this.zzeh;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzeh, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzei, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzej);
        SafeParcelWriter.writeInt(parcel, 5, this.zzek);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
