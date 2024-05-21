package com.tapdaq.unityplugin.listeners;

import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.unityplugin.JSONHelper;
import com.tapjoy.TJAdUnitConstants;
import com.unity3d.player.UnityPlayer;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AdListener extends TMAdListener {
    protected String tag;
    protected String typeString = "";

    public AdListener(String str) {
        this.tag = str;
    }

    /* access modifiers changed from: protected */
    public void SendToUnity(String str) {
        UnityPlayer.UnitySendMessage("TapdaqV1", str, this.typeString);
    }

    /* access modifiers changed from: protected */
    public void SendJsonToUnity(String str, String str2) {
        SendJsonToUnity(str, str2, (TMAdError) null);
    }

    /* access modifiers changed from: protected */
    public void SendJsonToUnity(String str, String str2, TMAdError tMAdError) {
        JSONObject jSONObject = new JSONObject();
        JSONHelper.AddValue(jSONObject, "adType", this.typeString);
        JSONHelper.AddValue(jSONObject, "message", str2);
        if (this.tag != null) {
            JSONHelper.AddValue(jSONObject, "tag", this.tag);
        }
        if (tMAdError != null) {
            try {
                JSONHelper.AddValue(jSONObject, TJAdUnitConstants.String.VIDEO_ERROR, new JSONObject(tMAdError.toString()));
            } catch (JSONException e) {
                TLog.error((Exception) e);
            }
        }
        UnityPlayer.UnitySendMessage("TapdaqV1", str, jSONObject.toString());
    }

    public void didClose() {
        SendJsonToUnity("_didClose", "");
    }

    public void didFailToLoad(TMAdError tMAdError) {
        SendJsonToUnity("_didFailToLoad", "", tMAdError);
    }

    public void didClick() {
        SendJsonToUnity("_didClick", "");
    }

    public void willDisplay() {
        SendJsonToUnity("_willDisplay", "");
    }

    public void didDisplay() {
        SendJsonToUnity("_didDisplay", "");
    }

    public void didFailToDisplay(TMAdError tMAdError) {
        SendJsonToUnity("_didFailToDisplay", "", tMAdError);
    }

    public void didLoad() {
        SendJsonToUnity("_didLoad", "LOADED");
    }

    public void didRefresh() {
        SendJsonToUnity("_didRefresh", "REFRESH");
    }

    public void didCustomEvent(Map<Object, Object> map) {
        UnityPlayer.UnitySendMessage("TapdaqV1", "_didCustomEvent", JSONHelper.MapToJson(map).toString());
    }
}
