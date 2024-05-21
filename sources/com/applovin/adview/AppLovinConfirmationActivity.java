package com.applovin.adview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class AppLovinConfirmationActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intent = getIntent();
        builder.setTitle(intent.getStringExtra("dialog_title"));
        builder.setMessage(intent.getStringExtra("dialog_body"));
        builder.setCancelable(false);
        builder.setPositiveButton(intent.getStringExtra("dialog_button_text"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                AppLovinConfirmationActivity.this.finish();
            }
        });
        builder.show();
    }
}
