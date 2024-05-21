package com.unity.purchasing.common;

public enum PurchaseFailureReason {
    BillingUnavailable,
    ExistingPurchasePending,
    ItemUnavailable,
    SignatureInvalid,
    UserCancelled,
    PaymentDeclined,
    DuplicateTransaction,
    Unknown
}
