package com.tapdaq.adapters.tapdaq.common;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TMVideoActivity extends Activity {
    /* access modifiers changed from: private */
    public int mDuration = 0;
    protected Handler mHandler;
    protected OnMediaCompletionListener mMediaCompletionListener;
    protected MediaPlayer mPlayer;
    /* access modifiers changed from: private */
    public int mPosition = 0;
    protected ScheduledFuture mScheduled;

    /* access modifiers changed from: protected */
    public void onComplete() {
    }

    /* access modifiers changed from: protected */
    public void updateMediaPlayerUI() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        this.mMediaCompletionListener = new OnMediaCompletionListener();
        this.mHandler = new Handler(getMainLooper()) {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TMVideoActivity.this.updateMediaPlayerUI();
            }
        };
        if (bundle != null && bundle.containsKey("Position") && bundle.containsKey("Duration")) {
            this.mPosition = bundle.getInt("Position", 0);
            this.mDuration = bundle.getInt("Duration", 0);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mPlayer != null) {
            int currentPosition = this.mPlayer.getCurrentPosition();
            int duration = this.mPlayer.getDuration();
            bundle.putInt("Position", currentPosition);
            bundle.putInt("Duration", duration);
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mScheduled != null) {
            this.mScheduled.cancel(true);
        }
        this.mHandler = null;
        if (this.mPlayer != null) {
            this.mPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) null);
        }
        this.mMediaCompletionListener = null;
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void setupMediaPlayerMonitor() {
        this.mScheduled = Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            public void run() {
                TMVideoActivity.this.mHandler.sendMessage(TMVideoActivity.this.mHandler.obtainMessage());
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    protected class OnMediaCompletionListener implements MediaPlayer.OnCompletionListener {
        protected OnMediaCompletionListener() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            int unused = TMVideoActivity.this.mDuration = TMVideoActivity.this.mDuration == 0 ? mediaPlayer.getCurrentPosition() : TMVideoActivity.this.mDuration;
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition > 0) {
                int unused2 = TMVideoActivity.this.mPosition = currentPosition;
            }
            if (TMVideoActivity.this.mPosition > 0 && TMVideoActivity.this.mDuration > 0 && TMVideoActivity.this.mPosition > TMVideoActivity.this.mDuration - 1000) {
                TMVideoActivity.this.onComplete();
            }
        }
    }
}
