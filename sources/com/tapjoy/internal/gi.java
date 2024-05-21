package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;

public final class gi {
    private static final gi d;
    private static gi e;
    public Boolean a = null;
    public String b = null;
    public boolean c = false;
    private Context f;

    static {
        gi giVar = new gi();
        d = giVar;
        e = giVar;
    }

    public static gi a() {
        return e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A[Catch:{ all -> 0x000a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x000c
            android.content.Context r0 = r4.f     // Catch:{ all -> 0x000a }
            if (r0 != 0) goto L_0x000c
            r4.f = r5     // Catch:{ all -> 0x000a }
            goto L_0x000c
        L_0x000a:
            r5 = move-exception
            goto L_0x006e
        L_0x000c:
            com.tapjoy.internal.gi r5 = e     // Catch:{ all -> 0x000a }
            android.content.Context r0 = r5.f     // Catch:{ all -> 0x000a }
            r1 = 0
            if (r0 == 0) goto L_0x0041
            android.content.Context r0 = r5.f     // Catch:{ all -> 0x000a }
            java.lang.String r2 = "tjcPrefrences"
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r2, r1)     // Catch:{ all -> 0x000a }
            java.lang.Boolean r2 = r5.a     // Catch:{ all -> 0x000a }
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = "gdpr"
            boolean r2 = r0.contains(r2)     // Catch:{ all -> 0x000a }
            if (r2 == 0) goto L_0x0033
            java.lang.String r2 = "gdpr"
            boolean r2 = r0.getBoolean(r2, r1)     // Catch:{ all -> 0x000a }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x000a }
            r5.a = r2     // Catch:{ all -> 0x000a }
        L_0x0033:
            java.lang.String r2 = r5.b     // Catch:{ all -> 0x000a }
            if (r2 != 0) goto L_0x0041
            java.lang.String r2 = "cgdpr"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getString(r2, r3)     // Catch:{ all -> 0x000a }
            r5.b = r0     // Catch:{ all -> 0x000a }
        L_0x0041:
            boolean r5 = r4.c     // Catch:{ all -> 0x000a }
            if (r5 == 0) goto L_0x006c
            com.tapjoy.internal.gi r5 = e     // Catch:{ all -> 0x000a }
            android.content.Context r0 = r5.f     // Catch:{ all -> 0x000a }
            r2 = 1
            if (r0 == 0) goto L_0x0066
            java.lang.Boolean r0 = r5.a     // Catch:{ all -> 0x000a }
            if (r0 == 0) goto L_0x0055
            boolean r0 = r5.b()     // Catch:{ all -> 0x000a }
            goto L_0x0056
        L_0x0055:
            r0 = 0
        L_0x0056:
            java.lang.String r3 = r5.b     // Catch:{ all -> 0x000a }
            if (r3 == 0) goto L_0x0064
            if (r0 == 0) goto L_0x0066
            boolean r5 = r5.c()     // Catch:{ all -> 0x000a }
            if (r5 == 0) goto L_0x0066
            r5 = 1
            goto L_0x0067
        L_0x0064:
            r5 = r0
            goto L_0x0067
        L_0x0066:
            r5 = 0
        L_0x0067:
            if (r5 != 0) goto L_0x006a
            r1 = 1
        L_0x006a:
            r4.c = r1     // Catch:{ all -> 0x000a }
        L_0x006c:
            monitor-exit(r4)
            return
        L_0x006e:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.gi.a(android.content.Context):void");
    }

    public final boolean b() {
        if (this.f == null) {
            return false;
        }
        SharedPreferences.Editor edit = this.f.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        edit.putBoolean("gdpr", this.a.booleanValue());
        edit.apply();
        return true;
    }

    public final boolean c() {
        if (this.f == null) {
            return false;
        }
        SharedPreferences.Editor edit = this.f.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        edit.putString("cgdpr", this.b);
        edit.apply();
        return true;
    }
}
