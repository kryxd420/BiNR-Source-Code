package com.adcolony.sdk;

class x {
    private String a;
    private String b;
    private String c;
    private String d = "%s_%s_%s";

    public x(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public String a() {
        return String.format(this.d, new Object[]{b(), c(), d()});
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }
}
