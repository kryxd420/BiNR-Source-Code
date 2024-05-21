package com.moat.analytics.mobile.tjy;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.base.exception.a;

class s implements Runnable {
    final /* synthetic */ n a;

    s(n nVar) {
        this.a = nVar;
    }

    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new t(this));
        } catch (Exception e) {
            a.a(e);
        }
    }
}
