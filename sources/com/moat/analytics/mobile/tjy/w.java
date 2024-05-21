package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.ref.WeakReference;

class w implements ba {
    final /* synthetic */ WeakReference a;
    final /* synthetic */ ap b;
    final /* synthetic */ v c;

    w(v vVar, WeakReference weakReference, ap apVar) {
        this.c = vVar;
        this.a = weakReference;
        this.b = apVar;
    }

    public a a() {
        WebView webView = (WebView) this.a.get();
        boolean b2 = this.b.b();
        if (webView == null) {
            if (b2) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return a.a();
        }
        if (b2) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
        }
        return a.a(new bj(webView, this.c.b, this.b));
    }
}
