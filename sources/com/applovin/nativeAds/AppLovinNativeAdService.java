package com.applovin.nativeAds;

public interface AppLovinNativeAdService {
    @Deprecated
    boolean hasPreloadedAdForZoneId(String str);

    @Deprecated
    void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener);

    @Deprecated
    void loadNativeAds(int i, String str, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener);

    void loadNextAd(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener);

    void precacheResources(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener);

    @Deprecated
    void preloadAdForZoneId(String str);
}
