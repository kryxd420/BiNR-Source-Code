package com.applovin.impl.sdk;

import android.content.Intent;
import android.text.TextUtils;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.d;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.k;
import com.applovin.impl.sdk.network.e;
import com.applovin.impl.sdk.network.f;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import com.tapjoy.TapjoyAuctionFlags;
import com.tapjoy.TapjoyConstants;
import com.unity.purchasing.googleplay.IabHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class EventServiceImpl implements AppLovinEventService {
    /* access modifiers changed from: private */
    public final j a;
    private final List<String> b;

    public EventServiceImpl(j jVar) {
        this.a = jVar;
        this.b = d.a((String) jVar.a(b.aM));
    }

    /* access modifiers changed from: private */
    public String a() {
        return ((String) this.a.a(b.aH)) + "4.0/pix";
    }

    /* access modifiers changed from: private */
    public HashMap<String, String> a(l lVar, k.a aVar) {
        k H = this.a.H();
        k.d a2 = H.a();
        k.b c = H.c();
        boolean contains = this.b.contains(lVar.a());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("event", contains ? com.applovin.impl.sdk.e.k.e(lVar.a()) : "postinstall");
        hashMap.put("ts", Long.toString(lVar.c()));
        hashMap.put(TapjoyConstants.TJC_PLATFORM, com.applovin.impl.sdk.e.k.e(a2.c));
        hashMap.put("model", com.applovin.impl.sdk.e.k.e(a2.a));
        hashMap.put("package_name", com.applovin.impl.sdk.e.k.e(c.c));
        hashMap.put("installer_name", com.applovin.impl.sdk.e.k.e(c.d));
        hashMap.put("ia", Long.toString(c.f));
        hashMap.put("api_did", this.a.a(b.T));
        hashMap.put("brand", com.applovin.impl.sdk.e.k.e(a2.d));
        hashMap.put("brand_name", com.applovin.impl.sdk.e.k.e(a2.e));
        hashMap.put("hardware", com.applovin.impl.sdk.e.k.e(a2.f));
        hashMap.put("revision", com.applovin.impl.sdk.e.k.e(a2.g));
        hashMap.put("sdk_version", AppLovinSdk.VERSION);
        hashMap.put("os", com.applovin.impl.sdk.e.k.e(a2.b));
        hashMap.put("orientation_lock", a2.l);
        hashMap.put(TapjoyConstants.TJC_APP_VERSION_NAME, com.applovin.impl.sdk.e.k.e(c.b));
        hashMap.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, com.applovin.impl.sdk.e.k.e(a2.i));
        hashMap.put("carrier", com.applovin.impl.sdk.e.k.e(a2.j));
        hashMap.put("tz_offset", String.valueOf(a2.o));
        hashMap.put("adr", a2.q ? TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE : "0");
        hashMap.put(AvidVideoPlaybackListenerImpl.VOLUME, String.valueOf(a2.s));
        hashMap.put("sim", a2.u ? TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE : "0");
        hashMap.put("gy", String.valueOf(a2.v));
        hashMap.put("tv", String.valueOf(a2.w));
        hashMap.put("tg", c.e);
        hashMap.put("fs", String.valueOf(a2.y));
        if (!((Boolean) this.a.a(b.eV)).booleanValue()) {
            hashMap.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.a.t());
        }
        a(aVar, (Map<String, String>) hashMap);
        if (((Boolean) this.a.a(b.ef)).booleanValue()) {
            n.a("cuid", this.a.k(), (Map<String, String>) hashMap);
        }
        Boolean bool = a2.z;
        if (bool != null) {
            hashMap.put("huc", bool.toString());
        }
        Boolean bool2 = a2.A;
        if (bool2 != null) {
            hashMap.put("aru", bool2.toString());
        }
        k.c cVar = a2.r;
        if (cVar != null) {
            hashMap.put("act", String.valueOf(cVar.a));
            hashMap.put("acm", String.valueOf(cVar.b));
        }
        String str = a2.t;
        if (com.applovin.impl.sdk.e.k.b(str)) {
            hashMap.put("ua", com.applovin.impl.sdk.e.k.e(str));
        }
        String str2 = a2.x;
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("so", com.applovin.impl.sdk.e.k.e(str2));
        }
        if (!contains) {
            hashMap.put("sub_event", com.applovin.impl.sdk.e.k.e(lVar.a()));
        }
        hashMap.put("sc", com.applovin.impl.sdk.e.k.e((String) this.a.a(b.X)));
        hashMap.put("sc2", com.applovin.impl.sdk.e.k.e((String) this.a.a(b.Y)));
        hashMap.put("server_installed_at", com.applovin.impl.sdk.e.k.e((String) this.a.a(b.Z)));
        n.a("persisted_data", com.applovin.impl.sdk.e.k.e((String) this.a.a(com.applovin.impl.sdk.b.d.s)), (Map<String, String>) hashMap);
        return hashMap;
    }

    private Map<String, String> a(Map<String, String> map) {
        String str;
        String str2;
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                Object key = next.getKey();
                Object value = next.getValue();
                if (!(key instanceof String) || !(value instanceof String)) {
                    p v = this.a.v();
                    v.c("EventServiceImpl", "Unexpected class type in trackEvent(); all keys and values passed as parameters must be String. Encountered " + key.getClass().getCanonicalName() + "/" + value.getClass().getCanonicalName() + "; will use toString() value instead, which may be unexpected...");
                    str = key.toString();
                    str2 = value.toString();
                } else {
                    str = (String) key;
                    str2 = (String) value;
                }
                hashMap.put(str, str2);
            }
        }
        return hashMap;
    }

    private void a(i.a aVar) {
        this.a.D().a((a) new i(this.a, aVar), q.a.BACKGROUND);
    }

    private void a(k.a aVar, Map<String, String> map) {
        String str = aVar.b;
        if (com.applovin.impl.sdk.e.k.b(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(aVar.a));
    }

    private void a(final l lVar, final boolean z) {
        if (((Boolean) this.a.a(b.aN)).booleanValue()) {
            p v = this.a.v();
            v.a("EventServiceImpl", "Tracking event: " + lVar);
            a((i.a) new i.a() {
                public void a(k.a aVar) {
                    try {
                        HashMap a2 = EventServiceImpl.this.a(lVar, aVar);
                        Map<String, String> b2 = lVar.b();
                        if (z) {
                            EventServiceImpl.this.a.G().a(e.j().a(EventServiceImpl.this.a()).b(EventServiceImpl.this.b()).a((Map<String, String>) a2).b(b2).a(((Boolean) EventServiceImpl.this.a.a(b.eV)).booleanValue()).a());
                        } else {
                            EventServiceImpl.this.a.K().dispatchPostbackRequest(f.b(EventServiceImpl.this.a).a(EventServiceImpl.this.a()).c(EventServiceImpl.this.b()).a((Map<String, String>) a2).a(b2 != null ? new JSONObject(b2) : null).a(((Boolean) EventServiceImpl.this.a.a(b.eV)).booleanValue()).a(), (AppLovinPostbackListener) null);
                        }
                    } catch (Throwable th) {
                        p v = EventServiceImpl.this.a.v();
                        v.b("EventServiceImpl", "Unable to track event due to failure to convert event parameters into JSONObject for event: " + lVar, th);
                    }
                }
            });
        }
    }

    private void a(String str, Map<String, String> map, boolean z) {
        a(new l(str, a(map), System.currentTimeMillis(), com.applovin.impl.sdk.e.k.f(UUID.randomUUID().toString())), z);
    }

    /* access modifiers changed from: private */
    public String b() {
        return ((String) this.a.a(b.aI)) + "4.0/pix";
    }

    /* access modifiers changed from: package-private */
    public void a(String str, boolean z) {
        a(str, (Map<String, String>) new HashMap(), z);
    }

    public String toString() {
        return "EventService{}";
    }

    public void trackCheckout(String str, Map<String, String> map) {
        HashMap hashMap = map != null ? new HashMap(map) : new HashMap(1);
        hashMap.put(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, str);
        trackEvent(AppLovinEventTypes.USER_COMPLETED_CHECKOUT, hashMap);
    }

    public void trackEvent(String str) {
        trackEvent(str, new HashMap());
    }

    public void trackEvent(String str, Map<String, String> map) {
        a(str, map, true);
    }

    public void trackInAppPurchase(Intent intent, Map<String, String> map) {
        HashMap hashMap = map != null ? new HashMap(map) : new HashMap();
        try {
            hashMap.put(AppLovinEventParameters.IN_APP_PURCHASE_DATA, intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA));
            hashMap.put(AppLovinEventParameters.IN_APP_DATA_SIGNATURE, intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE));
        } catch (Exception e) {
            this.a.v().c("EventServiceImpl", "Unable to track in app purchase; invalid purchanse intent", e);
        }
        trackEvent("iap", hashMap);
    }
}
