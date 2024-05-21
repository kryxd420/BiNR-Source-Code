package com.tapdaq.sdk.storage;

import android.content.Context;

public class StorageProvider {
    private static StorageBase mInstance;

    public static synchronized void setInstance(StorageBase storageBase) {
        synchronized (StorageProvider.class) {
            mInstance = storageBase;
        }
    }

    public static synchronized StorageBase getInstance(Context context) {
        StorageBase storageBase;
        synchronized (StorageProvider.class) {
            if (mInstance == null) {
                mInstance = new Storage(context);
            }
            storageBase = mInstance;
        }
        return storageBase;
    }
}
