package com.integralads.avid.library.adcolony.session.internal;

import com.tapjoy.TJAdUnitConstants;
import com.vungle.warren.model.Advertisement;

public enum MediaType {
    DISPLAY(TJAdUnitConstants.String.DISPLAY),
    VIDEO(Advertisement.KEY_VIDEO);
    
    private final String a;

    private MediaType(String str) {
        this.a = str;
    }

    public String toString() {
        return this.a;
    }
}
