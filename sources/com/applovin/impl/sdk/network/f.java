package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.b;
import java.util.Map;
import org.json.JSONObject;

public class f<T> extends b {

    public static class a<T> extends b.a<T> {
        public a(j jVar) {
            super(jVar);
            this.g = false;
            this.h = ((Integer) jVar.a(com.applovin.impl.sdk.b.b.dA)).intValue();
            this.i = ((Integer) jVar.a(com.applovin.impl.sdk.b.b.dz)).intValue();
            this.j = ((Integer) jVar.a(com.applovin.impl.sdk.b.b.dF)).intValue();
        }

        /* renamed from: b */
        public a a(T t) {
            this.f = t;
            return this;
        }

        /* renamed from: b */
        public a a(Map<String, String> map) {
            this.d = map;
            return this;
        }

        /* renamed from: b */
        public a a(JSONObject jSONObject) {
            this.e = jSONObject;
            return this;
        }

        /* renamed from: b */
        public a a(boolean z) {
            this.k = z;
            return this;
        }

        /* renamed from: b */
        public f<T> a() {
            return new f<>(this);
        }

        /* renamed from: d */
        public a a(int i) {
            this.h = i;
            return this;
        }

        /* renamed from: d */
        public a a(String str) {
            this.b = str;
            return this;
        }

        /* renamed from: e */
        public a b(int i) {
            this.i = i;
            return this;
        }

        /* renamed from: e */
        public a b(String str) {
            this.a = str;
            return this;
        }

        /* renamed from: f */
        public a c(int i) {
            this.j = i;
            return this;
        }

        /* renamed from: f */
        public a c(String str) {
            this.c = str;
            return this;
        }
    }

    protected f(a aVar) {
        super(aVar);
    }

    public static a b(j jVar) {
        return new a(jVar);
    }
}
