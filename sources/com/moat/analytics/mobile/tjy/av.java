package com.moat.analytics.mobile.tjy;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.base.functional.a;

class av implements Runnable {
    private static final long b = 90000;
    private final aa a;
    private final String c;
    /* access modifiers changed from: private */
    public final ax d;
    private ar e;

    private av(String str, aa aaVar, ax axVar) {
        this.e = ar.OFF;
        this.a = aaVar;
        this.d = axVar;
        this.c = "https://z.moatads.com/" + str + "/android/" + "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7".substring(0, 7) + "/status.json";
    }

    /* synthetic */ av(String str, aa aaVar, ax axVar, at atVar) {
        this(str, aaVar, axVar);
    }

    private void a() {
        long j = 0;
        while (true) {
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (currentTimeMillis < b) {
                try {
                    Thread.sleep((b + 10) - currentTimeMillis);
                } catch (InterruptedException unused) {
                }
            }
            j = System.currentTimeMillis();
            ar b2 = b();
            Handler handler = new Handler(Looper.getMainLooper());
            b2.equals(this.e);
            this.e = b2;
            handler.post(new aw(this, b2));
        }
    }

    private ar b() {
        a a2 = this.a.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=1.7.10");
        if (!a2.c()) {
            return ar.OFF;
        }
        u uVar = new u((String) a2.b());
        boolean unused = as.d = uVar.a();
        int unused2 = as.e = uVar.c();
        return uVar.b() ? ar.ON : ar.OFF;
    }

    public void run() {
        try {
            a();
        } catch (Exception e2) {
            this.e = ar.OFF;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e2);
        }
    }
}
