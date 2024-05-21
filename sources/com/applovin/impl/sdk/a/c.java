package com.applovin.impl.sdk.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Timer;
import java.util.TimerTask;

class c {
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public final a b;
    /* access modifiers changed from: private */
    public final Activity c;
    /* access modifiers changed from: private */
    public final Runnable d;
    /* access modifiers changed from: private */
    public final AppLovinAdRewardListener e;
    /* access modifiers changed from: private */
    public final Timer f;

    static class a {
        /* access modifiers changed from: private */
        public j a;
        /* access modifiers changed from: private */
        public a b;
        /* access modifiers changed from: private */
        public Activity c;
        /* access modifiers changed from: private */
        public AppLovinAdRewardListener d;
        /* access modifiers changed from: private */
        public Runnable e;

        private a() {
        }

        /* access modifiers changed from: package-private */
        public a a(Activity activity) {
            this.c = activity;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(a aVar) {
            this.b = aVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(j jVar) {
            this.a = jVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(AppLovinAdRewardListener appLovinAdRewardListener) {
            this.d = appLovinAdRewardListener;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(Runnable runnable) {
            this.e = runnable;
            return this;
        }

        /* access modifiers changed from: package-private */
        public c a() {
            return new c(this);
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.e;
        this.e = aVar.d;
        this.f = new Timer("IncentivizedAdLauncher");
    }

    static a a() {
        return new a();
    }

    /* access modifiers changed from: package-private */
    public void a(final AppLovinAd appLovinAd) {
        this.c.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(c.this.c);
                builder.setTitle((CharSequence) c.this.a.a(b.bE));
                builder.setMessage((CharSequence) c.this.a.a(b.bF));
                builder.setCancelable(false);
                builder.setPositiveButton((CharSequence) c.this.a.a(b.bG), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        c.this.a.E().a(g.k);
                        c.this.f.schedule(new TimerTask() {
                            public void run() {
                                c.this.c.runOnUiThread(c.this.d);
                            }
                        }, 200);
                    }
                });
                builder.setNegativeButton((CharSequence) c.this.a.a(b.bH), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        c.this.a.E().a(g.l);
                        c.this.b.a(appLovinAd, c.this.e);
                    }
                });
                builder.show();
            }
        });
    }
}
