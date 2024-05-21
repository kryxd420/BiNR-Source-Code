package com.tapdaq.sdk.debug;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAdOptions;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.common.TMBannerAdSizes;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.Utils;
import com.tapdaq.sdk.layout.NativeAdTemplateLayout;
import com.tapdaq.sdk.listeners.TMAdListener;
import java.util.ArrayList;

public class TMDebuggerMediateFragment extends Fragment {
    /* access modifiers changed from: private */
    public TMDebuggerAdListener mAdListener;
    /* access modifiers changed from: private */
    public SparseArray<TDDebuggerAdUnitLayout> mAdUnitLayouts = new SparseArray<>();
    private TMDebugAdapter mDebugAdapter;
    /* access modifiers changed from: private */
    public RelativeLayout mLayout;
    /* access modifiers changed from: private */
    public NativeAdTemplateLayout mNativeAdLayout;
    /* access modifiers changed from: private */
    public RelativeLayout mNativeAdLayoutContainer;
    /* access modifiers changed from: private */
    public EditText mTagTextView;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        this.mLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(getActivity());
        textView.setId(Utils.generateViewId());
        textView.setText("Placement Tag: ");
        double resolutionFloat = (double) TDDeviceInfo.getResolutionFloat(getActivity());
        Double.isNaN(resolutionFloat);
        textView.setHeight((int) (resolutionFloat * 50.0d));
        textView.setGravity(16);
        textView.setTextSize(14.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        this.mLayout.addView(textView, layoutParams);
        this.mTagTextView = new EditText(getActivity());
        this.mTagTextView.setId(Utils.generateViewId());
        this.mTagTextView.setText(TapdaqPlacement.TDPTagDefault);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(1, textView.getId());
        this.mLayout.addView(this.mTagTextView, layoutParams2);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(1);
        for (int i = 0; i < TMAdType.getTotalTypes(); i++) {
            TDDebuggerAdUnitLayout create = new TDDebuggerAdUnitLayoutFactory().create(getActivity(), i, (String[]) null, new OnClickLoad(i), new OnClickDisplay(i));
            linearLayout.addView(create, new LinearLayout.LayoutParams(-1, -2));
            this.mAdUnitLayouts.put(i, create);
        }
        this.mDebugAdapter = new TMDebugAdapter(getActivity(), new ArrayList());
        TDDebuggerLogLayout tDDebuggerLogLayout = new TDDebuggerLogLayout((Context) getActivity(), (ArrayAdapter) this.mDebugAdapter);
        this.mAdListener = new TMDebuggerAdListener(this.mDebugAdapter);
        this.mNativeAdLayout = new NativeAdTemplateLayout(getActivity());
        this.mNativeAdLayoutContainer = new RelativeLayout(getActivity());
        this.mNativeAdLayoutContainer.setId(Utils.generateViewId());
        this.mNativeAdLayoutContainer.setVisibility(8);
        this.mNativeAdLayoutContainer.setBackgroundColor(Color.argb(100, 0, 0, 0));
        this.mNativeAdLayoutContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                view.setVisibility(8);
                TMDebuggerMediateFragment.this.mNativeAdLayout.clear();
            }
        });
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams((int) (TDDeviceInfo.getResolutionFloat(getActivity()) * 320.0f), (int) (TDDeviceInfo.getResolutionFloat(getActivity()) * 250.0f));
        layoutParams3.addRule(13);
        this.mNativeAdLayoutContainer.addView(this.mNativeAdLayout, layoutParams3);
        this.mLayout.addView(this.mNativeAdLayoutContainer, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, (int) (TDDeviceInfo.getResolutionFloat(getActivity()) * 150.0f));
        layoutParams4.addRule(12);
        this.mLayout.addView(tDDebuggerLogLayout, layoutParams4);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams5.addRule(3, this.mTagTextView.getId());
        layoutParams5.addRule(2, tDDebuggerLogLayout.getId());
        ScrollView scrollView = new ScrollView(getActivity());
        scrollView.setId(Utils.generateViewId());
        scrollView.addView(linearLayout);
        this.mLayout.addView(scrollView, layoutParams5);
        return this.mLayout;
    }

    /* access modifiers changed from: private */
    public void log(String str) {
        this.mDebugAdapter.insert(str, 0);
    }

    private class OnClickLoad implements View.OnClickListener {
        private final int mType;

        OnClickLoad(int i) {
            this.mType = i;
        }

        public void onClick(View view) {
            TMDebuggerMediateFragment tMDebuggerMediateFragment = TMDebuggerMediateFragment.this;
            tMDebuggerMediateFragment.log("Load " + TMAdType.getString(this.mType));
            String obj = TMDebuggerMediateFragment.this.mTagTextView.getText().toString();
            switch (this.mType) {
                case 0:
                    TDDebuggerBannerAdUnitLayout tDDebuggerBannerAdUnitLayout = (TDDebuggerBannerAdUnitLayout) TMDebuggerMediateFragment.this.mAdUnitLayouts.get(this.mType);
                    tDDebuggerBannerAdUnitLayout.getAdView().load(TMDebuggerMediateFragment.this.getActivity(), TMBannerAdSizes.get((String) tDDebuggerBannerAdUnitLayout.getSelectedSpinnerItem()), (TMAdListener) TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 1:
                    Tapdaq.getInstance().loadInterstitial(TMDebuggerMediateFragment.this.getActivity(), obj, TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 2:
                    Tapdaq.getInstance().loadVideo(TMDebuggerMediateFragment.this.getActivity(), obj, TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 3:
                    Tapdaq.getInstance().loadRewardedVideo(TMDebuggerMediateFragment.this.getActivity(), obj, TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 4:
                    Tapdaq.getInstance().loadMediatedNativeAd(TMDebuggerMediateFragment.this.getActivity(), obj, new TDMediatedNativeAdOptions().setStartVideoMuted(true), TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 5:
                    Tapdaq.getInstance().loadOfferwall(TMDebuggerMediateFragment.this.getActivity(), TMDebuggerMediateFragment.this.mAdListener);
                    return;
                default:
                    return;
            }
        }
    }

    private class OnClickDisplay implements View.OnClickListener {
        private final int mType;

        OnClickDisplay(int i) {
            this.mType = i;
        }

        public void onClick(View view) {
            if (this.mType == 0) {
                TMDebuggerMediateFragment tMDebuggerMediateFragment = TMDebuggerMediateFragment.this;
                tMDebuggerMediateFragment.log("Clear " + TMAdType.getString(this.mType));
                ((TDDebuggerBannerAdUnitLayout) TMDebuggerMediateFragment.this.mAdUnitLayouts.get(this.mType)).getAdView().destroy(TMDebuggerMediateFragment.this.getActivity());
                return;
            }
            TMDebuggerMediateFragment tMDebuggerMediateFragment2 = TMDebuggerMediateFragment.this;
            tMDebuggerMediateFragment2.log("Show " + TMAdType.getString(this.mType));
            String obj = TMDebuggerMediateFragment.this.mTagTextView.getText().toString();
            switch (this.mType) {
                case 1:
                    Tapdaq.getInstance().showInterstitial(TMDebuggerMediateFragment.this.getActivity(), obj, TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 2:
                    Tapdaq.getInstance().showVideo(TMDebuggerMediateFragment.this.getActivity(), obj, TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 3:
                    Tapdaq.getInstance().showRewardedVideo(TMDebuggerMediateFragment.this.getActivity(), obj, TMDebuggerMediateFragment.this.mAdListener);
                    return;
                case 4:
                    if (TMDebuggerMediateFragment.this.mAdListener != null && TMDebuggerMediateFragment.this.mAdListener.getNativeAds().size() > 0) {
                        TMDebuggerMediateFragment.this.mNativeAdLayoutContainer.setVisibility(0);
                        TMDebuggerMediateFragment.this.mLayout.removeView(TMDebuggerMediateFragment.this.mNativeAdLayoutContainer);
                        TMDebuggerMediateFragment.this.mLayout.addView(TMDebuggerMediateFragment.this.mNativeAdLayoutContainer);
                        TMDebuggerMediateFragment.this.mNativeAdLayout.populate(TMDebuggerMediateFragment.this.mAdListener.getNativeAds().get(0));
                        TMDebuggerMediateFragment.this.mAdListener.getNativeAds().remove(0);
                        return;
                    }
                    return;
                case 5:
                    Tapdaq.getInstance().showOfferwall(TMDebuggerMediateFragment.this.getActivity(), TMDebuggerMediateFragment.this.mAdListener);
                    return;
                default:
                    return;
            }
        }
    }
}
