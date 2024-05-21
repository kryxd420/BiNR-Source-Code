package com.vungle.warren;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class NetworkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkStateReceiver";

    public void onReceive(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        if (!Vungle.isInitialized()) {
            enableBroadcastReceiver(context, false);
        } else {
            Vungle.handleApkDirectDownloads(context);
        }
    }

    public static void enableBroadcastReceiver(Context context, boolean z) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, NetworkStateReceiver.class), z ? 1 : 2, 1);
    }
}
