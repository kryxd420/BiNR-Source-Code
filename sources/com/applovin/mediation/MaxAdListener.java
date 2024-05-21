package com.applovin.mediation;

public interface MaxAdListener {
    void onAdClicked(MaxAd maxAd);

    void onAdDisplayFailed(MaxAd maxAd, int i);

    void onAdDisplayed(MaxAd maxAd);

    void onAdHidden(MaxAd maxAd);

    void onAdLoadFailed(String str, int i);

    void onAdLoaded(MaxAd maxAd);
}
