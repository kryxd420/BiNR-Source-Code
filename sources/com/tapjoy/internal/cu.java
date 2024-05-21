package com.tapjoy.internal;

public final class cu {
    private final dc a;

    private cu(dc dcVar) {
        this.a = dcVar;
    }

    public static cu a(cv cvVar) {
        dc dcVar = (dc) cvVar;
        ds.a((Object) cvVar, "AdSession is null");
        if (dcVar.c.b == null) {
            ds.a(dcVar);
            cu cuVar = new cu(dcVar);
            dcVar.c.b = cuVar;
            return cuVar;
        }
        throw new IllegalStateException("AdEvents already exists for AdSession");
    }

    public final void a() {
        ds.a(this.a);
        if (cz.NATIVE == this.a.a.a) {
            if (!this.a.d()) {
                try {
                    this.a.a();
                } catch (Exception unused) {
                }
            }
            if (this.a.d()) {
                dc dcVar = this.a;
                if (!dcVar.g) {
                    dj.a().a(dcVar.c.c(), "publishImpressionEvent", new Object[0]);
                    dcVar.g = true;
                    return;
                }
                throw new IllegalStateException("Impression event can only be sent once");
            }
            return;
        }
        throw new IllegalStateException("Impression event is not expected from the Native AdSession");
    }
}
