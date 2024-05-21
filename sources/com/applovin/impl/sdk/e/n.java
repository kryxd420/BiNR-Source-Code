package com.applovin.impl.sdk.e;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.ad.h;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.a;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.m;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.tapdaq.sdk.TapdaqPlacement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public abstract class n {
    public static double a(long j) {
        double d = (double) j;
        Double.isNaN(d);
        return d / 1000.0d;
    }

    public static float a(float f) {
        return f * 1000.0f;
    }

    public static int a(JSONObject jSONObject) {
        int a = g.a(jSONObject, "video_completion_percent", -1, (j) null);
        if (a < 0 || a > 100) {
            return 95;
        }
        return a;
    }

    public static long a(j jVar) {
        long longValue = ((Long) jVar.a(b.eX)).longValue();
        long longValue2 = ((Long) jVar.a(b.eY)).longValue();
        long currentTimeMillis = System.currentTimeMillis();
        return (longValue <= 0 || longValue2 <= 0) ? currentTimeMillis : currentTimeMillis + (longValue - longValue2);
    }

    public static Activity a(View view, j jVar) {
        if (view == null) {
            return null;
        }
        int i = 0;
        while (i < 1000) {
            i++;
            try {
                Context context = view.getContext();
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                ViewParent parent = view.getParent();
                if (!(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
            } catch (Throwable th) {
                jVar.v().b("Utils", "Encountered error while retrieving activity from view", th);
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.graphics.Bitmap, java.io.FileInputStream] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x005f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r8, int r9, int r10) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            r1.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            r2 = 1
            r1.inJustDecodeBounds = r2     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            android.content.res.Resources r3 = r8.getResources()     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            android.graphics.BitmapFactory.decodeResource(r3, r9)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            int r3 = r1.outHeight     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            if (r3 > r10) goto L_0x0018
            int r3 = r1.outWidth     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            if (r3 <= r10) goto L_0x0041
        L_0x0018:
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = (double) r10     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            int r10 = r1.outHeight     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            int r1 = r1.outWidth     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            int r10 = java.lang.Math.max(r10, r1)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            double r6 = (double) r10
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r6)
            double r4 = r4 / r6
            double r4 = java.lang.Math.log(r4)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r6 = java.lang.Math.log(r6)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            double r4 = r4 / r6
            double r4 = java.lang.Math.ceil(r4)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            int r10 = (int) r4     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            double r4 = (double) r10     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            double r1 = java.lang.Math.pow(r2, r4)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            int r2 = (int) r1     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
        L_0x0041:
            android.graphics.BitmapFactory$Options r10 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            r10.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            r10.inSampleSize = r2     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeResource(r8, r9)     // Catch:{ Exception -> 0x005f, all -> 0x0057 }
            r0.close()     // Catch:{ Exception -> 0x0056 }
            r0.close()     // Catch:{ Exception -> 0x0056 }
        L_0x0056:
            return r8
        L_0x0057:
            r8 = move-exception
            r0.close()     // Catch:{ Exception -> 0x005e }
            r0.close()     // Catch:{ Exception -> 0x005e }
        L_0x005e:
            throw r8
        L_0x005f:
            r0.close()     // Catch:{ Exception -> 0x0065 }
            r0.close()     // Catch:{ Exception -> 0x0065 }
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.n.a(android.content.Context, int, int):android.graphics.Bitmap");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0071 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.io.File r8, int r9) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x006f, all -> 0x0066 }
            r1.<init>()     // Catch:{ Exception -> 0x006f, all -> 0x0066 }
            r2 = 1
            r1.inJustDecodeBounds = r2     // Catch:{ Exception -> 0x006f, all -> 0x0066 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006f, all -> 0x0066 }
            r3.<init>(r8)     // Catch:{ Exception -> 0x006f, all -> 0x0066 }
            android.graphics.BitmapFactory.decodeStream(r3, r0, r1)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            r3.close()     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            int r4 = r1.outHeight     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            if (r4 > r9) goto L_0x001c
            int r4 = r1.outWidth     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            if (r4 <= r9) goto L_0x0045
        L_0x001c:
            r4 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r6 = (double) r9     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            int r9 = r1.outHeight     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            int r1 = r1.outWidth     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            int r9 = java.lang.Math.max(r9, r1)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            double r1 = (double) r9
            java.lang.Double.isNaN(r6)
            java.lang.Double.isNaN(r1)
            double r6 = r6 / r1
            double r1 = java.lang.Math.log(r6)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r6 = java.lang.Math.log(r6)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            double r1 = r1 / r6
            double r1 = java.lang.Math.ceil(r1)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            int r9 = (int) r1     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            double r1 = (double) r9     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            double r1 = java.lang.Math.pow(r4, r1)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            int r2 = (int) r1     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
        L_0x0045:
            android.graphics.BitmapFactory$Options r9 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            r9.<init>()     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            r9.inSampleSize = r2     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            r1.<init>(r8)     // Catch:{ Exception -> 0x0064, all -> 0x0062 }
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r1, r0, r9)     // Catch:{ Exception -> 0x0071, all -> 0x005f }
            r1.close()     // Catch:{ Exception -> 0x0071, all -> 0x005f }
            r3.close()     // Catch:{ Exception -> 0x005e }
            r1.close()     // Catch:{ Exception -> 0x005e }
        L_0x005e:
            return r8
        L_0x005f:
            r8 = move-exception
            r0 = r1
            goto L_0x0068
        L_0x0062:
            r8 = move-exception
            goto L_0x0068
        L_0x0064:
            r1 = r0
            goto L_0x0071
        L_0x0066:
            r8 = move-exception
            r3 = r0
        L_0x0068:
            r3.close()     // Catch:{ Exception -> 0x006e }
            r0.close()     // Catch:{ Exception -> 0x006e }
        L_0x006e:
            throw r8
        L_0x006f:
            r1 = r0
            r3 = r1
        L_0x0071:
            r3.close()     // Catch:{ Exception -> 0x0077 }
            r1.close()     // Catch:{ Exception -> 0x0077 }
        L_0x0077:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.n.a(java.io.File, int):android.graphics.Bitmap");
    }

    public static View a(Context context, View view) {
        View f = f(context);
        return f != null ? f : a(view);
    }

    public static View a(View view) {
        View rootView;
        if (view == null || (rootView = view.getRootView()) == null) {
            return null;
        }
        View findViewById = rootView.findViewById(16908290);
        return findViewById != null ? findViewById : rootView;
    }

    public static d a(JSONObject jSONObject, j jVar) {
        return d.a(AppLovinAdSize.fromString(g.a(jSONObject, "ad_size", (String) null, jVar)), AppLovinAdType.fromString(g.a(jSONObject, "ad_type", (String) null, jVar)), g.a(jSONObject, "zone_id", (String) null, jVar), jVar);
    }

    public static j a(AppLovinSdk appLovinSdk) {
        try {
            Field declaredField = appLovinSdk.getClass().getDeclaredField("mSdkImpl");
            declaredField.setAccessible(true);
            return (j) declaredField.get(appLovinSdk);
        } catch (Throwable unused) {
            throw new IllegalStateException("Internal error - unable to retrieve SDK implementation...");
        }
    }

    public static String a(Context context) {
        Bundle d = d(context);
        if (d == null) {
            return null;
        }
        String string = d.getString("applovin.sdk.key");
        return string != null ? string : "";
    }

    public static String a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    public static String a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(next.getKey());
            sb.append('=');
            sb.append(next.getValue());
        }
        return sb.toString();
    }

    public static Field a(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Class superclass = cls.getSuperclass();
            if (superclass == null) {
                return null;
            }
            return a(superclass, str);
        }
    }

    public static List<a> a(String str, JSONObject jSONObject, String str2, String str3, j jVar) {
        return a(str, jSONObject, str2, (String) null, str3, jVar);
    }

    public static List<a> a(String str, JSONObject jSONObject, String str2, String str3, String str4, j jVar) {
        JSONObject a = g.a(jSONObject, str, new JSONObject(), jVar);
        ArrayList arrayList = new ArrayList(a.length() + 1);
        if (k.b(str4)) {
            arrayList.add(new a(str4, (String) null));
        }
        if (a.length() > 0) {
            if (str3 == null) {
                str3 = "";
            }
            Iterator<String> keys = a.keys();
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (!TextUtils.isEmpty(next)) {
                        String optString = a.optString(next);
                        if (!TextUtils.isEmpty(optString)) {
                            optString = optString.replace("{CLCODE}", str2).replace("{EVENT_ID}", str3);
                        }
                        arrayList.add(new a(next.replace("{CLCODE}", str2).replace("{EVENT_ID}", str3), optString));
                    }
                } catch (Throwable th) {
                    jVar.v().b("Utils", "Failed to create and add postback url.", th);
                }
            }
        }
        return arrayList;
    }

    public static void a(AppLovinAdLoadListener appLovinAdLoadListener, d dVar, int i, j jVar) {
        if (appLovinAdLoadListener != null) {
            try {
                if (appLovinAdLoadListener instanceof m) {
                    ((m) appLovinAdLoadListener).a(dVar, i);
                } else {
                    appLovinAdLoadListener.failedToReceiveAd(i);
                }
            } catch (Throwable th) {
                jVar.v().b("Utils", "Unable process a failure to receive an ad", th);
            }
        }
    }

    public static void a(String str, Boolean bool, Map<String, String> map) {
        if (bool.booleanValue()) {
            map.put(str, Boolean.toString(true));
        }
    }

    public static void a(String str, String str2, Map<String, String> map) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    public static void a(String str, JSONObject jSONObject, j jVar) {
        if (jSONObject.has("no_fill_reason")) {
            Object a = g.a(jSONObject, "no_fill_reason", new Object(), jVar);
            StringBuilder sb = new StringBuilder();
            sb.append("\n**************************************************\n");
            sb.append("NO FILL received:\n");
            sb.append("..ID: " + str + "\n");
            sb.append("..SDK KEY: " + jVar.t() + "\n");
            sb.append("..Reason: " + a + "\n");
            sb.append("**************************************************\n");
            jVar.v().e("AppLovinSdk", sb.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = d(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a() {
        /*
            android.content.Context r0 = com.applovin.impl.sdk.j.y()
            r1 = 0
            if (r0 == 0) goto L_0x0016
            android.os.Bundle r0 = d((android.content.Context) r0)
            if (r0 == 0) goto L_0x0016
            java.lang.String r2 = "applovin.sdk.verbose_logging"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x0016
            r1 = 1
        L_0x0016:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.n.a():boolean");
    }

    public static boolean a(long j, long j2) {
        return (j & j2) != 0;
    }

    public static boolean a(Context context, Uri uri, j jVar) {
        boolean z;
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!(context instanceof Activity)) {
                intent.setFlags(268435456);
            }
            jVar.R().b();
            context.startActivity(intent);
            z = true;
        } catch (Throwable th) {
            p v = jVar.v();
            v.b("Utils", "Unable to open \"" + uri + "\".", th);
            z = false;
        }
        if (!z) {
            jVar.R().c();
        }
        return z;
    }

    public static boolean a(View view, Activity activity) {
        View rootView;
        if (!(activity == null || view == null)) {
            Window window = activity.getWindow();
            if (window != null) {
                rootView = window.getDecorView();
            } else {
                View findViewById = activity.findViewById(16908290);
                if (findViewById != null) {
                    rootView = findViewById.getRootView();
                }
            }
            return a(view, rootView);
        }
        return false;
    }

    public static boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (a(view, viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(g gVar, Context context, j jVar) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        boolean z = gVar != null && (gVar.b() || gVar.e() == null || jVar.O().a(gVar.e().getLastPathSegment(), context));
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return z;
    }

    public static boolean a(AppLovinAd appLovinAd, j jVar) {
        p v;
        String str;
        String str2;
        if (appLovinAd == null) {
            v = jVar.v();
            str = "AppLovinSdk";
            str2 = "Failing ad display - ad is null.";
        } else if (f.a(jVar.x(), jVar) || ((Boolean) jVar.a(b.dK)).booleanValue()) {
            return true;
        } else {
            v = jVar.v();
            str = "AppLovinSdk";
            str2 = "Failing ad display due to no internet connection.";
        }
        v.e(str, str2);
        return false;
    }

    public static boolean a(String str, List<String> list) {
        for (String startsWith : list) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static long b(float f) {
        return c(a(f));
    }

    public static AppLovinAd b(AppLovinAd appLovinAd, j jVar) {
        if (!(appLovinAd instanceof h)) {
            return appLovinAd;
        }
        h hVar = (h) appLovinAd;
        AppLovinAd dequeueAd = jVar.o().dequeueAd(hVar.getAdZone());
        p v = jVar.v();
        v.a("Utils", "Dequeued ad for dummy ad: " + dequeueAd);
        if (dequeueAd != null) {
            hVar.a(dequeueAd);
            ((AppLovinAdBase) dequeueAd).setDummyAd(hVar);
            return dequeueAd;
        } else if (((Boolean) jVar.a(b.cf)).booleanValue()) {
            return hVar.a();
        } else {
            return null;
        }
    }

    public static String b(String str) {
        return str.replace("{PLACEMENT}", "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0009, code lost:
        r2 = d(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(android.content.Context r2) {
        /*
            if (r2 != 0) goto L_0x0006
            android.content.Context r2 = com.applovin.impl.sdk.j.y()
        L_0x0006:
            r0 = 0
            if (r2 == 0) goto L_0x0018
            android.os.Bundle r2 = d((android.content.Context) r2)
            if (r2 == 0) goto L_0x0018
            java.lang.String r1 = "applovin.sdk.test_ads"
            boolean r2 = r2.getBoolean(r1, r0)
            if (r2 == 0) goto L_0x0018
            r0 = 1
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.n.b(android.content.Context):boolean");
    }

    private static long c(float f) {
        return (long) Math.round(f);
    }

    public static MaxAdFormat c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase("banner")) {
            return MaxAdFormat.BANNER;
        }
        if (str.equalsIgnoreCase("mrec")) {
            return MaxAdFormat.MREC;
        }
        if (str.equalsIgnoreCase(TapdaqPlacement.TDPTagLeaderBoard) || str.equalsIgnoreCase("leader")) {
            return MaxAdFormat.LEADER;
        }
        if (str.equalsIgnoreCase("interstitial") || str.equalsIgnoreCase("inter")) {
            return MaxAdFormat.INTERSTITIAL;
        }
        if (str.equalsIgnoreCase("rewarded") || str.equalsIgnoreCase("reward")) {
            return MaxAdFormat.REWARDED;
        }
        throw new IllegalArgumentException("Unknown format: " + str);
    }

    public static void c(AppLovinAd appLovinAd, j jVar) {
        if (appLovinAd instanceof AppLovinAdBase) {
            String t = jVar.t();
            String t2 = ((AppLovinAdBase) appLovinAd).getSdk().t();
            if (!t.equals(t2)) {
                p v = jVar.v();
                v.e("AppLovinAd", "Ad was loaded from sdk with key: " + t2 + ", but is being rendered from sdk with key: " + t);
                jVar.E().a(com.applovin.impl.sdk.c.g.p);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0009, code lost:
        r2 = d(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.content.Context r2) {
        /*
            if (r2 != 0) goto L_0x0006
            android.content.Context r2 = com.applovin.impl.sdk.j.y()
        L_0x0006:
            r0 = 0
            if (r2 == 0) goto L_0x0018
            android.os.Bundle r2 = d((android.content.Context) r2)
            if (r2 == 0) goto L_0x0018
            java.lang.String r1 = "applovin.sdk.verbose_logging"
            boolean r2 = r2.getBoolean(r1, r0)
            if (r2 == 0) goto L_0x0018
            r0 = 1
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.n.c(android.content.Context):boolean");
    }

    public static Bundle d(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable th) {
            Log.e("Utils", "Unable to retrieve application metadata", th);
            return null;
        }
    }

    public static String d(String str) {
        Uri parse = Uri.parse(str);
        return new Uri.Builder().scheme(parse.getScheme()).authority(parse.getAuthority()).path(parse.getPath()).build().toString();
    }

    public static int e(Context context) {
        Resources resources;
        Configuration configuration;
        if (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) {
            return 0;
        }
        return configuration.orientation;
    }

    public static View f(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        return ((Activity) context).getWindow().getDecorView().findViewById(16908290);
    }
}
