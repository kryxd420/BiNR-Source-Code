package com.tapdaq.sdk.helpers;

import android.content.Context;
import android.content.Intent;
import java.util.Locale;

public class TDActivityUtil {
    public static boolean IsActivityAvailable(Context context, Class<?> cls) {
        if (context != null) {
            if (context.getPackageManager().queryIntentActivities(new Intent(context, cls), 65536).size() > 0) {
                return true;
            }
            TLog.error(String.format(Locale.ENGLISH, "Class %s missing from AndroidManifest.xml", new Object[]{cls.getName()}));
        } else {
            TLog.debug("IsActivityAvailable Context is null, unable to perform check");
        }
        return false;
    }
}
