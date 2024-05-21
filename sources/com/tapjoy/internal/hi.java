package com.tapjoy.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import com.tapjoy.TJContentActivity;

public class hi extends hk {
    private static final String h = "hi";
    /* access modifiers changed from: private */
    public static hi i;
    final String a;
    final hy b;
    /* access modifiers changed from: private */
    public final hd j;
    /* access modifiers changed from: private */
    public c k;
    private boolean l;
    /* access modifiers changed from: private */
    public long m;
    private Context n;
    /* access modifiers changed from: private */
    public boolean o = false;

    public static void a() {
        hi hiVar = i;
        if (hiVar != null) {
            hiVar.e();
        }
    }

    public hi(hd hdVar, String str, hy hyVar, Context context) {
        this.j = hdVar;
        this.a = str;
        this.b = hyVar;
        this.n = context;
    }

    public final void b() {
        hy hyVar = this.b;
        if (hyVar.a != null) {
            hyVar.a.b();
        }
        if (hyVar.b != null) {
            hyVar.b.b();
        }
        hyVar.c.b();
        if (hyVar.e != null) {
            hyVar.e.b();
        }
        if (hyVar.f != null) {
            hyVar.f.b();
        }
        if (hyVar.m != null && hyVar.m.a != null) {
            hyVar.m.a.b();
        }
    }

    public final boolean c() {
        hy hyVar = this.b;
        if (hyVar.c == null || hyVar.c.b == null) {
            return false;
        }
        if (hyVar.m != null && hyVar.m.a != null && hyVar.m.a.b == null) {
            return false;
        }
        if (hyVar.b == null || hyVar.f == null || hyVar.b.b == null || hyVar.f.b == null) {
            return (hyVar.a == null || hyVar.e == null || hyVar.a.b == null || hyVar.e.b == null) ? false : true;
        }
        return true;
    }

    public final void a(final he heVar, final ga gaVar) {
        Activity a2 = a.a(this.n);
        if (a2 != null && !a2.isFinishing()) {
            try {
                a(a2, heVar, gaVar);
                new Object[1][0] = this.a;
                return;
            } catch (WindowManager.BadTokenException unused) {
            }
        }
        Activity a3 = gv.a();
        try {
            TJContentActivity.start(hd.a().e, new TJContentActivity.AbstractContentProducer() {
                public final void show(Activity activity) {
                    try {
                        hi.this.a(activity, heVar, gaVar);
                    } catch (WindowManager.BadTokenException unused) {
                        ha.b("Failed to show the content for \"{}\" caused by invalid activity", hi.this.a);
                        heVar.a(hi.this.a, hi.this.f, (gp) null);
                    }
                }

                public final void dismiss(Activity activity) {
                    hi.this.e();
                }
            }, (a3 == null || (a3.getWindow().getAttributes().flags & 1024) == 0) ? false : true);
            new Object[1][0] = this.a;
        } catch (ActivityNotFoundException unused2) {
            if (a3 != null && !a3.isFinishing()) {
                try {
                    a(a3, heVar, gaVar);
                    new Object[1][0] = this.a;
                    return;
                } catch (WindowManager.BadTokenException unused3) {
                    ha.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.a);
                    heVar.a(this.a, this.f, (gp) null);
                }
            }
            ha.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.a);
            heVar.a(this.a, this.f, (gp) null);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final android.app.Activity r12, final com.tapjoy.internal.he r13, com.tapjoy.internal.ga r14) {
        /*
            r11 = this;
            boolean r0 = r11.l
            if (r0 == 0) goto L_0x0013
            java.lang.String r12 = h
            com.tapjoy.TapjoyErrorMessage r13 = new com.tapjoy.TapjoyErrorMessage
            com.tapjoy.TapjoyErrorMessage$ErrorType r14 = com.tapjoy.TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR
            java.lang.String r0 = "Content is already displayed"
            r13.<init>(r14, r0)
            com.tapjoy.TapjoyLog.e((java.lang.String) r12, (com.tapjoy.TapjoyErrorMessage) r13)
            return
        L_0x0013:
            r0 = 1
            r11.l = r0
            i = r11
            com.tapjoy.internal.fu r1 = r14.a
            r11.g = r1
            com.tapjoy.internal.c r1 = new com.tapjoy.internal.c
            r1.<init>(r12)
            r11.k = r1
            com.tapjoy.internal.c r1 = r11.k
            com.tapjoy.internal.hi$2 r2 = new com.tapjoy.internal.hi$2
            r2.<init>(r13)
            r1.setOnCancelListener(r2)
            com.tapjoy.internal.c r1 = r11.k
            com.tapjoy.internal.hi$3 r2 = new com.tapjoy.internal.hi$3
            r2.<init>(r12, r13)
            r1.setOnDismissListener(r2)
            com.tapjoy.internal.c r1 = r11.k
            r2 = 0
            r1.setCanceledOnTouchOutside(r2)
            com.tapjoy.internal.iu r1 = new com.tapjoy.internal.iu
            com.tapjoy.internal.hy r3 = r11.b
            com.tapjoy.internal.hi$4 r4 = new com.tapjoy.internal.hi$4
            r4.<init>(r12, r13)
            r1.<init>(r12, r3, r4)
            com.tapjoy.internal.it r3 = new com.tapjoy.internal.it
            com.tapjoy.internal.hy r4 = r11.b
            r3.<init>(r12, r4, r1)
            android.widget.FrameLayout r1 = new android.widget.FrameLayout
            r1.<init>(r12)
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams
            r5 = 17
            r6 = -2
            r4.<init>(r6, r6, r5)
            r1.addView(r3, r4)
            com.tapjoy.internal.c r4 = r11.k
            r4.setContentView(r1)
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0184
            com.tapjoy.internal.c r1 = r11.k
            android.view.Window r1 = r1.getWindow()
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 16
            if (r4 != r5) goto L_0x009a
            java.lang.String r4 = "4.1.2"
            java.lang.String r5 = android.os.Build.VERSION.RELEASE
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x009a
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            android.content.Context r5 = r1.getContext()
            java.lang.Boolean r5 = a((android.content.Context) r5)
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0095
            r1 = 0
            goto L_0x009b
        L_0x0095:
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            r1.setFlags(r4, r4)
        L_0x009a:
            r1 = 1
        L_0x009b:
            if (r1 == 0) goto L_0x0184
            int r1 = com.tapjoy.internal.ag.a.b
            com.tapjoy.internal.ah r4 = new com.tapjoy.internal.ah
            r4.<init>()
            int[] r5 = com.tapjoy.internal.ag.AnonymousClass1.a
            int r1 = r1 - r0
            r1 = r5[r1]
            r5 = -1093874483(0xffffffffbecccccd, float:-0.4)
            r6 = -1032847360(0xffffffffc2700000, float:-60.0)
            r7 = 1114636288(0x42700000, float:60.0)
            r8 = 1050253722(0x3e99999a, float:0.3)
            r9 = 1053609165(0x3ecccccd, float:0.4)
            r10 = 1065353216(0x3f800000, float:1.0)
            switch(r1) {
                case 1: goto L_0x014b;
                case 2: goto L_0x011c;
                case 3: goto L_0x00ed;
                case 4: goto L_0x00bd;
                default: goto L_0x00bb;
            }
        L_0x00bb:
            goto L_0x0179
        L_0x00bd:
            com.tapjoy.internal.aj r1 = new com.tapjoy.internal.aj
            r1.<init>()
            r1.a = r0
            r1.b = r7
            com.tapjoy.internal.ai r0 = r1.a()
            com.tapjoy.internal.ah r0 = r4.a(r0)
            android.view.animation.ScaleAnimation r1 = new android.view.animation.ScaleAnimation
            r1.<init>(r9, r10, r9, r10)
            com.tapjoy.internal.ah r0 = r0.a(r1)
            com.tapjoy.internal.ak r1 = new com.tapjoy.internal.ak
            r1.<init>()
            com.tapjoy.internal.ak r1 = r1.a(r8)
            com.tapjoy.internal.ak r1 = r1.b(r5)
            android.view.animation.Animation r1 = r1.a()
            r0.a(r1)
            goto L_0x0179
        L_0x00ed:
            com.tapjoy.internal.aj r1 = new com.tapjoy.internal.aj
            r1.<init>()
            r1.a = r0
            r1.b = r6
            com.tapjoy.internal.ai r0 = r1.a()
            com.tapjoy.internal.ah r0 = r4.a(r0)
            android.view.animation.ScaleAnimation r1 = new android.view.animation.ScaleAnimation
            r1.<init>(r9, r10, r9, r10)
            com.tapjoy.internal.ah r0 = r0.a(r1)
            com.tapjoy.internal.ak r1 = new com.tapjoy.internal.ak
            r1.<init>()
            com.tapjoy.internal.ak r1 = r1.a(r8)
            com.tapjoy.internal.ak r1 = r1.b(r10)
            android.view.animation.Animation r1 = r1.a()
            r0.a(r1)
            goto L_0x0179
        L_0x011c:
            com.tapjoy.internal.aj r0 = new com.tapjoy.internal.aj
            r0.<init>()
            r0.a = r2
            r0.b = r6
            com.tapjoy.internal.ai r0 = r0.a()
            com.tapjoy.internal.ah r0 = r4.a(r0)
            android.view.animation.ScaleAnimation r1 = new android.view.animation.ScaleAnimation
            r1.<init>(r9, r10, r9, r10)
            com.tapjoy.internal.ah r0 = r0.a(r1)
            com.tapjoy.internal.ak r1 = new com.tapjoy.internal.ak
            r1.<init>()
            com.tapjoy.internal.ak r1 = r1.a(r5)
            com.tapjoy.internal.ak r1 = r1.b(r8)
            android.view.animation.Animation r1 = r1.a()
            r0.a(r1)
            goto L_0x0179
        L_0x014b:
            com.tapjoy.internal.aj r0 = new com.tapjoy.internal.aj
            r0.<init>()
            r0.a = r2
            r0.b = r7
            com.tapjoy.internal.ai r0 = r0.a()
            com.tapjoy.internal.ah r0 = r4.a(r0)
            android.view.animation.ScaleAnimation r1 = new android.view.animation.ScaleAnimation
            r1.<init>(r9, r10, r9, r10)
            com.tapjoy.internal.ah r0 = r0.a(r1)
            com.tapjoy.internal.ak r1 = new com.tapjoy.internal.ak
            r1.<init>()
            com.tapjoy.internal.ak r1 = r1.a(r10)
            com.tapjoy.internal.ak r1 = r1.b(r8)
            android.view.animation.Animation r1 = r1.a()
            r0.a(r1)
        L_0x0179:
            com.tapjoy.internal.ag r0 = r4.b()
            android.view.animation.Animation r0 = r0.a()
            r3.startAnimation(r0)
        L_0x0184:
            com.tapjoy.internal.c r0 = r11.k     // Catch:{ BadTokenException -> 0x01ca }
            r0.show()     // Catch:{ BadTokenException -> 0x01ca }
            com.tapjoy.internal.c r0 = r11.k
            android.view.Window r0 = r0.getWindow()
            r1 = -1
            r0.setLayout(r1, r1)
            android.view.Window r12 = r12.getWindow()
            android.view.WindowManager$LayoutParams r12 = r12.getAttributes()
            int r12 = r12.flags
            r0 = 1024(0x400, float:1.435E-42)
            r12 = r12 & r0
            if (r12 == 0) goto L_0x01ab
            com.tapjoy.internal.c r12 = r11.k
            android.view.Window r12 = r12.getWindow()
            r12.setFlags(r0, r0)
        L_0x01ab:
            long r0 = android.os.SystemClock.elapsedRealtime()
            r11.m = r0
            com.tapjoy.internal.hd r12 = r11.j
            com.tapjoy.internal.hy r0 = r11.b
            java.util.Map r0 = r0.k
            r12.a((java.util.Map) r0)
            r14.a()
            com.tapjoy.internal.fu r12 = r11.g
            if (r12 == 0) goto L_0x01c4
            r12.b()
        L_0x01c4:
            java.lang.String r12 = r11.a
            r13.c(r12)
            return
        L_0x01ca:
            r12 = move-exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hi.a(android.app.Activity, com.tapjoy.internal.he, com.tapjoy.internal.ga):void");
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.k != null) {
            this.k.dismiss();
        }
    }

    private static Boolean a(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            Object obj = bundle.get("tapjoy:hardwareAccelerated");
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
