package com.applovin.impl.sdk.e;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class i {
    public static int a(String str, String str2, PackageManager packageManager) {
        try {
            return packageManager.checkPermission(str, str2);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static ResolveInfo a(Intent intent, int i, PackageManager packageManager) {
        try {
            return packageManager.resolveActivity(intent, i);
        } catch (Throwable unused) {
            return null;
        }
    }
}
