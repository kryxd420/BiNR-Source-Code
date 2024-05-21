package com.tapjoy.internal;

public final class ab {
    public static Object a(be beVar) {
        int i = 1;
        while (true) {
            try {
                return beVar.call();
            } catch (OutOfMemoryError e) {
                if (i < 10) {
                    System.gc();
                    i++;
                } else {
                    throw e;
                }
            }
        }
    }
}
