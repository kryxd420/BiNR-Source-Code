package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

abstract class h<PlayerOrIMAAd> extends c<PlayerOrIMAAd> {
    int l = Integer.MIN_VALUE;
    private a m = a.UNINITIALIZED;
    private int n = Integer.MIN_VALUE;
    private double o = Double.NaN;
    private int p = Integer.MIN_VALUE;
    private int q = 0;

    protected enum a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    h(String str) {
        super(str);
    }

    private void n() {
        this.h.postDelayed(new Runnable() {
            public void run() {
                h hVar;
                try {
                    if (h.this.j.get() == null || h.this.i()) {
                        hVar = h.this;
                    } else if (Boolean.valueOf(h.this.m()).booleanValue()) {
                        h.this.h.postDelayed(this, 200);
                        return;
                    } else {
                        hVar = h.this;
                    }
                    hVar.h();
                } catch (Exception e) {
                    h.this.h();
                    m.a(e);
                }
            }
        }, 200);
    }

    /* access modifiers changed from: protected */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        Integer num;
        if (!moatAdEvent.b.equals(MoatAdEvent.a)) {
            num = moatAdEvent.b;
        } else {
            try {
                num = j();
            } catch (Exception unused) {
                num = Integer.valueOf(this.n);
            }
            moatAdEvent.b = num;
        }
        if (moatAdEvent.b.intValue() < 0 || (moatAdEvent.b.intValue() == 0 && moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && this.n > 0)) {
            num = Integer.valueOf(this.n);
            moatAdEvent.b = num;
        }
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (num.intValue() == Integer.MIN_VALUE || this.l == Integer.MIN_VALUE || !a(num, Integer.valueOf(this.l))) {
                this.m = a.STOPPED;
                moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.m = a.COMPLETED;
            }
        }
        return super.a(moatAdEvent);
    }

    public boolean a(Map<String, String> map, PlayerOrIMAAd playerorimaad, View view) {
        try {
            if (!this.e) {
                n();
            }
        } catch (Exception e) {
            m.a(e);
        }
        return super.a(map, playerorimaad, view);
    }

    /* access modifiers changed from: protected */
    public abstract Integer j();

    /* access modifiers changed from: protected */
    public abstract boolean k();

    /* access modifiers changed from: protected */
    public abstract Integer l();

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099 A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009b A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bc A[Catch:{ Exception -> 0x00d1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m() {
        /*
            r12 = this;
            java.lang.ref.WeakReference r0 = r12.j
            java.lang.Object r0 = r0.get()
            r1 = 0
            if (r0 == 0) goto L_0x00dd
            boolean r0 = r12.i()
            if (r0 == 0) goto L_0x0011
            goto L_0x00dd
        L_0x0011:
            r0 = 1
            java.lang.Integer r2 = r12.j()     // Catch:{ Exception -> 0x00d1 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x00d1 }
            int r3 = r12.n     // Catch:{ Exception -> 0x00d1 }
            if (r3 < 0) goto L_0x0021
            if (r2 >= 0) goto L_0x0021
            return r1
        L_0x0021:
            r12.n = r2     // Catch:{ Exception -> 0x00d1 }
            if (r2 != 0) goto L_0x0026
            return r0
        L_0x0026:
            java.lang.Integer r3 = r12.l()     // Catch:{ Exception -> 0x00d1 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x00d1 }
            boolean r4 = r12.k()     // Catch:{ Exception -> 0x00d1 }
            double r5 = (double) r3
            r7 = 4616189618054758400(0x4010000000000000, double:4.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r7
            double r7 = com.moat.analytics.mobile.vng.s.a()     // Catch:{ Exception -> 0x00d1 }
            r9 = 0
            int r10 = r12.p     // Catch:{ Exception -> 0x00d1 }
            if (r2 <= r10) goto L_0x0044
            r12.p = r2     // Catch:{ Exception -> 0x00d1 }
        L_0x0044:
            int r10 = r12.l     // Catch:{ Exception -> 0x00d1 }
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r10 != r11) goto L_0x004c
            r12.l = r3     // Catch:{ Exception -> 0x00d1 }
        L_0x004c:
            if (r4 == 0) goto L_0x008c
            com.moat.analytics.mobile.vng.h$a r3 = r12.m     // Catch:{ Exception -> 0x00d1 }
            com.moat.analytics.mobile.vng.h$a r4 = com.moat.analytics.mobile.vng.h.a.UNINITIALIZED     // Catch:{ Exception -> 0x00d1 }
            if (r3 != r4) goto L_0x005b
            com.moat.analytics.mobile.vng.MoatAdEventType r9 = com.moat.analytics.mobile.vng.MoatAdEventType.AD_EVT_START     // Catch:{ Exception -> 0x00d1 }
        L_0x0056:
            com.moat.analytics.mobile.vng.h$a r3 = com.moat.analytics.mobile.vng.h.a.PLAYING     // Catch:{ Exception -> 0x00d1 }
        L_0x0058:
            r12.m = r3     // Catch:{ Exception -> 0x00d1 }
            goto L_0x0097
        L_0x005b:
            com.moat.analytics.mobile.vng.h$a r3 = r12.m     // Catch:{ Exception -> 0x00d1 }
            com.moat.analytics.mobile.vng.h$a r4 = com.moat.analytics.mobile.vng.h.a.PAUSED     // Catch:{ Exception -> 0x00d1 }
            if (r3 != r4) goto L_0x0064
            com.moat.analytics.mobile.vng.MoatAdEventType r9 = com.moat.analytics.mobile.vng.MoatAdEventType.AD_EVT_PLAYING     // Catch:{ Exception -> 0x00d1 }
            goto L_0x0056
        L_0x0064:
            double r3 = (double) r2
            java.lang.Double.isNaN(r3)
            double r3 = r3 / r5
            double r3 = java.lang.Math.floor(r3)     // Catch:{ Exception -> 0x00d1 }
            int r3 = (int) r3     // Catch:{ Exception -> 0x00d1 }
            int r3 = r3 - r0
            r4 = -1
            if (r3 <= r4) goto L_0x0097
            r4 = 3
            if (r3 >= r4) goto L_0x0097
            com.moat.analytics.mobile.vng.MoatAdEventType[] r4 = f     // Catch:{ Exception -> 0x00d1 }
            r3 = r4[r3]     // Catch:{ Exception -> 0x00d1 }
            java.util.Map r4 = r12.g     // Catch:{ Exception -> 0x00d1 }
            boolean r4 = r4.containsKey(r3)     // Catch:{ Exception -> 0x00d1 }
            if (r4 != 0) goto L_0x0097
            java.util.Map r4 = r12.g     // Catch:{ Exception -> 0x00d1 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x00d1 }
            r4.put(r3, r5)     // Catch:{ Exception -> 0x00d1 }
            r9 = r3
            goto L_0x0097
        L_0x008c:
            com.moat.analytics.mobile.vng.h$a r3 = r12.m     // Catch:{ Exception -> 0x00d1 }
            com.moat.analytics.mobile.vng.h$a r4 = com.moat.analytics.mobile.vng.h.a.PAUSED     // Catch:{ Exception -> 0x00d1 }
            if (r3 == r4) goto L_0x0097
            com.moat.analytics.mobile.vng.MoatAdEventType r9 = com.moat.analytics.mobile.vng.MoatAdEventType.AD_EVT_PAUSED     // Catch:{ Exception -> 0x00d1 }
            com.moat.analytics.mobile.vng.h$a r3 = com.moat.analytics.mobile.vng.h.a.PAUSED     // Catch:{ Exception -> 0x00d1 }
            goto L_0x0058
        L_0x0097:
            if (r9 == 0) goto L_0x009b
            r3 = 1
            goto L_0x009c
        L_0x009b:
            r3 = 0
        L_0x009c:
            if (r3 != 0) goto L_0x00ba
            double r4 = r12.o     // Catch:{ Exception -> 0x00d1 }
            boolean r4 = java.lang.Double.isNaN(r4)     // Catch:{ Exception -> 0x00d1 }
            if (r4 != 0) goto L_0x00ba
            double r4 = r12.o     // Catch:{ Exception -> 0x00d1 }
            r6 = 0
            double r4 = r4 - r7
            double r4 = java.lang.Math.abs(r4)     // Catch:{ Exception -> 0x00d1 }
            r10 = 4587366580439587226(0x3fa999999999999a, double:0.05)
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x00ba
            com.moat.analytics.mobile.vng.MoatAdEventType r9 = com.moat.analytics.mobile.vng.MoatAdEventType.AD_EVT_VOLUME_CHANGE     // Catch:{ Exception -> 0x00d1 }
            r3 = 1
        L_0x00ba:
            if (r3 == 0) goto L_0x00cc
            com.moat.analytics.mobile.vng.MoatAdEvent r3 = new com.moat.analytics.mobile.vng.MoatAdEvent     // Catch:{ Exception -> 0x00d1 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00d1 }
            java.lang.Double r4 = java.lang.Double.valueOf(r7)     // Catch:{ Exception -> 0x00d1 }
            r3.<init>(r9, r2, r4)     // Catch:{ Exception -> 0x00d1 }
            r12.dispatchEvent(r3)     // Catch:{ Exception -> 0x00d1 }
        L_0x00cc:
            r12.o = r7     // Catch:{ Exception -> 0x00d1 }
            r12.q = r1     // Catch:{ Exception -> 0x00d1 }
            return r0
        L_0x00d1:
            int r2 = r12.q
            int r3 = r2 + 1
            r12.q = r3
            r3 = 5
            if (r2 >= r3) goto L_0x00db
            goto L_0x00dc
        L_0x00db:
            r0 = 0
        L_0x00dc:
            return r0
        L_0x00dd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.vng.h.m():boolean");
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            p.a("[SUCCESS] ", a() + " stopTracking succeeded for " + e());
        } catch (Exception e) {
            m.a(e);
        }
    }
}
