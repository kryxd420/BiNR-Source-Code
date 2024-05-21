package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "ShippingAddressRequirementsCreator")
public final class ShippingAddressRequirements extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ShippingAddressRequirements> CREATOR = new zzam();
    @SafeParcelable.Field(id = 1)
    ArrayList<String> zzel;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCountryCode(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "allowedCountryCode can't be null or empty! If you don't have restrictions, just leave it unset.");
            if (ShippingAddressRequirements.this.zzel == null) {
                ShippingAddressRequirements.this.zzel = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zzel.add(str);
            return this;
        }

        public final Builder addAllowedCountryCodes(@NonNull Collection<String> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("allowedCountryCodes can't be null or empty! If you don't have restrictions, just leave it unset.");
            }
            if (ShippingAddressRequirements.this.zzel == null) {
                ShippingAddressRequirements.this.zzel = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zzel.addAll(collection);
            return this;
        }

        public final ShippingAddressRequirements build() {
            return ShippingAddressRequirements.this;
        }
    }

    private ShippingAddressRequirements() {
    }

    @SafeParcelable.Constructor
    ShippingAddressRequirements(@SafeParcelable.Param(id = 1) ArrayList<String> arrayList) {
        this.zzel = arrayList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Nullable
    public final ArrayList<String> getAllowedCountryCodes() {
        return this.zzel;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, this.zzel, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
