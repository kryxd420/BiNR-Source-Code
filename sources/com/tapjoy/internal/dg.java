package com.tapjoy.internal;

import java.util.ArrayList;

public final class dg {
    private static dg c = new dg();
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();

    private dg() {
    }

    public static dg a() {
        return c;
    }

    public final boolean b() {
        return this.b.size() > 0;
    }
}
