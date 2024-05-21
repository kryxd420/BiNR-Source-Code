package com.tapdaq.sdk.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.network.HttpClientBase;
import java.io.IOException;

class HttpImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private Context mContext;
    private final HttpClientBase.ResponseImageHandler mHandler;
    private final int mHeight;
    private final int mWidth;

    HttpImageAsyncTask(Context context, int i, int i2, HttpClientBase.ResponseImageHandler responseImageHandler) {
        this.mContext = context;
        this.mWidth = i;
        this.mHeight = i2;
        this.mHandler = responseImageHandler;
    }

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(String... strArr) {
        try {
            return getBitmap(strArr[0]);
        } catch (IOException e) {
            TLog.error((Exception) e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (this.mHandler == null) {
            return;
        }
        if (bitmap != null) {
            this.mHandler.onSuccess(bitmap);
        } else {
            this.mHandler.onError(new Exception("Image Null"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap getBitmap(java.lang.String r15) throws java.io.IOException {
        /*
            r14 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x00e6 }
            r1.<init>(r15)     // Catch:{ all -> 0x00e6 }
            java.net.URLConnection r15 = r1.openConnection()     // Catch:{ all -> 0x00e6 }
            java.net.HttpURLConnection r15 = (java.net.HttpURLConnection) r15     // Catch:{ all -> 0x00e6 }
            r1 = 10000(0x2710, float:1.4013E-41)
            r15.setReadTimeout(r1)     // Catch:{ all -> 0x00e6 }
            r1 = 15000(0x3a98, float:2.102E-41)
            r15.setConnectTimeout(r1)     // Catch:{ all -> 0x00e6 }
            java.lang.String r1 = "GET"
            r15.setRequestMethod(r1)     // Catch:{ all -> 0x00e6 }
            r1 = 1
            r15.setDoInput(r1)     // Catch:{ all -> 0x00e6 }
            r15.connect()     // Catch:{ all -> 0x00e6 }
            int r2 = r15.getResponseCode()     // Catch:{ all -> 0x00e6 }
            java.lang.String r3 = "TD"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e6 }
            r4.<init>()     // Catch:{ all -> 0x00e6 }
            java.lang.String r5 = "The response is: "
            r4.append(r5)     // Catch:{ all -> 0x00e6 }
            r4.append(r2)     // Catch:{ all -> 0x00e6 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00e6 }
            android.util.Log.d(r3, r4)     // Catch:{ all -> 0x00e6 }
            java.io.InputStream r15 = r15.getInputStream()     // Catch:{ all -> 0x00e6 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L_0x004c
            if (r15 == 0) goto L_0x0049
            r15.close()
        L_0x0049:
            r14.mContext = r0
            return r0
        L_0x004c:
            byte[] r2 = com.tapdaq.sdk.helpers.TDMemoryInfo.InputStreamToByteArray(r15)     // Catch:{ all -> 0x00e4 }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x00e4 }
            r3.<init>(r2)     // Catch:{ all -> 0x00e4 }
            android.graphics.BitmapFactory$Options r4 = new android.graphics.BitmapFactory$Options     // Catch:{ all -> 0x00e4 }
            r4.<init>()     // Catch:{ all -> 0x00e4 }
            r4.inJustDecodeBounds = r1     // Catch:{ all -> 0x00e4 }
            android.graphics.BitmapFactory.decodeStream(r15, r0, r4)     // Catch:{ all -> 0x00e4 }
            int r5 = r14.mWidth     // Catch:{ all -> 0x00e4 }
            int r6 = r14.mHeight     // Catch:{ all -> 0x00e4 }
            int r5 = com.tapdaq.sdk.helpers.TDMemoryInfo.calculateInSampleSize(r4, r5, r6)     // Catch:{ all -> 0x00e4 }
            r4.inSampleSize = r5     // Catch:{ all -> 0x00e4 }
            android.content.Context r5 = r14.mContext     // Catch:{ all -> 0x00e4 }
            long r5 = com.tapdaq.sdk.helpers.TDMemoryInfo.GetAvailableMemory(r5)     // Catch:{ all -> 0x00e4 }
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x00e4 }
            r7.<init>(r2)     // Catch:{ all -> 0x00e4 }
            int r7 = com.tapdaq.sdk.helpers.TDMemoryInfo.DecodedImageSizeInBytes(r7, r4)     // Catch:{ all -> 0x00e4 }
            long r7 = (long) r7     // Catch:{ all -> 0x00e4 }
            r9 = 1048576(0x100000, double:5.180654E-318)
            long r7 = r7 / r9
        L_0x007d:
            int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            r7 = 0
            if (r9 <= 0) goto L_0x00d3
            int r8 = r4.inSampleSize     // Catch:{ all -> 0x00e4 }
            r9 = 4
            if (r8 >= r9) goto L_0x00d3
            int r8 = r4.inSampleSize     // Catch:{ all -> 0x00e4 }
            r9 = 2
            if (r8 >= r1) goto L_0x008f
            r4.inSampleSize = r1     // Catch:{ all -> 0x00e4 }
            goto L_0x0095
        L_0x008f:
            int r8 = r4.inSampleSize     // Catch:{ all -> 0x00e4 }
            int r8 = r8 * 2
            r4.inSampleSize = r8     // Catch:{ all -> 0x00e4 }
        L_0x0095:
            java.util.Locale r8 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00e4 }
            java.lang.String r10 = "Decoding Image with Sample Size: %s"
            java.lang.Object[] r11 = new java.lang.Object[r1]     // Catch:{ all -> 0x00e4 }
            int r12 = r4.inSampleSize     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x00e4 }
            r11[r7] = r12     // Catch:{ all -> 0x00e4 }
            java.lang.String r8 = java.lang.String.format(r8, r10, r11)     // Catch:{ all -> 0x00e4 }
            com.tapdaq.sdk.helpers.TLog.debug(r8)     // Catch:{ all -> 0x00e4 }
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x00e4 }
            r8.<init>(r2)     // Catch:{ all -> 0x00e4 }
            int r8 = com.tapdaq.sdk.helpers.TDMemoryInfo.DecodedImageSizeInBytes(r8, r4)     // Catch:{ all -> 0x00e4 }
            long r10 = (long) r8     // Catch:{ all -> 0x00e4 }
            int r8 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r8 > 0) goto L_0x00d1
            java.util.Locale r8 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00e4 }
            java.lang.String r12 = "Not enough memory to load image. %f available. %d required"
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x00e4 }
            java.lang.Long r13 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x00e4 }
            r9[r7] = r13     // Catch:{ all -> 0x00e4 }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00e4 }
            r9[r1] = r7     // Catch:{ all -> 0x00e4 }
            java.lang.String r7 = java.lang.String.format(r8, r12, r9)     // Catch:{ all -> 0x00e4 }
            com.tapdaq.sdk.helpers.TLog.warning(r7)     // Catch:{ all -> 0x00e4 }
        L_0x00d1:
            r7 = r10
            goto L_0x007d
        L_0x00d3:
            r4.inJustDecodeBounds = r7     // Catch:{ all -> 0x00e4 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r3, r0, r4)     // Catch:{ all -> 0x00e4 }
            r3.close()     // Catch:{ all -> 0x00e4 }
            if (r15 == 0) goto L_0x00e1
            r15.close()
        L_0x00e1:
            r14.mContext = r0
            return r1
        L_0x00e4:
            r1 = move-exception
            goto L_0x00e8
        L_0x00e6:
            r1 = move-exception
            r15 = r0
        L_0x00e8:
            if (r15 == 0) goto L_0x00ed
            r15.close()
        L_0x00ed:
            r14.mContext = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapdaq.sdk.network.HttpImageAsyncTask.getBitmap(java.lang.String):android.graphics.Bitmap");
    }
}
