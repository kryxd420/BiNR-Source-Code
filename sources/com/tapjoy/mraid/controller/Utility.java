package com.tapjoy.mraid.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Defines;
import com.tapjoy.mraid.view.MraidView;

@TargetApi(14)
public class Utility extends Abstract {
    private Assets c;
    private Display d;
    private Network e;
    private MraidSensor f;

    public Utility(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.c = new Assets(mraidView, context);
        this.d = new Display(mraidView, context);
        this.e = new Network(mraidView, context);
        this.f = new MraidSensor(mraidView, context);
        mraidView.addJavascriptInterface(this.c, "MRAIDAssetsControllerBridge");
        mraidView.addJavascriptInterface(this.d, "MRAIDDisplayControllerBridge");
        mraidView.addJavascriptInterface(this.e, "MRAIDNetworkControllerBridge");
        mraidView.addJavascriptInterface(this.f, "MRAIDSensorControllerBridge");
    }

    public void init(float f2) {
        StringBuilder sb = new StringBuilder("window.mraidview.fireChangeEvent({ state: 'default', network: '");
        sb.append(this.e.getNetwork());
        sb.append("', size: ");
        sb.append(this.d.getSize());
        sb.append(", placement: '");
        sb.append(this.a.getPlacementType());
        sb.append("', maxSize: ");
        sb.append(this.d.getMaxSize());
        sb.append(",expandProperties: ");
        sb.append(this.d.getMaxSize());
        sb.append(", screenSize: ");
        sb.append(this.d.getScreenSize());
        sb.append(", defaultPosition: { x:");
        sb.append((int) (((float) this.a.getLeft()) / f2));
        sb.append(", y: ");
        sb.append((int) (((float) this.a.getTop()) / f2));
        sb.append(", width: ");
        sb.append((int) (((float) this.a.getWidth()) / f2));
        sb.append(", height: ");
        sb.append((int) (((float) this.a.getHeight()) / f2));
        sb.append(" }, orientation:");
        sb.append(this.d.getOrientation());
        sb.append(",");
        String str = (("supports: [ 'level-1', 'level-2', 'screen', 'orientation', 'network'" + ", 'video'") + ", 'audio'") + ", 'map' ]";
        TapjoyLog.d("MRAID Utility", "getSupports: " + str);
        sb.append(str);
        sb.append(",viewable:true });");
        String sb2 = sb.toString();
        TapjoyLog.d("MRAID Utility", "init: injection: " + sb2);
        this.a.injectMraidJavaScript(sb2);
        fireReadyEvent();
        fireViewableChange(true);
    }

    public void fireReadyEvent() {
        this.a.injectMraidJavaScript("mraid.signalReady();");
    }

    public void fireViewableChange(boolean z) {
        MraidView mraidView = this.a;
        mraidView.injectMraidJavaScript("window.mraidview.fireChangeEvent({viewable:" + z + "});");
    }

    public String copyTextFromJarIntoAssetDir(String str, String str2) {
        return this.c.copyTextFromJarIntoAssetDir(str, str2);
    }

    public void setMaxSize(int i, int i2) {
        this.d.setMaxSize(i, i2);
    }

    @JavascriptInterface
    public void activate(String str) {
        TapjoyLog.d("MRAID Utility", "activate: " + str);
        if (str.equalsIgnoreCase(Defines.Events.NETWORK_CHANGE)) {
            this.e.startNetworkListener();
        } else if (str.equalsIgnoreCase(Defines.Events.SHAKE)) {
            this.f.startShakeListener();
        } else if (str.equalsIgnoreCase(Defines.Events.TILT_CHANGE)) {
            this.f.startTiltListener();
        } else if (str.equalsIgnoreCase(Defines.Events.HEADING_CHANGE)) {
            this.f.startHeadingListener();
        } else if (str.equalsIgnoreCase(Defines.Events.ORIENTATION_CHANGE)) {
            this.d.startConfigurationListener();
        }
    }

    @JavascriptInterface
    public void deactivate(String str) {
        TapjoyLog.d("MRAID Utility", "deactivate: " + str);
        if (str.equalsIgnoreCase(Defines.Events.NETWORK_CHANGE)) {
            this.e.stopNetworkListener();
        } else if (str.equalsIgnoreCase(Defines.Events.SHAKE)) {
            this.f.stopShakeListener();
        } else if (str.equalsIgnoreCase(Defines.Events.TILT_CHANGE)) {
            this.f.stopTiltListener();
        } else if (str.equalsIgnoreCase(Defines.Events.HEADING_CHANGE)) {
            this.f.stopHeadingListener();
        } else if (str.equalsIgnoreCase(Defines.Events.ORIENTATION_CHANGE)) {
            this.d.stopConfigurationListener();
        }
    }

    public void deleteOldAds() {
        this.c.deleteOldAds();
    }

    public void stopAllListeners() {
        try {
            this.c.stopAllListeners();
            this.d.stopAllListeners();
            this.e.stopAllListeners();
            this.f.stopAllListeners();
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public void showAlert(String str) {
        TapjoyLog.e("MRAID Utility", str);
    }
}
