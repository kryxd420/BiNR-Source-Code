package com.tapdaq.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TapdaqConfig {
    private String mAdMobContentRating = null;
    private STATUS mAgeRestrictedUser = STATUS.UNKNOWN;
    private boolean mAutoReload = false;
    private STATUS mConsent = STATUS.UNKNOWN;
    private STATUS mIsUserSubjectToGDPR = STATUS.UNKNOWN;
    private String mPluginVersion;
    private Map<Integer, List<String>> mTestDevices = new HashMap();

    public void setPluginVersion(String str) {
        this.mPluginVersion = str;
    }

    public String getPluginVersion() {
        return this.mPluginVersion;
    }

    public void setUserSubjectToGDPR(STATUS status) {
        this.mIsUserSubjectToGDPR = status;
    }

    public STATUS isUserSubjectToGDPR() {
        return this.mIsUserSubjectToGDPR;
    }

    public void setConsentGiven(boolean z) {
        if (z) {
            this.mConsent = STATUS.TRUE;
        } else {
            this.mConsent = STATUS.FALSE;
        }
    }

    public boolean isConsentGiven() {
        return this.mConsent == STATUS.TRUE;
    }

    public STATUS getConsenStatus() {
        return this.mConsent;
    }

    public void setIsAgeRestrictedUser(boolean z) {
        if (z) {
            this.mAgeRestrictedUser = STATUS.TRUE;
        } else {
            this.mAgeRestrictedUser = STATUS.FALSE;
        }
    }

    public boolean isAgeRestrictedUser() {
        return this.mAgeRestrictedUser == STATUS.TRUE;
    }

    public STATUS getAgeRestrictedUserStatus() {
        return this.mAgeRestrictedUser;
    }

    public void registerTestDevices(int i, List<String> list) {
        this.mTestDevices.put(Integer.valueOf(i), list);
    }

    public List<String> getTestDevices(int i) {
        if (this.mTestDevices.containsKey(Integer.valueOf(i))) {
            return this.mTestDevices.get(Integer.valueOf(i));
        }
        return null;
    }

    public void setAutoReloadAds(boolean z) {
        this.mAutoReload = z;
    }

    public boolean shouldAutoReloadAds() {
        return this.mAutoReload;
    }

    public void setAdMobContentRating(String str) {
        this.mAdMobContentRating = str;
    }

    public String getAdMobContentRating() {
        return this.mAdMobContentRating;
    }
}
