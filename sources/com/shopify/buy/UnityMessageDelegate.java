package com.shopify.buy;

import android.support.annotation.NonNull;
import com.unity3d.player.UnityPlayer;

public class UnityMessageDelegate {
    private final String unityDelegateObjectName;

    public UnityMessageDelegate(String str) {
        this.unityDelegateObjectName = str;
    }

    public void unitySendMessage(@NonNull String str, @NonNull String str2) {
        UnityPlayer.UnitySendMessage(this.unityDelegateObjectName, str, str2);
    }
}
