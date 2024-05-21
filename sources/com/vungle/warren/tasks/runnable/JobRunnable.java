package com.vungle.warren.tasks.runnable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.tasks.JobCreator;
import com.vungle.warren.tasks.JobInfo;
import com.vungle.warren.tasks.JobRunner;

public class JobRunnable extends PriorityRunnable {
    private static final String TAG = "JobRunnable";
    private final JobCreator creator;
    private final JobRunner jobRunner;
    private final JobInfo jobinfo;

    public JobRunnable(@NonNull JobInfo jobInfo, @NonNull JobCreator jobCreator, @NonNull JobRunner jobRunner2) {
        this.jobinfo = jobInfo;
        this.creator = jobCreator;
        this.jobRunner = jobRunner2;
    }

    public Integer getPriority() {
        return Integer.valueOf(this.jobinfo.getPriority());
    }

    public void run() {
        try {
            String jobTag = this.jobinfo.getJobTag();
            Bundle extras = this.jobinfo.getExtras();
            String str = TAG;
            Log.d(str, "Start job " + jobTag + "Thread " + Thread.currentThread().getName());
            int onRunJob = this.creator.create(jobTag).onRunJob(extras, this.jobRunner);
            String str2 = TAG;
            Log.d(str2, "On job finished " + jobTag + " with result " + onRunJob);
            if (onRunJob == 2) {
                long makeNextRescedule = this.jobinfo.makeNextRescedule();
                if (makeNextRescedule > 0) {
                    this.jobinfo.setDelay(makeNextRescedule);
                    this.jobRunner.execute(this.jobinfo);
                    String str3 = TAG;
                    Log.d(str3, "Rescheduling " + jobTag + " in " + makeNextRescedule);
                }
            }
        } catch (Throwable th) {
            Log.e(TAG, "Can't start job", th);
        }
    }
}
