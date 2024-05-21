package com.tapdaq.sdk.layout;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LogItemLayout extends LinearLayout {
    private TextView mTextView;

    public LogItemLayout(Context context) {
        super(context);
        this.mTextView = new TextView(context);
        addView(this.mTextView, new LinearLayout.LayoutParams(-1, -2));
    }

    public TextView getTextView() {
        return this.mTextView;
    }
}
