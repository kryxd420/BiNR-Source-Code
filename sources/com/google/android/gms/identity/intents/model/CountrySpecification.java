package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CountrySpecificationCreator")
@SafeParcelable.Reserved({1})
public class CountrySpecification extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CountrySpecification> CREATOR = new zza();
    @SafeParcelable.Field(id = 2)
    private String zzk;

    @SafeParcelable.Constructor
    public CountrySpecification(@SafeParcelable.Param(id = 2) String str) {
        this.zzk = str;
    }

    public String getCountryCode() {
        return this.zzk;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
