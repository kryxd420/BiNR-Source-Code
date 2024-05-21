package com.integralads.avid.library.adcolony.session.internal.trackingwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AvidWebViewClient extends WebViewClient {
    private AvidWebViewClientListener a;

    public interface AvidWebViewClientListener {
        void webViewDidLoadData();
    }

    public AvidWebViewClientListener getListener() {
        return this.a;
    }

    public void setListener(AvidWebViewClientListener avidWebViewClientListener) {
        this.a = avidWebViewClientListener;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.a != null) {
            this.a.webViewDidLoadData();
        }
    }
}
