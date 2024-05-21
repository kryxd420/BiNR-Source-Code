package com.unity3d.ads.purchasing;

public interface IPurchasing {
    void onGetProductCatalog();

    void onGetPurchasingVersion();

    void onInitializePurchasing();

    void onPurchasingCommand(String str);
}
