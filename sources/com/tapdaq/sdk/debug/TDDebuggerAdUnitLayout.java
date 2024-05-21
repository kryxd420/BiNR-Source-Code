package com.tapdaq.sdk.debug;

import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.helpers.Utils;

public class TDDebuggerAdUnitLayout extends LinearLayout {
    public Object getSelectedSpinnerItem() {
        return null;
    }

    public TDDebuggerAdUnitLayout(Context context) {
        super(context);
    }

    public TDDebuggerAdUnitLayout(Context context, int i, String[] strArr, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context);
        setOrientation(1);
        initialise(i, strArr, onClickListener, onClickListener2);
    }

    public TDDebuggerAdUnitLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TDDebuggerAdUnitLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public TDDebuggerAdUnitLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void initialise(int i, String[] strArr, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.addView(createButton("Load " + TMAdType.getStylizeName(i), onClickListener));
        linearLayout.addView(createButton("Show", onClickListener2));
        if (strArr != null) {
            linearLayout.addView(createSpinner(strArr));
        }
        addView(linearLayout, new LinearLayout.LayoutParams(-2, -2));
    }

    /* access modifiers changed from: protected */
    public Button createButton(String str, View.OnClickListener onClickListener) {
        Button button = new Button(getContext());
        button.setId(Utils.generateViewId());
        button.setText(str);
        if (Build.VERSION.SDK_INT >= 21) {
            button.setStateListAnimator((StateListAnimator) null);
        }
        button.setOnClickListener(onClickListener);
        return button;
    }

    /* access modifiers changed from: protected */
    public Spinner createSpinner(String[] strArr) {
        Spinner spinner = new Spinner(getContext());
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), 17367048, strArr);
        arrayAdapter.setDropDownViewResource(17367049);
        spinner.setAdapter(arrayAdapter);
        spinner.setId(Utils.generateViewId());
        return spinner;
    }
}
