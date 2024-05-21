package com.tapjoy.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class bw implements Closeable {
    final Writer a;
    private final List b = new ArrayList();
    private String c;
    private String d;
    private boolean e;

    public bw(Writer writer) {
        this.b.add(bt.EMPTY_DOCUMENT);
        this.d = ":";
        if (writer != null) {
            this.a = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    public final bw a() {
        return a(bt.EMPTY_ARRAY, "[");
    }

    public final bw b() {
        return a(bt.EMPTY_ARRAY, bt.NONEMPTY_ARRAY, "]");
    }

    public final bw c() {
        return a(bt.EMPTY_OBJECT, "{");
    }

    public final bw d() {
        return a(bt.EMPTY_OBJECT, bt.NONEMPTY_OBJECT, "}");
    }

    private bw a(bt btVar, String str) {
        a(true);
        this.b.add(btVar);
        this.a.write(str);
        return this;
    }

    private bw a(bt btVar, bt btVar2, String str) {
        bt e2 = e();
        if (e2 == btVar2 || e2 == btVar) {
            this.b.remove(this.b.size() - 1);
            if (e2 == btVar2) {
                g();
            }
            this.a.write(str);
            return this;
        }
        throw new IllegalStateException("Nesting problem: " + this.b);
    }

    private bt e() {
        return (bt) this.b.get(this.b.size() - 1);
    }

    private void a(bt btVar) {
        this.b.set(this.b.size() - 1, btVar);
    }

    public final bw b(String str) {
        if (str == null) {
            return f();
        }
        a(false);
        c(str);
        return this;
    }

    private bw f() {
        a(false);
        this.a.write("null");
        return this;
    }

    public final bw a(long j) {
        a(false);
        this.a.write(Long.toString(j));
        return this;
    }

    public final bw a(Number number) {
        if (number == null) {
            return f();
        }
        String obj = number.toString();
        if (this.e || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            a(false);
            this.a.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public final void close() {
        this.a.close();
        if (e() != bt.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    private void c(String str) {
        this.a.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case 8:
                    this.a.write("\\b");
                    continue;
                case 9:
                    this.a.write("\\t");
                    continue;
                case 10:
                    this.a.write("\\n");
                    continue;
                case 12:
                    this.a.write("\\f");
                    continue;
                case 13:
                    this.a.write("\\r");
                    continue;
                case '\"':
                case '\\':
                    this.a.write(92);
                    break;
                case 8232:
                case 8233:
                    this.a.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    continue;
                default:
                    if (charAt <= 31) {
                        this.a.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                        continue;
                    }
                    break;
            }
            this.a.write(charAt);
        }
        this.a.write("\"");
    }

    private void g() {
        if (this.c != null) {
            this.a.write("\n");
            for (int i = 1; i < this.b.size(); i++) {
                this.a.write(this.c);
            }
        }
    }

    private void a(boolean z) {
        switch (e()) {
            case EMPTY_DOCUMENT:
                if (this.e || z) {
                    a(bt.NONEMPTY_DOCUMENT);
                    return;
                }
                throw new IllegalStateException("JSON must start with an array or an object.");
            case EMPTY_ARRAY:
                a(bt.NONEMPTY_ARRAY);
                g();
                return;
            case NONEMPTY_ARRAY:
                this.a.append(',');
                g();
                return;
            case DANGLING_NAME:
                this.a.append(this.d);
                a(bt.NONEMPTY_OBJECT);
                return;
            case NONEMPTY_DOCUMENT:
                throw new IllegalStateException("JSON must have only one top-level value.");
            default:
                throw new IllegalStateException("Nesting problem: " + this.b);
        }
    }

    public final bw a(Object obj) {
        if (obj == null) {
            return f();
        }
        if (obj instanceof bu) {
            if (this.b.size() == this.b.size()) {
                return this;
            }
            throw new IllegalStateException(obj.getClass().getName() + ".writeToJson(JsonWriter) wrote incomplete value");
        } else if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            a(false);
            this.a.write(booleanValue ? "true" : "false");
            return this;
        } else if (obj instanceof Number) {
            if (obj instanceof Long) {
                return a(((Number) obj).longValue());
            }
            if (!(obj instanceof Double)) {
                return a((Number) obj);
            }
            double doubleValue = ((Number) obj).doubleValue();
            if (this.e || (!Double.isNaN(doubleValue) && !Double.isInfinite(doubleValue))) {
                a(false);
                this.a.append(Double.toString(doubleValue));
                return this;
            }
            throw new IllegalArgumentException("Numeric values must be finite, but was " + doubleValue);
        } else if (obj instanceof String) {
            return b((String) obj);
        } else {
            if (obj instanceof bo) {
                return a((bo) obj);
            }
            if (obj instanceof Collection) {
                return a((Collection) obj);
            }
            if (obj instanceof Map) {
                return a((Map) obj);
            }
            if (obj instanceof Date) {
                Date date = (Date) obj;
                if (date == null) {
                    return f();
                }
                return b(x.a(date));
            } else if (obj instanceof Object[]) {
                return a((Object[]) obj);
            } else {
                throw new IllegalArgumentException("Unknown type: " + obj.getClass().getName());
            }
        }
    }

    private bw a(Object[] objArr) {
        if (objArr == null) {
            return f();
        }
        a();
        for (Object a2 : objArr) {
            a(a2);
        }
        b();
        return this;
    }

    public final bw a(bo boVar) {
        a(false);
        boVar.a(this.a);
        return this;
    }

    public final bw a(Collection collection) {
        if (collection == null) {
            return f();
        }
        a();
        for (Object a2 : collection) {
            a(a2);
        }
        b();
        return this;
    }

    public final bw a(String str) {
        if (str != null) {
            bt e2 = e();
            if (e2 == bt.NONEMPTY_OBJECT) {
                this.a.write(44);
            } else if (e2 != bt.EMPTY_OBJECT) {
                throw new IllegalStateException("Nesting problem: " + this.b);
            }
            g();
            a(bt.DANGLING_NAME);
            c(str);
            return this;
        }
        throw new NullPointerException("name == null");
    }

    public final bw a(Map map) {
        if (map == null) {
            return f();
        }
        c();
        for (Map.Entry entry : map.entrySet()) {
            a(String.valueOf(entry.getKey()));
            a(entry.getValue());
        }
        d();
        return this;
    }
}
