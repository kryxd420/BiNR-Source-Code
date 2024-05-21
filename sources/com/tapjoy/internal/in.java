package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.ui.VungleActivity;
import java.util.List;
import java.util.Map;

public final class in extends im {
    public final String c;
    public boolean d = false;
    private final hd e;
    private final fe f;
    private final ey g;
    private final fl h;
    private Context i;

    public final String c() {
        return VungleActivity.PLACEMENT_EXTRA;
    }

    public in(hd hdVar, fe feVar, ey eyVar, fl flVar, String str, Context context) {
        this.e = hdVar;
        this.f = feVar;
        this.g = eyVar;
        this.h = flVar;
        this.c = str;
        this.i = context;
    }

    public final Map e() {
        Map e2 = super.e();
        e2.put(TJAdUnitConstants.String.VIDEO_INFO, new bp(ht.a(this.f)));
        e2.put(TapjoyConstants.TJC_APP_PLACEMENT, new bp(ht.a(this.g)));
        e2.put("user", new bp(ht.a(this.h)));
        e2.put(VungleActivity.PLACEMENT_EXTRA, this.c);
        return e2;
    }

    public static class a {
        public hk a;
        public final List b;

        public a(hk hkVar, List list) {
            this.a = hkVar;
            this.b = list;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object a(bq bqVar) {
        bqVar.h();
        hy hyVar = null;
        hv hvVar = null;
        List list = null;
        while (bqVar.j()) {
            String l = bqVar.l();
            if ("interstitial".equals(l)) {
                hyVar = (hy) bqVar.a(hy.n);
            } else if ("contextual_button".equals(l)) {
                hvVar = (hv) bqVar.a(hv.d);
            } else if ("enabled_placements".equals(l)) {
                list = bqVar.c();
            } else {
                bqVar.s();
            }
        }
        bqVar.i();
        if (hyVar != null && (hyVar.a() || hyVar.b())) {
            return new a(new hi(this.e, this.c, hyVar, this.i), list);
        }
        if (hvVar != null) {
            return new a(new gz(this.e, this.c, hvVar, this.i), list);
        }
        return new a(new hj(), list);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object f() {
        a aVar = (a) super.f();
        if (!(aVar.a instanceof hj)) {
            aVar.a.b();
            if (!aVar.a.c()) {
                new Object[1][0] = this.c;
                aVar.a = new hj();
            }
        }
        return aVar;
    }
}
