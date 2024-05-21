package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class j implements Runnable {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public void run() {
        try {
            if (this.a.f.get() == null || this.a.e()) {
                this.a.c();
            } else if (Boolean.valueOf(this.a.i()).booleanValue()) {
                this.a.d.postDelayed(this, 200);
            } else {
                this.a.c();
            }
        } catch (Exception e) {
            this.a.c();
            a.a(e);
        }
    }
}
