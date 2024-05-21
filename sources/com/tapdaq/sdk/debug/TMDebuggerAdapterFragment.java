package com.tapdaq.sdk.debug;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TDWaterfallService;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.common.TMBannerAdSizes;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.Utils;
import com.tapdaq.sdk.layout.NativeAdTemplateLayout;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TMDebuggerAdapterFragment extends Fragment {
    /* access modifiers changed from: private */
    public TMDebuggerAdListener mAdListener;
    /* access modifiers changed from: private */
    public List<TDAdRequest> mAdRequests = new ArrayList();
    /* access modifiers changed from: private */
    public SparseArray<TDDebuggerAdUnitLayout> mAdUnitLayouts = new SparseArray<>();
    private TMDebugAdapter mDebugAdapter;
    private TDDebuggerAdapterInfoLayout mInfoLayout;
    /* access modifiers changed from: private */
    public NativeAdTemplateLayout mNativeAdLayout;
    /* access modifiers changed from: private */
    public RelativeLayout mNativeAdLayoutContainer;
    /* access modifiers changed from: private */
    public TMAdapter mSelectedAdapter;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(1);
        this.mInfoLayout = new TDDebuggerAdapterInfoLayout(getActivity());
        linearLayout.addView(this.mInfoLayout);
        for (int i = 0; i < TMAdType.getTotalTypes(); i++) {
            TDDebuggerAdUnitLayout create = new TDDebuggerAdUnitLayoutFactory().create(getActivity(), i, (String[]) null, new OnClickLoad(i), new OnClickDisplay(i));
            linearLayout.addView(create, new LinearLayout.LayoutParams(-1, -2));
            this.mAdUnitLayouts.put(i, create);
        }
        this.mDebugAdapter = new TMDebugAdapter(getActivity(), new ArrayList());
        this.mAdListener = new TMDebuggerAdListener(this.mDebugAdapter);
        this.mAdListener.setAutoRetry(false);
        this.mNativeAdLayout = new NativeAdTemplateLayout(getActivity());
        this.mNativeAdLayoutContainer = new RelativeLayout(getActivity());
        this.mNativeAdLayoutContainer.setVisibility(8);
        this.mNativeAdLayoutContainer.setBackgroundColor(Color.argb(100, 0, 0, 0));
        this.mNativeAdLayoutContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                view.setVisibility(8);
                TMDebuggerAdapterFragment.this.mNativeAdLayout.clear();
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (TDDeviceInfo.getResolutionFloat(getActivity()) * 320.0f), (int) (TDDeviceInfo.getResolutionFloat(getActivity()) * 250.0f));
        layoutParams.addRule(13);
        this.mNativeAdLayoutContainer.addView(this.mNativeAdLayout, layoutParams);
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        ScrollView scrollView = new ScrollView(getActivity());
        scrollView.setId(Utils.generateViewId());
        scrollView.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (TDDeviceInfo.getResolutionFloat(getActivity()) * 150.0f));
        layoutParams2.addRule(12);
        TDDebuggerLogLayout tDDebuggerLogLayout = new TDDebuggerLogLayout((Context) getActivity(), (ArrayAdapter) this.mDebugAdapter);
        relativeLayout.addView(tDDebuggerLogLayout, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.addRule(2, tDDebuggerLogLayout.getId());
        relativeLayout.addView(scrollView, layoutParams3);
        relativeLayout.addView(this.mNativeAdLayoutContainer, new RelativeLayout.LayoutParams(-1, -1));
        return relativeLayout;
    }

    /* access modifiers changed from: package-private */
    public void setAdapter(TMAdapter tMAdapter) {
        this.mSelectedAdapter = tMAdapter;
    }

    public void onResume() {
        super.onResume();
        if (this.mSelectedAdapter != null) {
            this.mInfoLayout.updateAdapterStatus(getActivity(), this.mSelectedAdapter);
        }
        showAdapterView();
    }

    private void showAdapterView() {
        for (int i = 0; i < TMAdType.getTotalTypes(); i++) {
            if (this.mSelectedAdapter.canDisplayAdType(getActivity(), i)) {
                this.mAdUnitLayouts.get(i).setVisibility(0);
            } else {
                this.mAdUnitLayouts.get(i).setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void log(String str) {
        this.mDebugAdapter.insert(str, 0);
    }

    /* access modifiers changed from: private */
    public void loadAd(int i) {
        int i2 = i;
        new TDWaterfallService().request(getActivity(), TapdaqPlacement.TDPTagDefault, i2, Arrays.asList(new TMAdapter[]{this.mSelectedAdapter}), new TDWaterfallService.TDWaterfallCallback() {
            public void onSuccess(TDAdRequest tDAdRequest) {
                tDAdRequest.setAdListener(TMDebuggerAdapterFragment.this.mAdListener);
                TDWaterfallItem nextNetwork = tDAdRequest.getNextNetwork();
                if (nextNetwork != null) {
                    if (nextNetwork.getDemandType().equalsIgnoreCase("sdk_bidding")) {
                        TMDebuggerAdapterFragment.this.log("Loading Ad With Bid");
                    }
                    tDAdRequest.setAdapter(TMDebuggerAdapterFragment.this.mSelectedAdapter);
                    TMDebuggerAdapterFragment.this.mSelectedAdapter.loadAd(TMDebuggerAdapterFragment.this.getActivity(), tDAdRequest);
                    TMDebuggerAdapterFragment.this.mAdRequests.add(tDAdRequest);
                }
            }

            public void onError(final TMAdError tMAdError) {
                TMDebuggerAdapterFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        TMDebuggerAdapterFragment.this.mAdListener.didFailToLoad(tMAdError);
                    }
                });
            }
        });
    }

    private class OnClickLoad implements View.OnClickListener {
        private final int mType;

        OnClickLoad(int i) {
            this.mType = i;
        }

        public void onClick(View view) {
            TMDebuggerAdapterFragment.this.log(String.format("Load %s with: %s", new Object[]{TMAdType.getString(this.mType), TMDebuggerAdapterFragment.this.mSelectedAdapter.getName()}));
            if (this.mType == 0) {
                TDDebuggerBannerAdUnitLayout tDDebuggerBannerAdUnitLayout = (TDDebuggerBannerAdUnitLayout) TMDebuggerAdapterFragment.this.mAdUnitLayouts.get(this.mType);
                tDDebuggerBannerAdUnitLayout.getAdView().load(TMDebuggerAdapterFragment.this.getActivity(), TMBannerAdSizes.get((String) tDDebuggerBannerAdUnitLayout.getSelectedSpinnerItem()), TMDebuggerAdapterFragment.this.mSelectedAdapter, (TMAdListener) TMDebuggerAdapterFragment.this.mAdListener);
                return;
            }
            TMDebuggerAdapterFragment.this.loadAd(this.mType);
        }
    }

    private class OnClickDisplay implements View.OnClickListener {
        private final int mType;

        OnClickDisplay(int i) {
            this.mType = i;
        }

        public void onClick(View view) {
            if (this.mType == 0) {
                TMDebuggerAdapterFragment.this.log("Clear Banner");
                ((TDDebuggerBannerAdUnitLayout) TMDebuggerAdapterFragment.this.mAdUnitLayouts.get(this.mType)).getAdView().destroy(TMDebuggerAdapterFragment.this.getActivity());
                return;
            }
            TMDebuggerAdapterFragment.this.log(String.format("Show %s with: %s", new Object[]{TMAdType.getString(this.mType), TMDebuggerAdapterFragment.this.mSelectedAdapter.getName()}));
            for (TDAdRequest tDAdRequest : TMDebuggerAdapterFragment.this.mAdRequests) {
                if (tDAdRequest.getType() == this.mType) {
                    if (this.mType == 4) {
                        TMDebuggerAdapterFragment.this.log("Show Native Ad");
                        if (TMDebuggerAdapterFragment.this.mAdListener != null && TMDebuggerAdapterFragment.this.mAdListener.getNativeAds().size() > 0) {
                            TMDebuggerAdapterFragment.this.mNativeAdLayoutContainer.setVisibility(0);
                            TMDebuggerAdapterFragment.this.mNativeAdLayout.populate(TMDebuggerAdapterFragment.this.mAdListener.getNativeAds().get(0));
                            TMDebuggerAdapterFragment.this.mAdListener.getNativeAds().remove(0);
                        }
                    } else {
                        TMDebuggerAdapterFragment.this.mSelectedAdapter.showAd(TMDebuggerAdapterFragment.this.getActivity(), tDAdRequest);
                    }
                    TMDebuggerAdapterFragment.this.mAdRequests.remove(tDAdRequest);
                    return;
                }
            }
        }
    }
}
