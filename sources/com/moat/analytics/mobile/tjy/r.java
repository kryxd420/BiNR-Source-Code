package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class r implements Runnable {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public void run() {
        try {
            this.a.a.b();
        } catch (Exception e) {
            a.a(e);
        }
    }
}
