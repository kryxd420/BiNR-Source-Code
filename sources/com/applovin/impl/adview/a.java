package com.applovin.impl.adview;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.sdk.AppLovinSdkUtils;

public class a extends RelativeLayout {
    private final ProgressBar a;

    public a(Activity activity, int i, int i2) {
        super(activity);
        RelativeLayout.LayoutParams layoutParams;
        setClickable(false);
        this.a = new ProgressBar(activity, (AttributeSet) null, i2);
        this.a.setIndeterminate(true);
        this.a.setClickable(false);
        if (i == -2 || i == -1) {
            layoutParams = new RelativeLayout.LayoutParams(i, i);
        } else {
            int dpToPx = AppLovinSdkUtils.dpToPx(activity, i);
            layoutParams = new RelativeLayout.LayoutParams(dpToPx, dpToPx);
        }
        layoutParams.addRule(13);
        this.a.setLayoutParams(layoutParams);
        addView(this.a);
    }

    public void a() {
        setVisibility(0);
    }

    public void b() {
        setVisibility(8);
    }

    public void setColor(int i) {
        this.a.getIndeterminateDrawable().setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }
}
