package com.tapjoy.internal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class gl extends gk {
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public gl(File file, hc hcVar) {
        super(file, hcVar);
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        try {
            this.b.shutdown();
            this.b.awaitTermination(1, TimeUnit.SECONDS);
        } finally {
            super.finalize();
        }
    }

    class a implements Runnable {
        private int b;
        private long c;
        private String d;
        private String e;
        private Map f;

        a(int i, long j, String str, String str2, Map map) {
            this.b = i;
            this.c = j;
            this.d = str;
            this.e = str2;
            this.f = map;
        }

        public final void run() {
            try {
                switch (this.b) {
                    case 1:
                        gl.super.a(this.c);
                        return;
                    case 2:
                        gl.super.a();
                        return;
                    case 3:
                        gl.super.a(this.c, this.d, this.e, this.f);
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused) {
                gl.super.a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void a(long j) {
        try {
            this.b.execute(new a(1, j, (String) null, (String) null, (Map) null));
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.b.execute(new a(2, 0, (String) null, (String) null, (Map) null));
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final void a(long j, String str, String str2, Map map) {
        try {
            this.b.execute(new a(3, j, str, str2, map != null ? new HashMap(map) : null));
        } catch (Throwable unused) {
        }
    }
}
