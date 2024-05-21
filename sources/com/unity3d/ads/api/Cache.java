package com.unity3d.ads.api;

import android.annotation.TargetApi;
import android.media.MediaMetadataRetriever;
import android.util.Base64;
import android.util.SparseArray;
import com.unity3d.ads.cache.CacheDirectory;
import com.unity3d.ads.cache.CacheDirectoryType;
import com.unity3d.ads.cache.CacheError;
import com.unity3d.ads.cache.CacheThread;
import com.unity3d.ads.device.Device;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.ClientProperties;
import com.unity3d.ads.properties.SdkProperties;
import com.unity3d.ads.request.WebRequestError;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cache {
    @WebViewExposed
    public static void download(String str, String str2, JSONArray jSONArray, Boolean bool, WebViewCallback webViewCallback) {
        if (CacheThread.isActive()) {
            webViewCallback.error(CacheError.FILE_ALREADY_CACHING, new Object[0]);
        } else if (!Device.isActiveNetworkConnected()) {
            webViewCallback.error(CacheError.NO_INTERNET, new Object[0]);
        } else {
            try {
                CacheThread.download(str, fileIdToFilename(str2), Request.getHeadersMap(jSONArray), bool.booleanValue());
                webViewCallback.invoke(new Object[0]);
            } catch (Exception e) {
                DeviceLog.exception("Error mapping headers for the request", e);
                webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, str, str2);
            }
        }
    }

    @WebViewExposed
    public static void stop(WebViewCallback webViewCallback) {
        if (!CacheThread.isActive()) {
            webViewCallback.error(CacheError.NOT_CACHING, new Object[0]);
            return;
        }
        CacheThread.cancel();
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void isCaching(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Boolean.valueOf(CacheThread.isActive()));
    }

    @WebViewExposed
    public static void getFileContent(String str, String str2, WebViewCallback webViewCallback) {
        String str3;
        String fileIdToFilename = fileIdToFilename(str);
        File file = new File(fileIdToFilename);
        if (!file.exists()) {
            webViewCallback.error(CacheError.FILE_NOT_FOUND, str, fileIdToFilename);
            return;
        }
        try {
            byte[] readFileBytes = Utilities.readFileBytes(file);
            if (str2 == null) {
                webViewCallback.error(CacheError.UNSUPPORTED_ENCODING, str, fileIdToFilename, str2);
                return;
            }
            if (str2.equals("UTF-8")) {
                str3 = Charset.forName("UTF-8").decode(ByteBuffer.wrap(readFileBytes)).toString();
            } else if (str2.equals("Base64")) {
                str3 = Base64.encodeToString(readFileBytes, 2);
            } else {
                webViewCallback.error(CacheError.UNSUPPORTED_ENCODING, str, fileIdToFilename, str2);
                return;
            }
            webViewCallback.invoke(str3);
        } catch (IOException e) {
            CacheError cacheError = CacheError.FILE_IO_ERROR;
            webViewCallback.error(cacheError, str, fileIdToFilename, e.getMessage() + ", " + e.getClass().getName());
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:29:0x005a, B:34:0x006d] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x005a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x006d */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069 A[SYNTHETIC, Splitter:B:32:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007c A[SYNTHETIC, Splitter:B:37:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0091 A[SYNTHETIC, Splitter:B:45:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:29:0x005a=Splitter:B:29:0x005a, B:34:0x006d=Splitter:B:34:0x006d} */
    @com.unity3d.ads.webview.bridge.WebViewExposed
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setFileContent(java.lang.String r7, java.lang.String r8, java.lang.String r9, com.unity3d.ads.webview.bridge.WebViewCallback r10) {
        /*
            java.lang.String r0 = fileIdToFilename(r7)
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            java.lang.String r5 = "UTF-8"
            byte[] r5 = r9.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x009c }
            if (r8 == 0) goto L_0x003a
            int r6 = r8.length()
            if (r6 <= 0) goto L_0x003a
            java.lang.String r6 = "Base64"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x0023
            byte[] r5 = android.util.Base64.decode(r9, r2)
            goto L_0x003a
        L_0x0023:
            java.lang.String r9 = "UTF-8"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x002c
            goto L_0x003a
        L_0x002c:
            com.unity3d.ads.cache.CacheError r9 = com.unity3d.ads.cache.CacheError.UNSUPPORTED_ENCODING
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r4] = r7
            r1[r3] = r0
            r1[r2] = r8
            r10.error(r9, r1)
            return
        L_0x003a:
            r9 = 0
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x006d, IOException -> 0x005a }
            r6.<init>(r0)     // Catch:{ FileNotFoundException -> 0x006d, IOException -> 0x005a }
            r6.write(r5)     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054, all -> 0x0051 }
            r6.flush()     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054, all -> 0x0051 }
            r6.close()     // Catch:{ Exception -> 0x004a }
            goto L_0x0087
        L_0x004a:
            r7 = move-exception
            java.lang.String r8 = "Error closing FileOutputStream"
            com.unity3d.ads.log.DeviceLog.exception(r8, r7)
            goto L_0x0087
        L_0x0051:
            r7 = move-exception
            r9 = r6
            goto L_0x008f
        L_0x0054:
            r9 = r6
            goto L_0x005a
        L_0x0056:
            r9 = r6
            goto L_0x006d
        L_0x0058:
            r7 = move-exception
            goto L_0x008f
        L_0x005a:
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_IO_ERROR     // Catch:{ all -> 0x0058 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0058 }
            r1[r4] = r7     // Catch:{ all -> 0x0058 }
            r1[r3] = r0     // Catch:{ all -> 0x0058 }
            r1[r2] = r8     // Catch:{ all -> 0x0058 }
            r10.error(r5, r1)     // Catch:{ all -> 0x0058 }
            if (r9 == 0) goto L_0x0086
            r9.close()     // Catch:{ Exception -> 0x0080 }
            goto L_0x0086
        L_0x006d:
            com.unity3d.ads.cache.CacheError r5 = com.unity3d.ads.cache.CacheError.FILE_NOT_FOUND     // Catch:{ all -> 0x0058 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0058 }
            r1[r4] = r7     // Catch:{ all -> 0x0058 }
            r1[r3] = r0     // Catch:{ all -> 0x0058 }
            r1[r2] = r8     // Catch:{ all -> 0x0058 }
            r10.error(r5, r1)     // Catch:{ all -> 0x0058 }
            if (r9 == 0) goto L_0x0086
            r9.close()     // Catch:{ Exception -> 0x0080 }
            goto L_0x0086
        L_0x0080:
            r7 = move-exception
            java.lang.String r8 = "Error closing FileOutputStream"
            com.unity3d.ads.log.DeviceLog.exception(r8, r7)
        L_0x0086:
            r3 = 0
        L_0x0087:
            if (r3 == 0) goto L_0x008e
            java.lang.Object[] r7 = new java.lang.Object[r4]
            r10.invoke(r7)
        L_0x008e:
            return
        L_0x008f:
            if (r9 == 0) goto L_0x009b
            r9.close()     // Catch:{ Exception -> 0x0095 }
            goto L_0x009b
        L_0x0095:
            r8 = move-exception
            java.lang.String r9 = "Error closing FileOutputStream"
            com.unity3d.ads.log.DeviceLog.exception(r9, r8)
        L_0x009b:
            throw r7
        L_0x009c:
            com.unity3d.ads.cache.CacheError r9 = com.unity3d.ads.cache.CacheError.UNSUPPORTED_ENCODING
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r4] = r7
            r1[r3] = r0
            r1[r2] = r8
            r10.error(r9, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.api.Cache.setFileContent(java.lang.String, java.lang.String, java.lang.String, com.unity3d.ads.webview.bridge.WebViewCallback):void");
    }

    @WebViewExposed
    public static void getFiles(WebViewCallback webViewCallback) {
        File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory != null) {
            DeviceLog.debug("Unity Ads cache: checking app directory for Unity Ads cached files");
            File[] listFiles = cacheDirectory.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith(SdkProperties.getCacheFilePrefix());
                }
            });
            if (listFiles == null || listFiles.length == 0) {
                webViewCallback.invoke(new JSONArray());
            }
            try {
                JSONArray jSONArray = new JSONArray();
                for (File file : listFiles) {
                    String substring = file.getName().substring(SdkProperties.getCacheFilePrefix().length());
                    DeviceLog.debug("Unity Ads cache: found " + substring + ", " + file.length() + " bytes");
                    jSONArray.put(getFileJson(substring));
                }
                webViewCallback.invoke(jSONArray);
            } catch (JSONException e) {
                DeviceLog.exception("Error creating JSON", e);
                webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
            }
        }
    }

    @WebViewExposed
    public static void getFileInfo(String str, WebViewCallback webViewCallback) {
        try {
            webViewCallback.invoke(getFileJson(str));
        } catch (JSONException e) {
            DeviceLog.exception("Error creating JSON", e);
            webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getFilePath(String str, WebViewCallback webViewCallback) {
        if (new File(fileIdToFilename(str)).exists()) {
            webViewCallback.invoke(fileIdToFilename(str));
            return;
        }
        webViewCallback.error(CacheError.FILE_NOT_FOUND, new Object[0]);
    }

    @WebViewExposed
    public static void deleteFile(String str, WebViewCallback webViewCallback) {
        if (new File(fileIdToFilename(str)).delete()) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(CacheError.FILE_IO_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getHash(String str, WebViewCallback webViewCallback) {
        webViewCallback.invoke(Utilities.Sha256(str));
    }

    @WebViewExposed
    public static void setTimeouts(Integer num, Integer num2, WebViewCallback webViewCallback) {
        CacheThread.setConnectTimeout(num.intValue());
        CacheThread.setReadTimeout(num2.intValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getTimeouts(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(CacheThread.getConnectTimeout()), Integer.valueOf(CacheThread.getReadTimeout()));
    }

    @WebViewExposed
    public static void setProgressInterval(Integer num, WebViewCallback webViewCallback) {
        CacheThread.setProgressInterval(num.intValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getProgressInterval(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(CacheThread.getProgressInterval()));
    }

    @WebViewExposed
    public static void getFreeSpace(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Long.valueOf(Device.getFreeSpace(SdkProperties.getCacheDirectory())));
    }

    @WebViewExposed
    public static void getTotalSpace(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Long.valueOf(Device.getTotalSpace(SdkProperties.getCacheDirectory())));
    }

    @WebViewExposed
    public static void getMetaData(String str, JSONArray jSONArray, WebViewCallback webViewCallback) {
        try {
            SparseArray<String> metaData = getMetaData(fileIdToFilename(str), jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < metaData.size(); i++) {
                JSONArray jSONArray3 = new JSONArray();
                jSONArray3.put(metaData.keyAt(i));
                jSONArray3.put(metaData.valueAt(i));
                jSONArray2.put(jSONArray3);
            }
            webViewCallback.invoke(jSONArray2);
        } catch (JSONException e) {
            webViewCallback.error(CacheError.JSON_ERROR, e.getMessage());
        } catch (RuntimeException e2) {
            webViewCallback.error(CacheError.INVALID_ARGUMENT, e2.getMessage());
        } catch (IOException e3) {
            webViewCallback.error(CacheError.FILE_IO_ERROR, e3.getMessage());
        }
    }

    @WebViewExposed
    public static void getCacheDirectoryType(WebViewCallback webViewCallback) {
        CacheDirectory cacheDirectoryObject = SdkProperties.getCacheDirectoryObject();
        if (cacheDirectoryObject == null || cacheDirectoryObject.getCacheDirectory(ClientProperties.getApplicationContext()) == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
        } else if (!cacheDirectoryObject.getCacheDirectory(ClientProperties.getApplicationContext()).exists()) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_DOESNT_EXIST, new Object[0]);
        } else {
            CacheDirectoryType type = cacheDirectoryObject.getType();
            if (type == null) {
                webViewCallback.error(CacheError.CACHE_DIRECTORY_TYPE_NULL, new Object[0]);
                return;
            }
            webViewCallback.invoke(type.name());
        }
    }

    @WebViewExposed
    public static void getCacheDirectoryExists(WebViewCallback webViewCallback) {
        File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
            return;
        }
        webViewCallback.invoke(Boolean.valueOf(cacheDirectory.exists()));
    }

    @WebViewExposed
    public static void recreateCacheDirectory(WebViewCallback webViewCallback) {
        if (SdkProperties.getCacheDirectory().exists()) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_EXISTS, new Object[0]);
            return;
        }
        SdkProperties.setCacheDirectory((CacheDirectory) null);
        if (SdkProperties.getCacheDirectory() == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
        } else {
            webViewCallback.invoke(new Object[0]);
        }
    }

    @TargetApi(10)
    private static SparseArray<String> getMetaData(String str, JSONArray jSONArray) throws JSONException, IOException, RuntimeException {
        File file = new File(str);
        SparseArray<String> sparseArray = new SparseArray<>();
        if (file.exists()) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
            for (int i = 0; i < jSONArray.length(); i++) {
                int i2 = jSONArray.getInt(i);
                String extractMetadata = mediaMetadataRetriever.extractMetadata(i2);
                if (extractMetadata != null) {
                    sparseArray.put(i2, extractMetadata);
                }
            }
            return sparseArray;
        }
        throw new IOException("File: " + file.getAbsolutePath() + " doesn't exist");
    }

    private static String fileIdToFilename(String str) {
        return SdkProperties.getCacheDirectory() + "/" + SdkProperties.getCacheFilePrefix() + str;
    }

    private static JSONObject getFileJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", str);
        File file = new File(fileIdToFilename(str));
        if (file.exists()) {
            jSONObject.put("found", true);
            jSONObject.put("size", file.length());
            jSONObject.put("mtime", file.lastModified());
        } else {
            jSONObject.put("found", false);
        }
        return jSONObject;
    }
}
