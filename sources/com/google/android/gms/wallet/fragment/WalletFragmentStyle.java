package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class(creator = "WalletFragmentStyleCreator")
@SafeParcelable.Reserved({1})
public final class WalletFragmentStyle extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WalletFragmentStyle> CREATOR = new zzg();
    @SafeParcelable.Field(id = 2)
    private Bundle zzgb;
    @SafeParcelable.Field(id = 3)
    private int zzgc;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BuyButtonAppearance {
        public static final int ANDROID_PAY_DARK = 4;
        public static final int ANDROID_PAY_LIGHT = 5;
        public static final int ANDROID_PAY_LIGHT_WITH_BORDER = 6;
        @Deprecated
        public static final int GOOGLE_WALLET_CLASSIC = 1;
        @Deprecated
        public static final int GOOGLE_WALLET_GRAYSCALE = 2;
        @Deprecated
        public static final int GOOGLE_WALLET_MONOCHROME = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BuyButtonText {
        public static final int BUY_WITH = 5;
        public static final int DONATE_WITH = 7;
        public static final int LOGO_ONLY = 6;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Dimension {
        public static final int MATCH_PARENT = -1;
        public static final int UNIT_DIP = 1;
        public static final int UNIT_IN = 4;
        public static final int UNIT_MM = 5;
        public static final int UNIT_PT = 3;
        public static final int UNIT_PX = 0;
        public static final int UNIT_SP = 2;
        public static final int WRAP_CONTENT = -2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LogoImageType {
        public static final int ANDROID_PAY = 3;
        @Deprecated
        public static final int GOOGLE_WALLET_CLASSIC = 1;
        @Deprecated
        public static final int GOOGLE_WALLET_MONOCHROME = 2;
    }

    public WalletFragmentStyle() {
        this.zzgb = new Bundle();
        this.zzgb.putInt("buyButtonAppearanceDefault", 4);
        this.zzgb.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
    }

    @SafeParcelable.Constructor
    WalletFragmentStyle(@SafeParcelable.Param(id = 2) Bundle bundle, @SafeParcelable.Param(id = 3) int i) {
        this.zzgb = bundle;
        this.zzgc = i;
    }

    private static long zza(int i) {
        if (i >= 0) {
            return zza(0, (float) i);
        }
        if (i == -1 || i == -2) {
            return zzc(129, i);
        }
        StringBuilder sb = new StringBuilder(39);
        sb.append("Unexpected dimension value: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    private static long zza(int i, float f) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return zzc(i, Float.floatToIntBits(f));
            default:
                StringBuilder sb = new StringBuilder(30);
                sb.append("Unrecognized unit: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private final void zza(TypedArray typedArray, int i, String str) {
        TypedValue peekValue;
        long j;
        if (!this.zzgb.containsKey(str) && (peekValue = typedArray.peekValue(i)) != null) {
            Bundle bundle = this.zzgb;
            int i2 = peekValue.type;
            if (i2 == 5) {
                j = zzc(128, peekValue.data);
            } else if (i2 == 16) {
                j = zza(peekValue.data);
            } else {
                int i3 = peekValue.type;
                StringBuilder sb = new StringBuilder(38);
                sb.append("Unexpected dimension type: ");
                sb.append(i3);
                throw new IllegalArgumentException(sb.toString());
            }
            bundle.putLong(str, j);
        }
    }

    private final void zza(TypedArray typedArray, int i, String str, String str2) {
        TypedValue peekValue;
        if (!this.zzgb.containsKey(str) && !this.zzgb.containsKey(str2) && (peekValue = typedArray.peekValue(i)) != null) {
            if (peekValue.type < 28 || peekValue.type > 31) {
                this.zzgb.putInt(str2, peekValue.resourceId);
            } else {
                this.zzgb.putInt(str, peekValue.data);
            }
        }
    }

    private final void zzb(TypedArray typedArray, int i, String str) {
        TypedValue peekValue;
        if (!this.zzgb.containsKey(str) && (peekValue = typedArray.peekValue(i)) != null) {
            this.zzgb.putInt(str, peekValue.data);
        }
    }

    private static long zzc(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    public final WalletFragmentStyle setBuyButtonAppearance(int i) {
        this.zzgb.putInt("buyButtonAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int i) {
        this.zzgb.putLong("buyButtonHeight", zza(i));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int i, float f) {
        this.zzgb.putLong("buyButtonHeight", zza(i, f));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonText(int i) {
        this.zzgb.putInt("buyButtonText", i);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int i) {
        this.zzgb.putLong("buyButtonWidth", zza(i));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int i, float f) {
        this.zzgb.putLong("buyButtonWidth", zza(i, f));
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int i) {
        this.zzgb.remove("maskedWalletDetailsBackgroundResource");
        this.zzgb.putInt("maskedWalletDetailsBackgroundColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int i) {
        this.zzgb.remove("maskedWalletDetailsBackgroundColor");
        this.zzgb.putInt("maskedWalletDetailsBackgroundResource", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int i) {
        this.zzgb.remove("maskedWalletDetailsButtonBackgroundResource");
        this.zzgb.putInt("maskedWalletDetailsButtonBackgroundColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int i) {
        this.zzgb.remove("maskedWalletDetailsButtonBackgroundColor");
        this.zzgb.putInt("maskedWalletDetailsButtonBackgroundResource", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int i) {
        this.zzgb.putInt("maskedWalletDetailsButtonTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int i) {
        this.zzgb.putInt("maskedWalletDetailsHeaderTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int i) {
        this.zzgb.putInt("maskedWalletDetailsLogoImageType", i);
        return this;
    }

    @Deprecated
    public final WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int i) {
        this.zzgb.putInt("maskedWalletDetailsLogoTextColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int i) {
        this.zzgb.putInt("maskedWalletDetailsTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setStyleResourceId(int i) {
        this.zzgc = i;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzgb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzgc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza(String str, DisplayMetrics displayMetrics, int i) {
        int i2;
        if (!this.zzgb.containsKey(str)) {
            return i;
        }
        long j = this.zzgb.getLong(str);
        int i3 = (int) (j >>> 32);
        int i4 = (int) j;
        switch (i3) {
            case 0:
                i2 = 0;
                break;
            case 1:
                i2 = 1;
                break;
            case 2:
                i2 = 2;
                break;
            case 3:
                i2 = 3;
                break;
            case 4:
                i2 = 4;
                break;
            case 5:
                i2 = 5;
                break;
            default:
                switch (i3) {
                    case 128:
                        return TypedValue.complexToDimensionPixelSize(i4, displayMetrics);
                    case 129:
                        return i4;
                    default:
                        StringBuilder sb = new StringBuilder(36);
                        sb.append("Unexpected unit or type: ");
                        sb.append(i3);
                        throw new IllegalStateException(sb.toString());
                }
        }
        return Math.round(TypedValue.applyDimension(i2, Float.intBitsToFloat(i4), displayMetrics));
    }

    public final void zza(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.zzgc <= 0 ? R.style.WalletFragmentDefaultStyle : this.zzgc, R.styleable.WalletFragmentStyle);
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }
}
