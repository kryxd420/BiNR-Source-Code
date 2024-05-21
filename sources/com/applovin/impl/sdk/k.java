package com.applovin.impl.sdk;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.impl.sdk.ad.f;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.c.h;
import com.applovin.impl.sdk.d.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.i;
import com.applovin.impl.sdk.e.j;
import com.applovin.impl.sdk.e.n;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

public class k {
    private static final Queue<String> a = new LinkedList();
    private static String f;
    private static String g;
    private static int h;
    private final j b;
    /* access modifiers changed from: private */
    public final p c;
    /* access modifiers changed from: private */
    public final Context d;
    private final Map<Class, Object> e;
    /* access modifiers changed from: private */
    public final AtomicReference<a> i = new AtomicReference<>();

    public static class a {
        public boolean a;
        public String b = "";
    }

    public static class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public long f;
    }

    public static class c {
        public int a = -1;
        public int b = -1;
    }

    public static class d {
        public Boolean A;
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public int h;
        public String i;
        public String j;
        public Locale k;
        public String l;
        public float m;
        public int n;
        public double o;
        public int p;
        public boolean q;
        public c r;
        public int s;
        public String t;
        public boolean u;
        public boolean v;
        public boolean w;
        public String x;
        public long y;
        public Boolean z;
    }

    static {
        a.add("act");
        a.add("acm");
        a.add("adr");
        a.add("build");
        a.add(AvidVideoPlaybackListenerImpl.VOLUME);
        a.add("ua");
    }

    protected k(j jVar) {
        if (jVar != null) {
            this.b = jVar;
            this.c = jVar.v();
            this.d = jVar.x();
            this.e = Collections.synchronizedMap(new HashMap());
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private d a(d dVar) {
        if (dVar == null) {
            dVar = new d();
        }
        dVar.z = g.a(this.d);
        dVar.A = g.b(this.d);
        dVar.r = ((Boolean) this.b.a(com.applovin.impl.sdk.b.b.en)).booleanValue() ? j() : null;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ev)).booleanValue()) {
            dVar.q = n();
        }
        try {
            AudioManager audioManager = (AudioManager) this.d.getSystemService("audio");
            if (audioManager != null) {
                dVar.s = (int) (((float) audioManager.getStreamVolume(3)) * ((Float) this.b.a(com.applovin.impl.sdk.b.b.ew)).floatValue());
            }
        } catch (Throwable th) {
            this.c.b("DataCollector", "Unable to collect volume", th);
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ex)).booleanValue()) {
            if (f == null) {
                String r = r();
                if (!com.applovin.impl.sdk.e.k.b(r)) {
                    r = "";
                }
                f = r;
            }
            if (com.applovin.impl.sdk.e.k.b(f)) {
                dVar.t = f;
            }
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eq)).booleanValue()) {
            try {
                dVar.y = Environment.getDataDirectory().getFreeSpace();
            } catch (Throwable th2) {
                dVar.y = -1;
                this.c.b("DataCollector", "Unable to collect free space.", th2);
            }
        }
        String str = (String) this.b.w().a(com.applovin.impl.sdk.b.b.ez);
        if (g == null || !str.equalsIgnoreCase(g)) {
            try {
                g = str;
                PackageInfo packageInfo = this.d.getPackageManager().getPackageInfo(str, 0);
                dVar.p = packageInfo.versionCode;
                h = packageInfo.versionCode;
            } catch (Throwable unused) {
                h = 0;
            }
        } else {
            dVar.p = h;
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eo)).booleanValue()) {
            dVar.w = m();
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ep)).booleanValue()) {
            String k = k();
            if (!TextUtils.isEmpty(k)) {
                dVar.x = k;
            }
        }
        dVar.l = g();
        return dVar;
    }

    private boolean a(String str) {
        return a(str, this.d);
    }

    public static boolean a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        } else if (context != null) {
            return i.a(str, context.getPackageName(), context.getPackageManager()) == 0;
        } else {
            throw new IllegalArgumentException("No context specified");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.applovin.impl.sdk.b.b<java.lang.String>, com.applovin.impl.sdk.b.b] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r2, com.applovin.impl.sdk.b.b<java.lang.String> r3) {
        /*
            r1 = this;
            com.applovin.impl.sdk.j r0 = r1.b
            java.lang.Object r3 = r0.a(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.util.List r3 = com.applovin.impl.sdk.e.d.a((java.lang.String) r3)
            java.util.Iterator r3 = r3.iterator()
        L_0x0010:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0024
            java.lang.Object r0 = r3.next()
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x0010
            r2 = 1
            return r2
        L_0x0024:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.k.a(java.lang.String, com.applovin.impl.sdk.b.b):boolean");
    }

    private String b(int i2) {
        String str;
        JSONObject jSONObject = new JSONObject(f());
        PriorityQueue priorityQueue = new PriorityQueue(a);
        while (true) {
            String encodeToString = Base64.encodeToString(jSONObject.toString().getBytes(Charset.defaultCharset()), 2);
            if (encodeToString.length() <= i2) {
                return encodeToString;
            }
            do {
                str = (String) priorityQueue.poll();
                if (jSONObject.has(str) || priorityQueue.isEmpty()) {
                }
                str = (String) priorityQueue.poll();
                break;
            } while (priorityQueue.isEmpty());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.remove(str);
            } else {
                p pVar = this.c;
                pVar.d("DataCollector", "Unable to generate base64 request parameters with max length: " + i2);
                return "";
            }
        }
    }

    private String b(String str) {
        int length = str.length();
        int[] iArr = {11, 12, 10, 3, 2, 1, 15, 10, 15, 14};
        int length2 = iArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 < length; i2++) {
            cArr[i2] = str.charAt(i2);
            for (int i3 = length2 - 1; i3 >= 0; i3--) {
                cArr[i2] = (char) (cArr[i2] ^ iArr[i3]);
            }
        }
        return new String(cArr);
    }

    private String c(int i2) {
        if (i2 == 1) {
            return "receiver";
        }
        if (i2 == 2) {
            return "speaker";
        }
        if (i2 == 4 || i2 == 3) {
            return "headphones";
        }
        if (i2 == 8) {
            return "bluetootha2dpoutput";
        }
        if (i2 == 13 || i2 == 19 || i2 == 5 || i2 == 6 || i2 == 12 || i2 == 11) {
            return "lineout";
        }
        if (i2 == 9 || i2 == 10) {
            return "hdmioutput";
        }
        return null;
    }

    private Map<String, String> f() {
        return a((Map<String, String>) null, false, true);
    }

    private String g() {
        String str;
        try {
            int e2 = n.e(this.d);
            if (e2 == 1) {
                str = "portrait";
            } else if (e2 != 2) {
                return "none";
            } else {
                str = TJAdUnitConstants.String.LANDSCAPE;
            }
            return str;
        } catch (Throwable th) {
            this.b.v().b("DataCollector", "Encountered error while attempting to collect application orientation", th);
            return "none";
        }
    }

    private a h() {
        String str;
        String str2;
        p pVar;
        try {
            ContentResolver contentResolver = this.d.getContentResolver();
            String string = Settings.Secure.getString(contentResolver, TapjoyConstants.TJC_ADVERTISING_ID);
            a aVar = new a();
            if (string == null) {
                string = "";
            }
            aVar.b = string;
            aVar.a = Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 0;
            return aVar;
        } catch (Settings.SettingNotFoundException e2) {
            th = e2;
            pVar = this.c;
            str2 = "DataCollector";
            str = "Unable to determine if FireOS limited ad tracking is turned on";
            pVar.b(str2, str, th);
            return null;
        } catch (Throwable th) {
            th = th;
            pVar = this.c;
            str2 = "DataCollector";
            str = "Unable to collect FireOS IDFA";
            pVar.b(str2, str, th);
            return null;
        }
    }

    private a i() {
        Object invoke;
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (!(cls == null || (invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{this.d})) == null)) {
                Class<?> cls2 = invoke.getClass();
                Object invoke2 = cls2.getMethod("isLimitAdTrackingEnabled", (Class[]) null).invoke(invoke, (Object[]) null);
                String str = (String) cls2.getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
                if (str == null) {
                    str = "";
                }
                a aVar = new a();
                aVar.a = ((Boolean) invoke2).booleanValue();
                aVar.b = str;
                return aVar;
            }
        } catch (ClassNotFoundException e2) {
            this.c.c("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e2);
        } catch (Throwable th) {
            this.c.b("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", th);
        }
        return new a();
    }

    private c j() {
        try {
            c cVar = new c();
            Intent registerReceiver = this.d.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int i2 = -1;
            int intExtra = registerReceiver != null ? registerReceiver.getIntExtra(AppLovinEventTypes.USER_COMPLETED_LEVEL, -1) : -1;
            int intExtra2 = registerReceiver != null ? registerReceiver.getIntExtra("scale", -1) : -1;
            if (intExtra <= 0 || intExtra2 <= 0) {
                cVar.b = -1;
            } else {
                cVar.b = (int) ((((float) intExtra) / ((float) intExtra2)) * 100.0f);
            }
            if (registerReceiver != null) {
                i2 = registerReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
            }
            cVar.a = i2;
            return cVar;
        } catch (Throwable th) {
            this.c.b("DataCollector", "Unable to collect battery info", th);
            return null;
        }
    }

    private String k() {
        try {
            AudioManager audioManager = (AudioManager) this.d.getSystemService("audio");
            StringBuilder sb = new StringBuilder();
            if (e.g()) {
                for (AudioDeviceInfo type : audioManager.getDevices(2)) {
                    String c2 = c(type.getType());
                    if (!TextUtils.isEmpty(c2)) {
                        sb.append(c2);
                        sb.append(",");
                    }
                }
            } else {
                if (audioManager.isWiredHeadsetOn()) {
                    sb.append("headphones");
                    sb.append(",");
                }
                if (audioManager.isBluetoothA2dpOn()) {
                    sb.append("bluetootha2dpoutput");
                }
            }
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            String sb2 = sb.toString();
            if (TextUtils.isEmpty(sb2)) {
                this.c.a("DataCollector", "No sound outputs detected");
            }
            return sb2;
        } catch (Throwable th) {
            this.c.b("DataCollector", "Unable to collect sound outputs", th);
            return null;
        }
    }

    private double l() {
        double offset = (double) TimeZone.getDefault().getOffset(new Date().getTime());
        Double.isNaN(offset);
        double round = (double) Math.round((offset * 10.0d) / 3600000.0d);
        Double.isNaN(round);
        return round / 10.0d;
    }

    private boolean m() {
        try {
            if (this.b.g()) {
                return this.d.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
            }
            PackageManager packageManager = this.d.getPackageManager();
            return e.f() ? packageManager.hasSystemFeature("android.software.leanback") : e.c() ? packageManager.hasSystemFeature("android.hardware.type.television") : !packageManager.hasSystemFeature("android.hardware.touchscreen");
        } catch (Throwable th) {
            this.c.b("DataCollector", "Failed to determine if device is TV.", th);
            return false;
        }
    }

    private boolean n() {
        try {
            return o() || p();
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean o() {
        String str = Build.TAGS;
        return str != null && str.contains(b("lz}$blpz"));
    }

    private boolean p() {
        String[] strArr = {"&zpz}ld&hyy&Z|yl{|zl{'hyb", "&zk`g&z|", "&zpz}ld&k`g&z|", "&zpz}ld&qk`g&z|", "&mh}h&efjhe&qk`g&z|", "&mh}h&efjhe&k`g&z|", "&zpz}ld&zm&qk`g&z|", "&zpz}ld&k`g&oh`ezhol&z|", "&mh}h&efjhe&z|"};
        for (String b2 : strArr) {
            if (new File(b(b2)).exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean q() {
        return a(Build.DEVICE, com.applovin.impl.sdk.b.b.es) || a(Build.HARDWARE, com.applovin.impl.sdk.b.b.er) || a(Build.MANUFACTURER, com.applovin.impl.sdk.b.b.et) || a(Build.MODEL, com.applovin.impl.sdk.b.b.eu);
    }

    private String r() {
        final AtomicReference atomicReference = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Handler(this.d.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    atomicReference.set(new WebView(k.this.d).getSettings().getUserAgentString());
                } catch (Throwable th) {
                    countDownLatch.countDown();
                    throw th;
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(((Long) this.b.a(com.applovin.impl.sdk.b.b.ey)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable unused) {
        }
        return (String) atomicReference.get();
    }

    private String s() {
        if (!e.b() || !e.a((Class<?>) AppLovinInterstitialActivity.class, this.d)) {
            return "custom_size,launch_app";
        }
        return "custom_size,launch_app" + ",video";
    }

    /* access modifiers changed from: package-private */
    public f a(int i2) {
        String b2 = b(i2);
        boolean z = this.i.get() != null;
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eW)).booleanValue()) {
            return new f(b2, z);
        }
        return new f(j.a(b2, this.b.t(), n.a(this.b)), z);
    }

    public d a() {
        d dVar;
        TelephonyManager telephonyManager;
        Object obj = this.e.get(d.class);
        if (obj != null) {
            dVar = (d) obj;
        } else {
            dVar = new d();
            dVar.k = Locale.getDefault();
            dVar.a = Build.MODEL;
            dVar.b = Build.VERSION.RELEASE;
            dVar.c = b();
            dVar.d = Build.MANUFACTURER;
            dVar.e = Build.BRAND;
            dVar.f = Build.HARDWARE;
            dVar.h = Build.VERSION.SDK_INT;
            dVar.g = Build.DEVICE;
            dVar.o = l();
            dVar.u = q();
            try {
                dVar.v = ((SensorManager) this.d.getSystemService("sensor")).getDefaultSensor(4) != null;
            } catch (Throwable th) {
                this.c.b("DataCollector", "Unable to retrieve gyroscope availability", th);
            }
            if (a("android.permission.READ_PHONE_STATE") && (telephonyManager = (TelephonyManager) this.d.getSystemService("phone")) != null) {
                dVar.i = telephonyManager.getSimCountryIso().toUpperCase(Locale.ENGLISH);
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                try {
                    dVar.j = URLEncoder.encode(networkOperatorName, "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    dVar.j = networkOperatorName;
                }
            }
            try {
                DisplayMetrics displayMetrics = this.d.getResources().getDisplayMetrics();
                dVar.m = displayMetrics.density;
                dVar.n = displayMetrics.densityDpi;
            } catch (Throwable unused2) {
            }
            this.e.put(d.class, dVar);
        }
        return a(dVar);
    }

    public Map<String, String> a(Map<String, String> map, boolean z, boolean z2) {
        a aVar;
        HashMap hashMap = new HashMap();
        d a2 = a();
        hashMap.put("brand", com.applovin.impl.sdk.e.k.e(a2.d));
        hashMap.put("brand_name", com.applovin.impl.sdk.e.k.e(a2.e));
        hashMap.put("hardware", com.applovin.impl.sdk.e.k.e(a2.f));
        hashMap.put("api_level", String.valueOf(a2.h));
        hashMap.put("carrier", com.applovin.impl.sdk.e.k.e(a2.j));
        hashMap.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, com.applovin.impl.sdk.e.k.e(a2.i));
        hashMap.put("locale", com.applovin.impl.sdk.e.k.e(a2.k.toString()));
        hashMap.put("model", com.applovin.impl.sdk.e.k.e(a2.a));
        hashMap.put("os", com.applovin.impl.sdk.e.k.e(a2.b));
        hashMap.put(TapjoyConstants.TJC_PLATFORM, com.applovin.impl.sdk.e.k.e(a2.c));
        hashMap.put("revision", com.applovin.impl.sdk.e.k.e(a2.g));
        hashMap.put("orientation_lock", a2.l);
        hashMap.put("tz_offset", String.valueOf(a2.o));
        hashMap.put("wvvc", String.valueOf(a2.p));
        hashMap.put("adns", String.valueOf(a2.m));
        hashMap.put("adnsd", String.valueOf(a2.n));
        hashMap.put("sim", com.applovin.impl.sdk.e.k.a(a2.u));
        hashMap.put("gy", com.applovin.impl.sdk.e.k.a(a2.v));
        hashMap.put("tv", com.applovin.impl.sdk.e.k.a(a2.w));
        hashMap.put("fs", String.valueOf(a2.y));
        hashMap.put("adr", com.applovin.impl.sdk.e.k.a(a2.q));
        hashMap.put(AvidVideoPlaybackListenerImpl.VOLUME, String.valueOf(a2.s));
        n.a("ua", com.applovin.impl.sdk.e.k.e(a2.t), (Map<String, String>) hashMap);
        n.a("so", com.applovin.impl.sdk.e.k.e(a2.x), (Map<String, String>) hashMap);
        c cVar = a2.r;
        if (cVar != null) {
            hashMap.put("act", String.valueOf(cVar.a));
            hashMap.put("acm", String.valueOf(cVar.b));
        }
        Boolean bool = a2.z;
        if (bool != null) {
            hashMap.put("huc", bool.toString());
        }
        Boolean bool2 = a2.A;
        if (bool2 != null) {
            hashMap.put("aru", bool2.toString());
        }
        Point a3 = e.a(this.d);
        hashMap.put("dx", Integer.toString(a3.x));
        hashMap.put("dy", Integer.toString(a3.y));
        hashMap.put("accept", s());
        hashMap.put("api_did", this.b.a(com.applovin.impl.sdk.b.b.T));
        hashMap.put("sdk_version", AppLovinSdk.VERSION);
        hashMap.put("build", Integer.toString(116));
        hashMap.put("format", "json");
        b c2 = c();
        hashMap.put(TapjoyConstants.TJC_APP_VERSION_NAME, com.applovin.impl.sdk.e.k.e(c2.b));
        hashMap.put("ia", Long.toString(c2.f));
        hashMap.put("tg", String.valueOf(c2.e));
        hashMap.put("installer_name", c2.d);
        n.a("mediation_provider", com.applovin.impl.sdk.e.k.e(this.b.n()), (Map<String, String>) hashMap);
        hashMap.put("network", com.applovin.impl.sdk.e.f.d(this.b));
        n.a("plugin_version", (String) this.b.a(com.applovin.impl.sdk.b.b.ei), (Map<String, String>) hashMap);
        hashMap.put("preloading", String.valueOf(z));
        n.a("test_ads", Boolean.valueOf(this.b.l().isTestAdsEnabled()), (Map<String, String>) hashMap);
        hashMap.put("first_install", String.valueOf(this.b.B()));
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
            hashMap.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.t());
        }
        hashMap.put("sc", this.b.a(com.applovin.impl.sdk.b.b.X));
        hashMap.put("sc2", this.b.a(com.applovin.impl.sdk.b.b.Y));
        hashMap.put("server_installed_at", com.applovin.impl.sdk.e.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Z)));
        n.a("persisted_data", com.applovin.impl.sdk.e.k.e((String) this.b.a(com.applovin.impl.sdk.b.d.s)), (Map<String, String>) hashMap);
        hashMap.put("v1", Boolean.toString(e.a("android.permission.WRITE_EXTERNAL_STORAGE", this.d)));
        hashMap.put("v2", Boolean.toString(e.a((Class<?>) AppLovinInterstitialActivity.class, this.d)));
        hashMap.put("v3", Boolean.toString(e.b(this.d)));
        hashMap.put("v4", Boolean.toString(e.c(this.d)));
        hashMap.put("v5", Boolean.toString(e.a((Class<?>) AppLovinWebViewActivity.class, this.d)));
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eA)).booleanValue()) {
            h E = this.b.E();
            hashMap.put("li", String.valueOf(E.b(g.b)));
            hashMap.put("si", String.valueOf(E.b(g.d)));
            hashMap.put("pf", String.valueOf(E.b(g.h)));
            hashMap.put("mpf", String.valueOf(E.b(g.r)));
            hashMap.put("gpf", String.valueOf(E.b(g.i)));
        }
        hashMap.put("vz", com.applovin.impl.sdk.e.k.a(this.d.getPackageName(), this.b));
        if (this.b.i()) {
            hashMap.put("pnr", Boolean.toString(this.b.j()));
        }
        if (z2) {
            aVar = this.i.get();
            if (aVar == null) {
                aVar = new a();
                hashMap.put("inc", Boolean.toString(true));
            }
            e();
        } else {
            aVar = this.b.H().d();
        }
        String str = aVar.b;
        if (com.applovin.impl.sdk.e.k.b(str)) {
            hashMap.put("idfa", str);
        }
        hashMap.put("dnt", Boolean.toString(aVar.a));
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ef)).booleanValue()) {
            n.a("cuid", this.b.k(), (Map<String, String>) hashMap);
        }
        if (map != null) {
            hashMap.putAll(map);
        }
        hashMap.put("rid", UUID.randomUUID().toString());
        return hashMap;
    }

    public String b() {
        return this.b.g() ? "fireos" : TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
    }

    public b c() {
        PackageInfo packageInfo;
        Object obj = this.e.get(b.class);
        if (obj != null) {
            return (b) obj;
        }
        ApplicationInfo applicationInfo = this.d.getApplicationInfo();
        long lastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.d.getPackageManager();
        String str = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.d.getPackageName(), 0);
            try {
                str = packageManager.getInstallerPackageName(applicationInfo.packageName);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            packageInfo = null;
        }
        b bVar = new b();
        bVar.c = applicationInfo.packageName;
        if (str == null) {
            str = "";
        }
        bVar.d = str;
        bVar.f = lastModified;
        bVar.a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        bVar.b = packageInfo != null ? packageInfo.versionName : "";
        bVar.e = (String) this.b.a(com.applovin.impl.sdk.b.d.d);
        this.e.put(b.class, bVar);
        return bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.applovin.impl.sdk.k.a d() {
        /*
            r3 = this;
            com.applovin.impl.sdk.j r0 = r3.b
            boolean r0 = r0.g()
            if (r0 == 0) goto L_0x0025
            com.applovin.impl.sdk.k$a r0 = r3.h()
            if (r0 != 0) goto L_0x0029
            com.applovin.impl.sdk.j r0 = r3.b
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.b.ek
            java.lang.Object r0 = r0.a(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x001f
            goto L_0x0025
        L_0x001f:
            com.applovin.impl.sdk.k$a r0 = new com.applovin.impl.sdk.k$a
            r0.<init>()
            goto L_0x0029
        L_0x0025:
            com.applovin.impl.sdk.k$a r0 = r3.i()
        L_0x0029:
            com.applovin.impl.sdk.j r1 = r3.b
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.ee
            java.lang.Object r1 = r1.a(r2)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0052
            boolean r1 = r0.a
            if (r1 == 0) goto L_0x0057
            com.applovin.impl.sdk.j r1 = r3.b
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.ed
            java.lang.Object r1 = r1.a(r2)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0057
            java.lang.String r1 = ""
            r0.b = r1
            goto L_0x0057
        L_0x0052:
            com.applovin.impl.sdk.k$a r0 = new com.applovin.impl.sdk.k$a
            r0.<init>()
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.k.d():com.applovin.impl.sdk.k$a");
    }

    public void e() {
        this.b.D().a((com.applovin.impl.sdk.d.a) new com.applovin.impl.sdk.d.i(this.b, new i.a() {
            public void a(a aVar) {
                k.this.i.set(aVar);
            }
        }), q.a.BACKGROUND);
    }
}
