package com.unity.purchasing.common;

public interface IUnityCallback {
    void OnProductsRetrieved(String str);

    void OnPurchaseFailed(String str);

    void OnPurchaseSucceeded(String str, String str2, String str3);

    void OnSetupFailed(String str);
}
