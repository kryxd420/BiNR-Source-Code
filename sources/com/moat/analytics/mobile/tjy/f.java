package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.moat.analytics.mobile.tjy.base.exception.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

abstract class f {
    protected static final MoatAdEventType[] b = {MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    protected boolean a;
    protected final Map c = new HashMap();
    protected final Handler d = new Handler();
    protected Map e;
    protected WeakReference f;
    protected WeakReference g;
    protected final a h;
    protected final ap i;
    private boolean j;
    private WeakReference k;
    /* access modifiers changed from: private */
    public ad l;

    public f(String str, a aVar, ap apVar) {
        this.i = apVar;
        this.h = aVar;
        a("Initializing.");
        this.l = new ad(str, apVar, aVar);
        this.k = new WeakReference(aVar.c());
        this.j = false;
        this.a = false;
    }

    private int a(AudioManager audioManager) {
        return audioManager.getStreamVolume(3);
    }

    private MoatAdEvent a(Map map) {
        return new MoatAdEvent(MoatAdEventType.fromString((String) map.get("type")), map.containsKey("playHead") ? (Integer) map.get("playHead") : MoatAdEvent.TIME_UNAVAILABLE, map.containsKey(MoatAdEvent.EVENT_AD_VOLUME) ? (Double) map.get(MoatAdEvent.EVENT_AD_VOLUME) : MoatAdEvent.VOLUME_UNAVAILABLE);
    }

    private void b(MoatAdEvent moatAdEvent) {
        JSONObject a2 = a(moatAdEvent);
        a(String.format("Received event: %s", new Object[]{a2.toString()}));
        this.l.a(a2);
        MoatAdEventType moatAdEventType = moatAdEvent.eventType;
        if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
            this.c.put(moatAdEventType, 1);
            c();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Map a();

    /* access modifiers changed from: protected */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.adVolume.doubleValue())) {
            try {
                moatAdEvent.adVolume = Double.valueOf(d());
            } catch (Exception unused) {
                moatAdEvent.adVolume = Double.valueOf(1.0d);
            }
        }
        return new JSONObject(moatAdEvent.toMap());
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        if (this.i.b() || this.a) {
            Log.d("MoatVideoTracker", str);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(Integer num, Integer num2) {
        int intValue = num2.intValue() - num.intValue();
        double intValue2 = (double) num2.intValue();
        Double.isNaN(intValue2);
        return ((double) intValue) <= Math.min(750.0d, intValue2 * 0.05d);
    }

    public boolean a(Map map, Object obj, View view) {
        boolean z;
        String str;
        boolean z2 = false;
        if (map == null) {
            try {
                a("trackVideoAd received null adIds object. Not tracking.");
                z = false;
            } catch (Exception e2) {
                a.a(e2);
            }
        } else {
            z = true;
        }
        if (view == null) {
            a("trackVideoAd received null video view instance");
        }
        if (obj == null) {
            a("trackVideoAd received null ad instance. Not tracking.");
            z = false;
        }
        if (z) {
            Object[] objArr = new Object[3];
            objArr[0] = new JSONObject(map).toString();
            objArr[1] = obj.toString();
            if (view != null) {
                str = view.getClass().getSimpleName() + "@" + view.hashCode();
            } else {
                str = "null";
            }
            objArr[2] = str;
            a(String.format("trackVideoAd tracking ids: %s | ad: %s | view: %s", objArr));
            this.e = map;
            this.f = new WeakReference(obj);
            this.g = new WeakReference(view);
            b();
        }
        z2 = z;
        StringBuilder sb = new StringBuilder("Attempt to start tracking ad was ");
        sb.append(z2 ? "" : "un");
        sb.append("successful.");
        a(sb.toString());
        return z2;
    }

    /* access modifiers changed from: protected */
    public void b() {
        Map a2 = a();
        Integer num = (Integer) a2.get(AvidJSONUtil.KEY_WIDTH);
        Integer num2 = (Integer) a2.get(AvidJSONUtil.KEY_HEIGHT);
        Integer num3 = (Integer) a2.get("duration");
        a(String.format("Player metadata: height = %d, width = %d, duration = %d", new Object[]{num2, num, num3}));
        this.l.a((View) this.g.get(), this.e, num, num2, num3);
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (!this.j) {
            this.d.postDelayed(new g(this), 500);
            this.j = true;
        }
    }

    public void changeTargetView(View view) {
        String str;
        if (this.i.b()) {
            StringBuilder sb = new StringBuilder("changing view to ");
            if (view != null) {
                str = view.getClass().getSimpleName() + "@" + view.hashCode();
            } else {
                str = "null";
            }
            sb.append(str);
            Log.d("MoatVideoTracker", sb.toString());
        }
        this.g = new WeakReference(view);
        this.l.a(view);
    }

    /* access modifiers changed from: protected */
    public double d() {
        AudioManager audioManager = (AudioManager) ((Context) this.k.get()).getSystemService("audio");
        double a2 = (double) a(audioManager);
        double streamMaxVolume = (double) audioManager.getStreamMaxVolume(3);
        Double.isNaN(a2);
        Double.isNaN(streamMaxVolume);
        return a2 / streamMaxVolume;
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            b(moatAdEvent);
        } catch (Exception e2) {
            a.a(e2);
        }
    }

    public void dispatchEvent(Map map) {
        try {
            b(a(map));
        } catch (Exception e2) {
            a.a(e2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return this.c.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.c.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.c.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void setDebug(boolean z) {
        this.a = z;
    }
}
