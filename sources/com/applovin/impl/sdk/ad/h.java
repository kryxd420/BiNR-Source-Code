package com.applovin.impl.sdk.ad;

import android.util.Log;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import org.json.JSONObject;

public final class h extends AppLovinAdBase {
    private AppLovinAd a;
    private final d b;

    public h(d dVar, j jVar) {
        super(new JSONObject(), new JSONObject(), b.UNKNOWN, jVar);
        this.b = dVar;
    }

    private AppLovinAd c() {
        return (AppLovinAd) this.sdk.M().c(this.b);
    }

    private String d() {
        d adZone = getAdZone();
        if (adZone == null || adZone.l()) {
            return null;
        }
        return adZone.a();
    }

    public AppLovinAd a() {
        return this.a;
    }

    public void a(AppLovinAd appLovinAd) {
        this.a = appLovinAd;
    }

    public AppLovinAd b() {
        return this.a != null ? this.a : c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAd b2 = b();
        return b2 != null ? b2.equals(obj) : super.equals(obj);
    }

    public long getAdIdNumber() {
        try {
            AppLovinAd b2 = b();
            if (b2 != null) {
                return b2.getAdIdNumber();
            }
            return 0;
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad id number", th);
            return 0;
        }
    }

    public d getAdZone() {
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) b();
        return appLovinAdBase != null ? appLovinAdBase.getAdZone() : this.b;
    }

    public AppLovinAdSize getSize() {
        AppLovinAdSize appLovinAdSize = AppLovinAdSize.INTERSTITIAL;
        try {
            return getAdZone().b();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad size", th);
            return appLovinAdSize;
        }
    }

    public b getSource() {
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) b();
        return appLovinAdBase != null ? appLovinAdBase.getSource() : b.UNKNOWN;
    }

    public AppLovinAdType getType() {
        AppLovinAdType appLovinAdType = AppLovinAdType.REGULAR;
        try {
            return getAdZone().c();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad type", th);
            return appLovinAdType;
        }
    }

    public String getZoneId() {
        try {
            if (!this.b.l()) {
                return this.b.a();
            }
            return null;
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return zone id", th);
            return null;
        }
    }

    public int hashCode() {
        AppLovinAd b2 = b();
        return b2 != null ? b2.hashCode() : super.hashCode();
    }

    public boolean isVideoAd() {
        try {
            AppLovinAd b2 = b();
            if (b2 != null) {
                return b2.isVideoAd();
            }
            return false;
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return whether ad is video ad", th);
            return false;
        }
    }

    public String toString() {
        return "AppLovinAd{ #" + getAdIdNumber() + ", adType=" + getType() + ", adSize=" + getSize() + ", zoneId='" + d() + '\'' + '}';
    }
}
