package com.tapdaq.adapters.tapdaq.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.network.HttpClientBase;
import com.tapdaq.sdk.network.TClient;
import com.tapdaq.sdk.storage.FileStorage;
import com.tapjoy.TJAdUnitConstants;
import java.net.MalformedURLException;
import java.net.URL;

public class TMCache {
    public void cacheVideo(final Context context, final String str, final TMAdListenerBase tMAdListenerBase) {
        try {
            if (new FileStorage(context).exists(new URL(str).getPath().replace("/", "~"), TJAdUnitConstants.String.DATA)) {
                TMListenerHandler.DidLoad(tMAdListenerBase);
            } else {
                TClient.getInstance().executeFileGet(str, new HttpClientBase.ResponseFileHandler() {
                    public void onSuccess(byte[] bArr) {
                        try {
                            TMCache.this.cacheFile(context, str, bArr);
                            new Handler(context.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    TMListenerHandler.DidLoad(tMAdListenerBase);
                                }
                            });
                        } catch (MalformedURLException e) {
                            TLog.error((Exception) e);
                            new Handler(context.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(TapdaqError.FAILED_TO_CACHE_VIDEO, TapdaqError.FAILED_TO_CACHE_VIDEO_MESSAGE));
                                }
                            });
                        }
                    }

                    public void onError(Exception exc) {
                        new Handler(context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(TapdaqError.FAILED_TO_DOWNLOAD_VIDEO, TapdaqError.FAILED_TO_DOWNLOAD_VIDEO_MESSAGE));
                            }
                        });
                    }
                });
            }
        } catch (MalformedURLException e) {
            TLog.error((Exception) e);
            TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(TapdaqError.FAILED_TO_CACHE_VIDEO_INVALID_PATH, TapdaqError.FAILED_TO_CACHE_VIDEO_INVALID_PATH_MESSAGE));
        }
    }

    /* access modifiers changed from: private */
    public void cacheFile(Context context, String str, byte[] bArr) throws MalformedURLException {
        new FileStorage(context).saveFile(new URL(str).getPath().replace("/", "~"), bArr, TJAdUnitConstants.String.DATA, true);
    }

    public void cache(final Context context, final String str, final TMAdListenerBase tMAdListenerBase) {
        if (getCachedBitmap(context, str, tMAdListenerBase) == null) {
            TClient.getInstance().executeImageGET(context, str, TDDeviceInfo.getWidth(context), TDDeviceInfo.getHeight(context), new HttpClientBase.ResponseImageHandler() {
                public void onSuccess(Bitmap bitmap) {
                    TMCache.this.cache(context, str, bitmap, tMAdListenerBase);
                    new Handler(context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            TMListenerHandler.DidLoad(tMAdListenerBase);
                        }
                    });
                }

                public void onError(Exception exc) {
                    if (tMAdListenerBase != null) {
                        new Handler(context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(320, TapdaqError.FAILED_TO_DOWNLOAD_IMAGE_MESSAGE));
                            }
                        });
                    }
                }
            });
            return;
        }
        new Handler(context.getMainLooper()).post(new Runnable() {
            public void run() {
                TMListenerHandler.DidLoad(tMAdListenerBase);
            }
        });
    }

    public void cache(Context context, String str, Bitmap bitmap) {
        cache(context, str, bitmap, (TMAdListenerBase) null);
    }

    public void cache(Context context, String str, Bitmap bitmap, final TMAdListenerBase tMAdListenerBase) {
        try {
            new FileStorage(context).saveImage(new URL(str).getPath().replace("/", "~"), bitmap, true);
        } catch (Exception e) {
            TLog.error(e);
            if (tMAdListenerBase != null) {
                new Handler(context.getMainLooper()).post(new Runnable() {
                    public void run() {
                        TMAdError tMAdError = new TMAdError(321, TapdaqError.FAILED_TO_CACHE_IMAGE_MESSAGE);
                        tMAdError.addSubError("Exception", new TMAdError(0, e.getMessage()));
                        tMAdListenerBase.didFailToLoad(tMAdError);
                    }
                });
            }
        }
    }

    public Bitmap getCachedBitmap(Context context, String str) {
        return getCachedBitmap(context, str, (TMAdListenerBase) null);
    }

    public Bitmap getCachedBitmap(Context context, String str, final TMAdListenerBase tMAdListenerBase) {
        try {
            return new FileStorage(context).loadImage(new URL(str).getPath().replace("/", "~"));
        } catch (Exception e) {
            TLog.error(e);
            if (tMAdListenerBase == null) {
                return null;
            }
            new Handler(context.getMainLooper()).post(new Runnable() {
                public void run() {
                    TMAdError tMAdError = new TMAdError(322, TapdaqError.FAILED_TO_CACHE_IMAGE_INVALID_PATH_MESSAGE);
                    tMAdError.addSubError("Exception", new TMAdError(0, e.getMessage()));
                    tMAdListenerBase.didFailToLoad(tMAdError);
                }
            });
            return null;
        }
    }
}
