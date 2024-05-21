package com.tapdaq.sdk.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataAdapter;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataBase;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItemAdapter;

public class TDGson {
    public static Gson Create() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TMStatsDataBase.class, new TMStatsDataAdapter());
        gsonBuilder.registerTypeHierarchyAdapter(TDWaterfallItem.class, new TDWaterfallItemAdapter());
        return gsonBuilder.create();
    }
}
