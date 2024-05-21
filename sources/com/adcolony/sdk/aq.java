package com.adcolony.sdk;

import android.media.SoundPool;
import com.adcolony.sdk.aa;
import java.util.HashMap;
import org.json.JSONObject;

class aq {
    final String a;
    private final int b;
    private HashMap<Integer, Integer> c = new HashMap<>();
    private HashMap<Integer, Integer> d = new HashMap<>();
    private HashMap<Integer, Boolean> e = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<Integer, Integer> f = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<Integer, Integer> g = new HashMap<>();
    private HashMap<String, Integer> h = new HashMap<>();
    private SoundPool i;

    aq(final String str, final int i2) {
        this.a = str;
        this.b = i2;
        this.i = new SoundPool(50, 3, 0);
        this.i.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                JSONObject a2 = y.a();
                y.b(a2, "id", ((Integer) aq.this.f.get(Integer.valueOf(i))).intValue());
                y.a(a2, "ad_session_id", str);
                if (i2 == 0) {
                    new af("AudioPlayer.on_ready", i2, a2).b();
                    aq.this.g.put(aq.this.f.get(Integer.valueOf(i)), Integer.valueOf(i));
                    return;
                }
                new af("AudioPlayer.on_error", i2, a2).b();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        JSONObject c2 = afVar.c();
        int load = this.i.load(y.b(c2, "filepath"), 1);
        int i2 = y.d(c2, "repeats") ? -1 : 0;
        this.f.put(Integer.valueOf(load), Integer.valueOf(y.c(c2, "id")));
        new aa.a().a("Load audio with id = ").a(load).a(aa.d);
        this.d.put(Integer.valueOf(load), Integer.valueOf(i2));
        this.e.put(Integer.valueOf(load), false);
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        this.i.unload(this.g.get(Integer.valueOf(y.c(afVar.c(), "id"))).intValue());
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        int intValue = this.g.get(Integer.valueOf(y.c(afVar.c(), "id"))).intValue();
        if (!this.e.get(Integer.valueOf(intValue)).booleanValue()) {
            int play = this.i.play(intValue, 1.0f, 1.0f, 0, this.d.get(Integer.valueOf(intValue)).intValue(), 1.0f);
            if (play != 0) {
                this.c.put(Integer.valueOf(intValue), Integer.valueOf(play));
                return;
            }
            JSONObject a2 = y.a();
            y.b(a2, "id", y.c(afVar.c(), "id"));
            y.a(a2, "ad_session_id", this.a);
            new af("AudioPlayer.on_error", this.b, a2).b();
            return;
        }
        this.i.resume(this.c.get(Integer.valueOf(intValue)).intValue());
    }

    /* access modifiers changed from: package-private */
    public void d(af afVar) {
        int intValue = this.g.get(Integer.valueOf(y.c(afVar.c(), "id"))).intValue();
        this.i.pause(this.c.get(Integer.valueOf(intValue)).intValue());
        this.e.put(Integer.valueOf(intValue), true);
    }

    /* access modifiers changed from: package-private */
    public void e(af afVar) {
        this.i.stop(this.c.get(this.g.get(Integer.valueOf(y.c(afVar.c(), "id")))).intValue());
    }

    /* access modifiers changed from: package-private */
    public SoundPool a() {
        return this.i;
    }
}
