package com.tapdaq.sdk.listeners;

import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.model.rewards.TDReward;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TMListenerHandler {
    public static final String ACTION_CLICK = "onClick";
    public static final String ACTION_CLOSE = "onClose";

    public static void DidLoad(TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didLoad();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidLoad - No listener to inform");
        }
    }

    public static void DidLoad(TDMediatedNativeAd tDMediatedNativeAd, TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didLoad(tDMediatedNativeAd);
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidLoad - No listener to inform");
        }
    }

    public static void DidRefresh(TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didRefresh();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidRefresh - No listener to inform");
        }
    }

    public static void WillDisplay(TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.willDisplay();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("WillDisplay - No listener to inform");
        }
    }

    public static void DidDisplay(TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didDisplay();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidDisplay - No listener to inform");
        }
    }

    public static void DidFailToDisplay(TMAdListenerBase tMAdListenerBase, TMAdError tMAdError) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didFailToDisplay(tMAdError);
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning(String.format(Locale.ENGLISH, "DidFailToDisplay - No listener to inform - %s", new Object[]{tMAdError.getErrorMessage()}));
        }
    }

    public static void DidClick(TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didClick();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidClick - No listener to inform");
        }
    }

    public static void DidClose(TMAdListenerBase tMAdListenerBase) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didClose();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidClose - No listener to inform");
        }
    }

    public static void DidFailToLoad(TMAdListenerBase tMAdListenerBase, TMAdError tMAdError) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didFailToLoad(tMAdError);
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning(String.format(Locale.ENGLISH, "DidFailToLoad - No listener to inform - %s", new Object[]{tMAdError.getErrorMessage()}));
        }
    }

    public static void OnUserDeclined(TMRewardAdListenerBase tMRewardAdListenerBase) {
        if (tMRewardAdListenerBase != null) {
            try {
                tMRewardAdListenerBase.onUserDeclined();
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("OnUserDeclined - No listener to inform");
        }
    }

    public static void DidVerify(TMRewardAdListenerBase tMRewardAdListenerBase, TDReward tDReward) {
        if (tMRewardAdListenerBase != null) {
            try {
                tMRewardAdListenerBase.didVerify(tDReward);
                Object custom_json = tDReward.getCustom_json();
                Map hashMap = new HashMap();
                if (custom_json instanceof Map) {
                    hashMap = (Map) custom_json;
                }
                TMRewardAdListenerBase tMRewardAdListenerBase2 = tMRewardAdListenerBase;
                tMRewardAdListenerBase2.didVerify(tDReward.getTag(), tDReward.getName(), tDReward.getValue(), tDReward.isValid(), hashMap);
                tMRewardAdListenerBase.didVerify(tDReward.getTag(), tDReward.getName(), Double.valueOf((double) tDReward.getValue()));
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidVerify - No listener to inform");
        }
    }

    public static void DidCustomEvent(TMAdListenerBase tMAdListenerBase, Map<Object, Object> map) {
        if (tMAdListenerBase != null) {
            try {
                tMAdListenerBase.didCustomEvent(map);
            } catch (Exception e) {
                TLog.error(e);
            }
        } else {
            TLog.warning("DidCustomEvent - No listener to inform");
        }
    }
}
