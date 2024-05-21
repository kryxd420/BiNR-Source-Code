package com.tapdaq.sdk.debug;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.Utils;

public class TDDebuggerAdapterInfoLayout extends RelativeLayout {
    private TextView mAdapterBiddingStatusLbl;
    private TextView mAdapterNameLbl;
    private TextView mAdapterNetworkSDKLbl;
    private TextView mAdapterSDKLbl;
    private TextView mAdapterStatusLbl;
    private TextView mTapdaqSDKLbl;

    public TDDebuggerAdapterInfoLayout(Context context) {
        super(context);
        initialise();
    }

    public TDDebuggerAdapterInfoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialise();
    }

    public TDDebuggerAdapterInfoLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialise();
    }

    @TargetApi(21)
    public TDDebuggerAdapterInfoLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialise();
    }

    private void initialise() {
        setId(Utils.generateViewId());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.mAdapterNameLbl = createTextView("", 20, Typeface.DEFAULT_BOLD, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, this.mAdapterNameLbl.getId());
        TextView createTextView = createTextView("Tapdaq Version: ", 12, Typeface.DEFAULT, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(1, createTextView.getId());
        layoutParams3.addRule(6, createTextView.getId());
        this.mTapdaqSDKLbl = createTextView("", 12, Typeface.DEFAULT, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(3, createTextView.getId());
        TextView createTextView2 = createTextView("Adapter Version: ", 12, Typeface.DEFAULT, layoutParams4);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(1, createTextView2.getId());
        layoutParams5.addRule(6, createTextView2.getId());
        this.mAdapterSDKLbl = createTextView("", 12, Typeface.DEFAULT, layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.addRule(3, createTextView2.getId());
        TextView createTextView3 = createTextView("Network SDK Version: ", 12, Typeface.DEFAULT, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(1, createTextView3.getId());
        layoutParams7.addRule(6, createTextView3.getId());
        this.mAdapterNetworkSDKLbl = createTextView("", 12, Typeface.DEFAULT, layoutParams7);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(3, createTextView3.getId());
        TextView createTextView4 = createTextView("Bidding Status: ", 12, Typeface.DEFAULT, layoutParams8);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.addRule(1, createTextView4.getId());
        layoutParams9.addRule(6, createTextView4.getId());
        this.mAdapterBiddingStatusLbl = createTextView("", 12, Typeface.DEFAULT, layoutParams9);
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams10.addRule(3, createTextView4.getId());
        TextView createTextView5 = createTextView("Adapter Status: ", 12, Typeface.DEFAULT, layoutParams10);
        RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams11.addRule(1, createTextView5.getId());
        layoutParams11.addRule(6, createTextView5.getId());
        this.mAdapterStatusLbl = createTextView("", 12, Typeface.DEFAULT, layoutParams11);
    }

    private TextView createTextView(String str, int i, Typeface typeface, ViewGroup.LayoutParams layoutParams) {
        TextView textView = new TextView(getContext());
        textView.setId(Utils.generateViewId());
        textView.setTextSize((float) i);
        textView.setTypeface(typeface);
        textView.setText(str);
        addView(textView, layoutParams);
        return textView;
    }

    public void updateAdapterStatus(Context context, TMAdapter tMAdapter) {
        this.mAdapterNameLbl.setText(tMAdapter.getName());
        this.mTapdaqSDKLbl.setText(Tapdaq.getSDKVersion());
        this.mAdapterSDKLbl.setText(tMAdapter.getAdapterVersion());
        this.mAdapterNetworkSDKLbl.setText(tMAdapter.getSdkVersion());
        if (tMAdapter.getNetwork() != null) {
            this.mAdapterBiddingStatusLbl.setText(tMAdapter.getNetwork().getConfig().isSdkBiddingEnabled() ? "Enabled" : TDAdapterStatus.DISABLED_STR);
        } else {
            this.mAdapterBiddingStatusLbl.setText(TDAdapterStatus.DISABLED_STR);
        }
        this.mAdapterStatusLbl.setText(TDAdapterStatus.GetString(tMAdapter.getStatus()));
    }
}
