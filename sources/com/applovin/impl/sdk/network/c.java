package com.applovin.impl.sdk.network;

import android.content.Context;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.b.e;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final Object a = new Object();

    private static JSONObject a(String str, Context context) {
        JSONObject b = b(context);
        if (b == null) {
            b = new JSONObject();
        }
        if (!b.has(str)) {
            try {
                b.put(str, new JSONObject());
            } catch (JSONException unused) {
            }
        }
        return b;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:5|6|7|8|9|10|11|12|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0023 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0026 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(int r4, java.lang.String r5, android.content.Context r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            java.lang.Object r0 = a
            monitor-enter(r0)
            java.lang.String r5 = com.applovin.impl.sdk.e.n.d((java.lang.String) r5)     // Catch:{ all -> 0x002b }
            org.json.JSONObject r1 = a((java.lang.String) r5, (android.content.Context) r6)     // Catch:{ all -> 0x002b }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x002b }
            org.json.JSONObject r2 = r1.optJSONObject(r5)     // Catch:{ all -> 0x002b }
            int r3 = r2.optInt(r4)     // Catch:{ all -> 0x002b }
            int r3 = r3 + 1
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x0023 }
        L_0x0023:
            r1.put(r5, r2)     // Catch:{ JSONException -> 0x0026 }
        L_0x0026:
            a((org.json.JSONObject) r1, (android.content.Context) r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.network.c.a(int, java.lang.String, android.content.Context):void");
    }

    public static void a(Context context) {
        synchronized (a) {
            e.a(d.l, context);
        }
    }

    private static void a(JSONObject jSONObject, Context context) {
        e.a(d.l, jSONObject.toString(), context);
    }

    public static JSONObject b(Context context) {
        JSONObject jSONObject;
        synchronized (a) {
            try {
                jSONObject = new JSONObject((String) e.b(d.l, "{}", context));
            } catch (JSONException unused) {
                return new JSONObject();
            } catch (Throwable th) {
                throw th;
            }
        }
        return jSONObject;
    }
}
