package com.moat.analytics.mobile.tjy;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

class as implements ap {
    private static final AtomicReference a = new AtomicReference();
    /* access modifiers changed from: private */
    public static final Queue b = new ConcurrentLinkedQueue();
    /* access modifiers changed from: private */
    public static volatile ar c = ar.OFF;
    /* access modifiers changed from: private */
    public static volatile boolean d = false;
    /* access modifiers changed from: private */
    public static volatile int e = 200;

    as(aa aaVar) {
        if (a.get() == null) {
            if (a.compareAndSet((Object) null, Executors.newSingleThreadExecutor(new at(this)))) {
                ((ExecutorService) a.get()).submit(new av("TJY", aaVar, new au(this), (at) null));
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        synchronized (b) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = b.iterator();
            while (it.hasNext()) {
                aq aqVar = (aq) it.next();
                if (!aqVar.c()) {
                    if (currentTimeMillis - aqVar.d() >= 300000) {
                    }
                }
                it.remove();
            }
            if (b.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    b.remove();
                }
            }
        }
    }

    public ar a() {
        return c;
    }

    public void a(aq aqVar) {
        g();
        b.add(aqVar);
    }

    public boolean b() {
        return d;
    }

    public int c() {
        return e;
    }
}
