package com.applovin.impl.sdk.ad;

import android.content.Context;
import android.net.Uri;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.e;
import com.applovin.impl.sdk.network.f;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NativeAdImpl implements j, AppLovinNativeAd {
    public static final String QUERY_PARAM_IS_FIRST_PLAY = "fp";
    public static final String QUERY_PARAM_VIDEO_PERCENT_VIEWED = "pv";
    private final j a;
    private final d b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final List<com.applovin.impl.sdk.c.a> p;
    private final List<com.applovin.impl.sdk.c.a> q;
    private final String r;
    private final long s;
    private final List<String> t;
    private String u;
    private String v;
    private float w;
    private String x;
    private AtomicBoolean y;

    public static class a {
        private d a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private float l;
        private String m;
        private String n;
        private String o;
        private String p;
        private String q;
        private List<com.applovin.impl.sdk.c.a> r;
        private List<com.applovin.impl.sdk.c.a> s;
        private String t;
        private String u;
        private long v;
        private List<String> w;
        private j x;

        public a a(float f2) {
            this.l = f2;
            return this;
        }

        public a a(long j2) {
            this.v = j2;
            return this;
        }

        public a a(d dVar) {
            this.a = dVar;
            return this;
        }

        public a a(j jVar) {
            this.x = jVar;
            return this;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a a(List<com.applovin.impl.sdk.c.a> list) {
            this.r = list;
            return this;
        }

        public NativeAdImpl a() {
            return new NativeAdImpl(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x);
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public a b(List<com.applovin.impl.sdk.c.a> list) {
            this.s = list;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a c(List<String> list) {
            this.w = list;
            return this;
        }

        public a d(String str) {
            this.f = str;
            return this;
        }

        public a e(String str) {
            this.b = str;
            return this;
        }

        public a f(String str) {
            this.g = str;
            return this;
        }

        public a g(String str) {
            this.h = str;
            return this;
        }

        public a h(String str) {
            this.i = str;
            return this;
        }

        public a i(String str) {
            this.j = str;
            return this;
        }

        public a j(String str) {
            this.k = str;
            return this;
        }

        public a k(String str) {
            this.m = str;
            return this;
        }

        public a l(String str) {
            this.n = str;
            return this;
        }

        public a m(String str) {
            this.o = str;
            return this;
        }

        public a n(String str) {
            this.p = str;
            return this;
        }

        public a o(String str) {
            this.q = str;
            return this;
        }

        public a p(String str) {
            this.t = str;
            return this;
        }

        public a q(String str) {
            this.u = str;
            return this;
        }
    }

    private NativeAdImpl(d dVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, float f2, String str11, String str12, String str13, String str14, String str15, List<com.applovin.impl.sdk.c.a> list, List<com.applovin.impl.sdk.c.a> list2, String str16, String str17, long j2, List<String> list3, j jVar) {
        this.y = new AtomicBoolean();
        this.b = dVar;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.u = str9;
        this.v = str10;
        this.w = f2;
        this.x = str11;
        this.l = str12;
        this.m = str13;
        this.n = str14;
        this.o = str15;
        this.p = list;
        this.q = list2;
        this.r = str16;
        this.k = str17;
        this.s = j2;
        this.t = list3;
        this.a = jVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NativeAdImpl nativeAdImpl = (NativeAdImpl) obj;
        if (this.b == null ? nativeAdImpl.b != null : !this.b.equals(nativeAdImpl.b)) {
            return false;
        }
        if (this.j == null ? nativeAdImpl.j != null : !this.j.equals(nativeAdImpl.j)) {
            return false;
        }
        if (this.r == null ? nativeAdImpl.r != null : !this.r.equals(nativeAdImpl.r)) {
            return false;
        }
        if (this.l == null ? nativeAdImpl.l != null : !this.l.equals(nativeAdImpl.l)) {
            return false;
        }
        if (this.k == null ? nativeAdImpl.k != null : !this.k.equals(nativeAdImpl.k)) {
            return false;
        }
        if (this.i == null ? nativeAdImpl.i != null : !this.i.equals(nativeAdImpl.i)) {
            return false;
        }
        if (this.m == null ? nativeAdImpl.m != null : !this.m.equals(nativeAdImpl.m)) {
            return false;
        }
        if (this.d == null ? nativeAdImpl.d != null : !this.d.equals(nativeAdImpl.d)) {
            return false;
        }
        if (this.e == null ? nativeAdImpl.e != null : !this.e.equals(nativeAdImpl.e)) {
            return false;
        }
        if (this.f == null ? nativeAdImpl.f != null : !this.f.equals(nativeAdImpl.f)) {
            return false;
        }
        if (this.g == null ? nativeAdImpl.g != null : !this.g.equals(nativeAdImpl.g)) {
            return false;
        }
        if (this.h == null ? nativeAdImpl.h != null : !this.h.equals(nativeAdImpl.h)) {
            return false;
        }
        if (this.o == null ? nativeAdImpl.o != null : !this.o.equals(nativeAdImpl.o)) {
            return false;
        }
        if (this.n == null ? nativeAdImpl.n != null : !this.n.equals(nativeAdImpl.n)) {
            return false;
        }
        if (this.p == null ? nativeAdImpl.p != null : !this.p.equals(nativeAdImpl.p)) {
            return false;
        }
        if (this.q == null ? nativeAdImpl.q == null : this.q.equals(nativeAdImpl.q)) {
            return this.t == null ? nativeAdImpl.t == null : this.t.equals(nativeAdImpl.t);
        }
        return false;
    }

    public long getAdId() {
        return this.s;
    }

    public d getAdZone() {
        return this.b;
    }

    public String getCaptionText() {
        return this.j;
    }

    public String getClCode() {
        return this.r;
    }

    public String getClickUrl() {
        return this.l;
    }

    public String getCtaText() {
        return this.k;
    }

    public String getDescriptionText() {
        return this.i;
    }

    public String getIconUrl() {
        return this.u;
    }

    public String getImageUrl() {
        return this.v;
    }

    public String getImpressionTrackingUrl() {
        return this.m;
    }

    public List<String> getResourcePrefixes() {
        return this.t;
    }

    public String getSourceIconUrl() {
        return this.d;
    }

    public String getSourceImageUrl() {
        return this.e;
    }

    public String getSourceStarRatingImageUrl() {
        return this.f;
    }

    public String getSourceVideoUrl() {
        return this.g;
    }

    public float getStarRating() {
        return this.w;
    }

    public String getTitle() {
        return this.h;
    }

    public String getVideoEndTrackingUrl(int i2, boolean z) {
        Uri build;
        if (this.o == null) {
            build = Uri.EMPTY;
        } else {
            if (i2 < 0 || i2 > 100) {
                this.a.v().c("AppLovinNativeAd", "Invalid percent viewed supplied.", new IllegalArgumentException("Percent viewed must be an integer between 0 and 100."));
            }
            build = Uri.parse(this.o).buildUpon().appendQueryParameter(QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i2)).appendQueryParameter(QUERY_PARAM_IS_FIRST_PLAY, Boolean.toString(z)).build();
        }
        return build.toString();
    }

    public String getVideoStartTrackingUrl() {
        return this.n;
    }

    public String getVideoUrl() {
        return this.x;
    }

    public String getZoneId() {
        return this.c;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = (((((((((((((((((((((((((((((((this.d != null ? this.d.hashCode() : 0) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31) + (this.f != null ? this.f.hashCode() : 0)) * 31) + (this.g != null ? this.g.hashCode() : 0)) * 31) + (this.h != null ? this.h.hashCode() : 0)) * 31) + (this.i != null ? this.i.hashCode() : 0)) * 31) + (this.j != null ? this.j.hashCode() : 0)) * 31) + (this.k != null ? this.k.hashCode() : 0)) * 31) + (this.l != null ? this.l.hashCode() : 0)) * 31) + (this.m != null ? this.m.hashCode() : 0)) * 31) + (this.n != null ? this.n.hashCode() : 0)) * 31) + (this.o != null ? this.o.hashCode() : 0)) * 31) + (this.p != null ? this.p.hashCode() : 0)) * 31) + (this.q != null ? this.q.hashCode() : 0)) * 31) + (this.r != null ? this.r.hashCode() : 0)) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31;
        if (this.t != null) {
            i2 = this.t.hashCode();
        }
        return hashCode + i2;
    }

    public boolean isImagePrecached() {
        return (this.u != null && !this.u.equals(this.d)) && (this.v != null && !this.v.equals(this.e));
    }

    public boolean isVideoPrecached() {
        return this.x != null && !this.x.equals(this.g);
    }

    public void launchClickTarget(Context context) {
        for (com.applovin.impl.sdk.c.a next : this.q) {
            this.a.G().a(e.j().a(next.a()).b(next.b()).a(false).a());
        }
        n.a(context, Uri.parse(this.l), this.a);
    }

    public void setIconUrl(String str) {
        this.u = str;
    }

    public void setImageUrl(String str) {
        this.v = str;
    }

    public void setStarRating(float f2) {
        this.w = f2;
    }

    public void setVideoUrl(String str) {
        this.x = str;
    }

    public String toString() {
        return "AppLovinNativeAd{clCode='" + this.r + '\'' + ", adZone='" + this.b + '\'' + ", sourceIconUrl='" + this.d + '\'' + ", sourceImageUrl='" + this.e + '\'' + ", sourceStarRatingImageUrl='" + this.f + '\'' + ", sourceVideoUrl='" + this.g + '\'' + ", title='" + this.h + '\'' + ", descriptionText='" + this.i + '\'' + ", captionText='" + this.j + '\'' + ", ctaText='" + this.k + '\'' + ", iconUrl='" + this.u + '\'' + ", imageUrl='" + this.v + '\'' + ", starRating='" + this.w + '\'' + ", videoUrl='" + this.x + '\'' + ", clickUrl='" + this.l + '\'' + ", impressionTrackingUrl='" + this.m + '\'' + ", videoStartTrackingUrl='" + this.n + '\'' + ", videoEndTrackingUrl='" + this.o + '\'' + ", impressionPostbacks=" + this.p + '\'' + ", clickTrackingPostbacks=" + this.q + '\'' + ", resourcePrefixes=" + this.t + '}';
    }

    public void trackImpression() {
        trackImpression((AppLovinPostbackListener) null);
    }

    public void trackImpression(AppLovinPostbackListener appLovinPostbackListener) {
        if (!this.y.getAndSet(true)) {
            this.a.v().a("AppLovinNativeAd", "Tracking impression...");
            for (com.applovin.impl.sdk.c.a next : this.p) {
                this.a.K().dispatchPostbackRequest(f.b(this.a).a(next.a()).c(next.b()).a(false).a(), appLovinPostbackListener);
            }
        } else if (appLovinPostbackListener != null) {
            appLovinPostbackListener.onPostbackFailure(this.m, AppLovinErrorCodes.NATIVE_AD_IMPRESSION_ALREADY_TRACKED);
        }
    }
}
