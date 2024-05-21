package com.applovin.impl.adview;

import android.net.Uri;
import android.os.Bundle;
import com.applovin.impl.a.a;
import com.applovin.impl.a.d;
import com.applovin.impl.a.g;
import com.applovin.impl.a.h;
import com.applovin.impl.a.i;
import com.applovin.impl.a.k;
import com.applovin.impl.adview.g;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.p;
import com.tapjoy.TJAdUnitConstants;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class o extends j {
    private final Set<g> a = new HashSet();

    private void a() {
        if (isFullyWatched() && !this.a.isEmpty()) {
            p pVar = this.logger;
            pVar.c("InterstitialActivity", "Firing " + this.a.size() + " un-fired video progress trackers when video was completed.");
            a(this.a);
        }
    }

    private void a(a.c cVar) {
        a(cVar, d.UNSPECIFIED);
    }

    private void a(a.c cVar, d dVar) {
        a(cVar, "", dVar);
    }

    private void a(a.c cVar, String str) {
        a(cVar, str, d.UNSPECIFIED);
    }

    private void a(a.c cVar, String str, d dVar) {
        if (isVastAd()) {
            a(((a) this.currentAd).a(cVar, str), dVar);
        }
    }

    private void a(Set<g> set) {
        a(set, d.UNSPECIFIED);
    }

    private void a(Set<g> set, d dVar) {
        if (isVastAd() && set != null && !set.isEmpty()) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds((long) this.videoView.getCurrentPosition());
            k c = b().c();
            Uri a2 = c != null ? c.a() : null;
            p pVar = this.logger;
            pVar.a("InterstitialActivity", "Firing " + set.size() + " tracker(s): " + set);
            i.a(set, seconds, a2, dVar, this.sdk);
        }
    }

    private a b() {
        if (this.currentAd instanceof a) {
            return (a) this.currentAd;
        }
        return null;
    }

    public void clickThroughFromVideo() {
        super.clickThroughFromVideo();
        a(a.c.VIDEO_CLICK);
    }

    public void dismiss() {
        if (isVastAd()) {
            a(a.c.VIDEO, TJAdUnitConstants.String.CLOSE);
            a(a.c.COMPANION, TJAdUnitConstants.String.CLOSE);
        }
        super.dismiss();
    }

    public void handleCountdownStep() {
        if (isVastAd()) {
            long seconds = ((long) this.computedLengthSeconds) - TimeUnit.MILLISECONDS.toSeconds((long) (this.videoView.getDuration() - this.videoView.getCurrentPosition()));
            HashSet hashSet = new HashSet();
            for (g gVar : new HashSet(this.a)) {
                if (gVar.a(seconds, getVideoPercentViewed())) {
                    hashSet.add(gVar);
                    this.a.remove(gVar);
                }
            }
            a((Set<g>) hashSet);
        }
    }

    public void handleMediaError() {
        a(a.c.ERROR, d.MEDIA_FILE_ERROR);
        super.handleMediaError();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isVastAd()) {
            this.a.addAll(b().a(a.c.VIDEO, h.a));
            a(a.c.IMPRESSION);
            a(a.c.VIDEO, "creativeView");
        }
    }

    public void playVideo() {
        this.countdownManager.a("PROGRESS_TRACKING", ((Long) this.sdk.a(b.eN)).longValue(), (g.a) new g.a() {
            public void a() {
                o.this.handleCountdownStep();
            }

            public boolean b() {
                return o.this.shouldContinueFullLengthVideoCountdown();
            }
        });
        super.playVideo();
    }

    public void showPoststitial() {
        if (isVastAd()) {
            a();
            if (!i.c(b())) {
                dismiss();
                return;
            } else if (!this.poststitialWasDisplayed) {
                a(a.c.COMPANION, "creativeView");
            } else {
                return;
            }
        }
        super.showPoststitial();
    }

    public void skipVideo() {
        a(a.c.VIDEO, "skip");
        super.skipVideo();
    }

    public void toggleMute() {
        a.c cVar;
        String str;
        super.toggleMute();
        if (this.videoMuted) {
            cVar = a.c.VIDEO;
            str = "mute";
        } else {
            cVar = a.c.VIDEO;
            str = "unmute";
        }
        a(cVar, str);
    }
}
