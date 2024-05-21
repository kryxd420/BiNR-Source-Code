package com.tapjoy.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.fe;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fl;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public final class hg {
    public static final String a = UUID.randomUUID().toString();
    private static hg d;
    public final fl.a b = new fl.a();
    public final hn c;
    private final fe.a e = new fe.a();
    private final ey.a f = new ey.a();
    private final Context g;

    public static synchronized hg a(Context context) {
        hg hgVar;
        synchronized (hg.class) {
            if (d == null) {
                d = new hg(context, hn.a(context));
            }
            hgVar = d;
        }
        return hgVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x03bb  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x03c8  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0298  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02be A[SYNTHETIC, Splitter:B:82:0x02be] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0313  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0318  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0330  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0379 A[SYNTHETIC, Splitter:B:98:0x0379] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private hg(android.content.Context r8, com.tapjoy.internal.hn r9) {
        /*
            r7 = this;
            r7.<init>()
            com.tapjoy.internal.hr.a()
            com.tapjoy.internal.fe$a r0 = new com.tapjoy.internal.fe$a
            r0.<init>()
            r7.e = r0
            com.tapjoy.internal.ey$a r0 = new com.tapjoy.internal.ey$a
            r0.<init>()
            r7.f = r0
            com.tapjoy.internal.fl$a r0 = new com.tapjoy.internal.fl$a
            r0.<init>()
            r7.b = r0
            com.tapjoy.internal.fe$a r0 = r7.e
            java.lang.String r1 = "12.1.0/Android"
            r0.p = r1
            com.tapjoy.internal.fe$a r0 = r7.e
            java.lang.String r1 = "Android"
            r0.g = r1
            com.tapjoy.internal.fe$a r0 = r7.e
            java.lang.String r1 = android.os.Build.VERSION.RELEASE
            r0.h = r1
            com.tapjoy.internal.fe$a r0 = r7.e
            java.lang.String r1 = android.os.Build.MANUFACTURER
            r0.e = r1
            com.tapjoy.internal.fe$a r0 = r7.e
            java.lang.String r1 = android.os.Build.MODEL
            r0.f = r1
            com.tapjoy.internal.fe$a r0 = r7.e
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r1 = r1.toString()
            r0.l = r1
            com.tapjoy.internal.fe$a r0 = r7.e
            java.util.TimeZone r1 = java.util.TimeZone.getDefault()
            java.lang.String r1 = r1.getID()
            r0.m = r1
            android.content.Context r8 = r8.getApplicationContext()
            r7.g = r8
            com.tapjoy.internal.fe$a r0 = r7.e
            com.tapjoy.internal.gc r1 = com.tapjoy.internal.ge.b()
            java.lang.String r2 = "TJC_OPTION_DISABLE_ANDROID_ID_AS_ANALYTICS_ID"
            boolean r1 = r1.b(r2)
            r2 = 0
            if (r1 != 0) goto L_0x007d
            android.content.ContentResolver r1 = r8.getContentResolver()
            java.lang.String r3 = "android_id"
            java.lang.String r1 = android.provider.Settings.Secure.getString(r1, r3)
            java.lang.String r3 = "9774d56d682e549c"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x007d
            java.lang.String r1 = com.tapjoy.internal.ju.b(r1)
            goto L_0x007e
        L_0x007d:
            r1 = r2
        L_0x007e:
            if (r1 != 0) goto L_0x009e
            java.io.File r1 = com.tapjoy.internal.u.a(r8)
            java.io.File r3 = new java.io.File
            java.io.File r4 = com.tapjoy.internal.hd.c((android.content.Context) r8)
            java.lang.String r5 = "deviceid"
            r3.<init>(r4, r5)
            if (r1 == 0) goto L_0x0099
            java.io.File r4 = new java.io.File
            java.lang.String r5 = ".io.5rocks"
            r4.<init>(r1, r5)
            goto L_0x009a
        L_0x0099:
            r4 = r2
        L_0x009a:
            java.lang.String r1 = com.tapjoy.internal.y.a(r3, r4)
        L_0x009e:
            r0.d = r1
            java.lang.String r0 = com.tapjoy.internal.z.a(r8)
            if (r0 == 0) goto L_0x00b8
            com.tapjoy.internal.fe$a r1 = r7.e
            java.lang.String r3 = ":"
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replace(r3, r4)
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r0 = r0.toLowerCase(r3)
            r1.c = r0
        L_0x00b8:
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r8.getSystemService(r1)
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1
            if (r1 == 0) goto L_0x00ee
            java.lang.String r3 = r1.getSimCountryIso()
            boolean r4 = com.tapjoy.internal.ju.c(r3)
            if (r4 != 0) goto L_0x00da
            com.tapjoy.internal.fe$a r4 = r7.e
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r3 = r3.toUpperCase(r5)
            r4.q = r3
        L_0x00da:
            java.lang.String r1 = r1.getNetworkCountryIso()
            boolean r3 = com.tapjoy.internal.ju.c(r1)
            if (r3 != 0) goto L_0x00ee
            com.tapjoy.internal.fe$a r3 = r7.e
            java.util.Locale r4 = java.util.Locale.US
            java.lang.String r1 = r1.toUpperCase(r4)
            r3.r = r1
        L_0x00ee:
            java.lang.String r8 = r8.getPackageName()
            com.tapjoy.internal.fe$a r1 = r7.e
            r1.n = r8
            com.tapjoy.internal.fe$a r1 = r7.e
            android.content.pm.Signature[] r3 = com.tapjoy.internal.ac.e(r0, r8)
            r4 = 2
            if (r3 == 0) goto L_0x0112
            int r5 = r3.length
            if (r5 <= 0) goto L_0x0112
            r5 = 0
            r3 = r3[r5]
            byte[] r3 = r3.toByteArray()
            byte[] r3 = com.tapjoy.internal.ck.a(r3)
            java.lang.String r3 = android.util.Base64.encodeToString(r3, r4)
            goto L_0x0113
        L_0x0112:
            r3 = r2
        L_0x0113:
            java.lang.String r3 = com.tapjoy.internal.ju.a(r3)
            r1.o = r3
            com.tapjoy.internal.ey$a r1 = r7.f
            java.lang.String r3 = com.tapjoy.internal.ac.a(r0, r8)
            r1.c = r3
            com.tapjoy.internal.ey$a r1 = r7.f
            int r3 = com.tapjoy.internal.ac.b(r0, r8)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.d = r3
            java.lang.String r1 = r0.getInstallerPackageName(r8)
            boolean r3 = com.tapjoy.internal.ju.c(r1)
            if (r3 != 0) goto L_0x013b
            com.tapjoy.internal.ey$a r3 = r7.f
            r3.f = r1
        L_0x013b:
            java.lang.String r8 = a((android.content.pm.PackageManager) r0, (java.lang.String) r8)
            boolean r0 = com.tapjoy.internal.ju.c(r8)
            if (r0 != 0) goto L_0x0149
            com.tapjoy.internal.ey$a r0 = r7.f
            r0.g = r8
        L_0x0149:
            r7.a()
            r7.c = r9
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.c
            java.lang.String r8 = r8.a()
            if (r8 == 0) goto L_0x0173
            int r9 = r8.length()
            if (r9 <= 0) goto L_0x0173
            com.tapjoy.internal.fe$a r9 = r7.e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r8)
            java.lang.String r8 = " 12.1.0/Android"
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r9.p = r8
        L_0x0173:
            com.tapjoy.internal.hn r8 = r7.c
            java.lang.String r8 = r8.b()
            if (r8 == 0) goto L_0x017f
            com.tapjoy.internal.fl$a r9 = r7.b
            r9.d = r8
        L_0x017f:
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            android.content.SharedPreferences r0 = r9.b
            java.lang.String r1 = "it"
            r5 = 0
            long r0 = r0.getLong(r1, r5)
            int r3 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x01df
            android.content.Context r0 = r9.a
            android.content.pm.PackageManager r1 = r0.getPackageManager()
            java.lang.String r0 = r0.getPackageName()
            long r0 = com.tapjoy.internal.ac.c(r1, r0)
            int r3 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x01d0
            android.content.Context r0 = r9.a
            java.io.File r0 = com.tapjoy.internal.hd.d((android.content.Context) r0)
            long r0 = r0.lastModified()
            int r3 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x01d0
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r9.a
            android.content.pm.PackageManager r3 = r1.getPackageManager()
            java.lang.String r1 = r1.getPackageName()
            java.lang.String r1 = com.tapjoy.internal.ac.d(r3, r1)
            r0.<init>(r1)
            long r0 = r0.lastModified()
            int r3 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x01d0
            long r0 = java.lang.System.currentTimeMillis()
        L_0x01d0:
            android.content.SharedPreferences r9 = r9.b
            android.content.SharedPreferences$Editor r9 = r9.edit()
            java.lang.String r3 = "it"
            android.content.SharedPreferences$Editor r9 = r9.putLong(r3, r0)
            r9.apply()
        L_0x01df:
            java.lang.Long r9 = java.lang.Long.valueOf(r0)
            r8.c = r9
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.k r8 = r8.f
            int r8 = r8.b()
            com.tapjoy.internal.fl$a r9 = r7.b
            r0 = 7
            int r0 = a((int) r0, (int) r8)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9.e = r0
            com.tapjoy.internal.fl$a r9 = r7.b
            r0 = 30
            int r8 = a((int) r0, (int) r8)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.f = r8
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.k r8 = r8.h
            int r8 = r8.b()
            if (r8 <= 0) goto L_0x021a
            com.tapjoy.internal.fl$a r9 = r7.b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.h = r8
        L_0x021a:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.l r8 = r8.i
            long r8 = r8.a()
            int r0 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x022e
            com.tapjoy.internal.fl$a r0 = r7.b
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r0.i = r8
        L_0x022e:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.l r8 = r8.j
            long r8 = r8.a()
            int r0 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0242
            com.tapjoy.internal.fl$a r0 = r7.b
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r0.j = r8
        L_0x0242:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.l r8 = r8.k
            long r8 = r8.a()
            int r0 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0256
            com.tapjoy.internal.fl$a r0 = r7.b
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r0.k = r8
        L_0x0256:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.l
            java.lang.String r8 = r8.a()
            if (r8 == 0) goto L_0x0264
            com.tapjoy.internal.fl$a r9 = r7.b
            r9.l = r8
        L_0x0264:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.k r8 = r8.m
            int r8 = r8.b()
            if (r8 <= 0) goto L_0x0276
            com.tapjoy.internal.fl$a r9 = r7.b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.m = r8
        L_0x0276:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.i r8 = r8.n
            double r8 = r8.a()
            r0 = 0
            int r3 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r3 == 0) goto L_0x028c
            com.tapjoy.internal.fl$a r3 = r7.b
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r3.n = r8
        L_0x028c:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.l r8 = r8.o
            long r8 = r8.a()
            int r3 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x02a0
            com.tapjoy.internal.fl$a r3 = r7.b
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r3.o = r8
        L_0x02a0:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.i r8 = r8.p
            double r8 = r8.a()
            int r3 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r3 == 0) goto L_0x02b4
            com.tapjoy.internal.fl$a r0 = r7.b
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r0.p = r8
        L_0x02b4:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.g
            java.lang.String r8 = r8.a()
            if (r8 == 0) goto L_0x02ea
            byte[] r8 = android.util.Base64.decode(r8, r4)     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            com.tapjoy.internal.en r9 = com.tapjoy.internal.fj.c     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            java.lang.Object r8 = r9.a((byte[]) r8)     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            com.tapjoy.internal.fj r8 = (com.tapjoy.internal.fj) r8     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            com.tapjoy.internal.fl$a r9 = r7.b     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            java.util.List r9 = r9.g     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            r9.clear()     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            com.tapjoy.internal.fl$a r9 = r7.b     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            java.util.List r9 = r9.g     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            java.util.List r8 = r8.d     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            r9.addAll(r8)     // Catch:{ IllegalArgumentException -> 0x02e3, IOException -> 0x02db }
            goto L_0x02ea
        L_0x02db:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.g
            r8.c()
            goto L_0x02ea
        L_0x02e3:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.g
            r8.c()
        L_0x02ea:
            com.tapjoy.internal.ey$a r8 = r7.f
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.q
            java.lang.String r9 = r9.a()
            r8.e = r9
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.r
            java.lang.String r9 = r9.a()
            r8.s = r9
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.k r8 = r8.s
            java.lang.Integer r8 = r8.a()
            int r8 = r8.intValue()
            com.tapjoy.internal.fl$a r9 = r7.b
            r0 = -1
            if (r8 == r0) goto L_0x0318
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            goto L_0x0319
        L_0x0318:
            r8 = r2
        L_0x0319:
            r9.t = r8
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.k r8 = r8.t
            java.lang.Integer r8 = r8.a()
            int r8 = r8.intValue()
            com.tapjoy.internal.fl$a r9 = r7.b
            if (r8 == r0) goto L_0x0330
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            goto L_0x0331
        L_0x0330:
            r8 = r2
        L_0x0331:
            r9.u = r8
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.u
            java.lang.String r9 = r9.a()
            r8.v = r9
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.v
            java.lang.String r9 = r9.a()
            r8.w = r9
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.w
            java.lang.String r9 = r9.a()
            r8.x = r9
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.x
            java.lang.String r9 = r9.a()
            r8.y = r9
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.o r9 = r9.y
            java.lang.String r9 = r9.a()
            r8.z = r9
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.z
            java.lang.String r8 = r8.a()
            if (r8 == 0) goto L_0x03a5
            byte[] r8 = android.util.Base64.decode(r8, r4)     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            com.tapjoy.internal.en r9 = com.tapjoy.internal.fk.c     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            java.lang.Object r8 = r9.a((byte[]) r8)     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            com.tapjoy.internal.fk r8 = (com.tapjoy.internal.fk) r8     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            com.tapjoy.internal.fl$a r9 = r7.b     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            java.util.List r9 = r9.A     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            r9.clear()     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            com.tapjoy.internal.fl$a r9 = r7.b     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            java.util.List r9 = r9.A     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            java.util.List r8 = r8.d     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            r9.addAll(r8)     // Catch:{ IllegalArgumentException -> 0x039e, IOException -> 0x0396 }
            goto L_0x03a5
        L_0x0396:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.z
            r8.c()
            goto L_0x03a5
        L_0x039e:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.z
            r8.c()
        L_0x03a5:
            com.tapjoy.internal.hn r8 = r7.c
            com.tapjoy.internal.o r8 = r8.A
            java.lang.String r8 = r8.a()
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.h r9 = r9.B
            java.lang.Boolean r9 = r9.a()
            boolean r9 = r9.booleanValue()
            if (r8 == 0) goto L_0x03c8
            com.tapjoy.internal.fl$a r0 = r7.b
            r0.q = r8
            com.tapjoy.internal.fl$a r8 = r7.b
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            r8.r = r9
            goto L_0x03d0
        L_0x03c8:
            com.tapjoy.internal.fl$a r8 = r7.b
            r8.q = r2
            com.tapjoy.internal.fl$a r8 = r7.b
            r8.r = r2
        L_0x03d0:
            com.tapjoy.internal.fl$a r8 = r7.b
            com.tapjoy.internal.hn r9 = r7.c
            com.tapjoy.internal.h r9 = r9.C
            java.lang.Boolean r9 = r9.a()
            r8.B = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hg.<init>(android.content.Context, com.tapjoy.internal.hn):void");
    }

    private static String a(PackageManager packageManager, String str) {
        Object obj;
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            if (bundle == null || (obj = bundle.get("com.tapjoy.appstore")) == null) {
                return null;
            }
            return obj.toString().trim();
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0057 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r4 = this;
            monitor-enter(r4)
            android.util.DisplayMetrics r0 = new android.util.DisplayMetrics     // Catch:{ Exception -> 0x0057 }
            r0.<init>()     // Catch:{ Exception -> 0x0057 }
            android.content.Context r1 = r4.g     // Catch:{ Exception -> 0x0057 }
            java.lang.String r2 = "window"
            java.lang.Object r1 = r1.getSystemService(r2)     // Catch:{ Exception -> 0x0057 }
            android.view.WindowManager r1 = (android.view.WindowManager) r1     // Catch:{ Exception -> 0x0057 }
            android.view.Display r1 = r1.getDefaultDisplay()     // Catch:{ Exception -> 0x0057 }
            r1.getMetrics(r0)     // Catch:{ Exception -> 0x0057 }
            android.app.Activity r1 = com.tapjoy.internal.gv.a()     // Catch:{ Exception -> 0x0057 }
            if (r1 == 0) goto L_0x0036
            android.view.Window r1 = r1.getWindow()     // Catch:{ Exception -> 0x0057 }
            if (r1 == 0) goto L_0x0036
            int r2 = r0.heightPixels     // Catch:{ Exception -> 0x0057 }
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch:{ Exception -> 0x0057 }
            r3.<init>()     // Catch:{ Exception -> 0x0057 }
            android.view.View r1 = r1.getDecorView()     // Catch:{ Exception -> 0x0057 }
            r1.getWindowVisibleDisplayFrame(r3)     // Catch:{ Exception -> 0x0057 }
            int r1 = r3.top     // Catch:{ Exception -> 0x0057 }
            int r2 = r2 - r1
            r0.heightPixels = r2     // Catch:{ Exception -> 0x0057 }
        L_0x0036:
            com.tapjoy.internal.fe$a r1 = r4.e     // Catch:{ Exception -> 0x0057 }
            int r2 = r0.densityDpi     // Catch:{ Exception -> 0x0057 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0057 }
            r1.i = r2     // Catch:{ Exception -> 0x0057 }
            com.tapjoy.internal.fe$a r1 = r4.e     // Catch:{ Exception -> 0x0057 }
            int r2 = r0.widthPixels     // Catch:{ Exception -> 0x0057 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0057 }
            r1.j = r2     // Catch:{ Exception -> 0x0057 }
            com.tapjoy.internal.fe$a r1 = r4.e     // Catch:{ Exception -> 0x0057 }
            int r0 = r0.heightPixels     // Catch:{ Exception -> 0x0057 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0057 }
            r1.k = r0     // Catch:{ Exception -> 0x0057 }
            goto L_0x0057
        L_0x0055:
            r0 = move-exception
            goto L_0x0059
        L_0x0057:
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            return
        L_0x0059:
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hg.a():void");
    }

    public final ff b() {
        ff ffVar;
        synchronized (this) {
            this.e.l = Locale.getDefault().toString();
            this.e.m = TimeZone.getDefault().getID();
            boolean z = false;
            long currentTimeMillis = System.currentTimeMillis() - 259200000;
            Iterator it = this.b.g.iterator();
            while (it.hasNext()) {
                if (((fi) it.next()).g.longValue() <= currentTimeMillis) {
                    it.remove();
                    z = true;
                }
            }
            if (z) {
                g();
            }
            ffVar = new ff(this.e.b(), this.f.b(), this.b.b());
        }
        return ffVar;
    }

    /* access modifiers changed from: package-private */
    public final String c() {
        String a2;
        synchronized (this) {
            a2 = this.c.d.a();
        }
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00dc  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.tapjoy.internal.fg d() {
        /*
            r20 = this;
            r1 = r20
            monitor-enter(r20)
            java.util.Calendar r0 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x0141 }
            r2 = 1
            int r3 = r0.get(r2)     // Catch:{ all -> 0x0141 }
            int r3 = r3 * 10000
            r4 = 2
            int r5 = r0.get(r4)     // Catch:{ all -> 0x0141 }
            int r5 = r5 * 100
            int r3 = r3 + r5
            int r3 = r3 + 100
            r5 = 5
            int r6 = r0.get(r5)     // Catch:{ all -> 0x0141 }
            int r3 = r3 + r6
            com.tapjoy.internal.hn r6 = r1.c     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.k r6 = r6.e     // Catch:{ all -> 0x0141 }
            java.lang.Integer r6 = r6.a()     // Catch:{ all -> 0x0141 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0141 }
            r7 = 0
            if (r6 == r3) goto L_0x013f
            if (r6 != 0) goto L_0x004a
            com.tapjoy.internal.fl$a r0 = r1.b     // Catch:{ all -> 0x0141 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0141 }
            r0.e = r4     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.fl$a r0 = r1.b     // Catch:{ all -> 0x0141 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0141 }
            r0.f = r4     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.fg r0 = new com.tapjoy.internal.fg     // Catch:{ all -> 0x0141 }
            java.lang.String r4 = "fq7_0_1"
            java.lang.String r5 = "fq30_0_1"
            r0.<init>(r4, r5, r7)     // Catch:{ all -> 0x0141 }
            goto L_0x012f
        L_0x004a:
            com.tapjoy.internal.hn r8 = r1.c     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.k r8 = r8.f     // Catch:{ all -> 0x0141 }
            java.lang.Integer r8 = r8.a()     // Catch:{ all -> 0x0141 }
            int r8 = r8.intValue()     // Catch:{ all -> 0x0141 }
            r9 = 7
            int r10 = a((int) r9, (int) r8)     // Catch:{ all -> 0x0141 }
            r11 = 30
            int r12 = a((int) r11, (int) r8)     // Catch:{ all -> 0x0141 }
            java.util.Calendar r13 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x0141 }
            int r14 = r6 / 10000
            int r15 = r6 / 100
            int r15 = r15 % 100
            int r15 = r15 - r2
            int r6 = r6 % 100
            r13.set(r14, r15, r6)     // Catch:{ all -> 0x0141 }
            int r6 = r0.get(r2)     // Catch:{ all -> 0x0141 }
            int r14 = r13.get(r2)     // Catch:{ all -> 0x0141 }
            int r6 = r6 - r14
            int r6 = java.lang.Integer.signum(r6)     // Catch:{ all -> 0x0141 }
            r14 = -1
            if (r6 == r14) goto L_0x00a8
            if (r6 == r2) goto L_0x008e
            r4 = 6
            int r0 = r0.get(r4)     // Catch:{ all -> 0x0141 }
            int r4 = r13.get(r4)     // Catch:{ all -> 0x0141 }
            int r0 = r0 - r4
            goto L_0x00d4
        L_0x008e:
            java.lang.Object r14 = r0.clone()     // Catch:{ all -> 0x0141 }
            java.util.Calendar r14 = (java.util.Calendar) r14     // Catch:{ all -> 0x0141 }
            int r15 = r13.get(r2)     // Catch:{ all -> 0x0141 }
            int r4 = r13.get(r4)     // Catch:{ all -> 0x0141 }
            int r13 = r13.get(r5)     // Catch:{ all -> 0x0141 }
            r14.set(r15, r4, r13)     // Catch:{ all -> 0x0141 }
            long r16 = r0.getTimeInMillis()     // Catch:{ all -> 0x0141 }
            goto L_0x00c1
        L_0x00a8:
            java.lang.Object r14 = r13.clone()     // Catch:{ all -> 0x0141 }
            java.util.Calendar r14 = (java.util.Calendar) r14     // Catch:{ all -> 0x0141 }
            int r15 = r0.get(r2)     // Catch:{ all -> 0x0141 }
            int r4 = r0.get(r4)     // Catch:{ all -> 0x0141 }
            int r0 = r0.get(r5)     // Catch:{ all -> 0x0141 }
            r14.set(r15, r4, r0)     // Catch:{ all -> 0x0141 }
            long r16 = r13.getTimeInMillis()     // Catch:{ all -> 0x0141 }
        L_0x00c1:
            r0 = 0
        L_0x00c2:
            long r18 = r14.getTimeInMillis()     // Catch:{ all -> 0x0141 }
            int r4 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r4 >= 0) goto L_0x00d0
            r14.add(r5, r2)     // Catch:{ all -> 0x0141 }
            int r0 = r0 + 1
            goto L_0x00c2
        L_0x00d0:
            if (r6 <= 0) goto L_0x00d3
            goto L_0x00d4
        L_0x00d3:
            int r0 = -r0
        L_0x00d4:
            int r4 = java.lang.Math.abs(r0)     // Catch:{ all -> 0x0141 }
            if (r4 < r11) goto L_0x00dc
            r15 = 0
            goto L_0x00e4
        L_0x00dc:
            if (r0 < 0) goto L_0x00e1
            int r15 = r8 << r0
            goto L_0x00e4
        L_0x00e1:
            int r0 = -r0
            int r15 = r8 >> r0
        L_0x00e4:
            r2 = r2 | r15
            int r0 = a((int) r9, (int) r2)     // Catch:{ all -> 0x0141 }
            int r4 = a((int) r11, (int) r2)     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.fl$a r5 = r1.b     // Catch:{ all -> 0x0141 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0141 }
            r5.e = r6     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.fl$a r5 = r1.b     // Catch:{ all -> 0x0141 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0141 }
            r5.f = r6     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.fg r5 = new com.tapjoy.internal.fg     // Catch:{ all -> 0x0141 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0141 }
            java.lang.String r8 = "fq7_"
            r6.<init>(r8)     // Catch:{ all -> 0x0141 }
            r6.append(r10)     // Catch:{ all -> 0x0141 }
            java.lang.String r8 = "_"
            r6.append(r8)     // Catch:{ all -> 0x0141 }
            r6.append(r0)     // Catch:{ all -> 0x0141 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0141 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0141 }
            java.lang.String r8 = "fq30_"
            r6.<init>(r8)     // Catch:{ all -> 0x0141 }
            r6.append(r12)     // Catch:{ all -> 0x0141 }
            java.lang.String r8 = "_"
            r6.append(r8)     // Catch:{ all -> 0x0141 }
            r6.append(r4)     // Catch:{ all -> 0x0141 }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x0141 }
            r5.<init>(r0, r4, r7)     // Catch:{ all -> 0x0141 }
            r0 = r5
        L_0x012f:
            com.tapjoy.internal.hn r4 = r1.c     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.k r4 = r4.e     // Catch:{ all -> 0x0141 }
            r4.a((int) r3)     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.hn r3 = r1.c     // Catch:{ all -> 0x0141 }
            com.tapjoy.internal.k r3 = r3.f     // Catch:{ all -> 0x0141 }
            r3.a((int) r2)     // Catch:{ all -> 0x0141 }
            monitor-exit(r20)     // Catch:{ all -> 0x0141 }
            return r0
        L_0x013f:
            monitor-exit(r20)     // Catch:{ all -> 0x0141 }
            return r7
        L_0x0141:
            r0 = move-exception
            monitor-exit(r20)     // Catch:{ all -> 0x0141 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hg.d():com.tapjoy.internal.fg");
    }

    private static int a(int i, int i2) {
        return Integer.bitCount(((1 << i) - 1) & i2);
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str, long j, boolean z) {
        synchronized (this) {
            int size = this.b.g.size();
            int i = 0;
            while (i < size) {
                fi fiVar = (fi) this.b.g.get(i);
                if (!fiVar.f.equals(str)) {
                    i++;
                } else if (!z) {
                    return false;
                } else {
                    fi.a b2 = fiVar.b();
                    b2.d = Long.valueOf(j);
                    this.b.g.set(i, b2.b());
                    return true;
                }
            }
            this.b.g.add(new fi(str, Long.valueOf(j)));
            g();
            return true;
        }
    }

    private void g() {
        this.c.g.a(Base64.encodeToString(fj.c.b(new fj(this.b.g)), 2));
    }

    public final boolean a(String str) {
        boolean z;
        synchronized (this) {
            this.c.q.a(str);
            z = true;
            if (str != null) {
                z = true ^ js.a(this.f.e, str);
                this.f.e = str;
            } else {
                if (this.f.e == null) {
                    z = false;
                }
                this.f.e = null;
            }
        }
        return z;
    }

    public final boolean b(String str) {
        boolean z;
        synchronized (this) {
            this.c.r.a(str);
            z = !js.a(this.b.s, str);
            if (z) {
                this.b.s = str;
            }
        }
        return z;
    }

    public final boolean a(Integer num) {
        boolean z;
        synchronized (this) {
            this.c.s.a(num);
            z = !js.a(this.b.t, num);
            if (z) {
                this.b.t = num;
            }
        }
        return z;
    }

    public final boolean b(Integer num) {
        boolean z;
        synchronized (this) {
            this.c.t.a(num);
            z = !js.a(this.b.u, num);
            if (z) {
                this.b.u = num;
            }
        }
        return z;
    }

    public final boolean a(int i, String str) {
        boolean z;
        synchronized (this) {
            z = false;
            switch (i) {
                case 1:
                    this.c.u.a(str);
                    z = !js.a(this.b.v, str);
                    if (z) {
                        this.b.v = str;
                        break;
                    }
                    break;
                case 2:
                    this.c.v.a(str);
                    z = !js.a(this.b.w, str);
                    if (z) {
                        this.b.w = str;
                        break;
                    }
                    break;
                case 3:
                    this.c.w.a(str);
                    z = !js.a(this.b.x, str);
                    if (z) {
                        this.b.x = str;
                        break;
                    }
                    break;
                case 4:
                    this.c.x.a(str);
                    z = !js.a(this.b.y, str);
                    if (z) {
                        this.b.y = str;
                        break;
                    }
                    break;
                case 5:
                    this.c.y.a(str);
                    z = !js.a(this.b.z, str);
                    if (z) {
                        this.b.z = str;
                        break;
                    }
                    break;
            }
        }
        return z;
    }

    public final Set e() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.b.A);
        }
        return hashSet;
    }

    public final boolean c(String str) {
        synchronized (this) {
            for (int size = this.b.g.size() - 1; size >= 0; size--) {
                fi fiVar = (fi) this.b.g.get(size);
                if (fiVar.f.equals(str)) {
                    fi.a b2 = fiVar.b();
                    b2.e = Long.valueOf(System.currentTimeMillis());
                    this.b.g.set(size, b2.b());
                    g();
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean f() {
        return ((Boolean) js.b(this.b.B, fl.r)).booleanValue();
    }

    public final boolean a(boolean z) {
        boolean z2;
        synchronized (this) {
            this.c.C.a(z);
            z2 = z != ((Boolean) js.b(this.b.B, fl.r)).booleanValue();
            this.b.B = Boolean.valueOf(z);
        }
        return z2;
    }
}
