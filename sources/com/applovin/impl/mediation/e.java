package com.applovin.impl.mediation;

import android.app.Activity;
import com.applovin.impl.mediation.a.a;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class e implements MaxAd {
    private final JSONObject a;
    private final j b;
    private final Object c = new Object();
    private boolean d;
    private boolean e;
    private a f;
    private a g;
    private a h;

    public e(JSONObject jSONObject, j jVar) {
        this.a = jSONObject;
        this.b = jVar;
    }

    public a a() {
        return this.h;
    }

    public a a(Activity activity) {
        boolean z;
        a aVar;
        synchronized (this.c) {
            if (!this.d) {
                z = true;
                this.d = true;
                if (this.f != null) {
                    aVar = this.f;
                    z = false;
                } else if (this.g != null) {
                    aVar = this.g;
                } else {
                    throw new IllegalStateException("Ad with backup does not have either primary or backup ad to resolve");
                }
            } else {
                throw new IllegalStateException("Ad with backup was destroyed");
            }
        }
        if (z) {
            this.b.a(activity).a(aVar);
        }
        this.h = aVar;
        return aVar;
    }

    public void a(a aVar) {
        synchronized (this.c) {
            if (!this.e) {
                this.f = aVar;
            }
        }
    }

    public List<a> b() {
        ArrayList arrayList;
        synchronized (this.c) {
            this.e = true;
            arrayList = new ArrayList(2);
            if (this.f != null) {
                arrayList.add(this.f);
                this.f = null;
            }
            if (this.g != null) {
                arrayList.add(this.g);
                this.g = null;
            }
        }
        return arrayList;
    }

    public void b(a aVar) {
        synchronized (this.c) {
            if (!this.e) {
                this.g = aVar;
            }
        }
    }

    public boolean b(Activity activity) {
        a aVar;
        synchronized (this.c) {
            aVar = null;
            if (this.g != null) {
                this.f = this.g;
                this.g = null;
                aVar = this.f;
            }
        }
        if (aVar != null) {
            this.b.a(activity).maybeScheduleBackupAdPromotedToPrimaryPostback(aVar);
        }
        return aVar != null;
    }

    public boolean c() {
        boolean z;
        synchronized (this.c) {
            if (!this.e) {
                if (!this.d) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public long d() {
        return g.a(this.a, "ad_expiration_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.L)).longValue(), this.b);
    }

    public String getAdUnitId() {
        return g.a(this.a, "ad_unit_id", (String) null, this.b);
    }

    public MaxAdFormat getFormat() {
        return n.c(g.a(this.a, "ad_format", (String) null, this.b));
    }

    public boolean isReady() {
        synchronized (this.c) {
            if (this.f == null) {
                if (this.g == null) {
                    return false;
                }
            }
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediatedAdWithBackup{isReady=");
        sb.append(isReady());
        sb.append(", format=");
        sb.append(getFormat());
        sb.append(", adUnitId='");
        sb.append(getAdUnitId());
        sb.append("', hasAd=");
        boolean z = false;
        sb.append(this.f != null);
        sb.append(", hasBackup=");
        if (this.g != null) {
            z = true;
        }
        sb.append(z);
        sb.append('}');
        return sb.toString();
    }
}
