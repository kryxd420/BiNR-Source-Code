package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public final class fn {
    private static fn a = new fn();

    public static InputStream a(String str) {
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.connect();
        return openConnection.getInputStream();
    }

    public static URLConnection a(URL url) {
        return url.openConnection();
    }
}
