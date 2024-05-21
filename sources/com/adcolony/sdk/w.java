package com.adcolony.sdk;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import java.io.File;
import org.json.JSONObject;

class w extends ImageView {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private af k;
    private c l;

    w(Context context, af afVar, int i2, c cVar) {
        super(context);
        this.a = i2;
        this.k = afVar;
        this.l = cVar;
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
        y.a(a3, "ad_session_id", this.j);
        y.b(a3, "container_x", this.b + x);
        y.b(a3, "container_y", this.c + y);
        y.b(a3, "view_x", x);
        y.b(a3, "view_y", y);
        y.b(a3, "id", this.l.getId());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.l.c(), a3).b();
                break;
            case 1:
                if (!this.l.r()) {
                    a2.a(m.f().get(this.j));
                }
                if (x > 0 && x < this.d && y > 0 && y < this.e) {
                    new af("AdContainer.on_touch_ended", this.l.c(), a3).b();
                    break;
                } else {
                    new af("AdContainer.on_touch_cancelled", this.l.c(), a3).b();
                    break;
                }
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.l.c(), a3).b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.l.c(), a3).b();
                break;
            case 5:
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                y.b(a3, "container_x", ((int) motionEvent.getX(action2)) + this.b);
                y.b(a3, "container_y", ((int) motionEvent.getY(action2)) + this.c);
                y.b(a3, "view_x", (int) motionEvent.getX(action2));
                y.b(a3, "view_y", (int) motionEvent.getY(action2));
                new af("AdContainer.on_touch_began", this.l.c(), a3).b();
                break;
            case 6:
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                int x2 = (int) motionEvent.getX(action3);
                int y2 = (int) motionEvent.getY(action3);
                y.b(a3, "container_x", ((int) motionEvent.getX(action3)) + this.b);
                y.b(a3, "container_y", ((int) motionEvent.getY(action3)) + this.c);
                y.b(a3, "view_x", (int) motionEvent.getX(action3));
                y.b(a3, "view_y", (int) motionEvent.getY(action3));
                if (!this.l.r()) {
                    a2.a(m.f().get(this.j));
                }
                if (x2 > 0 && x2 < this.d && y2 > 0 && y2 < this.e) {
                    new af("AdContainer.on_touch_ended", this.l.c(), a3).b();
                    break;
                } else {
                    new af("AdContainer.on_touch_cancelled", this.l.c(), a3).b();
                    break;
                }
                break;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean a(af afVar) {
        JSONObject c2 = afVar.c();
        return y.c(c2, "id") == this.a && y.c(c2, "container_id") == this.l.d() && y.b(c2, "ad_session_id").equals(this.l.b());
    }

    /* access modifiers changed from: package-private */
    public void a() {
        JSONObject c2 = this.k.c();
        this.j = y.b(c2, "ad_session_id");
        this.b = y.c(c2, AvidJSONUtil.KEY_X);
        this.c = y.c(c2, AvidJSONUtil.KEY_Y);
        this.d = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.e = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        this.i = y.b(c2, "filepath");
        this.f = y.d(c2, "dpi");
        this.g = y.d(c2, "invert_y");
        this.h = y.d(c2, "wrap_content");
        setImageURI(Uri.fromFile(new File(this.i)));
        if (this.f) {
            float o = (((float) this.e) * a.a().n().o()) / ((float) getDrawable().getIntrinsicHeight());
            this.e = (int) (((float) getDrawable().getIntrinsicHeight()) * o);
            this.d = (int) (((float) getDrawable().getIntrinsicWidth()) * o);
            this.b -= this.d;
            this.c = this.g ? this.c + this.e : this.c - this.e;
        }
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = this.h ? new FrameLayout.LayoutParams(-2, -2) : new FrameLayout.LayoutParams(this.d, this.e);
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.gravity = 0;
        this.l.addView(this, layoutParams);
        this.l.n().add(a.a("ImageView.set_visible", (ah) new ah() {
            public void a(af afVar) {
                if (w.this.a(afVar)) {
                    w.this.d(afVar);
                }
            }
        }, true));
        this.l.n().add(a.a("ImageView.set_bounds", (ah) new ah() {
            public void a(af afVar) {
                if (w.this.a(afVar)) {
                    w.this.b(afVar);
                }
            }
        }, true));
        this.l.n().add(a.a("ImageView.set_image", (ah) new ah() {
            public void a(af afVar) {
                if (w.this.a(afVar)) {
                    w.this.c(afVar);
                }
            }
        }, true));
        this.l.o().add("ImageView.set_visible");
        this.l.o().add("ImageView.set_bounds");
        this.l.o().add("ImageView.set_image");
    }

    /* access modifiers changed from: private */
    public void b(af afVar) {
        JSONObject c2 = afVar.c();
        this.b = y.c(c2, AvidJSONUtil.KEY_X);
        this.c = y.c(c2, AvidJSONUtil.KEY_Y);
        this.d = y.c(c2, AvidJSONUtil.KEY_WIDTH);
        this.e = y.c(c2, AvidJSONUtil.KEY_HEIGHT);
        if (this.f) {
            float o = (((float) this.e) * a.a().n().o()) / ((float) getDrawable().getIntrinsicHeight());
            this.e = (int) (((float) getDrawable().getIntrinsicHeight()) * o);
            this.d = (int) (((float) getDrawable().getIntrinsicWidth()) * o);
            this.b -= this.d;
            this.c -= this.e;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.width = this.d;
        layoutParams.height = this.e;
        setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void c(af afVar) {
        this.i = y.b(afVar.c(), "filepath");
        setImageURI(Uri.fromFile(new File(this.i)));
    }

    /* access modifiers changed from: private */
    public void d(af afVar) {
        if (y.d(afVar.c(), TJAdUnitConstants.String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    /* access modifiers changed from: package-private */
    public int[] b() {
        return new int[]{this.b, this.c, this.d, this.e};
    }
}
