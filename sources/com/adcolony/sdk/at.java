package com.adcolony.sdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import com.adcolony.sdk.aa;
import com.tapjoy.TJAdUnitConstants;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class at {
    at() {
    }

    /* access modifiers changed from: package-private */
    public void a() {
        a.a("System.open_store", (ah) new ah() {
            public void a(af afVar) {
                at.this.a(afVar);
            }
        });
        a.a("System.save_screenshot", (ah) new ah() {
            public void a(af afVar) {
                at.this.b(afVar);
            }
        });
        a.a("System.telephone", (ah) new ah() {
            public void a(af afVar) {
                at.this.c(afVar);
            }
        });
        a.a("System.sms", (ah) new ah() {
            public void a(af afVar) {
                at.this.d(afVar);
            }
        });
        a.a("System.vibrate", (ah) new ah() {
            public void a(af afVar) {
                at.this.e(afVar);
            }
        });
        a.a("System.open_browser", (ah) new ah() {
            public void a(af afVar) {
                at.this.f(afVar);
            }
        });
        a.a("System.mail", (ah) new ah() {
            public void a(af afVar) {
                at.this.g(afVar);
            }
        });
        a.a("System.launch_app", (ah) new ah() {
            public void a(af afVar) {
                at.this.h(afVar);
            }
        });
        a.a("System.create_calendar_event", (ah) new ah() {
            public void a(af afVar) {
                at.this.i(afVar);
            }
        });
        a.a("System.check_app_presence", (ah) new ah() {
            public void a(af afVar) {
                at.this.j(afVar);
            }
        });
        a.a("System.check_social_presence", (ah) new ah() {
            public void a(af afVar) {
                at.this.k(afVar);
            }
        });
        a.a("System.social_post", (ah) new ah() {
            public void a(af afVar) {
                at.this.l(afVar);
            }
        });
        a.a("System.make_in_app_purchase", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = at.this.n(afVar);
            }
        });
        a.a("System.close", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = at.this.m(afVar);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean m(af afVar) {
        String b = y.b(afVar.c(), "ad_session_id");
        Activity c = a.c();
        if (c == null || !(c instanceof b)) {
            return false;
        }
        JSONObject a = y.a();
        y.a(a, "id", b);
        new af("AdSession.on_request_close", ((b) c).f, a).b();
        return true;
    }

    /* access modifiers changed from: private */
    public boolean n(af afVar) {
        JSONObject c = afVar.c();
        d m = a.a().m();
        String b = y.b(c, "ad_session_id");
        AdColonyInterstitial adColonyInterstitial = m.c().get(b);
        az azVar = m.f().get(b);
        if ((adColonyInterstitial == null || adColonyInterstitial.getListener() == null || adColonyInterstitial.d() == null) && (azVar == null || azVar.getListener() == null || azVar.getExpandedContainer() == null)) {
            return false;
        }
        if (azVar == null) {
            new af("AdUnit.make_in_app_purchase", adColonyInterstitial.d().c()).b();
        } else {
            new af("AdUnit.make_in_app_purchase", azVar.getExpandedContainer().c()).b();
        }
        b(y.b(c, "ad_session_id"));
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        String b = y.b(c, "product_id");
        if (b.equals("")) {
            b = y.b(c, "handle");
        }
        if (aw.a(new Intent("android.intent.action.VIEW", Uri.parse(b)))) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Unable to open.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        com.adcolony.sdk.aw.a("Error saving screenshot.", 0);
        com.adcolony.sdk.y.a(r2, "success", false);
        r11.a(r2).b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00d9, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00da, code lost:
        com.adcolony.sdk.aw.a("Error saving screenshot.", 0);
        com.adcolony.sdk.y.a(r2, "success", false);
        r11.a(r2).b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00eb, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x00a3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(final com.adcolony.sdk.af r11) {
        /*
            r10 = this;
            android.app.Activity r0 = com.adcolony.sdk.a.c()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
            int r2 = android.support.v4.app.ActivityCompat.checkSelfPermission(r0, r2)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            if (r2 != 0) goto L_0x00ec
            org.json.JSONObject r2 = r11.c()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r3 = "ad_session_id"
            java.lang.String r2 = com.adcolony.sdk.y.b((org.json.JSONObject) r2, (java.lang.String) r3)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r10.b((java.lang.String) r2)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            org.json.JSONObject r2 = com.adcolony.sdk.y.a()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r3.<init>()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.io.File r4 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r4 = r4.toString()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r3.append(r4)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r4 = "/Pictures/AdColony_Screenshots/AdColony_Screenshot_"
            r3.append(r4)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r3.append(r4)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r4 = ".jpg"
            r3.append(r4)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r3 = r3.toString()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            android.view.Window r4 = r0.getWindow()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            android.view.View r4 = r4.getDecorView()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            android.view.View r4 = r4.getRootView()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r5 = 1
            r4.setDrawingCacheEnabled(r5)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            android.graphics.Bitmap r6 = r4.getDrawingCache()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r4.setDrawingCacheEnabled(r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x00a3 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a3 }
            r7.<init>()     // Catch:{ Exception -> 0x00a3 }
            java.io.File r8 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r8 = r8.getPath()     // Catch:{ Exception -> 0x00a3 }
            r7.append(r8)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r8 = "/Pictures"
            r7.append(r8)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x00a3 }
            r4.<init>(r7)     // Catch:{ Exception -> 0x00a3 }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x00a3 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a3 }
            r8.<init>()     // Catch:{ Exception -> 0x00a3 }
            java.io.File r9 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r9 = r9.getPath()     // Catch:{ Exception -> 0x00a3 }
            r8.append(r9)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r9 = "/Pictures/AdColony_Screenshots"
            r8.append(r9)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00a3 }
            r7.<init>(r8)     // Catch:{ Exception -> 0x00a3 }
            r4.mkdirs()     // Catch:{ Exception -> 0x00a3 }
            r7.mkdirs()     // Catch:{ Exception -> 0x00a3 }
        L_0x00a3:
            java.io.File r4 = new java.io.File     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r7.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r8 = 90
            r6.compress(r4, r8, r7)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r7.flush()     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r7.close()     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            java.lang.String[] r4 = new java.lang.String[r5]     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r4[r1] = r3     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r3 = 0
            com.adcolony.sdk.at$7 r6 = new com.adcolony.sdk.at$7     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            r6.<init>(r2, r11)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            android.media.MediaScannerConnection.scanFile(r0, r4, r3, r6)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c8 }
            return r5
        L_0x00c8:
            java.lang.String r0 = "Error saving screenshot."
            com.adcolony.sdk.aw.a((java.lang.String) r0, (int) r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r0 = "success"
            com.adcolony.sdk.y.a((org.json.JSONObject) r2, (java.lang.String) r0, (boolean) r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            com.adcolony.sdk.af r0 = r11.a((org.json.JSONObject) r2)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r0.b()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            return r1
        L_0x00da:
            java.lang.String r0 = "Error saving screenshot."
            com.adcolony.sdk.aw.a((java.lang.String) r0, (int) r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r0 = "success"
            com.adcolony.sdk.y.a((org.json.JSONObject) r2, (java.lang.String) r0, (boolean) r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            com.adcolony.sdk.af r0 = r11.a((org.json.JSONObject) r2)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r0.b()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            return r1
        L_0x00ec:
            java.lang.String r0 = "Error saving screenshot."
            com.adcolony.sdk.aw.a((java.lang.String) r0, (int) r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            org.json.JSONObject r0 = r11.c()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            java.lang.String r2 = "success"
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r2, (boolean) r1)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            com.adcolony.sdk.af r0 = r11.a((org.json.JSONObject) r0)     // Catch:{ NoClassDefFoundError -> 0x0102 }
            r0.b()     // Catch:{ NoClassDefFoundError -> 0x0102 }
            return r1
        L_0x0102:
            java.lang.String r0 = "Error saving screenshot."
            com.adcolony.sdk.aw.a((java.lang.String) r0, (int) r1)
            org.json.JSONObject r0 = r11.c()
            java.lang.String r2 = "success"
            com.adcolony.sdk.y.a((org.json.JSONObject) r0, (java.lang.String) r2, (boolean) r1)
            com.adcolony.sdk.af r11 = r11.a((org.json.JSONObject) r0)
            r11.b()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.at.b(com.adcolony.sdk.af):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean c(af afVar) {
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        Intent intent = new Intent("android.intent.action.DIAL");
        if (aw.a(intent.setData(Uri.parse("tel:" + y.b(c, "phone_number"))))) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Failed to dial number.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean d(af afVar) {
        JSONObject c = afVar.c();
        JSONObject a = y.a();
        JSONArray g = y.g(c, "recipients");
        String str = "";
        for (int i = 0; i < g.length(); i++) {
            if (i != 0) {
                str = str + ";";
            }
            str = str + y.c(g, i);
        }
        if (aw.a(new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + str)).putExtra("sms_body", y.b(c, "body")))) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Failed to create sms.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean e(af afVar) {
        Activity c = a.c();
        if (c == null) {
            return false;
        }
        int a = y.a(afVar.c(), "length_ms", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL);
        JSONObject a2 = y.a();
        JSONArray z = a.a().n().z();
        boolean z2 = false;
        for (int i = 0; i < z.length(); i++) {
            if (y.c(z, i).equals("android.permission.VIBRATE")) {
                z2 = true;
            }
        }
        if (!z2) {
            new aa.a().a("No vibrate permission detected.").a(aa.e);
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        }
        try {
            ((Vibrator) c.getSystemService("vibrator")).vibrate((long) a);
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return true;
        } catch (Exception unused) {
            new aa.a().a("Vibrate command failed.").a(aa.e);
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f(af afVar) {
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        String b = y.b(c, "url");
        if (b.startsWith("browser")) {
            b = b.replaceFirst("browser", "http");
        }
        if (b.startsWith("safari")) {
            b = b.replaceFirst("safari", "http");
        }
        if (aw.a(new Intent("android.intent.action.VIEW", Uri.parse(b)))) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Failed to launch browser.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean g(af afVar) {
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        JSONArray g = y.g(c, "recipients");
        boolean d = y.d(c, TJAdUnitConstants.String.HTML);
        String b = y.b(c, "subject");
        String b2 = y.b(c, "body");
        String[] strArr = new String[g.length()];
        for (int i = 0; i < g.length(); i++) {
            strArr[i] = y.c(g, i);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        if (!d) {
            intent.setType("plain/text");
        }
        intent.putExtra("android.intent.extra.SUBJECT", b).putExtra("android.intent.extra.TEXT", b2).putExtra("android.intent.extra.EMAIL", strArr);
        if (aw.a(intent)) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Failed to send email.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean h(af afVar) {
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        if (y.d(c, "deep_link")) {
            return a(afVar);
        }
        Activity c2 = a.c();
        if (c2 == null) {
            return false;
        }
        if (aw.a(c2.getPackageManager().getLaunchIntentForPackage(y.b(c, "handle")))) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Failed to launch external application.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean i(af afVar) {
        Intent intent;
        af afVar2 = afVar;
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        String str = "";
        String str2 = "";
        JSONObject f = y.f(c, "params");
        JSONObject f2 = y.f(f, "recurrence");
        JSONArray b = y.b();
        JSONArray b2 = y.b();
        JSONArray b3 = y.b();
        String b4 = y.b(f, "description");
        y.b(f, "location");
        String b5 = y.b(f, "start");
        String b6 = y.b(f, "end");
        String b7 = y.b(f, "summary");
        if (f2 != null && f2.length() > 0) {
            str2 = y.b(f2, "expires");
            str = y.b(f2, "frequency");
            b = y.g(f2, "daysInWeek");
            b2 = y.g(f2, "daysInMonth");
            b3 = y.g(f2, "daysInYear");
        }
        if (b7.equals("")) {
            b7 = b4;
        }
        Date h = aw.h(b5);
        Date h2 = aw.h(b6);
        Date h3 = aw.h(str2);
        if (h == null || h2 == null) {
            aw.a("Unable to create Calendar Event", 0);
            y.a(a, "success", false);
            afVar2.a(a).b();
            return false;
        }
        long time = h.getTime();
        long time2 = h2.getTime();
        long j = 0;
        long time3 = h3 != null ? (h3.getTime() - h.getTime()) / 1000 : 0;
        if (str.equals("DAILY")) {
            j = (time3 / 86400) + 1;
        } else if (str.equals("WEEKLY")) {
            j = (time3 / 604800) + 1;
        } else if (str.equals("MONTHLY")) {
            j = (time3 / 2629800) + 1;
        } else if (str.equals("YEARLY")) {
            j = (time3 / 31557600) + 1;
        }
        long j2 = j;
        if (f2 == null || f2.length() <= 0) {
            intent = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(TJAdUnitConstants.String.TITLE, b7).putExtra("description", b4).putExtra("beginTime", time).putExtra("endTime", time2);
        } else {
            String str3 = "FREQ=" + str + ";COUNT=" + j2;
            try {
                if (b.length() != 0) {
                    str3 = str3 + ";BYDAY=" + aw.a(b);
                }
                if (b2.length() != 0) {
                    str3 = str3 + ";BYMONTHDAY=" + aw.b(b2);
                }
                if (b3.length() != 0) {
                    str3 = str3 + ";BYYEARDAY=" + aw.b(b3);
                }
            } catch (JSONException unused) {
            }
            intent = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(TJAdUnitConstants.String.TITLE, b7).putExtra("description", b4).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", str3);
        }
        if (aw.a(intent)) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Unable to create Calendar Event.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean j(af afVar) {
        JSONObject a = y.a();
        String b = y.b(afVar.c(), "name");
        boolean a2 = aw.a(b);
        y.a(a, "success", true);
        y.a(a, "result", a2);
        y.a(a, "name", b);
        y.a(a, NotificationCompat.CATEGORY_SERVICE, b);
        afVar.a(a).b();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean k(af afVar) {
        return j(afVar);
    }

    /* access modifiers changed from: package-private */
    public boolean l(af afVar) {
        JSONObject a = y.a();
        JSONObject c = afVar.c();
        Intent type = new Intent("android.intent.action.SEND").setType("text/plain");
        if (aw.a(type.putExtra("android.intent.extra.TEXT", y.b(c, "text") + " " + y.b(c, "url")), true)) {
            y.a(a, "success", true);
            afVar.a(a).b();
            a(y.b(c, "ad_session_id"));
            b(y.b(c, "ad_session_id"));
            return true;
        }
        aw.a("Unable to create social post.", 0);
        y.a(a, "success", false);
        afVar.a(a).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        d m = a.a().m();
        AdColonyInterstitial adColonyInterstitial = m.c().get(str);
        if (adColonyInterstitial == null || adColonyInterstitial.getListener() == null) {
            az azVar = m.f().get(str);
            e listener = azVar != null ? azVar.getListener() : null;
            if (azVar != null && listener != null && (listener instanceof AdColonyNativeAdViewListener)) {
                ((AdColonyNativeAdViewListener) listener).onLeftApplication((AdColonyNativeAdView) azVar);
                return;
            }
            return;
        }
        adColonyInterstitial.getListener().onLeftApplication(adColonyInterstitial);
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        d m = a.a().m();
        AdColonyInterstitial adColonyInterstitial = m.c().get(str);
        if (adColonyInterstitial == null || adColonyInterstitial.getListener() == null) {
            az azVar = m.f().get(str);
            e listener = azVar != null ? azVar.getListener() : null;
            if (azVar != null && listener != null && (listener instanceof AdColonyNativeAdViewListener)) {
                ((AdColonyNativeAdViewListener) listener).onClicked((AdColonyNativeAdView) azVar);
                return;
            }
            return;
        }
        adColonyInterstitial.getListener().onClicked(adColonyInterstitial);
    }
}
