package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.l;
import com.applovin.impl.sdk.t;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.atomic.AtomicBoolean;

class h implements t.a {
    /* access modifiers changed from: private */
    public static AlertDialog b;
    /* access modifiers changed from: private */
    public static final AtomicBoolean c = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final i a;
    private l d;

    public interface a {
        void a();

        void b();
    }

    h(i iVar, j jVar) {
        this.a = iVar;
        jVar.R().a((t.a) this);
    }

    public void a(long j, final j jVar, final a aVar) {
        if (j > 0) {
            if (b == null || !b.isShowing()) {
                if (c.getAndSet(true)) {
                    if (j < this.d.a()) {
                        p v = jVar.v();
                        v.a("ConsentAlertManager", "Scheduling consent alert earlier (" + j + "ms) than remaining scheduled time (" + this.d.a() + "ms)");
                        this.d.d();
                    } else {
                        p v2 = jVar.v();
                        v2.c("ConsentAlertManager", "Skip scheduling consent alert - one scheduled already with remaining time of " + this.d.a() + " milliseconds");
                        return;
                    }
                }
                p v3 = jVar.v();
                v3.a("ConsentAlertManager", "Scheduling consent alert for " + j + " milliseconds");
                this.d = l.a(j, jVar, new Runnable() {
                    public void run() {
                        p v;
                        String str;
                        String str2;
                        if (h.this.a.c()) {
                            jVar.v().d("ConsentAlertManager", "Consent dialog already showing, skip showing of consent alert");
                            return;
                        }
                        Activity a2 = jVar.T().a();
                        if (a2 == null || !f.a(jVar.x(), jVar)) {
                            if (a2 == null) {
                                v = jVar.v();
                                str = "ConsentAlertManager";
                                str2 = "No parent Activity found - rescheduling consent alert...";
                            } else {
                                v = jVar.v();
                                str = "ConsentAlertManager";
                                str2 = "No internet available - rescheduling consent alert...";
                            }
                            v.d(str, str2);
                            h.c.set(false);
                            h.this.a(((Long) jVar.a(b.aw)).longValue(), jVar, aVar);
                            return;
                        }
                        AppLovinSdkUtils.runOnUiThread(new Runnable() {
                            public void run() {
                                AlertDialog unused = h.b = new AlertDialog.Builder(jVar.T().a()).setTitle((CharSequence) jVar.a(b.ax)).setMessage((CharSequence) jVar.a(b.ay)).setCancelable(false).setPositiveButton((CharSequence) jVar.a(b.az), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        aVar.a();
                                        dialogInterface.dismiss();
                                        h.c.set(false);
                                    }
                                }).setNegativeButton((CharSequence) jVar.a(b.aA), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        aVar.b();
                                        dialogInterface.dismiss();
                                        h.c.set(false);
                                        h.this.a(((Long) jVar.a(b.av)).longValue(), jVar, aVar);
                                    }
                                }).create();
                                h.b.show();
                            }
                        });
                    }
                });
            }
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.b();
        }
    }

    public void c() {
        if (this.d != null) {
            this.d.c();
        }
    }
}
