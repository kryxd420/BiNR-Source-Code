package com.vungle.warren.tasks;

import android.text.TextUtils;
import com.vungle.warren.Storage;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.tasks.ReconfigJob;

public class VungleJobCreator implements JobCreator {
    private final Designer designer;
    private final ReconfigJob.ReconfigCall reconfigCall;
    private final Storage storage;

    public VungleJobCreator(Storage storage2, Designer designer2, ReconfigJob.ReconfigCall reconfigCall2) {
        this.storage = storage2;
        this.designer = designer2;
        this.reconfigCall = reconfigCall2;
    }

    public Job create(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Job tag is not specified");
        } else if (str.startsWith(ReconfigJob.TAG)) {
            return new ReconfigJob(this.reconfigCall);
        } else {
            if (str.startsWith(DownloadJob.TAG)) {
                return new DownloadJob(this.storage);
            }
            if (str.startsWith(SendReportsJob.TAG)) {
                return new SendReportsJob(this.storage);
            }
            if (str.startsWith(CleanupJob.TAG)) {
                return new CleanupJob(this.designer, this.storage);
            }
            throw new IllegalArgumentException("Unknown Job Type " + str);
        }
    }
}
