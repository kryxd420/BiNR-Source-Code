package com.tapdaq.sdk.storage;

import android.graphics.Bitmap;

public interface FileStorageBase {
    void clear();

    void deleteFile(String str, String str2);

    boolean exists(String str, String str2);

    String loadFile(String str, String str2);

    Bitmap loadImage(String str);

    void saveFile(String str, String str2, String str3, boolean z);

    void saveFile(String str, byte[] bArr, String str2, boolean z);

    void saveFileAsync(String str, String str2, String str3, boolean z);

    void saveImage(String str, Bitmap bitmap, boolean z);
}
