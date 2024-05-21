package com.jds.binaar.core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.unity3d.player.UnityPlayerActivity;

public class BINRMainActivity extends UnityPlayerActivity {
    public static Context m_context;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m_context = this;
    }

    public void showAlert(String str, String str2, String str3, String str4, final String str5) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setPositiveButton(str3, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BINRMainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str5)));
                BINRMainActivity.this.finish();
            }
        });
        builder.setNegativeButton(str4, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BINRMainActivity.this.finish();
            }
        });
        builder.create().show();
    }
}
