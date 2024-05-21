package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = "PatternItem";
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int type;
    @Nullable
    @SafeParcelable.Field(getter = "getLength", id = 3)
    private final Float zzdu;

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < 0.0f)) {
            z = false;
        }
        String valueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45);
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        Preconditions.checkArgument(z, sb.toString());
        this.type = i;
        this.zzdu = f;
    }

    @Nullable
    static List<PatternItem> zza(@Nullable List<PatternItem> list) {
        PatternItem patternItem;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem next : list) {
            if (next == null) {
                next = null;
            } else {
                switch (next.type) {
                    case 0:
                        patternItem = new Dash(next.zzdu.floatValue());
                        break;
                    case 1:
                        next = new Dot();
                        continue;
                    case 2:
                        patternItem = new Gap(next.zzdu.floatValue());
                        break;
                    default:
                        String str = TAG;
                        int i = next.type;
                        StringBuilder sb = new StringBuilder(37);
                        sb.append("Unknown PatternItem type: ");
                        sb.append(i);
                        Log.w(str, sb.toString());
                        continue;
                }
                next = patternItem;
            }
            arrayList.add(next);
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && Objects.equal(this.zzdu, patternItem.zzdu);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.zzdu);
    }

    public String toString() {
        int i = this.type;
        String valueOf = String.valueOf(this.zzdu);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzdu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
