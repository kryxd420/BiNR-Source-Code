package com.adcolony.sdk;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

class v implements GLSurfaceView.Renderer {
    static BitmapFactory.Options a = new BitmapFactory.Options();
    static ByteBuffer b;
    u c;
    String d;
    int e;
    int f;
    boolean g;
    boolean h;
    ArrayList<af> i = new ArrayList<>();
    c j;
    int k;
    int l;
    ArrayList<a> m = new ArrayList<>();
    HashMap<Integer, a> n = new HashMap<>();

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
    }

    v(u uVar, boolean z, String str) {
        System.out.println("AdColony new ADCGLViewRenderer");
        this.c = uVar;
        this.g = z;
        this.d = str;
        this.j = a.a().m().b().get(str);
        this.e = uVar.b;
        this.f = this.j.c();
        this.j.n().add(a.a("RenderView.create_image", (ah) new ah() {
            public void a(af afVar) {
                v.this.a(afVar);
            }
        }, true));
        this.j.n().add(a.a("RenderView.load_texture", (ah) new ah() {
            public void a(af afVar) {
                v.this.b(afVar);
            }
        }, true));
        this.j.o().add("RenderView.create_image");
        this.j.o().add("RenderView.load_texture");
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        if (!this.h) {
            this.h = true;
            synchronized (ADCNative.lock) {
                ADCNative.nativeDeleteSceneController(this.f, this.e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        a();
    }

    public synchronized void onDrawFrame(GL10 gl10) {
        a.f();
        synchronized (ADCNative.lock) {
            if (!this.h) {
                b();
                ADCNative.nativeRender(this.f, this.e, this.k, this.l);
            }
        }
    }

    public synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        synchronized (ADCNative.lock) {
            if (!this.h) {
                ADCNative.nativeCreateSceneController(this.f, this.e);
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        this.k = i2;
        this.l = i3;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(af afVar) {
        this.i.add(afVar);
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            af afVar = this.i.get(i2);
            JSONObject c2 = afVar.c();
            a aVar = null;
            if (c2.has("pixels")) {
                String b2 = y.b(c2, "pixels");
                int[] iArr = new int[(b2.length() / 4)];
                int length = b2.length() - 4;
                int length2 = iArr.length;
                while (length >= 0) {
                    char charAt = b2.charAt(length);
                    char charAt2 = b2.charAt(length + 1);
                    char charAt3 = b2.charAt(length + 2);
                    length += 4;
                    length2--;
                    iArr[length2] = (charAt << 24) | (charAt2 << 16) | (charAt3 << 8) | b2.charAt(length + 3);
                }
                int c3 = y.c(c2, AvidJSONUtil.KEY_WIDTH);
                int c4 = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
                if (c3 * c4 == iArr.length) {
                    aVar = a(y.c(c2, "texture_id"), y.b(c2, "filepath"), iArr, c3, c4);
                }
            } else if (c2.has("bytes")) {
                String b3 = y.b(c2, "bytes");
                byte[] bArr = new byte[b3.length()];
                int length3 = b3.length();
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        break;
                    }
                    bArr[length3] = (byte) b3.charAt(length3);
                }
                aVar = a(y.c(c2, "texture_id"), y.b(c2, "filepath"), bArr);
            } else if (c2.has("filepath")) {
                aVar = a(y.c(c2, "texture_id"), y.b(c2, "filepath"));
            }
            if (aVar == null) {
                y.a(c2, "success", false);
                return;
            }
            y.a(c2, "success", aVar.d);
            y.b(c2, "image_width", aVar.f);
            y.b(c2, "image_height", aVar.g);
            y.b(c2, "texture_width", aVar.h);
            y.b(c2, "texture_height", aVar.i);
            afVar.a(c2).b();
        }
        this.i.clear();
    }

    /* access modifiers changed from: package-private */
    public a a(int i2, String str) {
        a.inScaled = false;
        Activity c2 = a.c();
        Bitmap bitmap = null;
        if (!str.startsWith("file:///android_asset/") || c2 == null) {
            bitmap = BitmapFactory.decodeFile(str, a);
        } else {
            try {
                bitmap = BitmapFactory.decodeStream(c2.getAssets().open(str.substring("file:///android_asset/".length())), (Rect) null, a);
            } catch (IOException e2) {
                new aa.a().a(e2.toString()).a(aa.f);
            }
        }
        if (bitmap != null) {
            return a(i2, str, bitmap);
        }
        new aa.a().a("Failed to load ").a(str).a(" - using white ").a("16x16 as placeholder.").a(aa.f);
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(-1);
        a a2 = a(i2, str, createBitmap);
        a2.d = false;
        return a2;
    }

    /* access modifiers changed from: package-private */
    public a a(int i2, String str, byte[] bArr) {
        a.inScaled = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            return a(i2, str, decodeByteArray);
        }
        new aa.a().a("Failed to load ").a(str).a(" bytes - using ").a("white 16x16 as placeholder.").a(aa.f);
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(-1);
        a a2 = a(i2, str, createBitmap);
        a2.d = false;
        return a2;
    }

    /* access modifiers changed from: package-private */
    public a a(int i2, String str, int[] iArr, int i3, int i4) {
        return a(i2, str, Bitmap.createBitmap(iArr, i3, i4, Bitmap.Config.ARGB_8888));
    }

    /* access modifiers changed from: package-private */
    public a a(int i2, String str, Bitmap bitmap) {
        a aVar = new a(i2, str, bitmap.getWidth(), bitmap.getHeight());
        int i3 = aVar.h * aVar.i * 4;
        if (b == null || b.capacity() < i3) {
            if (i3 < 4194304) {
                i3 = 4194304;
            }
            b = ByteBuffer.allocateDirect(i3);
            b.order(ByteOrder.nativeOrder());
        }
        b.rewind();
        bitmap.copyPixelsToBuffer(b);
        this.m.add(aVar);
        this.n.put(Integer.valueOf(i2), aVar);
        synchronized (ADCNative.lock) {
            ADCNative.nativeCreateTexture(this.f, this.e, i2, str, b, aVar.f, aVar.g, aVar.h, aVar.i);
        }
        return aVar;
    }

    static class a {
        static int a = 1;
        int b;
        String c;
        boolean d = true;
        int e;
        int f;
        int g;
        int h;
        int i;

        a(int i2, String str, int i3, int i4) {
            this.b = i2;
            this.e = this.e;
            this.c = str;
            this.f = i3;
            this.g = i4;
            this.h = 1;
            while (this.h < i3) {
                this.h <<= 1;
            }
            this.i = 1;
            while (this.i < i4) {
                this.i <<= 1;
            }
        }
    }
}
