package com.applovin.impl.mediation.c;

import com.applovin.impl.mediation.e;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class c {
    private static final List<String> a = new ArrayList();

    public static class a {
        private final Set<String> a;
        private final Set<String> b;

        private a(Set<String> set, Set<String> set2) {
            this.a = set;
            this.b = set2;
        }

        public Set<String> a() {
            return this.a;
        }

        public Set<String> b() {
            return this.b;
        }
    }

    static {
        a.add("com.applovin.mediation.adapters.AdColonyMediationAdapter");
        a.add("com.applovin.mediation.adapters.AmazonMediationAdapter");
        a.add("com.applovin.mediation.adapters.AppLovinMediationAdapter");
        a.add("com.applovin.mediation.adapters.ChartboostMediationAdapter");
        a.add("com.applovin.mediation.adapters.DuAdMediationAdapter");
        a.add("com.applovin.mediation.adapters.FacebookMediationAdapter");
        a.add("com.applovin.mediation.adapters.FlurryMediationAdapter");
        a.add("com.applovin.mediation.adapters.GoogleMediationAdapter");
        a.add("com.applovin.mediation.adapters.InMobiMediationAdapter");
        a.add("com.applovin.mediation.adapters.InneractiveMediationAdapter");
        a.add("com.applovin.mediation.adapters.IronSourceMediationAdapter");
        a.add("com.applovin.mediation.adapters.MiaoMediationAdapter");
        a.add("com.applovin.mediation.adapters.MillennialMediaMediationAdapter");
        a.add("com.applovin.mediation.adapters.MintegralMediationAdapter");
        a.add("com.applovin.mediation.adapters.MoPubMediationAdapter");
        a.add("com.applovin.mediation.adapters.MyTargetMediationAdapter");
        a.add("com.applovin.mediation.adapters.NendMediationAdapter");
        a.add("com.applovin.mediation.adapters.OguryMediationAdapter");
        a.add("com.applovin.mediation.adapters.SmaatoMediationAdapter");
        a.add("com.applovin.mediation.adapters.TapjoyMediationAdapter");
        a.add("com.applovin.mediation.adapters.UnityAdsMediationAdapter");
        a.add("com.applovin.mediation.adapters.VungleMediationAdapter");
        a.add("com.applovin.mediation.adapters.YandexMediationAdapter");
    }

    public static a a() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(a.size());
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(a.size());
        for (String next : a) {
            try {
                Class.forName(next);
                linkedHashSet.add(next);
            } catch (Throwable unused) {
                linkedHashSet2.add(next);
            }
        }
        return new a(linkedHashSet, linkedHashSet2);
    }

    public static q.a a(MaxAdFormat maxAdFormat, q.a aVar, j jVar) {
        if (((Boolean) jVar.a(com.applovin.impl.sdk.b.a.N)).booleanValue()) {
            if (maxAdFormat == MaxAdFormat.BANNER || maxAdFormat == MaxAdFormat.LEADER) {
                return q.a.MEDIATION_BANNER;
            }
            if (maxAdFormat == MaxAdFormat.INTERSTITIAL) {
                return q.a.MEDIATION_INTERSTITIAL;
            }
            if (maxAdFormat == MaxAdFormat.REWARDED) {
                return q.a.MEDIATION_INCENTIVIZED;
            }
        }
        return aVar;
    }

    public static q.a a(MaxAdFormat maxAdFormat, j jVar) {
        return a(maxAdFormat, q.a.MEDIATION_MAIN, jVar);
    }

    public static MaxAd a(MaxAd maxAd) {
        return maxAd instanceof e ? ((e) maxAd).a() : maxAd;
    }

    public static String a(MaxAdFormat maxAdFormat) {
        return maxAdFormat.getLabel();
    }

    public static boolean a(MaxAdFormat maxAdFormat, MaxAdFormat maxAdFormat2) {
        return ((maxAdFormat == MaxAdFormat.BANNER || maxAdFormat == MaxAdFormat.MREC || maxAdFormat == MaxAdFormat.LEADER) && (maxAdFormat2 == MaxAdFormat.BANNER || maxAdFormat2 == MaxAdFormat.MREC || maxAdFormat2 == MaxAdFormat.LEADER)) || (maxAdFormat == MaxAdFormat.NATIVE && maxAdFormat2 == MaxAdFormat.NATIVE) || ((maxAdFormat == MaxAdFormat.INTERSTITIAL || maxAdFormat == MaxAdFormat.REWARDED) && (maxAdFormat2 == MaxAdFormat.INTERSTITIAL || maxAdFormat2 == MaxAdFormat.REWARDED));
    }
}
