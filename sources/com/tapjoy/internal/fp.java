package com.tapjoy.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyUtil;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

public final class fp {
    private static final fp a = new fp();
    /* access modifiers changed from: private */
    public Application b;
    private Application.ActivityLifecycleCallbacks c;
    private final HashSet d = new HashSet();

    public static void a(Context context) {
        if (Build.VERSION.SDK_INT >= 14 && context != null) {
            fp fpVar = a;
            Context applicationContext = context.getApplicationContext();
            if (fpVar.b == null) {
                try {
                    if (applicationContext instanceof Application) {
                        fpVar.b = (Application) applicationContext;
                    } else {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        TapjoyUtil.runOnMainThread(new Runnable() {
                            public final void run() {
                                try {
                                    Application unused = fp.this.b = fp.b();
                                } catch (Exception e) {
                                    TapjoyLog.w("Tapjoy.ActivityTracker", Log.getStackTraceString(e));
                                } catch (Throwable th) {
                                    countDownLatch.countDown();
                                    throw th;
                                }
                                countDownLatch.countDown();
                            }
                        });
                        countDownLatch.await();
                    }
                } catch (Exception e) {
                    TapjoyLog.w("Tapjoy.ActivityTracker", Log.getStackTraceString(e));
                }
                if (fpVar.b == null) {
                    return;
                }
            }
            synchronized (fpVar) {
                if (fpVar.c == null) {
                    Activity c2 = b.c();
                    if (c2 != null) {
                        fpVar.d.add(b(c2));
                    }
                    final HashSet hashSet = fpVar.d;
                    fpVar.c = new Application.ActivityLifecycleCallbacks() {
                        public final void onActivityCreated(Activity activity, Bundle bundle) {
                        }

                        public final void onActivityDestroyed(Activity activity) {
                        }

                        public final void onActivityPaused(Activity activity) {
                        }

                        public final void onActivityResumed(Activity activity) {
                        }

                        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                        }

                        public final void onActivityStarted(Activity activity) {
                            hashSet.add(fp.b(activity));
                            if (hashSet.size() == 1) {
                                gr.a();
                            }
                            b.a(activity);
                        }

                        public final void onActivityStopped(Activity activity) {
                            hashSet.remove(fp.b(activity));
                            if (hashSet.size() <= 0) {
                                gr.b();
                            }
                        }
                    };
                    fpVar.b.registerActivityLifecycleCallbacks(fpVar.c);
                    gr.a();
                }
            }
        }
    }

    public static void a() {
        if (Build.VERSION.SDK_INT >= 14) {
            fp fpVar = a;
            if (fpVar.b != null) {
                synchronized (fpVar) {
                    if (fpVar.c != null) {
                        fpVar.b.unregisterActivityLifecycleCallbacks(fpVar.c);
                        fpVar.c = null;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static String b(Activity activity) {
        return activity.getClass().getName() + "@" + System.identityHashCode(activity);
    }

    static /* synthetic */ Application b() {
        return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke((Object) null, (Object[]) null);
    }
}
