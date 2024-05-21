package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.shopify.buy.models.ShippingMethod;
import com.shopify.buy.utils.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShippingMethodSerializer {
    @VisibleForTesting
    static final String AMOUNT = "Amount";
    @VisibleForTesting
    static final String DETAIL = "Detail";
    @VisibleForTesting
    static final String IDENTIFIER = "Identifier";
    @VisibleForTesting
    static final String LABEL = "Label";

    private ShippingMethodSerializer() {
    }

    public static ShippingMethod fromJsonString(@NonNull String str) throws JSONException {
        return fromJson(new JSONObject(str));
    }

    public static ShippingMethod fromJson(@NonNull JSONObject jSONObject) throws JSONException {
        return new ShippingMethod(jSONObject.getString(IDENTIFIER), jSONObject.getString(DETAIL), jSONObject.getString(LABEL), BigDecimalSerializer.decimalPropertyFromJson(jSONObject, AMOUNT));
    }

    public static List<ShippingMethod> listFromJsonString(@NonNull String str) throws JSONException {
        JSONArray jSONArray = new JSONArray(str);
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(fromJson(jSONArray.getJSONObject(i)));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static JSONObject toJson(@NonNull ShippingMethod shippingMethod) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(IDENTIFIER, shippingMethod.identifier).put(DETAIL, shippingMethod.detail).put(LABEL, shippingMethod.label).put(AMOUNT, shippingMethod.amount);
        } catch (JSONException e) {
            String simpleName = shippingMethod.getClass().getSimpleName();
            Logger.error("Failed to convert " + simpleName + " into a JSON String.");
            e.printStackTrace();
        }
        return jSONObject;
    }
}
