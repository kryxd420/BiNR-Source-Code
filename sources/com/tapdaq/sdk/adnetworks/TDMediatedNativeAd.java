package com.tapdaq.sdk.adnetworks;

import android.content.Context;
import android.view.View;
import com.tapdaq.sdk.listeners.TapdaqAdEventHandler;
import java.util.List;

public class TDMediatedNativeAd {
    private View mAdChoicesView;
    private TapdaqAdEventHandler mAdEventHandler;
    private View mAdView;
    private String mAdvertiser;
    private String mBody;
    private String mCallToAction;
    private String mCaption;
    @Deprecated
    private TDMediatedNativeAdImage mIcon;
    private View mIconView;
    private List<TDMediatedNativeAdImage> mImages;
    private View mMediaView;
    private Object mNativeAd;
    private int mNetwork;
    private String mPrice;
    private double mStarRating;
    private String mStore;
    private String mSubtitle;
    private TapdaqAd mTapdaqAd;
    private String mTitle;
    private TDMediatedNativeAdVideoController mVideoController;
    private String mVideoUrl;

    public TDMediatedNativeAd(int i, TapdaqAd tapdaqAd, TapdaqAdEventHandler tapdaqAdEventHandler) {
        this.mNetwork = i;
        this.mTapdaqAd = tapdaqAd;
        this.mAdEventHandler = tapdaqAdEventHandler;
    }

    public void setAdView(View view) {
        this.mAdView = view;
    }

    public void setMediaView(View view) {
        this.mMediaView = view;
    }

    public void setNativeAd(Object obj) {
        this.mNativeAd = obj;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public void setBody(String str) {
        this.mBody = str;
    }

    public void setCaption(String str) {
        this.mCaption = str;
    }

    public void setAdvertiser(String str) {
        this.mAdvertiser = str;
    }

    public void setCallToAction(String str) {
        this.mCallToAction = str;
    }

    public void setImages(List<TDMediatedNativeAdImage> list) {
        this.mImages = list;
    }

    public void setAdChoicesView(View view) {
        this.mAdChoicesView = view;
    }

    @Deprecated
    public void setIcon(TDMediatedNativeAdImage tDMediatedNativeAdImage) {
        this.mIcon = tDMediatedNativeAdImage;
    }

    public void setAppIconView(View view) {
        this.mIconView = view;
    }

    public void setVideoController(TDMediatedNativeAdVideoController tDMediatedNativeAdVideoController) {
        this.mVideoController = tDMediatedNativeAdVideoController;
    }

    public void setPrice(String str) {
        this.mPrice = str;
    }

    public void setStarRating(double d) {
        this.mStarRating = d;
    }

    public void setStore(String str) {
        this.mStore = str;
    }

    public void setVideoUrl(String str) {
        this.mVideoUrl = str;
    }

    public View getAdView() {
        return this.mAdView;
    }

    public View getMediaView() {
        return this.mMediaView;
    }

    public Object getNativeAd() {
        return this.mNativeAd;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getBody() {
        return this.mBody;
    }

    public String getCaption() {
        return this.mCaption;
    }

    public String getAdvertiser() {
        return this.mAdvertiser;
    }

    public String getCallToAction() {
        return this.mCallToAction;
    }

    public List<TDMediatedNativeAdImage> getImages() {
        return this.mImages;
    }

    public View getAdChoiceView() {
        return this.mAdChoicesView;
    }

    @Deprecated
    public TDMediatedNativeAdImage getIcon() {
        return this.mIcon;
    }

    public View getAppIconView() {
        return this.mIconView;
    }

    public TDMediatedNativeAdVideoController getVideoController() {
        return this.mVideoController;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public double getStarRating() {
        return this.mStarRating;
    }

    public String getStore() {
        return this.mStore;
    }

    public String getVideoUrl() {
        return this.mVideoUrl;
    }

    public void registerView(View view) {
        this.mTapdaqAd.getAdRequest().getAdapter().registerView(view, this);
    }

    public void trackImpression(Context context) {
        this.mAdEventHandler.OnDidDisplay(context, this.mTapdaqAd);
    }

    @Deprecated
    public void trackImpression() {
        trackImpression((Context) null);
    }

    public void destroy() {
        if (getVideoController() != null) {
            getVideoController().pause();
        }
    }
}
