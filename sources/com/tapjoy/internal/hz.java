package com.tapjoy.internal;

import android.graphics.Point;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import java.net.URL;

public final class hz {
    public static final bl d = new bl() {
        private static Point b(bq bqVar) {
            bqVar.h();
            Point point = null;
            while (bqVar.j()) {
                if ("offset".equals(bqVar.l())) {
                    bqVar.h();
                    int i = 0;
                    int i2 = 0;
                    while (bqVar.j()) {
                        String l = bqVar.l();
                        if (AvidJSONUtil.KEY_X.equals(l)) {
                            i = bqVar.r();
                        } else if (AvidJSONUtil.KEY_Y.equals(l)) {
                            i2 = bqVar.r();
                        } else {
                            bqVar.s();
                        }
                    }
                    bqVar.i();
                    point = new Point(i, i2);
                } else {
                    bqVar.s();
                }
            }
            bqVar.i();
            return point;
        }

        public final /* synthetic */ Object a(bq bqVar) {
            bqVar.h();
            ib ibVar = null;
            Point point = null;
            Point point2 = null;
            while (bqVar.j()) {
                String l = bqVar.l();
                if ("image".equals(l)) {
                    String m = bqVar.m();
                    if (!ju.c(m)) {
                        ibVar = new ib(new URL(m));
                    }
                } else if (TJAdUnitConstants.String.LANDSCAPE.equals(l)) {
                    point = b(bqVar);
                } else if ("portrait".equals(l)) {
                    point2 = b(bqVar);
                } else {
                    bqVar.s();
                }
            }
            bqVar.i();
            return new hz(ibVar, point, point2);
        }
    };
    public final ib a;
    public final Point b;
    public final Point c;

    public hz(ib ibVar, Point point, Point point2) {
        this.a = ibVar;
        this.b = point;
        this.c = point2;
    }
}
