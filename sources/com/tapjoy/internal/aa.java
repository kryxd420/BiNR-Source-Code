package com.tapjoy.internal;

import android.util.Log;
import javax.annotation.Nullable;

public final class aa {
    public static void a(String str, String str2, Object... objArr) {
        a(6, str, str2, objArr);
    }

    public static void a(int i, String str, @Nullable String str2, @Nullable Throwable th) {
        if (str2 != null) {
            Log.println(i, str, str2);
        }
        if (th != null) {
            Log.println(i, str, Log.getStackTraceString(th));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r10, java.lang.String r11, java.lang.String r12, java.lang.Object... r13) {
        /*
            r0 = 0
            r1 = 1
            if (r13 == 0) goto L_0x0013
            int r2 = r13.length
            if (r2 != 0) goto L_0x0008
            goto L_0x0013
        L_0x0008:
            int r2 = r13.length
            int r2 = r2 - r1
            r2 = r13[r2]
            boolean r3 = r2 instanceof java.lang.Throwable
            if (r3 == 0) goto L_0x0013
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            goto L_0x0014
        L_0x0013:
            r2 = r0
        L_0x0014:
            if (r12 != 0) goto L_0x001d
            com.tapjoy.internal.jj r12 = new com.tapjoy.internal.jj
            r12.<init>(r0, r13, r2)
            goto L_0x00e4
        L_0x001d:
            if (r13 != 0) goto L_0x0027
            com.tapjoy.internal.jj r13 = new com.tapjoy.internal.jj
            r13.<init>(r12)
            r12 = r13
            goto L_0x00e4
        L_0x0027:
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            int r4 = r12.length()
            int r4 = r4 + 50
            r3.<init>(r4)
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x0035:
            int r7 = r13.length
            if (r5 >= r7) goto L_0x00c2
            java.lang.String r7 = "{}"
            int r7 = r12.indexOf(r7, r6)
            r8 = -1
            if (r7 != r8) goto L_0x0061
            if (r6 != 0) goto L_0x004b
            com.tapjoy.internal.jj r0 = new com.tapjoy.internal.jj
            r0.<init>(r12, r13, r2)
            r12 = r0
            goto L_0x00e4
        L_0x004b:
            int r0 = r12.length()
            java.lang.String r12 = r12.substring(r6, r0)
            r3.append(r12)
            com.tapjoy.internal.jj r12 = new com.tapjoy.internal.jj
            java.lang.String r0 = r3.toString()
            r12.<init>(r0, r13, r2)
            goto L_0x00e4
        L_0x0061:
            r8 = 92
            if (r7 == 0) goto L_0x006f
            int r9 = r7 + -1
            char r9 = r12.charAt(r9)
            if (r9 != r8) goto L_0x006f
            r9 = 1
            goto L_0x0070
        L_0x006f:
            r9 = 0
        L_0x0070:
            if (r9 == 0) goto L_0x00ab
            r9 = 2
            if (r7 < r9) goto L_0x007f
            int r9 = r7 + -2
            char r9 = r12.charAt(r9)
            if (r9 != r8) goto L_0x007f
            r8 = 1
            goto L_0x0080
        L_0x007f:
            r8 = 0
        L_0x0080:
            if (r8 != 0) goto L_0x0095
            int r5 = r5 + -1
            int r8 = r7 + -1
            java.lang.String r6 = r12.substring(r6, r8)
            r3.append(r6)
            r6 = 123(0x7b, float:1.72E-43)
            r3.append(r6)
            int r7 = r7 + 1
            goto L_0x00be
        L_0x0095:
            int r8 = r7 + -1
            java.lang.String r6 = r12.substring(r6, r8)
            r3.append(r6)
            r6 = r13[r5]
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            com.tapjoy.internal.jk.a((java.lang.StringBuffer) r3, (java.lang.Object) r6, (java.util.Map) r8)
            int r7 = r7 + 2
            goto L_0x00be
        L_0x00ab:
            java.lang.String r6 = r12.substring(r6, r7)
            r3.append(r6)
            r6 = r13[r5]
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            com.tapjoy.internal.jk.a((java.lang.StringBuffer) r3, (java.lang.Object) r6, (java.util.Map) r8)
            int r7 = r7 + 2
        L_0x00be:
            r6 = r7
            int r5 = r5 + r1
            goto L_0x0035
        L_0x00c2:
            int r4 = r12.length()
            java.lang.String r12 = r12.substring(r6, r4)
            r3.append(r12)
            int r12 = r13.length
            int r12 = r12 - r1
            if (r5 >= r12) goto L_0x00db
            com.tapjoy.internal.jj r12 = new com.tapjoy.internal.jj
            java.lang.String r0 = r3.toString()
            r12.<init>(r0, r13, r2)
            goto L_0x00e4
        L_0x00db:
            com.tapjoy.internal.jj r12 = new com.tapjoy.internal.jj
            java.lang.String r1 = r3.toString()
            r12.<init>(r1, r13, r0)
        L_0x00e4:
            java.lang.String r13 = r12.b
            java.lang.Throwable r12 = r12.c
            a((int) r10, (java.lang.String) r11, (java.lang.String) r13, (java.lang.Throwable) r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.aa.a(int, java.lang.String, java.lang.String, java.lang.Object[]):void");
    }
}
