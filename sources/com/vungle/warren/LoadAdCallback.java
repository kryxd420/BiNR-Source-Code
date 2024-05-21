package com.vungle.warren;

public interface LoadAdCallback {
    void onAdLoad(String str);

    void onError(String str, Throwable th);
}
