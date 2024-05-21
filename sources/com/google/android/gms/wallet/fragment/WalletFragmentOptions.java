package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.R;

@SafeParcelable.Class(creator = "WalletFragmentOptionsCreator")
@SafeParcelable.Reserved({1})
public final class WalletFragmentOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<WalletFragmentOptions> CREATOR = new zzf();
    /* access modifiers changed from: private */
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.wallet.WalletConstants.ENVIRONMENT_PRODUCTION", getter = "getEnvironment", id = 2)
    public int environment;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.wallet.fragment.WalletFragmentMode.BUY_BUTTON", getter = "getMode", id = 5)
    public int mode;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.wallet.WalletConstants.THEME_DARK", getter = "getTheme", id = 3)
    public int theme;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getFragmentStyle", id = 4)
    public WalletFragmentStyle zzfz;

    public final class Builder {
        private Builder() {
        }

        public final WalletFragmentOptions build() {
            return WalletFragmentOptions.this;
        }

        public final Builder setEnvironment(int i) {
            int unused = WalletFragmentOptions.this.environment = i;
            return this;
        }

        public final Builder setFragmentStyle(int i) {
            WalletFragmentStyle unused = WalletFragmentOptions.this.zzfz = new WalletFragmentStyle().setStyleResourceId(i);
            return this;
        }

        public final Builder setFragmentStyle(WalletFragmentStyle walletFragmentStyle) {
            WalletFragmentStyle unused = WalletFragmentOptions.this.zzfz = walletFragmentStyle;
            return this;
        }

        public final Builder setMode(int i) {
            int unused = WalletFragmentOptions.this.mode = i;
            return this;
        }

        public final Builder setTheme(int i) {
            int unused = WalletFragmentOptions.this.theme = i;
            return this;
        }
    }

    private WalletFragmentOptions() {
        this.environment = 3;
        this.zzfz = new WalletFragmentStyle();
    }

    @SafeParcelable.Constructor
    WalletFragmentOptions(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) WalletFragmentStyle walletFragmentStyle, @SafeParcelable.Param(id = 5) int i3) {
        this.environment = i;
        this.theme = i2;
        this.zzfz = walletFragmentStyle;
        this.mode = i3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static WalletFragmentOptions zza(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WalletFragmentOptions);
        int i = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_appTheme, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_environment, 1);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.WalletFragmentOptions_fragmentStyle, 0);
        int i3 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_fragmentMode, 1);
        obtainStyledAttributes.recycle();
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.theme = i;
        walletFragmentOptions.environment = i2;
        walletFragmentOptions.zzfz = new WalletFragmentStyle().setStyleResourceId(resourceId);
        walletFragmentOptions.zzfz.zza(context);
        walletFragmentOptions.mode = i3;
        return walletFragmentOptions;
    }

    public final int getEnvironment() {
        return this.environment;
    }

    public final WalletFragmentStyle getFragmentStyle() {
        return this.zzfz;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getTheme() {
        return this.theme;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getEnvironment());
        SafeParcelWriter.writeInt(parcel, 3, getTheme());
        SafeParcelWriter.writeParcelable(parcel, 4, getFragmentStyle(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getMode());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final void zza(Context context) {
        if (this.zzfz != null) {
            this.zzfz.zza(context);
        }
    }
}
