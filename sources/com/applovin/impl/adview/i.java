package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.applovin.impl.adview.f;
import com.applovin.impl.sdk.ad.a;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.d;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinSdkUtils;

class i extends Dialog implements h {
    private final Activity a;
    /* access modifiers changed from: private */
    public final j b;
    /* access modifiers changed from: private */
    public final p c;
    /* access modifiers changed from: private */
    public final c d;
    private final a e;
    /* access modifiers changed from: private */
    public RelativeLayout f;
    /* access modifiers changed from: private */
    public f g;

    i(a aVar, c cVar, Activity activity, j jVar) {
        super(activity, 16973840);
        if (aVar == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (cVar == null) {
            throw new IllegalArgumentException("No main view specified");
        } else if (jVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity != null) {
            this.b = jVar;
            this.c = jVar.v();
            this.a = activity;
            this.d = cVar;
            this.e = aVar;
            requestWindowFeature(1);
            setCancelable(false);
        } else {
            throw new IllegalArgumentException("No activity specified");
        }
    }

    private int a(int i) {
        return AppLovinSdkUtils.dpToPx(this.a, i);
    }

    private void a(f.a aVar) {
        if (this.g != null) {
            this.c.c("ExpandedAdDialog", "Attempting to create duplicate close button");
            return;
        }
        this.g = f.a(this.b, getContext(), aVar);
        this.g.setVisibility(8);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i.this.c();
            }
        });
        this.g.setClickable(false);
        int a2 = a(((Integer) this.b.a(b.cC)).intValue());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(10);
        int i = 11;
        layoutParams.addRule(((Boolean) this.b.a(b.cF)).booleanValue() ? 9 : 11);
        this.g.a(a2);
        int a3 = a(((Integer) this.b.a(b.cE)).intValue());
        int a4 = a(((Integer) this.b.a(b.cD)).intValue());
        layoutParams.setMargins(a4, a3, a4, 0);
        this.f.addView(this.g, layoutParams);
        this.g.bringToFront();
        int a5 = a(((Integer) this.b.a(b.cG)).intValue());
        View view = new View(this.a);
        view.setBackgroundColor(0);
        int i2 = a2 + a5;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams2.addRule(10);
        if (((Boolean) this.b.a(b.cF)).booleanValue()) {
            i = 9;
        }
        layoutParams2.addRule(i);
        layoutParams2.setMargins(a4 - a(5), a3 - a(5), a4 - a(5), 0);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (i.this.g.isClickable()) {
                    i.this.g.performClick();
                }
            }
        });
        this.f.addView(view, layoutParams2);
        view.bringToFront();
    }

    private void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
        this.f = new RelativeLayout(this.a);
        this.f.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f.setBackgroundColor(-1157627904);
        this.f.addView(this.d);
        if (!this.e.k()) {
            a(this.e.l());
            d();
        }
        setContentView(this.f);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.d.a("javascript:al_onCloseTapped();", (Runnable) new Runnable() {
            public void run() {
                i.this.dismiss();
            }
        });
    }

    private void d() {
        this.a.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (i.this.g == null) {
                        i.this.c();
                    }
                    i.this.g.setVisibility(0);
                    i.this.g.bringToFront();
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(((Long) i.this.b.a(b.cB)).longValue());
                    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationEnd(Animation animation) {
                            i.this.g.setClickable(true);
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }
                    });
                    i.this.g.startAnimation(alphaAnimation);
                } catch (Throwable th) {
                    i.this.c.b("ExpandedAdDialog", "Unable to fade in close button", th);
                    i.this.c();
                }
            }
        });
    }

    public a a() {
        return this.e;
    }

    public void dismiss() {
        d b2 = this.d.b();
        if (b2 != null) {
            b2.e();
        }
        this.a.runOnUiThread(new Runnable() {
            public void run() {
                i.this.f.removeView(i.this.d);
                i.super.dismiss();
            }
        });
    }

    public void onBackPressed() {
        this.d.a("javascript:al_onBackPressed();", (Runnable) new Runnable() {
            public void run() {
                i.this.dismiss();
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            Window window = getWindow();
            if (window != null) {
                window.setFlags(this.a.getWindow().getAttributes().flags, this.a.getWindow().getAttributes().flags);
                if (this.e.w()) {
                    window.addFlags(16777216);
                    return;
                }
                return;
            }
            this.c.d("ExpandedAdDialog", "Unable to turn on hardware acceleration - window is null");
        } catch (Throwable th) {
            this.c.b("ExpandedAdDialog", "Setting window flags failed.", th);
        }
    }
}
