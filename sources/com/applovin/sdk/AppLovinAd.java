package com.applovin.sdk;

public interface AppLovinAd {
    long getAdIdNumber();

    String getAdValue(String str);

    AppLovinAdSize getSize();

    AppLovinAdType getType();

    String getZoneId();

    boolean isVideoAd();
}
