package com.applovin.impl.sdk.e;

import android.util.AttributeSet;
import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAdSize;

public class b {
    public static AppLovinAdSize a(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(AppLovinAdView.NAMESPACE, "size");
        if (k.b(attributeValue)) {
            return AppLovinAdSize.fromString(attributeValue);
        }
        return null;
    }

    public static boolean b(AttributeSet attributeSet) {
        return attributeSet != null && attributeSet.getAttributeBooleanValue(AppLovinAdView.NAMESPACE, "loadAdOnCreate", false);
    }
}
