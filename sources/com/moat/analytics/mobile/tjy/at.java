package com.moat.analytics.mobile.tjy;

import java.util.concurrent.ThreadFactory;

class at implements ThreadFactory {
    final /* synthetic */ as a;

    at(as asVar) {
        this.a = asVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "MoatStatus");
        thread.setDaemon(true);
        return thread;
    }
}
