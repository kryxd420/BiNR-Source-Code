package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "CartCreator")
@SafeParcelable.Reserved({1})
public final class Cart extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Cart> CREATOR = new zzg();
    @SafeParcelable.Field(id = 2)
    String zzan;
    @SafeParcelable.Field(id = 3)
    String zzao;
    @SafeParcelable.Field(defaultValueUnchecked = "new java.util.ArrayList<LineItem>()", id = 4)
    ArrayList<LineItem> zzap;

    public final class Builder {
        private Builder() {
        }

        public final Builder addLineItem(LineItem lineItem) {
            Cart.this.zzap.add(lineItem);
            return this;
        }

        public final Cart build() {
            return Cart.this;
        }

        public final Builder setCurrencyCode(String str) {
            Cart.this.zzao = str;
            return this;
        }

        public final Builder setLineItems(List<LineItem> list) {
            Cart.this.zzap.clear();
            Cart.this.zzap.addAll(list);
            return this;
        }

        public final Builder setTotalPrice(String str) {
            Cart.this.zzan = str;
            return this;
        }
    }

    Cart() {
        this.zzap = new ArrayList<>();
    }

    @SafeParcelable.Constructor
    Cart(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) ArrayList<LineItem> arrayList) {
        this.zzan = str;
        this.zzao = str2;
        this.zzap = arrayList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final String getCurrencyCode() {
        return this.zzao;
    }

    public final ArrayList<LineItem> getLineItems() {
        return this.zzap;
    }

    public final String getTotalPrice() {
        return this.zzan;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzap, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
