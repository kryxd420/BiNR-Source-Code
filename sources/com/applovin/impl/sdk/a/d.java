package com.applovin.impl.sdk.a;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.j;

public class d {
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public final com.applovin.impl.adview.j b;
    /* access modifiers changed from: private */
    public AlertDialog c;

    public d(com.applovin.impl.adview.j jVar, j jVar2) {
        this.a = jVar2;
        this.b = jVar;
    }

    public void a() {
        this.b.runOnUiThread(new Runnable() {
            public void run() {
                if (d.this.c != null) {
                    d.this.c.dismiss();
                }
            }
        });
    }

    public void b() {
        this.b.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(d.this.b);
                builder.setTitle((CharSequence) d.this.a.a(b.bP));
                builder.setMessage((CharSequence) d.this.a.a(b.bQ));
                builder.setCancelable(false);
                builder.setPositiveButton((CharSequence) d.this.a.a(b.bS), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        d.this.b.continueVideo();
                        d.this.b.resumeReportRewardTask();
                    }
                });
                builder.setNegativeButton((CharSequence) d.this.a.a(b.bR), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        d.this.b.skipVideo();
                        d.this.b.resumeReportRewardTask();
                    }
                });
                AlertDialog unused = d.this.c = builder.show();
            }
        });
    }

    public void c() {
        this.b.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(d.this.b);
                builder.setTitle((CharSequence) d.this.a.a(b.bU));
                builder.setMessage((CharSequence) d.this.a.a(b.bV));
                builder.setCancelable(false);
                builder.setPositiveButton((CharSequence) d.this.a.a(b.bX), (DialogInterface.OnClickListener) null);
                builder.setNegativeButton((CharSequence) d.this.a.a(b.bW), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        d.this.b.dismiss();
                    }
                });
                AlertDialog unused = d.this.c = builder.show();
            }
        });
    }

    public boolean d() {
        if (this.c != null) {
            return this.c.isShowing();
        }
        return false;
    }
}
