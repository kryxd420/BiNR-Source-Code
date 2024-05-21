package com.applovin.impl.sdk.d;

import android.content.Context;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;

public abstract class a implements Runnable {
    private final String a;
    /* access modifiers changed from: protected */
    public final j b;
    private final p c;
    private final Context d;
    private final boolean e;

    public a(String str, j jVar) {
        this(str, jVar, false);
    }

    public a(String str, j jVar, boolean z) {
        this.a = str;
        this.b = jVar;
        this.c = jVar.v();
        this.d = jVar.x();
        this.e = z;
    }

    public abstract i a();

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.c.a(this.a, str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, Throwable th) {
        this.c.b(this.a, str, th);
    }

    /* access modifiers changed from: protected */
    public j b() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        this.c.b(this.a, str);
    }

    public String c() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void c(String str) {
        this.c.c(this.a, str);
    }

    /* access modifiers changed from: protected */
    public Context d() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void d(String str) {
        this.c.d(this.a, str);
    }

    public boolean e() {
        return this.e;
    }
}
