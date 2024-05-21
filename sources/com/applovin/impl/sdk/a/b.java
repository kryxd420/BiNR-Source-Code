package com.applovin.impl.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.applovin.adview.AppLovinConfirmationActivity;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinSdkUtils;

public class b {
    /* access modifiers changed from: private */
    public final j a;
    private final String b;
    /* access modifiers changed from: private */
    public final Context c;

    public b(j jVar, Context context, String str) {
        this.a = jVar;
        this.b = str;
        this.c = context;
    }

    /* access modifiers changed from: private */
    public void a(String str, Throwable th) {
        this.a.v().c("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", th);
        Toast.makeText(this.c, str, 1).show();
    }

    /* access modifiers changed from: private */
    public String b() {
        j jVar;
        com.applovin.impl.sdk.b.b bVar;
        if (this.b.equals("accepted")) {
            jVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bJ;
        } else if (this.b.equals("quota_exceeded")) {
            jVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bK;
        } else if (this.b.equals("rejected")) {
            jVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bL;
        } else {
            jVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bM;
        }
        return (String) jVar.a(bVar);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                String str = (String) b.this.a.a(com.applovin.impl.sdk.b.b.bI);
                String b = b.this.b();
                String str2 = (String) b.this.a.a(com.applovin.impl.sdk.b.b.bN);
                if (e.a((Class<?>) AppLovinConfirmationActivity.class, b.this.c)) {
                    try {
                        Intent intent = new Intent(b.this.c, AppLovinConfirmationActivity.class);
                        intent.putExtra("dialog_title", str);
                        intent.putExtra("dialog_body", b);
                        intent.putExtra("dialog_button_text", str2);
                        b.this.c.startActivity(intent);
                    } catch (Throwable th) {
                        b.this.a(b, th);
                    }
                } else {
                    b.this.a(b, (Throwable) null);
                }
            }
        });
    }
}
