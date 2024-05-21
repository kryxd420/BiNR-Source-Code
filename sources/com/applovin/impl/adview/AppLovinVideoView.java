package com.applovin.impl.adview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.j;

public class AppLovinVideoView extends VideoView implements q {
    private final j a;
    private int b = 0;
    private int c = 0;
    private float d = 0.0f;

    public AppLovinVideoView(Context context, j jVar) {
        super(context, (AttributeSet) null, 0);
        this.a = jVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.b <= 0 || this.c <= 0 || ((Boolean) this.a.a(b.fd)).booleanValue()) {
            super.onMeasure(i, i2);
            return;
        }
        int defaultSize = getDefaultSize(this.b, i);
        int defaultSize2 = getDefaultSize(this.c, i2);
        int i3 = (int) (((float) defaultSize) / this.d);
        if (defaultSize2 > i3) {
            defaultSize2 = i3;
        }
        int i4 = (int) (((float) defaultSize2) * this.d);
        if (defaultSize > i4) {
            defaultSize = i4;
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public void setVideoSize(int i, int i2) {
        this.b = i;
        this.c = i2;
        this.d = ((float) this.b) / ((float) this.c);
        try {
            getHolder().setFixedSize(i, i2);
            requestLayout();
            invalidate();
        } catch (Exception unused) {
        }
    }
}
