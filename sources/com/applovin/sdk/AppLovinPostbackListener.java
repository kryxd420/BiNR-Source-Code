package com.applovin.sdk;

public interface AppLovinPostbackListener {
    void onPostbackFailure(String str, int i);

    void onPostbackSuccess(String str);
}
