package com.moat.analytics.mobile.tjy;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.moat.analytics.mobile.tjy.base.exception.a;

class ae extends WebViewClient {
    final /* synthetic */ ad a;

    ae(ad adVar) {
        this.a = adVar;
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.a.i) {
            try {
                boolean unused = this.a.i = true;
                this.a.c = new bi((View) this.a.g.get(), this.a.h, true, this.a.a, this.a.b);
                this.a.c.c();
                this.a.a();
            } catch (Exception e) {
                a.a(e);
            }
        }
    }
}
