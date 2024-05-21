package com.applovin.impl.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StrictMode;
import android.text.TextUtils;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.ad.h;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.l;
import com.applovin.impl.sdk.d.m;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.network.e;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AppLovinAdServiceImpl implements AppLovinAdService {
    public static String URI_LOAD_URL = "/adservice/load_url";
    public static String URI_NO_OP = "/adservice/no_op";
    public static String URI_TRACK_CLICK_IMMEDIATELY = "/adservice/track_click_now";
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public final p b;
    private Handler c;
    private final Map<d, b> d;
    private final Object e = new Object();

    private class a implements AppLovinAdLoadListener {
        private final b b;

        private a(b bVar) {
            this.b = bVar;
        }

        public void adReceived(AppLovinAd appLovinAd) {
            HashSet<AppLovinAdLoadListener> hashSet;
            HashSet<AppLovinAdUpdateListener> hashSet2;
            d adZone = ((AppLovinAdBase) appLovinAd).getAdZone();
            if (!(appLovinAd instanceof h) && adZone.k()) {
                AppLovinAdServiceImpl.this.a.M().adReceived(appLovinAd);
                appLovinAd = new h(adZone, AppLovinAdServiceImpl.this.a);
            }
            synchronized (this.b.a) {
                if (adZone.h()) {
                    long i = adZone.i();
                    if (i > 0) {
                        this.b.c = System.currentTimeMillis() + (i * 1000);
                    } else if (i == 0) {
                        this.b.c = Long.MAX_VALUE;
                    }
                    this.b.b = appLovinAd;
                } else {
                    this.b.b = null;
                    this.b.c = 0;
                }
                hashSet = new HashSet<>(this.b.f);
                this.b.f.clear();
                hashSet2 = new HashSet<>(this.b.e);
                this.b.d = false;
            }
            AppLovinAdServiceImpl.this.b(adZone);
            for (AppLovinAdLoadListener a2 : hashSet) {
                AppLovinAdServiceImpl.this.a(appLovinAd, a2);
            }
            for (AppLovinAdUpdateListener a3 : hashSet2) {
                AppLovinAdServiceImpl.this.a(appLovinAd, a3);
            }
        }

        public void failedToReceiveAd(int i) {
            HashSet<AppLovinAdLoadListener> hashSet;
            synchronized (this.b.a) {
                hashSet = new HashSet<>(this.b.f);
                this.b.f.clear();
                this.b.d = false;
            }
            for (AppLovinAdLoadListener a2 : hashSet) {
                AppLovinAdServiceImpl.this.a(i, a2);
            }
        }
    }

    private static class b {
        final Object a;
        AppLovinAd b;
        long c;
        boolean d;
        /* access modifiers changed from: private */
        public final Collection<AppLovinAdUpdateListener> e;
        /* access modifiers changed from: private */
        public final Collection<AppLovinAdLoadListener> f;

        private b() {
            this.a = new Object();
            this.e = new HashSet();
            this.f = new HashSet();
        }

        public String toString() {
            return "AdLoadState{loadedAd=" + this.b + ", loadedAdExpiration=" + this.c + ", isWaitingForAd=" + this.d + ", updateListeners=" + this.e + ", pendingAdListeners=" + this.f + '}';
        }
    }

    private class c extends com.applovin.impl.sdk.d.a {
        private final d c;

        private c(d dVar) {
            super("UpdateAdTask", AppLovinAdServiceImpl.this.a);
            this.c = dVar;
        }

        public i a() {
            return i.y;
        }

        public void run() {
            AppLovinAdServiceImpl.this.b.a("AppLovinAdService", "Attempt update for spec: " + this.c);
            b b = AppLovinAdServiceImpl.this.a(this.c);
            synchronized (b.a) {
                boolean h = this.c.h();
                boolean c2 = AppLovinAdServiceImpl.this.a();
                boolean z = !b.e.isEmpty();
                boolean z2 = System.currentTimeMillis() > b.c;
                AppLovinAdServiceImpl.this.b.a("AppLovinAdService", "Update ad states - isRefreshEnabled=" + h + " hasUpdateListeners=" + z + " isCurrentAdExpired=" + z2 + " isDeviceOn=" + c2 + " isWaitingForAd=" + b.d);
                if (!h || !z || !z2 || !c2 || b.d) {
                    AppLovinAdServiceImpl.this.b.a("AppLovinAdService", "Ad update skipped");
                } else {
                    AppLovinAdServiceImpl.this.b.a("AppLovinAdService", "Performing ad update...");
                    b.d = true;
                    AppLovinAdServiceImpl.this.a(this.c, new a(b));
                }
            }
        }
    }

    AppLovinAdServiceImpl(j jVar) {
        if (jVar != null) {
            this.a = jVar;
            this.b = jVar.v();
            this.c = new Handler(Looper.getMainLooper());
            this.d = new HashMap(5);
            this.d.put(d.c(jVar), new b());
            this.d.put(d.d(jVar), new b());
            this.d.put(d.e(jVar), new b());
            this.d.put(d.f(jVar), new b());
            this.d.put(d.g(jVar), new b());
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    /* access modifiers changed from: private */
    public b a(d dVar) {
        b bVar;
        synchronized (this.e) {
            bVar = this.d.get(dVar);
            if (bVar == null) {
                bVar = new b();
                this.d.put(dVar, bVar);
            }
        }
        return bVar;
    }

    private String a(String str, int i, String str2, boolean z) {
        try {
            if (!k.b(str)) {
                return null;
            }
            if (i < 0 || i > 100) {
                i = 0;
            }
            return Uri.parse(str).buildUpon().appendQueryParameter(NativeAdImpl.QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter("vid_ts", str2).appendQueryParameter("uvs", Boolean.toString(z)).build().toString();
        } catch (Throwable th) {
            p pVar = this.b;
            pVar.b("AppLovinAdService", "Unknown error parsing the video end url: " + str, th);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(final int i, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fh)).booleanValue()) {
            this.c.post(new Runnable() {
                public void run() {
                    try {
                        appLovinAdLoadListener.failedToReceiveAd(i);
                    } catch (Throwable th) {
                        AppLovinAdServiceImpl.this.b.c("AppLovinAdService", "Unable to notify listener about ad load failure", th);
                    }
                }
            });
            return;
        }
        try {
            appLovinAdLoadListener.failedToReceiveAd(i);
        } catch (Throwable th) {
            this.b.c("AppLovinAdService", "Unable to notify listener about ad load failure", th);
        }
    }

    private void a(Uri uri, g gVar, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl) {
        if (appLovinAdView != null) {
            a((AppLovinAd) gVar);
            if (n.a(appLovinAdView.getContext(), uri, this.a)) {
                com.applovin.impl.sdk.e.h.c(adViewControllerImpl.getAdViewEventListener(), (AppLovinAd) gVar, appLovinAdView, this.a);
            }
            adViewControllerImpl.dismissInterstitialIfRequired();
            return;
        }
        this.b.d("AppLovinAdService", "Unable to launch click - adView has been prematurely destroyed");
    }

    /* access modifiers changed from: private */
    public void a(d dVar, a aVar) {
        AppLovinAd appLovinAd = (AppLovinAd) this.a.M().e(dVar);
        if (appLovinAd != null) {
            p pVar = this.b;
            pVar.a("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for " + dVar);
            aVar.adReceived(appLovinAd);
        } else {
            a((com.applovin.impl.sdk.d.a) new m(dVar, aVar, this.a), (AppLovinAdLoadListener) aVar);
        }
        if (dVar.k() && appLovinAd == null) {
            return;
        }
        if (dVar.l() || (appLovinAd != null && dVar.g() > 0)) {
            this.a.M().i(dVar);
        }
    }

    private void a(d dVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        AppLovinAd appLovinAd;
        p pVar;
        String str;
        String str2;
        int i;
        if (dVar == null) {
            throw new IllegalArgumentException("No zone specified");
        } else if (appLovinAdLoadListener != null) {
            if (!f.a(this.a.x(), this.a) && !((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dJ)).booleanValue()) {
                this.b.e("AppLovinAdService", "Failing ad load due to no internet connection.");
                i = AppLovinErrorCodes.NO_NETWORK;
            } else if (!((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dW)).booleanValue() || dVar.l() || !this.a.P().a() || this.a.P().a(dVar)) {
                p v = this.a.v();
                v.a("AppLovinAdService", "Loading next ad of zone {" + dVar + "}...");
                b a2 = a(dVar);
                synchronized (a2.a) {
                    boolean z = System.currentTimeMillis() > a2.c;
                    appLovinAd = null;
                    if (a2.b == null || z) {
                        a2.f.add(appLovinAdLoadListener);
                        if (!a2.d) {
                            this.b.a("AppLovinAdService", "Loading next ad...");
                            a2.d = true;
                            a aVar = new a(a2);
                            if (!dVar.j()) {
                                this.b.a("AppLovinAdService", "Task merge not necessary.");
                            } else if (this.a.M().a(dVar, (Object) aVar)) {
                                pVar = this.b;
                                str = "AppLovinAdService";
                                str2 = "Attaching load listener to initial preload task...";
                            } else {
                                this.b.a("AppLovinAdService", "Skipped attach of initial preload callback.");
                            }
                            a(dVar, aVar);
                        } else {
                            pVar = this.b;
                            str = "AppLovinAdService";
                            str2 = "Already waiting on an ad load...";
                        }
                        pVar.a(str, str2);
                    } else {
                        appLovinAd = a2.b;
                    }
                }
                if (appLovinAd != null) {
                    a(appLovinAd, appLovinAdLoadListener);
                    return;
                }
                return;
            } else {
                p pVar2 = this.b;
                pVar2.e("AppLovinAdService", "Failed to load ad for zone (" + dVar.a() + "). Please check that the zone has been added to your AppLovin account and given at least 30 minutes to fully propagate.");
                i = -7;
            }
            a(i, appLovinAdLoadListener);
        } else {
            throw new IllegalArgumentException("No callback specified");
        }
    }

    private void a(com.applovin.impl.sdk.c.a aVar) {
        if (k.b(aVar.a())) {
            this.a.G().a(e.j().a(n.b(aVar.a())).b(k.b(aVar.b()) ? n.b(aVar.b()) : null).a(false).a());
            return;
        }
        this.b.c("AppLovinAdService", "Requested a postback dispatch for a null URL; nothing to do...");
    }

    private void a(com.applovin.impl.sdk.d.a aVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (f.a(this.a.x(), this.a) || ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dJ)).booleanValue()) {
            this.a.a();
            p pVar = this.b;
            pVar.b("AppLovinAdService", "Loading ad using '" + aVar.c() + "'...");
            this.a.D().a(aVar, q.a.MAIN);
            return;
        }
        this.b.e("AppLovinAdService", "Failing ad load due to no internet connection.");
        a((int) AppLovinErrorCodes.NO_NETWORK, appLovinAdLoadListener);
    }

    private void a(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (appLovinAd instanceof AppLovinAdBase) {
            b a2 = a(((AppLovinAdBase) appLovinAd).getAdZone());
            synchronized (a2.a) {
                a2.b = null;
                a2.c = 0;
            }
        } else {
            throw new IllegalArgumentException("Unknown ad type specified: " + appLovinAd.getClass().getName());
        }
    }

    /* access modifiers changed from: private */
    public void a(final AppLovinAd appLovinAd, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fh)).booleanValue()) {
            this.c.post(new Runnable() {
                public void run() {
                    try {
                        appLovinAdLoadListener.adReceived(appLovinAd);
                    } catch (Throwable th) {
                        AppLovinAdServiceImpl.this.b.c("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
                    }
                }
            });
            return;
        }
        try {
            appLovinAdLoadListener.adReceived(appLovinAd);
        } catch (Throwable th) {
            this.b.c("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
        }
    }

    /* access modifiers changed from: private */
    public void a(final AppLovinAd appLovinAd, final AppLovinAdUpdateListener appLovinAdUpdateListener) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fi)).booleanValue()) {
            this.c.post(new Runnable() {
                public void run() {
                    try {
                        appLovinAdUpdateListener.adUpdated(appLovinAd);
                    } catch (Throwable th) {
                        AppLovinAdServiceImpl.this.b.c("AppLovinAdService", "Unable to notify listener about an updated loaded ad", th);
                    }
                }
            });
            return;
        }
        try {
            appLovinAdUpdateListener.adUpdated(appLovinAd);
        } catch (Throwable th) {
            this.b.c("AppLovinAdService", "Unable to notify listener about an updated loaded ad", th);
        }
    }

    private void a(List<com.applovin.impl.sdk.c.a> list) {
        if (list != null && !list.isEmpty()) {
            for (com.applovin.impl.sdk.c.a a2 : list) {
                a(a2);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean a() {
        PowerManager powerManager = (PowerManager) this.a.x().getSystemService("power");
        if (powerManager != null) {
            return powerManager.isScreenOn();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void b(d dVar) {
        long i = dVar.i();
        if (i > 0) {
            this.a.D().a((com.applovin.impl.sdk.d.a) new c(dVar), q.a.MAIN, (i + 2) * 1000);
        }
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener) {
        addAdUpdateListener(appLovinAdUpdateListener, AppLovinAdSize.BANNER);
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        } else if (appLovinAdSize != null) {
            d a2 = d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a);
            b a3 = a(a2);
            boolean z = false;
            synchronized (a3.a) {
                if (a3.c > 0 && !a3.e.contains(appLovinAdUpdateListener)) {
                    a3.e.add(appLovinAdUpdateListener);
                    z = true;
                    p pVar = this.b;
                    pVar.a("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
                }
            }
            if (z) {
                this.a.D().a((com.applovin.impl.sdk.d.a) new c(a2), q.a.MAIN);
            }
        } else {
            throw new IllegalArgumentException("No ad size specified");
        }
    }

    public AppLovinAd dequeueAd(d dVar) {
        AppLovinAd appLovinAd = (AppLovinAd) this.a.M().d(dVar);
        p pVar = this.b;
        pVar.a("AppLovinAdService", "Dequeued ad: " + appLovinAd + " for zone: " + dVar + "...");
        return appLovinAd;
    }

    /* JADX INFO: finally extract failed */
    public String getBidToken() {
        com.applovin.impl.sdk.ad.f fVar;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            fVar = this.a.H().a(((Integer) this.a.a(com.applovin.impl.sdk.b.b.aJ)).intValue());
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        if (fVar == null) {
            return "";
        }
        if (!TextUtils.isEmpty(fVar.a())) {
            p pVar = this.b;
            pVar.a("AppLovinAdService", "Generated bid token: " + fVar);
        } else {
            this.b.d("AppLovinAdService", "Failed to generate bid token");
        }
        if (!fVar.b()) {
            this.b.e("AppLovinAdService", "Bid token generated too early in session - please initialize the SDK first. Not doing so can negatively impact your eCPMs!");
        }
        return fVar.a();
    }

    public boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize) {
        return this.a.M().g(d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a));
    }

    public boolean hasPreloadedAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("AppLovinAdService", "Unable to check if ad is preloaded - invalid zone id");
            return false;
        }
        return this.a.M().g(d.a(str, this.a));
    }

    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        a(d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a), appLovinAdLoadListener);
    }

    public void loadNextAd(String str, AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        p pVar = this.b;
        pVar.a("AppLovinAdService", "Loading next ad of zone {" + str + "} with size " + appLovinAdSize);
        a(d.a(appLovinAdSize, AppLovinAdType.REGULAR, str, this.a), appLovinAdLoadListener);
    }

    /* JADX WARNING: type inference failed for: r10v19, types: [com.applovin.impl.sdk.d.o] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadNextAdForAdToken(java.lang.String r10, com.applovin.sdk.AppLovinAdLoadListener r11) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x0007
            java.lang.String r10 = r10.trim()
            goto L_0x0008
        L_0x0007:
            r10 = 0
        L_0x0008:
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            r1 = -8
            if (r0 == 0) goto L_0x001c
            com.applovin.impl.sdk.p r10 = r9.b
            java.lang.String r0 = "AppLovinAdService"
            java.lang.String r2 = "Invalid ad token specified"
            r10.e(r0, r2)
            r9.a((int) r1, (com.applovin.sdk.AppLovinAdLoadListener) r11)
            return
        L_0x001c:
            com.applovin.impl.sdk.ad.c r0 = new com.applovin.impl.sdk.ad.c
            com.applovin.impl.sdk.j r2 = r9.a
            r0.<init>(r10, r2)
            com.applovin.impl.sdk.ad.c$a r10 = r0.b()
            com.applovin.impl.sdk.ad.c$a r2 = com.applovin.impl.sdk.ad.c.a.REGULAR
            if (r10 != r2) goto L_0x004f
            com.applovin.impl.sdk.p r10 = r9.b
            java.lang.String r1 = "AppLovinAdService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Loading next ad for token: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r10.a(r1, r2)
            com.applovin.impl.sdk.d.o r10 = new com.applovin.impl.sdk.d.o
            com.applovin.impl.sdk.j r1 = r9.a
            r10.<init>(r0, r11, r1)
        L_0x004a:
            r9.a((com.applovin.impl.sdk.d.a) r10, (com.applovin.sdk.AppLovinAdLoadListener) r11)
            goto L_0x00fb
        L_0x004f:
            com.applovin.impl.sdk.ad.c$a r10 = r0.b()
            com.applovin.impl.sdk.ad.c$a r2 = com.applovin.impl.sdk.ad.c.a.AD_RESPONSE_JSON
            if (r10 != r2) goto L_0x00e0
            org.json.JSONObject r4 = r0.d()
            if (r4 == 0) goto L_0x00c7
            com.applovin.impl.sdk.j r10 = r9.a
            com.applovin.impl.sdk.e.f.f(r4, r10)
            com.applovin.impl.sdk.j r10 = r9.a
            com.applovin.impl.sdk.e.f.d(r4, r10)
            com.applovin.impl.sdk.j r10 = r9.a
            com.applovin.impl.sdk.e.f.c(r4, r10)
            java.lang.String r10 = "ads"
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
            com.applovin.impl.sdk.j r2 = r9.a
            org.json.JSONArray r10 = com.applovin.impl.sdk.e.g.a((org.json.JSONObject) r4, (java.lang.String) r10, (org.json.JSONArray) r1, (com.applovin.impl.sdk.j) r2)
            int r10 = r10.length()
            if (r10 <= 0) goto L_0x00a9
            com.applovin.impl.sdk.p r10 = r9.b
            java.lang.String r1 = "AppLovinAdService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Rendering ad for token: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r10.a(r1, r0)
            com.applovin.impl.sdk.j r10 = r9.a
            com.applovin.impl.sdk.ad.d r5 = com.applovin.impl.sdk.e.n.a((org.json.JSONObject) r4, (com.applovin.impl.sdk.j) r10)
            com.applovin.impl.sdk.d.r r10 = new com.applovin.impl.sdk.d.r
            com.applovin.impl.sdk.ad.b r6 = com.applovin.impl.sdk.ad.b.DECODED_AD_TOKEN_JSON
            com.applovin.impl.sdk.j r8 = r9.a
            r3 = r10
            r7 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            goto L_0x004a
        L_0x00a9:
            com.applovin.impl.sdk.p r10 = r9.b
            java.lang.String r1 = "AppLovinAdService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "No ad returned from the server for token: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r10.d(r1, r0)
            r10 = 204(0xcc, float:2.86E-43)
            r11.failedToReceiveAd(r10)
            goto L_0x00fb
        L_0x00c7:
            com.applovin.impl.sdk.p r10 = r9.b
            java.lang.String r2 = "AppLovinAdService"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unable to retrieve ad response JSON from token: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r10.d(r2, r0)
            goto L_0x00f8
        L_0x00e0:
            com.applovin.impl.sdk.p r10 = r9.b
            java.lang.String r2 = "AppLovinAdService"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid ad token specified: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r10.e(r2, r0)
        L_0x00f8:
            r11.failedToReceiveAd(r1)
        L_0x00fb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.AppLovinAdServiceImpl.loadNextAdForAdToken(java.lang.String, com.applovin.sdk.AppLovinAdLoadListener):void");
    }

    public void loadNextAdForZoneId(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (!TextUtils.isEmpty(str)) {
            p pVar = this.b;
            pVar.a("AppLovinAdService", "Loading next ad of zone {" + str + "}");
            a(d.a(str, this.a), appLovinAdLoadListener);
            return;
        }
        throw new IllegalArgumentException("No zone id specified");
    }

    public void loadNextAdForZoneIds(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener) {
        List<String> a2 = com.applovin.impl.sdk.e.d.a(list);
        if (a2 == null || a2.isEmpty()) {
            this.b.e("AppLovinAdService", "No zones were provided");
            a(-7, appLovinAdLoadListener);
            return;
        }
        p pVar = this.b;
        pVar.a("AppLovinAdService", "Loading next ad for zones: " + a2);
        a((com.applovin.impl.sdk.d.a) new l(a2, appLovinAdLoadListener, this.a), appLovinAdLoadListener);
    }

    public void loadNextIncentivizedAd(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        p pVar = this.b;
        pVar.a("AppLovinAdService", "Loading next incentivized ad of zone {" + str + "}");
        a(d.c(str, this.a), appLovinAdLoadListener);
    }

    public void preloadAd(AppLovinAdSize appLovinAdSize) {
        this.a.a();
        this.a.M().i(d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a));
    }

    public void preloadAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("AppLovinAdService", "Unable to preload ad for invalid zone identifier");
            return;
        }
        d a2 = d.a(str, this.a);
        this.a.M().h(a2);
        this.a.M().i(a2);
    }

    public void preloadAds(d dVar) {
        this.a.M().h(dVar);
        int g = dVar.g();
        if (g == 0 && this.a.M().b(dVar)) {
            g = 1;
        }
        this.a.M().b(dVar, g);
    }

    public void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (appLovinAdUpdateListener != null) {
            b a2 = a(d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a));
            synchronized (a2.a) {
                if (a2.e.contains(appLovinAdUpdateListener)) {
                    a2.e.remove(appLovinAdUpdateListener);
                    p pVar = this.b;
                    pVar.a("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
                }
            }
        }
    }

    public String toString() {
        return "AppLovinAdService{adLoadStates=" + this.d + '}';
    }

    public void trackAndLaunchClick(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        if (appLovinAd == null) {
            this.b.d("AppLovinAdService", "Unable to track ad view click. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking click on an ad...");
        g gVar = (g) appLovinAd;
        a(gVar.ak());
        a(uri, gVar, appLovinAdView, adViewControllerImpl);
    }

    public void trackAndLaunchVideoClick(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        if (appLovinAd == null) {
            this.b.d("AppLovinAdService", "Unable to track video click. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking VIDEO click on an ad...");
        a(((g) appLovinAd).al());
        n.a(appLovinAdView.getContext(), uri, this.a);
    }

    public void trackImpression(g gVar) {
        if (gVar == null) {
            this.b.d("AppLovinAdService", "Unable to track impression click. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking impression on ad...");
        a(gVar.am());
    }

    public void trackVideoEnd(g gVar, int i, boolean z) {
        if (gVar == null) {
            this.b.d("AppLovinAdService", "Unable to track video end. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking video end on ad...");
        List<com.applovin.impl.sdk.c.a> aj = gVar.aj();
        if (aj == null || aj.isEmpty()) {
            p pVar = this.b;
            pVar.c("AppLovinAdService", "Unable to submit persistent postback for AD #" + gVar.getAdIdNumber() + ". Missing video end tracking URL.");
            return;
        }
        String l = Long.toString(System.currentTimeMillis());
        for (com.applovin.impl.sdk.c.a next : aj) {
            if (k.b(next.a())) {
                String a2 = a(next.a(), i, l, z);
                String a3 = a(next.b(), i, l, z);
                if (a2 != null) {
                    a(new com.applovin.impl.sdk.c.a(a2, a3));
                } else {
                    p pVar2 = this.b;
                    pVar2.d("AppLovinAdService", "Failed to parse url: " + next.a());
                }
            } else {
                this.b.c("AppLovinAdService", "Requested a postback dispatch for an empty video end URL; nothing to do...");
            }
        }
    }
}
