package com.vungle.warren.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtility {
    private static final int BUFFER_SIZE = 4096;

    public static void unzip(String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
            String str3 = str2 + File.separator + nextEntry.getName();
            if (nextEntry.isDirectory()) {
                File file2 = new File(str3);
                if (!file2.exists()) {
                    file2.mkdir();
                }
            } else {
                extractFile(zipInputStream, str3);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        r1 = r1.getParentFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r1.mkdir() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        r1 = r1.getParentFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        extractFile(r4, r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0026 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035 A[Catch:{ all -> 0x0024 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extractFile(java.util.zip.ZipInputStream r4, java.lang.String r5) throws java.io.IOException {
        /*
            r0 = 0
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x0026 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0026 }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0026 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0026 }
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ FileNotFoundException -> 0x0022, all -> 0x001f }
        L_0x000f:
            int r2 = r4.read(r0)     // Catch:{ FileNotFoundException -> 0x0022, all -> 0x001f }
            r3 = -1
            if (r2 == r3) goto L_0x001b
            r3 = 0
            r1.write(r0, r3, r2)     // Catch:{ FileNotFoundException -> 0x0022, all -> 0x001f }
            goto L_0x000f
        L_0x001b:
            com.vungle.warren.utility.FileUtility.closeQuietly(r1)
            goto L_0x004a
        L_0x001f:
            r4 = move-exception
            r0 = r1
            goto L_0x004b
        L_0x0022:
            r0 = r1
            goto L_0x0026
        L_0x0024:
            r4 = move-exception
            goto L_0x004b
        L_0x0026:
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0024 }
            r1.<init>(r5)     // Catch:{ all -> 0x0024 }
            java.io.File r2 = r1.getParentFile()     // Catch:{ all -> 0x0024 }
            boolean r2 = r2.exists()     // Catch:{ all -> 0x0024 }
            if (r2 != 0) goto L_0x0047
            java.io.File r1 = r1.getParentFile()     // Catch:{ all -> 0x0024 }
        L_0x0039:
            boolean r2 = r1.mkdir()     // Catch:{ all -> 0x0024 }
            if (r2 != 0) goto L_0x0044
            java.io.File r1 = r1.getParentFile()     // Catch:{ all -> 0x0024 }
            goto L_0x0039
        L_0x0044:
            extractFile(r4, r5)     // Catch:{ all -> 0x0024 }
        L_0x0047:
            com.vungle.warren.utility.FileUtility.closeQuietly(r0)
        L_0x004a:
            return
        L_0x004b:
            com.vungle.warren.utility.FileUtility.closeQuietly(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.utility.UnzipUtility.extractFile(java.util.zip.ZipInputStream, java.lang.String):void");
    }
}
