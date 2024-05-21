package com.tapdaq.sdk.helpers;

import android.util.Log;

public class TLog {
    private static int LOGGING_LEVEL = 0;
    private static TLogLevel LOG_LEVEL = TLogLevel.DISABLED;
    private static final String TAG = "TAPDAQ";

    public static TLogLevel getLoggingLevel() {
        return LOG_LEVEL;
    }

    public static boolean isDebugEnabled() {
        return LOG_LEVEL == TLogLevel.DEBUG;
    }

    public static void setLoggingLevel(TLogLevel tLogLevel) {
        switch (tLogLevel) {
            case DISABLED:
                LOGGING_LEVEL = 0;
                return;
            case INFO:
                LOGGING_LEVEL = 1;
                return;
            case WARNING:
                LOGGING_LEVEL = 2;
                return;
            case ERROR:
                LOGGING_LEVEL = 3;
                return;
            case DEBUG:
                LOGGING_LEVEL = 4;
                return;
            default:
                LOGGING_LEVEL = 1;
                return;
        }
    }

    public static void info(String str) {
        if (LOGGING_LEVEL >= 1) {
            Log.i("TAPDAQ_INFO", str);
        }
    }

    public static void warning(String str) {
        if (LOGGING_LEVEL >= 2) {
            Log.w("TAPDAQ_WARNING", str);
        }
    }

    public static void error(String str) {
        if (LOGGING_LEVEL >= 3) {
            Log.e("TAPDAQ_ERROR", str);
        }
    }

    public static void error(String str, Throwable th) {
        if (LOGGING_LEVEL >= 3) {
            Log.e("TAPDAQ_ERROR", str, th);
        }
    }

    public static void error(Exception exc) {
        if (exc != null && LOGGING_LEVEL >= 3) {
            exc.printStackTrace();
        }
    }

    public static void debug(String str) {
        if (LOGGING_LEVEL >= 4) {
            Log.d("TAPDAQDEBUG", str);
        }
    }
}
