package com.unity3d.player;

final class l {
    private static boolean a = false;
    private boolean b = (!h.c);
    private boolean c = false;
    private boolean d = false;
    private boolean e = true;

    l() {
    }

    static void a() {
        a = true;
    }

    static void b() {
        a = false;
    }

    static boolean c() {
        return a;
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.c = z;
    }

    /* access modifiers changed from: package-private */
    public final void b(boolean z) {
        this.e = z;
    }

    /* access modifiers changed from: package-private */
    public final void c(boolean z) {
        this.d = z;
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        this.b = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean e() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return a && this.c && this.b && !this.e && !this.d;
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return this.d;
    }

    public final String toString() {
        return super.toString();
    }
}
