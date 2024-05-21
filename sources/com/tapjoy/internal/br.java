package com.tapjoy.internal;

import com.tapjoy.internal.bq;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public final class br extends bq {
    public static final bq.a a = new bq.a() {
        public final bq a(Reader reader) {
            return new br(reader);
        }

        public final bq a(String str) {
            return new br(new StringReader(str));
        }
    };
    private final cm b = new cm();
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 1;
    private int i = 1;
    private final List j = new ArrayList();
    private bv k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;

    public br(Reader reader) {
        a(bt.EMPTY_DOCUMENT);
        this.p = false;
        if (reader != null) {
            this.c = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    public final void f() {
        a(bv.BEGIN_ARRAY);
    }

    public final void g() {
        a(bv.END_ARRAY);
    }

    public final void h() {
        a(bv.BEGIN_OBJECT);
    }

    public final void i() {
        a(bv.END_OBJECT);
    }

    private void a(bv bvVar) {
        k();
        if (this.k == bvVar) {
            t();
            return;
        }
        throw new IllegalStateException("Expected " + bvVar + " but was " + k());
    }

    public final boolean j() {
        k();
        return (this.k == bv.END_OBJECT || this.k == bv.END_ARRAY) ? false : true;
    }

    public final bv k() {
        if (this.k != null) {
            return this.k;
        }
        switch ((bt) this.j.get(this.j.size() - 1)) {
            case EMPTY_DOCUMENT:
                b(bt.NONEMPTY_DOCUMENT);
                bv v = v();
                if (this.d || this.k == bv.BEGIN_ARRAY || this.k == bv.BEGIN_OBJECT) {
                    return v;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.k);
            case EMPTY_ARRAY:
                return a(true);
            case NONEMPTY_ARRAY:
                return a(false);
            case EMPTY_OBJECT:
                return b(true);
            case DANGLING_NAME:
                int y = y();
                if (y != 58) {
                    if (y == 61) {
                        z();
                        if ((this.f < this.g || a(1)) && this.e[this.f] == '>') {
                            this.f++;
                        }
                    } else {
                        throw d("Expected ':'");
                    }
                }
                b(bt.NONEMPTY_OBJECT);
                return v();
            case NONEMPTY_OBJECT:
                return b(false);
            case NONEMPTY_DOCUMENT:
                try {
                    bv v2 = v();
                    if (this.d) {
                        return v2;
                    }
                    throw d("Expected EOF");
                } catch (EOFException unused) {
                    bv bvVar = bv.END_DOCUMENT;
                    this.k = bvVar;
                    return bvVar;
                }
            case CLOSED:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    private bv t() {
        k();
        bv bvVar = this.k;
        this.k = null;
        this.m = null;
        this.l = null;
        return bvVar;
    }

    public final String l() {
        k();
        if (this.k == bv.NAME) {
            String str = this.l;
            t();
            return str;
        }
        throw new IllegalStateException("Expected a name but was " + k());
    }

    public final String m() {
        k();
        if (this.k == bv.STRING || this.k == bv.NUMBER) {
            String str = this.m;
            t();
            return str;
        }
        throw new IllegalStateException("Expected a string but was " + k());
    }

    public final boolean n() {
        k();
        if (this.k == bv.BOOLEAN) {
            boolean z = this.m == "true";
            t();
            return z;
        }
        throw new IllegalStateException("Expected a boolean but was " + this.k);
    }

    public final void o() {
        k();
        if (this.k == bv.NULL) {
            t();
            return;
        }
        throw new IllegalStateException("Expected null but was " + this.k);
    }

    public final double p() {
        k();
        if (this.k == bv.STRING || this.k == bv.NUMBER) {
            double parseDouble = Double.parseDouble(this.m);
            t();
            return parseDouble;
        }
        throw new IllegalStateException("Expected a double but was " + this.k);
    }

    public final long q() {
        long j2;
        k();
        if (this.k == bv.STRING || this.k == bv.NUMBER) {
            try {
                j2 = Long.parseLong(this.m);
            } catch (NumberFormatException unused) {
                double parseDouble = Double.parseDouble(this.m);
                long j3 = (long) parseDouble;
                if (((double) j3) == parseDouble) {
                    j2 = j3;
                } else {
                    throw new NumberFormatException(this.m);
                }
            }
            t();
            return j2;
        }
        throw new IllegalStateException("Expected a long but was " + this.k);
    }

    public final int r() {
        int i2;
        k();
        if (this.k == bv.STRING || this.k == bv.NUMBER) {
            try {
                i2 = Integer.parseInt(this.m);
            } catch (NumberFormatException unused) {
                double parseDouble = Double.parseDouble(this.m);
                int i3 = (int) parseDouble;
                if (((double) i3) == parseDouble) {
                    i2 = i3;
                } else {
                    throw new NumberFormatException(this.m);
                }
            }
            t();
            return i2;
        }
        throw new IllegalStateException("Expected an int but was " + this.k);
    }

    public final void close() {
        this.m = null;
        this.k = null;
        this.j.clear();
        this.j.add(bt.CLOSED);
        this.c.close();
    }

    public final void s() {
        k();
        if (this.k == bv.END_ARRAY || this.k == bv.END_OBJECT) {
            throw new IllegalStateException("Expected a value but was " + this.k);
        }
        this.p = true;
        int i2 = 0;
        do {
            try {
                bv t = t();
                if (t != bv.BEGIN_ARRAY) {
                    if (t != bv.BEGIN_OBJECT) {
                        if (t == bv.END_ARRAY || t == bv.END_OBJECT) {
                            i2--;
                            continue;
                        }
                    }
                }
                i2++;
                continue;
            } finally {
                this.p = false;
            }
        } while (i2 != 0);
    }

    private bt u() {
        return (bt) this.j.remove(this.j.size() - 1);
    }

    private void a(bt btVar) {
        this.j.add(btVar);
    }

    private void b(bt btVar) {
        this.j.set(this.j.size() - 1, btVar);
    }

    private bv a(boolean z) {
        if (z) {
            b(bt.NONEMPTY_ARRAY);
        } else {
            int y = y();
            if (y != 44) {
                if (y == 59) {
                    z();
                } else if (y == 93) {
                    u();
                    bv bvVar = bv.END_ARRAY;
                    this.k = bvVar;
                    return bvVar;
                } else {
                    throw d("Unterminated array");
                }
            }
        }
        int y2 = y();
        if (!(y2 == 44 || y2 == 59)) {
            if (y2 != 93) {
                this.f--;
                return v();
            } else if (z) {
                u();
                bv bvVar2 = bv.END_ARRAY;
                this.k = bvVar2;
                return bvVar2;
            }
        }
        z();
        this.f--;
        this.m = "null";
        bv bvVar3 = bv.NULL;
        this.k = bvVar3;
        return bvVar3;
    }

    private bv b(boolean z) {
        if (!z) {
            int y = y();
            if (!(y == 44 || y == 59)) {
                if (y == 125) {
                    u();
                    bv bvVar = bv.END_OBJECT;
                    this.k = bvVar;
                    return bvVar;
                }
                throw d("Unterminated object");
            }
        } else if (y() != 125) {
            this.f--;
        } else {
            u();
            bv bvVar2 = bv.END_OBJECT;
            this.k = bvVar2;
            return bvVar2;
        }
        int y2 = y();
        if (y2 != 34) {
            if (y2 != 39) {
                z();
                this.f--;
                this.l = c(false);
                if (this.l.length() == 0) {
                    throw d("Expected name");
                }
                b(bt.DANGLING_NAME);
                bv bvVar3 = bv.NAME;
                this.k = bvVar3;
                return bvVar3;
            }
            z();
        }
        this.l = a((char) y2);
        b(bt.DANGLING_NAME);
        bv bvVar32 = bv.NAME;
        this.k = bvVar32;
        return bvVar32;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x01eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tapjoy.internal.bv v() {
        /*
            r13 = this;
            int r0 = r13.y()
            r1 = 34
            if (r0 == r1) goto L_0x020f
            r1 = 39
            if (r0 == r1) goto L_0x020c
            r1 = 91
            if (r0 == r1) goto L_0x0202
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L_0x01f8
            int r0 = r13.f
            r1 = 1
            int r0 = r0 - r1
            r13.f = r0
            java.lang.String r0 = r13.c(r1)
            r13.m = r0
            int r0 = r13.o
            if (r0 == 0) goto L_0x01f1
            int r0 = r13.n
            r2 = -1
            if (r0 == r2) goto L_0x01e1
            int r0 = r13.o
            r2 = 85
            r3 = 117(0x75, float:1.64E-43)
            r4 = 76
            r5 = 108(0x6c, float:1.51E-43)
            r6 = 4
            if (r0 != r6) goto L_0x008c
            r0 = 110(0x6e, float:1.54E-43)
            char[] r7 = r13.e
            int r8 = r13.n
            char r7 = r7[r8]
            if (r0 == r7) goto L_0x004a
            r0 = 78
            char[] r7 = r13.e
            int r8 = r13.n
            char r7 = r7[r8]
            if (r0 != r7) goto L_0x008c
        L_0x004a:
            char[] r0 = r13.e
            int r7 = r13.n
            int r7 = r7 + r1
            char r0 = r0[r7]
            if (r3 == r0) goto L_0x005c
            char[] r0 = r13.e
            int r7 = r13.n
            int r7 = r7 + r1
            char r0 = r0[r7]
            if (r2 != r0) goto L_0x008c
        L_0x005c:
            char[] r0 = r13.e
            int r7 = r13.n
            int r7 = r7 + 2
            char r0 = r0[r7]
            if (r5 == r0) goto L_0x0070
            char[] r0 = r13.e
            int r7 = r13.n
            int r7 = r7 + 2
            char r0 = r0[r7]
            if (r4 != r0) goto L_0x008c
        L_0x0070:
            char[] r0 = r13.e
            int r7 = r13.n
            int r7 = r7 + 3
            char r0 = r0[r7]
            if (r5 == r0) goto L_0x0084
            char[] r0 = r13.e
            int r7 = r13.n
            int r7 = r7 + 3
            char r0 = r0[r7]
            if (r4 != r0) goto L_0x008c
        L_0x0084:
            java.lang.String r0 = "null"
            r13.m = r0
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.NULL
            goto L_0x01e3
        L_0x008c:
            int r0 = r13.o
            r7 = 69
            r8 = 101(0x65, float:1.42E-43)
            if (r0 != r6) goto L_0x00ee
            r0 = 116(0x74, float:1.63E-43)
            char[] r9 = r13.e
            int r10 = r13.n
            char r9 = r9[r10]
            if (r0 == r9) goto L_0x00a8
            r0 = 84
            char[] r9 = r13.e
            int r10 = r13.n
            char r9 = r9[r10]
            if (r0 != r9) goto L_0x00ee
        L_0x00a8:
            r0 = 114(0x72, float:1.6E-43)
            char[] r9 = r13.e
            int r10 = r13.n
            int r10 = r10 + r1
            char r9 = r9[r10]
            if (r0 == r9) goto L_0x00be
            r0 = 82
            char[] r9 = r13.e
            int r10 = r13.n
            int r10 = r10 + r1
            char r9 = r9[r10]
            if (r0 != r9) goto L_0x00ee
        L_0x00be:
            char[] r0 = r13.e
            int r9 = r13.n
            int r9 = r9 + 2
            char r0 = r0[r9]
            if (r3 == r0) goto L_0x00d2
            char[] r0 = r13.e
            int r3 = r13.n
            int r3 = r3 + 2
            char r0 = r0[r3]
            if (r2 != r0) goto L_0x00ee
        L_0x00d2:
            char[] r0 = r13.e
            int r2 = r13.n
            int r2 = r2 + 3
            char r0 = r0[r2]
            if (r8 == r0) goto L_0x00e6
            char[] r0 = r13.e
            int r2 = r13.n
            int r2 = r2 + 3
            char r0 = r0[r2]
            if (r7 != r0) goto L_0x00ee
        L_0x00e6:
            java.lang.String r0 = "true"
            r13.m = r0
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.BOOLEAN
            goto L_0x01e3
        L_0x00ee:
            int r0 = r13.o
            r2 = 5
            if (r0 != r2) goto L_0x0163
            r0 = 102(0x66, float:1.43E-43)
            char[] r2 = r13.e
            int r3 = r13.n
            char r2 = r2[r3]
            if (r0 == r2) goto L_0x0107
            r0 = 70
            char[] r2 = r13.e
            int r3 = r13.n
            char r2 = r2[r3]
            if (r0 != r2) goto L_0x0163
        L_0x0107:
            r0 = 97
            char[] r2 = r13.e
            int r3 = r13.n
            int r3 = r3 + r1
            char r2 = r2[r3]
            if (r0 == r2) goto L_0x011d
            r0 = 65
            char[] r2 = r13.e
            int r3 = r13.n
            int r3 = r3 + r1
            char r2 = r2[r3]
            if (r0 != r2) goto L_0x0163
        L_0x011d:
            char[] r0 = r13.e
            int r2 = r13.n
            int r2 = r2 + 2
            char r0 = r0[r2]
            if (r5 == r0) goto L_0x0131
            char[] r0 = r13.e
            int r2 = r13.n
            int r2 = r2 + 2
            char r0 = r0[r2]
            if (r4 != r0) goto L_0x0163
        L_0x0131:
            r0 = 115(0x73, float:1.61E-43)
            char[] r2 = r13.e
            int r3 = r13.n
            int r3 = r3 + 3
            char r2 = r2[r3]
            if (r0 == r2) goto L_0x0149
            r0 = 83
            char[] r2 = r13.e
            int r3 = r13.n
            int r3 = r3 + 3
            char r2 = r2[r3]
            if (r0 != r2) goto L_0x0163
        L_0x0149:
            char[] r0 = r13.e
            int r2 = r13.n
            int r2 = r2 + r6
            char r0 = r0[r2]
            if (r8 == r0) goto L_0x015b
            char[] r0 = r13.e
            int r2 = r13.n
            int r2 = r2 + r6
            char r0 = r0[r2]
            if (r7 != r0) goto L_0x0163
        L_0x015b:
            java.lang.String r0 = "false"
            r13.m = r0
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.BOOLEAN
            goto L_0x01e3
        L_0x0163:
            com.tapjoy.internal.cm r0 = r13.b
            char[] r2 = r13.e
            int r3 = r13.n
            int r4 = r13.o
            java.lang.String r0 = r0.a(r2, r3, r4)
            r13.m = r0
            char[] r0 = r13.e
            int r2 = r13.n
            int r3 = r13.o
            char r4 = r0[r2]
            r5 = 45
            if (r4 != r5) goto L_0x0185
            int r4 = r2 + 1
            char r6 = r0[r4]
            r12 = r6
            r6 = r4
            r4 = r12
            goto L_0x0186
        L_0x0185:
            r6 = r2
        L_0x0186:
            r9 = 57
            r10 = 48
            if (r4 != r10) goto L_0x0190
            int r6 = r6 + r1
            char r4 = r0[r6]
            goto L_0x01a2
        L_0x0190:
            r11 = 49
            if (r4 < r11) goto L_0x01de
            if (r4 > r9) goto L_0x01de
            int r6 = r6 + r1
            char r4 = r0[r6]
        L_0x0199:
            if (r4 < r10) goto L_0x01a2
            if (r4 > r9) goto L_0x01a2
            int r6 = r6 + 1
            char r4 = r0[r6]
            goto L_0x0199
        L_0x01a2:
            r11 = 46
            if (r4 != r11) goto L_0x01b3
            int r6 = r6 + 1
            char r4 = r0[r6]
        L_0x01aa:
            if (r4 < r10) goto L_0x01b3
            if (r4 > r9) goto L_0x01b3
            int r6 = r6 + 1
            char r4 = r0[r6]
            goto L_0x01aa
        L_0x01b3:
            if (r4 == r8) goto L_0x01b7
            if (r4 != r7) goto L_0x01d5
        L_0x01b7:
            int r6 = r6 + 1
            char r4 = r0[r6]
            r7 = 43
            if (r4 == r7) goto L_0x01c1
            if (r4 != r5) goto L_0x01c5
        L_0x01c1:
            int r6 = r6 + 1
            char r4 = r0[r6]
        L_0x01c5:
            if (r4 < r10) goto L_0x01db
            if (r4 > r9) goto L_0x01db
            int r6 = r6 + r1
            char r1 = r0[r6]
        L_0x01cc:
            if (r1 < r10) goto L_0x01d5
            if (r1 > r9) goto L_0x01d5
            int r6 = r6 + 1
            char r1 = r0[r6]
            goto L_0x01cc
        L_0x01d5:
            int r2 = r2 + r3
            if (r6 != r2) goto L_0x01e1
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.NUMBER
            goto L_0x01e3
        L_0x01db:
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.STRING
            goto L_0x01e3
        L_0x01de:
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.STRING
            goto L_0x01e3
        L_0x01e1:
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.STRING
        L_0x01e3:
            r13.k = r0
            com.tapjoy.internal.bv r0 = r13.k
            com.tapjoy.internal.bv r1 = com.tapjoy.internal.bv.STRING
            if (r0 != r1) goto L_0x01ee
            r13.z()
        L_0x01ee:
            com.tapjoy.internal.bv r0 = r13.k
            return r0
        L_0x01f1:
            java.lang.String r0 = "Expected literal value"
            java.io.IOException r0 = r13.d(r0)
            throw r0
        L_0x01f8:
            com.tapjoy.internal.bt r0 = com.tapjoy.internal.bt.EMPTY_OBJECT
            r13.a((com.tapjoy.internal.bt) r0)
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.BEGIN_OBJECT
            r13.k = r0
            return r0
        L_0x0202:
            com.tapjoy.internal.bt r0 = com.tapjoy.internal.bt.EMPTY_ARRAY
            r13.a((com.tapjoy.internal.bt) r0)
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.BEGIN_ARRAY
            r13.k = r0
            return r0
        L_0x020c:
            r13.z()
        L_0x020f:
            char r0 = (char) r0
            java.lang.String r0 = r13.a((char) r0)
            r13.m = r0
            com.tapjoy.internal.bv r0 = com.tapjoy.internal.bv.STRING
            r13.k = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.br.v():com.tapjoy.internal.bv");
    }

    private boolean a(int i2) {
        for (int i3 = 0; i3 < this.f; i3++) {
            if (this.e[i3] == 10) {
                this.h++;
                this.i = 1;
            } else {
                this.i++;
            }
        }
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(this.e, this.f, this.e, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(this.e, this.g, this.e.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g += read;
            if (this.h == 1 && this.i == 1 && this.g > 0 && this.e[0] == 65279) {
                this.f++;
                this.i--;
            }
        } while (this.g < i2);
        return true;
    }

    private int w() {
        int i2 = this.h;
        for (int i3 = 0; i3 < this.f; i3++) {
            if (this.e[i3] == 10) {
                i2++;
            }
        }
        return i2;
    }

    private int x() {
        int i2 = this.i;
        for (int i3 = 0; i3 < this.f; i3++) {
            i2 = this.e[i3] == 10 ? 1 : i2 + 1;
        }
        return i2;
    }

    private int y() {
        while (true) {
            boolean z = true;
            if (this.f < this.g || a(1)) {
                char[] cArr = this.e;
                int i2 = this.f;
                this.f = i2 + 1;
                char c2 = cArr[i2];
                if (!(c2 == 13 || c2 == ' ')) {
                    if (c2 == '#') {
                        z();
                        A();
                    } else if (c2 != '/') {
                        switch (c2) {
                            case 9:
                            case 10:
                                break;
                            default:
                                return c2;
                        }
                    } else if (this.f == this.g && !a(1)) {
                        return c2;
                    } else {
                        z();
                        char c3 = this.e[this.f];
                        if (c3 == '*') {
                            this.f++;
                            while (true) {
                                int i3 = 0;
                                if (this.f + "*/".length() > this.g && !a("*/".length())) {
                                    z = false;
                                    break;
                                }
                                while (i3 < "*/".length()) {
                                    if (this.e[this.f + i3] == "*/".charAt(i3)) {
                                        i3++;
                                    } else {
                                        this.f++;
                                    }
                                }
                                break;
                            }
                            if (z) {
                                this.f += 2;
                            } else {
                                throw d("Unterminated comment");
                            }
                        } else if (c3 != '/') {
                            return c2;
                        } else {
                            this.f++;
                            A();
                        }
                    }
                }
            } else {
                throw new EOFException("End of input");
            }
        }
    }

    private void z() {
        if (!this.d) {
            throw d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A() {
        /*
            r3 = this;
        L_0x0000:
            int r0 = r3.f
            int r1 = r3.g
            if (r0 < r1) goto L_0x000d
            r0 = 1
            boolean r0 = r3.a((int) r0)
            if (r0 == 0) goto L_0x001f
        L_0x000d:
            char[] r0 = r3.e
            int r1 = r3.f
            int r2 = r1 + 1
            r3.f = r2
            char r0 = r0[r1]
            r1 = 13
            if (r0 == r1) goto L_0x001f
            r1 = 10
            if (r0 != r1) goto L_0x0000
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.br.A():void");
    }

    private String a(char c2) {
        StringBuilder sb = null;
        do {
            int i2 = this.f;
            while (this.f < this.g) {
                char[] cArr = this.e;
                int i3 = this.f;
                this.f = i3 + 1;
                char c3 = cArr[i3];
                if (c3 == c2) {
                    if (this.p) {
                        return "skipped!";
                    }
                    if (sb == null) {
                        return this.b.a(this.e, i2, (this.f - i2) - 1);
                    }
                    sb.append(this.e, i2, (this.f - i2) - 1);
                    return sb.toString();
                } else if (c3 == '\\') {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.e, i2, (this.f - i2) - 1);
                    if (this.f != this.g || a(1)) {
                        char[] cArr2 = this.e;
                        int i4 = this.f;
                        this.f = i4 + 1;
                        char c4 = cArr2[i4];
                        if (c4 == 'b') {
                            c4 = 8;
                        } else if (c4 == 'f') {
                            c4 = 12;
                        } else if (c4 == 'n') {
                            c4 = 10;
                        } else if (c4 != 'r') {
                            switch (c4) {
                                case 't':
                                    c4 = 9;
                                    break;
                                case 'u':
                                    if (this.f + 4 <= this.g || a(4)) {
                                        String a2 = this.b.a(this.e, this.f, 4);
                                        this.f += 4;
                                        c4 = (char) Integer.parseInt(a2, 16);
                                        break;
                                    } else {
                                        throw d("Unterminated escape sequence");
                                    }
                                    break;
                            }
                        } else {
                            c4 = 13;
                        }
                        sb.append(c4);
                        i2 = this.f;
                    } else {
                        throw d("Unterminated escape sequence");
                    }
                }
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.e, i2, this.f - i2);
        } while (a(1));
        throw d("Unterminated string");
    }

    private String c(boolean z) {
        int i2;
        this.n = -1;
        int i3 = 0;
        this.o = 0;
        String str = null;
        StringBuilder sb = null;
        while (true) {
            i2 = 0;
            while (true) {
                if (this.f + i2 < this.g) {
                    switch (this.e[this.f + i2]) {
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case '{':
                        case '}':
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            z();
                            break;
                        default:
                            i2++;
                            break;
                    }
                } else if (i2 >= this.e.length) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.e, this.f, i2);
                    this.o += i2;
                    this.f += i2;
                    if (!a(1)) {
                    }
                } else if (!a(i2 + 1)) {
                    this.e[this.g] = 0;
                }
            }
        }
        i3 = i2;
        if (z && sb == null) {
            this.n = this.f;
        } else if (this.p) {
            str = "skipped!";
        } else if (sb == null) {
            str = this.b.a(this.e, this.f, i3);
        } else {
            sb.append(this.e, this.f, i3);
            str = sb.toString();
        }
        this.o += i3;
        this.f += i3;
        return str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" near ");
        StringBuilder sb2 = new StringBuilder();
        int min = Math.min(this.f, 20);
        sb2.append(this.e, this.f - min, min);
        sb2.append(this.e, this.f, Math.min(this.g - this.f, 20));
        sb.append(sb2);
        return sb.toString();
    }

    private IOException d(String str) {
        throw new bx(str + " at line " + w() + " column " + x());
    }
}
