package com.tapjoy.internal;

import com.tapjoy.TJGetCurrencyBalanceListener;

public class TJGetCurrencyBalanceListenerNative implements TJGetCurrencyBalanceListener {
    private final long a;

    private static native void onGetCurrencyBalanceResponseFailureNative(long j, String str);

    private static native void onGetCurrencyBalanceResponseNative(long j, String str, int i);

    private TJGetCurrencyBalanceListenerNative(long j) {
        if (j != 0) {
            this.a = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void onGetCurrencyBalanceResponse(String str, int i) {
        onGetCurrencyBalanceResponseNative(this.a, str, i);
    }

    public void onGetCurrencyBalanceResponseFailure(String str) {
        onGetCurrencyBalanceResponseFailureNative(this.a, str);
    }

    @fx
    static Object create(long j) {
        return new TJGetCurrencyBalanceListenerNative(j);
    }
}
