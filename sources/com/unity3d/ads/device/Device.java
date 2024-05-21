package com.unity3d.ads.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import com.applovin.sdk.AppLovinEventTypes;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.ClientProperties;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {

    public enum MemoryInfoType {
        TOTAL_MEMORY,
        FREE_MEMORY
    }

    public static int getApiLevel() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static int getScreenLayout() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getConfiguration().screenLayout;
        }
        return -1;
    }

    @SuppressLint({"DefaultLocale"})
    public static String getAndroidId() {
        try {
            return Settings.Secure.getString(ClientProperties.getApplicationContext().getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching androidId", e);
            return null;
        }
    }

    public static String getAdvertisingTrackingId() {
        return AdvertisingId.getAdvertisingTrackingId();
    }

    public static boolean isLimitAdTrackingEnabled() {
        return AdvertisingId.getLimitedAdTracking();
    }

    public static boolean isUsingWifi() {
        ConnectivityManager connectivityManager;
        if (ClientProperties.getApplicationContext() == null || (connectivityManager = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")) == null) {
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) ClientProperties.getApplicationContext().getSystemService("phone");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !connectivityManager.getBackgroundDataSetting() || !connectivityManager.getActiveNetworkInfo().isConnected() || telephonyManager == null || activeNetworkInfo.getType() != 1 || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static int getNetworkType() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkType();
        }
        return -1;
    }

    public static boolean getNetworkMetered() {
        ConnectivityManager connectivityManager;
        if (ClientProperties.getApplicationContext() == null || Build.VERSION.SDK_INT < 16 || (connectivityManager = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")) == null) {
            return false;
        }
        return connectivityManager.isActiveNetworkMetered();
    }

    public static String getNetworkOperator() {
        return ClientProperties.getApplicationContext() != null ? ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkOperator() : "";
    }

    public static String getNetworkOperatorName() {
        return ClientProperties.getApplicationContext() != null ? ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkOperatorName() : "";
    }

    public static int getScreenDensity() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return -1;
    }

    public static int getScreenWidth() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        }
        return -1;
    }

    public static int getScreenHeight() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        }
        return -1;
    }

    public static boolean isActiveNetworkConnected() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (ClientProperties.getApplicationContext() == null || (connectivityManager = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isAppInstalled(String str) {
        if (ClientProperties.getApplicationContext() != null) {
            try {
                PackageInfo packageInfo = ClientProperties.getApplicationContext().getPackageManager().getPackageInfo(str, 0);
                if (packageInfo == null || packageInfo.packageName == null || !str.equals(packageInfo.packageName)) {
                    return false;
                }
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
        return false;
    }

    public static List<Map<String, Object>> getInstalledPackages(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (ClientProperties.getApplicationContext() != null) {
            PackageManager packageManager = ClientProperties.getApplicationContext().getPackageManager();
            for (PackageInfo next : packageManager.getInstalledPackages(0)) {
                HashMap hashMap = new HashMap();
                if (z) {
                    hashMap.put("name", Utilities.Sha256(next.packageName));
                } else {
                    hashMap.put("name", next.packageName);
                }
                if (next.firstInstallTime > 0) {
                    hashMap.put("time", Long.valueOf(next.firstInstallTime));
                }
                String installerPackageName = packageManager.getInstallerPackageName(next.packageName);
                if (installerPackageName != null && !installerPackageName.isEmpty()) {
                    hashMap.put(TapjoyConstants.TJC_INSTALLER, installerPackageName);
                }
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    public static String getUniqueEventId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isWiredHeadsetOn() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((AudioManager) ClientProperties.getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
        }
        return false;
    }

    public static String getSystemProperty(String str, String str2) {
        if (str2 != null) {
            return System.getProperty(str, str2);
        }
        return System.getProperty(str);
    }

    public static int getRingerMode() {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager audioManager = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getRingerMode();
        }
        return -2;
    }

    public static int getStreamVolume(int i) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager audioManager = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamVolume(i);
        }
        return -2;
    }

    public static int getStreamMaxVolume(int i) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager audioManager = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamMaxVolume(i);
        }
        return -2;
    }

    public static int getScreenBrightness() {
        if (ClientProperties.getApplicationContext() != null) {
            return Settings.System.getInt(ClientProperties.getApplicationContext().getContentResolver(), "screen_brightness", -1);
        }
        return -1;
    }

    public static long getFreeSpace(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        return (long) Math.round((float) (file.getFreeSpace() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public static long getTotalSpace(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        return (long) Math.round((float) (file.getTotalSpace() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public static float getBatteryLevel() {
        Intent registerReceiver;
        if (ClientProperties.getApplicationContext() == null || (registerReceiver = ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) == null) {
            return -1.0f;
        }
        return ((float) registerReceiver.getIntExtra(AppLovinEventTypes.USER_COMPLETED_LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
    }

    public static int getBatteryStatus() {
        Intent registerReceiver;
        if (ClientProperties.getApplicationContext() == null || (registerReceiver = ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) == null) {
            return -1;
        }
        return registerReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
    }

    public static long getTotalMemory() {
        return getMemoryInfo(MemoryInfoType.TOTAL_MEMORY);
    }

    public static long getFreeMemory() {
        return getMemoryInfo(MemoryInfoType.FREE_MEMORY);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long getMemoryInfo(com.unity3d.ads.device.Device.MemoryInfoType r5) {
        /*
            int[] r0 = com.unity3d.ads.device.Device.AnonymousClass1.$SwitchMap$com$unity3d$ads$device$Device$MemoryInfoType
            int r1 = r5.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x000f;
                case 2: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r0 = -1
            goto L_0x0010
        L_0x000d:
            r0 = 2
            goto L_0x0010
        L_0x000f:
            r0 = 1
        L_0x0010:
            r1 = 0
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x003b }
            java.lang.String r3 = "/proc/meminfo"
            java.lang.String r4 = "r"
            r2.<init>(r3, r4)     // Catch:{ IOException -> 0x003b }
            r3 = 0
        L_0x001b:
            if (r3 >= r0) goto L_0x002a
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            int r3 = r3 + 1
            goto L_0x001b
        L_0x0024:
            r5 = move-exception
            r1 = r2
            goto L_0x005d
        L_0x0027:
            r0 = move-exception
            r1 = r2
            goto L_0x003c
        L_0x002a:
            long r0 = getMemoryValueFromString(r1)     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r2.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0038
        L_0x0032:
            r5 = move-exception
            java.lang.String r2 = "Error closing RandomAccessFile"
            com.unity3d.ads.log.DeviceLog.exception(r2, r5)
        L_0x0038:
            return r0
        L_0x0039:
            r5 = move-exception
            goto L_0x005d
        L_0x003b:
            r0 = move-exception
        L_0x003c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0039 }
            r2.<init>()     // Catch:{ all -> 0x0039 }
            java.lang.String r3 = "Error while reading memory info: "
            r2.append(r3)     // Catch:{ all -> 0x0039 }
            r2.append(r5)     // Catch:{ all -> 0x0039 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0039 }
            com.unity3d.ads.log.DeviceLog.exception(r5, r0)     // Catch:{ all -> 0x0039 }
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x005a
        L_0x0054:
            r5 = move-exception
            java.lang.String r0 = "Error closing RandomAccessFile"
            com.unity3d.ads.log.DeviceLog.exception(r0, r5)
        L_0x005a:
            r0 = -1
            return r0
        L_0x005d:
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0067
        L_0x0061:
            r0 = move-exception
            java.lang.String r1 = "Error closing RandomAccessFile"
            com.unity3d.ads.log.DeviceLog.exception(r1, r0)
        L_0x0067:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.device.Device.getMemoryInfo(com.unity3d.ads.device.Device$MemoryInfoType):long");
    }

    private static long getMemoryValueFromString(String str) {
        if (str == null) {
            return -1;
        }
        Matcher matcher = Pattern.compile("(\\d+)").matcher(str);
        String str2 = "";
        while (matcher.find()) {
            str2 = matcher.group(1);
        }
        return Long.parseLong(str2);
    }

    public static boolean isRooted() {
        try {
            return searchPathForBinary("su");
        } catch (Exception e) {
            DeviceLog.exception("Rooted check failed", e);
            return false;
        }
    }

    public static Boolean isAdbEnabled() {
        if (getApiLevel() < 17) {
            return oldAdbStatus();
        }
        return newAdbStatus();
    }

    private static Boolean oldAdbStatus() {
        try {
            boolean z = true;
            if (1 != Settings.Secure.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching adb enabled status", e);
            return null;
        }
    }

    @TargetApi(17)
    private static Boolean newAdbStatus() {
        try {
            boolean z = true;
            if (1 != Settings.Global.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching adb enabled status", e);
            return null;
        }
    }

    private static boolean searchPathForBinary(String str) {
        File[] listFiles;
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory() && (listFiles = file2.listFiles()) != null) {
                for (File name : listFiles) {
                    if (name.getName().equals(str)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public static String getGLVersion() {
        ActivityManager activityManager;
        ConfigurationInfo deviceConfigurationInfo;
        if (ClientProperties.getApplicationContext() == null || (activityManager = (ActivityManager) ClientProperties.getApplicationContext().getSystemService("activity")) == null || (deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo()) == null) {
            return null;
        }
        return deviceConfigurationInfo.getGlEsVersion();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0021 A[SYNTHETIC, Splitter:B:13:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getApkDigest() throws java.lang.Exception {
        /*
            android.content.Context r0 = com.unity3d.ads.properties.ClientProperties.getApplicationContext()
            java.lang.String r0 = r0.getPackageCodePath()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x001e }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x001e }
            r3.<init>(r0)     // Catch:{ all -> 0x001e }
            r2.<init>(r3)     // Catch:{ all -> 0x001e }
            java.lang.String r0 = com.unity3d.ads.misc.Utilities.Sha256((java.io.InputStream) r2)     // Catch:{ all -> 0x001b }
            r2.close()     // Catch:{ IOException -> 0x001a }
        L_0x001a:
            return r0
        L_0x001b:
            r0 = move-exception
            r1 = r2
            goto L_0x001f
        L_0x001e:
            r0 = move-exception
        L_0x001f:
            if (r1 == 0) goto L_0x0024
            r1.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.device.Device.getApkDigest():java.lang.String");
    }

    public static String getCertificateFingerprint() {
        try {
            Signature[] signatureArr = ClientProperties.getApplicationContext().getPackageManager().getPackageInfo(ClientProperties.getApplicationContext().getPackageName(), 64).signatures;
            if (signatureArr == null || signatureArr.length < 1) {
                return null;
            }
            return Utilities.toHexString(MessageDigest.getInstance("SHA-1").digest(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getEncoded()));
        } catch (Exception e) {
            DeviceLog.exception("Exception when signing certificate fingerprint", e);
            return null;
        }
    }

    public static String getBoard() {
        return Build.BOARD;
    }

    public static String getBootloader() {
        return Build.BOOTLOADER;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String getHost() {
        return Build.HOST;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public static ArrayList<String> getSupportedAbis() {
        if (getApiLevel() < 21) {
            return getOldAbiList();
        }
        return getNewAbiList();
    }

    public static List<Sensor> getSensorList() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((SensorManager) ClientProperties.getApplicationContext().getSystemService("sensor")).getSensorList(-1);
        }
        return null;
    }

    public static boolean isUSBConnected() {
        Intent registerReceiver;
        if (ClientProperties.getApplicationContext() == null || (registerReceiver = ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.hardware.usb.action.USB_STATE"))) == null) {
            return false;
        }
        return registerReceiver.getBooleanExtra("connected", false);
    }

    public static long getCPUCount() {
        return (long) Runtime.getRuntime().availableProcessors();
    }

    public static long getUptime() {
        return SystemClock.uptimeMillis();
    }

    public static long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public static String getBuildId() {
        return Build.ID;
    }

    public static String getBuildVersionIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    private static ArrayList<String> getOldAbiList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Build.CPU_ABI);
        arrayList.add(Build.CPU_ABI2);
        return arrayList;
    }

    @TargetApi(21)
    private static ArrayList<String> getNewAbiList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
        return arrayList;
    }

    public static Map<String, String> getProcessInfo() {
        HashMap hashMap = new HashMap();
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile("/proc/self/stat", "r");
            try {
                hashMap.put("stat", randomAccessFile2.readLine());
            } catch (IOException e) {
                RandomAccessFile randomAccessFile3 = randomAccessFile2;
                e = e;
                randomAccessFile = randomAccessFile3;
                try {
                    DeviceLog.exception("Error while reading processor info: ", e);
                    randomAccessFile.close();
                    return hashMap;
                } catch (Throwable th) {
                    th = th;
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                        DeviceLog.exception("Error closing RandomAccessFile", e2);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = randomAccessFile2;
                randomAccessFile.close();
                throw th;
            }
            try {
                randomAccessFile2.close();
            } catch (IOException e3) {
                DeviceLog.exception("Error closing RandomAccessFile", e3);
            }
        } catch (IOException e4) {
            e = e4;
            DeviceLog.exception("Error while reading processor info: ", e);
            randomAccessFile.close();
            return hashMap;
        }
        return hashMap;
    }
}
