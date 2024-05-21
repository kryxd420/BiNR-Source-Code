package com.tapjoy.internal;

import com.tapjoy.internal.gj;

public final class ga {
    public fu a;
    public volatile gj.a b;
    public int c;
    public volatile gj.a d;
    public volatile gj.a e;

    public final void a() {
        a(16);
        gj.a aVar = this.d;
        if (aVar != null) {
            this.d = null;
            aVar.b().c();
        }
    }

    public final synchronized void a(int i) {
        gj.a aVar = this.b;
        if (aVar != null && this.c < i) {
            this.c = i | this.c;
            aVar.a("state", (Object) Integer.valueOf(this.c)).b().c();
        }
    }
}
