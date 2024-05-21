package com.adcolony.sdk;

import java.util.HashMap;

class f {
    String a;
    private HashMap<Integer, Boolean> b = new HashMap<>();
    private aq c;
    private ae d;
    private int e;

    f(String str, int i) {
        this.a = str;
        this.e = i;
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        if (this.c == null) {
            this.c = new aq(this.a, this.e);
            this.d = new ae(this.a, this.e);
        }
        int c2 = y.c(afVar.c(), "id");
        if (y.d(afVar.c(), "use_sound_pool")) {
            this.b.put(Integer.valueOf(c2), true);
            this.c.a(afVar);
            return;
        }
        this.b.put(Integer.valueOf(c2), false);
        this.d.a(afVar);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c.a().autoPause();
        this.d.a();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.c.a().autoResume();
        this.d.b();
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.d(afVar);
        } else {
            this.d.b(afVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.c(afVar);
        } else {
            this.d.c(afVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void d(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.b(afVar);
        } else {
            this.d.d(afVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void e(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.e(afVar);
        } else {
            this.d.e(afVar);
        }
    }

    /* access modifiers changed from: package-private */
    public ae c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public aq d() {
        return this.c;
    }
}
