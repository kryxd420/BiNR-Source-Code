package com.applovin.sdk;

import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.e.n;
import java.util.HashMap;
import java.util.Map;

public class AppLovinSdkSettings {
    private boolean a;
    private boolean b;
    private long c;
    private String d;
    private String e;
    private boolean f;
    private final Map<String, Object> localSettings;

    public AppLovinSdkSettings() {
        this((Context) null);
    }

    public AppLovinSdkSettings(Context context) {
        this.localSettings = new HashMap();
        this.b = n.c(context);
        this.a = n.b(context);
        this.c = -1;
        this.d = AppLovinAdSize.INTERSTITIAL.getLabel() + "," + AppLovinAdSize.BANNER.getLabel() + "," + AppLovinAdSize.MREC.getLabel();
        this.e = AppLovinAdType.INCENTIVIZED.getLabel() + "," + AppLovinAdType.REGULAR.getLabel() + "," + AppLovinAdType.NATIVE.getLabel();
    }

    @Deprecated
    public String getAutoPreloadSizes() {
        return this.d;
    }

    @Deprecated
    public String getAutoPreloadTypes() {
        return this.e;
    }

    @Deprecated
    public long getBannerAdRefreshSeconds() {
        return this.c;
    }

    public boolean isMuted() {
        return this.f;
    }

    public boolean isTestAdsEnabled() {
        return this.a;
    }

    public boolean isVerboseLoggingEnabled() {
        return this.b;
    }

    @Deprecated
    public void setAutoPreloadSizes(String str) {
        this.d = str;
    }

    @Deprecated
    public void setAutoPreloadTypes(String str) {
        this.e = str;
    }

    @Deprecated
    public void setBannerAdRefreshSeconds(long j) {
        this.c = j;
    }

    public void setMuted(boolean z) {
        this.f = z;
    }

    public void setTestAdsEnabled(boolean z) {
        this.a = z;
    }

    public void setVerboseLogging(boolean z) {
        if (n.a()) {
            Log.e("AppLovinSdkSettings", "Ignoring setting of verbose logging - it is configured from Android manifest already or AppLovinSdkSettings was initialized without a context.");
        } else {
            this.b = z;
        }
    }

    public String toString() {
        return "AppLovinSdkSettings{isTestAdsEnabled=" + this.a + ", isVerboseLoggingEnabled=" + this.b + ", muted=" + this.f + '}';
    }
}
