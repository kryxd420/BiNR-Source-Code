package com.tapjoy.internal;

import android.content.Context;
import android.view.ViewGroup;

public final class it extends al {
    private final hy a;
    private final iu b;
    private ad c = null;

    public it(Context context, hy hyVar, iu iuVar) {
        super(context);
        this.a = hyVar;
        this.b = iuVar;
        addView(iuVar, new ViewGroup.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        ad adVar;
        ad adVar2;
        ad a2 = ad.a(getContext());
        if (!this.a.a()) {
            adVar = ad.LANDSCAPE;
            if (!a2.a()) {
                setRotationCount(0);
            } else if (a2.c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(1);
            }
        } else if (this.a.b()) {
            if (a2.a()) {
                adVar2 = ad.PORTRAIT;
            } else if (a2.b() || !ad.b(getContext()).a()) {
                adVar2 = ad.LANDSCAPE;
            } else {
                adVar2 = ad.PORTRAIT;
            }
            setRotationCount(0);
            adVar = adVar2;
        } else {
            adVar = ad.PORTRAIT;
            if (!a2.b()) {
                setRotationCount(0);
            } else if (a2.c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(3);
            }
        }
        if (this.c != adVar) {
            this.c = adVar;
            this.b.setLandscape(this.c.b());
        }
        super.onMeasure(i, i2);
    }
}
