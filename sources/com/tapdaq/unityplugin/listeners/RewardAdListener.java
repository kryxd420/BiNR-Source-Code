package com.tapdaq.unityplugin.listeners;

import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMRewardAdListenerBase;
import com.tapdaq.sdk.model.rewards.TDReward;
import com.tapdaq.unityplugin.JSONHelper;
import com.unity3d.player.UnityPlayer;
import org.json.JSONObject;

public class RewardAdListener extends VideoAdListener implements TMRewardAdListenerBase {
    public RewardAdListener(String str) {
        super(str);
        this.typeString = "REWARD_AD";
    }

    /* access modifiers changed from: protected */
    public void SendRewardJsonToUnity(String str, TDReward tDReward) {
        JSONObject jSONObject = new JSONObject();
        JSONHelper.AddValue(jSONObject, "EventId", tDReward.getEventId());
        JSONHelper.AddValue(jSONObject, "Location", tDReward.getTag());
        JSONHelper.AddValue(jSONObject, "RewardName", tDReward.getName());
        JSONHelper.AddValue(jSONObject, "RewardAmount", Integer.valueOf(tDReward.getValue()));
        JSONHelper.AddValue(jSONObject, "RewardValid", Boolean.valueOf(tDReward.isValid()));
        JSONHelper.AddValue(jSONObject, "RewardJSON", JSONHelper.ObjectToJsonStr(tDReward.getCustom_json()));
        if (this.tag != null) {
            JSONHelper.AddValue(jSONObject, "tag", this.tag);
        }
        UnityPlayer.UnitySendMessage("TapdaqV1", str, jSONObject.toString());
    }

    public void didRewardFail(TMAdError tMAdError) {
        super.didRewardFail(tMAdError);
        SendJsonToUnity("_didFail", tMAdError.getErrorMessage());
    }

    public void onUserDeclined() {
        SendToUnity("_onUserDeclined");
    }

    public void didVerify(TDReward tDReward) {
        SendRewardJsonToUnity("_didVerify", tDReward);
    }
}
