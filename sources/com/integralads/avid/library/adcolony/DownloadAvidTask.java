package com.integralads.avid.library.adcolony;

import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

public class DownloadAvidTask extends AsyncTask<String, Void, String> {
    private static final int a = 1024;
    private DownloadAvidTaskListener b;

    public interface DownloadAvidTaskListener {
        void failedToLoadAvid();

        void onLoadAvid(String str);
    }

    public DownloadAvidTaskListener getListener() {
        return this.b;
    }

    public void setListener(DownloadAvidTaskListener downloadAvidTaskListener) {
        this.b = downloadAvidTaskListener;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0073 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e A[SYNTHETIC, Splitter:B:26:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0095 A[Catch:{ IOException -> 0x0046 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0073=Splitter:B:31:0x0073, B:23:0x0054=Splitter:B:23:0x0054} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String doInBackground(java.lang.String... r8) {
        /*
            r7 = this;
            r0 = 0
            r8 = r8[r0]
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            r2 = 0
            if (r1 == 0) goto L_0x0010
            java.lang.String r8 = "AvidLoader: URL is empty"
            com.integralads.avid.library.adcolony.utils.AvidLogs.e(r8)
            return r2
        L_0x0010:
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            r1.<init>(r8)     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            r1.connect()     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            r3.<init>(r1)     // Catch:{ MalformedURLException -> 0x0072, IOException -> 0x0052, all -> 0x004f }
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            r1.<init>()     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
        L_0x002e:
            int r5 = r3.read(r4)     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            r6 = -1
            if (r5 == r6) goto L_0x003e
            java.lang.String r6 = new java.lang.String     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            r6.<init>(r4, r0, r5)     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            r1.write(r6)     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            goto L_0x002e
        L_0x003e:
            java.lang.String r0 = r1.toString()     // Catch:{ MalformedURLException -> 0x0073, IOException -> 0x004d }
            r3.close()     // Catch:{ IOException -> 0x0046 }
            return r0
        L_0x0046:
            r8 = move-exception
            java.lang.String r0 = "AvidLoader: can not close Stream"
            com.integralads.avid.library.adcolony.utils.AvidLogs.e(r0, r8)
            return r2
        L_0x004d:
            r8 = move-exception
            goto L_0x0054
        L_0x004f:
            r8 = move-exception
            r3 = r2
            goto L_0x0093
        L_0x0052:
            r8 = move-exception
            r3 = r2
        L_0x0054:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0092 }
            r0.<init>()     // Catch:{ all -> 0x0092 }
            java.lang.String r1 = "AvidLoader: IO error "
            r0.append(r1)     // Catch:{ all -> 0x0092 }
            java.lang.String r8 = r8.getLocalizedMessage()     // Catch:{ all -> 0x0092 }
            r0.append(r8)     // Catch:{ all -> 0x0092 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x0092 }
            com.integralads.avid.library.adcolony.utils.AvidLogs.e(r8)     // Catch:{ all -> 0x0092 }
            if (r3 == 0) goto L_0x0071
            r3.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0071:
            return r2
        L_0x0072:
            r3 = r2
        L_0x0073:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0092 }
            r0.<init>()     // Catch:{ all -> 0x0092 }
            java.lang.String r1 = "AvidLoader: something is wrong with the URL '"
            r0.append(r1)     // Catch:{ all -> 0x0092 }
            r0.append(r8)     // Catch:{ all -> 0x0092 }
            java.lang.String r8 = "'"
            r0.append(r8)     // Catch:{ all -> 0x0092 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x0092 }
            com.integralads.avid.library.adcolony.utils.AvidLogs.e(r8)     // Catch:{ all -> 0x0092 }
            if (r3 == 0) goto L_0x0091
            r3.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0091:
            return r2
        L_0x0092:
            r8 = move-exception
        L_0x0093:
            if (r3 == 0) goto L_0x0098
            r3.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0098:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.integralads.avid.library.adcolony.DownloadAvidTask.doInBackground(java.lang.String[]):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (this.b == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.b.onLoadAvid(str);
        } else {
            this.b.failedToLoadAvid();
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        if (this.b != null) {
            this.b.failedToLoadAvid();
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(String str) {
        onPostExecute(str);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a() {
        onCancelled();
    }
}
