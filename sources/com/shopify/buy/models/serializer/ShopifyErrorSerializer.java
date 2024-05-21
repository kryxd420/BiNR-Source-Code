package com.shopify.buy.models.serializer;

import com.shopify.buy.models.ShopifyError;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShopifyErrorSerializer {
    private static final String DESCRIPTION = "Description";
    private static final String ERROR_TYPE = "ErrorType";

    private ShopifyErrorSerializer() {
    }

    public static ShopifyError fromJson(JSONObject jSONObject) throws JSONException {
        return new ShopifyError(ShopifyError.ErrorType.ofName(jSONObject.getString(ERROR_TYPE)), jSONObject.getString(DESCRIPTION));
    }
}
