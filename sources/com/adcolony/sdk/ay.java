package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONObject;

class ay extends WebView implements ai {
    /* access modifiers changed from: private */
    public String a;
    private String b;
    private String c = "";
    /* access modifiers changed from: private */
    public String d = "";
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f = "";
    private String g = "";
    /* access modifiers changed from: private */
    public int h;
    private int i;
    private int j;
    private int k;
    private int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    public boolean o;
    private boolean p;
    /* access modifiers changed from: private */
    public boolean q;
    /* access modifiers changed from: private */
    public boolean r;
    private boolean s;
    /* access modifiers changed from: private */
    public JSONArray t = y.b();
    /* access modifiers changed from: private */
    public JSONObject u = y.a();
    /* access modifiers changed from: private */
    public c v;
    /* access modifiers changed from: private */
    public af w;

    public void b() {
    }

    ay(Context context, af afVar, int i2, int i3, c cVar) {
        super(context);
        this.w = afVar;
        a(afVar, i2, i3, cVar);
        d();
    }

    ay(Context context, int i2, boolean z) {
        super(context);
        this.m = i2;
        this.p = z;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (this.s) {
            new aa.a().a("Ignoring call to execute_js as WebView has been destroyed.").a(aa.b);
        } else if (Build.VERSION.SDK_INT >= 19) {
            evaluateJavascript(str, (ValueCallback) null);
        } else {
            loadUrl("javascript:" + str);
        }
    }

    public int a() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "id") == this.h && y.c(c2, "container_id") == this.v.d() && y.b(c2, "ad_session_id").equals(this.v.b());
    }

    /* access modifiers changed from: package-private */
    public void d() {
        a(false, (af) null);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, af afVar) {
        WebViewClient webViewClient;
        if (this.w == null) {
            this.w = afVar;
        }
        final JSONObject c2 = this.w.c();
        this.o = z;
        this.p = y.d(c2, "is_display_module");
        if (z) {
            String b2 = y.b(c2, "filepath");
            this.b = b2;
            this.a = "file://" + b2;
            this.u = y.f(c2, TJAdUnitConstants.String.VIDEO_INFO);
            this.n = true;
        }
        setFocusable(true);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (Build.VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                new aa.a().a("JS Alert: ").a(str2).a(aa.d);
                return true;
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Activity c;
                if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.WARNING) {
                    new aa.a().a("onConsoleMessage: ").a(consoleMessage.message()).a(aa.f);
                } else if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
                    if ((consoleMessage.message().contains("ADC3_update is not defined") || consoleMessage.message().contains("NativeLayer.dispatch_messages is not a function")) && (c = a.c()) != null && (c instanceof b)) {
                        af afVar = new af("AdSession.finish_fullscreen_ad", 0);
                        y.b(c2, NotificationCompat.CATEGORY_STATUS, 1);
                        new aa.a().a("Unable to communicate with ad, closing. Please ").a("ensure that you have added an exception for our ").a("Javascript interface in your ProGuard ").a("configuration and that you do not have a faulty ").a("proxy enabled on your device.").a(aa.g);
                        ((b) c).a(afVar);
                    }
                    new aa.a().a("onConsoleMessage: ").a(consoleMessage.message()).a(aa.h);
                }
                return true;
            }
        });
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            webViewClient = new a() {
                public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                    if (ay.this.v != null) {
                        JSONObject a2 = y.a();
                        y.b(a2, "id", ay.this.h);
                        y.a(a2, "ad_session_id", ay.this.e);
                        y.b(a2, "container_id", ay.this.v.d());
                        y.b(a2, "code", webResourceError.getErrorCode());
                        y.a(a2, TJAdUnitConstants.String.VIDEO_ERROR, webResourceError.getDescription().toString());
                        y.a(a2, "url", ay.this.a);
                        new af("WebView.on_error", ay.this.v.c(), a2).b();
                    }
                    new aa.a().a("onReceivedError: ").a(webResourceError.getDescription().toString()).a(aa.h);
                }

                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    if (!webResourceRequest.getUrl().toString().endsWith("mraid.js")) {
                        return null;
                    }
                    try {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ay.this.f.getBytes("UTF-8"));
                        boolean unused = ay.this.r = true;
                        return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
                    } catch (UnsupportedEncodingException unused2) {
                        new aa.a().a("UTF-8 not supported.").a(aa.h);
                        return null;
                    }
                }
            };
        } else {
            webViewClient = Build.VERSION.SDK_INT >= 21 ? new a() {
                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    if (!webResourceRequest.getUrl().toString().endsWith("mraid.js")) {
                        return null;
                    }
                    try {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ay.this.f.getBytes("UTF-8"));
                        boolean unused = ay.this.r = true;
                        return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
                    } catch (UnsupportedEncodingException unused2) {
                        new aa.a().a("UTF-8 not supported.").a(aa.h);
                        return null;
                    }
                }
            } : new a();
        }
        addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void dispatch_messages(String str) {
                JSONArray b = y.b(str);
                if (b == null) {
                    new aa.a().a("[INTERNAL] ADCJSON parse error in dispatch_messages ").a("javascript interface function").a(aa.g);
                    return;
                }
                for (int i = 0; i < b.length(); i++) {
                    a.a().q().a(y.d(b, i));
                }
            }
        }, "NativeLayer");
        setWebViewClient(webViewClient);
        if (this.p) {
            try {
                FileInputStream fileInputStream = new FileInputStream(this.b);
                StringBuilder sb = new StringBuilder(fileInputStream.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read < 0) {
                        break;
                    }
                    sb.append(new String(bArr, 0, read));
                }
                String sb2 = sb.toString();
                loadDataWithBaseURL(this.a, sb2.replaceFirst("var\\s*ADC_DEVICE_INFO\\s*=\\s*null\\s*;", "var ADC_DEVICE_INFO = " + y.b(y.f(c2, TJAdUnitConstants.String.VIDEO_INFO), "metadata") + ";\n"), "text/html", (String) null, (String) null);
            } catch (IOException e2) {
                a((Exception) e2);
                return;
            } catch (IllegalArgumentException e3) {
                a((Exception) e3);
                return;
            } catch (IndexOutOfBoundsException e4) {
                a((Exception) e4);
                return;
            }
        } else if (this.a.startsWith("http") || this.a.startsWith("file")) {
            loadUrl(this.a);
        } else {
            loadDataWithBaseURL(this.d.equals("") ? TJAdUnitConstants.String.DATA : this.d, z ? y.b(c2, TJAdUnitConstants.String.DATA) : this.a, "text/html", (String) null, (String) null);
        }
        if (!z) {
            e();
            f();
        }
        if (z || this.n) {
            a.a().q().a((ai) this);
        }
        if (!this.c.equals("")) {
            a(this.c);
        }
    }

    private boolean a(Exception exc) {
        AdColonyInterstitialListener listener;
        new aa.a().a(exc.getClass().toString()).a(" during metadata injection w/ metadata = ").a(y.b(this.u, "metadata")).a(aa.h);
        AdColonyInterstitial remove = a.a().m().c().remove(y.b(this.u, "ad_session_id"));
        if (remove == null || (listener = remove.getListener()) == null) {
            return false;
        }
        listener.onExpiring(remove);
        remove.a(true);
        return true;
    }

    private void b(Exception exc) {
        new aa.a().a(exc.getClass().toString()).a(" during metadata injection w/ metadata = ").a(y.b(this.u, "metadata")).a(aa.h);
        JSONObject a2 = y.a();
        y.a(a2, "id", this.e);
        new af("AdSession.on_error", this.v.c(), a2).b();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.v.n().add(a.a("WebView.set_visible", (ah) new ah() {
            public void a(af afVar) {
                if (ay.this.a(afVar)) {
                    ay.this.c(afVar);
                }
            }
        }, true));
        this.v.n().add(a.a("WebView.set_bounds", (ah) new ah() {
            public void a(af afVar) {
                if (ay.this.a(afVar)) {
                    ay.this.b(afVar);
                }
            }
        }, true));
        this.v.n().add(a.a("WebView.execute_js", (ah) new ah() {
            public void a(af afVar) {
                if (ay.this.a(afVar)) {
                    ay.this.a(y.b(afVar.c(), "custom_js"));
                }
            }
        }, true));
        this.v.o().add("WebView.set_visible");
        this.v.o().add("WebView.set_bounds");
        this.v.o().add("WebView.execute_js");
    }

    /* access modifiers changed from: package-private */
    public void f() {
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.k, this.l);
        layoutParams.setMargins(this.i, this.j, 0, 0);
        layoutParams.gravity = 0;
        this.v.addView(this, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar, int i2, c cVar) {
        a(afVar, i2, -1, cVar);
        f();
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar, int i2, int i3, c cVar) {
        JSONObject c2 = afVar.c();
        this.a = y.b(c2, "url");
        if (this.a.equals("")) {
            this.a = y.b(c2, TJAdUnitConstants.String.DATA);
        }
        this.d = y.b(c2, "base_url");
        this.c = y.b(c2, "custom_js");
        this.e = y.b(c2, "ad_session_id");
        this.u = y.f(c2, TJAdUnitConstants.String.VIDEO_INFO);
        this.g = y.b(c2, "mraid_filepath");
        boolean z = false;
        if (!this.p) {
            try {
                this.f = a.a().j().a(this.g, false).toString();
                this.f = this.f.replaceFirst("bridge.os_name\\s*=\\s*\"\"\\s*;", "bridge.os_name = \"\";\nvar ADC_DEVICE_INFO = " + this.u.toString() + ";\n");
            } catch (IOException e2) {
                b((Exception) e2);
            } catch (IllegalArgumentException e3) {
                b((Exception) e3);
            } catch (IndexOutOfBoundsException e4) {
                b((Exception) e4);
            }
        }
        this.h = i2;
        this.v = cVar;
        if (i3 >= 0) {
            this.m = i3;
        } else {
            e();
        }
        this.k = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.l = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        this.i = y.c(c2, AvidJSONUtil.KEY_X);
        this.j = y.c(c2, AvidJSONUtil.KEY_Y);
        if (y.d(c2, "enable_messages") || this.o) {
            z = true;
        }
        this.n = z;
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        JSONObject c2 = afVar.c();
        this.i = y.c(c2, AvidJSONUtil.KEY_X);
        this.j = y.c(c2, AvidJSONUtil.KEY_Y);
        this.k = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.l = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.i, this.j, 0, 0);
        layoutParams.width = this.k;
        layoutParams.height = this.l;
        setLayoutParams(layoutParams);
        if (this.o) {
            JSONObject a2 = y.a();
            y.a(a2, "success", true);
            y.b(a2, "id", this.m);
            afVar.a(a2).b();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        if (y.d(afVar.c(), TJAdUnitConstants.String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        if (this.o) {
            JSONObject a2 = y.a();
            y.a(a2, "success", true);
            y.b(a2, "id", this.m);
            afVar.a(a2).b();
        }
    }

    public void a(JSONObject jSONObject) {
        synchronized (this.t) {
            this.t.put(jSONObject);
        }
    }

    public void c() {
        if (a.d() && this.q) {
            aw.a((Runnable) new Runnable() {
                public void run() {
                    String str = "";
                    synchronized (ay.this.t) {
                        if (ay.this.t.length() > 0) {
                            if (ay.this.n) {
                                str = ay.this.t.toString();
                            }
                            JSONArray unused = ay.this.t = y.b();
                        }
                    }
                    if (ay.this.n) {
                        ay ayVar = ay.this;
                        ayVar.a("NativeLayer.dispatch_messages(ADC3_update(" + str + "));");
                    }
                }
            });
        }
    }

    private class a extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        private a() {
        }

        public void onLoadResource(WebView webView, String str) {
            if (str.equals(ay.this.a)) {
                ay.this.a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            boolean unused = ay.this.q = false;
            boolean unused2 = ay.this.r = false;
            new aa.a().a("onPageStarted with URL = ").a(str).a(aa.d);
        }

        public void onPageFinished(WebView webView, String str) {
            JSONObject a = y.a();
            y.b(a, "id", ay.this.h);
            y.a(a, "url", str);
            new aa.a().a("onPageFinished called with URL = ").a(str).a(aa.b);
            if (ay.this.v == null) {
                new af("WebView.on_load", ay.this.m, a).b();
            } else {
                y.a(a, "ad_session_id", ay.this.e);
                y.b(a, "container_id", ay.this.v.d());
                new af("WebView.on_load", ay.this.v.c(), a).b();
            }
            if ((ay.this.n || ay.this.o) && !ay.this.q && (str.startsWith(TJAdUnitConstants.String.DATA) || str.startsWith("file") || str.equals(ay.this.d) || ay.this.r)) {
                new aa.a().a("WebView data loaded - executing ADC3_init").a(aa.b);
                new aa.a().a("==============================================================").a(aa.b);
                new aa.a().a("ADC3_init(").a(ay.this.m).a(",").a(ay.this.u.toString()).a(");").a(aa.b);
                new aa.a().a("==============================================================").a(aa.b);
                ay ayVar = ay.this;
                ayVar.a("ADC3_init(" + ay.this.m + "," + ay.this.u.toString() + ");");
                boolean unused = ay.this.q = true;
            }
            if (ay.this.o) {
                JSONObject a2 = y.a();
                y.a(a2, "success", true);
                y.b(a2, "id", ay.this.m);
                ay.this.w.a(a2).b();
            }
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (Build.VERSION.SDK_INT >= 21 || !str.endsWith("mraid.js")) {
                return null;
            }
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ay.this.f.getBytes("UTF-8"));
                boolean unused = ay.this.r = true;
                return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
            } catch (UnsupportedEncodingException unused2) {
                new aa.a().a("UTF-8 not supported.").a(aa.h);
                return null;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (Build.VERSION.SDK_INT < 23) {
                JSONObject a = y.a();
                y.b(a, "id", ay.this.h);
                y.a(a, "ad_session_id", ay.this.e);
                y.b(a, "container_id", ay.this.v.d());
                y.b(a, "code", i);
                y.a(a, TJAdUnitConstants.String.VIDEO_ERROR, str);
                y.a(a, "url", str2);
                new af("WebView.on_error", ay.this.v.c(), a).b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.s = z;
    }
}
