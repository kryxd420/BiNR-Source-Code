package com.vungle.warren;

public interface InitCallback {
    void onAutoCacheAdAvailable(String str);

    void onError(Throwable th);

    void onSuccess();
}
