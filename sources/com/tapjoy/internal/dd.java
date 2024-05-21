package com.tapjoy.internal;

import com.vungle.warren.model.Advertisement;

public enum dd {
    PREROLL("preroll"),
    MIDROLL("midroll"),
    POSTROLL(Advertisement.KEY_POSTROLL),
    STANDALONE("standalone");
    
    private final String e;

    private dd(String str) {
        this.e = str;
    }

    public final String toString() {
        return this.e;
    }
}
