package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LoyaltyPointsBalanceCreator")
@SafeParcelable.Reserved({1})
public final class LoyaltyPointsBalance extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LoyaltyPointsBalance> CREATOR = new zzh();
    @SafeParcelable.Field(id = 5)
    String zzbn;
    @SafeParcelable.Field(id = 2)
    int zzgt;
    @SafeParcelable.Field(id = 3)
    String zzgu;
    @SafeParcelable.Field(id = 4)
    double zzgv;
    @SafeParcelable.Field(id = 6)
    long zzgw;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.wallet.wobs.LoyaltyPointsBalance.Type.UNDEFINED", id = 7)
    int zzgx;

    public final class Builder {
        private Builder() {
        }

        public final LoyaltyPointsBalance build() {
            return LoyaltyPointsBalance.this;
        }

        public final Builder setDouble(double d) {
            LoyaltyPointsBalance.this.zzgv = d;
            LoyaltyPointsBalance.this.zzgx = 2;
            return this;
        }

        public final Builder setInt(int i) {
            LoyaltyPointsBalance.this.zzgt = i;
            LoyaltyPointsBalance.this.zzgx = 0;
            return this;
        }

        public final Builder setMoney(String str, long j) {
            LoyaltyPointsBalance.this.zzbn = str;
            LoyaltyPointsBalance.this.zzgw = j;
            LoyaltyPointsBalance.this.zzgx = 3;
            return this;
        }

        public final Builder setString(String str) {
            LoyaltyPointsBalance.this.zzgu = str;
            LoyaltyPointsBalance.this.zzgx = 1;
            return this;
        }
    }

    public interface Type {
        public static final int DOUBLE = 2;
        public static final int INT = 0;
        public static final int MONEY = 3;
        public static final int STRING = 1;
        public static final int UNDEFINED = -1;
    }

    LoyaltyPointsBalance() {
        this.zzgx = -1;
        this.zzgt = -1;
        this.zzgv = -1.0d;
    }

    @SafeParcelable.Constructor
    LoyaltyPointsBalance(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) double d, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) long j, @SafeParcelable.Param(id = 7) int i2) {
        this.zzgt = i;
        this.zzgu = str;
        this.zzgv = d;
        this.zzbn = str2;
        this.zzgw = j;
        this.zzgx = i2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final String getCurrencyCode() {
        return this.zzbn;
    }

    public final long getCurrencyMicros() {
        return this.zzgw;
    }

    public final double getDouble() {
        return this.zzgv;
    }

    public final int getInt() {
        return this.zzgt;
    }

    public final String getString() {
        return this.zzgu;
    }

    public final int getType() {
        return this.zzgx;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzgt);
        SafeParcelWriter.writeString(parcel, 3, this.zzgu, false);
        SafeParcelWriter.writeDouble(parcel, 4, this.zzgv);
        SafeParcelWriter.writeString(parcel, 5, this.zzbn, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzgw);
        SafeParcelWriter.writeInt(parcel, 7, this.zzgx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
