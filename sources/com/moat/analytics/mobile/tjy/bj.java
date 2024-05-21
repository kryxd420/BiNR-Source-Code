package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.exception.b;
import com.moat.analytics.mobile.tjy.base.functional.a;

class bj implements WebAdTracker {
    private final a a;
    private final ap b;

    bj(WebView webView, a aVar, ap apVar) {
        a a2;
        this.b = apVar;
        if (apVar.b()) {
            Log.d("MoatWebAdTracker", "In initialization method.");
        }
        if (webView == null) {
            if (apVar.b()) {
                Log.e("MoatWebAdTracker", "WebView is null. Will not track.");
            }
            a2 = a.a();
        } else {
            a2 = a.a(new bi(webView, webView, false, aVar, apVar));
        }
        this.a = a2;
    }

    public boolean track() {
        boolean b2 = this.b.b();
        boolean z = false;
        if (b2) {
            try {
                Log.d("MoatWebAdTracker", "In track method.");
            } catch (b e) {
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            }
        }
        if (this.a.c()) {
            z = ((bh) this.a.b()).c();
        } else if (b2) {
            Log.e("MoatWebAdTracker", "Internal tracker not available. Not tracking.");
        }
        if (b2) {
            StringBuilder sb = new StringBuilder("Attempt to start tracking ad was ");
            sb.append(z ? "" : "un");
            sb.append("successful.");
            Log.d("MoatWebAdTracker", sb.toString());
        }
        return z;
    }
}
