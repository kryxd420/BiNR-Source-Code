package com.tapdaq.adapters.tapdaq.ads;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapjoy.mraid.view.MraidView;
import java.util.UUID;

public abstract class TDAd {
    protected TMAd mAd;
    protected String mBroadcastId = UUID.randomUUID().toString();
    /* access modifiers changed from: private */
    public TMAdListenerBase mListener;
    private BroadcastReceiver mReceiver;

    public TDAd(TMAd tMAd) {
        this.mAd = tMAd;
    }

    public void show(Activity activity, TMAdListenerBase tMAdListenerBase) {
        this.mListener = tMAdListenerBase;
        this.mReceiver = new ServiceReceiver();
        LocalBroadcastManager.getInstance(activity).registerReceiver(this.mReceiver, new IntentFilter(this.mBroadcastId));
        this.mListener.didDisplay();
    }

    private class ServiceReceiver extends BroadcastReceiver {
        private ServiceReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(MraidView.ACTION_KEY);
            if (stringExtra.startsWith(TMListenerHandler.ACTION_CLICK)) {
                TDAd.this.mListener.didClick();
            } else if (stringExtra.startsWith(TMListenerHandler.ACTION_CLOSE)) {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                TDAd.this.mListener.didClose();
            }
        }
    }
}
