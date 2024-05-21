package com.tapdaq.sdk.tasks;

import android.content.Context;

public interface TDThreadRunner {
    void run(Context context, Runnable runnable);
}
