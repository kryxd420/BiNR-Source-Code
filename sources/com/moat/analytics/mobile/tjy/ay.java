package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.asserts.a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;

class ay implements InvocationHandler {
    /* access modifiers changed from: private */
    public static final Object[] a = new Object[0];
    private final ap b;
    private final ba c;
    private final bc d;
    private final LinkedList e = new LinkedList();
    private final LinkedList f = new LinkedList();
    /* access modifiers changed from: private */
    public boolean g;
    private Object h;

    ay(ap apVar, ba baVar, bc bcVar) {
        a.a(apVar);
        a.a(baVar);
        a.a(bcVar);
        this.b = apVar;
        this.c = baVar;
        this.d = bcVar;
        apVar.a(new az(this));
    }

    static Object a(ap apVar, ba baVar, bc bcVar) {
        Class a2 = bcVar.a();
        ClassLoader classLoader = a2.getClassLoader();
        ay ayVar = new ay(apVar, baVar, bcVar);
        return Proxy.newProxyInstance(classLoader, new Class[]{a2}, ayVar);
    }

    private Object a(Method method) {
        try {
            return Boolean.TYPE.equals(method.getReturnType()) ? true : null;
        } catch (Exception e2) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(e2);
            return null;
        }
    }

    private Object a(Method method, Object[] objArr) {
        if (Object.class.equals(method.getDeclaringClass())) {
            String name = method.getName();
            Class a2 = this.d.a();
            if ("getClass".equals(name)) {
                return a2;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object invoke = method.invoke(this, objArr);
            return String.valueOf(invoke).replace(ay.class.getName(), a2.getName());
        } else if (!this.g || this.h != null) {
            if (this.b.a() == ar.ON) {
                b();
                if (this.h != null) {
                    return method.invoke(this.h, objArr);
                }
            }
            if (this.b.a() == ar.OFF && (!this.g || this.h != null)) {
                b(method, objArr);
            }
            return a(method);
        } else {
            c();
            return a(method);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!this.g) {
            try {
                this.h = this.c.a().c((Object) null);
            } catch (Exception unused) {
            }
            this.g = true;
        }
        if (this.h != null) {
            LinkedList<LinkedList> linkedList = new LinkedList<>();
            linkedList.add(this.e);
            linkedList.add(this.f);
            for (LinkedList linkedList2 : linkedList) {
                Iterator it = linkedList2.iterator();
                while (it.hasNext()) {
                    bb bbVar = (bb) it.next();
                    try {
                        Object[] objArr = new Object[bbVar.b.length];
                        WeakReference[] a2 = bbVar.b;
                        int length = a2.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            objArr[i2] = a2[i].get();
                            i++;
                            i2++;
                        }
                        bbVar.d.invoke(this.h, objArr);
                    } catch (Exception unused2) {
                    }
                }
                linkedList2.clear();
            }
        }
    }

    private void b(Method method, Object[] objArr) {
        LinkedList linkedList;
        bb bbVar;
        if (this.e.size() < 5) {
            linkedList = this.e;
            bbVar = new bb(this, method, objArr, (az) null);
        } else {
            if (this.f.size() >= 10) {
                this.f.removeFirst();
            }
            linkedList = this.f;
            bbVar = new bb(this, method, objArr, (az) null);
        }
        linkedList.add(bbVar);
    }

    private void c() {
        this.e.clear();
        this.f.clear();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return a(method, objArr);
        } catch (Exception e2) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(e2);
            return a(method);
        }
    }
}
