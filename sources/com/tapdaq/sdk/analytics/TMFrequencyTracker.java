package com.tapdaq.sdk.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.storage.StorageBase;
import com.tapdaq.sdk.storage.StorageProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TMFrequencyTracker {
    private static final String DELIMITER = ", ";
    private static final long MILLISECONDS_PER_DAY = 86400000;
    private final StorageBase mStorage;

    public TMFrequencyTracker(Context context) {
        this.mStorage = StorageProvider.getInstance(context);
    }

    public void addDisplay(TMAd tMAd) {
        String format = String.format(Locale.ENGLISH, "Displayed-%s", new Object[]{tMAd.getCreativeId()});
        String l = Long.toString(new Date().getTime());
        List<String> frequencyList = getFrequencyList(format);
        frequencyList.add(l);
        this.mStorage.putString(format, TextUtils.join(DELIMITER, frequencyList));
    }

    public boolean canDisplay(TMAd tMAd) {
        if (tMAd == null) {
            return false;
        }
        if (tMAd.getFrequencyCap() > 0) {
            String format = String.format(Locale.ENGLISH, "Displayed-%s", new Object[]{tMAd.getCreativeId()});
            long time = new Date().getTime() - (((long) tMAd.getFrequencyCapDurationInDays()) * MILLISECONDS_PER_DAY);
            List<String> frequencyList = getFrequencyList(format);
            if (this.mStorage.contains(format)) {
                ArrayList arrayList = new ArrayList();
                for (String next : frequencyList) {
                    try {
                        if (Long.parseLong(next) > time) {
                            arrayList.add(next);
                        }
                    } catch (Exception e) {
                        TLog.debug("Failed to Parse String to Long: " + next);
                        TLog.error(e);
                    }
                }
                String str = "";
                if (!arrayList.isEmpty()) {
                    if (arrayList.size() > 1) {
                        str = TextUtils.join(DELIMITER, arrayList);
                    } else {
                        str = (String) arrayList.get(0);
                    }
                }
                this.mStorage.putString(format, str);
                if (arrayList.size() >= tMAd.getFrequencyCap()) {
                    TLog.debug(String.format(Locale.ENGLISH, "Ad Frequency Cap met. Creative: %s. PromotionId: %s", new Object[]{tMAd.getCreativeId(), tMAd.getPromotionId()}));
                    return false;
                }
            }
        }
        return true;
    }

    public int getFrequencyCount(TMAd tMAd) {
        return getFrequencyList(String.format(Locale.ENGLISH, "Displayed-%s", new Object[]{tMAd.getCreativeId()})).size();
    }

    private List<String> getFrequencyList(String str) {
        if (!this.mStorage.contains(str)) {
            return new ArrayList();
        }
        String string = this.mStorage.getString(str);
        if (string.contains(DELIMITER)) {
            return new ArrayList(Arrays.asList(string.split(DELIMITER)));
        }
        return new ArrayList(Arrays.asList(new String[]{string}));
    }
}
