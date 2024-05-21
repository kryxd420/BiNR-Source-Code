package com.tapdaq.adapters.tapdaq;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.InputDeviceCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.tapdaq.adapters.tapdaq.common.TMVideoActivity;
import com.tapdaq.adapters.tapdaq.common.TMVideoFragment;
import com.tapdaq.adapters.tapdaq.layout.CloseButtonCircle;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.Utils;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.model.TMModel;
import com.tapdaq.sdk.model.analytics.TMAdSize;
import com.tapdaq.sdk.network.ResponseHandler;
import com.tapdaq.sdk.network.TMServiceClient;
import com.tapdaq.sdk.storage.FileStorage;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.mraid.view.MraidView;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

public class TMVideoInterstitialActivity extends TMVideoActivity {
    private static String TM_VIDEO_FRAGMENT_TAG = "TM_VIDEO_FRAGMENT_TAG";
    /* access modifiers changed from: private */
    public TMAd mAd;
    private Button mAdBtn;
    private String mBroadcastId;
    private CloseButtonCircle mCloseBtn;
    /* access modifiers changed from: private */
    public String mTag;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(createLayout());
        if (Build.VERSION.SDK_INT > 11 && getActionBar() != null) {
            getActionBar().hide();
        }
        String stringExtra = getIntent().getStringExtra("Ad");
        this.mTag = getIntent().getStringExtra("Tag");
        this.mBroadcastId = getIntent().getStringExtra("BROADCAST_ID");
        try {
            this.mAd = (TMAd) new Gson().fromJson(stringExtra, TMAd.class);
        } catch (Exception e) {
            TLog.error(e);
        }
        int intExtra = getIntent().getIntExtra("Orientation", -1);
        if (intExtra == 1) {
            setRequestedOrientation(1);
        } else if (intExtra == 2) {
            setRequestedOrientation(0);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        play();
    }

    private View createLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
        relativeLayout.setId(Utils.generateViewId());
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(relativeLayout.getId(), new TMVideoFragment(), TM_VIDEO_FRAGMENT_TAG);
        beginTransaction.commit();
        float resolutionFloat = TDDeviceInfo.getResolutionFloat(this);
        this.mAdBtn = new Button(this);
        this.mAdBtn.setOnClickListener(new ClickAd());
        this.mAdBtn.setBackgroundColor(0);
        relativeLayout.addView(this.mAdBtn, new RelativeLayout.LayoutParams(-1, -1));
        int i = (int) (40.0f * resolutionFloat);
        this.mCloseBtn = new CloseButtonCircle(this, i);
        this.mCloseBtn.setOnClickListener(new ClickClose());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        int i2 = (int) (resolutionFloat * 16.0f);
        layoutParams.setMargins(i2, i2, i2, i2);
        relativeLayout.addView(this.mCloseBtn, layoutParams);
        return relativeLayout;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        play();
    }

    public void onBackPressed() {
        if (this.mCloseBtn != null) {
            this.mCloseBtn.performClick();
        }
    }

    private void play() {
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag(TM_VIDEO_FRAGMENT_TAG);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        if (!(findFragmentByTag instanceof TMVideoFragment) || this.mAd.getVideoUrl() == null) {
            TLog.error("Missing Video Fragment");
            finish();
            return;
        }
        if (findFragmentByTag.getView() != null) {
            findFragmentByTag.getView().setLayoutParams(layoutParams);
        }
        String videoUrl = this.mAd.getVideoUrl();
        try {
            FileStorage fileStorage = new FileStorage(this);
            String replace = new URL(this.mAd.getVideoUrl()).getPath().replace("/", "~");
            if (fileStorage.exists(replace, TJAdUnitConstants.String.DATA)) {
                String path = fileStorage.getPath(replace, TJAdUnitConstants.String.DATA);
                videoUrl = String.format(Locale.ENGLISH, "%s/%s", new Object[]{path, replace});
            }
        } catch (MalformedURLException e) {
            TLog.error((Exception) e);
        }
        this.mPlayer = ((TMVideoFragment) findFragmentByTag).playVideo(videoUrl, this.mAd.isVideoLooped());
        this.mPlayer.setOnCompletionListener(this.mMediaCompletionListener);
    }

    /* access modifiers changed from: protected */
    public void onComplete() {
        super.onComplete();
    }

    private class ClickAd implements View.OnClickListener {
        private ClickAd() {
        }

        public void onClick(View view) {
            Intent intent;
            if (TMVideoInterstitialActivity.this.mAd.getCustomUrl() == null || TMVideoInterstitialActivity.this.mAd.getCustomUrl().isEmpty()) {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "%s%s", new Object[]{"market://details?id=", TMVideoInterstitialActivity.this.mAd.getStoreId()})));
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(TMVideoInterstitialActivity.this.mAd.getCustomUrl()));
            }
            if (intent.resolveActivity(TMVideoInterstitialActivity.this.getPackageManager()) != null) {
                TMVideoInterstitialActivity.this.startActivity(intent);
            }
            new TMServiceClient().click(TMVideoInterstitialActivity.this, TMVideoInterstitialActivity.this.mAd, "INTERSTITIAL_PORTRAIT_FAT", TMVideoInterstitialActivity.this.mTag, new TMAdSize(0.0f, 0.0f), new ResponseHandler(TMVideoInterstitialActivity.this.getApplicationContext(), ResponseHandler.CLICK));
            TMVideoInterstitialActivity.this.sendListenerEvent(TMListenerHandler.ACTION_CLICK, TMAdType.getString(1), TMVideoInterstitialActivity.this.mAd, (Map<String, String>) null);
        }
    }

    private class ClickClose implements View.OnClickListener {
        private ClickClose() {
        }

        public void onClick(View view) {
            TMVideoInterstitialActivity.this.sendListenerEvent(TMListenerHandler.ACTION_CLOSE, "VIDEO", TMVideoInterstitialActivity.this.mAd, (Map<String, String>) null);
            TMVideoInterstitialActivity.this.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void sendListenerEvent(String str, String str2, TMModel tMModel, Map<String, String> map) {
        Intent intent = new Intent(this.mBroadcastId);
        intent.putExtra(MraidView.ACTION_KEY, str);
        intent.putExtra("type", str2);
        intent.putExtra("value", new Gson().toJson((Object) tMModel));
        if (map != null) {
            for (String next : map.keySet()) {
                intent.putExtra(next, map.get(next));
            }
        }
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
