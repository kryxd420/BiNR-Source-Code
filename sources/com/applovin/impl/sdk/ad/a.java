package com.applovin.impl.sdk.ad;

import android.net.Uri;
import com.applovin.impl.adview.f;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.model.Advertisement;
import org.json.JSONObject;

public final class a extends g {
    public a(JSONObject jSONObject, JSONObject jSONObject2, b bVar, j jVar) {
        super(jSONObject, jSONObject2, bVar, jVar);
    }

    private String az() {
        return getStringFromAdObject("stream_url", "");
    }

    private void b(String str) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("backup_stream_url", str);
            }
        } catch (Throwable unused) {
        }
    }

    public String a() {
        String a;
        synchronized (this.adObjectLock) {
            a = g.a(this.adObject, TJAdUnitConstants.String.HTML, (String) null, this.sdk);
        }
        return a;
    }

    public void a(Uri uri) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put(Advertisement.KEY_VIDEO, uri.toString());
            }
        } catch (Throwable unused) {
        }
    }

    public void a(String str) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put(TJAdUnitConstants.String.HTML, str);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean b() {
        return this.adObject.has("stream_url");
    }

    public void c() {
        synchronized (this.adObjectLock) {
            Object remove = this.adObject.remove("stream_url");
            if (remove instanceof String) {
                b((String) remove);
            }
        }
    }

    public boolean d() {
        String stringFromAdObject = getStringFromAdObject("backup_stream_url", (String) null);
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("stream_url", stringFromAdObject);
            }
        } catch (Throwable unused) {
        }
        return b();
    }

    public Uri e() {
        String az = az();
        if (k.b(az)) {
            return Uri.parse(az);
        }
        String f = f();
        if (k.b(f)) {
            return Uri.parse(f);
        }
        return null;
    }

    public String f() {
        return getStringFromAdObject(Advertisement.KEY_VIDEO, "");
    }

    public Uri g() {
        String stringFromAdObject = getStringFromAdObject(TapjoyConstants.TJC_CLICK_URL, "");
        if (k.b(stringFromAdObject)) {
            return Uri.parse(stringFromAdObject);
        }
        return null;
    }

    public Uri h() {
        String stringFromAdObject = getStringFromAdObject("video_click_url", "");
        return k.b(stringFromAdObject) ? Uri.parse(stringFromAdObject) : g();
    }

    public boolean hasVideoUrl() {
        return e() != null;
    }

    public float i() {
        return getFloatFromAdObject("mraid_close_delay_graphic", 0.0f);
    }

    public boolean j() {
        return getBooleanFromAdObject("close_button_graphic_hidden", false);
    }

    public boolean k() {
        if (this.adObject.has("close_button_expandable_hidden")) {
            return getBooleanFromAdObject("close_button_expandable_hidden", false);
        }
        return true;
    }

    public f.a l() {
        return a(getIntFromAdObject("expandable_style", f.a.Invisible.ordinal()));
    }
}
