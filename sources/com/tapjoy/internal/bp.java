package com.tapjoy.internal;

import java.io.Writer;

public final class bp implements bo {
    public final String a;

    public bp(String str) {
        this.a = str;
    }

    public final void a(Writer writer) {
        writer.write(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof bp) {
            return this.a.equals(((bp) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
