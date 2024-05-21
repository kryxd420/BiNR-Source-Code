package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.applovin.impl.a.e;
import com.applovin.impl.sdk.ad.a;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.ad.i;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.d;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAd;

class c extends WebView {
    /* access modifiers changed from: private */
    public final p a;
    private final j b;
    private d c;
    private AppLovinAd d = null;
    private boolean e = false;

    c(d dVar, j jVar, Context context) {
        super(context);
        if (jVar != null) {
            this.b = jVar;
            this.a = jVar.v();
            setBackgroundColor(0);
            WebSettings settings = getSettings();
            settings.setSupportMultipleWindows(false);
            settings.setJavaScriptEnabled(true);
            setWebViewClient(dVar);
            setWebChromeClient(new b(jVar));
            setVerticalScrollBarEnabled(false);
            setHorizontalScrollBarEnabled(false);
            setScrollBarStyle(33554432);
            setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (view.hasFocus()) {
                        return false;
                    }
                    view.requestFocus();
                    return false;
                }
            });
            setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    c.this.a.a("AdWebView", "Received a LongClick event.");
                    return true;
                }
            });
            return;
        }
        throw new IllegalArgumentException("No sdk specified.");
    }

    private String a(String str, String str2) {
        if (k.b(str)) {
            return n.b(str).replace("{SOURCE}", str2);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fe, code lost:
        r1 = r4.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0112, code lost:
        r4 = r4.n();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(final com.applovin.impl.sdk.ad.g r4) {
        /*
            r3 = this;
            com.applovin.impl.sdk.j r0 = r3.b     // Catch:{ Throwable -> 0x0121 }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.b.fb     // Catch:{ Throwable -> 0x0121 }
            java.lang.Object r0 = r0.a(r1)     // Catch:{ Throwable -> 0x0121 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x0121 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0121 }
            if (r0 != 0) goto L_0x0016
            boolean r0 = r4.aq()     // Catch:{ Throwable -> 0x0121 }
            if (r0 == 0) goto L_0x001e
        L_0x0016:
            com.applovin.impl.adview.c$13 r0 = new com.applovin.impl.adview.c$13     // Catch:{ Throwable -> 0x0121 }
            r0.<init>()     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r0)     // Catch:{ Throwable -> 0x0121 }
        L_0x001e:
            boolean r0 = com.applovin.impl.sdk.e.e.d()     // Catch:{ Throwable -> 0x0121 }
            if (r0 == 0) goto L_0x002c
            com.applovin.impl.adview.c$14 r0 = new com.applovin.impl.adview.c$14     // Catch:{ Throwable -> 0x0121 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r0)     // Catch:{ Throwable -> 0x0121 }
        L_0x002c:
            boolean r0 = com.applovin.impl.sdk.e.e.e()     // Catch:{ Throwable -> 0x0121 }
            if (r0 == 0) goto L_0x0040
            boolean r0 = r4.as()     // Catch:{ Throwable -> 0x0121 }
            if (r0 == 0) goto L_0x0040
            com.applovin.impl.adview.c$15 r0 = new com.applovin.impl.adview.c$15     // Catch:{ Throwable -> 0x0121 }
            r0.<init>()     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r0)     // Catch:{ Throwable -> 0x0121 }
        L_0x0040:
            com.applovin.impl.adview.t r4 = r4.at()     // Catch:{ Throwable -> 0x0121 }
            if (r4 == 0) goto L_0x012b
            android.webkit.WebSettings r0 = r3.getSettings()     // Catch:{ Throwable -> 0x0121 }
            android.webkit.WebSettings$PluginState r1 = r4.b()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x0058
            com.applovin.impl.adview.c$16 r2 = new com.applovin.impl.adview.c$16     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x0058:
            java.lang.Boolean r1 = r4.c()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x0066
            com.applovin.impl.adview.c$17 r2 = new com.applovin.impl.adview.c$17     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x0066:
            java.lang.Boolean r1 = r4.d()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x0074
            com.applovin.impl.adview.c$18 r2 = new com.applovin.impl.adview.c$18     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x0074:
            java.lang.Boolean r1 = r4.e()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x0082
            com.applovin.impl.adview.c$19 r2 = new com.applovin.impl.adview.c$19     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x0082:
            java.lang.Boolean r1 = r4.f()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x0090
            com.applovin.impl.adview.c$2 r2 = new com.applovin.impl.adview.c$2     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x0090:
            java.lang.Boolean r1 = r4.g()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x009e
            com.applovin.impl.adview.c$3 r2 = new com.applovin.impl.adview.c$3     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x009e:
            java.lang.Boolean r1 = r4.h()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00ac
            com.applovin.impl.adview.c$4 r2 = new com.applovin.impl.adview.c$4     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x00ac:
            java.lang.Boolean r1 = r4.i()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00ba
            com.applovin.impl.adview.c$5 r2 = new com.applovin.impl.adview.c$5     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x00ba:
            java.lang.Boolean r1 = r4.j()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00c8
            com.applovin.impl.adview.c$6 r2 = new com.applovin.impl.adview.c$6     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x00c8:
            java.lang.Boolean r1 = r4.k()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00d6
            com.applovin.impl.adview.c$7 r2 = new com.applovin.impl.adview.c$7     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x00d6:
            boolean r1 = com.applovin.impl.sdk.e.e.c()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00f8
            java.lang.Boolean r1 = r4.l()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00ea
            com.applovin.impl.adview.c$8 r2 = new com.applovin.impl.adview.c$8     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x00ea:
            java.lang.Boolean r1 = r4.m()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x00f8
            com.applovin.impl.adview.c$9 r2 = new com.applovin.impl.adview.c$9     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x00f8:
            boolean r1 = com.applovin.impl.sdk.e.e.f()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x010c
            java.lang.Integer r1 = r4.a()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x010c
            com.applovin.impl.adview.c$10 r2 = new com.applovin.impl.adview.c$10     // Catch:{ Throwable -> 0x0121 }
            r2.<init>(r0, r1)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r2)     // Catch:{ Throwable -> 0x0121 }
        L_0x010c:
            boolean r1 = com.applovin.impl.sdk.e.e.g()     // Catch:{ Throwable -> 0x0121 }
            if (r1 == 0) goto L_0x012b
            java.lang.Boolean r4 = r4.n()     // Catch:{ Throwable -> 0x0121 }
            if (r4 == 0) goto L_0x012b
            com.applovin.impl.adview.c$11 r1 = new com.applovin.impl.adview.c$11     // Catch:{ Throwable -> 0x0121 }
            r1.<init>(r0, r4)     // Catch:{ Throwable -> 0x0121 }
            r3.a((java.lang.Runnable) r1)     // Catch:{ Throwable -> 0x0121 }
            goto L_0x012b
        L_0x0121:
            r4 = move-exception
            com.applovin.impl.sdk.p r0 = r3.a
            java.lang.String r1 = "AdWebView"
            java.lang.String r2 = "Unable to apply WebView settings"
            r0.b(r1, r2, r4)
        L_0x012b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.c.a(com.applovin.impl.sdk.ad.g):void");
    }

    private void a(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.a.b("AdWebView", "Unable to apply WebView setting", th);
        }
    }

    private void a(String str, String str2, String str3, j jVar) {
        p pVar;
        String str4;
        StringBuilder sb;
        String a2 = a(str3, str);
        if (k.b(a2)) {
            pVar = this.a;
            str4 = "AdWebView";
            sb = new StringBuilder();
        } else {
            a2 = a((String) jVar.a(b.eK), str);
            if (k.b(a2)) {
                pVar = this.a;
                str4 = "AdWebView";
                sb = new StringBuilder();
            } else {
                p pVar2 = this.a;
                pVar2.a("AdWebView", "Rendering webview for VAST ad with resourceURL : " + str);
                loadUrl(str);
                return;
            }
        }
        sb.append("Rendering webview for VAST ad with resourceContents : ");
        sb.append(a2);
        pVar.a(str4, sb.toString());
        loadDataWithBaseURL(str2, a2, "text/html", (String) null, "");
    }

    /* access modifiers changed from: package-private */
    public AppLovinAd a() {
        return this.d;
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    public void a(AppLovinAd appLovinAd) {
        p pVar;
        String str;
        String str2;
        p pVar2;
        String str3;
        String str4;
        String str5;
        String ar;
        String str6;
        String str7;
        String str8;
        String ar2;
        j jVar;
        if (!this.e) {
            this.d = appLovinAd;
            try {
                if (appLovinAd instanceof i) {
                    loadDataWithBaseURL("/", ((i) appLovinAd).a(), "text/html", (String) null, "");
                    pVar = this.a;
                    str = "AdWebView";
                    str2 = "Empty ad rendered";
                } else {
                    g gVar = (g) appLovinAd;
                    a(gVar);
                    if (gVar.ab()) {
                        setVisibility(0);
                    }
                    if (appLovinAd instanceof a) {
                        loadDataWithBaseURL(gVar.ar(), n.b(((a) appLovinAd).a()), "text/html", (String) null, "");
                        pVar = this.a;
                        str = "AdWebView";
                        str2 = "AppLovinAd rendered";
                    } else if (appLovinAd instanceof com.applovin.impl.a.a) {
                        com.applovin.impl.a.a aVar = (com.applovin.impl.a.a) appLovinAd;
                        com.applovin.impl.a.b d2 = aVar.d();
                        if (d2 != null) {
                            e b2 = d2.b();
                            Uri b3 = b2.b();
                            String uri = b3 != null ? b3.toString() : "";
                            String c2 = b2.c();
                            String j = aVar.j();
                            if (!k.b(uri)) {
                                if (!k.b(c2)) {
                                    pVar2 = this.a;
                                    str3 = "AdWebView";
                                    str4 = "Unable to load companion ad. No resources provided.";
                                    pVar2.d(str3, str4);
                                    return;
                                }
                            }
                            if (b2.a() == e.a.STATIC) {
                                this.a.a("AdWebView", "Rendering WebView for static VAST ad");
                                loadDataWithBaseURL(gVar.ar(), a((String) this.b.a(b.eJ), uri), "text/html", (String) null, "");
                                return;
                            }
                            if (b2.a() == e.a.HTML) {
                                if (k.b(c2)) {
                                    String a2 = a(j, c2);
                                    str5 = k.b(a2) ? a2 : c2;
                                    p pVar3 = this.a;
                                    pVar3.a("AdWebView", "Rendering WebView for HTML VAST ad with resourceContents: " + str5);
                                    ar = gVar.ar();
                                    str6 = "text/html";
                                    str7 = null;
                                    str8 = "";
                                } else if (k.b(uri)) {
                                    this.a.a("AdWebView", "Preparing to load HTML VAST ad resourceUri");
                                    ar2 = gVar.ar();
                                    jVar = this.b;
                                    a(uri, ar2, j, jVar);
                                    return;
                                } else {
                                    return;
                                }
                            } else if (b2.a() != e.a.IFRAME) {
                                pVar2 = this.a;
                                str3 = "AdWebView";
                                str4 = "Failed to render VAST companion ad of invalid type";
                                pVar2.d(str3, str4);
                                return;
                            } else if (k.b(uri)) {
                                this.a.a("AdWebView", "Preparing to load iFrame VAST ad resourceUri");
                                ar2 = gVar.ar();
                                jVar = this.b;
                                a(uri, ar2, j, jVar);
                                return;
                            } else if (k.b(c2)) {
                                String a3 = a(j, c2);
                                str5 = k.b(a3) ? a3 : c2;
                                p pVar4 = this.a;
                                pVar4.a("AdWebView", "Rendering WebView for iFrame VAST ad with resourceContents: " + str5);
                                ar = gVar.ar();
                                str6 = "text/html";
                                str7 = null;
                                str8 = "";
                            } else {
                                return;
                            }
                            loadDataWithBaseURL(ar, str5, str6, str7, str8);
                            return;
                        }
                        pVar = this.a;
                        str = "AdWebView";
                        str2 = "No companion ad provided.";
                    } else {
                        return;
                    }
                }
                pVar.a(str, str2);
            } catch (Throwable th) {
                this.a.b("AdWebView", "Unable to render AppLovinAd", th);
            }
        } else {
            this.a.e("AdWebView", "Ad can not be loaded in a destroyed webview");
        }
    }

    public void a(String str) {
        a(str, (Runnable) null);
    }

    public void a(String str, Runnable runnable) {
        try {
            p pVar = this.a;
            pVar.a("AdWebView", "Forwarding \"" + str + "\" to ad template");
            loadUrl(str);
        } catch (Throwable th) {
            this.a.b("AdWebView", "Unable to forward to template", th);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public d b() {
        return this.c;
    }

    public void computeScroll() {
    }

    public void destroy() {
        this.e = true;
        try {
            super.destroy();
            this.a.a("AdWebView", "Web view destroyed");
        } catch (Throwable th) {
            if (this.a != null) {
                this.a.b("AdWebView", "destroy() threw exception", th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            super.onFocusChanged(z, i, rect);
        } catch (Exception e2) {
            this.a.b("AdWebView", "onFocusChanged() threw exception", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Exception e2) {
            this.a.b("AdWebView", "onWindowFocusChanged() threw exception", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        try {
            super.onWindowVisibilityChanged(i);
        } catch (Exception e2) {
            this.a.b("AdWebView", "onWindowVisibilityChanged() threw exception", e2);
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        try {
            return super.requestFocus(i, rect);
        } catch (Exception e2) {
            this.a.b("AdWebView", "requestFocus() threw exception", e2);
            return false;
        }
    }

    public void scrollTo(int i, int i2) {
    }
}
