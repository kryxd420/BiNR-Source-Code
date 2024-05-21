package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class y {
    y() {
    }

    static JSONObject a() {
        return new JSONObject();
    }

    static JSONObject a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            new aa.a().a(e.toString()).a(aa.h);
            return new JSONObject();
        }
    }

    static JSONArray b() {
        return new JSONArray();
    }

    static JSONArray b(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException e) {
            new aa.a().a(e.toString()).a(aa.h);
            return new JSONArray();
        }
    }

    static JSONObject a(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            new aa.a().a(e.toString()).a(aa.h);
            return new JSONObject();
        }
    }

    static Object a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.get(str);
        } catch (JSONException unused) {
            return false;
        }
    }

    static Object b(JSONArray jSONArray, int i) {
        try {
            return jSONArray.get(i);
        } catch (JSONException unused) {
            return false;
        }
    }

    static String c(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getString(i);
        } catch (JSONException unused) {
            return "";
        }
    }

    static String b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return "";
        }
    }

    static int c(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getInt(str);
        } catch (JSONException unused) {
            return 0;
        }
    }

    static int a(JSONObject jSONObject, String str, int i) {
        try {
            return jSONObject.getInt(str);
        } catch (JSONException unused) {
            return i;
        }
    }

    static boolean d(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException unused) {
            return false;
        }
    }

    static double e(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    static JSONObject f(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    static JSONObject d(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    static JSONArray g(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONArray(str);
        } catch (JSONException unused) {
            return new JSONArray();
        }
    }

    static boolean a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putString(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static boolean b(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putInteger(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putLong(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putBoolean(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        try {
            jSONObject.put(str, jSONArray);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putArray(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        try {
            jSONObject.put(str, jSONObject2);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putObject(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, double d) {
        try {
            jSONObject.put(str, d);
            return true;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCJSON putDouble(): ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static void e(JSONArray jSONArray, int i) {
        jSONArray.put(i);
    }

    static void a(JSONArray jSONArray, boolean z) {
        jSONArray.put(z);
    }

    static void a(JSONArray jSONArray, String str) {
        jSONArray.put(str);
    }

    static void a(JSONArray jSONArray, Object obj) {
        jSONArray.put(obj);
    }

    static boolean h(JSONObject jSONObject, String str) {
        try {
            a.a().j().a(str, jSONObject.toString(), false);
            return true;
        } catch (IOException e) {
            new aa.a().a("IOException in ADCJSON's saveObject: ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static JSONObject c(String str) {
        try {
            return a(a.a().j().a(str, false).toString());
        } catch (IOException e) {
            new aa.a().a("IOException in ADCJSON's loadObject: ").a(e.toString()).a(aa.h);
            return a();
        }
    }

    static String[] a(JSONArray jSONArray) {
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = c(jSONArray, i);
        }
        return strArr;
    }

    static JSONArray a(String[] strArr) {
        JSONArray b = b();
        for (String a : strArr) {
            a(b, a);
        }
        return b;
    }

    static boolean b(JSONArray jSONArray, String str) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (c(jSONArray, i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    static boolean i(JSONObject jSONObject, String str) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (str.equals(keys.next())) {
                return true;
            }
        }
        return false;
    }

    static JSONArray a(JSONArray jSONArray, String[] strArr, boolean z) {
        for (String str : strArr) {
            if (!z || !b(jSONArray, str)) {
                a(jSONArray, str);
            }
        }
        return jSONArray;
    }

    static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
