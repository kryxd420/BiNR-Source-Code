package com.applovin.mediation.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.mediation.ads.MaxAdViewImpl;
import com.applovin.impl.sdk.e.n;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.sdk.AppLovinSdk;
import com.tapdaq.sdk.TapdaqError;

public class MaxAdView extends RelativeLayout {
    private MaxAdViewImpl a;

    public MaxAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaxAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        String attributeValue = attributeSet.getAttributeValue(AppLovinAdView.NAMESPACE, "adUnitId");
        if (attributeValue == null) {
            throw new IllegalArgumentException("No ad unit ID specified");
        } else if (TextUtils.isEmpty(attributeValue)) {
            throw new IllegalArgumentException("Empty ad unit ID specified");
        } else if (context instanceof Activity) {
            Activity activity = (Activity) context;
            a(attributeValue, AppLovinSdk.getInstance(activity), activity);
        } else {
            throw new IllegalArgumentException("Max ad view context is not an activity");
        }
    }

    public MaxAdView(String str, Activity activity) {
        this(str, AppLovinSdk.getInstance(activity), activity);
    }

    public MaxAdView(String str, AppLovinSdk appLovinSdk, Activity activity) {
        super(activity);
        a(str, appLovinSdk, activity);
    }

    private void a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(TapdaqError.AD_EXPIRED, TapdaqError.AD_EXPIRED, TapdaqError.AD_EXPIRED));
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setText("Max Ad");
        textView.setGravity(17);
        addView(textView, displayMetrics.widthPixels, (int) TypedValue.applyDimension(1, 50.0f, displayMetrics));
    }

    private void a(String str, AppLovinSdk appLovinSdk, Activity activity) {
        if (!isInEditMode()) {
            this.a = new MaxAdViewImpl(str, this, n.a(appLovinSdk), activity);
        } else {
            a(activity);
        }
    }

    public void destroy() {
        this.a.destroy();
    }

    public void loadAd() {
        this.a.loadAd();
    }

    public void setExtraParameter(String str, String str2) {
        this.a.setExtraParameter(str, str2);
    }

    public void setListener(MaxAdViewAdListener maxAdViewAdListener) {
        this.a.setListener(maxAdViewAdListener);
    }

    public void startAutoRefresh() {
        this.a.startAutoRefresh();
    }

    public void stopAutoRefresh() {
        this.a.stopAutoRefresh();
    }

    public String toString() {
        return this.a.toString();
    }
}
