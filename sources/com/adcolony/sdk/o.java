package com.adcolony.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.adcolony.sdk.aa;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

class o {
    static AlertDialog a;
    /* access modifiers changed from: private */
    public af b;
    /* access modifiers changed from: private */
    public AlertDialog c;
    /* access modifiers changed from: private */
    public boolean d;

    o() {
        a.a("Alert.show", (ah) new ah() {
            public void a(af afVar) {
                if (!a.d()) {
                    new aa.a().a("Null Activity reference, can't build AlertDialog.").a(aa.g);
                } else if (y.d(afVar.c(), "on_resume")) {
                    af unused = o.this.b = afVar;
                } else {
                    o.this.a(afVar);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(final af afVar) {
        Activity c2 = a.c();
        if (c2 != null) {
            final AlertDialog.Builder builder = a.a().n().r() >= 21 ? new AlertDialog.Builder(c2, 16974374) : new AlertDialog.Builder(c2, 16974126);
            JSONObject c3 = afVar.c();
            String b2 = y.b(c3, "message");
            String b3 = y.b(c3, TJAdUnitConstants.String.TITLE);
            String b4 = y.b(c3, "positive");
            String b5 = y.b(c3, "negative");
            builder.setMessage(b2);
            builder.setTitle(b3);
            builder.setPositiveButton(b4, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialog unused = o.this.c = null;
                    dialogInterface.dismiss();
                    JSONObject a2 = y.a();
                    y.a(a2, "positive", true);
                    boolean unused2 = o.this.d = false;
                    afVar.a(a2).b();
                }
            });
            if (!b5.equals("")) {
                builder.setNegativeButton(b5, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog unused = o.this.c = null;
                        dialogInterface.dismiss();
                        JSONObject a2 = y.a();
                        y.a(a2, "positive", false);
                        boolean unused2 = o.this.d = false;
                        afVar.a(a2).b();
                    }
                });
            }
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    AlertDialog unused = o.this.c = null;
                    boolean unused2 = o.this.d = false;
                    JSONObject a2 = y.a();
                    y.a(a2, "positive", false);
                    afVar.a(a2).b();
                }
            });
            aw.a((Runnable) new Runnable() {
                public void run() {
                    boolean unused = o.this.d = true;
                    AlertDialog unused2 = o.this.c = builder.show();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.b != null) {
            a(this.b);
            this.b = null;
        }
    }

    /* access modifiers changed from: package-private */
    public AlertDialog b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void a(AlertDialog alertDialog) {
        this.c = alertDialog;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.d;
    }
}
