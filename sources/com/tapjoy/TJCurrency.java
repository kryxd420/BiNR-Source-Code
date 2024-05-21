package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyErrorMessage;
import java.util.Map;
import java.util.UUID;

public class TJCurrency {
    private static TJGetCurrencyBalanceListener d;
    private static TJSpendCurrencyListener e;
    private static TJAwardCurrencyListener f;
    private static TJEarnedCurrencyListener g;
    String a = null;
    int b = 0;
    Context c;

    public TJCurrency(Context context) {
        this.c = context;
    }

    public void saveCurrencyBalance(int i) {
        SharedPreferences.Editor edit = this.c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        edit.putInt(TapjoyConstants.PREF_LAST_CURRENCY_BALANCE, i);
        edit.apply();
    }

    public int getLocalCurrencyBalance() {
        return this.c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).getInt(TapjoyConstants.PREF_LAST_CURRENCY_BALANCE, -9999);
    }

    public void getCurrencyBalance(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        d = tJGetCurrencyBalanceListener;
        final Map uRLParams = TapjoyConnectCore.getURLParams();
        new Thread(new Runnable() {
            public final void run() {
                TapjoyURLConnection tapjoyURLConnection = new TapjoyURLConnection();
                TJCurrency.this.a(tapjoyURLConnection.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_GET_CURRENCY_BALANCE_URL_PATH, uRLParams));
            }
        }).start();
    }

    public void spendCurrency(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        if (i < 0) {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the spendCurrency API"));
            return;
        }
        this.a = String.valueOf(i);
        e = tJSpendCurrencyListener;
        final Map uRLParams = TapjoyConnectCore.getURLParams();
        TapjoyUtil.safePut(uRLParams, TapjoyConstants.TJC_CURRENCY, this.a, true);
        new Thread(new Runnable() {
            public final void run() {
                TapjoyURLConnection tapjoyURLConnection = new TapjoyURLConnection();
                TJCurrency.this.b(tapjoyURLConnection.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_SPEND_CURRENCY_URL_PATH, uRLParams));
            }
        }).start();
    }

    public void awardCurrency(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        if (i < 0) {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the awardCurrency API"));
            return;
        }
        this.b = i;
        f = tJAwardCurrencyListener;
        String uuid = UUID.randomUUID().toString();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        final Map genericURLParams = TapjoyConnectCore.getGenericURLParams();
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_CURRENCY, String.valueOf(this.b), true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_GUID, uuid, true);
        TapjoyUtil.safePut(genericURLParams, "timestamp", String.valueOf(currentTimeMillis), true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_VERIFIER, TapjoyConnectCore.getAwardCurrencyVerifier(currentTimeMillis, this.b, uuid), true);
        new Thread(new Runnable() {
            public final void run() {
                TapjoyURLConnection tapjoyURLConnection = new TapjoyURLConnection();
                TJCurrency.this.c(tapjoyURLConnection.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_AWARD_CURRENCY_URL_PATH, genericURLParams));
            }
        }).start();
    }

    public void setEarnedCurrencyListener(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        g = tJEarnedCurrencyListener;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0072, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00cb, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.tapjoy.TapjoyHttpURLResponse r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r6.response     // Catch:{ all -> 0x00cc }
            if (r0 == 0) goto L_0x00b1
            java.lang.String r6 = r6.response     // Catch:{ all -> 0x00cc }
            org.w3c.dom.Document r6 = com.tapjoy.TapjoyUtil.buildDocument(r6)     // Catch:{ all -> 0x00cc }
            if (r6 == 0) goto L_0x00bf
            java.lang.String r0 = "Success"
            org.w3c.dom.NodeList r0 = r6.getElementsByTagName(r0)     // Catch:{ all -> 0x00cc }
            java.lang.String r0 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r0)     // Catch:{ all -> 0x00cc }
            if (r0 == 0) goto L_0x00a2
            java.lang.String r1 = "true"
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x00cc }
            if (r0 == 0) goto L_0x00a2
            java.lang.String r0 = "TapPoints"
            org.w3c.dom.NodeList r0 = r6.getElementsByTagName(r0)     // Catch:{ all -> 0x00cc }
            java.lang.String r0 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r0)     // Catch:{ all -> 0x00cc }
            java.lang.String r1 = "CurrencyName"
            org.w3c.dom.NodeList r6 = r6.getElementsByTagName(r1)     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r6)     // Catch:{ all -> 0x00cc }
            if (r0 == 0) goto L_0x0093
            if (r6 == 0) goto L_0x0093
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0073 }
            int r1 = r5.getLocalCurrencyBalance()     // Catch:{ Exception -> 0x0073 }
            com.tapjoy.TJEarnedCurrencyListener r2 = g     // Catch:{ Exception -> 0x0073 }
            if (r2 == 0) goto L_0x0065
            r2 = -9999(0xffffffffffffd8f1, float:NaN)
            if (r1 == r2) goto L_0x0065
            if (r0 <= r1) goto L_0x0065
            java.lang.String r2 = "TJCurrency"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0073 }
            java.lang.String r4 = "earned: "
            r3.<init>(r4)     // Catch:{ Exception -> 0x0073 }
            int r1 = r0 - r1
            r3.append(r1)     // Catch:{ Exception -> 0x0073 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0073 }
            com.tapjoy.TapjoyLog.i(r2, r3)     // Catch:{ Exception -> 0x0073 }
            com.tapjoy.TJEarnedCurrencyListener r2 = g     // Catch:{ Exception -> 0x0073 }
            r2.onEarnedCurrency(r6, r1)     // Catch:{ Exception -> 0x0073 }
        L_0x0065:
            r5.saveCurrencyBalance(r0)     // Catch:{ Exception -> 0x0073 }
            com.tapjoy.TJGetCurrencyBalanceListener r1 = d     // Catch:{ Exception -> 0x0073 }
            if (r1 == 0) goto L_0x0071
            com.tapjoy.TJGetCurrencyBalanceListener r1 = d     // Catch:{ Exception -> 0x0073 }
            r1.onGetCurrencyBalanceResponse(r6, r0)     // Catch:{ Exception -> 0x0073 }
        L_0x0071:
            monitor-exit(r5)
            return
        L_0x0073:
            r6 = move-exception
            java.lang.String r0 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00cc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            java.lang.String r4 = "Error parsing XML and calling listener: "
            r3.<init>(r4)     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00cc }
            r3.append(r6)     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00cc }
            r1.<init>(r2, r6)     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyLog.e((java.lang.String) r0, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x00cc }
            goto L_0x00bf
        L_0x0093:
            java.lang.String r6 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r0 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = "getCurrencyBalance response is invalid -- missing tags."
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyLog.e((java.lang.String) r6, (com.tapjoy.TapjoyErrorMessage) r0)     // Catch:{ all -> 0x00cc }
            goto L_0x00bf
        L_0x00a2:
            java.lang.String r6 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r0 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = "getCurrencyBalance response is invalid -- missing <Success> tag."
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyLog.e((java.lang.String) r6, (com.tapjoy.TapjoyErrorMessage) r0)     // Catch:{ all -> 0x00cc }
            goto L_0x00bf
        L_0x00b1:
            java.lang.String r6 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r0 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = "getCurrencyBalance response is NULL"
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00cc }
            com.tapjoy.TapjoyLog.e((java.lang.String) r6, (com.tapjoy.TapjoyErrorMessage) r0)     // Catch:{ all -> 0x00cc }
        L_0x00bf:
            com.tapjoy.TJGetCurrencyBalanceListener r6 = d     // Catch:{ all -> 0x00cc }
            if (r6 == 0) goto L_0x00ca
            com.tapjoy.TJGetCurrencyBalanceListener r6 = d     // Catch:{ all -> 0x00cc }
            java.lang.String r0 = "Failed to get currency balance"
            r6.onGetCurrencyBalanceResponseFailure(r0)     // Catch:{ all -> 0x00cc }
        L_0x00ca:
            monitor-exit(r5)
            return
        L_0x00cc:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJCurrency.a(com.tapjoy.TapjoyHttpURLResponse):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b2, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.tapjoy.TapjoyHttpURLResponse r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "Failed to spend currency"
            java.lang.String r1 = r5.response     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x009a
            java.lang.String r5 = r5.response     // Catch:{ all -> 0x00b3 }
            org.w3c.dom.Document r5 = com.tapjoy.TapjoyUtil.buildDocument(r5)     // Catch:{ all -> 0x00b3 }
            if (r5 == 0) goto L_0x00a8
            java.lang.String r1 = "Success"
            org.w3c.dom.NodeList r1 = r5.getElementsByTagName(r1)     // Catch:{ all -> 0x00b3 }
            java.lang.String r1 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r1)     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x005c
            java.lang.String r2 = "true"
            boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x00b3 }
            if (r2 == 0) goto L_0x005c
            java.lang.String r1 = "TapPoints"
            org.w3c.dom.NodeList r1 = r5.getElementsByTagName(r1)     // Catch:{ all -> 0x00b3 }
            java.lang.String r1 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r1)     // Catch:{ all -> 0x00b3 }
            java.lang.String r2 = "CurrencyName"
            org.w3c.dom.NodeList r5 = r5.getElementsByTagName(r2)     // Catch:{ all -> 0x00b3 }
            java.lang.String r5 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r5)     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x004d
            if (r5 == 0) goto L_0x004d
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00b3 }
            r4.saveCurrencyBalance(r0)     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TJSpendCurrencyListener r1 = e     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x004b
            com.tapjoy.TJSpendCurrencyListener r1 = e     // Catch:{ all -> 0x00b3 }
            r1.onSpendCurrencyResponse(r5, r0)     // Catch:{ all -> 0x00b3 }
        L_0x004b:
            monitor-exit(r4)
            return
        L_0x004d:
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00b3 }
            java.lang.String r3 = "spendCurrency response is invalid -- missing tags."
            r1.<init>(r2, r3)     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x00b3 }
            goto L_0x00a8
        L_0x005c:
            if (r1 == 0) goto L_0x008b
            java.lang.String r2 = "false"
            boolean r1 = r1.endsWith(r2)     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x008b
            java.lang.String r0 = "Message"
            org.w3c.dom.NodeList r0 = r5.getElementsByTagName(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.String r0 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.String r1 = "TJCurrency"
            com.tapjoy.TapjoyLog.i(r1, r0)     // Catch:{ all -> 0x00b3 }
            java.lang.String r1 = "BalanceTooLowError"
            java.lang.String r2 = "MessageCode"
            org.w3c.dom.NodeList r5 = r5.getElementsByTagName(r2)     // Catch:{ all -> 0x00b3 }
            java.lang.String r5 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r5)     // Catch:{ all -> 0x00b3 }
            boolean r5 = r1.equals(r5)     // Catch:{ all -> 0x00b3 }
            if (r5 == 0) goto L_0x00a8
            com.tapjoy.internal.fv.a()     // Catch:{ all -> 0x00b3 }
            goto L_0x00a8
        L_0x008b:
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00b3 }
            java.lang.String r3 = "spendCurrency response is invalid -- missing <Success> tag."
            r1.<init>(r2, r3)     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x00b3 }
            goto L_0x00a8
        L_0x009a:
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x00b3 }
            java.lang.String r3 = "spendCurrency response is NULL"
            r1.<init>(r2, r3)     // Catch:{ all -> 0x00b3 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x00b3 }
        L_0x00a8:
            com.tapjoy.TJSpendCurrencyListener r5 = e     // Catch:{ all -> 0x00b3 }
            if (r5 == 0) goto L_0x00b1
            com.tapjoy.TJSpendCurrencyListener r5 = e     // Catch:{ all -> 0x00b3 }
            r5.onSpendCurrencyResponseFailure(r0)     // Catch:{ all -> 0x00b3 }
        L_0x00b1:
            monitor-exit(r4)
            return
        L_0x00b3:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJCurrency.b(com.tapjoy.TapjoyHttpURLResponse):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c(com.tapjoy.TapjoyHttpURLResponse r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "Failed to award currency"
            java.lang.String r1 = r5.response     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x0085
            java.lang.String r5 = r5.response     // Catch:{ all -> 0x009e }
            org.w3c.dom.Document r5 = com.tapjoy.TapjoyUtil.buildDocument(r5)     // Catch:{ all -> 0x009e }
            if (r5 == 0) goto L_0x0093
            java.lang.String r1 = "Success"
            org.w3c.dom.NodeList r1 = r5.getElementsByTagName(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r1)     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x005c
            java.lang.String r2 = "true"
            boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x009e }
            if (r2 == 0) goto L_0x005c
            java.lang.String r1 = "TapPoints"
            org.w3c.dom.NodeList r1 = r5.getElementsByTagName(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r2 = "CurrencyName"
            org.w3c.dom.NodeList r5 = r5.getElementsByTagName(r2)     // Catch:{ all -> 0x009e }
            java.lang.String r5 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r5)     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x004d
            if (r5 == 0) goto L_0x004d
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x009e }
            r4.saveCurrencyBalance(r0)     // Catch:{ all -> 0x009e }
            com.tapjoy.TJAwardCurrencyListener r1 = f     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x004b
            com.tapjoy.TJAwardCurrencyListener r1 = f     // Catch:{ all -> 0x009e }
            r1.onAwardCurrencyResponse(r5, r0)     // Catch:{ all -> 0x009e }
        L_0x004b:
            monitor-exit(r4)
            return
        L_0x004d:
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x009e }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x009e }
            java.lang.String r3 = "awardCurrency response is invalid -- missing tags."
            r1.<init>(r2, r3)     // Catch:{ all -> 0x009e }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x009e }
            goto L_0x0093
        L_0x005c:
            if (r1 == 0) goto L_0x0076
            java.lang.String r2 = "false"
            boolean r1 = r1.endsWith(r2)     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x0076
            java.lang.String r0 = "Message"
            org.w3c.dom.NodeList r5 = r5.getElementsByTagName(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = com.tapjoy.TapjoyUtil.getNodeTrimValue(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyLog.i(r5, r0)     // Catch:{ all -> 0x009e }
            goto L_0x0093
        L_0x0076:
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x009e }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x009e }
            java.lang.String r3 = "awardCurrency response is invalid -- missing <Success> tag."
            r1.<init>(r2, r3)     // Catch:{ all -> 0x009e }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x009e }
            goto L_0x0093
        L_0x0085:
            java.lang.String r5 = "TJCurrency"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x009e }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SERVER_ERROR     // Catch:{ all -> 0x009e }
            java.lang.String r3 = "awardCurrency response is NULL"
            r1.<init>(r2, r3)     // Catch:{ all -> 0x009e }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x009e }
        L_0x0093:
            com.tapjoy.TJAwardCurrencyListener r5 = f     // Catch:{ all -> 0x009e }
            if (r5 == 0) goto L_0x009c
            com.tapjoy.TJAwardCurrencyListener r5 = f     // Catch:{ all -> 0x009e }
            r5.onAwardCurrencyResponseFailure(r0)     // Catch:{ all -> 0x009e }
        L_0x009c:
            monitor-exit(r4)
            return
        L_0x009e:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJCurrency.c(com.tapjoy.TapjoyHttpURLResponse):void");
    }
}
