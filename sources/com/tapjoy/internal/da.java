package com.tapjoy.internal;

public final class da {
    public final String a;
    public final String b;

    private da(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static da a(String str, String str2) {
        ds.a(str, "Name is null or empty");
        ds.a(str2, "Version is null or empty");
        return new da(str, str2);
    }
}
