package com.applovin.impl.adview;

import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

public class p {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    private final float i;
    private final float j;

    public p(JSONObject jSONObject, j jVar) {
        com.applovin.impl.sdk.p v = jVar.v();
        v.b("VideoButtonProperties", "Updating video button properties with JSON = " + g.a(jSONObject, jVar));
        this.a = g.a(jSONObject, AvidJSONUtil.KEY_WIDTH, 64, jVar);
        this.b = g.a(jSONObject, AvidJSONUtil.KEY_HEIGHT, 7, jVar);
        this.c = g.a(jSONObject, "margin", 20, jVar);
        this.d = g.a(jSONObject, "gravity", 85, jVar);
        this.e = g.a(jSONObject, "tap_to_fade", (Boolean) false, jVar).booleanValue();
        this.f = g.a(jSONObject, "tap_to_fade_duration_milliseconds", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL, jVar);
        this.g = g.a(jSONObject, "fade_in_duration_milliseconds", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL, jVar);
        this.h = g.a(jSONObject, "fade_out_duration_milliseconds", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL, jVar);
        this.i = g.a(jSONObject, "fade_in_delay_seconds", 1.0f, jVar);
        this.j = g.a(jSONObject, "fade_out_delay_seconds", 6.0f, jVar);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        p pVar = (p) obj;
        return this.a == pVar.a && this.b == pVar.b && this.c == pVar.c && this.d == pVar.d && this.e == pVar.e && this.f == pVar.f && this.g == pVar.g && this.h == pVar.h && Float.compare(pVar.i, this.i) == 0 && Float.compare(pVar.j, this.j) == 0;
    }

    public long f() {
        return (long) this.f;
    }

    public long g() {
        return (long) this.g;
    }

    public long h() {
        return (long) this.h;
    }

    public int hashCode() {
        int i2 = 0;
        int floatToIntBits = ((((((((((((((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + (this.e ? 1 : 0)) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + (this.i != 0.0f ? Float.floatToIntBits(this.i) : 0)) * 31;
        if (this.j != 0.0f) {
            i2 = Float.floatToIntBits(this.j);
        }
        return floatToIntBits + i2;
    }

    public float i() {
        return this.i;
    }

    public float j() {
        return this.j;
    }

    public String toString() {
        return "VideoButtonProperties{widthPercentOfScreen=" + this.a + ", heightPercentOfScreen=" + this.b + ", margin=" + this.c + ", gravity=" + this.d + ", tapToFade=" + this.e + ", tapToFadeDurationMillis=" + this.f + ", fadeInDurationMillis=" + this.g + ", fadeOutDurationMillis=" + this.h + ", fadeInDelay=" + this.i + ", fadeOutDelay=" + this.j + '}';
    }
}
