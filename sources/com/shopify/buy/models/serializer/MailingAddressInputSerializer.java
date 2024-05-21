package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.shopify.buy.models.MailingAddressInput;
import org.json.JSONException;
import org.json.JSONObject;

public final class MailingAddressInputSerializer {
    @VisibleForTesting
    static final String ADDRESS_1 = "address1";
    @VisibleForTesting
    static final String ADDRESS_2 = "address2";
    @VisibleForTesting
    static final String CITY = "city";
    @VisibleForTesting
    static final String COUNTRY = "country";
    @VisibleForTesting
    static final String FIRST_NAME = "firstName";
    @VisibleForTesting
    static final String LAST_NAME = "lastName";
    @VisibleForTesting
    static final String PHONE = "phone";
    @VisibleForTesting
    static final String PROVINCE = "province";
    @VisibleForTesting
    static final String ZIP = "zip";

    private MailingAddressInputSerializer() {
    }

    public static MailingAddressInput fromJson(@NonNull JSONObject jSONObject) throws JSONException {
        return MailingAddressInput.newBuilder().address1(jSONObject.getString(ADDRESS_1)).address2(jSONObject.getString(ADDRESS_2)).city(jSONObject.getString(CITY)).country(jSONObject.getString(COUNTRY)).firstName(jSONObject.getString(FIRST_NAME)).lastName(jSONObject.getString(LAST_NAME)).phone(jSONObject.getString(PHONE)).province(jSONObject.getString(PROVINCE)).zip(jSONObject.getString(ZIP)).build();
    }

    public static JSONObject toJson(@NonNull MailingAddressInput mailingAddressInput) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ADDRESS_1, mailingAddressInput.address1).put(ADDRESS_2, mailingAddressInput.address2).put(CITY, mailingAddressInput.city).put(COUNTRY, mailingAddressInput.country).put(FIRST_NAME, mailingAddressInput.firstName).put(LAST_NAME, mailingAddressInput.lastName).put(PHONE, mailingAddressInput.phone).put(PROVINCE, mailingAddressInput.province).put(ZIP, mailingAddressInput.zip);
        } catch (JSONException e) {
            Log.e("ShopifyBuyPlugin", "Failed to convert MailingAddressInput into a JSON String.");
            e.printStackTrace();
        }
        return jSONObject;
    }
}
