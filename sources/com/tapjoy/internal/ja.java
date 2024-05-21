package com.tapjoy.internal;

final class ja implements iw {
    public final iv a = new iv();
    public final jf b;
    boolean c;

    ja(jf jfVar) {
        if (jfVar != null) {
            this.b = jfVar;
            return;
        }
        throw new IllegalArgumentException("sink == null");
    }

    public final void a(iv ivVar, long j) {
        if (!this.c) {
            this.a.a(ivVar, j);
            b();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final iw b(iy iyVar) {
        if (!this.c) {
            this.a.b(iyVar);
            return b();
        }
        throw new IllegalStateException("closed");
    }

    public final iw b(String str) {
        if (!this.c) {
            this.a.b(str);
            return b();
        }
        throw new IllegalStateException("closed");
    }

    public final iw e(int i) {
        if (!this.c) {
            this.a.e(i);
            return b();
        }
        throw new IllegalStateException("closed");
    }

    public final iw d(int i) {
        if (!this.c) {
            this.a.d(i);
            return b();
        }
        throw new IllegalStateException("closed");
    }

    public final iw f(long j) {
        if (!this.c) {
            this.a.f(j);
            return b();
        }
        throw new IllegalStateException("closed");
    }

    private iw b() {
        if (!this.c) {
            iv ivVar = this.a;
            long j = ivVar.b;
            if (j == 0) {
                j = 0;
            } else {
                jc jcVar = ivVar.a.g;
                if (jcVar.c < 8192 && jcVar.e) {
                    j -= (long) (jcVar.c - jcVar.b);
                }
            }
            if (j > 0) {
                this.b.a(this.a, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public final iw a() {
        if (!this.c) {
            long j = this.a.b;
            if (j > 0) {
                this.b.a(this.a, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public final void flush() {
        if (!this.c) {
            if (this.a.b > 0) {
                this.b.a(this.a, this.a.b);
            }
            this.b.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final void close() {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.b > 0) {
                    this.b.a(this.a, this.a.b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                ji.a(th);
            }
        }
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
