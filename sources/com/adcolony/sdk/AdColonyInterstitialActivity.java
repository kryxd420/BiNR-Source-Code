package com.adcolony.sdk;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.adcolony.sdk.aa;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyInterstitialActivity extends b {
    AdColonyInterstitial n;
    private k o;

    public AdColonyInterstitialActivity() {
        AdColonyInterstitial adColonyInterstitial;
        if (!a.b()) {
            adColonyInterstitial = null;
        } else {
            adColonyInterstitial = a.a().u();
        }
        this.n = adColonyInterstitial;
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
        this.d = this.n == null ? 0 : this.n.e();
        super.onCreate(bundle);
        if (a.b() && this.n != null) {
            if (this.n.g()) {
                this.n.h().a(this.n.d());
            }
            this.o = new k(new Handler(Looper.getMainLooper()), this.n);
            if (this.n.getListener() != null) {
                this.n.getListener().onOpened(this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        super.a(afVar);
        d m = a.a().m();
        f remove = m.g().remove(this.e);
        if (remove != null) {
            for (MediaPlayer next : remove.c().c().values()) {
                if (next.isPlaying()) {
                    next.stop();
                }
                next.release();
            }
            remove.d().a().autoPause();
            remove.d().a().release();
        }
        JSONObject f = y.f(afVar.c(), "v4iap");
        JSONArray g = y.g(f, "product_ids");
        if (!(f == null || this.n == null || this.n.getListener() == null || g.length() <= 0)) {
            this.n.getListener().onIAPEvent(this.n, y.c(g, 0), y.c(f, "engagement_type"));
        }
        m.a(this.c);
        if (this.n != null) {
            m.c().remove(this.n.f());
            if (this.n.g()) {
                this.n.h().a();
            }
        }
        if (!(this.n == null || this.n.getListener() == null)) {
            this.n.getListener().onClosed(this.n);
            this.n.a((c) null);
            this.n.setListener((AdColonyInterstitialListener) null);
            this.n = null;
        }
        if (this.o != null) {
            this.o.a();
            this.o = null;
        }
        new aa.a().a("finish_ad call finished").a(aa.d);
    }

    /* access modifiers changed from: package-private */
    public void a(AdColonyInterstitial adColonyInterstitial) {
        this.n = adColonyInterstitial;
    }
}
