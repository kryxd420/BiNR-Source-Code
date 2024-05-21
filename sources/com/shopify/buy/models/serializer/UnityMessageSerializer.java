package com.shopify.buy.models.serializer;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.shopify.buy.models.UnityMessage;
import org.json.JSONException;
import org.json.JSONObject;

public final class UnityMessageSerializer {
    @VisibleForTesting
    static final String CONTENT_KEY = "Content";
    @VisibleForTesting
    static final String IDENTIFIER_KEY = "Identifier";

    private UnityMessageSerializer() {
    }

    public static JSONObject toJson(@NonNull UnityMessage unityMessage) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(IDENTIFIER_KEY, unityMessage.identifier).put(CONTENT_KEY, unityMessage.content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
