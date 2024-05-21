package com.applovin.impl.a;

import com.applovin.impl.sdk.ad.b;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class c {
    private static final List<String> c = Arrays.asList(new String[]{"video/mp4", "video/webm", "video/3gpp", "video/x-matroska"});
    protected List<o> a = new ArrayList();
    private final j b;
    private final JSONObject d;
    private final JSONObject e;
    private final b f;
    private final long g = System.currentTimeMillis();

    public c(JSONObject jSONObject, JSONObject jSONObject2, b bVar, j jVar) {
        this.b = jVar;
        this.d = jSONObject;
        this.e = jSONObject2;
        this.f = bVar;
    }

    public int a() {
        return this.a.size();
    }

    public List<o> b() {
        return this.a;
    }

    public JSONObject c() {
        return this.d;
    }

    public JSONObject d() {
        return this.e;
    }

    public b e() {
        return this.f;
    }

    public long f() {
        return this.g;
    }

    public d g() {
        String a2 = g.a(this.e, "zone_id", (String) null, this.b);
        return d.a(AppLovinAdSize.fromString(g.a(this.e, "ad_size", (String) null, this.b)), AppLovinAdType.fromString(g.a(this.e, "ad_type", (String) null, this.b)), a2, this.b);
    }

    public List<String> h() {
        List<String> a2 = com.applovin.impl.sdk.e.d.a(g.a(this.d, "vast_preferred_video_types", (String) null, (j) null));
        return !a2.isEmpty() ? a2 : c;
    }

    public int i() {
        return n.a(this.d);
    }
}
