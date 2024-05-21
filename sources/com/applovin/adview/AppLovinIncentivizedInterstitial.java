package com.applovin.adview;

import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.a.a;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinIncentivizedInterstitial {
    private final a a;

    public AppLovinIncentivizedInterstitial(Context context) {
        this(AppLovinSdk.getInstance(context));
    }

    public AppLovinIncentivizedInterstitial(AppLovinSdk appLovinSdk) {
        this((String) null, appLovinSdk);
    }

    public AppLovinIncentivizedInterstitial(String str, AppLovinSdk appLovinSdk) {
        if (appLovinSdk != null) {
            this.a = createIncentivizedAdController(str, appLovinSdk);
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    public static AppLovinIncentivizedInterstitial create(Context context) {
        return create(AppLovinSdk.getInstance(context));
    }

    public static AppLovinIncentivizedInterstitial create(AppLovinSdk appLovinSdk) {
        return create((String) null, appLovinSdk);
    }

    public static AppLovinIncentivizedInterstitial create(String str, AppLovinSdk appLovinSdk) {
        return new AppLovinIncentivizedInterstitial(str, appLovinSdk);
    }

    /* access modifiers changed from: protected */
    public a createIncentivizedAdController(String str, AppLovinSdk appLovinSdk) {
        return new a(str, appLovinSdk);
    }

    public void dismiss() {
        this.a.c();
    }

    public String getUserIdentifier() {
        Log.e("AppLovinIncentivizedInterstitial", "Please use AppLovinSdk.getUserIdentifier() instead to properly identify your users in our system. This property is now deprecated and will be removed in a future SDK version.");
        return AppLovinSdk.a().get(0).getUserIdentifier();
    }

    public String getZoneId() {
        return this.a.b();
    }

    public boolean isAdReadyToDisplay() {
        return this.a.a();
    }

    public void preload(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (appLovinAdLoadListener == null) {
            Log.i("AppLovinIncentivizedInterstitial", "AppLovinAdLoadListener was null when preloading incentivized interstitials; using a listener is highly recommended.");
        }
        this.a.a(appLovinAdLoadListener);
    }

    public void setUserIdentifier(String str) {
        for (AppLovinSdk userIdentifier : AppLovinSdk.a()) {
            userIdentifier.setUserIdentifier(str);
        }
        Log.e("AppLovinIncentivizedInterstitial", "Please use AppLovinSdk.setUserIdentifier(\"" + str + "\") instead to properly identify your users in our system. This property is now deprecated and will be removed in a future SDK version.");
    }

    public void show(Context context) {
        show(context, (AppLovinAdRewardListener) null, (AppLovinAdVideoPlaybackListener) null);
    }

    public void show(Context context, AppLovinAdRewardListener appLovinAdRewardListener) {
        show(context, appLovinAdRewardListener, (AppLovinAdVideoPlaybackListener) null);
    }

    public void show(Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        show(context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, (AppLovinAdDisplayListener) null);
    }

    public void show(Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        show(context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, (AppLovinAdClickListener) null);
    }

    public void show(Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        show(context, (String) null, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    @Deprecated
    public void show(Context context, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.a.a((AppLovinAd) null, context, "", appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    public void show(AppLovinAd appLovinAd, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.a.a(appLovinAd, context, (String) null, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    public String toString() {
        return "AppLovinIncentivizedInterstitial{zoneId='" + getZoneId() + "', isAdReadyToDisplay=" + isAdReadyToDisplay() + '}';
    }
}
