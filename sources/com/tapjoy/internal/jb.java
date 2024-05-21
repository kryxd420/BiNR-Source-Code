package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;

final class jb implements ix {
    public final iv a = new iv();
    public final jg b;
    boolean c;

    jb(jg jgVar) {
        if (jgVar != null) {
            this.b = jgVar;
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final long b(iv ivVar, long j) {
        if (ivVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else if (this.a.b == 0 && this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.a.b(ivVar, Math.min(j, this.a.b));
        }
    }

    public final boolean b() {
        if (!this.c) {
            return this.a.b() && this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed");
    }

    public final byte c() {
        a(1);
        return this.a.c();
    }

    public final iy b(long j) {
        a(j);
        return this.a.b(j);
    }

    public final String c(long j) {
        a(j);
        return this.a.c(j);
    }

    public final int e() {
        a(4);
        return ji.a(this.a.d());
    }

    public final long f() {
        a(8);
        return this.a.f();
    }

    public final void d(long j) {
        if (!this.c) {
            while (j > 0) {
                if (this.a.b == 0 && this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.a.b);
                this.a.d(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final void close() {
        if (!this.c) {
            this.c = true;
            this.b.close();
            iv ivVar = this.a;
            try {
                ivVar.d(ivVar.b);
            } catch (EOFException e) {
                throw new AssertionError(e);
            }
        }
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }

    public final void a(long j) {
        boolean z;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.c) {
            while (true) {
                if (this.a.b < j) {
                    if (this.b.b(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                throw new EOFException();
            }
        } else {
            throw new IllegalStateException("closed");
        }
    }
}
