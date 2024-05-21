package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ADCCrashReportManager {
    static boolean a = false;
    static final int b = 256;
    private static final String d = "fatalLog.txt";
    private static final String e = "ad_cache_report.txt";
    private static final String f = "com.adcolony.sdk";
    private static final String m = "com.adcolony.crashreports";
    private static final String n = "current.crash";
    private boolean c = false;
    private String g;
    private String h;
    /* access modifiers changed from: private */
    public Thread.UncaughtExceptionHandler i;
    private List<s> j;
    private JSONArray k;
    private JSONObject l;

    public native void initNativeCrashReporter(byte[] bArr);

    ADCCrashReportManager() {
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        if (!this.c) {
            new aa.a().a("Configuring Crash Reporter").a(aa.d);
            if (a) {
                this.i = Thread.getDefaultUncaughtExceptionHandler();
                a aVar = new a();
                new aa.a().a("adding exception handler.").a(aa.b);
                Thread.setDefaultUncaughtExceptionHandler(aVar);
                try {
                    this.h = k();
                    initNativeCrashReporter(this.h.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    new aa.a().a(e2.getMessage()).a(aa.h);
                    this.c = false;
                }
            }
            this.g = a.a().o().e() + d;
            this.j = new ArrayList();
            this.k = y.b();
            d();
            this.c = true;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        if (a) {
            c();
            j();
            i();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void c() {
        try {
            boolean a2 = a.a().j().a(this.g);
            boolean a3 = a.a().j().a(this.h);
            if (a2) {
                StringBuilder a4 = a.a().j().a(this.g, false);
                JSONArray g2 = y.g(y.a(a4.toString()), "crashList");
                for (int i2 = 0; i2 < g2.length(); i2++) {
                    JSONObject jSONObject = g2.getJSONObject(i2);
                    new aa.a().a("Log read from disk: ").a(jSONObject.toString()).a(aa.b);
                    this.j.add(new s().a(jSONObject));
                }
                new aa.a().a("Contents of crash Reporting file: ").a(a4.toString()).a(aa.b);
            } else {
                new aa.a().a("Java Crash log doesn't exist.").a(aa.b);
            }
            if (a3) {
                this.j.add(new s().a(a(a.a().j().b(this.h, true))));
            } else {
                new aa.a().a("Native Crash log doesn't exist.").a(aa.b);
            }
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving logs. Exception Msg: ").a(e2.getMessage()).a(aa.h);
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public synchronized void d() {
        this.l = y.a();
        try {
            String str = a.a().o().e() + e;
            if (a.a().j().a(str)) {
                this.l = y.c(str);
            }
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving ad-cache log. Exception Msg: ").a(e2.getMessage()).a(aa.h);
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public JSONArray e() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public List<s> f() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r0 = com.adcolony.sdk.y.b();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.Throwable r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            com.adcolony.sdk.aa$a r0 = new com.adcolony.sdk.aa$a     // Catch:{ all -> 0x00ac }
            r0.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "Writing crash log..."
            com.adcolony.sdk.aa$a r0 = r0.a((java.lang.String) r1)     // Catch:{ all -> 0x00ac }
            com.adcolony.sdk.aa r1 = com.adcolony.sdk.aa.b     // Catch:{ all -> 0x00ac }
            r0.a((com.adcolony.sdk.aa) r1)     // Catch:{ all -> 0x00ac }
            if (r10 != 0) goto L_0x0015
            monitor-exit(r9)
            return
        L_0x0015:
            org.json.JSONArray r0 = com.adcolony.sdk.y.b()     // Catch:{ all -> 0x00ac }
            java.lang.StackTraceElement[] r1 = r10.getStackTrace()     // Catch:{ all -> 0x00ac }
            java.lang.StackTraceElement r1 = r9.a(r1, r0)     // Catch:{ all -> 0x00ac }
            if (r1 != 0) goto L_0x0040
            java.lang.Throwable r1 = r10.getCause()     // Catch:{ all -> 0x00ac }
            r2 = 0
            if (r1 == 0) goto L_0x003e
            org.json.JSONArray r0 = com.adcolony.sdk.y.b()     // Catch:{ all -> 0x00ac }
            java.lang.StackTraceElement[] r3 = r1.getStackTrace()     // Catch:{ all -> 0x00ac }
            java.lang.StackTraceElement r3 = r9.a(r3, r0)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x003e
            java.lang.String r2 = r1.getMessage()     // Catch:{ all -> 0x00ac }
            r1 = r3
            goto L_0x0044
        L_0x003e:
            r1 = r2
            goto L_0x0044
        L_0x0040:
            java.lang.String r2 = r10.getMessage()     // Catch:{ all -> 0x00ac }
        L_0x0044:
            if (r1 == 0) goto L_0x0097
            if (r2 == 0) goto L_0x0097
            java.lang.String r3 = r1.getClassName()     // Catch:{ all -> 0x00ac }
            java.lang.String r4 = r1.getMethodName()     // Catch:{ all -> 0x00ac }
            int r1 = r1.getLineNumber()     // Catch:{ all -> 0x00ac }
            org.json.JSONObject r5 = com.adcolony.sdk.y.a()     // Catch:{ all -> 0x00ac }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ac }
            java.lang.String r8 = "timestamp"
            java.lang.String r6 = java.lang.Long.toString(r6)     // Catch:{ all -> 0x00ac }
            com.adcolony.sdk.y.a((org.json.JSONObject) r5, (java.lang.String) r8, (java.lang.String) r6)     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = "message"
            com.adcolony.sdk.y.a((org.json.JSONObject) r5, (java.lang.String) r6, (java.lang.String) r2)     // Catch:{ all -> 0x00ac }
            java.lang.String r2 = "sourceFile"
            com.adcolony.sdk.y.a((org.json.JSONObject) r5, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x00ac }
            java.lang.String r2 = "lineNumber"
            com.adcolony.sdk.y.b(r5, r2, r1)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "methodName"
            com.adcolony.sdk.y.a((org.json.JSONObject) r5, (java.lang.String) r1, (java.lang.String) r4)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "stackTrace"
            com.adcolony.sdk.y.a((org.json.JSONObject) r5, (java.lang.String) r1, (org.json.JSONArray) r0)     // Catch:{ all -> 0x00ac }
            r9.d(r5)     // Catch:{ all -> 0x00ac }
            com.adcolony.sdk.aa$a r0 = new com.adcolony.sdk.aa$a     // Catch:{ all -> 0x00ac }
            r0.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "saving to disk..."
            com.adcolony.sdk.aa$a r0 = r0.a((java.lang.String) r1)     // Catch:{ all -> 0x00ac }
            com.adcolony.sdk.aa r1 = com.adcolony.sdk.aa.b     // Catch:{ all -> 0x00ac }
            r0.a((com.adcolony.sdk.aa) r1)     // Catch:{ all -> 0x00ac }
            r9.c(r5)     // Catch:{ all -> 0x00ac }
            r9.h()     // Catch:{ all -> 0x00ac }
        L_0x0097:
            com.adcolony.sdk.aa$a r0 = new com.adcolony.sdk.aa$a     // Catch:{ all -> 0x00ac }
            r0.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "..printing stacktrace"
            com.adcolony.sdk.aa$a r0 = r0.a((java.lang.String) r1)     // Catch:{ all -> 0x00ac }
            com.adcolony.sdk.aa r1 = com.adcolony.sdk.aa.b     // Catch:{ all -> 0x00ac }
            r0.a((com.adcolony.sdk.aa) r1)     // Catch:{ all -> 0x00ac }
            r10.printStackTrace()     // Catch:{ all -> 0x00ac }
            monitor-exit(r9)
            return
        L_0x00ac:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.ADCCrashReportManager.a(java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void g() {
        String str;
        String str2;
        if (a) {
            JSONObject a2 = y.a();
            c s = a.a().s();
            if (s != null) {
                AdColonyInterstitial adColonyInterstitial = a.a().m().c().get(s.b());
                if (adColonyInterstitial == null) {
                    str = "";
                } else {
                    str = adColonyInterstitial.b();
                }
                if (adColonyInterstitial == null) {
                    str2 = "";
                } else {
                    str2 = adColonyInterstitial.c();
                }
                y.a(a2, "isAdActive", true);
                y.a(a2, "activeAdId", str);
                y.a(a2, "active_creative_ad_id", str2);
            } else {
                y.a(a2, "isAdActive", false);
                y.a(a2, "activeAdId", "");
                y.a(a2, "active_creative_ad_id", "");
            }
            try {
                String str3 = a.a().o().e() + "422de421e0f4e019426b9abfd780746bc40740eb";
                if (a.a().j().a(str3)) {
                    JSONObject c2 = y.c(str3);
                    JSONArray a3 = a(c2);
                    JSONArray b2 = b(c2);
                    y.b(a2, "adCacheSize", a3.length());
                    y.a(a2, "listOfCachedAds", a3);
                    y.a(a2, "listOfCreativeAdIds", b2);
                }
            } catch (Exception e2) {
                new aa.a().a("Exception occurred in FileSystem: ").a(e2.toString()).a(aa.f);
            }
            if (e(a2)) {
                y.h(this.l, a.a().o().e() + e);
                new aa.a().a("CrashReport AdCache=").a(this.l.toString()).a(aa.b);
                this.l = a2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public JSONArray a(JSONObject jSONObject) {
        JSONArray b2 = y.b();
        JSONArray g2 = y.g(y.f(jSONObject, TapjoyConstants.TJC_APP_PLACEMENT), "zones");
        for (int i2 = 0; i2 < g2.length(); i2++) {
            JSONArray g3 = y.g(y.d(g2, i2), "ads");
            for (int i3 = 0; i3 < g3.length(); i3++) {
                JSONObject f2 = y.f(y.d(g3, i3), "legacy");
                JSONObject f3 = y.f(y.d(g3, i3), "aurora");
                if (f2.has("uuid")) {
                    y.a(b2, y.b(f2, "uuid"));
                } else {
                    y.a(b2, y.b(f3, "uuid"));
                }
            }
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public JSONArray b(JSONObject jSONObject) {
        JSONArray b2 = y.b();
        JSONArray g2 = y.g(y.f(jSONObject, TapjoyConstants.TJC_APP_PLACEMENT), "zones");
        for (int i2 = 0; i2 < g2.length(); i2++) {
            JSONArray g3 = y.g(y.d(g2, i2), "ads");
            for (int i3 = 0; i3 < g3.length(); i3++) {
                JSONObject f2 = y.f(y.d(g3, i3), "legacy");
                y.f(y.d(g3, i3), "aurora");
                JSONObject f3 = y.f(f2, "meta");
                JSONObject f4 = y.f(f2, "meta");
                if (f3.has("creative_id")) {
                    y.a(b2, y.b(f3, "creative_id"));
                } else {
                    y.a(b2, y.b(f4, "creative_id"));
                }
            }
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void c(JSONObject jSONObject) {
        if (this.k == null) {
            this.k = y.b();
        } else if (this.k.length() == 256) {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 1; i2 < this.k.length(); i2++) {
                jSONArray.put(y.b(this.k, i2));
            }
            this.k = jSONArray;
        }
        this.k.put(jSONObject);
    }

    private void h() {
        JSONObject a2 = y.a();
        y.a(a2, "crashList", this.k);
        new aa.a().a("saving object to ").a(this.g).a(aa.b);
        y.h(a2, this.g);
    }

    private void d(JSONObject jSONObject) {
        if (this.l != null) {
            String b2 = y.b(this.l, "activeAdId");
            boolean d2 = y.d(this.l, "isAdActive");
            int c2 = y.c(this.l, "adCacheSize");
            JSONArray g2 = y.g(this.l, "listOfCachedAds");
            String b3 = y.b(this.l, "active_creative_ad_id");
            JSONArray g3 = y.g(this.l, "listOfCreativeAdIds");
            y.a(jSONObject, "isAdActive", d2);
            y.a(jSONObject, "activeAdId", b2);
            y.b(jSONObject, "adCacheSize", c2);
            y.a(jSONObject, "listOfCachedAds", g2);
            y.a(jSONObject, "active_creative_ad_id", b3);
            y.a(jSONObject, "listOfCreativeAdIds", g3);
        }
    }

    private void i() {
        this.j = new ArrayList();
        this.k = y.b();
        try {
            a.a().j().a(new File(this.g));
            a.a().j().a(new File(this.h));
        } catch (Exception unused) {
            new aa.a().a("Unable to delete log file.").a(aa.f);
        }
    }

    private void j() {
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            new aa.a().a("Writing a crash log to adc-instruments").a(aa.b);
            ac.a(this.j.get(i2));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        new com.adcolony.sdk.aa.a().a("Error occurred while parsing native crash report.").a(com.adcolony.sdk.aa.h);
        r13 = com.adcolony.sdk.y.a();
        r0 = java.lang.System.currentTimeMillis();
        com.adcolony.sdk.y.a(r13, "message", "An error occurred while parsing the native crash report.");
        com.adcolony.sdk.y.a(r13, "timestamp", java.lang.Long.toString(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c4, code lost:
        return r13;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x009b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized org.json.JSONObject a(java.util.List<java.lang.String> r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            org.json.JSONObject r0 = com.adcolony.sdk.y.a()     // Catch:{ Exception -> 0x009b }
            org.json.JSONArray r1 = com.adcolony.sdk.y.b()     // Catch:{ Exception -> 0x009b }
            org.json.JSONArray r2 = com.adcolony.sdk.y.b()     // Catch:{ Exception -> 0x009b }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x0011:
            int r7 = r13.size()     // Catch:{ Exception -> 0x009b }
            if (r4 >= r7) goto L_0x008a
            java.lang.Object r7 = r13.get(r4)     // Catch:{ Exception -> 0x009b }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x009b }
            r8 = 58
            int r8 = r7.indexOf(r8)     // Catch:{ Exception -> 0x009b }
            r9 = 0
            if (r8 < 0) goto L_0x003b
            int r10 = r7.length()     // Catch:{ Exception -> 0x009b }
            if (r8 >= r10) goto L_0x003b
            java.lang.String r9 = r7.substring(r3, r8)     // Catch:{ Exception -> 0x009b }
            int r8 = r8 + 1
            java.lang.String r8 = r7.substring(r8)     // Catch:{ Exception -> 0x009b }
            java.lang.String r8 = r8.trim()     // Catch:{ Exception -> 0x009b }
            goto L_0x003c
        L_0x003b:
            r8 = r9
        L_0x003c:
            r10 = 1
            if (r9 == 0) goto L_0x004d
            java.lang.String r11 = "signalMessage"
            boolean r11 = r9.equals(r11)     // Catch:{ Exception -> 0x009b }
            if (r11 == 0) goto L_0x004d
            java.lang.String r7 = "message"
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r8)     // Catch:{ Exception -> 0x009b }
            goto L_0x0087
        L_0x004d:
            if (r9 == 0) goto L_0x005d
            java.lang.String r11 = "date"
            boolean r11 = r9.equals(r11)     // Catch:{ Exception -> 0x009b }
            if (r11 == 0) goto L_0x005d
            java.lang.String r7 = "timestamp"
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r8)     // Catch:{ Exception -> 0x009b }
            goto L_0x0087
        L_0x005d:
            if (r9 == 0) goto L_0x0069
            java.lang.String r11 = "threadState"
            boolean r11 = r9.equals(r11)     // Catch:{ Exception -> 0x009b }
            if (r11 == 0) goto L_0x0069
            r6 = 1
            goto L_0x0087
        L_0x0069:
            if (r9 == 0) goto L_0x0076
            java.lang.String r11 = "backtrace"
            boolean r11 = r9.equals(r11)     // Catch:{ Exception -> 0x009b }
            if (r11 == 0) goto L_0x0076
            r5 = 1
            r6 = 0
            goto L_0x0087
        L_0x0076:
            if (r6 == 0) goto L_0x007c
            com.adcolony.sdk.y.a((org.json.JSONArray) r1, (java.lang.String) r7)     // Catch:{ Exception -> 0x009b }
            goto L_0x0087
        L_0x007c:
            if (r5 == 0) goto L_0x0082
            com.adcolony.sdk.y.a((org.json.JSONArray) r2, (java.lang.String) r7)     // Catch:{ Exception -> 0x009b }
            goto L_0x0087
        L_0x0082:
            if (r9 == 0) goto L_0x0087
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r9, (java.lang.String) r8)     // Catch:{ Exception -> 0x009b }
        L_0x0087:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x008a:
            java.lang.String r13 = "threadState"
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r13, (org.json.JSONArray) r1)     // Catch:{ Exception -> 0x009b }
            java.lang.String r13 = "stackTrace"
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r13, (org.json.JSONArray) r2)     // Catch:{ Exception -> 0x009b }
            r12.d(r0)     // Catch:{ Exception -> 0x009b }
            monitor-exit(r12)
            return r0
        L_0x0099:
            r13 = move-exception
            goto L_0x00c5
        L_0x009b:
            com.adcolony.sdk.aa$a r13 = new com.adcolony.sdk.aa$a     // Catch:{ all -> 0x0099 }
            r13.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = "Error occurred while parsing native crash report."
            com.adcolony.sdk.aa$a r13 = r13.a((java.lang.String) r0)     // Catch:{ all -> 0x0099 }
            com.adcolony.sdk.aa r0 = com.adcolony.sdk.aa.h     // Catch:{ all -> 0x0099 }
            r13.a((com.adcolony.sdk.aa) r0)     // Catch:{ all -> 0x0099 }
            org.json.JSONObject r13 = com.adcolony.sdk.y.a()     // Catch:{ all -> 0x0099 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "message"
            java.lang.String r3 = "An error occurred while parsing the native crash report."
            com.adcolony.sdk.y.a((org.json.JSONObject) r13, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "timestamp"
            java.lang.String r0 = java.lang.Long.toString(r0)     // Catch:{ all -> 0x0099 }
            com.adcolony.sdk.y.a((org.json.JSONObject) r13, (java.lang.String) r2, (java.lang.String) r0)     // Catch:{ all -> 0x0099 }
            monitor-exit(r12)
            return r13
        L_0x00c5:
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.ADCCrashReportManager.a(java.util.List):org.json.JSONObject");
    }

    private String k() {
        return a.a().o().e() + m + "." + n;
    }

    private boolean e(JSONObject jSONObject) {
        if (!this.l.has("isAdActive") || !this.l.has("activeAdId") || !this.l.has("adCacheSize") || !this.l.has("listOfCachedAds")) {
            return true;
        }
        boolean z = y.d(this.l, "isAdActive") != y.d(jSONObject, "isAdActive");
        boolean z2 = !y.b(this.l, "activeAdId").equals(y.b(jSONObject, "activeAdId"));
        boolean z3 = y.c(this.l, "adCacheSize") != y.c(jSONObject, "adCacheSize");
        boolean z4 = !y.g(this.l, "listOfCachedAds").equals(y.g(jSONObject, "listOfCachedAds"));
        if (z || z2 || z3 || z4) {
            return true;
        }
        return false;
    }

    private StackTraceElement a(StackTraceElement[] stackTraceElementArr, JSONArray jSONArray) {
        StackTraceElement stackTraceElement = null;
        for (StackTraceElement stackTraceElement2 : stackTraceElementArr) {
            jSONArray.put(stackTraceElement2.toString());
            String className = stackTraceElement2.getClassName();
            new aa.a().a("CRASH - classname=").a(className).a(aa.b);
            if (className.contains(f) && stackTraceElement == null) {
                stackTraceElement = stackTraceElement2;
            }
        }
        return stackTraceElement;
    }

    private class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            new aa.a().a("Caught exception.").a(aa.b);
            ADCCrashReportManager.this.a(th);
            ADCCrashReportManager.this.i.uncaughtException(thread, th);
        }
    }
}
