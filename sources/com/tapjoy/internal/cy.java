package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;

public enum cy {
    HTML(TJAdUnitConstants.String.HTML),
    NATIVE(TapjoyConstants.TJC_PLUGIN_NATIVE);
    
    private final String c;

    private cy(String str) {
        this.c = str;
    }

    public final String toString() {
        return this.c;
    }
}
