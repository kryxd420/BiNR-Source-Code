package com.tapjoy.internal;

import android.app.Activity;
import android.app.Application;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nullable;

public final class b {
    private static Application a;
    private static int b;
    private static final cb c = new cb();
    private static final Set d = Collections.synchronizedSet(new bb());
    private static final cb e = new cb();

    public static boolean b() {
        return b > 0;
    }

    @Nullable
    public static Activity c() {
        Activity activity = (Activity) c.a();
        if (activity != null) {
            return activity;
        }
        return (Activity) jx.a(d.iterator());
    }

    public static void a(Activity activity) {
        c.a(activity);
    }

    public static synchronized void a(Application application) {
        synchronized (b.class) {
            if (a != application) {
                a = application;
            }
        }
    }

    public static void b(Activity activity) {
        b++;
        c.a(activity);
        d.add(activity);
    }

    public static void c(Activity activity) {
        b--;
        c.a = null;
        d.remove(activity);
        if (b < 0) {
            b = 0;
        }
    }

    @Nullable
    public static Activity a() {
        Activity activity = (Activity) e.a();
        return activity == null ? c() : activity;
    }
}
