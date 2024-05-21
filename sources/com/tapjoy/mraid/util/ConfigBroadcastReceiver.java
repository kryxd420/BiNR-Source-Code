package com.tapjoy.mraid.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.mraid.controller.Display;

public class ConfigBroadcastReceiver extends BroadcastReceiver {
    private Display a;
    private int b = this.a.getOrientation();

    public ConfigBroadcastReceiver(Display display) {
        this.a = display;
    }

    public void onReceive(Context context, Intent intent) {
        int orientation;
        if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED") && (orientation = this.a.getOrientation()) != this.b) {
            this.b = orientation;
            this.a.onOrientationChanged(this.b);
        }
    }
}
