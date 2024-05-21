package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.tapdaq.sdk.TapdaqError;

public class AppLovinAdView extends RelativeLayout {
    public static final String NAMESPACE = "http://schemas.applovin.com/android/1.0";
    private AdViewController a;

    public AppLovinAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppLovinAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a((AppLovinAdSize) null, (String) null, (AppLovinSdk) null, context, attributeSet);
    }

    public AppLovinAdView(AppLovinAdSize appLovinAdSize, Context context) {
        this(appLovinAdSize, (String) null, context);
    }

    public AppLovinAdView(AppLovinAdSize appLovinAdSize, String str, Context context) {
        super(context);
        a(appLovinAdSize, str, (AppLovinSdk) null, context, (AttributeSet) null);
    }

    public AppLovinAdView(AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Context context) {
        this(appLovinSdk, appLovinAdSize, (String) null, context);
    }

    public AppLovinAdView(AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, String str, Context context) {
        super(context);
        a(appLovinAdSize, str, appLovinSdk, context, (AttributeSet) null);
    }

    private void a(AttributeSet attributeSet, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(TapdaqError.AD_EXPIRED, TapdaqError.AD_EXPIRED, TapdaqError.AD_EXPIRED));
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setText("AppLovin Ad");
        textView.setGravity(17);
        addView(textView, displayMetrics.widthPixels, (int) TypedValue.applyDimension(1, 50.0f, displayMetrics));
    }

    private void a(AppLovinAdSize appLovinAdSize, String str, AppLovinSdk appLovinSdk, Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            AdViewControllerImpl adViewControllerImpl = new AdViewControllerImpl();
            adViewControllerImpl.initializeAdView(this, context, appLovinAdSize, str, appLovinSdk, attributeSet);
            this.a = adViewControllerImpl;
            return;
        }
        a(attributeSet, context);
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    @Deprecated
    public AdViewController getAdViewController() {
        return this.a;
    }

    public AppLovinAdSize getSize() {
        if (this.a != null) {
            return this.a.getSize();
        }
        return null;
    }

    public String getZoneId() {
        if (this.a != null) {
            return this.a.getZoneId();
        }
        return null;
    }

    @Deprecated
    public boolean isAdReadyToDisplay() {
        return this.a != null && this.a.isAdReadyToDisplay();
    }

    public void loadNextAd() {
        if (this.a != null) {
            this.a.loadNextAd();
        } else {
            Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.a != null) {
            this.a.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.a != null) {
            this.a.onVisibilityChanged(i);
        }
    }

    public void pause() {
        if (this.a != null) {
            this.a.pause();
        }
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, (String) null);
    }

    @Deprecated
    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (this.a != null) {
            this.a.renderAd(appLovinAd, str);
        }
    }

    public void resume() {
        if (this.a != null) {
            this.a.resume();
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        if (this.a != null) {
            this.a.setAdClickListener(appLovinAdClickListener);
        }
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        if (this.a != null) {
            this.a.setAdDisplayListener(appLovinAdDisplayListener);
        }
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (this.a != null) {
            this.a.setAdLoadListener(appLovinAdLoadListener);
        }
    }

    @Deprecated
    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        if (this.a != null) {
            this.a.setAdVideoPlaybackListener(appLovinAdVideoPlaybackListener);
        }
    }

    public void setAdViewEventListener(AppLovinAdViewEventListener appLovinAdViewEventListener) {
        if (this.a != null) {
            this.a.setAdViewEventListener(appLovinAdViewEventListener);
        }
    }

    public void setAutoDestroy(boolean z) {
        if (this.a != null) {
            this.a.setAutoDestroy(z);
        }
    }

    public String toString() {
        return "AppLovinAdView{zoneId='" + getZoneId() + "', size=" + getSize() + '}';
    }
}
