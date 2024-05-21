package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.a;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class u extends a {
    private final AppLovinNativeAdLoadListener a;
    private final JSONObject c;

    u(JSONObject jSONObject, j jVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskRenderNativeAd", jVar);
        this.a = appLovinNativeAdLoadListener;
        this.c = jSONObject;
    }

    private String a(String str, Map<String, String> map, String str2) {
        String str3 = map.get(str);
        if (str3 != null) {
            return str3.replace("{CLCODE}", str2);
        }
        return null;
    }

    private String a(Map<String, String> map, String str, String str2) {
        String str3 = map.get(TapjoyConstants.TJC_CLICK_URL);
        if (str2 == null) {
            str2 = "";
        }
        return str3.replace("{CLCODE}", str).replace("{EVENT_ID}", str2);
    }

    private void a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray("native_ads");
        JSONObject optJSONObject = jSONObject2.optJSONObject("native_settings");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            c("No ads were returned from the server");
            this.a.onNativeAdsFailedToLoad(204);
            return;
        }
        List b = g.b(optJSONArray);
        ArrayList arrayList = new ArrayList(b.size());
        Map a2 = optJSONObject != null ? g.a(optJSONObject) : new HashMap(0);
        Iterator it = b.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            String str = (String) map.get("clcode");
            String a3 = g.a(jSONObject2, "zone_id", (String) null, this.b);
            String str2 = (String) map.get("event_id");
            d b2 = d.b(a3, this.b);
            String a4 = a("simp_url", (Map<String, String>) a2, str);
            String a5 = a((Map<String, String>) a2, str, str2);
            List<a> a6 = n.a("simp_urls", optJSONObject, str, a4, this.b);
            String str3 = g.a(optJSONObject, "should_post_click_url", (Boolean) true, this.b).booleanValue() ? a5 : null;
            List<a> list = a6;
            Iterator it2 = it;
            String str4 = a5;
            JSONObject jSONObject3 = optJSONObject;
            String str5 = a4;
            List<a> a7 = n.a("click_tracking_urls", optJSONObject, str, str2, str3, this.b);
            if (list.size() == 0) {
                throw new IllegalArgumentException("No impression URL available");
            } else if (a7.size() != 0) {
                String str6 = (String) map.get("resource_cache_prefix");
                NativeAdImpl a8 = new NativeAdImpl.a().a(b2).e(a3).f((String) map.get(TJAdUnitConstants.String.TITLE)).g((String) map.get("description")).h((String) map.get("caption")).q((String) map.get("cta")).a((String) map.get("icon_url")).b((String) map.get("image_url")).d((String) map.get(TapjoyConstants.TJC_VIDEO_URL)).c((String) map.get("star_rating_url")).i((String) map.get("icon_url")).j((String) map.get("image_url")).k((String) map.get(TapjoyConstants.TJC_VIDEO_URL)).a(Float.parseFloat((String) map.get("star_rating"))).p(str).l(str4).m(str5).n(a("video_start_url", (Map<String, String>) a2, str)).o(a("video_end_url", (Map<String, String>) a2, str)).a(list).b(a7).a(Long.parseLong((String) map.get("ad_id"))).c(str6 != null ? com.applovin.impl.sdk.e.d.a(str6) : this.b.b((b) b.bB)).a(this.b).a();
                arrayList.add(a8);
                a("Prepared native ad: " + a8.getAdId());
                it = it2;
                optJSONObject = jSONObject3;
                jSONObject2 = jSONObject;
            } else {
                throw new IllegalArgumentException("No click tracking URL available");
            }
        }
        if (this.a != null) {
            this.a.onNativeAdsLoaded(arrayList);
        }
    }

    public i a() {
        return i.u;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        try {
            if (this.a != null) {
                this.a.onNativeAdsFailedToLoad(i);
            }
        } catch (Exception e) {
            a("Unable to notify listener about failure.", e);
        }
    }

    public void run() {
        try {
            if (this.c != null) {
                if (this.c.length() != 0) {
                    a(this.c);
                    return;
                }
            }
            a((int) AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
        } catch (Exception e) {
            a("Unable to render native ad.", e);
            a((int) AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            this.b.F().a(a());
        }
    }
}
