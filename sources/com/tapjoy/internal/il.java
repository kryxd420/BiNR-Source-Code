package com.tapjoy.internal;

import android.graphics.Bitmap;
import com.tapjoy.internal.ig;
import javax.annotation.Nonnull;

final class il implements ig.a {
    il() {
    }

    @Nonnull
    public final Bitmap a(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    public final byte[] a(int i) {
        return new byte[i];
    }

    public final int[] b(int i) {
        return new int[i];
    }
}
