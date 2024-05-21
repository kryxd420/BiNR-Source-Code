package com.tapdaq.unityplugin.listeners;

import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMInitListener;
import com.unity3d.player.UnityPlayer;

public class TDInitListener extends TMInitListener {
    public void didInitialise() {
        UnityPlayer.UnitySendMessage("TapdaqV1", "_didLoadConfig", "");
    }

    public void didFailToInitialise(TMAdError tMAdError) {
        UnityPlayer.UnitySendMessage("TapdaqV1", "_didFailToLoadConfig", tMAdError.toString());
    }
}
