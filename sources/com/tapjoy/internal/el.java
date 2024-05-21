package com.tapjoy.internal;

import java.io.IOException;
import java.io.Serializable;

public abstract class el implements Serializable {
    transient int a = 0;
    protected transient int b = 0;
    private final transient en c;
    private final transient iy d;

    protected el(en enVar, iy iyVar) {
        if (enVar == null) {
            throw new NullPointerException("adapter == null");
        } else if (iyVar != null) {
            this.c = enVar;
            this.d = iyVar;
        } else {
            throw new NullPointerException("unknownFields == null");
        }
    }

    public final iy a() {
        iy iyVar = this.d;
        if (iyVar != null) {
            return iyVar;
        }
        return iy.b;
    }

    public String toString() {
        return en.c(this);
    }

    public static abstract class a {
        iv a;
        ep b;

        protected a() {
        }

        public final a a(iy iyVar) {
            if (iyVar.c() > 0) {
                if (this.b == null) {
                    this.a = new iv();
                    this.b = new ep(this.a);
                }
                try {
                    this.b.a(iyVar);
                } catch (IOException unused) {
                    throw new AssertionError();
                }
            }
            return this;
        }

        public final a a(int i, ek ekVar, Object obj) {
            if (this.b == null) {
                this.a = new iv();
                this.b = new ep(this.a);
            }
            try {
                ekVar.a().a(this.b, i, obj);
                return this;
            } catch (IOException unused) {
                throw new AssertionError();
            }
        }

        public final iy a() {
            return this.a != null ? new iy(this.a.clone().g()) : iy.b;
        }
    }
}
