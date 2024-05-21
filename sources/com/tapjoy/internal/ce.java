package com.tapjoy.internal;

public final class ce extends RuntimeException {
    public final int a;

    public ce(int i, String str) {
        super(str);
        this.a = i;
    }
}
