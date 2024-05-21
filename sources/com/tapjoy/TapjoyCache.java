package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TapjoyCache {
    public static final String CACHE_DIRECTORY_NAME = "Tapjoy/Cache/";
    public static final int CACHE_LIMIT = -1;
    private static TapjoyCache a = null;
    public static boolean unit_test_mode = false;
    private Context b;
    /* access modifiers changed from: private */
    public TapjoyCacheMap c;
    /* access modifiers changed from: private */
    public Vector d;
    private ExecutorService e;
    /* access modifiers changed from: private */
    public File f;

    public TapjoyCache(Context context) {
        if (a == null || unit_test_mode) {
            a = this;
            this.b = context;
            this.c = new TapjoyCacheMap(context, -1);
            this.d = new Vector();
            this.e = Executors.newFixedThreadPool(5);
            if (Environment.getExternalStorageDirectory() != null) {
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tapjoy"));
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tjcache/tmp/"));
            }
            this.f = new File(this.b.getFilesDir() + "/Tapjoy/Cache/");
            if (!this.f.exists()) {
                if (this.f.mkdirs()) {
                    TapjoyLog.d("TapjoyCache", "Created directory at: " + this.f.getPath());
                } else {
                    TapjoyLog.e("TapjoyCache", "Error initalizing cache");
                    a = null;
                }
            }
            a();
        }
    }

    private void a() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
            File file = new File((String) next.getKey());
            if (!file.exists() || !file.isFile()) {
                TapjoyLog.d("TapjoyCache", "Removing reference to missing asset: " + ((String) next.getKey()));
                edit.remove((String) next.getKey()).apply();
            } else {
                TapjoyCachedAssetData fromRawJSONString = TapjoyCachedAssetData.fromRawJSONString(next.getValue().toString());
                if (fromRawJSONString != null) {
                    TapjoyLog.d("TapjoyCache", "Loaded Asset: " + fromRawJSONString.getAssetURL());
                    String b2 = b(fromRawJSONString.getAssetURL());
                    if (b2 == null || "".equals(b2) || b2.length() <= 0) {
                        TapjoyLog.e("TapjoyCache", "Removing asset because deserialization failed.");
                        edit.remove((String) next.getKey()).apply();
                    } else if (fromRawJSONString.getTimeOfDeathInSeconds() < System.currentTimeMillis() / 1000) {
                        TapjoyLog.d("TapjoyCache", "Asset expired, removing from cache: " + fromRawJSONString.getAssetURL());
                        if (fromRawJSONString.getLocalFilePath() != null && fromRawJSONString.getLocalFilePath().length() > 0) {
                            TapjoyUtil.deleteFileOrDirectory(new File(fromRawJSONString.getLocalFilePath()));
                        }
                    } else {
                        this.c.put(b2, fromRawJSONString);
                    }
                } else {
                    TapjoyLog.e("TapjoyCache", "Removing asset because deserialization failed.");
                    edit.remove((String) next.getKey()).apply();
                }
            }
        }
    }

    public void cacheAssetGroup(final JSONArray jSONArray, final TJCacheListener tJCacheListener) {
        if (jSONArray != null && jSONArray.length() > 0) {
            new Thread() {
                public final void run() {
                    TapjoyLog.d("TapjoyCache", "Starting to cache asset group size of " + jSONArray.length());
                    ArrayList<Future> arrayList = new ArrayList<>();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            Future cacheAssetFromJSONObject = TapjoyCache.this.cacheAssetFromJSONObject(jSONArray.getJSONObject(i));
                            if (cacheAssetFromJSONObject != null) {
                                arrayList.add(cacheAssetFromJSONObject);
                            }
                        } catch (JSONException unused) {
                            TapjoyLog.e("TapjoyCache", "Failed to load JSON object from JSONArray");
                        }
                    }
                    int i2 = 1;
                    for (Future future : arrayList) {
                        try {
                            if (((Boolean) future.get()).booleanValue()) {
                            }
                        } catch (InterruptedException e) {
                            TapjoyLog.e("TapjoyCache", "Caching thread failed: " + e.toString());
                        } catch (ExecutionException e2) {
                            TapjoyLog.e("TapjoyCache", "Caching thread failed: " + e2.toString());
                        }
                        i2 = 2;
                    }
                    TapjoyLog.d("TapjoyCache", "Finished caching group");
                    if (tJCacheListener != null) {
                        tJCacheListener.onCachingComplete(i2);
                    }
                }
            }.start();
        } else if (tJCacheListener != null) {
            tJCacheListener.onCachingComplete(1);
        }
    }

    public Future cacheAssetFromJSONObject(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("url");
            Long.valueOf(86400);
            return cacheAssetFromURL(string, jSONObject.optString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID), Long.valueOf(jSONObject.optLong(TapjoyConstants.TJC_TIME_TO_LIVE)).longValue());
        } catch (JSONException unused) {
            TapjoyLog.e("TapjoyCache", "Required parameters to cache an asset from JSON is not present");
            return null;
        }
    }

    public Future cacheAssetFromURL(String str, String str2, long j) {
        try {
            URL url = new URL(str);
            if (!this.d.contains(b(str))) {
                return startCachingThread(url, str2, j);
            }
            TapjoyLog.d("TapjoyCache", "URL is already in the process of being cached: " + str);
            return null;
        } catch (MalformedURLException unused) {
            TapjoyLog.d("TapjoyCache", "Invalid cache assetURL");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static String b(String str) {
        if (str.startsWith("//")) {
            str = "http:" + str;
        }
        try {
            return new URL(str).getFile();
        } catch (MalformedURLException unused) {
            TapjoyLog.e("TapjoyCache", "Invalid URL " + str);
            return "";
        }
    }

    public Future startCachingThread(URL url, String str, long j) {
        if (url != null) {
            return this.e.submit(new CacheAssetThread(url, str, j));
        }
        return null;
    }

    public void clearTapjoyCache() {
        TapjoyLog.d("TapjoyCache", "Cleaning Tapjoy cache!");
        TapjoyUtil.deleteFileOrDirectory(this.f);
        if (this.f.mkdirs()) {
            TapjoyLog.d("TapjoyCache", "Created new cache directory at: " + this.f.getPath());
        }
        this.c = new TapjoyCacheMap(this.b, -1);
    }

    public boolean removeAssetFromCache(String str) {
        String b2 = b(str);
        return (b2 == "" || this.c.remove((Object) b2) == null) ? false : true;
    }

    public boolean isURLDownloading(String str) {
        String b2;
        if (this.d == null || (b2 = b(str)) == "" || !this.d.contains(b2)) {
            return false;
        }
        return true;
    }

    public boolean isURLCached(String str) {
        return this.c.get(b(str)) != null;
    }

    public TapjoyCachedAssetData getCachedDataForURL(String str) {
        String b2 = b(str);
        if (b2 != "") {
            return (TapjoyCachedAssetData) this.c.get(b2);
        }
        return null;
    }

    public TapjoyCacheMap getCachedData() {
        return this.c;
    }

    public String getPathOfCachedURL(String str) {
        String b2 = b(str);
        if (b2 == "" || !this.c.containsKey(b2)) {
            return str;
        }
        TapjoyCachedAssetData tapjoyCachedAssetData = (TapjoyCachedAssetData) this.c.get(b2);
        if (new File(tapjoyCachedAssetData.getLocalFilePath()).exists()) {
            return tapjoyCachedAssetData.getLocalURL();
        }
        getInstance().removeAssetFromCache(str);
        return str;
    }

    public String cachedAssetsToJSON() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : this.c.entrySet()) {
            try {
                jSONObject.put(((String) entry.getKey()).toString(), ((TapjoyCachedAssetData) entry.getValue()).toRawJSONString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public String getCachedOfferIDs() {
        ArrayList arrayList = new ArrayList();
        if (this.c == null) {
            return "";
        }
        for (Map.Entry value : this.c.entrySet()) {
            String offerId = ((TapjoyCachedAssetData) value.getValue()).getOfferId();
            if (!(offerId == null || offerId.length() == 0 || arrayList.contains(offerId))) {
                arrayList.add(offerId);
            }
        }
        return TextUtils.join(",", arrayList);
    }

    public void printCacheInformation() {
        TapjoyLog.d("TapjoyCache", "------------- Cache Data -------------");
        TapjoyLog.d("TapjoyCache", "Number of files in cache: " + this.c.size());
        TapjoyLog.d("TapjoyCache", "Cache Size: " + TapjoyUtil.fileOrDirectorySize(this.f));
        TapjoyLog.d("TapjoyCache", "--------------------------------------");
    }

    public static TapjoyCache getInstance() {
        return a;
    }

    public static void setInstance(TapjoyCache tapjoyCache) {
        a = tapjoyCache;
    }

    public class CacheAssetThread implements Callable {
        private URL b;
        private String c;
        private long d;

        public CacheAssetThread(URL url, String str, long j) {
            this.b = url;
            this.c = str;
            this.d = j;
            if (this.d <= 0) {
                this.d = 86400;
            }
            TapjoyCache.this.d.add(TapjoyCache.b(this.b.toString()));
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(19:12|13|14|15|16|(2:18|(1:20)(2:21|22))|23|24|25|26|27|28|29|30|31|32|(1:35)|36|37) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0121 */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0139  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x01ad A[SYNTHETIC, Splitter:B:58:0x01ad] */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x01b2 A[SYNTHETIC, Splitter:B:62:0x01b2] */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x01e8 A[SYNTHETIC, Splitter:B:72:0x01e8] */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x01ed A[SYNTHETIC, Splitter:B:76:0x01ed] */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x01f4 A[SYNTHETIC, Splitter:B:82:0x01f4] */
        /* JADX WARNING: Removed duplicated region for block: B:86:0x01f9 A[SYNTHETIC, Splitter:B:86:0x01f9] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean call() {
            /*
                r11 = this;
                java.net.URL r0 = r11.b
                java.lang.String r0 = r0.toString()
                java.lang.String r0 = com.tapjoy.TapjoyCache.b((java.lang.String) r0)
                com.tapjoy.TapjoyCache r1 = com.tapjoy.TapjoyCache.this
                com.tapjoy.TapjoyCacheMap r1 = r1.c
                boolean r1 = r1.containsKey(r0)
                r2 = 1
                if (r1 == 0) goto L_0x008c
                com.tapjoy.TapjoyCache r1 = com.tapjoy.TapjoyCache.this
                com.tapjoy.TapjoyCacheMap r1 = r1.c
                java.lang.Object r1 = r1.get(r0)
                com.tapjoy.TapjoyCachedAssetData r1 = (com.tapjoy.TapjoyCachedAssetData) r1
                java.io.File r3 = new java.io.File
                java.lang.String r1 = r1.getLocalFilePath()
                r3.<init>(r1)
                boolean r1 = r3.exists()
                if (r1 == 0) goto L_0x0085
                long r3 = r11.d
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 == 0) goto L_0x004c
                com.tapjoy.TapjoyCache r1 = com.tapjoy.TapjoyCache.this
                com.tapjoy.TapjoyCacheMap r1 = r1.c
                java.lang.Object r1 = r1.get(r0)
                com.tapjoy.TapjoyCachedAssetData r1 = (com.tapjoy.TapjoyCachedAssetData) r1
                long r3 = r11.d
                r1.resetTimeToLive(r3)
                goto L_0x005e
            L_0x004c:
                com.tapjoy.TapjoyCache r1 = com.tapjoy.TapjoyCache.this
                com.tapjoy.TapjoyCacheMap r1 = r1.c
                java.lang.Object r1 = r1.get(r0)
                com.tapjoy.TapjoyCachedAssetData r1 = (com.tapjoy.TapjoyCachedAssetData) r1
                r3 = 86400(0x15180, double:4.26873E-319)
                r1.resetTimeToLive(r3)
            L_0x005e:
                java.lang.String r1 = "TapjoyCache"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Reseting time to live for "
                r3.<init>(r4)
                java.net.URL r4 = r11.b
                java.lang.String r4 = r4.toString()
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                com.tapjoy.TapjoyLog.d(r1, r3)
                com.tapjoy.TapjoyCache r1 = com.tapjoy.TapjoyCache.this
                java.util.Vector r1 = r1.d
                r1.remove(r0)
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                return r0
            L_0x0085:
                com.tapjoy.TapjoyCache r1 = com.tapjoy.TapjoyCache.getInstance()
                r1.removeAssetFromCache(r0)
            L_0x008c:
                java.lang.System.currentTimeMillis()
                r1 = 0
                java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x01fd }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01fd }
                r4.<init>()     // Catch:{ Exception -> 0x01fd }
                com.tapjoy.TapjoyCache r5 = com.tapjoy.TapjoyCache.this     // Catch:{ Exception -> 0x01fd }
                java.io.File r5 = r5.f     // Catch:{ Exception -> 0x01fd }
                r4.append(r5)     // Catch:{ Exception -> 0x01fd }
                java.lang.String r5 = "/"
                r4.append(r5)     // Catch:{ Exception -> 0x01fd }
                java.lang.String r5 = com.tapjoy.TapjoyUtil.SHA256(r0)     // Catch:{ Exception -> 0x01fd }
                r4.append(r5)     // Catch:{ Exception -> 0x01fd }
                java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x01fd }
                r3.<init>(r4)     // Catch:{ Exception -> 0x01fd }
                java.lang.String r4 = "TapjoyCache"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r6 = "Downloading and caching asset from: "
                r5.<init>(r6)
                java.net.URL r6 = r11.b
                r5.append(r6)
                java.lang.String r6 = " to "
                r5.append(r6)
                r5.append(r3)
                java.lang.String r5 = r5.toString()
                com.tapjoy.TapjoyLog.d(r4, r5)
                r4 = 0
                java.net.URL r5 = r11.b     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                java.net.URLConnection r5 = com.tapjoy.internal.fn.a((java.net.URL) r5)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r6 = 15000(0x3a98, float:2.102E-41)
                r5.setConnectTimeout(r6)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r6 = 30000(0x7530, float:4.2039E-41)
                r5.setReadTimeout(r6)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r5.connect()     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                boolean r6 = r5 instanceof java.net.HttpURLConnection     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                if (r6 == 0) goto L_0x0108
                r6 = r5
                java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                int r6 = r6.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r7 = 200(0xc8, float:2.8E-43)
                if (r6 != r7) goto L_0x00f4
                goto L_0x0108
            L_0x00f4:
                java.io.IOException r2 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                java.lang.String r7 = "Unexpected response code: "
                r5.<init>(r7)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r5.append(r6)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                java.lang.String r5 = r5.toString()     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r2.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                throw r2     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
            L_0x0108:
                java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                java.io.InputStream r5 = r5.getInputStream()     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                r6.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x01b6, Exception -> 0x0182, all -> 0x017f }
                java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ SocketTimeoutException -> 0x017b, Exception -> 0x0177, all -> 0x0172 }
                java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ SocketTimeoutException -> 0x017b, Exception -> 0x0177, all -> 0x0172 }
                r7.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x017b, Exception -> 0x0177, all -> 0x0172 }
                r5.<init>(r7)     // Catch:{ SocketTimeoutException -> 0x017b, Exception -> 0x0177, all -> 0x0172 }
                com.tapjoy.TapjoyUtil.writeFileToDevice(r6, r5)     // Catch:{ SocketTimeoutException -> 0x0170, Exception -> 0x016e, all -> 0x016c }
                r6.close()     // Catch:{ IOException -> 0x0121 }
            L_0x0121:
                r5.close()     // Catch:{ IOException -> 0x0124 }
            L_0x0124:
                com.tapjoy.TapjoyCachedAssetData r1 = new com.tapjoy.TapjoyCachedAssetData
                java.net.URL r4 = r11.b
                java.lang.String r4 = r4.toString()
                java.lang.String r3 = r3.getAbsolutePath()
                long r5 = r11.d
                r1.<init>(r4, r3, r5)
                java.lang.String r3 = r11.c
                if (r3 == 0) goto L_0x013e
                java.lang.String r3 = r11.c
                r1.setOfferID(r3)
            L_0x013e:
                com.tapjoy.TapjoyCache r3 = com.tapjoy.TapjoyCache.this
                com.tapjoy.TapjoyCacheMap r3 = r3.c
                r3.put((java.lang.String) r0, (com.tapjoy.TapjoyCachedAssetData) r1)
                com.tapjoy.TapjoyCache r3 = com.tapjoy.TapjoyCache.this
                java.util.Vector r3 = r3.d
                r3.remove(r0)
                java.lang.String r0 = "TapjoyCache"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "----- Download complete -----"
                r3.<init>(r4)
                java.lang.String r1 = r1.toString()
                r3.append(r1)
                java.lang.String r1 = r3.toString()
                com.tapjoy.TapjoyLog.d(r0, r1)
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                return r0
            L_0x016c:
                r0 = move-exception
                goto L_0x0174
            L_0x016e:
                r2 = move-exception
                goto L_0x0179
            L_0x0170:
                r2 = move-exception
                goto L_0x017d
            L_0x0172:
                r0 = move-exception
                r5 = r4
            L_0x0174:
                r4 = r6
                goto L_0x01f2
            L_0x0177:
                r2 = move-exception
                r5 = r4
            L_0x0179:
                r4 = r6
                goto L_0x0184
            L_0x017b:
                r2 = move-exception
                r5 = r4
            L_0x017d:
                r4 = r6
                goto L_0x01b8
            L_0x017f:
                r0 = move-exception
                r5 = r4
                goto L_0x01f2
            L_0x0182:
                r2 = move-exception
                r5 = r4
            L_0x0184:
                java.lang.String r6 = "TapjoyCache"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f1 }
                java.lang.String r8 = "Error caching asset: "
                r7.<init>(r8)     // Catch:{ all -> 0x01f1 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01f1 }
                r7.append(r2)     // Catch:{ all -> 0x01f1 }
                java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyLog.e((java.lang.String) r6, (java.lang.String) r2)     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyCache r2 = com.tapjoy.TapjoyCache.this     // Catch:{ all -> 0x01f1 }
                java.util.Vector r2 = r2.d     // Catch:{ all -> 0x01f1 }
                r2.remove(r0)     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyUtil.deleteFileOrDirectory(r3)     // Catch:{ all -> 0x01f1 }
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x01f1 }
                if (r4 == 0) goto L_0x01b0
                r4.close()     // Catch:{ IOException -> 0x01b0 }
            L_0x01b0:
                if (r5 == 0) goto L_0x01b5
                r5.close()     // Catch:{ IOException -> 0x01b5 }
            L_0x01b5:
                return r0
            L_0x01b6:
                r2 = move-exception
                r5 = r4
            L_0x01b8:
                java.lang.String r6 = "TapjoyCache"
                com.tapjoy.TapjoyErrorMessage r7 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyErrorMessage$ErrorType r8 = com.tapjoy.TapjoyErrorMessage.ErrorType.NETWORK_ERROR     // Catch:{ all -> 0x01f1 }
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f1 }
                java.lang.String r10 = "Network timeout during caching: "
                r9.<init>(r10)     // Catch:{ all -> 0x01f1 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01f1 }
                r9.append(r2)     // Catch:{ all -> 0x01f1 }
                java.lang.String r2 = r9.toString()     // Catch:{ all -> 0x01f1 }
                r7.<init>(r8, r2)     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyLog.e((java.lang.String) r6, (com.tapjoy.TapjoyErrorMessage) r7)     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyCache r2 = com.tapjoy.TapjoyCache.this     // Catch:{ all -> 0x01f1 }
                java.util.Vector r2 = r2.d     // Catch:{ all -> 0x01f1 }
                r2.remove(r0)     // Catch:{ all -> 0x01f1 }
                com.tapjoy.TapjoyUtil.deleteFileOrDirectory(r3)     // Catch:{ all -> 0x01f1 }
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x01f1 }
                if (r4 == 0) goto L_0x01eb
                r4.close()     // Catch:{ IOException -> 0x01eb }
            L_0x01eb:
                if (r5 == 0) goto L_0x01f0
                r5.close()     // Catch:{ IOException -> 0x01f0 }
            L_0x01f0:
                return r0
            L_0x01f1:
                r0 = move-exception
            L_0x01f2:
                if (r4 == 0) goto L_0x01f7
                r4.close()     // Catch:{ IOException -> 0x01f7 }
            L_0x01f7:
                if (r5 == 0) goto L_0x01fc
                r5.close()     // Catch:{ IOException -> 0x01fc }
            L_0x01fc:
                throw r0
            L_0x01fd:
                com.tapjoy.TapjoyCache r2 = com.tapjoy.TapjoyCache.this
                java.util.Vector r2 = r2.d
                r2.remove(r0)
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyCache.CacheAssetThread.call():java.lang.Boolean");
        }
    }
}
