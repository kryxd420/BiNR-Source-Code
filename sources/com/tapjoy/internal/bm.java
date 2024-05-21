package com.tapjoy.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;

public final class bm {
    public static final bl a = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            Point point = new Point();
            bqVar.h();
            while (bqVar.j()) {
                String l = bqVar.l();
                if (AvidJSONUtil.KEY_X.equals(l)) {
                    point.x = bqVar.r();
                } else if (AvidJSONUtil.KEY_Y.equals(l)) {
                    point.y = bqVar.r();
                } else {
                    bqVar.s();
                }
            }
            bqVar.i();
            return point;
        }
    };
    public static final bl b = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            Rect rect = new Rect();
            switch (AnonymousClass3.a[bqVar.k().ordinal()]) {
                case 1:
                    bqVar.f();
                    rect.left = bqVar.r();
                    rect.top = bqVar.r();
                    rect.right = bqVar.r();
                    rect.bottom = bqVar.r();
                    while (bqVar.j()) {
                        bqVar.s();
                    }
                    bqVar.g();
                    break;
                case 2:
                    bqVar.h();
                    while (bqVar.j()) {
                        String l = bqVar.l();
                        if ("left".equals(l)) {
                            rect.left = bqVar.r();
                        } else if ("top".equals(l)) {
                            rect.top = bqVar.r();
                        } else if ("right".equals(l)) {
                            rect.right = bqVar.r();
                        } else if ("bottom".equals(l)) {
                            rect.bottom = bqVar.r();
                        } else {
                            bqVar.s();
                        }
                    }
                    bqVar.i();
                    break;
                default:
                    throw new IllegalStateException("Unexpected token: " + bqVar.k());
            }
            return rect;
        }
    };

    /* renamed from: com.tapjoy.internal.bm$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[bv.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.tapjoy.internal.bv[] r0 = com.tapjoy.internal.bv.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.tapjoy.internal.bv r1 = com.tapjoy.internal.bv.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.tapjoy.internal.bv r1 = com.tapjoy.internal.bv.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.bm.AnonymousClass3.<clinit>():void");
        }
    }
}
