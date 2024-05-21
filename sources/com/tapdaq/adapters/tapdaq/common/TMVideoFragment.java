package com.tapdaq.adapters.tapdaq.common;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.tapdaq.sdk.helpers.TLog;
import java.io.IOException;

public class TMVideoFragment extends Fragment implements SurfaceHolder.Callback {
    private boolean mLoop;
    private int mPosition = 0;
    private MediaPlayer.OnPreparedListener mPreparedListener;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private String mUrl;
    private MediaPlayer mp;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View createLayout = createLayout();
        this.mp = new MediaPlayer();
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        createLayout.setLayoutParams(layoutParams);
        if (bundle != null && bundle.containsKey("Position")) {
            this.mPosition = bundle.getInt("Position");
        }
        return createLayout;
    }

    private View createLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        this.mSurfaceView = new SurfaceView(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        relativeLayout.addView(this.mSurfaceView, layoutParams);
        return relativeLayout;
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mp != null) {
            int currentPosition = this.mp.getCurrentPosition();
            int duration = this.mp.getDuration();
            bundle.putInt("Position", currentPosition);
            bundle.putInt("Duration", duration);
        }
        super.onSaveInstanceState(bundle);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mPreparedListener = onPreparedListener;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mp != null) {
            this.mp.release();
        }
        this.mp = null;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        TLog.debug("Surface Created");
        this.mp.setDisplay(surfaceHolder);
        if (this.mUrl != null) {
            playVideo(this.mUrl, this.mLoop);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        TLog.debug("Surface changed");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        TLog.debug("Surface Destroyed");
    }

    public MediaPlayer playVideo(String str) {
        return playVideo(str, false);
    }

    public MediaPlayer playVideo(String str, boolean z) {
        if (this.mUrl != null) {
            try {
                this.mp.setOnPreparedListener(this.mPreparedListener);
                this.mp.setAudioStreamType(3);
                this.mp.setDataSource(getActivity(), Uri.parse(str));
                this.mp.prepare();
                this.mp.start();
                this.mp.setLooping(z);
                if (this.mPosition != 0) {
                    this.mp.seekTo(this.mPosition);
                }
            } catch (IOException e) {
                TLog.error((Exception) e);
            }
            this.mUrl = null;
            return this.mp;
        }
        if (!(this.mp == null || this.mPosition == 0)) {
            this.mp.seekTo(this.mPosition);
        }
        this.mLoop = z;
        this.mUrl = str;
        return this.mp;
    }
}
