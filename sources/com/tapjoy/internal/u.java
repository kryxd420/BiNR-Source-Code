package com.tapjoy.internal;

import android.content.Context;
import java.io.File;
import javax.annotation.Nullable;

public final class u {
    private static String a = "Android";
    private static String b = "data";

    @Nullable
    public static File a(Context context) {
        try {
            File externalFilesDir = context.getExternalFilesDir((String) null);
            int i = 0;
            while (externalFilesDir != null && i < 2) {
                externalFilesDir = externalFilesDir.getParentFile();
                i++;
            }
            return externalFilesDir;
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
