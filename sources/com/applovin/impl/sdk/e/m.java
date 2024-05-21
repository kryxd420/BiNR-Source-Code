package com.applovin.impl.sdk.e;

import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;

public final class m {
    private final j a;
    private String b;

    public m(j jVar) {
        this.a = jVar;
        if (!((Boolean) jVar.a(b.eh)).booleanValue()) {
            jVar.b(d.c);
        }
        String str = (String) jVar.a(d.c);
        if (k.b(str)) {
            p v = jVar.v();
            v.a("AppLovinSdk", "Using identifier (" + str + ") from previous session");
            this.b = str;
        }
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        if (((Boolean) this.a.a(b.eh)).booleanValue()) {
            this.a.a(d.c, str);
        }
        this.b = str;
    }
}
