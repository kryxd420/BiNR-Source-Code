package com.unity.purchasing.common;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class StoreDeserializer implements INativeStore, IStore {
    public void RetrieveProducts(String str) {
        RetrieveProducts(DeserializeProducts(str));
    }

    public void Purchase(String str, String str2) {
        Purchase(GetProductDefinition(str), str2);
    }

    public void FinishTransaction(String str, String str2) {
        FinishTransaction(GetProductDefinition(str), str2);
    }

    public static List<ProductDefinition> DeserializeProducts(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(GetProductDefinition(jSONArray.getJSONObject(i)));
            }
            return arrayList;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductDefinition GetProductDefinition(String str) {
        if (str == null) {
            return null;
        }
        try {
            return GetProductDefinition(new JSONObject(str));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static ProductDefinition GetProductDefinition(JSONObject jSONObject) {
        try {
            return new ProductDefinition(jSONObject.getString("id"), jSONObject.getString("storeSpecificId"), ProductType.valueOf(jSONObject.getString("type")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
