package com.tapjoy.internal;

/* renamed from: com.tapjoy.internal.if  reason: invalid class name */
public final class Cif {
    public float a;
    public int b;

    public static Cif a(String str) {
        if (ju.c(str)) {
            return null;
        }
        try {
            Cif ifVar = new Cif();
            int length = str.length() - 1;
            char charAt = str.charAt(length);
            if (charAt == 'w') {
                ifVar.a = Float.valueOf(str.substring(0, length)).floatValue();
                ifVar.b = 1;
            } else if (charAt == 'h') {
                ifVar.a = Float.valueOf(str.substring(0, length)).floatValue();
                ifVar.b = 2;
            } else {
                ifVar.a = Float.valueOf(str).floatValue();
                ifVar.b = 0;
            }
            return ifVar;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public final float a(float f, float f2) {
        if (this.b == 1) {
            return (this.a * f) / 100.0f;
        }
        if (this.b == 2) {
            return (this.a * f2) / 100.0f;
        }
        return this.a;
    }
}
