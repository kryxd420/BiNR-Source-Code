package com.adcolony.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinEventTypes;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

class ac {
    static boolean a = false;
    static final int b = 4000;
    static final int c = 4;
    static final int d = 3;
    static final int e = 2;
    static final int f = 1;
    static final int g = 0;
    static final int h = -1;
    static int i = 3;
    static JSONObject j = y.a();
    static int k = 1;
    static al l;
    private static ExecutorService m = null;
    private static final Queue<Runnable> n = new ConcurrentLinkedQueue();

    ac() {
    }

    static void a(int i2, String str, boolean z) {
        a(0, i2, str, z);
    }

    static void a(int i2, int i3, String str, boolean z) {
        if (!a(b(i2, i3, str, z))) {
            synchronized (n) {
                n.add(b(i2, i3, str, z));
            }
        }
    }

    private static Runnable b(final int i2, final int i3, final String str, final boolean z) {
        return new Runnable() {
            public void run() {
                ac.a(i2, str, i3);
                int i = 0;
                while (i <= str.length() / ac.b) {
                    int i2 = i * ac.b;
                    i++;
                    int i3 = i * ac.b;
                    if (i3 > str.length()) {
                        i3 = str.length();
                    }
                    if (i3 == 3 && ac.a(y.f(ac.j, Integer.toString(i2)), 3, z)) {
                        Log.d("AdColony [TRACE]", str.substring(i2, i3));
                    } else if (i3 == 2 && ac.a(y.f(ac.j, Integer.toString(i2)), 2, z)) {
                        Log.i("AdColony [INFO]", str.substring(i2, i3));
                    } else if (i3 == 1 && ac.a(y.f(ac.j, Integer.toString(i2)), 1, z)) {
                        Log.w("AdColony [WARNING]", str.substring(i2, i3));
                    } else if (i3 == 0 && ac.a(y.f(ac.j, Integer.toString(i2)), 0, z)) {
                        Log.e("AdColony [ERROR]", str.substring(i2, i3));
                    } else if (i3 == -1 && ac.i >= -1) {
                        Log.e("AdColony [FATAL]", str.substring(i2, i3));
                    }
                }
            }
        };
    }

    static boolean a(JSONObject jSONObject, int i2, boolean z) {
        int c2 = y.c(jSONObject, "print_level");
        boolean d2 = y.d(jSONObject, "log_private");
        if (jSONObject.length() == 0) {
            c2 = i;
            d2 = a;
        }
        return (!z || d2) && c2 != 4 && c2 >= i2;
    }

    static void a() {
        if (m == null || m.isShutdown() || m.isTerminated()) {
            m = Executors.newSingleThreadExecutor();
        }
        synchronized (n) {
            while (!n.isEmpty()) {
                a(n.poll());
            }
        }
    }

    static void b() {
        if (m != null) {
            m.shutdown();
            try {
                if (!m.awaitTermination(1, TimeUnit.SECONDS)) {
                    m.shutdownNow();
                    if (!m.awaitTermination(1, TimeUnit.SECONDS)) {
                        System.err.println("ADCLogManager: ScheduledExecutorService did not terminate");
                    }
                }
            } catch (InterruptedException unused) {
                m.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    static boolean a(JSONObject jSONObject, int i2) {
        int c2 = y.c(jSONObject, "send_level");
        if (jSONObject.length() == 0) {
            c2 = k;
        }
        return c2 >= i2 && c2 != 4;
    }

    static void a(HashMap<String, Object> hashMap) {
        try {
            l = new al(new z(new URL("https://wd.adcolony.com/logs")), Executors.newSingleThreadScheduledExecutor(), hashMap);
            l.a(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
    }

    static void a(int i2, String str, int i3) {
        if (l != null) {
            if (i3 == 3 && a(y.f(j, Integer.toString(i2)), 3)) {
                l.c(str);
            } else if (i3 == 2 && a(y.f(j, Integer.toString(i2)), 2)) {
                l.d(str);
            } else if (i3 == 1 && a(y.f(j, Integer.toString(i2)), 1)) {
                l.e(str);
            } else if (i3 == 0 && a(y.f(j, Integer.toString(i2)), 0)) {
                l.f(str);
            }
        }
    }

    static void a(s sVar) {
        if (l != null && k != 4) {
            l.a(sVar);
        }
    }

    static void c() {
        a.a("Log.set_log_level", (ah) new ah() {
            public void a(af afVar) {
                ac.i = y.c(afVar.c(), AppLovinEventTypes.USER_COMPLETED_LEVEL);
            }
        });
        a.a("Log.public.trace", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 3, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.trace", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 3, y.b(afVar.c(), "message"), true);
            }
        });
        a.a("Log.public.info", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 2, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.info", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 2, y.b(afVar.c(), "message"), true);
            }
        });
        a.a("Log.public.warning", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 1, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.warning", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 1, y.b(afVar.c(), "message"), true);
            }
        });
        a.a("Log.public.error", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 0, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.error", (ah) new ah() {
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 0, y.b(afVar.c(), "message"), true);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(JSONArray jSONArray) {
        j = b(jSONArray);
    }

    /* access modifiers changed from: package-private */
    public JSONObject b(JSONArray jSONArray) {
        JSONObject a2 = y.a();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject d2 = y.d(jSONArray, i2);
            y.a(a2, Integer.toString(y.c(d2, "id")), d2);
        }
        return a2;
    }

    private static boolean a(Runnable runnable) {
        try {
            if (m == null || m.isShutdown() || m.isTerminated()) {
                return false;
            }
            m.submit(runnable);
            return true;
        } catch (RejectedExecutionException unused) {
            Log.e("ADCLogError", "Internal error when submitting log to executor service.");
            return false;
        }
    }
}
