package com.tapdaq.adapters.tapdaq.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.widget.Button;

public class CloseButtonCircle extends Button {
    private int mRadius;

    public CloseButtonCircle(Context context, int i) {
        super(context);
        this.mRadius = i;
        setBackgroundColor(0);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float) (this.mRadius / 2), (float) (this.mRadius / 2), (float) (this.mRadius / 2), paint);
        Paint paint2 = new Paint();
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint2.setStrokeWidth(4.0f);
        double d = (double) this.mRadius;
        Double.isNaN(d);
        float f = (float) ((int) (d * 0.25d));
        double d2 = (double) this.mRadius;
        Double.isNaN(d2);
        float f2 = (float) ((int) (d2 * 0.25d));
        double d3 = (double) this.mRadius;
        Double.isNaN(d3);
        float f3 = (float) ((int) (d3 * 0.75d));
        double d4 = (double) this.mRadius;
        Double.isNaN(d4);
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        canvas2.drawLine(f, f2, f3, (float) ((int) (d4 * 0.75d)), paint3);
        double d5 = (double) this.mRadius;
        Double.isNaN(d5);
        float f4 = (float) ((int) (d5 * 0.25d));
        double d6 = (double) this.mRadius;
        Double.isNaN(d6);
        float f5 = (float) ((int) (d6 * 0.75d));
        double d7 = (double) this.mRadius;
        Double.isNaN(d7);
        float f6 = (float) ((int) (d7 * 0.75d));
        double d8 = (double) this.mRadius;
        Double.isNaN(d8);
        canvas2.drawLine(f4, f5, f6, (float) ((int) (d8 * 0.25d)), paint3);
    }
}
