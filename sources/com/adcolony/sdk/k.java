package com.adcolony.sdk;

import android.app.Activity;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import com.adcolony.sdk.aa;
import org.json.JSONObject;

class k extends ContentObserver {
    private AudioManager a;
    private AdColonyInterstitial b;

    public boolean deliverSelfNotifications() {
        return false;
    }

    public k(Handler handler, AdColonyInterstitial adColonyInterstitial) {
        super(handler);
        Activity c = a.c();
        if (c != null) {
            this.a = (AudioManager) c.getSystemService("audio");
            this.b = adColonyInterstitial;
            c.getApplicationContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this);
        }
    }

    public void onChange(boolean z) {
        if (this.a != null && this.b != null && this.b.d() != null) {
            double streamVolume = (double) ((((float) this.a.getStreamVolume(3)) / 15.0f) * 100.0f);
            int i = (int) streamVolume;
            if (this.b.g() && this.b.h().e() != null && !this.b.i()) {
                this.b.h().e().getAvidVideoPlaybackListener().recordAdVolumeChangeEvent(Integer.valueOf(i));
                this.b.h().a("volume_change");
            }
            JSONObject a2 = y.a();
            y.a(a2, "audio_percentage", streamVolume);
            y.a(a2, "ad_session_id", this.b.d().b());
            y.b(a2, "id", this.b.d().d());
            new af("AdContainer.on_audio_change", this.b.d().c(), a2).b();
            new aa.a().a("Volume changed to ").a(streamVolume).a(aa.d);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        Activity c = a.c();
        if (c != null) {
            c.getApplicationContext().getContentResolver().unregisterContentObserver(this);
        }
        this.b = null;
        this.a = null;
    }
}
