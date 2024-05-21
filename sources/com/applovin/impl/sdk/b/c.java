package com.applovin.impl.sdk.b;

import android.content.Context;
import android.content.SharedPreferences;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    protected final j a;
    protected final p b;
    protected final Context c;
    protected final SharedPreferences d;
    private final Map<String, Object> e = new HashMap();
    private Map<String, Object> f;

    public c(j jVar) {
        this.a = jVar;
        this.b = jVar.v();
        this.c = jVar.x();
        this.d = this.c.getSharedPreferences("com.applovin.sdk.1", 0);
        try {
            Field a2 = n.a((Class) jVar.l().getClass(), "localSettings");
            a2.setAccessible(true);
            this.f = (HashMap) a2.get(jVar.l());
        } catch (Throwable unused) {
        }
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) throws JSONException {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    private <T> T c(b<T> bVar) {
        try {
            return bVar.a(this.f.get(bVar.a()));
        } catch (Throwable unused) {
            return null;
        }
    }

    private String e() {
        return "com.applovin.sdk." + n.a(this.a.t()) + ".";
    }

    public <ST> b<ST> a(String str, b<ST> bVar) {
        for (b<ST> next : b.c()) {
            if (next.a().equals(str)) {
                return next;
            }
        }
        return bVar;
    }

    public <T> T a(b<T> bVar) {
        if (bVar != null) {
            synchronized (this.e) {
                try {
                    T c2 = c(bVar);
                    if (c2 != null) {
                        return c2;
                    }
                    Object obj = this.e.get(bVar.a());
                    if (obj != null) {
                        T a2 = bVar.a(obj);
                        return a2;
                    }
                    T b2 = bVar.b();
                    return b2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            throw new IllegalArgumentException("No setting type specified");
        }
    }

    public void a() {
        if (this.c != null) {
            this.b.b("SettingsManager", "Saving settingsValues with the application...");
            String e2 = e();
            synchronized (this.e) {
                SharedPreferences.Editor edit = this.d.edit();
                for (b next : b.c()) {
                    Object obj = this.e.get(next.a());
                    if (obj != null) {
                        this.a.a(e2 + next.a(), obj, edit);
                    }
                }
                edit.apply();
            }
            this.b.a("SettingsManager", "Settings saved with the application.");
            return;
        }
        throw new IllegalArgumentException("No context specified");
    }

    public <T> void a(b<?> bVar, Object obj) {
        if (bVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        } else if (obj != null) {
            synchronized (this.e) {
                this.e.put(bVar.a(), obj);
            }
            p pVar = this.b;
            pVar.a("SettingsManager", "Setting update: " + bVar.a() + " set to \"" + obj + "\"");
        } else {
            throw new IllegalArgumentException("No new value specified");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.applovin.sdk.AppLovinSdkSettings r9) {
        /*
            r8 = this;
            com.applovin.impl.sdk.p r0 = r8.b
            java.lang.String r1 = "SettingsManager"
            java.lang.String r2 = "Loading user-defined settings"
            r0.b(r1, r2)
            if (r9 != 0) goto L_0x000c
            return
        L_0x000c:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r8.e
            monitor-enter(r0)
            com.applovin.impl.sdk.j r1 = r8.a     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.W     // Catch:{ all -> 0x015d }
            java.lang.Object r1 = r1.a(r2)     // Catch:{ all -> 0x015d }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x015d }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x015d }
            if (r1 == 0) goto L_0x0032
            java.util.Map<java.lang.String, java.lang.Object> r1 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.W     // Catch:{ all -> 0x015d }
            java.lang.String r2 = r2.a()     // Catch:{ all -> 0x015d }
            boolean r3 = r9.isVerboseLoggingEnabled()     // Catch:{ all -> 0x015d }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x015d }
            r1.put(r2, r3)     // Catch:{ all -> 0x015d }
        L_0x0032:
            long r1 = r9.getBannerAdRefreshSeconds()     // Catch:{ all -> 0x015d }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r6 = 1
            r7 = 0
            if (r5 < 0) goto L_0x0067
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0048
            r3 = 30
            long r3 = java.lang.Math.max(r3, r1)     // Catch:{ all -> 0x015d }
        L_0x0048:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Long> r2 = com.applovin.impl.sdk.b.b.ct     // Catch:{ all -> 0x015d }
            java.lang.String r2 = r2.a()     // Catch:{ all -> 0x015d }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x015d }
            r1.put(r2, r3)     // Catch:{ all -> 0x015d }
            java.util.Map<java.lang.String, java.lang.Object> r1 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.cs     // Catch:{ all -> 0x015d }
            java.lang.String r2 = r2.a()     // Catch:{ all -> 0x015d }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x015d }
        L_0x0063:
            r1.put(r2, r3)     // Catch:{ all -> 0x015d }
            goto L_0x007a
        L_0x0067:
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x007a
            java.util.Map<java.lang.String, java.lang.Object> r1 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.cs     // Catch:{ all -> 0x015d }
            java.lang.String r2 = r2.a()     // Catch:{ all -> 0x015d }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r7)     // Catch:{ all -> 0x015d }
            goto L_0x0063
        L_0x007a:
            com.applovin.impl.sdk.j r1 = r8.a     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.bq     // Catch:{ all -> 0x015d }
            java.lang.Object r1 = r1.a(r2)     // Catch:{ all -> 0x015d }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x015d }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x015d }
            if (r1 == 0) goto L_0x00b7
            java.lang.String r1 = r9.getAutoPreloadSizes()     // Catch:{ all -> 0x015d }
            boolean r2 = com.applovin.impl.sdk.e.k.b(r1)     // Catch:{ all -> 0x015d }
            if (r2 != 0) goto L_0x0096
            java.lang.String r1 = "NONE"
        L_0x0096:
            java.lang.String r2 = "NONE"
            boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x015d }
            if (r2 == 0) goto L_0x00ac
            java.util.Map<java.lang.String, java.lang.Object> r1 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.String> r2 = com.applovin.impl.sdk.b.b.aQ     // Catch:{ all -> 0x015d }
            java.lang.String r2 = r2.a()     // Catch:{ all -> 0x015d }
            java.lang.String r3 = ""
            r1.put(r2, r3)     // Catch:{ all -> 0x015d }
            goto L_0x00b7
        L_0x00ac:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.String> r3 = com.applovin.impl.sdk.b.b.aQ     // Catch:{ all -> 0x015d }
            java.lang.String r3 = r3.a()     // Catch:{ all -> 0x015d }
            r2.put(r3, r1)     // Catch:{ all -> 0x015d }
        L_0x00b7:
            com.applovin.impl.sdk.j r1 = r8.a     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.br     // Catch:{ all -> 0x015d }
            java.lang.Object r1 = r1.a(r2)     // Catch:{ all -> 0x015d }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x015d }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x015d }
            if (r1 == 0) goto L_0x015b
            java.lang.String r9 = r9.getAutoPreloadTypes()     // Catch:{ all -> 0x015d }
            boolean r1 = com.applovin.impl.sdk.e.k.b(r9)     // Catch:{ all -> 0x015d }
            if (r1 != 0) goto L_0x00d3
            java.lang.String r9 = "NONE"
        L_0x00d3:
            java.lang.String r1 = "NONE"
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x015d }
            if (r1 != 0) goto L_0x012c
            java.util.List r9 = com.applovin.impl.sdk.e.d.a((java.lang.String) r9)     // Catch:{ all -> 0x015d }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x015d }
            r1 = 0
            r2 = 0
        L_0x00e5:
            boolean r3 = r9.hasNext()     // Catch:{ all -> 0x015d }
            if (r3 == 0) goto L_0x012e
            java.lang.Object r3 = r9.next()     // Catch:{ all -> 0x015d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x015d }
            com.applovin.sdk.AppLovinAdType r4 = com.applovin.sdk.AppLovinAdType.REGULAR     // Catch:{ all -> 0x015d }
            java.lang.String r4 = r4.getLabel()     // Catch:{ all -> 0x015d }
            boolean r4 = r3.equals(r4)     // Catch:{ all -> 0x015d }
            if (r4 == 0) goto L_0x00ff
            r7 = 1
            goto L_0x00e5
        L_0x00ff:
            com.applovin.sdk.AppLovinAdType r4 = com.applovin.sdk.AppLovinAdType.INCENTIVIZED     // Catch:{ all -> 0x015d }
            java.lang.String r4 = r4.getLabel()     // Catch:{ all -> 0x015d }
            boolean r4 = r3.equals(r4)     // Catch:{ all -> 0x015d }
            if (r4 != 0) goto L_0x012a
            java.lang.String r4 = "INCENT"
            boolean r4 = r3.contains(r4)     // Catch:{ all -> 0x015d }
            if (r4 != 0) goto L_0x012a
            java.lang.String r4 = "REWARD"
            boolean r4 = r3.contains(r4)     // Catch:{ all -> 0x015d }
            if (r4 == 0) goto L_0x011c
            goto L_0x012a
        L_0x011c:
            com.applovin.sdk.AppLovinAdType r4 = com.applovin.sdk.AppLovinAdType.NATIVE     // Catch:{ all -> 0x015d }
            java.lang.String r4 = r4.getLabel()     // Catch:{ all -> 0x015d }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x015d }
            if (r3 == 0) goto L_0x00e5
            r2 = 1
            goto L_0x00e5
        L_0x012a:
            r1 = 1
            goto L_0x00e5
        L_0x012c:
            r1 = 0
            r2 = 0
        L_0x012e:
            if (r7 != 0) goto L_0x013d
            java.util.Map<java.lang.String, java.lang.Object> r9 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.String> r3 = com.applovin.impl.sdk.b.b.aQ     // Catch:{ all -> 0x015d }
            java.lang.String r3 = r3.a()     // Catch:{ all -> 0x015d }
            java.lang.String r4 = ""
            r9.put(r3, r4)     // Catch:{ all -> 0x015d }
        L_0x013d:
            java.util.Map<java.lang.String, java.lang.Object> r9 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r3 = com.applovin.impl.sdk.b.b.aR     // Catch:{ all -> 0x015d }
            java.lang.String r3 = r3.a()     // Catch:{ all -> 0x015d }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x015d }
            r9.put(r3, r1)     // Catch:{ all -> 0x015d }
            java.util.Map<java.lang.String, java.lang.Object> r9 = r8.e     // Catch:{ all -> 0x015d }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.b.aS     // Catch:{ all -> 0x015d }
            java.lang.String r1 = r1.a()     // Catch:{ all -> 0x015d }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x015d }
            r9.put(r1, r2)     // Catch:{ all -> 0x015d }
        L_0x015b:
            monitor-exit(r0)     // Catch:{ all -> 0x015d }
            return
        L_0x015d:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x015d }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.b.c.a(com.applovin.sdk.AppLovinSdkSettings):void");
    }

    public void a(JSONObject jSONObject) {
        p pVar;
        String str;
        String str2;
        this.b.a("SettingsManager", "Loading settings...");
        synchronized (this.e) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next != null && next.length() > 0) {
                    try {
                        b<Long> a2 = a(next, (b) null);
                        if (a2 != null) {
                            this.e.put(a2.a(), a(next, jSONObject, a2.b()));
                            if (a2 == b.eX) {
                                this.e.put(b.eY.a(), Long.valueOf(System.currentTimeMillis()));
                            }
                        }
                    } catch (JSONException e2) {
                        th = e2;
                        pVar = this.b;
                        str = "SettingsManager";
                        str2 = "Unable to parse JSON settingsValues array";
                        pVar.b(str, str2, th);
                    } catch (Throwable th) {
                        th = th;
                        pVar = this.b;
                        str = "SettingsManager";
                        str2 = "Unable to convert setting object ";
                        pVar.b(str, str2, th);
                    }
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.applovin.impl.sdk.b.b<java.lang.String>, com.applovin.impl.sdk.b.b] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> b(com.applovin.impl.sdk.b.b<java.lang.String> r1) {
        /*
            r0 = this;
            java.lang.Object r1 = r0.a(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.util.List r1 = com.applovin.impl.sdk.e.d.a((java.lang.String) r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.b.c.b(com.applovin.impl.sdk.b.b):java.util.List");
    }

    public void b() {
        if (this.c != null) {
            this.b.b("SettingsManager", "Loading settingsValues saved with the application...");
            String e2 = e();
            synchronized (this.e) {
                for (b next : b.c()) {
                    try {
                        Object a2 = this.a.a(e2 + next.a(), null, next.b().getClass(), this.d);
                        if (a2 != null) {
                            this.e.put(next.a(), a2);
                        }
                    } catch (Exception e3) {
                        p pVar = this.b;
                        pVar.b("SettingsManager", "Unable to load \"" + next.a() + "\"", e3);
                    }
                }
            }
            return;
        }
        throw new IllegalArgumentException("No context specified");
    }

    public void c() {
        synchronized (this.e) {
            this.e.clear();
        }
        this.a.a(this.d);
    }

    public boolean d() {
        return this.a.l().isVerboseLoggingEnabled() || ((Boolean) a(b.W)).booleanValue();
    }
}
