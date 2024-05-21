package com.applovin.impl.a;

import com.google.android.gms.wallet.WalletConstants;
import com.tapdaq.sdk.TapdaqError;

public enum d {
    UNSPECIFIED(-1),
    XML_PARSING(100),
    GENERAL_WRAPPER_ERROR(TapdaqError.NO_AD_AVAILABLE),
    TIMED_OUT(TapdaqError.FREQUENCY_CAP_MET),
    WRAPPER_LIMIT_REACHED(302),
    NO_WRAPPER_RESPONSE(303),
    GENERAL_LINEAR_ERROR(400),
    NO_MEDIA_FILE_PROVIDED(401),
    MEDIA_FILE_TIMEOUT(WalletConstants.ERROR_CODE_SERVICE_UNAVAILABLE),
    MEDIA_FILE_ERROR(WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR),
    GENERAL_COMPANION_AD_ERROR(FetchConst.PRIORITY_NORMAL),
    UNABLE_TO_FETCH_COMPANION_AD_RESOURCE(603),
    CAN_NOT_FIND_COMPANION_AD_RESOURCE(604);
    
    private final int n;

    private d(int i) {
        this.n = i;
    }

    public int a() {
        return this.n;
    }
}
