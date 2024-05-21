package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import com.google.gson.JsonObject;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Report;
import com.vungle.warren.network.VungleApiClient;
import java.io.IOException;
import retrofit2.Response;

public class SendReportsJob implements Job {
    private static final long DEFAULT_DELAY = 30000;
    static final String TAG = SendReportsJob.class.getCanonicalName();
    private Storage storage;

    public SendReportsJob(Storage storage2) {
        this.storage = storage2;
    }

    public static JobInfo makeJobInfo() {
        return new JobInfo(TAG).setUpdateCurrent(true).setPriority(5).setReschedulePolicy(DEFAULT_DELAY, 1);
    }

    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        Log.d(TAG, "SendReportsJob: Current directory snapshot");
        for (Report next : this.storage.loadAll(Report.class)) {
            try {
                Response<JsonObject> execute = VungleApiClient.reportAd(next.toReportBody()).execute();
                if (execute.code() == 200) {
                    this.storage.delete(next);
                } else {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(execute);
                    if (retryAfterHeaderValue > 0) {
                        jobRunner.execute(makeJobInfo().setDelay(retryAfterHeaderValue));
                        return 1;
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, Log.getStackTraceString(e));
                return 2;
            }
        }
        return 0;
    }
}
