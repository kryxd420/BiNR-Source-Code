package com.adcolony.sdk;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyAdViewActivity extends b {
    az n;
    boolean o;

    public AdColonyAdViewActivity() {
        az azVar;
        if (!a.b()) {
            azVar = null;
        } else {
            azVar = a.a().t();
        }
        this.n = azVar;
        this.o = this.n instanceof AdColonyNativeAdView;
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void onCreate(Bundle bundle) {
        this.d = this.n == null ? 0 : this.n.b;
        super.onCreate(bundle);
        if (a.b() && this.n != null) {
            a.a().d(true);
            e listener = this.n.getListener();
            if (listener != null && (listener instanceof AdColonyNativeAdViewListener)) {
                ((AdColonyNativeAdViewListener) listener).onOpened((AdColonyNativeAdView) this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        super.a(afVar);
        if (this.n.getExpandedContainer() != null) {
            JSONObject f = y.f(afVar.c(), "v4iap");
            JSONArray g = y.g(f, "product_ids");
            e listener = this.n.getListener();
            if (listener != null) {
                if (this.o) {
                    AdColonyNativeAdViewListener adColonyNativeAdViewListener = (AdColonyNativeAdViewListener) listener;
                    adColonyNativeAdViewListener.onClosed((AdColonyNativeAdView) this.n);
                    if (f != null && g.length() > 0) {
                        adColonyNativeAdViewListener.onIAPEvent((AdColonyNativeAdView) this.n, y.c(g, 0), y.c(f, "engagement_type"));
                    }
                } else {
                    ba baVar = (ba) listener;
                    baVar.c(this.n);
                    if (f != null && g.length() > 0) {
                        baVar.a(this.n, y.c(g, 0), y.c(f, "engagement_type"));
                    }
                }
            }
            ((ViewGroup) this.n.getExpandedContainer().getParent()).removeView(this.n.getExpandedContainer());
            a.a().m().a(this.n.getExpandedContainer());
            this.n.setExpandedContainer((c) null);
            System.gc();
        }
    }
}
