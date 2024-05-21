package com.tapjoy.internal;

import android.view.View;

public abstract class cv {
    public abstract void a();

    public abstract void a(View view);

    public abstract void b();

    public static cv a(cw cwVar, cx cxVar) {
        if (cn.b()) {
            ds.a((Object) cwVar, "AdSessionConfiguration is null");
            ds.a((Object) cxVar, "AdSessionContext is null");
            return new dc(cwVar, cxVar);
        }
        throw new IllegalStateException("Method called before OMID activation");
    }
}
