package com.tapdaq.sdk.tasks;

import android.content.Context;
import com.tapdaq.sdk.helpers.TLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TDTaskManager {
    private static TDTaskManager mInstance;
    private ExecutorService mExecutor;
    private ExecutorService mExecutorQueue;
    private TDThreadRunner mThreadRunner;

    private TDTaskManager() {
    }

    public static synchronized TDTaskManager getInstance() {
        TDTaskManager tDTaskManager;
        synchronized (TDTaskManager.class) {
            if (mInstance == null) {
                mInstance = new TDTaskManager();
            }
            tDTaskManager = mInstance;
        }
        return tDTaskManager;
    }

    public void setMainThreadRunner(TDThreadRunner tDThreadRunner) {
        this.mThreadRunner = tDThreadRunner;
    }

    public void setExecutor(ExecutorService executorService) {
        this.mExecutor = executorService;
    }

    public void setExecutorQueue(ExecutorService executorService) {
        this.mExecutorQueue = executorService;
    }

    public void executeInQueue(Runnable runnable) {
        if (this.mExecutorQueue == null) {
            this.mExecutorQueue = Executors.newSingleThreadExecutor();
        }
        this.mExecutorQueue.execute(runnable);
    }

    public void execute(Runnable runnable) {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newScheduledThreadPool(1);
        }
        this.mExecutor.execute(runnable);
    }

    public void runOnMainThread(Context context, Runnable runnable) {
        if (context != null) {
            if (this.mThreadRunner == null) {
                this.mThreadRunner = new TDMainThreadRunner();
            }
            this.mThreadRunner.run(context, runnable);
            return;
        }
        TLog.error("Unable to run task on main thread, Context is null");
    }

    public void destroy() {
        if (this.mExecutorQueue != null && !this.mExecutorQueue.isShutdown()) {
            this.mExecutorQueue.shutdown();
        }
        if (this.mExecutor != null && !this.mExecutor.isShutdown()) {
            this.mExecutor.shutdown();
        }
        this.mThreadRunner = null;
        mInstance = null;
    }
}
