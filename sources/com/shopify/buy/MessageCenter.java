package com.shopify.buy;

import android.support.annotation.NonNull;
import com.shopify.buy.models.AndroidPayEventResponse;
import com.shopify.buy.models.MailingAddressInput;
import com.shopify.buy.models.NativePayment;
import com.shopify.buy.models.ShippingMethod;
import com.shopify.buy.models.WalletError;

public interface MessageCenter {
    void onCanCheckoutWithAndroidPayResult(boolean z);

    void onCancel();

    void onConfirmCheckout(@NonNull NativePayment nativePayment);

    void onError(@NonNull WalletError walletError);

    void onNativeMessage();

    void onUpdateShippingAddress(@NonNull MailingAddressInput mailingAddressInput, @NonNull MessageCallback<AndroidPayEventResponse> messageCallback);

    void onUpdateShippingLine(@NonNull ShippingMethod shippingMethod, @NonNull MessageCallback<AndroidPayEventResponse> messageCallback);
}
