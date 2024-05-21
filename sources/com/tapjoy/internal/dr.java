package com.tapjoy.internal;

import java.util.concurrent.TimeUnit;

public final class dr {
    public static double a() {
        return (double) TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }
}
