package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.c.h;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinErrorCodes;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import org.json.JSONException;

public class a {
    private final j a;
    private final p b;

    /* renamed from: com.applovin.impl.sdk.network.a$a  reason: collision with other inner class name */
    public static class C0005a {
        private long a;
        private long b;

        /* access modifiers changed from: private */
        public void a(long j) {
            this.a = j;
        }

        /* access modifiers changed from: private */
        public void b(long j) {
            this.b = j;
        }

        public long a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }
    }

    public interface b<T> {
        void a(int i);

        void a(T t, int i);
    }

    public a(j jVar) {
        this.a = jVar;
        this.b = jVar.v();
    }

    private int a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return AppLovinErrorCodes.NO_NETWORK;
        }
        if (th instanceof SocketTimeoutException) {
            return -102;
        }
        if (th instanceof IOException) {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : 401;
        } else if (th instanceof JSONException) {
            return FetchConst.ERROR_CONNECTION_TIMEOUT;
        } else {
            return -1;
        }
    }

    private HttpURLConnection a(String str, String str2, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dG)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dH)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private void a(int i, String str) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.af)).booleanValue()) {
            try {
                c.a(i, str, this.a.x());
            } catch (Throwable th) {
                p v = this.a.v();
                v.b("ConnectionManager", "Failed to track response code for URL: " + str, th);
            }
        }
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    private void a(String str) {
        h hVar;
        g gVar;
        if (k.a(str, f.e(this.a)) || k.a(str, f.f(this.a))) {
            hVar = this.a.E();
            gVar = g.h;
        } else if (k.a(str, com.applovin.impl.mediation.c.b.a(this.a)) || k.a(str, com.applovin.impl.mediation.c.b.b(this.a))) {
            hVar = this.a.E();
            gVar = g.r;
        } else {
            hVar = this.a.E();
            gVar = g.i;
        }
        hVar.a(gVar);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: java.lang.String} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=T, code=org.json.JSONObject, for r8v0, types: [T, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> void a(java.lang.String r5, int r6, java.lang.String r7, org.json.JSONObject r8, boolean r9, com.applovin.impl.sdk.network.a.b<T> r10) throws org.json.JSONException {
        /*
            r4 = this;
            com.applovin.impl.sdk.p r0 = r4.b
            java.lang.String r1 = "ConnectionManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r3 = " received from \""
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = "\": "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r0.a(r1, r2)
            r0 = 200(0xc8, float:2.8E-43)
            if (r6 < r0) goto L_0x00c0
            r0 = 300(0x12c, float:4.2E-43)
            if (r6 >= r0) goto L_0x00c0
            if (r9 == 0) goto L_0x0037
            com.applovin.impl.sdk.j r9 = r4.a
            java.lang.String r9 = r9.t()
            java.lang.String r5 = com.applovin.impl.sdk.e.j.a((java.lang.String) r5, (java.lang.String) r9)
        L_0x0037:
            if (r5 == 0) goto L_0x0042
            int r9 = r5.length()
            r0 = 2
            if (r9 <= r0) goto L_0x0042
            r9 = 1
            goto L_0x0043
        L_0x0042:
            r9 = 0
        L_0x0043:
            r0 = 204(0xcc, float:2.86E-43)
            if (r6 == r0) goto L_0x00bc
            if (r9 == 0) goto L_0x00bc
            boolean r9 = r8 instanceof java.lang.String     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            if (r9 == 0) goto L_0x004f
        L_0x004d:
            r8 = r5
            goto L_0x00bc
        L_0x004f:
            boolean r9 = r8 instanceof com.applovin.impl.sdk.e.o     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            if (r9 == 0) goto L_0x005a
            com.applovin.impl.sdk.j r9 = r4.a     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            com.applovin.impl.sdk.e.o r5 = com.applovin.impl.sdk.e.p.a((java.lang.String) r5, (com.applovin.impl.sdk.j) r9)     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            goto L_0x004d
        L_0x005a:
            boolean r9 = r8 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            if (r9 == 0) goto L_0x0065
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            r9.<init>(r5)     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            r8 = r9
            goto L_0x00bc
        L_0x0065:
            com.applovin.impl.sdk.p r5 = r4.b     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            java.lang.String r9 = "ConnectionManager"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            r0.<init>()     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            java.lang.String r1 = "Unable to handle '"
            r0.append(r1)     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            java.lang.Class r1 = r8.getClass()     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            java.lang.String r1 = r1.getName()     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            r0.append(r1)     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            java.lang.String r1 = "'"
            r0.append(r1)     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            r5.d(r9, r0)     // Catch:{ JSONException -> 0x009b, SAXException -> 0x008b }
            goto L_0x00bc
        L_0x008b:
            r5 = move-exception
            r4.a((java.lang.String) r7)
            com.applovin.impl.sdk.p r9 = r4.b
            java.lang.String r0 = "ConnectionManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid XML returned from \""
            goto L_0x00aa
        L_0x009b:
            r5 = move-exception
            r4.a((java.lang.String) r7)
            com.applovin.impl.sdk.p r9 = r4.b
            java.lang.String r0 = "ConnectionManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid JSON returned from \""
        L_0x00aa:
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = "\""
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r9.b(r0, r7, r5)
        L_0x00bc:
            r10.a(r8, r6)
            goto L_0x00e3
        L_0x00c0:
            com.applovin.impl.sdk.p r5 = r4.b
            java.lang.String r8 = "ConnectionManager"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r6)
            java.lang.String r0 = " error received from \""
            r9.append(r0)
            r9.append(r7)
            java.lang.String r7 = "\""
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r5.d(r8, r7)
            r10.a(r6)
        L_0x00e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.network.a.a(java.lang.String, int, java.lang.String, java.lang.Object, boolean, com.applovin.impl.sdk.network.a$b):void");
    }

    private void a(String str, String str2, int i, long j) {
        p pVar = this.b;
        pVar.b("ConnectionManager", "Successful " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s over " + f.d(this.a) + " to \"" + str2 + "\"");
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        p pVar = this.b;
        pVar.b("ConnectionManager", "Failed " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s over " + f.d(this.a) + " to \"" + str2 + "\"", th);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x0221 A[Catch:{ Throwable -> 0x022d }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0225 A[Catch:{ Throwable -> 0x022d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void a(com.applovin.impl.sdk.network.b<T> r18, com.applovin.impl.sdk.network.a.C0005a r19, com.applovin.impl.sdk.network.a.b<T> r20) {
        /*
            r17 = this;
            r8 = r17
            r0 = r19
            r9 = r20
            if (r18 == 0) goto L_0x027b
            java.lang.String r1 = r18.a()
            java.lang.String r10 = r18.c()
            if (r1 == 0) goto L_0x0273
            if (r10 == 0) goto L_0x026b
            if (r9 == 0) goto L_0x0263
            java.lang.String r2 = r1.toLowerCase()
            java.lang.String r3 = "http"
            boolean r2 = r2.startsWith(r3)
            if (r2 != 0) goto L_0x0045
            com.applovin.impl.sdk.p r0 = r8.b
            java.lang.String r2 = "ConnectionManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Requested postback submission to non HTTP endpoint "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = "; skipping..."
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.e(r2, r1)
            r0 = -900(0xfffffffffffffc7c, float:NaN)
            r9.a(r0)
            return
        L_0x0045:
            com.applovin.impl.sdk.j r2 = r8.a
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r3 = com.applovin.impl.sdk.b.b.dI
            java.lang.Object r2 = r2.a(r3)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0072
            java.lang.String r2 = "https://"
            boolean r2 = r1.contains(r2)
            if (r2 != 0) goto L_0x0072
            com.applovin.impl.sdk.j r2 = r8.a
            com.applovin.impl.sdk.p r2 = r2.v()
            java.lang.String r3 = "ConnectionManager"
            java.lang.String r4 = "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting..."
            r2.c(r3, r4)
            java.lang.String r2 = "http://"
            java.lang.String r3 = "https://"
            java.lang.String r1 = r1.replace(r2, r3)
        L_0x0072:
            boolean r7 = r18.l()
            com.applovin.impl.sdk.j r2 = r8.a
            long r2 = com.applovin.impl.sdk.e.n.a((com.applovin.impl.sdk.j) r2)
            java.util.Map r4 = r18.b()
            if (r4 == 0) goto L_0x008c
            java.util.Map r4 = r18.b()
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x0092
        L_0x008c:
            int r4 = r18.h()
            if (r4 < 0) goto L_0x00c6
        L_0x0092:
            java.util.Map r4 = r18.b()
            if (r4 == 0) goto L_0x00ab
            int r5 = r18.h()
            if (r5 < 0) goto L_0x00ab
            java.lang.String r5 = "current_retry_attempt"
            int r6 = r18.h()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r4.put(r5, r6)
        L_0x00ab:
            if (r7 == 0) goto L_0x00c2
            java.lang.String r4 = com.applovin.impl.sdk.e.n.a((java.util.Map<java.lang.String, java.lang.String>) r4)
            com.applovin.impl.sdk.j r5 = r8.a
            java.lang.String r5 = r5.t()
            java.lang.String r4 = com.applovin.impl.sdk.e.j.a((java.lang.String) r4, (java.lang.String) r5, (long) r2)
            java.lang.String r5 = "p"
            java.lang.String r1 = com.applovin.impl.sdk.e.k.a((java.lang.String) r1, (java.lang.String) r5, (java.lang.String) r4)
            goto L_0x00c6
        L_0x00c2:
            java.lang.String r1 = com.applovin.impl.sdk.e.k.a((java.lang.String) r1, (java.util.Map<java.lang.String, java.lang.String>) r4)
        L_0x00c6:
            r11 = r1
            long r12 = java.lang.System.currentTimeMillis()
            com.applovin.impl.sdk.p r1 = r8.b     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.String r4 = "ConnectionManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            r5.<init>()     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.String r6 = "Sending "
            r5.append(r6)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            r5.append(r10)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.String r6 = " request to \""
            r5.append(r6)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            r5.append(r11)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.String r6 = "\"..."
            r5.append(r6)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            r1.b(r4, r5)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            int r1 = r18.j()     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.net.HttpURLConnection r5 = r8.a((java.lang.String) r11, (java.lang.String) r10, (int) r1)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            org.json.JSONObject r1 = r18.d()     // Catch:{ Throwable -> 0x0233, all -> 0x0230 }
            if (r1 == 0) goto L_0x0172
            if (r7 == 0) goto L_0x011b
            org.json.JSONObject r1 = r18.d()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            com.applovin.impl.sdk.j r4 = r8.a     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r4 = r4.t()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r1 = com.applovin.impl.sdk.e.j.a((java.lang.String) r1, (java.lang.String) r4, (long) r2)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            goto L_0x0123
        L_0x0113:
            r0 = move-exception
            goto L_0x023a
        L_0x0116:
            r0 = move-exception
            r7 = r0
            r15 = r5
            goto L_0x0236
        L_0x011b:
            org.json.JSONObject r1 = r18.d()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
        L_0x0123:
            com.applovin.impl.sdk.p r2 = r8.b     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r3 = "ConnectionManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r4.<init>()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r6 = "Request to \""
            r4.append(r6)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r4.append(r11)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r6 = "\" is "
            r4.append(r6)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r4.append(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r2.a(r3, r4)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "application/json; charset=utf-8"
            r5.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r2 = 1
            r5.setDoOutput(r2)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r2 = "UTF-8"
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            byte[] r2 = r1.getBytes(r2)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            int r2 = r2.length     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r5.setFixedLengthStreamingMode(r2)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.io.OutputStream r4 = r5.getOutputStream()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            java.lang.String r6 = "UTF8"
            r3.<init>(r4, r6)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r2.print(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
            r2.close()     // Catch:{ Throwable -> 0x0116, all -> 0x0113 }
        L_0x0172:
            int r6 = r5.getResponseCode()     // Catch:{ MalformedURLException -> 0x0214 }
            if (r6 <= 0) goto L_0x0200
            r1 = r17
            r2 = r10
            r3 = r11
            r4 = r6
            r15 = r5
            r14 = r6
            r5 = r12
            r1.a(r2, r3, r4, r5)     // Catch:{ MalformedURLException -> 0x0215, Throwable -> 0x01fe, all -> 0x01fb }
            java.io.InputStream r6 = r15.getInputStream()     // Catch:{ MalformedURLException -> 0x0215, Throwable -> 0x01fe, all -> 0x01fb }
            r8.a(r14, r11)     // Catch:{ MalformedURLException -> 0x01f6, Throwable -> 0x01ee, all -> 0x01e6 }
            boolean r1 = r18.g()     // Catch:{ MalformedURLException -> 0x01f6, Throwable -> 0x01ee, all -> 0x01e6 }
            if (r1 == 0) goto L_0x01cc
            com.applovin.impl.sdk.j r1 = r8.a     // Catch:{ MalformedURLException -> 0x01f6, Throwable -> 0x01ee, all -> 0x01e6 }
            java.lang.String r2 = com.applovin.impl.sdk.e.f.a((java.io.InputStream) r6, (com.applovin.impl.sdk.j) r1)     // Catch:{ MalformedURLException -> 0x01f6, Throwable -> 0x01ee, all -> 0x01e6 }
            if (r0 == 0) goto L_0x01b8
            if (r2 == 0) goto L_0x01af
            int r1 = r2.length()     // Catch:{ MalformedURLException -> 0x01ac, Throwable -> 0x01a7, all -> 0x01a3 }
            long r3 = (long) r1     // Catch:{ MalformedURLException -> 0x01ac, Throwable -> 0x01a7, all -> 0x01a3 }
            r0.b(r3)     // Catch:{ MalformedURLException -> 0x01ac, Throwable -> 0x01a7, all -> 0x01a3 }
            goto L_0x01af
        L_0x01a3:
            r0 = move-exception
            r14 = r6
            goto L_0x025b
        L_0x01a7:
            r0 = move-exception
            r7 = r0
            r14 = r6
            goto L_0x0240
        L_0x01ac:
            r14 = r6
            goto L_0x0216
        L_0x01af:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x01ac, Throwable -> 0x01a7, all -> 0x01a3 }
            r1 = 0
            long r3 = r3 - r12
            r0.a(r3)     // Catch:{ MalformedURLException -> 0x01ac, Throwable -> 0x01a7, all -> 0x01a3 }
        L_0x01b8:
            int r3 = r15.getResponseCode()     // Catch:{ MalformedURLException -> 0x01f6, Throwable -> 0x01ee, all -> 0x01e6 }
            java.lang.Object r5 = r18.f()     // Catch:{ MalformedURLException -> 0x01f6, Throwable -> 0x01ee, all -> 0x01e6 }
            r1 = r17
            r4 = r11
            r16 = r6
            r6 = r7
            r7 = r20
            r1.a(r2, r3, r4, r5, r6, r7)     // Catch:{ MalformedURLException -> 0x01f8, Throwable -> 0x01dc, all -> 0x01da }
            goto L_0x0211
        L_0x01cc:
            r16 = r6
            if (r0 == 0) goto L_0x01de
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x01f8, Throwable -> 0x01dc, all -> 0x01da }
            r3 = 0
            long r1 = r1 - r12
            r0.a(r1)     // Catch:{ MalformedURLException -> 0x01f8, Throwable -> 0x01dc, all -> 0x01da }
            goto L_0x01de
        L_0x01da:
            r0 = move-exception
            goto L_0x01e9
        L_0x01dc:
            r0 = move-exception
            goto L_0x01f1
        L_0x01de:
            java.lang.Object r0 = r18.f()     // Catch:{ MalformedURLException -> 0x01f8, Throwable -> 0x01dc, all -> 0x01da }
            r9.a(r0, r14)     // Catch:{ MalformedURLException -> 0x01f8, Throwable -> 0x01dc, all -> 0x01da }
            goto L_0x0211
        L_0x01e6:
            r0 = move-exception
            r16 = r6
        L_0x01e9:
            r5 = r15
            r14 = r16
            goto L_0x025c
        L_0x01ee:
            r0 = move-exception
            r16 = r6
        L_0x01f1:
            r7 = r0
            r14 = r16
            goto L_0x0240
        L_0x01f6:
            r16 = r6
        L_0x01f8:
            r14 = r16
            goto L_0x0216
        L_0x01fb:
            r0 = move-exception
            r5 = r15
            goto L_0x023a
        L_0x01fe:
            r0 = move-exception
            goto L_0x0235
        L_0x0200:
            r15 = r5
            r14 = r6
            r7 = 0
            r1 = r17
            r2 = r10
            r3 = r11
            r4 = r14
            r5 = r12
            r1.a(r2, r3, r4, r5, r7)     // Catch:{ MalformedURLException -> 0x0215, Throwable -> 0x01fe, all -> 0x01fb }
            r9.a(r14)     // Catch:{ MalformedURLException -> 0x0215, Throwable -> 0x01fe, all -> 0x01fb }
            r16 = 0
        L_0x0211:
            r14 = r16
            goto L_0x0253
        L_0x0214:
            r15 = r5
        L_0x0215:
            r14 = 0
        L_0x0216:
            r0 = -901(0xfffffffffffffc7b, float:NaN)
            r8.a(r0, r11)     // Catch:{ Throwable -> 0x022d }
            boolean r1 = r18.g()     // Catch:{ Throwable -> 0x022d }
            if (r1 == 0) goto L_0x0225
            r9.a(r0)     // Catch:{ Throwable -> 0x022d }
            goto L_0x0253
        L_0x0225:
            java.lang.Object r1 = r18.f()     // Catch:{ Throwable -> 0x022d }
            r9.a(r1, r0)     // Catch:{ Throwable -> 0x022d }
            goto L_0x0253
        L_0x022d:
            r0 = move-exception
            r7 = r0
            goto L_0x0240
        L_0x0230:
            r0 = move-exception
            r15 = r5
            goto L_0x023a
        L_0x0233:
            r0 = move-exception
            r15 = r5
        L_0x0235:
            r7 = r0
        L_0x0236:
            r14 = 0
            goto L_0x0240
        L_0x0238:
            r0 = move-exception
            r5 = 0
        L_0x023a:
            r14 = 0
            goto L_0x025c
        L_0x023c:
            r0 = move-exception
            r7 = r0
            r14 = 0
            r15 = 0
        L_0x0240:
            int r0 = r8.a((java.lang.Throwable) r7)     // Catch:{ all -> 0x025a }
            r8.a(r0, r11)     // Catch:{ all -> 0x025a }
            r1 = r17
            r2 = r10
            r3 = r11
            r4 = r0
            r5 = r12
            r1.a(r2, r3, r4, r5, r7)     // Catch:{ all -> 0x025a }
            r9.a(r0)     // Catch:{ all -> 0x025a }
        L_0x0253:
            a((java.io.InputStream) r14)
            a((java.net.HttpURLConnection) r15)
            return
        L_0x025a:
            r0 = move-exception
        L_0x025b:
            r5 = r15
        L_0x025c:
            a((java.io.InputStream) r14)
            a((java.net.HttpURLConnection) r5)
            throw r0
        L_0x0263:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "No callback specified"
            r0.<init>(r1)
            throw r0
        L_0x026b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "No method specified"
            r0.<init>(r1)
            throw r0
        L_0x0273:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "No endpoint specified"
            r0.<init>(r1)
            throw r0
        L_0x027b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "No request specified"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.network.a.a(com.applovin.impl.sdk.network.b, com.applovin.impl.sdk.network.a$a, com.applovin.impl.sdk.network.a$b):void");
    }
}
