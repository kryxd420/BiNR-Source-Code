package com.applovin.impl.adview;

import android.os.Handler;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class g {
    /* access modifiers changed from: private */
    public final p a;
    private final Handler b;
    private final Set<b> c = new HashSet();
    /* access modifiers changed from: private */
    public final AtomicInteger d = new AtomicInteger();

    interface a {
        void a();

        boolean b();
    }

    private static class b {
        private final String a;
        private final a b;
        private final long c;

        private b(String str, long j, a aVar) {
            this.a = str;
            this.c = j;
            this.b = aVar;
        }

        /* access modifiers changed from: private */
        public String a() {
            return this.a;
        }

        /* access modifiers changed from: private */
        public long b() {
            return this.c;
        }

        /* access modifiers changed from: private */
        public a c() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a != null ? this.a.equalsIgnoreCase(bVar.a) : bVar.a == null;
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "CountdownProxy{identifier='" + this.a + '\'' + ", countdownStepMillis=" + this.c + '}';
        }
    }

    public g(Handler handler, j jVar) {
        if (handler == null) {
            throw new IllegalArgumentException("No handler specified.");
        } else if (jVar != null) {
            this.b = handler;
            this.a = jVar.v();
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    /* access modifiers changed from: private */
    public void a(final b bVar, final int i) {
        this.b.postDelayed(new Runnable() {
            public void run() {
                a b2 = bVar.c();
                if (!b2.b()) {
                    p b3 = g.this.a;
                    b3.a("CountdownManager", "Ending countdown for " + bVar.a());
                } else if (g.this.d.get() == i) {
                    try {
                        b2.a();
                    } catch (Throwable th) {
                        p b4 = g.this.a;
                        b4.b("CountdownManager", "Encountered error on countdown step for: " + bVar.a(), th);
                    }
                    g.this.a(bVar, i);
                } else {
                    p b5 = g.this.a;
                    b5.c("CountdownManager", "Killing duplicate countdown from previous generation: " + bVar.a());
                }
            }
        }, bVar.b());
    }

    public void a() {
        HashSet<b> hashSet = new HashSet<>(this.c);
        p pVar = this.a;
        pVar.a("CountdownManager", "Starting " + hashSet.size() + " countdowns...");
        int incrementAndGet = this.d.incrementAndGet();
        for (b bVar : hashSet) {
            p pVar2 = this.a;
            pVar2.a("CountdownManager", "Starting countdown: " + bVar.a() + " for generation " + incrementAndGet + "...");
            a(bVar, incrementAndGet);
        }
    }

    public void a(String str, long j, a aVar) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid step specified.");
        } else if (this.b != null) {
            p pVar = this.a;
            pVar.a("CountdownManager", "Adding countdown: " + str);
            this.c.add(new b(str, j, aVar));
        } else {
            throw new IllegalArgumentException("No handler specified.");
        }
    }

    public void b() {
        this.a.a("CountdownManager", "Removing all countdowns...");
        c();
        this.c.clear();
    }

    public void c() {
        this.a.a("CountdownManager", "Stopping countdowns...");
        this.d.incrementAndGet();
        this.b.removeCallbacksAndMessages((Object) null);
    }
}
