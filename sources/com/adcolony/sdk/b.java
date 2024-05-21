package com.adcolony.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.adcolony.sdk.aa;
import com.tapdaq.sdk.TapdaqPlacement;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

class b extends Activity {
    final int a = 0;
    final int b = 1;
    c c;
    int d = -1;
    String e;
    int f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    f m;

    b() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a.b() || a.a().s() == null) {
            finish();
            return;
        }
        l a2 = a.a();
        this.i = false;
        this.c = a2.s();
        this.c.b(false);
        if (aw.g()) {
            this.c.b(true);
        }
        this.e = this.c.b();
        this.f = this.c.c();
        this.m = a.a().m().g().get(this.e);
        this.j = a2.d().getMultiWindowEnabled();
        if (this.j) {
            getWindow().addFlags(2048);
            getWindow().clearFlags(1024);
        } else {
            getWindow().addFlags(1024);
            getWindow().clearFlags(2048);
        }
        requestWindowFeature(1);
        getWindow().getDecorView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (a2.d().getKeepScreenOn()) {
            getWindow().addFlags(128);
        }
        ViewParent parent = this.c.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.c);
        }
        setContentView(this.c);
        this.c.n().add(a.a("AdSession.finish_fullscreen_ad", (ah) new ah() {
            public void a(af afVar) {
                b.this.a(afVar);
            }
        }, true));
        this.c.n().add(a.a("AdSession.change_orientation", (ah) new ah() {
            public void a(af afVar) {
                JSONObject c = afVar.c();
                if (y.b(c, "id").equals(b.this.e)) {
                    b.this.a(y.c(c, "orientation"));
                }
            }
        }, true));
        this.c.o().add("AdSession.finish_fullscreen_ad");
        this.c.o().add("AdSession.change_orientation");
        a(this.d);
        if (!this.c.t()) {
            JSONObject a3 = y.a();
            y.a(a3, "id", this.c.b());
            y.b(a3, "screen_width", this.c.q());
            y.b(a3, "screen_height", this.c.p());
            new aa.a().a("AdSession.on_fullscreen_ad_started").a(aa.b);
            new af("AdSession.on_fullscreen_ad_started", this.c.c(), a3).b();
            this.c.c(true);
            return;
        }
        a();
    }

    public void onPause() {
        super.onPause();
        a(this.h);
        this.h = false;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.m = a.a().m().g().get(this.e);
        Iterator<Map.Entry<Integer, ax>> it = this.c.e().entrySet().iterator();
        while (it.hasNext() && !isFinishing()) {
            ax axVar = (ax) it.next().getValue();
            if (!axVar.j() && axVar.i().isPlaying()) {
                axVar.f();
            }
        }
        if (this.m != null) {
            this.m.a();
        }
        AdColonyInterstitial u = a.a().u();
        if (u != null && u.g() && u.h().e() != null && z && this.k) {
            u.h().b(TapdaqPlacement.TDPTagPause);
        }
    }

    public void onResume() {
        super.onResume();
        a();
        b(this.h);
        this.h = true;
        this.l = true;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        for (Map.Entry<Integer, ax> value : this.c.e().entrySet()) {
            ax axVar = (ax) value.getValue();
            if (!axVar.j() && !axVar.i().isPlaying() && !a.a().r().c()) {
                axVar.e();
            }
        }
        if (this.m != null) {
            this.m.b();
        }
        AdColonyInterstitial u = a.a().u();
        if (u != null && u.g() && u.h().e() != null) {
            if ((!z || (z && !this.k)) && this.l) {
                u.h().b("resume");
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z && this.h) {
            a.a().l().c(true);
            b(this.h);
            this.k = true;
        } else if (!z && this.h) {
            new aa.a().a("Activity is active but window does not have focus, pausing.").a(aa.d);
            a.a().l().b(true);
            a(this.h);
            this.k = false;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (a.b() && this.c != null && !this.g) {
            if ((Build.VERSION.SDK_INT < 24 || !aw.g()) && !this.c.s()) {
                JSONObject a2 = y.a();
                y.a(a2, "id", this.c.b());
                new af("AdSession.on_error", this.c.c(), a2).b();
                this.i = true;
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    public void onBackPressed() {
        JSONObject a2 = y.a();
        y.a(a2, "id", this.c.b());
        new af("AdSession.on_back_button", this.c.c(), a2).b();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        l a2 = a.a();
        if (this.c == null) {
            this.c = a2.s();
        }
        if (this.c != null) {
            this.c.b(false);
            if (aw.g()) {
                this.c.b(true);
            }
            int p = a2.c.p();
            int q = this.j ? a2.c.q() - aw.b(a.c()) : a2.c.q();
            if (p > 0 && q > 0) {
                JSONObject a3 = y.a();
                y.b(a3, "screen_width", p);
                y.b(a3, "screen_height", q);
                y.a(a3, "ad_session_id", this.c.b());
                y.b(a3, "id", this.c.d());
                this.c.setLayoutParams(new FrameLayout.LayoutParams(p, q));
                this.c.b(p);
                this.c.a(q);
                new af("AdContainer.on_orientation_change", this.c.c(), a3).b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        int c2 = y.c(afVar.c(), NotificationCompat.CATEGORY_STATUS);
        if ((c2 == 5 || c2 == 0 || c2 == 6 || c2 == 1) && !this.g) {
            l a2 = a.a();
            o r = a2.r();
            a2.b(afVar);
            if (r.b() != null) {
                r.b().dismiss();
                r.a((AlertDialog) null);
            }
            if (!this.i) {
                finish();
            }
            this.g = true;
            ((ViewGroup) getWindow().getDecorView()).removeAllViews();
            a2.c(false);
            JSONObject a3 = y.a();
            y.a(a3, "id", this.c.b());
            new af("AdSession.on_close", this.c.c(), a3).b();
            a2.a((c) null);
            a2.a((AdColonyInterstitial) null);
            a2.a((az) null);
            a.a().m().c().remove(this.c.b());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        switch (i2) {
            case 0:
                setRequestedOrientation(7);
                break;
            case 1:
                setRequestedOrientation(6);
                break;
            default:
                setRequestedOrientation(4);
                break;
        }
        this.d = i2;
    }
}
