package com.moat.analytics.mobile.tjy;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.base.exception.a;

class q implements Runnable {
    final /* synthetic */ n a;

    q(n nVar) {
        this.a = nVar;
    }

    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new r(this));
        } catch (Exception e) {
            a.a(e);
        }
    }
}
