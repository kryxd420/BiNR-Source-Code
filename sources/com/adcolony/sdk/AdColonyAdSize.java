package com.adcolony.sdk;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.tapdaq.sdk.TapdaqError;

public class AdColonyAdSize {
    public static final AdColonyAdSize MEDIUM_RECTANGLE = new AdColonyAdSize(TapdaqError.NO_AD_AVAILABLE, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    static final AdColonyAdSize c = new AdColonyAdSize(320, 50);
    int a;
    int b;

    public AdColonyAdSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }
}
