package com.shopify.buy.utils;

import java.util.Arrays;

public final class Objects {
    private Objects() {
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
