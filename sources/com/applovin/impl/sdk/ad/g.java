package com.applovin.impl.sdk.ad;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import com.applovin.impl.adview.f;
import com.applovin.impl.adview.p;
import com.applovin.impl.adview.t;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.e.d;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdType;
import com.tapjoy.TapjoyConstants;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public abstract class g extends AppLovinAdBase {
    private final AtomicBoolean a = new AtomicBoolean();
    private List<com.applovin.impl.sdk.c.a> b;
    private List<com.applovin.impl.sdk.c.a> c;
    private List<com.applovin.impl.sdk.c.a> d;
    private List<com.applovin.impl.sdk.c.a> e;

    public enum a {
        UNSPECIFIED,
        DISMISS,
        DO_NOT_DISMISS
    }

    public enum b {
        DEFAULT,
        ACTIVITY_PORTRAIT,
        ACTIVITY_LANDSCAPE
    }

    public g(JSONObject jSONObject, JSONObject jSONObject2, b bVar, j jVar) {
        super(jSONObject, jSONObject2, bVar, jVar);
    }

    private float a(AppLovinAdType appLovinAdType, float f, boolean z) {
        if (appLovinAdType.equals(AppLovinAdType.INCENTIVIZED)) {
            return 0.5f;
        }
        return (!appLovinAdType.equals(AppLovinAdType.REGULAR) || !z || f != -1.0f) ? 0.0f : 0.5f;
    }

    private String a() {
        String stringFromAdObject = getStringFromAdObject("video_end_url", (String) null);
        if (stringFromAdObject != null) {
            return stringFromAdObject.replace("{CLCODE}", getClCode());
        }
        return null;
    }

    private f.a b(boolean z) {
        return z ? f.a.WhiteXOnTransparentGrey : f.a.WhiteXOnOpaqueBlack;
    }

    private String c() {
        String stringFromAdObject = getStringFromAdObject("click_tracking_url", (String) null);
        if (stringFromAdObject != null) {
            return stringFromAdObject.replace("{CLCODE}", getClCode());
        }
        return null;
    }

    public boolean A() {
        return getBooleanFromAdObject("lock_current_orientation", false);
    }

    public int B() {
        return getIntFromAdObject("countdown_length", 0);
    }

    public int C() {
        int parseColor = Color.parseColor("#C8FFFFFF");
        String stringFromAdObject = getStringFromAdObject("countdown_color", (String) null);
        if (!k.b(stringFromAdObject)) {
            return parseColor;
        }
        try {
            return Color.parseColor(stringFromAdObject);
        } catch (Throwable th) {
            this.sdk.v().b("DirectAd", "Unable to parse countdown color", th);
            return parseColor;
        }
    }

    public int D() {
        String stringFromAdObject = getStringFromAdObject("video_background_color", (String) null);
        if (k.b(stringFromAdObject)) {
            try {
                return Color.parseColor(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return ViewCompat.MEASURED_STATE_MASK;
    }

    public int E() {
        int i = hasVideoUrl() ? ViewCompat.MEASURED_STATE_MASK : -1157627904;
        String stringFromAdObject = getStringFromAdObject("graphic_background_color", (String) null);
        if (!k.b(stringFromAdObject)) {
            return i;
        }
        try {
            return Color.parseColor(stringFromAdObject);
        } catch (Throwable unused) {
            return i;
        }
    }

    public a F() {
        String stringFromAdObject = getStringFromAdObject("poststitial_dismiss_type", (String) null);
        if (k.b(stringFromAdObject)) {
            if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equalsIgnoreCase(stringFromAdObject)) {
                return a.DISMISS;
            }
            if ("no_dismiss".equalsIgnoreCase(stringFromAdObject)) {
                return a.DO_NOT_DISMISS;
            }
        }
        return a.UNSPECIFIED;
    }

    public List<String> G() {
        String stringFromAdObject = getStringFromAdObject("resource_cache_prefix", (String) null);
        return stringFromAdObject != null ? d.a(stringFromAdObject) : this.sdk.b((com.applovin.impl.sdk.b.b) com.applovin.impl.sdk.b.b.bB);
    }

    public String H() {
        return getStringFromAdObject("cache_prefix", (String) null);
    }

    public boolean I() {
        return getBooleanFromAdObject("progress_bar_enabled", false);
    }

    public int J() {
        String stringFromAdObject = getStringFromAdObject("progress_bar_color", "#C8FFFFFF");
        if (k.b(stringFromAdObject)) {
            try {
                return Color.parseColor(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return 0;
    }

    public boolean K() {
        return getBooleanFromAdObject("vs_buffer_indicator_enabled", false);
    }

    public boolean L() {
        return getBooleanFromAdObject("vs_buffer_indicator_initial_load_enabled", false);
    }

    public int M() {
        return getIntFromAdObject("vs_buffer_indicator_style", 16842874);
    }

    public int N() {
        String stringFromAdObject = getStringFromAdObject("vs_buffer_indicator_color", (String) null);
        if (k.b(stringFromAdObject)) {
            try {
                return Color.parseColor(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    public int O() {
        int parseColor = Color.parseColor("#66000000");
        String stringFromAdObject = getStringFromAdObject("vs_buffer_indicator_bg_color", (String) null);
        if (!k.b(stringFromAdObject)) {
            return parseColor;
        }
        try {
            return Color.parseColor(stringFromAdObject);
        } catch (Throwable unused) {
            return parseColor;
        }
    }

    public boolean P() {
        return getBooleanFromAdObject("clear_dismissible", false);
    }

    public int Q() {
        int a2;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            return n.a(this.adObject);
        }
        synchronized (this.adObjectLock) {
            a2 = n.a(this.adObject);
        }
        return a2;
    }

    public int R() {
        return getIntFromAdObject("poststitial_shown_forward_delay_millis", -1);
    }

    public boolean S() {
        return getBooleanFromAdObject("should_apply_mute_setting_to_poststitial", false);
    }

    public boolean T() {
        return getBooleanFromAdObject("should_forward_close_button_tapped_to_poststitial", false);
    }

    public boolean U() {
        return getBooleanFromAdObject("forward_lifecycle_events_to_webview", false);
    }

    public int V() {
        return getIntFromAdObject("close_button_size", ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cM)).intValue());
    }

    public int W() {
        return getIntFromAdObject("close_button_top_margin", ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cN)).intValue());
    }

    public int X() {
        return getIntFromAdObject("close_button_horizontal_margin", ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cL)).intValue());
    }

    public boolean Y() {
        return getBooleanFromAdObject("lhs_close_button", (Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cK));
    }

    public boolean Z() {
        return getBooleanFromAdObject("lhs_skip_button", (Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.dd));
    }

    /* access modifiers changed from: protected */
    public f.a a(int i) {
        return i == 1 ? f.a.WhiteXOnTransparentGrey : i == 2 ? f.a.Invisible : f.a.WhiteXOnOpaqueBlack;
    }

    public void a(boolean z) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("html_resources_cached", z);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean aa() {
        return getBooleanFromAdObject("stop_video_player_after_poststitial_render", false);
    }

    public boolean ab() {
        return getBooleanFromAdObject("unhide_adview_on_render", false);
    }

    public long ac() {
        long longFromAdObject = getLongFromAdObject("report_reward_duration", -1);
        if (longFromAdObject >= 0) {
            return TimeUnit.SECONDS.toMillis(longFromAdObject);
        }
        return -1;
    }

    public int ad() {
        return getIntFromAdObject("report_reward_percent", -1);
    }

    public boolean ae() {
        return getBooleanFromAdObject("report_reward_percent_include_close_delay", true);
    }

    public AtomicBoolean af() {
        return this.a;
    }

    public boolean ag() {
        return getBooleanFromAdObject("show_skip_button_on_click", false);
    }

    public boolean ah() {
        return getBooleanFromAdObject("restore_original_orientation", false);
    }

    public boolean ai() {
        return getBooleanFromAdObject("use_stream_url_on_cache_drop", false);
    }

    public List<com.applovin.impl.sdk.c.a> aj() {
        if (this.b != null) {
            return this.b;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.b = n.a("video_end_urls", this.adObject, getClCode(), a(), this.sdk);
            }
        } else {
            this.b = n.a("video_end_urls", this.adObject, getClCode(), a(), this.sdk);
        }
        return this.b;
    }

    public List<com.applovin.impl.sdk.c.a> ak() {
        if (this.c != null) {
            return this.c;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.c = n.a("click_tracking_urls", this.adObject, getClCode(), c(), this.sdk);
            }
        } else {
            this.c = n.a("click_tracking_urls", this.adObject, getClCode(), c(), this.sdk);
        }
        return this.c;
    }

    public List<com.applovin.impl.sdk.c.a> al() {
        if (this.d != null) {
            return this.d;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.d = n.a("video_click_tracking_urls", this.adObject, getClCode(), (String) null, this.sdk);
            }
        } else {
            this.d = n.a("video_click_tracking_urls", this.adObject, getClCode(), (String) null, this.sdk);
        }
        if (this.d.isEmpty()) {
            this.d = ak();
        }
        return this.d;
    }

    public List<com.applovin.impl.sdk.c.a> am() {
        if (this.e != null) {
            return this.e;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.e = n.a("imp_urls", this.adObject, getClCode(), (String) null, this.sdk);
            }
        } else {
            this.e = n.a("imp_urls", this.adObject, getClCode(), (String) null, this.sdk);
        }
        return this.e;
    }

    public boolean an() {
        return getBooleanFromAdObject("render_poststitial_on_attach", false);
    }

    public boolean ao() {
        return getBooleanFromAdObject("render_poststitial_on_set_content_view", false);
    }

    public boolean ap() {
        return getBooleanFromAdObject("playback_requires_user_action", true);
    }

    public boolean aq() {
        return getBooleanFromAdObject("sanitize_webview", false);
    }

    public String ar() {
        String stringFromAdObject = getStringFromAdObject("base_url", "/");
        if ("null".equalsIgnoreCase(stringFromAdObject)) {
            return null;
        }
        return stringFromAdObject;
    }

    public boolean as() {
        return getBooleanFromAdObject("web_contents_debugging_enabled", false);
    }

    public t at() {
        JSONObject jsonObjectFromAdObject = getJsonObjectFromAdObject("web_view_settings", (JSONObject) null);
        if (jsonObjectFromAdObject != null) {
            return new t(jsonObjectFromAdObject, this.sdk);
        }
        return null;
    }

    public List<String> au() {
        return d.a(getStringFromAdObject("wls", ""));
    }

    public List<String> av() {
        return d.a(getStringFromAdObject("wlh", (String) null));
    }

    public boolean aw() {
        return getBooleanFromAdObject("tvv", false);
    }

    public Uri ax() {
        String stringFromAdObject = getStringFromAdObject("mute_image", (String) null);
        if (k.b(stringFromAdObject)) {
            try {
                return Uri.parse(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public Uri ay() {
        String stringFromAdObject = getStringFromAdObject("unmute_image", "");
        if (k.b(stringFromAdObject)) {
            try {
                return Uri.parse(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public void b(Uri uri) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("mute_image", uri);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean b() {
        this.sdk.v().d("DirectAd", "Attempting to invoke isVideoStream() from base ad class");
        return false;
    }

    public void c(Uri uri) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("unmute_image", uri);
            }
        } catch (Throwable unused) {
        }
    }

    public Uri e() {
        this.sdk.v().d("DirectAd", "Attempting to invoke getVideoUri() from base ad class");
        return null;
    }

    public Uri g() {
        this.sdk.v().d("DirectAd", "Attempting to invoke getClickDestinationUri() from base ad class");
        return null;
    }

    public Uri h() {
        this.sdk.v().d("DirectAd", "Attempting to invoke getVideoClickDestinationUri() from base ad class");
        return null;
    }

    public b m() {
        String upperCase = getStringFromAdObject("ad_target", b.DEFAULT.toString()).toUpperCase(Locale.ENGLISH);
        return "ACTIVITY_PORTRAIT".equalsIgnoreCase(upperCase) ? b.ACTIVITY_PORTRAIT : "ACTIVITY_LANDSCAPE".equalsIgnoreCase(upperCase) ? b.ACTIVITY_LANDSCAPE : b.DEFAULT;
    }

    public float n() {
        return getFloatFromAdObject("close_delay", 0.0f);
    }

    public float o() {
        return getFloatFromAdObject("close_delay_graphic", a(getType(), n(), hasVideoUrl()));
    }

    public f.a p() {
        int intFromAdObject = getIntFromAdObject("close_style", -1);
        return intFromAdObject == -1 ? b(hasVideoUrl()) : a(intFromAdObject);
    }

    public f.a q() {
        int intFromAdObject = getIntFromAdObject("skip_style", -1);
        return intFromAdObject == -1 ? p() : a(intFromAdObject);
    }

    public boolean r() {
        return getBooleanFromAdObject("dismiss_on_skip", false);
    }

    public boolean s() {
        return getBooleanFromAdObject("html_resources_cached", false);
    }

    public String t() {
        JSONObject jsonObjectFromAdObject = getJsonObjectFromAdObject("video_button_properties", (JSONObject) null);
        return jsonObjectFromAdObject != null ? com.applovin.impl.sdk.e.g.a(jsonObjectFromAdObject, "video_button_html", "", this.sdk) : "";
    }

    public p u() {
        return new p(getJsonObjectFromAdObject("video_button_properties", (JSONObject) null), this.sdk);
    }

    public boolean v() {
        return getBooleanFromAdObject("video_clickable", false);
    }

    public boolean w() {
        return getBooleanFromAdObject("accelerate_hardware", false);
    }

    public boolean x() {
        return getBooleanFromAdObject("keep_screen_on", false);
    }

    public boolean y() {
        return getBooleanFromAdObject("hide_close_on_exit_graphic", false);
    }

    public boolean z() {
        return getBooleanFromAdObject("hide_close_on_exit", false);
    }
}
