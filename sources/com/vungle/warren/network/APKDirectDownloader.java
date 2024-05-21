package com.vungle.warren.network;

import android.content.Context;
import com.tonyodev.fetch.Fetch;
import java.util.Map;

public class APKDirectDownloader extends FetchDownloader {
    public APKDirectDownloader(Context context) {
        super(context);
        new Fetch.Settings(context).setConcurrentDownloadsLimit(5).enableLogging(true).apply();
    }

    public void pause() {
        if (this.operations != null) {
            for (Map.Entry key : this.operations.entrySet()) {
                this.fetch.pause(((Long) key.getKey()).longValue());
            }
        }
    }

    public void resume() {
        if (this.operations != null) {
            for (Map.Entry key : this.operations.entrySet()) {
                this.fetch.resume(((Long) key.getKey()).longValue());
            }
        }
    }

    public boolean isDownloadTaskRunning() {
        return this.operations != null && !this.operations.isEmpty();
    }
}
