package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "TimeIntervalCreator")
@SafeParcelable.Reserved({1})
public final class TimeInterval extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TimeInterval> CREATOR = new zzk();
    @SafeParcelable.Field(id = 2)
    private long zzhb;
    @SafeParcelable.Field(id = 3)
    private long zzhc;

    TimeInterval() {
    }

    @SafeParcelable.Constructor
    public TimeInterval(@SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) long j2) {
        this.zzhb = j;
        this.zzhc = j2;
    }

    public final long getEndTimestamp() {
        return this.zzhc;
    }

    public final long getStartTimestamp() {
        return this.zzhb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zzhb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzhc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
