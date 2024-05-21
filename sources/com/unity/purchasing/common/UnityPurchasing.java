package com.unity.purchasing.common;

import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnityPurchasing implements IStoreCallback {
    private IUnityCallback bridge;

    public UnityPurchasing(IUnityCallback iUnityCallback) {
        this.bridge = iUnityCallback;
    }

    public void OnSetupFailed(InitializationFailureReason initializationFailureReason) {
        this.bridge.OnSetupFailed(initializationFailureReason.toString());
    }

    public void OnProductsRetrieved(List<ProductDescription> list) {
        this.bridge.OnProductsRetrieved(SerialiseProducts(list));
    }

    public void OnPurchaseSucceeded(String str, String str2, String str3) {
        this.bridge.OnPurchaseSucceeded(str, str2, str3);
    }

    public void OnPurchaseFailed(PurchaseFailureDescription purchaseFailureDescription) {
        this.bridge.OnPurchaseFailed(SerialisePurchaseFailure(purchaseFailureDescription));
    }

    public static String SerialisePurchaseFailure(PurchaseFailureDescription purchaseFailureDescription) {
        SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("message", (Object) purchaseFailureDescription.message);
        saneJSONObject.put(InAppPurchaseMetaData.KEY_PRODUCT_ID, (Object) purchaseFailureDescription.productId);
        saneJSONObject.put("reason", (Object) purchaseFailureDescription.reason.toString());
        saneJSONObject.put("storeSpecificErrorCode", (Object) purchaseFailureDescription.storeSpecificErrorCode);
        return saneJSONObject.toString();
    }

    public static String SerialiseProducts(List<ProductDescription> list) {
        JSONArray jSONArray = new JSONArray();
        for (ProductDescription SerialiseProduct : list) {
            jSONArray.put(SerialiseProduct(SerialiseProduct));
        }
        return jSONArray.toString();
    }

    private static JSONObject SerialiseProduct(ProductDescription productDescription) {
        SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("metadata", (Object) SerialiseMetadata(productDescription.metadata));
        saneJSONObject.put("receipt", (Object) productDescription.receipt);
        saneJSONObject.put("storeSpecificId", (Object) productDescription.storeSpecificId);
        saneJSONObject.put("transactionId", (Object) productDescription.transactionId);
        return saneJSONObject;
    }

    private static JSONObject SerialiseMetadata(ProductMetadata productMetadata) {
        SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("isoCurrencyCode", (Object) productMetadata.isoCurrencyCode);
        saneJSONObject.put("localizedDescription", (Object) productMetadata.localizedDescription);
        saneJSONObject.put("localizedPriceString", (Object) productMetadata.localizedPriceString);
        saneJSONObject.put("localizedPrice", productMetadata.localizedPrice == null ? 0.0d : productMetadata.localizedPrice.doubleValue());
        saneJSONObject.put("localizedTitle", (Object) productMetadata.localizedTitle);
        return saneJSONObject;
    }
}
