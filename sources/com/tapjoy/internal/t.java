package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.OutputStream;

public final class t implements bg {
    public static final t a = new t();

    public final /* synthetic */ void a(OutputStream outputStream, Object obj) {
        if (!((Bitmap) obj).compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
            throw new RuntimeException();
        }
    }

    private t() {
    }

    /* renamed from: a */
    public final Bitmap b(final InputStream inputStream) {
        try {
            return (Bitmap) ab.a(new be() {
                public final /* synthetic */ Object call() {
                    if (inputStream instanceof bf) {
                        return BitmapFactory.decodeStream(inputStream);
                    }
                    return BitmapFactory.decodeStream(new bf(inputStream));
                }
            });
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }
}
