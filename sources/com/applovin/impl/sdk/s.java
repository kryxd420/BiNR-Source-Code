package com.applovin.impl.sdk;

import com.applovin.impl.sdk.ad.j;
import java.util.LinkedList;
import java.util.Queue;

class s {
    private int a;
    private final Queue<j> b = new LinkedList();
    private final Object c = new Object();

    s(int i) {
        a(i);
    }

    /* access modifiers changed from: package-private */
    public int a() {
        int size;
        synchronized (this.c) {
            size = this.b.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        if (i > 25) {
            i = 25;
        }
        this.a = i;
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        synchronized (this.c) {
            if (a() <= 25) {
                this.b.offer(jVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        boolean z;
        synchronized (this.c) {
            z = a() >= this.a;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        boolean z;
        synchronized (this.c) {
            z = a() == 0;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public j e() {
        j poll;
        try {
            synchronized (this.c) {
                poll = !d() ? this.b.poll() : null;
            }
            return poll;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public j f() {
        j peek;
        synchronized (this.c) {
            peek = this.b.peek();
        }
        return peek;
    }
}
