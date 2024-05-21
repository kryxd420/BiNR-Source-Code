package com.tapjoy.internal;

import com.tapjoy.internal.ec;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ed implements ec.a {
    private final BlockingQueue a = new LinkedBlockingQueue();
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.a);
    private final ArrayDeque c = new ArrayDeque();
    private ec d = null;

    public final void a() {
        this.d = null;
        b();
    }

    public final void a(ec ecVar) {
        ecVar.d = this;
        this.c.add(ecVar);
        if (this.d == null) {
            b();
        }
    }

    private void b() {
        this.d = (ec) this.c.poll();
        if (this.d != null) {
            this.d.executeOnExecutor(this.b, new Object[0]);
        }
    }
}
