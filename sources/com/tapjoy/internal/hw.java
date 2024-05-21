package com.tapjoy.internal;

import android.graphics.Rect;

public final class hw {
    public static final bl h = new bl() {
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: android.graphics.Rect} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object a(com.tapjoy.internal.bq r12) {
            /*
                r11 = this;
                java.lang.String r0 = ""
                r12.h()
                r1 = 0
                r2 = 0
                r7 = r0
                r4 = r1
                r5 = r4
                r8 = r5
                r9 = r8
                r10 = r9
                r6 = 0
            L_0x000e:
                boolean r0 = r12.j()
                if (r0 == 0) goto L_0x007a
                java.lang.String r0 = r12.l()
                java.lang.String r1 = "region"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x002a
                com.tapjoy.internal.bl r0 = com.tapjoy.internal.bm.b
                java.lang.Object r0 = r0.a(r12)
                r4 = r0
                android.graphics.Rect r4 = (android.graphics.Rect) r4
                goto L_0x000e
            L_0x002a:
                java.lang.String r1 = "value"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0037
                java.lang.String r5 = r12.m()
                goto L_0x000e
            L_0x0037:
                java.lang.String r1 = "dismiss"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0044
                boolean r6 = r12.n()
                goto L_0x000e
            L_0x0044:
                java.lang.String r1 = "url"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0051
                java.lang.String r7 = r12.m()
                goto L_0x000e
            L_0x0051:
                java.lang.String r1 = "redirect_url"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x005e
                java.lang.String r8 = r12.b()
                goto L_0x000e
            L_0x005e:
                java.lang.String r1 = "ad_content"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x006b
                java.lang.String r9 = r12.b()
                goto L_0x000e
            L_0x006b:
                boolean r1 = com.tapjoy.internal.hs.a((java.lang.String) r0)
                if (r1 == 0) goto L_0x0076
                com.tapjoy.internal.hs r10 = com.tapjoy.internal.hs.a(r0, r12)
                goto L_0x000e
            L_0x0076:
                r12.s()
                goto L_0x000e
            L_0x007a:
                r12.i()
                com.tapjoy.internal.hw r12 = new com.tapjoy.internal.hw
                r3 = r12
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hw.AnonymousClass1.a(com.tapjoy.internal.bq):java.lang.Object");
        }
    };
    public final Rect a;
    public final String b;
    public final boolean c;
    public final String d;
    public String e;
    public String f;
    public final gp g;

    hw(Rect rect, String str, boolean z, String str2, String str3, String str4, gp gpVar) {
        this.a = rect;
        this.b = str;
        this.c = z;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = gpVar;
    }
}
