package com.moat.analytics.mobile.vng;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.s;
import com.moat.analytics.mobile.vng.w;
import com.tapjoy.TapjoyAuctionFlags;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.warren.model.Cookie;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

class j {
    boolean a = false;
    /* access modifiers changed from: private */
    public int b = 0;
    private boolean c = false;
    private boolean d = false;
    private final WeakReference<WebView> e;
    private final Map<b, String> f;
    private final LinkedList<String> g;
    private final AtomicBoolean h = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final long i;
    private final s.a j;
    private final List<String> k;
    private final a l;
    private final BroadcastReceiver m = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.c();
            } catch (Exception e) {
                m.a(e);
            }
            if (System.currentTimeMillis() - j.this.i > TapjoyConstants.TIMER_INCREMENT) {
                j.this.e();
            }
        }
    };
    private final BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.d();
            } catch (Exception e) {
                m.a(e);
            }
        }
    };

    enum a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    j(WebView webView, a aVar) {
        this.e = new WeakReference<>(webView);
        this.l = aVar;
        this.j = new s.a();
        this.g = new LinkedList<>();
        this.k = new ArrayList();
        this.f = new WeakHashMap();
        this.i = System.currentTimeMillis();
        IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
        IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
        LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.m, intentFilter);
        LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.n, intentFilter2);
        try {
            p.a(3, "JavaScriptBridge", (Object) this, b() ? "bridge installed" : "bridge not installed");
        } catch (Exception e2) {
            m.a(e2);
        }
    }

    private boolean a(WebView webView, String str) {
        String str2;
        StringBuilder sb;
        String str3;
        if (webView == null) {
            str2 = "JavaScriptBridge";
            sb = new StringBuilder();
            str3 = "WebView is null. Can't ";
        } else if (webView.getSettings().getJavaScriptEnabled()) {
            return true;
        } else {
            str2 = "JavaScriptBridge";
            sb = new StringBuilder();
            str3 = "JavaScript is not enabled in the given WebView. Can't ";
        }
        sb.append(str3);
        sb.append(str);
        p.a(6, str2, (Object) this, sb.toString());
        return false;
    }

    private boolean b() {
        if (h() != null && !a(h(), "installBridge")) {
            return false;
        }
        this.a = true;
        i.a().a(s.c(), this);
        return true;
    }

    static /* synthetic */ int c(j jVar) {
        int i2 = jVar.b;
        jVar.b = i2 + 1;
        return i2;
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            if (w.a().a != w.d.OFF) {
                if (!this.d) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Ready for communication (setting environment variables).");
                    this.d = true;
                }
                String format = String.format("javascript:(function(e,k){function l(){function f(a){var b=a.c,c=a.a,f=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var h=d[g];if('function'===typeof h)try{f?h(f):h()}catch(k){}a&&delete b[c]}}function d(a,b,c){'function'===typeof a&&(b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]})}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};csif={};this.g=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId};this.nvsj=function(a){briz||(d(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea;c!==kuea&&bjmk[c]||d(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||d(a,c,uqaj)};this.lgpr=function(a,b){d(a,b||kuea,yhgt)};this.hgen=function(a,b){d(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={c:iymv,b:a,a:ewat};briz?f(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={c:bjmk,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.egpw=function(a){var b=a||kuea;ryup[b]=!0;var c={c:uqaj,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.sglu=function(a){var b={c:yhgt,b:a.event||a,f:!1};(a.adKey||kuea)!==kuea&&(b.a=a.adKey);f(b);return 0<Object.keys(yhgt).length};this.ucbx=function(a){f({a:a.adKey||kuea,c:csif,b:a.event,f:!1})}}'undefined'===typeof e.MoatMAK&&(e.MoatMAK=new l,e.MoatMAK.g(k),e.__zMoatInit__=!0)})(window,%s);", new Object[]{i()});
                if (g()) {
                    h().loadUrl(format);
                }
            }
        } catch (Exception e2) {
            p.a("JavaScriptBridge", (Object) this, "Failed to initialize communication (did not set environment variables).", (Throwable) e2);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void d() {
        String str;
        String sb;
        try {
            if (w.a().a != w.d.OFF) {
                if (this.e != null && g()) {
                    if (!this.c || h().getUrl() != null) {
                        if (h().getUrl() != null) {
                            this.c = true;
                        }
                        for (Map.Entry<b, String> key : this.f.entrySet()) {
                            b bVar = (b) key.getKey();
                            if (bVar == null || bVar.d() == null) {
                                p.a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                                if (bVar != null) {
                                    if (!bVar.c) {
                                    }
                                }
                                c(bVar);
                            }
                            if (bVar.d) {
                                f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{bVar.b}));
                                String format = String.format("javascript: MoatMAK.sglu(%s)", new Object[]{bVar.f()});
                                if (Build.VERSION.SDK_INT >= 19) {
                                    h().evaluateJavascript(format, new ValueCallback<String>() {
                                        /* renamed from: a */
                                        public void onReceiveValue(String str) {
                                            String str2;
                                            if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("false")) {
                                                j jVar = j.this;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("Received value is:");
                                                if (str == null) {
                                                    str2 = "null";
                                                } else {
                                                    str2 = "(String)" + str;
                                                }
                                                sb.append(str2);
                                                p.a(3, "JavaScriptBridge", (Object) jVar, sb.toString());
                                                if (j.this.b >= 50) {
                                                    j.this.f();
                                                }
                                                j.c(j.this);
                                            } else if (str.equalsIgnoreCase("true")) {
                                                if (j.this.b != 0) {
                                                    p.a(3, "JavaScriptBridge", (Object) j.this, "Javascript has found ad");
                                                    j.this.e();
                                                }
                                                int unused = j.this.b = 0;
                                            } else {
                                                p.a(3, "JavaScriptBridge", (Object) j.this, "Received unusual value from Javascript:" + str);
                                            }
                                        }
                                    });
                                } else {
                                    h().loadUrl(format);
                                }
                            }
                        }
                        return;
                    }
                }
                if (this.e == null) {
                    str = "JavaScriptBridge";
                    sb = "WebView ref became null, stopping tracking loop";
                } else {
                    str = "JavaScriptBridge";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("WebView became null");
                    sb2.append(h() == null ? "" : "based on null url");
                    sb2.append(", stopping tracking loop");
                    sb = sb2.toString();
                }
                p.a(3, str, (Object) this, sb);
                f();
            }
        } catch (Exception e2) {
            m.a(e2);
            f();
        }
    }

    private void d(b bVar) {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (bVar != null) {
            i.a().a(bVar);
        }
    }

    private void d(String str) {
        if (this.k.size() >= 50) {
            this.k.subList(0, 25).clear();
        }
        this.k.add(str);
    }

    /* access modifiers changed from: private */
    public void e() {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
        i.a().a(this);
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.m);
    }

    private boolean e(String str) {
        if (this.a) {
            return true;
        }
        p.a(6, "JavaScriptBridge", (Object) this, "Bridge is not installed in the given WebView. Can't " + str);
        return false;
    }

    /* access modifiers changed from: private */
    public void f() {
        p.a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
        e();
        for (Map.Entry<b, String> key : this.f.entrySet()) {
            d((b) key.getKey());
        }
        this.f.clear();
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.n);
    }

    private void f(String str) {
        if (g()) {
            h().loadUrl(str);
        }
    }

    private boolean g() {
        return h() != null;
    }

    private WebView h() {
        return (WebView) this.e.get();
    }

    private String i() {
        try {
            HashMap hashMap = new HashMap();
            String a2 = this.j.a();
            String b2 = this.j.b();
            String num = Integer.toString(Build.VERSION.SDK_INT);
            String b3 = s.b();
            String str = this.l == a.WEBVIEW ? "0" : TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE;
            hashMap.put("versionHash", "3f2ae9c1894282b5e0222f0d06bbf457191f816f");
            hashMap.put("appName", a2);
            hashMap.put("namespace", "VNG");
            hashMap.put(MediationMetaData.KEY_VERSION, "2.2.0");
            hashMap.put("deviceOS", num);
            hashMap.put("isNative", str);
            hashMap.put(Cookie.APP_ID, b2);
            if (b3 != null) {
                hashMap.put("aqzx", b3);
            }
            return new JSONObject(hashMap).toString();
        } catch (Exception unused) {
            return "{}";
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        p.a(3, "JavaScriptBridge", (Object) this, "webViewReady");
        this.h.compareAndSet(false, true);
        e();
        for (String f2 : this.k) {
            f(f2);
        }
        this.k.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        if (bVar != null) {
            p.a(3, "JavaScriptBridge", (Object) this, "adding tracker" + bVar.b);
            this.f.put(bVar, "");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        String format = String.format("javascript: MoatMAK.crts(%s)", new Object[]{str});
        if (this.h.get()) {
            f(format);
        } else {
            d(format);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (!this.h.get() || !g()) {
            this.g.add(jSONObject2);
            return;
        }
        f(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        p.a(3, "JavaScriptBridge", (Object) this, "markUserInteractionEvent:" + str);
        String format = String.format("javascript: MoatMAK.ucbx(%s)", new Object[]{str});
        if (this.h.get()) {
            f(format);
        } else {
            d(format);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(b bVar) {
        try {
            if (g() && a(h(), "startTracking")) {
                if (e("startTracking")) {
                    if (bVar.d() == null) {
                        if (!bVar.c) {
                            p.a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null, won't start tracking");
                            return false;
                        }
                        p.a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null at start");
                    }
                    p.a(3, "JavaScriptBridge", (Object) this, "Starting tracking on tracker" + bVar.b);
                    f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{bVar.b}));
                    i.a().a(s.c(), bVar);
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            p.a("JavaScriptBridge", (Object) this, "Failed to initialize impression start.", (Throwable) e2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        p.a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
        this.h.compareAndSet(false, true);
        if (this.g.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (int i2 = 0; i2 < 10; i2++) {
                linkedList.addFirst(this.g.removeFirst());
            }
            int min = Math.min(Math.min(this.g.size() / 200, 10) + 200, this.g.size());
            for (int i3 = 0; i3 < min; i3++) {
                this.g.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.g.addFirst((String) it.next());
            }
        }
        int i4 = 0;
        while (!this.g.isEmpty() && i4 < 200) {
            i4++;
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            while (!this.g.isEmpty() && i4 < 200) {
                i4++;
                String first = this.g.getFirst();
                if (sb.length() + first.length() > 2000) {
                    break;
                }
                this.g.removeFirst();
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(first);
            }
            f(String.format("javascript:%s.dispatchMany([%s])", new Object[]{str, sb.toString()}));
        }
        this.g.clear();
    }

    /* access modifiers changed from: package-private */
    public boolean c(b bVar) {
        boolean z = false;
        if (g() && a(h(), "stopTracking") && e("stopTracking")) {
            try {
                p.a(3, "JavaScriptBridge", (Object) this, "Ending tracking on tracker" + bVar.b);
                f(String.format("javascript: MoatMAK.egpw(\"%s\")", new Object[]{bVar.b}));
            } catch (Exception e2) {
                p.a("JavaScriptBridge", (Object) this, "Failed to end impression.", (Throwable) e2);
            }
            z = true;
        }
        if (this.l == a.NATIVE_DISPLAY) {
            d(bVar);
        } else {
            f();
        }
        this.f.remove(bVar);
        return z;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            super.finalize();
            p.a(3, "JavaScriptBridge", (Object) this, "finalize");
            f();
        } catch (Exception e2) {
            m.a(e2);
        }
    }
}
