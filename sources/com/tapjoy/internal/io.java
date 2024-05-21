package com.tapjoy.internal;

import com.tapjoy.internal.fa;
import java.util.Map;

public final class io extends im {
    private final fa.a c = new fa.a();
    private fc d = null;

    public final String c() {
        return this.d == fc.USAGES ? "api/v1/usages" : "api/v1/cevs";
    }

    public final boolean a(ez ezVar) {
        if (this.d == null) {
            this.d = ezVar.n;
        } else if (ezVar.n != this.d) {
            return false;
        }
        this.c.c.add(ezVar);
        return true;
    }

    public final int g() {
        return this.c.c.size();
    }

    public final Map e() {
        Map e = super.e();
        e.put("events", new bp(ht.a(this.c.b())));
        return e;
    }
}
