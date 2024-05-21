package com.tapjoy.internal;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public final class bb extends AbstractSet implements Serializable, Set {
    transient WeakHashMap a;

    public bb() {
        this(new WeakHashMap());
    }

    private bb(WeakHashMap weakHashMap) {
        this.a = weakHashMap;
    }

    public final boolean add(Object obj) {
        return this.a.put(obj, this) == null;
    }

    public final void clear() {
        this.a.clear();
    }

    public final Object clone() {
        try {
            return (bb) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean contains(Object obj) {
        return this.a.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    public final Iterator iterator() {
        return this.a.keySet().iterator();
    }

    public final boolean remove(Object obj) {
        return this.a.remove(obj) != null;
    }

    public final int size() {
        return this.a.size();
    }
}
