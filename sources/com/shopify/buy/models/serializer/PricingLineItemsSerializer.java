package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import com.shopify.buy.models.PricingLineItems;
import com.shopify.buy.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class PricingLineItemsSerializer {
    private static final String SHIPPING_PRICE = "shippingPrice";
    private static final String SUBTOTAL = "subtotal";
    private static final String TAX_PRICE = "taxPrice";
    private static final String TOTAL_PRICE = "totalPrice";

    private PricingLineItemsSerializer() {
    }

    public static PricingLineItems fromJsonString(@NonNull String str) throws JSONException {
        return fromJson(new JSONObject(str));
    }

    public static PricingLineItems fromJson(@NonNull JSONObject jSONObject) throws JSONException {
        return new PricingLineItems(BigDecimalSerializer.decimalPropertyFromJson(jSONObject, SUBTOTAL), BigDecimalSerializer.decimalPropertyFromJson(jSONObject, TAX_PRICE), BigDecimalSerializer.decimalPropertyFromJson(jSONObject, TOTAL_PRICE), jSONObject.has(SHIPPING_PRICE) ? BigDecimalSerializer.nullableDecimalPropertyFromJson(jSONObject, SHIPPING_PRICE) : null);
    }

    public static JSONObject toJson(@NonNull PricingLineItems pricingLineItems) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SUBTOTAL, pricingLineItems.subtotal.toString()).put(TAX_PRICE, pricingLineItems.taxPrice.toString()).put(TOTAL_PRICE, pricingLineItems.totalPrice.toString());
            if (pricingLineItems.shippingPrice != null) {
                jSONObject.put(SHIPPING_PRICE, pricingLineItems.shippingPrice.doubleValue());
            }
        } catch (JSONException e) {
            String simpleName = pricingLineItems.getClass().getSimpleName();
            Logger.error("Failed to convert " + simpleName + " into a JSON String.");
            e.printStackTrace();
        }
        return jSONObject;
    }
}
