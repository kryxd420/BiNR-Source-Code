package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.shopify.buy.models.NativePayment;
import com.shopify.buy.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class NativePaymentSerializer {
    @VisibleForTesting
    static final String BILLING_CONTACT = "BillingContact";
    @VisibleForTesting
    static final String IDENTIFIER = "Identifier";
    @VisibleForTesting
    static final String SHIPPING_CONTACT = "ShippingContact";
    @VisibleForTesting
    static final String TOKEN_DATA = "TokenData";

    private NativePaymentSerializer() {
    }

    public static JSONObject toJson(@NonNull NativePayment nativePayment) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BILLING_CONTACT, MailingAddressInputSerializer.toJson(nativePayment.billingContact));
            if (nativePayment.shippingContact != null) {
                jSONObject.put(SHIPPING_CONTACT, MailingAddressInputSerializer.toJson(nativePayment.shippingContact));
            }
            jSONObject.put(IDENTIFIER, nativePayment.identifier).put(TOKEN_DATA, TokenDataSerializer.toJson(nativePayment.tokenData));
        } catch (JSONException e) {
            String simpleName = nativePayment.getClass().getSimpleName();
            Logger.error("Failed to convert " + simpleName + " into a JSON String.");
            e.printStackTrace();
        }
        return jSONObject;
    }
}
