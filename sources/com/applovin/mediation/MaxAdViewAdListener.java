package com.applovin.mediation;

public interface MaxAdViewAdListener extends MaxAdListener {
    void onAdCollapsed(MaxAd maxAd);

    void onAdExpanded(MaxAd maxAd);
}
