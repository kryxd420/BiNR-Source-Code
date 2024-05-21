package com.tapjoy.mraid.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.mraid.controller.Network;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private Network a;

    public NetworkBroadcastReceiver(Network network) {
        this.a = network;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.a.onConnectionChanged();
        }
    }
}
