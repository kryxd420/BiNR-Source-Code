package com.tapdaq.sdk.helpers;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TDDeviceNetwork {
    public static String getMobileNetworkName(Context context) {
        String str = "";
        TelephonyManager telephonyManager = getTelephonyManager(context);
        if (telephonyManager != null) {
            str = telephonyManager.getNetworkOperatorName();
        }
        return str == null ? "" : str;
    }

    public static String getMobileNetworkCode(Context context) {
        String networkOperator;
        if (context == null || (networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator()) == null || networkOperator.length() <= 5) {
            return "";
        }
        return networkOperator.substring(3, 5);
    }

    public static String getMobileCountryCode(Context context) {
        String networkOperator;
        TelephonyManager telephonyManager = getTelephonyManager(context);
        if (telephonyManager == null || (networkOperator = telephonyManager.getNetworkOperator()) == null || networkOperator.length() <= 3) {
            return "";
        }
        return networkOperator.substring(0, 3);
    }

    private static TelephonyManager getTelephonyManager(Context context) {
        try {
            return (TelephonyManager) context.getSystemService("phone");
        } catch (Exception unused) {
            TLog.error("Could not get TelephonyManager");
            return null;
        }
    }
}
