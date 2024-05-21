package com.moat.analytics.mobile.vng;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.moat.analytics.mobile.vng.j;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class g {
    WebView a;
    j b;
    final String c = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private final a d;
    /* access modifiers changed from: private */
    public boolean e = false;

    enum a {
        DISPLAY,
        VIDEO
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    g(Context context, a aVar) {
        this.d = aVar;
        this.a = new WebView(context);
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        this.b = new j(this.a, aVar == a.VIDEO ? j.a.NATIVE_VIDEO : j.a.NATIVE_DISPLAY);
    }

    private static String a(String str, String str2, Integer num, Integer num2, JSONObject jSONObject, Integer num3) {
        return String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str, str2, jSONObject.toString(), num3});
    }

    private static String b(String str) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n" + "</body>\n" + "</html>";
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.b = null;
        this.a.destroy();
        this.a = null;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (this.d == a.DISPLAY) {
            this.a.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    if (!g.this.e) {
                        try {
                            boolean unused = g.this.e = true;
                            g.this.b.a();
                        } catch (Exception e) {
                            m.a(e);
                        }
                    }
                }
            });
            this.a.loadData(b(str), "text/html", "utf-8");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.d == a.VIDEO) {
            this.a.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    if (!g.this.e) {
                        try {
                            boolean unused = g.this.e = true;
                            g.this.b.a();
                            g.this.b.c(g.this.c);
                        } catch (Exception e) {
                            m.a(e);
                        }
                    }
                }
            });
            this.a.loadData(a(this.c, str, num, num2, new JSONObject(map), num3), "text/html", (String) null);
        }
    }
}
