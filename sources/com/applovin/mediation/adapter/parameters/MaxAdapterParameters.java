package com.applovin.mediation.adapter.parameters;

import android.os.Bundle;

public interface MaxAdapterParameters {
    Bundle getServerParameters();

    boolean hasUserConsent();

    boolean isAgeRestrictedUser();

    boolean isTesting();
}
