package com.vungle.warren.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {
    public static boolean hasNonNull(JsonElement jsonElement, String str) {
        if (jsonElement == null || jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
            return false;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (!asJsonObject.has(str) || asJsonObject.get(str) == null || asJsonObject.get(str).isJsonNull()) {
            return false;
        }
        return true;
    }
}
