package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.impl.sdk.ad.b;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.ad.h;
import com.applovin.impl.sdk.ad.j;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Arrays;
import org.json.JSONObject;

public abstract class AppLovinAdBase implements j, AppLovinAd {
    private final int a;
    protected final JSONObject adObject;
    protected final Object adObjectLock;
    private d b;
    private final long c;
    private h d;
    protected final JSONObject fullResponse;
    protected final Object fullResponseLock;
    protected final j sdk;
    protected final b source;

    protected AppLovinAdBase(JSONObject jSONObject, JSONObject jSONObject2, b bVar, j jVar) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (jVar != null) {
            this.adObject = jSONObject;
            this.fullResponse = jSONObject2;
            this.source = bVar;
            this.sdk = jVar;
            this.adObjectLock = new Object();
            this.fullResponseLock = new Object();
            this.c = System.currentTimeMillis();
            char[] charArray = jSONObject.toString().toCharArray();
            Arrays.sort(charArray);
            this.a = new String(charArray).hashCode();
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    public boolean equals(Object obj) {
        AppLovinAd b2;
        if ((obj instanceof h) && (b2 = ((h) obj).b()) != null) {
            obj = b2;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) obj;
        if (this.b == null ? appLovinAdBase.b == null : this.b.equals(appLovinAdBase.b)) {
            return this.source == appLovinAdBase.source && this.a == appLovinAdBase.a;
        }
        return false;
    }

    public long getAdIdNumber() {
        return getLongFromAdObject("ad_id", -1);
    }

    public String getAdValue(String str) {
        JSONObject jsonObjectFromAdObject;
        if (!TextUtils.isEmpty(str) && (jsonObjectFromAdObject = getJsonObjectFromAdObject("ad_values", (JSONObject) null)) != null && jsonObjectFromAdObject.length() > 0) {
            return g.a(jsonObjectFromAdObject, str, (String) null, this.sdk);
        }
        return null;
    }

    public d getAdZone() {
        if (this.b != null) {
            return this.b;
        }
        this.b = d.a(getSize(), getType(), getStringFromFullResponse("zone_id", (String) null), this.sdk);
        return this.b;
    }

    /* access modifiers changed from: protected */
    public boolean getBooleanFromAdObject(String str, Boolean bool) {
        boolean booleanValue;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return g.a(this.adObject, str, bool, this.sdk).booleanValue();
        }
        synchronized (this.adObjectLock) {
            booleanValue = g.a(this.adObject, str, bool, this.sdk).booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public boolean getBooleanFromFullResponse(String str, boolean z) {
        boolean booleanValue;
        synchronized (this.fullResponseLock) {
            booleanValue = g.a(this.fullResponse, str, Boolean.valueOf(z), this.sdk).booleanValue();
        }
        return booleanValue;
    }

    public String getClCode() {
        String stringFromAdObject = getStringFromAdObject("clcode", "");
        return k.b(stringFromAdObject) ? stringFromAdObject : getStringFromFullResponse("clcode", "");
    }

    public long getCreatedAtMillis() {
        return this.c;
    }

    public h getDummyAd() {
        return this.d;
    }

    public long getFetchLatencyMillis() {
        return getLongFromFullResponse("ad_fetch_latency_millis", -1);
    }

    public long getFetchResponseSize() {
        return getLongFromFullResponse("ad_fetch_response_size", -1);
    }

    /* access modifiers changed from: protected */
    public float getFloatFromAdObject(String str, float f) {
        float a2;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return g.a(this.adObject, str, f, this.sdk);
        }
        synchronized (this.adObjectLock) {
            a2 = g.a(this.adObject, str, f, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public float getFloatFromFullResponse(String str, float f) {
        float a2;
        synchronized (this.fullResponseLock) {
            a2 = g.a(this.fullResponse, str, f, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public int getIntFromAdObject(String str, int i) {
        int a2;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return g.a(this.adObject, str, i, this.sdk);
        }
        synchronized (this.adObjectLock) {
            a2 = g.a(this.adObject, str, i, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public int getIntFromFullResponse(String str, int i) {
        int a2;
        synchronized (this.fullResponseLock) {
            a2 = g.a(this.fullResponse, str, i, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public JSONObject getJsonObjectFromAdObject(String str, JSONObject jSONObject) {
        JSONObject a2;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return g.a(this.adObject, str, jSONObject, this.sdk);
        }
        synchronized (this.adObjectLock) {
            a2 = g.a(this.adObject, str, jSONObject, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public JSONObject getJsonObjectFromFullResponse(String str, JSONObject jSONObject) {
        JSONObject a2;
        synchronized (this.fullResponseLock) {
            a2 = g.a(this.fullResponse, str, jSONObject, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public long getLongFromAdObject(String str, long j) {
        long a2;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return g.a(this.adObject, str, j, this.sdk);
        }
        synchronized (this.adObjectLock) {
            a2 = g.a(this.adObject, str, j, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public long getLongFromFullResponse(String str, long j) {
        long a2;
        synchronized (this.fullResponseLock) {
            a2 = g.a(this.fullResponse, str, j, this.sdk);
        }
        return a2;
    }

    public String getPrimaryKey() {
        return getStringFromAdObject("pk", "NA");
    }

    public j getSdk() {
        return this.sdk;
    }

    public String getSecondaryKey1() {
        return getStringFromAdObject("sk1", (String) null);
    }

    public String getSecondaryKey2() {
        return getStringFromAdObject("sk2", (String) null);
    }

    public AppLovinAdSize getSize() {
        return AppLovinAdSize.fromString(getStringFromFullResponse("ad_size", (String) null));
    }

    public b getSource() {
        return this.source;
    }

    /* access modifiers changed from: protected */
    public String getStringFromAdObject(String str, String str2) {
        String a2;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return g.a(this.adObject, str, str2, this.sdk);
        }
        synchronized (this.adObjectLock) {
            a2 = g.a(this.adObject, str, str2, this.sdk);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public String getStringFromFullResponse(String str, String str2) {
        String a2;
        synchronized (this.fullResponseLock) {
            a2 = g.a(this.fullResponse, str, str2, this.sdk);
        }
        return a2;
    }

    public AppLovinAdType getType() {
        return AppLovinAdType.fromString(getStringFromFullResponse("ad_type", (String) null));
    }

    public String getZoneId() {
        if (getAdZone().l()) {
            return null;
        }
        return getStringFromFullResponse("zone_id", (String) null);
    }

    public boolean hasShown() {
        return getBooleanFromAdObject("shown", false);
    }

    public boolean hasVideoUrl() {
        this.sdk.v().d("AppLovinAdBase", "Attempting to invoke hasVideoUrl() from base ad class");
        return false;
    }

    public int hashCode() {
        return this.a;
    }

    public boolean isVideoAd() {
        return this.adObject.has("is_video_ad") ? getBooleanFromAdObject("is_video_ad", false) : hasVideoUrl();
    }

    public void setDummyAd(h hVar) {
        this.d = hVar;
    }

    public void setHasShown(boolean z) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("shown", z);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean shouldCancelHtmlCachingIfShown() {
        return getBooleanFromAdObject("chcis", false);
    }

    public String toString() {
        return "AppLovinAd{adIdNumber" + getAdIdNumber() + ", source=" + getSource() + ", zoneId='" + getZoneId() + "'" + '}';
    }
}
