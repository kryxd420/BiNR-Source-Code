package com.adcolony.sdk;

class ad {
    static float[] a = new float[16];
    static ad b = new ad();
    double[] c = new double[16];
    boolean d;

    ad() {
        b();
    }

    ad(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17) {
        b(d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17);
    }

    /* access modifiers changed from: package-private */
    public ad a() {
        ad adVar = new ad();
        adVar.b(this);
        return adVar;
    }

    /* access modifiers changed from: package-private */
    public ad a(ad adVar) {
        ad adVar2 = adVar;
        if (adVar2.d) {
            return this;
        }
        return a(adVar2.c[0], adVar2.c[1], adVar2.c[2], adVar2.c[3], adVar2.c[4], adVar2.c[5], adVar2.c[6], adVar2.c[7], adVar2.c[8], adVar2.c[9], adVar2.c[10], adVar2.c[11], adVar2.c[12], adVar2.c[13], adVar2.c[14], adVar2.c[15]);
    }

    /* access modifiers changed from: package-private */
    public ad a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17) {
        return a(d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, this);
    }

    /* access modifiers changed from: package-private */
    public ad a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, ad adVar) {
        ad adVar2 = adVar;
        if (this.d) {
            return adVar.b(d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17);
        }
        double d18 = this.c[0];
        double d19 = this.c[1];
        double d20 = this.c[2];
        double d21 = this.c[3];
        double d22 = this.c[4];
        double d23 = this.c[5];
        double d24 = this.c[6];
        double d25 = this.c[7];
        double d26 = this.c[8];
        double d27 = this.c[9];
        double d28 = this.c[10];
        double d29 = this.c[11];
        double d30 = this.c[12];
        double d31 = this.c[13];
        double d32 = this.c[14];
        double d33 = this.c[15];
        adVar2.c[0] = (d18 * d2) + (d19 * d6) + (d20 * d10) + (d21 * d14);
        adVar2.c[1] = (d18 * d3) + (d19 * d7) + (d20 * d11) + (d21 * d15);
        adVar2.c[2] = (d18 * d4) + (d19 * d8) + (d20 * d12) + (d21 * d16);
        adVar2.c[3] = (d18 * d5) + (d19 * d9) + (d20 * d13) + (d21 * d17);
        adVar2.c[4] = (d22 * d2) + (d23 * d6) + (d24 * d10) + (d25 * d14);
        adVar2.c[5] = (d22 * d3) + (d23 * d7) + (d24 * d11) + (d25 * d15);
        adVar2.c[6] = (d22 * d4) + (d23 * d8) + (d24 * d12) + (d25 * d16);
        adVar2.c[7] = (d22 * d5) + (d23 * d9) + (d24 * d13) + (d25 * d17);
        adVar2.c[8] = (d26 * d2) + (d27 * d6) + (d28 * d10) + (d29 * d14);
        adVar2.c[9] = (d26 * d3) + (d27 * d7) + (d28 * d11) + (d29 * d15);
        adVar2.c[10] = (d26 * d4) + (d27 * d8) + (d28 * d12) + (d29 * d16);
        adVar2.c[11] = (d26 * d5) + (d27 * d9) + (d28 * d13) + (d29 * d17);
        adVar2.c[12] = (d30 * d2) + (d31 * d6) + (d32 * d10) + (d33 * d14);
        adVar2.c[13] = (d30 * d3) + (d31 * d7) + (d32 * d11) + (d33 * d15);
        adVar2.c[14] = (d30 * d4) + (d31 * d8) + (d32 * d12) + (d33 * d16);
        adVar2.c[15] = (d30 * d5) + (d31 * d9) + (d32 * d13) + (d33 * d17);
        this.d = false;
        return adVar2;
    }

    /* access modifiers changed from: package-private */
    public ad a(ad adVar, ad adVar2) {
        ad adVar3 = adVar;
        if (adVar3.d) {
            return adVar2.b(this);
        }
        ad adVar4 = adVar2;
        return a(adVar3.c[0], adVar3.c[1], adVar3.c[2], adVar3.c[3], adVar3.c[4], adVar3.c[5], adVar3.c[6], adVar3.c[7], adVar3.c[8], adVar3.c[9], adVar3.c[10], adVar3.c[11], adVar3.c[12], adVar3.c[13], adVar3.c[14], adVar3.c[15], adVar2);
    }

    /* access modifiers changed from: package-private */
    public ad a(double d2) {
        b.b();
        double cos = Math.cos(d2);
        double sin = Math.sin(d2);
        b.c[0] = cos;
        b.c[1] = sin;
        b.c[4] = -sin;
        b.c[5] = cos;
        b.d = false;
        return a(b);
    }

    /* access modifiers changed from: package-private */
    public ad b(double d2) {
        return a((d2 * 3.141592653589793d) / 180.0d);
    }

    /* access modifiers changed from: package-private */
    public ad a(double d2, double d3, double d4) {
        b.b();
        b.c[0] = d2;
        b.c[5] = d3;
        b.c[10] = d4;
        b.d = false;
        return a(b);
    }

    /* access modifiers changed from: package-private */
    public ad b(ad adVar) {
        for (int i = 15; i >= 0; i--) {
            this.c[i] = adVar.c[i];
        }
        this.d = adVar.d;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ad b(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17) {
        this.d = false;
        this.c[0] = d2;
        this.c[1] = d3;
        this.c[2] = d4;
        this.c[3] = d5;
        this.c[4] = d6;
        this.c[5] = d7;
        this.c[6] = d8;
        this.c[7] = d9;
        this.c[8] = d10;
        this.c[9] = d11;
        this.c[10] = d12;
        this.c[11] = d13;
        this.c[12] = d14;
        this.c[13] = d15;
        this.c[14] = d16;
        this.c[15] = d17;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ad b() {
        for (int i = 15; i >= 0; i--) {
            this.c[i] = 0.0d;
        }
        this.c[0] = 1.0d;
        this.c[5] = 1.0d;
        this.c[10] = 1.0d;
        this.c[15] = 1.0d;
        this.d = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ad a(int i, int i2, double d2) {
        b();
        double[] dArr = this.c;
        double d3 = (double) i;
        Double.isNaN(d3);
        dArr[0] = 2.0d / d3;
        double[] dArr2 = this.c;
        double d4 = (double) i2;
        Double.isNaN(d4);
        dArr2[5] = -2.0d / d4;
        this.c[10] = -2.0d / d2;
        this.c[12] = -1.0d;
        this.c[13] = 1.0d;
        this.c[14] = -1.0d;
        this.d = false;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ad b(double d2, double d3, double d4) {
        b.b();
        b.c[12] = d2;
        b.c[13] = d3;
        b.c[14] = d4;
        b.d = false;
        return a(b);
    }

    /* access modifiers changed from: package-private */
    public ad c(double d2) {
        for (int i = 15; i >= 0; i--) {
            this.c[i] = d2;
        }
        this.d = false;
        return this;
    }

    /* access modifiers changed from: package-private */
    public float[] c() {
        return a(a);
    }

    /* access modifiers changed from: package-private */
    public float[] a(float[] fArr) {
        for (int i = 15; i >= 0; i--) {
            fArr[i] = (float) this.c[i];
        }
        return fArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.c[0]);
        sb.append(' ');
        sb.append(this.c[1]);
        sb.append(' ');
        sb.append(this.c[2]);
        sb.append(' ');
        sb.append(this.c[3]);
        sb.append(10);
        sb.append(this.c[4]);
        sb.append(' ');
        sb.append(this.c[5]);
        sb.append(' ');
        sb.append(this.c[6]);
        sb.append(' ');
        sb.append(this.c[7]);
        sb.append(10);
        sb.append(this.c[8]);
        sb.append(' ');
        sb.append(this.c[9]);
        sb.append(' ');
        sb.append(this.c[10]);
        sb.append(' ');
        sb.append(this.c[11]);
        sb.append(10);
        sb.append(this.c[12]);
        sb.append(' ');
        sb.append(this.c[13]);
        sb.append(' ');
        sb.append(this.c[14]);
        sb.append(' ');
        sb.append(this.c[15]);
        sb.append(10);
        return sb.toString();
    }
}
