package com.vungle.warren;

public interface PlayAdCallback {
    void onAdEnd(String str, boolean z, boolean z2);

    void onAdStart(String str);

    void onError(String str, Throwable th);
}
