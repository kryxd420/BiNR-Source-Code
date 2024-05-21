package com.applovin.sdk;

public interface AppLovinPostbackService {
    void dispatchPostbackAsync(String str, AppLovinPostbackListener appLovinPostbackListener);
}
