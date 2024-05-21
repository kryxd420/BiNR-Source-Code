package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.applovin.impl.mediation.MediationServiceImpl;
import com.applovin.impl.sdk.ad.e;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.c;
import com.applovin.impl.sdk.c.f;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.c.h;
import com.applovin.impl.sdk.d.k;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.m;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.network.PostbackServiceImpl;
import com.applovin.impl.sdk.network.a;
import com.applovin.impl.sdk.network.d;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinUserService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.List;

public class j {
    protected static Context a;
    private e A;
    private c B;
    private t C;
    private a D;
    private o E;
    private PostbackServiceImpl F;
    private d G;
    private MediationServiceImpl H;
    private final Object I = new Object();
    private boolean J = false;
    private boolean K = false;
    private boolean L = false;
    private boolean M = false;
    private AppLovinSdk.SdkInitializationListener N;
    private AppLovinSdk.SdkInitializationListener O;
    /* access modifiers changed from: private */
    public AppLovinSdkConfiguration P;
    protected com.applovin.impl.sdk.b.c b;
    private String c;
    private WeakReference<Activity> d;
    private long e;
    private AppLovinSdkSettings f;
    private String g;
    private AppLovinAdServiceImpl h;
    private NativeAdServiceImpl i;
    private EventServiceImpl j;
    private UserServiceImpl k;
    private VariableServiceImpl l;
    private AppLovinSdk m;
    private p n;
    private q o;
    private a p;
    private h q;
    private com.applovin.impl.sdk.c.j r;
    private k s;
    private com.applovin.impl.sdk.b.e t;
    private f u;
    private i v;
    private m w;
    private d x;
    private q y;
    private n z;

    private void U() {
        try {
            if (((Integer) b(com.applovin.impl.sdk.b.d.b, 0)).intValue() < 90300) {
                Log.i("AppLovinSdk", "SDK has been updated since last run. Continuing...");
                w().c();
                w().a();
            } else {
                Log.d("AppLovinSdk", "SDK has not been updated since last run. Continuing...");
            }
        } catch (Exception e2) {
            v().b("AppLovinSdk", "Unable to check for SDK update", e2);
        } catch (Throwable th) {
            a(com.applovin.impl.sdk.b.d.b, Integer.valueOf(AppLovinSdk.VERSION_CODE));
            throw th;
        }
        a(com.applovin.impl.sdk.b.d.b, Integer.valueOf(AppLovinSdk.VERSION_CODE));
    }

    public static Context y() {
        return a;
    }

    public long A() {
        return this.e;
    }

    public boolean B() {
        return this.M;
    }

    public a C() {
        return this.p;
    }

    public q D() {
        return this.o;
    }

    public h E() {
        return this.q;
    }

    public com.applovin.impl.sdk.c.j F() {
        return this.r;
    }

    public d G() {
        return this.G;
    }

    public k H() {
        return this.s;
    }

    public f I() {
        return this.u;
    }

    public i J() {
        return this.v;
    }

    public PostbackServiceImpl K() {
        return this.F;
    }

    public AppLovinSdk L() {
        return this.m;
    }

    public d M() {
        return this.x;
    }

    public q N() {
        return this.y;
    }

    public n O() {
        return this.z;
    }

    public e P() {
        return this.A;
    }

    public c Q() {
        return this.B;
    }

    public t R() {
        return this.C;
    }

    public o S() {
        return this.E;
    }

    public a T() {
        return this.D;
    }

    public MediationServiceImpl a(Activity activity) {
        this.H.maybeInitialize(activity);
        return this.H;
    }

    public <ST> b<ST> a(String str, b<ST> bVar) {
        return this.b.a(str, bVar);
    }

    public <T> T a(b<T> bVar) {
        return this.b.a(bVar);
    }

    public <T> T a(com.applovin.impl.sdk.b.d<T> dVar) {
        return b(dVar, (Object) null);
    }

    public <T> T a(String str, T t2, Class cls, SharedPreferences sharedPreferences) {
        return this.t.a(str, t2, cls, sharedPreferences);
    }

    public void a() {
        synchronized (this.I) {
            if (!this.J && !this.K) {
                b();
            }
        }
    }

    public void a(long j2) {
        this.v.a(j2);
    }

    public void a(SharedPreferences sharedPreferences) {
        this.t.a(sharedPreferences);
    }

    public <T> void a(com.applovin.impl.sdk.b.d<T> dVar, T t2) {
        this.t.a(dVar, t2);
    }

    public <T> void a(com.applovin.impl.sdk.b.d<T> dVar, T t2, SharedPreferences sharedPreferences) {
        this.t.a(dVar, t2, sharedPreferences);
    }

    public void a(AppLovinSdk.SdkInitializationListener sdkInitializationListener) {
        if (!d()) {
            this.N = sdkInitializationListener;
        } else if (sdkInitializationListener != null) {
            sdkInitializationListener.onSdkInitialized(this.P);
        }
    }

    public void a(AppLovinSdk appLovinSdk) {
        this.m = appLovinSdk;
    }

    public void a(String str) {
        this.b.a((b<?>) b.ei, (Object) str);
        this.b.a();
    }

    public void a(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        com.applovin.impl.sdk.b.e eVar;
        com.applovin.impl.sdk.b.d<String> dVar;
        String bool;
        this.c = str;
        this.e = System.currentTimeMillis();
        this.f = appLovinSdkSettings;
        this.P = new SdkConfigurationImpl(this);
        a = context.getApplicationContext();
        if (context instanceof Activity) {
            this.d = new WeakReference<>((Activity) context);
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            this.n = new p(this);
            this.t = new com.applovin.impl.sdk.b.e(this);
            this.b = new com.applovin.impl.sdk.b.c(this);
            this.b.b();
            this.u = new f(this);
            this.u.b();
            U();
            this.z = new n(this);
            this.x = new d(this);
            this.y = new q(this);
            this.A = new e(this);
            this.j = new EventServiceImpl(this);
            this.k = new UserServiceImpl(this);
            this.l = new VariableServiceImpl(this);
            this.B = new c(this);
            this.o = new q(this);
            this.p = new a(this);
            this.q = new h(this);
            this.r = new com.applovin.impl.sdk.c.j(this);
            this.s = new k(this);
            this.D = new a(this, context);
            this.h = new AppLovinAdServiceImpl(this);
            this.i = new NativeAdServiceImpl(this);
            this.C = new t(this);
            this.E = new o(this);
            this.F = new PostbackServiceImpl(this);
            this.G = new d(this);
            this.H = new MediationServiceImpl(this);
            this.v = new i(this);
            this.w = new m(this);
            if (TextUtils.isEmpty(str)) {
                this.L = true;
                Log.e("AppLovinSdk", "Unable to find AppLovin SDK key. Please add  meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                StringWriter stringWriter = new StringWriter();
                new Throwable("").printStackTrace(new PrintWriter(stringWriter));
                String stringWriter2 = stringWriter.toString();
                Log.e("AppLovinSdk", "Called with an invalid SDK key from: " + stringWriter2);
            }
            if (!u()) {
                if (((Boolean) this.b.a(b.ae)).booleanValue()) {
                    appLovinSdkSettings.setTestAdsEnabled(n.b(context));
                    appLovinSdkSettings.setVerboseLogging(n.c(context));
                    w().a(appLovinSdkSettings);
                    w().a();
                }
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (TextUtils.isEmpty((String) this.t.b(com.applovin.impl.sdk.b.d.a, null, defaultSharedPreferences))) {
                    this.M = true;
                    eVar = this.t;
                    dVar = com.applovin.impl.sdk.b.d.a;
                    bool = Boolean.toString(true);
                } else {
                    eVar = this.t;
                    dVar = com.applovin.impl.sdk.b.d.a;
                    bool = Boolean.toString(false);
                }
                eVar.a(dVar, bool, defaultSharedPreferences);
                if (TextUtils.isEmpty((String) a(com.applovin.impl.sdk.b.d.d))) {
                    a(com.applovin.impl.sdk.b.d.d, String.valueOf(((int) (Math.random() * 100.0d)) + 1));
                }
                b();
            } else {
                a(false);
            }
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
    }

    public <T> void a(String str, T t2, SharedPreferences.Editor editor) {
        this.t.a(str, t2, editor);
    }

    public void a(boolean z2) {
        synchronized (this.I) {
            this.J = false;
            this.K = z2;
        }
        D().b();
    }

    public <T> T b(com.applovin.impl.sdk.b.d<T> dVar, T t2) {
        return this.t.b(dVar, t2);
    }

    public <T> T b(com.applovin.impl.sdk.b.d<T> dVar, T t2, SharedPreferences sharedPreferences) {
        return this.t.b(dVar, t2, sharedPreferences);
    }

    public List<String> b(b bVar) {
        return this.b.b(bVar);
    }

    public void b() {
        synchronized (this.I) {
            this.J = true;
            D().a();
            D().a((com.applovin.impl.sdk.d.a) new k(this), q.a.MAIN);
        }
    }

    public <T> void b(com.applovin.impl.sdk.b.d<T> dVar) {
        this.t.a(dVar);
    }

    public void b(String str) {
        this.w.a(str);
    }

    public void c(String str) {
        this.g = str;
    }

    public boolean c() {
        boolean z2;
        synchronized (this.I) {
            z2 = this.J;
        }
        return z2;
    }

    public boolean d() {
        boolean z2;
        synchronized (this.I) {
            z2 = this.K;
        }
        return z2;
    }

    public void e() {
        if (this.N != null) {
            this.n.a("AppLovinSdk", "Calling back publisher's initialization completion listener...");
            final AppLovinSdk.SdkInitializationListener sdkInitializationListener = this.N;
            if (d()) {
                this.N = null;
                this.O = null;
            } else if (this.O != sdkInitializationListener) {
                if (((Boolean) a(b.ai)).booleanValue()) {
                    this.N = null;
                } else {
                    this.O = sdkInitializationListener;
                }
            } else {
                return;
            }
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    sdkInitializationListener.onSdkInitialized(j.this.P);
                }
            });
        }
    }

    public void f() {
        long b2 = this.q.b(g.g);
        this.b.c();
        this.b.a();
        this.q.a();
        this.B.b();
        this.r.b();
        this.q.b(g.g, b2 + 1);
        b();
    }

    public boolean g() {
        for (String equalsIgnoreCase : com.applovin.impl.sdk.e.d.a((String) a(b.ej))) {
            if (equalsIgnoreCase.equalsIgnoreCase(Build.MANUFACTURER)) {
                return true;
            }
        }
        return false;
    }

    public void h() {
        this.C.a(a);
    }

    public boolean i() {
        return this.C.d();
    }

    public boolean j() {
        return this.C.e();
    }

    public String k() {
        return this.w.a();
    }

    public AppLovinSdkSettings l() {
        return this.f;
    }

    public AppLovinSdkConfiguration m() {
        return this.P;
    }

    public String n() {
        return this.g;
    }

    public AppLovinAdServiceImpl o() {
        return this.h;
    }

    public NativeAdServiceImpl p() {
        return this.i;
    }

    public AppLovinEventService q() {
        return this.j;
    }

    public AppLovinUserService r() {
        return this.k;
    }

    public VariableServiceImpl s() {
        return this.l;
    }

    public String t() {
        return this.c;
    }

    public String toString() {
        return "CoreSdk{sdkKey='" + this.c + '\'' + ", enabled=" + this.K + ", isFirstSession=" + this.M + '}';
    }

    public boolean u() {
        return this.L;
    }

    public p v() {
        return this.n;
    }

    public com.applovin.impl.sdk.b.c w() {
        return this.b;
    }

    public Context x() {
        return a;
    }

    public Activity z() {
        if (this.d != null) {
            return (Activity) this.d.get();
        }
        return null;
    }
}
