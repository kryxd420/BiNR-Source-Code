package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.internal.fw;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public final class ft extends gx implements Observer {
    private final Map b = new HashMap();
    private final fm c = new fm();
    private boolean d;
    private final gd e = new gd() {
        /* access modifiers changed from: protected */
        public final /* bridge */ /* synthetic */ String a(Object obj) {
            return "AppLaunch";
        }

        /* access modifiers changed from: protected */
        public final boolean a() {
            return super.a() && !ho.c();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            return TJPlacementManager.createPlacement(context, "AppLaunch", true, tJPlacementListener);
        }
    };

    public static void a() {
    }

    static {
        gx.a = new ft();
    }

    private ft() {
    }

    public final void update(Observable observable, Object obj) {
        fw.a aVar = fw.d;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        if (r3 == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005f, code lost:
        if (r5.c.a() != false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0061, code lost:
        r5.e.c((java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.app.Activity r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 == 0) goto L_0x0055
            int r1 = r6.getTaskId()
            r2 = -1
            r3 = 0
            if (r1 != r2) goto L_0x000c
            goto L_0x0053
        L_0x000c:
            android.content.Intent r6 = r6.getIntent()
            if (r6 == 0) goto L_0x0053
            java.util.Set r2 = r6.getCategories()
            if (r2 == 0) goto L_0x002e
            java.lang.String r4 = "android.intent.category.LAUNCHER"
            boolean r2 = r2.contains(r4)
            if (r2 == 0) goto L_0x002e
            java.lang.String r2 = "android.intent.action.MAIN"
            java.lang.String r4 = r6.getAction()
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x002e
            r2 = 1
            goto L_0x002f
        L_0x002e:
            r2 = 0
        L_0x002f:
            if (r2 != 0) goto L_0x0032
            goto L_0x0053
        L_0x0032:
            android.content.ComponentName r6 = r6.getComponent()
            if (r6 != 0) goto L_0x0039
            goto L_0x0053
        L_0x0039:
            java.lang.String r6 = r6.getClassName()
            java.util.Map r2 = r5.b
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.Object r6 = r2.put(r6, r4)
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 == 0) goto L_0x0052
            int r6 = r6.intValue()
            if (r6 != r1) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r3 = 1
        L_0x0053:
            if (r3 != 0) goto L_0x0061
        L_0x0055:
            boolean r6 = r5.d
            if (r6 != 0) goto L_0x0067
            com.tapjoy.internal.fm r6 = r5.c
            boolean r6 = r6.a()
            if (r6 == 0) goto L_0x0067
        L_0x0061:
            com.tapjoy.internal.gd r6 = r5.e
            r1 = 0
            r6.c(r1)
        L_0x0067:
            r5.d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ft.a(android.app.Activity):void");
    }
}
