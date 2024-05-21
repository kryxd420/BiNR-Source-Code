package com.applovin.impl.mediation.b;

import com.applovin.impl.mediation.a.e;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.f;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class d extends a {
    private final String a;
    private final String c;
    private final e d;
    private final Map<String, String> e;
    private final String f;
    private final String g;
    private final boolean h;

    public d(String str, Map<String, String> map, int i, String str2, e eVar, j jVar) {
        super("TaskFireMediationPostbacks", jVar);
        this.a = str;
        this.c = str + "_urls";
        this.e = map;
        this.f = String.valueOf(i);
        this.g = k.c(str2);
        this.d = eVar;
        this.h = eVar.d(this.c);
    }

    private f a(String str, String str2, String str3) {
        return f.b(b()).a(c(str, str2, str3)).a(false).a();
    }

    private com.applovin.impl.sdk.network.e b(String str, String str2, String str3) {
        return com.applovin.impl.sdk.network.e.j().a(c(str, str2, str3)).a(false).a();
    }

    private String c(String str, String str2, String str3) {
        return str.replace("{ERROR_CODE}", str2).replace("{ERROR_MESSAGE}", k.e(str3));
    }

    private void f() {
        try {
            final List<String> b = this.d.b(this.c, this.e);
            if (b == null || b.isEmpty()) {
                a("No postbacks to fire for event: " + this.a);
                return;
            }
            a("Firing " + b.size() + " '" + this.a + "' postback(s)");
            final AtomicInteger atomicInteger = new AtomicInteger();
            for (String a2 : b) {
                b().K().dispatchPostbackRequest(a(a2, this.f, this.g), q.a.MEDIATION_POSTBACKS, new AppLovinPostbackListener() {
                    public void onPostbackFailure(String str, int i) {
                        d dVar = d.this;
                        dVar.d("Failed to fire postback: " + str);
                    }

                    public void onPostbackSuccess(String str) {
                        d dVar = d.this;
                        dVar.a("Successfully fired postback: " + str);
                        if (atomicInteger.incrementAndGet() == b.size()) {
                            d.this.h();
                        }
                    }
                });
            }
        } catch (Throwable th) {
            a("Unable to create postback URL for mediated '" + this.a + "'", th);
        }
    }

    private void g() {
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(this.d.b(this.c, this.e));
            if (this.h) {
                arrayList.addAll(this.d.a(this.c, this.e));
            }
            if (!arrayList.isEmpty()) {
                a("Firing " + arrayList.size() + " '" + this.a + "' persistent postback(s)");
                for (String b : arrayList) {
                    b().G().a(b(b, this.f, this.g));
                }
                return;
            }
            a("No persistent postbacks to fire for event: " + this.a);
        } catch (Throwable th) {
            a("Unable to create persistent postback URL for mediated '" + this.a + "'", th);
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.h) {
            List<String> a2 = this.d.a(this.c, this.e);
            if (a2 == null || a2.isEmpty()) {
                a("Skip firing of successive urls - none found");
                return;
            }
            a("Firing " + a2.size() + " '" + this.a + "' successive postback(s)");
            for (String a3 : a2) {
                b().K().dispatchPostbackRequest(a(a3, this.f, this.g), q.a.MEDIATION_POSTBACKS, (AppLovinPostbackListener) null);
            }
        }
    }

    public i a() {
        return i.I;
    }

    public void run() {
        if (((Boolean) b().a(com.applovin.impl.sdk.b.a.i)).booleanValue()) {
            g();
        } else {
            f();
        }
    }
}
