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

@SafeParcelable.Class(creator = "CardRequirementsCreator")
public final class CardRequirements extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CardRequirements> CREATOR = new zze();
    @SafeParcelable.Field(id = 1)
    ArrayList<Integer> zzai;
    @SafeParcelable.Field(defaultValue = "true", id = 2)
    boolean zzaj;
    @SafeParcelable.Field(id = 3)
    boolean zzak;
    @SafeParcelable.Field(id = 4)
    int zzal;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (CardRequirements.this.zzai == null) {
                CardRequirements.this.zzai = new ArrayList<>();
            }
            CardRequirements.this.zzai.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(@NonNull Collection<Integer> collection) {
            Preconditions.checkArgument(collection != null && !collection.isEmpty(), "allowedCardNetworks can't be null or empty! You must provide a valid value from WalletConstants.CardNetwork.");
            if (CardRequirements.this.zzai == null) {
                CardRequirements.this.zzai = new ArrayList<>();
            }
            CardRequirements.this.zzai.addAll(collection);
            return this;
        }

        public final CardRequirements build() {
            Preconditions.checkNotNull(CardRequirements.this.zzai, "Allowed card networks must be non-empty! You can set it through addAllowedCardNetwork() or addAllowedCardNetworks() in the CardRequirements Builder.");
            return CardRequirements.this;
        }

        public final Builder setAllowPrepaidCards(boolean z) {
            CardRequirements.this.zzaj = z;
            return this;
        }

        public final Builder setBillingAddressFormat(int i) {
            CardRequirements.this.zzal = i;
            return this;
        }

        public final Builder setBillingAddressRequired(boolean z) {
            CardRequirements.this.zzak = z;
            return this;
        }
    }

    private CardRequirements() {
        this.zzaj = true;
    }

    @SafeParcelable.Constructor
    CardRequirements(@SafeParcelable.Param(id = 1) ArrayList<Integer> arrayList, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) int i) {
        this.zzai = arrayList;
        this.zzaj = z;
        this.zzak = z2;
        this.zzal = i;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final boolean allowPrepaidCards() {
        return this.zzaj;
    }

    @Nullable
    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzai;
    }

    public final int getBillingAddressFormat() {
        return this.zzal;
    }

    public final boolean isBillingAddressRequired() {
        return this.zzak;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 1, this.zzai, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzaj);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzak);
        SafeParcelWriter.writeInt(parcel, 4, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
