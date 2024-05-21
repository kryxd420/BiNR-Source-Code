package com.tapjoy.mraid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

public class BasicWebView extends WebView {
    @SuppressLint({"NewApi"})
    public BasicWebView(Context context) {
        super(context);
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        new GestureDetector(new a());
        if (getSettings() != null) {
            getSettings().setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= 17) {
                getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
        }
        setBackgroundColor(0);
    }

    public BasicWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    class a extends GestureDetector.SimpleOnGestureListener {
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }

        a() {
        }
    }
}
