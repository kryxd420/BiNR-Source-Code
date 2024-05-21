package com.tapdaq.adapters.tapdaq.ads;

import android.app.Activity;
import android.content.Intent;
import com.tapdaq.adapters.tapdaq.TMInterstitialActivity;
import com.tapdaq.adapters.tapdaq.TMVideoInterstitialActivity;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.listeners.TMAdListenerBase;

public class TDAdInterstitial extends TDAd {
    public TDAdInterstitial(TMAd tMAd) {
        super(tMAd);
    }

    public void show(Activity activity, TMAdListenerBase tMAdListenerBase) {
        Intent intent;
        super.show(activity, tMAdListenerBase);
        if (this.mAd.hasVideoAvailable()) {
            intent = new Intent(activity, TMVideoInterstitialActivity.class);
        } else {
            intent = new Intent(activity, TMInterstitialActivity.class);
        }
        intent.putExtra("BROADCAST_ID", this.mBroadcastId);
        intent.putExtra("Ad", this.mAd.toString());
        intent.putExtra("Orientation", activity.getResources().getConfiguration().orientation);
        activity.startActivity(intent);
    }
}
