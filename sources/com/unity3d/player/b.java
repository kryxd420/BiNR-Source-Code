package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class b {
    protected m a = null;
    protected d b = null;
    protected Context c = null;
    protected String d = null;
    protected String e = "";

    b(String str, d dVar) {
        this.e = str;
        this.b = dVar;
    }

    /* access modifiers changed from: protected */
    public void reportError(String str) {
        if (this.b != null) {
            d dVar = this.b;
            dVar.reportError(this.e + " Error [" + this.d + "]", str);
            return;
        }
        e.Log(6, this.e + " Error [" + this.d + "]: " + str);
    }

    /* access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        if (this.c instanceof Activity) {
            ((Activity) this.c).runOnUiThread(runnable);
            return;
        }
        e.Log(5, "Not running " + this.e + " from an Activity; Ignoring execution request...");
    }

    /* access modifiers changed from: protected */
    public boolean runOnUiThreadWithSync(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    b bVar = b.this;
                    bVar.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                } catch (Throwable th) {
                    semaphore.release();
                    throw th;
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                return true;
            }
            reportError("Timeout waiting for vr state change!");
            return false;
        } catch (InterruptedException e2) {
            reportError("Interrupted while trying to acquire sync lock. " + e2.getLocalizedMessage());
            return false;
        }
    }
}
