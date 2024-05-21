package com.tapdaq.sdk.model;

import com.google.gson.Gson;
import org.json.JSONObject;

public class TMModel {
    public String toString() {
        return new Gson().toJson((Object) this);
    }

    public JSONObject getJSONObject() {
        return new JSONObject();
    }
}
