package com.applovin.impl.a;

import com.applovin.impl.sdk.e.d;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.tapjoy.TJAdUnitConstants;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class g {
    private String a;
    private String b;
    private String c;
    private long d = -1;
    private int e = -1;

    private g() {
    }

    private static int a(String str, c cVar) {
        if ("start".equalsIgnoreCase(str)) {
            return 0;
        }
        if (TJAdUnitConstants.String.VIDEO_FIRST_QUARTILE.equalsIgnoreCase(str)) {
            return 25;
        }
        if (TJAdUnitConstants.String.VIDEO_MIDPOINT.equalsIgnoreCase(str)) {
            return 50;
        }
        if (TJAdUnitConstants.String.VIDEO_THIRD_QUARTILE.equalsIgnoreCase(str)) {
            return 75;
        }
        if (!TJAdUnitConstants.String.VIDEO_COMPLETE.equalsIgnoreCase(str)) {
            return -1;
        }
        if (cVar != null) {
            return cVar.i();
        }
        return 95;
    }

    public static g a(o oVar, c cVar, j jVar) {
        long seconds;
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (jVar != null) {
            try {
                String c2 = oVar.c();
                if (k.b(c2)) {
                    g gVar = new g();
                    gVar.c = c2;
                    gVar.a = oVar.b().get("id");
                    gVar.b = oVar.b().get("event");
                    gVar.e = a(gVar.a(), cVar);
                    String str = oVar.b().get("offset");
                    if (k.b(str)) {
                        String trim = str.trim();
                        if (trim.contains("%")) {
                            gVar.e = k.a(trim.substring(0, trim.length() - 1));
                        } else if (trim.contains(":")) {
                            List<String> a2 = d.a(trim, ":");
                            int size = a2.size();
                            if (size > 0) {
                                int i = size - 1;
                                long j = 0;
                                for (int i2 = i; i2 >= 0; i2--) {
                                    String str2 = a2.get(i2);
                                    if (k.d(str2)) {
                                        int parseInt = Integer.parseInt(str2);
                                        if (i2 == i) {
                                            seconds = (long) parseInt;
                                        } else if (i2 == size - 2) {
                                            seconds = TimeUnit.MINUTES.toSeconds((long) parseInt);
                                        } else if (i2 == size - 3) {
                                            seconds = TimeUnit.HOURS.toSeconds((long) parseInt);
                                        }
                                        j += seconds;
                                    }
                                }
                                gVar.d = j;
                                gVar.e = -1;
                            }
                        } else {
                            jVar.v().d("VastTracker", "Unable to parse time offset from rawOffsetString = " + trim);
                        }
                    }
                    return gVar;
                }
                jVar.v().d("VastTracker", "Unable to create tracker. Could not find URL.");
                return null;
            } catch (Throwable th) {
                jVar.v().b("VastTracker", "Error occurred while initializing", th);
                return null;
            }
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public String a() {
        return this.b;
    }

    public boolean a(long j, int i) {
        return (((this.d > 0 ? 1 : (this.d == 0 ? 0 : -1)) >= 0) && ((j > this.d ? 1 : (j == this.d ? 0 : -1)) >= 0)) || ((this.e >= 0) && (i >= this.e));
    }

    public String b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.d != gVar.d || this.e != gVar.e) {
            return false;
        }
        if (this.a == null ? gVar.a != null : !this.a.equals(gVar.a)) {
            return false;
        }
        if (this.b == null ? gVar.b == null : this.b.equals(gVar.b)) {
            return this.c.equals(gVar.c);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.c.hashCode()) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + this.e;
    }

    public String toString() {
        return "VastTracker{identifier='" + this.a + '\'' + ", event='" + this.b + '\'' + ", uriString='" + this.c + '\'' + ", offsetSeconds=" + this.d + ", offsetPercent=" + this.e + '}';
    }
}
