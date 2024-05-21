package com.tapjoy.internal;

import java.util.AbstractQueue;
import java.util.Iterator;

public abstract class aw extends AbstractQueue implements ba {
    public Iterator iterator() {
        return new Iterator() {
            private int b = 0;

            public final boolean hasNext() {
                return this.b < aw.this.size();
            }

            public final Object next() {
                aw awVar = aw.this;
                int i = this.b;
                this.b = i + 1;
                return awVar.a(i);
            }

            public final void remove() {
                if (this.b == 1) {
                    aw.this.b(1);
                    this.b = 0;
                    return;
                }
                throw new UnsupportedOperationException("For the first element only");
            }
        };
    }
}
