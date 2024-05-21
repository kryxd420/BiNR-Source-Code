package com.tonyodev.fetch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.tonyodev.fetch.callback.FetchTask;
import com.tonyodev.fetch.exception.InvalidStatusException;
import com.tonyodev.fetch.exception.NotUsableException;
import com.tonyodev.fetch.request.Header;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class Utils {
    private Utils() {
    }

    static void throwIfFetchTaskNull(FetchTask fetchTask) {
        if (fetchTask == null) {
            throw new NullPointerException("FetchTask cannot be null");
        }
    }

    static void throwIfInvalidStatus(int i) {
        if (i != -900) {
            switch (i) {
                case FetchConst.STATUS_QUEUED:
                case FetchConst.STATUS_DOWNLOADING:
                case FetchConst.STATUS_PAUSED:
                case FetchConst.STATUS_DONE:
                case FetchConst.STATUS_ERROR:
                case FetchConst.STATUS_REMOVED:
                    return;
                default:
                    throw new InvalidStatusException(i + " is not a valid status ", -114);
            }
        }
    }

    static boolean hasIntervalElapsed(long j, long j2, long j3) {
        return TimeUnit.NANOSECONDS.toMillis(j2 - j) >= j3;
    }

    static int getProgress(long j, long j2) {
        if (j2 < 1 || j < 1) {
            return 0;
        }
        if (j >= j2) {
            return 100;
        }
        double d = (double) j;
        double d2 = (double) j2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (int) ((d / d2) * 100.0d);
    }

    static String headerListToString(List<Header> list, boolean z) {
        if (list == null) {
            return "{}";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (Header next : list) {
                jSONObject.put(next.getHeader(), next.getValue());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            if (z) {
                e.printStackTrace();
            }
            return "{}";
        }
    }

    static List<Header> headerStringToList(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                arrayList.add(new Header(next, jSONObject.getString(next)));
            }
        } catch (JSONException e) {
            if (z) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    static boolean isOnWiFi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    static boolean createFileIfNotExist(String str) throws IOException, NullPointerException {
        File file = new File(str);
        if (!file.exists()) {
            return file.createNewFile();
        }
        return true;
    }

    static boolean createDirIfNotExist(String str) throws NullPointerException {
        File file = new File(str);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    static boolean deleteFile(String str) {
        return new File(str).delete();
    }

    static long getFileSize(String str) {
        return new File(str).length();
    }

    static boolean fileExist(String str) {
        return new File(str).exists();
    }

    static File getFile(String str) {
        return new File(str);
    }

    static void createFileOrThrow(String str) throws IOException, NullPointerException {
        File file = getFile(str);
        boolean createDirIfNotExist = createDirIfNotExist(file.getParentFile().getAbsolutePath());
        boolean createFileIfNotExist = createFileIfNotExist(file.getAbsolutePath());
        if (!createDirIfNotExist || !createFileIfNotExist) {
            throw new IOException("File could not be created for the filePath:" + str);
        }
    }

    static void throwIfNotUsable(Fetch fetch) {
        if (fetch == null) {
            throw new NullPointerException("Fetch cannot be null");
        } else if (fetch.isReleased()) {
            throw new NotUsableException("Fetch instance: " + fetch.toString() + " cannot be reused after calling its release() method.Call Fetch.getInstance() for a new instance of Fetch.", -115);
        }
    }

    static RequestInfo cursorToRequestInfo(Cursor cursor, boolean z, boolean z2) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    if (cursor.getCount() >= 1) {
                        cursor.moveToFirst();
                        RequestInfo createRequestInfo = createRequestInfo(cursor, z2);
                        if (cursor != null && z) {
                            cursor.close();
                        }
                        return createRequestInfo;
                    }
                }
            } catch (Exception e) {
                if (z2) {
                    e.printStackTrace();
                }
                if (cursor == null || !z) {
                    return null;
                }
                cursor.close();
                return null;
            } catch (Throwable th) {
                if (cursor != null && z) {
                    cursor.close();
                }
                throw th;
            }
        }
        if (cursor != null && z) {
            cursor.close();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r4 != false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        if (r4 != false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        r3.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<com.tonyodev.fetch.request.RequestInfo> cursorToRequestInfoList(android.database.Cursor r3, boolean r4, boolean r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r3 == 0) goto L_0x0047
            boolean r1 = r3.isClosed()     // Catch:{ Exception -> 0x0030 }
            if (r1 != 0) goto L_0x0047
            int r1 = r3.getCount()     // Catch:{ Exception -> 0x0030 }
            r2 = 1
            if (r1 >= r2) goto L_0x0015
            goto L_0x0047
        L_0x0015:
            r3.moveToFirst()     // Catch:{ Exception -> 0x0030 }
        L_0x0018:
            boolean r1 = r3.isAfterLast()     // Catch:{ Exception -> 0x0030 }
            if (r1 != 0) goto L_0x0029
            com.tonyodev.fetch.request.RequestInfo r1 = createRequestInfo(r3, r5)     // Catch:{ Exception -> 0x0030 }
            r0.add(r1)     // Catch:{ Exception -> 0x0030 }
            r3.moveToNext()     // Catch:{ Exception -> 0x0030 }
            goto L_0x0018
        L_0x0029:
            if (r3 == 0) goto L_0x0046
            if (r4 == 0) goto L_0x0046
            goto L_0x0043
        L_0x002e:
            r5 = move-exception
            goto L_0x0037
        L_0x0030:
            r1 = move-exception
            if (r5 == 0) goto L_0x003f
            r1.printStackTrace()     // Catch:{ all -> 0x002e }
            goto L_0x003f
        L_0x0037:
            if (r3 == 0) goto L_0x003e
            if (r4 == 0) goto L_0x003e
            r3.close()
        L_0x003e:
            throw r5
        L_0x003f:
            if (r3 == 0) goto L_0x0046
            if (r4 == 0) goto L_0x0046
        L_0x0043:
            r3.close()
        L_0x0046:
            return r0
        L_0x0047:
            if (r3 == 0) goto L_0x004e
            if (r4 == 0) goto L_0x004e
            r3.close()
        L_0x004e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.Utils.cursorToRequestInfoList(android.database.Cursor, boolean, boolean):java.util.List");
    }

    static RequestInfo createRequestInfo(Cursor cursor, boolean z) {
        Cursor cursor2 = cursor;
        if (cursor2 == null || cursor.isClosed() || cursor.getCount() < 1) {
            return null;
        }
        long j = cursor2.getLong(0);
        int i = cursor2.getInt(3);
        String string = cursor2.getString(1);
        String string2 = cursor2.getString(2);
        int i2 = cursor2.getInt(7);
        long j2 = cursor2.getLong(6);
        int i3 = cursor2.getInt(8);
        long j3 = cursor2.getLong(5);
        return new RequestInfo(j, i, string, string2, getProgress(j3, j2), j3, j2, i2, headerStringToList(cursor2.getString(4), z), i3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009e, code lost:
        if (r17 != false) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (r17 != false) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b8, code lost:
        r16.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a8 A[SYNTHETIC, Splitter:B:23:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.ArrayList<android.os.Bundle> cursorToQueryResultList(android.database.Cursor r16, boolean r17, boolean r18) {
        /*
            r1 = r16
            r3 = r18
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r1 == 0) goto L_0x00bc
            boolean r0 = r16.isClosed()     // Catch:{ Exception -> 0x00a3 }
            if (r0 == 0) goto L_0x0013
            goto L_0x00bc
        L_0x0013:
            r16.moveToFirst()     // Catch:{ Exception -> 0x00a3 }
        L_0x0016:
            boolean r0 = r16.isAfterLast()     // Catch:{ Exception -> 0x00a3 }
            if (r0 != 0) goto L_0x009c
            r0 = 0
            long r5 = r1.getLong(r0)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r0 = 3
            int r0 = r1.getInt(r0)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r7 = 1
            java.lang.String r7 = r1.getString(r7)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r8 = 2
            java.lang.String r8 = r1.getString(r8)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r9 = 7
            int r9 = r1.getInt(r9)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r10 = 6
            long r10 = r1.getLong(r10)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r12 = 8
            int r12 = r1.getInt(r12)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r13 = 5
            long r13 = r1.getLong(r13)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r15 = 4
            java.lang.String r15 = r1.getString(r15)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.util.ArrayList r15 = headersToBundleList(r15, r3)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            int r3 = getProgress(r13, r10)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r2.<init>()     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r1 = "com.tonyodev.fetch.extra_id"
            r2.putLong(r1, r5)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r1 = "com.tonyodev.fetch.extra_status"
            r2.putInt(r1, r0)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_url"
            r2.putString(r0, r7)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_file_path"
            r2.putString(r0, r8)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_error"
            r2.putInt(r0, r9)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_downloaded_bytes"
            r2.putLong(r0, r13)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_file_size"
            r2.putLong(r0, r10)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_progress"
            r2.putInt(r0, r3)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_priority"
            r2.putInt(r0, r12)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            java.lang.String r0 = "com.tonyodev.fetch.extra_headers"
            r2.putParcelableArrayList(r0, r15)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r4.add(r2)     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r16.moveToNext()     // Catch:{ Exception -> 0x0098, all -> 0x0094 }
            r1 = r16
            r3 = r18
            goto L_0x0016
        L_0x0094:
            r0 = move-exception
            r1 = r16
            goto L_0x00ac
        L_0x0098:
            r0 = move-exception
            r1 = r16
            goto L_0x00a4
        L_0x009c:
            if (r1 == 0) goto L_0x00bb
            if (r17 == 0) goto L_0x00bb
            goto L_0x00b8
        L_0x00a1:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a3:
            r0 = move-exception
        L_0x00a4:
            r3 = r18
            if (r3 == 0) goto L_0x00b4
            r0.printStackTrace()     // Catch:{ all -> 0x00a1 }
            goto L_0x00b4
        L_0x00ac:
            if (r1 == 0) goto L_0x00b3
            if (r17 == 0) goto L_0x00b3
            r16.close()
        L_0x00b3:
            throw r0
        L_0x00b4:
            if (r1 == 0) goto L_0x00bb
            if (r17 == 0) goto L_0x00bb
        L_0x00b8:
            r16.close()
        L_0x00bb:
            return r4
        L_0x00bc:
            if (r1 == 0) goto L_0x00c3
            if (r17 == 0) goto L_0x00c3
            r16.close()
        L_0x00c3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.Utils.cursorToQueryResultList(android.database.Cursor, boolean, boolean):java.util.ArrayList");
    }

    static long generateRequestId() {
        return System.nanoTime();
    }

    static void sendEventUpdate(LocalBroadcastManager localBroadcastManager, long j, int i, int i2, long j2, long j3, int i3) {
        if (localBroadcastManager != null) {
            Intent intent = new Intent(FetchService.EVENT_ACTION_UPDATE);
            intent.putExtra(FetchService.EXTRA_ID, j);
            intent.putExtra(FetchService.EXTRA_STATUS, i);
            intent.putExtra(FetchService.EXTRA_PROGRESS, i2);
            intent.putExtra(FetchService.EXTRA_DOWNLOADED_BYTES, j2);
            intent.putExtra(FetchService.EXTRA_FILE_SIZE, j3);
            intent.putExtra(FetchService.EXTRA_ERROR, i3);
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    static ArrayList<Bundle> headersToBundleList(String str, boolean z) {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        if (str == null) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Bundle bundle = new Bundle();
                bundle.putString(FetchService.EXTRA_HEADER_NAME, next);
                bundle.putString(FetchService.EXTRA_HEADER_VALUE, jSONObject.getString(next));
                arrayList.add(bundle);
            }
        } catch (JSONException e) {
            if (z) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    static String bundleListToHeaderString(List<Bundle> list, boolean z) {
        if (list == null) {
            return "{}";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Bundle next : list) {
                String string = next.getString(FetchService.EXTRA_HEADER_NAME);
                String string2 = next.getString(FetchService.EXTRA_HEADER_VALUE);
                if (string2 == null) {
                    string2 = "";
                }
                if (string != null) {
                    jSONObject.put(string, string2);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            if (z) {
                e.printStackTrace();
            }
            return "{}";
        }
    }

    static boolean containsRequest(Cursor cursor, boolean z) {
        if (cursor == null || cursor.getCount() <= 0) {
            return false;
        }
        if (!z) {
            return true;
        }
        cursor.close();
        return true;
    }

    static void throwIfInvalidUrl(String str) {
        String scheme = Uri.parse(str).getScheme();
        if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
            throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + str);
        }
    }
}
