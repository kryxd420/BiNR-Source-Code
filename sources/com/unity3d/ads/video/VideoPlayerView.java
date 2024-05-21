package com.unity3d.ads.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayerView extends VideoView {
    private boolean _infoListenerEnabled = true;
    /* access modifiers changed from: private */
    public MediaPlayer _mediaPlayer = null;
    private Timer _prepareTimer;
    private int _progressEventInterval = TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL;
    private Timer _videoTimer;
    /* access modifiers changed from: private */
    public String _videoUrl;
    private Float _volume = null;

    public VideoPlayerView(Context context) {
        super(context);
    }

    private void startVideoProgressTimer() {
        this._videoTimer = new Timer();
        this._videoTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                boolean z;
                try {
                    z = VideoPlayerView.this.isPlaying();
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PROGRESS, Integer.valueOf(VideoPlayerView.this.getCurrentPosition()));
                    } catch (IllegalStateException e) {
                        e = e;
                    }
                } catch (IllegalStateException e2) {
                    e = e2;
                    z = false;
                    DeviceLog.exception("Exception while sending current position to webapp", e);
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.ILLEGAL_STATE, VideoPlayerEvent.PROGRESS, VideoPlayerView.this._videoUrl, Boolean.valueOf(z));
                }
            }
        }, (long) this._progressEventInterval, (long) this._progressEventInterval);
    }

    public void stopVideoProgressTimer() {
        if (this._videoTimer != null) {
            this._videoTimer.cancel();
            this._videoTimer.purge();
            this._videoTimer = null;
        }
    }

    private void startPrepareTimer(long j) {
        this._prepareTimer = new Timer();
        this._prepareTimer.schedule(new TimerTask() {
            public void run() {
                if (!VideoPlayerView.this.isPlaying()) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_TIMEOUT, VideoPlayerView.this._videoUrl);
                    DeviceLog.error("Video player prepare timeout: " + VideoPlayerView.this._videoUrl);
                }
            }
        }, j);
    }

    public void stopPrepareTimer() {
        if (this._prepareTimer != null) {
            this._prepareTimer.cancel();
            this._prepareTimer.purge();
            this._prepareTimer = null;
        }
    }

    public boolean prepare(String str, final float f, int i) {
        DeviceLog.entered();
        this._videoUrl = str;
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoPlayerView.this.stopPrepareTimer();
                if (mediaPlayer != null) {
                    MediaPlayer unused = VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                VideoPlayerView.this.setVolume(Float.valueOf(f));
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARED, VideoPlayerView.this._videoUrl, Integer.valueOf(mediaPlayer.getDuration()), Integer.valueOf(mediaPlayer.getVideoWidth()), Integer.valueOf(mediaPlayer.getVideoHeight()));
            }
        });
        setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                VideoPlayerView.this.stopPrepareTimer();
                if (mediaPlayer != null) {
                    MediaPlayer unused = VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.GENERIC_ERROR, VideoPlayerView.this._videoUrl, Integer.valueOf(i), Integer.valueOf(i2));
                VideoPlayerView.this.stopVideoProgressTimer();
                return true;
            }
        });
        setInfoListenerEnabled(this._infoListenerEnabled);
        if (i > 0) {
            startPrepareTimer((long) i);
        }
        try {
            setVideoPath(this._videoUrl);
            return true;
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_ERROR, this._videoUrl);
            DeviceLog.exception("Error preparing video: " + this._videoUrl, e);
            return false;
        }
    }

    public void play() {
        DeviceLog.entered();
        setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mediaPlayer != null) {
                    MediaPlayer unused = VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.COMPLETED, VideoPlayerView.this._videoUrl);
                VideoPlayerView.this.stopVideoProgressTimer();
            }
        });
        start();
        stopVideoProgressTimer();
        startVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PLAY, this._videoUrl);
    }

    public void setInfoListenerEnabled(boolean z) {
        this._infoListenerEnabled = z;
        if (Build.VERSION.SDK_INT <= 16) {
            return;
        }
        if (this._infoListenerEnabled) {
            setOnInfoListener(new MediaPlayer.OnInfoListener() {
                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, VideoPlayerView.this._videoUrl, Integer.valueOf(i), Integer.valueOf(i2));
                    return true;
                }
            });
        } else {
            setOnInfoListener((MediaPlayer.OnInfoListener) null);
        }
    }

    public void pause() {
        try {
            super.pause();
            stopVideoProgressTimer();
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE, this._videoUrl);
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE_ERROR, this._videoUrl);
            DeviceLog.exception("Error pausing video", e);
        }
    }

    public void seekTo(int i) {
        try {
            super.seekTo(i);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO, this._videoUrl);
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO_ERROR, this._videoUrl);
            DeviceLog.exception("Error seeking video", e);
        }
    }

    public void stop() {
        stopPlayback();
        stopVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.STOP, this._videoUrl);
    }

    public float getVolume() {
        return this._volume.floatValue();
    }

    public void setVolume(Float f) {
        try {
            this._mediaPlayer.setVolume(f.floatValue(), f.floatValue());
            this._volume = f;
        } catch (Exception e) {
            DeviceLog.exception("MediaPlayer generic error", e);
        }
    }

    public void setProgressEventInterval(int i) {
        this._progressEventInterval = i;
        if (this._videoTimer != null) {
            stopVideoProgressTimer();
            startVideoProgressTimer();
        }
    }

    public int getProgressEventInterval() {
        return this._progressEventInterval;
    }
}
