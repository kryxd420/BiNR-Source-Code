package com.unity3d.ads.webplayer;

import android.webkit.JavascriptInterface;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;

public class WebPlayerBridgeInterface {
    @JavascriptInterface
    public void handleEvent(String str) {
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.WEBPLAYER_EVENT, str);
        }
    }
}
