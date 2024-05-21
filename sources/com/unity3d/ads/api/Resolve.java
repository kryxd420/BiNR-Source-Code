package com.unity3d.ads.api;

import com.unity3d.ads.request.IResolveHostListener;
import com.unity3d.ads.request.ResolveHostError;
import com.unity3d.ads.request.ResolveHostEvent;
import com.unity3d.ads.request.WebRequestThread;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;

public class Resolve {
    @WebViewExposed
    public static void resolve(final String str, String str2, WebViewCallback webViewCallback) {
        if (WebRequestThread.resolve(str2, new IResolveHostListener() {
            public void onResolve(String str, String str2) {
                if (WebViewApp.getCurrentApp() != null) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.COMPLETE, str, str, str2);
                }
            }

            public void onFailed(String str, ResolveHostError resolveHostError, String str2) {
                if (WebViewApp.getCurrentApp() != null) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.FAILED, str, str, resolveHostError.name(), str2);
                }
            }
        })) {
            webViewCallback.invoke(str);
            return;
        }
        webViewCallback.error(ResolveHostError.INVALID_HOST, str);
    }
}
