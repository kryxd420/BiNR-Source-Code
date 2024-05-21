package com.adcolony.sdk;

import android.app.Activity;
import android.support.v4.app.NotificationCompat;
import com.adcolony.sdk.aa;
import java.io.File;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;

class ADCVMModule implements ai {
    static boolean a = false;
    int b;
    ExecutorService c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public JSONArray e = y.b();
    private Runnable f;
    private Runnable g;
    /* access modifiers changed from: private */
    public ADCJSVirtualMachine h;

    /* JADX WARNING: Removed duplicated region for block: B:20:0x007a A[Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007d A[Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ADCVMModule(android.app.Activity r6, int r7, java.lang.String r8, org.json.JSONObject r9, java.util.concurrent.ExecutorService r10) {
        /*
            r5 = this;
            r5.<init>()
            org.json.JSONArray r0 = com.adcolony.sdk.y.b()
            r5.e = r0
            r5.b = r7
            r5.c = r10
            com.adcolony.sdk.l r10 = com.adcolony.sdk.a.a()
            r0 = 0
            r1 = 1
            if (r7 != r1) goto L_0x0017
            r2 = 1
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            r5.d = r2
            if (r7 != r1) goto L_0x0022
            boolean r2 = a
            if (r2 == 0) goto L_0x0022
            java.lang.String r8 = "ADCController.js"
        L_0x0022:
            com.adcolony.sdk.aa$a r2 = new com.adcolony.sdk.aa$a
            r2.<init>()
            java.lang.String r3 = "----------------------------------------------------------------------"
            com.adcolony.sdk.aa$a r2 = r2.a((java.lang.String) r3)
            com.adcolony.sdk.aa r3 = com.adcolony.sdk.aa.b
            r2.a((com.adcolony.sdk.aa) r3)
            com.adcolony.sdk.aa$a r2 = new com.adcolony.sdk.aa$a
            r2.<init>()
            java.lang.String r3 = "CREATING VM "
            com.adcolony.sdk.aa$a r2 = r2.a((java.lang.String) r3)
            com.adcolony.sdk.aa$a r2 = r2.a((java.lang.String) r8)
            com.adcolony.sdk.aa r3 = com.adcolony.sdk.aa.b
            r2.a((com.adcolony.sdk.aa) r3)
            if (r7 != r1) goto L_0x0058
            boolean r2 = a     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            if (r2 == 0) goto L_0x0058
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.io.InputStream r6 = r6.open(r8)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            goto L_0x0067
        L_0x0055:
            r6 = move-exception
            goto L_0x011b
        L_0x0058:
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r6.<init>(r8)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r2.<init>(r6)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r6 = r2
        L_0x0067:
            int r2 = r6.available()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            byte[] r3 = new byte[r2]     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r6.read(r3, r0, r2)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r6.close()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            boolean r6 = r10.h()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r2 = 0
            if (r6 == 0) goto L_0x007b
            r3 = r2
        L_0x007b:
            if (r3 == 0) goto L_0x0133
            java.lang.String r6 = ""
            if (r9 == 0) goto L_0x0085
            java.lang.String r6 = r9.toString()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
        L_0x0085:
            java.lang.String r9 = "UTF-8"
            byte[] r6 = r6.getBytes(r9)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            if (r7 != r1) goto L_0x00c6
            boolean r9 = a     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            if (r9 != 0) goto L_0x00c6
            org.json.JSONObject r9 = r10.c()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.lang.String r4 = "item"
            int r9 = com.adcolony.sdk.y.a((org.json.JSONObject) r9, (java.lang.String) r4, (int) r0)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            int r9 = r9 % 2
            if (r9 != r1) goto L_0x00c6
            byte[] r9 = com.adcolony.sdk.ADCGeneratedCrypto.decrypt(r3)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            if (r9 == 0) goto L_0x00a8
            int r0 = r9.length     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            if (r0 > 0) goto L_0x00c7
        L_0x00a8:
            com.adcolony.sdk.aa$a r9 = new com.adcolony.sdk.aa$a     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r9.<init>()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.lang.String r0 = "Can't instantiate controller VM. Deleting "
            com.adcolony.sdk.aa$a r9 = r9.a((java.lang.String) r0)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.lang.String r0 = "controller, launch response, and disabling AdColony."
            com.adcolony.sdk.aa$a r9 = r9.a((java.lang.String) r0)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            com.adcolony.sdk.aa r0 = com.adcolony.sdk.aa.h     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r9.a((com.adcolony.sdk.aa) r0)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            com.adcolony.sdk.aw.a()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r10.a((boolean) r1)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r9 = r2
            goto L_0x00c7
        L_0x00c6:
            r9 = r3
        L_0x00c7:
            if (r9 == 0) goto L_0x00d1
            com.adcolony.sdk.ADCVMModule$ADCJSVirtualMachine r0 = new com.adcolony.sdk.ADCVMModule$ADCJSVirtualMachine     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r0.<init>(r7, r9, r6)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r5.h = r0     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            goto L_0x0133
        L_0x00d1:
            com.adcolony.sdk.aa$a r6 = new com.adcolony.sdk.aa$a     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r6.<init>()     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            java.lang.String r7 = "Couldn't create virtual machine for: "
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r7)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r8)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            com.adcolony.sdk.aa r7 = com.adcolony.sdk.aa.b     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            r6.a((com.adcolony.sdk.aa) r7)     // Catch:{ IOException -> 0x0055, Exception -> 0x00e6 }
            goto L_0x0133
        L_0x00e6:
            com.adcolony.sdk.aa$a r6 = new com.adcolony.sdk.aa$a
            r6.<init>()
            java.lang.String r7 = "Unable to create virtual machine for: "
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r7)
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r8)
            com.adcolony.sdk.aa r7 = com.adcolony.sdk.aa.h
            r6.a((com.adcolony.sdk.aa) r7)
            boolean r6 = r5.d
            if (r6 == 0) goto L_0x0133
            com.adcolony.sdk.aa$a r6 = new com.adcolony.sdk.aa$a
            r6.<init>()
            java.lang.String r7 = "Can't instantiate controller VM. Deleting controller, launch "
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r7)
            java.lang.String r7 = "response, and disabling AdColony."
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r7)
            com.adcolony.sdk.aa r7 = com.adcolony.sdk.aa.h
            r6.a((com.adcolony.sdk.aa) r7)
            com.adcolony.sdk.aw.a()
            r10.a((boolean) r1)
            goto L_0x0133
        L_0x011b:
            com.adcolony.sdk.aa$a r7 = new com.adcolony.sdk.aa$a
            r7.<init>()
            java.lang.String r8 = "IOException while loading controller JS: "
            com.adcolony.sdk.aa$a r7 = r7.a((java.lang.String) r8)
            java.lang.String r6 = r6.toString()
            com.adcolony.sdk.aa$a r6 = r7.a((java.lang.String) r6)
            com.adcolony.sdk.aa r7 = com.adcolony.sdk.aa.h
            r6.a((com.adcolony.sdk.aa) r7)
        L_0x0133:
            com.adcolony.sdk.aa$a r6 = new com.adcolony.sdk.aa$a
            r6.<init>()
            java.lang.String r7 = "----------------------------------------------------------------------"
            com.adcolony.sdk.aa$a r6 = r6.a((java.lang.String) r7)
            com.adcolony.sdk.aa r7 = com.adcolony.sdk.aa.b
            r6.a((com.adcolony.sdk.aa) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.ADCVMModule.<init>(android.app.Activity, int, java.lang.String, org.json.JSONObject, java.util.concurrent.ExecutorService):void");
    }

    public int a() {
        return this.b;
    }

    public void a(JSONObject jSONObject) {
        synchronized (this.e) {
            this.e.put(jSONObject);
        }
    }

    public void b() {
        if (this.g == null) {
            this.g = new Runnable() {
                public void run() {
                    if (!a.a().h()) {
                        ADCVMModule.this.h.a();
                    }
                }
            };
        }
        this.c.submit(this.g);
        this.c.shutdown();
    }

    public void c() {
        if (this.f == null) {
            this.f = new Runnable() {
                public void run() {
                    JSONArray b;
                    String str = "";
                    synchronized (ADCVMModule.this.e) {
                        if (ADCVMModule.this.e.length() > 0) {
                            str = ADCVMModule.this.e.toString();
                            JSONArray unused = ADCVMModule.this.e = y.b();
                        }
                    }
                    String str2 = null;
                    if (!a.a().h()) {
                        try {
                            str2 = new String(ADCVMModule.this.h.a(str.getBytes("UTF-8")), "UTF-8");
                        } catch (Exception e) {
                            new aa.a().a("VM update failed: ").a(aw.a(e)).a(aa.h);
                            a.a().q().a(ADCVMModule.this.b);
                        }
                    }
                    if (str2 != null && str2.length() > 2 && (b = y.b(str2)) != null) {
                        for (int i = 0; i < b.length(); i++) {
                            JSONObject a2 = y.a(b, i);
                            if (a2 != null) {
                                a.a().q().a(a2);
                            }
                        }
                    }
                }
            };
        }
        this.c.submit(this.f);
    }

    public long d() {
        return this.h.b;
    }

    /* access modifiers changed from: package-private */
    public ExecutorService e() {
        return this.c;
    }

    private class ADCJSVirtualMachine {
        /* access modifiers changed from: private */
        public long b;
        private int c;

        private native long create(int i, byte[] bArr, byte[] bArr2);

        private native void delete(long j, int i);

        private native byte[] update(long j, int i, byte[] bArr);

        class a extends Exception {
            a(String str) {
                super(str);
            }
        }

        ADCJSVirtualMachine(int i, byte[] bArr, byte[] bArr2) throws a {
            this.b = create(i, bArr, bArr2);
            if (this.b >= 0) {
                this.c = i;
                return;
            }
            throw new a("Virtual machine could not be created.");
        }

        /* access modifiers changed from: package-private */
        public void a() {
            delete(this.b, this.c);
        }

        /* access modifiers changed from: package-private */
        public byte[] a(byte[] bArr) throws a {
            byte[] update = update(this.b, this.c, bArr);
            if (update != null) {
                return update;
            }
            final Activity c2 = a.c();
            if (c2 != null && (c2 instanceof b)) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        af afVar = new af("AdSession.finish_fullscreen_ad", 0);
                        y.b(afVar.c(), NotificationCompat.CATEGORY_STATUS, 1);
                        ((b) c2).a(afVar);
                    }
                });
            }
            if (ADCVMModule.this.d) {
                try {
                    new File(a.a().o().g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5").delete();
                } catch (Exception unused) {
                }
            }
            throw new a("Virtual machine error.");
        }
    }
}
