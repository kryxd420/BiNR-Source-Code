package com.shopify.buy.web;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import com.shopify.buy.MessageCenter;
import com.shopify.buy.utils.Logger;

final class WebCheckoutLauncher {
    private final Application.ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacksAdapter() {
        public void onActivityResumed(Activity activity) {
            WebCheckoutLauncher.this.onActivityResumed(activity);
        }
    };
    private final String checkoutUrl;
    private boolean launchedCustomTabCheckout;
    private boolean launchedWebCheckout;
    private final MessageCenter messageCenter;

    WebCheckoutLauncher(String str, MessageCenter messageCenter2) {
        this.checkoutUrl = str;
        this.messageCenter = messageCenter2;
    }

    /* access modifiers changed from: package-private */
    public void launchBrowserUsingMethod(@NonNull BrowserCheckoutMethod browserCheckoutMethod) {
        ShopifyClient.getInstance().registerActivityLifecycleCallbacks(this.callbacks);
        switch (browserCheckoutMethod) {
            case APP_BROWSER:
                launchChromeTab(this.checkoutUrl);
                return;
            case EXTERNAL_BROWSER:
                launchWebUrl(this.checkoutUrl);
                return;
            default:
                Logger.error("Unsupported BrowserCheckoutMethod: " + browserCheckoutMethod);
                return;
        }
    }

    private void launchWebUrl(String str) {
        this.launchedWebCheckout = true;
        ShopifyClient.getInstance().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    private void launchChromeTab(String str) {
        Activity activity = ShopifyClient.getInstance().getActivity();
        if (activity != null) {
            this.launchedCustomTabCheckout = true;
            new CustomTabsIntent.Builder().build().launchUrl(activity, Uri.parse(str));
            return;
        }
        Logger.error("Unable to launch Chrome tab: activity == null.");
    }

    /* access modifiers changed from: private */
    public void onActivityResumed(Activity activity) {
        ShopifyClient.getInstance().unregisterActivityLifecycleCallbacks(this.callbacks);
        if (this.launchedWebCheckout) {
            Logger.debug("Browser closed.");
            this.messageCenter.onNativeMessage();
            this.launchedWebCheckout = false;
        } else if (this.launchedCustomTabCheckout) {
            Logger.debug("Custom tab closed.");
            this.messageCenter.onNativeMessage();
            this.launchedCustomTabCheckout = false;
        }
    }
}
