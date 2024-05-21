package com.tapjoy;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class TJCloseButton extends ImageButton {
    private static final String a = "TJCloseButton";
    private ClosePosition b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public boolean d;

    public enum ClosePosition {
        TOP_LEFT(new int[]{10, 9}),
        TOP_CENTER(new int[]{10, 14}),
        TOP_RIGHT(new int[]{10, 11}),
        CENTER(new int[]{13}),
        BOTTOM_LEFT(new int[]{12, 9}),
        BOTTOM_CENTER(new int[]{12, 14}),
        BOTTOM_RIGHT(new int[]{12, 11});
        
        final RelativeLayout.LayoutParams a;

        private ClosePosition(int[] iArr) {
            this.a = new RelativeLayout.LayoutParams(-2, -2);
            for (int addRule : iArr) {
                this.a.addRule(addRule);
            }
            int deviceScreenDensityScale = (int) (TapjoyConnectCore.getDeviceScreenDensityScale() * -10.0f);
            this.a.setMargins(0, deviceScreenDensityScale, deviceScreenDensityScale, 0);
        }
    }

    public TJCloseButton(Context context) {
        this(context, ClosePosition.TOP_RIGHT);
    }

    public TJCloseButton(Context context, ClosePosition closePosition) {
        super(context);
        Bitmap bitmap;
        this.c = true;
        this.b = closePosition;
        Bitmap loadBitmapFromJar = TapjoyUtil.loadBitmapFromJar("tj_close_button.png", context);
        if (loadBitmapFromJar == null) {
            try {
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("tj_close_button", "drawable", context.getPackageName()));
            } catch (Exception unused) {
                TapjoyLog.w(a, "Could not find close button asset");
            }
            setImageBitmap(bitmap);
            setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
            setLayoutParams(this.b.a);
        }
        bitmap = loadBitmapFromJar;
        setImageBitmap(bitmap);
        setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        setLayoutParams(this.b.a);
    }

    /* access modifiers changed from: protected */
    @TargetApi(11)
    public void onAttachedToWindow() {
        if (Build.VERSION.SDK_INT >= 12) {
            setAlpha(0.0f);
            setVisibility(0);
            this.d = true;
            setClickable(false);
            new Handler().postDelayed(new Runnable() {
                @SuppressLint({"NewApi"})
                public final void run() {
                    TJCloseButton.this.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                        public final void onAnimationRepeat(Animator animator) {
                        }

                        public final void onAnimationStart(Animator animator) {
                        }

                        public final void onAnimationCancel(Animator animator) {
                            TJCloseButton.this.setClickable(TJCloseButton.this.c);
                            boolean unused = TJCloseButton.this.d = false;
                        }

                        public final void onAnimationEnd(Animator animator) {
                            TJCloseButton.this.setClickable(TJCloseButton.this.c);
                            boolean unused = TJCloseButton.this.d = false;
                        }
                    });
                }
            }, FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        }
    }

    /* access modifiers changed from: package-private */
    public void setClickableRequested(boolean z) {
        this.c = z;
        if (!this.d) {
            setClickable(z);
        }
    }
}
