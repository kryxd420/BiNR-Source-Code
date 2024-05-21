package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;

public class GraphicDesigner implements Designer {
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = "GraphicDesigner";
    private File cacheDir;

    public GraphicDesigner(@NonNull File file) {
        this.cacheDir = file;
        FileUtility.printDirectoryTree(getCacheDirectory());
    }

    public boolean hasAssetsFor(String str, int i) throws IllegalStateException {
        File[] listFiles = getCacheDirectory().listFiles();
        if (listFiles == null) {
            return false;
        }
        int length = listFiles.length;
        int i2 = 0;
        while (i2 < length) {
            File file = listFiles[i2];
            if (!file.isDirectory() || !file.getName().equals(str)) {
                i2++;
            } else if (file.list().length >= i) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public File getAssetDirectory(String str) throws IllegalStateException {
        File file = new File(getCacheDirectory().getPath() + File.separator + str);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public void deleteAssets(String str) throws IOException, IllegalStateException {
        File[] listFiles = getCacheDirectory().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory() && file.getName().equals(str)) {
                    FileUtility.delete(file);
                }
            }
        }
    }

    public File getCacheDirectory() throws IllegalStateException {
        if (this.cacheDir != null) {
            File file = new File(this.cacheDir.getPath() + File.separator + FOLDER_NAME);
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        }
        throw new IllegalStateException("Context has expired, cannot continue.");
    }

    public void clearCache() {
        if (this.cacheDir != null) {
            File file = new File(this.cacheDir.getPath() + File.separator + FOLDER_NAME);
            if (file.exists()) {
                try {
                    FileUtility.delete(file);
                } catch (IOException e) {
                    String str = TAG;
                    Log.e(str, "Failed to delete cached files. Reason: " + e.getLocalizedMessage());
                }
            }
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }
}
