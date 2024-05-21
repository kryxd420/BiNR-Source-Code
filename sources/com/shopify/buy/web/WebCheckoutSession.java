package com.shopify.buy.web;

import com.shopify.buy.UnityMessageCenter;
import com.shopify.buy.UnityMessageDelegate;
import com.unity3d.player.UnityPlayer;

public class WebCheckoutSession {
    private final WebCheckoutLauncher webCheckoutLauncher;

    public WebCheckoutSession(String str, String str2) {
        ShopifyClient.getInstance().init(UnityPlayer.currentActivity);
        this.webCheckoutLauncher = new WebCheckoutLauncher(str2, new UnityMessageCenter(new UnityMessageDelegate(str)));
    }

    public void checkout() {
        this.webCheckoutLauncher.launchBrowserUsingMethod(BrowserCheckoutMethod.APP_BROWSER);
    }
}
