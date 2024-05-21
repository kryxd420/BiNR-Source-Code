package com.tapjoy.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public final class is extends RelativeLayout {
    private hv a;
    /* access modifiers changed from: private */
    public a b;
    private ad c = ad.UNSPECIFIED;
    private int d = 0;
    private int e = 0;
    private ie f = null;
    private ArrayList g = null;
    private ArrayList h = null;

    public interface a {
        void a();

        void a(id idVar);
    }

    public is(Context context, hv hvVar, a aVar) {
        super(context);
        this.a = hvVar;
        this.b = aVar;
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.a();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r17, int r18) {
        /*
            r16 = this;
            r0 = r16
            int r1 = android.view.View.MeasureSpec.getSize(r17)
            int r2 = android.view.View.MeasureSpec.getSize(r18)
            if (r1 < r2) goto L_0x001a
            com.tapjoy.internal.ad r3 = r0.c
            com.tapjoy.internal.ad r4 = com.tapjoy.internal.ad.LANDSCAPE
            if (r3 == r4) goto L_0x0027
            com.tapjoy.internal.ad r3 = com.tapjoy.internal.ad.LANDSCAPE
            r0.c = r3
            r16.a()
            goto L_0x0027
        L_0x001a:
            com.tapjoy.internal.ad r3 = r0.c
            com.tapjoy.internal.ad r4 = com.tapjoy.internal.ad.PORTRAIT
            if (r3 == r4) goto L_0x0027
            com.tapjoy.internal.ad r3 = com.tapjoy.internal.ad.PORTRAIT
            r0.c = r3
            r16.a()
        L_0x0027:
            int r3 = r0.d
            if (r3 != r1) goto L_0x002f
            int r3 = r0.e
            if (r3 == r2) goto L_0x0116
        L_0x002f:
            r0.d = r1
            r0.e = r2
            float r1 = (float) r1
            float r2 = (float) r2
            com.tapjoy.internal.ie r3 = r0.f
            r4 = 0
            r5 = 1073741824(0x40000000, float:2.0)
            if (r3 == 0) goto L_0x0081
            com.tapjoy.internal.ie r3 = r0.f
            android.graphics.PointF r3 = r3.b
            if (r3 == 0) goto L_0x0081
            com.tapjoy.internal.ie r3 = r0.f
            android.graphics.PointF r3 = r3.b
            float r3 = r3.y
            float r3 = r3 * r1
            com.tapjoy.internal.ie r6 = r0.f
            android.graphics.PointF r6 = r6.b
            float r6 = r6.x
            float r3 = r3 / r6
            float r3 = r3 / r2
            r6 = 1065353216(0x3f800000, float:1.0)
            int r7 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r7 >= 0) goto L_0x006a
            com.tapjoy.internal.ie r3 = r0.f
            android.graphics.PointF r3 = r3.b
            float r3 = r3.y
            float r3 = r3 * r1
            com.tapjoy.internal.ie r6 = r0.f
            android.graphics.PointF r6 = r6.b
            float r6 = r6.x
            float r3 = r3 / r6
            float r2 = r2 - r3
            float r2 = r2 / r5
            goto L_0x0083
        L_0x006a:
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x0081
            com.tapjoy.internal.ie r3 = r0.f
            android.graphics.PointF r3 = r3.b
            float r3 = r3.x
            float r3 = r3 * r2
            com.tapjoy.internal.ie r6 = r0.f
            android.graphics.PointF r6 = r6.b
            float r6 = r6.y
            float r3 = r3 / r6
            float r1 = r1 - r3
            float r1 = r1 / r5
            r4 = r1
            r1 = r3
        L_0x0081:
            r3 = r2
            r2 = 0
        L_0x0083:
            java.lang.Iterable r6 = com.tapjoy.internal.af.a(r16)
            java.util.Iterator r6 = r6.iterator()
        L_0x008b:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0116
            java.lang.Object r7 = r6.next()
            android.view.View r7 = (android.view.View) r7
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            java.lang.Object r7 = r7.getTag()
            com.tapjoy.internal.id r7 = (com.tapjoy.internal.id) r7
            com.tapjoy.internal.if r9 = r7.a
            float r9 = r9.a(r1, r3)
            com.tapjoy.internal.if r10 = r7.b
            float r10 = r10.a(r1, r3)
            com.tapjoy.internal.if r11 = r7.c
            float r11 = r11.a(r1, r3)
            com.tapjoy.internal.if r12 = r7.d
            float r12 = r12.a(r1, r3)
            int r13 = r7.e
            int r7 = r7.f
            r14 = 14
            if (r13 != r14) goto L_0x00c9
            float r13 = r1 - r11
            float r13 = r13 / r5
            float r9 = r9 + r13
            r13 = 9
        L_0x00c9:
            r14 = 15
            r15 = 10
            if (r7 != r14) goto L_0x00d5
            float r7 = r3 - r12
            float r7 = r7 / r5
            float r10 = r10 + r7
            r7 = 10
        L_0x00d5:
            r14 = -1
            r8.addRule(r13, r14)
            r8.addRule(r7, r14)
            int r11 = java.lang.Math.round(r11)
            r8.width = r11
            int r11 = java.lang.Math.round(r12)
            r8.height = r11
            r11 = 9
            if (r13 != r11) goto L_0x00f4
            float r9 = r9 + r4
            int r9 = java.lang.Math.round(r9)
            r8.leftMargin = r9
            goto L_0x00ff
        L_0x00f4:
            r11 = 11
            if (r13 != r11) goto L_0x00ff
            float r9 = r9 + r4
            int r9 = java.lang.Math.round(r9)
            r8.rightMargin = r9
        L_0x00ff:
            if (r7 != r15) goto L_0x0109
            float r10 = r10 + r2
            int r7 = java.lang.Math.round(r10)
            r8.topMargin = r7
            goto L_0x008b
        L_0x0109:
            r9 = 12
            if (r7 != r9) goto L_0x008b
            float r10 = r10 + r2
            int r7 = java.lang.Math.round(r10)
            r8.bottomMargin = r7
            goto L_0x008b
        L_0x0116:
            super.onMeasure(r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.is.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            if (this.h != null) {
                Iterator it = this.h.iterator();
                while (it.hasNext()) {
                    ik ikVar = (ik) ((WeakReference) it.next()).get();
                    if (ikVar != null) {
                        ikVar.setVisibility(4);
                        ikVar.b();
                    }
                }
            }
            if (this.g != null) {
                Iterator it2 = this.g.iterator();
                while (it2.hasNext()) {
                    ik ikVar2 = (ik) ((WeakReference) it2.next()).get();
                    if (ikVar2 != null) {
                        ikVar2.setVisibility(0);
                        ikVar2.a();
                    }
                }
                return;
            }
            return;
        }
        if (this.g != null) {
            Iterator it3 = this.g.iterator();
            while (it3.hasNext()) {
                ik ikVar3 = (ik) ((WeakReference) it3.next()).get();
                if (ikVar3 != null) {
                    ikVar3.b();
                }
            }
        }
        if (this.h != null) {
            Iterator it4 = this.h.iterator();
            while (it4.hasNext()) {
                ik ikVar4 = (ik) ((WeakReference) it4.next()).get();
                if (ikVar4 != null) {
                    ikVar4.b();
                }
            }
        }
    }

    private void a() {
        ik ikVar;
        ik ikVar2;
        Bitmap bitmap;
        Iterator it = this.a.a.iterator();
        ie ieVar = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ie ieVar2 = (ie) it.next();
            if (ieVar2.a == this.c) {
                ieVar = ieVar2;
                break;
            } else if (ieVar2.a == ad.UNSPECIFIED) {
                ieVar = ieVar2;
            }
        }
        removeAllViews();
        if (this.g != null) {
            Iterator it2 = this.g.iterator();
            while (it2.hasNext()) {
                ik ikVar3 = (ik) ((WeakReference) it2.next()).get();
                if (ikVar3 != null) {
                    ikVar3.c();
                }
            }
            this.g.clear();
        }
        if (this.h != null) {
            Iterator it3 = this.h.iterator();
            while (it3.hasNext()) {
                ik ikVar4 = (ik) ((WeakReference) it3.next()).get();
                if (ikVar4 != null) {
                    ikVar4.c();
                }
            }
            this.h.clear();
        }
        if (ieVar != null) {
            this.f = ieVar;
            Context context = getContext();
            Iterator it4 = ieVar.c.iterator();
            while (it4.hasNext()) {
                id idVar = (id) it4.next();
                RelativeLayout relativeLayout = new RelativeLayout(context);
                if (idVar.l.c != null) {
                    ik ikVar5 = new ik(context);
                    ikVar5.setScaleType(ImageView.ScaleType.FIT_XY);
                    ikVar5.a(idVar.l.d, idVar.l.c);
                    if (this.g == null) {
                        this.g = new ArrayList();
                    }
                    this.g.add(new WeakReference(ikVar5));
                    ikVar = ikVar5;
                } else {
                    ikVar = null;
                }
                if (idVar.m == null || idVar.m.c == null) {
                    ikVar2 = null;
                } else {
                    ik ikVar6 = new ik(context);
                    ikVar6.setScaleType(ImageView.ScaleType.FIT_XY);
                    ikVar6.a(idVar.m.d, idVar.m.c);
                    if (this.h == null) {
                        this.h = new ArrayList();
                    }
                    this.h.add(new WeakReference(ikVar6));
                    ikVar2 = ikVar6;
                }
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                Bitmap bitmap2 = idVar.l.b;
                if (idVar.m != null) {
                    bitmap = idVar.m.b;
                } else {
                    bitmap = null;
                }
                final BitmapDrawable bitmapDrawable = bitmap2 != null ? new BitmapDrawable((Resources) null, bitmap2) : null;
                final BitmapDrawable bitmapDrawable2 = bitmap != null ? new BitmapDrawable((Resources) null, bitmap) : null;
                if (bitmapDrawable != null) {
                    ae.a(relativeLayout, bitmapDrawable);
                }
                if (ikVar != null) {
                    relativeLayout.addView(ikVar, layoutParams2);
                    ikVar.a();
                }
                if (ikVar2 != null) {
                    relativeLayout.addView(ikVar2, layoutParams2);
                    ikVar2.setVisibility(4);
                }
                final ik ikVar7 = ikVar2;
                final ik ikVar8 = ikVar;
                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            if (!(ikVar7 == null && bitmapDrawable2 == null)) {
                                if (ikVar8 != null) {
                                    ikVar8.b();
                                    ikVar8.setVisibility(4);
                                }
                                ae.a(view, (Drawable) null);
                            }
                            if (bitmapDrawable2 != null) {
                                ae.a(view, bitmapDrawable2);
                            } else if (ikVar7 != null) {
                                ikVar7.setVisibility(0);
                                ikVar7.a();
                            }
                        } else {
                            boolean z = true;
                            if (motionEvent.getAction() == 1) {
                                float x = motionEvent.getX();
                                float y = motionEvent.getY();
                                if (x >= 0.0f && x < ((float) view.getWidth()) && y >= 0.0f && y < ((float) view.getHeight())) {
                                    z = false;
                                }
                                if (z) {
                                    if (bitmapDrawable != null) {
                                        ae.a(view, bitmapDrawable);
                                    } else if (bitmapDrawable2 != null) {
                                        ae.a(view, (Drawable) null);
                                    }
                                }
                                if (ikVar7 != null) {
                                    ikVar7.b();
                                    ikVar7.setVisibility(4);
                                }
                                if (!((ikVar7 == null && bitmapDrawable2 == null) || ikVar8 == null || !z)) {
                                    ikVar8.setVisibility(0);
                                    ikVar8.a();
                                }
                            }
                        }
                        return false;
                    }
                });
                final RelativeLayout relativeLayout2 = relativeLayout;
                final id idVar2 = idVar;
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        if (ikVar7 != null) {
                            ikVar7.b();
                            relativeLayout2.removeView(ikVar7);
                        }
                        if (ikVar8 != null) {
                            ikVar8.b();
                            relativeLayout2.removeView(ikVar8);
                        }
                        is.this.b.a(idVar2);
                    }
                });
                relativeLayout.setTag(idVar);
                addView(relativeLayout, layoutParams);
            }
        }
    }
}
