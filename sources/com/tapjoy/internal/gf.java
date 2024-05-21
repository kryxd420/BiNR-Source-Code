package com.tapjoy.internal;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.TJConnectListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class gf {
    private final ReentrantLock a = new ReentrantLock();
    volatile int b = c.a;
    b c;
    long d = 1000;
    a e;
    private final Condition f = this.a.newCondition();
    private final LinkedList g = new LinkedList();
    private a h;

    public abstract boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener);

    enum c {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;

        static {
            f = new int[]{a, b, c, d, e};
        }

        public static int[] a() {
            return (int[]) f.clone();
        }
    }

    public final boolean b(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        this.a.lock();
        if (tJConnectListener != null) {
            try {
                this.g.addLast(fr.a(tJConnectListener, TJConnectListener.class));
            } catch (Throwable th) {
                this.a.unlock();
                throw th;
            }
        }
        a aVar = new a(context, str, hashtable);
        switch (AnonymousClass3.a[this.b - 1]) {
            case 1:
                a(true);
                this.a.unlock();
                return true;
            case 2:
                this.e = aVar;
                fw.b.addObserver(new Observer() {
                    public final void update(Observable observable, Object obj) {
                        fw.b.deleteObserver(this);
                        if (!Boolean.valueOf(Boolean.TRUE.equals(obj)).booleanValue() && gf.this.e != null && gf.this.e.a != null) {
                            gf.this.c = new b(gf.this, (byte) 0);
                            gf.this.c.e();
                        }
                    }
                });
                if (a(aVar.a, aVar.b, aVar.c, new TJConnectListener() {
                    public final void onConnectSuccess() {
                        gf gfVar = gf.this;
                        int i = c.e;
                        int i2 = c.b;
                        gfVar.a(i);
                        gf.this.a(true);
                    }

                    public final void onConnectFailure() {
                        gf.this.a(false);
                    }
                })) {
                    int i = c.b;
                    int i2 = c.a;
                    a(i);
                    this.a.unlock();
                    return true;
                }
                this.g.clear();
                this.a.unlock();
                return false;
            case 3:
            case 4:
                this.h = aVar;
                this.a.unlock();
                return true;
            case 5:
                this.h = aVar;
                b();
                this.a.unlock();
                return true;
            default:
                a(c.a);
                this.a.unlock();
                return false;
        }
        this.a.unlock();
        throw th;
    }

    /* renamed from: com.tapjoy.internal.gf$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[c.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0029 */
        static {
            /*
                int[] r0 = com.tapjoy.internal.gf.c.a()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                r0 = 1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.tapjoy.internal.gf.c.e     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.tapjoy.internal.gf.c.a     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.tapjoy.internal.gf.c.b     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r3 = 3
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0029 }
                int r2 = com.tapjoy.internal.gf.c.c     // Catch:{ NoSuchFieldError -> 0x0029 }
                int r2 = r2 - r0
                r3 = 4
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0029 }
            L_0x0029:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0031 }
                int r2 = com.tapjoy.internal.gf.c.d     // Catch:{ NoSuchFieldError -> 0x0031 }
                int r2 = r2 - r0
                r0 = 5
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0031 }
            L_0x0031:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.gf.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.a.lock();
        try {
            int i2 = this.b;
            this.b = i;
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final a a() {
        this.a.lock();
        try {
            if (this.h != null) {
                this.e = this.h;
                this.h = null;
            }
            return this.e;
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.a.lock();
        try {
            if (this.g.size() != 0) {
                ArrayList arrayList = new ArrayList(this.g);
                this.g.clear();
                this.a.unlock();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    TJConnectListener tJConnectListener = (TJConnectListener) it.next();
                    if (z) {
                        tJConnectListener.onConnectSuccess();
                    } else {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        this.a.lock();
        try {
            this.d = 1000;
            this.f.signal();
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(long j) {
        this.a.lock();
        try {
            int i = c.d;
            int i2 = c.c;
            a(i);
            if (this.f.await(j, TimeUnit.MILLISECONDS)) {
                this.d = 1000;
            }
            return false;
        } catch (InterruptedException unused) {
            return false;
        } finally {
            int i3 = c.c;
            int i4 = c.d;
            a(i3);
            this.a.unlock();
        }
    }

    class a {
        public final Context a;
        public final String b;
        public final Hashtable c;

        public a(Context context, String str, Hashtable hashtable) {
            Context context2;
            if (context == null) {
                context2 = null;
            } else if (context instanceof Application) {
                context2 = context;
            } else {
                context2 = context.getApplicationContext();
            }
            this.a = context2 == null ? context : context2;
            this.b = str;
            this.c = hashtable;
        }
    }

    class b extends ke {
        private boolean b;
        /* access modifiers changed from: private */
        public boolean c;
        private Context d;
        private BroadcastReceiver e;

        private b() {
            this.e = new BroadcastReceiver() {
                public final void onReceive(Context context, Intent intent) {
                    gf.this.b();
                }
            };
        }

        /* synthetic */ b(gf gfVar, byte b2) {
            this();
        }

        /* access modifiers changed from: protected */
        public final void a() {
            this.b = true;
            gf.this.b();
        }

        /* access modifiers changed from: protected */
        public final void b() {
            gf gfVar = gf.this;
            int i = c.c;
            int i2 = c.b;
            gfVar.a(i);
        }

        /* access modifiers changed from: protected */
        public final void c() {
            if (gf.this.c == this) {
                gf.this.c = null;
            }
            if (gf.this.b == c.c) {
                gf gfVar = gf.this;
                int i = c.a;
                int i2 = c.c;
                gfVar.a(i);
            }
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x004e */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0064 A[SYNTHETIC, Splitter:B:17:0x0064] */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0052 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void d() {
            /*
                r7 = this;
                com.tapjoy.internal.gf r0 = com.tapjoy.internal.gf.this
                com.tapjoy.internal.gf$a r0 = r0.a()
                android.content.Context r0 = r0.a
                r7.d = r0
                android.content.IntentFilter r0 = new android.content.IntentFilter
                java.lang.String r1 = "android.net.conn.CONNECTIVITY_CHANGE"
                r0.<init>(r1)
                android.content.Context r1 = r7.d
                android.content.BroadcastReceiver r2 = r7.e
                r1.registerReceiver(r2, r0)
            L_0x0018:
                boolean r0 = r7.b     // Catch:{ all -> 0x008b }
                if (r0 != 0) goto L_0x0087
                java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x008b }
                r1 = 1
                r0.<init>(r1)     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.fw$a r2 = com.tapjoy.internal.fw.b     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf$b$1 r3 = new com.tapjoy.internal.gf$b$1     // Catch:{ all -> 0x008b }
                r3.<init>(r0)     // Catch:{ all -> 0x008b }
                r2.addObserver(r3)     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf r2 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf$a r2 = r2.a()     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf r3 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                android.content.Context r4 = r2.a     // Catch:{ all -> 0x008b }
                java.lang.String r5 = r2.b     // Catch:{ all -> 0x008b }
                java.util.Hashtable r2 = r2.c     // Catch:{ all -> 0x008b }
                r6 = 0
                boolean r2 = r3.a(r4, r5, r2, r6)     // Catch:{ all -> 0x008b }
                r3 = 0
                if (r2 != 0) goto L_0x004b
                com.tapjoy.internal.gf r0 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                r0.a((boolean) r3)     // Catch:{ all -> 0x008b }
                r7.h()
                return
            L_0x004b:
                r0.await()     // Catch:{ InterruptedException -> 0x004e }
            L_0x004e:
                boolean r0 = r7.c     // Catch:{ all -> 0x008b }
                if (r0 == 0) goto L_0x0064
                com.tapjoy.internal.gf r0 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                int r2 = com.tapjoy.internal.gf.c.e     // Catch:{ all -> 0x008b }
                int r3 = com.tapjoy.internal.gf.c.c     // Catch:{ all -> 0x008b }
                r0.a((int) r2)     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf r0 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                r0.a((boolean) r1)     // Catch:{ all -> 0x008b }
                r7.h()
                return
            L_0x0064:
                com.tapjoy.internal.gf r0 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                r0.a((boolean) r3)     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf r0 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                long r0 = r0.d     // Catch:{ all -> 0x008b }
                r2 = 1000(0x3e8, double:4.94E-321)
                long r0 = java.lang.Math.max(r0, r2)     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf r2 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                r3 = 2
                long r3 = r0 << r3
                r5 = 3600000(0x36ee80, double:1.7786363E-317)
                long r3 = java.lang.Math.min(r3, r5)     // Catch:{ all -> 0x008b }
                r2.d = r3     // Catch:{ all -> 0x008b }
                com.tapjoy.internal.gf r2 = com.tapjoy.internal.gf.this     // Catch:{ all -> 0x008b }
                r2.a((long) r0)     // Catch:{ all -> 0x008b }
                goto L_0x0018
            L_0x0087:
                r7.h()
                return
            L_0x008b:
                r0 = move-exception
                r7.h()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.gf.b.d():void");
        }

        private void h() {
            this.d.unregisterReceiver(this.e);
        }
    }
}
