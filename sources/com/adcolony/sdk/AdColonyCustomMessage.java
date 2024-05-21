package com.adcolony.sdk;

import android.support.annotation.NonNull;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

public class AdColonyCustomMessage {
    String a;
    String b;

    public AdColonyCustomMessage(@NonNull String str, @NonNull String str2) {
        if (aw.d(str) || aw.d(str2)) {
            this.a = str;
            this.b = str2;
        }
    }

    public String getMessage() {
        return this.b;
    }

    public String getType() {
        return this.a;
    }

    public AdColonyCustomMessage set(String str, String str2) {
        this.a = str;
        this.b = str2;
        return this;
    }

    public void send() {
        try {
            AdColony.a.execute(new Runnable() {
                public void run() {
                    AdColony.a();
                    JSONObject a2 = y.a();
                    y.a(a2, "type", AdColonyCustomMessage.this.a);
                    y.a(a2, "message", AdColonyCustomMessage.this.b);
                    new af("CustomMessage.native_send", 1, a2).b();
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }
}
