package com.applovin.impl.adview;

import android.webkit.WebSettings;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import org.json.JSONObject;

public final class t {
    private j a;
    private JSONObject b;

    public t(JSONObject jSONObject, j jVar) {
        this.a = jVar;
        this.b = jSONObject;
    }

    public Integer a() {
        int i;
        String a2 = g.a(this.b, "mixed_content_mode", (String) null, this.a);
        if (k.b(a2)) {
            if ("always_allow".equalsIgnoreCase(a2)) {
                i = 0;
            } else if ("never_allow".equalsIgnoreCase(a2)) {
                i = 1;
            } else if ("compatibility_mode".equalsIgnoreCase(a2)) {
                i = 2;
            }
            return Integer.valueOf(i);
        }
        return null;
    }

    public WebSettings.PluginState b() {
        String a2 = g.a(this.b, "plugin_state", (String) null, this.a);
        if (k.b(a2)) {
            if ("on".equalsIgnoreCase(a2)) {
                return WebSettings.PluginState.ON;
            }
            if ("on_demand".equalsIgnoreCase(a2)) {
                return WebSettings.PluginState.ON_DEMAND;
            }
            if ("off".equalsIgnoreCase(a2)) {
                return WebSettings.PluginState.OFF;
            }
        }
        return null;
    }

    public Boolean c() {
        return g.a(this.b, "allow_file_access", (Boolean) null, this.a);
    }

    public Boolean d() {
        return g.a(this.b, "load_with_overview_mode", (Boolean) null, this.a);
    }

    public Boolean e() {
        return g.a(this.b, "use_wide_view_port", (Boolean) null, this.a);
    }

    public Boolean f() {
        return g.a(this.b, "allow_content_access", (Boolean) null, this.a);
    }

    public Boolean g() {
        return g.a(this.b, "use_built_in_zoom_controls", (Boolean) null, this.a);
    }

    public Boolean h() {
        return g.a(this.b, "display_zoom_controls", (Boolean) null, this.a);
    }

    public Boolean i() {
        return g.a(this.b, "save_form_data", (Boolean) null, this.a);
    }

    public Boolean j() {
        return g.a(this.b, "geolocation_enabled", (Boolean) null, this.a);
    }

    public Boolean k() {
        return g.a(this.b, "need_initial_focus", (Boolean) null, this.a);
    }

    public Boolean l() {
        return g.a(this.b, "allow_file_access_from_file_urls", (Boolean) null, this.a);
    }

    public Boolean m() {
        return g.a(this.b, "allow_universal_access_from_file_urls", (Boolean) null, this.a);
    }

    public Boolean n() {
        return g.a(this.b, "offscreen_pre_raster", (Boolean) null, this.a);
    }
}
