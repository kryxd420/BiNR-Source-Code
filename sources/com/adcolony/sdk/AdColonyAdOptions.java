package com.adcolony.sdk;

import android.support.annotation.NonNull;
import org.json.JSONObject;

public class AdColonyAdOptions {
    boolean a;
    boolean b;
    AdColonyUserMetadata c;
    JSONObject d = y.a();

    public AdColonyAdOptions enableConfirmationDialog(boolean z) {
        this.a = z;
        y.a(this.d, "confirmation_enabled", true);
        return this;
    }

    public AdColonyAdOptions enableResultsDialog(boolean z) {
        this.b = z;
        y.a(this.d, "results_enabled", true);
        return this;
    }

    public AdColonyAdOptions setOption(@NonNull String str, boolean z) {
        if (aw.d(str)) {
            y.a(this.d, str, z);
        }
        return this;
    }

    public Object getOption(@NonNull String str) {
        return y.a(this.d, str);
    }

    public AdColonyAdOptions setOption(@NonNull String str, double d2) {
        if (aw.d(str)) {
            y.a(this.d, str, d2);
        }
        return this;
    }

    public AdColonyAdOptions setOption(@NonNull String str, @NonNull String str2) {
        if (str != null && aw.d(str) && aw.d(str2)) {
            y.a(this.d, str, str2);
        }
        return this;
    }

    public AdColonyAdOptions setUserMetadata(@NonNull AdColonyUserMetadata adColonyUserMetadata) {
        this.c = adColonyUserMetadata;
        y.a(this.d, "user_metadata", adColonyUserMetadata.c);
        return this;
    }

    public AdColonyUserMetadata getUserMetadata() {
        return this.c;
    }
}
