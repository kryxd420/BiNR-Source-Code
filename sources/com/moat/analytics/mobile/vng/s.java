package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.support.annotation.FloatRange;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

class s {
    /* access modifiers changed from: private */
    public static String a;

    static class a {
        private boolean a = false;
        private String b = "_unknown_";
        private String c = "_unknown_";

        a() {
        }

        private void c() {
            try {
                Context c2 = s.c();
                if (c2 != null) {
                    PackageManager packageManager = c2.getPackageManager();
                    this.c = c2.getPackageName();
                    this.b = packageManager.getApplicationLabel(c2.getApplicationInfo()).toString();
                    this.a = true;
                    return;
                }
                p.a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                m.a(e);
            }
        }

        /* access modifiers changed from: package-private */
        public String a() {
            if (this.a) {
                return this.b;
            }
            c();
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public String b() {
            if (this.a) {
                return this.c;
            }
            c();
            return this.c;
        }
    }

    s() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    static double a() {
        try {
            double e = (double) e();
            double streamMaxVolume = (double) ((AudioManager) a.a().getSystemService("audio")).getStreamMaxVolume(3);
            Double.isNaN(e);
            Double.isNaN(streamMaxVolume);
            return e / streamMaxVolume;
        } catch (Exception e2) {
            m.a(e2);
            return 0.0d;
        }
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    String str;
                    String str2;
                    try {
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                        if (!advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            String unused = s.a = advertisingIdInfo.getId();
                            str = "Util";
                            str2 = "Retrieved Advertising ID = " + s.a;
                        } else {
                            str = "Util";
                            str2 = "User has limited ad tracking";
                        }
                        p.a(3, str, (Object) this, str2);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            });
        } catch (Exception e) {
            m.a(e);
        }
    }

    static String b() {
        return a;
    }

    static Context c() {
        return (Context) ((k) MoatAnalytics.getInstance()).e.get();
    }

    private static int e() {
        try {
            return ((AudioManager) a.a().getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            m.a(e);
            return 0;
        }
    }
}
