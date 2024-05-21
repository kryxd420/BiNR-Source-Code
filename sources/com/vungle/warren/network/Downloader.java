package com.vungle.warren.network;

import android.support.annotation.NonNull;
import java.io.File;
import java.io.IOException;

public interface Downloader {

    public interface Listener {
        void onComplete(File file);

        void onError(Throwable th);

        void onProgress(int i);
    }

    boolean download(@NonNull String str, @NonNull File file, @NonNull Listener listener) throws IOException, IllegalArgumentException, IllegalStateException;

    void pause();

    void resume();
}
