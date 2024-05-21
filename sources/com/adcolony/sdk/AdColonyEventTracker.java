package com.adcolony.sdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.adcolony.sdk.aa;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventTypes;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

public class AdColonyEventTracker {
    public static final String CUSTOM_EVENT_1 = "ADCT_CUSTOM_EVENT_1";
    public static final String CUSTOM_EVENT_2 = "ADCT_CUSTOM_EVENT_2";
    public static final String CUSTOM_EVENT_3 = "ADCT_CUSTOM_EVENT_3";
    public static final String CUSTOM_EVENT_4 = "ADCT_CUSTOM_EVENT_4";
    public static final String CUSTOM_EVENT_5 = "ADCT_CUSTOM_EVENT_5";
    public static final String LOGIN_DEFAULT = "ADCT_DEFAULT_LOGIN";
    public static final String LOGIN_FACEBOOK = "ADCT_FACEBOOK_LOGIN";
    public static final String LOGIN_GOOGLE = "ADCT_GOOGLE_LOGIN";
    public static final String LOGIN_LINKEDIN = "ADCT_LINKEDIN_LOGIN";
    public static final String LOGIN_OPENID = "ADCT_OPENID_LOGIN";
    public static final String LOGIN_TWITTER = "ADCT_TWITTER_LOGIN";
    public static final String REGISTRATION_CUSTOM = "ADCT_CUSTOM_REGISTRATION";
    public static final String REGISTRATION_DEFAULT = "ADCT_DEFAULT_REGISTRATION";
    public static final String REGISTRATION_FACEBOOK = "ADCT_FACEBOOK_REGISTRATION";
    public static final String REGISTRATION_GOOGLE = "ADCT_GOOGLE_REGISTRATION";
    public static final String REGISTRATION_LINKEDIN = "ADCT_LINKEDIN_REGISTRATION";
    public static final String REGISTRATION_OPENID = "ADCT_OPENID_REGISTRATION";
    public static final String REGISTRATION_TWITTER = "ADCT_TWITTER_REGISTRATION";
    public static final String SOCIAL_SHARING_CUSTOM = "ADCT_CUSTOM_SHARING";
    public static final String SOCIAL_SHARING_FACEBOOK = "ADCT_FACEBOOK_SHARING";
    public static final String SOCIAL_SHARING_FLICKR = "ADCT_FLICKR_SHARING";
    public static final String SOCIAL_SHARING_FOURSQUARE = "ADCT_FOURSQUARE_SHARING";
    public static final String SOCIAL_SHARING_GOOGLE = "ADCT_GOOGLE_SHARING";
    public static final String SOCIAL_SHARING_INSTAGRAM = "ADCT_INSTAGRAM_SHARING";
    public static final String SOCIAL_SHARING_LINKEDIN = "ADCT_LINKEDIN_SHARING";
    public static final String SOCIAL_SHARING_PINTEREST = "ADCT_PINTEREST_SHARING";
    public static final String SOCIAL_SHARING_SNAPCHAT = "ADCT_SNAPCHAT_SHARING";
    public static final String SOCIAL_SHARING_TUMBLR = "ADCT_TUMBLR_SHARING";
    public static final String SOCIAL_SHARING_TWITTER = "ADCT_TWITTER_SHARING";
    public static final String SOCIAL_SHARING_VIMEO = "ADCT_VIMEO_SHARING";
    public static final String SOCIAL_SHARING_VINE = "ADCT_VINE_SHARING";
    public static final String SOCIAL_SHARING_YOUTUBE = "ADCT_YOUTUBE_SHARING";
    private static final List<JSONObject> a = Collections.synchronizedList(new ArrayList());
    private static final int b = 200;

    static void a(JSONObject jSONObject) {
        synchronized (a) {
            if (200 > a.size()) {
                a.add(jSONObject);
            }
        }
    }

    static void a() {
        if (!l.C().equals("")) {
            synchronized (a) {
                for (JSONObject EventTracker__logEvent : a) {
                    ADCNative.EventTracker__logEvent(EventTracker__logEvent);
                }
                a.clear();
            }
        }
    }

    static boolean b() {
        boolean z;
        synchronized (a) {
            z = a.size() != 0;
        }
        return z;
    }

    public static void logTransaction(@Nullable String str, @Nullable Integer num, @Nullable Double d, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        if (!a(str5, "logTransaction")) {
            if (str2 == null || str2.length() == 3) {
                HashMap hashMap = new HashMap();
                hashMap.put("item_id", str);
                hashMap.put("quantity", String.valueOf(num));
                hashMap.put(InAppPurchaseMetaData.KEY_PRICE, String.valueOf(d));
                hashMap.put("currency_code", str2);
                hashMap.put("receipt", str3);
                hashMap.put(TapjoyConstants.TJC_STORE, str4);
                hashMap.put("description", str5);
                logEvent("transaction", hashMap);
                return;
            }
            new aa.a().a("Event logCreditsSpentWithName currency code is specified, but a three-letter ISO 4217 code, (e.g.: 'USD'). Event will not be sent.").a(aa.g);
        }
    }

    public static void logCreditsSpent(@Nullable String str, @Nullable Integer num, @Nullable Double d, @Nullable String str2) {
        if (str2 == null || str2.length() == 3) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", str);
            hashMap.put("quantity", String.valueOf(num));
            hashMap.put("value", String.valueOf(d));
            hashMap.put("currency_code", str2);
            logEvent("credits_spent", hashMap);
            return;
        }
        new aa.a().a("Event logCreditsSpentWithName currency code is specified, but a three-letter ISO 4217 code, (e.g.: 'USD'). Event will not be sent.").a(aa.g);
    }

    public static void logPaymentInfoAdded() {
        logEvent("payment_info_added");
    }

    public static void logAchievementUnlocked(@Nullable String str) {
        if (!a(str, "logAchievementUnlocked")) {
            HashMap hashMap = new HashMap();
            hashMap.put("description", str);
            logEvent("achievement_unlocked", hashMap);
        }
    }

    public static void logLevelAchieved(@Nullable Integer num) {
        HashMap hashMap = new HashMap();
        hashMap.put("level_achieved", String.valueOf(num));
        logEvent("level_achieved", hashMap);
    }

    public static void logAppRated() {
        logEvent("app_rated");
    }

    public static void logActivated() {
        logEvent("activated");
    }

    public static void logTutorialCompleted() {
        logEvent("tutorial_completed");
    }

    public static void logSocialSharingEvent(@Nullable String str, @Nullable String str2) {
        if (!a(str2, "logSocialSharingEvent")) {
            HashMap hashMap = new HashMap();
            hashMap.put("network", str);
            hashMap.put("description", str2);
            logEvent("social_sharing_event", hashMap);
        }
    }

    public static void logRegistrationCompleted(@Nullable String str, @Nullable String str2) {
        if (!a(str2, "logRegistrationCompleted")) {
            HashMap hashMap = new HashMap();
            hashMap.put(TJAdUnitConstants.String.METHOD, str);
            hashMap.put("description", str2);
            logEvent("registration_completed", hashMap);
        }
    }

    public static void logCustomEvent(@Nullable String str, @Nullable String str2) {
        if (!a(str2, "logCustomEvent")) {
            HashMap hashMap = new HashMap();
            hashMap.put("event", str);
            hashMap.put("description", str2);
            logEvent("custom_event", hashMap);
        }
    }

    public static void logAddToCart(@Nullable String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("item_id", str);
        logEvent("add_to_cart", hashMap);
    }

    public static void logAddToWishlist(@Nullable String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("item_id", str);
        logEvent("add_to_wishlist", hashMap);
    }

    public static void logCheckoutInitiated() {
        logEvent("checkout_initiated");
    }

    public static void logContentView(@Nullable String str, @Nullable String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(AppLovinEventParameters.CONTENT_IDENTIFIER, str);
        hashMap.put("content_type", str2);
        logEvent("content_view", hashMap);
    }

    public static void logInvite() {
        logEvent(AppLovinEventTypes.USER_SENT_INVITATION);
    }

    public static void logLogin(@Nullable String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.METHOD, str);
        logEvent(AppLovinEventTypes.USER_LOGGED_IN, hashMap);
    }

    public static void logReservation() {
        logEvent(AppLovinEventTypes.USER_CREATED_RESERVATION);
    }

    public static void logSearch(@Nullable String str) {
        if (str == null || str.length() <= 512) {
            HashMap hashMap = new HashMap();
            hashMap.put("search_string", str);
            logEvent(AppLovinEventTypes.USER_EXECUTED_SEARCH, hashMap);
            return;
        }
        new aa.a().a("logSearch searchString cannot exceed 512 characters. Event will ").a("not be sent.").a(aa.g);
    }

    public static void logEvent(@Nullable String str) {
        logEvent(str, (HashMap<String, String>) null);
    }

    public static void logEvent(@NonNull String str, @Nullable HashMap<String, String> hashMap) {
        JSONObject a2 = y.a();
        y.a(a2, TJAdUnitConstants.PARAM_PLACEMENT_NAME, str);
        JSONObject a3 = y.a();
        if (hashMap != null) {
            for (Map.Entry next : hashMap.entrySet()) {
                if (next.getValue() != null && !((String) next.getValue()).equals("null")) {
                    y.a(a3, (String) next.getKey(), (String) next.getValue());
                }
            }
        }
        b(a3);
        y.a(a2, "payload", a3);
        ADCNative.EventTracker__logEvent(a2);
    }

    private static void b(JSONObject jSONObject) {
        y.a(jSONObject, TapjoyConstants.TJC_DEVICE_TIMEZONE, TimeZone.getDefault().getID());
        y.a(jSONObject, "action_time", String.valueOf(Math.round((float) (System.currentTimeMillis() / 1000))));
    }

    private static boolean a(String str, String str2) {
        if (str == null || str.length() <= 512) {
            return false;
        }
        new aa.a().a("Description of event ").a(str2).a(" must be less").a(" than 512 characters").a(aa.g);
        return true;
    }
}
