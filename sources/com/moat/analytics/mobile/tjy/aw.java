package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class aw implements Runnable {
    final /* synthetic */ ar a;
    final /* synthetic */ av b;

    aw(av avVar, ar arVar) {
        this.b = avVar;
        this.a = arVar;
    }

    public void run() {
        try {
            this.b.d.a(this.a);
        } catch (Exception e) {
            a.a(e);
        }
    }
}
