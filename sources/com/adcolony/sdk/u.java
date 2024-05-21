package com.adcolony.sdk;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

class u extends GLSurfaceView {
    v a;
    int b;
    int c;
    int d;
    int e;
    int f;
    String g;
    boolean h;
    c i;
    af j;
    boolean k;

    u(Context context, af afVar, int i2, c cVar) {
        super(context);
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        this.b = i2;
        this.j = afVar;
        this.i = cVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        l a2 = a.a();
        d m = a2.m();
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject a3 = y.a();
        y.b(a3, "view_id", this.b);
        y.a(a3, "ad_session_id", this.g);
        y.b(a3, "container_x", this.c + x);
        y.b(a3, "container_y", this.d + y);
        y.b(a3, "view_x", x);
        y.b(a3, "view_y", y);
        y.b(a3, "id", this.i.d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.i.c(), a3).b();
                break;
            case 1:
                if (!this.i.r()) {
                    a2.a(m.f().get(this.g));
                }
                new af("AdContainer.on_touch_ended", this.i.c(), a3).b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.i.c(), a3).b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.i.c(), a3).b();
                break;
            case 5:
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action2)) + this.c);
                y.b(a3, "container_y", ((int) motionEvent.getY(action2)) + this.d);
                y.b(a3, "view_x", (int) motionEvent.getX(action2));
                y.b(a3, "view_y", (int) motionEvent.getY(action2));
                new af("AdContainer.on_touch_began", this.i.c(), a3).b();
                break;
            case 6:
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action3)) + this.c);
                y.b(a3, "container_y", ((int) motionEvent.getY(action3)) + this.d);
                y.b(a3, "view_x", (int) motionEvent.getX(action3));
                y.b(a3, "view_y", (int) motionEvent.getY(action3));
                if (!this.i.r()) {
                    a2.a(m.f().get(this.g));
                }
                new af("AdContainer.on_touch_ended", this.i.c(), a3).b();
                break;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        JSONObject c2 = this.j.c();
        this.g = y.b(c2, "ad_session_id");
        this.c = y.c(c2, AvidJSONUtil.KEY_X);
        this.d = y.c(c2, AvidJSONUtil.KEY_Y);
        this.e = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.f = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        this.h = y.d(c2, TJAdUnitConstants.String.TRANSPARENT);
        setEGLConfigChooser(8, 8, 8, 8, 16, 8);
        if (this.h) {
            getHolder().setFormat(-3);
            setZOrderOnTop(true);
        } else {
            getHolder().setFormat(1);
        }
        this.a = new v(this, true, this.g);
        setRenderer(this.a);
        this.i.n().add(a.a("RenderView.set_visible", (ah) new ah() {
            public void a(af afVar) {
                if (u.this.a(afVar)) {
                    u.this.c(afVar);
                }
            }
        }, true));
        this.i.n().add(a.a("RenderView.set_bounds", (ah) new ah() {
            public void a(af afVar) {
                if (u.this.a(afVar)) {
                    u.this.b(afVar);
                }
            }
        }, true));
        this.i.o().add("RenderView.set_visible");
        this.i.o().add("RenderView.set_bounds");
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.e, this.f);
        layoutParams.setMargins(this.c, this.d, 0, 0);
        layoutParams.gravity = 0;
        this.i.addView(this, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        if (this.k) {
            return false;
        }
        this.k = true;
        this.a.a();
        return true;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        b();
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "id") == this.b && y.c(c2, "container_id") == this.i.d() && y.b(c2, "ad_session_id").equals(this.i.b());
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        JSONObject c2 = afVar.c();
        this.c = y.c(c2, AvidJSONUtil.KEY_X);
        this.d = y.c(c2, AvidJSONUtil.KEY_Y);
        this.e = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.f = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.c, this.d, 0, 0);
        layoutParams.width = this.e;
        layoutParams.height = this.f;
        setLayoutParams(layoutParams);
        getHolder().setFixedSize(this.e, this.f);
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        if (y.d(afVar.c(), TJAdUnitConstants.String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
