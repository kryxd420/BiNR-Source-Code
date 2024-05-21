package com.tapdaq.sdk.network;

import android.os.AsyncTask;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.network.HttpClientBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class HttpJsonAsyncTask extends AsyncTask<String, Void, String> {
    private String mData;
    private TMAdError mError;
    private HttpClientBase.ResponseHandler mHandler;
    private Map<String, String> mHeaders;

    HttpJsonAsyncTask(Map<String, String> map, String str, HttpClientBase.ResponseHandler responseHandler) {
        this.mHeaders = map;
        this.mData = str;
        this.mHandler = responseHandler;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        try {
            if (this.mData != null) {
                return postUrl(strArr[0]);
            }
            return getUrl(strArr[0]);
        } catch (IOException e) {
            TLog.error((Exception) e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        if (this.mHandler == null) {
            return;
        }
        if (str != null) {
            try {
                if (str.isEmpty()) {
                    this.mHandler.onSuccess(new JSONObject());
                } else {
                    this.mHandler.onSuccess(new JSONObject(str));
                }
            } catch (JSONException unused) {
                this.mHandler.onError(new TMAdError(51, TapdaqError.INVALID_SERVER_RESPONSE_MESSAGE));
            }
        } else {
            this.mHandler.onError(this.mError);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getUrl(java.lang.String r11) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            r1 = 50
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00b0 }
            r2.<init>(r11)     // Catch:{ all -> 0x00b0 }
            java.net.URLConnection r11 = r2.openConnection()     // Catch:{ all -> 0x00b0 }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ all -> 0x00b0 }
            r2 = 60000(0xea60, float:8.4078E-41)
            r11.setReadTimeout(r2)     // Catch:{ all -> 0x00b0 }
            r11.setConnectTimeout(r2)     // Catch:{ all -> 0x00b0 }
            java.lang.String r2 = "GET"
            r11.setRequestMethod(r2)     // Catch:{ all -> 0x00b0 }
            r2 = 1
            r11.setDoInput(r2)     // Catch:{ all -> 0x00b0 }
            java.util.Map<java.lang.String, java.lang.String> r3 = r10.mHeaders     // Catch:{ all -> 0x00b0 }
            if (r3 == 0) goto L_0x0046
            java.util.Map<java.lang.String, java.lang.String> r3 = r10.mHeaders     // Catch:{ all -> 0x00b0 }
            java.util.Set r3 = r3.keySet()     // Catch:{ all -> 0x00b0 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00b0 }
        L_0x002e:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00b0 }
            if (r4 == 0) goto L_0x0046
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00b0 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x00b0 }
            java.util.Map<java.lang.String, java.lang.String> r5 = r10.mHeaders     // Catch:{ all -> 0x00b0 }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x00b0 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00b0 }
            r11.addRequestProperty(r4, r5)     // Catch:{ all -> 0x00b0 }
            goto L_0x002e
        L_0x0046:
            r11.connect()     // Catch:{ all -> 0x00b0 }
            int r3 = r11.getResponseCode()     // Catch:{ all -> 0x00b0 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == r4) goto L_0x0090
            r11 = 204(0xcc, float:2.86E-43)
            if (r3 == r11) goto L_0x0080
            com.tapdaq.sdk.common.TMAdError r11 = new com.tapdaq.sdk.common.TMAdError     // Catch:{ all -> 0x00b0 }
            java.util.Locale r4 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00b0 }
            java.lang.String r5 = "%s %d"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00b0 }
            r7 = 0
            java.lang.String r8 = "HTTP connection error: "
            r6[r7] = r8     // Catch:{ all -> 0x00b0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00b0 }
            r6[r2] = r3     // Catch:{ all -> 0x00b0 }
            java.lang.String r2 = java.lang.String.format(r4, r5, r6)     // Catch:{ all -> 0x00b0 }
            r11.<init>(r1, r2)     // Catch:{ all -> 0x00b0 }
            r10.mError = r11     // Catch:{ all -> 0x00b0 }
            com.tapdaq.sdk.common.TMAdError r11 = r10.mError
            if (r11 != 0) goto L_0x007f
            com.tapdaq.sdk.common.TMAdError r11 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r2 = "HTTP connection error: "
            r11.<init>(r1, r2)
            r10.mError = r11
        L_0x007f:
            return r0
        L_0x0080:
            java.lang.String r11 = ""
            com.tapdaq.sdk.common.TMAdError r0 = r10.mError
            if (r0 != 0) goto L_0x008f
            com.tapdaq.sdk.common.TMAdError r0 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r2 = "HTTP connection error: "
            r0.<init>(r1, r2)
            r10.mError = r0
        L_0x008f:
            return r11
        L_0x0090:
            java.io.InputStream r11 = r11.getInputStream()     // Catch:{ all -> 0x00b0 }
            java.lang.String r0 = r10.readIt(r11)     // Catch:{ all -> 0x00ab }
            com.tapdaq.sdk.common.TMAdError r2 = r10.mError
            if (r2 != 0) goto L_0x00a5
            com.tapdaq.sdk.common.TMAdError r2 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r3 = "HTTP connection error: "
            r2.<init>(r1, r3)
            r10.mError = r2
        L_0x00a5:
            if (r11 == 0) goto L_0x00aa
            r11.close()
        L_0x00aa:
            return r0
        L_0x00ab:
            r0 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
            goto L_0x00b1
        L_0x00b0:
            r11 = move-exception
        L_0x00b1:
            com.tapdaq.sdk.common.TMAdError r2 = r10.mError
            if (r2 != 0) goto L_0x00be
            com.tapdaq.sdk.common.TMAdError r2 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r3 = "HTTP connection error: "
            r2.<init>(r1, r3)
            r10.mError = r2
        L_0x00be:
            if (r0 == 0) goto L_0x00c3
            r0.close()
        L_0x00c3:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapdaq.sdk.network.HttpJsonAsyncTask.getUrl(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String postUrl(java.lang.String r12) throws java.io.IOException {
        /*
            r11 = this;
            r0 = 0
            r1 = 50
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00ec }
            r2.<init>(r12)     // Catch:{ all -> 0x00ec }
            java.net.URLConnection r12 = r2.openConnection()     // Catch:{ all -> 0x00ec }
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ all -> 0x00ec }
            r2 = 60000(0xea60, float:8.4078E-41)
            r12.setReadTimeout(r2)     // Catch:{ all -> 0x00ec }
            r12.setConnectTimeout(r2)     // Catch:{ all -> 0x00ec }
            java.lang.String r2 = "POST"
            r12.setRequestMethod(r2)     // Catch:{ all -> 0x00ec }
            r2 = 1
            r12.setDoInput(r2)     // Catch:{ all -> 0x00ec }
            java.util.Map<java.lang.String, java.lang.String> r3 = r11.mHeaders     // Catch:{ all -> 0x00ec }
            if (r3 == 0) goto L_0x0046
            java.util.Map<java.lang.String, java.lang.String> r3 = r11.mHeaders     // Catch:{ all -> 0x00ec }
            java.util.Set r3 = r3.keySet()     // Catch:{ all -> 0x00ec }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00ec }
        L_0x002e:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00ec }
            if (r4 == 0) goto L_0x0046
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x00ec }
            java.util.Map<java.lang.String, java.lang.String> r5 = r11.mHeaders     // Catch:{ all -> 0x00ec }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00ec }
            r12.addRequestProperty(r4, r5)     // Catch:{ all -> 0x00ec }
            goto L_0x002e
        L_0x0046:
            java.io.OutputStream r3 = r12.getOutputStream()     // Catch:{ all -> 0x00ec }
            java.io.BufferedWriter r4 = new java.io.BufferedWriter     // Catch:{ all -> 0x00ec }
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00ec }
            java.lang.String r6 = "UTF-8"
            r5.<init>(r3, r6)     // Catch:{ all -> 0x00ec }
            r4.<init>(r5)     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = r11.mData     // Catch:{ all -> 0x00ec }
            r4.write(r5)     // Catch:{ all -> 0x00ec }
            r4.flush()     // Catch:{ all -> 0x00ec }
            r4.close()     // Catch:{ all -> 0x00ec }
            r3.close()     // Catch:{ all -> 0x00ec }
            r12.connect()     // Catch:{ all -> 0x00ec }
            int r3 = r12.getResponseCode()     // Catch:{ all -> 0x00ec }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ec }
            r4.<init>()     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = "The response is: "
            r4.append(r5)     // Catch:{ all -> 0x00ec }
            r4.append(r3)     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00ec }
            com.tapdaq.sdk.helpers.TLog.debug(r4)     // Catch:{ all -> 0x00ec }
            java.io.InputStream r12 = r12.getInputStream()     // Catch:{ all -> 0x00ec }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == r4) goto L_0x00d5
            r4 = 204(0xcc, float:2.86E-43)
            if (r3 == r4) goto L_0x00c0
            com.tapdaq.sdk.common.TMAdError r4 = new com.tapdaq.sdk.common.TMAdError     // Catch:{ all -> 0x00bb }
            java.util.Locale r5 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00bb }
            java.lang.String r6 = "%s %d"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x00bb }
            r8 = 0
            java.lang.String r9 = "HTTP connection error: "
            r7[r8] = r9     // Catch:{ all -> 0x00bb }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00bb }
            r7[r2] = r3     // Catch:{ all -> 0x00bb }
            java.lang.String r2 = java.lang.String.format(r5, r6, r7)     // Catch:{ all -> 0x00bb }
            r4.<init>(r1, r2)     // Catch:{ all -> 0x00bb }
            r11.mError = r4     // Catch:{ all -> 0x00bb }
            com.tapdaq.sdk.common.TMAdError r2 = r11.mError
            if (r2 != 0) goto L_0x00b5
            com.tapdaq.sdk.common.TMAdError r2 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r3 = "HTTP connection error: "
            r2.<init>(r1, r3)
            r11.mError = r2
        L_0x00b5:
            if (r12 == 0) goto L_0x00ba
            r12.close()
        L_0x00ba:
            return r0
        L_0x00bb:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
            goto L_0x00ed
        L_0x00c0:
            java.lang.String r0 = ""
            com.tapdaq.sdk.common.TMAdError r2 = r11.mError
            if (r2 != 0) goto L_0x00cf
            com.tapdaq.sdk.common.TMAdError r2 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r3 = "HTTP connection error: "
            r2.<init>(r1, r3)
            r11.mError = r2
        L_0x00cf:
            if (r12 == 0) goto L_0x00d4
            r12.close()
        L_0x00d4:
            return r0
        L_0x00d5:
            java.lang.String r0 = r11.readIt(r12)     // Catch:{ all -> 0x00bb }
            com.tapdaq.sdk.common.TMAdError r2 = r11.mError
            if (r2 != 0) goto L_0x00e6
            com.tapdaq.sdk.common.TMAdError r2 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r3 = "HTTP connection error: "
            r2.<init>(r1, r3)
            r11.mError = r2
        L_0x00e6:
            if (r12 == 0) goto L_0x00eb
            r12.close()
        L_0x00eb:
            return r0
        L_0x00ec:
            r12 = move-exception
        L_0x00ed:
            com.tapdaq.sdk.common.TMAdError r2 = r11.mError
            if (r2 != 0) goto L_0x00fa
            com.tapdaq.sdk.common.TMAdError r2 = new com.tapdaq.sdk.common.TMAdError
            java.lang.String r3 = "HTTP connection error: "
            r2.<init>(r1, r3)
            r11.mError = r2
        L_0x00fa:
            if (r0 == 0) goto L_0x00ff
            r0.close()
        L_0x00ff:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapdaq.sdk.network.HttpJsonAsyncTask.postUrl(java.lang.String):java.lang.String");
    }

    private String readIt(InputStream inputStream) throws IOException, UnsupportedEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return new String(byteArrayOutputStream.toByteArray());
            }
        }
    }
}
