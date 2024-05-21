package com.integralads.avid.library.adcolony.utils;

import android.text.TextUtils;
import android.util.Log;

public class AvidLogs {
    private static final boolean a = true;
    private static final String b = "AVID";

    public static void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d(b, str);
        }
    }

    public static void w(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.w(b, str);
        }
    }

    public static void i(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.i(b, str);
        }
    }

    public static void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.e(b, str);
        }
    }

    public static void e(String str, Exception exc) {
        if (!TextUtils.isEmpty(str) || exc != null) {
            Log.e(b, str, exc);
        }
    }
}
