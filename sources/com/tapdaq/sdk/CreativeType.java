package com.tapdaq.sdk;

import android.content.Context;
import com.tapdaq.sdk.helpers.TDDeviceInfo;

public enum CreativeType {
    INTERSTITIAL_LANDSCAPE,
    INTERSTITIAL_PORTRAIT;
    
    private static final String CREATIVE_TYPE_INTERSTITIAL_LANDSCAPE_FAT = "INTERSTITIAL_LANDSCAPE_FAT";
    private static final String CREATIVE_TYPE_INTERSTITIAL_LANSCAPE_SLIM = "INTERSTITIAL_LANDSCAPE_SLIM";
    private static final String CREATIVE_TYPE_INTERSTITIAL_PORTRAIT_FAT = "INTERSTITIAL_PORTRAIT_FAT";
    private static final String CREATIVE_TYPE_INTERSTITIAL_PORTRAIT_SLIM = "INTERSTITIAL_PORTRAIT_SLIM";
    private final int adType;

    public boolean isForAdType(int i) {
        return this.adType == i;
    }

    public static CreativeType toEnum(String str) {
        if (str.equals(CREATIVE_TYPE_INTERSTITIAL_LANDSCAPE_FAT) || str.equals(CREATIVE_TYPE_INTERSTITIAL_LANSCAPE_SLIM)) {
            return INTERSTITIAL_LANDSCAPE;
        }
        if (str.equals(CREATIVE_TYPE_INTERSTITIAL_PORTRAIT_FAT) || str.equals(CREATIVE_TYPE_INTERSTITIAL_PORTRAIT_SLIM)) {
            return INTERSTITIAL_PORTRAIT;
        }
        return valueOf(str);
    }

    public String toStringForJSON(Context context) {
        if (this == INTERSTITIAL_PORTRAIT) {
            return TDDeviceInfo.getIsSlim(context) ? CREATIVE_TYPE_INTERSTITIAL_PORTRAIT_SLIM : CREATIVE_TYPE_INTERSTITIAL_PORTRAIT_FAT;
        }
        if (this == INTERSTITIAL_LANDSCAPE) {
            return TDDeviceInfo.getIsSlim(context) ? CREATIVE_TYPE_INTERSTITIAL_LANSCAPE_SLIM : CREATIVE_TYPE_INTERSTITIAL_LANDSCAPE_FAT;
        }
        return toString();
    }
}
