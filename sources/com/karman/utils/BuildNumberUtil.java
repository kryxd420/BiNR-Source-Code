package com.karman.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

public class BuildNumberUtil {
    public static String getBuildNumber(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KARMAN", e.getMessage());
            return "";
        }
    }
}
