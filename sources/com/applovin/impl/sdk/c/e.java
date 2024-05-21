package com.applovin.impl.sdk.c;

public final class e {
    private long a;
    private long b;
    private boolean c;
    private long d;
    private long e;

    public void a() {
        this.c = true;
    }

    public void a(long j) {
        this.a += j;
    }

    public void b(long j) {
        this.b += j;
    }

    public boolean b() {
        return this.c;
    }

    public long c() {
        return this.a;
    }

    public long d() {
        return this.b;
    }

    public void e() {
        this.d++;
    }

    public void f() {
        this.e++;
    }

    public long g() {
        return this.d;
    }

    public long h() {
        return this.e;
    }

    public String toString() {
        return "CacheStatsTracker{totalDownloadedBytes=" + this.a + ", totalCachedBytes=" + this.b + ", isHTMLCachingCancelled=" + this.c + ", htmlResourceCacheSuccessCount=" + this.d + ", htmlResourceCacheFailureCount=" + this.e + '}';
    }
}
