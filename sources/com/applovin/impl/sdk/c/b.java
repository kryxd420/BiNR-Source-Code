package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Set;

class b {
    static final b A = a("smwm", "AD_SHOWN_IN_MULTIWINDOW_MODE");
    private static final Set<String> D = new HashSet(31);
    static final b a = a("sas", "AD_SOURCE");
    static final b b = a("srt", "AD_RENDER_TIME");
    static final b c = a("sft", "AD_FETCH_TIME");
    static final b d = a("sfs", "AD_FETCH_SIZE");
    static final b e = a("sadb", "AD_DOWNLOADED_BYTES");
    static final b f = a("sacb", "AD_CACHED_BYTES");
    static final b g = a("stdl", "TIME_TO_DISPLAY_FROM_LOAD");
    static final b h = a("stdi", "TIME_TO_DISPLAY_FROM_INIT");
    static final b i = a("snas", "AD_NUMBER_IN_SESSION");
    static final b j = a("snat", "AD_NUMBER_TOTAL");
    static final b k = a("stah", "TIME_AD_HIDDEN_FROM_SHOW");
    static final b l = a("stas", "TIME_TO_SKIP_FROM_SHOW");
    static final b m = a("stac", "TIME_TO_CLICK_FROM_SHOW");
    static final b n = a("stbe", "TIME_TO_EXPAND_FROM_SHOW");
    static final b o = a("stbc", "TIME_TO_CONTRACT_FROM_SHOW");
    static final b p = a("saan", "AD_SHOWN_WITH_ACTIVE_NETWORK");
    static final b q = a("suvs", "INTERSTITIAL_USED_VIDEO_STREAM");
    static final b r = a("sugs", "AD_USED_GRAPHIC_STREAM");
    static final b s = a("svpv", "INTERSTITIAL_VIDEO_PERCENT_VIEWED");
    static final b t = a("stpd", "INTERSTITIAL_PAUSED_DURATION");
    static final b u = a("sspe", "INTERSTITIAL_SHOW_POSTSTITIAL_CODE_EXECUTED");
    static final b v = a("shsc", "HTML_RESOURCE_CACHE_SUCCESS_COUNT");
    static final b w = a("shfc", "HTML_RESOURCE_CACHE_FAILURE_COUNT");
    static final b x = a("svmi", "INTERSTITIAL_VIDEO_MUTED_INITIALLY");
    static final b y = a("stvm", "TIME_TO_TOGGLE_VIDEO_MUTE");
    static final b z = a("schc", "AD_CANCELLED_HTML_CACHING");
    private final String B;
    private final String C;

    static {
        a("sasw", "AD_SHOWN_WITH_WEBKIT");
        a("sisw", "IS_STREAMING_WEBKIT");
        a("surw", "UNABLE_TO_RETRIEVE_WEBKIT_HTML_STRING");
        a("surp", "UNABLE_TO_PERSIST_WEBKIT_HTML_PLACEMENT_REPLACED_STRING");
        a("swhp", "SUCCESSFULLY_PERSISTED_WEBKIT_HTML_STRING");
        a("skr", "STOREKIT_REDIRECTED");
        a("sklf", "STOREKIT_LOAD_FAILURE");
        a("skps", "STOREKIT_PRELOAD_SKIPPED");
    }

    private b(String str, String str2) {
        this.B = str;
        this.C = str2;
    }

    private static b a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key name specified");
        } else if (D.contains(str)) {
            throw new IllegalArgumentException("Key has already been used: " + str);
        } else if (!TextUtils.isEmpty(str2)) {
            D.add(str);
            return new b(str, str2);
        } else {
            throw new IllegalArgumentException("No debug name specified");
        }
    }

    public String a() {
        return this.B;
    }

    public String b() {
        return this.C;
    }
}
