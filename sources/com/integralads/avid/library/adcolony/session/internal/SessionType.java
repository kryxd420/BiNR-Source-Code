package com.integralads.avid.library.adcolony.session.internal;

import com.tapjoy.TJAdUnitConstants;
import com.vungle.warren.model.Advertisement;

public enum SessionType {
    DISPLAY(TJAdUnitConstants.String.DISPLAY),
    VIDEO(Advertisement.KEY_VIDEO),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");
    
    private final String a;

    private SessionType(String str) {
        this.a = str;
    }

    public String toString() {
        return this.a;
    }
}
