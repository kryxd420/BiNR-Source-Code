package com.tapdaq.sdk.model.waterfall;

import android.support.v4.os.EnvironmentCompat;
import com.tapdaq.sdk.Tapdaq;

public class TDCompliance {
    private String coppa = EnvironmentCompat.MEDIA_UNKNOWN;
    private String gdpr = Tapdaq.getInstance().config().getConsenStatus().toString();
}
