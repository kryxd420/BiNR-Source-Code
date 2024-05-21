package com.adcolony.sdk;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.session.AvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class c extends FrameLayout {
    private AvidManagedVideoAdSession A;
    boolean a;
    boolean b;
    Context c;
    VideoView d;
    private HashMap<Integer, ax> e;
    private HashMap<Integer, au> f;
    private HashMap<Integer, u> g;
    private HashMap<Integer, ay> h;
    private HashMap<Integer, h> i;
    private HashMap<Integer, r> j;
    private HashMap<Integer, w> k;
    private HashMap<Integer, Boolean> l;
    private HashMap<Integer, View> m;
    private int n;
    private int o;
    private int p;
    private int q;
    private String r;
    /* access modifiers changed from: private */
    public float s = 0.0f;
    /* access modifiers changed from: private */
    public double t = 0.0d;
    /* access modifiers changed from: private */
    public long u = 0;
    private ArrayList<ah> v;
    private ArrayList<String> w;
    private boolean x;
    private boolean y;
    private boolean z;

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    c(Context context, String str) {
        super(context);
        this.c = context;
        this.r = str;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "container_id") == this.p && y.b(c2, "ad_session_id").equals(this.r);
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        int i2;
        this.e = new HashMap<>();
        this.f = new HashMap<>();
        this.g = new HashMap<>();
        this.h = new HashMap<>();
        this.i = new HashMap<>();
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.m = new HashMap<>();
        this.v = new ArrayList<>();
        this.w = new ArrayList<>();
        JSONObject c2 = afVar.c();
        this.p = y.c(c2, "id");
        this.n = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.o = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        this.q = y.c(c2, "module_id");
        this.b = y.d(c2, "viewability_enabled");
        this.x = this.p == 1;
        l a2 = a.a();
        if (this.n == 0 && this.o == 0) {
            this.n = a2.c.p();
            if (a2.d().getMultiWindowEnabled()) {
                i2 = a2.c.q() - aw.b(a.c());
            } else {
                i2 = a2.c.q();
            }
            this.o = i2;
        } else {
            setLayoutParams(new FrameLayout.LayoutParams(this.n, this.o));
        }
        this.v.add(a.a("VideoView.create", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.a((View) c.this.g(afVar));
                }
            }
        }, true));
        this.v.add(a.a("VideoView.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.h(afVar);
                }
            }
        }, true));
        this.v.add(a.a("WebView.create", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.a((View) c.this.i(afVar));
                }
            }
        }, true));
        this.v.add(a.a("WebView.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.j(afVar);
                }
            }
        }, true));
        this.v.add(a.a("RenderView.create", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.a((View) c.this.m(afVar));
                }
            }
        }, true));
        this.v.add(a.a("RenderView.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.n(afVar);
                }
            }
        }, true));
        this.v.add(a.a("TextView.create", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.a(c.this.k(afVar));
                }
            }
        }, true));
        this.v.add(a.a("TextView.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.l(afVar);
                }
            }
        }, true));
        this.v.add(a.a("ImageView.create", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.a((View) c.this.e(afVar));
                }
            }
        }, true));
        this.v.add(a.a("ImageView.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.f(afVar);
                }
            }
        }, true));
        this.v.add(a.a("ColorView.create", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.a((View) c.this.c(afVar));
                }
            }
        }, true));
        this.v.add(a.a("ColorView.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (c.this.a(afVar)) {
                    c.this.d(afVar);
                }
            }
        }, true));
        this.w.add("VideoView.create");
        this.w.add("VideoView.destroy");
        this.w.add("WebView.create");
        this.w.add("WebView.destroy");
        this.w.add("RenderView.create");
        this.w.add("RenderView.destroy");
        this.w.add("TextView.create");
        this.w.add("TextView.destroy");
        this.w.add("ImageView.create");
        this.w.add("ImageView.destroy");
        this.w.add("ColorView.create");
        this.w.add("ColorView.destroy");
        this.d = new VideoView(this.c);
        this.d.setVisibility(8);
        addView(this.d);
        setClipToPadding(false);
        if (this.b) {
            d(y.d(afVar.c(), "advanced_viewability"));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        l a2 = a.a();
        d m2 = a2.m();
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        JSONObject a3 = y.a();
        y.b(a3, "view_id", -1);
        y.a(a3, "ad_session_id", this.r);
        y.b(a3, "container_x", x2);
        y.b(a3, "container_y", y2);
        y.b(a3, "view_x", x2);
        y.b(a3, "view_y", y2);
        y.b(a3, "id", this.p);
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.q, a3).b();
                break;
            case 1:
                if (!this.x) {
                    a2.a(m2.f().get(this.r));
                }
                new af("AdContainer.on_touch_ended", this.q, a3).b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.q, a3).b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.q, a3).b();
                break;
            case 5:
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", (int) motionEvent.getX(action2));
                y.b(a3, "container_y", (int) motionEvent.getY(action2));
                y.b(a3, "view_x", (int) motionEvent.getX(action2));
                y.b(a3, "view_y", (int) motionEvent.getY(action2));
                new af("AdContainer.on_touch_began", this.q, a3).b();
                break;
            case 6:
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", (int) motionEvent.getX(action3));
                y.b(a3, "container_y", (int) motionEvent.getY(action3));
                y.b(a3, "view_x", (int) motionEvent.getX(action3));
                y.b(a3, "view_y", (int) motionEvent.getY(action3));
                y.b(a3, AvidJSONUtil.KEY_X, (int) motionEvent.getX(action3));
                y.b(a3, AvidJSONUtil.KEY_Y, (int) motionEvent.getY(action3));
                if (!this.x) {
                    a2.a(m2.f().get(this.r));
                }
                new af("AdContainer.on_touch_ended", this.q, a3).b();
                break;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public h c(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        h hVar = new h(this.c, afVar, c2, this);
        hVar.a();
        this.i.put(Integer.valueOf(c2), hVar);
        this.m.put(Integer.valueOf(c2), hVar);
        return hVar;
    }

    /* access modifiers changed from: package-private */
    public boolean d(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        View remove = this.m.remove(Integer.valueOf(c2));
        h remove2 = this.i.remove(Integer.valueOf(c2));
        if (remove == null || remove2 == null) {
            d m2 = a.a().m();
            String d2 = afVar.d();
            m2.a(d2, "" + c2);
            return false;
        }
        removeView(remove2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public w e(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        w wVar = new w(this.c, afVar, c2, this);
        wVar.a();
        this.k.put(Integer.valueOf(c2), wVar);
        this.m.put(Integer.valueOf(c2), wVar);
        return wVar;
    }

    /* access modifiers changed from: package-private */
    public boolean f(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        View remove = this.m.remove(Integer.valueOf(c2));
        w remove2 = this.k.remove(Integer.valueOf(c2));
        if (remove == null || remove2 == null) {
            d m2 = a.a().m();
            String d2 = afVar.d();
            m2.a(d2, "" + c2);
            return false;
        }
        removeView(remove2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public ax g(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        ax axVar = new ax(this.c, afVar, c2, this);
        axVar.b();
        this.e.put(Integer.valueOf(c2), axVar);
        this.m.put(Integer.valueOf(c2), axVar);
        return axVar;
    }

    /* access modifiers changed from: package-private */
    public boolean h(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        View remove = this.m.remove(Integer.valueOf(c2));
        ax remove2 = this.e.remove(Integer.valueOf(c2));
        if (remove == null || remove2 == null) {
            d m2 = a.a().m();
            String d2 = afVar.d();
            m2.a(d2, "" + c2);
            return false;
        }
        if (remove2.h()) {
            remove2.d();
        }
        remove2.a();
        removeView(remove2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public ay i(af afVar) {
        ay ayVar;
        JSONObject c2 = afVar.c();
        int c3 = y.c(c2, "id");
        boolean d2 = y.d(c2, "is_module");
        l a2 = a.a();
        if (d2) {
            ayVar = a2.x().get(Integer.valueOf(y.c(c2, "module_id")));
            if (ayVar == null) {
                new aa.a().a("Module WebView created with invalid id").a(aa.g);
                return null;
            }
            ayVar.a(afVar, c3, this);
        } else {
            ayVar = new ay(this.c, afVar, c3, a2.q().d(), this);
        }
        this.h.put(Integer.valueOf(c3), ayVar);
        this.m.put(Integer.valueOf(c3), ayVar);
        JSONObject a3 = y.a();
        y.b(a3, "module_id", ayVar.a());
        afVar.a(a3).b();
        return ayVar;
    }

    /* access modifiers changed from: package-private */
    public boolean j(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        l a2 = a.a();
        View remove = this.m.remove(Integer.valueOf(c2));
        ay remove2 = this.h.remove(Integer.valueOf(c2));
        if (remove2 == null || remove == null) {
            d m2 = a2.m();
            String d2 = afVar.d();
            m2.a(d2, "" + c2);
            return false;
        }
        a2.q().a(remove2.a());
        removeView(remove2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public View k(af afVar) {
        JSONObject c2 = afVar.c();
        int c3 = y.c(c2, "id");
        if (y.d(c2, "editable")) {
            r rVar = new r(this.c, afVar, c3, this);
            rVar.a();
            this.j.put(Integer.valueOf(c3), rVar);
            this.m.put(Integer.valueOf(c3), rVar);
            this.l.put(Integer.valueOf(c3), true);
            return rVar;
        } else if (!y.d(c2, "button")) {
            au auVar = new au(this.c, afVar, c3, this);
            auVar.a();
            this.f.put(Integer.valueOf(c3), auVar);
            this.m.put(Integer.valueOf(c3), auVar);
            this.l.put(Integer.valueOf(c3), false);
            return auVar;
        } else {
            au auVar2 = new au(this.c, 16974145, afVar, c3, this);
            auVar2.a();
            this.f.put(Integer.valueOf(c3), auVar2);
            this.m.put(Integer.valueOf(c3), auVar2);
            this.l.put(Integer.valueOf(c3), false);
            return auVar2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean l(af afVar) {
        TextView textView;
        int c2 = y.c(afVar.c(), "id");
        View remove = this.m.remove(Integer.valueOf(c2));
        if (this.l.remove(Integer.valueOf(this.p)).booleanValue()) {
            textView = this.j.remove(Integer.valueOf(c2));
        } else {
            textView = this.f.remove(Integer.valueOf(c2));
        }
        if (remove == null || textView == null) {
            d m2 = a.a().m();
            String d2 = afVar.d();
            m2.a(d2, "" + c2);
            return false;
        }
        removeView(textView);
        return true;
    }

    /* access modifiers changed from: package-private */
    public u m(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        u uVar = new u(this.c, afVar, c2, this);
        uVar.a();
        this.g.put(Integer.valueOf(c2), uVar);
        this.m.put(Integer.valueOf(c2), uVar);
        return uVar;
    }

    /* access modifiers changed from: package-private */
    public boolean n(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        View remove = this.m.remove(Integer.valueOf(c2));
        u remove2 = this.g.remove(Integer.valueOf(c2));
        if (remove == null || remove2 == null) {
            d m2 = a.a().m();
            String d2 = afVar.d();
            m2.a(d2, "" + c2);
            return false;
        }
        remove2.b();
        removeView(remove2);
        return true;
    }

    private void d(final boolean z2) {
        final AnonymousClass5 r0 = new Runnable() {
            public void run() {
                if (c.this.u == 0) {
                    long unused = c.this.u = System.currentTimeMillis();
                }
                float a2 = bc.a((View) c.this.getParent(), a.c(), true, z2, true);
                double b2 = aw.b(aw.a((Context) a.c()));
                long currentTimeMillis = System.currentTimeMillis();
                if (c.this.u + 200 < currentTimeMillis) {
                    long unused2 = c.this.u = currentTimeMillis;
                    if (!(c.this.s == a2 && c.this.t == b2)) {
                        c.this.a(a2, b2);
                    }
                    float unused3 = c.this.s = a2;
                    double unused4 = c.this.t = b2;
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                while (!c.this.a) {
                    aw.a(r0);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void a(@FloatRange(from = 0.0d, to = 100.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) double d2) {
        JSONObject a2 = y.a();
        y.b(a2, "id", this.p);
        y.a(a2, "ad_session_id", this.r);
        y.a(a2, "exposure", (double) f2);
        y.a(a2, AvidVideoPlaybackListenerImpl.VOLUME, d2);
        new af("AdContainer.on_exposure_change", this.q, a2).b();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        JSONObject a2 = y.a();
        y.a(a2, "id", this.r);
        new af("AdSession.on_error", this.q, a2).b();
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, ax> e() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, au> f() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, u> g() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, ay> h() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, h> i() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, r> j() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, w> k() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, Boolean> l() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, View> m() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<ah> n() {
        return this.v;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> o() {
        return this.w;
    }

    /* access modifiers changed from: package-private */
    public int p() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.o = i2;
    }

    /* access modifiers changed from: package-private */
    public int q() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        this.n = i2;
    }

    /* access modifiers changed from: package-private */
    public boolean r() {
        return this.x;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        this.x = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean s() {
        return this.z;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        this.z = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean t() {
        return this.y;
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z2) {
        this.y = z2;
    }

    /* access modifiers changed from: package-private */
    public void a(AvidManagedVideoAdSession avidManagedVideoAdSession) {
        this.A = avidManagedVideoAdSession;
        a((Map) this.m);
    }

    /* access modifiers changed from: package-private */
    public void a(Map map) {
        if (this.A != null && map != null) {
            for (Map.Entry value : map.entrySet()) {
                this.A.registerFriendlyObstruction((View) value.getValue());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view) {
        if (this.A != null && view != null) {
            this.A.registerFriendlyObstruction(view);
        }
    }
}
