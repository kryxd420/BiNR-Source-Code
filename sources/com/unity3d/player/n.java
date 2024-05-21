package com.unity3d.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;

public final class n extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    /* access modifiers changed from: private */
    public static boolean a = false;
    private final Context b;
    private final SurfaceView c;
    private final SurfaceHolder d;
    private final String e;
    private final int f;
    private final int g;
    private final boolean h;
    private final long i;
    private final long j;
    private final FrameLayout k;
    private final Display l;
    private int m;
    private int n;
    private int o;
    private int p;
    private MediaPlayer q;
    private MediaController r;
    private boolean s = false;
    private boolean t = false;
    private int u = 0;
    private boolean v = false;
    private boolean w = false;
    private a x;
    private b y;
    private volatile int z = 0;

    public interface a {
        void a(int i);
    }

    public class b implements Runnable {
        private n b;
        private boolean c = false;

        public b(n nVar) {
            this.b = nVar;
        }

        public final void a() {
            this.c = true;
        }

        public final void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (!this.c) {
                if (n.a) {
                    n.b("Stopping the video player due to timeout.");
                }
                this.b.CancelOnPrepare();
            }
        }
    }

    protected n(Context context, String str, int i2, int i3, int i4, boolean z2, long j2, long j3, a aVar) {
        super(context);
        this.x = aVar;
        this.b = context;
        this.k = this;
        this.c = new SurfaceView(context);
        this.d = this.c.getHolder();
        this.d.addCallback(this);
        this.d.setType(3);
        this.k.setBackgroundColor(i2);
        this.k.addView(this.c);
        this.l = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        this.e = str;
        this.f = i3;
        this.g = i4;
        this.h = z2;
        this.i = j2;
        this.j = j3;
        if (a) {
            b("fileName: " + this.e);
        }
        if (a) {
            b("backgroundColor: " + i2);
        }
        if (a) {
            b("controlMode: " + this.f);
        }
        if (a) {
            b("scalingMode: " + this.g);
        }
        if (a) {
            b("isURL: " + this.h);
        }
        if (a) {
            b("videoOffset: " + this.i);
        }
        if (a) {
            b("videoLength: " + this.j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void a(int i2) {
        this.z = i2;
        if (this.x != null) {
            this.x.a(this.z);
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0081 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
            r8 = this;
            android.media.MediaPlayer r0 = r8.q
            if (r0 == 0) goto L_0x001e
            android.media.MediaPlayer r0 = r8.q
            android.view.SurfaceHolder r1 = r8.d
            r0.setDisplay(r1)
            boolean r0 = r8.v
            if (r0 != 0) goto L_0x001d
            boolean r0 = a
            if (r0 == 0) goto L_0x0018
            java.lang.String r0 = "Resuming playback"
            b(r0)
        L_0x0018:
            android.media.MediaPlayer r0 = r8.q
            r0.start()
        L_0x001d:
            return
        L_0x001e:
            r0 = 0
            r8.a((int) r0)
            r8.doCleanUp()
            android.media.MediaPlayer r0 = new android.media.MediaPlayer     // Catch:{ Exception -> 0x00d0 }
            r0.<init>()     // Catch:{ Exception -> 0x00d0 }
            r8.q = r0     // Catch:{ Exception -> 0x00d0 }
            boolean r0 = r8.h     // Catch:{ Exception -> 0x00d0 }
            if (r0 == 0) goto L_0x003e
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            android.content.Context r1 = r8.b     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r2 = r8.e     // Catch:{ Exception -> 0x00d0 }
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00d0 }
            r0.setDataSource(r1, r2)     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0092
        L_0x003e:
            long r0 = r8.j     // Catch:{ Exception -> 0x00d0 }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x005e
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = r8.e     // Catch:{ Exception -> 0x00d0 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r2 = r8.q     // Catch:{ Exception -> 0x00d0 }
            java.io.FileDescriptor r3 = r0.getFD()     // Catch:{ Exception -> 0x00d0 }
            long r4 = r8.i     // Catch:{ Exception -> 0x00d0 }
            long r6 = r8.j     // Catch:{ Exception -> 0x00d0 }
            r2.setDataSource(r3, r4, r6)     // Catch:{ Exception -> 0x00d0 }
        L_0x005a:
            r0.close()     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0092
        L_0x005e:
            android.content.res.Resources r0 = r8.getResources()     // Catch:{ Exception -> 0x00d0 }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = r8.e     // Catch:{ IOException -> 0x0081 }
            android.content.res.AssetFileDescriptor r0 = r0.openFd(r1)     // Catch:{ IOException -> 0x0081 }
            android.media.MediaPlayer r1 = r8.q     // Catch:{ IOException -> 0x0081 }
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch:{ IOException -> 0x0081 }
            long r3 = r0.getStartOffset()     // Catch:{ IOException -> 0x0081 }
            long r5 = r0.getLength()     // Catch:{ IOException -> 0x0081 }
            r1.setDataSource(r2, r3, r5)     // Catch:{ IOException -> 0x0081 }
            r0.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0092
        L_0x0081:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = r8.e     // Catch:{ Exception -> 0x00d0 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r1 = r8.q     // Catch:{ Exception -> 0x00d0 }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x00d0 }
            r1.setDataSource(r2)     // Catch:{ Exception -> 0x00d0 }
            goto L_0x005a
        L_0x0092:
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            android.view.SurfaceHolder r1 = r8.d     // Catch:{ Exception -> 0x00d0 }
            r0.setDisplay(r1)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r1 = 1
            r0.setScreenOnWhilePlaying(r1)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r0.setOnBufferingUpdateListener(r8)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r0.setOnCompletionListener(r8)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r0.setOnPreparedListener(r8)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r0.setOnVideoSizeChangedListener(r8)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r1 = 3
            r0.setAudioStreamType(r1)     // Catch:{ Exception -> 0x00d0 }
            android.media.MediaPlayer r0 = r8.q     // Catch:{ Exception -> 0x00d0 }
            r0.prepareAsync()     // Catch:{ Exception -> 0x00d0 }
            com.unity3d.player.n$b r0 = new com.unity3d.player.n$b     // Catch:{ Exception -> 0x00d0 }
            r0.<init>(r8)     // Catch:{ Exception -> 0x00d0 }
            r8.y = r0     // Catch:{ Exception -> 0x00d0 }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x00d0 }
            com.unity3d.player.n$b r1 = r8.y     // Catch:{ Exception -> 0x00d0 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00d0 }
            r0.start()     // Catch:{ Exception -> 0x00d0 }
            return
        L_0x00d0:
            r0 = move-exception
            boolean r1 = a
            if (r1 == 0) goto L_0x00ed
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "error: "
            r1.<init>(r2)
            java.lang.String r2 = r0.getMessage()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            b(r0)
        L_0x00ed:
            r0 = 2
            r8.a((int) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.n.c():void");
    }

    private void d() {
        if (!isPlaying()) {
            a(1);
            if (a) {
                b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        a(2);
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (a) {
            b("destroyPlayer");
        }
        if (!this.v) {
            pause();
        }
        doCleanUp();
    }

    /* access modifiers changed from: protected */
    public final void doCleanUp() {
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        if (this.q != null) {
            this.q.release();
            this.q = null;
        }
        this.o = 0;
        this.p = 0;
        this.t = false;
        this.s = false;
    }

    public final int getBufferPercentage() {
        if (this.h) {
            return this.u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        if (this.q == null) {
            return 0;
        }
        return this.q.getCurrentPosition();
    }

    public final int getDuration() {
        if (this.q == null) {
            return 0;
        }
        return this.q.getDuration();
    }

    public final boolean isPlaying() {
        boolean z2 = this.t && this.s;
        return this.q == null ? !z2 : this.q.isPlaying() || !z2;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
        if (a) {
            b("onBufferingUpdate percent:" + i2);
        }
        this.u = i2;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (a) {
            b("onCompletion called");
        }
        destroyPlayer();
        a(3);
    }

    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 && (this.f != 2 || i2 == 0 || keyEvent.isSystem())) {
            return this.r != null ? this.r.onKeyDown(i2, keyEvent) : super.onKeyDown(i2, keyEvent);
        }
        destroyPlayer();
        a(3);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (a) {
            b("onPrepared called");
        }
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        if (this.f == 0 || this.f == 1) {
            this.r = new MediaController(this.b);
            this.r.setMediaPlayer(this);
            this.r.setAnchorView(this);
            this.r.setEnabled(true);
            this.r.show();
        }
        this.t = true;
        if (this.t && this.s) {
            d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f != 2 || action != 0) {
            return this.r != null ? this.r.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        a(3);
        return true;
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        if (a) {
            b("onVideoSizeChanged called " + i2 + AvidJSONUtil.KEY_X + i3);
        }
        if (i2 != 0 && i3 != 0) {
            this.s = true;
            this.o = i2;
            this.p = i3;
            if (this.t && this.s) {
                d();
            }
        } else if (a) {
            b("invalid video width(" + i2 + ") or height(" + i3 + ")");
        }
    }

    public final void pause() {
        if (this.q != null) {
            if (this.w) {
                this.q.pause();
            }
            this.v = true;
        }
    }

    public final void seekTo(int i2) {
        if (this.q != null) {
            this.q.seekTo(i2);
        }
    }

    public final void start() {
        if (a) {
            b("Start");
        }
        if (this.q != null) {
            if (this.w) {
                this.q.start();
            }
            this.v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        if (a) {
            b("surfaceChanged called " + i2 + " " + i3 + AvidJSONUtil.KEY_X + i4);
        }
        if (this.m != i3 || this.n != i4) {
            this.m = i3;
            this.n = i4;
            if (this.w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (a) {
            b("surfaceCreated called");
        }
        this.w = true;
        c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (a) {
            b("surfaceDestroyed called");
        }
        this.w = false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        if (r3 <= r2) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        r0 = (int) (((float) r6.n) * r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        if (r3 >= r2) goto L_0x0053;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateVideoLayout() {
        /*
            r6 = this;
            boolean r0 = a
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = "updateVideoLayout"
            b(r0)
        L_0x0009:
            android.media.MediaPlayer r0 = r6.q
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            int r0 = r6.m
            if (r0 == 0) goto L_0x0016
            int r0 = r6.n
            if (r0 != 0) goto L_0x0034
        L_0x0016:
            android.content.Context r0 = r6.b
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r1 = r0.getDefaultDisplay()
            int r1 = r1.getWidth()
            r6.m = r1
            android.view.Display r0 = r0.getDefaultDisplay()
            int r0 = r0.getHeight()
            r6.n = r0
        L_0x0034:
            int r0 = r6.m
            int r1 = r6.n
            boolean r2 = r6.s
            if (r2 == 0) goto L_0x0073
            int r2 = r6.o
            float r2 = (float) r2
            int r3 = r6.p
            float r3 = (float) r3
            float r2 = r2 / r3
            int r3 = r6.m
            float r3 = (float) r3
            int r4 = r6.n
            float r4 = (float) r4
            float r3 = r3 / r4
            int r4 = r6.g
            r5 = 1
            if (r4 != r5) goto L_0x0060
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 > 0) goto L_0x0059
        L_0x0053:
            int r1 = r6.m
            float r1 = (float) r1
            float r1 = r1 / r2
            int r1 = (int) r1
            goto L_0x007c
        L_0x0059:
            int r0 = r6.n
            float r0 = (float) r0
            float r0 = r0 * r2
            int r0 = (int) r0
            goto L_0x007c
        L_0x0060:
            int r4 = r6.g
            r5 = 2
            if (r4 != r5) goto L_0x006a
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 < 0) goto L_0x0059
            goto L_0x0053
        L_0x006a:
            int r2 = r6.g
            if (r2 != 0) goto L_0x007c
            int r0 = r6.o
            int r1 = r6.p
            goto L_0x007c
        L_0x0073:
            boolean r2 = a
            if (r2 == 0) goto L_0x007c
            java.lang.String r2 = "updateVideoLayout: Video size is not known yet"
            b(r2)
        L_0x007c:
            int r2 = r6.m
            if (r2 != r0) goto L_0x0084
            int r2 = r6.n
            if (r2 == r1) goto L_0x00af
        L_0x0084:
            boolean r2 = a
            if (r2 == 0) goto L_0x00a1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "frameWidth = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = "; frameHeight = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            b(r2)
        L_0x00a1:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r0, r1, r3)
            android.widget.FrameLayout r0 = r6.k
            android.view.SurfaceView r1 = r6.c
            r0.updateViewLayout(r1, r2)
        L_0x00af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.n.updateVideoLayout():void");
    }
}
