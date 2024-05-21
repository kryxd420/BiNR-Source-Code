package com.tapdaq.sdk.network;

import android.os.AsyncTask;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.network.HttpClientBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class HttpFileAsyncTask extends AsyncTask<String, Void, byte[]> {
    private HttpClientBase.ResponseFileHandler mHandler;

    HttpFileAsyncTask(HttpClientBase.ResponseFileHandler responseFileHandler) {
        this.mHandler = responseFileHandler;
    }

    /* access modifiers changed from: protected */
    public byte[] doInBackground(String... strArr) {
        try {
            return getFile(strArr[0]);
        } catch (IOException e) {
            TLog.error((Exception) e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(byte[] bArr) {
        super.onPostExecute(bArr);
        if (this.mHandler == null) {
            return;
        }
        if (bArr != null) {
            this.mHandler.onSuccess(bArr);
        } else {
            this.mHandler.onError(new Exception("File Null"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] getFile(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x0059 }
            r1.<init>(r7)     // Catch:{ all -> 0x0059 }
            java.net.URLConnection r7 = r1.openConnection()     // Catch:{ all -> 0x0059 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ all -> 0x0059 }
            r1 = 10000(0x2710, float:1.4013E-41)
            r7.setReadTimeout(r1)     // Catch:{ all -> 0x0059 }
            r1 = 15000(0x3a98, float:2.102E-41)
            r7.setConnectTimeout(r1)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "GET"
            r7.setRequestMethod(r1)     // Catch:{ all -> 0x0059 }
            r1 = 1
            r7.setDoInput(r1)     // Catch:{ all -> 0x0059 }
            r7.connect()     // Catch:{ all -> 0x0059 }
            int r1 = r7.getResponseCode()     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = "TD"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r3.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = "The response is: "
            r3.append(r4)     // Catch:{ all -> 0x0059 }
            r3.append(r1)     // Catch:{ all -> 0x0059 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0059 }
            android.util.Log.d(r2, r3)     // Catch:{ all -> 0x0059 }
            java.io.InputStream r7 = r7.getInputStream()     // Catch:{ all -> 0x0059 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 == r2) goto L_0x004a
            if (r7 == 0) goto L_0x0049
            r7.close()
        L_0x0049:
            return r0
        L_0x004a:
            byte[] r0 = r6.readIt(r7)     // Catch:{ all -> 0x0054 }
            if (r7 == 0) goto L_0x0053
            r7.close()
        L_0x0053:
            return r0
        L_0x0054:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x005a
        L_0x0059:
            r7 = move-exception
        L_0x005a:
            if (r0 == 0) goto L_0x005f
            r0.close()
        L_0x005f:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapdaq.sdk.network.HttpFileAsyncTask.getFile(java.lang.String):byte[]");
    }

    private byte[] readIt(InputStream inputStream) throws IOException, UnsupportedEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
