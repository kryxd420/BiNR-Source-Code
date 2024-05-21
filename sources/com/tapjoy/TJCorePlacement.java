package com.tapjoy;

import android.content.Context;
import android.os.SystemClock;
import com.tapjoy.TJAdUnit;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.internal.b;
import com.tapjoy.internal.ce;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.fu;
import com.tapjoy.internal.fy;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.ga;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gj;
import com.tapjoy.internal.gm;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.ha;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.hi;
import com.tapjoy.internal.hk;
import com.tapjoy.internal.hm;
import com.tapjoy.internal.in;
import com.tapjoy.internal.ju;
import com.vungle.warren.ui.VungleActivity;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class TJCorePlacement {
    /* access modifiers changed from: package-private */
    public static final String a = "TJCorePlacement";
    private TJAdUnit.TJAdUnitVideoListener A = new TJAdUnit.TJAdUnitVideoListener() {
        public final void onVideoStart() {
            TJPlacement a2 = TJCorePlacement.this.a("SHOW");
            if (a2 != null && a2.getVideoListener() != null) {
                a2.getVideoListener().onVideoStart(a2);
            }
        }

        public final void onVideoCompleted() {
            TJPlacement a2 = TJCorePlacement.this.a("SHOW");
            if (a2 != null && a2.getVideoListener() != null) {
                a2.getVideoListener().onVideoComplete(a2);
            }
        }

        public final void onVideoError(String str) {
            TJPlacement a2 = TJCorePlacement.this.a("SHOW");
            if (a2 != null && a2.getVideoListener() != null) {
                a2.getVideoListener().onVideoError(a2, str);
            }
        }
    };
    /* access modifiers changed from: package-private */
    public Context b = b.c();
    /* access modifiers changed from: package-private */
    public TJPlacementData c;
    String d;
    /* access modifiers changed from: package-private */
    public long e;
    final ga f = new ga();
    TJAdUnit g;
    /* access modifiers changed from: package-private */
    public boolean h = false;
    /* access modifiers changed from: package-private */
    public hk i = null;
    boolean j;
    volatile boolean k = false;
    volatile boolean l = false;
    String m;
    String n;
    String o;
    String p;
    HashMap q;
    private Map r = new HashMap();
    /* access modifiers changed from: private */
    public Map s;
    /* access modifiers changed from: private */
    public fq t;
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public in v = null;
    /* access modifiers changed from: private */
    public volatile boolean w = false;
    private volatile boolean x = false;
    private boolean y;
    private TJAdUnit.TJAdUnitWebViewListener z = new TJAdUnit.TJAdUnitWebViewListener() {
        public final void onContentReady() {
            TJCorePlacement.this.e();
        }

        public final void onClosed() {
            if (TJCorePlacement.this.h) {
                TJPlacementManager.decrementPlacementCacheCount();
                boolean unused = TJCorePlacement.this.h = false;
            }
            if (TJCorePlacement.this.u) {
                TJPlacementManager.decrementPlacementPreRenderCount();
                boolean unused2 = TJCorePlacement.this.u = false;
            }
        }
    };

    TJCorePlacement(String str, String str2, boolean z2) {
        if (this.b == null) {
            TapjoyLog.d(a, "getVisibleActivity() is NULL. Activity can be explicitly set via `Tapjoy.setActivity(Activity)`");
        }
        this.y = z2;
        this.c = new TJPlacementData(str2, getPlacementContentUrl());
        this.c.setPlacementName(str);
        this.d = UUID.randomUUID().toString();
        this.g = new TJAdUnit();
        this.g.setWebViewListener(this.z);
        this.g.setVideoListener(this.A);
    }

    /* access modifiers changed from: package-private */
    public final void a(TJPlacement tJPlacement) {
        boolean z2 = false;
        if (tJPlacement == null) {
            a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "Cannot request content from a NULL placement"));
            return;
        }
        a("REQUEST", tJPlacement);
        if (this.e - SystemClock.elapsedRealtime() > 0) {
            String str = a;
            TapjoyLog.d(str, "Content has not expired yet for " + this.c.getPlacementName());
            if (this.k) {
                gj.b("TJPlacement.requestContent").a("content_type", (Object) a()).a("from", (Object) "cache").c();
                this.x = false;
                b(tJPlacement);
                e();
                return;
            }
            gj.b("TJPlacement.requestContent").a("content_type", (Object) "none").a("from", (Object) "cache").c();
            b(tJPlacement);
            return;
        }
        if (this.k) {
            gj.c("TJPlacement.requestContent").a("was_available", (Object) true);
        }
        if (this.l) {
            gj.c("TJPlacement.requestContent").a("was_ready", (Object) true);
        }
        if (!ju.c(this.o)) {
            HashMap hashMap = new HashMap();
            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_AGENT, this.o);
            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_ID, this.p);
            if (this.q != null && !this.q.isEmpty()) {
                z2 = true;
            }
            if (z2) {
                for (String str2 : this.q.keySet()) {
                    hashMap.put(TJAdUnitConstants.AUCTION_PARAM_PREFIX + str2, this.q.get(str2));
                }
                a(this.c.getAuctionMediationURL(), (Map) hashMap);
                return;
            }
            a(this.c.getMediationURL(), (Map) hashMap);
            return;
        }
        d();
    }

    private synchronized void d() {
        String url = this.c.getUrl();
        if (ju.c(url)) {
            url = getPlacementContentUrl();
            if (ju.c(url)) {
                gj.b("TJPlacement.requestContent").a("TJPlacement is missing APP_ID").c();
                a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "TJPlacement is missing APP_ID"));
                return;
            }
            this.c.updateUrl(url);
        }
        String str = a;
        TapjoyLog.d(str, "sendContentRequest -- URL: " + url + " name: " + this.c.getPlacementName());
        a(url, (Map) null);
    }

    private synchronized void a(String str, Map map) {
        if (this.w) {
            String str2 = a;
            TapjoyLog.i(str2, "Placement " + this.c.getPlacementName() + " is already requesting content");
            gj.b("TJPlacement.requestContent").b("already doing").c();
            return;
        }
        this.c.resetPlacementRequestData();
        ga gaVar = this.f;
        String str3 = null;
        gaVar.b = null;
        gaVar.d = null;
        gaVar.a = null;
        this.g.resetContentLoadState();
        this.w = false;
        this.x = false;
        this.k = false;
        this.l = false;
        this.i = null;
        this.v = null;
        this.w = true;
        final TJPlacement a2 = a("REQUEST");
        if (!this.y) {
            this.s = TapjoyConnectCore.getGenericURLParams();
            this.s.putAll(TapjoyConnectCore.getTimeStampAndVerifierParams());
        } else {
            this.s = TapjoyConnectCore.getLimitedGenericURLParams();
            this.s.putAll(TapjoyConnectCore.getLimitedTimeStampAndVerifierParams());
        }
        TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_NAME, this.c.getPlacementName(), true);
        TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_PRELOAD, "true", true);
        TapjoyUtil.safePut(this.s, "debug", Boolean.toString(ha.a), true);
        hd a3 = hd.a();
        Map map2 = this.s;
        if (a3.b != null) {
            hm hmVar = a3.b;
            hmVar.b();
            str3 = hmVar.b.a();
        }
        TapjoyUtil.safePut(map2, TJAdUnitConstants.PARAM_ACTION_ID_EXCLUSION, str3, true);
        TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_BY_SDK, String.valueOf(this.j), true);
        TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PUSH_ID, a2.pushId, true);
        TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_MEDIATION_SOURCE, this.m, true);
        TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_ADAPTER_VERSION, this.n, true);
        if (map != null) {
            this.s.putAll(map);
        }
        final fm fmVar = new fm(ge.b().c("placement_request_content_retry_timeout"));
        final gm d2 = ge.b().d("placement_request_content_retry_backoff");
        final gj.a d3 = gj.d("TJPlacement.requestContent");
        final String str4 = str;
        new Thread() {
            public final void run() {
                gj.a("TJPlacement.requestContent", d3);
                int i = 0;
                while (!a()) {
                    i++;
                    TJCorePlacement.this.s.put(TapjoyConstants.TJC_RETRY, Integer.toString(i));
                    if (i == 1) {
                        d3.a("retry_timeout", (Object) Long.valueOf(fmVar.b));
                    }
                    d3.a("retry_count", (long) i);
                }
            }

            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:64:0x0300 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private boolean a() {
                /*
                    r12 = this;
                    java.lang.String r0 = com.tapjoy.TJCorePlacement.a
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    java.lang.String r2 = "Sending content request for placement "
                    r1.<init>(r2)
                    com.tapjoy.TJCorePlacement r2 = com.tapjoy.TJCorePlacement.this
                    java.lang.String r2 = r2.c.getPlacementName()
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.tapjoy.TapjoyLog.i(r0, r1)
                    com.tapjoy.TJCorePlacement r0 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.internal.hd r1 = com.tapjoy.internal.hd.a()
                    com.tapjoy.TJCorePlacement r2 = com.tapjoy.TJCorePlacement.this
                    java.lang.String r8 = r2.c.getPlacementName()
                    com.tapjoy.TJCorePlacement r2 = com.tapjoy.TJCorePlacement.this
                    android.content.Context r9 = r2.b
                    com.tapjoy.internal.hl r1 = r1.a
                    com.tapjoy.internal.hd r2 = r1.a
                    r10 = 0
                    com.tapjoy.internal.ff r2 = r2.a((boolean) r10)
                    com.tapjoy.internal.in r11 = new com.tapjoy.internal.in
                    com.tapjoy.internal.hd r4 = r1.a
                    com.tapjoy.internal.fe r5 = r2.d
                    com.tapjoy.internal.ey r6 = r2.e
                    com.tapjoy.internal.fl r7 = r2.f
                    r3 = r11
                    r3.<init>(r4, r5, r6, r7, r8, r9)
                    com.tapjoy.internal.in unused = r0.v = r11
                    com.tapjoy.TapjoyURLConnection r0 = new com.tapjoy.TapjoyURLConnection
                    r0.<init>()
                    java.lang.String r1 = r7
                    com.tapjoy.TJCorePlacement r2 = com.tapjoy.TJCorePlacement.this
                    java.util.Map r2 = r2.s
                    r3 = 0
                    com.tapjoy.TapjoyHttpURLResponse r0 = r0.getResponseFromURL((java.lang.String) r1, (java.util.Map) r3, (java.util.Map) r3, (java.util.Map) r2)
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacementData r1 = r1.c
                    int r2 = r0.statusCode
                    r1.setHttpStatusCode(r2)
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacementData r1 = r1.c
                    java.lang.String r2 = r0.response
                    r1.setHttpResponse(r2)
                    java.lang.String r1 = "x-tapjoy-prerender"
                    java.lang.String r1 = r0.getHeaderFieldAsString(r1)
                    java.lang.String r2 = "0"
                    boolean r1 = r1.equals(r2)
                    r2 = 1
                    if (r1 != 0) goto L_0x0087
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacementData r1 = r1.c
                    r1.setPrerenderingRequested(r2)
                L_0x0087:
                    java.lang.String r1 = "X-Tapjoy-Debug"
                    java.lang.String r1 = r0.getHeaderFieldAsString(r1)
                    if (r1 == 0) goto L_0x00a4
                    java.lang.String r3 = com.tapjoy.TJCorePlacement.a
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    java.lang.String r5 = "Tapjoy-Server-Debug: "
                    r4.<init>(r5)
                    r4.append(r1)
                    java.lang.String r1 = r4.toString()
                    com.tapjoy.TapjoyLog.v(r3, r1)
                L_0x00a4:
                    long r3 = r0.expires
                    r5 = 0
                    int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r1 <= 0) goto L_0x00cc
                    long r3 = r0.expires
                    long r7 = r0.date
                    int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                    if (r1 <= 0) goto L_0x00b7
                    long r7 = r0.date
                    goto L_0x00bb
                L_0x00b7:
                    long r7 = com.tapjoy.internal.w.b()
                L_0x00bb:
                    r1 = 0
                    long r3 = r3 - r7
                    int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r1 <= 0) goto L_0x00d1
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    long r7 = android.os.SystemClock.elapsedRealtime()
                    long r7 = r7 + r3
                    long unused = r1.e = r7
                    goto L_0x00d1
                L_0x00cc:
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    long unused = r1.e = r5
                L_0x00d1:
                    if (r0 == 0) goto L_0x0305
                    com.tapjoy.TJPlacement r1 = r8
                    com.tapjoy.TJPlacementListener r1 = r1.getListener()
                    if (r1 == 0) goto L_0x0305
                    int r1 = r0.statusCode
                    if (r1 == 0) goto L_0x0297
                    r3 = 200(0xc8, float:2.8E-43)
                    if (r1 == r3) goto L_0x0109
                    java.lang.String r1 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r1 = com.tapjoy.internal.gj.b(r1)
                    java.lang.String r3 = "content_type"
                    java.lang.String r4 = "none"
                    com.tapjoy.internal.gj$a r1 = r1.a((java.lang.String) r3, (java.lang.Object) r4)
                    java.lang.String r3 = "code"
                    int r0 = r0.statusCode
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    com.tapjoy.internal.gj$a r0 = r1.a((java.lang.String) r3, (java.lang.Object) r0)
                    r0.c()
                    com.tapjoy.TJCorePlacement r0 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacement r1 = r8
                    r0.b((com.tapjoy.TJPlacement) r1)
                    goto L_0x0305
                L_0x0109:
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJCorePlacement.i(r1)
                    java.lang.String r1 = "Content-Type"
                    java.lang.String r1 = r0.getHeaderFieldAsString(r1)
                    boolean r3 = com.tapjoy.internal.ju.c(r1)
                    if (r3 != 0) goto L_0x01ea
                    java.lang.String r3 = "json"
                    boolean r1 = r1.contains(r3)
                    if (r1 == 0) goto L_0x01ea
                    java.lang.String r1 = "X-Tapjoy-Disable-Preload"
                    java.lang.String r1 = r0.getHeaderFieldAsString(r1)
                    java.lang.String r3 = "1"
                    boolean r1 = r1.equals(r3)
                    if (r1 == 0) goto L_0x01a0
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this     // Catch:{ TapjoyException -> 0x0160 }
                    java.lang.String r3 = r0.response     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.TJCorePlacement.a((com.tapjoy.TJCorePlacement) r1, (java.lang.String) r3)     // Catch:{ TapjoyException -> 0x0160 }
                    java.lang.String r1 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r1 = com.tapjoy.internal.gj.b(r1)     // Catch:{ TapjoyException -> 0x0160 }
                    java.lang.String r3 = "content_type"
                    java.lang.String r4 = "ad"
                    com.tapjoy.internal.gj$a r1 = r1.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ TapjoyException -> 0x0160 }
                    r1.c()     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.internal.ga r1 = r1.f     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.TJCorePlacement r3 = com.tapjoy.TJCorePlacement.this     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.internal.fq r3 = r3.t     // Catch:{ TapjoyException -> 0x0160 }
                    r1.a = r3     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.TJCorePlacement.k(r1)     // Catch:{ TapjoyException -> 0x0160 }
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this     // Catch:{ TapjoyException -> 0x0160 }
                    r1.e()     // Catch:{ TapjoyException -> 0x0160 }
                    goto L_0x0305
                L_0x0160:
                    r1 = move-exception
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r1 = r1.getMessage()
                    r3.append(r1)
                    java.lang.String r1 = " for placement "
                    r3.append(r1)
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    java.lang.String r1 = r1.c.getPlacementName()
                    r3.append(r1)
                    java.lang.String r1 = r3.toString()
                    java.lang.String r3 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r3 = com.tapjoy.internal.gj.b(r3)
                    java.lang.String r4 = "server error"
                    com.tapjoy.internal.gj$a r3 = r3.a((java.lang.String) r4)
                    r3.c()
                    com.tapjoy.TJCorePlacement r3 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacement r4 = r8
                    com.tapjoy.TapjoyErrorMessage$ErrorType r5 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR
                    com.tapjoy.TJError r6 = new com.tapjoy.TJError
                    int r0 = r0.statusCode
                    r6.<init>(r0, r1)
                    r3.a(r4, r5, r6)
                    goto L_0x0305
                L_0x01a0:
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    java.lang.String r3 = r0.response
                    boolean r1 = r1.b((java.lang.String) r3)
                    if (r1 == 0) goto L_0x01c7
                    java.lang.String r0 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r0 = com.tapjoy.internal.gj.b(r0)
                    java.lang.String r1 = "content_type"
                    java.lang.String r3 = "mm"
                    com.tapjoy.internal.gj$a r0 = r0.a((java.lang.String) r1, (java.lang.Object) r3)
                    r0.c()
                    com.tapjoy.TJCorePlacement r0 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJCorePlacement.k(r0)
                    com.tapjoy.TJCorePlacement r0 = com.tapjoy.TJCorePlacement.this
                    r0.e()
                    goto L_0x0305
                L_0x01c7:
                    java.lang.String r1 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r1 = com.tapjoy.internal.gj.b(r1)
                    java.lang.String r3 = "asset error"
                    com.tapjoy.internal.gj$a r1 = r1.a((java.lang.String) r3)
                    r1.c()
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacement r3 = r8
                    com.tapjoy.TapjoyErrorMessage$ErrorType r4 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR
                    com.tapjoy.TJError r5 = new com.tapjoy.TJError
                    int r6 = r0.statusCode
                    java.lang.String r0 = r0.response
                    r5.<init>(r6, r0)
                    r1.a(r3, r4, r5)
                    goto L_0x0305
                L_0x01ea:
                    java.lang.String r1 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r1 = com.tapjoy.internal.gj.b(r1)
                    java.lang.String r3 = "content_type"
                    java.lang.String r4 = "ad"
                    com.tapjoy.internal.gj$a r1 = r1.a((java.lang.String) r3, (java.lang.Object) r4)
                    r1.c()
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.internal.ga r1 = r1.f
                    com.tapjoy.TJCorePlacement r3 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.internal.fq r3 = r3.t
                    r1.a = r3
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJCorePlacement.k(r1)
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJCorePlacement$3$1 r3 = new com.tapjoy.TJCorePlacement$3$1
                    r3.<init>()
                    java.lang.String r4 = com.tapjoy.TJCorePlacement.a
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    java.lang.String r6 = "Checking if there is content to cache for placement "
                    r5.<init>(r6)
                    com.tapjoy.TJPlacementData r6 = r1.c
                    java.lang.String r6 = r6.getPlacementName()
                    r5.append(r6)
                    java.lang.String r5 = r5.toString()
                    com.tapjoy.TapjoyLog.i(r4, r5)
                    java.lang.String r4 = "x-tapjoy-cacheable-assets"
                    java.lang.String r0 = r0.getHeaderFieldAsString(r4)
                    r4 = 2
                    boolean r5 = com.tapjoy.TJPlacementManager.canCachePlacement()     // Catch:{ JSONException -> 0x0293 }
                    if (r5 != 0) goto L_0x0257
                    java.lang.String r0 = com.tapjoy.TJCorePlacement.a     // Catch:{ JSONException -> 0x0293 }
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0293 }
                    java.lang.String r6 = "Placement caching limit reached. No content will be cached for placement "
                    r5.<init>(r6)     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TJPlacementData r1 = r1.c     // Catch:{ JSONException -> 0x0293 }
                    java.lang.String r1 = r1.getPlacementName()     // Catch:{ JSONException -> 0x0293 }
                    r5.append(r1)     // Catch:{ JSONException -> 0x0293 }
                    java.lang.String r1 = r5.toString()     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TapjoyLog.i(r0, r1)     // Catch:{ JSONException -> 0x0293 }
                    r3.onCachingComplete(r4)     // Catch:{ JSONException -> 0x0293 }
                    goto L_0x0305
                L_0x0257:
                    org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0293 }
                    r5.<init>(r0)     // Catch:{ JSONException -> 0x0293 }
                    int r0 = r5.length()     // Catch:{ JSONException -> 0x0293 }
                    if (r0 <= 0) goto L_0x028e
                    java.lang.String r0 = com.tapjoy.TJCorePlacement.a     // Catch:{ JSONException -> 0x0293 }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0293 }
                    java.lang.String r7 = "Begin caching content for placement "
                    r6.<init>(r7)     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TJPlacementData r7 = r1.c     // Catch:{ JSONException -> 0x0293 }
                    java.lang.String r7 = r7.getPlacementName()     // Catch:{ JSONException -> 0x0293 }
                    r6.append(r7)     // Catch:{ JSONException -> 0x0293 }
                    java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TapjoyLog.i(r0, r6)     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TJPlacementManager.incrementPlacementCacheCount()     // Catch:{ JSONException -> 0x0293 }
                    r1.h = r2     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TapjoyCache r0 = com.tapjoy.TapjoyCache.getInstance()     // Catch:{ JSONException -> 0x0293 }
                    com.tapjoy.TJCorePlacement$6 r6 = new com.tapjoy.TJCorePlacement$6     // Catch:{ JSONException -> 0x0293 }
                    r6.<init>(r3)     // Catch:{ JSONException -> 0x0293 }
                    r0.cacheAssetGroup(r5, r6)     // Catch:{ JSONException -> 0x0293 }
                    goto L_0x0305
                L_0x028e:
                    r3.onCachingComplete(r2)     // Catch:{ JSONException -> 0x0293 }
                    goto L_0x0305
                L_0x0293:
                    r3.onCachingComplete(r4)
                    goto L_0x0305
                L_0x0297:
                    com.tapjoy.internal.fm r1 = r6
                    com.tapjoy.internal.gm r3 = r9
                    long r3 = r3.e
                    boolean r1 = r1.a(r3)
                    if (r1 == 0) goto L_0x02d3
                    java.lang.String r1 = "TJPlacement.requestContent"
                    com.tapjoy.internal.gj$a r1 = com.tapjoy.internal.gj.b(r1)
                    java.lang.String r3 = "network error"
                    com.tapjoy.internal.gj$a r1 = r1.a((java.lang.String) r3)
                    java.lang.String r3 = "retry_timeout"
                    com.tapjoy.internal.fm r4 = r6
                    long r4 = r4.b
                    java.lang.Long r4 = java.lang.Long.valueOf(r4)
                    com.tapjoy.internal.gj$a r1 = r1.a((java.lang.String) r3, (java.lang.Object) r4)
                    r1.c()
                    com.tapjoy.TJCorePlacement r1 = com.tapjoy.TJCorePlacement.this
                    com.tapjoy.TJPlacement r3 = r8
                    com.tapjoy.TapjoyErrorMessage$ErrorType r4 = com.tapjoy.TapjoyErrorMessage.ErrorType.NETWORK_ERROR
                    com.tapjoy.TJError r5 = new com.tapjoy.TJError
                    int r6 = r0.statusCode
                    java.lang.String r0 = r0.response
                    r5.<init>(r6, r0)
                    r1.a(r3, r4, r5)
                    goto L_0x0305
                L_0x02d3:
                    com.tapjoy.internal.gm r0 = r9
                    long r1 = r0.e
                    long r3 = r0.e
                    double r3 = (double) r3
                    double r7 = r0.d
                    java.lang.Double.isNaN(r3)
                    double r3 = r3 * r7
                    long r3 = (long) r3
                    long r7 = r0.b
                    int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                    if (r9 >= 0) goto L_0x02eb
                    long r3 = r0.b
                    goto L_0x02f3
                L_0x02eb:
                    long r7 = r0.c
                    int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                    if (r9 <= 0) goto L_0x02f3
                    long r3 = r0.c
                L_0x02f3:
                    r0.e = r3
                    int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
                    if (r3 <= 0) goto L_0x0304
                    monitor-enter(r0)
                    r0.wait(r1)     // Catch:{ InterruptedException -> 0x0300 }
                    goto L_0x0300
                L_0x02fe:
                    r1 = move-exception
                    goto L_0x0302
                L_0x0300:
                    monitor-exit(r0)     // Catch:{ all -> 0x02fe }
                    goto L_0x0304
                L_0x0302:
                    monitor-exit(r0)     // Catch:{ all -> 0x02fe }
                    throw r1
                L_0x0304:
                    return r10
                L_0x0305:
                    com.tapjoy.TJCorePlacement r0 = com.tapjoy.TJCorePlacement.this
                    boolean unused = r0.w = false
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJCorePlacement.AnonymousClass3.a():boolean");
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        try {
            in.a aVar = (in.a) this.v.a(URI.create(this.c.getUrl()), new ByteArrayInputStream(str.getBytes()));
            this.i = aVar.a;
            aVar.a.b();
            if (!aVar.a.c()) {
                TapjoyLog.e(a, "Failed to load fiverocks placement");
                return false;
            }
            fu fuVar = null;
            if (this.i instanceof hi) {
                fuVar = new fy(this.c.getPlacementName(), this.c.getPlacementType(), this.t);
            } else if (this.i instanceof gz) {
                fuVar = new fz(this.c.getPlacementName(), this.c.getPlacementType(), this.t);
            }
            this.f.a = fuVar;
            return true;
        } catch (IOException e2) {
            TapjoyLog.e(a, e2.toString());
            e2.printStackTrace();
            return false;
        } catch (ce e3) {
            TapjoyLog.e(a, e3.toString());
            e3.printStackTrace();
            return false;
        }
    }

    public Context getContext() {
        return this.b;
    }

    public void setContext(Context context) {
        this.b = context;
    }

    public TJAdUnit getAdUnit() {
        return this.g;
    }

    public TJPlacementData getPlacementData() {
        return this.c;
    }

    public boolean isContentReady() {
        return this.l;
    }

    public boolean isContentAvailable() {
        return this.k;
    }

    public String getPlacementContentUrl() {
        String b2 = b();
        if (!ju.c(b2)) {
            return TapjoyConnectCore.getPlacementURL() + "v1/apps/" + b2 + "/content?";
        }
        TapjoyLog.i(a, "Placement content URL cannot be generated for null app ID");
        return "";
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        if (this.i != null) {
            return "mm";
        }
        return this.k ? "ad" : "none";
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, TJPlacement tJPlacement) {
        synchronized (this.r) {
            this.r.put(str, tJPlacement);
            if (tJPlacement != null) {
                String str2 = a;
                TapjoyLog.d(str2, "Setting " + str + " placement: " + tJPlacement.getGUID());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final TJPlacement a(String str) {
        TJPlacement tJPlacement;
        synchronized (this.r) {
            tJPlacement = (TJPlacement) this.r.get(str);
            if (tJPlacement != null) {
                String str2 = a;
                TapjoyLog.d(str2, "Returning " + str + " placement: " + tJPlacement.getGUID());
            }
        }
        return tJPlacement;
    }

    /* access modifiers changed from: private */
    public void b(TJPlacement tJPlacement) {
        ga gaVar = this.f;
        String placementName = this.c.getPlacementName();
        String placementType = this.c.getPlacementType();
        String a2 = a();
        gaVar.c = 0;
        gaVar.b = gj.e("PlacementContent.funnel").a().a(VungleActivity.PLACEMENT_EXTRA, (Object) placementName).a("placement_type", (Object) placementType).a("content_type", (Object) a2).a("state", (Object) Integer.valueOf(gaVar.c));
        gaVar.b.c();
        if (!"none".equals(a2)) {
            gaVar.e = gj.e("PlacementContent.ready").a().a(VungleActivity.PLACEMENT_EXTRA, (Object) placementName).a("placement_type", (Object) placementType).a("content_type", (Object) a2);
        }
        if (tJPlacement != null && tJPlacement.getListener() != null) {
            String str = a;
            TapjoyLog.i(str, "Content request delivered successfully for placement " + this.c.getPlacementName() + ", contentAvailable: " + isContentAvailable() + ", mediationAgent: " + this.o);
            tJPlacement.getListener().onRequestSuccess(tJPlacement);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(TapjoyErrorMessage.ErrorType errorType, TJError tJError) {
        a(a("REQUEST"), errorType, tJError);
    }

    /* access modifiers changed from: package-private */
    public final void a(TJPlacement tJPlacement, TapjoyErrorMessage.ErrorType errorType, TJError tJError) {
        String str = a;
        TapjoyLog.e(str, new TapjoyErrorMessage(errorType, "Content request failed for placement " + this.c.getPlacementName() + "; Reason= " + tJError.message));
        if (tJPlacement != null && tJPlacement.getListener() != null) {
            tJPlacement.getListener().onRequestFailure(tJPlacement, tJError);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (!this.x) {
            this.l = true;
            String str = a;
            TapjoyLog.i(str, "Content is ready for placement " + this.c.getPlacementName());
            if (this.g.isPrerendered()) {
                ga gaVar = this.f;
                gj.a aVar = gaVar.b;
                if (aVar != null) {
                    aVar.a("prerendered", (Object) true);
                }
                gj.a aVar2 = gaVar.e;
                if (aVar2 != null) {
                    aVar2.a("prerendered", (Object) true);
                }
            }
            ga gaVar2 = this.f;
            gj.a aVar3 = gaVar2.e;
            if (aVar3 != null) {
                gaVar2.e = null;
                aVar3.b().c();
            }
            TJPlacement a2 = a("REQUEST");
            if (a2 != null && a2.getListener() != null) {
                a2.getListener().onContentReady(a2);
                this.x = true;
            }
        }
    }

    public boolean isLimited() {
        return this.y;
    }

    /* access modifiers changed from: package-private */
    public final String b() {
        if (!this.y) {
            return TapjoyConnectCore.getAppID();
        }
        return TapjoyConnectCore.getLimitedAppID();
    }

    static /* synthetic */ void i(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.t = new fq(tJCorePlacement.c.getPlacementName(), tJCorePlacement.c.getPlacementType());
        tJCorePlacement.g.setAdContentTracker(tJCorePlacement.t);
    }

    static /* synthetic */ void a(TJCorePlacement tJCorePlacement, String str) {
        if (str != null) {
            try {
                String str2 = a;
                TapjoyLog.d(str2, "Disable preload flag is set for placement " + tJCorePlacement.c.getPlacementName());
                tJCorePlacement.c.setRedirectURL(new JSONObject(str).getString(TapjoyConstants.TJC_REDIRECT_URL));
                tJCorePlacement.c.setPreloadDisabled(true);
                tJCorePlacement.c.setHasProgressSpinner(true);
                String str3 = a;
                TapjoyLog.d(str3, "redirect_url:" + tJCorePlacement.c.getRedirectURL());
            } catch (JSONException unused) {
                throw new TapjoyException("TJPlacement request failed, malformed server response");
            }
        } else {
            throw new TapjoyException("TJPlacement request failed due to null response");
        }
    }

    static /* synthetic */ void k(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.k = true;
        tJCorePlacement.b(tJCorePlacement.a("REQUEST"));
    }
}
