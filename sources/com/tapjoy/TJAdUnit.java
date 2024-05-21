package com.tapjoy;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitJSBridge;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.gj;
import com.tapjoy.internal.hr;
import com.tapjoy.internal.ju;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.view.BasicWebView;
import com.tapjoy.mraid.view.MraidView;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.json.JSONObject;

public class TJAdUnit implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener {
    public static TJVideoListener publisherVideoListener;
    /* access modifiers changed from: private */
    public boolean A;
    /* access modifiers changed from: private */
    public boolean B;
    private fq C;
    private final Runnable D = new Runnable() {
        public final void run() {
            int streamVolume = TJAdUnit.this.p.getStreamVolume(3);
            if (TJAdUnit.this.q != streamVolume) {
                int unused = TJAdUnit.this.q = streamVolume;
                TJAdUnit.this.a.onVolumeChanged();
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable E = new Runnable() {
        public final void run() {
            if (TJAdUnit.this.d.getCurrentPosition() != 0) {
                if (!TJAdUnit.this.m) {
                    boolean unused = TJAdUnit.this.m = true;
                }
                TJAdUnit.this.a.onVideoStarted(TJAdUnit.this.k);
                TJAdUnit.this.F.run();
            } else if (!TJAdUnit.this.A) {
                TJAdUnit.this.f.postDelayed(TJAdUnit.this.E, 200);
            } else {
                boolean unused2 = TJAdUnit.this.B = true;
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable F = new Runnable() {
        public final void run() {
            TJAdUnit.this.a.onVideoProgress(TJAdUnit.this.d.getCurrentPosition());
            TJAdUnit.this.f.postDelayed(TJAdUnit.this.F, 500);
        }
    };
    /* access modifiers changed from: package-private */
    public TJAdUnitJSBridge a;
    /* access modifiers changed from: package-private */
    public BasicWebView b;
    /* access modifiers changed from: package-private */
    public MraidView c;
    VideoView d;
    volatile boolean e;
    /* access modifiers changed from: private */
    public final Handler f = new Handler(Looper.getMainLooper());
    private TJAdUnitWebViewListener g;
    private TJAdUnitVideoListener h;
    /* access modifiers changed from: private */
    public TJAdUnitActivity i;
    private MediaPlayer j;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public boolean l;
    /* access modifiers changed from: private */
    public boolean m;
    private boolean n;
    @Nullable
    private ScheduledFuture o;
    /* access modifiers changed from: private */
    public AudioManager p;
    /* access modifiers changed from: private */
    public int q = 0;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    /* access modifiers changed from: private */
    public boolean v;
    /* access modifiers changed from: private */
    public boolean w;
    /* access modifiers changed from: private */
    public boolean x;
    /* access modifiers changed from: private */
    public boolean y;
    private int z = -1;

    public interface TJAdUnitVideoListener {
        void onVideoCompleted();

        void onVideoError(String str);

        void onVideoStart();
    }

    public interface TJAdUnitWebViewListener {
        void onClosed();

        void onContentReady();
    }

    private static boolean a(int i2) {
        return i2 == 0 || i2 == 8 || i2 == 6 || i2 == 11;
    }

    private static boolean b(int i2) {
        return i2 == 1 || i2 == 9 || i2 == 7 || i2 == 12;
    }

    public boolean preload(TJPlacementData tJPlacementData, Context context) {
        if (this.w || !tJPlacementData.isPrerenderingRequested() || !TJPlacementManager.canPreRenderPlacement() || TapjoyConnectCore.isViewOpen()) {
            fireContentReady();
            return false;
        }
        TapjoyLog.i("TJAdUnit", "Pre-rendering ad unit for placement: " + tJPlacementData.getPlacementName());
        TJPlacementManager.incrementPlacementPreRenderCount();
        load(tJPlacementData, true, context);
        return true;
    }

    public void load(final TJPlacementData tJPlacementData, final boolean z2, final Context context) {
        this.w = false;
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                TJAdUnit tJAdUnit = TJAdUnit.this;
                Context context = context;
                boolean z = false;
                if (Looper.myLooper() == Looper.getMainLooper() && !tJAdUnit.e && context != null) {
                    TapjoyLog.d("TJAdUnit", "Constructing ad unit");
                    tJAdUnit.e = true;
                    tJAdUnit.b = new BasicWebView(context);
                    tJAdUnit.b.loadDataWithBaseURL((String) null, "<!DOCTYPE html><html><head><title>Tapjoy Background Webview</title></head></html>", "text/html", "utf-8", (String) null);
                    tJAdUnit.c = new MraidView(context);
                    tJAdUnit.c.setListener(new a(tJAdUnit, (byte) 0));
                    tJAdUnit.d = new VideoView(context);
                    tJAdUnit.d.setOnCompletionListener(tJAdUnit);
                    tJAdUnit.d.setOnErrorListener(tJAdUnit);
                    tJAdUnit.d.setOnPreparedListener(tJAdUnit);
                    tJAdUnit.d.setVisibility(4);
                    tJAdUnit.a = new TJAdUnitJSBridge(context, tJAdUnit);
                    if (context instanceof TJAdUnitActivity) {
                        tJAdUnit.setAdUnitActivity((TJAdUnitActivity) context);
                    }
                }
                if (tJAdUnit.e) {
                    TapjoyLog.i("TJAdUnit", "Loading ad unit content");
                    boolean unused = TJAdUnit.this.w = true;
                    if (!ju.c(tJPlacementData.getRedirectURL())) {
                        if (tJPlacementData.isPreloadDisabled()) {
                            TJAdUnit.this.c.postUrl(tJPlacementData.getRedirectURL(), (byte[]) null);
                        } else {
                            TJAdUnit.this.c.loadUrl(tJPlacementData.getRedirectURL());
                        }
                    } else if (tJPlacementData.getBaseURL() == null || tJPlacementData.getHttpResponse() == null) {
                        TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error loading ad unit content"));
                        boolean unused2 = TJAdUnit.this.w = false;
                    } else {
                        TJAdUnit.this.c.loadDataWithBaseURL(tJPlacementData.getBaseURL(), tJPlacementData.getHttpResponse(), "text/html", "utf-8", (String) null);
                    }
                    TJAdUnit tJAdUnit2 = TJAdUnit.this;
                    if (TJAdUnit.this.w && z2) {
                        z = true;
                    }
                    boolean unused3 = tJAdUnit2.x = z;
                }
            }
        });
    }

    public void resume(TJAdUnitSaveStateData tJAdUnitSaveStateData) {
        if (this.a.didLaunchOtherActivity) {
            TapjoyLog.d("TJAdUnit", "onResume bridge.didLaunchOtherActivity callbackID: " + this.a.otherActivityCallbackID);
            this.a.invokeJSCallback(this.a.otherActivityCallbackID, Boolean.TRUE);
            this.a.didLaunchOtherActivity = false;
        }
        this.A = false;
        this.a.setEnabled(true);
        if (tJAdUnitSaveStateData != null) {
            this.k = tJAdUnitSaveStateData.seekTime;
            this.d.seekTo(this.k);
            if (this.j != null) {
                this.s = tJAdUnitSaveStateData.isVideoMuted;
            }
        }
        if (this.B) {
            this.B = false;
            this.f.postDelayed(this.E, 200);
        }
    }

    public void pause() {
        this.A = true;
        this.a.setEnabled(false);
        pauseVideo();
    }

    public void invokeBridgeCallback(String str, Object... objArr) {
        if (this.a != null && str != null) {
            this.a.invokeJSCallback(str, objArr);
        }
    }

    public void destroy() {
        this.a.destroy();
        if (this.b != null) {
            this.b.removeAllViews();
            this.b = null;
        }
        if (this.c != null) {
            this.c.removeAllViews();
            this.c = null;
        }
        b();
        this.e = false;
        this.v = false;
        setAdUnitActivity((TJAdUnitActivity) null);
        a();
        this.j = null;
        if (this.g != null) {
            this.g.onClosed();
        }
        resetContentLoadState();
    }

    public void resetContentLoadState() {
        this.w = false;
        this.y = false;
        this.x = false;
        this.z = -1;
        this.u = false;
        this.s = false;
    }

    public void setVisible(boolean z2) {
        this.v = z2;
        if (this.v && this.y) {
            this.a.display();
        }
    }

    public void fireContentReady() {
        if (this.g != null) {
            this.g.onContentReady();
        }
    }

    public void closeRequested(boolean z2) {
        if (this.c == null || !this.c.videoPlaying()) {
            this.a.closeRequested(Boolean.valueOf(z2));
        } else {
            this.c.videoViewCleanup();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        r5 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r5 = 9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setOrientation(int r11) {
        /*
            r10 = this;
            com.tapjoy.TJAdUnitActivity r0 = r10.i
            if (r0 == 0) goto L_0x007d
            com.tapjoy.TJAdUnitActivity r1 = r10.i
            r2 = 8
            r3 = 9
            r4 = -1
            r5 = 0
            r6 = 1
            if (r1 == 0) goto L_0x0054
            android.view.WindowManager r7 = r1.getWindowManager()
            android.view.Display r7 = r7.getDefaultDisplay()
            int r7 = r7.getRotation()
            android.util.DisplayMetrics r8 = new android.util.DisplayMetrics
            r8.<init>()
            android.view.WindowManager r1 = r1.getWindowManager()
            android.view.Display r1 = r1.getDefaultDisplay()
            r1.getMetrics(r8)
            int r1 = r8.widthPixels
            int r8 = r8.heightPixels
            if (r7 == 0) goto L_0x0034
            r9 = 2
            if (r7 != r9) goto L_0x0036
        L_0x0034:
            if (r8 > r1) goto L_0x0049
        L_0x0036:
            if (r7 == r6) goto L_0x003b
            r9 = 3
            if (r7 != r9) goto L_0x003e
        L_0x003b:
            if (r1 <= r8) goto L_0x003e
            goto L_0x0049
        L_0x003e:
            switch(r7) {
                case 0: goto L_0x0055;
                case 1: goto L_0x004c;
                case 2: goto L_0x004e;
                case 3: goto L_0x0051;
                default: goto L_0x0041;
            }
        L_0x0041:
            java.lang.String r1 = "TJAdUnit"
            java.lang.String r2 = "Unknown screen orientation. Defaulting to landscape."
            com.tapjoy.TapjoyLog.w(r1, r2)
            goto L_0x0055
        L_0x0049:
            switch(r7) {
                case 0: goto L_0x004c;
                case 1: goto L_0x0055;
                case 2: goto L_0x0051;
                case 3: goto L_0x004e;
                default: goto L_0x004c;
            }
        L_0x004c:
            r5 = 1
            goto L_0x0055
        L_0x004e:
            r5 = 8
            goto L_0x0055
        L_0x0051:
            r5 = 9
            goto L_0x0055
        L_0x0054:
            r5 = -1
        L_0x0055:
            int r1 = r10.z
            if (r1 == r4) goto L_0x005c
            int r1 = r10.z
            goto L_0x005d
        L_0x005c:
            r1 = r5
        L_0x005d:
            boolean r2 = a((int) r1)
            if (r2 == 0) goto L_0x0069
            boolean r2 = a((int) r11)
            if (r2 != 0) goto L_0x0075
        L_0x0069:
            boolean r2 = b((int) r1)
            if (r2 == 0) goto L_0x0076
            boolean r2 = b((int) r11)
            if (r2 == 0) goto L_0x0076
        L_0x0075:
            r11 = r1
        L_0x0076:
            r0.setRequestedOrientation(r11)
            r10.z = r11
            r10.u = r6
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJAdUnit.setOrientation(int):void");
    }

    public void unsetOrientation() {
        TJAdUnitActivity tJAdUnitActivity = this.i;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.setRequestedOrientation(-1);
        }
        this.z = -1;
        this.u = false;
    }

    /* access modifiers changed from: private */
    public void a() {
        TapjoyLog.d("TJAdUnit", "detachVolumeListener");
        if (this.o != null) {
            this.o.cancel(false);
            this.o = null;
        }
        this.p = null;
    }

    public void setAdUnitActivity(TJAdUnitActivity tJAdUnitActivity) {
        this.i = tJAdUnitActivity;
        if (this.c != null) {
            this.c.setContext(this.i);
        }
        if (this.a != null) {
            this.a.setAdUnitActivity(this.i);
        }
    }

    public void setAdContentTracker(fq fqVar) {
        this.C = fqVar;
    }

    public void setBackgroundColor(final String str, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundColor: " + str);
                    TJAdUnit.this.b.setBackgroundColor(Color.parseColor(str));
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception unused) {
                    TapjoyLog.d("TJAdUnit", "Error setting background color. backgroundWebView: " + TJAdUnit.this.b + ", hexColor: " + str);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setBackgroundContent(final String str, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundContent: " + str);
                    TJAdUnit.this.b.loadDataWithBaseURL((String) null, str, "text/html", "utf-8", (String) null);
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception unused) {
                    TapjoyLog.d("TJAdUnit", "Error setting background content. backgroundWebView: " + TJAdUnit.this.b + ", content: " + str);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setWebViewListener(TJAdUnitWebViewListener tJAdUnitWebViewListener) {
        this.g = tJAdUnitWebViewListener;
    }

    public void setVideoListener(TJAdUnitVideoListener tJAdUnitVideoListener) {
        this.h = tJAdUnitVideoListener;
    }

    public int getOrientation() {
        return this.z;
    }

    public boolean hasCalledLoad() {
        return this.w;
    }

    public boolean isPrerendered() {
        return this.x;
    }

    public boolean isLockedOrientation() {
        return this.u;
    }

    public BasicWebView getBackgroundWebView() {
        return this.b;
    }

    public MraidView getWebView() {
        return this.c;
    }

    public boolean getCloseRequested() {
        return this.a.closeRequested;
    }

    public void loadVideoUrl(final String str, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                if (TJAdUnit.this.d != null) {
                    TapjoyLog.i("TJAdUnit", "loadVideoUrl: " + str);
                    TJAdUnit.this.d.setVideoPath(str);
                    TJAdUnit.this.d.setVisibility(0);
                    TJAdUnit.this.d.seekTo(0);
                    adUnitAsyncTaskListner.onComplete(true);
                    return;
                }
                adUnitAsyncTaskListner.onComplete(false);
            }
        });
    }

    public boolean playVideo() {
        TapjoyLog.i("TJAdUnit", "playVideo");
        if (this.d == null) {
            return false;
        }
        this.d.start();
        this.n = false;
        this.f.postDelayed(this.E, 200);
        return true;
    }

    public boolean pauseVideo() {
        b();
        if (this.d == null || !this.d.isPlaying()) {
            return false;
        }
        this.d.pause();
        this.k = this.d.getCurrentPosition();
        TapjoyLog.i("TJAdUnit", "Video paused at: " + this.k);
        this.a.onVideoPaused(this.k);
        return true;
    }

    public void clearVideo(final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        if (this.d != null) {
            b();
            TapjoyUtil.runOnMainThread(new Runnable() {
                public final void run() {
                    TJAdUnit.this.d.setVisibility(4);
                    TJAdUnit.this.d.stopPlayback();
                    boolean unused = TJAdUnit.this.m = false;
                    boolean unused2 = TJAdUnit.this.l = false;
                    int unused3 = TJAdUnit.this.k = 0;
                    adUnitAsyncTaskListner.onComplete(true);
                }
            });
            return;
        }
        adUnitAsyncTaskListner.onComplete(false);
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z2) {
        if (this.j != null) {
            if (z2) {
                this.j.setVolume(0.0f, 0.0f);
            } else {
                this.j.setVolume(1.0f, 1.0f);
            }
            if (this.t != z2) {
                this.t = z2;
                this.a.onVolumeChanged();
                return;
            }
            return;
        }
        this.s = z2;
    }

    public void attachVolumeListener(boolean z2, int i2) {
        TJAdUnitActivity tJAdUnitActivity;
        TapjoyLog.d("TJAdUnit", "attachVolumeListener: isAttached=" + z2 + "; interval=" + i2);
        a();
        if (z2 && (tJAdUnitActivity = this.i) != null) {
            this.p = (AudioManager) tJAdUnitActivity.getSystemService("audio");
            this.q = this.p.getStreamVolume(3);
            this.r = this.p.getStreamMaxVolume(3);
            long j2 = (long) i2;
            this.o = hr.a.scheduleWithFixedDelay(this.D, j2, j2, TimeUnit.MILLISECONDS);
        }
    }

    public VideoView getVideoView() {
        return this.d;
    }

    public int getVideoSeekTime() {
        return this.k;
    }

    public boolean isVideoComplete() {
        return this.n;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onPrepared");
        final int duration = this.d.getDuration();
        final int measuredWidth = this.d.getMeasuredWidth();
        final int measuredHeight = this.d.getMeasuredHeight();
        this.j = mediaPlayer;
        if (this.s) {
            a(this.s);
        }
        if (this.k <= 0 || this.d.getCurrentPosition() == this.k) {
            this.a.onVideoReady(duration, measuredWidth, measuredHeight);
        } else {
            this.j.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                public final void onSeekComplete(MediaPlayer mediaPlayer) {
                    TJAdUnit.this.a.onVideoReady(duration, measuredWidth, measuredHeight);
                }
            });
        }
        this.j.setOnInfoListener(this);
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        String str;
        TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error encountered when instantiating the VideoView: " + i2 + " - " + i3));
        this.l = true;
        b();
        String str2 = (i2 != 100 ? "MEDIA_ERROR_UNKNOWN" : "MEDIA_ERROR_SERVER_DIED") + " -- ";
        if (i3 == -1010) {
            str = str2 + "MEDIA_ERROR_UNSUPPORTED";
        } else if (i3 == -1007) {
            str = str2 + "MEDIA_ERROR_MALFORMED";
        } else if (i3 == -1004) {
            str = str2 + "MEDIA_ERROR_IO";
        } else if (i3 != -110) {
            str = str2 + "MEDIA_ERROR_EXTRA_UNKNOWN";
        } else {
            str = str2 + "MEDIA_ERROR_TIMED_OUT";
        }
        this.a.onVideoError(str);
        if (i2 == 1 || i3 == -1004) {
            return true;
        }
        return false;
    }

    private void b() {
        this.f.removeCallbacks(this.E);
        this.f.removeCallbacks(this.F);
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onCompletion");
        b();
        this.n = true;
        if (!this.l) {
            this.a.onVideoCompletion();
        }
        this.l = false;
    }

    public void fireOnVideoStart() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoStart");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoStart();
        }
        if (this.h != null) {
            this.h.onVideoStart();
        }
    }

    public void fireOnVideoError(String str) {
        TapjoyLog.e("TJAdUnit", "Firing onVideoError with error: " + str);
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoError(3);
        }
        if (this.h != null) {
            this.h.onVideoError(str);
        }
    }

    public void fireOnVideoComplete() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoComplete");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoComplete();
        }
        if (this.h != null) {
            this.h.onVideoCompleted();
        }
    }

    public float getVolume() {
        return ((float) this.q) / ((float) this.r);
    }

    public boolean isMuted() {
        return this.t;
    }

    public void startAdContentTracking(String str, JSONObject jSONObject) {
        if (this.C != null) {
            this.C.a(str, jSONObject);
        }
    }

    public void endAdContentTracking(String str, JSONObject jSONObject) {
        if (this.C != null) {
            c();
            this.C.b(str, jSONObject);
        }
    }

    public void sendAdContentTracking(String str, JSONObject jSONObject) {
        if (this.C != null) {
            c();
            fq fqVar = this.C;
            Map a2 = fq.a(jSONObject);
            gj.e(str).a(fqVar.a).a(a2).b(fq.b(jSONObject)).c();
        }
    }

    private void c() {
        if (this.C != null) {
            this.C.a("prerendered", Boolean.valueOf(this.x));
        }
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
        String str = "";
        if (i2 == 3) {
            str = "MEDIA_INFO_VIDEO_RENDERING_START";
        } else if (i2 != 801) {
            switch (i2) {
                case 700:
                    str = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                    break;
                case 701:
                    str = "MEDIA_INFO_BUFFERING_START";
                    break;
                case 702:
                    str = "MEDIA_INFO_BUFFERING_END";
                    break;
            }
        } else {
            str = "MEDIA_INFO_NOT_SEEKABLE";
        }
        this.a.onVideoInfo(str);
        return false;
    }

    public TJVideoListener getPublisherVideoListener() {
        return publisherVideoListener;
    }

    class a implements MraidViewListener {
        public final boolean onEventFired() {
            return false;
        }

        public final boolean onReady() {
            return false;
        }

        public final boolean onResize() {
            return false;
        }

        public final boolean onResizeClose() {
            return false;
        }

        private a() {
        }

        /* synthetic */ a(TJAdUnit tJAdUnit, byte b) {
            this();
        }

        public final boolean onClose() {
            TJAdUnitActivity q = TJAdUnit.this.i;
            if (q == null) {
                return false;
            }
            q.handleClose();
            return false;
        }

        @TargetApi(8)
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (!TJAdUnit.this.a.closeRequested) {
                return true;
            }
            String[] strArr = {"Uncaught", "uncaught", "Error", TJAdUnitConstants.String.VIDEO_ERROR, "not defined"};
            TapjoyLog.d("TJAdUnit", "shouldClose...");
            TJAdUnitActivity q = TJAdUnit.this.i;
            if (q == null) {
                return true;
            }
            for (int i = 0; i < 5; i++) {
                if (consoleMessage.message().contains(strArr[i])) {
                    q.handleClose();
                    return true;
                }
            }
            return true;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            TJAdUnitActivity q = TJAdUnit.this.i;
            if (q != null) {
                q.showErrorDialog();
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            TapjoyLog.d("TJAdUnit", "onPageStarted: " + str);
            if (TJAdUnit.this.a != null) {
                TJAdUnit.this.a.allowRedirect = true;
                TJAdUnit.this.a.customClose = false;
                TJAdUnit.this.a.closeRequested = false;
                TJAdUnit.this.a();
            }
        }

        public final void onPageFinished(WebView webView, String str) {
            TapjoyLog.d("TJAdUnit", "onPageFinished: " + str);
            if (!(TJAdUnit.this == null || TJAdUnit.this.c == null || !TJAdUnit.this.c.isMraid())) {
                TJAdUnit.this.a.allowRedirect = false;
            }
            TJAdUnitActivity q = TJAdUnit.this.i;
            if (q != null) {
                q.setProgressSpinnerVisibility(false);
            }
            boolean unused = TJAdUnit.this.y = true;
            if (TJAdUnit.this.v) {
                TJAdUnit.this.a.display();
            }
            TJAdUnit.this.a.flushMessageQueue();
        }

        @TargetApi(9)
        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!a()) {
                TJAdUnitActivity q = TJAdUnit.this.i;
                if (q != null) {
                    q.showErrorDialog();
                }
                return true;
            }
            TapjoyLog.d("TJAdUnit", "interceptURL: " + str);
            if (TJAdUnit.this != null && TJAdUnit.this.c != null && TJAdUnit.this.c.isMraid() && str.contains("mraid")) {
                return false;
            }
            if (a(str)) {
                TapjoyLog.d("TJAdUnit", "Open redirecting URL:" + str);
                ((MraidView) webView).loadUrlStandard(str);
                return true;
            } else if (TJAdUnit.this.a.allowRedirect) {
                return false;
            } else {
                webView.loadUrl(str);
                return true;
            }
        }

        private static boolean a(String str) {
            try {
                String host = new URL(TapjoyConfig.TJC_SERVICE_URL).getHost();
                if ((host == null || !str.contains(host)) && !str.contains(TapjoyConnectCore.getRedirectDomain()) && !str.contains(TapjoyUtil.getRedirectDomain(TapjoyConnectCore.getPlacementURL()))) {
                    return false;
                }
                return true;
            } catch (MalformedURLException unused) {
                return false;
            }
        }

        private boolean a() {
            try {
                NetworkInfo activeNetworkInfo = TJAdUnit.this.c.getConnectivityManager().getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                    return false;
                }
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }
}
