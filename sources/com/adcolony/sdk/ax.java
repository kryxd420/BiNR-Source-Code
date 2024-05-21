package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import com.tapjoy.TJAdUnitConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

class ax extends TextureView implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, TextureView.SurfaceTextureListener {
    /* access modifiers changed from: private */
    public boolean A;
    private boolean B;
    /* access modifiers changed from: private */
    public boolean C;
    /* access modifiers changed from: private */
    public boolean D;
    private boolean E;
    private String F;
    /* access modifiers changed from: private */
    public String G;
    private FileInputStream H;
    private af I;
    /* access modifiers changed from: private */
    public c J;
    private Surface K;
    private SurfaceTexture L;
    /* access modifiers changed from: private */
    public RectF M = new RectF();
    /* access modifiers changed from: private */
    public a N;
    private ProgressBar O;
    /* access modifiers changed from: private */
    public MediaPlayer P;
    /* access modifiers changed from: private */
    public JSONObject Q = y.a();
    private ExecutorService R = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public af S;
    private float a;
    private float b;
    /* access modifiers changed from: private */
    public float c;
    /* access modifiers changed from: private */
    public float d;
    private float e;
    private float f;
    /* access modifiers changed from: private */
    public int g;
    private boolean h = true;
    /* access modifiers changed from: private */
    public Paint i = new Paint();
    /* access modifiers changed from: private */
    public Paint j = new Paint(1);
    private int k;
    private int l;
    private int m;
    private int n;
    /* access modifiers changed from: private */
    public int o;
    private int p;
    private int q;
    private int r;
    /* access modifiers changed from: private */
    public double s;
    /* access modifiers changed from: private */
    public double t;
    /* access modifiers changed from: private */
    public long u;
    /* access modifiers changed from: private */
    public boolean v;
    /* access modifiers changed from: private */
    public boolean w;
    /* access modifiers changed from: private */
    public boolean x;
    /* access modifiers changed from: private */
    public boolean y;
    private boolean z;

    ax(Context context, af afVar, int i2, c cVar) {
        super(context);
        this.J = cVar;
        this.I = afVar;
        this.o = i2;
        setSurfaceTextureListener(this);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.L != null) {
            this.A = true;
        }
        this.R.shutdown();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        Activity c2;
        JSONObject c3 = this.I.c();
        this.G = y.b(c3, "ad_session_id");
        this.k = y.c(c3, AvidJSONUtil.KEY_X);
        this.l = y.c(c3, AvidJSONUtil.KEY_Y);
        this.m = y.c(c3, AvidJSONUtil.KEY_WIDTH);
        this.n = y.c(c3, AvidJSONUtil.KEY_HEIGHT);
        this.C = y.d(c3, "enable_timer");
        this.E = y.d(c3, "enable_progress");
        this.F = y.b(c3, "filepath");
        this.p = y.c(c3, "video_width");
        this.q = y.c(c3, "video_height");
        this.f = a.a().c.o();
        new aa.a().a("Original video dimensions = ").a(this.p).a(AvidJSONUtil.KEY_X).a(this.q).a(aa.b);
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.m, this.n);
        layoutParams.setMargins(this.k, this.l, 0, 0);
        layoutParams.gravity = 0;
        this.J.addView(this, layoutParams);
        if (this.E && (c2 = a.c()) != null) {
            this.O = new ProgressBar(c2);
            this.J.addView(this.O, new FrameLayout.LayoutParams((int) (this.f * 100.0f), (int) (this.f * 100.0f), 17));
        }
        this.P = new MediaPlayer();
        this.z = false;
        try {
            if (!this.F.startsWith("http")) {
                this.H = new FileInputStream(this.F);
                this.P.setDataSource(this.H.getFD());
            } else {
                this.B = true;
                this.P.setDataSource(this.F);
            }
            this.P.setOnErrorListener(this);
            this.P.setOnPreparedListener(this);
            this.P.setOnCompletionListener(this);
            this.P.prepareAsync();
        } catch (IOException e2) {
            new aa.a().a("Failed to create/prepare MediaPlayer: ").a(e2.toString()).a(aa.g);
            k();
        }
        this.J.n().add(a.a("VideoView.play", (ah) new ah() {
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.e();
                }
            }
        }, true));
        this.J.n().add(a.a("VideoView.set_bounds", (ah) new ah() {
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.b(afVar);
                }
            }
        }, true));
        this.J.n().add(a.a("VideoView.set_visible", (ah) new ah() {
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.c(afVar);
                }
            }
        }, true));
        this.J.n().add(a.a("VideoView.pause", (ah) new ah() {
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.f();
                }
            }
        }, true));
        this.J.n().add(a.a("VideoView.seek_to_time", (ah) new ah() {
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    boolean unused = ax.this.e(afVar);
                }
            }
        }, true));
        this.J.n().add(a.a("VideoView.set_volume", (ah) new ah() {
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    boolean unused = ax.this.d(afVar);
                }
            }
        }, true));
        this.J.o().add("VideoView.play");
        this.J.o().add("VideoView.set_bounds");
        this.J.o().add("VideoView.set_visible");
        this.J.o().add("VideoView.pause");
        this.J.o().add("VideoView.seek_to_time");
        this.J.o().add("VideoView.set_volume");
    }

    /* access modifiers changed from: private */
    public boolean a(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "id") == this.o && y.c(c2, "container_id") == this.J.d() && y.b(c2, "ad_session_id").equals(this.J.b());
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        if (surfaceTexture == null || this.A) {
            new aa.a().a("Null texture provided by system's onSurfaceTextureAvailable or ").a("MediaPlayer has been destroyed.").a(aa.h);
            return;
        }
        this.K = new Surface(surfaceTexture);
        try {
            this.P.setSurface(this.K);
        } catch (IllegalStateException unused) {
            new aa.a().a("IllegalStateException thrown when calling MediaPlayer.setSurface()").a(aa.g);
            k();
        }
        this.L = surfaceTexture;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.L = surfaceTexture;
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.L = surfaceTexture;
        if (!this.A) {
            return false;
        }
        surfaceTexture.release();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.L = surfaceTexture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        l a2 = a.a();
        d m2 = a2.m();
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        JSONObject a3 = y.a();
        y.b(a3, "view_id", this.o);
        y.a(a3, "ad_session_id", this.G);
        y.b(a3, "container_x", this.k + x2);
        y.b(a3, "container_y", this.l + y2);
        y.b(a3, "view_x", x2);
        y.b(a3, "view_y", y2);
        y.b(a3, "id", this.J.d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.J.c(), a3).b();
                break;
            case 1:
                if (!this.J.r()) {
                    a2.a(m2.f().get(this.G));
                }
                new af("AdContainer.on_touch_ended", this.J.c(), a3).b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.J.c(), a3).b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.J.c(), a3).b();
                break;
            case 5:
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action2)) + this.k);
                y.b(a3, "container_y", ((int) motionEvent.getY(action2)) + this.l);
                y.b(a3, "view_x", (int) motionEvent.getX(action2));
                y.b(a3, "view_y", (int) motionEvent.getY(action2));
                new af("AdContainer.on_touch_began", this.J.c(), a3).b();
                break;
            case 6:
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action3)) + this.k);
                y.b(a3, "container_y", ((int) motionEvent.getY(action3)) + this.l);
                y.b(a3, "view_x", (int) motionEvent.getX(action3));
                y.b(a3, "view_y", (int) motionEvent.getY(action3));
                if (!this.J.r()) {
                    a2.a(m2.f().get(this.G));
                }
                new af("AdContainer.on_touch_ended", this.J.c(), a3).b();
                break;
        }
        return true;
    }

    public void onMeasure(int i2, int i3) {
        double d2 = (double) this.m;
        double d3 = (double) this.p;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        double d5 = (double) this.n;
        double d6 = (double) this.q;
        Double.isNaN(d5);
        Double.isNaN(d6);
        double d7 = d5 / d6;
        if (d4 > d7) {
            d4 = d7;
        }
        double d8 = (double) this.p;
        Double.isNaN(d8);
        int i4 = (int) (d8 * d4);
        double d9 = (double) this.q;
        Double.isNaN(d9);
        int i5 = (int) (d9 * d4);
        new aa.a().a("setMeasuredDimension to ").a(i4).a(" by ").a(i5).a(aa.d);
        setMeasuredDimension(i4, i5);
        if (this.B) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.width = i4;
            layoutParams.height = i5;
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 0, 0);
            setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        JSONObject a2 = y.a();
        y.a(a2, "id", this.G);
        new af("AdSession.on_error", this.J.c(), a2).b();
        this.v = true;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        k();
        aa.a aVar = new aa.a();
        aVar.a("MediaPlayer error: " + i2 + "," + i3).a(aa.g);
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.z = true;
        if (this.E) {
            this.J.removeView(this.O);
        }
        if (this.B) {
            this.p = mediaPlayer.getVideoWidth();
            this.q = mediaPlayer.getVideoHeight();
            onMeasure(this.p, this.q);
            new aa.a().a("MediaPlayer getVideoWidth = ").a(mediaPlayer.getVideoWidth()).a(aa.d);
            new aa.a().a("MediaPlayer getVideoHeight = ").a(mediaPlayer.getVideoHeight()).a(aa.d);
        }
        JSONObject a2 = y.a();
        y.b(a2, "id", this.o);
        y.b(a2, "container_id", this.J.d());
        y.a(a2, "ad_session_id", this.G);
        new aa.a().a("ADCVideoView is prepared").a(aa.b);
        new af("VideoView.on_ready", this.J.c(), a2).b();
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.v = true;
        this.s = this.t;
        y.b(this.Q, "id", this.o);
        y.b(this.Q, "container_id", this.J.d());
        y.a(this.Q, "ad_session_id", this.G);
        y.a(this.Q, "elapsed", this.s);
        y.a(this.Q, "duration", this.t);
        new af("VideoView.on_progress", this.J.c(), this.Q).b();
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.R != null && !this.R.isShutdown()) {
            try {
                this.R.submit(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (ax.this.S != null) {
                            JSONObject a2 = y.a();
                            y.b(a2, "id", ax.this.o);
                            y.a(a2, "ad_session_id", ax.this.G);
                            y.a(a2, "success", true);
                            ax.this.S.a(a2).b();
                            af unused = ax.this.S = null;
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
                k();
            }
        }
    }

    private void l() {
        try {
            this.R.submit(new Runnable() {
                public void run() {
                    long unused = ax.this.u = 0;
                    while (!ax.this.v && !ax.this.y && a.d()) {
                        Activity c = a.c();
                        if (!ax.this.v && !ax.this.A && c != null) {
                            if (ax.this.P.isPlaying()) {
                                if (ax.this.u == 0 && a.b) {
                                    long unused2 = ax.this.u = System.currentTimeMillis();
                                }
                                boolean unused3 = ax.this.x = true;
                                ax axVar = ax.this;
                                double currentPosition = (double) ax.this.P.getCurrentPosition();
                                Double.isNaN(currentPosition);
                                double unused4 = axVar.s = currentPosition / 1000.0d;
                                ax axVar2 = ax.this;
                                double duration = (double) ax.this.P.getDuration();
                                Double.isNaN(duration);
                                double unused5 = axVar2.t = duration / 1000.0d;
                                if (System.currentTimeMillis() - ax.this.u > 1000 && !ax.this.D && a.b) {
                                    if (ax.this.s == 0.0d) {
                                        new aa.a().a("getCurrentPosition() not working, firing ").a("AdSession.on_error").a(aa.h);
                                        ax.this.k();
                                    } else {
                                        boolean unused6 = ax.this.D = true;
                                    }
                                }
                                if (ax.this.C) {
                                    ax.this.c();
                                }
                            }
                            if (ax.this.x && !ax.this.v && !ax.this.y) {
                                y.b(ax.this.Q, "id", ax.this.o);
                                y.b(ax.this.Q, "container_id", ax.this.J.d());
                                y.a(ax.this.Q, "ad_session_id", ax.this.G);
                                y.a(ax.this.Q, "elapsed", ax.this.s);
                                y.a(ax.this.Q, "duration", ax.this.t);
                                new af("VideoView.on_progress", ax.this.J.c(), ax.this.Q).b();
                            }
                            if (ax.this.w || c.isFinishing()) {
                                boolean unused7 = ax.this.w = false;
                                ax.this.d();
                                return;
                            }
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException unused8) {
                                ax.this.k();
                                new aa.a().a("InterruptedException in ADCVideoView's update thread.").a(aa.g);
                            }
                        } else {
                            return;
                        }
                    }
                    if (ax.this.w) {
                        ax.this.d();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            k();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.h) {
            this.e = (float) (360.0d / this.t);
            this.j.setColor(-3355444);
            this.j.setShadowLayer((float) ((int) (this.f * 2.0f)), 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            this.j.setTextAlign(Paint.Align.CENTER);
            this.j.setLinearText(true);
            this.j.setTextSize(this.f * 12.0f);
            this.i.setStyle(Paint.Style.STROKE);
            float f2 = 6.0f;
            if (this.f * 2.0f <= 6.0f) {
                f2 = this.f * 2.0f;
            }
            float f3 = 4.0f;
            if (f2 >= 4.0f) {
                f3 = f2;
            }
            this.i.setStrokeWidth(f3);
            this.i.setShadowLayer((float) ((int) (this.f * 3.0f)), 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            this.i.setColor(-3355444);
            Rect rect = new Rect();
            this.j.getTextBounds("0123456789", 0, 9, rect);
            this.c = (float) rect.height();
            final Activity c2 = a.c();
            if (c2 != null) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        a unused = ax.this.N = new a(c2);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (ax.this.c * 4.0f), (int) (ax.this.c * 4.0f));
                        layoutParams.setMargins(0, ax.this.J.p() - ((int) (ax.this.c * 4.0f)), 0, 0);
                        layoutParams.gravity = 0;
                        ax.this.J.addView(ax.this.N, layoutParams);
                    }
                });
            }
            this.h = false;
        }
        this.g = (int) (this.t - this.s);
        this.a = (float) ((int) this.c);
        this.b = (float) ((int) (this.c * 3.0f));
        this.M.set(this.a - (this.c / 2.0f), this.b - (this.c * 2.0f), this.a + (this.c * 2.0f), this.b + (this.c / 2.0f));
        double d2 = (double) this.e;
        Double.isNaN(d2);
        this.d = (float) (d2 * (this.t - this.s));
    }

    /* access modifiers changed from: package-private */
    public void d() {
        new aa.a().a("MediaPlayer stopped and released.").a(aa.d);
        try {
            if (!this.v && this.z && this.P.isPlaying()) {
                this.P.stop();
            }
        } catch (IllegalStateException unused) {
            new aa.a().a("Caught IllegalStateException when calling stop on MediaPlayer").a(aa.f);
        }
        if (this.O != null) {
            this.J.removeView(this.O);
        }
        this.v = true;
        this.z = false;
        this.P.release();
    }

    /* access modifiers changed from: private */
    public void b(af afVar) {
        JSONObject c2 = afVar.c();
        this.k = y.c(c2, AvidJSONUtil.KEY_X);
        this.l = y.c(c2, AvidJSONUtil.KEY_Y);
        this.m = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.n = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.k, this.l, 0, 0);
        layoutParams.width = this.m;
        layoutParams.height = this.n;
        setLayoutParams(layoutParams);
        if (this.C && this.N != null) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams((int) (this.c * 4.0f), (int) (this.c * 4.0f));
            layoutParams2.setMargins(0, this.J.p() - ((int) (this.c * 4.0f)), 0, 0);
            layoutParams2.gravity = 0;
            this.N.setLayoutParams(layoutParams2);
        }
    }

    /* access modifiers changed from: private */
    public void c(af afVar) {
        if (y.d(afVar.c(), TJAdUnitConstants.String.VISIBLE)) {
            setVisibility(0);
            if (this.C && this.N != null) {
                this.N.setVisibility(0);
                return;
            }
            return;
        }
        setVisibility(4);
        if (this.C && this.N != null) {
            this.N.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public boolean d(af afVar) {
        boolean z2 = false;
        if (!this.z) {
            return false;
        }
        float e2 = (float) y.e(afVar.c(), AvidVideoPlaybackListenerImpl.VOLUME);
        AdColonyInterstitial u2 = a.a().u();
        if (u2 != null) {
            if (((double) e2) <= 0.0d) {
                z2 = true;
            }
            u2.b(z2);
        }
        this.P.setVolume(e2, e2);
        JSONObject a2 = y.a();
        y.a(a2, "success", true);
        afVar.a(a2).b();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        if (!this.z) {
            return false;
        }
        if (!this.y && a.b) {
            this.P.start();
            l();
            new aa.a().a("MediaPlayer is prepared - ADCVideoView play() called.").a(aa.b);
        } else if (!this.v && a.b) {
            this.P.start();
            this.y = false;
            if (!this.R.isShutdown()) {
                l();
            }
            if (this.N != null) {
                this.N.invalidate();
            }
        }
        setWillNotDraw(false);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        if (!this.z) {
            new aa.a().a("ADCVideoView pause() called while MediaPlayer is not prepared.").a(aa.f);
            return false;
        } else if (!this.x) {
            new aa.a().a("Ignoring ADCVideoView pause due to invalid MediaPlayer state.").a(aa.d);
            return false;
        } else {
            this.r = this.P.getCurrentPosition();
            this.t = (double) this.P.getDuration();
            this.P.pause();
            this.y = true;
            new aa.a().a("Video view paused").a(aa.b);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public boolean e(af afVar) {
        if (!this.z) {
            return false;
        }
        if (this.v) {
            this.v = false;
        }
        this.S = afVar;
        int c2 = y.c(afVar.c(), "time");
        int duration = this.P.getDuration() / 1000;
        this.P.setOnSeekCompleteListener(this);
        this.P.seekTo(c2 * 1000);
        if (duration == c2) {
            this.v = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.w = true;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.P != null;
    }

    /* access modifiers changed from: package-private */
    public MediaPlayer i() {
        return this.P;
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return this.v;
    }

    private class a extends View {
        a(Context context) {
            super(context);
            setWillNotDraw(false);
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{1, null});
            } catch (Exception unused) {
            }
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(ax.this.M, 270.0f, ax.this.d, false, ax.this.i);
            float centerX = ax.this.M.centerX();
            double centerY = (double) ax.this.M.centerY();
            double d = (double) ax.this.j.getFontMetrics().bottom;
            Double.isNaN(d);
            Double.isNaN(centerY);
            canvas.drawText("" + ax.this.g, centerX, (float) (centerY + (d * 1.35d)), ax.this.j);
            invalidate();
        }
    }
}
