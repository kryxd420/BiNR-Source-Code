package com.tapjoy.internal;

import android.os.Handler;
import android.webkit.WebView;
import java.util.List;

public final class dy extends dw {
    WebView f;
    private List g;
    private final String h;

    public dy(List list, String str) {
        this.g = list;
        this.h = str;
    }

    public final void b() {
        super.b();
        new Handler().postDelayed(new Runnable() {
            private WebView b = dy.this.f;

            public final void run() {
                this.b.destroy();
            }
        }, FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        this.f = null;
    }

    public final void a() {
        super.a();
        this.f = new WebView(di.a().a);
        this.f.getSettings().setJavaScriptEnabled(true);
        a(this.f);
        dj.a();
        dj.a(this.f, this.h);
        for (db dbVar : this.g) {
            String externalForm = dbVar.b.toExternalForm();
            dj.a();
            WebView webView = this.f;
            if (externalForm != null) {
                dj.a(webView, "var script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);".replace("%SCRIPT_SRC%", externalForm));
            }
        }
    }
}
