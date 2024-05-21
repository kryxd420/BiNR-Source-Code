package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;

final class jd {
    static jc a;
    static long b;

    private jd() {
    }

    static jc a() {
        synchronized (jd.class) {
            if (a == null) {
                return new jc();
            }
            jc jcVar = a;
            a = jcVar.f;
            jcVar.f = null;
            b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            return jcVar;
        }
    }

    static void a(jc jcVar) {
        if (jcVar.f != null || jcVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!jcVar.d) {
            synchronized (jd.class) {
                if (b + PlaybackStateCompat.ACTION_PLAY_FROM_URI <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    jcVar.f = a;
                    jcVar.c = 0;
                    jcVar.b = 0;
                    a = jcVar;
                }
            }
        }
    }
}
