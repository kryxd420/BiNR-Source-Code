package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import com.shopify.buy.models.AndroidPayEventResponse;
import org.json.JSONException;
import org.json.JSONObject;

public final class AndroidPayEventResponseSerializer {
    private static final String COUNTRY_CODE = "countryCode";
    private static final String CURRENCY_CODE = "currencyCode";
    private static final String MERCHANT_NAME = "merchantName";
    private static final String PRICING_LINE_ITEMS = "pricingLineItems";
    private static final String REQUIRES_SHIPPING = "requiresShipping";
    private static final String SHIPPING_METHODS = "shippingMethods";

    private AndroidPayEventResponseSerializer() {
    }

    public static AndroidPayEventResponse fromJsonString(@NonNull String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        return new AndroidPayEventResponse(jSONObject.getString(MERCHANT_NAME), PricingLineItemsSerializer.fromJson(jSONObject.getJSONObject(PRICING_LINE_ITEMS)), jSONObject.getString(CURRENCY_CODE), jSONObject.getString(COUNTRY_CODE), jSONObject.getBoolean(REQUIRES_SHIPPING), ShippingMethodSerializer.listFromJsonString(jSONObject.getJSONArray(SHIPPING_METHODS).toString()));
    }
}
