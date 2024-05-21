package com.tapjoy.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.sql.Timestamp;

public abstract class p extends q {
    public final Context a;
    public final r b;
    private boolean c = false;

    public p(Context context, r rVar) {
        this.a = context.getApplicationContext();
        this.b = rVar;
    }

    private boolean b() {
        boolean e = this.b.e(this.a);
        if (e) {
            long f = this.b.f(this.a);
            if (f != 0 && System.currentTimeMillis() > f) {
                new Object[1][0] = new Timestamp(f);
                return false;
            }
        }
        return e;
    }

    public final boolean a() {
        String a2 = this.b.a(this.a);
        if (ju.c(a2)) {
            return false;
        }
        a(a2);
        return true;
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        String b2 = this.b.b(this.a);
        if (b2.length() == 0) {
            e(str);
            return;
        }
        String a2 = this.b.a(this.a);
        if (!str.equals(a2)) {
            Object[] objArr = {a2, str, b2};
            e(str);
            return;
        }
        int d = this.b.d(this.a);
        int a3 = ac.a(this.a);
        if (d != Integer.MIN_VALUE && d != a3) {
            Object[] objArr2 = {Integer.valueOf(d), Integer.valueOf(a3), b2};
            e(str);
        } else if (this.b.c(this.a)) {
            new Object[1][0] = b2;
            e(str);
        } else if (!b()) {
            Object[] objArr3 = {str, b2};
            a(this.a, b2);
        } else {
            Object[] objArr4 = {str, b2};
        }
    }

    private void e(String str) {
        this.b.a(this.a, str);
        this.b.a(this.a, true);
        if ((!this.c && b(this.a, str)) || !c(this.a, str)) {
        }
    }

    private static boolean b(Context context, String str) {
        try {
            int b2 = ac.b(context.getPackageManager(), "com.google.android.gms");
            if (b2 >= 3159130) {
                Object[] objArr = {Integer.valueOf(b2), str};
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                b(context, intent);
                intent.putExtra("sender", str);
                return context.startService(intent) != null;
            }
            new Object[1][0] = Integer.valueOf(b2);
            return false;
        } catch (RuntimeException unused) {
        }
    }

    private static boolean c(Context context, String str) {
        try {
            Object[] objArr = {Integer.valueOf(ac.b(context.getPackageManager(), "com.google.android.gsf")), str};
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gsf");
            b(context, intent);
            intent.putExtra("sender", str);
            if (context.startService(intent) != null) {
                return true;
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    private static void b(Context context, Intent intent) {
        intent.putExtra(TapjoyConstants.TJC_APP_PLACEMENT, PendingIntent.getBroadcast(context, 0, new Intent(), 0));
    }

    /* access modifiers changed from: protected */
    public final void a(Context context) {
        this.b.b(context, (int) PathInterpolatorCompat.MAX_NUM_POINTS);
    }

    public final boolean a(Intent intent) {
        boolean z;
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            String stringExtra = intent.getStringExtra("registration_id");
            String stringExtra2 = intent.getStringExtra("unregistered");
            String stringExtra3 = intent.getStringExtra(TJAdUnitConstants.String.VIDEO_ERROR);
            if (stringExtra != null) {
                if (stringExtra.length() > 0) {
                    if (stringExtra.startsWith("|")) {
                        return false;
                    }
                    f(stringExtra);
                    return false;
                } else if (stringExtra2 == null && stringExtra3 == null && !this.c) {
                    this.c = true;
                    a();
                    return true;
                }
            }
            if (stringExtra2 != null) {
                String b2 = this.b.b(this.a);
                a(this.a);
                this.b.b(this.a, false);
                this.b.b(this.a, "");
                this.b.a(this.a, true);
                b(b2);
                return false;
            } else if (stringExtra3 == null) {
                return false;
            } else {
                if ("SERVICE_NOT_AVAILABLE".equals(stringExtra3)) {
                    z = c(stringExtra3);
                } else if (!this.c) {
                    c(stringExtra3);
                    this.c = true;
                    a();
                    return true;
                } else {
                    z = d(stringExtra3);
                }
                if (z) {
                    int g = this.b.g(this.a);
                    new Object[1][0] = Integer.valueOf(g);
                    Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
                    intent2.setPackage(this.a.getPackageName());
                    ((AlarmManager) this.a.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, SystemClock.elapsedRealtime() + ((long) g), PendingIntent.getBroadcast(this.a, 0, intent2, 0));
                    if (g < 3600000) {
                        this.b.b(this.a, g * 2);
                    }
                }
                return true;
            }
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            String stringExtra4 = intent.getStringExtra("message_type");
            if (stringExtra4 == null) {
                return a(this.a, intent);
            }
            if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(stringExtra4)) {
                String stringExtra5 = intent.getStringExtra("total_deleted");
                if (stringExtra5 != null) {
                    try {
                        int parseInt = Integer.parseInt(stringExtra5);
                        new Object[1][0] = Integer.valueOf(parseInt);
                        a(parseInt);
                    } catch (NumberFormatException unused) {
                        new Object[1][0] = stringExtra5;
                    }
                }
            } else {
                new Object[1][0] = stringExtra4;
            }
            return false;
        } else if ("com.google.android.gcm.intent.RETRY".equals(action)) {
            String str = intent.getPackage();
            if (str == null || !str.equals(this.a.getPackageName())) {
                new Object[1][0] = str;
                return false;
            }
            a();
            return true;
        } else {
            new Object[1][0] = action;
            return false;
        }
    }

    private synchronized void f(String str) {
        a(this.a);
        this.b.a(this.a, false);
        this.b.a(this.a, ac.a(this.a));
        if (!str.equals(this.b.b(this.a))) {
            new Object[1][0] = str;
            this.b.b(this.a, false);
            this.b.b(this.a, str);
            a(this.a, str);
        } else if (!b()) {
            new Object[1][0] = str;
            a(this.a, str);
        } else {
            new Object[1][0] = str;
        }
    }
}
