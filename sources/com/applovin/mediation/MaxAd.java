package com.applovin.mediation;

public interface MaxAd {
    String getAdUnitId();

    MaxAdFormat getFormat();

    boolean isReady();
}
