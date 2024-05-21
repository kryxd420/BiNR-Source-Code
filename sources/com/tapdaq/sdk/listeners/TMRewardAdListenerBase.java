package com.tapdaq.sdk.listeners;

import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.model.rewards.TDReward;
import java.util.Map;

public interface TMRewardAdListenerBase extends TMVideoAdListenerBase {
    void didRewardFail(TMAdError tMAdError);

    void didVerify(TDReward tDReward);

    @Deprecated
    void didVerify(String str, String str2, int i, boolean z, Map<Object, Object> map);

    @Deprecated
    void didVerify(String str, String str2, Double d);

    void onUserDeclined();
}
