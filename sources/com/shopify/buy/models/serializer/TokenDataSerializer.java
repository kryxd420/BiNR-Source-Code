package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import com.shopify.buy.models.TokenData;
import com.shopify.buy.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class TokenDataSerializer {
    private static final String PAYMENT_DATA = "PaymentData";
    private static final String TRANSACTION_IDENTIFIER = "TransactionIdentifier";

    private TokenDataSerializer() {
    }

    public static JSONObject toJson(@NonNull TokenData tokenData) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TRANSACTION_IDENTIFIER, tokenData.token).put(PAYMENT_DATA, tokenData.token);
        } catch (JSONException e) {
            String simpleName = tokenData.getClass().getSimpleName();
            Logger.error("Failed to convert " + simpleName + " into a JSON String.");
            e.printStackTrace();
        }
        return jSONObject;
    }
}
