package com.tapjoy.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class gn extends Observable {
    public final List b = new ArrayList();

    public class a {
        public final String a;
        public volatile Map b;

        a(String str) {
            this.a = str;
        }

        public final Object a(String str) {
            Map map = this.b;
            if (map != null) {
                return map.get(str);
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final a a(String str) {
        a aVar = new a(str);
        this.b.add(aVar);
        return aVar;
    }

    /* access modifiers changed from: protected */
    public void setChanged() {
        super.setChanged();
        notifyObservers();
    }

    public final boolean b(String str) {
        return a(str, false);
    }

    public final boolean a(String str, boolean z) {
        for (a a2 : this.b) {
            Object a3 = a2.a(str);
            if (a3 != null) {
                if (a3 instanceof Boolean) {
                    return ((Boolean) a3).booleanValue();
                }
                if (!(a3 instanceof String)) {
                    continue;
                } else if ("true".equals(a3)) {
                    return true;
                } else {
                    if ("false".equals(a3)) {
                        return false;
                    }
                }
            }
        }
        return z;
    }

    public final long c(String str) {
        for (a a2 : this.b) {
            Object a3 = a2.a(str);
            if (a3 != null) {
                if (a3 instanceof Number) {
                    return ((Number) a3).longValue();
                }
                if (a3 instanceof String) {
                    try {
                        return Long.parseLong((String) a3);
                    } catch (IllegalArgumentException unused) {
                    }
                } else {
                    continue;
                }
            }
        }
        return 0;
    }

    private static long a(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            return Long.parseLong((String) obj);
        }
        throw new IllegalArgumentException();
    }

    public final gm d(String str) {
        double parseDouble;
        for (a a2 : this.b) {
            Object a3 = a2.a(str);
            if (a3 instanceof List) {
                List list = (List) a3;
                try {
                    long a4 = a(list.get(0));
                    long a5 = a(list.get(1));
                    long a6 = a(list.get(2));
                    Object obj = list.get(3);
                    if (obj instanceof Number) {
                        parseDouble = ((Number) obj).doubleValue();
                    } else if (obj instanceof String) {
                        parseDouble = Double.parseDouble((String) obj);
                    } else {
                        throw new IllegalArgumentException();
                    }
                    return new gm(a4, a5, a6, parseDouble);
                } catch (RuntimeException unused) {
                }
            }
        }
        return gm.a;
    }
}
