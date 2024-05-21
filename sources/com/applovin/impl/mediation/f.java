package com.applovin.impl.mediation;

import android.os.Bundle;

public class f {
    private final Bundle a;

    public static class a {
        /* access modifiers changed from: private */
        public final Bundle a = new Bundle();

        public a a(String str) {
            if (str != null) {
                this.a.remove(str);
                return this;
            }
            throw new IllegalArgumentException("No key specified.");
        }

        public a a(String str, String str2) {
            if (str != null) {
                this.a.putString(str, str2);
                return this;
            }
            throw new IllegalArgumentException("No key specified");
        }

        public f a() {
            return new f(this);
        }
    }

    private f(a aVar) {
        this.a = aVar.a;
    }

    public Bundle a() {
        return this.a;
    }
}
