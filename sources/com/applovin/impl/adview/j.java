package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.g;
import com.applovin.impl.adview.s;
import com.applovin.impl.sdk.a.d;
import com.applovin.impl.sdk.ad.a;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.ad.i;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.d.aa;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.l;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TapjoyConstants;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class j extends Activity implements h {
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    public static volatile k lastKnownWrapper;
    /* access modifiers changed from: private */
    public f A;
    /* access modifiers changed from: private */
    public View B;
    /* access modifiers changed from: private */
    public f C;
    /* access modifiers changed from: private */
    public View D;
    /* access modifiers changed from: private */
    public e E;
    private ImageView F;
    /* access modifiers changed from: private */
    public WeakReference<MediaPlayer> G = new WeakReference<>((Object) null);
    private d H;
    /* access modifiers changed from: private */
    public r I;
    /* access modifiers changed from: private */
    public ProgressBar J;
    private s.a K;
    private a L;
    private l M;
    private AppLovinAdView a;
    /* access modifiers changed from: private */
    public k b;
    private volatile boolean c = false;
    protected int computedLengthSeconds = 0;
    protected g countdownManager;
    public volatile g currentAd;
    /* access modifiers changed from: private */
    public com.applovin.impl.sdk.c.d d;
    private volatile boolean e = false;
    private volatile boolean f = false;
    /* access modifiers changed from: private */
    public volatile boolean g = false;
    private volatile boolean h = false;
    private volatile boolean i = false;
    private volatile boolean j = false;
    /* access modifiers changed from: private */
    public volatile boolean k = false;
    /* access modifiers changed from: private */
    public volatile boolean l = false;
    public p logger;
    private boolean m = false;
    private volatile boolean n = false;
    private boolean o = true;
    /* access modifiers changed from: private */
    public boolean p = false;
    protected volatile boolean poststitialWasDisplayed = false;
    private boolean q = false;
    private long r = 0;
    /* access modifiers changed from: private */
    public long s = 0;
    public com.applovin.impl.sdk.j sdk;
    private int t = 0;
    private int u = Integer.MIN_VALUE;
    private AtomicBoolean v = new AtomicBoolean(false);
    protected volatile boolean videoMuted = false;
    public q videoView;
    private AtomicBoolean w = new AtomicBoolean(false);
    private Handler x;
    /* access modifiers changed from: private */
    public Handler y;
    private FrameLayout z;

    /* access modifiers changed from: private */
    public void A() {
        if (this.L != null) {
            this.L.b();
        }
    }

    /* access modifiers changed from: private */
    public void B() {
        if (D()) {
            N();
            pauseReportRewardTask();
            this.logger.a("InterActivity", "Prompting incentivized ad close warning");
            this.H.b();
            return;
        }
        skipVideo();
    }

    /* access modifiers changed from: private */
    public void C() {
        c adWebView;
        if (this.currentAd.T() && (adWebView = ((AdViewControllerImpl) this.a.getAdViewController()).getAdWebView()) != null) {
            adWebView.a("javascript:al_onCloseButtonTapped();");
        }
        if (E()) {
            this.logger.a("InterActivity", "Prompting incentivized non-video ad close warning");
            this.H.c();
            return;
        }
        dismiss();
    }

    private boolean D() {
        return H() && !isFullyWatched() && ((Boolean) this.sdk.a(b.bO)).booleanValue() && this.H != null;
    }

    private boolean E() {
        return I() && !G() && ((Boolean) this.sdk.a(b.bT)).booleanValue() && this.H != null;
    }

    private int F() {
        if (!(this.currentAd instanceof a)) {
            return 0;
        }
        float i2 = ((a) this.currentAd).i();
        if (i2 <= 0.0f) {
            i2 = this.currentAd.o();
        }
        double a2 = n.a(System.currentTimeMillis() - this.r);
        double d2 = (double) i2;
        Double.isNaN(d2);
        return (int) Math.min((a2 / d2) * 100.0d, 100.0d);
    }

    private boolean G() {
        return F() >= this.currentAd.Q();
    }

    private boolean H() {
        return AppLovinAdType.INCENTIVIZED.equals(this.currentAd.getType());
    }

    private boolean I() {
        return !this.currentAd.hasVideoUrl() && H();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0066, code lost:
        if (r0 > 0) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0098, code lost:
        if (r0 > 0) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009a, code lost:
        r0 = java.util.concurrent.TimeUnit.SECONDS.toMillis((long) r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void J() {
        /*
            r7 = this;
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            if (r0 == 0) goto L_0x00e6
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            long r0 = r0.ac()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0018
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            int r0 = r0.ad()
            if (r0 < 0) goto L_0x00e6
        L_0x0018:
            com.applovin.impl.sdk.e.l r0 = r7.M
            if (r0 != 0) goto L_0x00e6
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            long r0 = r0.ac()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x002e
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            long r0 = r0.ac()
            goto L_0x00b6
        L_0x002e:
            boolean r0 = r7.isVastAd()
            if (r0 == 0) goto L_0x0069
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            com.applovin.impl.a.a r0 = (com.applovin.impl.a.a) r0
            com.applovin.impl.a.j r1 = r0.a()
            if (r1 == 0) goto L_0x0051
            int r4 = r1.b()
            if (r4 <= 0) goto L_0x0051
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS
            int r1 = r1.b()
            long r5 = (long) r1
            long r4 = r4.toMillis(r5)
        L_0x004f:
            long r2 = r2 + r4
            goto L_0x005b
        L_0x0051:
            com.applovin.impl.adview.q r1 = r7.videoView
            int r1 = r1.getDuration()
            if (r1 <= 0) goto L_0x005b
            long r4 = (long) r1
            goto L_0x004f
        L_0x005b:
            boolean r1 = r0.ae()
            if (r1 == 0) goto L_0x00a2
            float r0 = r0.o()
            int r0 = (int) r0
            if (r0 <= 0) goto L_0x00a2
            goto L_0x009a
        L_0x0069:
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            boolean r0 = r0 instanceof com.applovin.impl.sdk.ad.a
            if (r0 == 0) goto L_0x00a2
            com.applovin.impl.sdk.ad.g r0 = r7.currentAd
            com.applovin.impl.sdk.ad.a r0 = (com.applovin.impl.sdk.ad.a) r0
            com.applovin.impl.adview.q r1 = r7.videoView
            int r1 = r1.getDuration()
            if (r1 <= 0) goto L_0x007d
            long r4 = (long) r1
            long r2 = r2 + r4
        L_0x007d:
            boolean r1 = r0.ae()
            if (r1 == 0) goto L_0x00a2
            float r1 = r0.i()
            int r1 = (int) r1
            if (r1 <= 0) goto L_0x0093
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            long r4 = (long) r1
            long r0 = r0.toMillis(r4)
        L_0x0091:
            long r2 = r2 + r0
            goto L_0x00a2
        L_0x0093:
            float r0 = r0.o()
            int r0 = (int) r0
            if (r0 <= 0) goto L_0x00a2
        L_0x009a:
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS
            long r4 = (long) r0
            long r0 = r1.toMillis(r4)
            goto L_0x0091
        L_0x00a2:
            double r0 = (double) r2
            com.applovin.impl.sdk.ad.g r2 = r7.currentAd
            int r2 = r2.ad()
            double r2 = (double) r2
            r4 = 4636737291354636288(0x4059000000000000, double:100.0)
            java.lang.Double.isNaN(r2)
            double r2 = r2 / r4
            java.lang.Double.isNaN(r0)
            double r0 = r0 * r2
            long r0 = (long) r0
        L_0x00b6:
            com.applovin.impl.sdk.p r2 = r7.logger
            java.lang.String r3 = "InterActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Scheduling report reward in "
            r4.append(r5)
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r5 = r5.toSeconds(r0)
            r4.append(r5)
            java.lang.String r5 = " seconds..."
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.a(r3, r4)
            com.applovin.impl.sdk.j r2 = r7.sdk
            com.applovin.impl.adview.j$15 r3 = new com.applovin.impl.adview.j$15
            r3.<init>()
            com.applovin.impl.sdk.e.l r0 = com.applovin.impl.sdk.e.l.a(r0, r2, r3)
            r7.M = r0
        L_0x00e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.j.J():void");
    }

    private void K() {
        p pVar;
        String str;
        StringBuilder sb;
        String str2;
        if (this.c && !this.q) {
            return;
        }
        if (this.a != null) {
            this.a.setAdDisplayListener(new AppLovinAdDisplayListener() {
                public void adDisplayed(AppLovinAd appLovinAd) {
                    if (!j.this.g) {
                        j.this.a(appLovinAd);
                    }
                }

                public void adHidden(AppLovinAd appLovinAd) {
                    j.this.b(appLovinAd);
                }
            });
            this.a.setAdClickListener(new AppLovinAdClickListener() {
                public void adClicked(AppLovinAd appLovinAd) {
                    h.a(j.this.b.e(), appLovinAd, j.this.sdk);
                }
            });
            this.currentAd = (g) this.b.b();
            if (this.w.compareAndSet(false, true)) {
                this.sdk.o().trackImpression(this.currentAd);
                this.currentAd.setHasShown(true);
            }
            a(this.currentAd);
            k();
            if (this.currentAd.isVideoAd()) {
                this.n = this.currentAd.b();
                if (this.n) {
                    pVar = this.logger;
                    str = "InterActivity";
                    sb = new StringBuilder();
                    str2 = "Preparing stream for ";
                } else {
                    pVar = this.logger;
                    str = "InterActivity";
                    sb = new StringBuilder();
                    str2 = "Preparing cached video playback for ";
                }
                sb.append(str2);
                sb.append(this.currentAd.e());
                pVar.a(str, sb.toString());
                if (this.d != null) {
                    this.d.b(this.n ? 1 : 0);
                }
            }
            this.videoMuted = j();
            Uri e2 = this.currentAd.e();
            a(e2);
            if (e2 == null) {
                J();
            }
            this.A.bringToFront();
            if (o() && this.B != null) {
                this.B.bringToFront();
            }
            if (this.C != null) {
                this.C.bringToFront();
            }
            if (!this.currentAd.an() && !this.currentAd.ao()) {
                this.a.renderAd(this.currentAd);
            }
            this.b.a(true);
            if (!this.currentAd.hasVideoUrl()) {
                if (I() && ((Boolean) this.sdk.a(b.ca)).booleanValue()) {
                    d((AppLovinAd) this.currentAd);
                }
                showPoststitial();
                return;
            }
            return;
        }
        exitWithError("AdView was null");
    }

    private void L() {
        if (this.videoView != null) {
            this.t = getVideoPercentViewed();
            this.videoView.stopPlayback();
        }
    }

    private boolean M() {
        return this.videoMuted;
    }

    private void N() {
        this.sdk.a(com.applovin.impl.sdk.b.d.o, Integer.valueOf(this.videoView != null ? this.videoView.getCurrentPosition() : 0));
        this.sdk.a(com.applovin.impl.sdk.b.d.p, true);
        try {
            this.countdownManager.c();
        } catch (Throwable th) {
            this.logger.b("InterActivity", "Unable to pause countdown timers", th);
        }
        this.videoView.pause();
    }

    private void O() {
        long max = Math.max(0, ((Long) this.sdk.a(b.ds)).longValue());
        if (max > 0) {
            p v2 = this.sdk.v();
            v2.a("InterActivity", "Resuming video with delay of " + max);
            this.y.postDelayed(new Runnable() {
                public void run() {
                    j.this.P();
                }
            }, max);
            return;
        }
        this.sdk.v().a("InterActivity", "Resuming video immediately");
        P();
    }

    /* access modifiers changed from: private */
    public void P() {
        if (!this.poststitialWasDisplayed && this.videoView != null && !this.videoView.isPlaying()) {
            this.videoView.seekTo(((Integer) this.sdk.b(com.applovin.impl.sdk.b.d.o, Integer.valueOf(this.videoView.getDuration()))).intValue());
            this.videoView.start();
            this.countdownManager.a();
        }
    }

    private void Q() {
        if (!this.j) {
            try {
                if (this.currentAd.hasVideoUrl()) {
                    int videoPercentViewed = getVideoPercentViewed();
                    this.sdk.o().trackVideoEnd(this.currentAd, videoPercentViewed, this.n);
                    a((AppLovinAd) this.currentAd, (double) videoPercentViewed, isFullyWatched());
                    if (this.d != null) {
                        this.d.c((long) videoPercentViewed);
                    }
                } else if ((this.currentAd instanceof a) && I() && ((Boolean) this.sdk.a(b.ca)).booleanValue()) {
                    int F2 = F();
                    p pVar = this.logger;
                    pVar.a("InterActivity", "Rewarded playable engaged at " + F2 + " percent");
                    a((AppLovinAd) this.currentAd, (double) F2, F2 >= this.currentAd.Q());
                }
            } catch (Throwable th) {
                if (this.logger != null) {
                    this.logger.b("InterActivity", "Failed to notify end listener.", th);
                }
            }
        }
    }

    private int a(int i2) {
        return AppLovinSdkUtils.dpToPx(this, i2);
    }

    private int a(int i2, boolean z2) {
        if (z2) {
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 1) {
                return 9;
            }
            if (i2 == 2) {
                return 8;
            }
            return i2 == 3 ? 1 : -1;
        } else if (i2 == 0) {
            return 1;
        } else {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 2) {
                return 9;
            }
            return i2 == 3 ? 8 : -1;
        }
    }

    private static int a(Display display) {
        if (display.getWidth() == display.getHeight()) {
            return 3;
        }
        return display.getWidth() < display.getHeight() ? 1 : 2;
    }

    private void a(long j2, final f fVar) {
        this.y.postDelayed(new Runnable() {
            public void run() {
                if (fVar.equals(j.this.A)) {
                    j.this.n();
                } else if (fVar.equals(j.this.C)) {
                    j.this.p();
                }
            }
        }, j2);
    }

    private void a(final Uri uri) {
        this.videoView = this.currentAd.aw() ? new m(this.sdk, this, new Runnable() {
            public void run() {
                j.this.handleMediaError();
            }
        }) : new AppLovinVideoView(this, this.sdk);
        if (uri != null) {
            this.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    WeakReference unused = j.this.G = new WeakReference(mediaPlayer);
                    boolean b = j.this.j();
                    float f = b ^ true ? 1.0f : 0.0f;
                    mediaPlayer.setVolume(f, f);
                    if (j.this.d != null) {
                        j.this.d.e(b ? 1 : 0);
                    }
                    int videoWidth = mediaPlayer.getVideoWidth();
                    int videoHeight = mediaPlayer.getVideoHeight();
                    j.this.computedLengthSeconds = (int) TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration());
                    j.this.videoView.setVideoSize(videoWidth, videoHeight);
                    if (j.this.videoView instanceof AppLovinVideoView) {
                        mediaPlayer.setDisplay(((AppLovinVideoView) j.this.videoView).getHolder());
                    }
                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
                            j.this.y.post(new Runnable() {
                                public void run() {
                                    p pVar = j.this.logger;
                                    pVar.d("InterActivity", "Media player error (" + i + "," + i2 + ").");
                                    j.this.handleMediaError();
                                }
                            });
                            return true;
                        }
                    });
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                            if (i != 3) {
                                if (i == 701) {
                                    j.this.z();
                                    return false;
                                } else if (i != 702) {
                                    return false;
                                }
                            }
                            j.this.A();
                            return false;
                        }
                    });
                    if (j.this.s == 0) {
                        j.this.r();
                        j.this.l();
                        j.this.w();
                        j.this.v();
                        j.this.playVideo();
                        j.this.J();
                    }
                }
            });
            this.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    j.this.i();
                }
            });
            this.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
                    j.this.y.post(new Runnable() {
                        public void run() {
                            p pVar = j.this.logger;
                            pVar.d("InterActivity", "Video view error (" + i + "," + i2 + ").");
                            j.this.handleMediaError();
                        }
                    });
                    return true;
                }
            });
            if (((Boolean) this.sdk.a(b.fo)).booleanValue()) {
                this.sdk.D().a((com.applovin.impl.sdk.d.a) new aa(this.sdk, new Runnable() {
                    public void run() {
                        j.this.videoView.setVideoURI(uri);
                    }
                }), q.a.MAIN);
            } else {
                StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                this.videoView.setVideoURI(uri);
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
        this.videoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        this.videoView.setOnTouchListener(new AppLovinTouchToClickListener(this, new View.OnClickListener() {
            public void onClick(View view) {
                j.this.e();
            }
        }));
        this.z.addView((View) this.videoView);
        setContentView(this.z);
        q();
        y();
    }

    /* access modifiers changed from: private */
    public void a(final View view, final boolean z2, long j2) {
        float f2 = 1.0f;
        float f3 = z2 ? 0.0f : 1.0f;
        if (!z2) {
            f2 = 0.0f;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f2);
        alphaAnimation.setDuration(j2);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (!z2) {
                    view.setVisibility(4);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        view.startAnimation(alphaAnimation);
    }

    private void a(g gVar) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.z = new FrameLayout(this);
        this.z.setLayoutParams(layoutParams);
        this.z.setBackgroundColor(gVar.D());
        this.y = new Handler();
        this.x = new Handler();
        this.countdownManager = new g(this.x, this.sdk);
    }

    /* access modifiers changed from: private */
    public void a(AppLovinAd appLovinAd) {
        h.a(this.b.d(), appLovinAd, this.sdk);
        this.g = true;
        this.sdk.S().c();
        AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
            public void run() {
                j.this.b(j.this.videoMuted);
            }
        }, ((Long) this.sdk.a(b.dw)).longValue());
    }

    private void a(AppLovinAd appLovinAd, double d2, boolean z2) {
        this.j = true;
        h.a(this.b.c(), appLovinAd, d2, z2, this.sdk);
    }

    private void a(String str) {
        g gVar = this.currentAd;
        if (gVar != null && gVar.U()) {
            b(str);
        }
    }

    private void a(boolean z2) {
        Uri ax = z2 ? this.currentAd.ax() : this.currentAd.ay();
        int a2 = a(((Integer) this.sdk.a(b.dl)).intValue());
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        AppLovinSdkUtils.safePopulateImageView(this.F, ax, a2);
        StrictMode.setThreadPolicy(allowThreadDiskReads);
    }

    private boolean a() {
        int identifier = getResources().getIdentifier((String) this.sdk.a(b.de), "bool", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        return identifier > 0 && getResources().getBoolean(identifier);
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void b() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void b(int i2) {
        try {
            setRequestedOrientation(i2);
        } catch (Throwable th) {
            this.sdk.v().b("InterActivity", "Failed to set requested orientation", th);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
        if (r7 == 2) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0063, code lost:
        if (r7 == 1) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if (r7 == 1) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(int r7, boolean r8) {
        /*
            r6 = this;
            com.applovin.impl.sdk.j r0 = r6.sdk
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.b.dc
            java.lang.Object r0 = r0.a(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            com.applovin.impl.adview.k r1 = r6.b
            com.applovin.impl.sdk.ad.g$b r1 = r1.f()
            com.applovin.impl.sdk.ad.g$b r2 = com.applovin.impl.sdk.ad.g.b.ACTIVITY_PORTRAIT
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 != r2) goto L_0x003c
            r1 = 9
            if (r8 == 0) goto L_0x0031
            if (r7 == r5) goto L_0x0029
            if (r7 == r3) goto L_0x0029
        L_0x0023:
            r6.c = r5
        L_0x0025:
            r6.b((int) r5)
            goto L_0x0066
        L_0x0029:
            if (r0 == 0) goto L_0x0066
            if (r7 != r5) goto L_0x0025
        L_0x002d:
            r6.b((int) r1)
            goto L_0x0066
        L_0x0031:
            if (r7 == 0) goto L_0x0036
            if (r7 == r4) goto L_0x0036
            goto L_0x0023
        L_0x0036:
            if (r0 == 0) goto L_0x0066
            if (r7 != 0) goto L_0x002d
            r1 = 1
            goto L_0x002d
        L_0x003c:
            com.applovin.impl.adview.k r1 = r6.b
            com.applovin.impl.sdk.ad.g$b r1 = r1.f()
            com.applovin.impl.sdk.ad.g$b r2 = com.applovin.impl.sdk.ad.g.b.ACTIVITY_LANDSCAPE
            if (r1 != r2) goto L_0x0066
            r1 = 8
            r2 = 0
            if (r8 == 0) goto L_0x005c
            if (r7 == 0) goto L_0x0055
            if (r7 == r4) goto L_0x0055
        L_0x004f:
            r6.c = r5
            r6.b((int) r2)
            goto L_0x0066
        L_0x0055:
            if (r0 == 0) goto L_0x0066
            if (r7 != r4) goto L_0x005a
            goto L_0x002d
        L_0x005a:
            r1 = 0
            goto L_0x002d
        L_0x005c:
            if (r7 == r5) goto L_0x0061
            if (r7 == r3) goto L_0x0061
            goto L_0x004f
        L_0x0061:
            if (r0 == 0) goto L_0x0066
            if (r7 != r5) goto L_0x002d
            goto L_0x005a
        L_0x0066:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.j.b(int, boolean):void");
    }

    /* access modifiers changed from: private */
    public void b(AppLovinAd appLovinAd) {
        dismiss();
        c(appLovinAd);
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        c adWebView = ((AdViewControllerImpl) this.a.getAdViewController()).getAdWebView();
        if (adWebView != null && k.b(str)) {
            adWebView.a(str);
        }
    }

    /* access modifiers changed from: private */
    public void b(boolean z2) {
        c adWebView;
        if (this.currentAd.S() && (adWebView = ((AdViewControllerImpl) this.a.getAdViewController()).getAdWebView()) != null) {
            try {
                adWebView.a(z2 ? "javascript:al_mute();" : "javascript:al_unmute();");
            } catch (Throwable th) {
                this.logger.b("InterActivity", "Unable to forward mute setting to template.", th);
            }
        }
    }

    private void c(AppLovinAd appLovinAd) {
        if (!this.h) {
            this.h = true;
            if (this.b != null) {
                h.b(this.b.d(), appLovinAd, this.sdk);
            }
            this.sdk.S().d();
        }
    }

    private void c(boolean z2) {
        this.videoMuted = z2;
        MediaPlayer mediaPlayer = (MediaPlayer) this.G.get();
        if (mediaPlayer != null) {
            float f2 = z2 ^ true ? 1.0f : 0.0f;
            try {
                mediaPlayer.setVolume(f2, f2);
            } catch (IllegalStateException e2) {
                p pVar = this.logger;
                pVar.b("InterActivity", "Failed to set MediaPlayer muted: " + z2, e2);
            }
        }
    }

    private boolean c() {
        if (this.b == null || this.sdk == null || ((Boolean) this.sdk.a(b.cW)).booleanValue()) {
            return true;
        }
        if (!((Boolean) this.sdk.a(b.cX)).booleanValue() || !this.k) {
            return ((Boolean) this.sdk.a(b.cY)).booleanValue() && this.poststitialWasDisplayed;
        }
        return true;
    }

    @SuppressLint({"WrongConstant"})
    private void d() {
        if (this.sdk == null || !((Boolean) this.sdk.a(b.fk)).booleanValue() || !isFinishing()) {
            if (!(this.currentAd == null || !this.currentAd.ah() || this.u == Integer.MIN_VALUE)) {
                setRequestedOrientation(this.u);
            }
            finish();
        }
    }

    private void d(AppLovinAd appLovinAd) {
        if (!this.i) {
            this.i = true;
            h.a(this.b.c(), appLovinAd, this.sdk);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (!this.currentAd.v() || this.currentAd.h() == null) {
            f();
            g();
            return;
        }
        this.sdk.v().a("InterActivity", "Clicking through video...");
        clickThroughFromVideo();
    }

    private void f() {
        if (((Boolean) this.sdk.a(b.df)).booleanValue() && this.E != null && this.E.getVisibility() != 8) {
            a((View) this.E, this.E.getVisibility() == 4, 750);
        }
    }

    private void g() {
        p u2 = this.currentAd.u();
        if (u2 != null && u2.e() && !this.poststitialWasDisplayed && this.I != null) {
            a((View) this.I, this.I.getVisibility() == 4, u2.f());
        }
    }

    private void h() {
        if (this.sdk != null) {
            this.sdk.a(com.applovin.impl.sdk.b.d.p, false);
            this.sdk.a(com.applovin.impl.sdk.b.d.o, 0);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        this.f = true;
        showPoststitial();
    }

    /* access modifiers changed from: private */
    public boolean j() {
        return ((Integer) this.sdk.b(com.applovin.impl.sdk.b.d.o, 0)).intValue() > 0 ? this.videoMuted : ((Boolean) this.sdk.a(b.dk)).booleanValue() ? this.sdk.l().isMuted() : ((Boolean) this.sdk.a(b.di)).booleanValue();
    }

    private void k() {
        this.A = f.a(this.sdk, this, this.currentAd.p());
        this.A.setVisibility(8);
        this.A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                j.this.C();
            }
        });
        int a2 = a(this.currentAd.V());
        int i2 = 3;
        int i3 = (this.currentAd.Y() ? 3 : 5) | 48;
        if (!this.currentAd.Z()) {
            i2 = 5;
        }
        int i4 = i2 | 48;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(a2, a2, i3 | 48);
        this.A.a(a2);
        int a3 = a(this.currentAd.W());
        int a4 = a(this.currentAd.X());
        layoutParams.setMargins(a4, a3, a4, a3);
        this.z.addView(this.A, layoutParams);
        this.C = f.a(this.sdk, this, this.currentAd.q());
        this.C.setVisibility(8);
        this.C.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                j.this.B();
            }
        });
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(a2, a2, i4);
        layoutParams2.setMargins(a4, a3, a4, a3);
        this.C.a(a2);
        this.z.addView(this.C, layoutParams2);
        this.C.bringToFront();
        if (o()) {
            int a5 = a(((Integer) this.sdk.a(b.ch)).intValue());
            this.B = new View(this);
            this.B.setBackgroundColor(0);
            this.B.setVisibility(8);
            this.D = new View(this);
            this.D.setBackgroundColor(0);
            this.D.setVisibility(8);
            int i5 = a2 + a5;
            int a6 = a3 - a(5);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(i5, i5, i3);
            layoutParams3.setMargins(a6, a6, a6, a6);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(i5, i5, i4);
            layoutParams4.setMargins(a6, a6, a6, a6);
            this.B.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    j.this.A.performClick();
                }
            });
            this.D.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    j.this.C.performClick();
                }
            });
            this.z.addView(this.B, layoutParams3);
            this.B.bringToFront();
            this.z.addView(this.D, layoutParams4);
            this.D.bringToFront();
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        if (this.F == null) {
            try {
                this.videoMuted = j();
                this.F = new ImageView(this);
                if (!m()) {
                    int a2 = a(((Integer) this.sdk.a(b.dl)).intValue());
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(a2, a2, ((Integer) this.sdk.a(b.dn)).intValue());
                    this.F.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    int a3 = a(((Integer) this.sdk.a(b.dm)).intValue());
                    layoutParams.setMargins(a3, a3, a3, a3);
                    if ((this.videoMuted ? this.currentAd.ax() : this.currentAd.ay()) != null) {
                        p v2 = this.sdk.v();
                        v2.a("InterActivity", "Added mute button with params: " + layoutParams);
                        a(this.videoMuted);
                        this.F.setClickable(true);
                        this.F.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                j.this.toggleMute();
                            }
                        });
                        this.z.addView(this.F, layoutParams);
                        this.F.bringToFront();
                        return;
                    }
                    this.sdk.v().d("InterActivity", "Attempting to add mute button but could not find uri");
                    return;
                }
                this.sdk.v().a("InterActivity", "Mute button should be hidden");
            } catch (Exception e2) {
                this.sdk.v().a("InterActivity", "Failed to attach mute button", (Throwable) e2);
            }
        }
    }

    private boolean m() {
        if (!((Boolean) this.sdk.a(b.dg)).booleanValue()) {
            return true;
        }
        if (!((Boolean) this.sdk.a(b.dh)).booleanValue() || j()) {
            return false;
        }
        return !((Boolean) this.sdk.a(b.dj)).booleanValue();
    }

    /* access modifiers changed from: private */
    public void n() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (j.this.k) {
                        j.this.A.setVisibility(0);
                        return;
                    }
                    boolean unused = j.this.k = true;
                    if (j.this.o() && j.this.B != null) {
                        j.this.B.setVisibility(0);
                        j.this.B.bringToFront();
                    }
                    j.this.A.setVisibility(0);
                    j.this.A.bringToFront();
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration((long) ((Integer) j.this.sdk.a(b.cO)).intValue());
                    alphaAnimation.setRepeatCount(0);
                    j.this.A.startAnimation(alphaAnimation);
                } catch (Throwable unused2) {
                    j.this.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean o() {
        return ((Integer) this.sdk.a(b.ch)).intValue() > 0;
    }

    /* access modifiers changed from: private */
    public void p() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (!j.this.l && j.this.C != null) {
                        boolean unused = j.this.l = true;
                        j.this.C.setVisibility(0);
                        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                        alphaAnimation.setDuration((long) ((Integer) j.this.sdk.a(b.cO)).intValue());
                        alphaAnimation.setRepeatCount(0);
                        j.this.C.startAnimation(alphaAnimation);
                        if (j.this.o() && j.this.D != null) {
                            j.this.D.setVisibility(0);
                            j.this.D.bringToFront();
                        }
                    }
                } catch (Throwable th) {
                    p pVar = j.this.logger;
                    pVar.c("InterActivity", "Unable to show skip button: " + th);
                }
            }
        });
    }

    private void q() {
        if (this.currentAd.n() >= 0.0f) {
            a(n.b(this.currentAd.n()), (!this.m || this.C == null) ? this.A : this.C);
        }
    }

    /* access modifiers changed from: private */
    public void r() {
        boolean z2 = ((Boolean) this.sdk.a(b.cV)).booleanValue() && u() > 0;
        if (this.E == null && z2) {
            this.E = new e(this);
            int C2 = this.currentAd.C();
            this.E.setTextColor(C2);
            this.E.setTextSize((float) ((Integer) this.sdk.a(b.cU)).intValue());
            this.E.setFinishedStrokeColor(C2);
            this.E.setFinishedStrokeWidth((float) ((Integer) this.sdk.a(b.cT)).intValue());
            this.E.setMax(u());
            this.E.setProgress(u());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(a(((Integer) this.sdk.a(b.cS)).intValue()), a(((Integer) this.sdk.a(b.cS)).intValue()), ((Integer) this.sdk.a(b.cR)).intValue());
            int a2 = a(((Integer) this.sdk.a(b.cQ)).intValue());
            layoutParams.setMargins(a2, a2, a2, a2);
            this.z.addView(this.E, layoutParams);
            this.E.bringToFront();
            this.E.setVisibility(0);
            final long t2 = t();
            this.countdownManager.a("COUNTDOWN_CLOCK", 1000, (g.a) new g.a() {
                public void a() {
                    if (j.this.E != null) {
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(t2 - ((long) j.this.videoView.getCurrentPosition()));
                        if (seconds <= 0) {
                            j.this.E.setVisibility(8);
                            boolean unused = j.this.p = true;
                        } else if (j.this.s()) {
                            j.this.E.setProgress((int) seconds);
                        }
                    }
                }

                public boolean b() {
                    return j.this.s();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean s() {
        return !this.p && !this.poststitialWasDisplayed && this.videoView.isPlaying();
    }

    private long t() {
        return TimeUnit.SECONDS.toMillis((long) u());
    }

    private int u() {
        int B2 = this.currentAd.B();
        return (B2 <= 0 && ((Boolean) this.sdk.a(b.dv)).booleanValue()) ? this.computedLengthSeconds + 1 : B2;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void v() {
        if (this.J == null && this.currentAd.I()) {
            this.logger.b("InterActivity", "Attaching video progress bar...");
            this.J = new ProgressBar(this, (AttributeSet) null, 16842872);
            this.J.setMax(((Integer) this.sdk.a(b.dq)).intValue());
            this.J.setPadding(0, 0, 0, 0);
            if (e.f()) {
                try {
                    this.J.setProgressTintList(ColorStateList.valueOf(this.currentAd.J()));
                } catch (Throwable th) {
                    this.logger.b("InterActivity", "Unable to update progress bar color.", th);
                }
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.videoView.getWidth(), 20, 80);
            layoutParams.setMargins(0, 0, 0, ((Integer) this.sdk.a(b.dr)).intValue());
            this.z.addView(this.J, layoutParams);
            this.J.bringToFront();
            this.countdownManager.a("PROGRESS_BAR", ((Long) this.sdk.a(b.dp)).longValue(), (g.a) new g.a() {
                public void a() {
                    if (j.this.J == null) {
                        return;
                    }
                    if (j.this.shouldContinueFullLengthVideoCountdown()) {
                        j.this.J.setProgress((int) ((((float) j.this.videoView.getCurrentPosition()) / ((float) j.this.videoView.getDuration())) * ((float) ((Integer) j.this.sdk.a(b.dq)).intValue())));
                        return;
                    }
                    j.this.J.setVisibility(8);
                }

                public boolean b() {
                    return j.this.shouldContinueFullLengthVideoCountdown();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void w() {
        final p u2 = this.currentAd.u();
        if (k.b(this.currentAd.t()) && u2 != null && this.I == null) {
            this.logger.b("InterActivity", "Attaching video button...");
            this.I = x();
            double a2 = (double) u2.a();
            Double.isNaN(a2);
            double b2 = (double) u2.b();
            Double.isNaN(b2);
            int width = this.videoView.getWidth();
            int height = this.videoView.getHeight();
            double d2 = (double) width;
            Double.isNaN(d2);
            double d3 = (double) height;
            Double.isNaN(d3);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) ((a2 / 100.0d) * d2), (int) ((b2 / 100.0d) * d3), u2.d());
            int a3 = a(u2.c());
            layoutParams.setMargins(a3, a3, a3, a3);
            this.z.addView(this.I, layoutParams);
            this.I.bringToFront();
            if (u2.i() > 0.0f) {
                this.I.setVisibility(4);
                this.y.postDelayed(new Runnable() {
                    public void run() {
                        j.this.a((View) j.this.I, true, u2.g());
                    }
                }, n.b(u2.i()));
            }
            if (u2.j() > 0.0f) {
                this.y.postDelayed(new Runnable() {
                    public void run() {
                        j.this.a((View) j.this.I, false, u2.h());
                    }
                }, n.b(u2.j()));
            }
        }
    }

    private r x() {
        p pVar = this.logger;
        pVar.a("InterActivity", "Create video button with HTML = " + this.currentAd.t());
        s sVar = new s(this.sdk);
        this.K = new s.a() {
            public void a(r rVar) {
                j.this.logger.a("InterActivity", "Clicking through from video button...");
                j.this.clickThroughFromVideo();
            }

            public void b(r rVar) {
                j.this.logger.a("InterActivity", "Closing ad from video button...");
                j.this.dismiss();
            }

            public void c(r rVar) {
                j.this.logger.a("InterActivity", "Skipping video from video button...");
                j.this.skipVideo();
            }
        };
        sVar.a(new WeakReference(this.K));
        r rVar = new r(sVar, getApplicationContext());
        rVar.a(this.currentAd.t());
        return rVar;
    }

    private void y() {
        if (this.n && this.currentAd.K()) {
            this.L = new a(this, ((Integer) this.sdk.a(b.du)).intValue(), this.currentAd.M());
            this.L.setColor(this.currentAd.N());
            this.L.setBackgroundColor(this.currentAd.O());
            this.L.setVisibility(8);
            this.z.addView(this.L, new FrameLayout.LayoutParams(-1, -1, 17));
            this.z.bringChildToFront(this.L);
        }
    }

    /* access modifiers changed from: private */
    public void z() {
        if (this.L != null) {
            this.L.a();
        }
    }

    public void clickThroughFromVideo() {
        try {
            if (this.currentAd.ag() && this.m) {
                p();
            }
            this.sdk.o().trackAndLaunchVideoClick(this.currentAd, this.a, this.currentAd.h());
            h.a(this.b.e(), (AppLovinAd) this.currentAd, this.sdk);
            if (this.d != null) {
                this.d.b();
            }
        } catch (Throwable th) {
            this.sdk.v().b("InterActivity", "Encountered error while clicking through video.", th);
        }
    }

    public void continueVideo() {
        P();
    }

    public void dismiss() {
        long currentTimeMillis = System.currentTimeMillis() - this.r;
        p pVar = this.logger;
        pVar.b("InterActivity", "Dismissing ad after " + currentTimeMillis + " milliseconds elapsed");
        h();
        Q();
        if (this.b != null) {
            if (this.currentAd != null) {
                c((AppLovinAd) this.currentAd);
                if (this.d != null) {
                    this.d.c();
                    this.d = null;
                }
            }
            this.b.a(false);
            this.b.g();
        }
        lastKnownWrapper = null;
        d();
    }

    public void exitWithError(String str) {
        try {
            Log.e("InterActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + k.b + "; CleanedUp = " + k.c));
            c((AppLovinAd) new i());
        } catch (Exception e2) {
            Log.e("InterActivity", "Failed to show a video ad due to error:", e2);
        }
        d();
    }

    public boolean getPoststitialWasDisplayed() {
        return this.poststitialWasDisplayed;
    }

    public int getVideoPercentViewed() {
        if (this.f) {
            return 100;
        }
        if (this.videoView != null) {
            int duration = this.videoView.getDuration();
            if (duration <= 0) {
                return this.t;
            }
            double currentPosition = (double) this.videoView.getCurrentPosition();
            double d2 = (double) duration;
            Double.isNaN(currentPosition);
            Double.isNaN(d2);
            return (int) ((currentPosition / d2) * 100.0d);
        }
        this.logger.d("InterActivity", "No video view detected on video end");
        return 0;
    }

    public void handleMediaError() {
        if (this.v.compareAndSet(false, true)) {
            this.logger.d("InterActivity", "Handling media player error - Finishing activity...");
            d();
            return;
        }
        this.logger.d("InterActivity", "Already handled media player error. Doing nothing...");
    }

    /* access modifiers changed from: protected */
    public boolean isFullyWatched() {
        return getVideoPercentViewed() >= this.currentAd.Q();
    }

    /* access modifiers changed from: protected */
    public boolean isVastAd() {
        return this.currentAd instanceof com.applovin.impl.a.a;
    }

    public void onBackPressed() {
        f fVar;
        if (c()) {
            this.logger.a("InterActivity", "Back button was pressed; forwarding to Android for handling...");
        } else {
            try {
                if (this.m && this.C != null && this.C.getVisibility() == 0 && this.C.getAlpha() > 0.0f && !this.k) {
                    this.logger.a("InterActivity", "Back button was pressed; forwarding as a click to skip button.");
                    fVar = this.C;
                } else if (this.A == null || this.A.getVisibility() != 0 || this.A.getAlpha() <= 0.0f) {
                    this.logger.a("InterActivity", "Back button was pressed, but was not eligible for dismissal.");
                    a("javascript:al_onBackPressed();");
                    return;
                } else {
                    this.logger.a("InterActivity", "Back button was pressed; forwarding as a click to close button.");
                    fVar = this.A;
                }
                fVar.performClick();
                a("javascript:al_onBackPressed();");
                return;
            } catch (Exception unused) {
            }
        }
        super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != 0 && (this.videoView instanceof m) && this.G.get() != null) {
            MediaPlayer mediaPlayer = (MediaPlayer) this.G.get();
            this.videoView.setVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            super.onCreate(r8)
            if (r8 == 0) goto L_0x0010
            java.lang.String r0 = "instance_impression_tracked"
            boolean r0 = r8.getBoolean(r0)
            java.util.concurrent.atomic.AtomicBoolean r1 = r7.w
            r1.set(r0)
        L_0x0010:
            r0 = 1
            r7.requestWindowFeature(r0)
            android.os.StrictMode$ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskReads()
            android.content.Intent r2 = r7.getIntent()     // Catch:{ Throwable -> 0x018a }
            java.lang.String r3 = "com.applovin.interstitial.wrapper_id"
            java.lang.String r2 = r2.getStringExtra(r3)     // Catch:{ Throwable -> 0x018a }
            if (r2 == 0) goto L_0x0185
            boolean r3 = r2.isEmpty()     // Catch:{ Throwable -> 0x018a }
            if (r3 != 0) goto L_0x0185
            com.applovin.impl.adview.k r2 = com.applovin.impl.adview.k.a((java.lang.String) r2)     // Catch:{ Throwable -> 0x018a }
            r7.b = r2     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.adview.k r2 = r7.b     // Catch:{ Throwable -> 0x018a }
            if (r2 != 0) goto L_0x003c
            com.applovin.impl.adview.k r2 = lastKnownWrapper     // Catch:{ Throwable -> 0x018a }
            if (r2 == 0) goto L_0x003c
            com.applovin.impl.adview.k r2 = lastKnownWrapper     // Catch:{ Throwable -> 0x018a }
            r7.b = r2     // Catch:{ Throwable -> 0x018a }
        L_0x003c:
            com.applovin.impl.adview.k r2 = r7.b     // Catch:{ Throwable -> 0x018a }
            if (r2 == 0) goto L_0x016d
            com.applovin.impl.adview.k r2 = r7.b     // Catch:{ Throwable -> 0x018a }
            com.applovin.sdk.AppLovinAd r2 = r2.b()     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.adview.k r3 = r7.b     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.j r3 = r3.a()     // Catch:{ Throwable -> 0x018a }
            r7.sdk = r3     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.adview.k r3 = r7.b     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.j r3 = r3.a()     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.p r3 = r3.v()     // Catch:{ Throwable -> 0x018a }
            r7.logger = r3     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.c.d r3 = new com.applovin.impl.sdk.c.d     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.j r4 = r7.sdk     // Catch:{ Throwable -> 0x018a }
            r3.<init>(r2, r4)     // Catch:{ Throwable -> 0x018a }
            r7.d = r3     // Catch:{ Throwable -> 0x018a }
            if (r2 == 0) goto L_0x0167
            com.applovin.impl.sdk.ad.g r2 = (com.applovin.impl.sdk.ad.g) r2     // Catch:{ Throwable -> 0x018a }
            r3 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r3 = r7.findViewById(r3)     // Catch:{ Throwable -> 0x018a }
            if (r3 == 0) goto L_0x0083
            boolean r4 = r2.hasVideoUrl()     // Catch:{ Throwable -> 0x018a }
            if (r4 == 0) goto L_0x007e
            int r4 = r2.D()     // Catch:{ Throwable -> 0x018a }
        L_0x007a:
            r3.setBackgroundColor(r4)     // Catch:{ Throwable -> 0x018a }
            goto L_0x0083
        L_0x007e:
            int r4 = r2.E()     // Catch:{ Throwable -> 0x018a }
            goto L_0x007a
        L_0x0083:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x018a }
            r7.r = r3     // Catch:{ Throwable -> 0x018a }
            boolean r3 = r2.w()     // Catch:{ Throwable -> 0x018a }
            if (r3 == 0) goto L_0x0098
            android.view.Window r3 = r7.getWindow()     // Catch:{ Throwable -> 0x018a }
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            r3.setFlags(r4, r4)     // Catch:{ Throwable -> 0x018a }
        L_0x0098:
            boolean r3 = r2.x()     // Catch:{ Throwable -> 0x018a }
            if (r3 == 0) goto L_0x00a7
            android.view.Window r3 = r7.getWindow()     // Catch:{ Throwable -> 0x018a }
            r4 = 128(0x80, float:1.794E-43)
            r3.addFlags(r4)     // Catch:{ Throwable -> 0x018a }
        L_0x00a7:
            java.lang.String r3 = "window"
            java.lang.Object r3 = r7.getSystemService(r3)     // Catch:{ Throwable -> 0x018a }
            android.view.WindowManager r3 = (android.view.WindowManager) r3     // Catch:{ Throwable -> 0x018a }
            android.view.Display r3 = r3.getDefaultDisplay()     // Catch:{ Throwable -> 0x018a }
            int r4 = a((android.view.Display) r3)     // Catch:{ Throwable -> 0x018a }
            int r3 = r3.getRotation()     // Catch:{ Throwable -> 0x018a }
            r5 = 0
            r6 = 2
            if (r4 != r6) goto L_0x00c1
            if (r3 == 0) goto L_0x00d0
        L_0x00c1:
            if (r4 != r6) goto L_0x00c5
            if (r3 == r6) goto L_0x00d0
        L_0x00c5:
            if (r4 != r0) goto L_0x00c9
            if (r3 == r0) goto L_0x00d0
        L_0x00c9:
            if (r4 != r0) goto L_0x00cf
            r4 = 3
            if (r3 != r4) goto L_0x00cf
            goto L_0x00d0
        L_0x00cf:
            r0 = 0
        L_0x00d0:
            int r4 = r7.a((int) r3, (boolean) r0)     // Catch:{ Throwable -> 0x018a }
            if (r8 != 0) goto L_0x00d9
            r7.u = r4     // Catch:{ Throwable -> 0x018a }
            goto L_0x00e1
        L_0x00d9:
            java.lang.String r6 = "original_orientation"
            int r8 = r8.getInt(r6, r4)     // Catch:{ Throwable -> 0x018a }
            r7.u = r8     // Catch:{ Throwable -> 0x018a }
        L_0x00e1:
            boolean r8 = r2.A()     // Catch:{ Throwable -> 0x018a }
            if (r8 == 0) goto L_0x0113
            r8 = -1
            if (r4 == r8) goto L_0x0106
            com.applovin.impl.sdk.p r8 = r7.logger     // Catch:{ Throwable -> 0x018a }
            java.lang.String r0 = "InterActivity"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x018a }
            r2.<init>()     // Catch:{ Throwable -> 0x018a }
            java.lang.String r3 = "Locking activity orientation to current orientation: "
            r2.append(r3)     // Catch:{ Throwable -> 0x018a }
            r2.append(r4)     // Catch:{ Throwable -> 0x018a }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x018a }
            r8.a(r0, r2)     // Catch:{ Throwable -> 0x018a }
            r7.b((int) r4)     // Catch:{ Throwable -> 0x018a }
            goto L_0x011d
        L_0x0106:
            com.applovin.impl.sdk.p r8 = r7.logger     // Catch:{ Throwable -> 0x018a }
            java.lang.String r2 = "InterActivity"
            java.lang.String r4 = "Unable to detect current orientation. Locking to targeted orientation..."
            r8.d(r2, r4)     // Catch:{ Throwable -> 0x018a }
        L_0x010f:
            r7.b((int) r3, (boolean) r0)     // Catch:{ Throwable -> 0x018a }
            goto L_0x011d
        L_0x0113:
            com.applovin.impl.sdk.p r8 = r7.logger     // Catch:{ Throwable -> 0x018a }
            java.lang.String r2 = "InterActivity"
            java.lang.String r4 = "Locking activity orientation to targeted orientation..."
            r8.a(r2, r4)     // Catch:{ Throwable -> 0x018a }
            goto L_0x010f
        L_0x011d:
            com.applovin.adview.AppLovinAdView r8 = new com.applovin.adview.AppLovinAdView     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.j r0 = r7.sdk     // Catch:{ Throwable -> 0x018a }
            com.applovin.sdk.AppLovinSdk r0 = r0.L()     // Catch:{ Throwable -> 0x018a }
            com.applovin.sdk.AppLovinAdSize r2 = com.applovin.sdk.AppLovinAdSize.INTERSTITIAL     // Catch:{ Throwable -> 0x018a }
            r8.<init>((com.applovin.sdk.AppLovinSdk) r0, (com.applovin.sdk.AppLovinAdSize) r2, (android.content.Context) r7)     // Catch:{ Throwable -> 0x018a }
            r7.a = r8     // Catch:{ Throwable -> 0x018a }
            com.applovin.adview.AppLovinAdView r8 = r7.a     // Catch:{ Throwable -> 0x018a }
            r8.setAutoDestroy(r5)     // Catch:{ Throwable -> 0x018a }
            com.applovin.adview.AppLovinAdView r8 = r7.a     // Catch:{ Throwable -> 0x018a }
            com.applovin.adview.AdViewController r8 = r8.getAdViewController()     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.adview.AdViewControllerImpl r8 = (com.applovin.impl.adview.AdViewControllerImpl) r8     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.c.d r0 = r7.d     // Catch:{ Throwable -> 0x018a }
            r8.setStatsManagerHelper(r0)     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.adview.k r8 = r7.b     // Catch:{ Throwable -> 0x018a }
            r8.a((com.applovin.impl.adview.h) r7)     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.j r8 = r7.sdk     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r0 = com.applovin.impl.sdk.b.b.dt     // Catch:{ Throwable -> 0x018a }
            java.lang.Object r8 = r8.a(r0)     // Catch:{ Throwable -> 0x018a }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x018a }
            boolean r8 = r8.booleanValue()     // Catch:{ Throwable -> 0x018a }
            r7.m = r8     // Catch:{ Throwable -> 0x018a }
            android.content.Context r8 = r7.getApplicationContext()     // Catch:{ Throwable -> 0x018a }
            boolean r8 = com.applovin.impl.sdk.e.e.b(r8)     // Catch:{ Throwable -> 0x018a }
            r7.q = r8     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.a.d r8 = new com.applovin.impl.sdk.a.d     // Catch:{ Throwable -> 0x018a }
            com.applovin.impl.sdk.j r0 = r7.sdk     // Catch:{ Throwable -> 0x018a }
            r8.<init>(r7, r0)     // Catch:{ Throwable -> 0x018a }
            r7.H = r8     // Catch:{ Throwable -> 0x018a }
            goto L_0x019d
        L_0x0167:
            java.lang.String r8 = "No current ad found."
        L_0x0169:
            r7.exitWithError(r8)     // Catch:{ Throwable -> 0x018a }
            goto L_0x019d
        L_0x016d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x018a }
            r8.<init>()     // Catch:{ Throwable -> 0x018a }
            java.lang.String r0 = "Wrapper is null; initialized state: "
            r8.append(r0)     // Catch:{ Throwable -> 0x018a }
            boolean r0 = com.applovin.impl.adview.k.b     // Catch:{ Throwable -> 0x018a }
            java.lang.String r0 = java.lang.Boolean.toString(r0)     // Catch:{ Throwable -> 0x018a }
            r8.append(r0)     // Catch:{ Throwable -> 0x018a }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x018a }
            goto L_0x0169
        L_0x0185:
            java.lang.String r8 = "Wrapper ID is null"
            goto L_0x0169
        L_0x0188:
            r8 = move-exception
            goto L_0x01b0
        L_0x018a:
            r8 = move-exception
            com.applovin.impl.sdk.p r0 = r7.logger     // Catch:{ all -> 0x0188 }
            if (r0 == 0) goto L_0x0198
            com.applovin.impl.sdk.p r0 = r7.logger     // Catch:{ all -> 0x0188 }
            java.lang.String r2 = "InterActivity"
            java.lang.String r3 = "Encountered error during onCreate."
            r0.b(r2, r3, r8)     // Catch:{ all -> 0x0188 }
        L_0x0198:
            java.lang.String r8 = "An error was encountered during interstitial ad creation."
            r7.exitWithError(r8)     // Catch:{ all -> 0x0188 }
        L_0x019d:
            android.os.StrictMode.setThreadPolicy(r1)
            r7.h()
            com.applovin.impl.sdk.c.d r8 = r7.d
            if (r8 == 0) goto L_0x01ac
            com.applovin.impl.sdk.c.d r8 = r7.d
            r8.a()
        L_0x01ac:
            r7.K()
            return
        L_0x01b0:
            android.os.StrictMode.setThreadPolicy(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.j.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006b, code lost:
        if (r4.currentAd != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0080, code lost:
        if (r4.currentAd == null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0082, code lost:
        Q();
        c((com.applovin.sdk.AppLovinAd) r4.currentAd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008a, code lost:
        super.onDestroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r4 = this;
            com.applovin.adview.AppLovinAdView r0 = r4.a     // Catch:{ Throwable -> 0x0070 }
            r1 = 0
            if (r0 == 0) goto L_0x001f
            com.applovin.adview.AppLovinAdView r0 = r4.a     // Catch:{ Throwable -> 0x0070 }
            android.view.ViewParent r0 = r0.getParent()     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x0018
            boolean r2 = r0 instanceof android.view.ViewGroup     // Catch:{ Throwable -> 0x0070 }
            if (r2 == 0) goto L_0x0018
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0     // Catch:{ Throwable -> 0x0070 }
            com.applovin.adview.AppLovinAdView r2 = r4.a     // Catch:{ Throwable -> 0x0070 }
            r0.removeView(r2)     // Catch:{ Throwable -> 0x0070 }
        L_0x0018:
            com.applovin.adview.AppLovinAdView r0 = r4.a     // Catch:{ Throwable -> 0x0070 }
            r0.destroy()     // Catch:{ Throwable -> 0x0070 }
            r4.a = r1     // Catch:{ Throwable -> 0x0070 }
        L_0x001f:
            com.applovin.impl.adview.q r0 = r4.videoView     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x002d
            com.applovin.impl.adview.q r0 = r4.videoView     // Catch:{ Throwable -> 0x0070 }
            r0.pause()     // Catch:{ Throwable -> 0x0070 }
            com.applovin.impl.adview.q r0 = r4.videoView     // Catch:{ Throwable -> 0x0070 }
            r0.stopPlayback()     // Catch:{ Throwable -> 0x0070 }
        L_0x002d:
            com.applovin.impl.sdk.j r0 = r4.sdk     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x004e
            com.applovin.impl.sdk.j r0 = r4.sdk     // Catch:{ Throwable -> 0x0070 }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r2 = com.applovin.impl.sdk.b.b.fg     // Catch:{ Throwable -> 0x0070 }
            java.lang.Object r0 = r0.a(r2)     // Catch:{ Throwable -> 0x0070 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x0070 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x004e
            java.lang.ref.WeakReference<android.media.MediaPlayer> r0 = r4.G     // Catch:{ Throwable -> 0x0070 }
            java.lang.Object r0 = r0.get()     // Catch:{ Throwable -> 0x0070 }
            android.media.MediaPlayer r0 = (android.media.MediaPlayer) r0     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x004e
            r0.release()     // Catch:{ Throwable -> 0x0070 }
        L_0x004e:
            com.applovin.impl.adview.g r0 = r4.countdownManager     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x0057
            com.applovin.impl.adview.g r0 = r4.countdownManager     // Catch:{ Throwable -> 0x0070 }
            r0.b()     // Catch:{ Throwable -> 0x0070 }
        L_0x0057:
            android.os.Handler r0 = r4.y     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x0060
            android.os.Handler r0 = r4.y     // Catch:{ Throwable -> 0x0070 }
            r0.removeCallbacksAndMessages(r1)     // Catch:{ Throwable -> 0x0070 }
        L_0x0060:
            android.os.Handler r0 = r4.x     // Catch:{ Throwable -> 0x0070 }
            if (r0 == 0) goto L_0x0069
            android.os.Handler r0 = r4.x     // Catch:{ Throwable -> 0x0070 }
            r0.removeCallbacksAndMessages(r1)     // Catch:{ Throwable -> 0x0070 }
        L_0x0069:
            com.applovin.impl.sdk.ad.g r0 = r4.currentAd
            if (r0 == 0) goto L_0x008a
            goto L_0x0082
        L_0x006e:
            r0 = move-exception
            goto L_0x008e
        L_0x0070:
            r0 = move-exception
            com.applovin.impl.sdk.p r1 = r4.logger     // Catch:{ all -> 0x006e }
            if (r1 == 0) goto L_0x007e
            com.applovin.impl.sdk.p r1 = r4.logger     // Catch:{ all -> 0x006e }
            java.lang.String r2 = "InterActivity"
            java.lang.String r3 = "Unable to destroy video view"
            r1.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)     // Catch:{ all -> 0x006e }
        L_0x007e:
            com.applovin.impl.sdk.ad.g r0 = r4.currentAd
            if (r0 == 0) goto L_0x008a
        L_0x0082:
            r4.Q()
            com.applovin.impl.sdk.ad.g r0 = r4.currentAd
            r4.c((com.applovin.sdk.AppLovinAd) r0)
        L_0x008a:
            super.onDestroy()
            return
        L_0x008e:
            com.applovin.impl.sdk.ad.g r1 = r4.currentAd
            if (r1 == 0) goto L_0x009a
            r4.Q()
            com.applovin.impl.sdk.ad.g r1 = r4.currentAd
            r4.c((com.applovin.sdk.AppLovinAd) r1)
        L_0x009a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.j.onDestroy():void");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.logger.a("InterActivity", "App paused...");
        this.s = System.currentTimeMillis();
        if (!this.e && (this.q || !this.c)) {
            N();
        }
        this.b.a(false);
        this.H.a();
        pauseReportRewardTask();
        a("javascript:al_onAppPaused();");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        f fVar;
        super.onResume();
        this.logger.a("InterActivity", "App resumed...");
        boolean z2 = true;
        this.b.a(true);
        if (!this.o) {
            if (this.d != null) {
                this.d.d(System.currentTimeMillis() - this.s);
            }
            if (!((Boolean) this.sdk.b(com.applovin.impl.sdk.b.d.p, false)).booleanValue() || this.H.d() || this.poststitialWasDisplayed) {
                if (!(this.currentAd instanceof a) || !((a) this.currentAd).j()) {
                    z2 = false;
                }
                if (this.currentAd != null && ((Boolean) this.sdk.a(b.cP)).booleanValue() && !this.currentAd.y() && this.poststitialWasDisplayed && this.A != null && !z2) {
                    fVar = this.A;
                }
                resumeReportRewardTask();
            } else {
                O();
                z();
                if (this.currentAd != null && ((Boolean) this.sdk.a(b.cP)).booleanValue() && !this.currentAd.z() && !this.poststitialWasDisplayed && this.m && this.C != null) {
                    fVar = this.C;
                }
                resumeReportRewardTask();
            }
            a(0, fVar);
            resumeReportRewardTask();
        } else if (!this.H.d() && !this.poststitialWasDisplayed && this.currentAd != null && this.currentAd.L()) {
            z();
        }
        a("javascript:al_onAppResumed();");
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("instance_impression_tracked", this.w.get());
        bundle.putInt("original_orientation", this.u);
        super.onSaveInstanceState(bundle);
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (z2) {
            this.logger.a("InterActivity", "Window gained focus");
            try {
                if (!e.e() || !((Boolean) this.sdk.a(b.f0do)).booleanValue() || !a()) {
                    getWindow().setFlags(1024, 1024);
                } else {
                    b();
                    if (((Long) this.sdk.a(b.cZ)).longValue() > 0) {
                        this.y.postDelayed(new Runnable() {
                            public void run() {
                                j.this.b();
                            }
                        }, ((Long) this.sdk.a(b.cZ)).longValue());
                    }
                }
                if (((Boolean) this.sdk.a(b.da)).booleanValue() && !this.poststitialWasDisplayed) {
                    O();
                    resumeReportRewardTask();
                }
            } catch (Throwable th) {
                this.logger.b("InterActivity", "Setting window flags failed.", th);
            }
        } else {
            this.logger.a("InterActivity", "Window lost focus");
            if (((Boolean) this.sdk.a(b.da)).booleanValue() && !this.poststitialWasDisplayed) {
                N();
                pauseReportRewardTask();
            }
        }
        this.o = false;
        a("javascript:al_onWindowFocusChanged( " + z2 + " );");
    }

    public void pauseReportRewardTask() {
        if (this.M != null) {
            this.M.b();
        }
    }

    /* access modifiers changed from: protected */
    public void playVideo() {
        d((AppLovinAd) this.currentAd);
        this.videoView.start();
        this.countdownManager.a();
    }

    public void resumeReportRewardTask() {
        if (this.M != null) {
            this.M.c();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldContinueFullLengthVideoCountdown() {
        return !this.f && !this.poststitialWasDisplayed;
    }

    public void showPoststitial() {
        long j2;
        f fVar;
        try {
            if (this.d != null) {
                this.d.g();
            }
            if (!this.currentAd.aa()) {
                L();
            }
            if (this.a != null) {
                ViewParent parent = this.a.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.a);
                }
                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.setBackgroundColor(this.currentAd.E());
                frameLayout.addView(this.a);
                if (this.currentAd.an()) {
                    this.a.renderAd(this.currentAd);
                }
                if (this.currentAd.aa()) {
                    L();
                }
                if (this.z != null) {
                    this.z.removeAllViewsInLayout();
                }
                if (o() && this.B != null) {
                    if (this.B.getParent() instanceof ViewGroup) {
                        ((ViewGroup) this.B.getParent()).removeView(this.B);
                    }
                    frameLayout.addView(this.B);
                    this.B.bringToFront();
                }
                if (this.A != null) {
                    ViewParent parent2 = this.A.getParent();
                    if (parent2 instanceof ViewGroup) {
                        ((ViewGroup) parent2).removeView(this.A);
                    }
                    frameLayout.addView(this.A);
                    this.A.bringToFront();
                }
                setContentView(frameLayout);
                if (this.currentAd.ao()) {
                    this.a.renderAd(this.currentAd);
                }
                if (((Boolean) this.sdk.a(b.fc)).booleanValue()) {
                    this.a.setVisibility(4);
                    this.a.setVisibility(0);
                }
                int R = this.currentAd.R();
                if (R >= 0) {
                    this.y.postDelayed(new Runnable() {
                        public void run() {
                            j.this.b("javascript:al_onPoststitialShow();");
                        }
                    }, (long) R);
                }
            }
            if (!((this.currentAd instanceof a) && ((a) this.currentAd).j())) {
                if (this.currentAd.o() >= 0.0f) {
                    j2 = n.b(this.currentAd.o());
                    fVar = this.A;
                } else if (this.currentAd.o() == -2.0f) {
                    this.A.setVisibility(0);
                } else {
                    j2 = 0;
                    fVar = this.A;
                }
                a(j2, fVar);
            } else {
                this.logger.a("InterActivity", "Skip showing of close button");
            }
            this.poststitialWasDisplayed = true;
        } catch (Throwable th) {
            this.logger.b("InterActivity", "Encountered error while showing poststitial. Dismissing...", th);
            dismiss();
        }
    }

    public void skipVideo() {
        if (this.d != null) {
            this.d.f();
        }
        if (this.currentAd.r()) {
            dismiss();
        } else {
            showPoststitial();
        }
    }

    public void toggleMute() {
        boolean z2 = !M();
        if (this.d != null) {
            this.d.h();
        }
        try {
            c(z2);
            a(z2);
            b(z2);
        } catch (Throwable th) {
            this.logger.b("InterActivity", "Unable to set volume to " + z2, th);
        }
    }
}
