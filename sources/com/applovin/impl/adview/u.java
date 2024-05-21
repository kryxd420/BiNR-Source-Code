package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import com.applovin.impl.adview.f;
import com.applovin.impl.sdk.j;

@SuppressLint({"ViewConstructor"})
public final class u extends f {
    private static final Paint d = new Paint(1);
    private static final Paint e = new Paint(1);
    private static final Paint f = new Paint(1);
    private float c = 1.0f;

    public u(j jVar, Context context) {
        super(jVar, context);
        d.setColor(-1);
        e.setColor(ViewCompat.MEASURED_STATE_MASK);
        f.setColor(-1);
        f.setStyle(Paint.Style.STROKE);
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
        return this.c * 10.0f;
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
        return this.c * 3.0f;
    }

    public f.a getStyle() {
        return f.a.WhiteXOnOpaqueBlack;
    }

    public float getViewScale() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float center = getCenter();
        canvas.drawCircle(center, center, center, d);
        canvas.drawCircle(center, center, getInnerCircleRadius(), e);
        float crossOffset = getCrossOffset();
        float size = getSize() - crossOffset;
        f.setStrokeWidth(getStrokeWidth());
        Canvas canvas2 = canvas;
        float f2 = crossOffset;
        float f3 = size;
        canvas2.drawLine(f2, crossOffset, f3, size, f);
        canvas2.drawLine(f2, size, f3, crossOffset, f);
    }

    public void setViewScale(float f2) {
        this.c = f2;
    }
}
