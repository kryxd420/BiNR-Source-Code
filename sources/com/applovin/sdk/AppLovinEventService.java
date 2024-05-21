package com.applovin.sdk;

import android.content.Intent;
import java.util.Map;

public interface AppLovinEventService {
    void trackCheckout(String str, Map<String, String> map);

    void trackEvent(String str);

    void trackEvent(String str, Map<String, String> map);

    void trackInAppPurchase(Intent intent, Map<String, String> map);
}
