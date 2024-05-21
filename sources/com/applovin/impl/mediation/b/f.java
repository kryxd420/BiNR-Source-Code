package com.applovin.impl.mediation.b;

import android.app.Activity;
import com.applovin.impl.mediation.c.c;
import com.applovin.impl.mediation.e;
import com.applovin.impl.sdk.c.h;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxErrorCodes;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends com.applovin.impl.sdk.d.a {
    /* access modifiers changed from: private */
    public final String a;
    /* access modifiers changed from: private */
    public final MaxAdFormat c;
    /* access modifiers changed from: private */
    public final JSONObject d;
    private final JSONArray e = this.d.optJSONArray("ads");
    /* access modifiers changed from: private */
    public final com.applovin.impl.mediation.f f;
    /* access modifiers changed from: private */
    public final MaxAdListener g;
    /* access modifiers changed from: private */
    public final Activity h;
    private final AtomicBoolean i;
    /* access modifiers changed from: private */
    public final e j;
    private final Object k;
    private a l;
    /* access modifiers changed from: private */
    public int m;

    private enum a {
        BACKUP_AD_STATE_NOT_NEEDED,
        BACKUP_AD_STATE_LOADING,
        BACKUP_AD_STATE_WAITING_FOR_RESPONSE,
        BACKUP_AD_STATE_LOADED,
        BACKUP_AD_STATE_FAILED
    }

    private class b extends com.applovin.impl.sdk.d.a {
        private final JSONArray c;
        private final int d;

        b(int i, JSONArray jSONArray) {
            super("TaskProcessNextWaterfallAd", f.this.b);
            if (i < 0 || i >= jSONArray.length()) {
                throw new IllegalArgumentException("Invalid ad index specified: " + i);
            }
            this.c = jSONArray;
            this.d = i;
        }

        private String a(int i) {
            if (i < 0 || i >= this.c.length()) {
                return "undefined";
            }
            try {
                return g.a(this.c.getJSONObject(i), "type", "undefined", this.b);
            } catch (JSONException unused) {
                d("Unable to parse next ad from the ad response");
                return "undefined";
            }
        }

        private void f() throws JSONException {
            int unused = f.this.m = this.d;
            JSONObject jSONObject = this.c.getJSONObject(this.d);
            if (f.b(jSONObject)) {
                g();
                return;
            }
            String a2 = a(this.d);
            if ("adapter".equalsIgnoreCase(a2)) {
                a("Starting task for adapter ad...");
                this.b.D().a((com.applovin.impl.sdk.d.a) new e(f.this.a, f.this.f, jSONObject, f.this.d, this.b, f.this.h, new com.applovin.impl.mediation.c.a(f.this.g, this.b) {
                    public void onAdLoadFailed(String str, int i) {
                        b.this.h();
                    }

                    public void onAdLoaded(MaxAd maxAd) {
                        f.this.a(maxAd);
                    }
                }));
                return;
            }
            d("Unable to process ad of unknown type: " + a2);
            f.this.a(-800);
        }

        private void g() {
            String str;
            a a2 = f.this.a(a.BACKUP_AD_STATE_WAITING_FOR_RESPONSE);
            if (a2 != a.BACKUP_AD_STATE_LOADING) {
                if (a2 == a.BACKUP_AD_STATE_LOADED) {
                    if (f.this.j.b(f.this.h)) {
                        b("Backup ad was promoted to primary");
                        return;
                    }
                    str = "Failed to promote backup ad to primary: nothing promoted";
                } else if (a2 == a.BACKUP_AD_STATE_FAILED) {
                    h();
                    return;
                } else {
                    str = "Unknown state of loading the backup ad: " + a2;
                }
                d(str);
                f.this.a(-5201);
            }
        }

        /* access modifiers changed from: private */
        public void h() {
            if (f.this.j.c()) {
                c("Not loading next waterfall ad because returned ad was already displayed");
            } else if (this.d < this.c.length() - 1) {
                b("Attempting to load next ad (" + this.d + ") after failure...");
                this.b.D().a((com.applovin.impl.sdk.d.a) new b(this.d + 1, this.c), c.a(f.this.c, ((Boolean) this.b.a(com.applovin.impl.sdk.b.a.F)).booleanValue() ? q.a.MAIN : q.a.BACKGROUND, this.b));
            } else {
                f.this.i();
            }
        }

        public i a() {
            return i.F;
        }

        public void run() {
            try {
                f();
            } catch (Throwable th) {
                a("Encountered error while processing ad number " + this.d, th);
                this.b.F().a(a());
                f.this.i();
            }
        }
    }

    f(String str, MaxAdFormat maxAdFormat, JSONObject jSONObject, com.applovin.impl.mediation.f fVar, Activity activity, j jVar, MaxAdListener maxAdListener) {
        super("TaskProcessMediationWaterfall " + str, jVar);
        this.a = str;
        this.c = maxAdFormat;
        this.d = jSONObject;
        this.f = fVar;
        this.g = maxAdListener;
        this.h = activity;
        this.j = new e(jSONObject, jVar);
        this.i = new AtomicBoolean();
        this.k = new Object();
        this.l = a.BACKUP_AD_STATE_NOT_NEEDED;
    }

    /* access modifiers changed from: private */
    public a a(a aVar) {
        a aVar2;
        synchronized (this.k) {
            aVar2 = this.l;
            this.l = aVar;
            b("Backup ad state changed from " + aVar2 + " to " + aVar);
        }
        return aVar2;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        h E;
        com.applovin.impl.sdk.c.g gVar;
        if (i2 == 204) {
            E = this.b.E();
            gVar = com.applovin.impl.sdk.c.g.s;
        } else if (i2 == -5001) {
            E = this.b.E();
            gVar = com.applovin.impl.sdk.c.g.t;
        } else {
            E = this.b.E();
            gVar = com.applovin.impl.sdk.c.g.u;
        }
        E.a(gVar);
        if (this.i.compareAndSet(false, true)) {
            b("Notifying parent of ad load failure...");
            com.applovin.impl.sdk.e.h.a(this.g, this.a, i2, this.b);
        }
    }

    /* access modifiers changed from: private */
    public void a(MaxAd maxAd) {
        if (maxAd instanceof com.applovin.impl.mediation.a.a) {
            this.j.a((com.applovin.impl.mediation.a.a) maxAd);
            h();
            return;
        }
        a(-5201);
    }

    /* access modifiers changed from: private */
    public void b(MaxAd maxAd) {
        if (maxAd instanceof com.applovin.impl.mediation.a.a) {
            b("Backup ad loaded");
            com.applovin.impl.mediation.a.a aVar = (com.applovin.impl.mediation.a.a) maxAd;
            if (a(a.BACKUP_AD_STATE_LOADED) == a.BACKUP_AD_STATE_WAITING_FOR_RESPONSE) {
                this.b.a(this.h).maybeScheduleBackupAdPromotedToPrimaryPostback(aVar);
                this.j.a(aVar);
            } else {
                this.j.b(aVar);
            }
            h();
            return;
        }
        a(-5201);
    }

    /* access modifiers changed from: private */
    public static boolean b(JSONObject jSONObject) {
        return jSONObject.optBoolean("is_backup");
    }

    private void f() throws JSONException {
        JSONObject jSONObject;
        int i2 = 0;
        while (true) {
            if (i2 >= this.e.length()) {
                jSONObject = null;
                break;
            }
            jSONObject = this.e.getJSONObject(i2);
            if (b(jSONObject)) {
                break;
            }
            i2++;
        }
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 != null) {
            b("Loading backup ad...");
            a(a.BACKUP_AD_STATE_LOADING);
            this.b.D().a((com.applovin.impl.sdk.d.a) new e(this.a, this.f, jSONObject2, this.d, this.b, this.h, new com.applovin.impl.mediation.c.a(this.g, this.b) {
                public void onAdLoadFailed(String str, int i) {
                    f.this.g();
                }

                public void onAdLoaded(MaxAd maxAd) {
                    f.this.b(maxAd);
                }
            }), q.a.MEDIATION_BACKUP);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        d("Backup ad failed to load...");
        if (a(a.BACKUP_AD_STATE_FAILED) == a.BACKUP_AD_STATE_WAITING_FOR_RESPONSE) {
            new b(this.m, this.e).h();
        }
    }

    private void h() {
        if (this.i.compareAndSet(false, true)) {
            b("Notifying parent of ad load success...");
            com.applovin.impl.sdk.e.h.a(this.g, (MaxAd) this.j, this.b);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        a((int) MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED);
    }

    private boolean j() {
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.a.R)).booleanValue()) {
            return true;
        }
        MaxAdFormat maxAdFormat = this.c;
        MaxAdFormat c2 = n.c(g.a(this.d, "ad_format", (String) null, this.b));
        boolean a2 = c.a(maxAdFormat, c2);
        if (!a2) {
            d("Requested ad format: " + maxAdFormat + ", is not equal to ad response format: " + c2);
        }
        return a2;
    }

    public i a() {
        return i.E;
    }

    public void run() {
        try {
            a("Processing ad response...");
            int length = this.e != null ? this.e.length() : 0;
            if (length <= 0) {
                c("No ads were returned from the server");
                n.a(this.a, this.d, this.b);
                a(204);
            } else if (!j()) {
                a(-800);
            } else {
                f();
                a("Loading the first out of " + length + " ads...");
                this.b.D().a((com.applovin.impl.sdk.d.a) new b(0, this.e));
            }
        } catch (Throwable th) {
            a("Encountered error while processing ad response", th);
            i();
            this.b.F().a(a());
        }
    }
}
