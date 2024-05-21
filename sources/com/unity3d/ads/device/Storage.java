package com.unity3d.ads.device;

import com.unity3d.ads.device.StorageManager;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.JsonStorage;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import java.io.File;
import org.json.JSONObject;

public class Storage extends JsonStorage {
    private String _targetFileName;
    private StorageManager.StorageType _type;

    public Storage(String str, StorageManager.StorageType storageType) {
        this._targetFileName = str;
        this._type = storageType;
    }

    public StorageManager.StorageType getType() {
        return this._type;
    }

    public synchronized boolean readStorage() {
        File file = new File(this._targetFileName);
        if (Utilities.readFile(file) == null) {
            return false;
        }
        try {
            setData(new JSONObject(Utilities.readFile(file)));
            return true;
        } catch (Exception e) {
            DeviceLog.exception("Error creating storage JSON", e);
            return false;
        }
    }

    public synchronized boolean initStorage() {
        readStorage();
        super.initData();
        return true;
    }

    public synchronized boolean writeStorage() {
        File file = new File(this._targetFileName);
        if (getData() == null) {
            return false;
        }
        return Utilities.writeFile(file, getData().toString());
    }

    public synchronized boolean clearStorage() {
        clearData();
        return new File(this._targetFileName).delete();
    }

    public synchronized boolean storageFileExists() {
        return new File(this._targetFileName).exists();
    }

    public synchronized void sendEvent(StorageEvent storageEvent, Object obj) {
        boolean z = false;
        if (WebViewApp.getCurrentApp() != null) {
            z = WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORAGE, storageEvent, this._type.name(), obj);
        }
        if (!z) {
            DeviceLog.debug("Couldn't send storage event to WebApp");
        }
    }
}
