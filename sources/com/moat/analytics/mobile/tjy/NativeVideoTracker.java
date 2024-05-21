package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import java.util.Map;

public interface NativeVideoTracker {
    void changeTargetView(View view);

    void dispatchEvent(MoatAdEvent moatAdEvent);

    @Deprecated
    void dispatchEvent(Map map);

    void setDebug(boolean z);

    boolean trackVideoAd(Map map, MediaPlayer mediaPlayer, View view);
}
