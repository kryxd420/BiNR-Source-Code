package com.vungle.warren.ui;

import android.content.DialogInterface;
import android.net.Uri;

public interface AdView {
    void close();

    String getWebsiteUrl();

    void open(String str);

    void playVideo(Uri uri, boolean z);

    void setOrientation(int i);

    void setVisibility(boolean z);

    void showCTAOverlay(boolean z, boolean z2, int i);

    void showCloseButton();

    void showDialog(String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener);

    void showWebsite(String str);

    void updateWindow(boolean z);
}
