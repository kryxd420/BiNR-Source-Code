package com.tapjoy.internal;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;

public final class hh {
    private final File a;

    public hh(File file) {
        this.a = file;
    }

    public final synchronized boolean a() {
        if (b() != null) {
            return false;
        }
        try {
            bj.a(this.a, UUID.randomUUID().toString());
            if (b() != null) {
                return true;
            }
            return false;
        } catch (IOException e) {
            this.a.delete();
            throw e;
        } catch (IOException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final String b() {
        if (!this.a.exists()) {
            return null;
        }
        try {
            String a2 = bj.a(this.a, an.c);
            if (a2.length() > 0) {
                return a2;
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }
}
