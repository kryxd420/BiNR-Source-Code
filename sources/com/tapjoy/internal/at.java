package com.tapjoy.internal;

import javax.annotation.Nullable;

public abstract class at implements aq {
    /* access modifiers changed from: protected */
    @Nullable
    public abstract ar a(Object obj, boolean z);

    public final Object a(Object obj) {
        ar a = a(obj, false);
        if (a != null) {
            return a.a();
        }
        return null;
    }

    public void a(Object obj, Object obj2) {
        a(obj, true).a(obj2);
    }
}
