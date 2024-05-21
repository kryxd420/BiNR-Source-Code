package com.tapjoy.mraid.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.listener.Player;
import com.tapjoy.mraid.util.MraidPlayer;
import com.tapjoy.mraid.util.Utils;
import com.tapjoy.mraid.view.MraidView;
import java.util.HashMap;
import java.util.Map;

public class ActionHandler extends Activity {
    private HashMap a = new HashMap();
    private RelativeLayout b;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Bundle extras = getIntent().getExtras();
        this.b = new RelativeLayout(this);
        this.b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.b.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setContentView(this.b);
        String string = extras.getString(MraidView.ACTION_KEY);
        if (string != null) {
            MraidView.Action valueOf = MraidView.Action.valueOf(string);
            switch (valueOf) {
                case PLAY_AUDIO:
                    a(extras, valueOf).playAudio();
                    return;
                case PLAY_VIDEO:
                    a(extras, valueOf).playVideo();
                    return;
                default:
                    return;
            }
        }
    }

    private MraidPlayer a(Bundle bundle, MraidView.Action action) {
        RelativeLayout.LayoutParams layoutParams;
        Abstract.PlayerProperties playerProperties = (Abstract.PlayerProperties) bundle.getParcelable(MraidView.PLAYER_PROPERTIES);
        Abstract.Dimensions dimensions = (Abstract.Dimensions) bundle.getParcelable(MraidView.DIMENSIONS);
        MraidPlayer mraidPlayer = new MraidPlayer(this);
        mraidPlayer.setPlayData(playerProperties, Utils.getData(MraidView.EXPAND_URL, bundle));
        if (playerProperties.inline || !playerProperties.startStyle.equals(Abstract.FULL_SCREEN)) {
            layoutParams = new RelativeLayout.LayoutParams(dimensions.width, dimensions.height);
            layoutParams.topMargin = dimensions.y;
            layoutParams.leftMargin = dimensions.x;
        } else {
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
        }
        mraidPlayer.setLayoutParams(layoutParams);
        this.b.addView(mraidPlayer);
        this.a.put(action, mraidPlayer);
        mraidPlayer.setListener(new Player() {
            public final void onPrepared() {
            }

            public final void onError() {
                ActionHandler.this.finish();
            }

            public final void onComplete() {
                ActionHandler.this.finish();
            }
        });
        return mraidPlayer;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        for (Map.Entry key : this.a.entrySet()) {
            switch ((MraidView.Action) key.getKey()) {
                case PLAY_AUDIO:
                case PLAY_VIDEO:
                    ((MraidPlayer) key.getValue()).releasePlayer();
                    break;
            }
        }
        super.onStop();
    }
}
