package com.tapjoy.internal;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.tapjoy.TapjoyConstants;
import javax.annotation.Nullable;

public final class z {
    @Nullable
    public static String a(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI);
        if (wifiManager == null) {
            return null;
        }
        try {
            return ju.b(wifiManager.getConnectionInfo().getMacAddress());
        } catch (RuntimeException | SecurityException unused) {
            return null;
        }
    }
}
