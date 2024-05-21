package com.tapdaq.sdk.debug;

import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.model.rewards.TDReward;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TMDebuggerAdListener extends TMAdListener {
    private TMDebugAdapter mDebugAdapter;
    private List<TDMediatedNativeAd> mMediatedNativeAds = new ArrayList();

    public List<TDMediatedNativeAd> getNativeAds() {
        return this.mMediatedNativeAds;
    }

    public void removeNativeAd(TDMediatedNativeAd tDMediatedNativeAd) {
        this.mMediatedNativeAds.remove(tDMediatedNativeAd);
    }

    TMDebuggerAdListener(TMDebugAdapter tMDebugAdapter) {
        this.mDebugAdapter = tMDebugAdapter;
    }

    public void didClose() {
        log("didClose");
    }

    public void didFailToLoad(TMAdError tMAdError) {
        log(String.format(Locale.ENGLISH, "didFailToLoad: %s", new Object[]{tMAdError.toString()}));
    }

    public void didClick() {
        log("didClick");
    }

    public void willDisplay() {
        log("willDisplay");
    }

    public void didDisplay() {
        log("didDisplay");
    }

    public void didFailToDisplay(TMAdError tMAdError) {
        log(String.format(Locale.ENGLISH, "didFailToDisplay: %s", new Object[]{tMAdError.toString()}));
    }

    public void didLoad() {
        log("didLoad");
    }

    public void didRefresh() {
        log("didRefresh");
    }

    public void didLoad(TDMediatedNativeAd tDMediatedNativeAd) {
        didLoad();
        this.mMediatedNativeAds.add(tDMediatedNativeAd);
    }

    public void didCustomEvent(Map<Object, Object> map) {
        log("didCustomEvent " + map.toString());
    }

    public void didComplete() {
        log("didComplete");
    }

    public void didEngagement() {
        log("didEngagement");
    }

    public void didRewardFail(TMAdError tMAdError) {
        super.didRewardFail(tMAdError);
        log("didRewardFail. Errorcode: " + tMAdError.getErrorCode() + " Message: " + tMAdError.getErrorMessage());
    }

    public void onUserDeclined() {
        log("onUserDeclined");
    }

    public void didVerify(TDReward tDReward) {
        super.didVerify(tDReward);
        log(String.format(Locale.ENGLISH, "didVerify - Placement: %s. Reward: %s. Value: %d. Valid: %b. Custom Data: %s", new Object[]{tDReward.getTag(), tDReward.getName(), Integer.valueOf(tDReward.getValue()), Boolean.valueOf(tDReward.isValid()), tDReward.getCustom_json().toString()}));
    }

    public void log(String str) {
        if (this.mDebugAdapter != null) {
            this.mDebugAdapter.insert(str, 0);
        }
    }
}
