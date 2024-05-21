package com.tapjoy.internal;

import java.io.Serializable;
import java.util.Arrays;

public class iy implements Serializable, Comparable {
    static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final iy b = new iy((byte[]) new byte[0].clone());
    final byte[] c;
    transient int d;
    transient String e;

    public /* synthetic */ int compareTo(Object obj) {
        iy iyVar = (iy) obj;
        int c2 = c();
        int c3 = iyVar.c();
        int min = Math.min(c2, c3);
        for (int i = 0; i < min; i++) {
            byte a2 = a(i) & 255;
            byte a3 = iyVar.a(i) & 255;
            if (a2 != a3) {
                return a2 < a3 ? -1 : 1;
            }
        }
        if (c2 == c3) {
            return 0;
        }
        return c2 < c3 ? -1 : 1;
    }

    public iy(byte[] bArr) {
        this.c = bArr;
    }

    public String a() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.c, ji.a);
        this.e = str2;
        return str2;
    }

    public String b() {
        char[] cArr = new char[(this.c.length * 2)];
        int i = 0;
        for (byte b2 : this.c) {
            int i2 = i + 1;
            cArr[i] = a[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = a[b2 & 15];
        }
        return new String(cArr);
    }

    public iy a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (i2 <= this.c.length) {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (i == 0 && i2 == this.c.length) {
                return this;
            } else {
                byte[] bArr = new byte[i3];
                System.arraycopy(this.c, i, bArr, 0, i3);
                return new iy(bArr);
            }
        } else {
            throw new IllegalArgumentException("endIndex > length(" + this.c.length + ")");
        }
    }

    public byte a(int i) {
        return this.c[i];
    }

    public int c() {
        return this.c.length;
    }

    public byte[] d() {
        return (byte[]) this.c.clone();
    }

    /* access modifiers changed from: package-private */
    public void a(iv ivVar) {
        ivVar.a(this.c, 0, this.c.length);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.c.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && ji.a(this.c, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof iy) {
            iy iyVar = (iy) obj;
            return iyVar.c() == this.c.length && iyVar.a(0, this.c, 0, this.c.length);
        }
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.c);
        this.d = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        if (this.c.length == 0) {
            return "[size=0]";
        }
        String a2 = a();
        int length = a2.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= length) {
                i = a2.length();
                break;
            } else if (i2 == 64) {
                break;
            } else {
                int codePointAt = a2.codePointAt(i);
                if ((!Character.isISOControl(codePointAt) || codePointAt == 10 || codePointAt == 13) && codePointAt != 65533) {
                    i2++;
                    i += Character.charCount(codePointAt);
                }
            }
        }
        i = -1;
        if (i != -1) {
            String replace = a2.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i < a2.length()) {
                sb = new StringBuilder("[size=");
                sb.append(this.c.length);
                sb.append(" text=");
                sb.append(replace);
                str = "…]";
            } else {
                sb = new StringBuilder("[text=");
                sb.append(replace);
                str = "]";
            }
            sb.append(str);
            return sb.toString();
        } else if (this.c.length <= 64) {
            return "[hex=" + b() + "]";
        } else {
            return "[size=" + this.c.length + " hex=" + a(0, 64).b() + "…]";
        }
    }
}
