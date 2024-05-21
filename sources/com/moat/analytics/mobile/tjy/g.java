package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void run() {
        try {
            this.a.a("Shutting down.");
            this.a.l.b();
            ad unused = this.a.l = null;
        } catch (Exception e) {
            a.a(e);
        }
    }
}
