package com.tapjoy;

import com.tapdaq.sdk.TapdaqError;
import com.tapjoy.internal.fn;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class TapjoyURLConnection {
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST = 1;

    public TapjoyHttpURLResponse getRedirectFromURL(String str) {
        return getResponseFromURL(str, "", 0, true, (Map) null, (String) null, (String) null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map) {
        return getResponseFromURL(str, TapjoyUtil.convertURLParams(map, false), 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, int i) {
        return getResponseFromURL(str, TapjoyUtil.convertURLParams(map, false), i);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str) {
        return getResponseFromURL(str, "", 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2) {
        return getResponseFromURL(str, str2, 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2, int i) {
        return getResponseFromURL(str, str2, i, false, (Map) null, (String) null, (String) null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, Map map2, Map map3) {
        return getResponseFromURL(str, map != null ? TapjoyUtil.convertURLParams(map, false) : "", 1, false, map2, "application/x-www-form-urlencoded", TapjoyUtil.convertURLParams(map3, false));
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, Map map2, String str2) {
        return getResponseFromURL(str, map != null ? TapjoyUtil.convertURLParams(map, false) : "", 1, false, map2, "application/json;charset=utf-8", str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x017a A[SYNTHETIC, Splitter:B:49:0x017a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.tapjoy.TapjoyHttpURLResponse getResponseFromURL(java.lang.String r7, java.lang.String r8, int r9, boolean r10, java.util.Map r11, java.lang.String r12, java.lang.String r13) {
        /*
            r6 = this;
            com.tapjoy.TapjoyHttpURLResponse r0 = new com.tapjoy.TapjoyHttpURLResponse
            r0.<init>()
            r1 = 10
            r2 = 0
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x015c }
            r4.<init>()     // Catch:{ Exception -> 0x015c }
            r4.append(r7)     // Catch:{ Exception -> 0x015c }
            r4.append(r8)     // Catch:{ Exception -> 0x015c }
            java.lang.String r7 = r4.toString()     // Catch:{ Exception -> 0x015c }
            java.lang.String r8 = "TapjoyURLConnection"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x015c }
            java.lang.String r5 = "http "
            r4.<init>(r5)     // Catch:{ Exception -> 0x015c }
            if (r9 != 0) goto L_0x0026
            java.lang.String r5 = "get"
            goto L_0x0028
        L_0x0026:
            java.lang.String r5 = "post"
        L_0x0028:
            r4.append(r5)     // Catch:{ Exception -> 0x015c }
            java.lang.String r5 = ": "
            r4.append(r5)     // Catch:{ Exception -> 0x015c }
            r4.append(r7)     // Catch:{ Exception -> 0x015c }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x015c }
            com.tapjoy.TapjoyLog.i(r8, r4)     // Catch:{ Exception -> 0x015c }
            java.net.URL r8 = new java.net.URL     // Catch:{ Exception -> 0x015c }
            r8.<init>(r7)     // Catch:{ Exception -> 0x015c }
            java.net.URLConnection r7 = com.tapjoy.internal.fn.a((java.net.URL) r8)     // Catch:{ Exception -> 0x015c }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x015c }
            if (r10 == 0) goto L_0x004e
            r7.setInstanceFollowRedirects(r2)     // Catch:{ Exception -> 0x004b }
            goto L_0x004e
        L_0x004b:
            r8 = move-exception
            goto L_0x015f
        L_0x004e:
            r8 = 15000(0x3a98, float:2.102E-41)
            r7.setConnectTimeout(r8)     // Catch:{ Exception -> 0x004b }
            r8 = 30000(0x7530, float:4.2039E-41)
            r7.setReadTimeout(r8)     // Catch:{ Exception -> 0x004b }
            if (r11 == 0) goto L_0x007e
            java.util.Set r8 = r11.entrySet()     // Catch:{ Exception -> 0x004b }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x004b }
        L_0x0062:
            boolean r11 = r8.hasNext()     // Catch:{ Exception -> 0x004b }
            if (r11 == 0) goto L_0x007e
            java.lang.Object r11 = r8.next()     // Catch:{ Exception -> 0x004b }
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11     // Catch:{ Exception -> 0x004b }
            java.lang.Object r4 = r11.getKey()     // Catch:{ Exception -> 0x004b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x004b }
            java.lang.Object r11 = r11.getValue()     // Catch:{ Exception -> 0x004b }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x004b }
            r7.setRequestProperty(r4, r11)     // Catch:{ Exception -> 0x004b }
            goto L_0x0062
        L_0x007e:
            r8 = 1
            if (r9 != r8) goto L_0x00cc
            java.lang.String r9 = "POST"
            r7.setRequestMethod(r9)     // Catch:{ Exception -> 0x004b }
            if (r13 == 0) goto L_0x00cc
            java.lang.String r9 = "TapjoyURLConnection"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004b }
            java.lang.String r4 = "Content-Type: "
            r11.<init>(r4)     // Catch:{ Exception -> 0x004b }
            r11.append(r12)     // Catch:{ Exception -> 0x004b }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x004b }
            com.tapjoy.TapjoyLog.i(r9, r11)     // Catch:{ Exception -> 0x004b }
            java.lang.String r9 = "TapjoyURLConnection"
            java.lang.String r11 = "Content:"
            com.tapjoy.TapjoyLog.i(r9, r11)     // Catch:{ Exception -> 0x004b }
            java.lang.String r9 = "TapjoyURLConnection"
            com.tapjoy.TapjoyLog.i(r9, r13)     // Catch:{ Exception -> 0x004b }
            java.lang.String r9 = "Content-Type"
            r7.setRequestProperty(r9, r12)     // Catch:{ Exception -> 0x004b }
            java.lang.String r9 = "Connection"
            java.lang.String r11 = "close"
            r7.setRequestProperty(r9, r11)     // Catch:{ Exception -> 0x004b }
            r7.setDoOutput(r8)     // Catch:{ Exception -> 0x004b }
            int r8 = r13.length()     // Catch:{ Exception -> 0x004b }
            r7.setFixedLengthStreamingMode(r8)     // Catch:{ Exception -> 0x004b }
            java.io.OutputStreamWriter r8 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x004b }
            java.io.OutputStream r9 = r7.getOutputStream()     // Catch:{ Exception -> 0x004b }
            r8.<init>(r9)     // Catch:{ Exception -> 0x004b }
            r8.write(r13)     // Catch:{ Exception -> 0x004b }
            r8.close()     // Catch:{ Exception -> 0x004b }
        L_0x00cc:
            r7.connect()     // Catch:{ Exception -> 0x004b }
            int r8 = r7.getResponseCode()     // Catch:{ Exception -> 0x004b }
            r0.statusCode = r8     // Catch:{ Exception -> 0x004b }
            java.util.Map r8 = r7.getHeaderFields()     // Catch:{ Exception -> 0x004b }
            r0.headerFields = r8     // Catch:{ Exception -> 0x004b }
            long r8 = r7.getDate()     // Catch:{ Exception -> 0x004b }
            r0.date = r8     // Catch:{ Exception -> 0x004b }
            long r8 = r7.getExpiration()     // Catch:{ Exception -> 0x004b }
            r0.expires = r8     // Catch:{ Exception -> 0x004b }
            if (r10 != 0) goto L_0x00f7
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x004b }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x004b }
            java.io.InputStream r9 = r7.getInputStream()     // Catch:{ Exception -> 0x004b }
            r8.<init>(r9)     // Catch:{ Exception -> 0x004b }
            r3.<init>(r8)     // Catch:{ Exception -> 0x004b }
        L_0x00f7:
            if (r10 != 0) goto L_0x011d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004b }
            r8.<init>()     // Catch:{ Exception -> 0x004b }
        L_0x00fe:
            java.lang.String r9 = r3.readLine()     // Catch:{ Exception -> 0x004b }
            if (r9 == 0) goto L_0x0117
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004b }
            r10.<init>()     // Catch:{ Exception -> 0x004b }
            r10.append(r9)     // Catch:{ Exception -> 0x004b }
            r10.append(r1)     // Catch:{ Exception -> 0x004b }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x004b }
            r8.append(r9)     // Catch:{ Exception -> 0x004b }
            goto L_0x00fe
        L_0x0117:
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x004b }
            r0.response = r8     // Catch:{ Exception -> 0x004b }
        L_0x011d:
            int r8 = r0.statusCode     // Catch:{ Exception -> 0x004b }
            r9 = 302(0x12e, float:4.23E-43)
            if (r8 != r9) goto L_0x012b
            java.lang.String r8 = "Location"
            java.lang.String r8 = r7.getHeaderField(r8)     // Catch:{ Exception -> 0x004b }
            r0.redirectURL = r8     // Catch:{ Exception -> 0x004b }
        L_0x012b:
            java.lang.String r8 = "content-length"
            java.lang.String r8 = r7.getHeaderField(r8)     // Catch:{ Exception -> 0x004b }
            if (r8 == 0) goto L_0x0156
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x013e }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x013e }
            r0.contentLength = r8     // Catch:{ Exception -> 0x013e }
            goto L_0x0156
        L_0x013e:
            r8 = move-exception
            java.lang.String r9 = "TapjoyURLConnection"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004b }
            java.lang.String r11 = "Exception: "
            r10.<init>(r11)     // Catch:{ Exception -> 0x004b }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x004b }
            r10.append(r8)     // Catch:{ Exception -> 0x004b }
            java.lang.String r8 = r10.toString()     // Catch:{ Exception -> 0x004b }
            com.tapjoy.TapjoyLog.e((java.lang.String) r9, (java.lang.String) r8)     // Catch:{ Exception -> 0x004b }
        L_0x0156:
            if (r3 == 0) goto L_0x01c9
            r3.close()     // Catch:{ Exception -> 0x004b }
            goto L_0x01c9
        L_0x015c:
            r7 = move-exception
            r8 = r7
            r7 = r3
        L_0x015f:
            java.lang.String r9 = "TapjoyURLConnection"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Exception: "
            r10.<init>(r11)
            java.lang.String r8 = r8.toString()
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            com.tapjoy.TapjoyLog.e((java.lang.String) r9, (java.lang.String) r8)
            r0.statusCode = r2
            if (r7 == 0) goto L_0x01c9
            java.lang.String r8 = r0.response     // Catch:{ Exception -> 0x01b1 }
            if (r8 != 0) goto L_0x01c9
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ Exception -> 0x01b1 }
            java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x01b1 }
            java.io.InputStream r7 = r7.getErrorStream()     // Catch:{ Exception -> 0x01b1 }
            r9.<init>(r7)     // Catch:{ Exception -> 0x01b1 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x01b1 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b1 }
            r7.<init>()     // Catch:{ Exception -> 0x01b1 }
        L_0x0191:
            java.lang.String r9 = r8.readLine()     // Catch:{ Exception -> 0x01b1 }
            if (r9 == 0) goto L_0x01aa
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b1 }
            r10.<init>()     // Catch:{ Exception -> 0x01b1 }
            r10.append(r9)     // Catch:{ Exception -> 0x01b1 }
            r10.append(r1)     // Catch:{ Exception -> 0x01b1 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x01b1 }
            r7.append(r9)     // Catch:{ Exception -> 0x01b1 }
            goto L_0x0191
        L_0x01aa:
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x01b1 }
            r0.response = r7     // Catch:{ Exception -> 0x01b1 }
            goto L_0x01c9
        L_0x01b1:
            r7 = move-exception
            java.lang.String r8 = "TapjoyURLConnection"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Exception trying to get error code/content: "
            r9.<init>(r10)
            java.lang.String r7 = r7.toString()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            com.tapjoy.TapjoyLog.e((java.lang.String) r8, (java.lang.String) r7)
        L_0x01c9:
            java.lang.String r7 = "TapjoyURLConnection"
            java.lang.String r8 = "--------------------"
            com.tapjoy.TapjoyLog.i(r7, r8)
            java.lang.String r7 = "TapjoyURLConnection"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "response status: "
            r8.<init>(r9)
            int r9 = r0.statusCode
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.tapjoy.TapjoyLog.i(r7, r8)
            java.lang.String r7 = "TapjoyURLConnection"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "response size: "
            r8.<init>(r9)
            int r9 = r0.contentLength
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.tapjoy.TapjoyLog.i(r7, r8)
            java.lang.String r7 = r0.redirectURL
            if (r7 == 0) goto L_0x021b
            java.lang.String r7 = r0.redirectURL
            int r7 = r7.length()
            if (r7 <= 0) goto L_0x021b
            java.lang.String r7 = "TapjoyURLConnection"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "redirectURL: "
            r8.<init>(r9)
            java.lang.String r9 = r0.redirectURL
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.tapjoy.TapjoyLog.i(r7, r8)
        L_0x021b:
            java.lang.String r7 = "TapjoyURLConnection"
            java.lang.String r8 = "--------------------"
            com.tapjoy.TapjoyLog.i(r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyURLConnection.getResponseFromURL(java.lang.String, java.lang.String, int, boolean, java.util.Map, java.lang.String, java.lang.String):com.tapjoy.TapjoyHttpURLResponse");
    }

    public String getContentLength(String str) {
        String str2;
        try {
            String replaceAll = str.replaceAll(" ", "%20");
            TapjoyLog.d("TapjoyURLConnection", "requestURL: " + replaceAll);
            HttpURLConnection httpURLConnection = (HttpURLConnection) fn.a(new URL(replaceAll));
            httpURLConnection.setConnectTimeout(TapdaqError.HYPRMX_FAILED_TO_LOAD_AD);
            httpURLConnection.setReadTimeout(30000);
            str2 = httpURLConnection.getHeaderField("content-length");
        } catch (Exception e) {
            TapjoyLog.e("TapjoyURLConnection", "Exception: " + e.toString());
            str2 = null;
        }
        TapjoyLog.d("TapjoyURLConnection", "content-length: " + str2);
        return str2;
    }
}
