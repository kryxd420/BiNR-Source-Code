package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;

public final class e {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public long g;

    public e(String str) {
        bq b2 = bq.b(str);
        b2.h();
        while (b2.j()) {
            String l = b2.l();
            if (InAppPurchaseMetaData.KEY_PRODUCT_ID.equals(l)) {
                this.a = b2.m();
            } else if ("type".equals(l)) {
                this.b = b2.m();
            } else if (InAppPurchaseMetaData.KEY_PRICE.equals(l)) {
                this.c = b2.m();
            } else if (TJAdUnitConstants.String.TITLE.equals(l)) {
                this.d = b2.m();
            } else if ("description".equals(l)) {
                this.e = b2.m();
            } else if ("price_currency_code".equals(l)) {
                this.f = b2.m();
            } else if ("price_amount_micros".equals(l)) {
                this.g = b2.q();
            } else {
                b2.s();
            }
        }
        b2.i();
    }
}
