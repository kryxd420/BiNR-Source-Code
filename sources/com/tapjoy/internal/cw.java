package com.tapjoy.internal;

public final class cw {
    public final cz a;
    public final cz b;
    public final boolean c;

    private cw(cz czVar, cz czVar2) {
        this.a = czVar;
        if (czVar2 == null) {
            this.b = cz.NONE;
        } else {
            this.b = czVar2;
        }
        this.c = false;
    }

    public static cw a(cz czVar, cz czVar2) {
        ds.a((Object) czVar, "Impression owner is null");
        if (!czVar.equals(cz.NONE)) {
            return new cw(czVar, czVar2);
        }
        throw new IllegalArgumentException("Impression owner is none");
    }
}
