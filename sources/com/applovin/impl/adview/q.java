package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

interface q {
    int getCurrentPosition();

    int getDuration();

    int getHeight();

    int getWidth();

    boolean isPlaying();

    void pause();

    void seekTo(int i);

    void setLayoutParams(ViewGroup.LayoutParams layoutParams);

    void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener);

    void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener);

    void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setVideoSize(int i, int i2);

    void setVideoURI(Uri uri);

    void start();

    void stopPlayback();
}
