package com.tapdaq.adapters.tapdaq.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class RectButton extends Button {
    private int mColor = -1;

    public RectButton(Context context) {
        super(context);
    }

    public RectButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RectButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public RectButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        makeRoundCorner(this.mColor, 5, this, 2, -3355444);
    }

    public void makeRoundCorner(int i, int i2, View view, int i3, int i4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius((float) i2);
        gradientDrawable.setStroke(i3, i4);
        view.setBackgroundDrawable(gradientDrawable);
    }
}
