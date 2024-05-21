package com.integralads.avid.library.adcolony.weakreference;

import android.webkit.WebView;
import com.integralads.avid.library.adcolony.utils.AvidCommand;

public class AvidWebView extends AvidView<WebView> {
    public AvidWebView(WebView webView) {
        super(webView);
    }

    public void injectJavaScript(String str) {
        injectFormattedJavaScript(AvidCommand.formatJavaScript(str));
    }

    public void injectFormattedJavaScript(String str) {
        WebView webView = (WebView) get();
        if (webView != null) {
            webView.loadUrl(str);
        }
    }
}
