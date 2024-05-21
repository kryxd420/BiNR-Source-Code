package com.tapjoy.internal;

import com.tapjoy.internal.en;

public abstract class ej extends en {
    public abstract eq a(int i);

    public final /* bridge */ /* synthetic */ int a(Object obj) {
        return ep.a(((eq) obj).a());
    }

    public final /* synthetic */ void a(ep epVar, Object obj) {
        epVar.c(((eq) obj).a());
    }

    protected ej(Class cls) {
        super(ek.VARINT, cls);
    }

    public final /* synthetic */ Object a(eo eoVar) {
        int d = eoVar.d();
        eq a = a(d);
        if (a != null) {
            return a;
        }
        throw new en.a(d, this.a);
    }
}
