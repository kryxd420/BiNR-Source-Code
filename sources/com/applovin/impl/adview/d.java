package com.applovin.impl.adview;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.net.Uri;
import android.view.ViewParent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.a.a;
import com.applovin.impl.a.b;
import com.applovin.impl.a.i;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAd;
import com.tapjoy.TapjoyConstants;

class d extends WebViewClient {
    private final j a;
    private final p b;
    private final AdViewControllerImpl c;

    public d(AdViewControllerImpl adViewControllerImpl, j jVar) {
        this.a = jVar;
        this.b = jVar.v();
        this.c = adViewControllerImpl;
    }

    private void a() {
        this.c.a();
    }

    private void a(Uri uri, c cVar) {
        p pVar;
        String str;
        String str2;
        try {
            String queryParameter = uri.getQueryParameter("n");
            if (k.b(queryParameter)) {
                String queryParameter2 = uri.getQueryParameter("load_type");
                if ("external".equalsIgnoreCase(queryParameter2)) {
                    p pVar2 = this.b;
                    pVar2.a("AdWebViewClient", "Loading new page externally: " + queryParameter);
                    n.a(cVar.getContext(), Uri.parse(queryParameter), this.a);
                    h.c(this.c.getAdViewEventListener(), this.c.getCurrentAd(), this.c.getParentView(), this.a);
                    return;
                } else if (TapjoyConstants.LOG_LEVEL_INTERNAL.equalsIgnoreCase(queryParameter2)) {
                    p pVar3 = this.b;
                    pVar3.a("AdWebViewClient", "Loading new page in WebView: " + queryParameter);
                    cVar.loadUrl(queryParameter);
                    String queryParameter3 = uri.getQueryParameter("bg_color");
                    if (k.b(queryParameter3)) {
                        cVar.setBackgroundColor(Color.parseColor(queryParameter3));
                        return;
                    }
                    return;
                } else {
                    pVar = this.b;
                    str = "AdWebViewClient";
                    str2 = "Could not find load type in original uri";
                }
            } else {
                pVar = this.b;
                str = "AdWebViewClient";
                str2 = "Could not find url to load from query in original uri";
            }
            pVar.d(str, str2);
        } catch (Throwable unused) {
            this.b.d("AdWebViewClient", "Failed to load new page from query in original uri");
        }
    }

    private void a(a aVar, c cVar) {
        b d = aVar.d();
        if (d != null) {
            i.a(d.c(), this.c.getSdk());
            a(cVar, d.a());
        }
    }

    private void a(c cVar) {
        ViewParent parent = cVar.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView) parent).loadNextAd();
        }
    }

    private void a(c cVar, Uri uri) {
        AppLovinAd a2 = cVar.a();
        AppLovinAdView parentView = this.c.getParentView();
        if (parentView == null || a2 == null) {
            p pVar = this.b;
            pVar.d("AdWebViewClient", "Attempting to track click that is null or not an ApplovinAdView instance for clickedUri = " + uri);
            return;
        }
        com.applovin.impl.sdk.c.d b2 = cVar.b();
        if (b2 != null) {
            b2.b();
        }
        this.c.a(a2, parentView, uri);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0172, code lost:
        if (r6.i() != false) goto L_0x00a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.webkit.WebView r9, java.lang.String r10, boolean r11) {
        /*
            r8 = this;
            com.applovin.impl.sdk.p r0 = r8.b
            java.lang.String r1 = "AdWebViewClient"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Processing click on ad URL \""
            r2.append(r3)
            r2.append(r10)
            java.lang.String r3 = "\""
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.b(r1, r2)
            r0 = 1
            if (r10 == 0) goto L_0x0186
            boolean r1 = r9 instanceof com.applovin.impl.adview.c
            if (r1 == 0) goto L_0x0186
            android.net.Uri r1 = android.net.Uri.parse(r10)
            r2 = r9
            com.applovin.impl.adview.c r2 = (com.applovin.impl.adview.c) r2
            java.lang.String r3 = r1.getScheme()
            java.lang.String r4 = r1.getHost()
            java.lang.String r5 = r1.getPath()
            com.applovin.impl.adview.AdViewControllerImpl r6 = r8.c
            com.applovin.sdk.AppLovinAd r6 = r6.getCurrentAd()
            java.lang.String r7 = "applovin"
            boolean r7 = r7.equals(r3)
            if (r7 == 0) goto L_0x0137
            java.lang.String r7 = "com.applovin.sdk"
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x0137
            java.lang.String r11 = "/adservice/next_ad"
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x005a
            r8.a(r2)
            goto L_0x0186
        L_0x005a:
            java.lang.String r11 = "/adservice/close_ad"
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x0067
            r8.a()
            goto L_0x0186
        L_0x0067:
            java.lang.String r11 = "/adservice/expand_ad"
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x0074
            r8.b()
            goto L_0x0186
        L_0x0074:
            java.lang.String r11 = "/adservice/contract_ad"
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x0081
            r8.c()
            goto L_0x0186
        L_0x0081:
            java.lang.String r11 = com.applovin.impl.sdk.AppLovinAdServiceImpl.URI_NO_OP
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x008a
            return r0
        L_0x008a:
            java.lang.String r11 = com.applovin.impl.sdk.AppLovinAdServiceImpl.URI_LOAD_URL
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x0097
            r8.a((android.net.Uri) r1, (com.applovin.impl.adview.c) r2)
            goto L_0x0186
        L_0x0097:
            java.lang.String r11 = com.applovin.impl.sdk.AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x00b5
            boolean r9 = r6 instanceof com.applovin.impl.a.a
            if (r9 == 0) goto L_0x00aa
            com.applovin.impl.a.a r6 = (com.applovin.impl.a.a) r6
        L_0x00a5:
            r8.a((com.applovin.impl.a.a) r6, (com.applovin.impl.adview.c) r2)
            goto L_0x0186
        L_0x00aa:
            java.lang.String r9 = com.applovin.impl.sdk.AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY
            android.net.Uri r9 = android.net.Uri.parse(r9)
            r8.a((com.applovin.impl.adview.c) r2, (android.net.Uri) r9)
            goto L_0x0186
        L_0x00b5:
            if (r5 == 0) goto L_0x0106
            java.lang.String r11 = "/launch/"
            boolean r11 = r5.startsWith(r11)
            if (r11 == 0) goto L_0x0106
            java.util.List r10 = r1.getPathSegments()
            if (r10 == 0) goto L_0x0186
            int r11 = r10.size()
            if (r11 <= r0) goto L_0x0186
            int r11 = r10.size()
            int r11 = r11 - r0
            java.lang.Object r10 = r10.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            android.content.Context r9 = r9.getContext()     // Catch:{ Throwable -> 0x00eb }
            android.content.pm.PackageManager r11 = r9.getPackageManager()     // Catch:{ Throwable -> 0x00eb }
            android.content.Intent r11 = r11.getLaunchIntentForPackage(r10)     // Catch:{ Throwable -> 0x00eb }
            r9.startActivity(r11)     // Catch:{ Throwable -> 0x00eb }
            r9 = 0
            r8.a((com.applovin.impl.adview.c) r2, (android.net.Uri) r9)     // Catch:{ Throwable -> 0x00eb }
            goto L_0x0186
        L_0x00eb:
            r9 = move-exception
            com.applovin.impl.sdk.p r11 = r8.b
            java.lang.String r1 = "AdWebViewClient"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Threw Exception Trying to Launch App for Package: "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            r11.b(r1, r10, r9)
            goto L_0x0186
        L_0x0106:
            com.applovin.impl.sdk.p r9 = r8.b
            java.lang.String r11 = "AdWebViewClient"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown URL: "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r9.c(r11, r10)
            com.applovin.impl.sdk.p r9 = r8.b
            java.lang.String r10 = "AdWebViewClient"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r1 = "Path: "
            r11.append(r1)
            r11.append(r5)
            java.lang.String r11 = r11.toString()
            r9.c(r10, r11)
            goto L_0x0186
        L_0x0137:
            if (r11 == 0) goto L_0x0184
            boolean r9 = r6 instanceof com.applovin.impl.sdk.ad.g
            if (r9 == 0) goto L_0x017a
            r9 = r6
            com.applovin.impl.sdk.ad.g r9 = (com.applovin.impl.sdk.ad.g) r9
            java.util.List r10 = r9.au()
            java.util.List r9 = r9.av()
            boolean r11 = r10.isEmpty()
            if (r11 != 0) goto L_0x0154
            boolean r10 = r10.contains(r3)
            if (r10 == 0) goto L_0x0161
        L_0x0154:
            boolean r10 = r9.isEmpty()
            if (r10 != 0) goto L_0x0168
            boolean r9 = r9.contains(r4)
            if (r9 == 0) goto L_0x0161
            goto L_0x0168
        L_0x0161:
            com.applovin.impl.sdk.p r9 = r8.b
            java.lang.String r10 = "AdWebViewClient"
            java.lang.String r11 = "URL is not whitelisted - bypassing click"
            goto L_0x0180
        L_0x0168:
            boolean r9 = r6 instanceof com.applovin.impl.a.a
            if (r9 == 0) goto L_0x0176
            com.applovin.impl.a.a r6 = (com.applovin.impl.a.a) r6
            boolean r9 = r6.i()
            if (r9 == 0) goto L_0x0176
            goto L_0x00a5
        L_0x0176:
            r8.a((com.applovin.impl.adview.c) r2, (android.net.Uri) r1)
            goto L_0x0186
        L_0x017a:
            com.applovin.impl.sdk.p r9 = r8.b
            java.lang.String r10 = "AdWebViewClient"
            java.lang.String r11 = "Bypassing click for ad of invalid type"
        L_0x0180:
            r9.d(r10, r11)
            goto L_0x0186
        L_0x0184:
            r9 = 0
            return r9
        L_0x0186:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.d.a(android.webkit.WebView, java.lang.String, boolean):boolean");
    }

    private void b() {
        this.c.expandAd();
    }

    private void c() {
        this.c.contractAd();
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.c.onAdHtmlLoaded(webView);
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean hasGesture = ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.cg)).booleanValue() ? webResourceRequest.hasGesture() : true;
        Uri url = webResourceRequest.getUrl();
        if (url != null) {
            return a(webView, url.toString(), hasGesture);
        }
        this.b.d("AdWebViewClient", "No url found for request");
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(webView, str, true);
    }
}
