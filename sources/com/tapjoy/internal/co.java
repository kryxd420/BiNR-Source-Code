package com.tapjoy.internal;

public final class co {
    boolean a;

    static int a(String str) {
        b(str);
        return Integer.parseInt(str.split("\\.", 2)[0]);
    }

    static void b(String str) {
        ds.a((Object) str, "Version cannot be null");
        if (!str.matches("[0-9]+\\.[0-9]+\\.[0-9]+.*")) {
            throw new IllegalArgumentException("Invalid version format : " + str);
        }
    }
}
