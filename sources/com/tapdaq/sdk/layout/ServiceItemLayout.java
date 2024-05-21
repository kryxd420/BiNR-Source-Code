package com.tapdaq.sdk.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tapdaq.sdk.helpers.TDDeviceInfo;

public class ServiceItemLayout extends LinearLayout {
    private TextView mTextView;

    public ServiceItemLayout(Context context) {
        super(context);
        float resolutionFloat = TDDeviceInfo.getResolutionFloat(context);
        this.mTextView = new TextView(context);
        int i = (int) (resolutionFloat * 10.0f);
        this.mTextView.setPadding(i, i, i, i);
        this.mTextView.setTextSize(14.0f);
        this.mTextView.setTypeface((Typeface) null, 1);
        addView(this.mTextView, new LinearLayout.LayoutParams(-1, -2));
    }

    public TextView getTextView() {
        return this.mTextView;
    }
}
