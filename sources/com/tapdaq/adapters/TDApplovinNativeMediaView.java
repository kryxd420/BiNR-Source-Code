package com.tapdaq.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;
import com.tapdaq.sdk.helpers.TLog;
import java.io.IOException;

public class TDApplovinNativeMediaView extends FrameLayout implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener {
    private AppLovinNativeAd mAd;
    private MediaPlayer mMediaPlayer;
    private AppLovinPostbackService mPostbackService;
    private SurfaceHolder mSurfaceHolder;

    public TDApplovinNativeMediaView(Context context) {
        super(context);
        initialise();
    }

    public TDApplovinNativeMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        initialise();
    }

    public TDApplovinNativeMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialise();
    }

    @RequiresApi(21)
    public TDApplovinNativeMediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialise();
    }

    private void initialise() {
        SurfaceView surfaceView = new SurfaceView(getContext());
        addView(surfaceView, new FrameLayout.LayoutParams(-1, -1));
        surfaceView.getHolder().addCallback(this);
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnCompletionListener(this);
    }

    public void setNativeAd(AppLovinNativeAd appLovinNativeAd, AppLovinPostbackService appLovinPostbackService) {
        this.mAd = appLovinNativeAd;
        this.mPostbackService = appLovinPostbackService;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        this.mMediaPlayer.setDisplay(surfaceHolder);
        if (this.mAd != null && this.mAd.getVideoUrl() != null) {
            playVideo();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mSurfaceHolder = surfaceHolder;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = null;
    }

    /* access modifiers changed from: package-private */
    public MediaPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    /* access modifiers changed from: package-private */
    public void playVideo() {
        if (this.mAd.getVideoUrl() != null && this.mAd.isVideoPrecached() && this.mSurfaceHolder != null) {
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setDataSource(getContext(), Uri.parse(this.mAd.getVideoUrl()));
                this.mMediaPlayer.prepare();
                this.mMediaPlayer.start();
                this.mPostbackService.dispatchPostbackAsync(this.mAd.getVideoStartTrackingUrl(), (AppLovinPostbackListener) null);
            } catch (IOException e) {
                TLog.error((Exception) e);
            }
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        String videoEndTrackingUrl = this.mAd.getVideoEndTrackingUrl(calculatePercentViewed(this.mMediaPlayer), true);
        if (videoEndTrackingUrl != null) {
            this.mPostbackService.dispatchPostbackAsync(videoEndTrackingUrl, (AppLovinPostbackListener) null);
        }
        this.mMediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) null);
    }

    private int calculatePercentViewed(MediaPlayer mediaPlayer) {
        return (int) Math.floor((double) ((((float) mediaPlayer.getCurrentPosition()) / ((float) mediaPlayer.getDuration())) * 100.0f));
    }
}
