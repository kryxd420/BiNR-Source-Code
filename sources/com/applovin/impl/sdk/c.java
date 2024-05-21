package com.applovin.impl.sdk;

import android.os.Handler;
import android.os.Looper;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.lang.ref.WeakReference;

public class c {
    private static final Handler a = new Handler(Looper.getMainLooper());
    private WeakReference<AppLovinAdDisplayListener> b = new WeakReference<>((Object) null);
    private WeakReference<AppLovinAdClickListener> c = new WeakReference<>((Object) null);
    private WeakReference<AppLovinAdRewardListener> d = new WeakReference<>((Object) null);

    public void a(AppLovinAdClickListener appLovinAdClickListener) {
        this.c = new WeakReference<>(appLovinAdClickListener);
    }

    public void a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.b = new WeakReference<>(appLovinAdDisplayListener);
    }
}
