package com.applovin.impl.sdk.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class o {
    public static final o a = new o();
    protected String b;
    protected final List<o> c;
    private final o d;
    private final String e;
    private final Map<String, String> f;

    private o() {
        this.d = null;
        this.e = "";
        this.f = Collections.emptyMap();
        this.b = "";
        this.c = Collections.emptyList();
    }

    public o(String str, Map<String, String> map, o oVar) {
        this.d = oVar;
        this.e = str;
        this.f = Collections.unmodifiableMap(map);
        this.c = new ArrayList();
    }

    public String a() {
        return this.e;
    }

    public List<o> a(String str) {
        if (str != null) {
            ArrayList arrayList = new ArrayList(this.c.size());
            for (o next : this.c) {
                if (str.equalsIgnoreCase(next.a())) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("No name specified.");
    }

    public o b(String str) {
        if (str != null) {
            for (o next : this.c) {
                if (str.equalsIgnoreCase(next.a())) {
                    return next;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("No name specified.");
    }

    public Map<String, String> b() {
        return this.f;
    }

    public o c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified.");
        } else if (this.c.size() <= 0) {
            return null;
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this);
            while (!arrayList.isEmpty()) {
                o oVar = (o) arrayList.get(0);
                arrayList.remove(0);
                if (str.equalsIgnoreCase(oVar.a())) {
                    return oVar;
                }
                arrayList.addAll(oVar.d());
            }
            return null;
        }
    }

    public String c() {
        return this.b;
    }

    public List<o> d() {
        return Collections.unmodifiableList(this.c);
    }

    public String toString() {
        return "XmlNode{elementName='" + this.e + '\'' + ", text='" + this.b + '\'' + ", attributes=" + this.f + '}';
    }
}
