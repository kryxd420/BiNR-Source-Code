package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AddressCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    @SafeParcelable.Field(id = 2)
    private String name;
    @SafeParcelable.Field(id = 3)
    private String zze;
    @SafeParcelable.Field(id = 4)
    private String zzf;
    @SafeParcelable.Field(id = 5)
    private String zzg;
    @SafeParcelable.Field(id = 6)
    private String zzh;
    @SafeParcelable.Field(id = 7)
    private String zzi;
    @SafeParcelable.Field(id = 8)
    private String zzj;
    @SafeParcelable.Field(id = 9)
    private String zzk;
    @SafeParcelable.Field(id = 10)
    private String zzl;
    @SafeParcelable.Field(id = 11)
    private boolean zzm;
    @SafeParcelable.Field(id = 12)
    private String zzn;

    zza() {
    }

    @SafeParcelable.Constructor
    zza(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7, @SafeParcelable.Param(id = 9) String str8, @SafeParcelable.Param(id = 10) String str9, @SafeParcelable.Param(id = 11) boolean z, @SafeParcelable.Param(id = 12) String str10) {
        this.name = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzh = str5;
        this.zzi = str6;
        this.zzj = str7;
        this.zzk = str8;
        this.zzl = str9;
        this.zzm = z;
        this.zzn = str10;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeString(parcel, 3, this.zze, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzh, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzl, false);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzm);
        SafeParcelWriter.writeString(parcel, 12, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
