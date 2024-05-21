package com.tapjoy.internal;

import java.net.URL;

public final class db {
    public final String a;
    public final URL b;
    public final String c;

    private db(String str, URL url, String str2) {
        this.a = str;
        this.b = url;
        this.c = str2;
    }

    public static db a(String str, URL url, String str2) {
        ds.a(str, "VendorKey is null or empty");
        ds.a((Object) url, "ResourceURL is null");
        ds.a(str2, "VerificationParameters is null or empty");
        return new db(str, url, str2);
    }

    public static db a(URL url) {
        ds.a((Object) url, "ResourceURL is null");
        return new db((String) null, url, (String) null);
    }
}
