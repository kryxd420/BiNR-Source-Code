package com.applovin.impl.sdk.ad;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

public final class i implements AppLovinAd {
    public String a() {
        return "<html><head></head><body></body></html>";
    }

    public long getAdIdNumber() {
        return 0;
    }

    public String getAdValue(String str) {
        return null;
    }

    public AppLovinAdSize getSize() {
        return AppLovinAdSize.BANNER;
    }

    public AppLovinAdType getType() {
        return AppLovinAdType.REGULAR;
    }

    public String getZoneId() {
        return null;
    }

    public boolean isVideoAd() {
        return false;
    }
}
