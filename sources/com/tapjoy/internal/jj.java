package com.tapjoy.internal;

public final class jj {
    public static jj a = new jj((String) null);
    public String b;
    public Throwable c;
    private Object[] d;

    public jj(String str) {
        this(str, (Object[]) null, (Throwable) null);
    }

    public jj(String str, Object[] objArr, Throwable th) {
        this.b = str;
        this.c = th;
        if (th == null) {
            this.d = objArr;
        } else if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        } else {
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            this.d = objArr2;
        }
    }
}
