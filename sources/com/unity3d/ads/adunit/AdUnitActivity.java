package com.unity3d.ads.adunit;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.unity3d.ads.api.AdUnit;
import com.unity3d.ads.api.VideoPlayer;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.ViewUtilities;
import com.unity3d.ads.video.VideoPlayerView;
import com.unity3d.ads.webplayer.WebPlayer;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdUnitActivity extends Activity {
    public static final String EXTRA_ACTIVITY_ID = "activityId";
    public static final String EXTRA_KEEP_SCREEN_ON = "keepScreenOn";
    public static final String EXTRA_KEY_EVENT_LIST = "keyEvents";
    public static final String EXTRA_ORIENTATION = "orientation";
    public static final String EXTRA_SYSTEM_UI_VISIBILITY = "systemUiVisibility";
    public static final String EXTRA_VIEWS = "views";
    private int _activityId;
    boolean _keepScreenOn;
    private ArrayList<Integer> _keyEventList;
    protected AdUnitRelativeLayout _layout;
    private int _orientation = -1;
    private int _systemUiVisibility;
    private RelativeLayout _videoContainer;
    private String[] _views;
    private WebPlayer _webPlayer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AdUnitEvent adUnitEvent;
        super.onCreate(bundle);
        if (WebViewApp.getCurrentApp() == null) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onCreate");
            finish();
            return;
        }
        AdUnit.setAdUnitActivity(this);
        createLayout();
        ViewUtilities.removeViewFromParent(this._layout);
        addContentView(this._layout, this._layout.getLayoutParams());
        if (bundle == null) {
            this._views = getIntent().getStringArrayExtra(EXTRA_VIEWS);
            this._keyEventList = getIntent().getIntegerArrayListExtra(EXTRA_KEY_EVENT_LIST);
            if (getIntent().hasExtra("orientation")) {
                this._orientation = getIntent().getIntExtra("orientation", -1);
            }
            if (getIntent().hasExtra(EXTRA_SYSTEM_UI_VISIBILITY)) {
                this._systemUiVisibility = getIntent().getIntExtra(EXTRA_SYSTEM_UI_VISIBILITY, 0);
            }
            if (getIntent().hasExtra(EXTRA_ACTIVITY_ID)) {
                this._activityId = getIntent().getIntExtra(EXTRA_ACTIVITY_ID, -1);
            }
            adUnitEvent = AdUnitEvent.ON_CREATE;
        } else {
            this._views = bundle.getStringArray(EXTRA_VIEWS);
            this._orientation = bundle.getInt("orientation", -1);
            this._systemUiVisibility = bundle.getInt(EXTRA_SYSTEM_UI_VISIBILITY, 0);
            this._keyEventList = bundle.getIntegerArrayList(EXTRA_KEY_EVENT_LIST);
            this._keepScreenOn = bundle.getBoolean(EXTRA_KEEP_SCREEN_ON);
            this._activityId = bundle.getInt(EXTRA_ACTIVITY_ID, -1);
            setKeepScreenOn(this._keepScreenOn);
            adUnitEvent = AdUnitEvent.ON_RESTORE;
        }
        setOrientation(this._orientation);
        setSystemUiVisibility(this._systemUiVisibility);
        if (this._views != null) {
            if (Arrays.asList(this._views).contains("videoplayer")) {
                createVideoPlayer();
            }
            if (Arrays.asList(this._views).contains("webplayer")) {
                createWebPlayer();
            }
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, adUnitEvent, Integer.valueOf(this._activityId));
    }

    public AdUnitRelativeLayout getLayout() {
        return this._layout;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_START, Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onStart");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_STOP, Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onStop");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (WebViewApp.getCurrentApp() != null) {
            setViews(this._views);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_RESUME, Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onResume");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (WebViewApp.getCurrentApp() != null) {
            if (isFinishing()) {
                ViewUtilities.removeViewFromParent(WebViewApp.getCurrentApp().getWebView());
            }
            destroyVideoPlayer();
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_PAUSE, Boolean.valueOf(isFinishing()), Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onPause");
            finish();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("orientation", this._orientation);
        bundle.putInt(EXTRA_SYSTEM_UI_VISIBILITY, this._systemUiVisibility);
        bundle.putIntegerArrayList(EXTRA_KEY_EVENT_LIST, this._keyEventList);
        bundle.putBoolean(EXTRA_KEEP_SCREEN_ON, this._keepScreenOn);
        bundle.putStringArray(EXTRA_VIEWS, this._views);
        bundle.putInt(EXTRA_ACTIVITY_ID, this._activityId);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_DESTROY, Boolean.valueOf(isFinishing()), Integer.valueOf(this._activityId));
            if (AdUnit.getCurrentAdUnitActivityId() == this._activityId) {
                AdUnit.setAdUnitActivity((AdUnitActivity) null);
            }
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onDestroy");
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this._keyEventList == null || !this._keyEventList.contains(Integer.valueOf(i))) {
            return false;
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.KEY_DOWN, Integer.valueOf(i), Long.valueOf(keyEvent.getEventTime()), Long.valueOf(keyEvent.getDownTime()), Integer.valueOf(keyEvent.getRepeatCount()), Integer.valueOf(this._activityId));
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_FOCUS_GAINED, Integer.valueOf(this._activityId));
        } else {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_FOCUS_LOST, Integer.valueOf(this._activityId));
        }
        super.onWindowFocusChanged(z);
    }

    public WebPlayer getWebPlayer() {
        return this._webPlayer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setViewFrame(java.lang.String r3, int r4, int r5, int r6, int r7) {
        /*
            r2 = this;
            java.lang.String r0 = "adunit"
            boolean r0 = r3.equals(r0)
            r1 = 0
            if (r0 == 0) goto L_0x0017
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r3.<init>(r6, r7)
            r3.setMargins(r4, r5, r1, r1)
            com.unity3d.ads.adunit.AdUnitRelativeLayout r0 = r2._layout
            r0.setLayoutParams(r3)
            goto L_0x003e
        L_0x0017:
            java.lang.String r0 = "videoplayer"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0022
            android.widget.RelativeLayout r3 = r2._videoContainer
            goto L_0x003f
        L_0x0022:
            java.lang.String r0 = "webview"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0033
            com.unity3d.ads.webview.WebViewApp r3 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebView r3 = r3.getWebView()
            goto L_0x003f
        L_0x0033:
            java.lang.String r0 = "webplayer"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003e
            com.unity3d.ads.webplayer.WebPlayer r3 = r2._webPlayer
            goto L_0x003f
        L_0x003e:
            r3 = 0
        L_0x003f:
            if (r3 == 0) goto L_0x004c
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r0.<init>(r6, r7)
            r0.setMargins(r4, r5, r1, r1)
            r3.setLayoutParams(r0)
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.adunit.AdUnitActivity.setViewFrame(java.lang.String, int, int, int, int):void");
    }

    public Map<String, Integer> getViewFrame(String str) {
        View view;
        if (str.equals("adunit")) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this._layout.getLayoutParams();
            HashMap hashMap = new HashMap();
            hashMap.put(AvidJSONUtil.KEY_X, Integer.valueOf(layoutParams.leftMargin));
            hashMap.put(AvidJSONUtil.KEY_Y, Integer.valueOf(layoutParams.topMargin));
            hashMap.put(AvidJSONUtil.KEY_WIDTH, Integer.valueOf(this._layout.getWidth()));
            hashMap.put(AvidJSONUtil.KEY_HEIGHT, Integer.valueOf(this._layout.getHeight()));
            return hashMap;
        }
        if (str.equals("videoplayer")) {
            view = this._videoContainer;
        } else {
            view = str.equals("webview") ? WebViewApp.getCurrentApp().getWebView() : null;
        }
        if (view == null) {
            return null;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(AvidJSONUtil.KEY_X, Integer.valueOf(layoutParams2.leftMargin));
        hashMap2.put(AvidJSONUtil.KEY_Y, Integer.valueOf(layoutParams2.topMargin));
        hashMap2.put(AvidJSONUtil.KEY_WIDTH, Integer.valueOf(view.getWidth()));
        hashMap2.put(AvidJSONUtil.KEY_HEIGHT, Integer.valueOf(view.getHeight()));
        return hashMap2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0028 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setViews(java.lang.String[] r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0005
            java.lang.String[] r7 = new java.lang.String[r0]
        L_0x0005:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.List r2 = java.util.Arrays.asList(r7)
            r1.<init>(r2)
            java.lang.String[] r2 = r6._views
            if (r2 != 0) goto L_0x0016
            java.lang.String[] r2 = new java.lang.String[r0]
            r6._views = r2
        L_0x0016:
            java.util.ArrayList r2 = new java.util.ArrayList
            java.lang.String[] r3 = r6._views
            java.util.List r3 = java.util.Arrays.asList(r3)
            r2.<init>(r3)
            r2.removeAll(r1)
            java.util.Iterator r1 = r2.iterator()
        L_0x0028:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0080
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            r3 = -1
            int r4 = r2.hashCode()
            r5 = -318269643(0xffffffffed079735, float:-2.6227047E27)
            if (r4 == r5) goto L_0x005d
            r5 = 1224424441(0x48fb3bf9, float:514527.78)
            if (r4 == r5) goto L_0x0053
            r5 = 1865295644(0x6f2e271c, float:5.389767E28)
            if (r4 == r5) goto L_0x0049
            goto L_0x0067
        L_0x0049:
            java.lang.String r4 = "videoplayer"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0067
            r2 = 0
            goto L_0x0068
        L_0x0053:
            java.lang.String r4 = "webview"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0067
            r2 = 1
            goto L_0x0068
        L_0x005d:
            java.lang.String r4 = "webplayer"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0067
            r2 = 2
            goto L_0x0068
        L_0x0067:
            r2 = -1
        L_0x0068:
            switch(r2) {
                case 0: goto L_0x007c;
                case 1: goto L_0x0070;
                case 2: goto L_0x006c;
                default: goto L_0x006b;
            }
        L_0x006b:
            goto L_0x0028
        L_0x006c:
            r6.destroyWebPlayer()
            goto L_0x0028
        L_0x0070:
            com.unity3d.ads.webview.WebViewApp r2 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebView r2 = r2.getWebView()
            com.unity3d.ads.misc.ViewUtilities.removeViewFromParent(r2)
            goto L_0x0028
        L_0x007c:
            r6.destroyVideoPlayer()
            goto L_0x0028
        L_0x0080:
            r6._views = r7
            int r1 = r7.length
        L_0x0083:
            if (r0 >= r1) goto L_0x00d3
            r2 = r7[r0]
            if (r2 != 0) goto L_0x008a
            goto L_0x00d0
        L_0x008a:
            java.lang.String r3 = "videoplayer"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x009b
            r6.createVideoPlayer()
            android.widget.RelativeLayout r2 = r6._videoContainer
            r6.handleViewPlacement(r2)
            goto L_0x00d0
        L_0x009b:
            java.lang.String r3 = "webview"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00c0
            com.unity3d.ads.webview.WebViewApp r2 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            if (r2 == 0) goto L_0x00b5
            com.unity3d.ads.webview.WebViewApp r2 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
            com.unity3d.ads.webview.WebView r2 = r2.getWebView()
            r6.handleViewPlacement(r2)
            goto L_0x00d0
        L_0x00b5:
            java.lang.String r7 = "WebApp IS NULL!"
            com.unity3d.ads.log.DeviceLog.error(r7)
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>()
            throw r7
        L_0x00c0:
            java.lang.String r3 = "webplayer"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00d0
            r6.createWebPlayer()
            com.unity3d.ads.webplayer.WebPlayer r2 = r6._webPlayer
            r6.handleViewPlacement(r2)
        L_0x00d0:
            int r0 = r0 + 1
            goto L_0x0083
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.adunit.AdUnitActivity.setViews(java.lang.String[]):void");
    }

    private void handleViewPlacement(View view) {
        if (view.getParent() == null || !view.getParent().equals(this._layout)) {
            ViewUtilities.removeViewFromParent(view);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            layoutParams.setMargins(0, 0, 0, 0);
            view.setPadding(0, 0, 0, 0);
            this._layout.addView(view, layoutParams);
            return;
        }
        this._layout.bringChildToFront(view);
    }

    public String[] getViews() {
        return this._views;
    }

    public void setOrientation(int i) {
        this._orientation = i;
        setRequestedOrientation(i);
    }

    public boolean setKeepScreenOn(boolean z) {
        this._keepScreenOn = z;
        if (getWindow() == null) {
            return false;
        }
        if (z) {
            getWindow().addFlags(128);
            return true;
        }
        getWindow().clearFlags(128);
        return true;
    }

    public boolean setSystemUiVisibility(int i) {
        this._systemUiVisibility = i;
        if (Build.VERSION.SDK_INT < 11) {
            return false;
        }
        try {
            getWindow().getDecorView().setSystemUiVisibility(i);
            return true;
        } catch (Exception e) {
            DeviceLog.exception("Error while setting SystemUIVisibility", e);
            return false;
        }
    }

    public void setKeyEventList(ArrayList<Integer> arrayList) {
        this._keyEventList = arrayList;
    }

    /* access modifiers changed from: protected */
    public void createLayout() {
        if (this._layout == null) {
            this._layout = new AdUnitRelativeLayout(this);
            this._layout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            ViewUtilities.setBackground(this._layout, new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        }
    }

    private void createWebPlayer() {
        if (this._webPlayer == null) {
            this._webPlayer = new WebPlayer(this, com.unity3d.ads.api.WebPlayer.getWebSettings(), com.unity3d.ads.api.WebPlayer.getWebPlayerSettings());
            this._webPlayer.setEventSettings(com.unity3d.ads.api.WebPlayer.getWebPlayerEventSettings());
        }
    }

    private void destroyWebPlayer() {
        if (this._webPlayer != null) {
            ViewUtilities.removeViewFromParent(this._webPlayer);
        }
        this._webPlayer = null;
    }

    private void createVideoPlayer() {
        if (this._videoContainer == null) {
            this._videoContainer = new RelativeLayout(this);
        }
        if (VideoPlayer.getVideoPlayerView() == null) {
            VideoPlayer.setVideoPlayerView(new VideoPlayerView(this));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            VideoPlayer.getVideoPlayerView().setLayoutParams(layoutParams);
            this._videoContainer.addView(VideoPlayer.getVideoPlayerView());
        }
    }

    private void destroyVideoPlayer() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().stopVideoProgressTimer();
            VideoPlayer.getVideoPlayerView().stopPlayback();
            ViewUtilities.removeViewFromParent(VideoPlayer.getVideoPlayerView());
        }
        VideoPlayer.setVideoPlayerView((VideoPlayerView) null);
        if (this._videoContainer != null) {
            ViewUtilities.removeViewFromParent(this._videoContainer);
            this._videoContainer = null;
        }
    }
}
