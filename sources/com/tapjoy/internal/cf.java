package com.tapjoy.internal;

import javax.annotation.Nullable;

public final class cf implements Runnable {
    private final cd a;
    @Nullable
    private final ci b;

    protected cf(cd cdVar, @Nullable ci ciVar) {
        this.a = cdVar;
        this.b = ciVar;
    }

    public final void run() {
        try {
            Object f = this.a.f();
            if (this.b != null && !(this.b instanceof cj)) {
                this.b.a(this.a, f);
            }
        } catch (Throwable unused) {
            if (this.b != null && !(this.b instanceof cj)) {
                this.b.a(this.a);
            }
        }
    }
}
