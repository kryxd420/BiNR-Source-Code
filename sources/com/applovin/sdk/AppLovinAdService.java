package com.applovin.sdk;

import java.util.List;

public interface AppLovinAdService {
    @Deprecated
    public static final String URI_AD_SERVICE = "/adservice";
    @Deprecated
    public static final String URI_API_SERVICE = "/api";
    @Deprecated
    public static final String URI_CLOSE_AD = "/adservice/close_ad";
    @Deprecated
    public static final String URI_CONTRACT_AD = "/adservice/contract_ad";
    @Deprecated
    public static final String URI_EXPAND_AD = "/adservice/expand_ad";
    @Deprecated
    public static final String URI_LAUNCH_APP = "/launch";
    @Deprecated
    public static final String URI_NEXT_AD = "/adservice/next_ad";

    @Deprecated
    void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener);

    @Deprecated
    void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize);

    String getBidToken();

    @Deprecated
    boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize);

    @Deprecated
    boolean hasPreloadedAdForZoneId(String str);

    void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener);

    void loadNextAdForAdToken(String str, AppLovinAdLoadListener appLovinAdLoadListener);

    void loadNextAdForZoneId(String str, AppLovinAdLoadListener appLovinAdLoadListener);

    void loadNextAdForZoneIds(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener);

    @Deprecated
    void preloadAd(AppLovinAdSize appLovinAdSize);

    @Deprecated
    void preloadAdForZoneId(String str);

    @Deprecated
    void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize);
}
