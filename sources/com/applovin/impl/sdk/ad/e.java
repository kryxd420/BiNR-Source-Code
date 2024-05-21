package com.applovin.impl.sdk.ad;

import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    private final j a;
    private final p b;
    private LinkedHashSet<d> c;
    private final Object d = new Object();
    private volatile boolean e;

    public e(j jVar) {
        this.a = jVar;
        this.b = jVar.v();
        this.c = c();
    }

    private LinkedHashSet<d> b(JSONArray jSONArray) {
        LinkedHashSet<d> linkedHashSet = new LinkedHashSet<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject a2 = g.a(jSONArray, i, (JSONObject) null, this.a);
            p pVar = this.b;
            pVar.a("AdZoneManager", "Loading zone: " + g.a(a2, this.a) + "...");
            linkedHashSet.add(d.a(g.a(a2, "id", (String) null, this.a), a2, this.a));
        }
        return linkedHashSet;
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private java.util.LinkedHashSet<com.applovin.impl.sdk.ad.d> c() {
        /*
            r6 = this;
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            com.applovin.impl.sdk.j r1 = r6.a     // Catch:{ Throwable -> 0x0074 }
            com.applovin.impl.sdk.b.d<java.lang.String> r2 = com.applovin.impl.sdk.b.d.m     // Catch:{ Throwable -> 0x0074 }
            java.lang.Object r1 = r1.a(r2)     // Catch:{ Throwable -> 0x0074 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0074 }
            boolean r2 = com.applovin.impl.sdk.e.k.b(r1)     // Catch:{ Throwable -> 0x0074 }
            if (r2 == 0) goto L_0x003e
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Throwable -> 0x0074 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0074 }
            int r3 = r2.length()     // Catch:{ Throwable -> 0x0074 }
            if (r3 <= 0) goto L_0x0026
            java.util.LinkedHashSet r1 = r6.b(r2)     // Catch:{ Throwable -> 0x0074 }
            r0 = r1
            goto L_0x003e
        L_0x0026:
            com.applovin.impl.sdk.p r2 = r6.b     // Catch:{ Throwable -> 0x0074 }
            java.lang.String r3 = "AdZoneManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0074 }
            r4.<init>()     // Catch:{ Throwable -> 0x0074 }
            java.lang.String r5 = "Unable to inflate json string: "
            r4.append(r5)     // Catch:{ Throwable -> 0x0074 }
            r4.append(r1)     // Catch:{ Throwable -> 0x0074 }
            java.lang.String r1 = r4.toString()     // Catch:{ Throwable -> 0x0074 }
            r2.a(r3, r1)     // Catch:{ Throwable -> 0x0074 }
        L_0x003e:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x00b2
            com.applovin.impl.sdk.p r1 = r6.b
            java.lang.String r2 = "AdZoneManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Retrieved persisted zones: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r1.a(r2, r3)
            java.util.Iterator r1 = r0.iterator()
        L_0x0060:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00b2
            java.lang.Object r2 = r1.next()
            com.applovin.impl.sdk.ad.d r2 = (com.applovin.impl.sdk.ad.d) r2
            com.applovin.impl.sdk.j r3 = r6.a
            r2.a(r3)
            goto L_0x0060
        L_0x0072:
            r1 = move-exception
            goto L_0x00b3
        L_0x0074:
            r1 = move-exception
            com.applovin.impl.sdk.p r2 = r6.b     // Catch:{ all -> 0x0072 }
            java.lang.String r3 = "AdZoneManager"
            java.lang.String r4 = "Encountered error retrieving persisted zones"
            r2.b(r3, r4, r1)     // Catch:{ all -> 0x0072 }
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x00b2
            com.applovin.impl.sdk.p r1 = r6.b
            java.lang.String r2 = "AdZoneManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Retrieved persisted zones: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r1.a(r2, r3)
            java.util.Iterator r1 = r0.iterator()
        L_0x00a0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00b2
            java.lang.Object r2 = r1.next()
            com.applovin.impl.sdk.ad.d r2 = (com.applovin.impl.sdk.ad.d) r2
            com.applovin.impl.sdk.j r3 = r6.a
            r2.a(r3)
            goto L_0x00a0
        L_0x00b2:
            return r0
        L_0x00b3:
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x00e7
            com.applovin.impl.sdk.p r2 = r6.b
            java.lang.String r3 = "AdZoneManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Retrieved persisted zones: "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r2.a(r3, r4)
            java.util.Iterator r0 = r0.iterator()
        L_0x00d5:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00e7
            java.lang.Object r2 = r0.next()
            com.applovin.impl.sdk.ad.d r2 = (com.applovin.impl.sdk.ad.d) r2
            com.applovin.impl.sdk.j r3 = r6.a
            r2.a(r3)
            goto L_0x00d5
        L_0x00e7:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ad.e.c():java.util.LinkedHashSet");
    }

    private void c(JSONArray jSONArray) {
        if (((Boolean) this.a.a(b.dV)).booleanValue()) {
            this.b.a("AdZoneManager", "Persisting zones...");
            this.a.a(d.m, jSONArray.toString());
        }
    }

    public LinkedHashSet<d> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new LinkedHashSet<>();
        }
        LinkedHashSet<d> linkedHashSet = new LinkedHashSet<>(jSONArray.length());
        LinkedHashSet<d> linkedHashSet2 = null;
        synchronized (this.d) {
            if (!this.e) {
                p pVar = this.b;
                pVar.a("AdZoneManager", "Found " + jSONArray.length() + " zone(s)...");
                linkedHashSet2 = b(jSONArray);
                linkedHashSet = new LinkedHashSet<>(linkedHashSet2);
                linkedHashSet.removeAll(this.c);
                this.c = linkedHashSet2;
                this.e = true;
            }
        }
        if (linkedHashSet2 != null) {
            c(jSONArray);
            this.b.a("AdZoneManager", "Finished loading zones");
        }
        return linkedHashSet;
    }

    public boolean a() {
        return this.e;
    }

    public boolean a(d dVar) {
        boolean contains;
        synchronized (this.d) {
            contains = this.c.contains(dVar);
        }
        return contains;
    }

    public LinkedHashSet<d> b() {
        LinkedHashSet<d> linkedHashSet;
        synchronized (this.d) {
            linkedHashSet = this.c;
        }
        return linkedHashSet;
    }
}
