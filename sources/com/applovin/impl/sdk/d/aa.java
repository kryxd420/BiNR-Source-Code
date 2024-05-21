package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.j;

public class aa extends a {
    private final Runnable a;

    public aa(j jVar, Runnable runnable) {
        super("TaskRunnable", jVar);
        this.a = runnable;
    }

    public i a() {
        return i.f;
    }

    public void run() {
        this.a.run();
    }
}
