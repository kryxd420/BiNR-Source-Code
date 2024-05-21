package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.vng.x;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    /* access modifiers changed from: private */
    public final String a;

    static class a implements ReactiveVideoTracker {
        a() {
        }

        public void changeTargetView(View view) {
        }

        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public void setActivity(Activity activity) {
        }

        public void setPlayerVolume(Double d) {
        }

        public void stopTracking() {
        }

        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.a = str;
    }

    /* renamed from: c */
    public ReactiveVideoTracker a() {
        return (ReactiveVideoTracker) x.a(new x.a<ReactiveVideoTracker>() {
            public com.moat.analytics.mobile.vng.a.b.a<ReactiveVideoTracker> a() {
                p.a("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return com.moat.analytics.mobile.vng.a.b.a.a(new y(ReactiveVideoTrackerPlugin.this.a));
            }
        }, ReactiveVideoTracker.class);
    }

    /* renamed from: d */
    public ReactiveVideoTracker b() {
        return new a();
    }
}
