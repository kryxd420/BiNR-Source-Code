package com.tapdaq.adapters.tapdaq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.tapdaq.adapters.tapdaq.layout.CloseButtonCircle;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.adapters.tapdaq.storage.TMCache;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.Utils;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.model.TMModel;
import com.tapdaq.sdk.model.analytics.TMAdSize;
import com.tapdaq.sdk.network.HttpClientBase;
import com.tapdaq.sdk.network.ResponseHandler;
import com.tapdaq.sdk.network.TClient;
import com.tapdaq.sdk.network.TMServiceClient;
import com.tapdaq.sdk.storage.FileStorage;
import com.tapjoy.mraid.view.MraidView;
import java.net.URL;
import java.util.Map;

public class TMInterstitialActivity extends Activity {
    /* access modifiers changed from: private */
    public TMAd mAd;
    private Button mAdBtn;
    /* access modifiers changed from: private */
    public ImageView mAdImage;
    /* access modifiers changed from: private */
    public TMAdSize mAdSize;
    private String mBroadcastId;
    private Button mCloseBtn;
    private String mTag;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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
        if (intExtra == getResources().getConfiguration().orientation) {
            setContentView(createLayout());
            populate();
        } else if (intExtra == 1) {
            setRequestedOrientation(1);
        } else if (intExtra == 2) {
            setRequestedOrientation(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setContentView(createLayout());
        populate();
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.mCloseBtn != null) {
            this.mCloseBtn.performClick();
        }
    }

    private View createLayout() {
        float resolutionFloat = TDDeviceInfo.getResolutionFloat(this);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.mAdImage = new ImageView(this);
        if (Build.VERSION.SDK_INT < 17) {
            this.mAdImage.setId(Utils.generateViewId());
        } else {
            this.mAdImage.setId(View.generateViewId());
        }
        this.mAdImage.setScaleType(ImageView.ScaleType.FIT_XY);
        this.mAdImage.setAdjustViewBounds(false);
        relativeLayout.addView(this.mAdImage, new RelativeLayout.LayoutParams(-1, -1));
        this.mAdBtn = new Button(this);
        this.mAdBtn.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(5, this.mAdImage.getId());
        layoutParams.addRule(7, this.mAdImage.getId());
        layoutParams.addRule(6, this.mAdImage.getId());
        layoutParams.addRule(8, this.mAdImage.getId());
        relativeLayout.addView(this.mAdBtn, layoutParams);
        int i = (int) (40.0f * resolutionFloat);
        this.mCloseBtn = new CloseButtonCircle(this, i);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, i);
        layoutParams2.addRule(6, this.mAdImage.getId());
        layoutParams2.addRule(7, this.mAdImage.getId());
        int i2 = (int) (resolutionFloat * 16.0f);
        layoutParams2.setMargins(i2, i2, i2, i2);
        relativeLayout.addView(this.mCloseBtn, layoutParams2);
        return relativeLayout;
    }

    private void populate() {
        Bitmap bitmap;
        FileStorage fileStorage = new FileStorage(this);
        String imageUrl = this.mAd.getImageUrl();
        try {
            bitmap = fileStorage.loadImage(new URL(imageUrl).getPath().replace("/", "~"));
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        if (bitmap != null) {
            PointF calculateImageSize = calculateImageSize(bitmap);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) calculateImageSize.x, (int) calculateImageSize.y);
            layoutParams.addRule(13);
            this.mAdImage.setLayoutParams(layoutParams);
            this.mAdImage.setImageBitmap(bitmap);
            this.mAdSize = new TMAdSize((float) bitmap.getWidth(), (float) bitmap.getHeight());
        } else {
            TClient.getInstance().executeImageGET(this, imageUrl, TDDeviceInfo.getWidth(this), TDDeviceInfo.getHeight(this), new ImageHandler(imageUrl));
        }
        this.mAdBtn.setOnClickListener(new ClickAd());
        this.mCloseBtn.setOnClickListener(new ClickClose());
    }

    private class ClickAd implements View.OnClickListener {
        private ClickAd() {
        }

        public void onClick(View view) {
            new TMServiceClient().click(TMInterstitialActivity.this, TMInterstitialActivity.this.mAd, "INTERSTITIAL_PORTRAIT_FAT", TapdaqPlacement.TDPTagDefault, TMInterstitialActivity.this.mAdSize, new ResponseHandler(TMInterstitialActivity.this.getApplicationContext(), ResponseHandler.CLICK));
            TMInterstitialActivity.this.sendListenerEvent(TMListenerHandler.ACTION_CLICK, TMAdType.getString(1), TMInterstitialActivity.this.mAd, (Map<String, String>) null);
            if (TMInterstitialActivity.this.mAd != null) {
                TMInterstitialActivity.this.mAd.performClick(TMInterstitialActivity.this);
            }
        }
    }

    private class ClickClose implements View.OnClickListener {
        private ClickClose() {
        }

        public void onClick(View view) {
            TMInterstitialActivity.this.sendListenerEvent(TMListenerHandler.ACTION_CLOSE, TMAdType.getString(1), TMInterstitialActivity.this.mAd, (Map<String, String>) null);
            TMInterstitialActivity.this.finish();
        }
    }

    private class ImageHandler implements HttpClientBase.ResponseImageHandler {
        private String mUrl;

        public ImageHandler(String str) {
            this.mUrl = str;
        }

        public void onSuccess(Bitmap bitmap) {
            PointF access$400 = TMInterstitialActivity.this.calculateImageSize(bitmap);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) access$400.x, (int) access$400.y);
            layoutParams.addRule(13);
            TMInterstitialActivity.this.mAdImage.setLayoutParams(layoutParams);
            TMInterstitialActivity.this.mAdImage.setImageBitmap(bitmap);
            TMAdSize unused = TMInterstitialActivity.this.mAdSize = new TMAdSize((float) bitmap.getWidth(), (float) bitmap.getHeight());
            new TMCache().cache((Context) TMInterstitialActivity.this, this.mUrl, bitmap);
        }

        public void onError(Exception exc) {
            TMInterstitialActivity.this.finish();
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

    /* access modifiers changed from: private */
    public PointF calculateImageSize(Bitmap bitmap) {
        if (TDDeviceInfo.isDevicePortrait(this)) {
            float width = (float) TDDeviceInfo.getWidth(this);
            return new PointF(width, (width / ((float) bitmap.getWidth())) * ((float) bitmap.getHeight()));
        }
        float height = (float) TDDeviceInfo.getHeight(this);
        return new PointF((height / ((float) bitmap.getHeight())) * ((float) bitmap.getWidth()), height);
    }
}
