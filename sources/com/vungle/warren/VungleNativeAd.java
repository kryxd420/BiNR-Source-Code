package com.vungle.warren;

import android.view.View;

public interface VungleNativeAd {
    void finishDisplayingAd();

    View renderNativeView();

    void setAdVisibility(boolean z);
}
