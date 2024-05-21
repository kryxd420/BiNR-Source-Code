package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static File getTempFile(Context context) {
        String str = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), str + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException unused) {
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r8 = r2;
        r2 = r9;
        r9 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r2 = null;
     */
    @android.support.annotation.RequiresApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.ByteBuffer mmap(java.io.File r9) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0032 }
            r1.<init>(r9)     // Catch:{ IOException -> 0x0032 }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            long r6 = r2.size()     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            java.nio.channels.FileChannel$MapMode r3 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            r4 = 0
            java.nio.MappedByteBuffer r9 = r2.map(r3, r4, r6)     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            r1.close()     // Catch:{ IOException -> 0x0032 }
            return r9
        L_0x001a:
            r9 = move-exception
            r2 = r0
            goto L_0x0023
        L_0x001d:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x001f }
        L_0x001f:
            r2 = move-exception
            r8 = r2
            r2 = r9
            r9 = r8
        L_0x0023:
            if (r2 == 0) goto L_0x002e
            r1.close()     // Catch:{ Throwable -> 0x0029 }
            goto L_0x0031
        L_0x0029:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ IOException -> 0x0032 }
            goto L_0x0031
        L_0x002e:
            r1.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0031:
            throw r9     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        r7 = r1;
        r1 = r10;
        r10 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0045, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0046, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004b, code lost:
        r7 = r10;
        r10 = r9;
        r9 = r7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0045 A[ExcHandler: all (th java.lang.Throwable)] */
    @android.support.annotation.RequiresApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer mmap(android.content.Context r8, android.os.CancellationSignal r9, android.net.Uri r10) {
        /*
            android.content.ContentResolver r8 = r8.getContentResolver()
            r0 = 0
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r8 = r8.openFileDescriptor(r10, r1, r9)     // Catch:{ IOException -> 0x005f }
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            java.io.FileDescriptor r10 = r8.getFileDescriptor()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            r9.<init>(r10)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            java.nio.channels.FileChannel r1 = r9.getChannel()     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            long r5 = r1.size()     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            java.nio.channels.FileChannel$MapMode r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r3 = 0
            java.nio.MappedByteBuffer r10 = r1.map(r2, r3, r5)     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r9.close()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            if (r8 == 0) goto L_0x002c
            r8.close()     // Catch:{ IOException -> 0x005f }
        L_0x002c:
            return r10
        L_0x002d:
            r10 = move-exception
            r1 = r0
            goto L_0x0036
        L_0x0030:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r1 = move-exception
            r7 = r1
            r1 = r10
            r10 = r7
        L_0x0036:
            if (r1 == 0) goto L_0x0041
            r9.close()     // Catch:{ Throwable -> 0x003c, all -> 0x0045 }
            goto L_0x0044
        L_0x003c:
            r9 = move-exception
            r1.addSuppressed(r9)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            goto L_0x0044
        L_0x0041:
            r9.close()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
        L_0x0044:
            throw r10     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
        L_0x0045:
            r9 = move-exception
            r10 = r0
            goto L_0x004e
        L_0x0048:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x004a }
        L_0x004a:
            r10 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x004e:
            if (r8 == 0) goto L_0x005e
            if (r10 == 0) goto L_0x005b
            r8.close()     // Catch:{ Throwable -> 0x0056 }
            goto L_0x005e
        L_0x0056:
            r8 = move-exception
            r10.addSuppressed(r8)     // Catch:{ IOException -> 0x005f }
            goto L_0x005e
        L_0x005b:
            r8.close()     // Catch:{ IOException -> 0x005f }
        L_0x005e:
            throw r9     // Catch:{ IOException -> 0x005f }
        L_0x005f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        File tempFile = getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!copyToFile(tempFile, resources, i)) {
                return null;
            }
            ByteBuffer mmap = mmap(tempFile);
            tempFile.delete();
            return mmap;
        } finally {
            tempFile.delete();
        }
    }

    public static boolean copyToFile(File file, InputStream inputStream) {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        closeQuietly(fileOutputStream2);
                        return true;
                    }
                }
            } catch (IOException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream);
            return false;
        }
    }

    public static boolean copyToFile(File file, Resources resources, int i) {
        InputStream inputStream;
        try {
            inputStream = resources.openRawResource(i);
            try {
                boolean copyToFile = copyToFile(file, inputStream);
                closeQuietly(inputStream);
                return copyToFile;
            } catch (Throwable th) {
                th = th;
                closeQuietly(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            closeQuietly(inputStream);
            throw th;
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
}
