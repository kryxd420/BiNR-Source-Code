package com.tonyodev.fetch;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import com.applovin.sdk.AppLovinErrorCodes;
import com.tapdaq.sdk.TapdaqError;
import com.tonyodev.fetch.exception.DownloadInterruptedException;
import com.tonyodev.fetch.request.Header;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

final class FetchRunnable implements Runnable {
    private static final String ACTION_DONE = "com.tonyodev.fetch.action_done";
    private static final String EXTRA_ID = "com.tonyodev.fetch.extra_id";
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper databaseHelper;
    private long downloadedBytes;
    private final String filePath;
    private long fileSize;
    private final List<Header> headers;
    private HttpURLConnection httpURLConnection;
    private final long id;
    private BufferedInputStream input;
    private volatile boolean interrupted = false;
    private final boolean loggingEnabled;
    private final long onUpdateInterval;
    private RandomAccessFile output;
    private int progress;
    private final String url;

    private boolean isResponseOk(int i) {
        return i == 200 || i == 202 || i == 206;
    }

    @NonNull
    static IntentFilter getDoneFilter() {
        return new IntentFilter(ACTION_DONE);
    }

    FetchRunnable(@NonNull Context context2, long j, @NonNull String str, @NonNull String str2, @NonNull List<Header> list, long j2, boolean z, long j3) {
        if (context2 == null) {
            throw new NullPointerException("Context cannot be null");
        } else if (str == null) {
            throw new NullPointerException("Url cannot be null");
        } else if (str2 != null) {
            if (list == null) {
                this.headers = new ArrayList();
            } else {
                this.headers = list;
            }
            this.id = j;
            this.url = str;
            this.filePath = str2;
            this.fileSize = j2;
            this.context = context2.getApplicationContext();
            this.broadcastManager = LocalBroadcastManager.getInstance(this.context);
            this.databaseHelper = DatabaseHelper.getInstance(this.context);
            this.loggingEnabled = z;
            this.onUpdateInterval = j3;
            this.databaseHelper.setLoggingEnabled(z);
        } else {
            throw new NullPointerException("FilePath cannot be null");
        }
    }

    public void run() {
        try {
            setHttpConnectionPrefs();
            Utils.createFileOrThrow(this.filePath);
            this.downloadedBytes = Utils.getFileSize(this.filePath);
            this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
            this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
            HttpURLConnection httpURLConnection2 = this.httpURLConnection;
            httpURLConnection2.setRequestProperty("Range", "bytes=" + this.downloadedBytes + "-");
            if (!isInterrupted()) {
                this.httpURLConnection.connect();
                int responseCode = this.httpURLConnection.getResponseCode();
                if (!isResponseOk(responseCode)) {
                    throw new IllegalStateException("SSRV:" + responseCode);
                } else if (!isInterrupted()) {
                    if (this.fileSize < 1) {
                        setContentLength();
                        this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                        this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                    }
                    this.output = new RandomAccessFile(this.filePath, "rw");
                    if (responseCode == 206) {
                        this.output.seek(this.downloadedBytes);
                    } else {
                        this.output.seek(0);
                    }
                    this.input = new BufferedInputStream(this.httpURLConnection.getInputStream());
                    writeToFileAndPost();
                    this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                    if (!isInterrupted()) {
                        if (this.downloadedBytes >= this.fileSize && !isInterrupted()) {
                            if (this.fileSize < 1) {
                                this.fileSize = Utils.getFileSize(this.filePath);
                                this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                                this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                            } else {
                                this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                            }
                            if (this.databaseHelper.updateStatus(this.id, FetchConst.STATUS_DONE, -1)) {
                                Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_DONE, this.progress, this.downloadedBytes, this.fileSize, -1);
                            }
                        }
                        release();
                        broadcastDone();
                        return;
                    }
                    throw new DownloadInterruptedException("DIE", -118);
                } else {
                    throw new DownloadInterruptedException("DIE", -118);
                }
            } else {
                throw new DownloadInterruptedException("DIE", -118);
            }
        } catch (Exception e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            int code = ErrorUtils.getCode(e.getMessage());
            if (canRetry(code)) {
                if (this.databaseHelper.updateStatus(this.id, FetchConst.STATUS_QUEUED, -1)) {
                    Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_QUEUED, this.progress, this.downloadedBytes, this.fileSize, -1);
                }
            } else if (this.databaseHelper.updateStatus(this.id, FetchConst.STATUS_ERROR, code)) {
                Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_ERROR, this.progress, this.downloadedBytes, this.fileSize, code);
            }
        } catch (Throwable th) {
            release();
            broadcastDone();
            throw th;
        }
    }

    private void setHttpConnectionPrefs() throws IOException {
        this.httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.setReadTimeout(TapdaqError.TAPJOY_SDK_ERROR);
        this.httpURLConnection.setConnectTimeout(TapdaqError.HYPRMX_FAILED_TO_LOAD_AD);
        this.httpURLConnection.setUseCaches(false);
        this.httpURLConnection.setDefaultUseCaches(false);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (Header next : this.headers) {
            this.httpURLConnection.addRequestProperty(next.getHeader(), next.getValue());
        }
    }

    private void setContentLength() {
        try {
            this.fileSize = this.downloadedBytes + Long.valueOf(this.httpURLConnection.getHeaderField("Content-Length")).longValue();
        } catch (Exception unused) {
            this.fileSize = -1;
        }
    }

    private void writeToFileAndPost() throws IOException {
        byte[] bArr = new byte[1024];
        long nanoTime = System.nanoTime();
        while (true) {
            int read = this.input.read(bArr, 0, 1024);
            if (read != -1 && !isInterrupted()) {
                this.output.write(bArr, 0, read);
                this.downloadedBytes += (long) read;
                if (Utils.hasIntervalElapsed(nanoTime, System.nanoTime(), this.onUpdateInterval) && !isInterrupted()) {
                    this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                    Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_DOWNLOADING, this.progress, this.downloadedBytes, this.fileSize, -1);
                    this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                    nanoTime = System.nanoTime();
                }
            } else {
                return;
            }
        }
    }

    private boolean canRetry(int i) {
        if (Utils.isNetworkAvailable(this.context) && i != -118) {
            switch (i) {
                case FetchConst.ERROR_CONNECTION_TIMEOUT:
                case AppLovinErrorCodes.NO_NETWORK:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void release() {
        try {
            if (this.input != null) {
                this.input.close();
            }
        } catch (IOException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            if (this.output != null) {
                this.output.close();
            }
        } catch (IOException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        if (this.httpURLConnection != null) {
            this.httpURLConnection.disconnect();
        }
    }

    private void broadcastDone() {
        Intent intent = new Intent(ACTION_DONE);
        intent.putExtra("com.tonyodev.fetch.extra_id", this.id);
        this.broadcastManager.sendBroadcast(intent);
    }

    private boolean isInterrupted() {
        return this.interrupted;
    }

    /* access modifiers changed from: package-private */
    public synchronized void interrupt() {
        this.interrupted = true;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getId() {
        return this.id;
    }

    static long getIdFromIntent(Intent intent) {
        if (intent == null) {
            return -1;
        }
        return intent.getLongExtra("com.tonyodev.fetch.extra_id", -1);
    }
}
