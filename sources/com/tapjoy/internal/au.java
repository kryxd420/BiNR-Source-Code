package com.tapjoy.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;

public final class au extends at {
    private final LinkedHashMap a = new LinkedHashMap(0, 0.75f, true);
    private int b = 10;

    private void a() {
        int size = this.a.size() - this.b;
        if (size > 0) {
            Iterator it = this.a.entrySet().iterator();
            while (size > 0 && it.hasNext()) {
                size--;
                it.next();
                it.remove();
            }
        }
    }

    public final void a(Object obj, Object obj2) {
        super.a(obj, obj2);
        a();
    }

    /* access modifiers changed from: protected */
    public final ar a(Object obj, boolean z) {
        ap apVar = (ap) this.a.get(obj);
        if (apVar != null || !z) {
            return apVar;
        }
        ap apVar2 = new ap(obj);
        this.a.put(obj, apVar2);
        a();
        return apVar2;
    }
}
