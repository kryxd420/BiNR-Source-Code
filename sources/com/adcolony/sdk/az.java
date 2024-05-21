package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

class az extends FrameLayout {
    public static final int ADCOLONY_IAP_ENGAGEMENT_END_CARD = 0;
    public static final int ADCOLONY_IAP_ENGAGEMENT_OVERLAY = 1;
    float a = 1.0f;
    int b = 2;
    /* access modifiers changed from: private */
    public c c;
    private c d;
    private e e;
    /* access modifiers changed from: private */
    public String f;
    private String g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public boolean k;
    private ImageView l;
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private ExecutorService q = Executors.newSingleThreadExecutor();

    az(Context context, af afVar, e eVar) {
        super(context);
        this.e = eVar;
        this.p = eVar.a;
        this.f = y.b(afVar.c(), "id");
        new aa.a().a("Retrieving container tied to ad session id: ").a(this.f).a(aa.b);
        this.c = a.a().m().b().get(this.f);
        setLayoutParams(new FrameLayout.LayoutParams(this.c.q(), this.c.p()));
        addView(this.c);
        d();
    }

    public boolean destroy() {
        af afVar;
        if (this.i) {
            new aa.a().a("Ignoring subsequent call to destroy()").a(aa.e);
            return false;
        } else if (!a.b()) {
            return false;
        } else {
            l a2 = a.a();
            this.i = true;
            JSONObject a3 = y.a();
            y.a(a3, "id", this.f);
            if (this.j) {
                afVar = new af("AdSession.on_native_ad_view_destroyed", this.c.c(), a3);
            } else {
                afVar = new af("AdSession.on_ad_view_destroyed", this.c.c(), a3);
            }
            if (a2.y()) {
                a2.c(afVar);
            } else {
                afVar.b();
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        d m2 = a.a().m();
        m2.a(this.c);
        if (this.d != null) {
            m2.a(this.d);
        }
        f remove = m2.g().remove(this.f);
        if (remove != null) {
            for (MediaPlayer next : remove.c().c().values()) {
                if (next.isPlaying()) {
                    next.stop();
                }
                next.release();
            }
            remove.d().a().autoPause();
            remove.d().a().release();
        }
        m2.f().remove(this.f);
        this.c = null;
        this.e = null;
        removeAllViews();
        this.q.shutdown();
        return true;
    }

    public boolean setVolume(float f2) {
        double d2 = (double) f2;
        if (d2 < 0.0d || d2 > 1.0d) {
            return false;
        }
        if (this.i) {
            new aa.a().a("Ignoring call to setVolume as view has been destroyed.").a(aa.e);
            return false;
        }
        if (d2 > 0.0d) {
            this.a = f2;
        }
        JSONObject a2 = y.a();
        y.a(a2, "id", this.f);
        y.a(a2, AvidVideoPlaybackListenerImpl.VOLUME, d2);
        new af(this.j ? "AdSession.on_native_ad_view_set_volume" : "AdSession.on_ad_view_destroyed", this.c.c(), a2).b();
        return true;
    }

    public boolean setMuted(boolean z) {
        if (this.i) {
            new aa.a().a("Ignoring call to setMuted() as view has been destroyed").a(aa.e);
            return false;
        } else if (z) {
            return setVolume(0.0f);
        } else {
            return setVolume(this.a);
        }
    }

    public String getZoneID() {
        if (!this.i) {
            return this.p;
        }
        new aa.a().a("Ignoring call to getZoneID() as view has been destroyed").a(aa.e);
        return "";
    }

    public boolean pause() {
        if (this.i) {
            new aa.a().a("Ignoring call to pause() as view has been destroyed").a(aa.e);
            return false;
        }
        JSONObject a2 = y.a();
        y.a(a2, "id", this.f);
        new af("AdSession.on_manual_pause", this.c.c(), a2).b();
        return true;
    }

    public boolean resume() {
        if (this.i) {
            new aa.a().a("Ignoring call to resume() as view has been destroyed").a(aa.e);
            return false;
        }
        JSONObject a2 = y.a();
        y.a(a2, "id", this.f);
        new af("AdSession.on_manual_resume", this.c.c(), a2).b();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        Activity c2;
        if (this.g.equals("") || (c2 = a.c()) == null) {
            return false;
        }
        this.l = new ImageView(c2);
        this.l.setImageBitmap(BitmapFactory.decodeFile(this.g));
        return true;
    }

    private void d() {
        try {
            this.q.submit(new Runnable() {
                public void run() {
                    JSONObject a2 = y.a();
                    y.a(a2, "id", az.this.f);
                    while (!az.this.i) {
                        Rect rect = new Rect();
                        Rect rect2 = new Rect();
                        az.this.getLocalVisibleRect(rect);
                        az.this.getGlobalVisibleRect(rect2);
                        ViewParent parent = az.this.getParent();
                        if (parent != null) {
                            parent.getChildVisibleRect(az.this, rect2, (Point) null);
                        }
                        boolean z = rect.bottom - rect.top > az.this.c.p() / 2;
                        boolean z2 = (rect2.bottom - rect2.top < az.this.c.p() / 2 || rect2.bottom - rect2.top >= az.this.c.p()) && az.this.k;
                        boolean z3 = rect.bottom > az.this.c.p() || rect.bottom < 0 || rect.top < 0 || rect2.top <= 0;
                        if (!z3 && !z2 && z && !az.this.h) {
                            boolean unused = az.this.k = true;
                            boolean unused2 = az.this.h = true;
                            new af(az.this.j ? "AdSession.on_native_ad_view_visible" : "AdSession.on_ad_view_visible", az.this.c.c(), a2).b();
                        } else if ((!z || (z && z3)) && az.this.h) {
                            boolean unused3 = az.this.h = false;
                            new af(az.this.j ? "AdSession.on_native_ad_view_hidden" : "AdSession.on_ad_view_hidden", az.this.c.c(), a2).b();
                            new aa.a().a("AdColonyAdView has been hidden.").a(aa.d);
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException unused4) {
                        }
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            JSONObject a2 = y.a();
            y.a(a2, "id", this.f);
            new af("AdSession.on_error", this.c.c(), a2).b();
        }
    }

    /* access modifiers changed from: package-private */
    public void setNative(boolean z) {
        this.j = z;
    }

    /* access modifiers changed from: package-private */
    public void setAdvertiserName(String str) {
        this.m = str;
    }

    /* access modifiers changed from: package-private */
    public void setTitle(String str) {
        this.n = str;
    }

    /* access modifiers changed from: package-private */
    public void setDescription(String str) {
        this.o = str;
    }

    /* access modifiers changed from: package-private */
    public void setImageFilepath(String str) {
        this.g = str;
    }

    /* access modifiers changed from: package-private */
    public void setExpandedContainer(c cVar) {
        this.d = cVar;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public String getAdSessionId() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public c getContainer() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public c getExpandedContainer() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public String getAdvertiserName() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public ImageView getIcon() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public String getTitle() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public String getDescription() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public e getListener() {
        return this.e;
    }
}
