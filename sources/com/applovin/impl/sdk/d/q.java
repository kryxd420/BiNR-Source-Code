package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class q {
    private final String a = "TaskManager";
    /* access modifiers changed from: private */
    public final j b;
    /* access modifiers changed from: private */
    public final p c;
    private final ScheduledThreadPoolExecutor d;
    private final ScheduledThreadPoolExecutor e;
    private final ScheduledThreadPoolExecutor f;
    private final ScheduledThreadPoolExecutor g;
    private final ScheduledThreadPoolExecutor h;
    private final ScheduledThreadPoolExecutor i;
    private final ScheduledThreadPoolExecutor j;
    private final ScheduledThreadPoolExecutor k;
    private final ScheduledThreadPoolExecutor l;
    private final ScheduledThreadPoolExecutor m;
    private final ScheduledThreadPoolExecutor n;
    private final ScheduledThreadPoolExecutor o;
    private final ScheduledThreadPoolExecutor p;
    private final ScheduledThreadPoolExecutor q;
    private final ScheduledThreadPoolExecutor r;
    private final ScheduledThreadPoolExecutor s;
    private final List<c> t = new ArrayList(5);
    private final Object u = new Object();
    private boolean v;

    public enum a {
        MAIN,
        TIMEOUT,
        BACKGROUND,
        POSTBACKS,
        CACHING_INTERSTITIAL,
        CACHING_INCENTIVIZED,
        CACHING_OTHER,
        REWARD,
        MEDIATION_MAIN,
        MEDIATION_TIMEOUT,
        MEDIATION_BACKGROUND,
        MEDIATION_BACKUP,
        MEDIATION_POSTBACKS,
        MEDIATION_BANNER,
        MEDIATION_INTERSTITIAL,
        MEDIATION_INCENTIVIZED
    }

    private class b implements ThreadFactory {
        private final String b;

        public b(String str) {
            this.b = str;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AppLovinSdk:" + this.b + ":" + n.a(q.this.b.t()));
            thread.setDaemon(true);
            thread.setPriority(10);
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    q.this.c.b("TaskManager", "Caught unhandled exception", th);
                }
            });
            return thread;
        }
    }

    private class c implements Runnable {
        private final String b;
        /* access modifiers changed from: private */
        public final a c;
        /* access modifiers changed from: private */
        public final a d;

        c(a aVar, a aVar2) {
            this.b = aVar.c();
            this.c = aVar;
            this.d = aVar2;
        }

        public void run() {
            long a2;
            p b2;
            String str;
            StringBuilder sb;
            long currentTimeMillis = System.currentTimeMillis();
            try {
                e.a();
                if (q.this.b.c()) {
                    if (!this.c.e()) {
                        q.this.c.b(this.b, "Task re-scheduled...");
                        q.this.a(this.c, this.d, (long) FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
                        a2 = q.this.a(this.d) - 1;
                        b2 = q.this.c;
                        str = "TaskManager";
                        sb = new StringBuilder();
                        sb.append(this.d);
                        sb.append(" queue finished task ");
                        sb.append(this.c.c());
                        sb.append(" with queue size ");
                        sb.append(a2);
                        b2.b(str, sb.toString());
                    }
                }
                q.this.c.b(this.b, "Task started execution...");
                this.c.run();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                q.this.b.F().a(this.c.a(), currentTimeMillis2);
                p b3 = q.this.c;
                String str2 = this.b;
                b3.b(str2, "Task executed successfully in " + currentTimeMillis2 + "ms.");
                a2 = q.this.a(this.d) - 1;
                b2 = q.this.c;
                str = "TaskManager";
                sb = new StringBuilder();
            } catch (Throwable th) {
                p b4 = q.this.c;
                b4.b("TaskManager", this.d + " queue finished task " + this.c.c() + " with queue size " + (q.this.a(this.d) - 1));
                throw th;
            }
            sb.append(this.d);
            sb.append(" queue finished task ");
            sb.append(this.c.c());
            sb.append(" with queue size ");
            sb.append(a2);
            b2.b(str, sb.toString());
        }
    }

    public q(j jVar) {
        this.b = jVar;
        this.c = jVar.v();
        this.d = a("main");
        this.e = a("timeout");
        this.f = a("back");
        this.g = a("postbacks");
        this.h = a("caching_interstitial");
        this.i = a("caching_incentivized");
        this.j = a("caching_other");
        this.k = a("reward");
        this.l = a("mediation_main");
        this.m = a("mediation_timeout");
        this.n = a("mediation_background");
        this.o = a("mediation_backup");
        this.p = a("mediation_postbacks");
        this.q = a("mediation_banner");
        this.r = a("mediation_interstitial");
        this.s = a("mediation_incentivized");
    }

    /* access modifiers changed from: private */
    public long a(a aVar) {
        long taskCount;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        if (aVar == a.MAIN) {
            taskCount = this.d.getTaskCount();
            scheduledThreadPoolExecutor = this.d;
        } else if (aVar == a.TIMEOUT) {
            taskCount = this.e.getTaskCount();
            scheduledThreadPoolExecutor = this.e;
        } else if (aVar == a.BACKGROUND) {
            taskCount = this.f.getTaskCount();
            scheduledThreadPoolExecutor = this.f;
        } else if (aVar == a.POSTBACKS) {
            taskCount = this.g.getTaskCount();
            scheduledThreadPoolExecutor = this.g;
        } else if (aVar == a.CACHING_INTERSTITIAL) {
            taskCount = this.h.getTaskCount();
            scheduledThreadPoolExecutor = this.h;
        } else if (aVar == a.CACHING_INCENTIVIZED) {
            taskCount = this.i.getTaskCount();
            scheduledThreadPoolExecutor = this.i;
        } else if (aVar == a.CACHING_OTHER) {
            taskCount = this.j.getTaskCount();
            scheduledThreadPoolExecutor = this.j;
        } else if (aVar == a.REWARD) {
            taskCount = this.k.getTaskCount();
            scheduledThreadPoolExecutor = this.k;
        } else if (aVar == a.MEDIATION_MAIN) {
            taskCount = this.l.getTaskCount();
            scheduledThreadPoolExecutor = this.l;
        } else if (aVar == a.MEDIATION_TIMEOUT) {
            taskCount = this.m.getTaskCount();
            scheduledThreadPoolExecutor = this.m;
        } else if (aVar == a.MEDIATION_BACKGROUND) {
            taskCount = this.n.getTaskCount();
            scheduledThreadPoolExecutor = this.n;
        } else if (aVar == a.MEDIATION_BACKUP) {
            taskCount = this.o.getTaskCount();
            scheduledThreadPoolExecutor = this.o;
        } else if (aVar == a.MEDIATION_POSTBACKS) {
            taskCount = this.p.getTaskCount();
            scheduledThreadPoolExecutor = this.p;
        } else if (aVar == a.MEDIATION_BANNER) {
            taskCount = this.q.getTaskCount();
            scheduledThreadPoolExecutor = this.q;
        } else if (aVar == a.MEDIATION_INTERSTITIAL) {
            taskCount = this.r.getTaskCount();
            scheduledThreadPoolExecutor = this.r;
        } else if (aVar != a.MEDIATION_INCENTIVIZED) {
            return 0;
        } else {
            taskCount = this.s.getTaskCount();
            scheduledThreadPoolExecutor = this.s;
        }
        return taskCount - scheduledThreadPoolExecutor.getCompletedTaskCount();
    }

    private ScheduledThreadPoolExecutor a(String str) {
        return new ScheduledThreadPoolExecutor(1, new b(str));
    }

    private static void a(Runnable runnable, long j2, ScheduledExecutorService scheduledExecutorService) {
        if (j2 > 0) {
            scheduledExecutorService.schedule(runnable, j2, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    private boolean a(c cVar) {
        if (cVar.c.e()) {
            return false;
        }
        synchronized (this.u) {
            if (this.v) {
                return false;
            }
            this.t.add(cVar);
            return true;
        }
    }

    public void a() {
        synchronized (this.u) {
            this.v = false;
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                p pVar = this.c;
                pVar.b("TaskManager", "Executing " + aVar.c() + " immediately...");
                aVar.run();
                this.b.F().a(aVar.a(), System.currentTimeMillis() - currentTimeMillis);
                p pVar2 = this.c;
                pVar2.b("TaskManager", aVar.c() + " finished executing...");
            } catch (Throwable th) {
                this.c.b(aVar.c(), "Task failed execution", th);
                this.b.F().a(aVar.a(), true, System.currentTimeMillis() - currentTimeMillis);
            }
        } else {
            this.c.d("TaskManager", "Attempted to execute null task immediately");
        }
    }

    public void a(a aVar, a aVar2) {
        a(aVar, aVar2, 0);
    }

    public void a(a aVar, a aVar2, long j2) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        if (aVar == null) {
            throw new IllegalArgumentException("No task specified");
        } else if (j2 >= 0) {
            if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.a.G)).booleanValue()) {
                if (aVar2 == a.MEDIATION_MAIN) {
                    aVar2 = a.MAIN;
                } else if (aVar2 == a.MEDIATION_BACKGROUND) {
                    aVar2 = a.BACKGROUND;
                } else if (aVar2 == a.MEDIATION_POSTBACKS) {
                    aVar2 = a.POSTBACKS;
                }
            }
            c cVar = new c(aVar, aVar2);
            if (!a(cVar)) {
                p pVar = this.c;
                pVar.a("TaskManager", "Scheduling " + aVar.c() + " on " + aVar2 + " queue in " + j2 + "ms with new queue size " + (a(aVar2) + 1));
                if (aVar2 == a.MAIN) {
                    scheduledThreadPoolExecutor = this.d;
                } else if (aVar2 == a.TIMEOUT) {
                    scheduledThreadPoolExecutor = this.e;
                } else if (aVar2 == a.BACKGROUND) {
                    scheduledThreadPoolExecutor = this.f;
                } else if (aVar2 == a.POSTBACKS) {
                    scheduledThreadPoolExecutor = this.g;
                } else if (aVar2 == a.CACHING_INTERSTITIAL) {
                    scheduledThreadPoolExecutor = this.h;
                } else if (aVar2 == a.CACHING_INCENTIVIZED) {
                    scheduledThreadPoolExecutor = this.i;
                } else if (aVar2 == a.CACHING_OTHER) {
                    scheduledThreadPoolExecutor = this.j;
                } else if (aVar2 == a.REWARD) {
                    scheduledThreadPoolExecutor = this.k;
                } else if (aVar2 == a.MEDIATION_MAIN) {
                    scheduledThreadPoolExecutor = this.l;
                } else if (aVar2 == a.MEDIATION_TIMEOUT) {
                    scheduledThreadPoolExecutor = this.m;
                } else if (aVar2 == a.MEDIATION_BACKGROUND) {
                    scheduledThreadPoolExecutor = this.n;
                } else if (aVar2 == a.MEDIATION_BACKUP) {
                    scheduledThreadPoolExecutor = this.o;
                } else if (aVar2 == a.MEDIATION_POSTBACKS) {
                    scheduledThreadPoolExecutor = this.p;
                } else if (aVar2 == a.MEDIATION_BANNER) {
                    scheduledThreadPoolExecutor = this.q;
                } else if (aVar2 == a.MEDIATION_INTERSTITIAL) {
                    scheduledThreadPoolExecutor = this.r;
                } else if (aVar2 == a.MEDIATION_INCENTIVIZED) {
                    scheduledThreadPoolExecutor = this.s;
                } else {
                    return;
                }
                a((Runnable) cVar, j2, (ScheduledExecutorService) scheduledThreadPoolExecutor);
                return;
            }
            p pVar2 = this.c;
            String c2 = aVar.c();
            pVar2.b(c2, "Task " + aVar.c() + " execution delayed until after init");
        } else {
            throw new IllegalArgumentException("Invalid delay specified: " + j2);
        }
    }

    public void b() {
        synchronized (this.u) {
            this.v = true;
            for (c next : this.t) {
                a(next.c, next.d);
            }
            this.t.clear();
        }
    }
}
