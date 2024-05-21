package com.moat.analytics.mobile.vng;

import android.view.View;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class y extends c<Object> implements ReactiveVideoTracker {
    private Integer l;

    public y(String str) {
        super(str);
        p.a("[SUCCESS] ", a() + " created");
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "ReactiveVideoTracker";
    }

    /* access modifiers changed from: protected */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.b.equals(MoatAdEvent.a) && !a(moatAdEvent.b, this.l)) {
            moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> g() {
        HashMap hashMap = new HashMap();
        View view = (View) this.k.get();
        int i = 0;
        int i2 = 0;
        if (view != null) {
            i = Integer.valueOf(view.getWidth());
            i2 = Integer.valueOf(view.getHeight());
        }
        hashMap.put("duration", this.l);
        hashMap.put(AvidJSONUtil.KEY_WIDTH, i);
        hashMap.put(AvidJSONUtil.KEY_HEIGHT, i2);
        return hashMap;
    }

    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        if (this.e) {
            p.a(3, "ReactiveVideoTracker", (Object) this, "trackVideoAd already called");
            p.a("[ERROR] ", a() + " trackVideoAd can't be called twice");
            return false;
        } else if (num.intValue() < 1000) {
            p.a(3, "ReactiveVideoTracker", (Object) this, String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        } else {
            this.l = num;
            return super.a(map, new Object(), view);
        }
    }
}
