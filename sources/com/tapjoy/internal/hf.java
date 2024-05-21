package com.tapjoy.internal;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapjoy.TJAdUnitConstants;
import com.vungle.warren.ui.VungleActivity;

public final class hf extends p {
    private static hf c;

    public static synchronized hf b(Context context) {
        hf hfVar;
        synchronized (hf.class) {
            if (c == null) {
                c = new hf(context);
            }
            hfVar = c;
        }
        return hfVar;
    }

    private hf(Context context) {
        super(context, new r() {
            public final String a(Context context) {
                return hn.a(context).b.getString("gcm.senderIds", "");
            }

            public final void a(Context context, String str) {
                n.a(hn.a(context).b, "gcm.senderIds", str);
            }

            public final String b(Context context) {
                return hn.a(context).b.getString("gcm.regId", "");
            }

            public final void b(Context context, String str) {
                n.a(hn.a(context).b, "gcm.regId", str);
            }

            public final boolean c(Context context) {
                return hn.a(context).b.getBoolean("gcm.stale", true);
            }

            public final void a(Context context, boolean z) {
                n.a(hn.a(context).b, "gcm.stale", z);
            }

            public final int d(Context context) {
                return hn.a(context).b.getInt("gcm.appVersion", Integer.MIN_VALUE);
            }

            public final void a(Context context, int i) {
                n.a(hn.a(context).b, "gcm.appVersion", i);
            }

            public final boolean e(Context context) {
                return hn.a(context).b.getBoolean("gcm.onServer", false);
            }

            public final void b(Context context, boolean z) {
                hn.a(context).a(z);
            }

            public final long f(Context context) {
                return hn.a(context).b.getLong("gcm.onServerExpirationTime", 0);
            }

            public final void a(Context context, long j) {
                SharedPreferences.Editor edit = hn.a(context).b.edit();
                edit.putLong("gcm.onServerExpirationTime", j);
                edit.apply();
            }

            public final int g(Context context) {
                return hn.a(context).b.getInt("gcm.backoff", 0);
            }

            public final void b(Context context, int i) {
                n.a(hn.a(context).b, "gcm.backoff", i);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public final void e(String str) {
        if (str != null && str.length() > 0) {
            super.a(this.a);
            super.a(new String[]{str}[0]);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, String str) {
        new Object[1][0] = str;
        hd.a(context).a(str);
    }

    /* access modifiers changed from: protected */
    public final void b(String str) {
        new Object[1][0] = str;
        a();
    }

    /* access modifiers changed from: protected */
    public final boolean a(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        Object[] objArr = {intent2, intent.getExtras()};
        String stringExtra = intent2.getStringExtra("fiverocks");
        if (stringExtra == null) {
            return false;
        }
        if (hg.a(context).f()) {
            hd.a(context).b(stringExtra);
            return true;
        }
        String stringExtra2 = intent2.getStringExtra(TJAdUnitConstants.String.TITLE);
        String stringExtra3 = intent2.getStringExtra("message");
        if (stringExtra3 != null) {
            Bundle extras = intent.getExtras();
            Object obj = extras.get("rich");
            Object obj2 = extras.get("sound");
            String string = extras.getString("payload");
            Object obj3 = extras.get("always");
            boolean z = "true".equals(obj3) || Boolean.TRUE.equals(obj3);
            Object obj4 = extras.get("repeatable");
            boolean z2 = "true".equals(obj4) || Boolean.TRUE.equals(obj4);
            String string2 = extras.getString(VungleActivity.PLACEMENT_EXTRA);
            int b = b(extras.get("nid"));
            String string3 = extras.getString("channel_id");
            if (z || !hd.a(context).d()) {
                Notification a = a(context, stringExtra, ju.a(stringExtra2), stringExtra3, a(obj), a(obj2), string, string2, b, string3);
                if (hd.a(context).a(context2, stringExtra, z2)) {
                    a(context2, b, a);
                }
            }
        }
        return true;
    }

    public static boolean a(Object obj) {
        return Boolean.TRUE.equals(obj) || "true".equals(obj);
    }

    public static int b(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        new Object[1][0] = Integer.valueOf(i);
    }

    /* access modifiers changed from: protected */
    public final boolean c(String str) {
        new Object[1][0] = str;
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean d(String str) {
        new Object[1][0] = str;
        return false;
    }

    public static void a(Context context, int i, Notification notification) {
        ((NotificationManager) context.getSystemService("notification")).notify(i, notification);
    }

    private static int a(Bundle bundle, String str, Context context) {
        if (bundle != null) {
            Object obj = bundle.get(str);
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                try {
                    if ("drawable".equals(context.getResources().getResourceTypeName(intValue))) {
                        return intValue;
                    }
                } catch (Resources.NotFoundException unused) {
                }
            }
            if (obj != null) {
                Object[] objArr = {str};
                if (ha.a) {
                    aa.a(4, TMMediationNetworks.TAPJOY_NAME, "meta-data of {} invalid", objArr);
                }
            }
        }
        return 0;
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [android.text.Spanned] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r7 == false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        r5 = android.text.Html.fromHtml(r5);
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.app.Notification a(android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, boolean r7, boolean r8, java.lang.String r9, java.lang.String r10, int r11, java.lang.String r12) {
        /*
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = r3.getApplicationContext()
            java.lang.Class<com.tapjoy.TapjoyReceiver> r2 = com.tapjoy.TapjoyReceiver.class
            r0.<init>(r1, r2)
            java.lang.String r1 = "com.tapjoy.PUSH_CLICK"
            r0.setAction(r1)
            java.lang.String r1 = "com.tapjoy.PUSH_ID"
            r0.putExtra(r1, r4)
            if (r9 == 0) goto L_0x001c
            java.lang.String r4 = "com.tapjoy.PUSH_PAYLOAD"
            r0.putExtra(r4, r9)
        L_0x001c:
            if (r10 == 0) goto L_0x0023
            java.lang.String r4 = "com.tapjoy.PUSH_PLACEMENT"
            r0.putExtra(r4, r10)
        L_0x0023:
            r4 = 134217728(0x8000000, float:3.85186E-34)
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 19
            if (r9 != r10) goto L_0x002d
            r4 = 268435456(0x10000000, float:2.5243549E-29)
        L_0x002d:
            android.content.Context r9 = r3.getApplicationContext()
            android.app.PendingIntent r4 = android.app.PendingIntent.getBroadcast(r9, r11, r0, r4)
            r9 = 0
            if (r4 != 0) goto L_0x0039
            return r9
        L_0x0039:
            android.content.pm.PackageManager r10 = r3.getPackageManager()
            java.lang.String r11 = r3.getPackageName()
            r0 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r11 = r10.getApplicationInfo(r11, r0)     // Catch:{ NameNotFoundException -> 0x00ff }
            int r0 = r5.length()
            if (r0 != 0) goto L_0x0052
            java.lang.CharSequence r5 = r10.getApplicationLabel(r11)
            goto L_0x0058
        L_0x0052:
            if (r7 == 0) goto L_0x0058
            android.text.Spanned r5 = android.text.Html.fromHtml(r5)
        L_0x0058:
            if (r7 == 0) goto L_0x005e
            android.text.Spanned r6 = android.text.Html.fromHtml(r6)
        L_0x005e:
            android.os.Bundle r7 = r11.metaData
            java.lang.String r10 = "com.tapjoy.notification.icon"
            int r7 = a((android.os.Bundle) r7, (java.lang.String) r10, (android.content.Context) r3)
            if (r7 != 0) goto L_0x0072
            int r7 = r11.icon
            if (r7 == 0) goto L_0x006f
            int r7 = r11.icon
            goto L_0x0072
        L_0x006f:
            r7 = 17301651(0x1080093, float:2.4979667E-38)
        L_0x0072:
            android.os.Bundle r10 = r11.metaData
            java.lang.String r0 = "com.tapjoy.notification.icon.large"
            int r10 = a((android.os.Bundle) r10, (java.lang.String) r0, (android.content.Context) r3)
            if (r10 == 0) goto L_0x0085
            android.content.res.Resources r0 = r3.getResources()
            android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeResource(r0, r10)
            goto L_0x0086
        L_0x0085:
            r10 = r9
        L_0x0086:
            if (r12 != 0) goto L_0x0096
            android.os.Bundle r0 = r11.metaData
            if (r0 == 0) goto L_0x0096
            android.os.Bundle r11 = r11.metaData
            java.lang.String r12 = "com.tapjoy.notification.default_channel_id"
            java.lang.String r0 = "tapjoy"
            java.lang.String r12 = r11.getString(r12, r0)
        L_0x0096:
            int r11 = android.os.Build.VERSION.SDK_INT
            r0 = 26
            if (r11 < r0) goto L_0x00be
            java.lang.String r9 = "notification"
            java.lang.Object r9 = r3.getSystemService(r9)
            android.app.NotificationManager r9 = (android.app.NotificationManager) r9
            if (r12 == 0) goto L_0x00af
            android.app.NotificationChannel r11 = r9.getNotificationChannel(r12)
            if (r11 != 0) goto L_0x00ad
            goto L_0x00af
        L_0x00ad:
            r9 = r12
            goto L_0x00be
        L_0x00af:
            android.app.NotificationChannel r11 = new android.app.NotificationChannel
            java.lang.String r12 = "tapjoy"
            java.lang.String r0 = "Tapjoy"
            r1 = 3
            r11.<init>(r12, r0, r1)
            r9.createNotificationChannel(r11)
            java.lang.String r9 = "tapjoy"
        L_0x00be:
            com.tapjoy.internal.jm$c r11 = new com.tapjoy.internal.jm$c
            r11.<init>(r3, r9)
            com.tapjoy.internal.jm$c r3 = r11.a((int) r7)
            com.tapjoy.internal.jm$c r3 = r3.c(r5)
            com.tapjoy.internal.jm$c r3 = r3.a((java.lang.CharSequence) r5)
            com.tapjoy.internal.jm$c r3 = r3.b(r6)
            com.tapjoy.internal.jm$c r3 = r3.a((android.app.PendingIntent) r4)
            com.tapjoy.internal.jm$c r3 = r3.a()
            com.tapjoy.internal.jm$c r3 = r3.c()
            com.tapjoy.internal.jm$b r4 = new com.tapjoy.internal.jm$b
            r4.<init>()
            com.tapjoy.internal.jm$b r4 = r4.a((java.lang.CharSequence) r5)
            com.tapjoy.internal.jm$b r4 = r4.b(r6)
            com.tapjoy.internal.jm$c r3 = r3.a((com.tapjoy.internal.jm.d) r4)
            if (r8 == 0) goto L_0x00f5
            r3.b()
        L_0x00f5:
            if (r10 == 0) goto L_0x00fa
            r3.a((android.graphics.Bitmap) r10)
        L_0x00fa:
            android.app.Notification r3 = r3.d()
            return r3
        L_0x00ff:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hf.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, java.lang.String, java.lang.String, int, java.lang.String):android.app.Notification");
    }
}
