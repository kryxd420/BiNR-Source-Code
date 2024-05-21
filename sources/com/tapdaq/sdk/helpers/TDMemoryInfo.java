package com.tapdaq.sdk.helpers;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v4.media.session.PlaybackStateCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TDMemoryInfo {
    public static long GetAvailableMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    public static byte[] InputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static int DecodedImageSizeInBytes(InputStream inputStream, BitmapFactory.Options options) {
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        return ImageSizeInBytes(options.outWidth, options.outHeight);
    }

    public static int ImageSizeInBytes(int i, int i2) {
        return ((i * i2) * 32) / 8;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
