package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

final class zzad extends zzbk {
    private final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaChangeListener zzbo;

    zzad(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        this.zzbo = onStreetViewPanoramaChangeListener;
    }

    public final void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
        this.zzbo.onStreetViewPanoramaChange(streetViewPanoramaLocation);
    }
}
