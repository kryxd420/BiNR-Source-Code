package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinSdkUtils;

class m extends FrameLayout implements TextureView.SurfaceTextureListener, q {
    private final p a;
    private final TextureView b;
    private final MediaPlayer c = new MediaPlayer();
    private final Runnable d;
    private int e;
    private int f;
    private int g;

    m(j jVar, Context context, Runnable runnable) {
        super(context);
        this.a = jVar.v();
        this.d = runnable;
        this.b = new TextureView(context);
        this.b.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        this.b.setSurfaceTextureListener(this);
        addView(this.b);
    }

    private void a() {
        AppLovinSdkUtils.runOnUiThreadDelayed(this.d, 250);
    }

    public int getCurrentPosition() {
        return this.c.getCurrentPosition();
    }

    public int getDuration() {
        return this.c.getDuration();
    }

    public boolean isPlaying() {
        return this.c.isPlaying();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Surface surface = new Surface(surfaceTexture);
        try {
            this.c.setSurface(surface);
            this.c.setAudioStreamType(3);
            this.c.prepareAsync();
        } catch (Throwable th) {
            this.a.b("TextureVideoView", "Failed to prepare media player", th);
            surface.release();
            a();
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void pause() {
        this.c.pause();
    }

    public void seekTo(int i) {
        this.c.seekTo(i);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.c.setOnCompletionListener(onCompletionListener);
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.c.setOnErrorListener(onErrorListener);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.c.setOnPreparedListener(onPreparedListener);
    }

    public void setVideoSize(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int e2 = n.e(getContext());
        if (this.e == 0) {
            i4 = this.b.getWidth();
            i3 = this.b.getHeight();
            this.e = e2;
            this.f = i4;
            this.g = i3;
        } else if (e2 == this.e) {
            i4 = this.f;
            i3 = this.g;
        } else {
            i4 = this.g;
            i3 = this.f;
        }
        float f2 = ((float) i2) / ((float) i);
        float f3 = (float) i4;
        int i6 = (int) (f3 * f2);
        if (i3 >= i6) {
            i5 = i4;
        } else {
            i5 = (int) (((float) i3) / f2);
            i6 = i3;
        }
        try {
            Matrix matrix = new Matrix();
            this.b.getTransform(matrix);
            matrix.setScale(((float) i5) / f3, ((float) i6) / ((float) i3));
            matrix.postTranslate((float) ((i4 - i5) / 2), (float) ((i3 - i6) / 2));
            this.b.setTransform(matrix);
            invalidate();
            requestLayout();
        } catch (Throwable unused) {
            p pVar = this.a;
            pVar.d("TextureVideoView", "Failed to set video size to width: " + i + " height: " + i2);
            a();
        }
    }

    public void setVideoURI(Uri uri) {
        try {
            this.c.setDataSource(uri.toString());
        } catch (Throwable th) {
            p pVar = this.a;
            pVar.b("TextureVideoView", "Failed to set video URI: " + uri, th);
            a();
        }
    }

    public void start() {
        this.c.start();
    }

    public void stopPlayback() {
        this.c.stop();
    }
}
