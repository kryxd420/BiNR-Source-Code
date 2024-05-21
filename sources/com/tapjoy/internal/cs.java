package com.tapjoy.internal;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;

public final class cs extends ContentObserver {
    public final Context a;
    public float b;
    private final AudioManager c;
    private final cp d;
    private final cr e;

    public cs(Handler handler, Context context, cp cpVar, cr crVar) {
        super(handler);
        this.a = context;
        this.c = (AudioManager) context.getSystemService("audio");
        this.d = cpVar;
        this.e = crVar;
    }

    public final float a() {
        int streamVolume = this.c.getStreamVolume(3);
        int streamMaxVolume = this.c.getStreamMaxVolume(3);
        if (streamMaxVolume <= 0 || streamVolume <= 0) {
            return 0.0f;
        }
        float f = ((float) streamVolume) / ((float) streamMaxVolume);
        if (f > 1.0f) {
            return 1.0f;
        }
        return f;
    }

    public final void b() {
        this.e.a(this.b);
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        float a2 = a();
        if (a2 != this.b) {
            this.b = a2;
            b();
        }
    }
}
