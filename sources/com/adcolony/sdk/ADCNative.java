package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import com.tapjoy.TapjoyConstants;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

class ADCNative {
    static ADCNative lock = new ADCNative();

    /* access modifiers changed from: private */
    public static native void EventTracker__logEvent(long j, byte[] bArr);

    static native void nativeCreateSceneController(int i, int i2);

    static native void nativeCreateTexture(int i, int i2, int i3, String str, ByteBuffer byteBuffer, int i4, int i5, int i6, int i7);

    static native void nativeDeleteSceneController(int i, int i2);

    static native void nativeHelloWorld();

    static native boolean nativeIsArm();

    static native boolean nativeNeonSupported();

    static native void nativeRender(int i, int i2, int i3, int i4);

    ADCNative() {
    }

    static void Logger__logNative(String[] strArr) {
        a.b(strArr);
    }

    static void EventTracker__logEvent(final JSONObject jSONObject) {
        final ADCVMModule aDCVMModule = (ADCVMModule) a.a().q().e().get(1);
        if (aDCVMModule == null || l.C().equals("")) {
            AdColonyEventTracker.a(jSONObject);
            return;
        }
        ExecutorService e = aDCVMModule.e();
        if (e != null) {
            e.submit(new Runnable() {
                public void run() {
                    long d = aDCVMModule.d();
                    ADCNative.a(jSONObject);
                    ADCNative.EventTracker__logEvent(d, ("ADC3__EventTracker__logEvent(" + jSONObject.toString() + ")").getBytes(Charset.forName("UTF-8")));
                }
            });
        } else {
            new aa.a().a("ExecutorService is null.").a(aa.g);
        }
    }

    /* access modifiers changed from: private */
    public static void a(JSONObject jSONObject) {
        JSONObject f = y.f(jSONObject, "payload");
        if (ADCVMModule.a) {
            y.a(f, TapjoyConstants.TJC_API_KEY, "bb2cf0647ba654d7228dd3f9405bbc6a");
        } else {
            y.a(f, TapjoyConstants.TJC_API_KEY, l.C());
        }
        try {
            jSONObject.remove("payload");
            jSONObject.put("payload", f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static class a {
        static int a = 4;
        static int b = 0;
        static int c = 1;
        static int d = 2;
        static int e = 3;

        private a() {
        }

        /* access modifiers changed from: private */
        public static void b(String[] strArr) {
            if (strArr != null && strArr.length == a) {
                ac.a(Integer.parseInt(strArr[b]), Integer.parseInt(strArr[c]), strArr[d], Boolean.parseBoolean(strArr[e]));
            }
        }
    }
}
