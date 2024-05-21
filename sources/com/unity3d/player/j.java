package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

public final class j extends View {
    final int a;
    final int b = getResources().getIdentifier("unity_static_splash", "drawable", getContext().getPackageName());
    Bitmap c;
    Bitmap d;

    /* renamed from: com.unity3d.player.j$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                int[] r0 = com.unity3d.player.j.a.a()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                r0 = 1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.unity3d.player.j.a.a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.unity3d.player.j.a.b     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.unity3d.player.j.a.c     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r0 = 3
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.j.AnonymousClass1.<clinit>():void");
        }
    }

    enum a {
        ;

        static {
            d = new int[]{a, b, c};
        }

        public static int[] a() {
            return (int[]) d.clone();
        }
    }

    public j(Context context, int i) {
        super(context);
        this.a = i;
        if (this.b != 0) {
            forceLayout();
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        if (r12 < r10) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
        r8 = (int) (r2 * r0);
        r10 = r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        /*
            r7 = this;
            int r8 = r7.b
            if (r8 != 0) goto L_0x0005
            return
        L_0x0005:
            android.graphics.Bitmap r8 = r7.c
            r9 = 0
            if (r8 != 0) goto L_0x001d
            android.graphics.BitmapFactory$Options r8 = new android.graphics.BitmapFactory$Options
            r8.<init>()
            r8.inScaled = r9
            android.content.res.Resources r10 = r7.getResources()
            int r11 = r7.b
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeResource(r10, r11, r8)
            r7.c = r8
        L_0x001d:
            android.graphics.Bitmap r8 = r7.c
            int r8 = r8.getWidth()
            android.graphics.Bitmap r10 = r7.c
            int r10 = r10.getHeight()
            int r11 = r7.getWidth()
            int r12 = r7.getHeight()
            if (r11 == 0) goto L_0x00cc
            if (r12 != 0) goto L_0x0037
            goto L_0x00cc
        L_0x0037:
            float r0 = (float) r8
            float r1 = (float) r10
            float r0 = r0 / r1
            float r1 = (float) r11
            float r2 = (float) r12
            float r3 = r1 / r2
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            r4 = 1
            if (r3 > 0) goto L_0x0045
            r3 = 1
            goto L_0x0046
        L_0x0045:
            r3 = 0
        L_0x0046:
            int[] r5 = com.unity3d.player.j.AnonymousClass1.a
            int r6 = r7.a
            int r6 = r6 - r4
            r5 = r5[r6]
            switch(r5) {
                case 1: goto L_0x0061;
                case 2: goto L_0x0051;
                case 3: goto L_0x0051;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x006c
        L_0x0051:
            int r8 = r7.a
            int r10 = com.unity3d.player.j.a.c
            if (r8 != r10) goto L_0x0059
            r8 = 1
            goto L_0x005a
        L_0x0059:
            r8 = 0
        L_0x005a:
            r8 = r8 ^ r3
            if (r8 == 0) goto L_0x0068
            float r1 = r1 / r0
            int r10 = (int) r1
            r8 = r11
            goto L_0x006c
        L_0x0061:
            if (r11 >= r8) goto L_0x0066
            float r1 = r1 / r0
            int r10 = (int) r1
            r8 = r11
        L_0x0066:
            if (r12 >= r10) goto L_0x006c
        L_0x0068:
            float r2 = r2 * r0
            int r8 = (int) r2
            r10 = r12
        L_0x006c:
            android.graphics.Bitmap r11 = r7.d
            if (r11 == 0) goto L_0x008f
            android.graphics.Bitmap r11 = r7.d
            int r11 = r11.getWidth()
            if (r11 != r8) goto L_0x0081
            android.graphics.Bitmap r11 = r7.d
            int r11 = r11.getHeight()
            if (r11 != r10) goto L_0x0081
            return
        L_0x0081:
            android.graphics.Bitmap r11 = r7.d
            android.graphics.Bitmap r12 = r7.c
            if (r11 == r12) goto L_0x008f
            android.graphics.Bitmap r11 = r7.d
            r11.recycle()
            r11 = 0
            r7.d = r11
        L_0x008f:
            android.graphics.Bitmap r11 = r7.c
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createScaledBitmap(r11, r8, r10, r4)
            r7.d = r8
            android.graphics.Bitmap r8 = r7.d
            android.content.res.Resources r10 = r7.getResources()
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            int r10 = r10.densityDpi
            r8.setDensity(r10)
            android.graphics.drawable.ColorDrawable r8 = new android.graphics.drawable.ColorDrawable
            r10 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r8.<init>(r10)
            android.graphics.drawable.BitmapDrawable r10 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r11 = r7.getResources()
            android.graphics.Bitmap r12 = r7.d
            r10.<init>(r11, r12)
            r11 = 17
            r10.setGravity(r11)
            android.graphics.drawable.LayerDrawable r11 = new android.graphics.drawable.LayerDrawable
            r12 = 2
            android.graphics.drawable.Drawable[] r12 = new android.graphics.drawable.Drawable[r12]
            r12[r9] = r8
            r12[r4] = r10
            r11.<init>(r12)
            r7.setBackground(r11)
        L_0x00cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.j.onLayout(boolean, int, int, int, int):void");
    }
}
