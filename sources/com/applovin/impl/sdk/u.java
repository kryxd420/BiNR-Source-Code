package com.applovin.impl.sdk;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Animation;
import com.applovin.impl.mediation.a.b;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.n;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;

public class u {
    private final j a;
    private final p b;
    private final MaxAdView c;

    public u(MaxAdView maxAdView, j jVar) {
        this.a = jVar;
        this.b = jVar.v();
        this.c = maxAdView;
    }

    public long a(b bVar) {
        long j;
        this.b.a("ViewabilityTracker", "Checking visibility...");
        if (!this.c.isShown()) {
            this.b.d("ViewabilityTracker", "View is hidden");
            j = 2;
        } else {
            j = 0;
        }
        if (this.c.getAlpha() < bVar.n()) {
            this.b.d("ViewabilityTracker", "View is transparent");
            j |= 4;
        }
        Animation animation = this.c.getAnimation();
        if (animation != null && animation.hasStarted() && !animation.hasEnded()) {
            this.b.d("ViewabilityTracker", "View is animating");
            j |= 8;
        }
        if (this.c.getParent() == null) {
            this.b.d("ViewabilityTracker", "No parent view found");
            j |= 16;
        }
        int pxToDp = AppLovinSdkUtils.pxToDp(this.c.getContext(), this.c.getWidth());
        if (pxToDp < bVar.l()) {
            p pVar = this.b;
            pVar.d("ViewabilityTracker", "View has width (" + pxToDp + ") below threshold");
            j |= 32;
        }
        int pxToDp2 = AppLovinSdkUtils.pxToDp(this.c.getContext(), this.c.getHeight());
        if (pxToDp2 < bVar.m()) {
            p pVar2 = this.b;
            pVar2.d("ViewabilityTracker", "View has height (" + pxToDp2 + ") below threshold");
            j |= 64;
        }
        Point a2 = e.a(this.c.getContext());
        Rect rect = new Rect(0, 0, a2.x, a2.y);
        int[] iArr = {-1, -1};
        this.c.getLocationOnScreen(iArr);
        Rect rect2 = new Rect(iArr[0], iArr[1], iArr[0] + this.c.getWidth(), iArr[1] + this.c.getHeight());
        if (!Rect.intersects(rect, rect2)) {
            p pVar3 = this.b;
            pVar3.d("ViewabilityTracker", "Rect (" + rect2 + ") outside of screen's bounds (" + rect + ")");
            j |= 128;
        }
        Activity a3 = this.a.T().a();
        if (a3 != null && !n.a((View) this.c, a3)) {
            this.b.d("ViewabilityTracker", "View is not in top activity's view hierarchy");
            j |= 256;
        }
        p pVar4 = this.b;
        pVar4.a("ViewabilityTracker", "Returning flags: " + Long.toBinaryString(j));
        return j;
    }
}
