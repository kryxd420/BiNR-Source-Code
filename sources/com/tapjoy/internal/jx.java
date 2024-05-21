package com.tapjoy.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class jx {
    static final ka a = new ka() {
        public final boolean hasNext() {
            return false;
        }

        public final Object next() {
            throw new NoSuchElementException();
        }
    };
    private static final Iterator b = new Iterator() {
        public final boolean hasNext() {
            return false;
        }

        public final Object next() {
            throw new NoSuchElementException();
        }

        public final void remove() {
            throw new IllegalStateException();
        }
    };

    public static Object a(Iterator it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
