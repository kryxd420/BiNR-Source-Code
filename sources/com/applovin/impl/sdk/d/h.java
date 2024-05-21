package com.applovin.impl.sdk.d;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.a.a;
import com.applovin.impl.a.b;
import com.applovin.impl.a.e;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.c.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.List;

class h extends c {
    private final a c;

    public h(a aVar, j jVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        super("TaskCacheVastAd", aVar, jVar, appLovinAdLoadListener);
        this.c = aVar;
    }

    private void h() {
        String str;
        a aVar;
        String str2;
        if (this.c.l()) {
            b d = this.c.d();
            if (d != null) {
                e b = d.b();
                if (b != null) {
                    try {
                        Uri b2 = b.b();
                        String uri = b2 != null ? b2.toString() : "";
                        String c2 = b.c();
                        if (!URLUtil.isValidUrl(uri)) {
                            if (!k.b(c2)) {
                                c("Companion ad does not have any resources attached. Skipping...");
                                return;
                            }
                        }
                        if (b.a() == e.a.STATIC) {
                            a("Caching static companion ad at " + uri + "...");
                            List<String> f = this.c.f();
                            Uri b3 = b(uri, f, f != null && !f.isEmpty());
                            if (b3 != null) {
                                b.a(b3);
                                aVar = this.c;
                            } else {
                                str2 = "Failed to cache static companion ad";
                                d(str2);
                                return;
                            }
                        } else if (b.a() == e.a.HTML) {
                            if (k.b(uri)) {
                                a("Begin caching HTML companion ad. Fetching from " + uri + "...");
                                String g = g(uri);
                                if (k.b(g)) {
                                    a("HTML fetched. Caching HTML now...");
                                    b.a(a(g, this.c.f(), (g) this.c));
                                    aVar = this.c;
                                } else {
                                    str2 = "Unable to load companion ad resources from " + uri;
                                    d(str2);
                                    return;
                                }
                            } else {
                                a("Caching provided HTML for companion ad. No fetch required. HTML: " + c2);
                                b.a(a(c2, this.c.f(), (g) this.c));
                                aVar = this.c;
                            }
                        } else if (b.a() == e.a.IFRAME) {
                            a("Skip caching of iFrame resource...");
                            return;
                        } else {
                            return;
                        }
                        aVar.a(true);
                        return;
                    } catch (Throwable th) {
                        a("Failed to cache companion ad", th);
                        return;
                    }
                } else {
                    d("Failed to retrieve non-video resources from companion ad. Skipping...");
                    return;
                }
            } else {
                str = "No companion ad provided. Skipping...";
            }
        } else {
            str = "Companion ad caching disabled. Skipping...";
        }
        a(str);
    }

    private void i() {
        com.applovin.impl.a.k c2;
        Uri b;
        if (!this.c.az()) {
            a("Video caching disabled. Skipping...");
        } else if (this.c.a() != null && (c2 = this.c.c()) != null && (b = c2.b()) != null) {
            List<String> f = this.c.f();
            Uri a = a(b.toString(), f, f != null && !f.isEmpty());
            if (a != null) {
                a("Video file successfully cached into: " + a);
                c2.a(a);
                return;
            }
            d("Failed to cache video file: " + c2);
        }
    }

    private void j() {
        String str;
        String str2;
        if (this.c.k() != null) {
            a("Begin caching HTML template. Fetching from " + this.c.k() + "...");
            str = a(this.c.k().toString(), this.c.G());
        } else {
            str = this.c.j();
        }
        if (k.b(str)) {
            this.c.a(a(str, this.c.G(), (g) this.c));
            str2 = "Finish caching HTML template " + this.c.j() + " for ad #" + this.c.getAdIdNumber();
        } else {
            str2 = "Unable to load HTML template";
        }
        a(str2);
    }

    public i a() {
        return i.l;
    }

    public void run() {
        a("Begin caching for VAST ad #" + this.c.getAdIdNumber() + "...");
        f();
        h();
        i();
        j();
        g();
        a("Finished caching VAST ad #" + this.c.getAdIdNumber());
        long currentTimeMillis = System.currentTimeMillis() - this.c.getCreatedAtMillis();
        d.a(this.c, this.b);
        d.a(currentTimeMillis, (AppLovinAdBase) this.c, this.b);
        a(this.c);
    }
}
