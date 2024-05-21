package com.tapjoy.internal;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class gv {
    public static final bd a = new bd() {
        public final boolean a(Runnable runnable) {
            GLSurfaceView gLSurfaceView = (GLSurfaceView) gv.c.a();
            if (gLSurfaceView == null) {
                return false;
            }
            gLSurfaceView.queueEvent(runnable);
            return true;
        }
    };
    private static Activity b;
    /* access modifiers changed from: private */
    public static final cb c = new cb();
    /* access modifiers changed from: private */
    public static final cb d = new cb();

    static void a(GLSurfaceView gLSurfaceView) {
        new Object[1][0] = gLSurfaceView;
        c.a(gLSurfaceView);
        gLSurfaceView.queueEvent(new Runnable() {
            public final void run() {
                Thread currentThread = Thread.currentThread();
                new Object[1][0] = currentThread;
                gv.d.a(currentThread);
            }
        });
    }

    public static Activity a() {
        Activity activity = b;
        return activity == null ? b.a() : activity;
    }

    public static Thread b() {
        return (Thread) d.a();
    }
}
