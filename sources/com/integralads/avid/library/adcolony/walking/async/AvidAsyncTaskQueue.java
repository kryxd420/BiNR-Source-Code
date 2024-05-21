package com.integralads.avid.library.adcolony.walking.async;

import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AvidAsyncTaskQueue implements AvidAsyncTask.AvidAsyncTaskListener {
    private static final int a = 1;
    private final BlockingQueue<Runnable> b = new LinkedBlockingQueue();
    private final ThreadPoolExecutor c = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.b);
    private final ArrayDeque<AvidAsyncTask> d = new ArrayDeque<>();
    private AvidAsyncTask e = null;

    public void submitTask(AvidAsyncTask avidAsyncTask) {
        avidAsyncTask.setListener(this);
        this.d.add(avidAsyncTask);
        if (this.e == null) {
            c();
        }
    }

    private void c() {
        this.e = this.d.poll();
        if (this.e != null) {
            this.e.start(this.c);
        }
    }

    public void onTaskCompleted(AvidAsyncTask avidAsyncTask) {
        this.e = null;
        c();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public AvidAsyncTask a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ArrayDeque<AvidAsyncTask> b() {
        return this.d;
    }
}
