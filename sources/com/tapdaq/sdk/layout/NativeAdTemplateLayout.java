package com.tapdaq.sdk.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAdImage;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.Utils;
import com.tapdaq.sdk.network.HttpClientBase;
import com.tapdaq.sdk.network.TClient;

public class NativeAdTemplateLayout extends RelativeLayout {
    private TDMediatedNativeAd mAd;
    private View mAdChoicesView;
    private View mAdview;
    private TextView mBodyTextView;
    private Button mButton;
    private TextView mCaptionTextView;
    private FrameLayout mIconView;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    private TextView mPriceTextView;
    private TextView mStarRating;
    private TextView mStoreTextView;
    private TextView mSubtitleTextView;
    private TextView mTitleView;

    public NativeAdTemplateLayout(Context context) {
        super(context);
        init(context);
    }

    public NativeAdTemplateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public NativeAdTemplateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(-1);
        this.mTitleView = new TextView(context);
        this.mSubtitleTextView = new TextView(context);
        this.mBodyTextView = new TextView(context);
        this.mCaptionTextView = new TextView(context);
        this.mImageView = new ImageView(context);
        this.mButton = new Button(context);
        this.mIconView = new FrameLayout(context);
        this.mPriceTextView = new TextView(context);
        this.mStoreTextView = new TextView(context);
        this.mStarRating = new TextView(context);
        this.mTitleView.setId(Utils.generateViewId());
        this.mSubtitleTextView.setId(Utils.generateViewId());
        this.mBodyTextView.setId(Utils.generateViewId());
        this.mCaptionTextView.setId(Utils.generateViewId());
        this.mImageView.setId(Utils.generateViewId());
        this.mButton.setId(Utils.generateViewId());
        this.mIconView.setId(Utils.generateViewId());
        this.mPriceTextView.setId(Utils.generateViewId());
        this.mStoreTextView.setId(Utils.generateViewId());
        this.mStarRating.setId(Utils.generateViewId());
        addView(this.mImageView, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        addView(this.mTitleView, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, this.mTitleView.getId());
        layoutParams2.addRule(9);
        addView(this.mSubtitleTextView, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(3, this.mSubtitleTextView.getId());
        layoutParams3.addRule(9);
        addView(this.mBodyTextView, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(3, this.mBodyTextView.getId());
        layoutParams4.addRule(9);
        addView(this.mCaptionTextView, layoutParams4);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(12);
        layoutParams5.addRule(9);
        addView(this.mButton, layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams((int) (TDDeviceInfo.getResolutionFloat(context) * 40.0f), (int) (TDDeviceInfo.getResolutionFloat(context) * 40.0f));
        layoutParams6.addRule(12);
        layoutParams6.addRule(11);
        addView(this.mIconView, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(3, this.mCaptionTextView.getId());
        layoutParams7.addRule(9);
        addView(this.mPriceTextView, layoutParams7);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(3, this.mPriceTextView.getId());
        layoutParams8.addRule(9);
        addView(this.mStoreTextView, layoutParams8);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.addRule(3, this.mStoreTextView.getId());
        layoutParams9.addRule(9);
        addView(this.mStarRating, layoutParams9);
    }

    public void clear() {
        removeAllViews();
        this.mStoreTextView.setText("");
        this.mStarRating.setText("");
        this.mPriceTextView.setText("");
        this.mTitleView.setText("");
        this.mSubtitleTextView.setText("");
        this.mBodyTextView.setText("");
        this.mCaptionTextView.setText("");
        this.mImageView.setImageBitmap((Bitmap) null);
        this.mButton.setText("");
        this.mIconView.removeAllViews();
        if (this.mAd != null) {
            this.mAd.destroy();
        }
    }

    public void populate(TDMediatedNativeAd tDMediatedNativeAd) {
        clear();
        init(getContext());
        this.mAd = tDMediatedNativeAd;
        if (tDMediatedNativeAd != null) {
            if (tDMediatedNativeAd.getAdView() != null) {
                this.mAdview = tDMediatedNativeAd.getAdView();
                addView(this.mAdview, 0, new RelativeLayout.LayoutParams(-1, -1));
            }
            if (tDMediatedNativeAd.getMediaView() != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13);
                addView(tDMediatedNativeAd.getMediaView(), 2, layoutParams);
            }
            this.mTitleView.setText(tDMediatedNativeAd.getTitle());
            if (tDMediatedNativeAd.getSubtitle() != null) {
                this.mSubtitleTextView.setText(tDMediatedNativeAd.getSubtitle());
            }
            this.mBodyTextView.setText(tDMediatedNativeAd.getBody());
            if (tDMediatedNativeAd.getCaption() != null) {
                this.mCaptionTextView.setText(tDMediatedNativeAd.getCaption());
            }
            this.mButton.setText(tDMediatedNativeAd.getCallToAction());
            if (tDMediatedNativeAd.getPrice() != null) {
                this.mPriceTextView.setText(tDMediatedNativeAd.getPrice());
            }
            this.mStarRating.setText(Double.toString(tDMediatedNativeAd.getStarRating()));
            if (tDMediatedNativeAd.getStore() != null) {
                this.mStoreTextView.setText(tDMediatedNativeAd.getStore());
            }
            if (tDMediatedNativeAd.getImages() != null) {
                TDMediatedNativeAdImage tDMediatedNativeAdImage = tDMediatedNativeAd.getImages().get(0);
                if (tDMediatedNativeAdImage.getDrawable() != null) {
                    this.mImageView.setImageDrawable(tDMediatedNativeAd.getImages().get(0).getDrawable());
                } else if (tDMediatedNativeAdImage.getUrl() != null) {
                    new TClient().executeImageGET(getContext(), tDMediatedNativeAdImage.getUrl(), 0, 0, new HttpClientBase.ResponseImageHandler() {
                        public void onError(Exception exc) {
                        }

                        public void onSuccess(Bitmap bitmap) {
                            NativeAdTemplateLayout.this.mImageView.setImageBitmap(bitmap);
                        }
                    });
                }
                removeView(this.mImageView);
                addView(this.mImageView, 1);
            }
            if (tDMediatedNativeAd.getAppIconView() != null) {
                this.mIconView.addView(tDMediatedNativeAd.getAppIconView());
            }
            if (tDMediatedNativeAd.getAdChoiceView() != null) {
                this.mAdChoicesView = tDMediatedNativeAd.getAdChoiceView();
                this.mAdChoicesView.setId(Utils.generateViewId());
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(10);
                layoutParams2.addRule(11);
                addView(this.mAdChoicesView, layoutParams2);
            }
            tDMediatedNativeAd.registerView(this.mButton);
            if (tDMediatedNativeAd.getVideoController() != null && tDMediatedNativeAd.getVideoController().hasVideoContent()) {
                tDMediatedNativeAd.getVideoController().play();
            }
            tDMediatedNativeAd.trackImpression(getContext());
        }
    }
}
