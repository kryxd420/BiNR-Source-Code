package com.shopify.buy.utils;

import android.util.Log;

public final class Logger {
    private static boolean enabled;

    private Logger() {
    }

    public static void setEnabled(boolean z) {
        enabled = z;
    }

    public static void debug(String str) {
        if (enabled) {
            Log.d(buildTag(), str);
        }
    }

    public static void error(String str) {
        if (enabled) {
            Log.e(buildTag(), str);
        }
    }

    private static String buildTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String className = stackTraceElement.getClassName();
        String substring = className.substring(className.lastIndexOf(46) + 1);
        return substring + ":" + stackTraceElement.getLineNumber();
    }
}
