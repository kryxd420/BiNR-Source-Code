package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Set;

public class g {
    public static final g A = a("repeated_load_count_banner", true);
    private static final Set<String> C = new HashSet(32);
    private static final Set<g> D = new HashSet(16);
    public static final g a = a("ad_req");
    public static final g b = a("ad_imp");
    public static final g c = a("ad_session_start");
    public static final g d = a("ad_imp_session");
    public static final g e = a("cached_files_expired");
    public static final g f = a("cache_drop_count");
    public static final g g = a("sdk_reset_state_count", true);
    public static final g h = a("ad_response_process_failures", true);
    public static final g i = a("response_process_failures", true);
    public static final g j = a("incent_shown_without_prompt_count", true);
    public static final g k = a("incent_prompt_accepted_count", true);
    public static final g l = a("incent_prompt_rejected_count", true);
    public static final g m = a("incent_failed_to_display_count", true);
    public static final g n = a("app_paused_and_resumed");
    public static final g o = a("cached_video_removed_count", true);
    public static final g p = a("ad_rendered_with_mismatched_sdk_key", true);
    public static final g q = a("med_ad_req");
    public static final g r = a("med_ad_response_process_failures", true);
    public static final g s = a("med_waterfall_ad_no_fill", true);
    public static final g t = a("med_waterfall_ad_adapter_load_failed", true);
    public static final g u = a("med_waterfall_ad_invalid_response", true);
    public static final g v = a("initial_load_count_inter", true);
    public static final g w = a("initial_load_count_rewarded", true);
    public static final g x = a("initial_load_count_banner", true);
    public static final g y = a("repeated_load_count_inter", true);
    public static final g z = a("repeated_load_count_rewarded", true);
    private final String B;

    static {
        a("fullscreen_ad_nil_vc_count");
    }

    private g(String str) {
        this.B = str;
    }

    private static g a(String str) {
        return a(str, false);
    }

    private static g a(String str, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key name specified");
        } else if (!C.contains(str)) {
            C.add(str);
            g gVar = new g(str);
            if (z2) {
                D.add(gVar);
            }
            return gVar;
        } else {
            throw new IllegalArgumentException("Key has already been used: " + str);
        }
    }

    public static Set<g> b() {
        return D;
    }

    public String a() {
        return this.B;
    }
}
