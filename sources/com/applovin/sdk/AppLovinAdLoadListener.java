package com.applovin.sdk;

public interface AppLovinAdLoadListener {
    void adReceived(AppLovinAd appLovinAd);

    void failedToReceiveAd(int i);
}
