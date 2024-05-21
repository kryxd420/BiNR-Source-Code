package com.tonyodev.fetch.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Header {
    private final String header;
    private final String value;

    public Header(@NonNull String str, @Nullable String str2) {
        if (str == null) {
            throw new NullPointerException("header cannot be null");
        } else if (!str.contains(":")) {
            str2 = str2 == null ? "" : str2;
            this.header = str;
            this.value = str2;
        } else {
            throw new IllegalArgumentException("header may not contain ':'");
        }
    }

    @NonNull
    public String getHeader() {
        return this.header;
    }

    @NonNull
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.header + ":" + this.value;
    }
}
