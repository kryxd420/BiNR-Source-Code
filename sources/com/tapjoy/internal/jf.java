package com.tapjoy.internal;

import java.io.Closeable;
import java.io.Flushable;

public interface jf extends Closeable, Flushable {
    void a(iv ivVar, long j);

    void close();

    void flush();
}
