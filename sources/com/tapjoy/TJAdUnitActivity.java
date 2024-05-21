package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.fu;
import com.tapjoy.internal.gr;
import com.tapjoy.internal.hd;
import com.tapjoy.mraid.view.BasicWebView;
import com.tapjoy.mraid.view.MraidView;

public class TJAdUnitActivity extends Activity implements View.OnClickListener {
    private static TJAdUnitActivity b;
    private final Handler a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public TJAdUnit c;
    private TJPlacementData d;
    private TJAdUnitSaveStateData e = new TJAdUnitSaveStateData();
    private RelativeLayout f = null;
    private TJCloseButton g;
    private ProgressBar h;
    private boolean i = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        TapjoyLog.d("TJAdUnitActivity", "TJAdUnitActivity onCreate: " + bundle);
        super.onCreate(bundle);
        b = this;
        if (bundle != null) {
            this.e = (TJAdUnitSaveStateData) bundle.getSerializable("ad_unit_bundle");
            if (this.e != null && this.e.isVideoComplete) {
                TapjoyLog.d("TJAdUnitActivity", "finishing TJAdUnitActivity");
                finish();
                return;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) == null) {
            TapjoyLog.e("TJAdUnitActivity", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Failed to launch AdUnit Activity"));
            finish();
            return;
        }
        this.d = (TJPlacementData) extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA);
        if (this.d.getContentViewId() != null) {
            TapjoyConnectCore.viewWillOpen(this.d.getContentViewId(), 1);
        }
        if (TJPlacementManager.a(this.d.getKey()) != null) {
            this.c = TJPlacementManager.a(this.d.getKey()).getAdUnit();
        } else {
            this.c = new TJAdUnit();
            this.c.setAdContentTracker(new fq(this.d.getPlacementName(), this.d.getPlacementType()));
        }
        if (!this.c.hasCalledLoad()) {
            TapjoyLog.d("TJAdUnitActivity", "No content loaded for ad unit -- loading now");
            this.c.load(this.d, false, this);
        }
        this.c.setAdUnitActivity(this);
        if (Build.VERSION.SDK_INT < 11) {
            setTheme(16973829);
        } else {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f = new RelativeLayout(this);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackgroundColor(0);
        BasicWebView backgroundWebView = this.c.getBackgroundWebView();
        backgroundWebView.setLayoutParams(layoutParams);
        if (backgroundWebView.getParent() != null) {
            ((ViewGroup) backgroundWebView.getParent()).removeView(backgroundWebView);
        }
        MraidView webView = this.c.getWebView();
        webView.setLayoutParams(layoutParams);
        if (webView.getParent() != null) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        VideoView videoView = this.c.getVideoView();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        videoView.setLayoutParams(layoutParams2);
        if (videoView.getParent() != null) {
            ((ViewGroup) videoView.getParent()).removeView(videoView);
        }
        this.f.addView(backgroundWebView);
        this.f.addView(videoView);
        this.f.addView(webView);
        this.h = new ProgressBar(this, (AttributeSet) null, 16842874);
        if (this.d.hasProgressSpinner()) {
            setProgressSpinnerVisibility(true);
        } else {
            setProgressSpinnerVisibility(false);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        this.h.setLayoutParams(layoutParams3);
        this.f.addView(this.h);
        if (!this.c.getWebView().isMraid()) {
            this.g = new TJCloseButton(this);
            this.g.setOnClickListener(this);
            this.f.addView(this.g);
        }
        setContentView(this.f);
        this.c.setVisible(true);
        TJCorePlacement a2 = TJPlacementManager.a(this.d.getKey());
        if (a2 != null) {
            String str = TJCorePlacement.a;
            TapjoyLog.i(str, "Content shown for placement " + a2.c.getPlacementName());
            a2.f.a();
            TJPlacement a3 = a2.a("SHOW");
            if (a3 != null && a3.getListener() != null) {
                a3.getListener().onContentShow(a3);
            }
        }
    }

    public void setCloseButtonVisibility(boolean z) {
        if (z) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(4);
        }
    }

    public void setCloseButtonClickable(boolean z) {
        this.g.setClickableRequested(z);
    }

    public void setProgressSpinnerVisibility(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(4);
        }
    }

    public void onBackPressed() {
        handleClose();
    }

    public void handleClose() {
        handleClose(false);
    }

    public void handleClose(boolean z) {
        if (!this.c.getCloseRequested()) {
            TapjoyLog.d("TJAdUnitActivity", TJAdUnitConstants.String.CLOSE_REQUESTED);
            this.c.closeRequested(z);
            this.a.postDelayed(new Runnable() {
                public final void run() {
                    if (TJAdUnitActivity.this.c.getCloseRequested()) {
                        TapjoyLog.d("TJAdUnitActivity", "Did not receive callback from content. Closing ad.");
                        TJAdUnitActivity.this.finish();
                    }
                }
            }, 1000);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        TJPlacement a2;
        super.onDestroy();
        b = null;
        TapjoyLog.d("TJAdUnitActivity", "onDestroy");
        if (this.c != null) {
            this.c.destroy();
        }
        if (this.d != null && this.d.isBaseActivity()) {
            if (this.d.getContentViewId() != null) {
                TapjoyConnectCore.viewDidClose(this.d.getContentViewId());
            }
            TJCorePlacement a3 = TJPlacementManager.a(this.d.getKey());
            if (a3 != null && (a2 = a3.a("SHOW")) != null && a2.getListener() != null) {
                String str = TJCorePlacement.a;
                TapjoyLog.i(str, "Content dismissed for placement " + a3.c.getPlacementName());
                fu fuVar = a3.f.a;
                if (fuVar != null) {
                    fuVar.b.clear();
                }
                if (a2 != null && a2.a != null) {
                    a2.a.onContentDismiss(a2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        TapjoyLog.d("TJAdUnitActivity", "onResume");
        super.onResume();
        if (this.c.isLockedOrientation()) {
            setRequestedOrientation(this.c.getOrientation());
        }
        this.c.resume(this.e);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        TapjoyLog.d("TJAdUnitActivity", "onStart");
        if (hd.a().n) {
            this.i = true;
            gr.a(this);
        }
        if (!this.d.isBaseActivity()) {
            setResult(-1, getIntent());
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        TapjoyLog.d("TJAdUnitActivity", "onPause");
        this.c.pause();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TapjoyLog.d("TJAdUnitActivity", "onSaveInstanceState");
        this.e.seekTime = this.c.getVideoSeekTime();
        this.e.isVideoComplete = this.c.isVideoComplete();
        this.e.isVideoMuted = this.c.isMuted();
        bundle.putSerializable("ad_unit_bundle", this.e);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.i) {
            this.i = false;
            gr.b(this);
        }
        super.onStop();
        TapjoyLog.d("TJAdUnitActivity", "onStop");
    }

    public void showErrorDialog() {
        if (isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this, 16974394).setMessage("An error occured. Please try again later.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    TJAdUnitActivity.this.handleClose();
                    dialogInterface.cancel();
                }
            }).create().show();
        } else {
            new AlertDialog.Builder(this).setMessage("An error occured. Please try again later.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    TJAdUnitActivity.this.handleClose();
                    dialogInterface.cancel();
                }
            }).create().show();
        }
    }

    public void onClick(View view) {
        handleClose();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 327 && intent != null && intent.hasExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) && this.c != null) {
            this.c.invokeBridgeCallback(((TJPlacementData) intent.getSerializableExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA)).getCallbackID(), Boolean.TRUE);
        }
    }

    static void a() {
        TJAdUnitActivity tJAdUnitActivity = b;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.handleClose(true);
        }
    }
}
