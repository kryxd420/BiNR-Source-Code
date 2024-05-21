package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class o implements Runnable {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public void run() {
        try {
            this.a.e();
        } catch (Exception e) {
            a.a(e);
        }
    }
}
