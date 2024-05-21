package com.unity.purchasing.googleplay;

import com.android.vending.billing.IInAppBillingService;

public interface BillingServiceProcessor {
    void workWith(IInAppBillingService iInAppBillingService);
}
