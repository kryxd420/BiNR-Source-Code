package com.moat.analytics.mobile.vng;

import android.graphics.Rect;
import android.view.View;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.moat.analytics.mobile.vng.NativeDisplayTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class t extends b implements NativeDisplayTracker {
    private final Map<String, String> f;
    private final Set<NativeDisplayTracker.MoatUserInteractionType> g = new HashSet();

    t(View view, Map<String, String> map) {
        super(view, true, false);
        p.a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.f = map;
        g gVar = ((k) k.getInstance()).d;
        if (gVar != null) {
            super.a(gVar.b);
            super.a(gVar.a);
        }
        g();
        p.a("[SUCCESS] ", a() + " created for " + e() + ", with adIds:" + map.toString());
    }

    private static String a(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            String str = "moatClientLevel" + i;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            String str2 = "moatClientSlicer" + i2;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
        }
        for (String next : map.keySet()) {
            if (!linkedHashMap.containsKey(next)) {
                linkedHashMap.put(next, map.get(next));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    private void g() {
        if (this.a != null) {
            this.a.a(h());
        }
    }

    private String h() {
        try {
            String a = a(this.f);
            p.a(3, "NativeDisplayTracker", (Object) this, "Parsed ad ids = " + a);
            return "{\"adIds\":" + a + ", \"adKey\":\"" + this.b + "\", \"adSize\":" + i() + "}";
        } catch (Exception e) {
            m.a(e);
            return "";
        }
    }

    private String i() {
        try {
            Rect a = z.a(super.d());
            int width = a.width();
            int height = a.height();
            HashMap hashMap = new HashMap();
            hashMap.put(AvidJSONUtil.KEY_WIDTH, Integer.toString(width));
            hashMap.put(AvidJSONUtil.KEY_HEIGHT, Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            m.a(e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            p.a(3, "NativeDisplayTracker", (Object) this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.g.contains(moatUserInteractionType)) {
                this.g.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.b);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.a != null) {
                    this.a.b(jSONObject.toString());
                }
            }
        } catch (JSONException e) {
            e = e;
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a(e);
        } catch (Exception e2) {
            e = e2;
            m.a(e);
        }
    }
}
