package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

@SafeParcelable.Class(creator = "WalletFragmentInitParamsCreator")
@SafeParcelable.Reserved({1})
public final class WalletFragmentInitParams extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<WalletFragmentInitParams> CREATOR = new zzd();
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getAccountName", id = 2)
    public String zzci;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getMaskedWalletRequest", id = 3)
    public MaskedWalletRequest zzfi;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getMaskedWallet", id = 5)
    public MaskedWallet zzfj;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(defaultValue = "-1", getter = "getMaskedWalletRequestCode", id = 4)
    public int zzfx;

    public final class Builder {
        private Builder() {
        }

        public final WalletFragmentInitParams build() {
            boolean z = false;
            Preconditions.checkState((WalletFragmentInitParams.this.zzfj != null && WalletFragmentInitParams.this.zzfi == null) || (WalletFragmentInitParams.this.zzfj == null && WalletFragmentInitParams.this.zzfi != null), "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            if (WalletFragmentInitParams.this.zzfx >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }

        public final Builder setAccountName(String str) {
            String unused = WalletFragmentInitParams.this.zzci = str;
            return this;
        }

        public final Builder setMaskedWallet(MaskedWallet maskedWallet) {
            MaskedWallet unused = WalletFragmentInitParams.this.zzfj = maskedWallet;
            return this;
        }

        public final Builder setMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            MaskedWalletRequest unused = WalletFragmentInitParams.this.zzfi = maskedWalletRequest;
            return this;
        }

        public final Builder setMaskedWalletRequestCode(int i) {
            int unused = WalletFragmentInitParams.this.zzfx = i;
            return this;
        }
    }

    private WalletFragmentInitParams() {
        this.zzfx = -1;
    }

    @SafeParcelable.Constructor
    WalletFragmentInitParams(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) MaskedWalletRequest maskedWalletRequest, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) MaskedWallet maskedWallet) {
        this.zzci = str;
        this.zzfi = maskedWalletRequest;
        this.zzfx = i;
        this.zzfj = maskedWallet;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final String getAccountName() {
        return this.zzci;
    }

    public final MaskedWallet getMaskedWallet() {
        return this.zzfj;
    }

    public final MaskedWalletRequest getMaskedWalletRequest() {
        return this.zzfi;
    }

    public final int getMaskedWalletRequestCode() {
        return this.zzfx;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAccountName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getMaskedWalletRequest(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, getMaskedWalletRequestCode());
        SafeParcelWriter.writeParcelable(parcel, 5, getMaskedWallet(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
