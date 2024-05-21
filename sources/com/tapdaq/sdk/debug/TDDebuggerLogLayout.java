package com.tapdaq.sdk.debug;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tapdaq.sdk.helpers.Utils;

public class TDDebuggerLogLayout extends RelativeLayout {
    public TDDebuggerLogLayout(Context context) {
        super(context);
    }

    public TDDebuggerLogLayout(Context context, ArrayAdapter arrayAdapter) {
        super(context);
        setId(Utils.generateViewId());
        initialise(arrayAdapter);
    }

    public TDDebuggerLogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TDDebuggerLogLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public TDDebuggerLogLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private void initialise(ArrayAdapter arrayAdapter) {
        TextView textView = new TextView(getContext());
        textView.setId(Utils.generateViewId());
        textView.setText("Logs");
        textView.setTextSize(16.0f);
        addView(textView);
        ListView listView = new ListView(getContext());
        listView.setId(Utils.generateViewId());
        listView.setAdapter(arrayAdapter);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, textView.getId());
        addView(listView, layoutParams);
    }
}
