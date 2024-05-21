package com.vungle.warren;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.presenter.LocalAdPresenter;
import com.vungle.warren.presenter.WebAdPresenter;
import java.io.File;

public class AdvertisementPresenterFactory {
    private static final String EXTRA_ADVERTISEMENT = "ADV_FACTORY_ADVERTISEMENT";
    private static final String TAG = "AdvertisementPresenterFactory";
    private Advertisement advertisement;
    private final Storage storage = Vungle._instance.storage;

    public AdvertisementPresenter getAdPresenter(String str, Bundle bundle, String str2) throws IllegalArgumentException, IllegalStateException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Missing placementID! Cannot play advertisement.");
        } else if (this.storage == null || !Vungle.isInitialized()) {
            throw new IllegalStateException("Vungle SDK is not initialized");
        } else {
            Placement placement = (Placement) this.storage.load(str, Placement.class);
            if (placement != null) {
                if (bundle == null) {
                    this.advertisement = this.storage.findValidAdvertisementForPlacement(str);
                } else {
                    String string = bundle.getString(EXTRA_ADVERTISEMENT);
                    if (!TextUtils.isEmpty(string)) {
                        this.advertisement = (Advertisement) this.storage.load(string, Advertisement.class);
                    }
                }
                if (!Vungle.canPlayAd(this.advertisement)) {
                    Log.e(TAG, "Advertisement is null or assets are missing");
                    return null;
                }
                File advertisementAssetDirectory = this.storage.getAdvertisementAssetDirectory(this.advertisement.getId());
                if (advertisementAssetDirectory.isDirectory()) {
                    switch (this.advertisement.getAdType()) {
                        case 0:
                            return new LocalAdPresenter(this.advertisement, placement, this.storage, advertisementAssetDirectory, str2);
                        case 1:
                            return new WebAdPresenter(this.advertisement, placement, this.storage, advertisementAssetDirectory, str2);
                        default:
                            throw new IllegalArgumentException("No presenter available for ad type!");
                    }
                } else {
                    throw new IllegalStateException("No asset directory for " + str + " exists!");
                }
            } else {
                throw new IllegalArgumentException("No placement for ID " + str + " found. Please use a valid placement ID");
            }
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString(EXTRA_ADVERTISEMENT, this.advertisement == null ? null : this.advertisement.getId());
    }
}
