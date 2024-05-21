package com.integralads.avid.library.adcolony.session.internal.trackingwebview;

import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidWebViewClient;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;

public class AvidTrackingWebViewManager implements AvidJavaScriptResourceInjector, AvidWebViewClient.AvidWebViewClientListener {
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    private static final String d = "<html><body></body></html>";
    private static final String e = "text/html";
    private static final String f = "(function () {\nvar script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);\n})();";
    private static final String g = "%SCRIPT_SRC%";
    private final AvidWebView h;
    private final AvidWebViewClient i;
    private int j = 0;
    private final ArrayList<String> k = new ArrayList<>();

    public AvidTrackingWebViewManager(WebView webView) {
        this.h = new AvidWebView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        this.i = new AvidWebViewClient();
        this.i.setListener(this);
        webView.setWebViewClient(this.i);
    }

    public void loadHTML() {
        WebView webView = (WebView) this.h.get();
        if (webView != null && this.j == 0) {
            this.j = 1;
            webView.loadData(d, e, (String) null);
        }
    }

    public void injectJavaScriptResource(String str) {
        if (this.j == 2) {
            a(str);
        } else {
            this.k.add(str);
        }
    }

    public void webViewDidLoadData() {
        this.j = 2;
        Iterator<String> it = this.k.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        this.k.clear();
    }

    private void a(String str) {
        this.h.injectJavaScript(f.replace(g, str));
    }
}
