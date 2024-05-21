package com.applovin.impl.mediation;

import android.text.TextUtils;
import com.applovin.impl.mediation.a.e;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapters.MediationAdapterBase;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class g {
    private final j a;
    private final p b;
    private final Object c = new Object();
    private final Map<String, Class<? extends MaxAdapter>> d = new HashMap();
    private final Set<String> e = new HashSet();

    g(j jVar) {
        if (jVar != null) {
            this.a = jVar;
            this.b = jVar.v();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private h a(e eVar, Class<? extends MaxAdapter> cls) {
        try {
            h hVar = new h(eVar, (MediationAdapterBase) cls.getConstructor(new Class[]{AppLovinSdk.class}).newInstance(new Object[]{this.a.L()}), this.a);
            if (hVar.c()) {
                return hVar;
            }
            p pVar = this.b;
            pVar.e("MediationAdapterManager", "Adapter is disabled after initialization: " + eVar);
            return null;
        } catch (Throwable th) {
            p pVar2 = this.b;
            pVar2.c("MediationAdapterManager", "Failed to load adapter: " + eVar, th);
            return null;
        }
    }

    private Class<? extends MaxAdapter> a(String str) {
        p pVar;
        String str2;
        String str3;
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null) {
                pVar = this.b;
                str2 = "MediationAdapterManager";
                str3 = "No class found for " + str;
            } else if (MaxAdapter.class.isAssignableFrom(cls)) {
                return cls.asSubclass(MaxAdapter.class);
            } else {
                pVar = this.b;
                str2 = "MediationAdapterManager";
                str3 = str + " error: not an instance of '" + MaxAdapter.class.getName() + "'.";
            }
            pVar.e(str2, str3);
            return null;
        } catch (Throwable th) {
            this.b.c("MediationAdapterManager", "Failed to load: " + str, th);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public h a(e eVar) {
        Class<? extends MaxAdapter> cls;
        if (eVar != null) {
            String u = eVar.u();
            String t = eVar.t();
            if (TextUtils.isEmpty(u)) {
                p pVar = this.b;
                pVar.d("MediationAdapterManager", "No adapter name provided for " + t + ", not loading the adapter ");
                return null;
            } else if (TextUtils.isEmpty(t)) {
                p pVar2 = this.b;
                pVar2.d("MediationAdapterManager", "Unable to find default classname for '" + u + "'");
                return null;
            } else {
                synchronized (this.c) {
                    if (!this.e.contains(t)) {
                        if (this.d.containsKey(t)) {
                            cls = this.d.get(t);
                        } else {
                            cls = a(t);
                            if (cls == null) {
                                this.e.add(t);
                                p pVar3 = this.b;
                                pVar3.e("MediationAdapterManager", "Failed to load adapter classname: " + t);
                                return null;
                            }
                        }
                        h a2 = a(eVar, cls);
                        if (a2 != null) {
                            p pVar4 = this.b;
                            pVar4.a("MediationAdapterManager", "Loaded " + u);
                            this.d.put(t, cls);
                            return a2;
                        }
                        p pVar5 = this.b;
                        pVar5.d("MediationAdapterManager", "Failed to load " + u);
                        this.e.add(t);
                        return null;
                    }
                    p pVar6 = this.b;
                    pVar6.a("MediationAdapterManager", "Not attempting to load " + u + " due to prior errors");
                    return null;
                }
            }
        } else {
            throw new IllegalArgumentException("No adapter spec specified");
        }
    }

    /* access modifiers changed from: package-private */
    public Collection<String> a() {
        Set unmodifiableSet;
        synchronized (this.c) {
            HashSet hashSet = new HashSet(this.d.size());
            for (Class<? extends MaxAdapter> name : this.d.values()) {
                hashSet.add(name.getName());
            }
            unmodifiableSet = Collections.unmodifiableSet(hashSet);
        }
        return unmodifiableSet;
    }

    /* access modifiers changed from: package-private */
    public Collection<String> b() {
        Set<T> unmodifiableSet;
        synchronized (this.c) {
            unmodifiableSet = Collections.unmodifiableSet(this.e);
        }
        return unmodifiableSet;
    }
}
