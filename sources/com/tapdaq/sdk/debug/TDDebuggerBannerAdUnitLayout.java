package com.tapdaq.sdk.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.common.TMBannerAdSizes;

public class TDDebuggerBannerAdUnitLayout extends TDDebuggerAdUnitLayout {
    private static String[] sizes = {TMBannerAdSizes.STANDARD.name, TMBannerAdSizes.LARGE.name, TMBannerAdSizes.MEDIUM_RECT.name, TMBannerAdSizes.LEADERBOARD.name, TMBannerAdSizes.FULL.name, TMBannerAdSizes.SMART.name, TMBannerAdSizes.SKYSCRAPER.name};
    private TMBannerAdView mAdView;
    private Spinner mBannerSpinner;

    public TDDebuggerBannerAdUnitLayout(Context context) {
        super(context);
    }

    public TDDebuggerBannerAdUnitLayout(Context context, int i, String[] strArr, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context, i, sizes, onClickListener, onClickListener2);
    }

    public TDDebuggerBannerAdUnitLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TDDebuggerBannerAdUnitLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TDDebuggerBannerAdUnitLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void initialise(int i, String[] strArr, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.addView(createButton("Load Banner", onClickListener));
        linearLayout.addView(createButton("Clear", onClickListener2));
        if (strArr != null) {
            this.mBannerSpinner = createSpinner(strArr);
            linearLayout.addView(this.mBannerSpinner);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        addView(linearLayout, layoutParams);
        this.mAdView = new TMBannerAdView(getContext());
        layoutParams.gravity = 1;
        addView(this.mAdView, layoutParams);
    }

    public Object getSelectedSpinnerItem() {
        return this.mBannerSpinner.getSelectedItem();
    }

    public TMBannerAdView getAdView() {
        return this.mAdView;
    }
}
