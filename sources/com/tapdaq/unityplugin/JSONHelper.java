package com.tapdaq.unityplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
    public static JSONObject AddValue(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static <T> T GetValue(JSONObject jSONObject, String str) {
        try {
            return jSONObject.get(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String ObjectToJsonStr(Object obj) {
        if (!(obj instanceof Map)) {
            return obj instanceof ArrayList ? new JSONArray((ArrayList) obj).toString() : "";
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll((Map) obj);
        return new JSONObject(hashMap).toString();
    }

    public static JSONObject MapToJson(Map<Object, Object> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            AddValue(jSONObject, next.getKey().toString(), next.getValue());
        }
        return jSONObject;
    }
}
