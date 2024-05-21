package com.applovin.impl.adview;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;

class b extends WebChromeClient {
    private final p a;

    public b(j jVar) {
        this.a = jVar.v();
    }

    public void onConsoleMessage(String str, int i, String str2) {
        p pVar = this.a;
        pVar.c("AdWebView", "console.log[" + i + "] :" + str);
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        this.a.a("AdWebView", consoleMessage.sourceId() + ": " + consoleMessage.lineNumber() + ": " + consoleMessage.message());
        return true;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        p pVar = this.a;
        pVar.c("AdWebView", "Alert attempted: " + str2);
        return true;
    }

    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        p pVar = this.a;
        pVar.c("AdWebView", "JS onBeforeUnload attempted: " + str2);
        return true;
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        p pVar = this.a;
        pVar.c("AdWebView", "JS confirm attempted: " + str2);
        return true;
    }
}
