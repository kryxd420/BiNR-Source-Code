package com.applovin.impl.sdk.d;

import android.text.TextUtils;
import com.applovin.impl.sdk.b.c;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.a;
import com.applovin.impl.sdk.network.b;
import java.util.concurrent.TimeUnit;

public abstract class w<T> extends a implements a.b<T> {
    /* access modifiers changed from: private */
    public final b<T> a;
    private final a.b<T> c;
    protected a.C0005a d;
    /* access modifiers changed from: private */
    public q.a e;
    /* access modifiers changed from: private */
    public com.applovin.impl.sdk.b.b<String> f;
    /* access modifiers changed from: private */
    public com.applovin.impl.sdk.b.b<String> g;

    public w(b<T> bVar, j jVar) {
        this(bVar, jVar, false);
    }

    public w(b<T> bVar, final j jVar, boolean z) {
        super("TaskRepeatRequest", jVar, z);
        this.e = q.a.BACKGROUND;
        this.f = null;
        this.g = null;
        if (bVar != null) {
            this.a = bVar;
            this.d = new a.C0005a();
            this.c = new a.b<T>() {
                public void a(int i) {
                    com.applovin.impl.sdk.b.b bVar;
                    w wVar;
                    boolean z = false;
                    boolean z2 = i < 200 || i >= 500;
                    if (i != -103) {
                        z = true;
                    }
                    if (z2 && z) {
                        String e = w.this.a.e();
                        if (w.this.a.i() > 0) {
                            w wVar2 = w.this;
                            wVar2.c("Unable to send request due to server failure (code " + i + "). " + w.this.a.i() + " attempts left, retrying in " + TimeUnit.MILLISECONDS.toSeconds((long) w.this.a.k()) + " seconds...");
                            int i2 = w.this.a.i() - 1;
                            w.this.a.a(i2);
                            if (i2 == 0) {
                                w.this.c(w.this.f);
                                if (k.b(e) && e.length() >= 4) {
                                    w.this.a.a(e);
                                    w wVar3 = w.this;
                                    wVar3.b("Switching to backup endpoint " + e);
                                }
                            }
                            jVar.D().a((a) w.this, w.this.e, (long) w.this.a.k());
                            return;
                        }
                        if (e == null || !e.equals(w.this.a.a())) {
                            wVar = w.this;
                            bVar = w.this.f;
                        } else {
                            wVar = w.this;
                            bVar = w.this.g;
                        }
                        wVar.c(bVar);
                    }
                    w.this.a(i);
                }

                public void a(T t, int i) {
                    w.this.a.a(0);
                    w.this.a(t, i);
                }
            };
            return;
        }
        throw new IllegalArgumentException("No request specified");
    }

    /* access modifiers changed from: private */
    public <ST> void c(com.applovin.impl.sdk.b.b<ST> bVar) {
        if (bVar != null) {
            c w = b().w();
            w.a((com.applovin.impl.sdk.b.b<?>) bVar, (Object) bVar.b());
            w.a();
        }
    }

    public i a() {
        return i.e;
    }

    public void a(int i) {
    }

    public void a(com.applovin.impl.sdk.b.b<String> bVar) {
        this.f = bVar;
    }

    public void a(q.a aVar) {
        this.e = aVar;
    }

    public void a(T t, int i) {
    }

    public void b(com.applovin.impl.sdk.b.b<String> bVar) {
        this.g = bVar;
    }

    public void run() {
        int i;
        a C = b().C();
        if (!b().c() && !b().d()) {
            d("AppLovin SDK is disabled: please check your connection");
            b().v().e("AppLovinSdk", "AppLovin SDK is disabled: please check your connection");
            i = -22;
        } else if (!k.b(this.a.a()) || this.a.a().length() < 4) {
            d("Task has an invalid or null request endpoint.");
            i = -900;
        } else {
            if (TextUtils.isEmpty(this.a.c())) {
                this.a.b(this.a.d() != null ? "POST" : "GET");
            }
            C.a(this.a, this.d, this.c);
            return;
        }
        a(i);
    }
}
