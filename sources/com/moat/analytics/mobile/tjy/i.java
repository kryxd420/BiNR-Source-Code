package com.moat.analytics.mobile.tjy;

import org.json.JSONObject;

abstract class i extends f {
    protected k j = k.UNINITIALIZED;
    protected int k = Integer.MIN_VALUE;
    protected double l = Double.NaN;
    protected int m = Integer.MIN_VALUE;
    protected int n = Integer.MIN_VALUE;
    private int o = 0;

    public i(String str, a aVar, ap apVar) {
        super(str, aVar, apVar);
    }

    /* access modifiers changed from: protected */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        Integer num;
        if (!moatAdEvent.adPlayhead.equals(MoatAdEvent.TIME_UNAVAILABLE)) {
            num = moatAdEvent.adPlayhead;
        } else {
            try {
                num = f();
            } catch (Exception unused) {
                num = Integer.valueOf(this.k);
            }
            moatAdEvent.adPlayhead = num;
        }
        if (moatAdEvent.adPlayhead.intValue() < 0) {
            num = Integer.valueOf(this.k);
            moatAdEvent.adPlayhead = num;
        }
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE) {
            if (num.intValue() == Integer.MIN_VALUE || this.n == Integer.MIN_VALUE || !a(num, Integer.valueOf(this.n))) {
                this.j = k.STOPPED;
                moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.j = k.COMPLETED;
            }
        }
        return super.a(moatAdEvent);
    }

    /* access modifiers changed from: protected */
    public void b() {
        super.b();
        this.d.postDelayed(new j(this), 200);
    }

    /* access modifiers changed from: protected */
    public abstract Integer f();

    /* access modifiers changed from: protected */
    public abstract boolean g();

    /* access modifiers changed from: protected */
    public abstract Integer h();

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0098 A[Catch:{ Exception -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009a A[Catch:{ Exception -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bb A[Catch:{ Exception -> 0x00d0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean i() {
        /*
            r12 = this;
            java.lang.ref.WeakReference r0 = r12.f
            java.lang.Object r0 = r0.get()
            r1 = 0
            if (r0 == 0) goto L_0x00da
            boolean r0 = r12.e()
            if (r0 == 0) goto L_0x0011
            goto L_0x00da
        L_0x0011:
            r0 = 1
            java.lang.Integer r2 = r12.f()     // Catch:{ Exception -> 0x00d0 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x00d0 }
            int r3 = r12.k     // Catch:{ Exception -> 0x00d0 }
            if (r3 < 0) goto L_0x0021
            if (r2 >= 0) goto L_0x0021
            return r1
        L_0x0021:
            r12.k = r2     // Catch:{ Exception -> 0x00d0 }
            if (r2 != 0) goto L_0x0026
            return r0
        L_0x0026:
            java.lang.Integer r3 = r12.h()     // Catch:{ Exception -> 0x00d0 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x00d0 }
            boolean r4 = r12.g()     // Catch:{ Exception -> 0x00d0 }
            double r5 = (double) r3
            r7 = 4616189618054758400(0x4010000000000000, double:4.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r7
            double r7 = r12.d()     // Catch:{ Exception -> 0x00d0 }
            r9 = 0
            int r10 = r12.m     // Catch:{ Exception -> 0x00d0 }
            if (r2 <= r10) goto L_0x0044
            r12.m = r2     // Catch:{ Exception -> 0x00d0 }
        L_0x0044:
            int r10 = r12.n     // Catch:{ Exception -> 0x00d0 }
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r10 != r11) goto L_0x004c
            r12.n = r3     // Catch:{ Exception -> 0x00d0 }
        L_0x004c:
            if (r4 == 0) goto L_0x008b
            com.moat.analytics.mobile.tjy.k r3 = r12.j     // Catch:{ Exception -> 0x00d0 }
            com.moat.analytics.mobile.tjy.k r4 = com.moat.analytics.mobile.tjy.k.UNINITIALIZED     // Catch:{ Exception -> 0x00d0 }
            if (r3 != r4) goto L_0x005b
            com.moat.analytics.mobile.tjy.MoatAdEventType r9 = com.moat.analytics.mobile.tjy.MoatAdEventType.AD_EVT_START     // Catch:{ Exception -> 0x00d0 }
        L_0x0056:
            com.moat.analytics.mobile.tjy.k r3 = com.moat.analytics.mobile.tjy.k.PLAYING     // Catch:{ Exception -> 0x00d0 }
        L_0x0058:
            r12.j = r3     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0096
        L_0x005b:
            com.moat.analytics.mobile.tjy.k r3 = r12.j     // Catch:{ Exception -> 0x00d0 }
            com.moat.analytics.mobile.tjy.k r4 = com.moat.analytics.mobile.tjy.k.PAUSED     // Catch:{ Exception -> 0x00d0 }
            if (r3 != r4) goto L_0x0064
            com.moat.analytics.mobile.tjy.MoatAdEventType r9 = com.moat.analytics.mobile.tjy.MoatAdEventType.AD_EVT_PLAYING     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0056
        L_0x0064:
            double r3 = (double) r2
            java.lang.Double.isNaN(r3)
            double r3 = r3 / r5
            double r3 = java.lang.Math.floor(r3)     // Catch:{ Exception -> 0x00d0 }
            int r3 = (int) r3     // Catch:{ Exception -> 0x00d0 }
            int r3 = r3 - r0
            if (r3 < 0) goto L_0x0096
            r4 = 3
            if (r3 >= r4) goto L_0x0096
            com.moat.analytics.mobile.tjy.MoatAdEventType[] r4 = b     // Catch:{ Exception -> 0x00d0 }
            r3 = r4[r3]     // Catch:{ Exception -> 0x00d0 }
            java.util.Map r4 = r12.c     // Catch:{ Exception -> 0x00d0 }
            boolean r4 = r4.containsKey(r3)     // Catch:{ Exception -> 0x00d0 }
            if (r4 != 0) goto L_0x0096
            java.util.Map r4 = r12.c     // Catch:{ Exception -> 0x00d0 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x00d0 }
            r4.put(r3, r5)     // Catch:{ Exception -> 0x00d0 }
            r9 = r3
            goto L_0x0096
        L_0x008b:
            com.moat.analytics.mobile.tjy.k r3 = r12.j     // Catch:{ Exception -> 0x00d0 }
            com.moat.analytics.mobile.tjy.k r4 = com.moat.analytics.mobile.tjy.k.PAUSED     // Catch:{ Exception -> 0x00d0 }
            if (r3 == r4) goto L_0x0096
            com.moat.analytics.mobile.tjy.MoatAdEventType r9 = com.moat.analytics.mobile.tjy.MoatAdEventType.AD_EVT_PAUSED     // Catch:{ Exception -> 0x00d0 }
            com.moat.analytics.mobile.tjy.k r3 = com.moat.analytics.mobile.tjy.k.PAUSED     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0058
        L_0x0096:
            if (r9 == 0) goto L_0x009a
            r3 = 1
            goto L_0x009b
        L_0x009a:
            r3 = 0
        L_0x009b:
            if (r3 != 0) goto L_0x00b9
            double r4 = r12.l     // Catch:{ Exception -> 0x00d0 }
            boolean r4 = java.lang.Double.isNaN(r4)     // Catch:{ Exception -> 0x00d0 }
            if (r4 != 0) goto L_0x00b9
            double r4 = r12.l     // Catch:{ Exception -> 0x00d0 }
            r6 = 0
            double r4 = r4 - r7
            double r4 = java.lang.Math.abs(r4)     // Catch:{ Exception -> 0x00d0 }
            r10 = 4587366580439587226(0x3fa999999999999a, double:0.05)
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x00b9
            com.moat.analytics.mobile.tjy.MoatAdEventType r9 = com.moat.analytics.mobile.tjy.MoatAdEventType.AD_EVT_VOLUME_CHANGE     // Catch:{ Exception -> 0x00d0 }
            r3 = 1
        L_0x00b9:
            if (r3 == 0) goto L_0x00cb
            com.moat.analytics.mobile.tjy.MoatAdEvent r3 = new com.moat.analytics.mobile.tjy.MoatAdEvent     // Catch:{ Exception -> 0x00d0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00d0 }
            java.lang.Double r4 = java.lang.Double.valueOf(r7)     // Catch:{ Exception -> 0x00d0 }
            r3.<init>(r9, r2, r4)     // Catch:{ Exception -> 0x00d0 }
            r12.dispatchEvent((com.moat.analytics.mobile.tjy.MoatAdEvent) r3)     // Catch:{ Exception -> 0x00d0 }
        L_0x00cb:
            r12.l = r7     // Catch:{ Exception -> 0x00d0 }
            r12.o = r1     // Catch:{ Exception -> 0x00d0 }
            return r0
        L_0x00d0:
            int r2 = r12.o
            int r3 = r2 + 1
            r12.o = r3
            r3 = 5
            if (r2 >= r3) goto L_0x00da
            return r0
        L_0x00da:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.tjy.i.i():boolean");
    }
}
