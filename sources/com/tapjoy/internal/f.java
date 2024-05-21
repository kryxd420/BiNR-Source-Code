package com.tapjoy.internal;

import com.unity3d.ads.metadata.InAppPurchaseMetaData;

public final class f {
    public String a;
    public String b;
    public String c;
    public long d;
    public int e;
    public String f;
    public String g;

    public f(String str) {
        bq b2 = bq.b(str);
        b2.h();
        while (b2.j()) {
            String l = b2.l();
            if ("orderId".equals(l)) {
                this.a = b2.m();
            } else if ("packageName".equals(l)) {
                this.b = b2.m();
            } else if (InAppPurchaseMetaData.KEY_PRODUCT_ID.equals(l)) {
                this.c = b2.m();
            } else if ("purchaseTime".equals(l)) {
                this.d = b2.q();
            } else if ("purchaseState".equals(l)) {
                this.e = b2.r();
            } else if ("developerPayload".equals(l)) {
                this.f = b2.m();
            } else if ("purchaseToken".equals(l)) {
                this.g = b2.m();
            } else {
                b2.s();
            }
        }
        b2.i();
    }
}
