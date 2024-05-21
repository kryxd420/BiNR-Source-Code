package com.tapjoy.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class bq implements bn, bs {
    private HashMap a;

    public static bq a(InputStream inputStream) {
        return a.a().a(inputStream);
    }

    public static bq b(String str) {
        return a.a().a(str);
    }

    public static abstract class a {
        private static a a;

        public static a a() {
            a aVar = a;
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = br.a;
            a = aVar2;
            return aVar2;
        }

        public final bq a(InputStream inputStream) {
            return a((Reader) new InputStreamReader(inputStream, jq.c));
        }

        public bq a(Reader reader) {
            return a(kc.a(reader));
        }

        public bq a(String str) {
            return a((InputStream) new ByteArrayInputStream(str.getBytes(jq.c.name())));
        }
    }

    public final Object a(String str) {
        if (this.a != null) {
            return this.a.get(str);
        }
        return null;
    }

    public final void a(String str, Object obj) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, obj);
    }

    public final boolean a() {
        return k() == bv.BEGIN_OBJECT;
    }

    private boolean t() {
        if (k() != bv.NULL) {
            return false;
        }
        o();
        return true;
    }

    public final String b() {
        if (t()) {
            return null;
        }
        return m();
    }

    public final String c(String str) {
        if (t()) {
            return str;
        }
        return m();
    }

    private Object u() {
        bv k = k();
        switch (k) {
            case BEGIN_ARRAY:
                return c();
            case BEGIN_OBJECT:
                return d();
            case NULL:
                o();
                return null;
            case BOOLEAN:
                return Boolean.valueOf(n());
            case NUMBER:
                return new cl(m());
            case STRING:
                return m();
            default:
                throw new IllegalStateException("Expected a value but was " + k);
        }
    }

    public final List c() {
        LinkedList linkedList = new LinkedList();
        a((List) linkedList);
        return linkedList;
    }

    private void a(List list) {
        f();
        while (j()) {
            list.add(u());
        }
        g();
    }

    public final Map d() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a((Map) linkedHashMap);
        return linkedHashMap;
    }

    public final void a(Map map) {
        h();
        while (j()) {
            map.put(l(), u());
        }
        i();
    }

    @Nullable
    public final Object a(bl blVar) {
        if (t()) {
            return null;
        }
        return blVar.a(this);
    }

    public final void a(List list, bl blVar) {
        f();
        while (j()) {
            list.add(blVar.a(this));
        }
        g();
    }

    private static URI d(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            throw new by(e);
        }
    }

    public final URL e() {
        URI uri = (URI) a("BASE_URI");
        if (uri != null) {
            return uri.resolve(d(m())).toURL();
        }
        return new URL(m());
    }
}
