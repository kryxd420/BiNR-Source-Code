package com.tapjoy.internal;

import java.io.IOException;

public final class by extends IOException {
    public by(Throwable th) {
        super(th.getMessage());
    }
}
