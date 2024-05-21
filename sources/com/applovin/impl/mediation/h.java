package com.applovin.impl.mediation;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.applovin.impl.mediation.a.e;
import com.applovin.impl.mediation.a.g;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxErrorCodes;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.adapter.MaxAdViewAdapter;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.MaxAdapterError;
import com.applovin.mediation.adapter.MaxInterstitialAdapter;
import com.applovin.mediation.adapter.MaxRewardedAdapter;
import com.applovin.mediation.adapter.MaxSignalProvider;
import com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterSignalCollectionParameters;
import java.util.concurrent.atomic.AtomicBoolean;

public class h {
    /* access modifiers changed from: private */
    public final Handler a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final j b;
    /* access modifiers changed from: private */
    public final p c;
    private final String d;
    /* access modifiers changed from: private */
    public final e e;
    /* access modifiers changed from: private */
    public final String f;
    /* access modifiers changed from: private */
    public MaxAdapter g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public com.applovin.impl.mediation.a.a i;
    /* access modifiers changed from: private */
    public View j;
    /* access modifiers changed from: private */
    public final a k = new a();
    /* access modifiers changed from: private */
    public MaxAdapterResponseParameters l;
    private final AtomicBoolean m = new AtomicBoolean(true);
    /* access modifiers changed from: private */
    public final AtomicBoolean n = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean o = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean p = new AtomicBoolean(false);

    private class a implements MaxAdViewAdapterListener, MaxInterstitialAdapterListener, MaxRewardedAdapterListener {
        /* access modifiers changed from: private */
        public d b;

        private a() {
        }

        private int a(MaxAdapterError maxAdapterError) {
            return maxAdapterError != null ? maxAdapterError.getErrorCode() : MaxAdapterError.ERROR_CODE_UNSPECIFIED;
        }

        /* access modifiers changed from: private */
        public void a(d dVar) {
            if (dVar != null) {
                this.b = dVar;
                return;
            }
            throw new IllegalArgumentException("No listener specified");
        }

        private void a(String str) {
            h.this.o.set(true);
            a(str, (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (h.this.n.compareAndSet(false, true)) {
                        a.this.b.onAdLoaded(h.this.i);
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public void a(String str, int i) {
            a(str, i, "");
        }

        private void a(String str, final int i, final String str2) {
            a(str, (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (h.this.n.compareAndSet(false, true)) {
                        a.this.b.a(h.this.h, i, str2);
                        if (h.this.e.D()) {
                            h.this.g();
                        }
                    }
                }
            });
        }

        private void a(final String str, final MaxAdListener maxAdListener, final Runnable runnable) {
            h.this.a.post(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        String name = maxAdListener != null ? maxAdListener.getClass().getName() : null;
                        p h = h.this.c;
                        h.b("MediationAdapterWrapper", "Failed to forward call (" + str + ") to " + name, e);
                    }
                }
            });
        }

        private void a(String str, MaxAdapterError maxAdapterError) {
            a(str, a(maxAdapterError), b(maxAdapterError));
        }

        private String b(MaxAdapterError maxAdapterError) {
            return maxAdapterError != null ? maxAdapterError.getAdapterError() : "";
        }

        /* access modifiers changed from: private */
        public void b(String str, int i) {
            b(str, i, "");
        }

        private void b(String str, final int i, final String str2) {
            a(str, (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.a((MaxAd) h.this.i, i, str2);
                }
            });
        }

        private void b(String str, MaxAdapterError maxAdapterError) {
            b(str, a(maxAdapterError), b(maxAdapterError));
        }

        public void onAdViewAdClicked() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": adview ad clicked");
            a("onAdViewAdClicked", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdClicked(h.this.i);
                }
            });
        }

        public void onAdViewAdCollapsed() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": adview ad collapsed");
            a("onAdViewAdCollapsed", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (a.this.b instanceof MaxAdViewAdListener) {
                        ((MaxAdViewAdListener) a.this.b).onAdCollapsed(h.this.i);
                    }
                }
            });
        }

        public void onAdViewAdDisplayFailed(MaxAdapterError maxAdapterError) {
            p h = h.this.c;
            h.c("MediationAdapterWrapper", h.this.f + ": adview ad failed to display with code: " + maxAdapterError);
            b("onAdViewAdDisplayFailed", maxAdapterError);
        }

        public void onAdViewAdDisplayed() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": adview ad displayed");
            a("onAdViewAdDisplayed", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdDisplayed(h.this.i);
                }
            });
        }

        public void onAdViewAdExpanded() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": adview ad expanded");
            a("onAdViewAdExpanded", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (a.this.b instanceof MaxAdViewAdListener) {
                        ((MaxAdViewAdListener) a.this.b).onAdExpanded(h.this.i);
                    }
                }
            });
        }

        public void onAdViewAdHidden() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": adview ad hidden");
            a("onAdViewAdHidden", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdHidden(h.this.i);
                }
            });
        }

        public void onAdViewAdLoadFailed(MaxAdapterError maxAdapterError) {
            p h = h.this.c;
            h.c("MediationAdapterWrapper", h.this.f + ": adview ad ad failed to load with code: " + maxAdapterError);
            a("onAdViewAdLoadFailed", maxAdapterError);
        }

        public void onAdViewAdLoaded(View view) {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": adview ad loaded");
            View unused = h.this.j = view;
            a("onAdViewAdLoaded");
        }

        public void onInterstitialAdClicked() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": interstitial ad clicked");
            a("onInterstitialAdClicked", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdClicked(h.this.i);
                }
            });
        }

        public void onInterstitialAdDisplayFailed(MaxAdapterError maxAdapterError) {
            p h = h.this.c;
            h.c("MediationAdapterWrapper", h.this.f + ": interstitial ad failed to display with code " + maxAdapterError);
            b("onInterstitialAdDisplayFailed", maxAdapterError);
        }

        public void onInterstitialAdDisplayed() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": interstitial ad displayed");
            a("onInterstitialAdDisplayed", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdDisplayed(h.this.i);
                }
            });
        }

        public void onInterstitialAdHidden() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": interstitial ad hidden");
            a("onInterstitialAdHidden", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdHidden(h.this.i);
                }
            });
        }

        public void onInterstitialAdLoadFailed(MaxAdapterError maxAdapterError) {
            p h = h.this.c;
            h.c("MediationAdapterWrapper", h.this.f + ": interstitial ad failed to load with error " + maxAdapterError);
            a("onInterstitialAdLoadFailed", maxAdapterError);
        }

        public void onInterstitialAdLoaded() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": interstitial ad loaded");
            a("onInterstitialAdLoaded");
        }

        public void onRewardedAdClicked() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": rewarded ad clicked");
            a("onRewardedAdClicked", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdClicked(h.this.i);
                }
            });
        }

        public void onRewardedAdDisplayFailed(MaxAdapterError maxAdapterError) {
            p h = h.this.c;
            h.c("MediationAdapterWrapper", h.this.f + ": rewarded ad display failed with error: " + maxAdapterError);
            b("onRewardedAdDisplayFailed", maxAdapterError);
        }

        public void onRewardedAdDisplayed() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": rewarded ad displayed");
            a("onRewardedAdDisplayed", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdDisplayed(h.this.i);
                }
            });
        }

        public void onRewardedAdHidden() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": rewarded ad hidden");
            a("onRewardedAdHidden", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    a.this.b.onAdHidden(h.this.i);
                }
            });
        }

        public void onRewardedAdLoadFailed(MaxAdapterError maxAdapterError) {
            p h = h.this.c;
            h.c("MediationAdapterWrapper", h.this.f + ": rewarded ad failed to load with code: " + maxAdapterError);
            a("onRewardedAdLoadFailed", maxAdapterError);
        }

        public void onRewardedAdLoaded() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": rewarded ad loaded");
            a("onRewardedAdLoaded");
        }

        public void onRewardedAdVideoCompleted() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": rewarded video completed");
            a("onRewardedAdVideoCompleted", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (a.this.b instanceof MaxRewardedAdListener) {
                        ((MaxRewardedAdListener) a.this.b).onRewardedVideoCompleted(h.this.i);
                    }
                }
            });
        }

        public void onRewardedAdVideoStarted() {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": rewarded video started");
            a("onRewardedAdVideoStarted", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (a.this.b instanceof MaxRewardedAdListener) {
                        ((MaxRewardedAdListener) a.this.b).onRewardedVideoStarted(h.this.i);
                    }
                }
            });
        }

        public void onUserRewarded(final MaxReward maxReward) {
            p h = h.this.c;
            h.b("MediationAdapterWrapper", h.this.f + ": user was rewarded: " + maxReward);
            a("onUserRewarded", (MaxAdListener) this.b, (Runnable) new Runnable() {
                public void run() {
                    if (a.this.b instanceof MaxRewardedAdListener) {
                        ((MaxRewardedAdListener) a.this.b).onUserRewarded(h.this.i, maxReward);
                    }
                }
            });
        }
    }

    private static class b {
        /* access modifiers changed from: private */
        public final g a;
        /* access modifiers changed from: private */
        public final MaxSignalCollectionListener b;
        /* access modifiers changed from: private */
        public final AtomicBoolean c = new AtomicBoolean();

        b(g gVar, MaxSignalCollectionListener maxSignalCollectionListener) {
            this.a = gVar;
            this.b = maxSignalCollectionListener;
        }
    }

    private class c extends com.applovin.impl.sdk.d.a {
        private c() {
            super("TaskTimeoutMediatedAd", h.this.b);
        }

        public i a() {
            return i.G;
        }

        public void run() {
            if (!h.this.o.get()) {
                p h = h.this.c;
                String c = c();
                h.c(c, h.this.f + " is timing out " + h.this.i + "...");
                h.this.k.a(c(), (int) MaxErrorCodes.MEDIATION_ADAPTER_TIMEOUT);
            }
        }
    }

    private class d extends com.applovin.impl.sdk.d.a {
        private final b c;

        private d(b bVar) {
            super("TaskTimeoutSignalCollection", h.this.b);
            this.c = bVar;
        }

        public i a() {
            return i.H;
        }

        public void run() {
            if (!this.c.c.get()) {
                c(h.this.f + " is timing out " + this.c.a + "...");
                h hVar = h.this;
                hVar.b("The adapter (" + h.this.f + ") timed out", this.c);
            }
        }
    }

    h(e eVar, MaxAdapter maxAdapter, j jVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("No adapter name specified");
        } else if (maxAdapter == null) {
            throw new IllegalArgumentException("No adapter specified");
        } else if (jVar != null) {
            this.d = eVar.u();
            this.g = maxAdapter;
            this.b = jVar;
            this.c = jVar.v();
            this.e = eVar;
            this.f = maxAdapter.getClass().getSimpleName();
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        p pVar = this.c;
        pVar.b("MediationAdapterWrapper", "Marking " + this.f + " as disabled due to: " + str);
        this.m.set(false);
    }

    /* access modifiers changed from: private */
    public void a(String str, b bVar) {
        if (bVar.c.compareAndSet(false, true) && bVar.b != null) {
            bVar.b.onSignalCollected(str);
        }
    }

    private void a(final String str, final Runnable runnable) {
        AnonymousClass3 r0 = new Runnable() {
            public void run() {
                try {
                    p h = h.this.c;
                    h.a("MediationAdapterWrapper", h.this.f + ": running " + str + "...");
                    runnable.run();
                    p h2 = h.this.c;
                    h2.a("MediationAdapterWrapper", h.this.f + ": finished " + str + "");
                } catch (Throwable th) {
                    p h3 = h.this.c;
                    h3.b("MediationAdapterWrapper", "Unable to run adapter operation " + str + ", marking " + h.this.f + " as disabled", th);
                    h hVar = h.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("fail_");
                    sb.append(str);
                    hVar.a(sb.toString());
                }
            }
        };
        if (this.e.w()) {
            this.a.post(r0);
        } else {
            r0.run();
        }
    }

    /* access modifiers changed from: private */
    public void b(String str, b bVar) {
        if (bVar.c.compareAndSet(false, true) && bVar.b != null) {
            bVar.b.onSignalCollectionFailed(str);
        }
    }

    public View a() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public void a(final com.applovin.impl.mediation.a.a aVar, final Activity activity) {
        final Runnable runnable;
        if (aVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (aVar.b() != this) {
            throw new IllegalArgumentException("Mediated ad belongs to a different adapter");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (!this.m.get()) {
            p pVar = this.c;
            pVar.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is disabled. Showing ads with this adapter is disabled.");
            this.k.b("ad_show", (int) MaxErrorCodes.MEDIATION_ADAPTER_DISABLED);
        } else if (!d()) {
            p pVar2 = this.c;
            pVar2.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' does not have an ad loaded. Please load an ad first");
            this.k.b("ad_show", (int) MaxErrorCodes.MEDIATION_ADAPTER_AD_NOT_READY);
        } else {
            if (aVar.getFormat() == MaxAdFormat.INTERSTITIAL) {
                if (this.g instanceof MaxInterstitialAdapter) {
                    runnable = new Runnable() {
                        public void run() {
                            ((MaxInterstitialAdapter) h.this.g).showInterstitialAd(h.this.l, activity, h.this.k);
                        }
                    };
                } else {
                    p pVar3 = this.c;
                    pVar3.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an interstitial adapter.");
                    this.k.b("showFullscreenAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                    return;
                }
            } else if (aVar.getFormat() != MaxAdFormat.REWARDED) {
                p pVar4 = this.c;
                pVar4.e("MediationAdapterWrapper", "Failed to show " + aVar + ": " + aVar.getFormat() + " is not a supported ad format");
                this.k.b("showFullscreenAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            } else if (this.g instanceof MaxRewardedAdapter) {
                runnable = new Runnable() {
                    public void run() {
                        ((MaxRewardedAdapter) h.this.g).showRewardedAd(h.this.l, activity, h.this.k);
                    }
                };
            } else {
                p pVar5 = this.c;
                pVar5.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an incentivized adapter.");
                this.k.b("showFullscreenAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            a("ad_render", (Runnable) new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable th) {
                        p h = h.this.c;
                        h.b("MediationAdapterWrapper", "Failed to start displaying ad" + aVar, th);
                        h.this.k.b("ad_render", (int) MaxAdapterError.ERROR_CODE_UNSPECIFIED);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final MaxAdapterInitializationParameters maxAdapterInitializationParameters, final Activity activity) {
        a("initialize", (Runnable) new Runnable() {
            public void run() {
                h.this.g.initialize(maxAdapterInitializationParameters, activity, new MaxAdapter.OnCompletionListener() {
                    public void onCompletion() {
                        long C = h.this.e.C();
                        if (C > 0) {
                            h.this.a.postDelayed(new Runnable() {
                                public void run() {
                                    h.this.b.a(activity).a(h.this.e.t());
                                }
                            }, C);
                        } else {
                            h.this.b.a(activity).a(h.this.e.t());
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(MaxAdapterSignalCollectionParameters maxAdapterSignalCollectionParameters, g gVar, Activity activity, MaxSignalCollectionListener maxSignalCollectionListener) {
        if (maxSignalCollectionListener == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (!this.m.get()) {
            p pVar = this.c;
            pVar.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is disabled. Signal collection ads with this adapter is disabled.");
            maxSignalCollectionListener.onSignalCollectionFailed("The adapter (" + this.f + ") is disabled");
        } else {
            final b bVar = new b(gVar, maxSignalCollectionListener);
            if (this.g instanceof MaxSignalProvider) {
                final MaxSignalProvider maxSignalProvider = (MaxSignalProvider) this.g;
                final MaxAdapterSignalCollectionParameters maxAdapterSignalCollectionParameters2 = maxAdapterSignalCollectionParameters;
                final Activity activity2 = activity;
                final g gVar2 = gVar;
                a("collect_signal", (Runnable) new Runnable() {
                    public void run() {
                        maxSignalProvider.collectSignal(maxAdapterSignalCollectionParameters2, activity2, new MaxSignalCollectionListener() {
                            public void onSignalCollected(String str) {
                                h.this.a(str, bVar);
                            }

                            public void onSignalCollectionFailed(String str) {
                                h.this.b(str, bVar);
                            }
                        });
                        if (bVar.c.get()) {
                            return;
                        }
                        if (gVar2.y() == 0) {
                            p h = h.this.c;
                            h.a("MediationAdapterWrapper", "Failing signal collection " + gVar2 + " since it has 0 timeout");
                            h hVar = h.this;
                            hVar.b("The adapter (" + h.this.f + ") has 0 timeout", bVar);
                        } else if (gVar2.y() > 0) {
                            p h2 = h.this.c;
                            h2.a("MediationAdapterWrapper", "Setting timeout " + gVar2.y() + "ms. for " + gVar2);
                            h.this.b.D().a((com.applovin.impl.sdk.d.a) new d(bVar), q.a.MEDIATION_TIMEOUT, gVar2.y());
                        } else {
                            p h3 = h.this.c;
                            h3.a("MediationAdapterWrapper", "Negative timeout set for " + gVar2 + ", not scheduling a timeout");
                        }
                    }
                });
                return;
            }
            b("The adapter (" + this.f + ") does not support signal collection", bVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, com.applovin.impl.mediation.a.a aVar) {
        this.h = str;
        this.i = aVar;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, final MaxAdapterResponseParameters maxAdapterResponseParameters, final com.applovin.impl.mediation.a.a aVar, final Activity activity, d dVar) {
        final Runnable runnable;
        if (aVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.m.get()) {
            p pVar = this.c;
            pVar.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' was disabled due to earlier failures. Loading ads with this adapter is disabled.");
            dVar.onAdLoadFailed(str, MaxErrorCodes.MEDIATION_ADAPTER_DISABLED);
        } else {
            this.l = maxAdapterResponseParameters;
            this.k.a(dVar);
            if (aVar.getFormat() == MaxAdFormat.INTERSTITIAL) {
                if (this.g instanceof MaxInterstitialAdapter) {
                    runnable = new Runnable() {
                        public void run() {
                            ((MaxInterstitialAdapter) h.this.g).loadInterstitialAd(maxAdapterResponseParameters, activity, h.this.k);
                        }
                    };
                } else {
                    p pVar2 = this.c;
                    pVar2.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an interstitial adapter.");
                    this.k.a("loadAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                    return;
                }
            } else if (aVar.getFormat() == MaxAdFormat.REWARDED) {
                if (this.g instanceof MaxRewardedAdapter) {
                    runnable = new Runnable() {
                        public void run() {
                            ((MaxRewardedAdapter) h.this.g).loadRewardedAd(maxAdapterResponseParameters, activity, h.this.k);
                        }
                    };
                } else {
                    p pVar3 = this.c;
                    pVar3.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an incentivized adapter.");
                    this.k.a("loadAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                    return;
                }
            } else if (aVar.getFormat() != MaxAdFormat.BANNER && aVar.getFormat() != MaxAdFormat.LEADER && aVar.getFormat() != MaxAdFormat.MREC) {
                p pVar4 = this.c;
                pVar4.e("MediationAdapterWrapper", "Failed to load " + aVar + ": " + aVar.getFormat() + " is not a supported ad format");
                this.k.a("loadAd", (int) MaxErrorCodes.FORMAT_TYPE_NOT_SUPPORTED);
                return;
            } else if (this.g instanceof MaxAdViewAdapter) {
                runnable = new Runnable() {
                    public void run() {
                        ((MaxAdViewAdapter) h.this.g).loadAdViewAd(maxAdapterResponseParameters, aVar.getFormat(), activity, h.this.k);
                    }
                };
            } else {
                p pVar5 = this.c;
                pVar5.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an adview-based adapter.");
                this.k.a("loadAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            a("ad_load", (Runnable) new Runnable() {
                public void run() {
                    com.applovin.impl.sdk.c.h E;
                    com.applovin.impl.sdk.c.g gVar;
                    try {
                        if (h.this.p.compareAndSet(false, true)) {
                            if (h.this.i.getFormat() == MaxAdFormat.INTERSTITIAL) {
                                E = h.this.b.E();
                                gVar = com.applovin.impl.sdk.c.g.v;
                            } else if (h.this.i.getFormat() == MaxAdFormat.REWARDED) {
                                E = h.this.b.E();
                                gVar = com.applovin.impl.sdk.c.g.w;
                            } else {
                                E = h.this.b.E();
                                gVar = com.applovin.impl.sdk.c.g.x;
                            }
                        } else if (h.this.i.getFormat() == MaxAdFormat.INTERSTITIAL) {
                            E = h.this.b.E();
                            gVar = com.applovin.impl.sdk.c.g.y;
                        } else if (h.this.i.getFormat() == MaxAdFormat.REWARDED) {
                            E = h.this.b.E();
                            gVar = com.applovin.impl.sdk.c.g.z;
                        } else {
                            E = h.this.b.E();
                            gVar = com.applovin.impl.sdk.c.g.A;
                        }
                        E.a(gVar);
                        runnable.run();
                    } catch (Throwable th) {
                        p h = h.this.c;
                        h.b("MediationAdapterWrapper", "Failed start loading " + aVar, th);
                        h.this.k.a("loadAd", -1);
                    }
                    if (h.this.n.get()) {
                        return;
                    }
                    if (h.this.e.y() == 0) {
                        p h2 = h.this.c;
                        h2.a("MediationAdapterWrapper", "Failing ad " + aVar + " since it has 0 timeout");
                        h.this.k.a("loadAd", (int) MaxErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT);
                    } else if (h.this.e.y() > 0) {
                        p h3 = h.this.c;
                        h3.a("MediationAdapterWrapper", "Setting timeout " + h.this.e.y() + "ms. for " + aVar);
                        h.this.b.D().a((com.applovin.impl.sdk.d.a) new c(), q.a.MEDIATION_TIMEOUT, h.this.e.y());
                    } else {
                        p h4 = h.this.c;
                        h4.a("MediationAdapterWrapper", "Negative timeout set for " + aVar + ", not scheduling a timeout");
                    }
                }
            });
        }
    }

    public String b() {
        return this.d;
    }

    public boolean c() {
        return this.m.get();
    }

    public boolean d() {
        return this.n.get() && this.o.get();
    }

    public String e() {
        if (this.g == null) {
            return null;
        }
        try {
            return this.g.getSdkVersion();
        } catch (Throwable th) {
            p pVar = this.c;
            pVar.b("MediationAdapterWrapper", "Unable to get adapter's SDK version, marking " + this + " as disabled", th);
            a("fail_version");
            return null;
        }
    }

    public String f() {
        if (this.g == null) {
            return null;
        }
        try {
            return this.g.getAdapterVersion();
        } catch (Throwable th) {
            p pVar = this.c;
            pVar.b("MediationAdapterWrapper", "Unable to get adapter version, marking " + this + " as disabled", th);
            a("fail_version");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void g() {
        a("destroy", (Runnable) new Runnable() {
            public void run() {
                h.this.a("destroy");
                h.this.g.onDestroy();
                MaxAdapter unused = h.this.g = null;
            }
        });
    }

    public String toString() {
        return "MediationAdapterWrapper{adapterTag='" + this.f + "'" + '}';
    }
}
