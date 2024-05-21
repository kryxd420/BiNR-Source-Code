package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;

public class PurchaseActivity extends Activity {
    protected static final String TAG = "UnityIAP";
    private boolean receivedResult;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "Creating purchase activity");
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("vr", false)) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= 11) {
                decorView.setSystemUiVisibility(6);
            }
        }
        String string = getIntent().getExtras().getString(InAppPurchaseMetaData.KEY_PRODUCT_ID);
        String string2 = getIntent().getExtras().getString("developerPayload");
        String string3 = getIntent().getExtras().getString("type");
        String string4 = getIntent().getExtras().getString("oldSkuMetadata");
        String string5 = getIntent().getExtras().getString("newSku");
        if (string3 == null || !string3.equals("subscription_update")) {
            if (!GooglePlayPurchasing.ContinuePurchase(this, string, string2)) {
                setResult(0);
                finish();
            }
        } else if (!GooglePlayPurchasing.ContinueSubscriptionUpdate(this, string4, string5)) {
            setResult(0);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        processActivityResult(i, i2, intent);
        this.receivedResult = true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (!this.receivedResult) {
            processActivityResult(GooglePlayPurchasing.ACTIVITY_REQUEST_CODE, 0, (Intent) null);
        }
    }

    public void processActivityResult(int i, int i2, Intent intent) {
        GooglePlayPurchasing.ProcessActivityResult(i, i2, intent);
        finish();
    }
}
