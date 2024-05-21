package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;

class ad {
    protected final a a;
    protected final ap b;
    protected bh c;
    private final String d;
    private final String e = String.format("_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private WeakReference f;
    /* access modifiers changed from: private */
    public WeakReference g;
    /* access modifiers changed from: private */
    public WebView h;
    /* access modifiers changed from: private */
    public boolean i = false;
    private boolean j;
    private final LinkedList k = new LinkedList();

    ad(String str, ap apVar, a aVar) {
        this.d = str;
        this.b = apVar;
        this.a = aVar;
        this.f = new WeakReference(aVar.c());
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.k.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (int i2 = 0; i2 < 10; i2++) {
                linkedList.addFirst((String) this.k.removeFirst());
            }
            int min = Math.min(Math.min(this.k.size() / 200, 10) + 200, this.k.size());
            for (int i3 = 0; i3 < min; i3++) {
                this.k.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.k.addFirst((String) it.next());
            }
        }
        int i4 = 0;
        while (!this.k.isEmpty() && i4 < 200) {
            i4++;
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            while (!this.k.isEmpty() && i4 < 200) {
                i4++;
                String str = (String) this.k.getFirst();
                if (sb.length() + str.length() > 2000) {
                    break;
                }
                this.k.removeFirst();
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
            a(String.format("javascript:%s.dispatchMany([%s])", new Object[]{this.e, sb.toString()}));
        }
        this.k.clear();
    }

    public void a(View view) {
        this.g = new WeakReference(view);
        if (this.c != null) {
            this.c.a(view);
        }
    }

    public void a(View view, Map map, Integer num, Integer num2, Integer num3) {
        this.g = new WeakReference(view);
        this.h = new WebView((Context) this.f.get());
        WebSettings settings = this.h.getSettings();
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
        this.h.setWebViewClient(new ae(this));
        this.h.loadData(String.format("<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), this.e, this.d, new JSONObject(map).toString(), num3}), "text/html", (String) null);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.h.loadUrl(str);
    }

    public void a(JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (!this.i || this.h == null) {
            this.k.add(jSONObject2);
            return;
        }
        this.h.loadUrl(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{this.e, jSONObject2}));
    }

    public void b() {
        if (!this.j) {
            if (this.c != null) {
                this.c.d();
                this.c = null;
            }
            if (this.h != null) {
                this.h.loadUrl("about:blank");
                this.h.destroy();
                this.h = null;
            }
            this.j = true;
        }
    }
}
