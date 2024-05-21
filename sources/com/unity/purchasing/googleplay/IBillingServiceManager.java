package com.unity.purchasing.googleplay;

public interface IBillingServiceManager {
    void dispose();

    void initialise() throws GooglePlayBillingUnAvailableException;

    void workWith(BillingServiceProcessor billingServiceProcessor);
}
