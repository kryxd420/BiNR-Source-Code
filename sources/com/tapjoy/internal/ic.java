package com.tapjoy.internal;

final class ic extends hs implements gu {
    public static final bl a = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            bqVar.h();
            String str = null;
            String str2 = null;
            String str3 = null;
            int i = 1;
            while (bqVar.j()) {
                String l = bqVar.l();
                if ("id".equals(l)) {
                    str = bqVar.m();
                } else if ("name".equals(l)) {
                    str2 = bqVar.m();
                } else if ("quantity".equals(l)) {
                    i = bqVar.r();
                } else if ("token".equals(l)) {
                    str3 = bqVar.m();
                } else {
                    bqVar.s();
                }
            }
            bqVar.i();
            return new ic(str, str2, i, str3);
        }
    };
    private final String b;
    private final String c;
    private final int d;
    private final String e;

    ic(String str, String str2, int i, String str3) {
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final int c() {
        return this.d;
    }

    public final String d() {
        return this.e;
    }
}
