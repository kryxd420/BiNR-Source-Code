package com.moat.analytics.mobile.tjy;

import android.view.View;
import java.util.Iterator;

class bp implements Iterator {
    final /* synthetic */ bo a;
    private int b;

    private bp(bo boVar) {
        this.a = boVar;
        this.b = -1;
    }

    /* renamed from: a */
    public View next() {
        this.b++;
        return this.a.a.getChildAt(this.b);
    }

    public boolean hasNext() {
        return this.b + 1 < this.a.a.getChildCount();
    }

    public void remove() {
        throw new UnsupportedOperationException("Not implemented. Under development.");
    }
}
