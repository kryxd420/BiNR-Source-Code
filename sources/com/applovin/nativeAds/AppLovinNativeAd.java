package com.applovin.nativeAds;

import android.content.Context;
import com.applovin.sdk.AppLovinPostbackListener;

public interface AppLovinNativeAd {
    long getAdId();

    String getCaptionText();

    @Deprecated
    String getClickUrl();

    String getCtaText();

    String getDescriptionText();

    String getIconUrl();

    String getImageUrl();

    String getImpressionTrackingUrl();

    float getStarRating();

    String getTitle();

    String getVideoEndTrackingUrl(int i, boolean z);

    String getVideoStartTrackingUrl();

    String getVideoUrl();

    String getZoneId();

    boolean isImagePrecached();

    boolean isVideoPrecached();

    void launchClickTarget(Context context);

    void trackImpression();

    void trackImpression(AppLovinPostbackListener appLovinPostbackListener);
}
