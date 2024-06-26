package com.tapjoy.internal;

import com.tapjoy.TJConnectListener;

public class TJConnectListenerNative implements TJConnectListener {
    private final long a;

    private static native void onConnectFailureNative(long j);

    private static native void onConnectSuccessNative(long j);

    private TJConnectListenerNative(long j) {
        if (j != 0) {
            this.a = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void onConnectSuccess() {
        onConnectSuccessNative(this.a);
    }

    public void onConnectFailure() {
        onConnectFailureNative(this.a);
    }

    @fx
    static Object create(long j) {
        return new TJConnectListenerNative(j);
    }
}
