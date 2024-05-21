package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.applovin.impl.adview.f;
import com.applovin.impl.sdk.j;

@SuppressLint({"ViewConstructor"})
public final class n extends f {
    private static final Paint d = new Paint(1);
    private static final Paint e = new Paint(1);
    private float c = 1.0f;

    public n(j jVar, Context context) {
        super(jVar, context);
        d.setARGB(80, 0, 0, 0);
        e.setColor(-1);
        e.setStyle(Paint.Style.STROKE);
    }

    public void a(int i) {
        setViewScale(((float) i) / 30.0f);
    }

    /* access modifiers changed from: protected */
    public float getCenter() {
        return getSize() / 2.0f;
    }

    /* access modifiers changed from: protected */
    public float getCrossOffset() {
        return this.c * 8.0f;
    }

    /* access modifiers changed from: protected */
    public float getInnerCircleOffset() {
        return this.c * 2.0f;
    }

    /* access modifiers changed from: protected */
    public float getInnerCircleRadius() {
        return getCenter() - getInnerCircleOffset();
    }

    /* access modifiers changed from: protected */
    public float getSize() {
        return this.c * 30.0f;
    }

    /* access modifiers changed from: protected */
    public float getStrokeWidth() {
        return this.c * 2.0f;
    }

    public f.a getStyle() {
        return f.a.WhiteXOnTransparentGrey;
    }

    public float getViewScale() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float center = getCenter();
        canvas.drawCircle(center, center, center, d);
        float crossOffset = getCrossOffset();
        float size = getSize() - crossOffset;
        e.setStrokeWidth(getStrokeWidth());
        Canvas canvas2 = canvas;
        float f = crossOffset;
        float f2 = size;
        canvas2.drawLine(f, crossOffset, f2, size, e);
        canvas2.drawLine(f, size, f2, crossOffset, e);
    }

    public void setViewScale(float f) {
        this.c = f;
    }
}
