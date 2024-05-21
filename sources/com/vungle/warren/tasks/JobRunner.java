package com.vungle.warren.tasks;

import android.support.annotation.NonNull;

public interface JobRunner {
    void execute(@NonNull JobInfo jobInfo);
}
