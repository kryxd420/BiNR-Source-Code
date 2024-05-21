package com.tapdaq.sdk.layout;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class NativeAdItemLayout extends LinearLayout {
    private Button mButton;
    private Spinner mSpinner;

    public NativeAdItemLayout(Context context) {
        super(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 0.5f;
        this.mButton = new Button(context);
        this.mSpinner = new Spinner(context);
        addView(this.mButton, layoutParams);
        addView(this.mSpinner, layoutParams);
    }

    public Button getButton() {
        return this.mButton;
    }

    public Spinner getSpinner() {
        return this.mSpinner;
    }
}
