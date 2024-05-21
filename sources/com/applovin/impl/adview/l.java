package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.content.Context;
import com.applovin.impl.adview.f;
import com.applovin.impl.sdk.j;

@SuppressLint({"ViewConstructor"})
public final class l extends f {
    private float c = 1.0f;

    public l(j jVar, Context context) {
        super(jVar, context);
    }

    public void a(int i) {
        setViewScale(((float) i) / 30.0f);
    }

    public f.a getStyle() {
        return f.a.Invisible;
    }

    public float getViewScale() {
        return this.c;
    }

    public void setViewScale(float f) {
        this.c = f;
    }
}
