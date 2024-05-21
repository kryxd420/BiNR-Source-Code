package com.unity.purchasing.common;

public interface IDelayedExecutor {
    void ExecuteDelayed(Runnable runnable, long j);
}
