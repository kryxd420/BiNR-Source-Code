package com.applovin.mediation.ads;

import android.app.Activity;
import android.text.TextUtils;
import com.applovin.impl.mediation.ads.MaxInterstitialImpl;
import com.applovin.mediation.MaxAdListener;
import com.applovin.sdk.AppLovinSdk;

public class MaxInterstitialAd {
    private final MaxInterstitialImpl a;

    public MaxInterstitialAd(String str, Activity activity) {
        this(str, AppLovinSdk.getInstance(activity), activity);
    }

    public MaxInterstitialAd(String str, AppLovinSdk appLovinSdk, Activity activity) {
        if (str == null) {
            throw new IllegalArgumentException("No ad unit ID specified");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Empty ad unit ID specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (appLovinSdk != null) {
            this.a = new MaxInterstitialImpl(str, appLovinSdk, activity);
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    public void destroy() {
        this.a.destroy();
    }

    public boolean isReady() {
        return this.a.isReady();
    }

    public void loadAd() {
        this.a.loadAd();
    }

    public void setExtraParameter(String str, String str2) {
        this.a.setExtraParameter(str, str2);
    }

    public void setListener(MaxAdListener maxAdListener) {
        this.a.setListener(maxAdListener);
    }

    public void showAd() {
        this.a.showAd();
    }

    public String toString() {
        return this.a.toString();
    }
}
