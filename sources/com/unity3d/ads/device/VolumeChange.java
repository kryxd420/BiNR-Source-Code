package com.unity3d.ads.device;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.unity3d.ads.properties.ClientProperties;
import java.util.ArrayList;
import java.util.Iterator;

public class VolumeChange {
    private static ContentObserver _contentObserver;
    private static ArrayList<IVolumeChangeListener> _listeners;

    public static void startObserving() {
        ContentResolver contentResolver;
        if (_contentObserver == null) {
            _contentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
                public boolean deliverSelfNotifications() {
                    return false;
                }

                public void onChange(boolean z, Uri uri) {
                    VolumeChange.triggerListeners();
                }
            };
            Context applicationContext = ClientProperties.getApplicationContext();
            if (applicationContext != null && (contentResolver = applicationContext.getContentResolver()) != null) {
                contentResolver.registerContentObserver(Settings.System.CONTENT_URI, true, _contentObserver);
            }
        }
    }

    public static void stopObservering() {
        ContentResolver contentResolver;
        if (_contentObserver != null) {
            Context applicationContext = ClientProperties.getApplicationContext();
            if (!(applicationContext == null || (contentResolver = applicationContext.getContentResolver()) == null)) {
                contentResolver.unregisterContentObserver(_contentObserver);
            }
            _contentObserver = null;
        }
    }

    public static void registerListener(IVolumeChangeListener iVolumeChangeListener) {
        if (_listeners == null) {
            _listeners = new ArrayList<>();
        }
        if (!_listeners.contains(iVolumeChangeListener)) {
            startObserving();
            _listeners.add(iVolumeChangeListener);
        }
    }

    public static void unregisterListener(IVolumeChangeListener iVolumeChangeListener) {
        if (_listeners.contains(iVolumeChangeListener)) {
            _listeners.remove(iVolumeChangeListener);
        }
        if (_listeners == null || _listeners.size() == 0) {
            stopObservering();
        }
    }

    public static void clearAllListeners() {
        if (_listeners != null) {
            _listeners.clear();
        }
        stopObservering();
        _listeners = null;
    }

    /* access modifiers changed from: private */
    public static void triggerListeners() {
        if (_listeners != null) {
            Iterator<IVolumeChangeListener> it = _listeners.iterator();
            while (it.hasNext()) {
                IVolumeChangeListener next = it.next();
                next.onVolumeChanged(Device.getStreamVolume(next.getStreamType()));
            }
        }
    }
}
