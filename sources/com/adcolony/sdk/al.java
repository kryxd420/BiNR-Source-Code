package com.adcolony.sdk;

import android.content.Context;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import com.adcolony.sdk.ab;
import com.applovin.sdk.AppLovinEventTypes;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class al {
    static final String a = "adcolony_android";
    static final String b = "adcolony_fatal_reports";
    z c;
    ScheduledExecutorService d;
    List<ab> e = new ArrayList();
    List<ab> f = new ArrayList();
    HashMap<String, Object> g;
    private x h = new x(a, "3.3.5", "Production");
    private x i = new x(b, "3.3.5", "Production");

    al(z zVar, ScheduledExecutorService scheduledExecutorService, HashMap<String, Object> hashMap) {
        this.c = zVar;
        this.d = scheduledExecutorService;
        this.g = hashMap;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(String str) {
        this.g.put("controllerVersion", str);
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(String str) {
        this.g.put("sessionId", str);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(long j, TimeUnit timeUnit) {
        try {
            if (!this.d.isShutdown() && !this.d.isTerminated()) {
                this.d.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        al.this.b();
                    }
                }, j, j, timeUnit);
            }
        } catch (RuntimeException unused) {
            Log.e("ADCLogError", "Internal error when submitting remote log to executor service");
        }
        return;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r4.d.shutdownNow();
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0040 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.concurrent.ScheduledExecutorService r0 = r4.d     // Catch:{ all -> 0x004e }
            r0.shutdown()     // Catch:{ all -> 0x004e }
            java.util.concurrent.ScheduledExecutorService r0 = r4.d     // Catch:{ InterruptedException -> 0x0040 }
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0040 }
            r2 = 1
            boolean r0 = r0.awaitTermination(r2, r1)     // Catch:{ InterruptedException -> 0x0040 }
            if (r0 != 0) goto L_0x004c
            java.util.concurrent.ScheduledExecutorService r0 = r4.d     // Catch:{ InterruptedException -> 0x0040 }
            r0.shutdownNow()     // Catch:{ InterruptedException -> 0x0040 }
            java.util.concurrent.ScheduledExecutorService r0 = r4.d     // Catch:{ InterruptedException -> 0x0040 }
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0040 }
            boolean r0 = r0.awaitTermination(r2, r1)     // Catch:{ InterruptedException -> 0x0040 }
            if (r0 != 0) goto L_0x004c
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ InterruptedException -> 0x0040 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0040 }
            r1.<init>()     // Catch:{ InterruptedException -> 0x0040 }
            java.lang.Class r2 = r4.getClass()     // Catch:{ InterruptedException -> 0x0040 }
            java.lang.String r2 = r2.getSimpleName()     // Catch:{ InterruptedException -> 0x0040 }
            r1.append(r2)     // Catch:{ InterruptedException -> 0x0040 }
            java.lang.String r2 = ": ScheduledExecutorService did not terminate"
            r1.append(r2)     // Catch:{ InterruptedException -> 0x0040 }
            java.lang.String r1 = r1.toString()     // Catch:{ InterruptedException -> 0x0040 }
            r0.println(r1)     // Catch:{ InterruptedException -> 0x0040 }
            goto L_0x004c
        L_0x0040:
            java.util.concurrent.ScheduledExecutorService r0 = r4.d     // Catch:{ all -> 0x004e }
            r0.shutdownNow()     // Catch:{ all -> 0x004e }
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x004e }
            r0.interrupt()     // Catch:{ all -> 0x004e }
        L_0x004c:
            monitor-exit(r4)
            return
        L_0x004e:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.al.a():void");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    synchronized void b() {
        /*
            r2 = this;
            monitor-enter(r2)
            monitor-enter(r2)     // Catch:{ all -> 0x003e }
            java.util.List<com.adcolony.sdk.ab> r0 = r2.e     // Catch:{ IOException | JSONException -> 0x0039 }
            int r0 = r0.size()     // Catch:{ IOException | JSONException -> 0x0039 }
            if (r0 <= 0) goto L_0x001c
            com.adcolony.sdk.x r0 = r2.h     // Catch:{ IOException | JSONException -> 0x0039 }
            java.util.List<com.adcolony.sdk.ab> r1 = r2.e     // Catch:{ IOException | JSONException -> 0x0039 }
            java.lang.String r0 = r2.a((com.adcolony.sdk.x) r0, (java.util.List<com.adcolony.sdk.ab>) r1)     // Catch:{ IOException | JSONException -> 0x0039 }
            com.adcolony.sdk.z r1 = r2.c     // Catch:{ IOException | JSONException -> 0x0039 }
            r1.a(r0)     // Catch:{ IOException | JSONException -> 0x0039 }
            java.util.List<com.adcolony.sdk.ab> r0 = r2.e     // Catch:{ IOException | JSONException -> 0x0039 }
            r0.clear()     // Catch:{ IOException | JSONException -> 0x0039 }
        L_0x001c:
            java.util.List<com.adcolony.sdk.ab> r0 = r2.f     // Catch:{ IOException | JSONException -> 0x0039 }
            int r0 = r0.size()     // Catch:{ IOException | JSONException -> 0x0039 }
            if (r0 <= 0) goto L_0x0039
            com.adcolony.sdk.x r0 = r2.i     // Catch:{ IOException | JSONException -> 0x0039 }
            java.util.List<com.adcolony.sdk.ab> r1 = r2.f     // Catch:{ IOException | JSONException -> 0x0039 }
            java.lang.String r0 = r2.a((com.adcolony.sdk.x) r0, (java.util.List<com.adcolony.sdk.ab>) r1)     // Catch:{ IOException | JSONException -> 0x0039 }
            com.adcolony.sdk.z r1 = r2.c     // Catch:{ IOException | JSONException -> 0x0039 }
            r1.a(r0)     // Catch:{ IOException | JSONException -> 0x0039 }
            java.util.List<com.adcolony.sdk.ab> r0 = r2.f     // Catch:{ IOException | JSONException -> 0x0039 }
            r0.clear()     // Catch:{ IOException | JSONException -> 0x0039 }
            goto L_0x0039
        L_0x0037:
            r0 = move-exception
            goto L_0x003c
        L_0x0039:
            monitor-exit(r2)     // Catch:{ all -> 0x0037 }
            monitor-exit(r2)
            return
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x0037 }
            throw r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.al.b():void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void c(String str) {
        a(new ab.a().a(3).a(this.h).a(str).a());
    }

    /* access modifiers changed from: package-private */
    public synchronized void d(String str) {
        a(new ab.a().a(2).a(this.h).a(str).a());
    }

    /* access modifiers changed from: package-private */
    public synchronized void e(String str) {
        a(new ab.a().a(1).a(this.h).a(str).a());
    }

    /* access modifiers changed from: package-private */
    public synchronized void f(String str) {
        a(new ab.a().a(0).a(this.h).a(str).a());
    }

    /* access modifiers changed from: package-private */
    public void a(s sVar) {
        sVar.a(this.i);
        sVar.a(-1);
        b((ab) sVar);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(final ab abVar) {
        try {
            if (!this.d.isShutdown() && !this.d.isTerminated()) {
                this.d.submit(new Runnable() {
                    public void run() {
                        al.this.e.add(abVar);
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
            Log.e("ADCLogError", "Internal error when submitting remote log to executor service");
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(ab abVar) {
        this.f.add(abVar);
    }

    /* access modifiers changed from: package-private */
    public String a(x xVar, List<ab> list) throws IOException, JSONException {
        String c2 = a.a().c.c();
        String str = this.g.get("advertiserId") != null ? (String) this.g.get("advertiserId") : EnvironmentCompat.MEDIA_UNKNOWN;
        if (c2 != null && c2.length() > 0 && !c2.equals(str)) {
            this.g.put("advertiserId", c2);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("index", xVar.b());
        jSONObject.put("environment", xVar.d());
        jSONObject.put(MediationMetaData.KEY_VERSION, xVar.c());
        JSONArray jSONArray = new JSONArray();
        for (ab c3 : list) {
            jSONArray.put(c(c3));
        }
        jSONObject.put("logs", jSONArray);
        return jSONObject.toString();
    }

    private synchronized JSONObject c(ab abVar) throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject(this.g);
        jSONObject.put("environment", abVar.f().d());
        jSONObject.put(AppLovinEventTypes.USER_COMPLETED_LEVEL, abVar.b());
        jSONObject.put("message", abVar.d());
        jSONObject.put("clientTimestamp", abVar.e());
        JSONObject mediationInfo = a.a().d().getMediationInfo();
        JSONObject pluginInfo = a.a().d().getPluginInfo();
        double a2 = a.a().n().a((Context) a.c());
        jSONObject.put("mediation_network", y.b(mediationInfo, "name"));
        jSONObject.put("mediation_network_version", y.b(mediationInfo, MediationMetaData.KEY_VERSION));
        jSONObject.put(TapjoyConstants.TJC_PLUGIN, y.b(pluginInfo, "name"));
        jSONObject.put("plugin_version", y.b(pluginInfo, MediationMetaData.KEY_VERSION));
        jSONObject.put("batteryInfo", a2);
        if (abVar instanceof s) {
            jSONObject = y.a(jSONObject, ((s) abVar).a());
            jSONObject.put(TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        }
        return jSONObject;
    }
}
