package com.tonyodev.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.tonyodev.fetch.FetchCallRunnable;
import com.tonyodev.fetch.callback.FetchCall;
import com.tonyodev.fetch.callback.FetchTask;
import com.tonyodev.fetch.exception.EnqueueException;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Header;
import com.tonyodev.fetch.request.Request;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Fetch implements FetchConst {
    private static final FetchCallRunnable.Callback callsCallback = new FetchCallRunnable.Callback() {
        public void onDone(Request request) {
            Fetch.callsMap.remove(request);
        }
    };
    /* access modifiers changed from: private */
    public static final ConcurrentMap<Request, FetchCallRunnable> callsMap = new ConcurrentHashMap();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final LocalBroadcastManager broadcastManager;
    /* access modifiers changed from: private */
    public final Context context;
    private final DatabaseHelper dbHelper;
    private volatile boolean isReleased = false;
    private final List<FetchListener> listeners = new ArrayList();
    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            FetchService.processPendingRequests(context);
        }
    };
    private final BroadcastReceiver updateReceiver = new BroadcastReceiver() {
        private long downloadedBytes;
        private int error;
        private long fileSize;
        private long id;
        private int progress;
        private int status;

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                this.id = intent.getLongExtra(FetchService.EXTRA_ID, -1);
                this.status = intent.getIntExtra(FetchService.EXTRA_STATUS, -1);
                this.progress = intent.getIntExtra(FetchService.EXTRA_PROGRESS, -1);
                this.downloadedBytes = intent.getLongExtra(FetchService.EXTRA_DOWNLOADED_BYTES, -1);
                this.fileSize = intent.getLongExtra(FetchService.EXTRA_FILE_SIZE, -1);
                this.error = intent.getIntExtra(FetchService.EXTRA_ERROR, -1);
                try {
                    Iterator access$200 = Fetch.this.getListenerIterator();
                    while (access$200.hasNext()) {
                        ((FetchListener) access$200.next()).onUpdate(this.id, this.status, this.progress, this.downloadedBytes, this.fileSize, this.error);
                    }
                } catch (Exception e) {
                    if (Fetch.this.isLoggingEnabled()) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private Fetch(Context context2) {
        this.context = context2.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(this.context);
        this.dbHelper = DatabaseHelper.getInstance(this.context);
        this.dbHelper.setLoggingEnabled(isLoggingEnabled());
        this.broadcastManager.registerReceiver(this.updateReceiver, FetchService.getEventUpdateFilter());
        this.context.registerReceiver(this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        startService(this.context);
    }

    public static void startService(@NonNull Context context2) {
        FetchService.processPendingRequests(context2);
    }

    public static Fetch getInstance(@NonNull Context context2) {
        return newInstance(context2);
    }

    public static Fetch newInstance(@NonNull Context context2) {
        if (context2 != null) {
            return new Fetch(context2);
        }
        throw new NullPointerException("Context cannot be null");
    }

    public static void call(@NonNull Request request, @NonNull FetchCall<String> fetchCall) {
        if (request == null) {
            throw new NullPointerException("Request cannot be null");
        } else if (fetchCall == null) {
            throw new NullPointerException("FetchCall cannot be null");
        } else if (!callsMap.containsKey(request)) {
            FetchCallRunnable fetchCallRunnable = new FetchCallRunnable(request, fetchCall, callsCallback);
            callsMap.put(request, fetchCallRunnable);
            new Thread(fetchCallRunnable).start();
        }
    }

    public static void cancelCall(@NonNull Request request) {
        FetchCallRunnable fetchCallRunnable;
        if (request != null && callsMap.containsKey(request) && (fetchCallRunnable = (FetchCallRunnable) callsMap.get(request)) != null) {
            fetchCallRunnable.interrupt();
        }
    }

    public void release() {
        if (!isReleased()) {
            setReleased(true);
            this.listeners.clear();
            this.broadcastManager.unregisterReceiver(this.updateReceiver);
            this.context.unregisterReceiver(this.networkReceiver);
        }
    }

    public void addFetchListener(@NonNull FetchListener fetchListener) {
        Utils.throwIfNotUsable(this);
        if (fetchListener == null) {
            throw new NullPointerException("fetchListener cannot be null");
        } else if (!this.listeners.contains(fetchListener)) {
            this.listeners.add(fetchListener);
        }
    }

    public void removeFetchListener(@NonNull FetchListener fetchListener) {
        Utils.throwIfNotUsable(this);
        if (fetchListener != null) {
            this.listeners.remove(fetchListener);
        }
    }

    public void removeFetchListeners() {
        Utils.throwIfNotUsable(this);
        this.listeners.clear();
    }

    public long enqueue(@NonNull Request request) {
        Utils.throwIfNotUsable(this);
        if (request != null) {
            long generateRequestId = Utils.generateRequestId();
            try {
                String url = request.getUrl();
                String filePath = request.getFilePath();
                int priority = request.getPriority();
                String headerListToString = Utils.headerListToString(request.getHeaders(), isLoggingEnabled());
                long j = 0;
                File file = Utils.getFile(filePath);
                if (file.exists()) {
                    j = file.length();
                }
                if (this.dbHelper.insert(generateRequestId, url, filePath, FetchConst.STATUS_QUEUED, headerListToString, j, 0, priority, -1)) {
                    startService(this.context);
                    return generateRequestId;
                }
                throw new EnqueueException("could not insert request", FetchConst.ERROR_ENQUEUE_ERROR);
            } catch (EnqueueException e) {
                if (isLoggingEnabled()) {
                    e.printStackTrace();
                }
                return -1;
            }
        } else {
            throw new NullPointerException("Request cannot be null");
        }
    }

    @NonNull
    public List<Long> enqueue(@NonNull List<Request> list) {
        long j;
        Utils.throwIfNotUsable(this);
        if (list != null) {
            if (list.size() < 1) {
                return new ArrayList(0);
            }
            ArrayList arrayList = new ArrayList(list.size());
            new ArrayList();
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.dbHelper.getInsertStatementOpen());
                for (Request next : list) {
                    if (next != null) {
                        j = Utils.generateRequestId();
                        String url = next.getUrl();
                        String filePath = next.getFilePath();
                        String headerListToString = Utils.headerListToString(next.getHeaders(), isLoggingEnabled());
                        int priority = next.getPriority();
                        long j2 = 0;
                        File file = Utils.getFile(filePath);
                        if (file.exists()) {
                            j2 = file.length();
                        }
                        sb.append(this.dbHelper.getRowInsertStatement(j, url, filePath, FetchConst.STATUS_QUEUED, headerListToString, j2, 0, priority, -1));
                        sb.append(", ");
                    } else {
                        j = -1;
                    }
                    arrayList.add(Long.valueOf(j));
                }
                sb.delete(sb.length() - 2, sb.length()).append(this.dbHelper.getInsertStatementClose());
                if (this.dbHelper.insert(sb.toString())) {
                    startService(this.context);
                    return arrayList;
                }
                throw new EnqueueException("could not insert requests", FetchConst.ERROR_ENQUEUE_ERROR);
            } catch (EnqueueException e) {
                if (isLoggingEnabled()) {
                    e.printStackTrace();
                }
                arrayList.clear();
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(-1L);
                }
            }
        } else {
            throw new NullPointerException("Request list cannot be null");
        }
    }

    public void remove(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void removeAll() {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE_ALL);
        FetchService.sendToService(this.context, bundle);
    }

    public void removeRequest(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE_REQUEST);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void removeRequests() {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE_REQUEST_ALL);
        FetchService.sendToService(this.context, bundle);
    }

    public void pause(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_PAUSE);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void resume(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_RESUME);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void setAllowedNetwork(int i) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setAllowedNetwork(i).apply();
    }

    public void setPriority(long j, int i) {
        Utils.throwIfNotUsable(this);
        int i2 = FetchConst.PRIORITY_HIGH;
        if (i != 601) {
            i2 = FetchConst.PRIORITY_NORMAL;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_PRIORITY);
        bundle.putLong(FetchService.EXTRA_ID, j);
        bundle.putInt(FetchService.EXTRA_PRIORITY, i2);
        FetchService.sendToService(this.context, bundle);
    }

    public void retry(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_RETRY);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    @Nullable
    public synchronized RequestInfo get(long j) {
        Utils.throwIfNotUsable(this);
        return Utils.cursorToRequestInfo(this.dbHelper.get(j), true, isLoggingEnabled());
    }

    @NonNull
    public synchronized List<RequestInfo> get() {
        Utils.throwIfNotUsable(this);
        return Utils.cursorToRequestInfoList(this.dbHelper.get(), true, isLoggingEnabled());
    }

    @NonNull
    public synchronized List<RequestInfo> get(long... jArr) {
        Utils.throwIfNotUsable(this);
        if (jArr == null) {
            return new ArrayList();
        }
        return Utils.cursorToRequestInfoList(this.dbHelper.get(jArr), true, isLoggingEnabled());
    }

    @NonNull
    public synchronized List<RequestInfo> getByStatus(int i) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfInvalidStatus(i);
        return Utils.cursorToRequestInfoList(this.dbHelper.getByStatus(i), true, isLoggingEnabled());
    }

    @Nullable
    public synchronized RequestInfo get(@NonNull Request request) {
        Utils.throwIfNotUsable(this);
        if (request != null) {
        } else {
            throw new NullPointerException("Request cannot be null.");
        }
        return Utils.cursorToRequestInfo(this.dbHelper.getByUrlAndFilePath(request.getUrl(), request.getFilePath()), true, isLoggingEnabled());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        return null;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.io.File getDownloadedFile(long r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.tonyodev.fetch.Utils.throwIfNotUsable(r2)     // Catch:{ all -> 0x0033 }
            com.tonyodev.fetch.DatabaseHelper r0 = r2.dbHelper     // Catch:{ all -> 0x0033 }
            android.database.Cursor r3 = r0.get((long) r3)     // Catch:{ all -> 0x0033 }
            r4 = 1
            boolean r0 = r2.isLoggingEnabled()     // Catch:{ all -> 0x0033 }
            com.tonyodev.fetch.request.RequestInfo r3 = com.tonyodev.fetch.Utils.cursorToRequestInfo(r3, r4, r0)     // Catch:{ all -> 0x0033 }
            r4 = 0
            if (r3 == 0) goto L_0x0031
            int r0 = r3.getStatus()     // Catch:{ all -> 0x0033 }
            r1 = 903(0x387, float:1.265E-42)
            if (r0 == r1) goto L_0x001f
            goto L_0x0031
        L_0x001f:
            java.lang.String r3 = r3.getFilePath()     // Catch:{ all -> 0x0033 }
            java.io.File r3 = com.tonyodev.fetch.Utils.getFile(r3)     // Catch:{ all -> 0x0033 }
            boolean r0 = r3.exists()     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x002f
            monitor-exit(r2)
            return r3
        L_0x002f:
            monitor-exit(r2)
            return r4
        L_0x0031:
            monitor-exit(r2)
            return r4
        L_0x0033:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.Fetch.getDownloadedFile(long):java.io.File");
    }

    @Nullable
    public synchronized String getFilePath(long j) {
        Utils.throwIfNotUsable(this);
        RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.dbHelper.get(j), true, isLoggingEnabled());
        if (cursorToRequestInfo == null) {
            return null;
        }
        return cursorToRequestInfo.getFilePath();
    }

    public long addCompletedDownload(@NonNull String str) {
        String str2 = str;
        Utils.throwIfNotUsable(this);
        if (str2 != null) {
            try {
                if (Utils.fileExist(str)) {
                    long generateRequestId = Utils.generateRequestId();
                    File file = Utils.getFile(str);
                    String uri = Uri.fromFile(file).toString();
                    String headerListToString = Utils.headerListToString((List<Header>) null, isLoggingEnabled());
                    long length = file.length();
                    if (this.dbHelper.insert(generateRequestId, uri, str, FetchConst.STATUS_DONE, headerListToString, length, length, FetchConst.PRIORITY_NORMAL, -1)) {
                        return generateRequestId;
                    }
                    throw new EnqueueException("could not insert request:" + str2, FetchConst.ERROR_ENQUEUE_ERROR);
                }
                throw new EnqueueException("File does not exist at filePath: " + str2, -102);
            } catch (EnqueueException e) {
                if (isLoggingEnabled()) {
                    e.printStackTrace();
                }
                return -1;
            }
        } else {
            throw new NullPointerException("File path cannot be null");
        }
    }

    @NonNull
    public List<Long> addCompletedDownloads(@NonNull List<String> list) {
        long j;
        Utils.throwIfNotUsable(this);
        if (list != null) {
            if (list.size() < 1) {
                return new ArrayList(0);
            }
            ArrayList arrayList = new ArrayList(list.size());
            new ArrayList();
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.dbHelper.getInsertStatementOpen());
                Iterator<String> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (next != null) {
                        File file = Utils.getFile(next);
                        if (!file.exists()) {
                            break;
                        }
                        j = Utils.generateRequestId();
                        String uri = Uri.fromFile(file).toString();
                        String headerListToString = Utils.headerListToString((List<Header>) null, isLoggingEnabled());
                        long length = file.length();
                        sb.append(this.dbHelper.getRowInsertStatement(j, uri, next, FetchConst.STATUS_DONE, headerListToString, length, length, FetchConst.PRIORITY_NORMAL, -1));
                        sb.append(",");
                    } else {
                        j = -1;
                    }
                    arrayList.add(Long.valueOf(j));
                }
                sb.delete(sb.length() - 2, sb.length()).append(this.dbHelper.getInsertStatementClose());
                if (this.dbHelper.insert(sb.toString())) {
                    return arrayList;
                }
                throw new EnqueueException("could not insert requests", FetchConst.ERROR_ENQUEUE_ERROR);
            } catch (EnqueueException e) {
                if (isLoggingEnabled()) {
                    e.printStackTrace();
                }
                arrayList.clear();
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(-1L);
                }
            }
        } else {
            throw new NullPointerException("Request list cannot be null");
        }
    }

    public void runOnBackgroundThread(@NonNull final FetchTask fetchTask) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfFetchTaskNull(fetchTask);
        new Thread(new Runnable() {
            public void run() {
                Fetch newInstance = Fetch.newInstance(Fetch.this.context);
                fetchTask.onProcess(newInstance);
                newInstance.release();
            }
        }).start();
    }

    public void runOnMainThread(@NonNull final FetchTask fetchTask) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfFetchTaskNull(fetchTask);
        mainHandler.post(new Runnable() {
            public void run() {
                Fetch newInstance = Fetch.newInstance(Fetch.this.context);
                fetchTask.onProcess(newInstance);
                newInstance.release();
            }
        });
    }

    public synchronized boolean contains(@NonNull Request request) {
        Utils.throwIfNotUsable(this);
        if (request != null) {
        } else {
            throw new NullPointerException("Request cannot be null.");
        }
        return Utils.containsRequest(this.dbHelper.getByUrlAndFilePath(request.getUrl(), request.getFilePath()), true);
    }

    public boolean isValid() {
        return !isReleased();
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.isReleased;
    }

    private void setReleased(boolean z) {
        this.isReleased = z;
    }

    /* access modifiers changed from: private */
    public boolean isLoggingEnabled() {
        return FetchService.isLoggingEnabled(this.context);
    }

    public void enableLogging(boolean z) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).enableLogging(z).apply();
    }

    public void setConcurrentDownloadsLimit(int i) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setConcurrentDownloadsLimit(i).apply();
    }

    public void setOnUpdateInterval(long j) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setOnUpdateInterval(j).apply();
    }

    public void updateUrlForRequest(long j, @Nullable String str) {
        Utils.throwIfNotUsable(this);
        if (str != null) {
            Utils.throwIfInvalidUrl(str);
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, 322);
            bundle.putLong(FetchService.EXTRA_ID, j);
            bundle.putString(FetchService.EXTRA_URL, str);
            FetchService.sendToService(this.context, bundle);
            return;
        }
        throw new NullPointerException("Url cannot be null");
    }

    /* access modifiers changed from: private */
    public Iterator<FetchListener> getListenerIterator() {
        return this.listeners.iterator();
    }

    public static class Settings {
        private final Context context;
        private final List<Bundle> settings = new ArrayList();

        public Settings(@NonNull Context context2) {
            if (context2 != null) {
                this.context = context2;
                return;
            }
            throw new NullPointerException("Context cannot be null");
        }

        public Settings enableLogging(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, 320);
            bundle.putBoolean(FetchService.EXTRA_LOGGING_ID, z);
            this.settings.add(bundle);
            return this;
        }

        public Settings setAllowedNetwork(int i) {
            int i2 = FetchConst.NETWORK_WIFI;
            if (i != 201) {
                i2 = 200;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_NETWORK);
            bundle.putInt(FetchService.EXTRA_NETWORK_ID, i2);
            this.settings.add(bundle);
            return this;
        }

        public Settings setConcurrentDownloadsLimit(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, 321);
            bundle.putInt(FetchService.EXTRA_CONCURRENT_DOWNLOADS_LIMIT, i);
            this.settings.add(bundle);
            return this;
        }

        public Settings setOnUpdateInterval(long j) {
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_ON_UPDATE_INTERVAL);
            bundle.putLong(FetchService.EXTRA_ON_UPDATE_INTERVAL, j);
            this.settings.add(bundle);
            return this;
        }

        public void apply() {
            for (Bundle sendToService : this.settings) {
                FetchService.sendToService(this.context, sendToService);
            }
        }
    }
}
