package com.applovin.impl.mediation.ads.a;

import android.app.Activity;
import com.applovin.impl.mediation.ads.MaxInterstitialImpl;
import com.applovin.impl.mediation.c.c;
import com.applovin.impl.mediation.e;
import com.applovin.impl.sdk.b;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class b extends a implements b.a {
    /* access modifiers changed from: private */
    public final com.applovin.impl.sdk.b a;
    /* access modifiers changed from: private */
    public final com.applovin.impl.mediation.b b;
    private final Object c = new Object();
    /* access modifiers changed from: private */
    public MaxAd d = null;
    private C0003b e = C0003b.IDLE;
    /* access modifiers changed from: private */
    public final AtomicBoolean f = new AtomicBoolean();
    /* access modifiers changed from: protected */
    public final a listenerWrapper = new a();

    protected class a implements MaxAdListener, MaxRewardedAdListener {
        protected a() {
        }

        public void onAdClicked(MaxAd maxAd) {
            h.d(b.this.adListener, maxAd, b.this.sdk);
        }

        public void onAdDisplayFailed(final MaxAd maxAd, final int i) {
            b.this.transitionToState(C0003b.IDLE, new Runnable() {
                public void run() {
                    b.this.a.a();
                    b.this.b();
                    h.a(b.this.adListener, maxAd, i, b.this.sdk);
                }
            });
        }

        public void onAdDisplayed(MaxAd maxAd) {
            b.this.a.a();
            h.b(b.this.adListener, maxAd, b.this.sdk);
        }

        public void onAdHidden(final MaxAd maxAd) {
            if (c.a(b.this.d) != maxAd) {
                b.this.logger.d(b.this.tag, "AD HIDDEN callback received for previous ad");
                return;
            }
            b.this.b.a(maxAd);
            b.this.transitionToState(C0003b.IDLE, new Runnable() {
                public void run() {
                    b.this.b();
                    h.c(b.this.adListener, maxAd, b.this.sdk);
                }
            });
        }

        public void onAdLoadFailed(final String str, final int i) {
            b.this.transitionToState(C0003b.IDLE, new Runnable() {
                public void run() {
                    b.this.b();
                    if (b.this.f.compareAndSet(true, false)) {
                        b.this.loadRequestBuilder.a("expired_ad_ad_unit_id");
                    }
                    h.a(b.this.adListener, str, i, b.this.sdk);
                }
            });
        }

        public void onAdLoaded(final MaxAd maxAd) {
            if (b.this.f.compareAndSet(true, false)) {
                b.this.loadRequestBuilder.a("expired_ad_ad_unit_id");
                b.this.b(maxAd);
                return;
            }
            b.this.transitionToState(C0003b.READY, new Runnable() {
                public void run() {
                    b.this.b(maxAd);
                    h.a(b.this.adListener, maxAd, b.this.sdk);
                }
            });
        }

        public void onRewardedVideoCompleted(MaxAd maxAd) {
            h.f(b.this.adListener, maxAd, b.this.sdk);
        }

        public void onRewardedVideoStarted(MaxAd maxAd) {
            h.e(b.this.adListener, maxAd, b.this.sdk);
        }

        public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
            h.a(b.this.adListener, maxAd, maxReward, b.this.sdk);
        }
    }

    /* renamed from: com.applovin.impl.mediation.ads.a.b$b  reason: collision with other inner class name */
    public enum C0003b {
        IDLE,
        LOADING,
        READY,
        SHOWING,
        DESTROYED
    }

    protected b(String str, String str2, j jVar) {
        super(str, str2, jVar);
        this.a = new com.applovin.impl.sdk.b(jVar, this);
        this.b = new com.applovin.impl.mediation.b(jVar, this.listenerWrapper);
    }

    /* access modifiers changed from: private */
    public MaxAd a() {
        MaxAd maxAd;
        synchronized (this.c) {
            maxAd = this.d;
            this.d = null;
        }
        return maxAd;
    }

    private void a(MaxAd maxAd) {
        synchronized (this.c) {
            this.d = maxAd;
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.sdk.a(getActivity()).destroyAd(a());
    }

    /* access modifiers changed from: private */
    public void b(MaxAd maxAd) {
        a(maxAd);
        c(maxAd);
    }

    private void c(MaxAd maxAd) {
        long h = maxAd instanceof com.applovin.impl.mediation.a.c ? ((com.applovin.impl.mediation.a.c) maxAd).h() : maxAd instanceof e ? ((e) maxAd).d() : -1;
        if (h >= 0) {
            p pVar = this.logger;
            String str = this.tag;
            pVar.a(str, "Scheduling ad expiration " + TimeUnit.MILLISECONDS.toMinutes(h) + " minutes from now for " + getAdUnitId() + " ...");
            this.a.a(h);
        }
    }

    public void destroy() {
        transitionToState(C0003b.DESTROYED, new Runnable() {
            public void run() {
                MaxAd a2 = b.this.a();
                p pVar = b.this.logger;
                String str = b.this.tag;
                pVar.a(str, "Destroying ad for '" + b.this.adUnitId + "'; current ad: " + a2 + "...");
                b.this.sdk.a(b.this.getActivity()).destroyAd(a2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public abstract Activity getActivity();

    /* access modifiers changed from: protected */
    public MaxAd getLoadedAd() {
        MaxAd maxAd;
        synchronized (this.c) {
            maxAd = this.d;
        }
        return maxAd;
    }

    public boolean isReady() {
        boolean z;
        synchronized (this.c) {
            z = this.d != null && this.d.isReady() && this.e == C0003b.READY;
        }
        return z;
    }

    public void onAdExpired() {
        p pVar = this.logger;
        String str = this.tag;
        pVar.a(str, "Ad expired " + getAdUnitId());
        this.f.set(true);
        this.loadRequestBuilder.a("expired_ad_ad_unit_id", getAdUnitId());
        this.sdk.a(getActivity()).loadAd(this.adUnitId, this instanceof MaxInterstitialImpl ? MaxAdFormat.INTERSTITIAL : MaxAdFormat.REWARDED, this.loadRequestBuilder.a(), getActivity(), this.listenerWrapper);
    }

    /* access modifiers changed from: protected */
    public void onTransitionedToState(C0003b bVar, C0003b bVar2) {
    }

    /* access modifiers changed from: protected */
    public void showFullscreenAd(Activity activity) {
        Object loadedAd = getLoadedAd();
        if (loadedAd instanceof e) {
            loadedAd = ((e) loadedAd).a(activity);
        }
        com.applovin.impl.mediation.a.c cVar = (com.applovin.impl.mediation.a.c) loadedAd;
        this.b.b(cVar);
        p pVar = this.logger;
        String str = this.tag;
        pVar.a(str, "Showing ad for '" + this.adUnitId + "'; loaded ad: " + cVar + "...");
        this.sdk.a(activity).showFullscreenAd(cVar, activity);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x017d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void transitionToState(com.applovin.impl.mediation.ads.a.b.C0003b r8, java.lang.Runnable r9) {
        /*
            r7 = this;
            com.applovin.impl.mediation.ads.a.b$b r0 = r7.e
            java.lang.Object r1 = r7.c
            monitor-enter(r1)
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r4.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "Attempting state transition from "
            r4.append(r5)     // Catch:{ all -> 0x01ab }
            r4.append(r0)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = " to "
            r4.append(r5)     // Catch:{ all -> 0x01ab }
            r4.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01ab }
            r2.a(r3, r4)     // Catch:{ all -> 0x01ab }
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.IDLE     // Catch:{ all -> 0x01ab }
            r3 = 1
            r4 = 0
            if (r0 != r2) goto L_0x0060
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.LOADING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0032
        L_0x002f:
            r4 = 1
            goto L_0x0151
        L_0x0032:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.DESTROYED     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0037
            goto L_0x002f
        L_0x0037:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.SHOWING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0046
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "No ad is loading or loaded"
        L_0x0041:
            r2.e(r3, r5)     // Catch:{ all -> 0x01ab }
            goto L_0x0151
        L_0x0046:
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Unable to transition to: "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            r5.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
        L_0x005b:
            r2.d(r3, r5)     // Catch:{ all -> 0x01ab }
            goto L_0x0151
        L_0x0060:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.LOADING     // Catch:{ all -> 0x01ab }
            if (r0 != r2) goto L_0x009f
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.IDLE     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0069
            goto L_0x002f
        L_0x0069:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.LOADING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0074
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "An ad is already loading"
            goto L_0x0041
        L_0x0074:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.READY     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0079
            goto L_0x002f
        L_0x0079:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.SHOWING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0084
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "An ad is not ready to be shown yet"
            goto L_0x0041
        L_0x0084:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.DESTROYED     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0089
            goto L_0x002f
        L_0x0089:
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Unable to transition to: "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            r5.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
            goto L_0x005b
        L_0x009f:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.READY     // Catch:{ all -> 0x01ab }
            if (r0 != r2) goto L_0x00e1
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.IDLE     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00a8
            goto L_0x002f
        L_0x00a8:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.LOADING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00b3
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "An ad is already loaded"
            goto L_0x0041
        L_0x00b3:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.READY     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00be
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "An ad is already marked as ready"
            goto L_0x005b
        L_0x00be:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.SHOWING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00c4
            goto L_0x002f
        L_0x00c4:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.DESTROYED     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00ca
            goto L_0x002f
        L_0x00ca:
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Unable to transition to: "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            r5.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
            goto L_0x005b
        L_0x00e1:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.SHOWING     // Catch:{ all -> 0x01ab }
            if (r0 != r2) goto L_0x012c
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.IDLE     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00eb
            goto L_0x002f
        L_0x00eb:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.LOADING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x00f7
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "Can not load another interstitial while the ad is showing"
            goto L_0x0041
        L_0x00f7:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.READY     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0103
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "An ad is already showing, ignoring"
            goto L_0x005b
        L_0x0103:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.SHOWING     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x010f
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "The ad is already showing, not showing another one"
            goto L_0x0041
        L_0x010f:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.DESTROYED     // Catch:{ all -> 0x01ab }
            if (r8 != r2) goto L_0x0115
            goto L_0x002f
        L_0x0115:
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Unable to transition to: "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            r5.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
            goto L_0x005b
        L_0x012c:
            com.applovin.impl.mediation.ads.a.b$b r2 = com.applovin.impl.mediation.ads.a.b.C0003b.DESTROYED     // Catch:{ all -> 0x01ab }
            if (r0 != r2) goto L_0x0138
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = "No operations are allowed on a destroyed instance"
            goto L_0x0041
        L_0x0138:
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Unknown state: "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            com.applovin.impl.mediation.ads.a.b$b r6 = r7.e     // Catch:{ all -> 0x01ab }
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
            goto L_0x005b
        L_0x0151:
            if (r4 == 0) goto L_0x017d
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Transitioning from "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            com.applovin.impl.mediation.ads.a.b$b r6 = r7.e     // Catch:{ all -> 0x01ab }
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = " to "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            r5.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "..."
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
            r2.a(r3, r5)     // Catch:{ all -> 0x01ab }
            r7.e = r8     // Catch:{ all -> 0x01ab }
            goto L_0x019f
        L_0x017d:
            com.applovin.impl.sdk.p r2 = r7.logger     // Catch:{ all -> 0x01ab }
            java.lang.String r3 = r7.tag     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r5.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Not allowed transition from "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            com.applovin.impl.mediation.ads.a.b$b r6 = r7.e     // Catch:{ all -> 0x01ab }
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = " to "
            r5.append(r6)     // Catch:{ all -> 0x01ab }
            r5.append(r8)     // Catch:{ all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ab }
            r2.c(r3, r5)     // Catch:{ all -> 0x01ab }
        L_0x019f:
            monitor-exit(r1)     // Catch:{ all -> 0x01ab }
            if (r4 == 0) goto L_0x01aa
            if (r9 == 0) goto L_0x01a7
            r9.run()
        L_0x01a7:
            r7.onTransitionedToState(r0, r8)
        L_0x01aa:
            return
        L_0x01ab:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01ab }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.mediation.ads.a.b.transitionToState(com.applovin.impl.mediation.ads.a.b$b, java.lang.Runnable):void");
    }
}
