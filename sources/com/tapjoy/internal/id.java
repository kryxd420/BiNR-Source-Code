package com.tapjoy.internal;

import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TapjoyConstants;

public final class id {
    public static final bl n = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            return new id(bqVar);
        }
    };
    public Cif a;
    public Cif b;
    public Cif c;
    public Cif d;
    public int e = 9;
    public int f = 10;
    public String g;
    public String h;
    public String i;
    public boolean j = false;
    public String k;
    public ib l;
    public ib m;

    public id(bq bqVar) {
        bqVar.h();
        while (bqVar.j()) {
            String l2 = bqVar.l();
            if (AvidJSONUtil.KEY_X.equals(l2)) {
                this.a = Cif.a(bqVar.m());
            } else if (AvidJSONUtil.KEY_Y.equals(l2)) {
                this.b = Cif.a(bqVar.m());
            } else if (AvidJSONUtil.KEY_WIDTH.equals(l2)) {
                this.c = Cif.a(bqVar.m());
            } else if (AvidJSONUtil.KEY_HEIGHT.equals(l2)) {
                this.d = Cif.a(bqVar.m());
            } else if ("url".equals(l2)) {
                this.g = bqVar.m();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l2)) {
                this.h = bqVar.m();
            } else if ("ad_content".equals(l2)) {
                this.i = bqVar.m();
            } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l2)) {
                this.j = bqVar.n();
            } else if ("value".equals(l2)) {
                this.k = bqVar.m();
            } else if ("image".equals(l2)) {
                this.l = (ib) ib.e.a(bqVar);
            } else if ("image_clicked".equals(l2)) {
                this.m = (ib) ib.e.a(bqVar);
            } else if ("align".equals(l2)) {
                String m2 = bqVar.m();
                if ("left".equals(m2)) {
                    this.e = 9;
                } else if ("right".equals(m2)) {
                    this.e = 11;
                } else if ("center".equals(m2)) {
                    this.e = 14;
                } else {
                    bqVar.s();
                }
            } else if ("valign".equals(l2)) {
                String m3 = bqVar.m();
                if ("top".equals(m3)) {
                    this.f = 10;
                } else if ("middle".equals(m3)) {
                    this.f = 15;
                } else if ("bottom".equals(m3)) {
                    this.f = 12;
                } else {
                    bqVar.s();
                }
            } else {
                bqVar.s();
            }
        }
        bqVar.i();
    }
}
