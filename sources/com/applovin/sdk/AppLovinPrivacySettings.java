package com.applovin.sdk;

import android.content.Context;
import com.applovin.impl.sdk.g;

public class AppLovinPrivacySettings {
    public static boolean hasUserConsent(Context context) {
        Boolean a = g.a(context);
        if (a != null) {
            return a.booleanValue();
        }
        return false;
    }

    public static boolean isAgeRestrictedUser(Context context) {
        Boolean b = g.b(context);
        if (b != null) {
            return b.booleanValue();
        }
        return false;
    }

    public static void setHasUserConsent(boolean z, Context context) {
        if (g.a(z, context)) {
            AppLovinSdk.reinitializeAll(Boolean.valueOf(z));
        }
    }

    public static void setIsAgeRestrictedUser(boolean z, Context context) {
        if (g.b(z, context)) {
            AppLovinSdk.reinitializeAll();
        }
    }
}
