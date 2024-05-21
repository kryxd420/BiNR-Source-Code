package com.tapdaq.unityplugin.listeners;

import com.tapdaq.sdk.listeners.TMVideoAdListenerBase;

public class VideoAdListener extends AdListener implements TMVideoAdListenerBase {
    public VideoAdListener(String str) {
        super(str);
        this.typeString = "VIDEO";
    }

    public void didComplete() {
        SendToUnity("_didComplete");
    }

    public void didEngagement() {
        SendToUnity("_didEngagement");
    }
}
