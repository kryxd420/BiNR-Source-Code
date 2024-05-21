package com.tapdaq.adapters.tapdaq.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.google.android.gms.common.util.Strings;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.TMReachability;
import com.tapdaq.sdk.model.TMModel;
import java.util.List;
import java.util.Locale;

public class TMAd extends TMModel {
    protected String advertiser_id;
    protected String creative_id;
    protected String custom_url;
    protected int frequency_cap;
    protected int frequency_cap_duration_in_days;
    protected String image_url;
    protected boolean is_blocking_installed_app;
    protected boolean is_video_looped;
    protected TMMetadata metadata;
    protected List<String> placement_tags;
    protected String promotion_id;
    protected String store_id;
    protected String store_url;
    protected String video_url;

    public String getAdvertiserId() {
        return this.advertiser_id;
    }

    public String getPromotionId() {
        return this.promotion_id;
    }

    public String getCreativeId() {
        return this.creative_id;
    }

    public String getCustomUrl() {
        return this.custom_url;
    }

    public boolean isBlockingInstalledApp() {
        return this.is_blocking_installed_app;
    }

    public String getStoreUrl() {
        return this.store_url;
    }

    public String getStoreId() {
        return this.store_id;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public String getVideoUrl() {
        return this.video_url;
    }

    public boolean isVideoLooped() {
        return this.is_video_looped;
    }

    public boolean hasVideoAvailable() {
        return this.video_url != null && !this.video_url.isEmpty();
    }

    public int getFrequencyCap() {
        return this.frequency_cap;
    }

    public int getFrequencyCapDurationInDays() {
        return this.frequency_cap_duration_in_days;
    }

    public TMMetadata getMetadata() {
        return this.metadata;
    }

    public List<String> getPlacementTags() {
        return this.placement_tags;
    }

    public void performClick(Context context) {
        if (getCustomUrl() != null && !getCustomUrl().isEmpty()) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(getCustomUrl()));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                TLog.debug("Custom Url: " + getCustomUrl());
                context.startActivity(intent);
                return;
            }
        }
        if (getStoreId() != null) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(getStoreId());
            if (launchIntentForPackage != null && launchIntentForPackage.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(launchIntentForPackage);
            } else if (TMReachability.IsNetworkAvailable(context)) {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "%s%s", new Object[]{"market://details?id=", getStoreId()})));
                if (intent2.resolveActivity(context.getPackageManager()) != null) {
                    TLog.debug("Store ID: " + getStoreId());
                    context.startActivity(intent2);
                }
            } else {
                TLog.error("Unable to config Play Store, No internet available");
            }
        } else {
            TLog.error("Unable to config Play Store, No App ID available");
        }
    }

    public boolean isInstalled(Context context) {
        if (!Strings.isEmptyOrWhitespace(getStoreId())) {
            try {
                return context.getPackageManager().getApplicationInfo(getStoreId(), 0).enabled;
            } catch (PackageManager.NameNotFoundException unused) {
                TLog.debug("Package not found: " + getStoreId());
            }
        }
        return false;
    }
}
