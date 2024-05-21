package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbo;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzag extends zzbo {
    private final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaLongClickListener zzbr;

    zzag(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        this.zzbr = onStreetViewPanoramaLongClickListener;
    }

    public final void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.zzbr.onStreetViewPanoramaLongClick(streetViewPanoramaOrientation);
    }
}
