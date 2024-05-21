package com.tapdaq.sdk.storage;

import android.content.Context;

public class FileStorageProvider {
    private static FileStorageBase mInstance;

    public static synchronized void setInstance(FileStorageBase fileStorageBase) {
        synchronized (FileStorageProvider.class) {
            mInstance = fileStorageBase;
        }
    }

    public static synchronized FileStorageBase getInstance(Context context) {
        FileStorageBase fileStorageBase;
        synchronized (FileStorageProvider.class) {
            if (mInstance == null) {
                mInstance = new FileStorage(context);
            }
            fileStorageBase = mInstance;
        }
        return fileStorageBase;
    }
}
