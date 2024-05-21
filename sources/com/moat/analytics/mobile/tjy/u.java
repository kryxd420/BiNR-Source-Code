package com.moat.analytics.mobile.tjy;

import android.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;

public class u {
    private boolean a = false;
    private boolean b = false;
    private int c = 200;

    public u(String str) {
        a(str);
    }

    private boolean a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == Build.VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public void a(String str) {
        int i;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("sa");
            boolean equals = string.equals("8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
            if ((string.equals("on") || equals) && !a(jSONObject)) {
                this.a = true;
                this.b = equals;
            }
            if (jSONObject.has("in") && (i = jSONObject.getInt("in")) >= 100 && i <= 1000) {
                this.c = i;
            }
        } catch (Exception unused) {
            this.a = false;
            this.b = false;
            this.c = 200;
        }
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }
}
