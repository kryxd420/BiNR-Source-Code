package com.moat.analytics.mobile.tjy;

import java.util.Map;

public interface NativeDisplayTracker {
    void stopTracking();

    boolean track(Map map);
}
