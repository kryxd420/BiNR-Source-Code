package com.tapdaq.adapters.tapdaq.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Button;

public class CloseButton extends Button {
    private final int h;
    private int mStrokeColor = -1;
    private final int mStrokeWidth;
    private final int w;

    public CloseButton(Context context, int i, int i2) {
        super(context);
        this.w = i;
        this.h = i2;
        setWidth(i);
        setHeight(i2);
        this.mStrokeWidth = 4;
        setBackgroundColor(0);
    }

    public CloseButton(Context context, int i, int i2, int i3) {
        super(context);
        this.w = i;
        this.h = i2;
        setWidth(i);
        setHeight(i2);
        this.mStrokeWidth = i3;
        setBackgroundColor(0);
    }

    public void setColor(int i) {
        this.mStrokeColor = i;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(this.mStrokeColor);
        paint.setStrokeWidth((float) this.mStrokeWidth);
        Paint paint2 = paint;
        canvas.drawLine(0.0f, 0.0f, (float) this.w, (float) this.h, paint2);
        canvas.drawLine(0.0f, (float) this.h, (float) this.w, 0.0f, paint2);
    }
}
