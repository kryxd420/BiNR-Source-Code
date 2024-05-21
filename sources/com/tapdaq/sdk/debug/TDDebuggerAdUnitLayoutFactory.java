package com.tapdaq.sdk.debug;

import android.content.Context;
import android.view.View;

public class TDDebuggerAdUnitLayoutFactory {
    public TDDebuggerAdUnitLayout create(Context context, int i, String[] strArr, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (i == 0) {
            return new TDDebuggerBannerAdUnitLayout(context, i, strArr, onClickListener, onClickListener2);
        }
        return new TDDebuggerAdUnitLayout(context, i, strArr, onClickListener, onClickListener2);
    }
}
