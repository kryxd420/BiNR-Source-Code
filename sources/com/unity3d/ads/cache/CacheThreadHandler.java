package com.unity3d.ads.cache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.unity3d.ads.api.Request;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.request.WebRequest;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CacheThreadHandler extends Handler {
    private boolean _active = false;
    private boolean _canceled = false;
    private WebRequest _currentRequest = null;

    CacheThreadHandler() {
    }

    public void handleMessage(Message message) {
        HashMap hashMap;
        Bundle data = message.getData();
        String string = data.getString("source");
        data.remove("source");
        String string2 = data.getString("target");
        data.remove("target");
        int i = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        int i2 = data.getInt("readTimeout");
        data.remove("readTimeout");
        int i3 = data.getInt("progressInterval");
        data.remove("progressInterval");
        boolean z = data.getBoolean("append", false);
        data.remove("append");
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            HashMap hashMap2 = new HashMap();
            for (String str : data.keySet()) {
                hashMap2.put(str, Arrays.asList(data.getStringArray(str)));
            }
            hashMap = hashMap2;
        } else {
            hashMap = null;
        }
        File file = new File(string2);
        if ((z && !file.exists()) || (!z && file.exists())) {
            this._active = false;
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_STATE_WRONG, string, string2, Boolean.valueOf(z), Boolean.valueOf(file.exists()));
        } else if (message.what == 1) {
            downloadFile(string, string2, i, i2, i3, hashMap, z);
        }
    }

    public void setCancelStatus(boolean z) {
        this._canceled = z;
        if (z && this._currentRequest != null) {
            this._active = false;
            this._currentRequest.cancel();
        }
    }

    public boolean isActive() {
        return this._active;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r6v5, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v7, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r6v9, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v11, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: type inference failed for: r6v46 */
    /* JADX WARNING: type inference failed for: r6v47 */
    /* JADX WARNING: type inference failed for: r6v48 */
    /* JADX WARNING: type inference failed for: r6v49 */
    /* JADX WARNING: type inference failed for: r6v50 */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02d0 A[SYNTHETIC, Splitter:B:110:0x02d0] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02fe A[SYNTHETIC, Splitter:B:118:0x02fe] */
    /* JADX WARNING: Removed duplicated region for block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:126:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x018e A[SYNTHETIC, Splitter:B:66:0x018e] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01df A[SYNTHETIC, Splitter:B:77:0x01df] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0230 A[SYNTHETIC, Splitter:B:88:0x0230] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0281 A[SYNTHETIC, Splitter:B:99:0x0281] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void downloadFile(java.lang.String r22, java.lang.String r23, int r24, int r25, int r26, java.util.HashMap<java.lang.String, java.util.List<java.lang.String>> r27, boolean r28) {
        /*
            r21 = this;
            r13 = r21
            r14 = r22
            r0 = r23
            r1 = r28
            boolean r2 = r13._canceled
            if (r2 != 0) goto L_0x0325
            if (r14 == 0) goto L_0x0325
            if (r0 != 0) goto L_0x0012
            goto L_0x0325
        L_0x0012:
            java.io.File r5 = new java.io.File
            r5.<init>(r0)
            if (r1 == 0) goto L_0x0047
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unity Ads cache: resuming download "
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = " to "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " at "
            r2.append(r0)
            long r3 = r5.length()
            r2.append(r3)
            java.lang.String r0 = " bytes"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.unity3d.ads.log.DeviceLog.debug(r0)
            goto L_0x0063
        L_0x0047:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unity Ads cache: start downloading "
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = " to "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.unity3d.ads.log.DeviceLog.debug(r0)
        L_0x0063:
            boolean r0 = com.unity3d.ads.device.Device.isActiveNetworkConnected()
            r15 = 2
            r12 = 1
            r11 = 0
            if (r0 != 0) goto L_0x0085
            java.lang.String r0 = "Unity Ads cache: download cancelled, no internet connection available"
            com.unity3d.ads.log.DeviceLog.debug(r0)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r1 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r2 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            java.lang.Object[] r3 = new java.lang.Object[r15]
            com.unity3d.ads.cache.CacheError r4 = com.unity3d.ads.cache.CacheError.NO_INTERNET
            r3[r11] = r4
            r3[r12] = r14
            r0.sendEvent(r1, r2, r3)
            return
        L_0x0085:
            r13._active = r12
            long r2 = android.os.SystemClock.elapsedRealtime()
            r10 = 0
            r8 = 3
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x02a5, MalformedURLException -> 0x0256, IOException -> 0x0205, IllegalStateException -> 0x01b4, NetworkIOException -> 0x0163, all -> 0x015b }
            r9.<init>(r5, r1)     // Catch:{ FileNotFoundException -> 0x02a5, MalformedURLException -> 0x0256, IOException -> 0x0205, IllegalStateException -> 0x01b4, NetworkIOException -> 0x0163, all -> 0x015b }
            r0 = r24
            r1 = r25
            r4 = r27
            com.unity3d.ads.request.WebRequest r0 = r13.getWebRequest(r14, r0, r1, r4)     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            r13._currentRequest = r0     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.request.WebRequest r0 = r13._currentRequest     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.cache.CacheThreadHandler$1 r1 = new com.unity3d.ads.cache.CacheThreadHandler$1     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            r4 = r26
            r1.<init>(r5, r4)     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            r0.setProgressListener(r1)     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.request.WebRequest r0 = r13._currentRequest     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            long r6 = r0.makeStreamRequest(r9)     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            r13._active = r11     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.request.WebRequest r0 = r13._currentRequest     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            long r16 = r0.getContentLength()     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.request.WebRequest r0 = r13._currentRequest     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            boolean r0 = r0.isCanceled()     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.request.WebRequest r1 = r13._currentRequest     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            int r18 = r1.getResponseCode()     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            com.unity3d.ads.request.WebRequest r1 = r13._currentRequest     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            java.util.Map r19 = r1.getResponseHeaders()     // Catch:{ FileNotFoundException -> 0x0150, MalformedURLException -> 0x0145, IOException -> 0x013a, IllegalStateException -> 0x012f, NetworkIOException -> 0x0125, all -> 0x0119 }
            r1 = r21
            r4 = r22
            r20 = r9
            r15 = 3
            r8 = r16
            r15 = r10
            r10 = r0
            r11 = r18
            r16 = 1
            r12 = r19
            r1.postProcessDownload(r2, r4, r5, r6, r8, r10, r11, r12)     // Catch:{ FileNotFoundException -> 0x0116, MalformedURLException -> 0x0113, IOException -> 0x0110, IllegalStateException -> 0x010d, NetworkIOException -> 0x010a, all -> 0x0107 }
            r13._currentRequest = r15
            r20.close()     // Catch:{ Exception -> 0x00e5 }
            goto L_0x02f6
        L_0x00e5:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r1)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r6 = 0
            r4[r6] = r5
            r4[r16] = r14
            java.lang.String r1 = r1.getMessage()
            r5 = 2
            r4[r5] = r1
            goto L_0x02f3
        L_0x0107:
            r0 = move-exception
            r6 = 0
            goto L_0x0120
        L_0x010a:
            r0 = move-exception
            r6 = 0
            goto L_0x012c
        L_0x010d:
            r0 = move-exception
            r6 = 0
            goto L_0x0136
        L_0x0110:
            r0 = move-exception
            r6 = 0
            goto L_0x0141
        L_0x0113:
            r0 = move-exception
            r6 = 0
            goto L_0x014c
        L_0x0116:
            r0 = move-exception
            r6 = 0
            goto L_0x0157
        L_0x0119:
            r0 = move-exception
            r20 = r9
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x0120:
            r1 = r0
            r10 = r20
            goto L_0x02fa
        L_0x0125:
            r0 = move-exception
            r20 = r9
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x012c:
            r10 = r20
            goto L_0x0168
        L_0x012f:
            r0 = move-exception
            r20 = r9
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x0136:
            r10 = r20
            goto L_0x01b9
        L_0x013a:
            r0 = move-exception
            r20 = r9
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x0141:
            r10 = r20
            goto L_0x020a
        L_0x0145:
            r0 = move-exception
            r20 = r9
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x014c:
            r10 = r20
            goto L_0x025b
        L_0x0150:
            r0 = move-exception
            r20 = r9
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x0157:
            r10 = r20
            goto L_0x02aa
        L_0x015b:
            r0 = move-exception
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x0160:
            r1 = r0
            goto L_0x02fa
        L_0x0163:
            r0 = move-exception
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x0168:
            java.lang.String r1 = "Network error"
            com.unity3d.ads.log.DeviceLog.exception(r1, r0)     // Catch:{ all -> 0x02f7 }
            r13._active = r6     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewApp r1 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR     // Catch:{ all -> 0x02f7 }
            r4 = 3
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheError r4 = com.unity3d.ads.cache.CacheError.NETWORK_ERROR     // Catch:{ all -> 0x02f7 }
            r5[r6] = r4     // Catch:{ all -> 0x02f7 }
            r5[r16] = r14     // Catch:{ all -> 0x02f7 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02f7 }
            r4 = 2
            r5[r4] = r0     // Catch:{ all -> 0x02f7 }
            r1.sendEvent(r2, r3, r5)     // Catch:{ all -> 0x02f7 }
            r13._currentRequest = r15
            if (r10 == 0) goto L_0x02f6
            r10.close()     // Catch:{ Exception -> 0x0193 }
            goto L_0x02f6
        L_0x0193:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r1)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r4[r6] = r5
            r4[r16] = r14
            java.lang.String r1 = r1.getMessage()
            r5 = 2
            r4[r5] = r1
            goto L_0x02f3
        L_0x01b4:
            r0 = move-exception
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x01b9:
            java.lang.String r1 = "Illegal state"
            com.unity3d.ads.log.DeviceLog.exception(r1, r0)     // Catch:{ all -> 0x02f7 }
            r13._active = r6     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewApp r1 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR     // Catch:{ all -> 0x02f7 }
            r4 = 3
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheError r4 = com.unity3d.ads.cache.CacheError.ILLEGAL_STATE     // Catch:{ all -> 0x02f7 }
            r5[r6] = r4     // Catch:{ all -> 0x02f7 }
            r5[r16] = r14     // Catch:{ all -> 0x02f7 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02f7 }
            r4 = 2
            r5[r4] = r0     // Catch:{ all -> 0x02f7 }
            r1.sendEvent(r2, r3, r5)     // Catch:{ all -> 0x02f7 }
            r13._currentRequest = r15
            if (r10 == 0) goto L_0x02f6
            r10.close()     // Catch:{ Exception -> 0x01e4 }
            goto L_0x02f6
        L_0x01e4:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r1)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r4[r6] = r5
            r4[r16] = r14
            java.lang.String r1 = r1.getMessage()
            r5 = 2
            r4[r5] = r1
            goto L_0x02f3
        L_0x0205:
            r0 = move-exception
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x020a:
            java.lang.String r1 = "Couldn't request stream"
            com.unity3d.ads.log.DeviceLog.exception(r1, r0)     // Catch:{ all -> 0x02f7 }
            r13._active = r6     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewApp r1 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR     // Catch:{ all -> 0x02f7 }
            r4 = 3
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheError r4 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR     // Catch:{ all -> 0x02f7 }
            r5[r6] = r4     // Catch:{ all -> 0x02f7 }
            r5[r16] = r14     // Catch:{ all -> 0x02f7 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02f7 }
            r4 = 2
            r5[r4] = r0     // Catch:{ all -> 0x02f7 }
            r1.sendEvent(r2, r3, r5)     // Catch:{ all -> 0x02f7 }
            r13._currentRequest = r15
            if (r10 == 0) goto L_0x02f6
            r10.close()     // Catch:{ Exception -> 0x0235 }
            goto L_0x02f6
        L_0x0235:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r1)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r4[r6] = r5
            r4[r16] = r14
            java.lang.String r1 = r1.getMessage()
            r5 = 2
            r4[r5] = r1
            goto L_0x02f3
        L_0x0256:
            r0 = move-exception
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x025b:
            java.lang.String r1 = "Malformed URL"
            com.unity3d.ads.log.DeviceLog.exception(r1, r0)     // Catch:{ all -> 0x02f7 }
            r13._active = r6     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewApp r1 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR     // Catch:{ all -> 0x02f7 }
            r4 = 3
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheError r4 = com.unity3d.ads.cache.CacheError.MALFORMED_URL     // Catch:{ all -> 0x02f7 }
            r5[r6] = r4     // Catch:{ all -> 0x02f7 }
            r5[r16] = r14     // Catch:{ all -> 0x02f7 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02f7 }
            r4 = 2
            r5[r4] = r0     // Catch:{ all -> 0x02f7 }
            r1.sendEvent(r2, r3, r5)     // Catch:{ all -> 0x02f7 }
            r13._currentRequest = r15
            if (r10 == 0) goto L_0x02f6
            r10.close()     // Catch:{ Exception -> 0x0285 }
            goto L_0x02f6
        L_0x0285:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r1)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r4[r6] = r5
            r4[r16] = r14
            java.lang.String r1 = r1.getMessage()
            r5 = 2
            r4[r5] = r1
            goto L_0x02f3
        L_0x02a5:
            r0 = move-exception
            r15 = r10
            r6 = 0
            r16 = 1
        L_0x02aa:
            java.lang.String r1 = "Couldn't create target file"
            com.unity3d.ads.log.DeviceLog.exception(r1, r0)     // Catch:{ all -> 0x02f7 }
            r13._active = r6     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewApp r1 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR     // Catch:{ all -> 0x02f7 }
            r4 = 3
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x02f7 }
            com.unity3d.ads.cache.CacheError r4 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR     // Catch:{ all -> 0x02f7 }
            r5[r6] = r4     // Catch:{ all -> 0x02f7 }
            r5[r16] = r14     // Catch:{ all -> 0x02f7 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02f7 }
            r4 = 2
            r5[r4] = r0     // Catch:{ all -> 0x02f7 }
            r1.sendEvent(r2, r3, r5)     // Catch:{ all -> 0x02f7 }
            r13._currentRequest = r15
            if (r10 == 0) goto L_0x02f6
            r10.close()     // Catch:{ Exception -> 0x02d4 }
            goto L_0x02f6
        L_0x02d4:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r1)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r3 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r4[r6] = r5
            r4[r16] = r14
            java.lang.String r1 = r1.getMessage()
            r5 = 2
            r4[r5] = r1
        L_0x02f3:
            r0.sendEvent(r2, r3, r4)
        L_0x02f6:
            return
        L_0x02f7:
            r0 = move-exception
            goto L_0x0160
        L_0x02fa:
            r13._currentRequest = r15
            if (r10 == 0) goto L_0x0324
            r10.close()     // Catch:{ Exception -> 0x0302 }
            goto L_0x0324
        L_0x0302:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = "Error closing stream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r2)
            com.unity3d.ads.webview.WebViewApp r0 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebViewEventCategory r3 = com.unity3d.ads.webview.WebViewEventCategory.CACHE
            com.unity3d.ads.cache.CacheEvent r4 = com.unity3d.ads.cache.CacheEvent.DOWNLOAD_ERROR
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            com.unity3d.ads.cache.CacheError r7 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR
            r5[r6] = r7
            r5[r16] = r14
            java.lang.String r2 = r2.getMessage()
            r6 = 2
            r5[r6] = r2
            r0.sendEvent(r3, r4, r5)
        L_0x0324:
            throw r1
        L_0x0325:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.cache.CacheThreadHandler.downloadFile(java.lang.String, java.lang.String, int, int, int, java.util.HashMap, boolean):void");
    }

    private void postProcessDownload(long j, String str, File file, long j2, long j3, boolean z, int i, Map<String, List<String>> map) {
        String str2 = str;
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (!file.setReadable(true, false)) {
            DeviceLog.debug("Unity Ads cache: could not set file readable!");
        }
        if (!z) {
            DeviceLog.debug("Unity Ads cache: File " + file.getName() + " of " + j2 + " bytes downloaded in " + elapsedRealtime + "ms");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_END, str2, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(elapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
            return;
        }
        long j4 = j2;
        DeviceLog.debug("Unity Ads cache: downloading of " + str2 + " stopped");
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STOPPED, str2, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(elapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
    }

    private WebRequest getWebRequest(String str, int i, int i2, HashMap<String, List<String>> hashMap) throws MalformedURLException {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        return new WebRequest(str, "GET", hashMap2, i, i2);
    }
}
