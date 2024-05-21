package com.moat.analytics.mobile.tjy;

import android.view.View;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class bd extends f implements ReactiveVideoTracker {
    private Integer j;

    public bd(String str, a aVar, ap apVar) {
        super(str, aVar, apVar);
    }

    /* access modifiers changed from: protected */
    public Map a() {
        HashMap hashMap = new HashMap();
        View view = (View) this.g.get();
        int i = 0;
        int i2 = 0;
        if (view != null) {
            i = Integer.valueOf(view.getWidth());
            i2 = Integer.valueOf(view.getHeight());
        }
        hashMap.put("duration", this.j);
        hashMap.put(AvidJSONUtil.KEY_WIDTH, i);
        hashMap.put(AvidJSONUtil.KEY_HEIGHT, i2);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE && !a(moatAdEvent.adPlayhead, this.j)) {
            moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    public /* bridge */ /* synthetic */ boolean a(Map map, Object obj, View view) {
        return super.a(map, obj, view);
    }

    public /* bridge */ /* synthetic */ void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    public /* bridge */ /* synthetic */ void dispatchEvent(MoatAdEvent moatAdEvent) {
        super.dispatchEvent(moatAdEvent);
    }

    public /* bridge */ /* synthetic */ void dispatchEvent(Map map) {
        super.dispatchEvent(map);
    }

    public /* bridge */ /* synthetic */ void setDebug(boolean z) {
        super.setDebug(z);
    }

    public boolean trackVideoAd(Map map, Integer num, View view) {
        if (num.intValue() < 1000) {
            a(String.format("Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        }
        this.j = num;
        return super.a(map, new Object(), view);
    }
}
