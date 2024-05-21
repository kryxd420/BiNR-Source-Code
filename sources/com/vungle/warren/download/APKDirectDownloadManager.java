package com.vungle.warren.download;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.vungle.warren.NetworkStateReceiver;
import com.vungle.warren.locale.LocaleString;
import com.vungle.warren.network.APKDirectDownloader;
import com.vungle.warren.network.Downloader;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class APKDirectDownloadManager {
    private static final String APK_POSTFIX = "apk";
    public static final int DIRECT_DOWNLOAD_FLAG_DISABLED = 0;
    public static final int DIRECT_DOWNLOAD_FLAG_ENABLED = 1;
    public static final int DIRECT_DOWNLOAD_FLAG_NOT_SET = -1;
    private static final String FOLDER_APK = "apk";
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = "DirectDownloadManager";
    /* access modifiers changed from: private */
    public static APKDirectDownloadManager _instance = new APKDirectDownloadManager();
    private int apkDirectDownloadStatus = -1;
    private WeakReference<Context> context;
    private APKDirectDownloader downloader;
    private boolean isNetworkReceiverEnabled = false;
    /* access modifiers changed from: private */
    public boolean isWifiEnabled = false;
    private NotificationCompat.Builder mBuilder;
    /* access modifiers changed from: private */
    public List<Integer> notificationList = new ArrayList();
    private NotificationManager notifyManager;
    private List<String> pendingDownloadUrl = new ArrayList();
    private List<Integer> pendingNotificationList = new ArrayList();

    public static void init(Context context2) {
        _instance.context = new WeakReference<>(context2);
        _instance.clearDownloadApkCache();
        if (_instance.downloader == null) {
            _instance.downloader = new APKDirectDownloader((Context) _instance.context.get());
        }
    }

    public static boolean isDirectDownloadEnabled(boolean z, boolean z2) {
        int i = _instance.apkDirectDownloadStatus;
        if (i != -1) {
            if (i != 1) {
                return false;
            }
            return z2;
        } else if (z) {
            return z2;
        } else {
            return false;
        }
    }

    public static void setDirectDownloadStatus(int i) {
        _instance.apkDirectDownloadStatus = i;
    }

    public static void download(String str) {
        enableNetworkListener(true, getContext());
        if (!TextUtils.isEmpty(str)) {
            APKDirectDownloadManager aPKDirectDownloadManager = _instance;
            if (isApkUrl(str)) {
                try {
                    String valueOf = String.valueOf(System.currentTimeMillis());
                    final int hashCode = valueOf.hashCode();
                    if (_instance.isWifiEnabled()) {
                        _instance.notificationList.add(Integer.valueOf(hashCode));
                        final File apkDirectory = _instance.getApkDirectory(valueOf);
                        _instance.downloader.download(str, apkDirectory, new Downloader.Listener() {
                            public void onComplete(File file) {
                                Log.d(APKDirectDownloadManager.TAG, "download complete :" + file.getAbsolutePath());
                                APKDirectDownloadManager._instance.installApk(apkDirectory);
                                if (!APKDirectDownloadManager.isDownloadTaskRunning()) {
                                    APKDirectDownloadManager.enableNetworkListener(false, APKDirectDownloadManager.getContext());
                                }
                                APKDirectDownloadManager._instance.notificationList.remove(Integer.valueOf(hashCode));
                            }

                            public void onProgress(int i) {
                                Log.d(APKDirectDownloadManager.TAG, "download progress :" + i);
                                APKDirectDownloadManager._instance.notifyProgress(hashCode, i);
                            }

                            public void onError(Throwable th) {
                                Log.d(APKDirectDownloadManager.TAG, "download complete :" + th.getMessage());
                                if (APKDirectDownloadManager.isDownloadTaskRunning()) {
                                    APKDirectDownloadManager.enableNetworkListener(false, APKDirectDownloadManager.getContext());
                                }
                                APKDirectDownloadManager._instance.dismissNotification(hashCode);
                            }
                        });
                        return;
                    }
                    _instance.notifyProgress(hashCode, -1);
                    _instance.pendingNotificationList.add(Integer.valueOf(hashCode));
                    _instance.pendingDownloadUrl.add(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Intent intent = new Intent();
                intent.setClass((Context) _instance.context.get(), VungleWebViewActivity.class);
                intent.putExtra(VungleWebViewActivity.INTENT_URL, str);
                intent.setFlags(268435456);
                ((Context) _instance.context.get()).startActivity(intent);
            }
        }
    }

    public static boolean isApkUrl(String str) {
        return MimeTypeMap.getFileExtensionFromUrl(str).toLowerCase().endsWith("apk");
    }

    private void clearDownloadApkCache() {
        new Thread(new Runnable() {
            public void run() {
                File[] listFiles = APKDirectDownloadManager.this.getCacheDirectory().listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].isFile() && listFiles[i].exists() && listFiles[i].isFile()) {
                            Log.d(APKDirectDownloadManager.TAG, "Clear cache:" + listFiles[i].getName());
                            listFiles[i].delete();
                        }
                    }
                }
            }
        }).start();
    }

    private File getApkDirectory(String str) throws IllegalStateException {
        File file = new File(getCacheDirectory().getPath() + File.separator);
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(getCacheDirectory().getPath() + File.separator + str + "." + "apk");
    }

    /* access modifiers changed from: private */
    public File getCacheDirectory() throws IllegalStateException {
        if (this.context != null) {
            File file = new File(((Context) this.context.get()).getCacheDir().getPath() + File.separator + FOLDER_NAME + File.separator + "apk");
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        }
        throw new IllegalStateException("Context has expired, cannot continue.");
    }

    /* access modifiers changed from: private */
    public void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        if (Build.VERSION.SDK_INT > 23) {
            intent.setDataAndType(FileProvider.getUriForFile((Context) this.context.get(), "com.vungle.download.provider", file), "application/vnd.android.package-archive");
            intent.addFlags(3);
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        if (((Context) this.context.get()).getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            ((Context) this.context.get()).startActivity(intent);
        }
        String name = file.getName();
        String substring = name.substring(0, name.length() - 4);
        Log.d(TAG, "identifier is" + substring);
        dismissNotification(substring.hashCode());
    }

    /* access modifiers changed from: private */
    public void notifyProgress(int i, int i2) {
        Log.d(TAG, "notify id is :" + i + " progress:" + i2);
        if (this.notifyManager == null) {
            Context context2 = (Context) this.context.get();
            this.notifyManager = (NotificationManager) ((Context) this.context.get()).getSystemService("notification");
            this.mBuilder = new NotificationCompat.Builder((Context) this.context.get());
            this.mBuilder.setSmallIcon(17301634);
        }
        if (i2 == -1 || !this.isWifiEnabled) {
            this.mBuilder.setContentTitle(LocaleString.getLocaleText(4)).setContentText(LocaleString.getLocaleText(2)).setProgress(0, 0, false);
        } else if (i2 < 0 || i2 >= 100) {
            this.mBuilder.setContentText(LocaleString.getLocaleText(5)).setProgress(0, 0, false);
        } else {
            this.mBuilder.setContentTitle(LocaleString.getLocaleText(4)).setContentText(LocaleString.getLocaleText(3));
            this.mBuilder.setProgress(100, i2, false);
        }
        this.notifyManager.notify(i, this.mBuilder.build());
    }

    /* access modifiers changed from: private */
    public void dismissNotification(int i) {
        if (this.notifyManager != null) {
            this.notifyManager.cancel(i);
        }
    }

    private boolean isWifiEnabled() {
        boolean z = false;
        if (PermissionChecker.checkCallingOrSelfPermission((Context) this.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
            Context context2 = (Context) this.context.get();
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) ((Context) this.context.get()).getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                switch (activeNetworkInfo.getType()) {
                    case 1:
                    case 6:
                        z = true;
                        break;
                }
            } else {
                return false;
            }
        }
        this.isWifiEnabled = z;
        return z;
    }

    public static void handleDownload(Context context2) {
        if (context2 != null) {
            if (_instance == null || !isDownloadTaskRunning() || _instance.apkDirectDownloadStatus == 0) {
                enableNetworkListener(false, context2);
                return;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = true;
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting() || activeNetworkInfo.getType() != 1) {
                z = false;
            }
            if (z && !_instance.isWifiEnabled) {
                resumeDownload();
            } else if (_instance.isWifiEnabled) {
                pauseDownload();
            }
        }
    }

    /* access modifiers changed from: private */
    public static void resumeDownload() {
        _instance.isWifiEnabled = true;
        _instance.downloader.resume();
        if (!_instance.pendingNotificationList.isEmpty()) {
            for (Integer intValue : _instance.pendingNotificationList) {
                _instance.dismissNotification(intValue.intValue());
            }
        }
        if (!_instance.pendingDownloadUrl.isEmpty()) {
            for (String download : _instance.pendingDownloadUrl) {
                download(download);
            }
            _instance.pendingDownloadUrl.clear();
        }
    }

    /* access modifiers changed from: private */
    public static void pauseDownload() {
        _instance.isWifiEnabled = false;
        _instance.downloader.pause();
        for (Integer intValue : _instance.notificationList) {
            _instance.notifyProgress(intValue.intValue(), -1);
        }
    }

    public static void enableNetworkListener(boolean z, Context context2) {
        if (_instance.isNetworkReceiverEnabled != z && context2 != null) {
            _instance.isNetworkReceiverEnabled = z;
            if (Build.VERSION.SDK_INT >= 24) {
                AnonymousClass3 r4 = new ConnectivityManager.NetworkCallback() {
                    @RequiresApi(api = 24)
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        Log.d(APKDirectDownloadManager.TAG, "onAvailable:" + network);
                        APKDirectDownloadManager.resumeDownload();
                        boolean unused = APKDirectDownloadManager._instance.isWifiEnabled = true;
                    }

                    @RequiresApi(api = 24)
                    public void onLost(Network network) {
                        super.onLost(network);
                        Log.d(APKDirectDownloadManager.TAG, "onLost:" + network);
                        APKDirectDownloadManager.pauseDownload();
                        boolean unused = APKDirectDownloadManager._instance.isWifiEnabled = false;
                    }
                };
                ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) _instance.context.get()).getSystemService("connectivity");
                if (connectivityManager != null) {
                    if (z) {
                        NetworkRequest.Builder builder = new NetworkRequest.Builder();
                        builder.addTransportType(1).addCapability(12);
                        connectivityManager.registerNetworkCallback(builder.build(), r4);
                        return;
                    }
                    connectivityManager.unregisterNetworkCallback(r4);
                }
            } else if (z) {
                NetworkStateReceiver.enableBroadcastReceiver(context2, true);
            } else {
                NetworkStateReceiver.enableBroadcastReceiver(context2, false);
            }
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public static Context getContext() {
        if (_instance == null || _instance.context == null) {
            return null;
        }
        return (Context) _instance.context.get();
    }

    public static boolean isDownloadTaskRunning() {
        return !_instance.pendingDownloadUrl.isEmpty() || _instance.downloader.isDownloadTaskRunning();
    }
}
