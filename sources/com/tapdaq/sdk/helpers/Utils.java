package com.tapdaq.sdk.helpers;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        int i;
        int i2;
        do {
            i = sNextGeneratedId.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(i, i2));
        return i;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
