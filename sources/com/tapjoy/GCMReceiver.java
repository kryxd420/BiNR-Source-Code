package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tapjoy.internal.hf;

public class GCMReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean a = hf.b(context).a(intent);
        if (isOrderedBroadcast()) {
            setResult(-1, (String) null, (Bundle) null);
            if (a) {
                abortBroadcast();
            }
        }
    }
}
