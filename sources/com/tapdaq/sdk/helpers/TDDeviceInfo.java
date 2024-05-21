package com.tapdaq.sdk.helpers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.tapjoy.TJAdUnitConstants;
import java.util.Locale;

public class TDDeviceInfo {
    static final Double FAT_VS_SLIM_RATIO_THRESHOLD = Double.valueOf(1.42d);
    private static String mAdvertisingId = null;
    private static Boolean mLimitAdTracking = null;

    @Nullable
    public static String getAdvertisementId(Context context) {
        if (mAdvertisingId == null || mAdvertisingId.isEmpty()) {
            try {
                mAdvertisingId = AdvertisingIdClient.getAdvertisingIdInfo(context.getApplicationContext()).getId();
            } catch (Exception e) {
                TLog.error("Something went wrong while getting advertisement id!", e);
            }
        }
        return mAdvertisingId;
    }

    public static boolean getLimitAdTracking(Context context) {
        if (mLimitAdTracking == null) {
            try {
                mLimitAdTracking = Boolean.valueOf(AdvertisingIdClient.getAdvertisingIdInfo(context.getApplicationContext()).isLimitAdTrackingEnabled());
            } catch (Exception e) {
                TLog.error("Something went wrong while getting limit ad tracking setting!", e);
            }
        }
        if (mLimitAdTracking == null) {
            return false;
        }
        return mLimitAdTracking.booleanValue();
    }

    public static float getResolutionFloat(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            TLog.error("Could not get density!", e);
            return 0.0f;
        }
    }

    public static String getCountry(Context context) {
        String str;
        try {
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleList locales = configuration.getLocales();
                if (locales.size() > 0) {
                    str = locales.get(0).getCountry();
                } else {
                    str = "";
                }
            } else {
                str = configuration.locale.getCountry();
            }
            return str;
        } catch (Exception e) {
            TLog.error("Something went wrong while getting country!", e);
            return "";
        }
    }

    public static String getLocale(Context context) {
        String str;
        try {
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleList locales = configuration.getLocales();
                if (locales.size() > 0) {
                    str = locales.get(0).getLanguage();
                } else {
                    str = "";
                }
            } else {
                str = configuration.locale.getLanguage();
            }
            return str;
        } catch (Exception e) {
            TLog.error("Something went wrong while getting Locale!", e);
            return "";
        }
    }

    public static int getWidth(Context context) {
        try {
            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e) {
            TLog.error("Could not get screen width!", e);
            return 0;
        }
    }

    public static int getHeight(Context context) {
        try {
            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            TLog.error("Could not get screen height!", e);
            return 0;
        }
    }

    public static String getDeviceOrientation(Context context) {
        return isDevicePortrait(context) ? "portrait" : TJAdUnitConstants.String.LANDSCAPE;
    }

    public static boolean isDevicePortrait(Context context) {
        int i;
        try {
            i = context.getResources().getConfiguration().orientation;
        } catch (Exception e) {
            TLog.error("Could not get orientation!", e);
            i = 0;
        }
        if (i == 1) {
            return true;
        }
        return false;
    }

    public static boolean isTablet(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
            float f2 = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            if (Math.sqrt((double) ((f2 * f2) + (f * f))) >= 6.5d) {
                return true;
            }
            return false;
        } catch (Exception e) {
            TLog.error(e);
            return false;
        }
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceAPI() {
        return String.format(Locale.ENGLISH, "%s", new Object[]{Integer.valueOf(Build.VERSION.SDK_INT)});
    }

    public static String getBundleID(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e) {
            TLog.error("Could not get package name!", e);
            return "";
        }
    }

    public static String getBundleVersion(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                TLog.error("Something went wrong whilst getting the bundleVersion!", e);
            } catch (Exception e2) {
                TLog.error(e2);
            }
        }
        return "";
    }

    public static boolean getIsSlim(Context context) {
        double width = (double) getWidth(context);
        double height = (double) getHeight(context);
        if (width <= 0.0d) {
            return false;
        }
        Double.isNaN(height);
        Double.isNaN(width);
        if (height / width > FAT_VS_SLIM_RATIO_THRESHOLD.doubleValue()) {
            return true;
        }
        return false;
    }
}
