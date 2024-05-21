package com.applovin.nativeAds;

import java.util.List;

public interface AppLovinNativeAdLoadListener {
    void onNativeAdsFailedToLoad(int i);

    void onNativeAdsLoaded(List<AppLovinNativeAd> list);
}
