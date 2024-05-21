package com.applovin.adview;

import android.content.Context;
import android.util.AttributeSet;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

@Deprecated
public interface AdViewController {
    void contractAd();

    void destroy();

    void expandAd();

    AppLovinAdSize getSize();

    String getZoneId();

    void initializeAdView(AppLovinAdView appLovinAdView, Context context, AppLovinAdSize appLovinAdSize, String str, AppLovinSdk appLovinSdk, AttributeSet attributeSet);

    boolean isAdReadyToDisplay();

    boolean isAutoDestroy();

    void loadNextAd();

    void onDetachedFromWindow();

    void onVisibilityChanged(int i);

    void pause();

    void renderAd(AppLovinAd appLovinAd);

    @Deprecated
    void renderAd(AppLovinAd appLovinAd, String str);

    void resume();

    void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener);

    void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener);

    void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener);

    void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener);

    void setAdViewEventListener(AppLovinAdViewEventListener appLovinAdViewEventListener);

    void setAutoDestroy(boolean z);
}
