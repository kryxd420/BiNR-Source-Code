package com.adcolony.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.adcolony.sdk.aa;
import com.adcolony.sdk.p;
import com.applovin.sdk.AppLovinEventTypes;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.integralads.avid.library.adcolony.BuildConfig;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.tapdaq.sdk.TapdaqError;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.warren.network.VungleApiClient;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

class l implements p.a {
    private static volatile String I = "";
    static final String a = "026ae9c9824b3e483fa6c71fa88f57ae27816141";
    static final String b = "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5";
    static String e = "https://adc3-launch.adcolony.com/v4/launch";
    private JSONObject A;
    private HashMap<String, AdColonyZone> B = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<Integer, ay> C = new HashMap<>();
    private String D;
    private String E;
    private String F;
    private String G;
    private String H = "";
    private boolean J;
    /* access modifiers changed from: private */
    public boolean K;
    private boolean L;
    /* access modifiers changed from: private */
    public boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    /* access modifiers changed from: private */
    public boolean S;
    /* access modifiers changed from: private */
    public boolean T;
    private int U;
    /* access modifiers changed from: private */
    public int V = 1;
    private final int W = TapdaqError.NO_PLACEMENT_TAG_AVAILABLE;
    private Application.ActivityLifecycleCallbacks X;
    private ExternalAvidAdSessionContext Y;
    n c;
    aj d;
    boolean f;
    private m g;
    /* access modifiers changed from: private */
    public ag h;
    /* access modifiers changed from: private */
    public q i;
    /* access modifiers changed from: private */
    public ao j;
    private d k;
    /* access modifiers changed from: private */
    public o l;
    private t m;
    private at n;
    /* access modifiers changed from: private */
    public ar o;
    private ADCCrashReportManager p;
    private ac q;
    private c r;
    private az s;
    private AdColonyInterstitial t;
    /* access modifiers changed from: private */
    public AdColonyRewardListener u;
    private HashMap<String, AdColonyCustomMessageListener> v = new HashMap<>();
    /* access modifiers changed from: private */
    public AdColonyAppOptions w;
    /* access modifiers changed from: private */
    public af x;
    private boolean y;
    private af z;

    l() {
    }

    /* access modifiers changed from: package-private */
    public Activity a() {
        return a.c();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.adcolony.sdk.AdColonyAppOptions r4, boolean r5) {
        /*
            r3 = this;
            r3.L = r5
            r3.w = r4
            com.adcolony.sdk.ADCCrashReportManager r0 = new com.adcolony.sdk.ADCCrashReportManager
            r0.<init>()
            r3.p = r0
            com.adcolony.sdk.ag r0 = new com.adcolony.sdk.ag
            r0.<init>()
            r3.h = r0
            com.adcolony.sdk.m r0 = new com.adcolony.sdk.m
            r0.<init>()
            r3.g = r0
            com.adcolony.sdk.q r0 = new com.adcolony.sdk.q
            r0.<init>()
            r3.i = r0
            com.adcolony.sdk.q r0 = r3.i
            r0.a()
            com.adcolony.sdk.ao r0 = new com.adcolony.sdk.ao
            r0.<init>()
            r3.j = r0
            com.adcolony.sdk.ao r0 = r3.j
            r0.a()
            com.adcolony.sdk.d r0 = new com.adcolony.sdk.d
            r0.<init>()
            r3.k = r0
            com.adcolony.sdk.d r0 = r3.k
            r0.a()
            com.adcolony.sdk.o r0 = new com.adcolony.sdk.o
            r0.<init>()
            r3.l = r0
            com.adcolony.sdk.t r0 = new com.adcolony.sdk.t
            r0.<init>()
            r3.m = r0
            com.adcolony.sdk.t r0 = r3.m
            r0.a()
            com.adcolony.sdk.ac r0 = new com.adcolony.sdk.ac
            r0.<init>()
            r3.q = r0
            com.adcolony.sdk.ac r0 = r3.q
            com.adcolony.sdk.ac.c()
            com.adcolony.sdk.ar r0 = new com.adcolony.sdk.ar
            r0.<init>()
            r3.o = r0
            com.adcolony.sdk.ar r0 = r3.o
            r0.a()
            com.adcolony.sdk.at r0 = new com.adcolony.sdk.at
            r0.<init>()
            r3.n = r0
            com.adcolony.sdk.at r0 = r3.n
            r0.a()
            com.adcolony.sdk.n r0 = new com.adcolony.sdk.n
            r0.<init>()
            r3.c = r0
            com.adcolony.sdk.n r0 = r3.c
            r0.e()
            com.adcolony.sdk.aj r0 = new com.adcolony.sdk.aj
            r0.<init>()
            r3.d = r0
            com.adcolony.sdk.aj r0 = r3.d
            java.lang.String r0 = r0.c()
            r3.D = r0
            android.app.Activity r0 = com.adcolony.sdk.a.c()
            com.adcolony.sdk.AdColony.a((android.app.Activity) r0, (com.adcolony.sdk.AdColonyAppOptions) r4)
            r4 = 0
            r0 = 1
            if (r5 != 0) goto L_0x0146
            java.io.File r5 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.adcolony.sdk.ar r2 = r3.o
            java.lang.String r2 = r2.g()
            r1.append(r2)
            java.lang.String r2 = "026ae9c9824b3e483fa6c71fa88f57ae27816141"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r5.<init>(r1)
            boolean r5 = r5.exists()
            r3.P = r5
            java.io.File r5 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.adcolony.sdk.ar r2 = r3.o
            java.lang.String r2 = r2.g()
            r1.append(r2)
            java.lang.String r2 = "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r5.<init>(r1)
            boolean r5 = r5.exists()
            r3.Q = r5
            boolean r5 = r3.P
            if (r5 == 0) goto L_0x0115
            boolean r5 = r3.Q
            if (r5 == 0) goto L_0x0115
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            com.adcolony.sdk.ar r1 = r3.o
            java.lang.String r1 = r1.g()
            r5.append(r1)
            java.lang.String r1 = "026ae9c9824b3e483fa6c71fa88f57ae27816141"
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            org.json.JSONObject r5 = com.adcolony.sdk.y.c(r5)
            java.lang.String r1 = "sdkVersion"
            java.lang.String r5 = com.adcolony.sdk.y.b((org.json.JSONObject) r5, (java.lang.String) r1)
            com.adcolony.sdk.n r1 = r3.c
            java.lang.String r1 = r1.C()
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0115
            r5 = 1
            goto L_0x0116
        L_0x0115:
            r5 = 0
        L_0x0116:
            r3.O = r5
            boolean r5 = r3.P
            if (r5 == 0) goto L_0x013e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            com.adcolony.sdk.ar r1 = r3.o
            java.lang.String r1 = r1.g()
            r5.append(r1)
            java.lang.String r1 = "026ae9c9824b3e483fa6c71fa88f57ae27816141"
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            org.json.JSONObject r5 = com.adcolony.sdk.y.c(r5)
            r3.A = r5
            org.json.JSONObject r5 = r3.A
            r3.b((org.json.JSONObject) r5)
        L_0x013e:
            boolean r5 = r3.O
            r3.e((boolean) r5)
            r3.K()
        L_0x0146:
            java.lang.String r5 = "Module.load"
            com.adcolony.sdk.l$1 r1 = new com.adcolony.sdk.l$1
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "Module.unload"
            com.adcolony.sdk.l$12 r1 = new com.adcolony.sdk.l$12
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "AdColony.on_configured"
            com.adcolony.sdk.l$13 r1 = new com.adcolony.sdk.l$13
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "AdColony.get_app_info"
            com.adcolony.sdk.l$14 r1 = new com.adcolony.sdk.l$14
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "AdColony.v4vc_reward"
            com.adcolony.sdk.l$15 r1 = new com.adcolony.sdk.l$15
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "AdColony.zone_info"
            com.adcolony.sdk.l$16 r1 = new com.adcolony.sdk.l$16
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "AdColony.probe_launch_server"
            com.adcolony.sdk.l$17 r1 = new com.adcolony.sdk.l$17
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "Crypto.sha1"
            com.adcolony.sdk.l$18 r1 = new com.adcolony.sdk.l$18
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "Crypto.crc32"
            com.adcolony.sdk.l$19 r1 = new com.adcolony.sdk.l$19
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "Crypto.uuid"
            com.adcolony.sdk.l$2 r1 = new com.adcolony.sdk.l$2
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "Device.query_advertiser_info"
            com.adcolony.sdk.l$3 r1 = new com.adcolony.sdk.l$3
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            java.lang.String r5 = "AdColony.controller_version"
            com.adcolony.sdk.l$4 r1 = new com.adcolony.sdk.l$4
            r1.<init>()
            com.adcolony.sdk.a.a((java.lang.String) r5, (com.adcolony.sdk.ah) r1)
            com.adcolony.sdk.ar r5 = r3.o
            int r5 = com.adcolony.sdk.aw.a((com.adcolony.sdk.ar) r5)
            if (r5 != r0) goto L_0x01c8
            r1 = 1
            goto L_0x01c9
        L_0x01c8:
            r1 = 0
        L_0x01c9:
            r3.S = r1
            r1 = 2
            if (r5 != r1) goto L_0x01cf
            r4 = 1
        L_0x01cf:
            r3.T = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.l.a(com.adcolony.sdk.AdColonyAppOptions, boolean):void");
    }

    /* access modifiers changed from: private */
    public void E() {
        JSONObject a2 = y.a();
        y.a(a2, "type", "AdColony.on_configuration_completed");
        JSONArray jSONArray = new JSONArray();
        for (String put : f().keySet()) {
            jSONArray.put(put);
        }
        JSONObject a3 = y.a();
        y.a(a3, "zone_ids", jSONArray);
        y.a(a2, "message", a3);
        new af("CustomMessage.controller_send", 0, a2).b();
    }

    private boolean F() {
        if (this.L || !this.c.d().contains("arm") || ADCNative.nativeNeonSupported()) {
            return true;
        }
        new aa.a().a("ARM architechture without NEON support. Disabling AdColony.").a(aa.g);
        a(true);
        return false;
    }

    private boolean e(boolean z2) {
        return a(z2, false);
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.H;
    }

    /* access modifiers changed from: package-private */
    public JSONObject c() {
        return this.A;
    }

    /* access modifiers changed from: private */
    public boolean a(boolean z2, boolean z3) {
        if (!a.d()) {
            return false;
        }
        this.R = z3;
        this.O = z2;
        if (z2 && !z3 && !J()) {
            return false;
        }
        G();
        return true;
    }

    /* access modifiers changed from: private */
    public void G() {
        new Thread(new Runnable() {
            public void run() {
                JSONObject a2 = y.a();
                y.a(a2, "url", l.e);
                y.a(a2, "content_type", "application/json");
                y.a(a2, AppLovinEventTypes.USER_VIEWED_CONTENT, l.this.c.a(l.this.c).toString());
                new aa.a().a("Launch: ").a(l.this.c.a(l.this.c).toString()).a(aa.b);
                new aa.a().a("Saving Launch to ").a(l.this.o.g()).a(l.a).a(aa.d);
                l.this.i.a(new p(new af("WebServices.post", 0, a2), l.this));
            }
        }).start();
    }

    private boolean a(JSONObject jSONObject) {
        if (!this.O) {
            new aa.a().a("Non-standard launch. Downloading new controller.").a(aa.f);
            return true;
        } else if (this.A != null && y.b(y.f(this.A, "controller"), "sha1").equals(y.b(y.f(jSONObject, "controller"), "sha1"))) {
            return false;
        } else {
            new aa.a().a("Controller sha1 does not match, downloading new controller.").a(aa.f);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void f(af afVar) {
        a(y.c(afVar.c(), "id"));
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        this.L = z2;
    }

    /* access modifiers changed from: private */
    public void g(af afVar) {
        JSONObject jSONObject = this.w.d;
        y.a(jSONObject, TapjoyConstants.TJC_APP_ID, this.w.a);
        y.a(jSONObject, "zone_ids", this.w.c);
        JSONObject a2 = y.a();
        y.a(a2, "options", jSONObject);
        afVar.a(a2).b();
    }

    /* access modifiers changed from: package-private */
    public boolean a(final af afVar) {
        final Activity c2 = a.c();
        if (c2 == null) {
            return false;
        }
        try {
            int c3 = afVar.c().has("id") ? y.c(afVar.c(), "id") : 0;
            if (c3 <= 0) {
                c3 = this.h.d();
            }
            int i2 = c3;
            a(i2);
            boolean d2 = y.d(afVar.c(), "is_webview");
            final boolean d3 = y.d(afVar.c(), "is_display_module");
            if (d2) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        ay ayVar = new ay(c2.getApplicationContext(), l.this.h.d(), d3);
                        ay ayVar2 = ayVar;
                        ayVar2.a(true, afVar);
                        l.this.C.put(Integer.valueOf(ayVar.a()), ayVar2);
                    }
                });
            } else {
                ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                final af afVar2 = afVar;
                final int i3 = i2;
                final ExecutorService executorService = newSingleThreadExecutor;
                newSingleThreadExecutor.submit(new Runnable() {
                    public void run() {
                        JSONObject f = y.f(afVar2.c(), TJAdUnitConstants.String.VIDEO_INFO);
                        l a2 = a.a();
                        if (i3 == 1 && a2.d() != null) {
                            y.a(f, "options", a2.d().d());
                        }
                        l.this.h.a((ai) new ADCVMModule(c2, i3, y.b(afVar2.c(), "filepath"), f, executorService));
                    }
                });
                JSONObject a2 = y.a();
                y.a(a2, "success", true);
                y.b(a2, "id", i2);
                afVar.a(a2).b();
            }
            return true;
        } catch (RuntimeException e2) {
            new aa.a().a("Failed to create AdUnit file://").a(y.b(afVar.c(), "filepath")).a(aa.h);
            new aa.a().a(e2.toString()).a(aa.h);
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        this.x = afVar;
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        this.z = afVar;
    }

    private void H() {
        if (a.a().l().e()) {
            this.U++;
            int i2 = this.V * this.U;
            int i3 = TapdaqError.NO_PLACEMENT_TAG_AVAILABLE;
            if (i2 <= 120) {
                i3 = this.U * this.V;
            }
            this.V = i3;
            aw.a((Runnable) new Runnable() {
                public void run() {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (a.a().l().e()) {
                                l.this.G();
                            }
                        }
                    }, (long) (l.this.V * 1000));
                }
            });
            return;
        }
        new aa.a().a("Max launch server download attempts hit, or AdColony is no longer").a(" active.").a(aa.f);
    }

    public void a(p pVar, af afVar, Map<String, List<String>> map) {
        if (pVar.a.equals(e)) {
            if (pVar.c) {
                new aa.a().a("Launch: ").a(pVar.b).a(aa.b);
                JSONObject a2 = y.a(pVar.b);
                y.a(a2, "sdkVersion", this.c.C());
                y.h(a2, this.o.g() + a);
                if (c(a2)) {
                    if (a(a2)) {
                        new aa.a().a("Controller missing or out of date. Downloading controller").a(aa.d);
                        JSONObject a3 = y.a();
                        y.a(a3, "url", this.E);
                        y.a(a3, "filepath", this.o.g() + b);
                        this.i.a(new p(new af("WebServices.download", 0, a3), this));
                    }
                    this.A = a2;
                } else if (!this.O) {
                    new aa.a().a("Incomplete or disabled launch server response. ").a("Disabling AdColony until next launch.").a(aa.g);
                    a(true);
                }
            } else {
                H();
            }
        } else if (!pVar.a.equals(this.E)) {
        } else {
            if (!c(this.F)) {
                new aa.a().a("Downloaded controller sha1 does not match, retrying.").a(aa.e);
                H();
            } else if (!this.O && !this.R) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        boolean j = l.this.J();
                        aa.a aVar = new aa.a();
                        aVar.a("Loaded library. Success=" + j).a(aa.b);
                    }
                });
            }
        }
    }

    private boolean I() {
        if (!this.N) {
            try {
                System.loadLibrary("js");
                System.loadLibrary(BuildConfig.SDK_NAME);
            } catch (UnsatisfiedLinkError unused) {
                a(true);
                new aa.a().a("Expecting libadcolony.so in libs folder but it was not found.").a(" Disabling AdColony until next launch.").a(aa.g);
                return false;
            }
        }
        this.N = true;
        return true;
    }

    /* access modifiers changed from: private */
    public boolean J() {
        if (!I()) {
            return false;
        }
        this.p.a();
        this.p.b();
        this.h.a();
        F();
        return true;
    }

    private boolean c(String str) {
        Activity c2 = a.c();
        if (c2 == null) {
            return false;
        }
        File file = new File(c2.getFilesDir().getAbsolutePath() + "/adc3/" + b);
        if (file.exists()) {
            return aw.a(str, file);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context, af afVar) {
        AdvertisingIdClient.Info info;
        if (context == null) {
            return false;
        }
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (NoClassDefFoundError unused) {
            new aa.a().a("Google Play Services ads dependencies are missing. Collecting ").a("Android ID instead of Advertising ID.").a(aa.e);
            return false;
        } catch (NoSuchMethodError unused2) {
            new aa.a().a("Google Play Services is out of date, please update to GPS 4.0+. ").a("Collecting Android ID instead of Advertising ID.").a(aa.e);
            info = null;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (Build.MANUFACTURER.equals(VungleApiClient.MANUFACTURER_AMAZON)) {
                return false;
            }
            new aa.a().a("Advertising ID is not available. Collecting Android ID instead of").a(" Advertising ID.").a(aa.e);
            return false;
        }
        if (info == null) {
            return false;
        }
        this.c.a = info.getId();
        ac.l.g.put("advertisingId", this.c.a);
        this.c.c = info.isLimitAdTrackingEnabled();
        this.c.b = true;
        if (afVar != null) {
            JSONObject a2 = y.a();
            y.a(a2, "advertiser_id", this.c.c());
            y.a(a2, "limit_ad_tracking", this.c.g());
            afVar.a(a2).b();
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(AdColonyAppOptions adColonyAppOptions) {
        synchronized (this.k.c()) {
            for (Map.Entry<String, AdColonyInterstitial> value : this.k.c().entrySet()) {
                AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) value.getValue();
                AdColonyInterstitialListener listener = adColonyInterstitial.getListener();
                adColonyInterstitial.a(true);
                if (listener != null) {
                    listener.onExpiring(adColonyInterstitial);
                }
            }
            this.k.c().clear();
        }
        this.M = false;
        a(1);
        this.B.clear();
        this.w = adColonyAppOptions;
        this.h.a();
        a(true, true);
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2) {
        if (this.h.a(i2) == null) {
            return false;
        }
        if (this.C.containsKey(Integer.valueOf(i2))) {
            ay ayVar = this.C.get(Integer.valueOf(i2));
            if (ayVar.g()) {
                ayVar.loadUrl("about:blank");
                ayVar.clearCache(true);
                ayVar.removeAllViews();
                ayVar.a(true);
            }
            this.C.remove(Integer.valueOf(i2));
        }
        if (this.z != null) {
            this.z.b();
            this.z = null;
            this.y = false;
        }
        new aa.a().a("Destroying module with id = ").a(i2).a(aa.d);
        return true;
    }

    private void b(JSONObject jSONObject) {
        if (!ADCVMModule.a) {
            JSONObject f2 = y.f(jSONObject, "logging");
            ac.k = y.a(f2, "send_level", 1);
            ac.a = y.d(f2, "log_private");
            ac.i = y.a(f2, "print_level", 3);
            ADCCrashReportManager.a = y.d(f2, "enable_crash_reporting");
            if (ADCCrashReportManager.a && I()) {
                this.p.a();
                this.p.b();
            }
            this.q.a(y.g(f2, "modules"));
        }
        this.c.a(y.f(jSONObject, "metadata"));
        this.H = y.b(y.f(jSONObject, "controller"), MediationMetaData.KEY_VERSION);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        new java.io.File(r4.o.g() + a).delete();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0047 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(org.json.JSONObject r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0014
            com.adcolony.sdk.aa$a r5 = new com.adcolony.sdk.aa$a
            r5.<init>()
            java.lang.String r1 = "Launch response verification failed - response is null or unknown"
            com.adcolony.sdk.aa$a r5 = r5.a((java.lang.String) r1)
            com.adcolony.sdk.aa r1 = com.adcolony.sdk.aa.d
            r5.a((com.adcolony.sdk.aa) r1)
            return r0
        L_0x0014:
            java.lang.String r1 = "controller"
            org.json.JSONObject r1 = com.adcolony.sdk.y.f(r5, r1)     // Catch:{ Exception -> 0x0047 }
            java.lang.String r2 = "url"
            java.lang.String r2 = com.adcolony.sdk.y.b((org.json.JSONObject) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0047 }
            r4.E = r2     // Catch:{ Exception -> 0x0047 }
            java.lang.String r2 = "sha1"
            java.lang.String r1 = com.adcolony.sdk.y.b((org.json.JSONObject) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0047 }
            r4.F = r1     // Catch:{ Exception -> 0x0047 }
            java.lang.String r1 = "status"
            java.lang.String r1 = com.adcolony.sdk.y.b((org.json.JSONObject) r5, (java.lang.String) r1)     // Catch:{ Exception -> 0x0047 }
            r4.G = r1     // Catch:{ Exception -> 0x0047 }
            java.lang.String r1 = "pie"
            java.lang.String r1 = com.adcolony.sdk.y.b((org.json.JSONObject) r5, (java.lang.String) r1)     // Catch:{ Exception -> 0x0047 }
            I = r1     // Catch:{ Exception -> 0x0047 }
            boolean r1 = com.adcolony.sdk.AdColonyEventTracker.b()     // Catch:{ Exception -> 0x0047 }
            if (r1 == 0) goto L_0x0043
            com.adcolony.sdk.AdColonyEventTracker.a()     // Catch:{ Exception -> 0x0047 }
        L_0x0043:
            r4.b((org.json.JSONObject) r5)     // Catch:{ Exception -> 0x0047 }
            goto L_0x0066
        L_0x0047:
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0066 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0066 }
            r1.<init>()     // Catch:{ Exception -> 0x0066 }
            com.adcolony.sdk.ar r2 = r4.o     // Catch:{ Exception -> 0x0066 }
            java.lang.String r2 = r2.g()     // Catch:{ Exception -> 0x0066 }
            r1.append(r2)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r2 = "026ae9c9824b3e483fa6c71fa88f57ae27816141"
            r1.append(r2)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0066 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x0066 }
            r5.delete()     // Catch:{ Exception -> 0x0066 }
        L_0x0066:
            java.lang.String r5 = r4.G
            java.lang.String r1 = "disable"
            boolean r5 = r5.equals(r1)
            r1 = 1
            if (r5 == 0) goto L_0x00aa
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0090 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0090 }
            r2.<init>()     // Catch:{ Exception -> 0x0090 }
            com.adcolony.sdk.ar r3 = r4.o     // Catch:{ Exception -> 0x0090 }
            java.lang.String r3 = r3.g()     // Catch:{ Exception -> 0x0090 }
            r2.append(r3)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r3 = "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5"
            r2.append(r3)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0090 }
            r5.<init>(r2)     // Catch:{ Exception -> 0x0090 }
            r5.delete()     // Catch:{ Exception -> 0x0090 }
        L_0x0090:
            com.adcolony.sdk.aa$a r5 = new com.adcolony.sdk.aa$a
            r5.<init>()
            java.lang.String r2 = "Launch server response with disabled status. Disabling AdColony "
            com.adcolony.sdk.aa$a r5 = r5.a((java.lang.String) r2)
            java.lang.String r2 = "until next launch."
            com.adcolony.sdk.aa$a r5 = r5.a((java.lang.String) r2)
            com.adcolony.sdk.aa r2 = com.adcolony.sdk.aa.f
            r5.a((com.adcolony.sdk.aa) r2)
            r4.a((boolean) r1)
            return r0
        L_0x00aa:
            java.lang.String r5 = r4.E
            java.lang.String r2 = ""
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L_0x00c0
            java.lang.String r5 = r4.G
            java.lang.String r2 = ""
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x00bf
            goto L_0x00c0
        L_0x00bf:
            return r1
        L_0x00c0:
            com.adcolony.sdk.aa$a r5 = new com.adcolony.sdk.aa$a
            r5.<init>()
            java.lang.String r1 = "Missing controller status or URL. Disabling AdColony until next "
            com.adcolony.sdk.aa$a r5 = r5.a((java.lang.String) r1)
            java.lang.String r1 = "launch."
            com.adcolony.sdk.aa$a r5 = r5.a((java.lang.String) r1)
            com.adcolony.sdk.aa r1 = com.adcolony.sdk.aa.g
            r5.a((com.adcolony.sdk.aa) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.l.c(org.json.JSONObject):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean d(final af afVar) {
        if (this.u == null) {
            return false;
        }
        aw.a((Runnable) new Runnable() {
            public void run() {
                l.this.u.onReward(new AdColonyReward(afVar));
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void e(af afVar) {
        AdColonyZone adColonyZone;
        if (this.L) {
            new aa.a().a("AdColony is disabled. Ignoring zone_info message.").a(aa.f);
            return;
        }
        String b2 = y.b(afVar.c(), "zone_id");
        if (this.B.containsKey(b2)) {
            adColonyZone = this.B.get(b2);
        } else {
            AdColonyZone adColonyZone2 = new AdColonyZone(b2);
            this.B.put(b2, adColonyZone2);
            adColonyZone = adColonyZone2;
        }
        adColonyZone.a(afVar);
    }

    private void K() {
        Activity c2 = a.c();
        if (c2 != null && this.X == null) {
            this.X = new Application.ActivityLifecycleCallbacks() {
                public void onActivityDestroyed(Activity activity) {
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                public void onActivityStarted(Activity activity) {
                }

                public void onActivityStopped(Activity activity) {
                }

                public void onActivityResumed(Activity activity) {
                    a.b = true;
                    a.a(activity);
                    Activity c = a.c();
                    if (c == null || !l.this.j.c() || !(c instanceof b) || ((b) c).g) {
                        new aa.a().a("onActivityResumed() Activity Lifecycle Callback").a(aa.d);
                        a.a(activity);
                        if (l.this.x != null) {
                            l.this.x.a(l.this.x.c()).b();
                            af unused = l.this.x = null;
                        }
                        boolean unused2 = l.this.K = false;
                        l.this.j.d(true);
                        l.this.j.e(true);
                        l.this.j.f(false);
                        if (l.this.f && !l.this.j.e()) {
                            l.this.j.a(true);
                        }
                        l.this.l.a();
                        if (ac.l == null || ac.l.d == null || ac.l.d.isShutdown() || ac.l.d.isTerminated()) {
                            AdColony.a(activity, a.a().w);
                            return;
                        }
                        return;
                    }
                    new aa.a().a("Ignoring onActivityResumed").a(aa.d);
                }

                public void onActivityPaused(Activity activity) {
                    a.b = false;
                    l.this.j.d(false);
                    l.this.j.e(true);
                    a.a().c.D();
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    if (!l.this.j.e()) {
                        l.this.j.a(true);
                    }
                    a.a(activity);
                }
            };
            c2.getApplication().registerActivityLifecycleCallbacks(this.X);
        }
    }

    /* access modifiers changed from: package-private */
    public AdColonyAppOptions d() {
        if (this.w == null) {
            this.w = new AdColonyAppOptions();
        }
        return this.w;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.w != null;
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull AdColonyAppOptions adColonyAppOptions) {
        this.w = adColonyAppOptions;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, AdColonyZone> f() {
        return this.B;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        this.K = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.K;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.L;
    }

    /* access modifiers changed from: package-private */
    public AdColonyRewardListener i() {
        return this.u;
    }

    /* access modifiers changed from: package-private */
    public void a(AdColonyRewardListener adColonyRewardListener) {
        this.u = adColonyRewardListener;
    }

    /* access modifiers changed from: package-private */
    public t j() {
        if (this.m == null) {
            this.m = new t();
            this.m.a();
        }
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public ADCCrashReportManager k() {
        if (this.p == null) {
            this.p = new ADCCrashReportManager();
        }
        this.p.a();
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public ao l() {
        if (this.j == null) {
            this.j = new ao();
            this.j.a();
        }
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public d m() {
        if (this.k == null) {
            this.k = new d();
            this.k.a();
        }
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public n n() {
        if (this.c == null) {
            this.c = new n();
            this.c.e();
        }
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public ar o() {
        if (this.o == null) {
            this.o = new ar();
            this.o.a();
        }
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public aj p() {
        if (this.d == null) {
            this.d = new aj();
        }
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public ag q() {
        if (this.h == null) {
            this.h = new ag();
            this.h.a();
        }
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public o r() {
        if (this.l == null) {
            this.l = new o();
        }
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public c s() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public void a(c cVar) {
        this.r = cVar;
    }

    /* access modifiers changed from: package-private */
    public az t() {
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public void a(az azVar) {
        this.s = azVar;
    }

    /* access modifiers changed from: package-private */
    public AdColonyInterstitial u() {
        return this.t;
    }

    /* access modifiers changed from: package-private */
    public void a(AdColonyInterstitial adColonyInterstitial) {
        this.t = adColonyInterstitial;
    }

    /* access modifiers changed from: package-private */
    public String v() {
        return this.D;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.D = str;
    }

    /* access modifiers changed from: package-private */
    public boolean w() {
        return this.J;
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z2) {
        this.J = z2;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, ay> x() {
        return this.C;
    }

    /* access modifiers changed from: package-private */
    public boolean y() {
        return this.y;
    }

    /* access modifiers changed from: package-private */
    public void d(boolean z2) {
        this.y = z2;
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull String str) {
        e = str;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, AdColonyCustomMessageListener> z() {
        return this.v;
    }

    /* access modifiers changed from: package-private */
    public boolean A() {
        return this.M;
    }

    /* access modifiers changed from: package-private */
    public boolean B() {
        return this.N;
    }

    static String C() {
        return I;
    }

    /* access modifiers changed from: package-private */
    public ExternalAvidAdSessionContext D() {
        if (this.Y == null) {
            this.Y = new ExternalAvidAdSessionContext("3.3.5", true);
        }
        return this.Y;
    }
}
