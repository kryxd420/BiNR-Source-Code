package com.adcolony.sdk;

import android.app.Activity;
import android.webkit.WebView;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.session.AvidDisplayAdSession;
import com.integralads.avid.library.adcolony.session.AvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.session.AvidVideoAdSession;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.model.Advertisement;
import org.json.JSONArray;
import org.json.JSONObject;

class g {
    /* access modifiers changed from: private */
    public AvidDisplayAdSession a;
    /* access modifiers changed from: private */
    public AvidVideoAdSession b;
    /* access modifiers changed from: private */
    public AvidManagedVideoAdSession c;
    private AdColonyCustomMessageListener d;
    /* access modifiers changed from: private */
    public JSONArray e;
    private int f = -1;
    /* access modifiers changed from: private */
    public String g = "";
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;

    g(JSONObject jSONObject) {
        this.f = a(jSONObject);
        this.e = y.g(jSONObject, "js_resources");
    }

    /* access modifiers changed from: package-private */
    public int a(JSONObject jSONObject) {
        if (this.f != -1) {
            return this.f;
        }
        this.h = y.c(jSONObject, "ad_unit_type");
        String b2 = y.b(jSONObject, "ad_type");
        if (this.h == 0) {
            return 2;
        }
        if (this.h == 1) {
            if (b2.equals(Advertisement.KEY_VIDEO)) {
                return 2;
            }
            return 1;
        } else if (b2.equals(Advertisement.KEY_VIDEO)) {
            return 0;
        } else {
            return 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(c cVar) {
        if (!this.k && this.f >= 0) {
            if (this.f == 2) {
                c();
            }
            b(cVar);
            switch (this.f) {
                case 0:
                    this.b.getAvidDeferredAdSessionListener().recordReadyEvent();
                    break;
                case 1:
                    this.a.getAvidDeferredAdSessionListener().recordReadyEvent();
                    break;
                case 2:
                    this.c.getAvidDeferredAdSessionListener().recordReadyEvent();
                    break;
            }
            this.k = true;
            a("record_ready");
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        switch (d()) {
            case 0:
                this.b.endSession();
                this.b = null;
                a("end_session");
                return;
            case 1:
                this.a.endSession();
                this.a = null;
                a("end_session");
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = com.adcolony.sdk.a.c();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r2 = this;
            int r0 = r2.f
            if (r0 < 0) goto L_0x0012
            android.app.Activity r0 = com.adcolony.sdk.a.c()
            if (r0 == 0) goto L_0x0012
            com.adcolony.sdk.g$1 r1 = new com.adcolony.sdk.g$1
            r1.<init>(r0)
            com.adcolony.sdk.aw.a((java.lang.Runnable) r1)
        L_0x0012:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.g.b():void");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        JSONObject a2 = y.a();
        JSONObject a3 = y.a();
        y.b(a3, "session_type", this.f);
        y.a(a3, TapjoyConstants.TJC_SESSION_ID, this.g);
        y.a(a3, "event", str);
        y.a(a2, "type", "ias_hook");
        y.a(a2, "message", a3.toString());
        new af("CustomMessage.controller_send", 0, a2).b();
    }

    /* access modifiers changed from: package-private */
    public void b(c cVar) {
        Activity c2 = a.c();
        if (c2 != null) {
            a("register_ad_view");
            WebView webView = a.a().x().get(Integer.valueOf(cVar.c()));
            if (webView == null && !cVar.h().isEmpty()) {
                webView = (WebView) cVar.h().entrySet().iterator().next().getValue();
            }
            if (this.b != null && webView != null) {
                this.b.registerAdView(webView, c2);
            } else if (this.a != null && webView != null) {
                this.a.registerAdView(webView, c2);
                if (this.h == 1 && cVar != null) {
                    JSONObject a2 = y.a();
                    y.a(a2, "id", this.a.getAvidAdSessionId());
                    new af("AdSession.send_avid_id", cVar.c(), a2).b();
                }
            } else if (this.c != null) {
                this.c.registerAdView(cVar, c2);
                cVar.a(this.c);
                a("register_obstructions");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.d = new AdColonyCustomMessageListener() {
            public void onAdColonyCustomMessage(AdColonyCustomMessage adColonyCustomMessage) {
                JSONObject a2 = y.a(adColonyCustomMessage.getMessage());
                String b = y.b(a2, "event_type");
                boolean d = y.d(a2, "replay");
                boolean equals = y.b(a2, "skip_type").equals("dec");
                if (b.equals("skip") && equals) {
                    return;
                }
                if (!d || (!b.equals("start") && !b.equals("first_quartile") && !b.equals(TJAdUnitConstants.String.VIDEO_MIDPOINT) && !b.equals("third_quartile") && !b.equals(TJAdUnitConstants.String.VIDEO_COMPLETE))) {
                    g.this.b(b);
                }
            }
        };
        AdColony.addCustomMessageListener(this.d, "ias_ad_event");
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        if (a.d() && this.c != null) {
            AvidVideoPlaybackListener avidVideoPlaybackListener = this.c.getAvidVideoPlaybackListener();
            char c2 = 65535;
            try {
                switch (str.hashCode()) {
                    case -1941887438:
                        if (str.equals("first_quartile")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -1638835128:
                        if (str.equals(TJAdUnitConstants.String.VIDEO_MIDPOINT)) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -1367724422:
                        if (str.equals("cancel")) {
                            c2 = 9;
                            break;
                        }
                        break;
                    case -934426579:
                        if (str.equals("resume")) {
                            c2 = 13;
                            break;
                        }
                        break;
                    case -651914917:
                        if (str.equals("third_quartile")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case -599445191:
                        if (str.equals(TJAdUnitConstants.String.VIDEO_COMPLETE)) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case -567202649:
                        if (str.equals("continue")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case -342650039:
                        if (str.equals("sound_mute")) {
                            c2 = 10;
                            break;
                        }
                        break;
                    case 3532159:
                        if (str.equals("skip")) {
                            c2 = 8;
                            break;
                        }
                        break;
                    case 106440182:
                        if (str.equals(TapdaqPlacement.TDPTagPause)) {
                            c2 = 12;
                            break;
                        }
                        break;
                    case 109757538:
                        if (str.equals("start")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 583742045:
                        if (str.equals("in_video_engagement")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case 823102269:
                        if (str.equals("html5_interaction")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case 1648173410:
                        if (str.equals("sound_unmute")) {
                            c2 = 11;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        avidVideoPlaybackListener.recordAdLoadedEvent();
                        avidVideoPlaybackListener.recordAdStartedEvent();
                        avidVideoPlaybackListener.recordAdPlayingEvent();
                        avidVideoPlaybackListener.recordAdImpressionEvent();
                        avidVideoPlaybackListener.recordAdVideoStartEvent();
                        a(str);
                        return;
                    case 1:
                        avidVideoPlaybackListener.recordAdVideoFirstQuartileEvent();
                        a(str);
                        return;
                    case 2:
                        avidVideoPlaybackListener.recordAdVideoMidpointEvent();
                        a(str);
                        return;
                    case 3:
                        avidVideoPlaybackListener.recordAdVideoThirdQuartileEvent();
                        a(str);
                        return;
                    case 4:
                        avidVideoPlaybackListener.recordAdCompleteEvent();
                        a(str);
                        return;
                    case 5:
                        avidVideoPlaybackListener.recordAdUserCloseEvent();
                        avidVideoPlaybackListener.recordAdStoppedEvent();
                        AdColony.removeCustomMessageListener("ias_ad_event");
                        this.c.endSession();
                        a("end_session");
                        this.c = null;
                        a(str);
                        return;
                    case 6:
                    case 7:
                        avidVideoPlaybackListener.recordAdClickThruEvent();
                        a(str);
                        if (this.j && !this.i) {
                            avidVideoPlaybackListener.recordAdPausedEvent();
                            a(TapdaqPlacement.TDPTagPause);
                            this.i = true;
                            this.j = false;
                            return;
                        }
                        return;
                    case 8:
                    case 9:
                        avidVideoPlaybackListener.recordAdSkippedEvent();
                        avidVideoPlaybackListener.recordAdStoppedEvent();
                        AdColony.removeCustomMessageListener("ias_ad_event");
                        this.c.endSession();
                        a("end_session");
                        this.c = null;
                        a(str);
                        return;
                    case 10:
                        avidVideoPlaybackListener.recordAdVolumeChangeEvent(0);
                        a(str);
                        return;
                    case 11:
                        avidVideoPlaybackListener.recordAdVolumeChangeEvent(100);
                        a(str);
                        return;
                    case 12:
                        if (!this.i && !this.j) {
                            avidVideoPlaybackListener.recordAdPausedEvent();
                            a(TapdaqPlacement.TDPTagPause);
                            this.i = true;
                            this.j = false;
                            return;
                        }
                        return;
                    case 13:
                        if (this.i) {
                            avidVideoPlaybackListener.recordAdPlayingEvent();
                            a("resume");
                            this.i = false;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (IllegalStateException unused) {
                new aa.a().a("Recording IAS event for ").a(str).a(" caused IllegalStateException.").a(aa.f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public AvidManagedVideoAdSession e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.j = true;
    }
}
