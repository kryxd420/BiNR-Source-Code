package com.applovin.mediation.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.sdk.AppLovinSdk;

public abstract class MediationAdapterBase implements MaxAdapter {
    protected static final String KEY_MUTED = "muted";
    private final j mSdk;
    private final String mTag = getClass().getSimpleName();
    private final AppLovinSdk mWrappingSdk;

    public MediationAdapterBase(AppLovinSdk appLovinSdk) {
        this.mWrappingSdk = appLovinSdk;
        this.mSdk = n.a(appLovinSdk);
    }

    protected static String mediationTag() {
        return "AppLovinSdk_9.3.0";
    }

    /* access modifiers changed from: protected */
    public void checkActivities(Context context, Class<?>... clsArr) {
        if (clsArr != null && clsArr.length > 0) {
            int length = clsArr.length;
            int i = 0;
            while (i < length) {
                Class<?> cls = clsArr[i];
                try {
                    if (context.getPackageManager().getActivityInfo(new ComponentName(context, cls), 128) != null) {
                        log("Found defined activity: " + cls.getName());
                        i++;
                    } else {
                        throw new PackageManager.NameNotFoundException("null_activity_info");
                    }
                } catch (Throwable th) {
                    log("No activity found for: " + cls);
                    throw new IllegalStateException("Activity " + cls.getName() + " not defined", th);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkExistence(Class<?>... clsArr) {
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls : clsArr) {
                log("Found: " + cls.getName());
            }
        }
    }

    /* access modifiers changed from: protected */
    public j getSdk() {
        return this.mSdk;
    }

    /* access modifiers changed from: protected */
    public AppLovinSdk getWrappingSdk() {
        return this.mWrappingSdk;
    }

    /* access modifiers changed from: protected */
    public void log(String str) {
        Log.i(this.mTag, str);
    }

    /* access modifiers changed from: protected */
    public void log(String str, Throwable th) {
        Log.i(this.mTag, str, th);
    }
}
