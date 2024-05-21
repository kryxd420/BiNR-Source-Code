package com.tapjoy.internal;

import android.view.animation.Animation;

public class ag {
    protected final Animation a;

    public enum a {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;

        static {
            e = new int[]{a, b, c, d};
        }

        public static int[] a() {
            return (int[]) e.clone();
        }
    }

    /* renamed from: com.tapjoy.internal.ag$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] a = new int[a.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
        static {
            /*
                int[] r0 = com.tapjoy.internal.ag.a.a()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                r0 = 1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.tapjoy.internal.ag.a.a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.tapjoy.internal.ag.a.b     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.tapjoy.internal.ag.a.c     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r3 = 3
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0029 }
                int r2 = com.tapjoy.internal.ag.a.d     // Catch:{ NoSuchFieldError -> 0x0029 }
                int r2 = r2 - r0
                r0 = 4
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0029 }
            L_0x0029:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ag.AnonymousClass1.<clinit>():void");
        }
    }

    public ag(Animation animation) {
        this.a = animation;
        animation.setDuration(400);
    }

    public Animation a() {
        return this.a;
    }

    public final ag b() {
        this.a.setDuration(600);
        return this;
    }
}
