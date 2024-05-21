package com.tapjoy.internal;

import java.lang.ref.WeakReference;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class cb {
    public WeakReference a;

    public final Object a() {
        WeakReference weakReference = this.a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final void a(Object obj) {
        this.a = new WeakReference(obj);
    }
}
