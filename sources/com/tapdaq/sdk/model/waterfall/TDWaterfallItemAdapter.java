package com.tapdaq.sdk.model.waterfall;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class TDWaterfallItemAdapter implements JsonDeserializer {
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || !asJsonObject.has("demand_type") || !asJsonObject.get("demand_type").getAsString().equalsIgnoreCase("sdk_bidding")) {
            return new Gson().fromJson((JsonElement) asJsonObject, TDWaterfallItem.class);
        }
        return new Gson().fromJson((JsonElement) asJsonObject, TDWaterfallBiddingItem.class);
    }
}
