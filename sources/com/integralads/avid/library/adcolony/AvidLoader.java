package com.integralads.avid.library.adcolony;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.DownloadAvidTask;
import com.integralads.avid.library.adcolony.utils.NetworkUtils;

public class AvidLoader implements DownloadAvidTask.DownloadAvidTaskListener {
    private static final int a = 2000;
    private static final String b = "https://mobile-static.adsafeprotected.com/avid-v2.js";
    private static AvidLoader c = new AvidLoader();
    private AvidLoaderListener d;
    /* access modifiers changed from: private */
    public DownloadAvidTask e;
    /* access modifiers changed from: private */
    public Context f;
    private TaskExecutor g = new TaskExecutor();
    private TaskRepeater h;
    /* access modifiers changed from: private */
    public final Runnable i = new Runnable() {
        public void run() {
            if (AvidLoader.this.f == null || !NetworkUtils.isNetworkAvailable(AvidLoader.this.f)) {
                AvidLoader.this.d();
            } else {
                AvidLoader.this.c();
            }
        }
    };

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public static AvidLoader getInstance() {
        return c;
    }

    public void registerAvidLoader(Context context) {
        this.f = context;
        this.h = new TaskRepeater();
        c();
    }

    public void unregisterAvidLoader() {
        if (this.h != null) {
            this.h.cleanup();
            this.h = null;
        }
        this.d = null;
        this.f = null;
    }

    public void setListener(AvidLoaderListener avidLoaderListener) {
        this.d = avidLoaderListener;
    }

    public AvidLoaderListener getListener() {
        return this.d;
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!AvidBridge.isAvidJsReady() && this.e == null) {
            this.e = new DownloadAvidTask();
            this.e.setListener(this);
            this.g.executeTask(this.e);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.h != null) {
            this.h.repeatLoading();
        }
    }

    public void onLoadAvid(String str) {
        this.e = null;
        AvidBridge.setAvidJs(str);
        if (this.d != null) {
            this.d.onAvidLoaded();
        }
    }

    public void failedToLoadAvid() {
        this.e = null;
        d();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public DownloadAvidTask a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public TaskRepeater b() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(TaskRepeater taskRepeater) {
        this.h = taskRepeater;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(TaskExecutor taskExecutor) {
        this.g = taskExecutor;
    }

    @VisibleForTesting
    static void a(AvidLoader avidLoader) {
        c = avidLoader;
    }

    public class TaskExecutor {
        public TaskExecutor() {
        }

        public void executeTask(DownloadAvidTask downloadAvidTask) {
            if (Build.VERSION.SDK_INT >= 11) {
                AvidLoader.this.e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{AvidLoader.b});
            } else {
                AvidLoader.this.e.execute(new String[]{AvidLoader.b});
            }
        }
    }

    public class TaskRepeater {
        private Handler b = new Handler();

        public TaskRepeater() {
        }

        public void repeatLoading() {
            this.b.postDelayed(AvidLoader.this.i, FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        }

        public void cleanup() {
            this.b.removeCallbacks(AvidLoader.this.i);
        }
    }
}
