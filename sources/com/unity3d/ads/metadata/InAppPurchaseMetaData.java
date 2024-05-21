package com.unity3d.ads.metadata;

import android.content.Context;

public class InAppPurchaseMetaData extends MetaData {
    public static final String IAP_KEY = "iap";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_PRICE = "price";
    public static final String KEY_PRODUCT_ID = "productId";
    public static final String KEY_RECEIPT_PURCHASE_DATA = "receiptPurchaseData";
    public static final String KEY_SIGNATURE = "signature";

    public InAppPurchaseMetaData(Context context) {
        super(context);
    }

    public void setProductId(String str) {
        set(KEY_PRODUCT_ID, str);
    }

    public void setPrice(Double d) {
        set(KEY_PRICE, d);
    }

    public void setCurrency(String str) {
        set("currency", str);
    }

    public void setReceiptPurchaseData(String str) {
        set(KEY_RECEIPT_PURCHASE_DATA, str);
    }

    public void setSignature(String str) {
        set(KEY_SIGNATURE, str);
    }

    public synchronized boolean set(String str, Object obj) {
        return setRaw(str, obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() {
        /*
            r6 = this;
            android.content.Context r0 = r6._context
            boolean r0 = com.unity3d.ads.device.StorageManager.init(r0)
            if (r0 == 0) goto L_0x0059
            com.unity3d.ads.device.StorageManager$StorageType r0 = com.unity3d.ads.device.StorageManager.StorageType.PUBLIC
            com.unity3d.ads.device.Storage r0 = com.unity3d.ads.device.StorageManager.getStorage(r0)
            org.json.JSONObject r1 = r6.getData()
            if (r1 == 0) goto L_0x005e
            if (r0 == 0) goto L_0x005e
            java.lang.String r1 = "iap.purchases"
            java.lang.Object r1 = r0.get(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0027
            org.json.JSONArray r1 = (org.json.JSONArray) r1     // Catch:{ Exception -> 0x0022 }
            goto L_0x0028
        L_0x0022:
            java.lang.String r1 = "Invalid object type for purchases"
            com.unity3d.ads.log.DeviceLog.error(r1)
        L_0x0027:
            r1 = r2
        L_0x0028:
            if (r1 != 0) goto L_0x002f
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
        L_0x002f:
            org.json.JSONObject r2 = r6.getData()
            java.lang.String r3 = "ts"
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0053 }
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x0053 }
            r1.put(r2)
            java.lang.String r2 = "iap.purchases"
            r0.set(r2, r1)
            r0.writeStorage()
            com.unity3d.ads.device.StorageEvent r1 = com.unity3d.ads.device.StorageEvent.SET
            java.lang.String r2 = "iap.purchases"
            java.lang.Object r2 = r0.get(r2)
            r0.sendEvent(r1, r2)
            goto L_0x005e
        L_0x0053:
            java.lang.String r0 = "Error constructing purchase object"
            com.unity3d.ads.log.DeviceLog.error(r0)
            return
        L_0x0059:
            java.lang.String r0 = "Unity Ads could not commit metadata due to storage error or the data is null"
            com.unity3d.ads.log.DeviceLog.error(r0)
        L_0x005e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.metadata.InAppPurchaseMetaData.commit():void");
    }
}
