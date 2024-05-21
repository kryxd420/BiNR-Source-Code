package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.ez;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class hd {
    private static final hd q;
    private static hd r;
    private static Handler w;
    private static File x;
    public final hl a = new hl(this);
    public hm b;
    public boolean c = false;
    public String d = null;
    public Context e;
    public hg f;
    public hc g;
    public hq h;
    public hb i;
    public String j;
    public boolean k;
    public String l;
    public String m;
    public boolean n = false;
    public String o;
    public he p = he.a((gs) null);
    private boolean s = false;
    private boolean t = false;
    private String u;
    private String v;

    static {
        hd hdVar = new hd();
        q = hdVar;
        r = hdVar;
    }

    public static hd a() {
        return r;
    }

    private hd() {
    }

    public final synchronized void b(Context context) {
        if (this.e == null) {
            Context applicationContext = context.getApplicationContext();
            this.e = applicationContext;
            ge.a().a(applicationContext);
            this.f = hg.a(applicationContext);
            File file = new File(c(applicationContext), "events2");
            if (this.i == null) {
                this.i = new hb(file);
            }
            this.g = new hc(this.f, this.i);
            this.h = new hq(this.g);
            this.b = new hm(applicationContext);
            gj.a(new gl(new File(c(applicationContext), "usages"), this.g));
            hx hxVar = hx.a;
            hxVar.b = applicationContext.getApplicationContext();
            hxVar.c = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2E", 0);
            hxVar.d = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2U", 0);
            hxVar.a();
        }
    }

    public final ff a(boolean z) {
        if (z) {
            this.f.a();
        }
        return this.f.b();
    }

    public final synchronized void b() {
        if (this.k) {
            hf.b(this.e).e(this.d);
            a((String) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(@javax.annotation.Nullable final java.lang.String r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.k     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x0040
            if (r6 != 0) goto L_0x000d
            java.lang.String r0 = r5.o     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x000d
            java.lang.String r6 = r5.o     // Catch:{ all -> 0x0046 }
        L_0x000d:
            r0 = 0
            r5.o = r0     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0044
            com.tapjoy.internal.hg r0 = r5.f     // Catch:{ all -> 0x0046 }
            com.tapjoy.internal.ff r0 = r0.b()     // Catch:{ all -> 0x0046 }
            java.lang.String r1 = "GCM registration id of device {} updated for sender {}: {}"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0046 }
            r3 = 0
            com.tapjoy.internal.fe r4 = r0.d     // Catch:{ all -> 0x0046 }
            java.lang.String r4 = r4.h     // Catch:{ all -> 0x0046 }
            r2[r3] = r4     // Catch:{ all -> 0x0046 }
            r3 = 1
            java.lang.String r4 = r5.d     // Catch:{ all -> 0x0046 }
            r2[r3] = r4     // Catch:{ all -> 0x0046 }
            r3 = 2
            r2[r3] = r6     // Catch:{ all -> 0x0046 }
            com.tapjoy.internal.ha.a((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0046 }
            com.tapjoy.internal.ip r1 = new com.tapjoy.internal.ip     // Catch:{ all -> 0x0046 }
            r1.<init>(r0, r6)     // Catch:{ all -> 0x0046 }
            com.tapjoy.internal.hd$1 r0 = new com.tapjoy.internal.hd$1     // Catch:{ all -> 0x0046 }
            r0.<init>(r6)     // Catch:{ all -> 0x0046 }
            java.util.concurrent.ExecutorService r6 = com.tapjoy.internal.cd.a     // Catch:{ all -> 0x0046 }
            r1.a((com.tapjoy.internal.ci) r0, (java.util.concurrent.ExecutorService) r6)     // Catch:{ all -> 0x0046 }
            monitor-exit(r5)
            return
        L_0x0040:
            if (r6 == 0) goto L_0x0044
            r5.o = r6     // Catch:{ all -> 0x0046 }
        L_0x0044:
            monitor-exit(r5)
            return
        L_0x0046:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hd.a(java.lang.String):void");
    }

    public final void b(String str) {
        hc hcVar = this.g;
        ez.a a2 = hcVar.a(fc.APP, "push_ignore");
        a2.s = new fg((String) null, (String) null, str);
        hcVar.a(a2);
    }

    public final boolean a(Context context, String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        b(context);
        if (!this.f.a(str, currentTimeMillis, z)) {
            return false;
        }
        hc hcVar = this.g;
        ez.a a2 = hcVar.a(fc.APP, "push_show");
        a2.s = new fg((String) null, (String) null, str);
        hcVar.a(a2);
        return true;
    }

    public static void a(GLSurfaceView gLSurfaceView) {
        if (ha.a((Object) gLSurfaceView, "setGLSurfaceView: The given GLSurfaceView was null")) {
            gv.a(gLSurfaceView);
        }
    }

    public final Set c() {
        if (!d("getUserTags")) {
            return new HashSet();
        }
        return this.f.e();
    }

    public final void a(Set set) {
        if (d("setUserTags")) {
            if (set != null && !set.isEmpty()) {
                HashSet hashSet = new HashSet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (str != null) {
                        String trim = str.trim();
                        if (!trim.isEmpty() && trim.length() <= 200) {
                            hashSet.add(trim);
                            if (hashSet.size() >= 200) {
                                break;
                            }
                        }
                    }
                }
                set = hashSet;
            }
            hg hgVar = this.f;
            synchronized (hgVar) {
                if (set != null) {
                    try {
                        if (!set.isEmpty()) {
                            hgVar.c.z.a(Base64.encodeToString(fk.c.b(new fk(new ArrayList(set))), 2));
                            hgVar.b.A.clear();
                            hgVar.b.A.addAll(set);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                hgVar.c.z.c();
                hgVar.b.A.clear();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003d A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0061 A[SYNTHETIC, Splitter:B:39:0x0061] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(android.content.Context r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.k     // Catch:{ all -> 0x0119 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)
            return
        L_0x0007:
            r3.b((android.content.Context) r4)     // Catch:{ all -> 0x0119 }
            android.content.Context r4 = r3.e     // Catch:{ all -> 0x0119 }
            r0 = 0
            r1 = 1
            if (r4 == 0) goto L_0x0012
            r4 = 1
            goto L_0x0013
        L_0x0012:
            r4 = 0
        L_0x0013:
            java.lang.String r2 = "The given context was null"
            boolean r4 = com.tapjoy.internal.ha.a((boolean) r4, (java.lang.String) r2)     // Catch:{ all -> 0x0119 }
            if (r4 != 0) goto L_0x001d
            monitor-exit(r3)
            return
        L_0x001d:
            if (r8 == 0) goto L_0x0031
            int r4 = r8.length()     // Catch:{ all -> 0x0119 }
            r2 = 24
            if (r4 != r2) goto L_0x0031
            java.lang.String r4 = "[0-9a-f]{24}"
            boolean r4 = r8.matches(r4)     // Catch:{ all -> 0x0119 }
            if (r4 == 0) goto L_0x0031
            r4 = 1
            goto L_0x003b
        L_0x0031:
            java.lang.String r4 = "Invalid App ID: {}"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0119 }
            r2[r0] = r8     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.ha.b(r4, r2)     // Catch:{ all -> 0x0119 }
            r4 = 0
        L_0x003b:
            if (r4 != 0) goto L_0x003f
            monitor-exit(r3)
            return
        L_0x003f:
            if (r9 == 0) goto L_0x0053
            int r4 = r9.length()     // Catch:{ all -> 0x0119 }
            r2 = 20
            if (r4 != r2) goto L_0x0053
            java.lang.String r4 = "[0-9A-Za-z\\-_]{20}"
            boolean r4 = r9.matches(r4)     // Catch:{ all -> 0x0119 }
            if (r4 == 0) goto L_0x0053
            r4 = 1
            goto L_0x005d
        L_0x0053:
            java.lang.String r4 = "Invalid App Key: {}"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0119 }
            r2[r0] = r9     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.ha.b(r4, r2)     // Catch:{ all -> 0x0119 }
            r4 = 0
        L_0x005d:
            if (r4 != 0) goto L_0x0061
            monitor-exit(r3)
            return
        L_0x0061:
            r3.l = r5     // Catch:{ all -> 0x0119 }
            r3.m = r6     // Catch:{ all -> 0x0119 }
            r3.u = r8     // Catch:{ all -> 0x0119 }
            r3.v = r9     // Catch:{ all -> 0x0119 }
            java.net.URL r4 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0112 }
            r4.<init>(r7)     // Catch:{ MalformedURLException -> 0x0112 }
            java.lang.String r5 = "TapjoySDK"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0119 }
            r9.<init>()     // Catch:{ all -> 0x0119 }
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = " "
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            r9.append(r6)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = " ("
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = android.os.Build.MODEL     // Catch:{ all -> 0x0119 }
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = "; Android "
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0119 }
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = "; "
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.util.Locale r5 = java.util.Locale.getDefault()     // Catch:{ all -> 0x0119 }
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = ")"
            r9.append(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = r9.toString()     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.ch r6 = new com.tapjoy.internal.ch     // Catch:{ all -> 0x0119 }
            r6.<init>(r5, r4)     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.cd.b = r6     // Catch:{ all -> 0x0119 }
            java.util.concurrent.ExecutorService r4 = java.util.concurrent.Executors.newCachedThreadPool()     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.cd.a = r4     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.hb r4 = r3.i     // Catch:{ all -> 0x0119 }
            r4.b = r6     // Catch:{ all -> 0x0119 }
            r4.a()     // Catch:{ all -> 0x0119 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ all -> 0x0119 }
            r4[r0] = r7     // Catch:{ all -> 0x0119 }
            r3.k = r1     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.hh r4 = new com.tapjoy.internal.hh     // Catch:{ all -> 0x0119 }
            android.content.Context r5 = r3.e     // Catch:{ all -> 0x0119 }
            java.io.File r5 = d((android.content.Context) r5)     // Catch:{ all -> 0x0119 }
            r4.<init>(r5)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = r4.b()     // Catch:{ all -> 0x0119 }
            if (r5 == 0) goto L_0x00d5
            goto L_0x00d6
        L_0x00d5:
            r1 = 0
        L_0x00d6:
            if (r1 != 0) goto L_0x00eb
            boolean r4 = r4.a()     // Catch:{ all -> 0x0119 }
            if (r4 == 0) goto L_0x00eb
            com.tapjoy.internal.hc r4 = r3.g     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.fc r5 = com.tapjoy.internal.fc.APP     // Catch:{ all -> 0x0119 }
            java.lang.String r6 = "install"
            com.tapjoy.internal.ez$a r5 = r4.a(r5, r6)     // Catch:{ all -> 0x0119 }
            r4.a(r5)     // Catch:{ all -> 0x0119 }
        L_0x00eb:
            com.tapjoy.internal.hg r4 = r3.f     // Catch:{ all -> 0x0119 }
            boolean r5 = com.tapjoy.internal.ju.c(r8)     // Catch:{ all -> 0x0119 }
            if (r5 != 0) goto L_0x010d
            com.tapjoy.internal.hn r5 = r4.c     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.o r5 = r5.D     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = r5.a()     // Catch:{ all -> 0x0119 }
            boolean r5 = r8.equals(r5)     // Catch:{ all -> 0x0119 }
            if (r5 != 0) goto L_0x010d
            com.tapjoy.internal.hn r5 = r4.c     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.o r5 = r5.D     // Catch:{ all -> 0x0119 }
            r5.a(r8)     // Catch:{ all -> 0x0119 }
            com.tapjoy.internal.hn r4 = r4.c     // Catch:{ all -> 0x0119 }
            r4.a((boolean) r0)     // Catch:{ all -> 0x0119 }
        L_0x010d:
            r3.b()     // Catch:{ all -> 0x0119 }
            monitor-exit(r3)
            return
        L_0x0112:
            r4 = move-exception
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0119 }
            r5.<init>(r4)     // Catch:{ all -> 0x0119 }
            throw r5     // Catch:{ all -> 0x0119 }
        L_0x0119:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hd.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public final boolean c(String str) {
        if ((this.k || this.j != null) && this.e != null) {
            return true;
        }
        if (!ha.a) {
            return false;
        }
        ha.b(str + ": Should be called after initializing the SDK");
        return false;
    }

    public final boolean d(String str) {
        if (this.e != null) {
            return true;
        }
        if (!ha.a) {
            return false;
        }
        ha.b(str + ": Should be called after initializing the SDK");
        return false;
    }

    public final boolean d() {
        return this.h != null && this.h.b.get();
    }

    public final boolean e() {
        boolean z;
        hq hqVar = this.h;
        if (hqVar.c != null) {
            hqVar.c.cancel(false);
            hqVar.c = null;
        }
        if (hqVar.b.compareAndSet(false, true)) {
            ha.a("New session started");
            hc hcVar = hqVar.a;
            fg d2 = hcVar.a.d();
            hg hgVar = hcVar.a;
            synchronized (hgVar) {
                int b2 = hgVar.c.h.b() + 1;
                hgVar.c.h.a(b2);
                hgVar.b.h = Integer.valueOf(b2);
            }
            ez.a a2 = hcVar.a(fc.APP, TapdaqPlacement.TDPTagBOOTUP);
            hcVar.c = SystemClock.elapsedRealtime();
            if (d2 != null) {
                a2.s = d2;
            }
            hcVar.a(a2);
            fw.c.notifyObservers();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        hl hlVar = this.a;
        synchronized (hlVar) {
            hlVar.b = null;
        }
        hx.a.a();
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void a(Map map) {
        hc hcVar = this.g;
        ez.a a2 = hcVar.a(fc.CAMPAIGN, TMStatsManager.IMPRESSION_EVENT);
        if (map != null) {
            a2.r = bk.a((Object) map);
        }
        hcVar.a(a2);
    }

    /* access modifiers changed from: package-private */
    public final void a(Map map, long j2) {
        hc hcVar = this.g;
        ez.a a2 = hcVar.a(fc.CAMPAIGN, "view");
        a2.i = Long.valueOf(j2);
        if (map != null) {
            a2.r = bk.a((Object) map);
        }
        hcVar.a(a2);
    }

    /* access modifiers changed from: package-private */
    public final void a(Map map, String str) {
        hc hcVar = this.g;
        ez.a a2 = hcVar.a(fc.CAMPAIGN, TMStatsManager.CLICK_EVENT);
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put("region", str);
        a2.r = bk.a((Object) linkedHashMap);
        hcVar.a(a2);
    }

    public static synchronized void a(Runnable runnable) {
        synchronized (hd.class) {
            if (w == null) {
                w = new Handler(Looper.getMainLooper());
            }
            w.post(runnable);
        }
    }

    public static synchronized File c(Context context) {
        File file;
        synchronized (hd.class) {
            if (x == null) {
                x = context.getDir("fiverocks", 0);
            }
            file = x;
        }
        return file;
    }

    static File d(Context context) {
        return new File(c(context), "install");
    }

    public static String a(Context context, Intent intent) {
        String a2 = d.a(intent);
        if (a2 != null) {
            hd hdVar = r;
            hdVar.b(context);
            if (ju.c(hdVar.f.c()) || intent.getBooleanExtra("fiverocks:force", false)) {
                hg hgVar = hdVar.f;
                synchronized (hgVar) {
                    hgVar.c.d.a(a2);
                    hgVar.b.d = a2;
                }
                if (a2.length() > 0) {
                    hc hcVar = hdVar.g;
                    hcVar.a(hcVar.a(fc.APP, TapjoyConstants.TJC_REFERRER));
                }
            }
        }
        return a2;
    }

    public static hd a(Context context) {
        hd hdVar = r;
        hdVar.b(context);
        return hdVar;
    }
}
