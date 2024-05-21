package com.moat.analytics.mobile.tjy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class n implements l {
    private final ScheduledExecutorService a;
    private ScheduledFuture b;
    private ScheduledFuture c;
    /* access modifiers changed from: private */
    public final ap d;
    /* access modifiers changed from: private */
    public int e = 0;
    private boolean f = false;
    private boolean g = false;
    private WebView h;
    private m i;

    n(Context context, ap apVar) {
        this.d = apVar;
        this.a = Executors.newScheduledThreadPool(1);
    }

    /* access modifiers changed from: private */
    public void b() {
        try {
            if (this.d.a() != ar.OFF) {
                if (this.d.b() && !this.g) {
                    Log.d("MoatJavaScriptBridge", "Ready for communication (setting environment variables).");
                    this.g = true;
                }
                this.h.loadUrl(String.format("javascript:(function(b,f){function g(){function b(a,e){for(k in a)if(a.hasOwnProperty(k)){var c=a[k].fn;if('function'===typeof c)try{e?c(e):c()}catch(d){}}}function d(a,b,c){'function'===typeof a&&(c[b]={ts:+new Date,fn:a})}bjmk={};uqaj={};yhgt={};ryup=dptk=!1;this.a=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash};this.bpsy=function(a){dptk||ryup||d(a,+new Date,bjmk)};this.qmrv=function(a){ryup||d(a,+new Date,uqaj)};this.lgpr=function(a,b){d(a,b,yhgt)};this.xrnk=function(a){yhgt.hasOwnProperty(a)&&delete yhgt[a]};this.vgft=function(){return dptk};this.lkpu=function(){return ryup};this.mqjh=function(){dptk||ryup||(dptk=!0,b(bjmk))};this.egpw=function(){ryup||(ryup=!0,b(uqaj))};this.sglu=function(a){b(yhgt,a);return 0<Object.keys(yhgt).length}}'undefined'===typeof b.MoatMAK&&(b.MoatMAK=new g,b.MoatMAK.a(f),b.__zMoatInit__=!0)})(window,%s);", new Object[]{this.i.b()}));
            }
        } catch (Exception e2) {
            if (this.d.b()) {
                Log.e("MoatJavaScriptBridge", "Failed to initialize communication (did not set environment variables).", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void c() {
        if (this.d.a() != ar.OFF) {
            if (this.h == null || (this.f && this.h.getUrl() == null)) {
                if (this.d.b()) {
                    StringBuilder sb = new StringBuilder("WebView became null");
                    sb.append(this.h == null ? "" : "based on null url");
                    sb.append(", stopping tracking loop");
                    Log.d("MoatJavaScriptBridge", sb.toString());
                }
                g();
                return;
            }
            if (this.h.getUrl() != null) {
                this.f = true;
            }
            String format = String.format("MoatMAK.sglu(%s)", new Object[]{this.i.a()});
            if (Build.VERSION.SDK_INT >= 19) {
                this.h.evaluateJavascript(format, new p(this));
                return;
            }
            WebView webView = this.h;
            webView.loadUrl("javascript:" + format);
        }
    }

    private void d() {
        if (this.d.b()) {
            Log.d("MoatJavaScriptBridge", "Starting metadata reporting loop");
        }
        this.c = this.a.scheduleWithFixedDelay(new q(this), 0, 50, TimeUnit.MILLISECONDS);
    }

    static /* synthetic */ int e(n nVar) {
        int i2 = nVar.e;
        nVar.e = i2 + 1;
        return i2;
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.c != null) {
            if (!this.c.isCancelled() && this.d.b()) {
                Log.d("MoatJavaScriptBridge", "Stopping metadata reporting loop");
            }
            this.c.cancel(true);
        }
    }

    private void f() {
        if (this.d.b()) {
            Log.d("MoatJavaScriptBridge", "Starting view update loop");
        }
        this.b = this.a.scheduleWithFixedDelay(new s(this), 0, (long) this.d.c(), TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.b != null) {
            if (this.b.isCancelled() && this.d.b()) {
                Log.d("MoatJavaScriptBridge", "Stopping view update loop");
            }
            this.b.cancel(true);
        }
    }

    public void a() {
        if (this.d.a() != ar.OFF) {
            e();
            g();
        }
    }

    public boolean a(WebView webView, m mVar) {
        boolean b2 = this.d.b();
        if (webView.getSettings().getJavaScriptEnabled()) {
            this.h = webView;
            this.i = mVar;
            d();
            f();
            this.a.schedule(new o(this), 10, TimeUnit.SECONDS);
            return true;
        } else if (!b2) {
            return false;
        } else {
            Log.e("MoatJavaScriptBridge", "JavaScript is not enabled in the given WebView. Can't track.");
            return false;
        }
    }
}
