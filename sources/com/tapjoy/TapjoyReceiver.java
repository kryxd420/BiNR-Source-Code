package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.fc;
import com.tapjoy.internal.fg;
import com.tapjoy.internal.ha;
import com.tapjoy.internal.hc;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.hf;
import com.tapjoy.internal.ho;
import java.util.List;

public class TapjoyReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intent2;
        hf.b(context);
        if ("com.tapjoy.PUSH_CLICK".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("com.tapjoy.PUSH_ID");
            String stringExtra2 = intent.getStringExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD);
            String stringExtra3 = intent.getStringExtra("com.tapjoy.PUSH_PLACEMENT");
            if (!(stringExtra == null || stringExtra.length() == 0)) {
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();
                Intent intent3 = new Intent("android.intent.action.MAIN");
                intent3.setPackage(packageName);
                intent3.addCategory("android.intent.category.LAUNCHER");
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent3, 0);
                if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                    intent2 = null;
                } else {
                    intent2 = new Intent(intent3);
                    intent2.setFlags(268435456);
                    intent2.setClassName(queryIntentActivities.get(0).activityInfo.packageName, queryIntentActivities.get(0).activityInfo.name);
                }
                if (intent2 != null) {
                    if (stringExtra2 != null) {
                        intent2.putExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD, stringExtra2);
                    }
                    hd a = hd.a(context);
                    if (a.f.c(stringExtra)) {
                        hc hcVar = a.g;
                        ez.a a2 = hcVar.a(fc.APP, "push_click");
                        a2.s = new fg((String) null, (String) null, stringExtra);
                        hcVar.a(a2);
                    }
                    if (stringExtra3 != null) {
                        ho.a(stringExtra, stringExtra3);
                    }
                    context.startActivity(intent2);
                } else {
                    ha.b("No intent that can be used to launch the main activity.");
                }
            }
        }
        if (isOrderedBroadcast()) {
            setResult(-1, (String) null, (Bundle) null);
        }
    }
}
