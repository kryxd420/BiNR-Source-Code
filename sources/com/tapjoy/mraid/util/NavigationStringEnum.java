package com.tapjoy.mraid.util;

import com.tapjoy.TJAdUnitConstants;

public enum NavigationStringEnum {
    NONE("none"),
    CLOSE(TJAdUnitConstants.String.CLOSE),
    BACK("back"),
    FORWARD("forward"),
    REFRESH("refresh");
    
    private String a;

    private NavigationStringEnum(String str) {
        this.a = str;
    }

    public final String getText() {
        return this.a;
    }

    public static NavigationStringEnum fromString(String str) {
        if (str == null) {
            return null;
        }
        for (NavigationStringEnum navigationStringEnum : values()) {
            if (str.equalsIgnoreCase(navigationStringEnum.a)) {
                return navigationStringEnum;
            }
        }
        return null;
    }
}
