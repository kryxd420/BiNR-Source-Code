package com.vungle.warren.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtility {
    public static void printDirectoryTree(File file) {
        if (file.isDirectory()) {
            StringBuilder sb = new StringBuilder();
            printDirectoryTree(file, 0, sb);
            Log.v(TMMediationNetworks.VUNGLE_NAME, sb.toString());
            return;
        }
        throw new IllegalArgumentException("folder is not a Directory");
    }

    private static void printDirectoryTree(File file, int i, StringBuilder sb) {
        if (file != null) {
            if (file.isDirectory()) {
                sb.append(getIndentString(i));
                sb.append("+--");
                sb.append(file.getName());
                sb.append("/");
                sb.append("\n");
                if (file.listFiles() != null) {
                    for (File file2 : file.listFiles()) {
                        if (file2.isDirectory()) {
                            printDirectoryTree(file2, i + 1, sb);
                        } else {
                            printFile(file2, i + 1, sb);
                        }
                    }
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("folder is not a Directory");
        }
    }

    private static void printFile(File file, int i, StringBuilder sb) {
        sb.append(getIndentString(i));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    public static void delete(File file) throws IOException {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete(delete);
                }
            }
            if (!file.delete()) {
                throw new FileNotFoundException("Failed to delete file: " + file);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public static byte[] extractBytes(@Nullable File file) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (file == null) {
            return new byte[0];
        }
        int length = (int) file.length();
        if (length > 0) {
            byte[] bArr = new byte[length];
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    if (bufferedInputStream.read(bArr) >= length) {
                        bufferedInputStream.close();
                        return bArr;
                    }
                    throw new IOException("Failed to read all bytes in the file, object recreation will fail");
                } catch (FileNotFoundException e) {
                    e = e;
                    e.printStackTrace();
                    closeQuietly(bufferedInputStream);
                    return new byte[0];
                }
            } catch (FileNotFoundException e2) {
                e = e2;
                bufferedInputStream = null;
                e.printStackTrace();
                closeQuietly(bufferedInputStream);
                return new byte[0];
            }
        }
        return new byte[0];
    }
}
