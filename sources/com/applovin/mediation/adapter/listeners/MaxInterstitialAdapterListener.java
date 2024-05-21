package com.applovin.mediation.adapter.listeners;

import com.applovin.mediation.adapter.MaxAdapterError;

public interface MaxInterstitialAdapterListener extends MaxAdapterListener {
    void onInterstitialAdClicked();

    void onInterstitialAdDisplayFailed(MaxAdapterError maxAdapterError);

    void onInterstitialAdDisplayed();

    void onInterstitialAdHidden();

    void onInterstitialAdLoadFailed(MaxAdapterError maxAdapterError);

    void onInterstitialAdLoaded();
}
