package com.tapdaq.adapters.tapdaq;

import com.tapdaq.sdk.helpers.TLog;

public final class ValidationLog {
    public static boolean notNull(Object obj, String str, Object... objArr) {
        if (obj != null) {
            return true;
        }
        TLog.error(String.format(str, objArr));
        return false;
    }

    public static boolean isTrue(boolean z, String str, Object... objArr) {
        if (z) {
            return true;
        }
        TLog.error(String.format(str, objArr));
        return false;
    }
}
