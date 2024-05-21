package com.applovin.impl.sdk.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.WindowManager;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.tonyodev.fetch.FetchService;

public class e {
    public static Point a(Context context) {
        Point point = new Point();
        point.x = FetchService.QUERY_SINGLE;
        point.y = 320;
        try {
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        } catch (Throwable unused) {
        }
        return point;
    }

    public static void a() {
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        } catch (Throwable unused) {
        }
    }

    public static boolean a(Class<?> cls, Context context) {
        return i.a(new Intent(context, cls), 0, context.getPackageManager()) != null;
    }

    public static boolean a(String str, Context context) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean b(Context context) {
        try {
            return n.a((long) context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, (long) PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean c(Context context) {
        try {
            return n.a((long) context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 128);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean f() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean g() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean h() {
        return Build.VERSION.SDK_INT >= 24;
    }
}
