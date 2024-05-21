package com.tapjoy.mraid.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.fn;
import com.tapjoy.internal.kb;
import com.tapjoy.mraid.view.MraidView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Assets extends Abstract {
    private static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private int c = 0;

    public void stopAllListeners() {
    }

    public Assets(MraidView mraidView, Context context) {
        super(mraidView, context);
    }

    @JavascriptInterface
    public void storePictureInit(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.b);
        builder.setMessage("Are you sure you want to save file from " + str + " to your SD card?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Assets.this.storePicture(str);
            }
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) null);
        builder.show();
    }

    public void storePicture(String str) {
        TapjoyLog.d("MRAID Assets", "Storing media from " + str + " to device photo album.  Output directory: " + Environment.getExternalStorageDirectory() + " state: " + Environment.getExternalStorageState());
        this.c = this.c + 1;
        try {
            URL url = new URL(str);
            String str2 = "MraidMedia" + this.c + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str2);
            long currentTimeMillis = System.currentTimeMillis();
            TapjoyLog.d("MRAID Assets", "download beginning");
            TapjoyLog.d("MRAID Assets", "download url:" + url);
            TapjoyLog.d("MRAID Assets", "downloaded file name:" + str2);
            InputStream inputStream = fn.a(url).getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            kb.a(inputStream, fileOutputStream);
            fileOutputStream.close();
            TapjoyLog.d("MRAID Assets", "download ready in" + ((System.currentTimeMillis() - currentTimeMillis) / 1000) + " sec");
        } catch (IOException e) {
            TapjoyLog.d("MRAID Assets", "Error: " + e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079 A[SYNTHETIC, Splitter:B:30:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0081 A[SYNTHETIC, Splitter:B:37:0x0081] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String copyTextFromJarIntoAssetDir(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.Class<com.tapjoy.mraid.controller.Assets> r1 = com.tapjoy.mraid.controller.Assets.class
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            java.net.URL r1 = r1.getResource(r6)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            r2 = 0
            if (r1 != 0) goto L_0x0019
            android.content.Context r1 = r4.b     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            java.io.InputStream r6 = r1.open(r6)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            goto L_0x0050
        L_0x0019:
            java.lang.String r1 = r1.getFile()     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            java.lang.String r3 = "jar:"
            boolean r3 = r1.startsWith(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            if (r3 == 0) goto L_0x002a
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
        L_0x002a:
            java.lang.String r3 = "file:"
            boolean r3 = r1.startsWith(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            if (r3 == 0) goto L_0x0037
            r3 = 5
            java.lang.String r1 = r1.substring(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
        L_0x0037:
            java.lang.String r3 = "!"
            int r3 = r1.indexOf(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            if (r3 <= 0) goto L_0x0043
            java.lang.String r1 = r1.substring(r2, r3)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
        L_0x0043:
            java.util.jar.JarFile r3 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            r3.<init>(r1)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            java.util.jar.JarEntry r6 = r3.getJarEntry(r6)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
            java.io.InputStream r6 = r3.getInputStream(r6)     // Catch:{ Exception -> 0x005e, all -> 0x005c }
        L_0x0050:
            java.lang.String r5 = r4.writeToDisk(r6, r5, r2)     // Catch:{ Exception -> 0x005a }
            if (r6 == 0) goto L_0x0059
            r6.close()     // Catch:{ Exception -> 0x0059 }
        L_0x0059:
            return r5
        L_0x005a:
            r5 = move-exception
            goto L_0x0060
        L_0x005c:
            r5 = move-exception
            goto L_0x007f
        L_0x005e:
            r5 = move-exception
            r6 = r0
        L_0x0060:
            java.lang.String r1 = "MRAID Assets"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            java.lang.String r3 = "copyTextFromJarIntoAssetDir: "
            r2.<init>(r3)     // Catch:{ all -> 0x007d }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x007d }
            r2.append(r5)     // Catch:{ all -> 0x007d }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x007d }
            com.tapjoy.TapjoyLog.e((java.lang.String) r1, (java.lang.String) r5)     // Catch:{ all -> 0x007d }
            if (r6 == 0) goto L_0x007c
            r6.close()     // Catch:{ Exception -> 0x007c }
        L_0x007c:
            return r0
        L_0x007d:
            r5 = move-exception
            r0 = r6
        L_0x007f:
            if (r0 == 0) goto L_0x0084
            r0.close()     // Catch:{ Exception -> 0x0084 }
        L_0x0084:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.mraid.controller.Assets.copyTextFromJarIntoAssetDir(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0036 A[SYNTHETIC, Splitter:B:21:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003e A[SYNTHETIC, Splitter:B:27:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addAsset(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            r0 = 0
            java.io.InputStream r4 = com.tapjoy.internal.fn.a((java.lang.String) r4)     // Catch:{ Exception -> 0x0030 }
            r0 = 0
            r2.writeToDisk(r4, r3, r0)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            java.lang.String r1 = "MraidAdController.addedAsset('"
            r0.<init>(r1)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            r0.append(r3)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            java.lang.String r3 = "' )"
            r0.append(r3)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            java.lang.String r3 = r0.toString()     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            com.tapjoy.mraid.view.MraidView r0 = r2.a     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            r0.injectMraidJavaScript(r3)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            if (r4 == 0) goto L_0x003b
            r4.close()     // Catch:{ Exception -> 0x0027 }
            return
        L_0x0027:
            return
        L_0x0028:
            r3 = move-exception
            goto L_0x003c
        L_0x002a:
            r3 = move-exception
            r0 = r4
            goto L_0x0031
        L_0x002d:
            r3 = move-exception
            r4 = r0
            goto L_0x003c
        L_0x0030:
            r3 = move-exception
        L_0x0031:
            r3.printStackTrace()     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ Exception -> 0x003a }
            return
        L_0x003a:
            return
        L_0x003b:
            return
        L_0x003c:
            if (r4 == 0) goto L_0x0041
            r4.close()     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.mraid.controller.Assets.addAsset(java.lang.String, java.lang.String):void");
    }

    public int cacheRemaining() {
        StatFs statFs = new StatFs(this.b.getFilesDir().getPath());
        return statFs.getFreeBlocks() * statFs.getBlockSize();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001e A[ADDED_TO_REGION, Catch:{ all -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002e A[SYNTHETIC, Splitter:B:19:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0046 A[LOOP:1: B:26:0x0043->B:28:0x0046, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00fc A[SYNTHETIC, Splitter:B:36:0x00fc] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0029 A[EDGE_INSN: B:40:0x0029->B:17:0x0029 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String writeToDisk(java.io.InputStream r7, java.lang.String r8, boolean r9) {
        /*
            r6 = this;
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            if (r9 == 0) goto L_0x0012
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch:{ NoSuchAlgorithmException -> 0x000e }
            goto L_0x0013
        L_0x000e:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0012:
            r2 = r1
        L_0x0013:
            java.io.FileOutputStream r3 = r6.getAssetOutputString(r8)     // Catch:{ all -> 0x00f8 }
        L_0x0017:
            int r1 = r7.read(r0)     // Catch:{ all -> 0x00f6 }
            r4 = 0
            if (r1 <= 0) goto L_0x0029
            if (r9 == 0) goto L_0x0025
            if (r2 == 0) goto L_0x0025
            r2.update(r0)     // Catch:{ all -> 0x00f6 }
        L_0x0025:
            r3.write(r0, r4, r1)     // Catch:{ all -> 0x00f6 }
            goto L_0x0017
        L_0x0029:
            r3.flush()     // Catch:{ all -> 0x00f6 }
            if (r3 == 0) goto L_0x0031
            r3.close()     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            java.lang.String r7 = r6.a()
            if (r9 == 0) goto L_0x00e6
            if (r2 == 0) goto L_0x00e6
            byte[] r9 = r2.digest()
            int r0 = r9.length
            int r0 = r0 * 2
            char[] r0 = new char[r0]
            r1 = 0
        L_0x0043:
            int r2 = r9.length
            if (r4 >= r2) goto L_0x0063
            int r2 = r1 + 1
            char[] r3 = d
            byte r5 = r9[r4]
            int r5 = r5 >>> 4
            r5 = r5 & 15
            char r3 = r3[r5]
            r0[r1] = r3
            int r1 = r2 + 1
            char[] r3 = d
            byte r5 = r9[r4]
            r5 = r5 & 15
            char r3 = r3[r5]
            r0[r2] = r3
            int r4 = r4 + 1
            goto L_0x0043
        L_0x0063:
            java.lang.String r9 = new java.lang.String
            r9.<init>(r0)
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r2 = java.io.File.separator
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            java.lang.String r3 = java.io.File.separator
            r2.append(r3)
            java.lang.String r3 = "ad"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            r1.mkdir()
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            java.lang.String r7 = java.io.File.separator
            r2.append(r7)
            java.lang.String r7 = "ad"
            r2.append(r7)
            java.lang.String r7 = java.io.File.separator
            r2.append(r7)
            r2.append(r9)
            java.lang.String r7 = r2.toString()
            r1.<init>(r7)
            r1.mkdir()
            java.io.File r7 = new java.io.File
            java.lang.String r9 = r0.getName()
            r7.<init>(r1, r9)
            r0.renameTo(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = r1.getPath()
            r7.append(r9)
            java.lang.String r9 = java.io.File.separator
            r7.append(r9)
            java.lang.String r7 = r7.toString()
        L_0x00e6:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            r9.append(r8)
            java.lang.String r7 = r9.toString()
            return r7
        L_0x00f6:
            r7 = move-exception
            goto L_0x00fa
        L_0x00f8:
            r7 = move-exception
            r3 = r1
        L_0x00fa:
            if (r3 == 0) goto L_0x00ff
            r3.close()     // Catch:{ Exception -> 0x00ff }
        L_0x00ff:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.mraid.controller.Assets.writeToDisk(java.io.InputStream, java.lang.String, boolean):java.lang.String");
    }

    private String a() {
        return this.b.getFilesDir().getPath();
    }

    public FileOutputStream getAssetOutputString(String str) {
        File a = a(b(str));
        a.mkdirs();
        return new FileOutputStream(new File(a, c(str)));
    }

    public void removeAsset(String str) {
        File a = a(b(str));
        a.mkdirs();
        new File(a, c(str)).delete();
        this.a.injectMraidJavaScript("MraidAdController.assetRemoved('" + str + "' )");
    }

    private File a(String str) {
        File filesDir = this.b.getFilesDir();
        return new File(filesDir.getPath() + File.separator + str);
    }

    private static String b(String str) {
        if (str.lastIndexOf(File.separatorChar) >= 0) {
            return str.substring(0, str.lastIndexOf(File.separatorChar));
        }
        return "/";
    }

    private static String c(String str) {
        return str.lastIndexOf(File.separatorChar) >= 0 ? str.substring(str.lastIndexOf(File.separatorChar) + 1) : str;
    }

    public String getAssetPath() {
        return "file://" + this.b.getFilesDir() + "/";
    }

    public static boolean deleteDirectory(String str) {
        if (str != null) {
            return deleteDirectory(new File(str));
        }
        return false;
    }

    public static boolean deleteDirectory(File file) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    deleteDirectory(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }

    public void deleteOldAds() {
        String a = a();
        deleteDirectory(new File(a + File.separator + "ad"));
    }
}
