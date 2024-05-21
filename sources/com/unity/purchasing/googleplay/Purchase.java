package com.unity.purchasing.googleplay;

import android.text.TextUtils;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import org.json.JSONException;
import org.json.JSONObject;

public class Purchase {
    boolean autoRenewing;
    String mDeveloperPayload;
    String mItemType;
    String mOrderId;
    String mOriginalJson;
    String mPackageName;
    int mPurchaseState;
    long mPurchaseTime;
    String mSignature;
    String mSku;
    String mToken;

    public Purchase(String str, String str2, String str3) throws JSONException {
        this.mItemType = str;
        this.mOriginalJson = str2;
        JSONObject jSONObject = new JSONObject(this.mOriginalJson);
        this.mOrderId = jSONObject.optString("orderId");
        this.mPackageName = jSONObject.optString("packageName");
        this.mSku = jSONObject.optString(InAppPurchaseMetaData.KEY_PRODUCT_ID);
        this.mPurchaseTime = jSONObject.optLong("purchaseTime");
        this.mPurchaseState = jSONObject.optInt("purchaseState");
        this.mDeveloperPayload = jSONObject.optString("developerPayload");
        this.mToken = jSONObject.optString("token", jSONObject.optString("purchaseToken"));
        this.mSignature = str3;
        this.autoRenewing = jSONObject.optBoolean("autoRenewing");
    }

    public String getItemType() {
        return this.mItemType;
    }

    public String getOrderIdOrPurchaseToken() {
        if (TextUtils.isEmpty(this.mOrderId)) {
            return this.mToken;
        }
        return this.mOrderId;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getSku() {
        return this.mSku;
    }

    public long getPurchaseTime() {
        return this.mPurchaseTime;
    }

    public int getPurchaseState() {
        return this.mPurchaseState;
    }

    public String getDeveloperPayload() {
        return this.mDeveloperPayload;
    }

    public String getToken() {
        return this.mToken;
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public boolean getAutoRenewing() {
        return this.autoRenewing;
    }

    public String toString() {
        return "PurchaseInfo(type:" + this.mItemType + "):" + this.mOriginalJson;
    }
}
