package okhttp3;

import java.util.concurrent.TimeUnit;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    String headerValue;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.headerValue = str;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static okhttp3.CacheControl parse(okhttp3.Headers r22) {
        /*
            r0 = r22
            int r1 = r22.size()
            r6 = 0
            r7 = 1
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = -1
            r13 = -1
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = -1
            r18 = -1
            r19 = 0
            r20 = 0
        L_0x0019:
            if (r6 >= r1) goto L_0x013e
            java.lang.String r9 = r0.name(r6)
            java.lang.String r2 = r0.value(r6)
            java.lang.String r4 = "Cache-Control"
            boolean r4 = r9.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0031
            if (r8 == 0) goto L_0x002f
        L_0x002d:
            r7 = 0
            goto L_0x003a
        L_0x002f:
            r8 = r2
            goto L_0x003a
        L_0x0031:
            java.lang.String r4 = "Pragma"
            boolean r4 = r9.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0137
            goto L_0x002d
        L_0x003a:
            r4 = 0
        L_0x003b:
            int r9 = r2.length()
            if (r4 >= r9) goto L_0x0137
            java.lang.String r9 = "=,;"
            int r9 = okhttp3.internal.http.HttpHeaders.skipUntil(r2, r4, r9)
            java.lang.String r4 = r2.substring(r4, r9)
            java.lang.String r4 = r4.trim()
            int r3 = r2.length()
            if (r9 == r3) goto L_0x009c
            char r3 = r2.charAt(r9)
            r5 = 44
            if (r3 == r5) goto L_0x009c
            char r3 = r2.charAt(r9)
            r5 = 59
            if (r3 != r5) goto L_0x0066
            goto L_0x009c
        L_0x0066:
            int r9 = r9 + 1
            int r3 = okhttp3.internal.http.HttpHeaders.skipWhitespace(r2, r9)
            int r5 = r2.length()
            if (r3 >= r5) goto L_0x008b
            char r5 = r2.charAt(r3)
            r9 = 34
            if (r5 != r9) goto L_0x008b
            int r3 = r3 + 1
            java.lang.String r5 = "\""
            int r5 = okhttp3.internal.http.HttpHeaders.skipUntil(r2, r3, r5)
            java.lang.String r3 = r2.substring(r3, r5)
            r21 = 1
            int r5 = r5 + 1
            goto L_0x00a2
        L_0x008b:
            r21 = 1
            java.lang.String r5 = ",;"
            int r5 = okhttp3.internal.http.HttpHeaders.skipUntil(r2, r3, r5)
            java.lang.String r3 = r2.substring(r3, r5)
            java.lang.String r3 = r3.trim()
            goto L_0x00a2
        L_0x009c:
            r21 = 1
            int r9 = r9 + 1
            r5 = r9
            r3 = 0
        L_0x00a2:
            java.lang.String r9 = "no-cache"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00ae
            r9 = -1
            r10 = 1
            goto L_0x0134
        L_0x00ae:
            java.lang.String r9 = "no-store"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00ba
            r9 = -1
            r11 = 1
            goto L_0x0134
        L_0x00ba:
            java.lang.String r9 = "max-age"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00ca
            r9 = -1
            int r3 = okhttp3.internal.http.HttpHeaders.parseSeconds(r3, r9)
            r12 = r3
            goto L_0x0134
        L_0x00ca:
            java.lang.String r9 = "s-maxage"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00d9
            r9 = -1
            int r3 = okhttp3.internal.http.HttpHeaders.parseSeconds(r3, r9)
            r13 = r3
            goto L_0x0134
        L_0x00d9:
            java.lang.String r9 = "private"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00e4
            r9 = -1
            r14 = 1
            goto L_0x0134
        L_0x00e4:
            java.lang.String r9 = "public"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00ef
            r9 = -1
            r15 = 1
            goto L_0x0134
        L_0x00ef:
            java.lang.String r9 = "must-revalidate"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00fb
            r9 = -1
            r16 = 1
            goto L_0x0134
        L_0x00fb:
            java.lang.String r9 = "max-stale"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x010e
            r4 = 2147483647(0x7fffffff, float:NaN)
            int r3 = okhttp3.internal.http.HttpHeaders.parseSeconds(r3, r4)
            r17 = r3
            r9 = -1
            goto L_0x0134
        L_0x010e:
            java.lang.String r9 = "min-fresh"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x011e
            r9 = -1
            int r3 = okhttp3.internal.http.HttpHeaders.parseSeconds(r3, r9)
            r18 = r3
            goto L_0x0134
        L_0x011e:
            r9 = -1
            java.lang.String r3 = "only-if-cached"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x012a
            r19 = 1
            goto L_0x0134
        L_0x012a:
            java.lang.String r3 = "no-transform"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x0134
            r20 = 1
        L_0x0134:
            r4 = r5
            goto L_0x003b
        L_0x0137:
            r9 = -1
            r21 = 1
            int r6 = r6 + 1
            goto L_0x0019
        L_0x013e:
            if (r7 != 0) goto L_0x0143
            r21 = 0
            goto L_0x0145
        L_0x0143:
            r21 = r8
        L_0x0145:
            okhttp3.CacheControl r0 = new okhttp3.CacheControl
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.CacheControl.parse(okhttp3.Headers):okhttp3.CacheControl");
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue2 = headerValue();
        this.headerValue = headerValue2;
        return headerValue2;
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds);
            sb.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds);
            sb.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public static final class Builder {
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean noCache;
        boolean noStore;
        boolean noTransform;
        boolean onlyIfCached;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder maxAge(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                this.maxAgeSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxAge < 0: " + i);
        }

        public Builder maxStale(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                this.maxStaleSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i);
        }

        public Builder minFresh(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                this.minFreshSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("minFresh < 0: " + i);
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }
    }
}
