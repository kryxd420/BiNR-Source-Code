package com.vungle.warren;

import android.support.annotation.RestrictTo;
import java.util.Map;

public class AdConfig {
    public static final int APK_DIRECT_DOWNLOAD = 32;
    public static final int AUTO_ROTATE = 16;
    public static final String FLAG_DIRECT_DOWNLOAD = "direct_download";
    public static final int IMMEDIATE_BACK = 2;
    public static final int IMMERSIVE = 4;
    public static final int MUTED = 1;
    public static final int TRANSITION_ANIMATE = 8;
    private int flexviewCloseSec;
    private int ordinal;
    private int settings;

    public @interface Settings {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int getSettings() {
        return this.settings;
    }

    public void setImmersiveMode(boolean z) {
        if (z) {
            this.settings |= 4;
        } else {
            this.settings &= -5;
        }
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int i) {
        this.ordinal = i;
    }

    public void setBackButtonImmediatelyEnabled(boolean z) {
        if (z) {
            this.settings |= 2;
        } else {
            this.settings &= -3;
        }
    }

    public void setAutoRotate(boolean z) {
        if (z) {
            this.settings |= 16;
        } else {
            this.settings &= -17;
        }
    }

    public void setMuted(boolean z) {
        if (z) {
            this.settings |= 1;
        } else {
            this.settings &= -2;
        }
    }

    public void setTransitionAnimationEnabled(boolean z) {
        if (z) {
            this.settings |= 8;
        } else {
            this.settings &= -9;
        }
    }

    public void setFlexViewCloseTime(int i) {
        this.flexviewCloseSec = i;
    }

    public int getFlexViewCloseTime() {
        return this.flexviewCloseSec;
    }

    public void setExtraSettings(Map<String, Object> map) {
        if (map != null && !map.isEmpty() && map.containsKey(FLAG_DIRECT_DOWNLOAD)) {
            if (((Boolean) map.get(FLAG_DIRECT_DOWNLOAD)).booleanValue()) {
                this.settings |= 32;
            } else {
                this.settings &= -33;
            }
        }
    }
}
