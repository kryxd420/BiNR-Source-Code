package com.tapjoy.mraid.util;

import com.tapdaq.sdk.TapdaqPlacement;

public enum TransitionStringEnum {
    DEFAULT(TapdaqPlacement.TDPTagDefault),
    DISSOLVE("dissolve"),
    FADE("fade"),
    ROLL("roll"),
    SLIDE("slide"),
    ZOOM("zoom"),
    NONE("none");
    
    private String a;

    private TransitionStringEnum(String str) {
        this.a = str;
    }

    public final String getText() {
        return this.a;
    }

    public static TransitionStringEnum fromString(String str) {
        if (str == null) {
            return null;
        }
        for (TransitionStringEnum transitionStringEnum : values()) {
            if (str.equalsIgnoreCase(transitionStringEnum.a)) {
                return transitionStringEnum;
            }
        }
        return null;
    }
}
