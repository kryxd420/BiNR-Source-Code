package com.adcolony.sdk;

import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

class ae {
    final String a;
    /* access modifiers changed from: private */
    public final int b;
    private HashMap<Integer, MediaPlayer> c = new HashMap<>();
    private HashMap<Integer, a> d = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<Integer, Boolean> e = new HashMap<>();
    private HashMap<Integer, Boolean> f = new HashMap<>();
    private ArrayList<MediaPlayer> g = new ArrayList<>();

    ae(String str, int i) {
        this.a = str;
        this.b = i;
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        JSONObject c2 = afVar.c();
        int c3 = y.c(c2, "id");
        a aVar = new a(c3, y.d(c2, "repeats"));
        this.c.put(Integer.valueOf(c3), mediaPlayer);
        this.d.put(Integer.valueOf(c3), aVar);
        this.e.put(Integer.valueOf(c3), false);
        this.f.put(Integer.valueOf(c3), false);
        mediaPlayer.setOnErrorListener(aVar);
        mediaPlayer.setOnPreparedListener(aVar);
        try {
            mediaPlayer.setDataSource(y.b(c2, "filepath"));
        } catch (Exception unused) {
            JSONObject a2 = y.a();
            y.b(a2, "id", c3);
            y.a(a2, "ad_session_id", this.a);
            new af("AudioPlayer.on_error", this.b, a2).b();
        }
        mediaPlayer.prepareAsync();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.g.clear();
        for (MediaPlayer next : this.c.values()) {
            if (next.isPlaying()) {
                next.pause();
                this.g.add(next);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        Iterator<MediaPlayer> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
        this.g.clear();
    }

    /* access modifiers changed from: package-private */
    public void b(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        if (this.f.get(Integer.valueOf(c2)).booleanValue()) {
            this.c.get(Integer.valueOf(c2)).pause();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        if (this.e.get(Integer.valueOf(c2)).booleanValue()) {
            this.c.get(Integer.valueOf(c2)).start();
            this.f.put(Integer.valueOf(c2), true);
        }
    }

    /* access modifiers changed from: package-private */
    public void d(af afVar) {
        this.c.remove(Integer.valueOf(y.c(afVar.c(), "id"))).release();
    }

    /* access modifiers changed from: package-private */
    public void e(af afVar) {
        int c2 = y.c(afVar.c(), "id");
        if (this.f.get(Integer.valueOf(c2)).booleanValue()) {
            MediaPlayer mediaPlayer = this.c.get(Integer.valueOf(c2));
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, MediaPlayer> c() {
        return this.c;
    }

    private class a implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
        int a;
        boolean b;

        a(int i, boolean z) {
            this.a = i;
            this.b = z;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            JSONObject a2 = y.a();
            y.b(a2, "id", this.a);
            y.a(a2, "ad_session_id", ae.this.a);
            new af("AudioPlayer.on_error", ae.this.b, a2).b();
            return true;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setLooping(this.b);
            ae.this.e.put(Integer.valueOf(this.a), true);
            JSONObject a2 = y.a();
            y.b(a2, "id", this.a);
            y.a(a2, "ad_session_id", ae.this.a);
            new af("AudioPlayer.on_ready", ae.this.b, a2).b();
        }
    }
}
