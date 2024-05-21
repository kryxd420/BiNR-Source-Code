package com.adcolony.sdk;

import android.app.Activity;
import android.os.StatFs;
import com.adcolony.sdk.aa;
import java.io.File;

class ar {
    private String a;
    private String b;
    private String c;
    private String d;
    private File e;
    private File f;
    private File g;

    ar() {
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        new aa.a().a("Configuring storage").a(aa.d);
        l a2 = a.a();
        this.a = c() + "/adc3/";
        this.b = this.a + "media/";
        this.e = new File(this.b);
        if (!this.e.isDirectory()) {
            this.e.delete();
            this.e.mkdirs();
        }
        if (!this.e.isDirectory()) {
            a2.a(true);
            return false;
        } else if (a(this.b) < 2.097152E7d) {
            new aa.a().a("Not enough memory available at media path, disabling AdColony.").a(aa.e);
            a2.a(true);
            return false;
        } else {
            this.c = c() + "/adc3/data/";
            this.f = new File(this.c);
            if (!this.f.isDirectory()) {
                this.f.delete();
            }
            this.f.mkdirs();
            this.d = this.a + "tmp/";
            this.g = new File(this.d);
            if (!this.g.isDirectory()) {
                this.g.delete();
                this.g.mkdirs();
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        if (this.e == null || this.f == null || this.g == null) {
            return false;
        }
        if (!this.e.isDirectory()) {
            this.e.delete();
        }
        if (!this.f.isDirectory()) {
            this.f.delete();
        }
        if (!this.g.isDirectory()) {
            this.g.delete();
        }
        this.e.mkdirs();
        this.f.mkdirs();
        this.g.mkdirs();
        return true;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        Activity c2 = a.c();
        if (c2 == null) {
            return "";
        }
        return c2.getFilesDir().getAbsolutePath();
    }

    /* access modifiers changed from: package-private */
    public double a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return (double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
        } catch (RuntimeException unused) {
            return 0.0d;
        }
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public String g() {
        return this.a;
    }
}
