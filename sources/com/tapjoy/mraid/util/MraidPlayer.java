package com.tapjoy.mraid.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.listener.Player;

public class MraidPlayer extends VideoView implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static String h = "Loading. Please Wait..";
    private static String i = "MRAID Player";
    private Abstract.PlayerProperties a;
    private AudioManager b = ((AudioManager) getContext().getSystemService("audio"));
    private Player c;
    private int d;
    private String e;
    private RelativeLayout f;
    private ImageButton g;
    private boolean j;

    public MraidPlayer(Context context) {
        super(context);
    }

    public void setPlayData(Abstract.PlayerProperties playerProperties, String str) {
        this.j = false;
        this.a = playerProperties;
        this.e = str;
    }

    public void playAudio() {
        a();
    }

    public ImageButton getCloseImageButton() {
        return this.g;
    }

    private void a() {
        this.e = this.e.trim();
        this.e = Utils.convert(this.e);
        if (this.e != null || this.c == null) {
            setVideoURI(Uri.parse(this.e));
            TapjoyLog.d("player", Uri.parse(this.e).toString());
            if (this.a.showControl()) {
                MediaController mediaController = new MediaController(getContext());
                setMediaController(mediaController);
                mediaController.setAnchorView(this);
            }
            setOnCompletionListener(this);
            setOnErrorListener(this);
            setOnPreparedListener(this);
            if (!this.a.inline && !this.a.inline) {
                this.f = new RelativeLayout(getContext());
                this.f.setLayoutParams(getLayoutParams());
                TextView textView = new TextView(getContext());
                textView.setText(h);
                textView.setTextColor(-1);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                this.f.addView(textView, layoutParams);
                ((ViewGroup) getParent()).addView(this.f);
            }
            if (this.a.isAutoPlay()) {
                start();
                return;
            }
            return;
        }
        b();
        this.c.onError();
    }

    public void playVideo() {
        if (this.a.doMute()) {
            this.d = this.b.getStreamVolume(3);
            this.b.setStreamVolume(3, 0, 4);
        }
        a();
    }

    public void setListener(Player player) {
        this.c = player;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.a.doLoop()) {
            start();
        } else if (this.a.exitOnComplete() || this.a.inline) {
            releasePlayer();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        String str = i;
        TapjoyLog.i(str, "Player error : " + i2);
        c();
        b();
        if (this.c == null) {
            return false;
        }
        this.c.onError();
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        c();
        if (this.c != null) {
            this.c.onPrepared();
        }
    }

    private void b() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    public void releasePlayer() {
        if (!this.j) {
            this.j = true;
            stopPlayback();
            b();
            if (this.a != null && this.a.doMute()) {
                this.b.setStreamVolume(3, this.d, 4);
            }
            if (this.c != null) {
                this.c.onComplete();
            }
        }
    }

    private void c() {
        if (this.f != null) {
            ((ViewGroup) getParent()).removeView(this.f);
        }
    }
}
