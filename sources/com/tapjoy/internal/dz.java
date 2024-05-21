package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewParent;
import com.tapjoy.internal.dl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public final class dz implements dl.a {
    public static Handler a = new Handler(Looper.getMainLooper());
    private static dz c = new dz();
    /* access modifiers changed from: private */
    public static Handler d = null;
    /* access modifiers changed from: private */
    public static final Runnable j = new Runnable() {
        public final void run() {
            dz.b(dz.a());
        }
    };
    /* access modifiers changed from: private */
    public static final Runnable k = new Runnable() {
        public final void run() {
            if (dz.d != null) {
                dz.d.post(dz.j);
                dz.d.postDelayed(dz.k, 200);
            }
        }
    };
    public List b = new ArrayList();
    private int e;
    private dm f = new dm();
    private ea g = new ea();
    /* access modifiers changed from: private */
    public eh h = new eh(new ed());
    private double i;

    dz() {
    }

    public static dz a() {
        return c;
    }

    private void a(View view, dl dlVar, JSONObject jSONObject, int i2) {
        dlVar.a(view, jSONObject, this, i2 == ei.a);
    }

    private void g() {
        if (this.b.size() > 0) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public static void b() {
        if (d == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            d = handler;
            handler.post(j);
            d.postDelayed(k, 200);
        }
    }

    public static void c() {
        if (d != null) {
            d.removeCallbacks(k);
            d = null;
        }
    }

    public final void a(View view, dl dlVar, JSONObject jSONObject) {
        String str;
        boolean z;
        if (dt.c(view)) {
            ea eaVar = this.g;
            int i2 = eaVar.c.contains(view) ? ei.a : eaVar.f ? ei.b : ei.c;
            if (i2 != ei.c) {
                JSONObject a2 = dlVar.a(view);
                dp.a(jSONObject, a2);
                ea eaVar2 = this.g;
                ArrayList arrayList = null;
                if (eaVar2.a.size() == 0) {
                    str = null;
                } else {
                    str = (String) eaVar2.a.get(view);
                    if (str != null) {
                        eaVar2.a.remove(view);
                    }
                }
                if (str != null) {
                    dp.a(a2, str);
                    this.g.f = true;
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    ea eaVar3 = this.g;
                    if (!(eaVar3.b.size() == 0 || (arrayList = (ArrayList) eaVar3.b.get(view)) == null)) {
                        eaVar3.b.remove(view);
                        Collections.sort(arrayList);
                    }
                    if (arrayList != null) {
                        dp.a(a2, (List) arrayList);
                    }
                    a(view, dlVar, a2, i2);
                }
                this.e++;
            }
        }
    }

    static /* synthetic */ void b(dz dzVar) {
        boolean z;
        dzVar.e = 0;
        dzVar.i = dr.a();
        ea eaVar = dzVar.g;
        dg a2 = dg.a();
        if (a2 != null) {
            for (dc dcVar : Collections.unmodifiableCollection(a2.b)) {
                View c2 = dcVar.c();
                if (dcVar.d()) {
                    if (c2 != null) {
                        if (c2.hasWindowFocus()) {
                            HashSet hashSet = new HashSet();
                            View view = c2;
                            while (true) {
                                if (view != null) {
                                    if (!dt.c(view)) {
                                        break;
                                    }
                                    hashSet.add(view);
                                    ViewParent parent = view.getParent();
                                    view = parent instanceof View ? (View) parent : null;
                                } else {
                                    eaVar.c.addAll(hashSet);
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (z) {
                            eaVar.d.add(dcVar.f);
                            eaVar.a.put(c2, dcVar.f);
                            eaVar.a(dcVar);
                        }
                    }
                    eaVar.e.add(dcVar.f);
                }
            }
        }
        double a3 = dr.a();
        dn dnVar = dzVar.f.a;
        if (dzVar.g.e.size() > 0) {
            JSONObject a4 = dnVar.a((View) null);
            eh ehVar = dzVar.h;
            ehVar.a.a(new ef(ehVar, dzVar.g.e, a4, a3));
        }
        if (dzVar.g.d.size() > 0) {
            JSONObject a5 = dnVar.a((View) null);
            dzVar.a((View) null, dnVar, a5, ei.a);
            dp.a(a5);
            eh ehVar2 = dzVar.h;
            ehVar2.a.a(new eg(ehVar2, dzVar.g.d, a5, a3));
        } else {
            dzVar.h.b();
        }
        ea eaVar2 = dzVar.g;
        eaVar2.a.clear();
        eaVar2.b.clear();
        eaVar2.c.clear();
        eaVar2.d.clear();
        eaVar2.e.clear();
        eaVar2.f = false;
        dr.a();
        dzVar.g();
    }
}
