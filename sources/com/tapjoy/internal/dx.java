package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.webkit.WebView;

public final class dx extends dw {
    @SuppressLint({"SetJavaScriptEnabled"})
    public dx(WebView webView) {
        if (webView != null && !webView.getSettings().getJavaScriptEnabled()) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
        a(webView);
    }
}
