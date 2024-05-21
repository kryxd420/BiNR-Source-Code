package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Map;

class ah extends i implements NativeVideoTracker {
    public ah(String str, a aVar, ap apVar) {
        super(str, aVar, apVar);
    }

    /* access modifiers changed from: protected */
    public Map a() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f.get();
        HashMap hashMap = new HashMap();
        hashMap.put(AvidJSONUtil.KEY_WIDTH, Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put(AvidJSONUtil.KEY_HEIGHT, Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    public void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    /* access modifiers changed from: protected */
    public Integer f() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getCurrentPosition());
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return ((MediaPlayer) this.f.get()).isPlaying();
    }

    /* access modifiers changed from: protected */
    public Integer h() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getDuration());
    }

    /* renamed from: trackVideoAd */
    public boolean a(Map map, MediaPlayer mediaPlayer, View view) {
        if (mediaPlayer == null) {
            a("Null player instance. Not tracking.");
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.a(map, mediaPlayer, view);
        } catch (IllegalStateException unused) {
            a("Playback has already completed. Not tracking.");
            return false;
        }
    }
}
