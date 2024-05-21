package com.integralads.avid.library.adcolony.video;

import com.integralads.avid.library.adcolony.base.AvidBaseListenerImpl;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidVideoPlaybackListenerImpl extends AvidBaseListenerImpl implements AvidVideoPlaybackListener {
    public static final String AD_CLICK_THRU = "AdClickThru";
    public static final String AD_DURATION = "adDuration";
    public static final String AD_DURATION_CHANGE = "AdDurationChange";
    public static final String AD_ENTERED_FULLSCREEN = "AdEnteredFullscreen";
    public static final String AD_ERROR = "AdError";
    public static final String AD_EXITED_FULLSCREEN = "AdExitedFullscreen";
    public static final String AD_EXPANDED_CHANGE = "AdExpandedChange";
    public static final String AD_IMPRESSION = "AdImpression";
    public static final String AD_LOADED = "AdLoaded";
    public static final String AD_PAUSED = "AdPaused";
    public static final String AD_PLAYING = "AdPlaying";
    public static final String AD_REMAINING_TIME = "adDuration";
    public static final String AD_SKIPPED = "AdSkipped";
    public static final String AD_STARTED = "AdStarted";
    public static final String AD_STOPPED = "AdStopped";
    public static final String AD_USER_ACCEPT_INVITATION = "AdUserAcceptInvitation";
    public static final String AD_USER_CLOSE = "AdUserClose";
    public static final String AD_USER_MINIMIZE = "AdUserMinimize";
    public static final String AD_VIDEO_COMPLETE = "AdVideoComplete";
    public static final String AD_VIDEO_FIRST_QUARTILE = "AdVideoFirstQuartile";
    public static final String AD_VIDEO_MIDPOINT = "AdVideoMidpoint";
    public static final String AD_VIDEO_START = "AdVideoStart";
    public static final String AD_VIDEO_THIRD_QUARTILE = "AdVideoThirdQuartile";
    public static final String AD_VOLUME_CHANGE = "AdVolumeChange";
    public static final String MESSAGE = "message";
    public static final String VOLUME = "volume";

    public AvidVideoPlaybackListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        super(internalAvidAdSession, avidBridgeManager);
    }

    public void recordAdImpressionEvent() {
        a(AD_IMPRESSION, (JSONObject) null);
    }

    public void recordAdStartedEvent() {
        a(AD_STARTED, (JSONObject) null);
    }

    public void recordAdLoadedEvent() {
        a(AD_LOADED, (JSONObject) null);
    }

    public void recordAdVideoStartEvent() {
        a(AD_VIDEO_START, (JSONObject) null);
    }

    public void recordAdStoppedEvent() {
        a(AD_STOPPED, (JSONObject) null);
    }

    public void recordAdCompleteEvent() {
        a(AD_VIDEO_COMPLETE, (JSONObject) null);
    }

    public void recordAdClickThruEvent() {
        a(AD_CLICK_THRU, (JSONObject) null);
    }

    public void recordAdVideoFirstQuartileEvent() {
        a(AD_VIDEO_FIRST_QUARTILE, (JSONObject) null);
    }

    public void recordAdVideoMidpointEvent() {
        a(AD_VIDEO_MIDPOINT, (JSONObject) null);
    }

    public void recordAdVideoThirdQuartileEvent() {
        a(AD_VIDEO_THIRD_QUARTILE, (JSONObject) null);
    }

    public void recordAdPausedEvent() {
        a(AD_PAUSED, (JSONObject) null);
    }

    public void recordAdPlayingEvent() {
        a(AD_PLAYING, (JSONObject) null);
    }

    public void recordAdExpandedChangeEvent() {
        a(AD_EXPANDED_CHANGE, (JSONObject) null);
    }

    public void recordAdUserMinimizeEvent() {
        a(AD_USER_MINIMIZE, (JSONObject) null);
    }

    public void recordAdUserAcceptInvitationEvent() {
        a(AD_USER_ACCEPT_INVITATION, (JSONObject) null);
    }

    public void recordAdUserCloseEvent() {
        a(AD_USER_CLOSE, (JSONObject) null);
    }

    public void recordAdSkippedEvent() {
        a(AD_SKIPPED, (JSONObject) null);
    }

    public void recordAdVolumeChangeEvent(Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(VOLUME, num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(AD_VOLUME_CHANGE, jSONObject);
    }

    public void recordAdEnteredFullscreenEvent() {
        a(AD_ENTERED_FULLSCREEN, (JSONObject) null);
    }

    public void recordAdExitedFullscreenEvent() {
        a(AD_EXITED_FULLSCREEN, (JSONObject) null);
    }

    public void recordAdDurationChangeEvent(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adDuration", str);
            jSONObject.put("adDuration", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(AD_DURATION_CHANGE, jSONObject);
    }

    public void recordAdError(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(AD_ERROR, jSONObject);
    }

    private void a(String str, JSONObject jSONObject) {
        assertSessionIsNotEnded();
        a();
        getAvidBridgeManager().publishVideoEvent(str, jSONObject);
    }

    private void a() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
