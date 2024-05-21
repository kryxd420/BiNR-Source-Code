package com.tapjoy.internal;

import com.tapjoy.internal.dh;
import java.util.Collections;

public final class dk implements cr, dh.a {
    private static dk c;
    public float a = 0.0f;
    public cs b;
    private final ct d;
    private final cq e;
    private dg f;

    private dk(ct ctVar, cq cqVar) {
        this.d = ctVar;
        this.e = cqVar;
    }

    public static dk a() {
        if (c == null) {
            c = new dk(new ct(), new cq());
        }
        return c;
    }

    public final void a(boolean z) {
        if (z) {
            dz.a();
            dz.b();
            return;
        }
        dz.a();
        dz.c();
    }

    public final void a(float f2) {
        this.a = f2;
        if (this.f == null) {
            this.f = dg.a();
        }
        for (dc dcVar : Collections.unmodifiableCollection(this.f.b)) {
            dcVar.c.a(f2);
        }
    }
}
