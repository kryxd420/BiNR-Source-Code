package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.google.vr.ndk.base.DaydreamApi;
import com.unity.purchasing.common.IStoreCallback;
import com.unity.purchasing.common.IUnityCallback;
import com.unity.purchasing.common.InitializationFailureReason;
import com.unity.purchasing.common.ProductDefinition;
import com.unity.purchasing.common.ProductDescription;
import com.unity.purchasing.common.ProductMetadata;
import com.unity.purchasing.common.ProductType;
import com.unity.purchasing.common.PurchaseFailureDescription;
import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.StoreDeserializer;
import com.unity.purchasing.common.UnityPurchasing;
import com.unity.purchasing.googleplay.IabHelper;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlayPurchasing extends StoreDeserializer {
    public static final int ACTIVITY_REQUEST_CODE = 999;
    protected static final String TAG = "UnityIAP";
    private static GooglePlayPurchasing instance;
    private static final boolean isDaydreamApiAvailable;
    public IabHelper.OnIabPurchaseFinishedListener PurchaseListener = new IabHelper.OnIabPurchaseFinishedListener() {
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0092  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x009d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onIabPurchaseFinished(com.unity.purchasing.googleplay.IabResult r6, com.unity.purchasing.googleplay.Purchase r7) {
            /*
                r5 = this;
                com.unity.purchasing.googleplay.GooglePlayPurchasing r0 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                boolean r0 = r0.purchaseInProgress
                if (r0 != 0) goto L_0x0009
                return
            L_0x0009:
                java.lang.String r0 = "onIabPurchaseFinished: %s"
                boolean r1 = r6.isSuccess()
                java.lang.String r1 = java.lang.Boolean.toString(r1)
                com.unity.purchasing.googleplay.GooglePlayPurchasing.log(r0, r1)
                java.lang.String r0 = r6.mMessage
                com.unity.purchasing.googleplay.GooglePlayPurchasing.log(r0)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r0 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                r1 = 0
                boolean unused = r0.purchaseInProgress = r1
                boolean r0 = r6.isSuccess()
                if (r0 == 0) goto L_0x0033
                java.lang.String r6 = "Product purchased successfully!"
                com.unity.purchasing.googleplay.GooglePlayPurchasing.log(r6)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                r6.NotifyUnityOfPurchase(r7)
                goto L_0x00a6
            L_0x0033:
                int r7 = r6.getResponse()
                java.lang.String r0 = "Purchase response code:%s"
                java.lang.String r1 = java.lang.Integer.toString(r7)
                com.unity.purchasing.googleplay.GooglePlayPurchasing.log(r0, r1)
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.Unknown
                r1 = -1005(0xfffffffffffffc13, float:NaN)
                if (r7 == r1) goto L_0x005e
                r1 = 7
                if (r7 == r1) goto L_0x0053
                switch(r7) {
                    case 1: goto L_0x005e;
                    case 2: goto L_0x0050;
                    case 3: goto L_0x0050;
                    case 4: goto L_0x004d;
                    default: goto L_0x004c;
                }
            L_0x004c:
                goto L_0x0060
            L_0x004d:
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.ItemUnavailable
                goto L_0x0060
            L_0x0050:
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.BillingUnavailable
                goto L_0x0060
            L_0x0053:
                com.unity.purchasing.googleplay.GooglePlayPurchasing r1 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.googleplay.GooglePlayPurchasing$Features r1 = r1.features
                boolean r1 = r1.supportsPurchaseFailureReasonDuplicateTransaction
                if (r1 == 0) goto L_0x0060
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.DuplicateTransaction
                goto L_0x0060
            L_0x005e:
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.UserCancelled
            L_0x0060:
                com.unity.purchasing.common.PurchaseFailureDescription r1 = new com.unity.purchasing.common.PurchaseFailureDescription
                com.unity.purchasing.googleplay.GooglePlayPurchasing r2 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                java.lang.String r2 = r2.skuUnderPurchase
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "GOOGLEPLAY_"
                r3.append(r4)
                java.lang.String r4 = r6.mMessage
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.util.HashMap<java.lang.Integer, java.lang.String> r4 = com.unity.purchasing.googleplay.IabHelper.billingResponseCodeNames
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                java.lang.Object r7 = r4.get(r7)
                java.lang.String r7 = (java.lang.String) r7
                r1.<init>(r2, r0, r3, r7)
                int r6 = r6.getResponse()
                r7 = -1002(0xfffffffffffffc16, float:NaN)
                if (r6 != r7) goto L_0x009d
                java.lang.String r6 = "Received bad response, polling for successful purchases to investigate failure more deeply"
                com.unity.purchasing.googleplay.GooglePlayPurchasing.log(r6)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                r6.reconcileFailedPurchaseWithInventory(r1)
                goto L_0x00a6
            L_0x009d:
                com.unity.purchasing.googleplay.GooglePlayPurchasing r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.common.IStoreCallback r6 = r6.unityPurchasing
                r6.OnPurchaseFailed(r1)
            L_0x00a6:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.GooglePlayPurchasing.AnonymousClass2.onIabPurchaseFinished(com.unity.purchasing.googleplay.IabResult, com.unity.purchasing.googleplay.Purchase):void");
        }
    };
    public boolean activityPending;
    /* access modifiers changed from: private */
    public Context context;
    Features features = new Features();
    public IabHelper helper;
    /* access modifiers changed from: private */
    public Inventory inventory;
    private boolean isUnityVrEnabled;
    private IActivityLauncher launcher;
    private IBillingServiceManager manager;
    /* access modifiers changed from: private */
    public int offlineBackOffTime = 5000;
    public String productJSON;
    /* access modifiers changed from: private */
    public volatile boolean purchaseInProgress = false;
    private BroadcastReceiver purchasesUpdatedReceiver = null;
    /* access modifiers changed from: private */
    public String skuUnderPurchase;
    private volatile boolean subscriptionUpdateInProgress = false;
    /* access modifiers changed from: private */
    public IStoreCallback unityPurchasing;

    static {
        boolean z;
        try {
            Class.forName("com.google.vr.ndk.base.DaydreamApi");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        isDaydreamApiAvailable = z;
    }

    class Features {
        public boolean supportsPurchaseFailureReasonDuplicateTransaction;

        Features() {
        }
    }

    public static GooglePlayPurchasing instance(IUnityCallback iUnityCallback) {
        if (instance == null) {
            BillingServiceManager billingServiceManager = new BillingServiceManager(UnityPlayer.currentActivity);
            instance = new GooglePlayPurchasing(new UnityPurchasing(iUnityCallback), new IabHelper(UnityPlayer.currentActivity, billingServiceManager, new ActivityLauncher()), billingServiceManager, UnityPlayer.currentActivity, new ActivityLauncher());
        }
        return instance;
    }

    public static boolean ContinuePurchase(Activity activity, String str, String str2) {
        if (instance == null) {
            return false;
        }
        instance.StartPurchase(activity, str, str2);
        return true;
    }

    public static boolean ContinueSubscriptionUpdate(Activity activity, String str, String str2) {
        if (instance == null) {
            return false;
        }
        instance.StartSubscriptionUpdate(activity, str, str2);
        return true;
    }

    public static void ProcessActivityResult(int i, int i2, Intent intent) {
        if (instance != null) {
            instance.onActivityResult(i, i2, intent);
        }
    }

    public GooglePlayPurchasing(IStoreCallback iStoreCallback, IabHelper iabHelper, IBillingServiceManager iBillingServiceManager, Context context2, IActivityLauncher iActivityLauncher) {
        this.unityPurchasing = iStoreCallback;
        this.helper = iabHelper;
        this.helper.enableDaydreamApi(isDaydreamApiAvailable);
        this.manager = iBillingServiceManager;
        this.context = context2;
        this.launcher = iActivityLauncher;
        instance = this;
        registerPurchasesUpdatedReceiver();
    }

    public void SetUnityVrEnabled(boolean z) {
        this.isUnityVrEnabled = z;
        log("isUnityVrEnabled = %s", String.valueOf(this.isUnityVrEnabled));
    }

    public void UpgradeDowngradeSubscription(String str, String str2) {
        if (this.subscriptionUpdateInProgress) {
            log("Subscription update is in progress");
        } else if (!this.helper.subscriptionUpgradeDowngradeSupported()) {
            log("UpgradeDowngradeSubscription is not supported, this service needs v5 and higher android in app billing api");
        } else if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            log("Cannot update subscription. Subscription product identifiers(SKUs) must not be empty");
        } else {
            boolean z = (this.context instanceof UnityPlayerActivity) && this.isUnityVrEnabled && isDaydreamApiAvailable;
            final Intent createPurchaseIntent = createPurchaseIntent(z);
            createPurchaseIntent.putExtra("oldSkuMetadata", str);
            createPurchaseIntent.putExtra("newSku", str2);
            createPurchaseIntent.putExtra("type", "subscription_update");
            this.subscriptionUpdateInProgress = true;
            this.activityPending = true;
            if (z) {
                new Handler(this.context.getMainLooper()).post(new Runnable() {
                    public void run() {
                        createPurchaseIntent.putExtra("vr", true);
                        DaydreamApi create = DaydreamApi.create(GooglePlayPurchasing.this.context);
                        create.launchInVr(createPurchaseIntent);
                        create.close();
                    }
                });
            } else {
                this.launcher.startActivity(this.context, createPurchaseIntent);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void StartSubscriptionUpdate(android.app.Activity r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0015 }
            r1.<init>(r9)     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r2 = "productId"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x0015 }
            if (r2 == 0) goto L_0x0019
            java.lang.String r2 = "productId"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x0015 }
            goto L_0x001a
        L_0x0015:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0019:
            r1 = r0
        L_0x001a:
            if (r1 != 0) goto L_0x0022
            java.lang.String r8 = "Error: the product that is going to be updated does not have a valid product id"
            log(r8)
            return
        L_0x0022:
            com.unity.purchasing.googleplay.Inventory r2 = r7.inventory
            boolean r2 = r2.hasDetails(r1)
            if (r2 != 0) goto L_0x0030
            java.lang.String r8 = "Error: the product that is going to be updated is not in the current inventory"
            log(r8)
            return
        L_0x0030:
            com.unity.purchasing.googleplay.Inventory r2 = r7.inventory
            boolean r2 = r2.hasPurchase(r1)
            if (r2 != 0) goto L_0x003e
            java.lang.String r8 = "Error: the product that is going to be updated has not been purchased yet."
            log(r8)
            return
        L_0x003e:
            com.unity.purchasing.googleplay.Inventory r2 = r7.inventory
            boolean r2 = r2.hasDetails(r10)
            if (r2 != 0) goto L_0x004c
            java.lang.String r8 = "Error: the product that is going to be updated to is not in the current inventory"
            log(r8)
            return
        L_0x004c:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r3.add(r1)
            java.lang.String r6 = r7.addFreeTrialAndIntroPriceFlagToDeveloperPayload(r0, r10, r9)
            com.unity.purchasing.googleplay.IabHelper r0 = r7.helper
            r4 = 999(0x3e7, float:1.4E-42)
            com.unity.purchasing.googleplay.IabHelper$OnIabPurchaseFinishedListener r5 = r7.PurchaseListener
            r1 = r8
            r2 = r10
            r0.launchSubscriptionUpdateFlow(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.GooglePlayPurchasing.StartSubscriptionUpdate(android.app.Activity, java.lang.String, java.lang.String):void");
    }

    public void StartPurchase(Activity activity, String str, String str2) {
        this.helper.enableUnityVr(this.isUnityVrEnabled);
        String addFreeTrialAndIntroPriceFlagToDeveloperPayload = addFreeTrialAndIntroPriceFlagToDeveloperPayload(str2, str, (String) null);
        if (this.inventory.getSkuDetails(str).mItemType == "inapp") {
            this.helper.launchPurchaseFlow(activity, str, ACTIVITY_REQUEST_CODE, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload);
            return;
        }
        this.helper.launchSubscriptionPurchaseFlow(activity, str, ACTIVITY_REQUEST_CODE, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload);
    }

    private String addFreeTrialAndIntroPriceFlagToDeveloperPayload(String str, String str2, String str3) {
        boolean z;
        boolean z2;
        String str4 = null;
        SkuDetails skuDetails = this.inventory.hasDetails(str2) ? this.inventory.getSkuDetails(str2) : null;
        if (str == null) {
            str = "";
        }
        boolean z3 = false;
        String encodeToString = Base64.encodeToString(str.getBytes(), 0);
        if (skuDetails == null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("developerPayload", encodeToString);
                jSONObject.put("is_free_trial", false);
                jSONObject.put("has_introductory_price_trial", false);
                jSONObject.put("is_updated", false);
                jSONObject.put("update_subscription_metadata", (Object) null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject.toString();
        }
        if (str3 != null) {
            log("oldSkuMetadata is NOT null");
            str4 = getUpdateMetadata(str3, skuDetails);
            z = true;
        } else {
            log("oldSkuMetadata is null");
            z = false;
        }
        String type = skuDetails.getType();
        String introductoryPricePeriod = skuDetails.getIntroductoryPricePeriod();
        String freeTrialPeriod = skuDetails.getFreeTrialPeriod();
        boolean hasPurchaseHistory = this.inventory.hasPurchaseHistory(str2);
        Iterator<String> it = this.inventory.getAllSkus("subs").iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            String next = it.next();
            if (!this.inventory.getSkuDetails(next).getFreeTrialPeriod().isEmpty() && this.inventory.hasPurchaseHistory(next)) {
                z2 = true;
                break;
            }
        }
        boolean z4 = !type.equals("inapp") && !freeTrialPeriod.isEmpty() && !hasPurchaseHistory && !z2;
        if (!type.equals("inapp") && !introductoryPricePeriod.isEmpty() && !hasPurchaseHistory) {
            z3 = true;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("developerPayload", encodeToString);
            jSONObject2.put("is_free_trial", z4);
            jSONObject2.put("has_introductory_price_trial", z3);
            jSONObject2.put("is_updated", z);
            jSONObject2.put("update_subscription_metadata", str4);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject2.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getUpdateMetadata(java.lang.String r13, com.unity.purchasing.googleplay.SkuDetails r14) {
        /*
            r12 = this;
            long r0 = r14.getPriceInMicros()
            r2 = 0
            r3 = 0
            r4 = 0
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0034 }
            r6.<init>(r13)     // Catch:{ JSONException -> 0x0034 }
            java.lang.String r13 = "productId"
            java.lang.String r13 = r6.getString(r13)     // Catch:{ JSONException -> 0x0034 }
            java.lang.String r7 = "is_free_trial"
            boolean r7 = r6.getBoolean(r7)     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r8 = "is_introductory_price_period"
            boolean r8 = r6.getBoolean(r8)     // Catch:{ JSONException -> 0x002c }
            java.lang.String r2 = "remaining_time_in_seconds"
            double r9 = r6.getDouble(r2)     // Catch:{ JSONException -> 0x0027 }
            long r9 = (long) r9
            goto L_0x003d
        L_0x0027:
            r2 = move-exception
            r11 = r2
            r2 = r13
            r13 = r11
            goto L_0x0038
        L_0x002c:
            r6 = move-exception
            r2 = r13
            r13 = r6
            goto L_0x0037
        L_0x0030:
            r6 = move-exception
            r2 = r13
            r13 = r6
            goto L_0x0036
        L_0x0034:
            r13 = move-exception
            r2 = r3
        L_0x0036:
            r7 = 0
        L_0x0037:
            r8 = 0
        L_0x0038:
            r13.printStackTrace()
            r13 = r2
            r9 = r4
        L_0x003d:
            if (r13 == 0) goto L_0x008f
            com.unity.purchasing.googleplay.Inventory r2 = r12.inventory
            boolean r2 = r2.hasDetails(r13)
            if (r2 != 0) goto L_0x0048
            goto L_0x008f
        L_0x0048:
            com.unity.purchasing.googleplay.Inventory r2 = r12.inventory
            com.unity.purchasing.googleplay.SkuDetails r13 = r2.getSkuDetails(r13)
            if (r7 == 0) goto L_0x0051
            r9 = r4
        L_0x0051:
            if (r8 == 0) goto L_0x0064
            long r2 = r14.getPriceInMicros()
            int r14 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x0064
            long r2 = r13.getIntroductoryPriceInMicros()
            java.lang.String r13 = r13.getIntroductoryPricePeriod()
            goto L_0x006c
        L_0x0064:
            long r2 = r13.getPriceInMicros()
            java.lang.String r13 = r13.getSubscriptionPeriod()
        L_0x006c:
            org.json.JSONObject r14 = new org.json.JSONObject
            r14.<init>()
            java.lang.String r4 = "old_sku_remaining_seconds"
            r14.put(r4, r9)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r4 = "old_sku_price_in_micros"
            r14.put(r4, r2)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r2 = "old_sku_period_string"
            r14.put(r2, r13)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r13 = "new_sku_price_in_micros"
            r14.put(r13, r0)     // Catch:{ JSONException -> 0x0086 }
            goto L_0x008a
        L_0x0086:
            r13 = move-exception
            r13.printStackTrace()
        L_0x008a:
            java.lang.String r13 = r14.toString()
            return r13
        L_0x008f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.GooglePlayPurchasing.getUpdateMetadata(java.lang.String, com.unity.purchasing.googleplay.SkuDetails):java.lang.String");
    }

    /* access modifiers changed from: private */
    public void pollForNewPurchases() {
        reconcileFailedPurchaseWithInventory((PurchaseFailureDescription) null);
    }

    /* access modifiers changed from: private */
    public void reconcileFailedPurchaseWithInventory(final PurchaseFailureDescription purchaseFailureDescription) {
        this.manager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService iInAppBillingService) {
                boolean z = false;
                try {
                    boolean hasPurchase = purchaseFailureDescription != null ? GooglePlayPurchasing.this.inventory.hasPurchase(purchaseFailureDescription.productId) : false;
                    if (GooglePlayPurchasing.this.helper.queryPurchases(GooglePlayPurchasing.this.inventory, "inapp", iInAppBillingService) != 0) {
                        GooglePlayPurchasing.log("Received bad response from queryPurchases");
                    }
                    boolean hasPurchase2 = purchaseFailureDescription != null ? GooglePlayPurchasing.this.inventory.hasPurchase(purchaseFailureDescription.productId) : false;
                    if (purchaseFailureDescription != null) {
                        if ((!hasPurchase && !hasPurchase2) || (hasPurchase && hasPurchase2)) {
                            GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(purchaseFailureDescription);
                        } else if (!hasPurchase && hasPurchase2) {
                            Purchase purchase = GooglePlayPurchasing.this.inventory.getPurchase(purchaseFailureDescription.productId);
                            GooglePlayPurchasing.this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), GooglePlayPurchasing.this.encodeReceipt(purchase, GooglePlayPurchasing.this.inventory.getSkuDetails(purchaseFailureDescription.productId)), purchase.getOrderIdOrPurchaseToken());
                        }
                        z = true;
                    }
                    if (!z) {
                        GooglePlayPurchasing.this.NotifyUnityOfProducts(GooglePlayPurchasing.this.inventory);
                    }
                } catch (RemoteException | JSONException e) {
                    Log.e(GooglePlayPurchasing.TAG, "exception", e);
                    if (purchaseFailureDescription != null && 0 == 0) {
                        GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(purchaseFailureDescription);
                    }
                }
            }
        });
    }

    private void registerPurchasesUpdatedReceiver() {
        if (this.purchasesUpdatedReceiver == null) {
            this.purchasesUpdatedReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    GooglePlayPurchasing.this.pollForNewPurchases();
                }
            };
            this.context.registerReceiver(this.purchasesUpdatedReceiver, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
        }
    }

    /* access modifiers changed from: private */
    public void QueryInventory(final List<String> list, long j) {
        log("QueryInventory: %s", Integer.toString(list.size()));
        this.helper.queryInventoryAsync(true, list, new IabHelper.QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) throws Exception {
                GooglePlayPurchasing.log("onQueryInventoryFinished: %s", Boolean.toString(iabResult.isSuccess()));
                GooglePlayPurchasing.log(iabResult.mMessage);
                if (iabResult.isFailure()) {
                    GooglePlayPurchasing.log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    GooglePlayPurchasing.this.QueryInventory(list, (long) GooglePlayPurchasing.this.offlineBackOffTime);
                    int unused = GooglePlayPurchasing.this.offlineBackOffTime = Math.min(300000, GooglePlayPurchasing.this.offlineBackOffTime * 2);
                    return;
                }
                Inventory unused2 = GooglePlayPurchasing.this.inventory = inventory;
                GooglePlayPurchasing.log("The number of owned skus is" + GooglePlayPurchasing.this.inventory.getAllSkus("subs").size());
                GooglePlayPurchasing.log("The number of purchased skus is" + GooglePlayPurchasing.this.inventory.getAllPurchases().size());
                GooglePlayPurchasing.log("The number of subscriptions purchased history is" + GooglePlayPurchasing.this.inventory.getSubscriptionsWithHistory().size());
                GooglePlayPurchasing.this.NotifyUnityOfProducts(inventory);
            }
        }, j);
    }

    /* access modifiers changed from: private */
    public void NotifyUnityOfProducts(Inventory inventory2) {
        String str;
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : inventory2.mSkuMap.entrySet()) {
            SkuDetails skuDetails = (SkuDetails) next.getValue();
            try {
                jSONObject.put((String) next.getKey(), skuDetails.getOriginalJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ProductMetadata productMetadata = new ProductMetadata(skuDetails.getPrice(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getISOCurrencyCode(), new BigDecimal(skuDetails.getPriceInMicros()).divide(new BigDecimal(1000000)));
            String str2 = (String) next.getKey();
            String str3 = null;
            if (inventory2.hasPurchase(str2)) {
                Purchase purchase = inventory2.getPurchase(str2);
                str3 = encodeReceipt(purchase, inventory2.getSkuDetails(str2));
                str = purchase.getOrderIdOrPurchaseToken();
            } else {
                str = null;
            }
            arrayList.add(new ProductDescription(str2, productMetadata, str3, str));
        }
        this.productJSON = jSONObject.toString();
        this.unityPurchasing.OnProductsRetrieved(arrayList);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.helper != null) {
            log("onActivityResult");
            this.helper.handleActivityResult(i, i2, intent);
            this.purchaseInProgress = false;
            this.subscriptionUpdateInProgress = false;
        }
    }

    private Purchase findPurchaseByOrderId(String str) {
        for (Purchase next : this.inventory.getAllPurchases()) {
            if (next.getOrderIdOrPurchaseToken().equals(str)) {
                return next;
            }
        }
        log("No consumable with order %s", str);
        return null;
    }

    /* access modifiers changed from: private */
    public void NotifyUnityOfPurchase(Purchase purchase) {
        log("NotifyUnityOfPurchase");
        this.inventory.addPurchase(purchase);
        SkuDetails skuDetails = this.inventory.getSkuDetails(purchase.getSku());
        if (purchase.getItemType().equals("subs")) {
            this.inventory.addPurchaseToSubscriptionPurchaseHistory(purchase.getSku());
            log("Add" + purchase.getSku() + "to purchase history map");
        }
        this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), encodeReceipt(purchase, skuDetails), purchase.getOrderIdOrPurchaseToken());
    }

    /* access modifiers changed from: private */
    public String encodeReceipt(Purchase purchase, SkuDetails skuDetails) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("json", purchase.getOriginalJson());
            jSONObject.put(InAppPurchaseMetaData.KEY_SIGNATURE, purchase.getSignature());
            jSONObject.put("skuDetails", skuDetails.getOriginalJSON());
            jSONObject.put("isPurchaseHistorySupported", this.helper.subscriptionPurchaseHistorySupported());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* access modifiers changed from: private */
    public static void log(String str) {
        Log.i(TAG, str);
    }

    /* access modifiers changed from: private */
    public static void log(String str, String str2) {
        log(String.format(str, new Object[]{str2}));
    }

    public void RetrieveProducts(List<ProductDefinition> list) {
        final ArrayList arrayList = new ArrayList();
        for (ProductDefinition productDefinition : list) {
            arrayList.add(productDefinition.storeSpecificId);
        }
        AnonymousClass6 r4 = new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult iabResult) {
                GooglePlayPurchasing.log("onIabSetupFinished: %s", Integer.toString(iabResult.mResponse));
                if (iabResult.isFailure()) {
                    GooglePlayPurchasing.log("Failed to setup IAB. Notifying Unity...");
                    GooglePlayPurchasing.this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
                    return;
                }
                GooglePlayPurchasing.log("Requesting %s products", Integer.toString(arrayList.size()));
                GooglePlayPurchasing.this.QueryInventory(arrayList, 0);
            }
        };
        if (!this.helper.mSetupDone) {
            try {
                this.manager.initialise();
                this.helper.startSetup(r4);
            } catch (GooglePlayBillingUnAvailableException unused) {
                this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
            }
        } else {
            log("Requesting %s products", Integer.toString(arrayList.size()));
            QueryInventory(arrayList, 0);
        }
    }

    public void Purchase(ProductDefinition productDefinition) {
        Purchase(productDefinition, (String) null);
    }

    public void Purchase(ProductDefinition productDefinition, String str) {
        if (this.purchaseInProgress) {
            this.unityPurchasing.OnPurchaseFailed(new PurchaseFailureDescription(productDefinition.storeSpecificId, PurchaseFailureReason.ExistingPurchasePending));
            return;
        }
        String str2 = productDefinition.storeSpecificId;
        this.skuUnderPurchase = str2;
        log("onPurchaseProduct: %s", str2);
        SkuDetails skuDetails = this.inventory.getSkuDetails(str2);
        log("ITEM TYPE:%s", skuDetails.getType());
        boolean z = (this.context instanceof UnityPlayerActivity) && this.isUnityVrEnabled && isDaydreamApiAvailable;
        final Intent createPurchaseIntent = createPurchaseIntent(z);
        createPurchaseIntent.putExtra(InAppPurchaseMetaData.KEY_PRODUCT_ID, str2);
        createPurchaseIntent.putExtra("itemType", skuDetails.getType());
        createPurchaseIntent.putExtra("developerPayload", str);
        this.purchaseInProgress = true;
        this.activityPending = true;
        if (z) {
            new Handler(this.context.getMainLooper()).post(new Runnable() {
                public void run() {
                    createPurchaseIntent.putExtra("vr", true);
                    DaydreamApi create = DaydreamApi.create(GooglePlayPurchasing.this.context);
                    create.launchInVr(createPurchaseIntent);
                    create.close();
                }
            });
        } else {
            this.launcher.startActivity(this.context, createPurchaseIntent);
        }
    }

    /* access modifiers changed from: protected */
    public Intent createPurchaseIntent(boolean z) {
        return new Intent(this.context, z ? VRPurchaseActivity.class : PurchaseActivity.class);
    }

    public void FinishTransaction(ProductDefinition productDefinition, String str) {
        Purchase findPurchaseByOrderId;
        log("Finish transaction:%s", str);
        if (productDefinition == null) {
            log("Received FinishTransaction for unknown product with transaction %s. Not consuming.", str);
        } else if (productDefinition.type == ProductType.Consumable && (findPurchaseByOrderId = findPurchaseByOrderId(str)) != null) {
            log("Consuming %s", findPurchaseByOrderId.getSku());
            this.inventory.erasePurchase(findPurchaseByOrderId.getSku());
            this.helper.consumeAsync(findPurchaseByOrderId, (IabHelper.OnConsumeFinishedListener) new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase, IabResult iabResult) throws JSONException {
                    GooglePlayPurchasing.log("onConsumeFinished:%s", Boolean.toString(iabResult.isSuccess()));
                    GooglePlayPurchasing.log(iabResult.mMessage);
                    GooglePlayPurchasing.log(String.valueOf(iabResult.getResponse()));
                }
            });
        }
    }

    public void SetFeatures(String str) {
        for (String equals : str.split(",")) {
            if (equals.equals("supportsPurchaseFailureReasonDuplicateTransaction")) {
                this.features.supportsPurchaseFailureReasonDuplicateTransaction = true;
            }
        }
    }
}
