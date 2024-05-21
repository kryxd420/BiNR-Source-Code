package com.moat.analytics.mobile.vng;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.a.a.a;
import com.moat.analytics.mobile.vng.v;
import com.moat.analytics.mobile.vng.x;
import java.lang.ref.WeakReference;
import java.util.Map;

class n extends MoatFactory {
    n() {
        if (!a()) {
            p.a(3, "Factory", (Object) this, "Failed to initialize MoatFactory. Please check that you've initialized the Moat SDK correctly.");
            p.a("[ERROR] ", "Failed to initialize MoatFactory, SDK was not started");
            throw new m();
        }
    }

    private NativeDisplayTracker a(View view, final Map<String, String> map) {
        a.a(view);
        a.a(map);
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) x.a(new x.a<NativeDisplayTracker>() {
            public com.moat.analytics.mobile.vng.a.b.a<NativeDisplayTracker> a() {
                View view = (View) weakReference.get();
                if (view == null) {
                    p.a(3, "Factory", (Object) this, "Target view is null. Not creating NativeDisplayTracker.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, subject view is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                } else if (map == null || map.isEmpty()) {
                    p.a(3, "Factory", (Object) this, "adIds is null or empty. NativeDisplayTracker initialization failed.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, adIds is null or empty");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                } else {
                    p.a(3, "Factory", (Object) this, "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                    p.a("[INFO] ", "Attempting to create NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                    return com.moat.analytics.mobile.vng.a.b.a.a(new t(view, map));
                }
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker a(final String str) {
        return (NativeVideoTracker) x.a(new x.a<NativeVideoTracker>() {
            public com.moat.analytics.mobile.vng.a.b.a<NativeVideoTracker> a() {
                if (str == null || str.isEmpty()) {
                    p.a(3, "Factory", (Object) this, "partnerCode is null or empty. NativeVideoTracker initialization failed.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, partnerCode is null or empty");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", (Object) this, "Creating NativeVideo tracker.");
                p.a("[INFO] ", "Attempting to create NativeVideoTracker");
                return com.moat.analytics.mobile.vng.a.b.a.a(new u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker a(ViewGroup viewGroup) {
        a.a(viewGroup);
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() {
            public com.moat.analytics.mobile.vng.a.b.a<WebAdTracker> a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                if (viewGroup == null) {
                    p.a(3, "Factory", (Object) this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    p.a("[ERROR] ", "WebAdTracker not created, adContainer ViewGroup is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", (Object) this, "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                p.a("[INFO] ", "Attempting to create WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                com.moat.analytics.mobile.vng.a.b.a<WebView> a2 = ab.a(viewGroup);
                boolean c = a2.c();
                StringBuilder sb = new StringBuilder();
                sb.append("WebView ");
                sb.append(c ? "" : "not ");
                sb.append("found inside of ad container.");
                p.a(3, "Factory", (Object) this, sb.toString());
                if (!c) {
                    p.a("[ERROR] ", "WebAdTracker not created, no WebView to track inside of ad container");
                }
                return com.moat.analytics.mobile.vng.a.b.a.a(new aa(a2.c(null)));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker a(WebView webView) {
        a.a(webView);
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() {
            public com.moat.analytics.mobile.vng.a.b.a<WebAdTracker> a() {
                WebView webView = (WebView) weakReference.get();
                if (webView == null) {
                    p.a(3, "Factory", (Object) this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    p.a("[ERROR] ", "WebAdTracker not created, webView is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", (Object) this, "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                p.a("[INFO] ", "Attempting to create WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                return com.moat.analytics.mobile.vng.a.b.a.a(new aa(webView));
            }
        }, WebAdTracker.class);
    }

    private <T> T a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }

    private boolean a() {
        return ((k) k.getInstance()).a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return a(moatPlugin);
        } catch (Exception e) {
            m.a(e);
            return moatPlugin.b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
        try {
            return a(view, map);
        } catch (Exception e) {
            m.a(e);
            return new v.c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return a(str);
        } catch (Exception e) {
            m.a(e);
            return new v.d();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return a(viewGroup);
        } catch (Exception e) {
            m.a(e);
            return new v.e();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return a(webView);
        } catch (Exception e) {
            m.a(e);
            return new v.e();
        }
    }
}
