package com.adcolony.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.util.Log;
import com.adcolony.sdk.ap;
import com.google.android.gms.gcm.Task;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

class an {
    static final int a = 1;
    static final int b = 2;
    static final int c = 4;
    static final int d = 1;
    static final int e = 2;
    static final int f = 512;
    static int g = 1;
    static ByteBuffer h;
    static IntBuffer i;
    static BitmapFactory.Options j = new BitmapFactory.Options();
    static int[] k = new int[1];
    ap A;
    ap B;
    ap C;
    ap D;
    ap E;
    ap F;
    int l;
    int m;
    int n;
    int o;
    ArrayList<a> p = new ArrayList<>();
    int q;
    int r;
    boolean s = true;
    boolean t = true;
    a u;
    int v;
    FloatBuffer w;
    FloatBuffer x;
    IntBuffer y;
    av z = new av(this);

    an() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4096);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.w = allocateDirect.asFloatBuffer();
        this.w.rewind();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(4096);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.x = allocateDirect2.asFloatBuffer();
        this.x.rewind();
        ByteBuffer allocateDirect3 = ByteBuffer.allocateDirect(Math.max(2048, 4194304));
        allocateDirect3.order(ByteOrder.nativeOrder());
        this.y = allocateDirect3.asIntBuffer();
        this.y.rewind();
    }

    /* access modifiers changed from: package-private */
    public void a(double d2, double d3, double d4, double d5, int i2) {
        int i3 = (i2 >> 24) & 255;
        int i4 = (i2 >> 16) & 255;
        int i5 = (i2 >> 8) & 255;
        int i6 = i2 & 255;
        if (i3 > 0) {
            this.t = false;
        }
        if (i3 < 255) {
            this.s = false;
        }
        this.w.put((float) d2);
        this.w.put((float) d3);
        this.x.put((float) d4);
        this.x.put((float) d5);
        this.y.put((((i6 * i3) / 255) << 16) | (i3 << 24) | (((i5 * i3) / 255) << 8) | ((i4 * i3) / 255));
        this.v++;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        b((a) null);
        this.z.b();
        b();
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, double d2, int i4) {
        if (i2 != 0) {
            int i5 = 0;
            d();
            if ((i2 & 1) != 0) {
                i5 = 16384;
                GLES20.glClearColor(((float) ((i3 >> 16) & 255)) / 255.0f, ((float) ((i3 >> 8) & 255)) / 255.0f, ((float) (i3 & 255)) / 255.0f, ((float) ((i3 >> 24) & 255)) / 255.0f);
            }
            if ((i2 & 2) != 0) {
                i5 |= 256;
                GLES20.glClearDepthf((float) d2);
            }
            if ((i2 & 4) != 0) {
                i5 |= 1024;
                GLES20.glClearStencil(i4);
            }
            GLES20.glClear(i5);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        d();
        GLES20.glDisable(3089);
    }

    /* access modifiers changed from: package-private */
    public a a(Bitmap bitmap) {
        d();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = 1;
        int i3 = 1;
        while (i3 < width) {
            i3 <<= 1;
        }
        while (i2 < height) {
            i2 <<= 1;
        }
        int i4 = i3 * i2 * 4;
        if (h == null || h.capacity() < i4) {
            int i5 = 4194304;
            if (i4 >= 4194304) {
                i5 = i4;
            }
            h = ByteBuffer.allocateDirect(i5);
            h.order(ByteOrder.nativeOrder());
            i = h.asIntBuffer();
        }
        h.rewind();
        bitmap.copyPixelsToBuffer(h);
        bitmap.recycle();
        a aVar = new a();
        this.p.add(aVar);
        return aVar.a(i, width, height);
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        this.p.remove(aVar);
        k[0] = aVar.b;
        GLES20.glDeleteTextures(1, k, 0);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        d();
        b();
    }

    /* access modifiers changed from: package-private */
    public a a(int i2) {
        for (int i3 = 0; i3 < this.p.size(); i3++) {
            a aVar = this.p.get(i3);
            if (aVar.b == i2) {
                return aVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        int i2;
        if (this.v != 0) {
            this.z.g();
            if ((this.q & 1) != 0) {
                int i3 = 771;
                switch ((this.q >> 8) & 15) {
                    case 0:
                        i2 = 0;
                        break;
                    case 2:
                        i2 = 770;
                        break;
                    case 3:
                        i2 = 771;
                        break;
                    default:
                        i2 = 1;
                        break;
                }
                switch ((this.q >> 12) & 15) {
                    case 1:
                        i3 = 1;
                        break;
                    case 2:
                        i3 = 770;
                        break;
                    case 3:
                        break;
                    default:
                        i3 = 0;
                        break;
                }
                GLES20.glBlendFunc(i2, i3);
                GLES20.glEnable(3042);
            } else {
                GLES20.glDisable(3042);
            }
            if (this.u != null) {
                GLES20.glEnable(3553);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.u.a);
                if ((this.q & 12) != 0) {
                    GLES20.glTexParameterf(3553, 10242, 10497.0f);
                    GLES20.glTexParameterf(3553, 10243, 10497.0f);
                } else {
                    GLES20.glTexParameterf(3553, 10242, 33071.0f);
                    GLES20.glTexParameterf(3553, 10243, 33071.0f);
                }
                if ((this.q & 16) != 0) {
                    GLES20.glTexParameterf(3553, 10241, 9728.0f);
                    GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9728.0f);
                } else {
                    GLES20.glTexParameterf(3553, 10241, 9729.0f);
                    GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
                }
                int i4 = this.q & 16711680;
                if (i4 == 65536) {
                    this.C.a();
                } else if (i4 == 131072) {
                    this.D.a();
                } else if (i4 != 196608) {
                    this.B.a();
                } else if (this.s) {
                    this.E.a();
                } else if (this.t) {
                    this.B.a();
                } else {
                    this.F.a();
                }
            } else {
                GLES20.glDisable(3553);
                this.A.a();
            }
            switch (this.r) {
                case 1:
                    this.A.a();
                    GLES20.glDrawArrays(1, 0, this.v);
                    break;
                case 2:
                    GLES20.glDrawArrays(4, 0, this.v);
                    break;
            }
            this.v = 0;
            this.w.rewind();
            this.x.rewind();
            this.y.rewind();
            this.s = true;
            this.t = true;
        }
    }

    /* access modifiers changed from: package-private */
    public a a(String str) {
        d();
        j.inScaled = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, j);
        if (decodeFile == null) {
            PrintStream printStream = System.out;
            printStream.println("Failed to load " + str);
            decodeFile = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
        }
        return a(decodeFile);
    }

    /* access modifiers changed from: package-private */
    public a a(InputStream inputStream) {
        d();
        j.inScaled = false;
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, j);
        if (decodeStream == null) {
            Log.w("ADC3", "Failed to decode input stream.");
            decodeStream = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
        }
        return a(decodeStream);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        System.out.println("ADCRenderer on_surface_created()");
        this.A = new ap.a(this);
        this.B = new ap.b(this);
        this.C = new ap.d(this);
        this.D = new ap.c(this);
        this.E = new ap.e(this);
        this.F = new ap.f(this);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        PrintStream printStream = System.out;
        printStream.println("ADCRenderer on_surface_changed " + i2 + AvidJSONUtil.KEY_X + i3);
        this.l = i2;
        this.m = i3;
        this.n = i2;
        this.o = i3;
        GLES20.glViewport(0, 0, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, int i5) {
        d();
        double d2 = (double) this.l;
        double d3 = (double) this.n;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        double d5 = (double) this.m;
        double d6 = (double) this.o;
        Double.isNaN(d5);
        Double.isNaN(d6);
        double d7 = d5 / d6;
        double d8 = (double) i4;
        Double.isNaN(d8);
        int i6 = (int) (d8 * d4);
        double d9 = (double) i5;
        Double.isNaN(d9);
        int i7 = (int) (d9 * d7);
        double d10 = (double) i2;
        Double.isNaN(d10);
        int i8 = (int) (d10 * d4);
        int i9 = this.m;
        double d11 = (double) i3;
        Double.isNaN(d11);
        GLES20.glScissor(i8, i9 - (((int) (d11 * d7)) + i7), i6, i7);
        GLES20.glEnable(3089);
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (this.r != 1) {
            d();
            this.r = 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, double d2) {
        this.n = i2;
        this.o = i3;
        this.z.a(this.z.d.a(i2, i3, d2));
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        if (i2 != this.q) {
            d();
            this.q = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i2, int i3, int i4, int i5) {
        b(i2 | 2 | (i3 << 8) | (i4 << 12) | (i5 << 16));
    }

    /* access modifiers changed from: package-private */
    public void b(a aVar) {
        if (aVar != this.u) {
            d();
            this.u = aVar;
        }
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (this.r != 2) {
            d();
            this.r = 2;
        }
    }

    class a {
        int a = an.k[0];
        int b;
        int c;
        int d;
        int e;
        int f;
        double g;
        double h;

        a() {
            int i2 = an.g;
            an.g = i2 + 1;
            this.b = i2;
            GLES20.glGenTextures(1, an.k, 0);
        }

        /* access modifiers changed from: package-private */
        public a a(IntBuffer intBuffer, int i2, int i3) {
            int i4 = 1;
            while (i4 < i2) {
                i4 <<= 1;
            }
            int i5 = 1;
            while (i5 < i3) {
                i5 <<= 1;
            }
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            double d2 = (double) this.c;
            double d3 = (double) this.e;
            Double.isNaN(d2);
            Double.isNaN(d3);
            this.g = d2 / d3;
            double d4 = (double) this.d;
            double d5 = (double) this.f;
            Double.isNaN(d4);
            Double.isNaN(d5);
            this.h = d4 / d5;
            intBuffer.rewind();
            int i6 = i2 * i3;
            while (true) {
                i6--;
                if (i6 < 0) {
                    break;
                }
                int i7 = intBuffer.get(i6);
                intBuffer.put(i6, ((i7 << 16) & 16711680) | (-16711936 & i7) | ((i7 >> 16) & 255));
            }
            intBuffer.rewind();
            if (this.c < this.e) {
                int i8 = ((this.d - 1) * this.e) + this.c;
                int i9 = this.c * this.d;
                int i10 = this.d;
                int i11 = this.e - this.c;
                while (true) {
                    i10--;
                    if (i10 < 0) {
                        break;
                    }
                    intBuffer.put(i8, intBuffer.get(i9 - 1));
                    int i12 = this.c;
                    while (true) {
                        i12--;
                        if (i12 < 0) {
                            break;
                        }
                        i8--;
                        i9--;
                        intBuffer.put(i8, intBuffer.get(i9));
                    }
                    i8 -= i11;
                }
            }
            intBuffer.rewind();
            if (this.d < this.f) {
                int i13 = this.d * this.e;
                int i14 = this.e + i13;
                int i15 = this.e;
                while (true) {
                    i15--;
                    if (i15 < 0) {
                        break;
                    }
                    i14--;
                    i13--;
                    intBuffer.put(i14, intBuffer.get(i13));
                }
            }
            GLES20.glBindTexture(3553, this.a);
            intBuffer.rewind();
            GLES20.glTexImage2D(3553, 0, 6408, this.e, this.f, 0, 6408, 5121, intBuffer);
            System.out.println("ADC3 Texture::set gl_texture_id:" + this.a + " texture_id:" + this.b + " w:" + i2 + " h:" + i3);
            return this;
        }
    }
}
