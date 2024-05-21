package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.ref.WeakReference;

class x implements ba {
    final /* synthetic */ WeakReference a;
    final /* synthetic */ ap b;
    final /* synthetic */ v c;

    x(v vVar, WeakReference weakReference, ap apVar) {
        this.c = vVar;
        this.a = weakReference;
        this.b = apVar;
    }

    public a a() {
        ViewGroup viewGroup = (ViewGroup) this.a.get();
        boolean b2 = this.b.b();
        if (viewGroup == null) {
            if (b2) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return a.a();
        }
        if (b2) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
        }
        a a2 = this.c.a.a(viewGroup);
        boolean c2 = a2.c();
        if (b2) {
            StringBuilder sb = new StringBuilder("WebView ");
            sb.append(c2 ? "" : "not ");
            sb.append("found inside of ad container.");
            Log.e("MoatFactory", sb.toString());
        }
        return a.a(new bj((WebView) a2.c((Object) null), this.c.b, this.b));
    }
}
