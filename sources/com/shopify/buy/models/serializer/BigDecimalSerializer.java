package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;

public final class BigDecimalSerializer {
    private BigDecimalSerializer() {
    }

    public static BigDecimal decimalPropertyFromJson(@NonNull JSONObject jSONObject, @NonNull String str) throws JSONException {
        return new BigDecimal(jSONObject.getString(str)).setScale(2, 3);
    }

    public static BigDecimal nullableDecimalPropertyFromJson(@NonNull JSONObject jSONObject, @NonNull String str) throws JSONException {
        if (jSONObject.has(str)) {
            return decimalPropertyFromJson(jSONObject, str);
        }
        return null;
    }
}
