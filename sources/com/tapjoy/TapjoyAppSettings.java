package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.internal.ju;

public class TapjoyAppSettings {
    public static final String TAG = "TapjoyAppSettings";
    private static TapjoyAppSettings b;
    String a = this.d.getString(TapjoyConstants.PREF_LOG_LEVEL, (String) null);
    private Context c;
    private SharedPreferences d = this.c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);

    private TapjoyAppSettings(Context context) {
        this.c = context;
        if (!ju.c(this.a)) {
            String str = TAG;
            TapjoyLog.d(str, "restoreLoggingLevel from sharedPref -- loggingLevel=" + this.a);
            TapjoyLog.a(this.a, true);
        }
    }

    public static TapjoyAppSettings getInstance() {
        return b;
    }

    public static void init(Context context) {
        TapjoyLog.d(TAG, "initializing app settings");
        b = new TapjoyAppSettings(context);
    }

    public void saveLoggingLevel(String str) {
        if (ju.c(str)) {
            TapjoyLog.d(TAG, "saveLoggingLevel -- server logging level is NULL or Empty string");
            return;
        }
        String str2 = TAG;
        TapjoyLog.d(str2, "saveLoggingLevel -- currentLevel=" + this.a + ";newLevel=" + str);
        if (ju.c(this.a) || !this.a.equals(str)) {
            SharedPreferences.Editor edit = this.d.edit();
            edit.putString(TapjoyConstants.PREF_LOG_LEVEL, str);
            edit.apply();
            this.a = str;
            TapjoyLog.a(this.a, true);
        }
        boolean isLoggingEnabled = TapjoyLog.isLoggingEnabled();
        String str3 = TAG;
        StringBuilder sb = new StringBuilder("Tapjoy remote device debugging set to '");
        sb.append(str);
        sb.append("'. The SDK Debug-setting is: ");
        sb.append(isLoggingEnabled ? "'Enabled'" : "'Disabled'");
        TapjoyLog.i(str3, sb.toString());
    }

    public void clearLoggingLevel() {
        SharedPreferences.Editor edit = this.d.edit();
        edit.remove(TapjoyConstants.PREF_LOG_LEVEL);
        edit.apply();
        this.a = null;
        boolean isLoggingEnabled = TapjoyLog.isLoggingEnabled();
        String str = TAG;
        StringBuilder sb = new StringBuilder("Tapjoy remote device debugging 'Disabled'. The SDK Debug-setting is: ");
        sb.append(isLoggingEnabled ? "'Enabled'" : "'Disabled'");
        TapjoyLog.i(str, sb.toString());
        TapjoyLog.setDebugEnabled(isLoggingEnabled);
    }

    public void saveConnectResultAndParams(String str, String str2, long j) {
        if (!ju.c(str) && !ju.c(str2)) {
            SharedPreferences.Editor edit = this.d.edit();
            edit.putString(TapjoyConstants.PREF_LAST_CONNECT_RESULT, str);
            edit.putString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, str2);
            if (j >= 0) {
                edit.putLong(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES, j);
            } else {
                edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES);
            }
            TapjoyLog.i(TAG, "Stored connect result");
            edit.apply();
        }
    }

    public void removeConnectResult() {
        if (this.d.getString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, (String) null) != null) {
            SharedPreferences.Editor edit = this.d.edit();
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT);
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH);
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES);
            TapjoyLog.i(TAG, "Removed connect result");
            edit.apply();
        }
    }

    public String getConnectResult(String str, long j) {
        String string = this.d.getString(TapjoyConstants.PREF_LAST_CONNECT_RESULT, (String) null);
        if (!ju.c(string) && !ju.c(str) && str.equals(this.d.getString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, (String) null))) {
            long j2 = this.d.getLong(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES, -1);
            if (j2 < 0 || j2 >= j) {
                return string;
            }
        }
        return null;
    }
}
