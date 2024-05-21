package com.tapjoy.internal;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class kd {
    static final Logger a = Logger.getLogger(kd.class.getName());

    private kd() {
    }

    public static void a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                try {
                    a.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
                } catch (IOException e2) {
                    a.log(Level.SEVERE, "IOException should not have been thrown.", e2);
                }
            }
        }
    }
}
