package com.tapjoy.internal;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public final class bc extends AbstractMap {
    private final HashMap a = new HashMap();
    private final ca b = new ca();

    public final int size() {
        b();
        return this.a.size();
    }

    public final void clear() {
        this.a.clear();
        do {
        } while (this.b.a() != null);
    }

    public final boolean containsKey(Object obj) {
        b();
        return this.a.containsKey(obj);
    }

    public final boolean containsValue(Object obj) {
        b();
        for (bz bzVar : this.a.values()) {
            if (obj.equals(bzVar.get())) {
                return true;
            }
        }
        return false;
    }

    public final Object get(Object obj) {
        b();
        return a((bz) this.a.get(obj));
    }

    public final Object put(Object obj, Object obj2) {
        b();
        return a((bz) this.a.put(obj, new bz(obj, obj2, this.b)));
    }

    public final Object remove(Object obj) {
        b();
        return a((bz) this.a.remove(obj));
    }

    private static Object a(bz bzVar) {
        if (bzVar != null) {
            return bzVar.get();
        }
        return null;
    }

    public final Set entrySet() {
        b();
        throw new UnsupportedOperationException();
    }

    public final Set keySet() {
        b();
        return this.a.keySet();
    }

    public final Collection values() {
        b();
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object obj) {
        b();
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        b();
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        b();
        throw new UnsupportedOperationException();
    }

    private void b() {
        while (true) {
            bz a2 = this.b.a();
            if (a2 != null) {
                this.a.remove(a2.a);
            } else {
                return;
            }
        }
    }

    public static bc a() {
        return new bc();
    }
}
