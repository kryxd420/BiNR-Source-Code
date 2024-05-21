package com.unity3d.ads.request;

import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.request.WebRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WebRequestThread {
    private static int _corePoolSize = 1;
    private static long _keepAliveTime = 1000;
    private static int _maximumPoolSize = 1;
    private static CancelableThreadPoolExecutor _pool = null;
    private static LinkedBlockingQueue<Runnable> _queue = null;
    /* access modifiers changed from: private */
    public static boolean _ready = false;
    /* access modifiers changed from: private */
    public static final Object _readyLock = new Object();

    private static synchronized void init() {
        synchronized (WebRequestThread.class) {
            _queue = new LinkedBlockingQueue<>();
            _pool = new CancelableThreadPoolExecutor(_corePoolSize, _maximumPoolSize, _keepAliveTime, TimeUnit.MILLISECONDS, _queue);
            _pool.prestartAllCoreThreads();
            _queue.add(new Runnable() {
                public void run() {
                    boolean unused = WebRequestThread._ready = true;
                    synchronized (WebRequestThread._readyLock) {
                        WebRequestThread._readyLock.notify();
                    }
                }
            });
            while (!_ready) {
                try {
                    synchronized (_readyLock) {
                        _readyLock.wait();
                    }
                } catch (InterruptedException unused) {
                    DeviceLog.debug("Couldn't synchronize thread");
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:5|6|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void reset() {
        /*
            java.lang.Class<com.unity3d.ads.request.WebRequestThread> r0 = com.unity3d.ads.request.WebRequestThread.class
            monitor-enter(r0)
            cancel()     // Catch:{ all -> 0x002a }
            com.unity3d.ads.request.CancelableThreadPoolExecutor r1 = _pool     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0028
            com.unity3d.ads.request.CancelableThreadPoolExecutor r1 = _pool     // Catch:{ all -> 0x002a }
            r1.shutdown()     // Catch:{ all -> 0x002a }
            com.unity3d.ads.request.CancelableThreadPoolExecutor r1 = _pool     // Catch:{ InterruptedException -> 0x001b }
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x001b }
            r1.awaitTermination(r2, r4)     // Catch:{ InterruptedException -> 0x001b }
        L_0x001b:
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r1 = _queue     // Catch:{ all -> 0x002a }
            r1.clear()     // Catch:{ all -> 0x002a }
            r1 = 0
            _pool = r1     // Catch:{ all -> 0x002a }
            _queue = r1     // Catch:{ all -> 0x002a }
            r1 = 0
            _ready = r1     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r0)
            return
        L_0x002a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.request.WebRequestThread.reset():void");
    }

    public static synchronized void cancel() {
        synchronized (WebRequestThread.class) {
            if (_pool != null) {
                _pool.cancel();
                Iterator<Runnable> it = _queue.iterator();
                while (it.hasNext()) {
                    Runnable next = it.next();
                    if (next instanceof WebRequestRunnable) {
                        ((WebRequestRunnable) next).setCancelStatus(true);
                    }
                }
                _queue.clear();
                _pool.purge();
            }
        }
    }

    public static synchronized void request(String str, WebRequest.RequestType requestType, Map<String, List<String>> map, Integer num, Integer num2, IWebRequestListener iWebRequestListener) {
        synchronized (WebRequestThread.class) {
            request(str, requestType, map, (String) null, num, num2, iWebRequestListener);
        }
    }

    public static synchronized void request(String str, WebRequest.RequestType requestType, Map<String, List<String>> map, String str2, Integer num, Integer num2, IWebRequestListener iWebRequestListener) {
        String str3 = str;
        synchronized (WebRequestThread.class) {
            if (!_ready) {
                init();
            }
            if (str3 != null) {
                if (str.length() >= 3) {
                    _queue.add(new WebRequestRunnable(str, requestType.name(), str2, num.intValue(), num2.intValue(), map, iWebRequestListener));
                    return;
                }
            }
            iWebRequestListener.onFailed(str, "Request is NULL or too short");
        }
    }

    public static synchronized void setConcurrentRequestCount(int i) {
        synchronized (WebRequestThread.class) {
            _corePoolSize = i;
            _maximumPoolSize = _corePoolSize;
            if (_pool != null) {
                _pool.setCorePoolSize(_corePoolSize);
                _pool.setMaximumPoolSize(_maximumPoolSize);
            }
        }
    }

    public static synchronized void setMaximumPoolSize(int i) {
        synchronized (WebRequestThread.class) {
            _maximumPoolSize = i;
            if (_pool != null) {
                _pool.setMaximumPoolSize(_maximumPoolSize);
            }
        }
    }

    public static synchronized void setKeepAliveTime(long j) {
        synchronized (WebRequestThread.class) {
            _keepAliveTime = j;
            if (_pool != null) {
                _pool.setKeepAliveTime(_keepAliveTime, TimeUnit.MILLISECONDS);
            }
        }
    }

    public static synchronized boolean resolve(final String str, final IResolveHostListener iResolveHostListener) {
        synchronized (WebRequestThread.class) {
            if (str != null) {
                if (str.length() >= 3) {
                    new Thread(new Runnable() {
                        /* JADX WARNING: Removed duplicated region for block: B:12:0x0035 A[ADDED_TO_REGION] */
                        /* JADX WARNING: Removed duplicated region for block: B:15:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                                r7 = this;
                                android.os.ConditionVariable r0 = new android.os.ConditionVariable
                                r0.<init>()
                                r1 = 0
                                java.lang.Thread r2 = new java.lang.Thread     // Catch:{ Exception -> 0x001a }
                                com.unity3d.ads.request.WebRequestThread$2$1 r3 = new com.unity3d.ads.request.WebRequestThread$2$1     // Catch:{ Exception -> 0x001a }
                                r3.<init>(r0)     // Catch:{ Exception -> 0x001a }
                                r2.<init>(r3)     // Catch:{ Exception -> 0x001a }
                                r2.start()     // Catch:{ Exception -> 0x0015 }
                                r1 = r2
                                goto L_0x002d
                            L_0x0015:
                                r1 = move-exception
                                r6 = r2
                                r2 = r1
                                r1 = r6
                                goto L_0x001b
                            L_0x001a:
                                r2 = move-exception
                            L_0x001b:
                                java.lang.String r3 = "Exception while resolving host"
                                com.unity3d.ads.log.DeviceLog.exception(r3, r2)
                                com.unity3d.ads.request.IResolveHostListener r3 = r4
                                java.lang.String r4 = r3
                                com.unity3d.ads.request.ResolveHostError r5 = com.unity3d.ads.request.ResolveHostError.UNEXPECTED_EXCEPTION
                                java.lang.String r2 = r2.getMessage()
                                r3.onFailed(r4, r5, r2)
                            L_0x002d:
                                r2 = 20000(0x4e20, double:9.8813E-320)
                                boolean r0 = r0.block(r2)
                                if (r0 != 0) goto L_0x0045
                                if (r1 == 0) goto L_0x0045
                                r1.interrupt()
                                com.unity3d.ads.request.IResolveHostListener r0 = r4
                                java.lang.String r1 = r3
                                com.unity3d.ads.request.ResolveHostError r2 = com.unity3d.ads.request.ResolveHostError.TIMEOUT
                                java.lang.String r3 = "Timeout"
                                r0.onFailed(r1, r2, r3)
                            L_0x0045:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.request.WebRequestThread.AnonymousClass2.run():void");
                        }
                    }).start();
                    return true;
                }
            }
            iResolveHostListener.onFailed(str, ResolveHostError.INVALID_HOST, "Host is NULL");
            return false;
        }
    }
}
