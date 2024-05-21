package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Storage;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Placement;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DownloadJob implements Job {
    private static final long DEFAUT_DELAY = 5000;
    private static final String PLACEMENT_KEY = "placement";
    static final String TAG = DownloadJob.class.getCanonicalName();
    private Placement placement;
    /* access modifiers changed from: private */
    public int result;
    private final Storage storage;

    public DownloadJob(Storage storage2) {
        this.storage = storage2;
    }

    public static JobInfo makeJobInfo(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("placement", str);
        return new JobInfo(TAG + " " + str).setUpdateCurrent(true).setExtras(bundle).setReschedulePolicy(z ? DEFAUT_DELAY : -1, 1).setPriority(4);
    }

    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        String string = bundle.getString("placement", (String) null);
        Collection<String> validPlacements = this.storage.getValidPlacements();
        if (string == null || !validPlacements.contains(string)) {
            return 1;
        }
        this.placement = (Placement) this.storage.load(string, Placement.class);
        if (this.placement == null) {
            return 1;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Vungle.loadAd(string, new LoadAdCallback() {
            public void onAdLoad(String str) {
                int unused = DownloadJob.this.result = 0;
                countDownLatch.countDown();
            }

            public void onError(String str, Throwable th) {
                if (th instanceof VungleException) {
                    VungleException vungleException = (VungleException) th;
                    String str2 = DownloadJob.TAG;
                    Log.e(str2, "scheduleJob: loadAd onError: " + vungleException.getExceptionCode());
                    if (vungleException.getExceptionCode() == 8 || vungleException.getExceptionCode() == 1 || vungleException.getExceptionCode() == 14) {
                        int unused = DownloadJob.this.result = 1;
                    } else {
                        int unused2 = DownloadJob.this.result = 2;
                    }
                } else {
                    int unused3 = DownloadJob.this.result = 2;
                }
                countDownLatch.countDown();
            }
        });
        try {
            if (countDownLatch.await(1, TimeUnit.MINUTES)) {
                String str = TAG;
                Log.d(str, "scheduleJob: latch await" + this.result);
                return this.result;
            }
            Log.d(TAG, "scheduleJob: latch await else 2");
            return 2;
        } catch (InterruptedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return 1;
        }
    }
}
