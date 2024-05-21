package com.unity3d.ads.properties;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.log.DeviceLog;
import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class ClientProperties {
    private static final X500Principal DEBUG_CERT = new X500Principal("CN=Android Debug,O=Android,C=US");
    private static WeakReference<Activity> _activity;
    private static Application _application;
    private static Context _applicationContext;
    private static String _gameId;
    private static IUnityAdsListener _listener;

    public static Activity getActivity() {
        return (Activity) _activity.get();
    }

    public static void setActivity(Activity activity) {
        _activity = new WeakReference<>(activity);
    }

    public static Context getApplicationContext() {
        return _applicationContext;
    }

    public static void setApplicationContext(Context context) {
        _applicationContext = context;
    }

    public static Application getApplication() {
        return _application;
    }

    public static void setApplication(Application application) {
        _application = application;
    }

    public static IUnityAdsListener getListener() {
        return _listener;
    }

    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        _listener = iUnityAdsListener;
    }

    public static String getGameId() {
        return _gameId;
    }

    public static void setGameId(String str) {
        _gameId = str;
    }

    public static String getAppName() {
        return _applicationContext.getPackageName();
    }

    public static String getAppVersion() {
        try {
            return getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            DeviceLog.exception("Error getting package info", e);
            return null;
        }
    }

    public static boolean isAppDebuggable() {
        boolean z;
        int i = 0;
        if (getApplicationContext() == null) {
            return false;
        }
        PackageManager packageManager = getApplicationContext().getPackageManager();
        String packageName = getApplicationContext().getPackageName();
        boolean z2 = true;
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            int i2 = applicationInfo.flags & 2;
            applicationInfo.flags = i2;
            if (i2 == 0) {
                z2 = false;
            }
            z = z2;
            z2 = false;
        } catch (PackageManager.NameNotFoundException e) {
            DeviceLog.exception("Could not find name", e);
            z = false;
        }
        if (!z2) {
            return z;
        }
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(packageName, 64).signatures;
            int length = signatureArr.length;
            while (i < length) {
                boolean equals = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(DEBUG_CERT);
                if (equals) {
                    return equals;
                }
                i++;
                z = equals;
            }
            return z;
        } catch (PackageManager.NameNotFoundException e2) {
            DeviceLog.exception("Could not find name", e2);
            return z;
        } catch (CertificateException e3) {
            DeviceLog.exception("Certificate exception", e3);
            return z;
        }
    }
}
