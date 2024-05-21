package com.adcolony.sdk;

import android.app.Activity;
import com.adcolony.sdk.aa;
import com.applovin.sdk.AppLovinEventTypes;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

class p implements Runnable {
    String a = "";
    String b = "";
    boolean c;
    int d;
    int e;
    private HttpURLConnection f;
    private InputStream g;
    private af h;
    private a i;
    private final int j = 4096;
    private String k;
    private int l = 0;
    private boolean m = false;
    private Map<String, List<String>> n;
    private String o = "";

    interface a {
        void a(p pVar, af afVar, Map<String, List<String>> map);
    }

    p(af afVar, a aVar) {
        this.h = afVar;
        this.i = aVar;
    }

    public void run() {
        boolean z = false;
        this.c = false;
        try {
            if (b()) {
                this.c = c();
                if (this.h.d().equals("WebServices.post") && this.e != 200) {
                    this.c = false;
                }
            }
        } catch (MalformedURLException e2) {
            new aa.a().a("MalformedURLException: ").a(e2.toString()).a(aa.h);
            this.c = true;
        } catch (OutOfMemoryError unused) {
            aa.a a2 = new aa.a().a("Out of memory error - disabling AdColony. (").a(this.d).a("/").a(this.l);
            a2.a("): " + this.a).a(aa.g);
            a.a().a(true);
        } catch (IOException e3) {
            new aa.a().a("Download of ").a(this.a).a(" failed: ").a(e3.toString()).a(aa.f);
            this.e = this.e == 0 ? 504 : this.e;
        } catch (IllegalStateException e4) {
            new aa.a().a("okhttp error: ").a(e4.toString()).a(aa.g);
            e4.printStackTrace();
        } catch (Exception e5) {
            new aa.a().a("Exception: ").a(e5.toString()).a(aa.g);
            e5.printStackTrace();
        }
        z = true;
        if (this.c) {
            new aa.a().a("Downloaded ").a(this.a).a(aa.d);
        }
        if (z) {
            this.i.a(this, this.h, this.n);
        }
    }

    private boolean b() throws IOException {
        JSONObject c2 = this.h.c();
        String b2 = y.b(c2, "content_type");
        String b3 = y.b(c2, AppLovinEventTypes.USER_VIEWED_CONTENT);
        boolean d2 = y.d(c2, "no_redirect");
        this.a = y.b(c2, "url");
        this.o = y.b(c2, "filepath");
        this.k = y.b(c2, "encoding");
        this.l = y.a(c2, "max_size", 0);
        this.m = this.l != 0;
        this.d = 0;
        this.g = null;
        this.f = null;
        this.n = null;
        if (!this.a.startsWith("file://")) {
            this.f = (HttpURLConnection) new URL(this.a).openConnection();
            this.f.setInstanceFollowRedirects(!d2);
            this.f.setRequestProperty("Accept-Charset", "UTF-8");
            if (!b2.equals("")) {
                this.f.setRequestProperty("Content-Type", b2);
            }
            if (this.h.d().equals("WebServices.post")) {
                this.f.setDoOutput(true);
                this.f.setFixedLengthStreamingMode(b3.getBytes("UTF-8").length);
                new PrintStream(this.f.getOutputStream()).print(b3);
            }
        } else if (this.a.startsWith("file:///android_asset/")) {
            Activity c3 = a.c();
            if (c3 != null) {
                this.g = c3.getAssets().open(this.a.substring("file:///android_asset/".length()));
            }
        } else {
            this.g = new FileInputStream(this.a.substring("file://".length()));
        }
        if (this.f == null && this.g == null) {
            return false;
        }
        return true;
    }

    private boolean c() throws Exception {
        OutputStream outputStream;
        String d2 = this.h.d();
        if (this.g != null) {
            outputStream = this.o.length() == 0 ? new ByteArrayOutputStream(4096) : new FileOutputStream(new File(this.o).getAbsolutePath());
        } else if (d2.equals("WebServices.download")) {
            this.g = this.f.getInputStream();
            outputStream = new FileOutputStream(this.o);
        } else if (d2.equals("WebServices.get")) {
            this.g = this.f.getInputStream();
            outputStream = new ByteArrayOutputStream(4096);
        } else if (d2.equals("WebServices.post")) {
            this.f.connect();
            this.g = this.f.getResponseCode() == 200 ? this.f.getInputStream() : this.f.getErrorStream();
            outputStream = new ByteArrayOutputStream(4096);
        } else {
            outputStream = null;
        }
        if (this.f != null) {
            this.e = this.f.getResponseCode();
            this.n = this.f.getHeaderFields();
        }
        return a(this.g, outputStream);
    }

    private boolean a(InputStream inputStream, OutputStream outputStream) throws Exception {
        BufferedInputStream bufferedInputStream;
        Exception e2;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 4096);
                    if (read != -1) {
                        this.d += read;
                        if (this.m) {
                            if (this.d > this.l) {
                                throw new Exception("Data exceeds expected maximum (" + this.d + "/" + this.l + "): " + this.f.getURL().toString());
                            }
                        }
                        outputStream.write(bArr, 0, read);
                    } else {
                        String str = "UTF-8";
                        if (this.k != null && !this.k.isEmpty()) {
                            str = this.k;
                        }
                        if (outputStream instanceof ByteArrayOutputStream) {
                            this.b = ((ByteArrayOutputStream) outputStream).toString(str);
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        bufferedInputStream.close();
                        return true;
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    throw e2;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (Exception e4) {
            Exception exc = e4;
            bufferedInputStream = null;
            e2 = exc;
            throw e2;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            bufferedInputStream = null;
            th = th3;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public af a() {
        return this.h;
    }
}
