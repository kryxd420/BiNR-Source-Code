package com.tapjoy.internal;

final class em {
    static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }
}
