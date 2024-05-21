package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import com.tapjoy.TJAdUnitConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

class t {
    private LinkedList<Runnable> a = new LinkedList<>();
    private boolean b;

    t() {
    }

    /* access modifiers changed from: private */
    public boolean f(af afVar) {
        JSONObject c = afVar.c();
        String b2 = y.b(c, "filepath");
        a.a().o().b();
        JSONObject a2 = y.a();
        try {
            int c2 = y.c(c, "offset");
            int c3 = y.c(c, "size");
            boolean d = y.d(c, "gunzip");
            String b3 = y.b(c, "output_filepath");
            as asVar = new as(new FileInputStream(b2), c2, c3);
            InputStream gZIPInputStream = d ? new GZIPInputStream(asVar, 1024) : asVar;
            if (b3.equals("")) {
                StringBuilder sb = new StringBuilder(gZIPInputStream.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = gZIPInputStream.read(bArr, 0, 1024);
                    if (read < 0) {
                        break;
                    }
                    sb.append(new String(bArr, 0, read, "ISO-8859-1"));
                }
                y.b(a2, "size", sb.length());
                y.a(a2, TJAdUnitConstants.String.DATA, sb.toString());
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(b3);
                byte[] bArr2 = new byte[1024];
                int i = 0;
                while (true) {
                    int read2 = gZIPInputStream.read(bArr2, 0, 1024);
                    if (read2 < 0) {
                        break;
                    }
                    fileOutputStream.write(bArr2, 0, read2);
                    i += read2;
                }
                fileOutputStream.close();
                y.b(a2, "size", i);
            }
            gZIPInputStream.close();
            y.a(a2, "success", true);
            afVar.a(a2).b();
            return true;
        } catch (IOException unused) {
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        } catch (OutOfMemoryError unused2) {
            new aa.a().a("Out of memory error - disabling AdColony.").a(aa.g);
            a.a().a(true);
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00f8, code lost:
        new com.adcolony.sdk.aa.a().a("Out of memory error - disabling AdColony.").a(com.adcolony.sdk.aa.g);
        com.adcolony.sdk.a.a().a(true);
        com.adcolony.sdk.y.a(r4, "success", false);
        r0.a(r4).b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x011d, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[ExcHandler: OutOfMemoryError (unused java.lang.OutOfMemoryError), SYNTHETIC, Splitter:B:1:0x0027] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean g(com.adcolony.sdk.af r20) {
        /*
            r19 = this;
            r0 = r20
            org.json.JSONObject r1 = r20.c()
            java.lang.String r2 = "filepath"
            java.lang.String r2 = com.adcolony.sdk.y.b((org.json.JSONObject) r1, (java.lang.String) r2)
            java.lang.String r3 = "bundle_path"
            java.lang.String r3 = com.adcolony.sdk.y.b((org.json.JSONObject) r1, (java.lang.String) r3)
            java.lang.String r4 = "bundle_filenames"
            org.json.JSONArray r1 = com.adcolony.sdk.y.g(r1, r4)
            com.adcolony.sdk.l r4 = com.adcolony.sdk.a.a()
            com.adcolony.sdk.ar r4 = r4.o()
            r4.b()
            org.json.JSONObject r4 = com.adcolony.sdk.y.a()
            java.io.File r7 = new java.io.File     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r9 = "r"
            r8.<init>(r7, r9)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r9 = 4
            byte[] r9 = new byte[r9]     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r9 = 32
            byte[] r9 = new byte[r9]     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r8.readInt()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            int r10 = r8.readInt()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            org.json.JSONArray r11 = new org.json.JSONArray     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r11.<init>()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r12 = 1024(0x400, float:1.435E-42)
            byte[] r13 = new byte[r12]     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r14 = 0
        L_0x004b:
            if (r14 >= r10) goto L_0x00df
            r15 = 8
            int r16 = r14 * 44
            int r15 = r15 + r16
            long r5 = (long) r15     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r8.seek(r5)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r8.read(r9)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r5 = new java.lang.String     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r5.<init>(r9)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r8.readInt()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            int r5 = r8.readInt()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            int r6 = r8.readInt()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r11.put(r6)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00b3 }
            r15.<init>()     // Catch:{ JSONException -> 0x00b3 }
            r15.append(r2)     // Catch:{ JSONException -> 0x00b3 }
            java.lang.Object r12 = r1.get(r14)     // Catch:{ JSONException -> 0x00b3 }
            r15.append(r12)     // Catch:{ JSONException -> 0x00b3 }
            java.lang.String r12 = r15.toString()     // Catch:{ JSONException -> 0x00b3 }
            r18 = r1
            r17 = r2
            long r1 = (long) r5
            r8.seek(r1)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.<init>(r12)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            int r2 = r6 / 1024
            int r6 = r6 % 1024
            r5 = 0
        L_0x0092:
            if (r5 >= r2) goto L_0x00a0
            r12 = 1024(0x400, float:1.435E-42)
            r15 = 0
            r8.read(r13, r15, r12)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.write(r13, r15, r12)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            int r5 = r5 + 1
            goto L_0x0092
        L_0x00a0:
            r12 = 1024(0x400, float:1.435E-42)
            r15 = 0
            r8.read(r13, r15, r6)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.write(r13, r15, r6)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.close()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            int r14 = r14 + 1
            r2 = r17
            r1 = r18
            goto L_0x004b
        L_0x00b3:
            com.adcolony.sdk.aa$a r1 = new com.adcolony.sdk.aa$a     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.<init>()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r2 = "Could extract file name at index "
            com.adcolony.sdk.aa$a r1 = r1.a((java.lang.String) r2)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            com.adcolony.sdk.aa$a r1 = r1.a((int) r14)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r2 = " unpacking ad unit bundle at "
            com.adcolony.sdk.aa$a r1 = r1.a((java.lang.String) r2)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            com.adcolony.sdk.aa$a r1 = r1.a((java.lang.String) r3)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            com.adcolony.sdk.aa r2 = com.adcolony.sdk.aa.g     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.a((com.adcolony.sdk.aa) r2)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r1 = "success"
            r2 = 0
            com.adcolony.sdk.y.a((org.json.JSONObject) r4, (java.lang.String) r1, (boolean) r2)     // Catch:{ IOException -> 0x011f, OutOfMemoryError -> 0x00f8 }
            com.adcolony.sdk.af r1 = r0.a((org.json.JSONObject) r4)     // Catch:{ IOException -> 0x011f, OutOfMemoryError -> 0x00f8 }
            r1.b()     // Catch:{ IOException -> 0x011f, OutOfMemoryError -> 0x00f8 }
            return r2
        L_0x00df:
            r8.close()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r7.delete()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r1 = "success"
            r2 = 1
            com.adcolony.sdk.y.a((org.json.JSONObject) r4, (java.lang.String) r1, (boolean) r2)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            java.lang.String r1 = "file_sizes"
            com.adcolony.sdk.y.a((org.json.JSONObject) r4, (java.lang.String) r1, (org.json.JSONArray) r11)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            com.adcolony.sdk.af r1 = r0.a((org.json.JSONObject) r4)     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            r1.b()     // Catch:{ IOException -> 0x011e, OutOfMemoryError -> 0x00f8 }
            return r2
        L_0x00f8:
            com.adcolony.sdk.aa$a r1 = new com.adcolony.sdk.aa$a
            r1.<init>()
            java.lang.String r2 = "Out of memory error - disabling AdColony."
            com.adcolony.sdk.aa$a r1 = r1.a((java.lang.String) r2)
            com.adcolony.sdk.aa r2 = com.adcolony.sdk.aa.g
            r1.a((com.adcolony.sdk.aa) r2)
            com.adcolony.sdk.l r1 = com.adcolony.sdk.a.a()
            r2 = 1
            r1.a((boolean) r2)
            java.lang.String r1 = "success"
            r2 = 0
            com.adcolony.sdk.y.a((org.json.JSONObject) r4, (java.lang.String) r1, (boolean) r2)
            com.adcolony.sdk.af r0 = r0.a((org.json.JSONObject) r4)
            r0.b()
            return r2
        L_0x011e:
            r2 = 0
        L_0x011f:
            com.adcolony.sdk.aa$a r1 = new com.adcolony.sdk.aa$a
            r1.<init>()
            java.lang.String r5 = "Failed to find or open ad unit bundle at path: "
            com.adcolony.sdk.aa$a r1 = r1.a((java.lang.String) r5)
            com.adcolony.sdk.aa$a r1 = r1.a((java.lang.String) r3)
            com.adcolony.sdk.aa r3 = com.adcolony.sdk.aa.h
            r1.a((com.adcolony.sdk.aa) r3)
            java.lang.String r1 = "success"
            com.adcolony.sdk.y.a((org.json.JSONObject) r4, (java.lang.String) r1, (boolean) r2)
            com.adcolony.sdk.af r0 = r0.a((org.json.JSONObject) r4)
            r0.b()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.t.g(com.adcolony.sdk.af):boolean");
    }

    /* access modifiers changed from: package-private */
    public void a() {
        a.a("FileSystem.save", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        t.this.a(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.delete", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        t.this.a(afVar, new File(y.b(afVar.c(), "filepath")));
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.listing", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        t.this.b(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.load", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        t.this.c(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.rename", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        t.this.d(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.exists", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        t.this.e(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.extract", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        boolean unused = t.this.f(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.unpack_bundle", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        boolean unused = t.this.g(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.create_directory", (ah) new ah() {
            public void a(final af afVar) {
                t.this.a((Runnable) new Runnable() {
                    public void run() {
                        boolean unused = t.this.h(afVar);
                        t.this.b();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        JSONObject c = afVar.c();
        String b2 = y.b(c, "filepath");
        String b3 = y.b(c, TJAdUnitConstants.String.DATA);
        String b4 = y.b(c, "encoding");
        boolean z = b4 != null && b4.equals("utf8");
        a.a().o().b();
        JSONObject a2 = y.a();
        try {
            a(b2, b3, z);
            y.a(a2, "success", true);
            afVar.a(a2).b();
            return true;
        } catch (IOException unused) {
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, boolean z) throws IOException {
        BufferedWriter bufferedWriter = z ? new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str), "UTF-8")) : new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str)));
        bufferedWriter.write(str2);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar, File file) {
        a.a().o().b();
        JSONObject a2 = y.a();
        if (a(file)) {
            y.a(a2, "success", true);
            afVar.a(a2).b();
            return true;
        }
        y.a(a2, "success", false);
        afVar.a(a2).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean a(File file) {
        try {
            if (!file.isDirectory()) {
                return file.delete();
            }
            if (file.list().length == 0) {
                return file.delete();
            }
            String[] list = file.list();
            if (list.length > 0) {
                return a(new File(file, list[0]));
            }
            if (file.list().length == 0) {
                return file.delete();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(af afVar) {
        String b2 = y.b(afVar.c(), "filepath");
        a.a().o().b();
        JSONObject a2 = y.a();
        String[] list = new File(b2).list();
        if (list != null) {
            JSONArray b3 = y.b();
            for (String str : list) {
                JSONObject a3 = y.a();
                y.a(a3, "filename", str);
                if (new File(b2 + str).isDirectory()) {
                    y.a(a3, "is_folder", true);
                } else {
                    y.a(a3, "is_folder", false);
                }
                y.a(b3, (Object) a3);
            }
            y.a(a2, "success", true);
            y.a(a2, "entries", b3);
            afVar.a(a2).b();
            return true;
        }
        y.a(a2, "success", false);
        afVar.a(a2).b();
        return false;
    }

    /* access modifiers changed from: package-private */
    public String c(af afVar) {
        JSONObject c = afVar.c();
        String b2 = y.b(c, "filepath");
        String b3 = y.b(c, "encoding");
        boolean z = b3 != null && b3.equals("utf8");
        a.a().o().b();
        JSONObject a2 = y.a();
        try {
            StringBuilder a3 = a(b2, z);
            y.a(a2, "success", true);
            y.a(a2, TJAdUnitConstants.String.DATA, a3.toString());
            afVar.a(a2).b();
            return a3.toString();
        } catch (IOException unused) {
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public StringBuilder a(String str, boolean z) throws IOException {
        BufferedReader bufferedReader;
        File file = new File(str);
        StringBuilder sb = new StringBuilder((int) file.length());
        if (z) {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
                sb.append("\n");
            } else {
                bufferedReader.close();
                return sb;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<String> b(String str, boolean z) throws IOException {
        BufferedReader bufferedReader;
        File file = new File(str);
        file.length();
        ArrayList arrayList = new ArrayList();
        if (z) {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                arrayList.add(readLine);
            } else {
                bufferedReader.close();
                return arrayList;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean d(af afVar) {
        JSONObject c = afVar.c();
        String b2 = y.b(c, "filepath");
        String b3 = y.b(c, "new_filepath");
        a.a().o().b();
        JSONObject a2 = y.a();
        try {
            if (new File(b2).renameTo(new File(b3))) {
                y.a(a2, "success", true);
                afVar.a(a2).b();
                return true;
            }
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        } catch (Exception unused) {
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean e(af afVar) {
        String b2 = y.b(afVar.c(), "filepath");
        a.a().o().b();
        JSONObject a2 = y.a();
        try {
            boolean a3 = a(b2);
            y.a(a2, "result", a3);
            y.a(a2, "success", true);
            afVar.a(a2).b();
            return a3;
        } catch (Exception e) {
            y.a(a2, "result", false);
            y.a(a2, "success", false);
            afVar.a(a2).b();
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) throws Exception {
        return new File(str).exists();
    }

    /* access modifiers changed from: private */
    public boolean h(af afVar) {
        String b2 = y.b(afVar.c(), "filepath");
        a.a().o().b();
        JSONObject a2 = y.a();
        try {
            if (new File(b2).mkdir()) {
                y.a(a2, "success", true);
                afVar.a(a2).b();
                return true;
            }
            y.a(a2, "success", false);
            return false;
        } catch (Exception unused) {
            y.a(a2, "success", false);
            afVar.a(a2).b();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Runnable runnable) {
        if (!this.a.isEmpty() || this.b) {
            this.a.push(runnable);
            return;
        }
        this.b = true;
        runnable.run();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.b = false;
        if (!this.a.isEmpty()) {
            this.b = true;
            this.a.removeLast().run();
        }
    }
}
