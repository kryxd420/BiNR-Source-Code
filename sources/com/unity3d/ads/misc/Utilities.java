package com.unity3d.ads.misc;

import android.os.Handler;
import android.os.Looper;
import com.unity3d.ads.log.DeviceLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class Utilities {
    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public static void runOnUiThread(Runnable runnable, long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (j > 0) {
            handler.postDelayed(runnable, j);
        } else {
            handler.post(runnable);
        }
    }

    public static String Sha256(String str) {
        return Sha256(str.getBytes());
    }

    public static String Sha256(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(bArr, 0, bArr.length);
            return toHexString(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String Sha256(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return toHexString(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String toHexString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            byte b2 = b & 255;
            if (b2 <= 15) {
                str = str + "0";
            }
            str = str + Integer.toHexString(b2);
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0031 A[SYNTHETIC, Splitter:B:23:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0058 A[SYNTHETIC, Splitter:B:31:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeFile(java.io.File r4, java.lang.String r5) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            r2 = 1
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0029 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0029 }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r3.write(r5)     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r3.flush()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r3.close()     // Catch:{ Exception -> 0x0019 }
            goto L_0x001f
        L_0x0019:
            r5 = move-exception
            java.lang.String r0 = "Error closing FileOutputStream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r5)
        L_0x001f:
            r0 = 1
            goto L_0x003b
        L_0x0021:
            r4 = move-exception
            r1 = r3
            goto L_0x0056
        L_0x0024:
            r5 = move-exception
            r1 = r3
            goto L_0x002a
        L_0x0027:
            r4 = move-exception
            goto L_0x0056
        L_0x0029:
            r5 = move-exception
        L_0x002a:
            java.lang.String r2 = "Could not write file"
            com.unity3d.ads.log.DeviceLog.exception(r2, r5)     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ Exception -> 0x0035 }
            goto L_0x003b
        L_0x0035:
            r5 = move-exception
            java.lang.String r1 = "Error closing FileOutputStream"
            com.unity3d.ads.log.DeviceLog.exception(r1, r5)
        L_0x003b:
            if (r0 == 0) goto L_0x0055
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "Wrote file: "
            r5.append(r1)
            java.lang.String r4 = r4.getAbsolutePath()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.unity3d.ads.log.DeviceLog.debug(r4)
        L_0x0055:
            return r0
        L_0x0056:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch:{ Exception -> 0x005c }
            goto L_0x0062
        L_0x005c:
            r5 = move-exception
            java.lang.String r0 = "Error closing FileOutputStream"
            com.unity3d.ads.log.DeviceLog.exception(r0, r5)
        L_0x0062:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.misc.Utilities.writeFile(java.io.File, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0038 A[SYNTHETIC, Splitter:B:23:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044 A[SYNTHETIC, Splitter:B:28:0x0044] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFile(java.io.File r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = ""
            boolean r2 = r4.exists()
            if (r2 == 0) goto L_0x004f
            boolean r2 = r4.canRead()
            if (r2 == 0) goto L_0x004f
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x002e }
            r2.<init>(r4)     // Catch:{ Exception -> 0x002e }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x002b }
            r4.<init>(r2)     // Catch:{ Exception -> 0x002b }
        L_0x001c:
            java.lang.String r3 = r4.readLine()     // Catch:{ Exception -> 0x0029 }
            if (r3 == 0) goto L_0x0027
            java.lang.String r1 = r1.concat(r3)     // Catch:{ Exception -> 0x0029 }
            goto L_0x001c
        L_0x0027:
            r0 = r1
            goto L_0x0036
        L_0x0029:
            r1 = move-exception
            goto L_0x0031
        L_0x002b:
            r1 = move-exception
            r4 = r0
            goto L_0x0031
        L_0x002e:
            r1 = move-exception
            r4 = r0
            r2 = r4
        L_0x0031:
            java.lang.String r3 = "Problem reading file"
            com.unity3d.ads.log.DeviceLog.exception(r3, r1)
        L_0x0036:
            if (r4 == 0) goto L_0x0042
            r4.close()     // Catch:{ Exception -> 0x003c }
            goto L_0x0042
        L_0x003c:
            r4 = move-exception
            java.lang.String r1 = "Couldn't close BufferedReader"
            com.unity3d.ads.log.DeviceLog.exception(r1, r4)
        L_0x0042:
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ Exception -> 0x0048 }
            goto L_0x004e
        L_0x0048:
            r4 = move-exception
            java.lang.String r1 = "Couldn't close FileReader"
            com.unity3d.ads.log.DeviceLog.exception(r1, r4)
        L_0x004e:
            return r0
        L_0x004f:
            java.lang.String r4 = "File did not exist or couldn't be read"
            com.unity3d.ads.log.DeviceLog.error(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.misc.Utilities.readFile(java.io.File):java.lang.String");
    }

    public static byte[] readFileBytes(File file) throws IOException {
        if (file == null) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[((int) file.length())];
        int i = 0;
        int i2 = 4096;
        long j = (long) 4096;
        if (file.length() < j) {
            i2 = (int) file.length();
        }
        while (true) {
            int read = fileInputStream.read(bArr, i, i2);
            if (read > 0) {
                i += read;
                if (file.length() - ((long) i) < j) {
                    i2 = ((int) file.length()) - i;
                }
            } else {
                fileInputStream.close();
                return bArr;
            }
        }
    }

    public static JSONObject mergeJsonObjects(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject == null) {
            return jSONObject2;
        }
        if (jSONObject2 == null) {
            return jSONObject;
        }
        JSONObject jSONObject3 = new JSONObject();
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONObject3.put(next, jSONObject2.get(next));
        }
        Iterator<String> keys2 = jSONObject.keys();
        while (keys2.hasNext()) {
            String next2 = keys2.next();
            if (!jSONObject3.has(next2) || !(jSONObject3.get(next2) instanceof JSONObject) || !(jSONObject.get(next2) instanceof JSONObject)) {
                jSONObject3.put(next2, jSONObject.get(next2));
            } else {
                jSONObject3.put(next2, mergeJsonObjects(jSONObject.getJSONObject(next2), jSONObject3.getJSONObject(next2)));
            }
        }
        return jSONObject3;
    }
}
