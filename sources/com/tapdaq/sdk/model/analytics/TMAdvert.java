package com.tapdaq.sdk.model.analytics;

import android.content.Context;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.analytics.TMFrequencyTracker;
import com.tapdaq.sdk.helpers.Utils;
import java.util.Date;
import java.util.Locale;

public class TMAdvert {
    private String ad_format;
    private TMAdSize ad_size;
    private String advertiser_id;
    private String click_id = generateClickID();
    private String creative_id;
    private int frequency_count;
    private String placement_tag;
    private String promotion_id;

    public TMAdvert(Context context, TMAd tMAd, String str, String str2, TMAdSize tMAdSize) {
        this.advertiser_id = tMAd.getAdvertiserId();
        this.promotion_id = tMAd.getPromotionId();
        this.creative_id = tMAd.getCreativeId();
        this.frequency_count = new TMFrequencyTracker(context).getFrequencyCount(tMAd);
        this.ad_format = str;
        this.placement_tag = str2;
        this.ad_size = tMAdSize == null ? new TMAdSize(0.0f, 0.0f) : tMAdSize;
    }

    private String generateClickID() {
        return String.format(Locale.ENGLISH, "%d_%s", new Object[]{Long.valueOf(new Date().getTime()), Utils.generateUUID()});
    }
}
