package com.applovin.impl.sdk.network;

import android.content.SharedPreferences;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONObject;

public class d {
    private final j a;
    /* access modifiers changed from: private */
    public final p b;
    private ArrayList<e> c;
    private ArrayList<e> d;
    private final Object e;
    private final SharedPreferences f;

    public d(j jVar) {
        if (jVar != null) {
            this.a = jVar;
            this.b = jVar.v();
            this.f = jVar.x().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
            this.e = new Object();
            this.c = c();
            this.d = new ArrayList<>();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private void b(e eVar) {
        synchronized (this.e) {
            if (this.c.size() < ((Integer) this.a.a(b.dB)).intValue()) {
                this.c.add(eVar);
                d();
                p pVar = this.b;
                pVar.a("PersistentPostbackManager", "Enqueued postback: " + eVar);
            } else {
                p pVar2 = this.b;
                pVar2.c("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only." + eVar);
            }
        }
    }

    private ArrayList<e> c() {
        Set<String> set = (Set) this.a.b(com.applovin.impl.sdk.b.d.h, new LinkedHashSet(0), this.f);
        ArrayList<e> arrayList = new ArrayList<>(Math.max(1, set.size()));
        int intValue = ((Integer) this.a.a(b.dC)).intValue();
        p pVar = this.b;
        pVar.a("PersistentPostbackManager", "Deserializing " + set.size() + " postback(s).");
        for (String str : set) {
            try {
                e eVar = new e(new JSONObject(str), this.a);
                if (!((Boolean) this.a.a(b.dD)).booleanValue() || eVar.f() >= intValue) {
                    if (eVar.f() <= intValue) {
                        p pVar2 = this.b;
                        pVar2.a("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: " + eVar);
                    }
                }
                arrayList.add(eVar);
            } catch (Throwable th) {
                p pVar3 = this.b;
                pVar3.b("PersistentPostbackManager", "Unable to deserialize postback request from json: " + str, th);
            }
        }
        p pVar4 = this.b;
        pVar4.a("PersistentPostbackManager", "Successfully loaded postback queue with " + arrayList.size() + " postback(s).");
        return arrayList;
    }

    private void c(final e eVar) {
        p pVar = this.b;
        pVar.a("PersistentPostbackManager", "Preparing to submit postback..." + eVar);
        if (this.a.c()) {
            this.b.a("PersistentPostbackManager", "Skipping postback dispatch because SDK is still initializing - postback will be dispatched afterwards");
            return;
        }
        synchronized (this.e) {
            eVar.g();
            d();
        }
        int intValue = ((Integer) this.a.a(b.dC)).intValue();
        if (eVar.f() > intValue) {
            p pVar2 = this.b;
            pVar2.c("PersistentPostbackManager", "Exceeded maximum persisted attempt count of " + intValue + ". Dequeuing postback: " + eVar);
            d(eVar);
            return;
        }
        this.a.K().dispatchPostbackRequest(f.b(this.a).a(eVar.a()).a(eVar.d()).c(eVar.b()).a(eVar.c() != null ? new JSONObject(eVar.c()) : null).a(eVar.e()).a(), new AppLovinPostbackListener() {
            public void onPostbackFailure(String str, int i) {
                p a2 = d.this.b;
                a2.b("PersistentPostbackManager", "Failed to submit postback with errorCode " + i + ". Will retry later...  Postback: " + eVar);
                d.this.e(eVar);
            }

            public void onPostbackSuccess(String str) {
                d.this.d(eVar);
                p a2 = d.this.b;
                a2.a("PersistentPostbackManager", "Successfully submitted postback: " + eVar);
                d.this.b();
            }
        });
    }

    private void d() {
        String str;
        String str2;
        p pVar;
        if (e.b()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.c.size());
            Iterator<e> it = this.c.iterator();
            while (it.hasNext()) {
                try {
                    linkedHashSet.add(it.next().i().toString());
                } catch (Throwable th) {
                    this.b.b("PersistentPostbackManager", "Unable to serialize postback request to JSON.", th);
                }
            }
            this.a.a(com.applovin.impl.sdk.b.d.h, linkedHashSet, this.f);
            pVar = this.b;
            str2 = "PersistentPostbackManager";
            str = "Wrote updated postback queue to disk.";
        } else {
            pVar = this.b;
            str2 = "PersistentPostbackManager";
            str = "Skipping writing postback queue to disk due to old Android version...";
        }
        pVar.a(str2, str);
    }

    /* access modifiers changed from: private */
    public void d(e eVar) {
        synchronized (this.e) {
            this.c.remove(eVar);
            d();
        }
        p pVar = this.b;
        pVar.a("PersistentPostbackManager", "Dequeued successfully transmitted postback: " + eVar);
    }

    /* access modifiers changed from: private */
    public void e(e eVar) {
        synchronized (this.e) {
            this.d.add(eVar);
        }
    }

    public void a() {
        synchronized (this.e) {
            if (this.c != null) {
                for (e c2 : new ArrayList(this.c)) {
                    c(c2);
                }
            }
        }
    }

    public void a(e eVar) {
        a(eVar, true);
    }

    public void a(e eVar, boolean z) {
        if (k.b(eVar.a())) {
            if (z) {
                eVar.h();
            }
            synchronized (this.e) {
                b(eVar);
                c(eVar);
            }
        }
    }

    public void b() {
        synchronized (this.e) {
            Iterator<e> it = this.d.iterator();
            while (it.hasNext()) {
                c(it.next());
            }
            this.d.clear();
        }
    }
}
