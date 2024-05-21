package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ag {
    private ArrayList<ai> a = new ArrayList<>();
    private HashMap<Integer, ai> b = new HashMap<>();
    private int c = 2;
    private HashMap<String, ArrayList<ah>> d = new HashMap<>();
    private JSONArray e = y.b();
    private int f = 1;

    ag() {
    }

    /* access modifiers changed from: package-private */
    public void a(String str, ah ahVar) {
        ArrayList arrayList = this.d.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.d.put(str, arrayList);
        }
        arrayList.add(ahVar);
    }

    /* access modifiers changed from: package-private */
    public void b(String str, ah ahVar) {
        synchronized (this.d) {
            ArrayList arrayList = this.d.get(str);
            if (arrayList != null) {
                arrayList.remove(ahVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = com.adcolony.sdk.a.c();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r3 = this;
            com.adcolony.sdk.l r0 = com.adcolony.sdk.a.a()
            boolean r1 = r0.g()
            if (r1 != 0) goto L_0x0024
            boolean r0 = r0.h()
            if (r0 != 0) goto L_0x0024
            android.app.Activity r0 = com.adcolony.sdk.a.c()
            if (r0 != 0) goto L_0x0017
            goto L_0x0024
        L_0x0017:
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()
            com.adcolony.sdk.ag$1 r2 = new com.adcolony.sdk.ag$1
            r2.<init>(r0, r1)
            r1.submit(r2)
            return
        L_0x0024:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.ag.a():void");
    }

    /* access modifiers changed from: package-private */
    public ai a(ai aiVar) {
        synchronized (this.a) {
            this.a.add(aiVar);
            this.b.put(Integer.valueOf(aiVar.a()), aiVar);
        }
        return aiVar;
    }

    /* access modifiers changed from: package-private */
    public ai a(int i) {
        synchronized (this.a) {
            ai aiVar = this.b.get(Integer.valueOf(i));
            if (aiVar == null) {
                return null;
            }
            this.a.remove(aiVar);
            this.b.remove(Integer.valueOf(i));
            aiVar.b();
            return aiVar;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        synchronized (this.a) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                this.a.get(size).c();
            }
        }
        JSONArray jSONArray = null;
        if (this.e.length() > 0) {
            jSONArray = this.e;
            this.e = y.b();
        }
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    final JSONObject jSONObject = jSONArray.getJSONObject(i);
                    final String string = jSONObject.getString("m_type");
                    if (jSONObject.getInt("m_origin") >= 2) {
                        aw.a((Runnable) new Runnable() {
                            public void run() {
                                ag.this.a(string, jSONObject);
                            }
                        });
                    } else {
                        a(string, jSONObject);
                    }
                } catch (JSONException e2) {
                    new aa.a().a("JSON error from message dispatcher's updateModules(): ").a(e2.toString()).a(aa.h);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, JSONObject jSONObject) {
        synchronized (this.d) {
            ArrayList arrayList = this.d.get(str);
            if (arrayList != null) {
                af afVar = new af(jSONObject);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    try {
                        ((ah) arrayList.get(i)).a(afVar);
                        i++;
                    } catch (RuntimeException e2) {
                        new aa.a().a((Object) e2).a(aa.h);
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("m_id")) {
                int i = this.f;
                this.f = i + 1;
                jSONObject.put("m_id", i);
            }
            if (!jSONObject.has("m_origin")) {
                jSONObject.put("m_origin", 0);
            }
            int i2 = jSONObject.getInt("m_target");
            if (i2 == 0) {
                synchronized (this) {
                    this.e.put(jSONObject);
                }
                return;
            }
            ai aiVar = this.b.get(Integer.valueOf(i2));
            if (aiVar != null) {
                aiVar.a(jSONObject);
            }
        } catch (JSONException e2) {
            new aa.a().a("JSON error in ADCMessageDispatcher's sendMessage(): ").a(e2.toString()).a(aa.h);
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<ai> c() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        int i = this.c;
        this.c = i + 1;
        return i;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, ai> e() {
        return this.b;
    }
}
