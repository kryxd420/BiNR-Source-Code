package com.tapdaq.sdk.tasks;

import android.content.Context;
import android.os.Handler;

public class TDMainThreadRunner implements TDThreadRunner {
    public void run(Context context, Runnable runnable) {
        new Handler(context.getMainLooper()).post(runnable);
    }
}
