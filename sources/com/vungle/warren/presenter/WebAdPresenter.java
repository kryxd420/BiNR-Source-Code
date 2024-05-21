package com.vungle.warren.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.Storage;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.ui.AdView;
import com.vungle.warren.ui.VungleWebClient;
import com.vungle.warren.utility.UnzipUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class WebAdPresenter implements AdvertisementPresenter, VungleWebClient.MRAIDDelegate {
    private static String EXTRA_CONTENT_PREPARED = "content_prepared";
    private static final String EXTRA_INCENTIVIZED_SENT = "incentivized_sent";
    private static String EXTRA_REPORT = "saved_report";
    private static final String EXTRA_WEB_READY = "web_ready";
    protected static final double NINE_BY_SIXTEEN_ASPECT_RATIO = 0.5625d;
    /* access modifiers changed from: private */
    public static final String TAG = WebAdPresenter.class.getCanonicalName();
    private AdView adView;
    private Advertisement advertisement;
    private File assetDir;
    /* access modifiers changed from: private */
    public boolean backEnabled;
    private AdvertisementPresenter.EventListener bus;
    private DirectDownloadAdapter directDownloadAdapter;
    private boolean directDownloadApkEnabled;
    private float duration;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean hasSend80Percent = false;
    private boolean hasSendStart = false;
    private AtomicBoolean isDestroying = new AtomicBoolean(false);
    private boolean muted;
    private Placement placement;
    private boolean prepared = false;
    /* access modifiers changed from: private */
    public Report report;
    private AtomicBoolean sendReportIncentivized = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Storage storage;
    private String userID;
    private VungleWebClient webClient;

    public void initializeViewabilityTracker(int i, VideoView videoView) {
    }

    public void stopViewabilityTracker() {
    }

    private WebAdPresenter() throws IllegalAccessException {
        throw new IllegalAccessException("Use the parametrized constructor for creating a WebAdPresenter!");
    }

    public WebAdPresenter(Advertisement advertisement2, Placement placement2, Storage storage2, File file, String str) {
        this.advertisement = advertisement2;
        this.storage = storage2;
        this.assetDir = file;
        this.placement = placement2;
        this.userID = str;
    }

    public void setEventListener(AdvertisementPresenter.EventListener eventListener) {
        this.bus = eventListener;
    }

    public void setDirectDownloadAdapter(DirectDownloadAdapter directDownloadAdapter2) {
        this.directDownloadAdapter = directDownloadAdapter2;
    }

    public void reportError(String str) {
        this.report.recordError(str);
    }

    public void reportAction(String str, @Nullable String str2) {
        this.report.recordAction(str, str2, System.currentTimeMillis());
        this.storage.save(this.report);
    }

    public WebViewClient getWebViewClient() {
        return this.webClient;
    }

    public void notifyPropertiesChanged() {
        this.adView.updateWindow(this.advertisement.getTemplateType().equals("flexview"));
        this.webClient.notifyPropertiesChange(true);
    }

    public void attach(AdView adView2) {
        this.isDestroying.set(false);
        this.adView = adView2;
        int settings = this.advertisement.getAdConfig().getSettings();
        if (settings > 0) {
            this.muted = (settings & 1) == 1;
            this.backEnabled = (settings & 2) == 2;
            this.directDownloadApkEnabled = (settings & 32) == 32;
        }
        int i = 4;
        if ((this.advertisement.getAdConfig().getSettings() & 16) != 16) {
            switch (this.advertisement.getOrientation()) {
                case 0:
                    i = 1;
                    break;
                case 1:
                    i = 0;
                    break;
                case 2:
                    break;
                default:
                    i = -1;
                    break;
            }
        }
        adView2.setOrientation(i);
    }

    public void prepare(Bundle bundle) {
        String str = null;
        if (this.bus != null) {
            this.bus.onNext("start", (String) null);
        }
        boolean z = false;
        if (bundle != null) {
            this.prepared = bundle.getBoolean(EXTRA_CONTENT_PREPARED, false);
        }
        loadMraid(new File(this.assetDir.getPath() + File.separator + Advertisement.KEY_TEMPLATE));
        Cookie cookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        if ("flexview".equals(this.advertisement.getTemplateType()) && this.advertisement.getAdConfig().getFlexViewCloseTime() > 0) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    WebAdPresenter.this.report.recordAction("mraidCloseByTimer", "", currentTimeMillis);
                    WebAdPresenter.this.report.recordAction("mraidClose", "", currentTimeMillis);
                    WebAdPresenter.this.storage.save(WebAdPresenter.this.report);
                    WebAdPresenter.this.closeView();
                }
            }, (long) (this.advertisement.getAdConfig().getFlexViewCloseTime() * 1000));
        }
        if (cookie != null) {
            str = cookie.getString("userID");
        }
        this.report = new Report(this.advertisement, this.placement, System.currentTimeMillis(), str);
        this.webClient = new VungleWebClient(this.advertisement, this.placement, this.directDownloadAdapter);
        this.webClient.setMRAIDDelegate(this);
        Cookie cookie2 = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie2 != null) {
            if (cookie2.getBoolean("is_country_data_protected").booleanValue() && EnvironmentCompat.MEDIA_UNKNOWN.equals(cookie2.getString("consent_status"))) {
                z = true;
            }
            this.webClient.setConsentStatus(z, cookie2.getString("consent_title"), cookie2.getString("consent_message"), cookie2.getString("button_accept"), cookie2.getString("button_deny"));
            if (z) {
                cookie2.putValue("consent_status", "opted_out_by_timeout");
                this.storage.save(cookie2);
            }
        }
        int showCloseDelay = this.advertisement.getShowCloseDelay(this.placement.isIncentivized());
        if (showCloseDelay > 0) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    boolean unused = WebAdPresenter.this.backEnabled = true;
                }
            }, (long) showCloseDelay);
        } else {
            this.backEnabled = true;
        }
        this.adView.updateWindow(this.advertisement.getTemplateType().equals("flexview"));
    }

    private void loadMraid(File file) {
        File file2 = new File(file.getParent() + File.separator + "templateUnzip");
        if (!this.prepared) {
            try {
                UnzipUtility.unzip(file.getPath(), file2.getPath());
                File file3 = new File(file2.getPath() + File.separator + "mraid.js");
                if (!file3.exists()) {
                    if (this.bus != null) {
                        this.bus.onError(new VungleException(10));
                    }
                    closeView();
                    return;
                }
                try {
                    PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file3, true)));
                    printWriter.println("!function(e){\"use strict\";var t,r=e.vungle=e.vungle||{};r.error||(t=r.error=r.error||{},t.bridgeError={BRG000:\"Missing command in mraidBridge.executeSDKCommand method call!\",BRG001:\"Vungle SDK is not ready to process MRAID command!\"},t.bridgeExtError={BRX000:\"Cannot retrieve #dynamic container in Ad Unit!\"},t.mraidError={MRD000:\"Cannot add listener for unknown MRAID event!\",MRD001:\"Missing argument(s)! Both event and/or listener are required for method call!\",MRD002:\"Unable to find listener registered for event!\",MRD003:\"Missing MRAID event! Cannot remove event listener!\",MRD004:\"Missing object! An expand properties object is required for method call!\",MRD005:\"Missing object! An orientation properties object is required for method call!\",MRD006:\"Missing object! An resize properties object is required for method call!\",MRD007:\"Missing URL! A URL is required for method call!\",MRD008:\"Ad unit is hidden and cannot be closed!\",MRD009:\"Missing URL! A video/caption URL is required for method call!\",MRD010:\"Ad Unit is not viewable! Please make sure isViewAble is set to true!\",MRD011:\"Ad unit can only be expanded from the default or resized state!\",MRD012:\"Ad unit can only be resized from the default or resized state!\",MRD013:\"Missing URI! A valid URI is required for method call!\",MRD015:\"Invalid data/type detected when updating MRAID properties!\",MRD016:\"Missing app store id! An app store id is required for method call!\"},t.mraidClientError={MRC000:\"MRAID SDK error detected!\",MRC001:\"Missing MRAID object in window!\",MRC002:\"Missing video URL!  mraidClient.playVideo cannot retrieve video URL from arguments!\"},t.adTemplateError={ADT000:\"Missing page template JavaScript!\",ADT001:\"Error encountered loading template configuration!\"},t.gestureTrackingError={GET000:\"Cannot serialize user interaction tracking event object!\"})}(window),function(e){\"use strict\";var t=e.vungle=e.vungle||{},r=t.mraidCore=t.mraidCore||{},n=t.debugLog=t.debugLog||[];n&&n.push(\"vungle.mraidCore loaded.\"),r.consts={versions:{V1:\"1.0\",V2:\"2.0\"},states:{LOADING:\"loading\",DEFAULT:\"default\",RESIZED:\"resized\",EXPANDED:\"expanded\",HIDDEN:\"hidden\"},events:{INFO:\"info\",READY:\"ready\",ERROR:\"error\",STATE_CHANGE:\"stateChange\",ORIENTATION_CHANGE:\"orientationChange\",VIEWABLE_CHANGE:\"viewableChange\",SIZE_CHANGE:\"sizeChange\"},placements:{UNKNOWN:\"unknown\",INLINE:\"inline\",INTERSTITIAL:\"interstitial\"},orientations:{PORTRAIT:\"portrait\",LANDSCAPE:\"landscape\",NONE:\"none\"},closePositions:{CENTER:\"center\",TOP_LEFT:\"top-left\",TOP_CENTER:\"top-center\",TOP_RIGHT:\"top-right\",BOTTOM_LEFT:\"bottom-left\",BOTTOM_CENTER:\"bottom-center\",BOTTOM_RIGHT:\"bottom-right\"}},r.PropertiesValueObject=function(e){var t=function(e){var t;return e&&\"object\"==typeof e?(t={},Object.keys(e).forEach(function(r){t[r]=e[r]})):t=e,t};this.value=t(e),this.clone=function(){return t(this.value)},this.update=function(e){if(e&&\"object\"==typeof e){var t=this;Object.keys(e).forEach(function(r){t.value[r]=e[r]})}else this.value=e}},r.EventListeners=function(e){var t={};this.event=e,this.listenerCount=0,this.add=function(e){var r=String(e);return!t[r]&&(t[r]=e,this.listenerCount++,!0)},this.remove=function(e){var r=String(e);return!(!t.hasOwnProperty(r)||!t[r])&&(t[r]=null,delete t[r],this.listenerCount--,!0)},this.removeAll=function(){var e=this;Object.keys(t).forEach(function(r){e.remove(t[r])})},this.broadcast=function(e){var r=this;Object.keys(t).forEach(function(n){t[n].apply(r.mraid,e)})}};var o=new r.PropertiesValueObject(r.consts.versions.V1),i=new r.PropertiesValueObject({width:0,height:0}),a=new r.PropertiesValueObject({width:0,height:0}),s=new r.PropertiesValueObject({x:0,y:0,width:0,height:0}),d=new r.PropertiesValueObject({x:0,y:0,width:0,height:0}),c=new r.PropertiesValueObject({width:0,height:0,useCustomClose:!1,isModal:!0}),u=new r.PropertiesValueObject({width:0,height:0,offsetX:0,offsetY:0,customClosePosition:r.consts.closePositions.TOP_RIGHT,allowOffscreen:!0}),l=new r.PropertiesValueObject({allowOrientationChange:!0,forceOrientation:r.consts.orientations.NONE}),p=new r.PropertiesValueObject({sms:!1,tel:!1,calendar:!1,storePicture:!1,inlineVideo:!1}),m=new r.PropertiesValueObject(r.consts.states.LOADING),g=new r.PropertiesValueObject(r.consts.placements.UNKNOWN),f=new r.PropertiesValueObject((!1)),E=new r.PropertiesValueObject((!1)),v=new r.PropertiesValueObject((!1)),R=new r.PropertiesValueObject((!1)),y=new r.PropertiesValueObject((!1)),h=new r.PropertiesValueObject((!1)),C=new r.PropertiesValueObject((!1)),D=new r.PropertiesValueObject((!1)),B=new r.PropertiesValueObject((!1)),x=new r.PropertiesValueObject((!1)),P={},O=new r.PropertiesValueObject(\"\"),S=new r.PropertiesValueObject(\"\");r.eventListeners=P,r.propertiesHandlers={os:{update:function(e){O.update(e)},clone:function(){return O.clone()}},osVersion:{update:function(e){S.update(e)},clone:function(){return S.clone()}},incentivized:{update:function(e){E.update(e)},clone:function(){return E.clone()}},consentRequired:{update:function(e){v.update(e)},clone:function(){return v.clone()}},consentTitleText:{update:function(e){R.update(e)},clone:function(){return R.clone()}},consentBodyText:{update:function(e){y.update(e)},clone:function(){return y.clone()}},consentAcceptButtonText:{update:function(e){h.update(e)},clone:function(){return h.clone()}},consentDenyButtonText:{update:function(e){C.update(e)},clone:function(){return C.clone()}},version:{update:function(e){o.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting MRAID version to: \"+String(e))},clone:function(){return o.clone()}},maxSize:{update:function(e){i.update(e),c.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting maxSize to: \"+String(e))},clone:function(){return i.clone()}},screenSize:{update:function(e){var t;a.update(e),t=a.clone(),r.broadcastEvent(r.consts.events.INFO,\"Setting screenSize to: \"+String(e)),r.broadcastEvent(r.consts.events.SIZE_CHANGE,t.width,t.height)},clone:function(){return a.clone()}},defaultPosition:{update:function(e){s.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting defaultPosition to: \"+String(e))},clone:function(){return s.clone()}},currentPosition:{update:function(e){d.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting currentPosition to: \"+String(e))},clone:function(){return d.clone()}},expandProperties:{update:function(e){c.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting expandProperties to: \"+String(e))},clone:function(){return c.clone()}},resizeProperties:{update:function(e){u.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting resizeProperties to: \"+String(e))},clone:function(){return u.clone()}},orientationProperties:{update:function(e){l.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting orientationProperties to: \"+String(e))},clone:function(){return l.clone()}},supports:{update:function(e){p.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting supports to: \"+String(e))},clone:function(){return p.clone()}},state:{update:function(e){m.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting state to: \"+String(e)),r.broadcastEvent(r.consts.events.STATE_CHANGE,m.clone()),m.clone()===r.consts.states.DEFAULT&&r.broadcastEvent(r.consts.events.READY)},clone:function(){return m.clone()}},placementType:{update:function(e){g.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting placementType to: \"+String(e))},clone:function(){return g.clone()}},isViewable:{update:function(e){f.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting isViewable to: \"+String(e)),r.broadcastEvent(r.consts.events.VIEWABLE_CHANGE,f.clone())},clone:function(){return f.clone()}},customClose:{update:function(e){D.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting useCustomClose to: \"+String(e))},clone:function(){return D.clone()}},customPrivacy:{update:function(e){B.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting useCustomPrivacy to: \"+String(e))},clone:function(){return B.clone()}},enableBackImmediately:{update:function(e){x.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting enableBackImmediately to: \"+String(e))},clone:function(){return x.clone()}}},r.propertiesValidator=function(e,r,n){var o=!0;return typeof e!=typeof r&&\"useCustomClose\"!==n?(o=!1,t.mraidBridgeExt&&t.mraidBridgeExt.notifyError([\"MRD015\",n,e].join(\":\"))):\"object\"==typeof e&&Object.keys(e).forEach(function(e){e in r||(o=!1,t.mraidBridgeExt&&t.mraidBridgeExt.notifyError([\"MRD015\",n,e].join(\":\")))}),o},r.broadcastEvent=function(){var e=Array.prototype.slice.call(arguments),t=e.shift(),r=P[t];r&&r.broadcast(e)},r.isValidMARIDEvent=function(e){for(var t in r.consts.events)if(r.consts.events[t]===e)return!0;return!1}}(window),function(e){\"use strict\";var t=e.vungle=e.vungle||{},r=t.mraidBridge=t.mraidBridge||{};r.notifyContainer=function(t){e.location=t}}(window),function(e){\"use strict\";var t,r=e.vungle=e.vungle||{},n=r.mraidBridge=r.mraidBridge||{},o=r.mraidBridgeExt=r.mraidBridgeExt||{},i=r.debugLog=r.debugLog||[],a=!1,s=!1,d=[],c=!1,u=!1,l=!1,p=!1,m=r.mraidCore.broadcastEvent,g=r.mraidCore.consts.states,f=r.mraidCore.consts.events;i&&i.push(\"vungle.mraidBridge loaded.\"),o.getReplacementTokens=function(){return t},o.getIsVungleAd=function(){return c},o.getIsDirectDownload=function(){return u},o.getIsDisplayIAP=function(){return l},o.getIsInstalled=function(){return p},o.getEnableBackButtonImmediately=function(){return r.mraidCore.propertiesHandlers.enableBackImmediately.clone()},n.notifyPropertiesChange=function(e,t){i&&i.push(\"mraidBridge.notifyPropertiesChange:\"+JSON.stringify(e)),Object.keys(e).forEach(function(t){r.mraidCore.propertiesHandlers[t]?r.mraidCore.propertiesHandlers[t].update(e[t]):n.notifyErrorEvent(\"notifyPropertiesChange\",\"MRD015: Unhandled Property received - \"+t+\" - \"+e[t])}),\"undefined\"!=typeof t&&t===!0&&r.mraidBridge.notifyContainer(\"mraid://propertiesChangeCompleted\")},n.notifyCommandComplete=function(){i&&i.push(\"mraidBridge.notifyCommandComplete\");var e=\"\";d.length?(e=d.shift(),r.mraidBridge.notifyContainer(e)):s=!1},n.notifyReadyEvent=function(e){i&&i.push(\"mraidBridge.notifyReadyEvent\");var n=r.mraidCore.propertiesHandlers.state.clone();a=!0,\"undefined\"!=typeof e?(c=!0,p=e.isInstalled,l=e.isDisplayIAP,u=e.isDirectDownload,t=e):r.mraidBridgeExt.notifySuccessfulViewAd(),n!==g.DEFAULT?r.mraidCore.propertiesHandlers.state.update(g.DEFAULT):m(f.READY)},n.notifyErrorEvent=function(e,t){i&&i.push(\"mraidBridge.notifyErrorEvent:\"+e+\":\"+t),m(f.ERROR,e,t)},n.executeSDKCommand=function(){var e,t,o,c=\"\";if(!a)throw n.notifyErrorEvent(c,r.error.bridgeError.BRG001),{name:\"VungleMRAIDBridgeException\",message:\"BRG001\"};if(!arguments)throw n.notifyErrorEvent(c,r.error.bridgeError.BRG000),{name:\"VungleMRAIDBridgeException\",message:\"BRG000\"};for(c+=\"mraid://\"+arguments[0],o=1;o<arguments.length;o+=2)e=arguments[o],t=arguments[o+1],c+=(1===o?\"?\":\"&\")+encodeURIComponent(e),\"undefined\"!=typeof t&&(c+=\"=\"+encodeURIComponent(t));s?d.push(c):(s=!0,r.mraidBridge.notifyContainer(c)),i&&i.push(\"mraidBridge.executeSDKCommand: \"+c)},o.fireVideoCompleteEvent=function(){i&&i.push(\"mraidBridgeExt.fireVideoCompleteEvent\");var t=e.document.querySelector(\"#dynamic\"),r=new e.Event(\"vungle.events.video.ended\");t?t.dispatchEvent(r):o.notifyError(e.vungle.error.bridgeExtError.BRX000)},o.notifyPrepareStoreViewSuccess=function(){i&&i.push(\"mraidBridgeExt.notifyPrepareStoreViewSuccess\");var t=e.document.querySelector(\"#dynamic\"),r=new e.Event(\"vungle.events.preparestore.success\");t?t.dispatchEvent(r):o.notifyError(e.vungle.error.bridgeExtError.BRX000)},o.notifyError=function(t){i&&i.push(\"mraidBridgeExt.notifyError:\"+t);try{r.mraidBridge.executeSDKCommand(\"error\",\"code\",t)}catch(n){\"VungleMRAIDBridgeException\"===n.name?i&&i.push(\"%cVungleMRAIDBridgeException caught in mraidBridgeExt.notifyError! Message: %s\",\"color: red; font-size: x-large\",e.vungle.error.bridgeError[n.message]):i&&i.push(\"%cUnknown Exception caught in mraidBridgeExt.notifyError! Message: %s\",\"color: red; font-size: x-large\",n.message)}},o.notifyTPAT=function(e){i&&i.push(\"mraidBridgeExt.notifyTPATEvent:\"+e);try{r.mraidBridge.executeSDKCommand(\"tpat\",\"event\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifyTPAT\",t.message)}},o.consentAction=function(e){i&&i.push(\"mraidBridgeExt.consentActionEvent:\"+e);try{r.mraidBridge.executeSDKCommand(\"consentAction\",\"event\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.consentActionEvent\",t.message)}},o.notifyUserInteraction=function(e,t){i&&i.push(\"mraidBridgeExt.notifyUserInteraction\");try{r.mraidBridge.executeSDKCommand(\"action\",e,t)}catch(n){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifyUserInteraction\",n.message)}},o.notifyEventValuePairEvent=function(e,t){i&&i.push(\"mraidBridgeExt.notifyEventValuePairEvent\");var n=e||\"null\",o=t||\"null\";try{r.mraidBridge.executeSDKCommand(\"actionWithValue\",\"event\",n,\"value\",o)}catch(n){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifyEventValuePairEvent\",n.message)}},o.playHTML5Video=function(e){i&&i.push(\"mraidBridgeExt.playHTML5Video\");try{r.mraidBridge.executeSDKCommand(\"playHTML5Video\",\"selector\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.playHTML5Video\",t.message)}},o.openPrivacy=function(e){i&&i.push(\"mraidBridgeExt.openPrivacy\");try{r.mraidBridge.executeSDKCommand(\"openPrivacy\",\"url\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.openPrivacy\",t.message)}},o.requestMRAIDClose=function(){i&&i.push(\"mraidBridgeExt.requestMRAIDClose\");try{var e,t=\"windows\"===r.mraidExt.getOS()&&(0===r.mraidExt.getOSVersion().indexOf(\"WinPhone81\")||0===r.mraidExt.getOSVersion().indexOf(\"Win81\")),n=\"android\"===r.mraidExt.getOS()&&parseInt(r.mraidExt.getOSVersion(),10)<=17;t||n?(e=document.createEvent(\"Event\"),e.initEvent(\"vungle.events.request.close\",!0,!0)):e=new Event(\"vungle.events.request.close\"),c?document.querySelector(\"#dynamic\").dispatchEvent(e):r.mraidBridge.executeSDKCommand(\"close\")}catch(o){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.requestMRAIDClose\",o.message)}},o.notifySuccessfulViewAd=function(){i&&i.push(\"mraidBridgeExt.notifySuccessfulViewAd\");try{r.mraidBridge.executeSDKCommand(\"successfulView\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifySuccessfulViewAd\",e.message)}},o.openAppInDevice=function(){i&&i.push(\"mraidBridgeExt.openAppInDevice\");try{r.mraidBridge.executeSDKCommand(\"openAppInDevice\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.openAppInDevice\",e.message)}},o.openStorePage=function(){i&&i.push(\"mraidBridgeExt.openStorePage\");try{r.mraidBridge.executeSDKCommand(\"openStorePage\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.openStorePage\",e.message)}},o.cancelDownload=function(){i&&i.push(\"mraidBridgeExt.cancelDownload\");try{r.mraidBridge.executeSDKCommand(\"cancelDownload\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.cancelDownload\",e.message)}},o.getInstallationStatus=function(t){i&&i.push(\"mraidBridgeExt.getInstallationStatus\");var r=e.document.querySelector(\"#dynamic\"),n=new e.CustomEvent(\"vungle.events.installationStatus.updated\",{detail:t});r?r.dispatchEvent(n):o.notifyError(e.vungle.error.bridgeExtError.BRX000)},o.startDownloadAppOnDevice=function(){i&&i.push(\"mraidBridgeExt.startDownloadAppOnDevice\");try{r.mraidBridge.executeSDKCommand(\"startDownloadAppOnDevice\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.startDownloadAppOnDevice\",e.message)}}}(window),function(e){\"use strict\";var t=e.vungle=e.vungle||{},r=e.mraid=t.mraid=t.mraid||{},n=t.mraidExt=t.mraidExt||{},o=t.debugLog=t.debugLog||[],i=t.mraidBridge.executeSDKCommand,a=t.mraidCore.broadcastEvent,s=t.mraidCore.propertiesHandlers,d=t.mraidCore.propertiesValidator,c=t.mraidCore.consts.states,u=t.mraidCore.consts.events,l=t.mraidCore.consts.placements;o&&o.push(\"vungle.mraid loaded.\"),r.addEventListener=function(e,r){e&&r?t.mraidCore.isValidMARIDEvent(e)?(t.mraidCore.eventListeners[e]||(t.mraidCore.eventListeners[e]=new t.mraidCore.EventListeners(e)),t.mraidCore.eventListeners[e].add(r)):a(u.ERROR,\"mraid.addEventListener\",t.error.mraidError.MRD000+\":\"+e):a(u.ERROR,\"mraid.addEventListener\",t.error.mraidError.MRD001)},r.removeEventListener=function(e,r){var n,o=!1;e?(n=t.mraidCore.eventListeners[e],r?(n&&(o=n.remove(r)),o||a(u.ERROR,\"mraid.removeEventListener\",t.error.mraidError.MRD002+\":\"+e)):n&&t.mraidCore.eventListeners.removeAll(),n&&0===n.count&&(t.mraidCore.eventListeners[e]=null,delete t.mraidCore.eventListeners[e])):a(u.ERROR,\"mraid.removeEventListener\",t.error.mraidError.MRD003)},n.prepareStoreView=function(e){e?i(\"prepareStoreView\",\"appStoreId\",e):a(u.ERROR,\"mraid.prepareStoreView\",t.error.mraidError.MRD016)},n.presentStoreView=function(e){e?i(\"presentStoreView\",\"appStoreId\",e):a(u.ERROR,\"mraid.presentStoreView\",t.error.mraidError.MRD016)},n.getOS=function(){return s.os.clone()},n.getOSVersion=function(){return s.osVersion.clone()},n.getIncentivized=function(){return s.incentivized.clone()},r.getVersion=function(){return s.version.clone()},r.getMaxSize=function(){return s.maxSize.clone()},r.getScreenSize=function(){return s.screenSize.clone()},r.getDefaultPosition=function(){return s.defaultPosition.clone()},r.getCurrentPosition=function(){return s.currentPosition.clone()},r.getExpandProperties=function(){return s.expandProperties.clone()},r.getResizeProperties=function(){return s.resizeProperties.clone()},r.getOrientationProperties=function(){return s.orientationProperties.clone()},r.supports=function(e){return s.supports.clone()[e]},r.getState=function(){return s.state.clone()},r.getPlacementType=function(){return s.placementType.clone()},r.getConsentRequired=function(){return s.consentRequired.clone()},r.getConsentTitleText=function(){return s.consentTitleText.clone()},r.getConsentBodyText=function(){return s.consentBodyText.clone()},r.getConsentAcceptButtonText=function(){return s.consentAcceptButtonText.clone()},r.getConsentDenyButtonText=function(){return s.consentDenyButtonText.clone()},r.isViewable=function(){return s.isViewable.clone()},r.getViewable=r.isViewable,r.setExpandProperties=function(e){var r={};\"object\"==typeof e?(Object.keys(e).forEach(function(t){\"isModal\"!==t&&(r[t]=e[t])}),d(r,s.expandProperties.clone(),\"expandProperties\")&&s.expandProperties.update(r)):(a(u.ERROR,\"mraid.setExpandProperties\",t.error.mraidError.MRD004),t.mraidBridgeExt&&t.mraidBridgeExt.notifyError(\"MRD004\"))},r.setOrientationProperties=function(e){var r;\"object\"==typeof e?(r=[\"setOrientationProperties\",\"allowOrientationChange\",e.allowOrientationChange,\"forceOrientation\",e.forceOrientation],d(e,s.orientationProperties.clone(),\"orientationProperties\")&&(s.orientationProperties.update(e),i.apply(null,r))):(a(u.ERROR,\"mraid.setOrientationProperties\",t.error.mraidError.MRD005),t.mraidBridgeExt&&t.mraidBridgeExt.notifyError(\"MRD005\"))},r.setResizeProperties=function(e){\"object\"==typeof e?d(e,s.resizeProperties.clone(),\"resizeProperties\")&&s.resizeProperties.update(e):(a(u.ERROR,\"mraid.setResizeProperties\",t.error.mraidError.MRD006),t.mraidBridgeExt&&t.mraidBridgeExt.notifyError(\"MRD006\"))},r.useCustomClose=function(e){var t;d(e,s.customClose.clone(),\"useCustomClose\")&&d({useCustomClose:e},s.expandProperties.clone(),\"expandProperties\")&&(s.customClose.update(e),s.expandProperties.update({useCustomClose:e}),t=e===!0?\"invisible\":e===!1?\"visible\":void 0===e?\"gone\":\"\",i(\"useCustomClose\",\"sdkCloseButton\",t))},n.useCustomPrivacy=function(e){d(e,s.customPrivacy.clone(),\"useCustomPrivacy\")&&(s.customPrivacy.update(e),i(\"useCustomPrivacy\",\"useCustomPrivacy\",e))},r.open=function(e){e?i(\"open\",\"url\",encodeURI(e)):a(u.ERROR,\"mraid.open\",t.error.mraidError.MRD007)},r.close=function(){r.getState()!==c.HIDDEN?i(\"close\"):a(u.ERROR,\"mraid.close\",t.error.mraidError.MRD008)},r.playVideo=function(){var e=arguments&&arguments[0]?arguments[0]:\"\",n=arguments&&arguments[1]?arguments[1]:\"\";r.isViewable()?e.length>0?i(\"playVideo\",\"uri\",e,\"captionUrl\",n):a(u.ERROR,\"mraid.playVideo\",t.error.mraidError.MRD009):a(u.ERROR,\"mraid.playVideo\",t.error.mraidError.MRD010)},r.expand=function(e){var r=s.customClose.clone(),n=s.placementType.clone(),o=s.state.clone(),d=[\"expand\",\"useCustomClose\",r];n!==l.INLINE||o!==c.DEFAULT&&o!==c.RESIZED?a(u.ERROR,\"mraid.expand\",t.error.mraidError.MRD011):(e&&(arguments.push(\"url\"),arguments.push(encodeURI(e))),i.apply(null,d))},r.resize=function(){var e=s.resize.clone(),n=[\"resize\"],o=r.getState();o!==c.DEFAULT&&o!==c.RESIZED?(n.push(\"width\"),n.push(e.width),n.push(\"height\"),n.push(e.height),n.push(\"offsetX\"),n.push(e.offsetX),n.push(\"offsetY\"),n.push(e.offsetY),n.push(\"customClosePosition\"),n.push(e.customClosePosition),n.push(\"allowOffscreen\"),n.push(e.allowOffscreen),i.apply(null,n)):a(u.ERROR,\"mraid.resize\",t.error.mraidError.MRD012)},r.createCalendarEvent=function(){},r.storePicture=function(e){r.isViewable()?e?a(u.ERROR,\"mraid.storePicture\",t.error.mraidError.MRD013):i(\"storePicture\",\"uri\",e):a(u.ERROR,\"mraid.storePicture\",t.error.mraidError.MRD010)}}(window);");
                    printWriter.close();
                    this.prepared = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                closeView();
                return;
            }
        }
        File file4 = new File(file2.getPath() + File.separator + "index.html");
        if (!file4.exists()) {
            if (this.bus != null) {
                this.bus.onError(new VungleException(10));
            }
            this.adView.close();
            return;
        }
        AdView adView2 = this.adView;
        adView2.showWebsite("file://" + file4.getPath());
    }

    public void play() {
        setAdVisibility(true);
    }

    public void stop(boolean z, boolean z2) {
        setAdVisibility(false);
        if (!z && z2 && !this.isDestroying.getAndSet(true)) {
            String str = null;
            if (this.webClient != null) {
                this.webClient.setMRAIDDelegate((VungleWebClient.MRAIDDelegate) null);
            }
            if (this.bus != null) {
                AdvertisementPresenter.EventListener eventListener = this.bus;
                if (this.report.isCTAClicked()) {
                    str = "isCTAClicked";
                }
                eventListener.onNext("end", str);
            }
            if (this.directDownloadAdapter != null) {
                this.directDownloadAdapter.getSdkDownloadClient().cleanUp();
            }
        }
    }

    public void setAdVisibility(boolean z) {
        this.webClient.setAdVisibility(z);
    }

    public void generateSaveState(Bundle bundle) {
        if (bundle != null) {
            this.storage.save(this.report);
            bundle.putString(EXTRA_REPORT, this.report.getId());
            bundle.putBoolean(EXTRA_INCENTIVIZED_SENT, this.sendReportIncentivized.get());
            bundle.putBoolean(EXTRA_CONTENT_PREPARED, true);
        }
    }

    public void restoreFromSave(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(EXTRA_REPORT);
            this.report = TextUtils.isEmpty(string) ? null : (Report) this.storage.load(string, Report.class);
            boolean z = bundle.getBoolean(EXTRA_INCENTIVIZED_SENT, false);
            if (z) {
                this.sendReportIncentivized.set(z);
            }
            if (this.report == null) {
                this.adView.close();
            }
        }
    }

    public void onProgressUpdate(int i) {
        if (i == 100) {
            Advertisement.Checkpoint checkpoint = this.advertisement.getCheckpoints().get(this.advertisement.getCheckpoints().size() - 1);
            if (checkpoint.getPercentage() == 100) {
                for (String pingTPAT : checkpoint.getUrls()) {
                    VungleApiClient.pingTPAT(pingTPAT);
                }
            }
        }
    }

    public boolean handleExit(String str) {
        if (str == null) {
            if (this.backEnabled) {
                this.adView.showWebsite("javascript:window.vungle.mraidBridgeExt.requestMRAIDClose()");
            }
            return false;
        } else if (this.advertisement == null || this.placement == null) {
            Log.e(TAG, "Unable to close advertisement");
            return false;
        } else if (!this.placement.getId().equals(str)) {
            Log.e(TAG, "Cannot close FlexView Ad with invalid placement reference id");
            return false;
        } else if (!"flexview".equals(this.advertisement.getTemplateType())) {
            Log.e(TAG, "Cannot close a Non FlexView ad");
            return false;
        } else {
            this.adView.showWebsite("javascript:window.vungle.mraidBridgeExt.requestMRAIDClose()");
            reportAction("mraidCloseByApi", (String) null);
            return true;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0198, code lost:
        if (r8.equals("false") == false) goto L_0x01af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01ec, code lost:
        if (r8.equals(com.tapjoy.TJAdUnitConstants.String.VISIBLE) == false) goto L_0x0203;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x021e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01ca A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean processCommand(java.lang.String r8, com.google.gson.JsonObject r9) {
        /*
            r7 = this;
            int r0 = r8.hashCode()
            r1 = 2
            r2 = -1
            r3 = 0
            r4 = 1
            switch(r0) {
                case -1912374177: goto L_0x008c;
                case -1891064718: goto L_0x0081;
                case -1422950858: goto L_0x0077;
                case -1382780692: goto L_0x006c;
                case -735200587: goto L_0x0062;
                case -660787472: goto L_0x0058;
                case -511324706: goto L_0x004d;
                case -503430878: goto L_0x0042;
                case -348095344: goto L_0x0038;
                case 3417674: goto L_0x002e;
                case 3566511: goto L_0x0023;
                case 94756344: goto L_0x0018;
                case 1614272768: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0097
        L_0x000d:
            java.lang.String r0 = "useCustomClose"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 6
            goto L_0x0098
        L_0x0018:
            java.lang.String r0 = "close"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 0
            goto L_0x0098
        L_0x0023:
            java.lang.String r0 = "tpat"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 3
            goto L_0x0098
        L_0x002e:
            java.lang.String r0 = "open"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 5
            goto L_0x0098
        L_0x0038:
            java.lang.String r0 = "useCustomPrivacy"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 7
            goto L_0x0098
        L_0x0042:
            java.lang.String r0 = "cancelDownload"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 11
            goto L_0x0098
        L_0x004d:
            java.lang.String r0 = "openPrivacy"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 8
            goto L_0x0098
        L_0x0058:
            java.lang.String r0 = "consentAction"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 1
            goto L_0x0098
        L_0x0062:
            java.lang.String r0 = "actionWithValue"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 2
            goto L_0x0098
        L_0x006c:
            java.lang.String r0 = "startDownloadAppOnDevice"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 10
            goto L_0x0098
        L_0x0077:
            java.lang.String r0 = "action"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 4
            goto L_0x0098
        L_0x0081:
            java.lang.String r0 = "openAppInDevice"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 12
            goto L_0x0098
        L_0x008c:
            java.lang.String r0 = "successfulView"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0097
            r8 = 9
            goto L_0x0098
        L_0x0097:
            r8 = -1
        L_0x0098:
            r0 = 3178655(0x30809f, float:4.454244E-39)
            r5 = 0
            switch(r8) {
                case 0: goto L_0x03f7;
                case 1: goto L_0x03b6;
                case 2: goto L_0x028b;
                case 3: goto L_0x026f;
                case 4: goto L_0x026e;
                case 5: goto L_0x021f;
                case 6: goto L_0x01cb;
                case 7: goto L_0x0177;
                case 8: goto L_0x0155;
                case 9: goto L_0x00ca;
                case 10: goto L_0x00bc;
                case 11: goto L_0x00ae;
                case 12: goto L_0x00a0;
                default: goto L_0x009f;
            }
        L_0x009f:
            return r3
        L_0x00a0:
            com.vungle.warren.DirectDownloadAdapter r8 = r7.directDownloadAdapter
            if (r8 == 0) goto L_0x00ad
            com.vungle.warren.DirectDownloadAdapter r8 = r7.directDownloadAdapter
            com.vungle.warren.SDKDownloadClient r8 = r8.getSdkDownloadClient()
            r8.sendOpenPackageRequest()
        L_0x00ad:
            return r4
        L_0x00ae:
            com.vungle.warren.DirectDownloadAdapter r8 = r7.directDownloadAdapter
            if (r8 == 0) goto L_0x00bb
            com.vungle.warren.DirectDownloadAdapter r8 = r7.directDownloadAdapter
            com.vungle.warren.SDKDownloadClient r8 = r8.getSdkDownloadClient()
            r8.cancelDownloadRequest()
        L_0x00bb:
            return r4
        L_0x00bc:
            com.vungle.warren.DirectDownloadAdapter r8 = r7.directDownloadAdapter
            if (r8 == 0) goto L_0x00c9
            com.vungle.warren.DirectDownloadAdapter r8 = r7.directDownloadAdapter
            com.vungle.warren.SDKDownloadClient r8 = r8.getSdkDownloadClient()
            r8.sendDownloadRequest()
        L_0x00c9:
            return r4
        L_0x00ca:
            com.vungle.warren.presenter.AdvertisementPresenter$EventListener r8 = r7.bus
            if (r8 == 0) goto L_0x00d5
            com.vungle.warren.presenter.AdvertisementPresenter$EventListener r8 = r7.bus
            java.lang.String r9 = "successfulView"
            r8.onNext(r9, r5)
        L_0x00d5:
            com.vungle.warren.Storage r8 = r7.storage
            java.lang.String r9 = "configSettings"
            java.lang.Class<com.vungle.warren.model.Cookie> r0 = com.vungle.warren.model.Cookie.class
            com.vungle.warren.persistence.Memorable r8 = r8.load(r9, r0)
            com.vungle.warren.model.Cookie r8 = (com.vungle.warren.model.Cookie) r8
            com.vungle.warren.model.Placement r9 = r7.placement
            boolean r9 = r9.isIncentivized()
            if (r9 == 0) goto L_0x0154
            if (r8 == 0) goto L_0x0154
            java.lang.String r9 = "isReportIncentivizedEnabled"
            java.lang.Boolean r8 = r8.getBoolean(r9)
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0154
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.sendReportIncentivized
            boolean r8 = r8.getAndSet(r4)
            if (r8 != 0) goto L_0x0154
            com.google.gson.JsonObject r8 = new com.google.gson.JsonObject
            r8.<init>()
            java.lang.String r9 = "placement_reference_id"
            com.google.gson.JsonPrimitive r0 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Placement r1 = r7.placement
            java.lang.String r1 = r1.getId()
            r0.<init>((java.lang.String) r1)
            r8.add(r9, r0)
            java.lang.String r9 = "app_id"
            com.google.gson.JsonPrimitive r0 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Advertisement r1 = r7.advertisement
            java.lang.String r1 = r1.getAppID()
            r0.<init>((java.lang.String) r1)
            r8.add(r9, r0)
            java.lang.String r9 = "adStartTime"
            com.google.gson.JsonPrimitive r0 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Report r1 = r7.report
            long r1 = r1.getAdStartTime()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r0.<init>((java.lang.Number) r1)
            r8.add(r9, r0)
            java.lang.String r9 = "user"
            com.google.gson.JsonPrimitive r0 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Report r1 = r7.report
            java.lang.String r1 = r1.getUserID()
            r0.<init>((java.lang.String) r1)
            r8.add(r9, r0)
            retrofit2.Call r8 = com.vungle.warren.network.VungleApiClient.ri(r8)
            com.vungle.warren.presenter.WebAdPresenter$4 r9 = new com.vungle.warren.presenter.WebAdPresenter$4
            r9.<init>()
            r8.enqueue(r9)
        L_0x0154:
            return r4
        L_0x0155:
            java.lang.String r8 = "url"
            com.google.gson.JsonElement r8 = r9.get(r8)
            java.lang.String r8 = r8.getAsString()
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.VIEW"
            r9.<init>(r0)
            android.net.Uri r8 = android.net.Uri.parse(r8)
            r9.setData(r8)
            com.vungle.warren.ui.AdView r8 = r7.adView
            java.lang.String r9 = r9.toUri(r3)
            r8.open(r9)
            return r4
        L_0x0177:
            java.lang.String r8 = "useCustomPrivacy"
            com.google.gson.JsonElement r8 = r9.get(r8)
            java.lang.String r8 = r8.getAsString()
            int r9 = r8.hashCode()
            if (r9 == r0) goto L_0x01a5
            r0 = 3569038(0x36758e, float:5.001287E-39)
            if (r9 == r0) goto L_0x019b
            r0 = 97196323(0x5cb1923, float:1.9099262E-35)
            if (r9 == r0) goto L_0x0192
            goto L_0x01af
        L_0x0192:
            java.lang.String r9 = "false"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01af
            goto L_0x01b0
        L_0x019b:
            java.lang.String r9 = "true"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01af
            r1 = 1
            goto L_0x01b0
        L_0x01a5:
            java.lang.String r9 = "gone"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01af
            r1 = 0
            goto L_0x01b0
        L_0x01af:
            r1 = -1
        L_0x01b0:
            switch(r1) {
                case 0: goto L_0x01ca;
                case 1: goto L_0x01ca;
                case 2: goto L_0x01ca;
                default: goto L_0x01b3;
            }
        L_0x01b3:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown value "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r9.<init>(r8)
            throw r9
        L_0x01ca:
            return r4
        L_0x01cb:
            java.lang.String r8 = "sdkCloseButton"
            com.google.gson.JsonElement r8 = r9.get(r8)
            java.lang.String r8 = r8.getAsString()
            int r9 = r8.hashCode()
            r5 = -1901805651(0xffffffff8ea4bfad, float:-4.06137E-30)
            if (r9 == r5) goto L_0x01f9
            if (r9 == r0) goto L_0x01ef
            r0 = 466743410(0x1bd1f072, float:3.4731534E-22)
            if (r9 == r0) goto L_0x01e6
            goto L_0x0203
        L_0x01e6:
            java.lang.String r9 = "visible"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x0203
            goto L_0x0204
        L_0x01ef:
            java.lang.String r9 = "gone"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x0203
            r1 = 0
            goto L_0x0204
        L_0x01f9:
            java.lang.String r9 = "invisible"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x0203
            r1 = 1
            goto L_0x0204
        L_0x0203:
            r1 = -1
        L_0x0204:
            switch(r1) {
                case 0: goto L_0x021e;
                case 1: goto L_0x021e;
                case 2: goto L_0x021e;
                default: goto L_0x0207;
            }
        L_0x0207:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown value "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r9.<init>(r8)
            throw r9
        L_0x021e:
            return r4
        L_0x021f:
            java.lang.String r8 = "download"
            r7.reportAction(r8, r5)
            java.lang.String r8 = "mraidOpen"
            r7.reportAction(r8, r5)
            java.lang.String r8 = "mraidClose"
            r7.reportAction(r8, r5)
            r7.closeView()
            java.lang.String r8 = "url"
            com.google.gson.JsonElement r8 = r9.get(r8)
            java.lang.String r8 = r8.getAsString()
            com.vungle.warren.model.Advertisement r9 = r7.advertisement
            java.lang.String r9 = r9.getCTAURL(r4)
            com.vungle.warren.network.VungleApiClient.pingTPAT(r9)
            boolean r9 = r7.directDownloadApkEnabled
            com.vungle.warren.model.Advertisement r0 = r7.advertisement
            boolean r0 = r0.isRequiresNonMarketInstall()
            boolean r9 = com.vungle.warren.download.APKDirectDownloadManager.isDirectDownloadEnabled(r9, r0)
            if (r9 == 0) goto L_0x0256
            com.vungle.warren.download.APKDirectDownloadManager.download(r8)
            goto L_0x026d
        L_0x0256:
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.VIEW"
            r9.<init>(r0)
            android.net.Uri r8 = android.net.Uri.parse(r8)
            r9.setData(r8)
            com.vungle.warren.ui.AdView r8 = r7.adView
            java.lang.String r9 = r9.toUri(r3)
            r8.open(r9)
        L_0x026d:
            return r4
        L_0x026e:
            return r4
        L_0x026f:
            java.lang.String r8 = "event"
            com.google.gson.JsonElement r8 = r9.get(r8)
            java.lang.String r8 = r8.getAsString()
            com.vungle.warren.model.Advertisement r9 = r7.advertisement
            java.lang.String[] r8 = r9.getTpatUrls(r8)
            int r9 = r8.length
        L_0x0280:
            if (r3 >= r9) goto L_0x028a
            r0 = r8[r3]
            com.vungle.warren.network.VungleApiClient.pingTPAT(r0)
            int r3 = r3 + 1
            goto L_0x0280
        L_0x028a:
            return r4
        L_0x028b:
            java.lang.String r8 = "event"
            com.google.gson.JsonElement r8 = r9.get(r8)
            java.lang.String r8 = r8.getAsString()
            java.lang.String r0 = "value"
            com.google.gson.JsonElement r9 = r9.get(r0)
            java.lang.String r9 = r9.getAsString()
            com.vungle.warren.model.Report r0 = r7.report
            long r1 = java.lang.System.currentTimeMillis()
            r0.recordAction(r8, r9, r1)
            com.vungle.warren.Storage r0 = r7.storage
            com.vungle.warren.model.Report r1 = r7.report
            r0.save(r1)
            java.lang.String r0 = "videoViewed"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x03a2
            float r0 = r7.duration
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x03a2
            float r0 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x02cb }
            float r1 = r7.duration     // Catch:{ NumberFormatException -> 0x02cb }
            float r0 = r0 / r1
            r1 = 1120403456(0x42c80000, float:100.0)
            float r0 = r0 * r1
            int r0 = (int) r0
            goto L_0x02d3
        L_0x02cb:
            java.lang.String r0 = TAG
            java.lang.String r1 = "value for videoViewed is null !"
            android.util.Log.e(r0, r1)
            r0 = 0
        L_0x02d3:
            if (r0 <= 0) goto L_0x03a2
            com.vungle.warren.presenter.AdvertisementPresenter$EventListener r1 = r7.bus
            if (r1 == 0) goto L_0x02ef
            com.vungle.warren.presenter.AdvertisementPresenter$EventListener r1 = r7.bus
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = "percentViewed:"
            r2.append(r6)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.onNext(r2, r5)
        L_0x02ef:
            boolean r1 = r7.hasSendStart
            if (r1 != 0) goto L_0x0306
            if (r0 <= r4) goto L_0x0306
            r7.hasSendStart = r4
            com.vungle.warren.DirectDownloadAdapter r1 = r7.directDownloadAdapter
            if (r1 == 0) goto L_0x0306
            com.vungle.warren.DirectDownloadAdapter r1 = r7.directDownloadAdapter
            com.vungle.warren.SDKDownloadClient r1 = r1.getSdkDownloadClient()
            com.vungle.warren.DirectDownloadAdapter$CONTRACT_TYPE r2 = com.vungle.warren.DirectDownloadAdapter.CONTRACT_TYPE.CPI
            r1.sendADDisplayingNotify(r3, r2)
        L_0x0306:
            boolean r1 = r7.hasSend80Percent
            if (r1 != 0) goto L_0x031f
            r1 = 80
            if (r0 <= r1) goto L_0x031f
            r7.hasSend80Percent = r4
            com.vungle.warren.DirectDownloadAdapter r1 = r7.directDownloadAdapter
            if (r1 == 0) goto L_0x031f
            com.vungle.warren.DirectDownloadAdapter r1 = r7.directDownloadAdapter
            com.vungle.warren.SDKDownloadClient r1 = r1.getSdkDownloadClient()
            com.vungle.warren.DirectDownloadAdapter$CONTRACT_TYPE r2 = com.vungle.warren.DirectDownloadAdapter.CONTRACT_TYPE.CPI
            r1.sendADDisplayingNotify(r4, r2)
        L_0x031f:
            com.vungle.warren.Storage r1 = r7.storage
            java.lang.String r2 = "configSettings"
            java.lang.Class<com.vungle.warren.model.Cookie> r3 = com.vungle.warren.model.Cookie.class
            com.vungle.warren.persistence.Memorable r1 = r1.load(r2, r3)
            com.vungle.warren.model.Cookie r1 = (com.vungle.warren.model.Cookie) r1
            com.vungle.warren.model.Placement r2 = r7.placement
            boolean r2 = r2.isIncentivized()
            if (r2 == 0) goto L_0x03a2
            r2 = 75
            if (r0 <= r2) goto L_0x03a2
            if (r1 == 0) goto L_0x03a2
            java.lang.String r0 = "isReportIncentivizedEnabled"
            java.lang.Boolean r0 = r1.getBoolean(r0)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x03a2
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.sendReportIncentivized
            boolean r0 = r0.getAndSet(r4)
            if (r0 != 0) goto L_0x03a2
            com.google.gson.JsonObject r0 = new com.google.gson.JsonObject
            r0.<init>()
            java.lang.String r1 = "placement_reference_id"
            com.google.gson.JsonPrimitive r2 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Placement r3 = r7.placement
            java.lang.String r3 = r3.getId()
            r2.<init>((java.lang.String) r3)
            r0.add(r1, r2)
            java.lang.String r1 = "app_id"
            com.google.gson.JsonPrimitive r2 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Advertisement r3 = r7.advertisement
            java.lang.String r3 = r3.getAppID()
            r2.<init>((java.lang.String) r3)
            r0.add(r1, r2)
            java.lang.String r1 = "adStartTime"
            com.google.gson.JsonPrimitive r2 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Report r3 = r7.report
            long r5 = r3.getAdStartTime()
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r2.<init>((java.lang.Number) r3)
            r0.add(r1, r2)
            java.lang.String r1 = "user"
            com.google.gson.JsonPrimitive r2 = new com.google.gson.JsonPrimitive
            com.vungle.warren.model.Report r3 = r7.report
            java.lang.String r3 = r3.getUserID()
            r2.<init>((java.lang.String) r3)
            r0.add(r1, r2)
            retrofit2.Call r0 = com.vungle.warren.network.VungleApiClient.ri(r0)
            com.vungle.warren.presenter.WebAdPresenter$3 r1 = new com.vungle.warren.presenter.WebAdPresenter$3
            r1.<init>()
            r0.enqueue(r1)
        L_0x03a2:
            java.lang.String r0 = "videoLength"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x03b0
            float r8 = java.lang.Float.parseFloat(r9)
            r7.duration = r8
        L_0x03b0:
            com.vungle.warren.ui.AdView r8 = r7.adView
            r8.setVisibility(r4)
            return r4
        L_0x03b6:
            com.vungle.warren.Storage r8 = r7.storage
            java.lang.String r0 = "consentIsImportantToVungle"
            java.lang.Class<com.vungle.warren.model.Cookie> r1 = com.vungle.warren.model.Cookie.class
            com.vungle.warren.persistence.Memorable r8 = r8.load(r0, r1)
            com.vungle.warren.model.Cookie r8 = (com.vungle.warren.model.Cookie) r8
            if (r8 != 0) goto L_0x03cb
            com.vungle.warren.model.Cookie r8 = new com.vungle.warren.model.Cookie
            java.lang.String r0 = "consentIsImportantToVungle"
            r8.<init>((java.lang.String) r0)
        L_0x03cb:
            java.lang.String r0 = "event"
            com.google.gson.JsonElement r9 = r9.get(r0)
            java.lang.String r9 = r9.getAsString()
            java.lang.String r0 = "consent_status"
            r8.putValue(r0, r9)
            java.lang.String r9 = "consent_source"
            java.lang.String r0 = "vungle_modal"
            r8.putValue(r9, r0)
            java.lang.String r9 = "timestamp"
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r8.putValue(r9, r0)
            com.vungle.warren.Storage r9 = r7.storage
            r9.save(r8)
            return r4
        L_0x03f7:
            java.lang.String r8 = "mraidClose"
            r7.reportAction(r8, r5)
            r7.closeView()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.presenter.WebAdPresenter.processCommand(java.lang.String, com.google.gson.JsonObject):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[SYNTHETIC, Splitter:B:19:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleAction(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -314498168(0xffffffffed412388, float:-3.7358476E27)
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x002a
            r1 = 94756344(0x5a5ddf8, float:1.5598064E-35)
            if (r0 == r1) goto L_0x0020
            r1 = 1427818632(0x551ac888, float:1.06366291E13)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "download"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "close"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "privacy"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            switch(r0) {
                case 0: goto L_0x007d;
                case 1: goto L_0x004f;
                case 2: goto L_0x0080;
                default: goto L_0x0038;
            }
        L_0x0038:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown action "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L_0x004f:
            com.vungle.warren.model.Advertisement r5 = r4.advertisement     // Catch:{ ActivityNotFoundException -> 0x0079 }
            java.lang.String r5 = r5.getCTAURL(r2)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            com.vungle.warren.network.VungleApiClient.pingTPAT(r5)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            android.content.Intent r5 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x0079 }
            java.lang.String r0 = "android.intent.action.VIEW"
            r5.<init>(r0)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            com.vungle.warren.model.Advertisement r0 = r4.advertisement     // Catch:{ ActivityNotFoundException -> 0x0079 }
            java.lang.String r0 = r0.getCTAURL(r3)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            android.net.Uri r0 = android.net.Uri.parse(r0)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            r5.setData(r0)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            com.vungle.warren.ui.AdView r0 = r4.adView     // Catch:{ ActivityNotFoundException -> 0x0079 }
            java.lang.String r5 = r5.toUri(r3)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            r0.open(r5)     // Catch:{ ActivityNotFoundException -> 0x0079 }
            r4.closeView()     // Catch:{ ActivityNotFoundException -> 0x0079 }
            goto L_0x0080
        L_0x0079:
            r4.closeView()
            goto L_0x0080
        L_0x007d:
            r4.closeView()
        L_0x0080:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.presenter.WebAdPresenter.handleAction(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void closeView() {
        this.adView.close();
        this.handler.removeCallbacksAndMessages((Object) null);
    }
}
