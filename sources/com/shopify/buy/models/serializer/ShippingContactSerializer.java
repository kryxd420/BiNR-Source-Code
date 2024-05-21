package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import com.shopify.buy.models.ShippingContact;
import com.shopify.buy.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShippingContactSerializer {
    private static final String EMAIL = "email";

    private ShippingContactSerializer() {
    }

    public static ShippingContact fromJson(@NonNull JSONObject jSONObject) throws JSONException {
        return new ShippingContact(MailingAddressInputSerializer.fromJson(jSONObject), jSONObject.getString("email"));
    }

    public static JSONObject toJson(@NonNull ShippingContact shippingContact) {
        JSONObject json = MailingAddressInputSerializer.toJson(shippingContact);
        try {
            json.put("email", shippingContact.email);
        } catch (JSONException e) {
            String simpleName = shippingContact.getClass().getSimpleName();
            Logger.error("Failed to convert " + simpleName + " into a JSON String.");
            e.printStackTrace();
        }
        return json;
    }
}
