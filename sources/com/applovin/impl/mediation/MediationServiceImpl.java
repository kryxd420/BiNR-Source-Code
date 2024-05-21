package com.applovin.impl.mediation;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.applovin.impl.mediation.MaxAdapterParametersImpl;
import com.applovin.impl.mediation.a.e;
import com.applovin.impl.mediation.a.f;
import com.applovin.impl.mediation.a.g;
import com.applovin.impl.mediation.b.b;
import com.applovin.impl.mediation.b.c;
import com.applovin.impl.mediation.b.d;
import com.applovin.impl.mediation.f;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxErrorCodes;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

public class MediationServiceImpl {
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public final p b;
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final g d;
    private final LinkedHashSet<String> e = new LinkedHashSet<>();
    private final Object f = new Object();

    private class a implements d, MaxAdViewAdListener, MaxRewardedAdListener {
        private final com.applovin.impl.mediation.a.a b;
        private final MaxAdListener c;

        private a(com.applovin.impl.mediation.a.a aVar, MaxAdListener maxAdListener) {
            this.b = aVar;
            this.c = maxAdListener;
        }

        public void a(MaxAd maxAd, int i, String str) {
            MediationServiceImpl.this.b(this.b, i, str, this.c);
        }

        public void a(String str, int i, String str2) {
            MediationServiceImpl.this.a(this.b, i, str2, this.c);
        }

        public void onAdClicked(MaxAd maxAd) {
            MediationServiceImpl.this.d(this.b);
            h.d(this.c, maxAd, MediationServiceImpl.this.a);
        }

        public void onAdCollapsed(MaxAd maxAd) {
            h.h(this.c, maxAd, MediationServiceImpl.this.a);
        }

        public void onAdDisplayFailed(MaxAd maxAd, int i) {
            MediationServiceImpl.this.b(this.b, i, "", this.c);
        }

        public void onAdDisplayed(MaxAd maxAd) {
            MediationServiceImpl.this.b.a("MediationService", "Scheduling impression for ad via callback...");
            MediationServiceImpl.this.maybeScheduleCallbackAdImpressionPostback(this.b);
            h.b(this.c, maxAd, MediationServiceImpl.this.a);
            if (maxAd.getFormat() == MaxAdFormat.INTERSTITIAL || maxAd.getFormat() == MaxAdFormat.REWARDED) {
                MediationServiceImpl.this.a.S().c();
            }
        }

        public void onAdExpanded(MaxAd maxAd) {
            h.g(this.c, maxAd, MediationServiceImpl.this.a);
        }

        public void onAdHidden(MaxAd maxAd) {
            h.c(this.c, maxAd, MediationServiceImpl.this.a);
            if (maxAd.getFormat() == MaxAdFormat.INTERSTITIAL || maxAd.getFormat() == MaxAdFormat.REWARDED) {
                MediationServiceImpl.this.a.S().d();
            }
        }

        public void onAdLoadFailed(String str, int i) {
            MediationServiceImpl.this.a(this.b, i, "", this.c);
        }

        public void onAdLoaded(MaxAd maxAd) {
            MediationServiceImpl.this.c(this.b);
            h.a(this.c, maxAd, MediationServiceImpl.this.a);
        }

        public void onRewardedVideoCompleted(MaxAd maxAd) {
            h.f(this.c, maxAd, MediationServiceImpl.this.a);
        }

        public void onRewardedVideoStarted(MaxAd maxAd) {
            h.e(this.c, maxAd, MediationServiceImpl.this.a);
        }

        public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
            h.a(this.c, maxAd, maxReward, MediationServiceImpl.this.a);
        }
    }

    public MediationServiceImpl(j jVar) {
        if (jVar != null) {
            this.a = jVar;
            this.b = jVar.v();
            this.d = new g(jVar);
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private MaxAdapterParametersImpl.a a(Context context) {
        return new MaxAdapterParametersImpl.a().b(AppLovinPrivacySettings.hasUserConsent(context)).a(AppLovinPrivacySettings.isAgeRestrictedUser(context));
    }

    private void a(int i, String str, com.applovin.impl.mediation.a.a aVar) {
        long e2 = aVar.e();
        p pVar = this.b;
        pVar.a("MediationService", "Firing ad load failure postback with load time: " + e2);
        HashMap hashMap = new HashMap(1);
        hashMap.put("{LOAD_TIME_MS}", String.valueOf(e2));
        a("mlerr", (Map<String, String>) hashMap, i, str, (e) aVar);
    }

    /* access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.a.a aVar, int i, String str, MaxAdListener maxAdListener) {
        a(i, str, aVar);
        destroyAd(aVar);
        h.a(maxAdListener, aVar.getAdUnitId(), i, this.a);
    }

    private void a(String str, int i, e eVar) {
        a(str, (Map<String, String>) Collections.EMPTY_MAP, i, (String) null, eVar);
    }

    private void a(String str, int i, String str2, e eVar) {
        a(str, (Map<String, String>) Collections.EMPTY_MAP, i, str2, eVar);
    }

    /* access modifiers changed from: private */
    public void a(String str, g gVar) {
        a("serr", (Map<String, String>) Collections.EMPTY_MAP, 0, str, (e) gVar);
    }

    private void a(String str, Map<String, String> map, int i, String str2, e eVar) {
        this.a.D().a((com.applovin.impl.sdk.d.a) new d(str, map, i, str2, eVar, this.a), q.a.MEDIATION_POSTBACKS);
    }

    private boolean a(e eVar) {
        boolean contains;
        synchronized (this.f) {
            contains = this.e.contains(eVar.t());
        }
        return contains;
    }

    private void b(int i, String str, com.applovin.impl.mediation.a.a aVar) {
        a("mierr", i, str, (e) aVar);
    }

    private void b(com.applovin.impl.mediation.a.a aVar) {
        p pVar = this.b;
        pVar.a("MediationService", "Firing ad preload postback for " + aVar.u());
        a("mpreload", 0, (e) aVar);
    }

    /* access modifiers changed from: private */
    public void b(com.applovin.impl.mediation.a.a aVar, int i, String str, MaxAdListener maxAdListener) {
        b(i, str, aVar);
        h.a(maxAdListener, (MaxAd) aVar, i, this.a);
    }

    /* access modifiers changed from: private */
    public void c(com.applovin.impl.mediation.a.a aVar) {
        long e2 = aVar.e();
        p pVar = this.b;
        pVar.a("MediationService", "Firing ad load success postback with load time: " + e2);
        String str = aVar.a() ? "boad" : "load";
        HashMap hashMap = new HashMap(1);
        hashMap.put("{LOAD_TIME_MS}", String.valueOf(e2));
        a(str, (Map<String, String>) hashMap, 0, (String) null, (e) aVar);
    }

    /* access modifiers changed from: private */
    public void d(com.applovin.impl.mediation.a.a aVar) {
        a("mclick", 0, (e) aVar);
    }

    /* access modifiers changed from: package-private */
    public void a(com.applovin.impl.mediation.a.a aVar) {
        p pVar = this.b;
        pVar.a("MediationService", "Firing backup ad used to display for " + aVar.u());
        a("bimp", 0, (e) aVar);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        synchronized (this.f) {
            this.e.add(str);
        }
    }

    public void collectSignal(final g gVar, Activity activity, final f.a aVar) {
        String str;
        p pVar;
        String str2;
        StringBuilder sb;
        String str3;
        if (gVar == null) {
            throw new IllegalArgumentException("No spec specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (aVar != null) {
            final h a2 = this.d.a((e) gVar);
            if (a2 != null) {
                MaxAdapterParametersImpl a3 = a(activity.getApplicationContext()).a((e) gVar, activity.getApplicationContext()).a();
                a2.a((MaxAdapterInitializationParameters) a3, activity);
                AnonymousClass3 r2 = new MaxSignalCollectionListener() {
                    public void onSignalCollected(String str) {
                        aVar.a(f.a(gVar, a2, str));
                    }

                    public void onSignalCollectionFailed(String str) {
                        MediationServiceImpl.this.a(str, gVar);
                        aVar.a(f.b(gVar, a2, str));
                    }
                };
                if (!gVar.b()) {
                    pVar = this.b;
                    str3 = "MediationService";
                    sb = new StringBuilder();
                    str2 = "Collecting signal for adapter: ";
                } else if (a((e) gVar)) {
                    pVar = this.b;
                    str3 = "MediationService";
                    sb = new StringBuilder();
                    str2 = "Collecting signal for now-initialized adapter: ";
                } else {
                    p pVar2 = this.b;
                    pVar2.d("MediationService", "Skip collecting signal for not-initialized adapter: " + a2.b());
                    str = "Adapter not initialized yet";
                }
                sb.append(str2);
                sb.append(a2.b());
                pVar.a(str3, sb.toString());
                a2.a(a3, gVar, activity, r2);
                return;
            }
            str = "Could not load adapter";
            aVar.a(f.a(gVar, str));
        } else {
            throw new IllegalArgumentException("No callback specified");
        }
    }

    public void destroyAd(MaxAd maxAd) {
        if (maxAd != null) {
            p pVar = this.b;
            pVar.b("MediationService", "Destroying " + maxAd);
            ArrayList<com.applovin.impl.mediation.a.a> arrayList = new ArrayList<>();
            if (maxAd instanceof e) {
                arrayList.addAll(((e) maxAd).b());
            } else if (maxAd instanceof com.applovin.impl.mediation.a.a) {
                arrayList.add((com.applovin.impl.mediation.a.a) maxAd);
            }
            for (com.applovin.impl.mediation.a.a aVar : arrayList) {
                h b2 = aVar.b();
                if (b2 != null) {
                    b2.g();
                    aVar.g();
                }
            }
        }
    }

    public Collection<String> getFailedAdapterClassnames() {
        return this.d.b();
    }

    public LinkedHashSet<String> getInitializedAdapterNames() {
        return this.e;
    }

    public Collection<String> getLoadedAdapterClassnames() {
        return this.d.a();
    }

    public void initializeAdapter(e eVar, Activity activity) {
        if (eVar == null) {
            throw new IllegalArgumentException("No spec specified");
        } else if (activity != null) {
            h a2 = this.d.a(eVar);
            if (a2 != null) {
                p pVar = this.b;
                pVar.b("MediationService", "Initializing adapter " + eVar);
                a2.a((MaxAdapterInitializationParameters) a(activity.getApplicationContext()).a(eVar, activity.getApplicationContext()).a(), activity);
            }
        } else {
            throw new IllegalArgumentException("No activity specified");
        }
    }

    public void loadAd(final String str, MaxAdFormat maxAdFormat, f fVar, Activity activity, MaxAdListener maxAdListener) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No ad unit ID specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (maxAdListener != null) {
            this.a.a();
            if (fVar == null) {
                fVar = new f.a().a();
            }
            final c cVar = new c(str, maxAdFormat, fVar, activity, this.a, maxAdListener);
            p pVar = this.b;
            pVar.b("MediationService", "Scheduling signal collection before fetching next ad for ad unit '" + str + "'");
            final q.a a2 = com.applovin.impl.mediation.c.c.a(maxAdFormat, this.a);
            this.a.D().a((com.applovin.impl.sdk.d.a) new b(activity, this.a, new b.a() {
                public void a(JSONArray jSONArray) {
                    cVar.a(jSONArray);
                    p a2 = MediationServiceImpl.this.b;
                    a2.b("MediationService", "Scheduling fetching next ad after signal collection for ad unit '" + str + "'");
                    if (((Boolean) MediationServiceImpl.this.a.a(com.applovin.impl.sdk.b.a.c)).booleanValue()) {
                        MediationServiceImpl.this.a.D().a((com.applovin.impl.sdk.d.a) cVar);
                    } else {
                        MediationServiceImpl.this.a.D().a((com.applovin.impl.sdk.d.a) cVar, a2);
                    }
                }
            }), a2);
        } else {
            throw new IllegalArgumentException("No listener specified");
        }
    }

    public void loadThirdPartyMediatedAd(String str, com.applovin.impl.mediation.a.a aVar, Activity activity, MaxAdListener maxAdListener) {
        if (aVar != null) {
            p pVar = this.b;
            pVar.a("MediationService", "Loading " + aVar + "...");
            b(aVar);
            h a2 = this.d.a((e) aVar);
            if (a2 != null) {
                MaxAdapterParametersImpl a3 = a(activity.getApplicationContext()).a(aVar, activity.getApplicationContext()).a();
                a2.a((MaxAdapterInitializationParameters) a3, activity);
                com.applovin.impl.mediation.a.a a4 = aVar.a(a2);
                a2.a(str, a4);
                a4.f();
                a2.a(str, a3, a4, activity, new a(a4, maxAdListener));
                return;
            }
            p pVar2 = this.b;
            pVar2.c("MediationService", "Failed to load " + aVar + ": adapter not loaded");
            a(aVar, (int) MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, "", maxAdListener);
            return;
        }
        throw new IllegalArgumentException("No mediated ad specified");
    }

    public void maybeInitialize(Activity activity) {
        if (this.c.compareAndSet(false, true)) {
            this.a.D().a((com.applovin.impl.sdk.d.a) new com.applovin.impl.mediation.b.a(activity, this.a), q.a.MEDIATION_MAIN);
        }
    }

    public void maybeScheduleBackupAdPromotedToPrimaryPostback(com.applovin.impl.mediation.a.a aVar) {
        long e2 = aVar.e();
        p pVar = this.b;
        pVar.a("MediationService", "Firing ad load success postback with load time: " + e2);
        HashMap hashMap = new HashMap(1);
        hashMap.put("{LOAD_TIME_MS}", String.valueOf(e2));
        a("load", (Map<String, String>) hashMap, 0, (String) null, (e) aVar);
    }

    public void maybeScheduleCallbackAdImpressionPostback(com.applovin.impl.mediation.a.a aVar) {
        a("mcimp", 0, (e) aVar);
    }

    public void maybeScheduleRawAdImpressionPostback(com.applovin.impl.mediation.a.a aVar) {
        a("mimp", 0, (e) aVar);
    }

    public void maybeScheduleViewabilityAdImpressionPostback(com.applovin.impl.mediation.a.b bVar, long j) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("{VIEWABILITY_FLAGS}", String.valueOf(j));
        hashMap.put("{USED_VIEWABILITY_TIMER}", String.valueOf(bVar.p()));
        a("mvimp", (Map<String, String>) hashMap, 0, (String) null, (e) bVar);
    }

    public void showFullscreenAd(MaxAd maxAd, final Activity activity) {
        if (maxAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (activity != null) {
            if (maxAd instanceof e) {
                maxAd = ((e) maxAd).a(activity);
            }
            if (maxAd instanceof com.applovin.impl.mediation.a.c) {
                this.a.S().a(true);
                final com.applovin.impl.mediation.a.c cVar = (com.applovin.impl.mediation.a.c) maxAd;
                final h b2 = cVar.b();
                if (b2 != null) {
                    long B = cVar.B();
                    p pVar = this.b;
                    pVar.b("MediationService", "Showing ad " + maxAd.getAdUnitId() + " with delay of " + B + "ms...");
                    AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
                        public void run() {
                            b2.a((com.applovin.impl.mediation.a.a) cVar, activity);
                            MediationServiceImpl.this.a.S().a(false);
                            MediationServiceImpl.this.b.a("MediationService", "Scheduling impression for ad manually...");
                            MediationServiceImpl.this.maybeScheduleRawAdImpressionPostback(cVar);
                        }
                    }, B);
                    return;
                }
                this.a.S().a(false);
                p pVar2 = this.b;
                pVar2.c("MediationService", "Failed to show " + maxAd + ": adapter not found");
                p pVar3 = this.b;
                pVar3.e("MediationService", "There may be an integration problem with the adapter for ad unit id '" + cVar.getAdUnitId() + "'. Please check if you have a supported version of that SDK integrated into your project.");
                throw new IllegalStateException("Could not find adapter for provided ad");
            }
            p pVar4 = this.b;
            pVar4.e("MediationService", "Unable to show ad for '" + maxAd.getAdUnitId() + "': only REWARDED or INTERSTITIAL ads are eligible for showFullscreenAd(). " + maxAd.getFormat() + " ad was provided.");
            throw new IllegalArgumentException("Provided ad is not a MediatedFullscreenAd");
        } else {
            throw new IllegalArgumentException("No activity specified");
        }
    }
}
