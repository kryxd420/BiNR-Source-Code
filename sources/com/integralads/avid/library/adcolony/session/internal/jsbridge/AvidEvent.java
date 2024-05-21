package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import org.json.JSONObject;

public class AvidEvent {
    private int a;
    private String b;
    private JSONObject c;

    public AvidEvent() {
    }

    public AvidEvent(int i, String str, JSONObject jSONObject) {
        this.a = i;
        this.b = str;
        this.c = jSONObject;
    }

    public AvidEvent(int i, String str) {
        this(i, str, (JSONObject) null);
    }

    public int getTag() {
        return this.a;
    }

    public void setTag(int i) {
        this.a = i;
    }

    public String getType() {
        return this.b;
    }

    public void setType(String str) {
        this.b = str;
    }

    public JSONObject getData() {
        return this.c;
    }

    public void setData(JSONObject jSONObject) {
        this.c = jSONObject;
    }
}
