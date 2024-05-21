package com.tapdaq.adapters;

import android.media.MediaPlayer;
import android.view.View;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAdVideoController;

public class TDApplovinNativeVideoController implements TDMediatedNativeAdVideoController {
    private TDMediatedNativeAd mAd;
    private MediaPlayer mMediaPlayer;

    public TDApplovinNativeVideoController(TDMediatedNativeAd tDMediatedNativeAd, MediaPlayer mediaPlayer) {
        this.mAd = tDMediatedNativeAd;
        this.mMediaPlayer = mediaPlayer;
    }

    public void play() {
        View mediaView = this.mAd.getMediaView();
        if (mediaView != null && (mediaView instanceof TDApplovinNativeMediaView)) {
            ((TDApplovinNativeMediaView) mediaView).playVideo();
        }
    }

    public void pause() {
        if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
            View mediaView = this.mAd.getMediaView();
            if (mediaView != null && (mediaView instanceof TDApplovinNativeMediaView)) {
                ((TDApplovinNativeMediaView) mediaView).onCompletion(this.mMediaPlayer);
            }
        }
    }

    public boolean hasVideoContent() {
        if (!(this.mAd.getNativeAd() instanceof AppLovinNativeAd)) {
            return false;
        }
        AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) this.mAd.getNativeAd();
        if (appLovinNativeAd.getVideoUrl() == null || !appLovinNativeAd.isVideoPrecached()) {
            return false;
        }
        return true;
    }
}
