package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.ValueCallback;

class p implements ValueCallback {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    /* renamed from: a */
    public void onReceiveValue(String str) {
        String str2;
        if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("false")) {
            if (this.a.d.b()) {
                StringBuilder sb = new StringBuilder("Received value is:");
                if (str == null) {
                    str2 = "null";
                } else {
                    str2 = "(String)" + str;
                }
                sb.append(str2);
                Log.d("MoatJavaScriptBridge", sb.toString());
            }
            if (this.a.e == -1 || this.a.e == 50) {
                this.a.g();
            }
            n.e(this.a);
        } else if (str.equalsIgnoreCase("true")) {
            int unused = this.a.e = -1;
            this.a.e();
        } else if (this.a.d.b()) {
            Log.d("MoatJavaScriptBridge", "Received unusual value from Javascript:" + str);
        }
    }
}
