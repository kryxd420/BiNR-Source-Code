package com.tapjoy.internal;

import android.graphics.PointF;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;
import java.util.List;

public final class ie {
    public static final bl d = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            return new ie(bqVar);
        }
    };
    public ad a = ad.UNSPECIFIED;
    public PointF b;
    public ArrayList c = new ArrayList();

    public ie(bq bqVar) {
        bqVar.h();
        while (bqVar.j()) {
            String l = bqVar.l();
            if (TJAdUnitConstants.String.BUTTONS.equals(l)) {
                if (bqVar.k() == bv.BEGIN_ARRAY) {
                    bqVar.a((List) this.c, id.n);
                } else {
                    bqVar.s();
                }
            } else if ("window_aspect_ratio".equals(l)) {
                if (bqVar.a()) {
                    PointF pointF = new PointF();
                    bqVar.h();
                    while (bqVar.j()) {
                        String l2 = bqVar.l();
                        if (AvidJSONUtil.KEY_WIDTH.equals(l2)) {
                            pointF.x = (float) bqVar.p();
                        } else if (AvidJSONUtil.KEY_HEIGHT.equals(l2)) {
                            pointF.y = (float) bqVar.p();
                        } else {
                            bqVar.s();
                        }
                    }
                    bqVar.i();
                    if (!(pointF.x == 0.0f || pointF.y == 0.0f)) {
                        this.b = pointF;
                    }
                } else {
                    bqVar.s();
                }
            } else if ("orientation".equals(l)) {
                String m = bqVar.m();
                if (TJAdUnitConstants.String.LANDSCAPE.equals(m)) {
                    this.a = ad.LANDSCAPE;
                } else if ("portrait".equals(m)) {
                    this.a = ad.PORTRAIT;
                }
            } else {
                bqVar.s();
            }
        }
        bqVar.i();
    }
}
