package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;

public enum cz {
    NATIVE(TapjoyConstants.TJC_PLUGIN_NATIVE),
    JAVASCRIPT("javascript"),
    NONE("none");
    
    private final String d;

    private cz(String str) {
        this.d = str;
    }

    public final String toString() {
        return this.d;
    }
}
