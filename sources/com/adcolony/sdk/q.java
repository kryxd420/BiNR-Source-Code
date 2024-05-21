package com.adcolony.sdk;

import android.support.v4.app.NotificationCompat;
import com.adcolony.sdk.aa;
import com.adcolony.sdk.p;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

class q implements p.a {
    private BlockingQueue<Runnable> a = new LinkedBlockingQueue();
    private ThreadPoolExecutor b = new ThreadPoolExecutor(4, 16, 60, TimeUnit.SECONDS, this.a);

    q() {
    }

    /* access modifiers changed from: package-private */
    public void a() {
        a.a("WebServices.download", (ah) new ah() {
            public void a(af afVar) {
                q.this.a(new p(afVar, q.this));
            }
        });
        a.a("WebServices.get", (ah) new ah() {
            public void a(af afVar) {
                q.this.a(new p(afVar, q.this));
            }
        });
        a.a("WebServices.post", (ah) new ah() {
            public void a(af afVar) {
                q.this.a(new p(afVar, q.this));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(p pVar) {
        try {
            this.b.execute(pVar);
        } catch (RejectedExecutionException unused) {
            aa.a a2 = new aa.a().a("RejectedExecutionException: ThreadPoolExecutor unable to execute ");
            a2.a("download for url " + pVar.a).a(aa.h);
            a(pVar, pVar.a(), (Map<String, List<String>>) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.b.setCorePoolSize(i);
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.b.getCorePoolSize();
    }

    public void a(p pVar, af afVar, Map<String, List<String>> map) {
        JSONObject a2 = y.a();
        y.a(a2, "url", pVar.a);
        y.a(a2, "success", pVar.c);
        y.b(a2, NotificationCompat.CATEGORY_STATUS, pVar.e);
        y.a(a2, "body", pVar.b);
        y.b(a2, "size", pVar.d);
        if (map != null) {
            JSONObject a3 = y.a();
            for (Map.Entry next : map.entrySet()) {
                String obj = ((List) next.getValue()).toString();
                String substring = obj.substring(1, obj.length() - 1);
                if (next.getKey() != null) {
                    y.a(a3, (String) next.getKey(), substring);
                }
            }
            y.a(a2, "headers", a3);
        }
        afVar.a(a2).b();
    }
}
