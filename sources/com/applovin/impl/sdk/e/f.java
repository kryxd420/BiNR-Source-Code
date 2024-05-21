package com.applovin.impl.sdk.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.os.EnvironmentCompat;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.b.c;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.k;
import com.applovin.impl.sdk.p;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapjoy.TapjoyConstants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private static final int[] a = {7, 4, 2, 1, 11};
    private static final int[] b = {5, 6, 10, 3, 9, 8, 14};
    private static final int[] c = {15, 12, 13};
    private static final String d = f.class.getSimpleName();

    private static NetworkInfo a(Context context) {
        ConnectivityManager connectivityManager;
        if (!k.a("android.permission.ACCESS_NETWORK_STATE", context) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static String a(InputStream inputStream, j jVar) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[((Integer) jVar.a(b.dQ)).intValue()];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            jVar.v().b(d, "Encountered error while reading stream", th);
            return null;
        }
    }

    public static String a(String str, j jVar) {
        return a((String) jVar.a(b.aF), str, jVar);
    }

    public static String a(String str, String str2, j jVar) {
        if (str == null || str.length() < 4) {
            throw new IllegalArgumentException("Invalid domain specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (jVar != null) {
            return str + str2;
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    public static JSONObject a(JSONObject jSONObject) throws JSONException {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    public static void a(int i, j jVar) {
        b bVar;
        boolean z;
        c w = jVar.w();
        if (i == 401) {
            w.a((b<?>) b.T, (Object) "");
            bVar = b.V;
            z = "";
        } else if (i == 418) {
            bVar = b.S;
            z = true;
        } else if ((i >= 400 && i < 500) || i == -1) {
            jVar.f();
            return;
        } else {
            return;
        }
        w.a((b<?>) bVar, z);
        w.a();
    }

    private static boolean a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, j jVar) {
        if (!(k.a("android.permission.ACCESS_NETWORK_STATE", context) && context.getSystemService("connectivity") != null)) {
            return true;
        }
        NetworkInfo a2 = a(context);
        return a2 != null ? a2.isConnected() : ((Boolean) jVar.a(b.dL)).booleanValue();
    }

    public static String b(String str, j jVar) {
        return a((String) jVar.a(b.aG), str, jVar);
    }

    public static Map<String, String> c(j jVar) {
        HashMap hashMap = new HashMap();
        String str = (String) jVar.a(b.V);
        if (k.b(str)) {
            hashMap.put("device_token", str);
        } else if (!((Boolean) jVar.a(b.eV)).booleanValue()) {
            hashMap.put(TapjoyConstants.TJC_API_KEY, jVar.t());
        }
        hashMap.put("sc", k.e((String) jVar.a(b.X)));
        hashMap.put("sc2", k.e((String) jVar.a(b.Y)));
        hashMap.put("server_installed_at", k.e((String) jVar.a(b.Z)));
        n.a("persisted_data", k.e((String) jVar.a(d.s)), (Map<String, String>) hashMap);
        return hashMap;
    }

    public static void c(JSONObject jSONObject, j jVar) {
        String a2 = g.a(jSONObject, "persisted_data", (String) null, jVar);
        if (k.b(a2)) {
            jVar.a(d.s, a2);
            jVar.v().b(d, "Updated persisted data");
        }
    }

    public static String d(j jVar) {
        NetworkInfo a2 = a(jVar.x());
        if (a2 == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        int type = a2.getType();
        int subtype = a2.getSubtype();
        String str = type == 1 ? TapjoyConstants.TJC_CONNECTION_TYPE_WIFI : type == 0 ? a(subtype, a) ? "2g" : a(subtype, b) ? "3g" : a(subtype, c) ? "4g" : TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE : EnvironmentCompat.MEDIA_UNKNOWN;
        p v = jVar.v();
        String str2 = d;
        v.a(str2, "Network " + type + "/" + subtype + " resolved to " + str);
        return str;
    }

    public static void d(JSONObject jSONObject, j jVar) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (jVar != null) {
            try {
                if (jSONObject.has(TapdaqPlacement.TDPTagSettings)) {
                    c w = jVar.w();
                    if (!jSONObject.isNull(TapdaqPlacement.TDPTagSettings)) {
                        w.a(jSONObject.getJSONObject(TapdaqPlacement.TDPTagSettings));
                        w.a();
                        jVar.v().a(d, "New settings processed");
                    }
                }
            } catch (JSONException e) {
                jVar.v().b(d, "Unable to parse settings out of API response", e);
            }
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    public static String e(j jVar) {
        return a((String) jVar.a(b.aD), "4.0/ad", jVar);
    }

    public static void e(JSONObject jSONObject, j jVar) {
        JSONArray a2 = g.a(jSONObject, "zones", (JSONArray) null, jVar);
        if (a2 != null) {
            Iterator it = jVar.P().a(a2).iterator();
            while (it.hasNext()) {
                com.applovin.impl.sdk.ad.d dVar = (com.applovin.impl.sdk.ad.d) it.next();
                if (dVar.d()) {
                    jVar.p().preloadAds(dVar);
                } else {
                    jVar.o().preloadAds(dVar);
                }
            }
            jVar.M().a((LinkedHashSet) jVar.P().b());
            jVar.N().a((LinkedHashSet) jVar.P().b());
        }
    }

    public static String f(j jVar) {
        return a((String) jVar.a(b.aE), "4.0/ad", jVar);
    }

    public static void f(JSONObject jSONObject, j jVar) {
        JSONObject a2 = g.a(jSONObject, "variables", (JSONObject) null, jVar);
        if (a2 != null) {
            jVar.s().updateVariables(a2);
        }
    }
}
