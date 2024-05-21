package com.adcolony.sdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.adcolony.sdk.aa;
import com.applovin.sdk.AppLovinEventTypes;
import com.tapjoy.TapjoyConstants;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

class n {
    static final String g = "Production";
    String a = "";
    boolean b;
    boolean c;
    JSONObject d = y.a();
    int e = 2;
    String f = "";
    private String h = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
    private String i = "android_native";
    private JSONArray j = y.b();

    /* access modifiers changed from: package-private */
    public String C() {
        return "3.3.5";
    }

    /* access modifiers changed from: package-private */
    public String v() {
        return "";
    }

    n() {
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        this.d = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public JSONObject a() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        Activity c2 = a.c();
        if (c2 == null) {
            return "";
        }
        return Settings.Secure.getString(c2.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return System.getProperty("os.arch").toLowerCase();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.b = false;
        a.a("Device.get_info", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        if (n.this.r() < 14) {
                            new a(afVar, n.this, false).execute(new Void[0]);
                        } else {
                            new a(afVar, n.this, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        }
                    }
                });
            }
        });
        a.a("Device.application_exists", (ah) new ah() {
            public void a(af afVar) {
                JSONObject a2 = y.a();
                y.a(a2, "result", aw.a(y.b(afVar.c(), "name")));
                y.a(a2, "success", true);
                afVar.a(a2).b();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return s() ? "tablet" : "phone";
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public String h() {
        String str;
        Activity c2 = a.c();
        if (c2 == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) c2.getSystemService("phone");
        if (telephonyManager == null) {
            str = "";
        } else {
            str = telephonyManager.getNetworkOperatorName();
        }
        return str.length() == 0 ? EnvironmentCompat.MEDIA_UNKNOWN : str;
    }

    /* access modifiers changed from: package-private */
    public int i() {
        ActivityManager activityManager;
        Activity c2 = a.c();
        if (c2 == null || (activityManager = (ActivityManager) c2.getApplicationContext().getSystemService("activity")) == null) {
            return 0;
        }
        return activityManager.getMemoryClass();
    }

    /* access modifiers changed from: package-private */
    public String j() {
        String str;
        Activity c2 = a.c();
        if (c2 == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) c2.getSystemService("phone");
        if (telephonyManager == null) {
            str = null;
        } else {
            str = telephonyManager.getSimCountryIso();
        }
        return str == null ? "" : str;
    }

    /* access modifiers changed from: package-private */
    public String k() {
        return TimeZone.getDefault().getID();
    }

    /* access modifiers changed from: package-private */
    public int l() {
        return TimeZone.getDefault().getOffset(15) / 60000;
    }

    /* access modifiers changed from: package-private */
    public int m() {
        TimeZone timeZone = TimeZone.getDefault();
        if (!timeZone.inDaylightTime(new Date())) {
            return 0;
        }
        return timeZone.getDSTSavings() / 60000;
    }

    /* access modifiers changed from: package-private */
    public long n() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / ((long) 1048576);
    }

    /* access modifiers changed from: package-private */
    public float o() {
        Activity c2 = a.c();
        if (c2 == null) {
            return 0.0f;
        }
        return c2.getResources().getDisplayMetrics().density;
    }

    /* access modifiers changed from: package-private */
    public int p() {
        Activity c2 = a.c();
        if (c2 == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        c2.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* access modifiers changed from: package-private */
    public int q() {
        Activity c2 = a.c();
        if (c2 == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        c2.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /* access modifiers changed from: package-private */
    public int r() {
        return Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: package-private */
    public double a(Context context) {
        Intent registerReceiver;
        if (context == null || (registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) == null) {
            return 0.0d;
        }
        int intExtra = registerReceiver.getIntExtra(AppLovinEventTypes.USER_COMPLETED_LEVEL, -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra < 0 || intExtra2 < 0) {
            return 0.0d;
        }
        double d2 = (double) intExtra;
        double d3 = (double) intExtra2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return d2 / d3;
    }

    /* access modifiers changed from: package-private */
    public boolean s() {
        Activity c2 = a.c();
        if (c2 == null) {
            return false;
        }
        DisplayMetrics displayMetrics = c2.getResources().getDisplayMetrics();
        float f2 = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
        float f3 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
        if (Math.sqrt((double) ((f2 * f2) + (f3 * f3))) >= 6.0d) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public String t() {
        return Locale.getDefault().getLanguage();
    }

    /* access modifiers changed from: package-private */
    public String u() {
        return Locale.getDefault().getCountry();
    }

    /* access modifiers changed from: package-private */
    public String w() {
        return Build.MANUFACTURER;
    }

    /* access modifiers changed from: package-private */
    public String x() {
        return Build.MODEL;
    }

    /* access modifiers changed from: package-private */
    public String y() {
        return Build.VERSION.RELEASE;
    }

    /* access modifiers changed from: package-private */
    public JSONArray z() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public int A() {
        Activity c2 = a.c();
        if (c2 == null) {
            return 2;
        }
        switch (c2.getResources().getConfiguration().orientation) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return 2;
        }
    }

    /* access modifiers changed from: package-private */
    public int B() {
        Activity c2 = a.c();
        if (c2 == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        c2.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    /* access modifiers changed from: package-private */
    public boolean D() {
        if (!a.d()) {
            return false;
        }
        int A = A();
        switch (A) {
            case 0:
                if (this.e == 1) {
                    new aa.a().a("Sending device info update").a(aa.d);
                    this.e = A;
                    if (r() < 14) {
                        new a((af) null, this, true).execute(new Void[0]);
                    } else {
                        new a((af) null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                    return true;
                }
                break;
            case 1:
                if (this.e == 0) {
                    new aa.a().a("Sending device info update").a(aa.d);
                    this.e = A;
                    if (r() < 14) {
                        new a((af) null, this, true).execute(new Void[0]);
                    } else {
                        new a((af) null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                    return true;
                }
                break;
        }
        return false;
    }

    class a extends AsyncTask<Void, Void, JSONObject> {
        af a;
        n b;
        boolean c;

        a(af afVar, n nVar, boolean z) {
            this.a = afVar;
            this.b = nVar;
            this.c = z;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public JSONObject doInBackground(Void... voidArr) {
            if (Build.VERSION.SDK_INT < 14) {
                return null;
            }
            return n.this.a(this.b);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(JSONObject jSONObject) {
            if (this.c) {
                new af("Device.update_info", 1, jSONObject).b();
            } else {
                this.a.a(jSONObject).b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(n nVar) {
        JSONObject a2 = y.a();
        l a3 = a.a();
        y.a(a2, TapjoyConstants.TJC_CARRIER_NAME, nVar.h());
        y.a(a2, "data_path", a.a().o().e());
        y.b(a2, "device_api", nVar.r());
        y.b(a2, "display_width", nVar.p());
        y.b(a2, "display_height", nVar.q());
        y.b(a2, "screen_width", nVar.p());
        y.b(a2, "screen_height", nVar.q());
        y.b(a2, "display_dpi", nVar.B());
        y.a(a2, TapjoyConstants.TJC_DEVICE_TYPE_NAME, nVar.f());
        y.a(a2, "locale_language_code", nVar.t());
        y.a(a2, "ln", nVar.t());
        y.a(a2, "locale_country_code", nVar.u());
        y.a(a2, "locale", nVar.u());
        y.a(a2, TapjoyConstants.TJC_DEVICE_MAC_ADDRESS, nVar.v());
        y.a(a2, "manufacturer", nVar.w());
        y.a(a2, "device_brand", nVar.w());
        y.a(a2, "media_path", a.a().o().d());
        y.a(a2, "temp_storage_path", a.a().o().f());
        y.b(a2, "memory_class", nVar.i());
        y.b(a2, "network_speed", 20);
        y.a(a2, "memory_used_mb", nVar.n());
        y.a(a2, "model", nVar.x());
        y.a(a2, "device_model", nVar.x());
        y.a(a2, TapjoyConstants.TJC_SDK_TYPE, "android_native");
        y.a(a2, "sdk_version", nVar.C());
        y.a(a2, "network_type", a3.d.c());
        y.a(a2, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, nVar.y());
        y.a(a2, "os_name", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        y.a(a2, TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        y.a(a2, "arch", nVar.d());
        y.a(a2, "user_id", y.b(a3.d().d, "user_id"));
        y.a(a2, TapjoyConstants.TJC_APP_ID, a3.d().a);
        y.a(a2, "app_bundle_name", aw.d());
        y.a(a2, "app_bundle_version", aw.b());
        y.a(a2, "battery_level", nVar.a((Context) a.c()));
        y.a(a2, "cell_service_country_code", nVar.j());
        y.a(a2, "timezone_ietf", nVar.k());
        y.b(a2, "timezone_gmt_m", nVar.l());
        y.b(a2, "timezone_dst_m", nVar.m());
        y.a(a2, "launch_metadata", nVar.a());
        y.a(a2, "controller_version", a3.b());
        this.e = nVar.A();
        y.b(a2, "current_orientation", this.e);
        JSONArray b2 = y.b();
        if (aw.a("com.android.vending")) {
            b2.put("google");
        }
        if (aw.a("com.amazon.venezia")) {
            b2.put("amazon");
        }
        y.a(a2, "available_stores", b2);
        this.j = aw.b((Context) a.c());
        y.a(a2, "permissions", this.j);
        int i2 = 40;
        while (!nVar.b && i2 > 0) {
            try {
                Thread.sleep(50);
                i2--;
            } catch (Exception unused) {
            }
        }
        y.a(a2, "advertiser_id", nVar.c());
        y.a(a2, "limit_tracking", nVar.g());
        if (nVar.c() == null || nVar.c().equals("")) {
            y.a(a2, "android_id_sha1", aw.c(nVar.b()));
        }
        return a2;
    }
}
