package com.tapdaq.adapters.tapdaq.queues;

import android.content.Context;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.analytics.TMFrequencyTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TMAdQueue {
    private List<TMAd> mAds = new ArrayList();
    private String mCreativeType;
    private String mTag;

    public TMAdQueue(String str, String str2) {
        this.mTag = str;
        this.mCreativeType = str2;
    }

    public TMAdQueue(String str, TMAd tMAd, String str2) {
        this.mTag = str;
        this.mCreativeType = str2;
        adAd(tMAd);
    }

    public void adAd(TMAd tMAd) {
        if (tMAd != null) {
            this.mAds.add(tMAd);
        }
    }

    public List<TMAd> getAllAds() {
        return this.mAds;
    }

    public TMAd getAd(Context context) {
        TMFrequencyTracker tMFrequencyTracker = new TMFrequencyTracker(context);
        if (this.mAds.size() > 0) {
            for (TMAd next : this.mAds) {
                if ((!next.isBlockingInstalledApp() || !next.isInstalled(context)) && tMFrequencyTracker.canDisplay(next)) {
                    return next;
                }
            }
        }
        return null;
    }

    public TMAd popAd(Context context) {
        TMFrequencyTracker tMFrequencyTracker = new TMFrequencyTracker(context);
        TMAd tMAd = null;
        if (this.mAds.size() > 0) {
            Iterator<TMAd> it = this.mAds.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TMAd next = it.next();
                if ((!next.isBlockingInstalledApp() || !next.isInstalled(context)) && tMFrequencyTracker.canDisplay(next)) {
                    tMAd = next;
                    break;
                }
            }
            if (tMAd != null) {
                this.mAds.remove(tMAd);
                this.mAds.add(tMAd);
            }
        }
        return tMAd;
    }
}
