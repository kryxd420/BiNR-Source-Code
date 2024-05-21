package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class IabHelper {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
    public static final int IABHELPER_BAD_RESPONSE = -1002;
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
    public static final int IABHELPER_MISSING_TOKEN = -1007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
    public static final int IABHELPER_UNKNOWN_ERROR = -1008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
    public static final int IABHELPER_USER_CANCELLED = -1005;
    public static final int IABHELPER_VERIFICATION_FAILED = -1003;
    public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    private static final int IN_APP_BILLING_API_VERSION_3 = 3;
    private static final int IN_APP_BILLING_API_VERSION_5 = 5;
    private static final int IN_APP_BILLING_API_VERSION_6 = 6;
    private static final int IN_APP_BILLING_API_VERSION_7 = 7;
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
    public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    public static HashMap<Integer, String> billingResponseCodeNames = new HashMap<>();
    /* access modifiers changed from: private */
    public Inventory inv;
    /* access modifiers changed from: private */
    public IActivityLauncher launcher;
    String mAsyncOperation = "";
    Context mContext;
    /* access modifiers changed from: private */
    public boolean mDaydreamApiAvailable = false;
    boolean mDebugLog = false;
    String mDebugTag = "IabHelper";
    volatile boolean mDisposed = false;
    OnIabPurchaseFinishedListener mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    volatile boolean mSetupDone = false;
    boolean mSubscriptionPurchaseHistorySupported = false;
    boolean mSubscriptionUpgradeDowngradeSupported = false;
    boolean mSubscriptionsSupported = false;
    /* access modifiers changed from: private */
    public boolean mUnityVrEnabled = false;
    /* access modifiers changed from: private */
    public boolean mVrSupported = false;
    private IBillingServiceManager serviceManager;

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult) throws JSONException;
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) throws Exception;
    }

    static {
        billingResponseCodeNames.put(0, "BILLING_RESPONSE_RESULT_OK");
        billingResponseCodeNames.put(1, "BILLING_RESPONSE_RESULT_USER_CANCELED");
        billingResponseCodeNames.put(2, "BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE");
        billingResponseCodeNames.put(3, "BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE");
        billingResponseCodeNames.put(4, "BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE");
        billingResponseCodeNames.put(5, "BILLING_RESPONSE_RESULT_DEVELOPER_ERROR");
        billingResponseCodeNames.put(6, "BILLING_RESPONSE_RESULT_ERROR");
        billingResponseCodeNames.put(7, "BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED");
        billingResponseCodeNames.put(8, "BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED");
        billingResponseCodeNames.put(-1000, "IABHELPER_ERROR_BASE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_REMOTE_EXCEPTION), "IABHELPER_REMOTE_EXCEPTION");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_BAD_RESPONSE), "IABHELPER_BAD_RESPONSE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_VERIFICATION_FAILED), "IABHELPER_VERIFICATION_FAILED");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_SEND_INTENT_FAILED), "IABHELPER_SEND_INTENT_FAILED");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_USER_CANCELLED), "IABHELPER_USER_CANCELLED");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_UNKNOWN_PURCHASE_RESPONSE), "IABHELPER_UNKNOWN_PURCHASE_RESPONSE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_MISSING_TOKEN), "IABHELPER_MISSING_TOKEN");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_UNKNOWN_ERROR), "IABHELPER_UNKNOWN_ERROR");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE), "IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_INVALID_CONSUMPTION), "IABHELPER_INVALID_CONSUMPTION");
    }

    public IabHelper(Context context, IBillingServiceManager iBillingServiceManager, IActivityLauncher iActivityLauncher) {
        this.mContext = context.getApplicationContext();
        this.serviceManager = iBillingServiceManager;
        this.launcher = iActivityLauncher;
        this.inv = new Inventory();
        logDebug("IAB helper created.");
    }

    public void enableDebugLogging(boolean z, String str) {
        this.mDebugLog = z;
        this.mDebugTag = str;
    }

    public void enableDebugLogging(boolean z) {
        this.mDebugLog = z;
    }

    public void enableUnityVr(boolean z) {
        this.mUnityVrEnabled = z;
    }

    public void enableDaydreamApi(boolean z) {
        this.mDaydreamApiAvailable = z;
    }

    public void startSetup(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        if (!this.mSetupDone) {
            logDebug("Starting in-app billing setup.");
            this.serviceManager.workWith(new BillingServiceProcessor() {
                public void workWith(IInAppBillingService iInAppBillingService) {
                    IabHelper.this.finishSetup(onIabSetupFinishedListener, iInAppBillingService);
                }
            });
            return;
        }
        throw new IllegalStateException("IAB helper is already set up.");
    }

    /* access modifiers changed from: private */
    public void finishSetup(OnIabSetupFinishedListener onIabSetupFinishedListener, IInAppBillingService iInAppBillingService) {
        String packageName = this.mContext.getPackageName();
        try {
            logDebug("Checking for in-app billing 3 support.");
            int isBillingSupported = iInAppBillingService.isBillingSupported(3, packageName, "inapp");
            if (isBillingSupported != 0) {
                onIabSetupFinishedListener.onIabSetupFinished(new IabResult(isBillingSupported, "Billing V3 not supported."));
                this.mSubscriptionsSupported = false;
                return;
            }
            logDebug("In-app billing version 3 supported for " + packageName);
            int isBillingSupported2 = iInAppBillingService.isBillingSupported(3, packageName, "subs");
            if (isBillingSupported2 == 0) {
                logDebug("Subscriptions AVAILABLE.");
                this.mSubscriptionsSupported = true;
                if (iInAppBillingService.isBillingSupported(5, packageName, "subs") == 0) {
                    this.mSubscriptionUpgradeDowngradeSupported = true;
                    logDebug("Subscription upgrade and downgrade are AVAILABLE.");
                } else {
                    logDebug("Subscription upgrade and downgrade are NOT AVAILABLE.");
                }
                if (iInAppBillingService.isBillingSupported(6, packageName, "subs") == 0) {
                    this.mSubscriptionPurchaseHistorySupported = true;
                    logDebug("Subscriptions information parse AVAILABLE.");
                } else {
                    logDebug("Subscriptions information parse NOT AVAILABLE.");
                }
            } else {
                logDebug("Subscriptions NOT AVAILABLE. Response: " + isBillingSupported2);
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("vr", true);
            int isBillingSupportedExtraParams = iInAppBillingService.isBillingSupportedExtraParams(7, this.mContext.getPackageName(), "inapp", bundle);
            if (isBillingSupportedExtraParams == 0) {
                logDebug("VR supported.");
                this.mVrSupported = true;
            } else {
                logDebug("VR purchases  NOT AVAILABLE. Response: " + isBillingSupportedExtraParams);
            }
            this.mSetupDone = true;
            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(0, "Setup successful."));
        } catch (RemoteException unused) {
            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(IABHELPER_REMOTE_EXCEPTION, "RemoteException while setting up in-app billing."));
        }
    }

    public void dispose() {
        logDebug("Disposing.");
        this.mSetupDone = false;
        this.serviceManager.dispose();
        this.mDisposed = true;
    }

    public boolean subscriptionsSupported() {
        return this.mSubscriptionsSupported;
    }

    public boolean subscriptionPurchaseHistorySupported() {
        return this.mSubscriptionPurchaseHistorySupported;
    }

    public boolean subscriptionUpgradeDowngradeSupported() {
        return this.mSubscriptionUpgradeDowngradeSupported;
    }

    public void launchPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) {
        launchPurchaseFlow(activity, str, i, onIabPurchaseFinishedListener, "");
    }

    public void launchPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        launchPurchaseFlow(activity, str, "inapp", i, onIabPurchaseFinishedListener, str2);
    }

    public void launchSubscriptionPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) {
        launchSubscriptionPurchaseFlow(activity, str, i, onIabPurchaseFinishedListener, "");
    }

    public void launchSubscriptionPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        launchPurchaseFlow(activity, str, "subs", i, onIabPurchaseFinishedListener, str2);
    }

    public void launchSubscriptionUpdateFlow(Activity activity, String str, List<String> list, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        OnIabPurchaseFinishedListener onIabPurchaseFinishedListener2 = onIabPurchaseFinishedListener;
        checkSetupDone("launchSubscriptionUpdateFlow");
        if (!this.mSubscriptionsSupported || !this.mSubscriptionUpgradeDowngradeSupported) {
            IabResult iabResult = new IabResult(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE, "Subscription upgrade/downgrade is not available.");
            if (onIabPurchaseFinishedListener2 != null) {
                onIabPurchaseFinishedListener2.onIabPurchaseFinished(iabResult, (Purchase) null);
                return;
            }
            return;
        }
        final String str3 = str;
        final List<String> list2 = list;
        final String str4 = str2;
        final Activity activity2 = activity;
        final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener3 = onIabPurchaseFinishedListener;
        final int i2 = i;
        this.serviceManager.workWith(new BillingServiceProcessor() {
            /* JADX WARNING: Removed duplicated region for block: B:22:0x00ec A[Catch:{ JSONException -> 0x00d4 }] */
            /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void workWith(com.android.vending.billing.IInAppBillingService r10) {
                /*
                    r9 = this;
                    r0 = 0
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2.<init>()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = "Constructing buy intent for "
                    r2.append(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = r2     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2.append(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = ", item type: "
                    r2.append(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = "subs"
                    r2.append(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = r2.toString()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.logDebug(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    boolean r1 = r1.mVrSupported     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    if (r1 == 0) goto L_0x006b
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    boolean r1 = r1.mUnityVrEnabled     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    if (r1 == 0) goto L_0x006b
                    android.os.Bundle r8 = new android.os.Bundle     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r8.<init>()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r1 = "vr"
                    r2 = 1
                    r8.putBoolean(r1, r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r1 = "skusToReplace"
                    java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.util.List r4 = r3     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r3.<init>(r4)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r8.putStringArrayList(r1, r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r1 = "replaceSkusProration"
                    r8.putBoolean(r1, r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = "Initiating VR purchase intent"
                    r1.logDebug(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r3 = 7
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    android.content.Context r1 = r1.mContext     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r4 = r1.getPackageName()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r5 = r2     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r6 = "subs"
                    java.lang.String r7 = r4     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2 = r10
                    android.os.Bundle r10 = r2.getBuyIntentExtraParams(r3, r4, r5, r6, r7, r8)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    goto L_0x0081
                L_0x006b:
                    r2 = 5
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    android.content.Context r1 = r1.mContext     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = r1.getPackageName()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.util.List r4 = r3     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r5 = r2     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r6 = "subs"
                    java.lang.String r7 = r4     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1 = r10
                    android.os.Bundle r10 = r1.getBuyIntentToReplaceSkus(r2, r3, r4, r5, r6, r7)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                L_0x0081:
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    int r1 = r1.getResponseCodeFromBundle(r10)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    if (r1 == 0) goto L_0x00f6
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2.<init>()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = "Unable to update subscription, Error response: "
                    r2.append(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = com.unity.purchasing.googleplay.IabHelper.getResponseDesc(r1)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2.append(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = r2.toString()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r10.logError(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabResult r10 = new com.unity.purchasing.googleplay.IabResult     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = "Unable to update subscription item"
                    r10.<init>(r1, r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.common.SaneJSONObject r2 = new com.unity.purchasing.common.SaneJSONObject     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2.<init>()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r3 = "productId"
                    java.lang.String r4 = r2     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r2.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r3 = 7
                    if (r1 != r3) goto L_0x00d6
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ JSONException -> 0x00d4 }
                    com.unity.purchasing.googleplay.Inventory r1 = r1.inv     // Catch:{ JSONException -> 0x00d4 }
                    java.lang.String r3 = r2     // Catch:{ JSONException -> 0x00d4 }
                    boolean r1 = r1.hasPurchase(r3)     // Catch:{ JSONException -> 0x00d4 }
                    if (r1 == 0) goto L_0x00d6
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ JSONException -> 0x00d4 }
                    com.unity.purchasing.googleplay.Inventory r1 = r1.inv     // Catch:{ JSONException -> 0x00d4 }
                    java.lang.String r2 = r2     // Catch:{ JSONException -> 0x00d4 }
                    com.unity.purchasing.googleplay.Purchase r1 = r1.getPurchase(r2)     // Catch:{ JSONException -> 0x00d4 }
                    goto L_0x00e3
                L_0x00d4:
                    r10 = move-exception
                    goto L_0x00f2
                L_0x00d6:
                    com.unity.purchasing.googleplay.Purchase r1 = new com.unity.purchasing.googleplay.Purchase     // Catch:{ JSONException -> 0x00d4 }
                    java.lang.String r3 = "subs"
                    java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00d4 }
                    java.lang.String r4 = ""
                    r1.<init>(r3, r2, r4)     // Catch:{ JSONException -> 0x00d4 }
                L_0x00e3:
                    android.app.Activity r2 = r5     // Catch:{ JSONException -> 0x00d4 }
                    r2.finish()     // Catch:{ JSONException -> 0x00d4 }
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r2 = r6     // Catch:{ JSONException -> 0x00d4 }
                    if (r2 == 0) goto L_0x00f1
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r2 = r6     // Catch:{ JSONException -> 0x00d4 }
                    r2.onIabPurchaseFinished(r10, r1)     // Catch:{ JSONException -> 0x00d4 }
                L_0x00f1:
                    return
                L_0x00f2:
                    r10.printStackTrace()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    return
                L_0x00f6:
                    java.lang.String r1 = "BUY_INTENT"
                    android.os.Parcelable r10 = r10.getParcelable(r1)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r3 = r10
                    android.app.PendingIntent r3 = (android.app.PendingIntent) r3     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.<init>()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = "Launching buy intent for "
                    r1.append(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = r2     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.append(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r2 = ". Request code: "
                    r1.append(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    int r2 = r7     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.append(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r1 = r1.toString()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r10.logDebug(r1)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    int r1 = r7     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r10.mRequestCode = r1     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r10.mPurchaseListener = r1     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r1 = "subs"
                    r10.mPurchasingItemType = r1     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    boolean r10 = r10.mVrSupported     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    if (r10 == 0) goto L_0x015d
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    boolean r10 = r10.mUnityVrEnabled     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    if (r10 == 0) goto L_0x015d
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    boolean r10 = r10.mDaydreamApiAvailable     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    if (r10 == 0) goto L_0x015d
                    com.unity.purchasing.googleplay.IabHelper$2$1 r10 = new com.unity.purchasing.googleplay.IabHelper$2$1     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r10.<init>(r3)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    android.os.Handler r1 = new android.os.Handler     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    android.os.Looper r2 = android.os.Looper.getMainLooper()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.<init>(r2)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.post(r10)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    goto L_0x01d2
                L_0x015d:
                    r3.getIntentSender()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    com.unity.purchasing.googleplay.IActivityLauncher r1 = r10.launcher     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    android.app.Activity r2 = r5     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    int r4 = r7     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    android.content.Intent r5 = new android.content.Intent     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r5.<init>()     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    java.lang.String r6 = r2     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    r1.startIntentSenderForResult(r2, r3, r4, r5, r6)     // Catch:{ SendIntentException -> 0x01a4, RemoteException -> 0x0175 }
                    goto L_0x01d2
                L_0x0175:
                    r10 = move-exception
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "RemoteException while launching purchase flow for sku "
                    r2.append(r3)
                    java.lang.String r3 = r2
                    r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    r1.logError(r2)
                    r10.printStackTrace()
                    com.unity.purchasing.googleplay.IabResult r10 = new com.unity.purchasing.googleplay.IabResult
                    r1 = -1001(0xfffffffffffffc17, float:NaN)
                    java.lang.String r2 = "Remote exception while starting purchase flow"
                    r10.<init>(r1, r2)
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                    if (r1 == 0) goto L_0x01d2
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                    r1.onIabPurchaseFinished(r10, r0)
                    goto L_0x01d2
                L_0x01a4:
                    r10 = move-exception
                    com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "SendIntentException while launching purchase flow for sku "
                    r2.append(r3)
                    java.lang.String r3 = r2
                    r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    r1.logError(r2)
                    r10.printStackTrace()
                    com.unity.purchasing.googleplay.IabResult r10 = new com.unity.purchasing.googleplay.IabResult
                    r1 = -1004(0xfffffffffffffc14, float:NaN)
                    java.lang.String r2 = "Failed to send intent."
                    r10.<init>(r1, r2)
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                    if (r1 == 0) goto L_0x01d2
                    com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                    r1.onIabPurchaseFinished(r10, r0)
                L_0x01d2:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.IabHelper.AnonymousClass2.workWith(com.android.vending.billing.IInAppBillingService):void");
            }
        });
    }

    public void launchPurchaseFlow(Activity activity, String str, String str2, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str3) {
        OnIabPurchaseFinishedListener onIabPurchaseFinishedListener2 = onIabPurchaseFinishedListener;
        checkSetupDone("launchPurchaseFlow");
        String str4 = str2;
        if (!str2.equals("subs") || this.mSubscriptionsSupported) {
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final Activity activity2 = activity;
            final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener3 = onIabPurchaseFinishedListener;
            final int i2 = i;
            this.serviceManager.workWith(new BillingServiceProcessor() {
                /* JADX WARNING: Removed duplicated region for block: B:22:0x00d9 A[Catch:{ JSONException -> 0x00c1 }] */
                /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void workWith(com.android.vending.billing.IInAppBillingService r10) {
                    /*
                        r9 = this;
                        r0 = 0
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.<init>()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = "Constructing buy intent for "
                        r2.append(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = r2     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.append(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = ", item type: "
                        r2.append(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = r3     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.append(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = r2.toString()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.logDebug(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        boolean r1 = r1.mVrSupported     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        if (r1 == 0) goto L_0x005a
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        boolean r1 = r1.mUnityVrEnabled     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        if (r1 == 0) goto L_0x005a
                        android.os.Bundle r8 = new android.os.Bundle     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r8.<init>()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r1 = "vr"
                        r2 = 1
                        r8.putBoolean(r1, r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = "Initiating VR purchase intent"
                        r1.logDebug(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r3 = 7
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        android.content.Context r1 = r1.mContext     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r4 = r1.getPackageName()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r5 = r2     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r6 = r3     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r7 = r4     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2 = r10
                        android.os.Bundle r10 = r2.getBuyIntentExtraParams(r3, r4, r5, r6, r7, r8)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        goto L_0x006e
                    L_0x005a:
                        r2 = 3
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        android.content.Context r1 = r1.mContext     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = r1.getPackageName()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r4 = r2     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r5 = r3     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r6 = r4     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1 = r10
                        android.os.Bundle r10 = r1.getBuyIntent(r2, r3, r4, r5, r6)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                    L_0x006e:
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        int r1 = r1.getResponseCodeFromBundle(r10)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        if (r1 == 0) goto L_0x00e3
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.<init>()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = "Unable to buy item, Error response: "
                        r2.append(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = com.unity.purchasing.googleplay.IabHelper.getResponseDesc(r1)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.append(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = r2.toString()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r10.logError(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabResult r10 = new com.unity.purchasing.googleplay.IabResult     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = "Unable to buy item"
                        r10.<init>(r1, r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.common.SaneJSONObject r2 = new com.unity.purchasing.common.SaneJSONObject     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.<init>()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r3 = "productId"
                        java.lang.String r4 = r2     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r2.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r3 = 7
                        if (r1 != r3) goto L_0x00c3
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ JSONException -> 0x00c1 }
                        com.unity.purchasing.googleplay.Inventory r1 = r1.inv     // Catch:{ JSONException -> 0x00c1 }
                        java.lang.String r3 = r2     // Catch:{ JSONException -> 0x00c1 }
                        boolean r1 = r1.hasPurchase(r3)     // Catch:{ JSONException -> 0x00c1 }
                        if (r1 == 0) goto L_0x00c3
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ JSONException -> 0x00c1 }
                        com.unity.purchasing.googleplay.Inventory r1 = r1.inv     // Catch:{ JSONException -> 0x00c1 }
                        java.lang.String r2 = r2     // Catch:{ JSONException -> 0x00c1 }
                        com.unity.purchasing.googleplay.Purchase r1 = r1.getPurchase(r2)     // Catch:{ JSONException -> 0x00c1 }
                        goto L_0x00d0
                    L_0x00c1:
                        r10 = move-exception
                        goto L_0x00df
                    L_0x00c3:
                        com.unity.purchasing.googleplay.Purchase r1 = new com.unity.purchasing.googleplay.Purchase     // Catch:{ JSONException -> 0x00c1 }
                        java.lang.String r3 = r3     // Catch:{ JSONException -> 0x00c1 }
                        java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00c1 }
                        java.lang.String r4 = ""
                        r1.<init>(r3, r2, r4)     // Catch:{ JSONException -> 0x00c1 }
                    L_0x00d0:
                        android.app.Activity r2 = r5     // Catch:{ JSONException -> 0x00c1 }
                        r2.finish()     // Catch:{ JSONException -> 0x00c1 }
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r2 = r6     // Catch:{ JSONException -> 0x00c1 }
                        if (r2 == 0) goto L_0x00de
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r2 = r6     // Catch:{ JSONException -> 0x00c1 }
                        r2.onIabPurchaseFinished(r10, r1)     // Catch:{ JSONException -> 0x00c1 }
                    L_0x00de:
                        return
                    L_0x00df:
                        r10.printStackTrace()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        return
                    L_0x00e3:
                        java.lang.String r1 = "BUY_INTENT"
                        android.os.Parcelable r10 = r10.getParcelable(r1)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r3 = r10
                        android.app.PendingIntent r3 = (android.app.PendingIntent) r3     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.<init>()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = "Launching buy intent for "
                        r1.append(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = r2     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.append(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r2 = ". Request code: "
                        r1.append(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        int r2 = r7     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.append(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r1 = r1.toString()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r10.logDebug(r1)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        int r1 = r7     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r10.mRequestCode = r1     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r10.mPurchaseListener = r1     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r1 = r3     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r10.mPurchasingItemType = r1     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        boolean r10 = r10.mVrSupported     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        if (r10 == 0) goto L_0x014a
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        boolean r10 = r10.mUnityVrEnabled     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        if (r10 == 0) goto L_0x014a
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        boolean r10 = r10.mDaydreamApiAvailable     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        if (r10 == 0) goto L_0x014a
                        com.unity.purchasing.googleplay.IabHelper$3$1 r10 = new com.unity.purchasing.googleplay.IabHelper$3$1     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r10.<init>(r3)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        android.os.Handler r1 = new android.os.Handler     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        android.os.Looper r2 = android.os.Looper.getMainLooper()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.<init>(r2)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.post(r10)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        goto L_0x01bf
                    L_0x014a:
                        r3.getIntentSender()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IabHelper r10 = com.unity.purchasing.googleplay.IabHelper.this     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        com.unity.purchasing.googleplay.IActivityLauncher r1 = r10.launcher     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        android.app.Activity r2 = r5     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        int r4 = r7     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        android.content.Intent r5 = new android.content.Intent     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r5.<init>()     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        java.lang.String r6 = r2     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        r1.startIntentSenderForResult(r2, r3, r4, r5, r6)     // Catch:{ SendIntentException -> 0x0191, RemoteException -> 0x0162 }
                        goto L_0x01bf
                    L_0x0162:
                        r10 = move-exception
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        r2.<init>()
                        java.lang.String r3 = "RemoteException while launching purchase flow for sku "
                        r2.append(r3)
                        java.lang.String r3 = r2
                        r2.append(r3)
                        java.lang.String r2 = r2.toString()
                        r1.logError(r2)
                        r10.printStackTrace()
                        com.unity.purchasing.googleplay.IabResult r10 = new com.unity.purchasing.googleplay.IabResult
                        r1 = -1001(0xfffffffffffffc17, float:NaN)
                        java.lang.String r2 = "Remote exception while starting purchase flow"
                        r10.<init>(r1, r2)
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                        if (r1 == 0) goto L_0x01bf
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                        r1.onIabPurchaseFinished(r10, r0)
                        goto L_0x01bf
                    L_0x0191:
                        r10 = move-exception
                        com.unity.purchasing.googleplay.IabHelper r1 = com.unity.purchasing.googleplay.IabHelper.this
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        r2.<init>()
                        java.lang.String r3 = "SendIntentException while launching purchase flow for sku "
                        r2.append(r3)
                        java.lang.String r3 = r2
                        r2.append(r3)
                        java.lang.String r2 = r2.toString()
                        r1.logError(r2)
                        r10.printStackTrace()
                        com.unity.purchasing.googleplay.IabResult r10 = new com.unity.purchasing.googleplay.IabResult
                        r1 = -1004(0xfffffffffffffc14, float:NaN)
                        java.lang.String r2 = "Failed to send intent."
                        r10.<init>(r1, r2)
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                        if (r1 == 0) goto L_0x01bf
                        com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r1 = r6
                        r1.onIabPurchaseFinished(r10, r0)
                    L_0x01bf:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.IabHelper.AnonymousClass3.workWith(com.android.vending.billing.IInAppBillingService):void");
                }
            });
            return;
        }
        IabResult iabResult = new IabResult(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE, "Subscriptions are not available.");
        if (onIabPurchaseFinishedListener2 != null) {
            onIabPurchaseFinishedListener2.onIabPurchaseFinished(iabResult, (Purchase) null);
        }
    }

    public boolean handleActivityResult(int i, int i2, Intent intent) {
        if (i != this.mRequestCode) {
            return false;
        }
        checkSetupDone("handleActivityResult");
        if (intent == null) {
            logError("Null data in IAB activity result.");
            IabResult iabResult = new IabResult(IABHELPER_BAD_RESPONSE, "Null data in IAB result");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult, (Purchase) null);
            }
            return true;
        }
        int responseCodeFromIntent = getResponseCodeFromIntent(intent);
        String stringExtra = intent.getStringExtra(RESPONSE_INAPP_PURCHASE_DATA);
        String stringExtra2 = intent.getStringExtra(RESPONSE_INAPP_SIGNATURE);
        if (i2 == -1 && responseCodeFromIntent == 0) {
            logDebug("Successful resultcode from purchase activity.");
            logDebug("Purchase data: " + stringExtra);
            logDebug("Data signature: " + stringExtra2);
            logDebug("Extras: " + intent.getExtras());
            logDebug("Expected item type: " + this.mPurchasingItemType);
            if (stringExtra == null || stringExtra2 == null) {
                logError("BUG: either purchaseData or dataSignature is null.");
                logDebug("Extras: " + intent.getExtras().toString());
                IabResult iabResult2 = new IabResult(IABHELPER_UNKNOWN_ERROR, "IAB returned null purchaseData or dataSignature");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(iabResult2, (Purchase) null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this.mPurchasingItemType, stringExtra, stringExtra2);
                purchase.getSku();
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(new IabResult(0, "Success"), purchase);
                }
            } catch (JSONException e) {
                logError("Failed to parse purchase data.");
                e.printStackTrace();
                IabResult iabResult3 = new IabResult(IABHELPER_BAD_RESPONSE, "Failed to parse purchase data.");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(iabResult3, (Purchase) null);
                }
                return true;
            }
        } else if (i2 == -1) {
            logDebug("Result code was OK but in-app billing response was not OK: " + getResponseDesc(responseCodeFromIntent));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(new IabResult(responseCodeFromIntent, "Problem purchasing item."), (Purchase) null);
            }
        } else if (i2 == 0) {
            logDebug("Purchase canceled - Response: " + getResponseDesc(responseCodeFromIntent));
            IabResult iabResult4 = new IabResult(IABHELPER_USER_CANCELLED, "User canceled.");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult4, (Purchase) null);
            }
        } else {
            logError("Purchase failed. Result code: " + Integer.toString(i2) + ". Response: " + getResponseDesc(responseCodeFromIntent));
            IabResult iabResult5 = new IabResult(IABHELPER_UNKNOWN_PURCHASE_RESPONSE, "Unknown purchase response.");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult5, (Purchase) null);
            }
        }
        return true;
    }

    public Inventory queryInventory(boolean z, List<String> list, IInAppBillingService iInAppBillingService) throws IabException {
        return queryInventory(z, list, (List<String>) null, iInAppBillingService);
    }

    public Inventory queryInventory(boolean z, List<String> list, List<String> list2, IInAppBillingService iInAppBillingService) throws IabException {
        checkSetupDone("queryInventory");
        try {
            int queryPurchases = queryPurchases(this.inv, "inapp", iInAppBillingService);
            if (queryPurchases == 0) {
                if (z) {
                    int querySkuDetails = querySkuDetails("inapp", this.inv, list, iInAppBillingService);
                    if (querySkuDetails != 0) {
                        throw new IabException(querySkuDetails, "Error refreshing inventory (querying prices of items).");
                    }
                }
                if (this.mSubscriptionsSupported) {
                    int queryPurchases2 = queryPurchases(this.inv, "subs", iInAppBillingService);
                    if (queryPurchases2 != 0) {
                        throw new IabException(queryPurchases2, "Error refreshing inventory (querying owned subscriptions).");
                    } else if (z) {
                        int querySkuDetails2 = querySkuDetails("subs", this.inv, list, iInAppBillingService);
                        if (querySkuDetails2 != 0) {
                            throw new IabException(querySkuDetails2, "Error refreshing inventory (querying prices of subscriptions).");
                        }
                    }
                }
                if (this.mSubscriptionPurchaseHistorySupported) {
                    int queryPurchaseHistory = queryPurchaseHistory(this.inv, "subs", iInAppBillingService);
                    if (queryPurchaseHistory != 0) {
                        throw new IabException(queryPurchaseHistory, "Error query Purchase History");
                    }
                }
                return this.inv;
            }
            throw new IabException(queryPurchases, "Error refreshing inventory (querying owned items).");
        } catch (RemoteException e) {
            throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while refreshing inventory.", e);
        } catch (JSONException e2) {
            throw new IabException(IABHELPER_BAD_RESPONSE, "Error parsing JSON response while refreshing inventory.", e2);
        } catch (SecurityException e3) {
            throw new IabException(IABHELPER_UNKNOWN_ERROR, "SecurityException querying inventory, update Google Play - https://github.com/googlesamples/android-play-billing/issues/26", e3);
        }
    }

    public void queryInventoryAsync(boolean z, List<String> list, QueryInventoryFinishedListener queryInventoryFinishedListener, long j) {
        checkSetupDone("queryInventory");
        final long j2 = j;
        final boolean z2 = z;
        final List<String> list2 = list;
        final QueryInventoryFinishedListener queryInventoryFinishedListener2 = queryInventoryFinishedListener;
        this.serviceManager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService iInAppBillingService) {
                Inventory inventory;
                try {
                    Thread.sleep(j2);
                } catch (InterruptedException unused) {
                }
                IabResult iabResult = new IabResult(0, "Inventory refresh successful.");
                try {
                    inventory = IabHelper.this.queryInventory(z2, list2, iInAppBillingService);
                } catch (IabException e) {
                    iabResult = e.getResult();
                    inventory = null;
                }
                if (!IabHelper.this.mDisposed && queryInventoryFinishedListener2 != null) {
                    try {
                        queryInventoryFinishedListener2.onQueryInventoryFinished(iabResult, inventory);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void queryInventoryAsync(QueryInventoryFinishedListener queryInventoryFinishedListener) {
        queryInventoryAsync(true, (List<String>) null, queryInventoryFinishedListener, 0);
    }

    public void queryInventoryAsync(boolean z, QueryInventoryFinishedListener queryInventoryFinishedListener) {
        queryInventoryAsync(z, (List<String>) null, queryInventoryFinishedListener, 0);
    }

    /* access modifiers changed from: package-private */
    public void consume(Purchase purchase, IInAppBillingService iInAppBillingService) throws IabException {
        if (purchase.mItemType.equals("inapp")) {
            try {
                String token = purchase.getToken();
                String sku = purchase.getSku();
                if (token == null || token.equals("")) {
                    logError("Can't consume " + sku + ". No token.");
                    throw new IabException((int) IABHELPER_MISSING_TOKEN, "PurchaseInfo is missing token for sku: " + sku + " " + purchase);
                }
                logDebug("Consuming sku: " + sku + ", token: " + token);
                int consumePurchase = iInAppBillingService.consumePurchase(3, this.mContext.getPackageName(), token);
                if (consumePurchase == 0) {
                    logDebug("Successfully consumed sku: " + sku);
                    return;
                }
                logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(consumePurchase));
                throw new IabException(consumePurchase, "Error consuming sku " + sku);
            } catch (RemoteException e) {
                throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while consuming. PurchaseInfo: " + purchase, e);
            }
        } else {
            throw new IabException((int) IABHELPER_INVALID_CONSUMPTION, "Items of type '" + purchase.mItemType + "' can't be consumed.");
        }
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener onConsumeFinishedListener) {
        checkSetupDone("consume");
        ArrayList arrayList = new ArrayList();
        arrayList.add(purchase);
        consumeAsyncInternal(arrayList, onConsumeFinishedListener, (OnConsumeMultiFinishedListener) null);
    }

    public void consumeAsync(List<Purchase> list, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        checkSetupDone("consume");
        consumeAsyncInternal(list, (OnConsumeFinishedListener) null, onConsumeMultiFinishedListener);
    }

    public static String getResponseDesc(int i) {
        String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (i <= -1000) {
            int i2 = -1000 - i;
            if (i2 >= 0 && i2 < split2.length) {
                return split2[i2];
            }
            return String.valueOf(i) + ":Unknown IAB Helper Error";
        } else if (i >= 0 && i < split.length) {
            return split[i];
        } else {
            return String.valueOf(i) + ":Unknown";
        }
    }

    /* access modifiers changed from: package-private */
    public void checkSetupDone(String str) {
        if (!this.mSetupDone) {
            logError("Illegal state for operation (" + str + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + str);
        }
    }

    /* access modifiers changed from: package-private */
    public int getResponseCodeFromBundle(Bundle bundle) {
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            logDebug("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            logError("Unexpected type for bundle response code.");
            logError(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + obj.getClass().getName());
        }
    }

    /* access modifiers changed from: package-private */
    public int getResponseCodeFromIntent(Intent intent) {
        Object obj = intent.getExtras().get("RESPONSE_CODE");
        if (obj == null) {
            logError("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            logError("Unexpected type for intent response code.");
            logError(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + obj.getClass().getName());
        }
    }

    /* access modifiers changed from: package-private */
    public int queryPurchaseHistory(Inventory inventory, String str, IInAppBillingService iInAppBillingService) throws JSONException, RemoteException {
        if (this.mDisposed) {
            logDebug("queryPurchase History - Biller disposed. Returning...");
            return 0;
        }
        logDebug("Querying owned items' purchase history, item type: " + str);
        logDebug("Package name: " + this.mContext.getPackageName());
        String str2 = null;
        do {
            logDebug("Calling getPurchaseHistory with continuation token: " + str2);
            Bundle purchaseHistory = iInAppBillingService.getPurchaseHistory(6, this.mContext.getPackageName(), str, str2, new Bundle());
            if (purchaseHistory == null) {
                return 0;
            }
            int responseCodeFromBundle = getResponseCodeFromBundle(purchaseHistory);
            logDebug("Purchase history response: " + String.valueOf(responseCodeFromBundle));
            if (responseCodeFromBundle != 0) {
                logDebug("getPurchaseHistory() failed: " + getResponseDesc(responseCodeFromBundle));
                return responseCodeFromBundle;
            } else if (!purchaseHistory.containsKey(RESPONSE_INAPP_ITEM_LIST) || !purchaseHistory.containsKey(RESPONSE_INAPP_PURCHASE_DATA_LIST) || !purchaseHistory.containsKey(RESPONSE_INAPP_SIGNATURE_LIST)) {
                logError("Bundle returned from getPurchaseHistory() doesn't contain required fields.");
                return IABHELPER_BAD_RESPONSE;
            } else {
                ArrayList<String> stringArrayList = purchaseHistory.getStringArrayList(RESPONSE_INAPP_ITEM_LIST);
                for (int i = 0; i < stringArrayList.size(); i++) {
                    String str3 = stringArrayList.get(i);
                    logDebug("This sku has purchase history: " + str3);
                    inventory.addPurchaseToSubscriptionPurchaseHistory(str3);
                }
                str2 = purchaseHistory.getString(INAPP_CONTINUATION_TOKEN);
                logDebug("Continuation token: " + str2);
            }
        } while (!TextUtils.isEmpty(str2));
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int queryPurchases(Inventory inventory, String str, IInAppBillingService iInAppBillingService) throws JSONException, RemoteException {
        if (this.mDisposed) {
            logDebug("queryPurchases - Biller disposed. Returning...");
            return 0;
        }
        logDebug("Querying owned items, item type: " + str);
        logDebug("Package name: " + this.mContext.getPackageName());
        String str2 = null;
        do {
            logDebug("Calling getPurchases with continuation token: " + str2);
            Bundle purchases = iInAppBillingService.getPurchases(3, this.mContext.getPackageName(), str, str2);
            int responseCodeFromBundle = getResponseCodeFromBundle(purchases);
            logDebug("Owned items response: " + String.valueOf(responseCodeFromBundle));
            if (responseCodeFromBundle != 0) {
                logDebug("getPurchases() failed: " + getResponseDesc(responseCodeFromBundle));
                return responseCodeFromBundle;
            } else if (!purchases.containsKey(RESPONSE_INAPP_ITEM_LIST) || !purchases.containsKey(RESPONSE_INAPP_PURCHASE_DATA_LIST) || !purchases.containsKey(RESPONSE_INAPP_SIGNATURE_LIST)) {
                logError("Bundle returned from getPurchases() doesn't contain required fields.");
                return IABHELPER_BAD_RESPONSE;
            } else {
                ArrayList<String> stringArrayList = purchases.getStringArrayList(RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> stringArrayList2 = purchases.getStringArrayList(RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> stringArrayList3 = purchases.getStringArrayList(RESPONSE_INAPP_SIGNATURE_LIST);
                for (int i = 0; i < stringArrayList2.size(); i++) {
                    String str3 = stringArrayList2.get(i);
                    logDebug("Sku is owned: " + stringArrayList.get(i));
                    Purchase purchase = new Purchase(str, str3, stringArrayList3.get(i));
                    if (TextUtils.isEmpty(purchase.getToken())) {
                        logWarn("BUG: empty/null token!");
                        logDebug("Purchase data: " + str3);
                    }
                    inventory.addPurchase(purchase);
                }
                str2 = purchases.getString(INAPP_CONTINUATION_TOKEN);
                logDebug("Continuation token: " + str2);
            }
        } while (!TextUtils.isEmpty(str2));
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int querySkuDetails(String str, Inventory inventory, List<String> list, IInAppBillingService iInAppBillingService) throws RemoteException, JSONException {
        logDebug("Querying SKU details.");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inventory.getAllOwnedSkus(str));
        if (list != null) {
            arrayList.addAll(list);
        }
        if (arrayList.size() == 0) {
            logDebug("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        while (arrayList.size() > 0) {
            int min = Math.min(20, arrayList.size());
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < min; i++) {
                arrayList2.add(arrayList.get(i));
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, arrayList2);
            Bundle skuDetails = iInAppBillingService.getSkuDetails(3, this.mContext.getPackageName(), str, bundle);
            arrayList.removeAll(arrayList2);
            if (!skuDetails.containsKey(RESPONSE_GET_SKU_DETAILS_LIST)) {
                int responseCodeFromBundle = getResponseCodeFromBundle(skuDetails);
                if (responseCodeFromBundle != 0) {
                    logDebug("getSkuDetails() failed: " + getResponseDesc(responseCodeFromBundle));
                    return responseCodeFromBundle;
                }
                logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return IABHELPER_BAD_RESPONSE;
            }
            Iterator<String> it = skuDetails.getStringArrayList(RESPONSE_GET_SKU_DETAILS_LIST).iterator();
            while (it.hasNext()) {
                inventory.addSkuDetails(new SkuDetails(str, it.next()));
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void consumeAsyncInternal(final List<Purchase> list, final OnConsumeFinishedListener onConsumeFinishedListener, final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        this.serviceManager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService iInAppBillingService) {
                ArrayList arrayList = new ArrayList();
                for (Purchase purchase : list) {
                    try {
                        IabHelper.this.consume(purchase, iInAppBillingService);
                        arrayList.add(new IabResult(0, "Successful consume of sku " + purchase.getSku()));
                    } catch (IabException e) {
                        arrayList.add(e.getResult());
                    }
                }
                if (!IabHelper.this.mDisposed && onConsumeFinishedListener != null) {
                    try {
                        onConsumeFinishedListener.onConsumeFinished((Purchase) list.get(0), (IabResult) arrayList.get(0));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                if (!IabHelper.this.mDisposed && onConsumeMultiFinishedListener != null) {
                    onConsumeMultiFinishedListener.onConsumeMultiFinished(list, arrayList);
                }
            }
        });
    }

    public static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Integer.valueOf(bArr[i] & 255)}));
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void logDebug(String str) {
        Log.i("UnityIAP", str);
    }

    /* access modifiers changed from: package-private */
    public void logError(String str) {
        String str2 = this.mDebugTag;
        Log.e(str2, "In-app billing error: " + str);
    }

    /* access modifiers changed from: package-private */
    public void logWarn(String str) {
        String str2 = this.mDebugTag;
        Log.w(str2, "In-app billing warning: " + str);
    }
}
