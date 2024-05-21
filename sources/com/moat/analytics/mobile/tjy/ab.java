package com.moat.analytics.mobile.tjy;

import java.io.InputStream;
import java.io.InputStreamReader;

enum ab implements aa {
    instance;

    private String a(InputStream inputStream) {
        char[] cArr = new char[256];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int read = inputStreamReader.read(cArr, 0, 256);
            if (read <= 0) {
                break;
            }
            i += read;
            sb.append(cArr, 0, read);
        } while (i < 1024);
        return sb.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:17|16|19|20|(2:22|23)|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004e */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0054 A[SYNTHETIC, Splitter:B:22:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A[SYNTHETIC, Splitter:B:27:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.moat.analytics.mobile.tjy.base.functional.a a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x004e }
            r1.<init>(r5)     // Catch:{ IOException -> 0x004e }
            java.net.URLConnection r5 = r1.openConnection()     // Catch:{ IOException -> 0x004e }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x004e }
            r1 = 0
            r5.setUseCaches(r1)     // Catch:{ IOException -> 0x004e }
            r1 = 10000(0x2710, float:1.4013E-41)
            r5.setReadTimeout(r1)     // Catch:{ IOException -> 0x004e }
            r1 = 15000(0x3a98, float:2.102E-41)
            r5.setConnectTimeout(r1)     // Catch:{ IOException -> 0x004e }
            java.lang.String r1 = "GET"
            r5.setRequestMethod(r1)     // Catch:{ IOException -> 0x004e }
            r1 = 1
            r5.setDoInput(r1)     // Catch:{ IOException -> 0x004e }
            r5.connect()     // Catch:{ IOException -> 0x004e }
            int r1 = r5.getResponseCode()     // Catch:{ IOException -> 0x004e }
            r2 = 400(0x190, float:5.6E-43)
            if (r1 < r2) goto L_0x0033
            com.moat.analytics.mobile.tjy.base.functional.a r5 = com.moat.analytics.mobile.tjy.base.functional.a.a()     // Catch:{ IOException -> 0x004e }
            return r5
        L_0x0033:
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ IOException -> 0x004e }
            java.lang.String r0 = r4.a((java.io.InputStream) r5)     // Catch:{ IOException -> 0x004a, all -> 0x0045 }
            com.moat.analytics.mobile.tjy.base.functional.a r0 = com.moat.analytics.mobile.tjy.base.functional.a.a(r0)     // Catch:{ IOException -> 0x004a, all -> 0x0045 }
            if (r5 == 0) goto L_0x0044
            r5.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            return r0
        L_0x0045:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x0058
        L_0x004a:
            r0 = r5
            goto L_0x004e
        L_0x004c:
            r5 = move-exception
            goto L_0x0058
        L_0x004e:
            com.moat.analytics.mobile.tjy.base.functional.a r5 = com.moat.analytics.mobile.tjy.base.functional.a.a()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            return r5
        L_0x0058:
            if (r0 == 0) goto L_0x005d
            r0.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.tjy.ab.a(java.lang.String):com.moat.analytics.mobile.tjy.base.functional.a");
    }
}
