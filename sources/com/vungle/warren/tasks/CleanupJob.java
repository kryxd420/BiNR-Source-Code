package com.vungle.warren.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class CleanupJob implements Job {
    static final String TAG = CleanupJob.class.getCanonicalName();
    private Designer designer;
    private Storage storage;

    CleanupJob(@NonNull Designer designer2, @NonNull Storage storage2) {
        this.designer = designer2;
        this.storage = storage2;
    }

    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        if (this.designer == null || this.storage == null) {
            return 1;
        }
        Log.d(TAG, "CleanupJob: Current directory snapshot");
        FileUtility.printDirectoryTree(this.designer.getCacheDirectory());
        File[] listFiles = this.designer.getCacheDirectory().listFiles();
        List<Advertisement> loadAll = this.storage.loadAll(Advertisement.class);
        List<Placement> loadAll2 = this.storage.loadAll(Placement.class);
        if (loadAll2.size() == 0) {
            return 0;
        }
        Collection<Placement> loadValidPlacements = this.storage.loadValidPlacements();
        HashSet hashSet = new HashSet();
        for (Placement next : loadAll2) {
            if (loadValidPlacements.isEmpty() || loadValidPlacements.contains(next)) {
                for (String next2 : next.getAdvertisementIDs()) {
                    Advertisement advertisement = (Advertisement) this.storage.load(next2, Advertisement.class);
                    if (advertisement == null) {
                        JobRunner jobRunner2 = jobRunner;
                        Log.w(TAG, "removing adv " + next2 + " from placement " + next.getId());
                        this.storage.removeAdvertisementFromPlacement(next.getId(), next2);
                    } else if (advertisement.getExpireTime() > System.currentTimeMillis() || advertisement.getState() == 2) {
                        JobRunner jobRunner3 = jobRunner;
                        hashSet.add(advertisement.getId());
                        Log.w(TAG, "setting valid adv " + next2 + " for placement " + next.getId());
                    } else {
                        this.storage.removeAdvertisementFromPlacement(next.getId(), next2);
                        try {
                            this.designer.deleteAssets(advertisement.getId());
                        } catch (IOException e) {
                            Log.e(TAG, Log.getStackTraceString(e));
                        }
                        this.storage.delete(advertisement);
                        if (next.isAutoCached()) {
                            jobRunner.execute(DownloadJob.makeJobInfo(next.getId(), true).setDelay(1000));
                        } else {
                            JobRunner jobRunner4 = jobRunner;
                        }
                    }
                }
            } else {
                Log.d(TAG, String.format(Locale.ENGLISH, "Placement %s is no longer valid, deleting it and its advertisement", new Object[]{next.getId()}));
                this.storage.delete(next);
            }
            JobRunner jobRunner5 = jobRunner;
        }
        for (Advertisement next3 : loadAll) {
            if (next3.getState() == 2) {
                hashSet.add(next3.getId());
                Log.d(TAG, "found adv in viewing state " + next3.getId());
            } else if (!hashSet.contains(next3.getId())) {
                Log.e(TAG, "delete ad " + next3.getId());
                this.storage.delete(next3);
            }
        }
        for (File file : listFiles) {
            if (!hashSet.contains(file.getName())) {
                Log.v(TAG, String.format(Locale.ENGLISH, "Deleting assets under directory %s", new Object[]{file.getName()}));
                try {
                    FileUtility.delete(file);
                } catch (Throwable th) {
                    Log.e(TAG, "Failed to delete asset directory!", th);
                }
            }
        }
        return 0;
    }

    public static JobInfo makeJobInfo() {
        return new JobInfo(TAG).setPriority(0).setUpdateCurrent(true);
    }
}
