package com.tapdaq.adapters;

import android.content.Context;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.listeners.TapdaqAdEventHandler;

public class TDApplovinNativeAd extends TDMediatedNativeAd {
    public TDApplovinNativeAd(int i, TapdaqAd tapdaqAd, TapdaqAdEventHandler tapdaqAdEventHandler) {
        super(i, tapdaqAd, tapdaqAdEventHandler);
    }

    public void trackImpression(Context context) {
        super.trackImpression(context);
        Object nativeAd = getNativeAd();
        if (nativeAd instanceof AppLovinNativeAd) {
            ((AppLovinNativeAd) nativeAd).trackImpression();
        }
    }
}
