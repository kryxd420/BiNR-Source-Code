package com.applovin.impl.sdk.d;

import android.net.Uri;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.ad.a;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;

public class d extends c {
    private final a c;
    private boolean d;
    private boolean e;

    public d(a aVar, j jVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        super("TaskCacheAppLovinAd", aVar, jVar, appLovinAdLoadListener);
        this.c = aVar;
    }

    private void h() {
        a("Caching HTML resources...");
        this.c.a(a(this.c.a(), this.c.G(), (g) this.c));
        this.c.a(true);
        a("Finish caching non-video resources for ad #" + this.c.getAdIdNumber());
        a("Ad updated with cachedHTML = " + this.c.a());
    }

    private void i() {
        Uri e2 = e(this.c.f());
        if (e2 != null) {
            this.c.c();
            this.c.a(e2);
        }
    }

    public i a() {
        return i.i;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public void run() {
        boolean b = this.c.b();
        boolean z = this.e;
        if (b || z) {
            a("Begin caching for streaming ad #" + this.c.getAdIdNumber() + "...");
            f();
            if (b) {
                if (this.d) {
                    g();
                }
                h();
                if (!this.d) {
                    g();
                }
                i();
            } else {
                g();
                h();
            }
        } else {
            a("Begin processing for non-streaming ad #" + this.c.getAdIdNumber() + "...");
            f();
            h();
            i();
            g();
        }
        long currentTimeMillis = System.currentTimeMillis() - this.c.getCreatedAtMillis();
        com.applovin.impl.sdk.c.d.a(this.c, this.b);
        com.applovin.impl.sdk.c.d.a(currentTimeMillis, (AppLovinAdBase) this.c, this.b);
        a(this.c);
    }
}
