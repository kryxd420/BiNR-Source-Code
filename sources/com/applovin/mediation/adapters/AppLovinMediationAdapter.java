package com.applovin.mediation.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.adapter.MaxAdViewAdapter;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.MaxAdapterError;
import com.applovin.mediation.adapter.MaxInterstitialAdapter;
import com.applovin.mediation.adapter.MaxRewardedAdapter;
import com.applovin.mediation.adapter.MaxSignalProvider;
import com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterSignalCollectionParameters;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class AppLovinMediationAdapter extends MediationAdapterBase implements MaxAdViewAdapter, MaxInterstitialAdapter, MaxRewardedAdapter, MaxSignalProvider {
    /* access modifiers changed from: private */
    public AppLovinAdView mLoadedAdView;
    /* access modifiers changed from: private */
    public AppLovinAd mLoadedInterstitialAd;
    /* access modifiers changed from: private */
    public AppLovinAd mLoadedRewardedAd;
    /* access modifiers changed from: private */
    public MaxReward mPendingReward;

    private class RewardListenerWrapper implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
        private final MaxRewardedAdapterListener listener;

        private RewardListenerWrapper(MaxRewardedAdapterListener maxRewardedAdapterListener) {
            this.listener = maxRewardedAdapterListener;
        }

        public void adClicked(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdClicked();
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdDisplayed();
        }

        public void adHidden(AppLovinAd appLovinAd) {
            if (AppLovinMediationAdapter.this.mPendingReward != null) {
                this.listener.onUserRewarded(AppLovinMediationAdapter.this.mPendingReward);
                MaxReward unused = AppLovinMediationAdapter.this.mPendingReward = null;
            }
            this.listener.onRewardedAdHidden();
        }

        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdDisplayFailed(MaxAdapterError.REWARD_ERROR);
        }

        public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
            this.listener.onRewardedAdDisplayFailed(MaxAdapterError.REWARD_ERROR);
        }

        public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
            this.listener.onRewardedAdDisplayFailed(MaxAdapterError.REWARD_ERROR);
        }

        public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
            String str = map.get("currency");
            String str2 = map.get("amount");
            AppLovinMediationAdapter appLovinMediationAdapter = AppLovinMediationAdapter.this;
            appLovinMediationAdapter.log("Rewarded verified " + str2 + " " + str);
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                MaxReward unused = AppLovinMediationAdapter.this.mPendingReward = MaxReward.createDefault();
                return;
            }
            int i = 0;
            try {
                i = (int) Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                AppLovinMediationAdapter appLovinMediationAdapter2 = AppLovinMediationAdapter.this;
                appLovinMediationAdapter2.log("Failed to parse AppLovin reward amount: " + str2, e);
            }
            MaxReward unused2 = AppLovinMediationAdapter.this.mPendingReward = MaxReward.create(i, str);
        }

        public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
            this.listener.onRewardedAdDisplayFailed(MaxAdapterError.REWARD_ERROR);
        }

        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdVideoStarted();
        }

        public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
            this.listener.onRewardedAdVideoCompleted();
        }
    }

    public AppLovinMediationAdapter(AppLovinSdk appLovinSdk) {
        super(appLovinSdk);
    }

    /* access modifiers changed from: private */
    public static MaxAdapterError toMaxError(int i) {
        int i2 = 204;
        if (i == -103) {
            i2 = MaxAdapterError.ERROR_CODE_NO_CONNECTION;
        } else if (i != 204) {
            i2 = i == -1 ? MaxAdapterError.ERROR_CODE_INTERNAL_ERROR : i >= 500 ? MaxAdapterError.ERROR_CODE_SERVER_ERROR : MaxAdapterError.ERROR_CODE_UNSPECIFIED;
        }
        return new MaxAdapterError(i2, i);
    }

    /* access modifiers changed from: private */
    public static MaxAdapterError toMaxError(AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
        return MaxAdapterError.UNSPECIFIED;
    }

    public void collectSignal(MaxAdapterSignalCollectionParameters maxAdapterSignalCollectionParameters, Activity activity, MaxSignalCollectionListener maxSignalCollectionListener) {
        maxSignalCollectionListener.onSignalCollected(getSdk().o().getBidToken());
    }

    public String getAdapterVersion() {
        return getSdkVersion();
    }

    public String getSdkVersion() {
        return AppLovinSdk.VERSION;
    }

    public void initialize(MaxAdapterInitializationParameters maxAdapterInitializationParameters, Activity activity, MaxAdapter.OnCompletionListener onCompletionListener) {
        getSdk().c("max");
        onCompletionListener.onCompletion();
    }

    public void loadAdViewAd(MaxAdapterResponseParameters maxAdapterResponseParameters, MaxAdFormat maxAdFormat, final Activity activity, final MaxAdViewAdapterListener maxAdViewAdapterListener) {
        AnonymousClass1 r0 = new AppLovinAdLoadListener() {
            public void adReceived(final AppLovinAd appLovinAd) {
                AppLovinSdkUtils.runOnUiThread(new Runnable() {
                    public void run() {
                        AppLovinAdView unused = AppLovinMediationAdapter.this.mLoadedAdView = new AppLovinAdView(AppLovinMediationAdapter.this.getSdk().L(), appLovinAd.getSize(), (Context) activity);
                        AppLovinMediationAdapter.this.mLoadedAdView.setAdDisplayListener(new AppLovinAdDisplayListener() {
                            public void adDisplayed(AppLovinAd appLovinAd) {
                                maxAdViewAdapterListener.onAdViewAdDisplayed();
                            }

                            public void adHidden(AppLovinAd appLovinAd) {
                                maxAdViewAdapterListener.onAdViewAdHidden();
                            }
                        });
                        AppLovinMediationAdapter.this.mLoadedAdView.setAdClickListener(new AppLovinAdClickListener() {
                            public void adClicked(AppLovinAd appLovinAd) {
                                maxAdViewAdapterListener.onAdViewAdClicked();
                            }
                        });
                        AppLovinMediationAdapter.this.mLoadedAdView.setAdViewEventListener(new AppLovinAdViewEventListener() {
                            public void adClosedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
                                maxAdViewAdapterListener.onAdViewAdCollapsed();
                            }

                            public void adFailedToDisplay(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
                                maxAdViewAdapterListener.onAdViewAdDisplayFailed(AppLovinMediationAdapter.toMaxError(appLovinAdViewDisplayErrorCode));
                            }

                            public void adLeftApplication(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
                            }

                            public void adOpenedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
                                maxAdViewAdapterListener.onAdViewAdExpanded();
                            }
                        });
                        maxAdViewAdapterListener.onAdViewAdLoaded(AppLovinMediationAdapter.this.mLoadedAdView);
                        AppLovinMediationAdapter.this.mLoadedAdView.renderAd(appLovinAd);
                    }
                });
            }

            public void failedToReceiveAd(int i) {
                maxAdViewAdapterListener.onAdViewAdLoadFailed(AppLovinMediationAdapter.toMaxError(i));
            }
        };
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getBidResponse())) {
            getSdk().o().loadNextAdForAdToken(maxAdapterResponseParameters.getBidResponse(), r0);
        } else if (!TextUtils.isEmpty(maxAdapterResponseParameters.getThirdPartyAdPlacementId())) {
            getSdk().o().loadNextAdForZoneId(maxAdapterResponseParameters.getThirdPartyAdPlacementId(), r0);
        } else {
            AppLovinAdSize appLovinAdSize = null;
            if (maxAdFormat == MaxAdFormat.BANNER) {
                appLovinAdSize = AppLovinAdSize.BANNER;
            } else if (maxAdFormat == MaxAdFormat.MREC) {
                appLovinAdSize = AppLovinAdSize.MREC;
            } else if (maxAdFormat == MaxAdFormat.LEADER) {
                appLovinAdSize = AppLovinAdSize.LEADER;
            }
            if (appLovinAdSize != null) {
                getSdk().o().loadNextAd(appLovinAdSize, r0);
                return;
            }
            log("Failed to load ad for format: " + maxAdFormat);
        }
    }

    public void loadInterstitialAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, final MaxInterstitialAdapterListener maxInterstitialAdapterListener) {
        AnonymousClass2 r2 = new AppLovinAdLoadListener() {
            public void adReceived(AppLovinAd appLovinAd) {
                AppLovinAd unused = AppLovinMediationAdapter.this.mLoadedInterstitialAd = appLovinAd;
                maxInterstitialAdapterListener.onInterstitialAdLoaded();
            }

            public void failedToReceiveAd(int i) {
                maxInterstitialAdapterListener.onInterstitialAdLoadFailed(AppLovinMediationAdapter.toMaxError(i));
            }
        };
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getBidResponse())) {
            getSdk().o().loadNextAdForAdToken(maxAdapterResponseParameters.getBidResponse(), r2);
        } else if (!TextUtils.isEmpty(maxAdapterResponseParameters.getThirdPartyAdPlacementId())) {
            getSdk().o().loadNextAdForZoneId(maxAdapterResponseParameters.getThirdPartyAdPlacementId(), r2);
        } else {
            getSdk().o().loadNextAd(AppLovinAdSize.INTERSTITIAL, r2);
        }
    }

    public void loadRewardedAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, final MaxRewardedAdapterListener maxRewardedAdapterListener) {
        AnonymousClass5 r2 = new AppLovinAdLoadListener() {
            public void adReceived(AppLovinAd appLovinAd) {
                AppLovinAd unused = AppLovinMediationAdapter.this.mLoadedRewardedAd = appLovinAd;
                maxRewardedAdapterListener.onRewardedAdLoaded();
            }

            public void failedToReceiveAd(int i) {
                maxRewardedAdapterListener.onRewardedAdLoadFailed(AppLovinMediationAdapter.toMaxError(i));
            }
        };
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getBidResponse())) {
            getSdk().o().loadNextAdForAdToken(maxAdapterResponseParameters.getBidResponse(), r2);
        } else if (!TextUtils.isEmpty(maxAdapterResponseParameters.getThirdPartyAdPlacementId())) {
            getSdk().o().loadNextAdForZoneId(maxAdapterResponseParameters.getThirdPartyAdPlacementId(), r2);
        } else {
            getSdk().o().loadNextAdForZoneId("inter_videoa", r2);
        }
    }

    public void onDestroy() {
        this.mLoadedInterstitialAd = null;
        this.mLoadedRewardedAd = null;
        if (this.mLoadedAdView != null) {
            this.mLoadedAdView.destroy();
            this.mLoadedAdView = null;
        }
    }

    public void showInterstitialAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, final MaxInterstitialAdapterListener maxInterstitialAdapterListener) {
        if (this.mLoadedInterstitialAd != null) {
            AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(getSdk().L(), activity.getApplicationContext());
            create.setAdDisplayListener(new AppLovinAdDisplayListener() {
                public void adDisplayed(AppLovinAd appLovinAd) {
                    maxInterstitialAdapterListener.onInterstitialAdDisplayed();
                }

                public void adHidden(AppLovinAd appLovinAd) {
                    maxInterstitialAdapterListener.onInterstitialAdHidden();
                }
            });
            create.setAdClickListener(new AppLovinAdClickListener() {
                public void adClicked(AppLovinAd appLovinAd) {
                    maxInterstitialAdapterListener.onInterstitialAdClicked();
                }
            });
            create.showAndRender(this.mLoadedInterstitialAd);
            return;
        }
        maxInterstitialAdapterListener.onInterstitialAdDisplayFailed(MaxAdapterError.AD_NOT_READY);
    }

    public void showRewardedAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, MaxRewardedAdapterListener maxRewardedAdapterListener) {
        if (this.mLoadedRewardedAd != null) {
            AppLovinIncentivizedInterstitial create = AppLovinIncentivizedInterstitial.create(getSdk().L());
            RewardListenerWrapper rewardListenerWrapper = new RewardListenerWrapper(maxRewardedAdapterListener);
            create.show(this.mLoadedRewardedAd, (Context) activity, (AppLovinAdRewardListener) rewardListenerWrapper, (AppLovinAdVideoPlaybackListener) rewardListenerWrapper, (AppLovinAdDisplayListener) rewardListenerWrapper, (AppLovinAdClickListener) rewardListenerWrapper);
            return;
        }
        maxRewardedAdapterListener.onRewardedAdLoadFailed(MaxAdapterError.AD_NOT_READY);
    }
}
