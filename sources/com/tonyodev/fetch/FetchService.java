package com.tonyodev.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.tonyodev.fetch.request.RequestInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class FetchService implements FetchConst {
    public static final int ACTION_CONCURRENT_DOWNLOADS_LIMIT = 321;
    public static final int ACTION_ENQUEUE = 310;
    public static final int ACTION_LOGGING = 320;
    public static final int ACTION_NETWORK = 314;
    public static final int ACTION_ON_UPDATE_INTERVAL = 323;
    public static final int ACTION_PAUSE = 311;
    public static final int ACTION_PRIORITY = 317;
    public static final int ACTION_PROCESS_PENDING = 315;
    public static final int ACTION_QUERY = 316;
    public static final int ACTION_REMOVE = 313;
    public static final int ACTION_REMOVE_ALL = 319;
    public static final int ACTION_REMOVE_REQUEST = 324;
    public static final int ACTION_REMOVE_REQUEST_ALL = 325;
    public static final int ACTION_RESUME = 312;
    public static final int ACTION_RETRY = 318;
    public static final String ACTION_TYPE = "com.tonyodev.fetch.action_type";
    public static final int ACTION_UPDATE_REQUEST_URL = 322;
    public static final String EVENT_ACTION_ENQUEUED = "com.tonyodev.fetch.event_action_enqueued";
    public static final String EVENT_ACTION_ENQUEUE_FAILED = "com.tonyodev.fetch.event_action_enqueue_failed";
    public static final String EVENT_ACTION_QUERY = "com.tonyodev.fetch.event_action_query";
    public static final String EVENT_ACTION_UPDATE = "com.tonyodev.fetch.event_action_update";
    public static final String EXTRA_CONCURRENT_DOWNLOADS_LIMIT = "com.tonyodev.fetch.extra_concurrent_download_limit";
    public static final String EXTRA_DOWNLOADED_BYTES = "com.tonyodev.fetch.extra_downloaded_bytes";
    public static final String EXTRA_ERROR = "com.tonyodev.fetch.extra_error";
    public static final String EXTRA_FILE_PATH = "com.tonyodev.fetch.extra_file_path";
    public static final String EXTRA_FILE_SIZE = "com.tonyodev.fetch.extra_file_size";
    public static final String EXTRA_HEADERS = "com.tonyodev.fetch.extra_headers";
    public static final String EXTRA_HEADER_NAME = "com.tonyodev.fetch.extra_header_name";
    public static final String EXTRA_HEADER_VALUE = "com.tonyodev.fetch.extra_header_value";
    public static final String EXTRA_ID = "com.tonyodev.fetch.extra_id";
    public static final String EXTRA_LOGGING_ID = "com.tonyodev.fetch.extra_logging_id";
    public static final String EXTRA_NETWORK_ID = "com.tonyodev.fetch.extra_network_id";
    public static final String EXTRA_ON_UPDATE_INTERVAL = "com.tonyodev.fetch.extra_on_update_interval";
    public static final String EXTRA_PRIORITY = "com.tonyodev.fetch.extra_priority";
    public static final String EXTRA_PROGRESS = "com.tonyodev.fetch.extra_progress";
    public static final String EXTRA_QUERY_ID = "com.tonyodev.fetch.extra_query_id";
    public static final String EXTRA_QUERY_RESULT = "com.tonyodev.fetch.extra_query_result";
    public static final String EXTRA_QUERY_TYPE = "com.tonyodev.fetch.extra_query_type";
    public static final String EXTRA_STATUS = "com.tonyodev.fetch.extra_status";
    public static final String EXTRA_URL = "com.tonyodev.fetch.extra_url";
    public static final int QUERY_ALL = 481;
    public static final int QUERY_BY_STATUS = 482;
    public static final int QUERY_SINGLE = 480;
    private static final String SHARED_PREFERENCES = "com.tonyodev.fetch.shared_preferences";
    private static FetchService fetchService;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<Long, FetchRunnable> activeDownloads = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public final LocalBroadcastManager broadcastManager;
    private final Context context;
    /* access modifiers changed from: private */
    public final DatabaseHelper databaseHelper;
    private final BroadcastReceiver doneReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                if (FetchService.this.activeDownloads.containsKey(Long.valueOf(idFromIntent))) {
                    FetchService.this.activeDownloads.remove(Long.valueOf(idFromIntent));
                }
                FetchService.this.startDownload();
            }
        }
    };
    private int downloadsLimit = 1;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private boolean loggingEnabled = true;
    private long onUpdateInterval = FetchConst.DEFAULT_ON_UPDATE_INTERVAL;
    private int preferredNetwork = 200;
    /* access modifiers changed from: private */
    public final List<BroadcastReceiver> registeredReceivers = new ArrayList();
    /* access modifiers changed from: private */
    public volatile boolean runningTask = false;
    private final SharedPreferences sharedPreferences;
    private volatile boolean shuttingDown = false;

    public static void sendToService(@NonNull Context context2, @Nullable Bundle bundle) {
        if (context2 != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            getInstance(context2).runAction(bundle);
            return;
        }
        throw new NullPointerException("Context cannot be null");
    }

    public static void processPendingRequests(@NonNull Context context2) {
        if (context2 != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(ACTION_TYPE, ACTION_PROCESS_PENDING);
            getInstance(context2).runAction(bundle);
            return;
        }
        throw new NullPointerException("Context cannot be null");
    }

    public static FetchService getInstance(@NonNull Context context2) {
        if (context2 != null) {
            if (fetchService == null || fetchService.shuttingDown) {
                fetchService = new FetchService(context2);
            }
            return fetchService;
        }
        throw new IllegalArgumentException("context cannot be null");
    }

    @NonNull
    public static IntentFilter getEventEnqueuedFilter() {
        return new IntentFilter(EVENT_ACTION_ENQUEUED);
    }

    @NonNull
    public static IntentFilter getEventEnqueueFailedFilter() {
        return new IntentFilter(EVENT_ACTION_ENQUEUE_FAILED);
    }

    @NonNull
    public static IntentFilter getEventUpdateFilter() {
        return new IntentFilter(EVENT_ACTION_UPDATE);
    }

    @NonNull
    public static IntentFilter getEventQueryFilter() {
        return new IntentFilter(EVENT_ACTION_QUERY);
    }

    private FetchService(@NonNull Context context2) {
        this.context = context2.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(context2);
        this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFERENCES, 0);
        this.databaseHelper = DatabaseHelper.getInstance(context2);
        this.broadcastManager.registerReceiver(this.doneReceiver, FetchRunnable.getDoneFilter());
        this.registeredReceivers.add(this.doneReceiver);
        this.downloadsLimit = getDownloadsLimit();
        this.preferredNetwork = getAllowedNetwork();
        this.loggingEnabled = isLoggingEnabled();
        this.onUpdateInterval = getOnUpdateInterval();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        if (!this.executor.isShutdown()) {
            this.executor.execute(new Runnable() {
                public void run() {
                    FetchService.this.databaseHelper.clean();
                    FetchService.this.databaseHelper.verifyOK();
                }
            });
        }
    }

    public void runAction(@NonNull Bundle bundle) {
        if (bundle != null) {
            processAction(bundle);
            return;
        }
        throw new IllegalArgumentException("Bundle cannot be null");
    }

    public void shutdown() {
        this.shuttingDown = true;
        if (!this.executor.isShutdown()) {
            this.executor.shutdown();
        }
        interruptActiveDownloads();
        for (BroadcastReceiver unregisterReceiver : this.registeredReceivers) {
            this.broadcastManager.unregisterReceiver(unregisterReceiver);
        }
        this.registeredReceivers.clear();
    }

    private void processAction(final Bundle bundle) {
        try {
            if (!this.executor.isShutdown()) {
                this.executor.execute(new Runnable() {
                    public void run() {
                        FetchService.this.databaseHelper.clean();
                        long j = bundle.getLong(FetchService.EXTRA_ID, -1);
                        switch (bundle.getInt(FetchService.ACTION_TYPE, -1)) {
                            case 310:
                                FetchService.this.enqueue(bundle.getString(FetchService.EXTRA_URL), bundle.getString(FetchService.EXTRA_FILE_PATH), bundle.getParcelableArrayList(FetchService.EXTRA_HEADERS), bundle.getInt(FetchService.EXTRA_PRIORITY, FetchConst.PRIORITY_NORMAL));
                                return;
                            case FetchService.ACTION_PAUSE /*311*/:
                                FetchService.this.pause(j);
                                return;
                            case FetchService.ACTION_RESUME /*312*/:
                                FetchService.this.resume(j);
                                return;
                            case FetchService.ACTION_REMOVE /*313*/:
                                FetchService.this.remove(j);
                                return;
                            case FetchService.ACTION_NETWORK /*314*/:
                                FetchService.this.setAllowedNetwork(bundle.getInt(FetchService.EXTRA_NETWORK_ID, 200));
                                return;
                            case FetchService.ACTION_PROCESS_PENDING /*315*/:
                                FetchService.this.startDownload();
                                return;
                            case FetchService.ACTION_QUERY /*316*/:
                                long j2 = bundle.getLong(FetchService.EXTRA_QUERY_ID, -1);
                                FetchService.this.query(bundle.getInt(FetchService.EXTRA_QUERY_TYPE, FetchService.QUERY_ALL), j2, j, bundle.getInt(FetchService.EXTRA_STATUS, -1));
                                return;
                            case FetchService.ACTION_PRIORITY /*317*/:
                                FetchService.this.setRequestPriority(j, bundle.getInt(FetchService.EXTRA_PRIORITY, FetchConst.PRIORITY_NORMAL));
                                return;
                            case FetchService.ACTION_RETRY /*318*/:
                                FetchService.this.retry(j);
                                return;
                            case FetchService.ACTION_REMOVE_ALL /*319*/:
                                FetchService.this.removeAll();
                                return;
                            case 320:
                                FetchService.this.setLoggingEnabled(bundle.getBoolean(FetchService.EXTRA_LOGGING_ID, true));
                                return;
                            case 321:
                                FetchService.this.setDownloadsLimit(bundle.getInt(FetchService.EXTRA_CONCURRENT_DOWNLOADS_LIMIT, 1));
                                return;
                            case 322:
                                FetchService.this.updateRequestUrl(j, bundle.getString(FetchService.EXTRA_URL));
                                return;
                            case FetchService.ACTION_ON_UPDATE_INTERVAL /*323*/:
                                FetchService.this.setOnUpdateInterval(bundle.getLong(FetchService.EXTRA_ON_UPDATE_INTERVAL, FetchConst.DEFAULT_ON_UPDATE_INTERVAL));
                                return;
                            case FetchService.ACTION_REMOVE_REQUEST /*324*/:
                                FetchService.this.removeRequest(j);
                                return;
                            case FetchService.ACTION_REMOVE_REQUEST_ALL /*325*/:
                                FetchService.this.removeRequestAll();
                                return;
                            default:
                                FetchService.this.startDownload();
                                return;
                        }
                    }
                });
            }
        } catch (Exception e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e4, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startDownload() {
        /*
            r15 = this;
            monitor-enter(r15)
            boolean r0 = r15.shuttingDown     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00e5
            boolean r0 = r15.runningTask     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x000b
            goto L_0x00e5
        L_0x000b:
            android.content.Context r0 = r15.context     // Catch:{ all -> 0x00e7 }
            boolean r0 = com.tonyodev.fetch.Utils.isNetworkAvailable(r0)     // Catch:{ all -> 0x00e7 }
            android.content.Context r1 = r15.context     // Catch:{ all -> 0x00e7 }
            boolean r1 = com.tonyodev.fetch.Utils.isOnWiFi(r1)     // Catch:{ all -> 0x00e7 }
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0023
            int r4 = r15.preferredNetwork     // Catch:{ all -> 0x00e7 }
            r5 = 201(0xc9, float:2.82E-43)
            if (r4 != r5) goto L_0x0034
            if (r1 != 0) goto L_0x0034
        L_0x0023:
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.tonyodev.fetch.FetchRunnable> r1 = r15.activeDownloads     // Catch:{ all -> 0x00e7 }
            int r1 = r1.size()     // Catch:{ all -> 0x00e7 }
            if (r1 <= 0) goto L_0x0034
            r15.runningTask = r3     // Catch:{ all -> 0x00e7 }
            r15.interruptActiveDownloads()     // Catch:{ all -> 0x00e7 }
            r15.runningTask = r2     // Catch:{ all -> 0x00e7 }
            goto L_0x00e3
        L_0x0034:
            if (r0 == 0) goto L_0x00ca
            boolean r0 = r15.runningTask     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00ca
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.tonyodev.fetch.FetchRunnable> r0 = r15.activeDownloads     // Catch:{ all -> 0x00e7 }
            int r0 = r0.size()     // Catch:{ all -> 0x00e7 }
            int r1 = r15.downloadsLimit     // Catch:{ all -> 0x00e7 }
            if (r0 >= r1) goto L_0x00ca
            com.tonyodev.fetch.DatabaseHelper r0 = r15.databaseHelper     // Catch:{ all -> 0x00e7 }
            boolean r0 = r0.hasPendingRequests()     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x00ca
            r15.runningTask = r3     // Catch:{ all -> 0x00e7 }
            com.tonyodev.fetch.DatabaseHelper r0 = r15.databaseHelper     // Catch:{ Exception -> 0x00aa }
            android.database.Cursor r0 = r0.getNextPendingRequest()     // Catch:{ Exception -> 0x00aa }
            if (r0 == 0) goto L_0x00b2
            boolean r1 = r0.isClosed()     // Catch:{ Exception -> 0x00aa }
            if (r1 != 0) goto L_0x00b2
            int r1 = r0.getCount()     // Catch:{ Exception -> 0x00aa }
            if (r1 <= 0) goto L_0x00b2
            boolean r1 = r15.loggingEnabled     // Catch:{ Exception -> 0x00aa }
            com.tonyodev.fetch.request.RequestInfo r0 = com.tonyodev.fetch.Utils.cursorToRequestInfo(r0, r3, r1)     // Catch:{ Exception -> 0x00aa }
            com.tonyodev.fetch.FetchRunnable r1 = new com.tonyodev.fetch.FetchRunnable     // Catch:{ Exception -> 0x00aa }
            android.content.Context r4 = r15.context     // Catch:{ Exception -> 0x00aa }
            long r5 = r0.getId()     // Catch:{ Exception -> 0x00aa }
            java.lang.String r7 = r0.getUrl()     // Catch:{ Exception -> 0x00aa }
            java.lang.String r8 = r0.getFilePath()     // Catch:{ Exception -> 0x00aa }
            java.util.List r9 = r0.getHeaders()     // Catch:{ Exception -> 0x00aa }
            long r10 = r0.getFileSize()     // Catch:{ Exception -> 0x00aa }
            boolean r12 = r15.loggingEnabled     // Catch:{ Exception -> 0x00aa }
            long r13 = r15.onUpdateInterval     // Catch:{ Exception -> 0x00aa }
            r3 = r1
            r3.<init>(r4, r5, r7, r8, r9, r10, r12, r13)     // Catch:{ Exception -> 0x00aa }
            com.tonyodev.fetch.DatabaseHelper r3 = r15.databaseHelper     // Catch:{ Exception -> 0x00aa }
            long r4 = r0.getId()     // Catch:{ Exception -> 0x00aa }
            r0 = 901(0x385, float:1.263E-42)
            r6 = -1
            r3.updateStatus(r4, r0, r6)     // Catch:{ Exception -> 0x00aa }
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.tonyodev.fetch.FetchRunnable> r0 = r15.activeDownloads     // Catch:{ Exception -> 0x00aa }
            long r3 = r1.getId()     // Catch:{ Exception -> 0x00aa }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ Exception -> 0x00aa }
            r0.put(r3, r1)     // Catch:{ Exception -> 0x00aa }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x00aa }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00aa }
            r0.start()     // Catch:{ Exception -> 0x00aa }
            goto L_0x00b2
        L_0x00aa:
            r0 = move-exception
            boolean r1 = r15.loggingEnabled     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x00b2
            r0.printStackTrace()     // Catch:{ all -> 0x00e7 }
        L_0x00b2:
            r15.runningTask = r2     // Catch:{ all -> 0x00e7 }
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.tonyodev.fetch.FetchRunnable> r0 = r15.activeDownloads     // Catch:{ all -> 0x00e7 }
            int r0 = r0.size()     // Catch:{ all -> 0x00e7 }
            int r1 = r15.downloadsLimit     // Catch:{ all -> 0x00e7 }
            if (r0 >= r1) goto L_0x00e3
            com.tonyodev.fetch.DatabaseHelper r0 = r15.databaseHelper     // Catch:{ all -> 0x00e7 }
            boolean r0 = r0.hasPendingRequests()     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x00e3
            r15.startDownload()     // Catch:{ all -> 0x00e7 }
            goto L_0x00e3
        L_0x00ca:
            boolean r0 = r15.runningTask     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00e3
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.tonyodev.fetch.FetchRunnable> r0 = r15.activeDownloads     // Catch:{ all -> 0x00e7 }
            int r0 = r0.size()     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00e3
            com.tonyodev.fetch.DatabaseHelper r0 = r15.databaseHelper     // Catch:{ all -> 0x00e7 }
            boolean r0 = r0.hasPendingRequests()     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00e3
            r15.shuttingDown = r3     // Catch:{ all -> 0x00e7 }
            r15.shutdown()     // Catch:{ all -> 0x00e7 }
        L_0x00e3:
            monitor-exit(r15)
            return
        L_0x00e5:
            monitor-exit(r15)
            return
        L_0x00e7:
            r0 = move-exception
            monitor-exit(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.FetchService.startDownload():void");
    }

    private void interruptActiveDownloads() {
        for (Long l : this.activeDownloads.keySet()) {
            FetchRunnable fetchRunnable = this.activeDownloads.get(l);
            if (fetchRunnable != null) {
                fetchRunnable.interrupt();
            }
        }
    }

    private void interruptActiveDownload(long j) {
        FetchRunnable fetchRunnable;
        if (this.activeDownloads.containsKey(Long.valueOf(j)) && (fetchRunnable = this.activeDownloads.get(Long.valueOf(j))) != null) {
            fetchRunnable.interrupt();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a4 A[Catch:{ all -> 0x009b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enqueue(java.lang.String r26, java.lang.String r27, java.util.ArrayList<android.os.Bundle> r28, int r29) {
        /*
            r25 = this;
            r11 = r25
            r10 = r26
            r9 = r27
            if (r10 == 0) goto L_0x0078
            if (r9 == 0) goto L_0x0078
            if (r28 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ EnqueueException -> 0x0013 }
            r0.<init>()     // Catch:{ EnqueueException -> 0x0013 }
            r8 = r0
            goto L_0x001c
        L_0x0013:
            r0 = move-exception
            r8 = r28
        L_0x0016:
            r13 = r9
            r14 = r10
            goto L_0x00a0
        L_0x001a:
            r8 = r28
        L_0x001c:
            long r3 = com.tonyodev.fetch.Utils.generateRequestId()     // Catch:{ EnqueueException -> 0x0075 }
            boolean r0 = r11.loggingEnabled     // Catch:{ EnqueueException -> 0x0075 }
            java.lang.String r18 = com.tonyodev.fetch.Utils.bundleListToHeaderString(r8, r0)     // Catch:{ EnqueueException -> 0x0075 }
            r21 = 0
            r0 = 0
            java.io.File r2 = com.tonyodev.fetch.Utils.getFile(r27)     // Catch:{ EnqueueException -> 0x0075 }
            boolean r5 = r2.exists()     // Catch:{ EnqueueException -> 0x0075 }
            if (r5 == 0) goto L_0x003b
            long r0 = r2.length()     // Catch:{ EnqueueException -> 0x0039 }
            goto L_0x003b
        L_0x0039:
            r0 = move-exception
            goto L_0x0016
        L_0x003b:
            r19 = r0
            com.tonyodev.fetch.DatabaseHelper r12 = r11.databaseHelper     // Catch:{ EnqueueException -> 0x0075 }
            r17 = 900(0x384, float:1.261E-42)
            r24 = -1
            r13 = r3
            r15 = r26
            r16 = r27
            r23 = r29
            boolean r0 = r12.insert(r13, r15, r16, r17, r18, r19, r21, r23, r24)     // Catch:{ EnqueueException -> 0x0075 }
            if (r0 == 0) goto L_0x0065
            java.lang.String r2 = "com.tonyodev.fetch.event_action_enqueued"
            r7 = 900(0x384, float:1.261E-42)
            r0 = -1
            r1 = r25
            r5 = r26
            r6 = r27
            r12 = r8
            r13 = r9
            r9 = r29
            r14 = r10
            r10 = r0
            r1.sendEnqueueEvent(r2, r3, r5, r6, r7, r8, r9, r10)     // Catch:{ EnqueueException -> 0x0072 }
            goto L_0x00bc
        L_0x0065:
            r12 = r8
            r13 = r9
            r14 = r10
            com.tonyodev.fetch.exception.EnqueueException r0 = new com.tonyodev.fetch.exception.EnqueueException     // Catch:{ EnqueueException -> 0x0072 }
            java.lang.String r1 = "could not enqueue request"
            r2 = -117(0xffffffffffffff8b, float:NaN)
            r0.<init>(r1, r2)     // Catch:{ EnqueueException -> 0x0072 }
            throw r0     // Catch:{ EnqueueException -> 0x0072 }
        L_0x0072:
            r0 = move-exception
            r8 = r12
            goto L_0x00a0
        L_0x0075:
            r0 = move-exception
            r12 = r8
            goto L_0x0016
        L_0x0078:
            r13 = r9
            r14 = r10
            com.tonyodev.fetch.exception.EnqueueException r0 = new com.tonyodev.fetch.exception.EnqueueException     // Catch:{ EnqueueException -> 0x009d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EnqueueException -> 0x009d }
            r2.<init>()     // Catch:{ EnqueueException -> 0x009d }
            java.lang.String r3 = "Request was not properly formatted. url:"
            r2.append(r3)     // Catch:{ EnqueueException -> 0x009d }
            r2.append(r14)     // Catch:{ EnqueueException -> 0x009d }
            java.lang.String r3 = ", filePath:"
            r2.append(r3)     // Catch:{ EnqueueException -> 0x009d }
            r2.append(r13)     // Catch:{ EnqueueException -> 0x009d }
            java.lang.String r2 = r2.toString()     // Catch:{ EnqueueException -> 0x009d }
            r3 = -116(0xffffffffffffff8c, float:NaN)
            r0.<init>(r2, r3)     // Catch:{ EnqueueException -> 0x009d }
            throw r0     // Catch:{ EnqueueException -> 0x009d }
        L_0x009b:
            r0 = move-exception
            goto L_0x00c0
        L_0x009d:
            r0 = move-exception
            r8 = r28
        L_0x00a0:
            boolean r1 = r11.loggingEnabled     // Catch:{ all -> 0x009b }
            if (r1 == 0) goto L_0x00a7
            r0.printStackTrace()     // Catch:{ all -> 0x009b }
        L_0x00a7:
            java.lang.String r2 = "com.tonyodev.fetch.event_action_enqueue_failed"
            r3 = -1
            r7 = -900(0xfffffffffffffc7c, float:NaN)
            int r10 = r0.getErrorCode()     // Catch:{ all -> 0x009b }
            r1 = r25
            r5 = r26
            r6 = r27
            r9 = r29
            r1.sendEnqueueEvent(r2, r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x009b }
        L_0x00bc:
            r25.startDownload()
            return
        L_0x00c0:
            r25.startDownload()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.FetchService.enqueue(java.lang.String, java.lang.String, java.util.ArrayList, int):void");
    }

    /* access modifiers changed from: private */
    public void resume(long j) {
        RequestInfo cursorToRequestInfo;
        if (!this.activeDownloads.containsKey(Long.valueOf(j))) {
            if (this.databaseHelper.resume(j) && (cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled)) != null) {
                Utils.sendEventUpdate(this.broadcastManager, cursorToRequestInfo.getId(), cursorToRequestInfo.getStatus(), cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), cursorToRequestInfo.getError());
            }
            startDownload();
        }
    }

    /* access modifiers changed from: private */
    public void pause(final long j) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            AnonymousClass3 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == j) {
                        FetchService.this.pauseAction(j);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        boolean unused = FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(r0);
            this.broadcastManager.registerReceiver(r0, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        pauseAction(j);
        startDownload();
    }

    /* access modifiers changed from: private */
    public void pauseAction(long j) {
        RequestInfo cursorToRequestInfo;
        if (this.databaseHelper.pause(j) && (cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled)) != null) {
            Utils.sendEventUpdate(this.broadcastManager, cursorToRequestInfo.getId(), cursorToRequestInfo.getStatus(), cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), cursorToRequestInfo.getError());
        }
    }

    /* access modifiers changed from: private */
    public void remove(final long j) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            AnonymousClass4 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == j) {
                        FetchService.this.removeAction(j);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        boolean unused = FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(r0);
            this.broadcastManager.registerReceiver(r0, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        removeAction(j);
        startDownload();
    }

    /* access modifiers changed from: private */
    public void removeAction(long j) {
        RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled);
        if (cursorToRequestInfo != null && this.databaseHelper.delete(j)) {
            Utils.deleteFile(cursorToRequestInfo.getFilePath());
            Utils.sendEventUpdate(this.broadcastManager, j, FetchConst.STATUS_REMOVED, 0, 0, 0, -1);
        }
    }

    /* access modifiers changed from: private */
    public void removeAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            AnonymousClass5 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        FetchService.this.removeAction(FetchRunnable.getIdFromIntent(intent));
                    }
                    if (FetchService.this.activeDownloads.size() == 0) {
                        FetchService.this.removeAllAction();
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        boolean unused = FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(r0);
            this.broadcastManager.registerReceiver(r0, FetchRunnable.getDoneFilter());
            interruptActiveDownloads();
            return;
        }
        removeAllAction();
        startDownload();
    }

    /* access modifiers changed from: private */
    public void removeAllAction() {
        List<RequestInfo> cursorToRequestInfoList = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (cursorToRequestInfoList != null && this.databaseHelper.deleteAll()) {
            for (RequestInfo next : cursorToRequestInfoList) {
                Utils.deleteFile(next.getFilePath());
                Utils.sendEventUpdate(this.broadcastManager, next.getId(), FetchConst.STATUS_REMOVED, 0, 0, 0, -1);
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeRequestAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            AnonymousClass6 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        FetchService.this.removeRequestAction(FetchRunnable.getIdFromIntent(intent));
                    }
                    if (FetchService.this.activeDownloads.size() == 0) {
                        FetchService.this.removeRequestAllAction();
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        boolean unused = FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(r0);
            this.broadcastManager.registerReceiver(r0, FetchRunnable.getDoneFilter());
            interruptActiveDownloads();
            return;
        }
        removeRequestAllAction();
        startDownload();
    }

    /* access modifiers changed from: private */
    public void removeRequestAllAction() {
        List<RequestInfo> cursorToRequestInfoList = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (cursorToRequestInfoList != null && this.databaseHelper.deleteAll()) {
            for (RequestInfo next : cursorToRequestInfoList) {
                Utils.sendEventUpdate(this.broadcastManager, next.getId(), FetchConst.STATUS_REMOVED, next.getProgress(), next.getDownloadedBytes(), next.getFileSize(), -1);
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeRequest(final long j) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            AnonymousClass7 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == j) {
                        FetchService.this.removeRequestAction(j);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        boolean unused = FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(r0);
            this.broadcastManager.registerReceiver(r0, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        removeRequestAction(j);
        startDownload();
    }

    /* access modifiers changed from: private */
    public void removeRequestAction(long j) {
        RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled);
        if (cursorToRequestInfo != null && this.databaseHelper.delete(j)) {
            Utils.sendEventUpdate(this.broadcastManager, j, FetchConst.STATUS_REMOVED, cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), -1);
        }
    }

    /* access modifiers changed from: private */
    public void query(int i, long j, long j2, int i2) {
        Cursor cursor;
        if (i == 480) {
            cursor = this.databaseHelper.get(j2);
        } else if (i != 482) {
            cursor = this.databaseHelper.get();
        } else {
            cursor = this.databaseHelper.getByStatus(i2);
        }
        sendEventQuery(j, Utils.cursorToQueryResultList(cursor, true, this.loggingEnabled));
        startDownload();
    }

    /* access modifiers changed from: private */
    public void setRequestPriority(long j, int i) {
        if (this.databaseHelper.setPriority(j, i) && this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    /* access modifiers changed from: private */
    public void setAllowedNetwork(int i) {
        this.preferredNetwork = i;
        this.sharedPreferences.edit().putInt(EXTRA_NETWORK_ID, i).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    /* access modifiers changed from: private */
    public void retry(long j) {
        RequestInfo cursorToRequestInfo;
        if (!this.activeDownloads.containsKey(Long.valueOf(j))) {
            if (this.databaseHelper.retry(j) && (cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled)) != null) {
                Utils.sendEventUpdate(this.broadcastManager, cursorToRequestInfo.getId(), cursorToRequestInfo.getStatus(), cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), cursorToRequestInfo.getError());
            }
            startDownload();
        }
    }

    /* access modifiers changed from: private */
    public void updateRequestUrl(final long j, final String str) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            AnonymousClass8 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == j) {
                        FetchService.this.updateRequestUrlAction(j, str);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        boolean unused = FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(r0);
            this.broadcastManager.registerReceiver(r0, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        updateRequestUrlAction(j, str);
        startDownload();
    }

    /* access modifiers changed from: private */
    public void updateRequestUrlAction(long j, String str) {
        this.databaseHelper.updateUrl(j, str);
        this.databaseHelper.retry(j);
    }

    private int getAllowedNetwork() {
        return this.sharedPreferences.getInt(EXTRA_NETWORK_ID, 200);
    }

    private void sendEnqueueEvent(String str, long j, String str2, String str3, int i, ArrayList<Bundle> arrayList, int i2, int i3) {
        Intent intent = new Intent(str);
        intent.putExtra(EXTRA_ID, j);
        intent.putExtra(EXTRA_STATUS, i);
        intent.putExtra(EXTRA_URL, str2);
        intent.putExtra(EXTRA_FILE_PATH, str3);
        intent.putExtra(EXTRA_HEADERS, arrayList);
        intent.putExtra(EXTRA_PROGRESS, 0);
        intent.putExtra(EXTRA_FILE_SIZE, 0);
        intent.putExtra(EXTRA_ERROR, i3);
        intent.putExtra(EXTRA_PRIORITY, i2);
        this.broadcastManager.sendBroadcast(intent);
    }

    private void sendEventQuery(long j, ArrayList<Bundle> arrayList) {
        Intent intent = new Intent(EVENT_ACTION_QUERY);
        intent.putExtra(EXTRA_QUERY_ID, j);
        intent.putExtra(EXTRA_QUERY_RESULT, arrayList);
        this.broadcastManager.sendBroadcast(intent);
    }

    private int getDownloadsLimit() {
        return this.sharedPreferences.getInt(EXTRA_CONCURRENT_DOWNLOADS_LIMIT, 1);
    }

    /* access modifiers changed from: private */
    public void setDownloadsLimit(int i) {
        if (i < 1) {
            i = 1;
        }
        this.downloadsLimit = i;
        this.sharedPreferences.edit().putInt(EXTRA_CONCURRENT_DOWNLOADS_LIMIT, i).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    /* access modifiers changed from: private */
    public void setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        this.sharedPreferences.edit().putBoolean(EXTRA_LOGGING_ID, z).apply();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        startDownload();
    }

    private boolean isLoggingEnabled() {
        return this.sharedPreferences.getBoolean(EXTRA_LOGGING_ID, true);
    }

    static boolean isLoggingEnabled(Context context2) {
        return context2.getSharedPreferences(SHARED_PREFERENCES, 0).getBoolean(EXTRA_LOGGING_ID, true);
    }

    /* access modifiers changed from: private */
    public void setOnUpdateInterval(long j) {
        this.onUpdateInterval = j;
        this.sharedPreferences.edit().putLong(EXTRA_ON_UPDATE_INTERVAL, j).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    private long getOnUpdateInterval() {
        this.onUpdateInterval = this.sharedPreferences.getLong(EXTRA_ON_UPDATE_INTERVAL, FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        return this.onUpdateInterval;
    }
}
