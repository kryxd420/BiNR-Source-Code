package com.tapjoy.internal;

import java.util.Iterator;

public abstract class ka implements Iterator {
    protected ka() {
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
