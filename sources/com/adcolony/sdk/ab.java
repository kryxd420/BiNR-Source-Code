package com.adcolony.sdk;

import java.text.SimpleDateFormat;
import java.util.Date;

class ab {
    static final SimpleDateFormat l = new SimpleDateFormat("yyyyMMdd'T'HHmmss.SSSZ");
    static final String m = "message";
    static final String n = "timestamp";
    /* access modifiers changed from: private */
    public Date a;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public x c;
    protected String o;

    ab() {
    }

    /* access modifiers changed from: package-private */
    public void a(x xVar) {
        this.c = xVar;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.b = i;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        switch (this.b) {
            case -1:
                return "Fatal";
            case 0:
                return "Error";
            case 1:
                return "Warn";
            case 2:
                return "Info";
            case 3:
                return "Debug";
            default:
                return "UNKNOWN LOG LEVEL";
        }
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return l.format(this.a);
    }

    /* access modifiers changed from: package-private */
    public x f() {
        return this.c;
    }

    public String toString() {
        return e() + " " + b() + "/" + f().d() + ": " + d();
    }

    static class a {
        protected ab b = new ab();

        a() {
        }

        /* access modifiers changed from: package-private */
        public a a(int i) {
            int unused = this.b.b = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(x xVar) {
            x unused = this.b.c = xVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(String str) {
            this.b.o = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(Date date) {
            Date unused = this.b.a = date;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ab a() {
            if (this.b.a == null) {
                Date unused = this.b.a = new Date(System.currentTimeMillis());
            }
            return this.b;
        }
    }
}
