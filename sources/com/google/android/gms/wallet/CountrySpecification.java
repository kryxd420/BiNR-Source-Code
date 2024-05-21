package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CountrySpecificationCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public class CountrySpecification extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CountrySpecification> CREATOR = new zzh();
    @SafeParcelable.Field(id = 2)
    private String zzh;

    @SafeParcelable.Constructor
    public CountrySpecification(@SafeParcelable.Param(id = 2) String str) {
        this.zzh = str;
    }

    public String getCountryCode() {
        return this.zzh;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
