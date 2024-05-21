package com.tapjoy.internal;

import com.tapdaq.sdk.TapdaqPlacement;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

public final class df {
    private final dc a;

    private df(dc dcVar) {
        this.a = dcVar;
    }

    private static void b(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Invalid Video volume");
        }
    }

    public static df a(cv cvVar) {
        dc dcVar = (dc) cvVar;
        ds.a((Object) cvVar, "AdSession is null");
        if (!(cz.NATIVE == dcVar.a.b)) {
            throw new IllegalStateException("Cannot create VideoEvents for JavaScript AdSession");
        } else if (!dcVar.d) {
            ds.a(dcVar);
            if (dcVar.c.c == null) {
                df dfVar = new df(dcVar);
                dcVar.c.c = dfVar;
                return dfVar;
            }
            throw new IllegalStateException("VideoEvents already exists for AdSession");
        } else {
            throw new IllegalStateException("AdSession is started");
        }
    }

    public final void a(de deVar) {
        ds.a((Object) deVar, "VastProperties is null");
        ds.a(this.a);
        this.a.c.a("loaded", deVar.a());
    }

    public final void a(float f, float f2) {
        if (f > 0.0f) {
            b(f2);
            ds.b(this.a);
            JSONObject jSONObject = new JSONObject();
            dp.a(jSONObject, "duration", Float.valueOf(f));
            dp.a(jSONObject, "videoPlayerVolume", Float.valueOf(f2));
            dp.a(jSONObject, "deviceVolume", Float.valueOf(dk.a().a));
            this.a.c.a("start", jSONObject);
            return;
        }
        throw new IllegalArgumentException("Invalid Video duration");
    }

    public final void a() {
        ds.b(this.a);
        this.a.c.a(TJAdUnitConstants.String.VIDEO_FIRST_QUARTILE);
    }

    public final void b() {
        ds.b(this.a);
        this.a.c.a(TJAdUnitConstants.String.VIDEO_MIDPOINT);
    }

    public final void c() {
        ds.b(this.a);
        this.a.c.a(TJAdUnitConstants.String.VIDEO_THIRD_QUARTILE);
    }

    public final void d() {
        ds.b(this.a);
        this.a.c.a(TJAdUnitConstants.String.VIDEO_COMPLETE);
    }

    public final void e() {
        ds.b(this.a);
        this.a.c.a(TapdaqPlacement.TDPTagPause);
    }

    public final void f() {
        ds.b(this.a);
        this.a.c.a("resume");
    }

    public final void g() {
        ds.b(this.a);
        this.a.c.a(TJAdUnitConstants.String.VIDEO_BUFFER_START);
    }

    public final void h() {
        ds.b(this.a);
        this.a.c.a("bufferFinish");
    }

    public final void i() {
        ds.b(this.a);
        this.a.c.a(TJAdUnitConstants.String.VIDEO_SKIPPED);
    }

    public final void a(float f) {
        b(f);
        ds.b(this.a);
        JSONObject jSONObject = new JSONObject();
        dp.a(jSONObject, "videoPlayerVolume", Float.valueOf(f));
        dp.a(jSONObject, "deviceVolume", Float.valueOf(dk.a().a));
        this.a.c.a("volumeChange", jSONObject);
    }
}
