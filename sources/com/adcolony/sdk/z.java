package com.adcolony.sdk;

import java.net.URL;

class z {
    URL a;

    public z(URL url) {
        this.a = url;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.io.DataOutputStream} */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            java.net.URL r3 = r6.a     // Catch:{ IOException -> 0x0075, all -> 0x0071 }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x0075, all -> 0x0071 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0075, all -> 0x0071 }
            java.lang.String r4 = "POST"
            r3.setRequestMethod(r4)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r4 = "Content-Encoding"
            java.lang.String r5 = "gzip"
            r3.setRequestProperty(r4, r5)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "application/json"
            r3.setRequestProperty(r4, r5)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r3.setDoInput(r0)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.io.DataOutputStream r5 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0066, all -> 0x0064 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0066, all -> 0x0064 }
            java.lang.String r1 = "UTF-8"
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            byte[] r7 = r7.getBytes(r1)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            r5.write(r7)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            r5.close()     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            int r7 = r3.getResponseCode()     // Catch:{ IOException -> 0x005d, all -> 0x0059 }
            r4.close()
            if (r3 == 0) goto L_0x0058
            java.io.InputStream r0 = r3.getInputStream()
            if (r0 == 0) goto L_0x0055
            java.io.InputStream r0 = r3.getInputStream()
            r0.close()
        L_0x0055:
            r3.disconnect()
        L_0x0058:
            return r7
        L_0x0059:
            r7 = move-exception
            r1 = r5
            r2 = 1
            goto L_0x007e
        L_0x005d:
            r7 = move-exception
            r1 = r3
            goto L_0x0079
        L_0x0060:
            r7 = move-exception
            goto L_0x007d
        L_0x0062:
            r7 = move-exception
            goto L_0x006f
        L_0x0064:
            r7 = move-exception
            goto L_0x007e
        L_0x0066:
            r7 = move-exception
            r5 = r1
            goto L_0x006f
        L_0x0069:
            r7 = move-exception
            r4 = r1
            goto L_0x007e
        L_0x006c:
            r7 = move-exception
            r4 = r1
            r5 = r4
        L_0x006f:
            r1 = r3
            goto L_0x0078
        L_0x0071:
            r7 = move-exception
            r3 = r1
            r4 = r3
            goto L_0x007e
        L_0x0075:
            r7 = move-exception
            r4 = r1
            r5 = r4
        L_0x0078:
            r0 = 0
        L_0x0079:
            throw r7     // Catch:{ all -> 0x007a }
        L_0x007a:
            r7 = move-exception
            r2 = r0
            r3 = r1
        L_0x007d:
            r1 = r5
        L_0x007e:
            if (r1 == 0) goto L_0x0085
            if (r2 != 0) goto L_0x0085
            r1.close()
        L_0x0085:
            if (r4 == 0) goto L_0x008a
            r4.close()
        L_0x008a:
            if (r3 == 0) goto L_0x009c
            java.io.InputStream r0 = r3.getInputStream()
            if (r0 == 0) goto L_0x0099
            java.io.InputStream r0 = r3.getInputStream()
            r0.close()
        L_0x0099:
            r3.disconnect()
        L_0x009c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.z.a(java.lang.String):int");
    }
}
