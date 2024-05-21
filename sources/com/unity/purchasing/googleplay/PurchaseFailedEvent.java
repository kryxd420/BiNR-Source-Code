package com.unity.purchasing.googleplay;

import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.SaneJSONObject;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;

public class PurchaseFailedEvent {
    public static String jsonEncodePurchaseFailure(String str, PurchaseFailureReason purchaseFailureReason, String str2) {
        SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put(InAppPurchaseMetaData.KEY_PRODUCT_ID, (Object) str);
        saneJSONObject.put("reason", (Object) purchaseFailureReason);
        saneJSONObject.put("message", (Object) str2);
        return saneJSONObject.toString();
    }
}
