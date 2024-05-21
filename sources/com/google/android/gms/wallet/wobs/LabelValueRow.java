package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "LabelValueRowCreator")
@SafeParcelable.Reserved({1})
public final class LabelValueRow extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LabelValueRow> CREATOR = new zze();
    @SafeParcelable.Field(id = 2)
    String zzgn;
    @SafeParcelable.Field(id = 3)
    String zzgo;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 4)
    ArrayList<LabelValue> zzgp;

    public final class Builder {
        private Builder() {
        }

        public final Builder addColumn(LabelValue labelValue) {
            LabelValueRow.this.zzgp.add(labelValue);
            return this;
        }

        public final Builder addColumns(Collection<LabelValue> collection) {
            LabelValueRow.this.zzgp.addAll(collection);
            return this;
        }

        public final LabelValueRow build() {
            return LabelValueRow.this;
        }

        public final Builder setHexBackgroundColor(String str) {
            LabelValueRow.this.zzgo = str;
            return this;
        }

        public final Builder setHexFontColor(String str) {
            LabelValueRow.this.zzgn = str;
            return this;
        }
    }

    LabelValueRow() {
        this.zzgp = ArrayUtils.newArrayList();
    }

    @SafeParcelable.Constructor
    LabelValueRow(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) ArrayList<LabelValue> arrayList) {
        this.zzgn = str;
        this.zzgo = str2;
        this.zzgp = arrayList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final ArrayList<LabelValue> getColumns() {
        return this.zzgp;
    }

    public final String getHexBackgroundColor() {
        return this.zzgo;
    }

    public final String getHexFontColor() {
        return this.zzgn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzgn, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzgo, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzgp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
