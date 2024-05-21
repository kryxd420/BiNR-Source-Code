package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class ch implements cg {
    private final String a;
    private final URL b;

    public ch(String str, URL url) {
        this.a = str;
        this.b = url;
    }

    public final Object a(cd cdVar) {
        URI uri;
        URL url = new URL(this.b, cdVar.c());
        String b2 = cdVar.b();
        if ("GET".equals(b2) || "DELETE".equals(b2)) {
            Map e = cdVar.e();
            if (!e.isEmpty()) {
                url = new URL(url, url.getPath() + "?" + fo.a(e));
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) fn.a(url);
        httpURLConnection.setRequestMethod(b2);
        httpURLConnection.setRequestProperty("User-Agent", this.a);
        for (Map.Entry entry : cdVar.a().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), entry.getValue().toString());
        }
        if (!"GET".equals(b2) && !"DELETE".equals(b2)) {
            if ("POST".equals(b2) || "PUT".equals(b2)) {
                String d = cdVar.d();
                if (d == null) {
                    fo.a(httpURLConnection, "application/x-www-form-urlencoded", fo.a(cdVar.e()), jq.c);
                } else if ("application/json".equals(d)) {
                    fo.a(httpURLConnection, "application/json; charset=utf-8", bk.a((Object) cdVar.e()), jq.c);
                } else {
                    throw new IllegalArgumentException("Unknown content type: " + d);
                }
            } else {
                throw new IllegalArgumentException("Unknown method: " + b2);
            }
        }
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 409) {
            switch (responseCode) {
                case 200:
                case FetchConst.NETWORK_WIFI /*201*/:
                    break;
                default:
                    throw new IOException("Unexpected status code: " + httpURLConnection.getResponseCode());
            }
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            uri = httpURLConnection.getURL().toURI();
        } catch (URISyntaxException unused) {
            uri = null;
        }
        try {
            return cdVar.a(uri, inputStream);
        } finally {
            inputStream.close();
        }
    }
}
