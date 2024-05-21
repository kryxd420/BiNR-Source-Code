package com.applovin.impl.sdk.e;

import android.os.Bundle;
import android.text.TextUtils;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    public static float a(JSONObject jSONObject, String str, float f, j jVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return f;
        }
        try {
            double d = jSONObject.getDouble(str);
            return (-3.4028234663852886E38d >= d || d >= 3.4028234663852886E38d) ? f : (float) d;
        } catch (JSONException e) {
            if (jVar == null) {
                return f;
            }
            p v = jVar.v();
            v.b("JsonUtils", "Failed to retrieve float property for key = " + str, e);
            return f;
        }
    }

    public static int a(JSONObject jSONObject, String str, int i, j jVar) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to retrieve int property for key = " + str, e);
                }
            }
        }
        return i;
    }

    public static long a(JSONObject jSONObject, String str, long j, j jVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return j;
        }
        try {
            return jSONObject.getLong(str);
        } catch (JSONException e) {
            if (jVar == null) {
                return j;
            }
            p v = jVar.v();
            v.b("JsonUtils", "Failed to retrieve int property for key = " + str, e);
            return j;
        }
    }

    public static Boolean a(JSONObject jSONObject, String str, Boolean bool, j jVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return bool;
        }
        try {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        } catch (JSONException unused) {
            boolean z = true;
            if (a(jSONObject, str, (bool == null || !bool.booleanValue()) ? 0 : 1, jVar) <= 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
    }

    private static Object a(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        return obj instanceof JSONObject ? a((JSONObject) obj) : obj instanceof JSONArray ? b((JSONArray) obj) : obj;
    }

    public static Object a(JSONArray jSONArray, int i, Object obj, j jVar) {
        if (jSONArray != null && jSONArray.length() > i) {
            try {
                return jSONArray.get(i);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to retrieve object at index " + i + " for JSON array", e);
                }
            }
        }
        return obj;
    }

    public static Object a(JSONObject jSONObject, String str, Object obj, j jVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return obj;
        }
        try {
            Object obj2 = jSONObject.get(str);
            return obj2 != null ? obj2 : obj;
        } catch (JSONException e) {
            if (jVar == null) {
                return obj;
            }
            p v = jVar.v();
            v.b("JsonUtils", "Failed to retrieve Object for key = " + str, e);
            return obj;
        }
    }

    public static String a(JSONObject jSONObject, j jVar) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.toString(4);
        } catch (JSONException e) {
            if (jVar != null) {
                jVar.v().b("JsonUtils", "Failed to convert to indented string", e);
            }
            return jSONObject.toString();
        }
    }

    public static String a(JSONObject jSONObject, String str, String str2, j jVar) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to retrieve string property for key = " + str, e);
                }
            }
        }
        return str2;
    }

    public static ArrayList<Bundle> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList<>();
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(b(jSONArray.optJSONObject(i)));
        }
        return arrayList;
    }

    public static Map<String, String> a(Bundle bundle) throws JSONException {
        HashMap hashMap = new HashMap(bundle.size());
        for (String str : bundle.keySet()) {
            hashMap.put(str, String.valueOf(bundle.get(str)));
        }
        return hashMap;
    }

    public static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, a(jSONObject.get(next)).toString());
        }
        return hashMap;
    }

    public static JSONArray a(JSONObject jSONObject, String str, JSONArray jSONArray, j jVar) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to retrieve JSON array for key = " + str, e);
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject a(String str, j jVar) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            p v = jVar.v();
            v.d("JsonUtils", "Failed to deserialize into JSON: " + str);
            return null;
        }
    }

    public static JSONObject a(Map<String, ?> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            jSONObject.put((String) next.getKey(), next.getValue());
        }
        return jSONObject;
    }

    public static JSONObject a(JSONArray jSONArray, int i, JSONObject jSONObject, j jVar) {
        if (jSONArray != null && i < jSONArray.length()) {
            try {
                return jSONArray.getJSONObject(i);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to retrieve JSON object from array for index = " + i, e);
                }
            }
        }
        return jSONObject;
    }

    public static JSONObject a(JSONObject jSONObject, String str, JSONObject jSONObject2, j jVar) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getJSONObject(str);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to retrieve JSON property for key = " + str, e);
                }
            }
        }
        return jSONObject2;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static Bundle b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return Bundle.EMPTY;
        }
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (jSONObject.isNull(next)) {
                bundle.putString(next, (String) null);
            } else {
                Object opt = jSONObject.opt(next);
                if (opt instanceof JSONObject) {
                    bundle.putBundle(next, b((JSONObject) opt));
                } else if (opt instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) opt;
                    if (jSONArray.length() == 0) {
                        bundle.putStringArrayList(next, new ArrayList(0));
                    } else if (a(jSONArray, 0, (Object) null, (j) null) instanceof String) {
                        ArrayList arrayList = new ArrayList(jSONArray.length());
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add((String) a(jSONArray, i, (Object) null, (j) null));
                        }
                        bundle.putStringArrayList(next, arrayList);
                    } else {
                        bundle.putParcelableArrayList(next, a(jSONArray));
                    }
                } else if (opt instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) opt).booleanValue());
                } else if (opt instanceof String) {
                    bundle.putString(next, (String) opt);
                } else if (opt instanceof Integer) {
                    bundle.putInt(next, ((Integer) opt).intValue());
                } else if (opt instanceof Long) {
                    bundle.putLong(next, ((Long) opt).longValue());
                } else if (opt instanceof Double) {
                    bundle.putDouble(next, ((Double) opt).doubleValue());
                }
            }
        }
        return bundle;
    }

    public static List b(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.get(i)));
        }
        return arrayList;
    }

    public static void b(JSONObject jSONObject, String str, long j, j jVar) {
        if (jSONObject != null) {
            try {
                jSONObject.put(str, j);
            } catch (JSONException e) {
                if (jVar != null) {
                    p v = jVar.v();
                    v.b("JsonUtils", "Failed to put long property for key = " + str, e);
                }
            }
        }
    }
}
