package com.adcolony.sdk;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

class h extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private c h;
    private af i;

    h(Context context, af afVar, int i2, c cVar) {
        super(context);
        this.h = cVar;
        this.i = afVar;
        this.a = i2;
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "id") == this.a && y.c(c2, "container_id") == this.h.d() && y.b(c2, "ad_session_id").equals(this.h.b());
    }

    /* access modifiers changed from: package-private */
    public void a() {
        JSONObject c2 = this.i.c();
        this.g = y.b(c2, "ad_session_id");
        this.b = y.c(c2, AvidJSONUtil.KEY_X);
        this.c = y.c(c2, AvidJSONUtil.KEY_Y);
        this.d = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.e = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        this.f = y.b(c2, "color");
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.d, this.e);
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.gravity = 0;
        this.h.addView(this, layoutParams);
        setBackgroundColor(aw.g(this.f));
        this.h.n().add(a.a("ColorView.set_bounds", (ah) new ah() {
            public void a(af afVar) {
                if (h.this.a(afVar)) {
                    h.this.b(afVar);
                }
            }
        }, true));
        this.h.n().add(a.a("ColorView.set_visible", (ah) new ah() {
            public void a(af afVar) {
                if (h.this.a(afVar)) {
                    h.this.d(afVar);
                }
            }
        }, true));
        this.h.n().add(a.a("ColorView.set_color", (ah) new ah() {
            public void a(af afVar) {
                if (h.this.a(afVar)) {
                    h.this.c(afVar);
                }
            }
        }, true));
        this.h.o().add("ColorView.set_bounds");
        this.h.o().add("ColorView.set_visible");
        this.h.o().add("ColorView.set_color");
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
        y.b(a3, "view_id", this.a);
        y.a(a3, "ad_session_id", this.g);
        y.b(a3, "container_x", this.b + x);
        y.b(a3, "container_y", this.c + y);
        y.b(a3, "view_x", x);
        y.b(a3, "view_y", y);
        y.b(a3, "id", this.h.d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.h.c(), a3).b();
                break;
            case 1:
                if (!this.h.r()) {
                    a2.a(m.f().get(this.g));
                }
                new af("AdContainer.on_touch_ended", this.h.c(), a3).b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.h.c(), a3).b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.h.c(), a3).b();
                break;
            case 5:
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action2)) + this.b);
                y.b(a3, "container_y", ((int) motionEvent.getY(action2)) + this.c);
                y.b(a3, "view_x", (int) motionEvent.getX(action2));
                y.b(a3, "view_y", (int) motionEvent.getY(action2));
                new af("AdContainer.on_touch_began", this.h.c(), a3).b();
                break;
            case 6:
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action3)) + this.b);
                y.b(a3, "container_y", ((int) motionEvent.getY(action3)) + this.c);
                y.b(a3, "view_x", (int) motionEvent.getX(action3));
                y.b(a3, "view_y", (int) motionEvent.getY(action3));
                if (!this.h.r()) {
                    a2.a(m.f().get(this.g));
                }
                new af("AdContainer.on_touch_ended", this.h.c(), a3).b();
                break;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        JSONObject c2 = afVar.c();
        this.b = y.c(c2, AvidJSONUtil.KEY_X);
        this.c = y.c(c2, AvidJSONUtil.KEY_Y);
        this.d = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.e = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.width = this.d;
        layoutParams.height = this.e;
        setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        setBackgroundColor(aw.g(y.b(afVar.c(), "color")));
    }

    /* access modifiers changed from: package-private */
    public void d(af afVar) {
        if (y.d(afVar.c(), TJAdUnitConstants.String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
