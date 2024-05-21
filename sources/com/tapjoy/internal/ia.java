package com.tapjoy.internal;

final class ia extends hs implements gt {
    public static final bl a = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            String str = "";
            String str2 = "";
            bqVar.h();
            while (bqVar.j()) {
                String l = bqVar.l();
                if ("campaign_id".equals(l)) {
                    str = bqVar.c("");
                } else if ("product_id".equals(l)) {
                    str2 = bqVar.c("");
                } else {
                    bqVar.s();
                }
            }
            bqVar.i();
            return new ia(str, str2);
        }
    };
    private final String b;
    private final String c;

    ia(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }
}
