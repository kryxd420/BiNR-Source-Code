package com.adcolony.sdk;

import java.util.ArrayList;

class av {
    an a;
    ad b = new ad().b();
    float[] c = new float[16];
    ad d = new ad().b();
    ad e = new ad().b();
    ad f = new ad().b();
    ArrayList<ad> g = new ArrayList<>();
    ArrayList<ad> h = new ArrayList<>();
    boolean i;
    boolean j;
    boolean k = true;

    av(an anVar) {
        this.a = anVar;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.a.d();
        this.e.b();
        this.i = true;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a();
        do {
        } while (d());
    }

    /* access modifiers changed from: package-private */
    public ad c() {
        int size = this.h.size();
        if (size == 0) {
            return new ad();
        }
        return this.h.remove(size - 1);
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        int size = this.g.size();
        if (size == 0) {
            return false;
        }
        this.a.d();
        this.j = true;
        this.h.add(this.g.remove(size - 1));
        this.e.b();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.a.d();
        this.g.add(c().b(this.e));
        this.e.b();
        this.j = true;
        this.i = true;
    }

    /* access modifiers changed from: package-private */
    public void a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        double d11;
        double d12;
        boolean z;
        double d13;
        double d14 = d4;
        double d15 = d5;
        this.a.d();
        double d16 = d14 / d9;
        double d17 = d15 / d10;
        boolean z2 = true;
        double d18 = 1.0d;
        if (d16 >= 0.0d) {
            d12 = d16;
            d11 = 1.0d;
            z = false;
        } else {
            d12 = -d16;
            d11 = -1.0d;
            z = true;
        }
        if (d17 >= 0.0d) {
            d13 = d17;
            z2 = false;
        } else {
            d13 = -d17;
            d18 = -1.0d;
        }
        double d19 = d6 * d14;
        double d20 = d7 * d15;
        if (z || z2) {
            d19 -= d14 / 2.0d;
            d20 -= d15 / 2.0d;
            b((-d14) / 2.0d, (-d15) / 2.0d);
        }
        double cos = Math.cos(d8);
        double sin = Math.sin(d8);
        double d21 = d19 * d12;
        double d22 = (d2 - (d21 * cos)) + (sin * d13 * d20);
        double d23 = cos * d13;
        this.e.a(cos * d12 * d11, d12 * sin * d11, 0.0d, 0.0d, (-sin) * d13 * d18, d23 * d18, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, d22, (d3 - (d21 * sin)) - (d23 * d20), 0.0d, 1.0d);
    }

    /* access modifiers changed from: package-private */
    public void a(double d2) {
        this.a.d();
        this.e.b(d2);
    }

    /* access modifiers changed from: package-private */
    public void b(double d2) {
        this.a.d();
        this.e.a(d2);
    }

    /* access modifiers changed from: package-private */
    public void a(double d2, double d3) {
        this.a.d();
        this.e.a(d2, d3, 1.0d);
    }

    /* access modifiers changed from: package-private */
    public void a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17) {
        this.a.d();
        this.e.b(d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17);
        this.i = true;
    }

    /* access modifiers changed from: package-private */
    public void a(ad adVar) {
        this.a.d();
        this.d.b(adVar);
        this.k = true;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        b();
    }

    /* access modifiers changed from: package-private */
    public void b(double d2, double d3) {
        this.e.b(d2, d3, 0.0d);
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (this.j || this.k) {
            this.k = false;
            if (this.j) {
                this.j = false;
                this.f.b();
                for (int size = this.g.size() - 1; size >= 0; size--) {
                    this.f.a(this.g.get(size));
                }
            }
            this.b.b();
            this.b.a(this.e);
            this.b.a(this.f);
            this.b.a(this.d);
            this.b.a(this.c);
        }
    }
}
