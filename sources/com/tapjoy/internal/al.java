package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Matrix;
import android.view.MotionEvent;

public class al extends am {
    private int a = 0;
    private final Matrix b = new Matrix();
    private final float[] c = new float[2];

    public al(Context context) {
        super(context);
    }

    public int getRotationCount() {
        return this.a;
    }

    public void setRotationCount(int i) {
        this.a = i & 3;
    }

    public void onMeasure(int i, int i2) {
        if (this.a % 2 == 0) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:5|6|7|8|(1:12)|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchDraw(android.graphics.Canvas r13) {
        /*
            r12 = this;
            int r0 = r12.a
            if (r0 != 0) goto L_0x0008
            super.dispatchDraw(r13)
            return
        L_0x0008:
            r13.save()
            int r0 = r12.getWidth()
            int r1 = r12.getHeight()
            r3 = 0
            r4 = 0
            float r5 = (float) r0
            float r6 = (float) r1
            android.graphics.Region$Op r7 = android.graphics.Region.Op.REPLACE
            r2 = r13
            r2.clipRect(r3, r4, r5, r6, r7)
            android.view.ViewParent r2 = r12.getParent()     // Catch:{ Exception -> 0x005c }
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2     // Catch:{ Exception -> 0x005c }
            android.view.ViewParent r3 = r2.getParent()     // Catch:{ Exception -> 0x0032 }
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3     // Catch:{ Exception -> 0x0032 }
            boolean r4 = r3 instanceof android.widget.ScrollView     // Catch:{ Exception -> 0x0032 }
            if (r4 != 0) goto L_0x0031
            boolean r4 = r3 instanceof android.widget.HorizontalScrollView     // Catch:{ Exception -> 0x0032 }
            if (r4 == 0) goto L_0x0032
        L_0x0031:
            r2 = r3
        L_0x0032:
            int r3 = r12.getLeft()     // Catch:{ Exception -> 0x005c }
            int r4 = r2.getScrollX()     // Catch:{ Exception -> 0x005c }
            int r3 = r3 - r4
            int r4 = r12.getTop()     // Catch:{ Exception -> 0x005c }
            int r5 = r2.getScrollY()     // Catch:{ Exception -> 0x005c }
            int r4 = r4 - r5
            int r5 = 0 - r3
            float r7 = (float) r5     // Catch:{ Exception -> 0x005c }
            int r5 = 0 - r4
            float r8 = (float) r5     // Catch:{ Exception -> 0x005c }
            int r5 = r2.getWidth()     // Catch:{ Exception -> 0x005c }
            int r5 = r5 - r3
            float r9 = (float) r5     // Catch:{ Exception -> 0x005c }
            int r2 = r2.getHeight()     // Catch:{ Exception -> 0x005c }
            int r2 = r2 - r4
            float r10 = (float) r2     // Catch:{ Exception -> 0x005c }
            android.graphics.Region$Op r11 = android.graphics.Region.Op.INTERSECT     // Catch:{ Exception -> 0x005c }
            r6 = r13
            r6.clipRect(r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x005c }
        L_0x005c:
            int r2 = r12.a
            int r2 = r2 * 90
            float r2 = (float) r2
            r13.rotate(r2)
            int r2 = r12.a
            r3 = 0
            switch(r2) {
                case 1: goto L_0x007e;
                case 2: goto L_0x0076;
                case 3: goto L_0x0070;
                default: goto L_0x006a;
            }
        L_0x006a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            r13.<init>()
            throw r13
        L_0x0070:
            int r2 = -r1
            float r2 = (float) r2
            r13.translate(r2, r3)
            goto L_0x0083
        L_0x0076:
            int r2 = -r0
            float r2 = (float) r2
            int r4 = -r1
            float r4 = (float) r4
            r13.translate(r2, r4)
            goto L_0x0083
        L_0x007e:
            int r2 = -r0
            float r2 = (float) r2
            r13.translate(r3, r2)
        L_0x0083:
            android.graphics.Matrix r2 = r12.b
            int r4 = r12.a
            int r4 = r4 * -90
            float r4 = (float) r4
            r2.setRotate(r4)
            int r2 = r12.a
            switch(r2) {
                case 1: goto L_0x00ad;
                case 2: goto L_0x00a1;
                case 3: goto L_0x0098;
                default: goto L_0x0092;
            }
        L_0x0092:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            r13.<init>()
            throw r13
        L_0x0098:
            android.graphics.Matrix r0 = r12.b
            int r1 = r1 + -1
            float r1 = (float) r1
            r0.postTranslate(r1, r3)
            goto L_0x00b5
        L_0x00a1:
            android.graphics.Matrix r2 = r12.b
            int r0 = r0 + -1
            float r0 = (float) r0
            int r1 = r1 + -1
            float r1 = (float) r1
            r2.postTranslate(r0, r1)
            goto L_0x00b5
        L_0x00ad:
            android.graphics.Matrix r1 = r12.b
            int r0 = r0 + -1
            float r0 = (float) r0
            r1.postTranslate(r3, r0)
        L_0x00b5:
            super.dispatchDraw(r13)
            r13.restore()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.al.dispatchDraw(android.graphics.Canvas):void");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a == 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float[] fArr = this.c;
        fArr[0] = motionEvent.getX();
        fArr[1] = motionEvent.getY();
        this.b.mapPoints(fArr);
        motionEvent.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(motionEvent);
    }
}
