package com.tapdaq.sdk.adnetworks;

import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapdaq.sdk.model.waterfall.TMReward;
import java.util.List;
import java.util.Map;

public class TDAdRequest {
    private static final String HASHED_USER_ID_KEY = "hashed_user_id";
    private TMAdError mAdError;
    private String mHashedUserId;
    private TMAdListenerBase mListener;
    private long mLoadedAt;
    private TDMediatedNativeAdOptions mMediatedNativeAdOptions = new TDMediatedNativeAdOptions();
    private String mPlacement;
    private TMReward mReward;
    private TMAdapter mSelectedAdapter;
    private int mType;
    private List<TDWaterfallItem> mWaterfall;
    private String mWaterfallId;
    private TDWaterfallItem mWaterfallItem;

    public TDAdRequest(String str, int i, String str2, List<TDWaterfallItem> list, TMReward tMReward) {
        this.mWaterfallId = str;
        this.mType = i;
        this.mPlacement = str2;
        this.mWaterfall = list;
        this.mReward = tMReward;
        this.mAdError = new TMAdError();
    }

    public void setAdListener(TMAdListenerBase tMAdListenerBase) {
        this.mListener = tMAdListenerBase;
    }

    public void setAdapter(TMAdapter tMAdapter) {
        this.mSelectedAdapter = tMAdapter;
    }

    public void setLoadedAt(long j) {
        this.mLoadedAt = j;
    }

    public void setHashedUserId(String str) {
        this.mHashedUserId = str;
    }

    public void setReward(TMReward tMReward) {
        this.mReward = tMReward;
    }

    public void setOptions(Object obj) {
        if (obj != null && this.mType == 4 && (obj instanceof TDMediatedNativeAdOptions)) {
            this.mMediatedNativeAdOptions = (TDMediatedNativeAdOptions) obj;
        }
    }

    public String getWaterfallId() {
        return this.mWaterfallId;
    }

    public int getType() {
        return this.mType;
    }

    public String getHashedUserId() {
        return this.mHashedUserId;
    }

    public String getPlacement() {
        return this.mPlacement;
    }

    public TMReward getReward() {
        if (this.mHashedUserId != null && !this.mHashedUserId.isEmpty()) {
            this.mReward.addCustomData(HASHED_USER_ID_KEY, this.mHashedUserId);
        }
        return this.mReward;
    }

    public TMAdapter getAdapter() {
        return this.mSelectedAdapter;
    }

    public TMAdListenerBase getListener() {
        return this.mListener;
    }

    public TMAdError getAdError() {
        return this.mAdError;
    }

    public String getNetworkErrorMessage() {
        if (this.mAdError == null || this.mAdError.getSubErrors() == null || this.mWaterfallItem == null || !this.mAdError.getSubErrors().containsKey(getWaterfallItem().getNetwork())) {
            return null;
        }
        return this.mAdError.getSubErrors().get(getWaterfallItem().getNetwork()).getErrorMessage();
    }

    public TDMediatedNativeAdOptions getMediatedNativeAdOptions() {
        return this.mMediatedNativeAdOptions;
    }

    public TDWaterfallItem getNextNetwork() {
        for (TDWaterfallItem next : this.mWaterfall) {
            if (!next.hasAttempted()) {
                this.mWaterfallItem = next;
                next.setAttempted();
                return next;
            }
        }
        return null;
    }

    public List<TDWaterfallItem> getWaterfall() {
        return this.mWaterfall;
    }

    public TDWaterfallItem getWaterfallItem() {
        return this.mWaterfallItem;
    }

    public Integer getWaterfallPosition() {
        if (this.mWaterfallItem == null || this.mWaterfall == null || !this.mWaterfall.contains(this.mWaterfallItem)) {
            return null;
        }
        return Integer.valueOf(this.mWaterfall.indexOf(this.mWaterfallItem));
    }

    public String getAdUnitId() {
        String adUnitIdKey = getAdUnitIdKey();
        if (this.mWaterfallItem.getAdUnitIds() == null || !this.mWaterfallItem.getAdUnitIds().containsKey(adUnitIdKey)) {
            return this.mWaterfallItem.getAdUnitId();
        }
        return this.mWaterfallItem.getAdUnitIds().get(adUnitIdKey);
    }

    public Map<String, String> getAdUnitIds() {
        return this.mWaterfallItem.getAdUnitIds();
    }

    public long getLoadTimeout() {
        return this.mWaterfallItem.getAdLoadTimeout();
    }

    public boolean isExpired() {
        return this.mSelectedAdapter != null && this.mSelectedAdapter.isExpired(Long.valueOf(this.mLoadedAt));
    }

    private String getAdUnitIdKey() {
        switch (getType()) {
            case 0:
                return "banner_id";
            case 1:
                return "interstitial_id";
            case 2:
                return "video_interstitial_id";
            case 3:
                return "rewarded_video_id";
            case 4:
                return "native_id";
            case 5:
                return "offerwall_id";
            default:
                return "";
        }
    }
}
