package com.applovin.impl.adview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class e extends View {
    private static final int s = Color.rgb(66, 145, 241);
    private static final int t = Color.rgb(66, 145, 241);
    private static final int u = Color.rgb(66, 145, 241);
    protected Paint a;
    protected Paint b;
    private Paint c;
    private Paint d;
    private RectF e;
    private float f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private float l;
    private int m;
    private String n;
    private String o;
    private float p;
    private String q;
    private float r;
    private final float v;
    private final float w;
    private final float x;
    private final int y;

    private static class a {
        /* access modifiers changed from: private */
        public static float c(Resources resources, float f) {
            return (f * resources.getDisplayMetrics().density) + 0.5f;
        }

        /* access modifiers changed from: private */
        public static float d(Resources resources, float f) {
            return f * resources.getDisplayMetrics().scaledDensity;
        }
    }

    public e(Context context) {
        this(context, (AttributeSet) null);
    }

    public e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public e(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new RectF();
        this.i = 0;
        this.n = "";
        this.o = "";
        this.q = "";
        this.w = a.d(getResources(), 14.0f);
        this.y = (int) a.c(getResources(), 100.0f);
        this.v = a.c(getResources(), 4.0f);
        this.x = a.d(getResources(), 18.0f);
        b();
        a();
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        int i3 = this.y;
        return mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
    }

    private float getProgressAngle() {
        return (((float) getProgress()) / ((float) this.j)) * 360.0f;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.a = new TextPaint();
        this.a.setColor(this.g);
        this.a.setTextSize(this.f);
        this.a.setAntiAlias(true);
        this.b = new TextPaint();
        this.b.setColor(this.h);
        this.b.setTextSize(this.p);
        this.b.setAntiAlias(true);
        this.c = new Paint();
        this.c.setColor(this.k);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(this.l);
        this.d = new Paint();
        this.d.setColor(this.m);
        this.d.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.k = s;
        this.g = t;
        this.f = this.w;
        setMax(100);
        setProgress(0);
        this.l = this.v;
        this.m = 0;
        this.p = this.x;
        this.h = u;
    }

    public int getFinishedStrokeColor() {
        return this.k;
    }

    public float getFinishedStrokeWidth() {
        return this.l;
    }

    public int getInnerBackgroundColor() {
        return this.m;
    }

    public String getInnerBottomText() {
        return this.q;
    }

    public int getInnerBottomTextColor() {
        return this.h;
    }

    public float getInnerBottomTextSize() {
        return this.p;
    }

    public int getMax() {
        return this.j;
    }

    public String getPrefixText() {
        return this.n;
    }

    public int getProgress() {
        return this.i;
    }

    public String getSuffixText() {
        return this.o;
    }

    public int getTextColor() {
        return this.g;
    }

    public float getTextSize() {
        return this.f;
    }

    public void invalidate() {
        a();
        super.invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = this.l;
        this.e.set(f2, f2, ((float) getWidth()) - f2, ((float) getHeight()) - f2);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - this.l) + this.l) / 2.0f, this.d);
        canvas.drawArc(this.e, 270.0f, -getProgressAngle(), false, this.c);
        String str = this.n + this.i + this.o;
        if (!TextUtils.isEmpty(str)) {
            canvas.drawText(str, (((float) getWidth()) - this.a.measureText(str)) / 2.0f, (((float) getWidth()) - (this.a.descent() + this.a.ascent())) / 2.0f, this.a);
        }
        if (!TextUtils.isEmpty(getInnerBottomText())) {
            this.b.setTextSize(this.p);
            canvas.drawText(getInnerBottomText(), (((float) getWidth()) - this.b.measureText(getInnerBottomText())) / 2.0f, (((float) getHeight()) - this.r) - ((this.a.descent() + this.a.ascent()) / 2.0f), this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(a(i2), a(i3));
        this.r = (float) (getHeight() - ((getHeight() * 3) / 4));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.g = bundle.getInt("text_color");
            this.f = bundle.getFloat("text_size");
            this.p = bundle.getFloat("inner_bottom_text_size");
            this.q = bundle.getString("inner_bottom_text");
            this.h = bundle.getInt("inner_bottom_text_color");
            this.k = bundle.getInt("finished_stroke_color");
            this.l = bundle.getFloat("finished_stroke_width");
            this.m = bundle.getInt("inner_background_color");
            a();
            setMax(bundle.getInt("max"));
            setProgress(bundle.getInt(NotificationCompat.CATEGORY_PROGRESS));
            this.n = bundle.getString("prefix");
            this.o = bundle.getString("suffix");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("text_size", getTextSize());
        bundle.putFloat("inner_bottom_text_size", getInnerBottomTextSize());
        bundle.putFloat("inner_bottom_text_color", (float) getInnerBottomTextColor());
        bundle.putString("inner_bottom_text", getInnerBottomText());
        bundle.putInt("inner_bottom_text_color", getInnerBottomTextColor());
        bundle.putInt("finished_stroke_color", getFinishedStrokeColor());
        bundle.putInt("max", getMax());
        bundle.putInt(NotificationCompat.CATEGORY_PROGRESS, getProgress());
        bundle.putString("suffix", getSuffixText());
        bundle.putString("prefix", getPrefixText());
        bundle.putFloat("finished_stroke_width", getFinishedStrokeWidth());
        bundle.putInt("inner_background_color", getInnerBackgroundColor());
        return bundle;
    }

    public void setFinishedStrokeColor(int i2) {
        this.k = i2;
        invalidate();
    }

    public void setFinishedStrokeWidth(float f2) {
        this.l = f2;
        invalidate();
    }

    public void setInnerBackgroundColor(int i2) {
        this.m = i2;
        invalidate();
    }

    public void setInnerBottomText(String str) {
        this.q = str;
        invalidate();
    }

    public void setInnerBottomTextColor(int i2) {
        this.h = i2;
        invalidate();
    }

    public void setInnerBottomTextSize(float f2) {
        this.p = f2;
        invalidate();
    }

    public void setMax(int i2) {
        if (i2 > 0) {
            this.j = i2;
            invalidate();
        }
    }

    public void setPrefixText(String str) {
        this.n = str;
        invalidate();
    }

    public void setProgress(int i2) {
        this.i = i2;
        if (this.i > getMax()) {
            this.i %= getMax();
        }
        invalidate();
    }

    public void setSuffixText(String str) {
        this.o = str;
        invalidate();
    }

    public void setTextColor(int i2) {
        this.g = i2;
        invalidate();
    }

    public void setTextSize(float f2) {
        this.f = f2;
        invalidate();
    }
}
