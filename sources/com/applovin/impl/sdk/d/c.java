package com.applovin.impl.sdk.d;

import android.net.Uri;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.d;
import com.applovin.impl.sdk.c.e;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.network.a;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.tapdaq.sdk.TapdaqError;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

abstract class c extends a {
    protected final g a;
    private AppLovinAdLoadListener c;
    private final n d;
    private final Collection<Character> e;
    private final e f;

    c(String str, g gVar, j jVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        super(str, jVar);
        if (gVar != null) {
            this.a = gVar;
            this.c = appLovinAdLoadListener;
            this.d = jVar.O();
            this.e = h();
            this.f = new e();
            return;
        }
        throw new IllegalArgumentException("No ad specified.");
    }

    private Uri a(Uri uri, String str) {
        String str2;
        StringBuilder sb;
        if (uri != null) {
            String uri2 = uri.toString();
            if (k.b(uri2)) {
                a("Caching " + str + " image...");
                return f(uri2);
            }
            sb = new StringBuilder();
            sb.append("Failed to cache ");
            sb.append(str);
            str2 = " image";
        } else {
            sb = new StringBuilder();
            sb.append("No ");
            sb.append(str);
            str2 = " image to cache";
        }
        sb.append(str2);
        a(sb.toString());
        return null;
    }

    private String a(String str, String str2) {
        StringBuilder sb;
        String replace = str2.replace("/", "_");
        String H = this.a.H();
        if (k.b(H)) {
            replace = H + replace;
        }
        File a2 = this.d.a(replace, this.b.x(), true);
        if (a2 == null) {
            return null;
        }
        if (a2.exists()) {
            this.f.b(a2.length());
            sb = new StringBuilder();
        } else {
            if (!this.d.a(a2, str + str2, Arrays.asList(new String[]{str}), this.f)) {
                return null;
            }
            sb = new StringBuilder();
        }
        sb.append("file://");
        sb.append(a2.getAbsolutePath());
        return sb.toString();
    }

    private Collection<Character> h() {
        HashSet hashSet = new HashSet();
        for (char valueOf : ((String) this.b.a(b.bv)).toCharArray()) {
            hashSet.add(Character.valueOf(valueOf));
        }
        hashSet.add('\"');
        return hashSet;
    }

    /* access modifiers changed from: package-private */
    public Uri a(String str, List<String> list, boolean z) {
        String str2;
        try {
            if (k.b(str)) {
                a("Caching video " + str + "...");
                String a2 = this.d.a(d(), str, this.a.H(), list, z, this.f);
                if (k.b(a2)) {
                    File a3 = this.d.a(a2, d(), false);
                    if (a3 != null) {
                        Uri fromFile = Uri.fromFile(a3);
                        if (fromFile != null) {
                            a("Finish caching video for ad #" + this.a.getAdIdNumber() + ". Updating ad with cachedVideoFilename = " + a2);
                            return fromFile;
                        }
                        str2 = "Unable to create URI from cached video file = " + a3;
                    } else {
                        str2 = "Unable to cache video = " + str + "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!";
                    }
                } else if (((Boolean) this.b.a(b.bA)).booleanValue()) {
                    d(TapdaqError.FAILED_TO_CACHE_VIDEO_MESSAGE);
                    com.applovin.impl.sdk.e.n.a(this.c, this.a.getAdZone(), AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES, this.b);
                    this.c = null;
                } else {
                    str2 = "Failed to cache video, but not failing ad load";
                }
                d(str2);
            }
        } catch (Exception e2) {
            a("Encountered exception while attempting to cache video.", e2);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String a(String str, List<String> list) {
        return c(str, list, true);
    }

    /* access modifiers changed from: package-private */
    public String a(String str, List<String> list, g gVar) {
        if (!k.b(str)) {
            return str;
        }
        if (!((Boolean) this.b.a(b.bz)).booleanValue()) {
            a("Resource caching is disabled, skipping cache...");
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        boolean shouldCancelHtmlCachingIfShown = gVar.shouldCancelHtmlCachingIfShown();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            int i = 0;
            int i2 = 0;
            while (i < sb.length() && (i = sb.indexOf(next, i2)) != -1) {
                int length = sb.length();
                int i3 = i;
                while (!this.e.contains(Character.valueOf(sb.charAt(i3))) && i3 < length) {
                    i3++;
                }
                if (i3 <= i || i3 == length) {
                    d("Unable to cache resource; ad HTML is invalid.");
                    return str;
                }
                String substring = sb.substring(next.length() + i, i3);
                if (!k.b(substring)) {
                    a("Skip caching of non-resource " + substring);
                } else if (!shouldCancelHtmlCachingIfShown || !gVar.hasShown()) {
                    String a2 = a(next, substring);
                    if (a2 != null) {
                        sb.replace(i, i3, a2);
                        this.f.e();
                    } else {
                        this.f.f();
                    }
                } else {
                    a("Cancelling HTML caching due to ad being shown already");
                    this.f.a();
                    return str;
                }
                i2 = i3;
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void a(AppLovinAdBase appLovinAdBase) {
        d.a(this.f, appLovinAdBase, this.b);
    }

    /* access modifiers changed from: package-private */
    public Uri b(String str, List<String> list, boolean z) {
        String str2;
        try {
            String a2 = this.d.a(d(), str, this.a.H(), list, z, this.f);
            if (!k.b(a2)) {
                return null;
            }
            File a3 = this.d.a(a2, d(), false);
            if (a3 != null) {
                Uri fromFile = Uri.fromFile(a3);
                if (fromFile != null) {
                    return fromFile;
                }
                str2 = "Unable to extract Uri from image file";
            } else {
                str2 = "Unable to retrieve File from cached image filename = " + a2;
            }
            d(str2);
            return null;
        } catch (MalformedURLException e2) {
            a("Failed to cache image at url = " + str, e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String c(String str, List<String> list, boolean z) {
        if (k.b(str)) {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                a("Nothing to cache, skipping...");
                return null;
            }
            String lastPathSegment = parse.getLastPathSegment();
            if (k.b(this.a.H())) {
                lastPathSegment = this.a.H() + lastPathSegment;
            }
            File a2 = this.d.a(lastPathSegment, d(), true);
            ByteArrayOutputStream a3 = (a2 == null || !a2.exists()) ? null : this.d.a(a2);
            if (a3 == null) {
                a3 = this.d.a(str, list, z);
                if (a3 != null) {
                    this.d.a(a3, a2);
                    this.f.a((long) a3.size());
                }
            } else {
                this.f.b((long) a3.size());
            }
            try {
                return a3.toString("UTF-8");
            } catch (UnsupportedEncodingException e2) {
                a("UTF-8 encoding not supported.", e2);
            } catch (Throwable th) {
                a("String resource at " + str + " failed to load.", th);
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Uri e(String str) {
        return a(str, this.a.G(), true);
    }

    /* access modifiers changed from: package-private */
    public Uri f(String str) {
        return b(str, this.a.G(), true);
    }

    /* access modifiers changed from: package-private */
    public void f() {
        a("Caching mute images...");
        Uri a2 = a(this.a.ax(), "mute");
        if (a2 != null) {
            this.a.b(a2);
        }
        Uri a3 = a(this.a.ay(), "unmute");
        if (a3 != null) {
            this.a.c(a3);
        }
        a("Ad updated with muteImageFilename = " + this.a.ax() + ", unmuteImageFilename = " + this.a.ay());
    }

    /* access modifiers changed from: package-private */
    public String g(final String str) {
        if (!k.b(str)) {
            return null;
        }
        com.applovin.impl.sdk.network.b a2 = com.applovin.impl.sdk.network.b.a(this.b).a(str).b("GET").a("").a(0).a();
        final AtomicReference atomicReference = new AtomicReference((Object) null);
        this.b.C().a(a2, new a.C0005a(), new a.b<String>() {
            public void a(int i) {
                c cVar = c.this;
                cVar.d("Failed to load resource from '" + str + "'");
            }

            public void a(String str, int i) {
                atomicReference.set(str);
            }
        });
        String str2 = (String) atomicReference.get();
        if (str2 != null) {
            this.f.a((long) str2.length());
        }
        return str2;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (this.c != null) {
            a("Rendered new ad:" + this.a);
            this.c.adReceived(this.a);
            this.c = null;
        }
    }
}
