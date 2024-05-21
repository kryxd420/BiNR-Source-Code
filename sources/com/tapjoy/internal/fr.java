package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class fr {
    public static Object a(Object obj, Class cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new a(obj, Thread.currentThread(), Looper.myLooper()));
    }

    static class a implements InvocationHandler {
        /* access modifiers changed from: private */
        public final Object a;
        private final Thread b;
        private final Looper c;

        public a(Object obj, Thread thread, Looper looper) {
            this.a = obj;
            this.b = thread;
            this.c = looper;
        }

        public final Object invoke(Object obj, final Method method, final Object[] objArr) {
            if (this.b == Thread.currentThread()) {
                return method.invoke(this.a, objArr);
            }
            if (method.getReturnType().equals(Void.TYPE)) {
                AnonymousClass1 r4 = new Runnable() {
                    public final void run() {
                        try {
                            method.invoke(a.this.a, objArr);
                        } catch (IllegalArgumentException e) {
                            throw jv.a(e);
                        } catch (IllegalAccessException e2) {
                            throw jv.a(e2);
                        } catch (InvocationTargetException e3) {
                            throw jv.a(e3);
                        }
                    }
                };
                if (this.c != null && new Handler(this.c).post(r4)) {
                    return null;
                }
                if (this.b == gv.b() && gv.a.a(r4)) {
                    return null;
                }
                Looper mainLooper = Looper.getMainLooper();
                if (mainLooper == null || !new Handler(mainLooper).post(r4)) {
                    return method.invoke(this.a, objArr);
                }
                return null;
            }
            throw new UnsupportedOperationException("method not return void: " + method.getName());
        }
    }
}
