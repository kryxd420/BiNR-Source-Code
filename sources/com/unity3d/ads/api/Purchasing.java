package com.unity3d.ads.api;

import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.purchasing.IPurchasing;
import com.unity3d.ads.purchasing.PurchasingError;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;

public class Purchasing {
    public static IPurchasing purchaseInterface;

    public static void setPurchasingInterface(IPurchasing iPurchasing) {
        purchaseInterface = iPurchasing;
    }

    @WebViewExposed
    public static void initiatePurchasingCommand(final String str, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onPurchasingCommand(str);
                }
            }
        });
        if (purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getPromoVersion(WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onGetPurchasingVersion();
                }
            }
        });
        if (purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getPromoCatalog(WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onGetProductCatalog();
                }
            }
        });
        if (purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void initializePurchasing(WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onInitializePurchasing();
                }
            }
        });
        if (purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }
}
