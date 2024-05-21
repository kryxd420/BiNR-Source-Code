package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

class au extends Button {
    private c A;
    private af B;
    private final int a = 0;
    private final int b = 1;
    private final int c = 2;
    private final int d = 3;
    private final int e = 1;
    private final int f = 2;
    private final int g = 3;
    private final int h = 0;
    private final int i = 1;
    private final int j = 2;
    private final int k = 1;
    private final int l = 2;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private String w;
    private String x;
    private String y;
    private String z;

    /* access modifiers changed from: package-private */
    public int a(boolean z2, int i2) {
        switch (i2) {
            case 0:
                return z2 ? 1 : 16;
            case 1:
                return z2 ? 3 : 48;
            case 2:
                return z2 ? 5 : 80;
            default:
                return 17;
        }
    }

    au(Context context, af afVar, int i2, c cVar) {
        super(context);
        this.m = i2;
        this.B = afVar;
        this.A = cVar;
    }

    au(Context context, int i2, af afVar, int i3, c cVar) {
        super(context, (AttributeSet) null, i2);
        this.m = i3;
        this.B = afVar;
        this.A = cVar;
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        JSONObject c2 = afVar.c();
        this.u = y.c(c2, AvidJSONUtil.KEY_X);
        this.v = y.c(c2, AvidJSONUtil.KEY_Y);
        setGravity(a(true, this.u) | a(false, this.v));
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        JSONObject a2 = y.a();
        y.a(a2, "text", getText().toString());
        afVar.a(a2).b();
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
        y.b(a3, "view_id", this.m);
        y.a(a3, "ad_session_id", this.z);
        y.b(a3, "container_x", this.n + x2);
        y.b(a3, "container_y", this.o + y2);
        y.b(a3, "view_x", x2);
        y.b(a3, "view_y", y2);
        y.b(a3, "id", this.A.getId());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.A.c(), a3).b();
                break;
            case 1:
                if (!this.A.r()) {
                    a2.a(m2.f().get(this.z));
                }
                if (x2 > 0 && x2 < getWidth() && y2 > 0 && y2 < getHeight()) {
                    new af("AdContainer.on_touch_ended", this.A.c(), a3).b();
                    break;
                } else {
                    new af("AdContainer.on_touch_cancelled", this.A.c(), a3).b();
                    break;
                }
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.A.c(), a3).b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.A.c(), a3).b();
                break;
            case 5:
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action2)) + this.n);
                y.b(a3, "container_y", ((int) motionEvent.getY(action2)) + this.o);
                y.b(a3, "view_x", (int) motionEvent.getX(action2));
                y.b(a3, "view_y", (int) motionEvent.getY(action2));
                new af("AdContainer.on_touch_began", this.A.c(), a3).b();
                break;
            case 6:
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                int x3 = (int) motionEvent.getX(action3);
                int y3 = (int) motionEvent.getY(action3);
                y.b(a3, "container_x", ((int) motionEvent.getX(action3)) + this.n);
                y.b(a3, "container_y", ((int) motionEvent.getY(action3)) + this.o);
                y.b(a3, "view_x", (int) motionEvent.getX(action3));
                y.b(a3, "view_y", (int) motionEvent.getY(action3));
                if (!this.A.r()) {
                    a2.a(m2.f().get(this.z));
                }
                if (x3 > 0 && x3 < getWidth() && y3 > 0 && y3 < getHeight()) {
                    new af("AdContainer.on_touch_ended", this.A.c(), a3).b();
                    break;
                } else {
                    new af("AdContainer.on_touch_cancelled", this.A.c(), a3).b();
                    break;
                }
                break;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean c(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "id") == this.m && y.c(c2, "container_id") == this.A.d() && y.b(c2, "ad_session_id").equals(this.A.b());
    }

    /* access modifiers changed from: package-private */
    public void a() {
        int i2;
        int i3;
        JSONObject c2 = this.B.c();
        this.z = y.b(c2, "ad_session_id");
        this.n = y.c(c2, AvidJSONUtil.KEY_X);
        this.o = y.c(c2, AvidJSONUtil.KEY_Y);
        this.p = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.q = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        this.s = y.c(c2, "font_family");
        this.r = y.c(c2, "font_style");
        this.t = y.c(c2, "font_size");
        this.w = y.b(c2, "background_color");
        this.x = y.b(c2, "font_color");
        this.y = y.b(c2, "text");
        this.u = y.c(c2, "align_x");
        this.v = y.c(c2, "align_y");
        l a2 = a.a();
        if (this.y.equals("")) {
            this.y = "Learn More";
        }
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = y.d(c2, "wrap_content") ? new FrameLayout.LayoutParams(-2, -2) : new FrameLayout.LayoutParams(this.p, this.q);
        layoutParams.gravity = 0;
        setText(this.y);
        setTextSize((float) this.t);
        if (y.d(c2, "overlay")) {
            this.n = 0;
            this.o = 0;
            i3 = (int) (a2.c.o() * 6.0f);
            i2 = (int) (a2.c.o() * 6.0f);
            int o2 = (int) (a2.c.o() * 4.0f);
            setPadding(o2, o2, o2, o2);
            layoutParams.gravity = 85;
        } else {
            i3 = 0;
            i2 = 0;
        }
        layoutParams.setMargins(this.n, this.o, i3, i2);
        this.A.addView(this, layoutParams);
        switch (this.s) {
            case 0:
                setTypeface(Typeface.DEFAULT);
                break;
            case 1:
                setTypeface(Typeface.SERIF);
                break;
            case 2:
                setTypeface(Typeface.SANS_SERIF);
                break;
            case 3:
                setTypeface(Typeface.MONOSPACE);
                break;
        }
        switch (this.r) {
            case 0:
                setTypeface(getTypeface(), 0);
                break;
            case 1:
                setTypeface(getTypeface(), 1);
                break;
            case 2:
                setTypeface(getTypeface(), 2);
                break;
            case 3:
                setTypeface(getTypeface(), 3);
                break;
        }
        setGravity(a(true, this.u) | a(false, this.v));
        if (!this.w.equals("")) {
            setBackgroundColor(aw.g(this.w));
        }
        if (!this.x.equals("")) {
            setTextColor(aw.g(this.x));
        }
        this.A.n().add(a.a("TextView.set_visible", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.k(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_bounds", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.d(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_font_color", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.f(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_background_color", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.e(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_typeface", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.j(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_font_size", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.g(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_font_style", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.h(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.get_text", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.b(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_text", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.i(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.align", (ah) new ah() {
            public void a(af afVar) {
                if (au.this.c(afVar)) {
                    au.this.a(afVar);
                }
            }
        }, true));
        this.A.o().add("TextView.set_visible");
        this.A.o().add("TextView.set_bounds");
        this.A.o().add("TextView.set_font_color");
        this.A.o().add("TextView.set_background_color");
        this.A.o().add("TextView.set_typeface");
        this.A.o().add("TextView.set_font_size");
        this.A.o().add("TextView.set_font_style");
        this.A.o().add("TextView.get_text");
        this.A.o().add("TextView.set_text");
        this.A.o().add("TextView.align");
        new aa.a().a("TextView added to layout").a(aa.d);
    }

    /* access modifiers changed from: package-private */
    public void d(af afVar) {
        JSONObject c2 = afVar.c();
        this.n = y.c(c2, AvidJSONUtil.KEY_X);
        this.o = y.c(c2, AvidJSONUtil.KEY_Y);
        this.p = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.q = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.n, this.o, 0, 0);
        layoutParams.width = this.p;
        layoutParams.height = this.q;
        setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void e(af afVar) {
        this.w = y.b(afVar.c(), "background_color");
        setBackgroundColor(aw.g(this.w));
    }

    /* access modifiers changed from: package-private */
    public void f(af afVar) {
        this.x = y.b(afVar.c(), "font_color");
        setTextColor(aw.g(this.x));
    }

    /* access modifiers changed from: package-private */
    public void g(af afVar) {
        this.t = y.c(afVar.c(), "font_size");
        setTextSize((float) this.t);
    }

    /* access modifiers changed from: package-private */
    public void h(af afVar) {
        int c2 = y.c(afVar.c(), "font_style");
        this.r = c2;
        switch (c2) {
            case 0:
                setTypeface(getTypeface(), 0);
                return;
            case 1:
                setTypeface(getTypeface(), 1);
                return;
            case 2:
                setTypeface(getTypeface(), 2);
                return;
            case 3:
                setTypeface(getTypeface(), 3);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void i(af afVar) {
        this.y = y.b(afVar.c(), "text");
        setText(this.y);
    }

    /* access modifiers changed from: package-private */
    public void j(af afVar) {
        int c2 = y.c(afVar.c(), "font_family");
        this.s = c2;
        switch (c2) {
            case 0:
                setTypeface(Typeface.DEFAULT);
                return;
            case 1:
                setTypeface(Typeface.SERIF);
                return;
            case 2:
                setTypeface(Typeface.SANS_SERIF);
                return;
            case 3:
                setTypeface(Typeface.MONOSPACE);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void k(af afVar) {
        if (y.d(afVar.c(), TJAdUnitConstants.String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
