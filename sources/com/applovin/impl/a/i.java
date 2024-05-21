package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.e;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class i {
    private static DateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private static Random b = new Random(System.currentTimeMillis());

    public static Uri a(String str, long j, Uri uri, d dVar, j jVar) {
        if (URLUtil.isValidUrl(str)) {
            try {
                String replace = str.replace("[ERRORCODE]", Integer.toString(dVar.a()));
                if (j >= 0) {
                    replace = replace.replace("[CONTENTPLAYHEAD]", a(j));
                }
                if (uri != null) {
                    replace = replace.replace("[ASSETURI]", uri.toString());
                }
                return Uri.parse(replace.replace("[CACHEBUSTING]", a()).replace("[TIMESTAMP]", b()));
            } catch (Throwable th) {
                p v = jVar.v();
                v.b("VastUtils", "Unable to replace macros in URL string " + str, th);
                return null;
            }
        } else {
            jVar.v().d("VastUtils", "Unable to replace macros in invalid URL string.");
            return null;
        }
    }

    public static d a(a aVar) {
        if (b(aVar) || c(aVar)) {
            return null;
        }
        return d.GENERAL_WRAPPER_ERROR;
    }

    private static String a() {
        return Integer.toString(b.nextInt(89999999) + 10000000);
    }

    private static String a(long j) {
        if (j <= 0) {
            return "00:00:00.000";
        }
        return String.format("%02d:%02d:%02d.000", new Object[]{Long.valueOf(TimeUnit.SECONDS.toHours(j)), Long.valueOf(TimeUnit.SECONDS.toMinutes(j) % TimeUnit.MINUTES.toSeconds(1)), Long.valueOf(j % TimeUnit.MINUTES.toSeconds(1))});
    }

    public static String a(c cVar) {
        o c;
        if (cVar != null) {
            List<o> b2 = cVar.b();
            int size = cVar.b().size();
            if (size <= 0 || (c = b2.get(size - 1).c("VASTAdTagURI")) == null) {
                return null;
            }
            return c.c();
        }
        throw new IllegalArgumentException("Unable to get resolution uri string for fetching the next wrapper or inline response in the chain");
    }

    public static String a(o oVar, String str, String str2) {
        o b2 = oVar.b(str);
        if (b2 != null) {
            String c = b2.c();
            if (k.b(c)) {
                return c;
            }
        }
        return str2;
    }

    private static Set<g> a(c cVar, j jVar) {
        if (cVar == null) {
            return null;
        }
        List<o> b2 = cVar.b();
        Set<g> hashSet = new HashSet<>(b2.size());
        for (o next : b2) {
            o c = next.c("Wrapper");
            if (c == null) {
                c = next.c("InLine");
            }
            hashSet = a(hashSet, c != null ? c.a("Error") : next.a("Error"), cVar, jVar);
        }
        p v = jVar.v();
        v.a("VastUtils", "Retrieved " + hashSet.size() + " top level error trackers: " + hashSet);
        return hashSet;
    }

    private static Set<g> a(Set<g> set, List<o> list, c cVar, j jVar) {
        if (list != null) {
            for (o a2 : list) {
                g a3 = g.a(a2, cVar, jVar);
                if (a3 != null) {
                    set.add(a3);
                }
            }
        }
        return set;
    }

    public static void a(c cVar, AppLovinAdLoadListener appLovinAdLoadListener, d dVar, int i, j jVar) {
        if (jVar != null) {
            n.a(appLovinAdLoadListener, cVar.g(), i, jVar);
            a(a(cVar, jVar), dVar, jVar);
            return;
        }
        throw new IllegalArgumentException("Unable to handle failure. No sdk specified.");
    }

    public static void a(o oVar, Map<String, Set<g>> map, c cVar, j jVar) {
        List<o> a2;
        p v;
        String str;
        String str2;
        if (jVar != null) {
            if (oVar == null) {
                v = jVar.v();
                str = "VastUtils";
                str2 = "Unable to render event trackers; null node provided";
            } else if (map == null) {
                v = jVar.v();
                str = "VastUtils";
                str2 = "Unable to render event trackers; null event trackers provided";
            } else {
                o b2 = oVar.b("TrackingEvents");
                if (b2 != null && (a2 = b2.a("Tracking")) != null) {
                    for (o next : a2) {
                        String str3 = next.b().get("event");
                        if (k.b(str3)) {
                            g a3 = g.a(next, cVar, jVar);
                            if (a3 != null) {
                                Set set = map.get(str3);
                                if (set != null) {
                                    set.add(a3);
                                } else {
                                    HashSet hashSet = new HashSet();
                                    hashSet.add(a3);
                                    map.put(str3, hashSet);
                                }
                            }
                        } else {
                            p v2 = jVar.v();
                            v2.d("VastUtils", "Could not find event for tracking node = " + next);
                        }
                    }
                    return;
                }
                return;
            }
            v.d(str, str2);
            return;
        }
        throw new IllegalArgumentException("Unable to render event trackers. No sdk specified.");
    }

    public static void a(List<o> list, Set<g> set, c cVar, j jVar) {
        p v;
        String str;
        String str2;
        if (jVar != null) {
            if (list == null) {
                v = jVar.v();
                str = "VastUtils";
                str2 = "Unable to render trackers; null nodes provided";
            } else if (set == null) {
                v = jVar.v();
                str = "VastUtils";
                str2 = "Unable to render trackers; null trackers provided";
            } else {
                for (o a2 : list) {
                    g a3 = g.a(a2, cVar, jVar);
                    if (a3 != null) {
                        set.add(a3);
                    }
                }
                return;
            }
            v.d(str, str2);
            return;
        }
        throw new IllegalArgumentException("Unable to render trackers. No sdk specified.");
    }

    public static void a(Set<g> set, long j, Uri uri, d dVar, j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Unable to fire trackers. No sdk specified.");
        } else if (set != null && !set.isEmpty()) {
            for (g b2 : set) {
                Uri a2 = a(b2.b(), j, uri, dVar, jVar);
                if (a2 != null) {
                    jVar.G().a(e.j().a(a2.toString()).a(false).a(), false);
                }
            }
        }
    }

    public static void a(Set<g> set, d dVar, j jVar) {
        a(set, -1, (Uri) null, dVar, jVar);
    }

    public static void a(Set<g> set, j jVar) {
        a(set, -1, (Uri) null, d.UNSPECIFIED, jVar);
    }

    public static boolean a(o oVar) {
        if (oVar != null) {
            return oVar.c("Wrapper") != null;
        }
        throw new IllegalArgumentException("Unable to check if a given XmlNode contains a wrapper response");
    }

    private static String b() {
        a.setTimeZone(TimeZone.getDefault());
        return a.format(new Date());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r1 = (r1 = r1.a()).a();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(com.applovin.impl.a.a r1) {
        /*
            r0 = 0
            if (r1 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.applovin.impl.a.j r1 = r1.a()
            if (r1 == 0) goto L_0x0017
            java.util.List r1 = r1.a()
            if (r1 == 0) goto L_0x0017
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0017
            r0 = 1
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.a.i.b(com.applovin.impl.a.a):boolean");
    }

    public static boolean b(o oVar) {
        if (oVar != null) {
            return oVar.c("InLine") != null;
        }
        throw new IllegalArgumentException("Unable to check if a given XmlNode contains an inline response");
    }

    public static boolean c(a aVar) {
        b d;
        e b2;
        if (aVar == null || (d = aVar.d()) == null || (b2 = d.b()) == null) {
            return false;
        }
        return b2.b() != null || k.b(b2.c());
    }
}
