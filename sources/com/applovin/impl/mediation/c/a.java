package com.applovin.impl.mediation.c;

import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;

public class a implements MaxAdListener, MaxAdViewAdListener, MaxRewardedAdListener {
    private final j a;
    private final MaxAdListener b;

    public a(MaxAdListener maxAdListener, j jVar) {
        this.a = jVar;
        this.b = maxAdListener;
    }

    public void onAdClicked(MaxAd maxAd) {
        h.d(this.b, maxAd, this.a);
    }

    public void onAdCollapsed(MaxAd maxAd) {
        h.h(this.b, maxAd, this.a);
    }

    public void onAdDisplayFailed(MaxAd maxAd, int i) {
        h.a(this.b, maxAd, i, this.a);
    }

    public void onAdDisplayed(MaxAd maxAd) {
        h.b(this.b, maxAd, this.a);
    }

    public void onAdExpanded(MaxAd maxAd) {
        h.g(this.b, maxAd, this.a);
    }

    public void onAdHidden(MaxAd maxAd) {
        h.c(this.b, maxAd, this.a);
    }

    public void onAdLoadFailed(String str, int i) {
        h.a(this.b, str, i, this.a);
    }

    public void onAdLoaded(MaxAd maxAd) {
        h.a(this.b, maxAd, this.a);
    }

    public void onRewardedVideoCompleted(MaxAd maxAd) {
        h.f(this.b, maxAd, this.a);
    }

    public void onRewardedVideoStarted(MaxAd maxAd) {
        h.e(this.b, maxAd, this.a);
    }

    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
        h.a(this.b, maxAd, maxReward, this.a);
    }
}
