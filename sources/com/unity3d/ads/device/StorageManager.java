package com.unity3d.ads.device;

import android.content.Context;
import com.unity3d.ads.properties.SdkProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageManager {
    protected static final Map<StorageType, String> _storageFileMap = new HashMap();
    protected static final List<Storage> _storages = new ArrayList();

    public enum StorageType {
        PRIVATE,
        PUBLIC
    }

    public static boolean init(Context context) {
        File filesDir;
        if (context == null || (filesDir = context.getFilesDir()) == null) {
            return false;
        }
        StorageType storageType = StorageType.PUBLIC;
        addStorageLocation(storageType, filesDir + "/" + SdkProperties.getLocalStorageFilePrefix() + "public-data.json");
        if (!setupStorage(StorageType.PUBLIC)) {
            return false;
        }
        StorageType storageType2 = StorageType.PRIVATE;
        addStorageLocation(storageType2, filesDir + "/" + SdkProperties.getLocalStorageFilePrefix() + "private-data.json");
        if (!setupStorage(StorageType.PRIVATE)) {
            return false;
        }
        return true;
    }

    public static void initStorage(StorageType storageType) {
        if (hasStorage(storageType)) {
            Storage storage = getStorage(storageType);
            if (storage != null) {
                storage.initStorage();
            }
        } else if (_storageFileMap.containsKey(storageType)) {
            Storage storage2 = new Storage(_storageFileMap.get(storageType), storageType);
            storage2.initStorage();
            _storages.add(storage2);
        }
    }

    private static boolean setupStorage(StorageType storageType) {
        if (hasStorage(storageType)) {
            return true;
        }
        initStorage(storageType);
        Storage storage = getStorage(storageType);
        if (storage != null && !storage.storageFileExists()) {
            storage.writeStorage();
        }
        return storage != null;
    }

    public static Storage getStorage(StorageType storageType) {
        if (_storages == null) {
            return null;
        }
        for (Storage next : _storages) {
            if (next.getType().equals(storageType)) {
                return next;
            }
        }
        return null;
    }

    public static boolean hasStorage(StorageType storageType) {
        if (_storages == null) {
            return false;
        }
        for (Storage type : _storages) {
            if (type.getType().equals(storageType)) {
                return true;
            }
        }
        return false;
    }

    public static synchronized void addStorageLocation(StorageType storageType, String str) {
        synchronized (StorageManager.class) {
            if (!_storageFileMap.containsKey(storageType)) {
                _storageFileMap.put(storageType, str);
            }
        }
    }

    public static synchronized void removeStorage(StorageType storageType) {
        synchronized (StorageManager.class) {
            if (getStorage(storageType) != null) {
                _storages.remove(getStorage(storageType));
            }
            if (_storageFileMap != null) {
                _storageFileMap.remove(storageType);
            }
        }
    }
}
