package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.support.annotation.VisibleForTesting;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidJavascriptInterface;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;

public class AvidWebViewManager implements AvidJavascriptInterface.AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext a;
    private final AvidWebView b = new AvidWebView((WebView) null);
    private final AvidBridgeManager c;
    private AvidJavascriptInterface d;

    public AvidWebViewManager(InternalAvidAdSessionContext internalAvidAdSessionContext, AvidBridgeManager avidBridgeManager) {
        this.a = internalAvidAdSessionContext;
        this.c = avidBridgeManager;
    }

    public void setWebView(WebView webView) {
        if (this.b.get() != webView) {
            this.c.setWebView((WebView) null);
            b();
            this.b.set(webView);
            if (webView != null) {
                this.d = new AvidJavascriptInterface(this.a);
                this.d.setCallback(this);
                webView.addJavascriptInterface(this.d, AvidJavascriptInterface.AVID_OBJECT);
            }
        }
    }

    public void destroy() {
        setWebView((WebView) null);
    }

    private void b() {
        if (this.d != null) {
            this.d.setCallback((AvidJavascriptInterface.AvidJavascriptInterfaceCallback) null);
            this.d = null;
        }
    }

    public void onAvidAdSessionContextInvoked() {
        this.c.setWebView((WebView) this.b.get());
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public AvidJavascriptInterface a() {
        return this.d;
    }
}
