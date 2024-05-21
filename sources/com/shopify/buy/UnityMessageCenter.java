package com.shopify.buy;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.shopify.buy.models.AndroidPayEventResponse;
import com.shopify.buy.models.MailingAddressInput;
import com.shopify.buy.models.NativePayment;
import com.shopify.buy.models.ShippingMethod;
import com.shopify.buy.models.UnityMessage;
import com.shopify.buy.models.WalletError;
import com.shopify.buy.models.serializer.AndroidPayEventResponseSerializer;
import com.shopify.buy.models.serializer.MailingAddressInputSerializer;
import com.shopify.buy.models.serializer.NativePaymentSerializer;
import com.shopify.buy.models.serializer.ShippingMethodSerializer;
import com.shopify.buy.models.serializer.ShopifyErrorSerializer;
import com.shopify.buy.models.serializer.UnityMessageSerializer;
import com.shopify.buy.utils.Logger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UnityMessageCenter implements MessageCenter {
    private static final Map<String, UnityCall> CALLS_IN_WAITING = new HashMap();
    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());
    private static final String NATIVE_WEB_DELEGATE_METHOD_CONTENT = "dismissed";
    private static final Map<Class<?>, TypeConverter<?>> TYPE_CONVERTERS = new HashMap();
    private UnityMessageDelegate unityMessageDelegate;

    public interface TypeConverter<T> {
        @NonNull
        T parse(String str) throws JSONException;
    }

    static {
        TYPE_CONVERTERS.put(AndroidPayEventResponse.class, new TypeConverter<AndroidPayEventResponse>() {
            @NonNull
            public AndroidPayEventResponse parse(String str) throws JSONException {
                return AndroidPayEventResponseSerializer.fromJsonString(str);
            }
        });
        TYPE_CONVERTERS.put(Void.class, new TypeConverter<Void>() {
            @NonNull
            public Void parse(String str) throws JSONException {
                return null;
            }
        });
    }

    private UnityMessageCenter() {
    }

    public UnityMessageCenter(@NonNull UnityMessageDelegate unityMessageDelegate2) {
        this.unityMessageDelegate = unityMessageDelegate2;
    }

    private void sendMessageTo(@NonNull Method method, @NonNull UnityMessage unityMessage) {
        sendMessageTo(method, unityMessage, (MessageCallback) null);
    }

    private <T> void sendMessageTo(@NonNull Method<T> method, @NonNull UnityMessage unityMessage, @Nullable MessageCallback<T> messageCallback) {
        if (messageCallback != null) {
            CALLS_IN_WAITING.put(unityMessage.identifier, new UnityCall(method, messageCallback));
        }
        this.unityMessageDelegate.unitySendMessage(method.name, UnityMessageSerializer.toJson(unityMessage).toString());
    }

    public static void onUnityResponse(String str, final String str2) {
        Logger.debug("New message from Unity: identifier = " + str + ", content = " + str2);
        final UnityCall remove = CALLS_IN_WAITING.remove(str);
        if (remove != null) {
            MAIN_THREAD_HANDLER.post(new Runnable() {
                public void run() {
                    UnityMessageCenter.deliverResult(remove, str2);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static <T> void deliverResult(@NonNull UnityCall<T> unityCall, @NonNull String str) {
        if (unityCall.messageCallback != null) {
            try {
                Logger.debug(str);
                unityCall.messageCallback.onError(ShopifyErrorSerializer.fromJson(new JSONObject(str)));
            } catch (JSONException unused) {
                Class access$400 = unityCall.method.responseType;
                TypeConverter typeConverter = TYPE_CONVERTERS.get(access$400);
                if (typeConverter == null) {
                    Logger.error("Missing converter for type " + access$400.getCanonicalName());
                    return;
                }
                try {
                    unityCall.messageCallback.onResponse(typeConverter.parse(str));
                } catch (JSONException e) {
                    Logger.error("Error trying to parse json: " + str);
                    e.printStackTrace();
                }
            }
        }
    }

    public void onNativeMessage() {
        sendMessageTo(Method.ON_NATIVE_MESSAGE, UnityMessage.fromContent(NATIVE_WEB_DELEGATE_METHOD_CONTENT));
    }

    public void onUpdateShippingAddress(@NonNull MailingAddressInput mailingAddressInput, @NonNull MessageCallback<AndroidPayEventResponse> messageCallback) {
        sendMessageTo(Method.ON_UPDATE_SHIPPING_ADDRESS, UnityMessage.fromContent(MailingAddressInputSerializer.toJson(mailingAddressInput).toString()), messageCallback);
    }

    public void onUpdateShippingLine(@NonNull ShippingMethod shippingMethod, @NonNull MessageCallback<AndroidPayEventResponse> messageCallback) {
        sendMessageTo(Method.ON_UPDATE_SHIPPING_LINE, UnityMessage.fromContent(ShippingMethodSerializer.toJson(shippingMethod).toString()), messageCallback);
    }

    public void onConfirmCheckout(@NonNull NativePayment nativePayment) {
        sendMessageTo(Method.ON_CONFIRM_CHECKOUT, UnityMessage.fromContent(NativePaymentSerializer.toJson(nativePayment).toString()));
    }

    public void onError(@NonNull WalletError walletError) {
        sendMessageTo(Method.ON_ERROR, UnityMessage.fromContent(walletError.toString()));
    }

    public void onCancel() {
        sendMessageTo(Method.ON_CANCEL, UnityMessage.fromContent(""));
    }

    public void onCanCheckoutWithAndroidPayResult(boolean z) {
        sendMessageTo(Method.ON_CAN_CHECKOUT_WITH_AP_RESULT, UnityMessage.fromContent(String.valueOf(z)));
    }

    private static final class Method<T> {
        static final Method<Void> ON_CANCEL = new Method<>("OnCancel", Void.class);
        static final Method<Void> ON_CAN_CHECKOUT_WITH_AP_RESULT = new Method<>("OnCanCheckoutWithAndroidPayResult", Void.class);
        static final Method<Void> ON_CONFIRM_CHECKOUT = new Method<>("OnConfirmCheckout", Void.class);
        static final Method<Void> ON_ERROR = new Method<>("OnError", Void.class);
        static final Method<Void> ON_NATIVE_MESSAGE = new Method<>("OnNativeMessage", Void.class);
        static final Method<AndroidPayEventResponse> ON_UPDATE_SHIPPING_ADDRESS = new Method<>("OnUpdateShippingAddress", AndroidPayEventResponse.class);
        static final Method<AndroidPayEventResponse> ON_UPDATE_SHIPPING_LINE = new Method<>("OnUpdateShippingLine", AndroidPayEventResponse.class);
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public final Class<T> responseType;

        Method(String str, Class<T> cls) {
            this.name = str;
            this.responseType = cls;
        }
    }

    private static class UnityCall<T> {
        /* access modifiers changed from: private */
        @Nullable
        public final MessageCallback<T> messageCallback;
        /* access modifiers changed from: private */
        @NonNull
        public final Method<T> method;

        UnityCall(@NonNull Method<T> method2, @Nullable MessageCallback<T> messageCallback2) {
            this.method = method2;
            this.messageCallback = messageCallback2;
        }
    }
}
