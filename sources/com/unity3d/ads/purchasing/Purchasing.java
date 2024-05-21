package com.unity3d.ads.purchasing;

import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;

public class Purchasing {

    public enum UnityAdsPurchasingEvent {
        COMMAND,
        VERSION,
        CATALOG,
        INITIALIZATION,
        EVENT
    }

    public static void initialize(IPurchasing iPurchasing) {
        com.unity3d.ads.api.Purchasing.setPurchasingInterface(iPurchasing);
    }

    public static void dispatchReturnEvent(int i, String str) {
        WebViewApp currentApp = WebViewApp.getCurrentApp();
        if (currentApp != null && currentApp.isWebAppLoaded()) {
            currentApp.sendEvent(WebViewEventCategory.PURCHASING, UnityAdsPurchasingEvent.values()[i], str);
        }
    }
}
