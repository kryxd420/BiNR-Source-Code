package com.tapdaq.sdk.helpers;

import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import com.tapdaq.sdk.Tapdaq;
import com.tapjoy.TapjoyConstants;
import java.util.Locale;

public class TDSystemInfo {
    public static String getDeviceipWiFiData(Context context) {
        if (!Tapdaq.getInstance().config().isConsentGiven() || context == null) {
            return "";
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) != 0) {
                return "";
            }
            int ipAddress = ((WifiManager) context.getApplicationContext().getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI)).getConnectionInfo().getIpAddress();
            return String.format(Locale.getDefault(), "%d.%d.%d.%d", new Object[]{Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255)});
        } catch (Exception e) {
            TLog.error(e);
            return "";
        }
    }

    public static boolean isVolumeEnabled(Context context) {
        if (context != null) {
            try {
                if (((AudioManager) context.getSystemService("audio")).getStreamVolume(3) != 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                TLog.error(e);
            }
        }
        return false;
    }

    public static String getCPUArchitecture() {
        return System.getProperty("os.arch");
    }
}
