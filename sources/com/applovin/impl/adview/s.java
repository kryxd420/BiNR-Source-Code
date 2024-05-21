package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.lang.ref.WeakReference;

public class s extends WebViewClient {
    private final p a;
    private WeakReference<a> b;

    public interface a {
        void a(r rVar);

        void b(r rVar);

        void c(r rVar);
    }

    public s(j jVar) {
        this.a = jVar.v();
    }

    private void a(WebView webView, String str) {
        p pVar = this.a;
        pVar.b("WebViewButtonClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof r)) {
            r rVar = (r) webView;
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            a aVar = (a) this.b.get();
            if ("applovin".equalsIgnoreCase(scheme) && "com.applovin.sdk".equalsIgnoreCase(host) && aVar != null) {
                if ("/track_click".equals(path)) {
                    aVar.a(rVar);
                } else if ("/close_ad".equals(path)) {
                    aVar.b(rVar);
                } else if ("/skip_ad".equals(path)) {
                    aVar.c(rVar);
                } else {
                    p pVar2 = this.a;
                    pVar2.c("WebViewButtonClient", "Unknown URL: " + str);
                    p pVar3 = this.a;
                    pVar3.c("WebViewButtonClient", "Path: " + path);
                }
            }
        }
    }

    public void a(WeakReference<a> weakReference) {
        this.b = weakReference;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a(webView, str);
        return true;
    }
}
