package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.is;
import java.util.Iterator;

public class gz extends hk {
    private static final String h = "gz";
    private static gz i;
    final String a;
    final hv b;
    /* access modifiers changed from: private */
    public final hd j;
    private boolean k;
    private boolean l;
    private long m;
    private Context n;
    private is o;
    private Activity p;
    private he q;
    private Handler r;
    private Runnable s;

    public static void a() {
        gz gzVar = i;
        if (gzVar != null) {
            AnonymousClass1 r1 = new Runnable(gzVar) {
                final /* synthetic */ gz a;

                {
                    this.a = r1;
                }

                public final void run() {
                    gz.a(this.a);
                }
            };
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null && mainLooper.getThread() == Thread.currentThread()) {
                r1.run();
            } else {
                v.a().post(r1);
            }
        }
    }

    public gz(hd hdVar, String str, hv hvVar, Context context) {
        this.j = hdVar;
        this.a = str;
        this.b = hvVar;
        this.n = context;
    }

    public final void b() {
        Iterator it = this.b.a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ie) it.next()).c.iterator();
            while (it2.hasNext()) {
                id idVar = (id) it2.next();
                if (idVar.l != null) {
                    idVar.l.b();
                }
                if (idVar.m != null) {
                    idVar.m.b();
                }
            }
        }
    }

    public final boolean c() {
        Iterator it = this.b.a.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Iterator it2 = ((ie) it.next()).c.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    continue;
                    break;
                }
                id idVar = (id) it2.next();
                if ((idVar.l == null || idVar.l.a()) && (idVar.m == null || idVar.m.a())) {
                }
            }
            z = false;
            continue;
            if (!z) {
                return false;
            }
        }
        return z;
    }

    public final void a(he heVar, ga gaVar) {
        this.q = heVar;
        this.p = gv.a();
        if (this.p != null && !this.p.isFinishing()) {
            try {
                a(this.p, heVar, gaVar);
                new Object[1][0] = this.a;
                return;
            } catch (WindowManager.BadTokenException unused) {
            }
        }
        this.p = a.a(this.n);
        if (this.p != null && !this.p.isFinishing()) {
            try {
                a(this.p, heVar, gaVar);
                new Object[1][0] = this.a;
                return;
            } catch (WindowManager.BadTokenException unused2) {
            }
        }
        ha.b("Failed to show the content for \"{}\". No usable activity found.", this.a);
        heVar.a(this.a, this.f, (gp) null);
    }

    private void a(final Activity activity, final he heVar, ga gaVar) {
        if (this.k) {
            TapjoyLog.e(h, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
            return;
        }
        this.k = true;
        this.l = true;
        i = this;
        this.g = gaVar.a;
        this.o = new is(activity, this.b, new is.a() {
            public final void a(id idVar) {
                fz fzVar;
                if (!(!(gz.this.g instanceof fz) || (fzVar = (fz) gz.this.g) == null || fzVar.c == null)) {
                    fzVar.c.a();
                }
                gz.this.j.a(gz.this.b.b, idVar.k);
                if (!ju.c(idVar.h)) {
                    gz.this.e.a(activity, idVar.h, ju.b(idVar.i));
                    gz.this.d = true;
                } else if (!ju.c(idVar.g)) {
                    hk.a((Context) activity, idVar.g);
                }
                heVar.a(gz.this.a, (gp) null);
                if (idVar.j) {
                    gz.a(gz.this);
                }
            }

            public final void a() {
                gz.a(gz.this);
            }
        });
        Window window = activity.getWindow();
        is isVar = this.o;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        Window.Callback callback = window.getCallback();
        window.setCallback((Window.Callback) null);
        window.addContentView(isVar, layoutParams);
        window.setCallback(callback);
        this.m = SystemClock.elapsedRealtime();
        this.j.a(this.b.b);
        gaVar.a();
        fu fuVar = this.g;
        if (fuVar != null) {
            fuVar.b();
        }
        heVar.c(this.a);
        if (this.b.c > 0.0f) {
            this.r = new Handler(Looper.getMainLooper());
            this.s = new Runnable() {
                public final void run() {
                    gz.a(gz.this);
                }
            };
            this.r.postDelayed(this.s, (long) (this.b.c * 1000.0f));
        }
    }

    static /* synthetic */ void a(gz gzVar) {
        if (gzVar.l) {
            gzVar.l = false;
            if (gzVar.r != null) {
                gzVar.r.removeCallbacks(gzVar.s);
                gzVar.s = null;
                gzVar.r = null;
            }
            if (i == gzVar) {
                i = null;
            }
            gzVar.j.a(gzVar.b.b, SystemClock.elapsedRealtime() - gzVar.m);
            if (!gzVar.d && gzVar.q != null) {
                gzVar.q.a(gzVar.a, gzVar.f, (gp) null);
                gzVar.q = null;
            }
            ViewGroup viewGroup = (ViewGroup) gzVar.o.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(gzVar.o);
            }
            gzVar.o = null;
            if (gzVar.p instanceof TJContentActivity) {
                gzVar.p.finish();
            }
            gzVar.p = null;
        }
    }
}
