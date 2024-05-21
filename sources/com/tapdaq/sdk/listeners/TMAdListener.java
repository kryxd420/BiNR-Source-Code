package com.tapdaq.sdk.listeners;

import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.model.rewards.TDReward;
import java.util.Map;

public class TMAdListener implements TMRewardAdListenerBase {
    private boolean mAutoRetry = true;

    public void didClick() {
    }

    public void didClose() {
    }

    public void didComplete() {
    }

    public void didCustomEvent(Map<Object, Object> map) {
    }

    public void didDisplay() {
    }

    public void didEngagement() {
    }

    public void didFailToDisplay(TMAdError tMAdError) {
    }

    public void didFailToLoad(TMAdError tMAdError) {
    }

    public void didLoad() {
    }

    public void didLoad(TDMediatedNativeAd tDMediatedNativeAd) {
    }

    public void didRefresh() {
    }

    public void didRewardFail(TMAdError tMAdError) {
    }

    public void didVerify(TDReward tDReward) {
    }

    @Deprecated
    public void didVerify(String str, String str2, int i, boolean z, Map<Object, Object> map) {
    }

    @Deprecated
    public void didVerify(String str, String str2, Double d) {
    }

    public void onUserDeclined() {
    }

    public void willDisplay() {
    }

    public void setAutoRetry(boolean z) {
        this.mAutoRetry = z;
    }

    public boolean shouldAutoRetry() {
        return this.mAutoRetry;
    }
}
