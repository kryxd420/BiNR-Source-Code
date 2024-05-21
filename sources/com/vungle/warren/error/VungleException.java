package com.vungle.warren.error;

public class VungleException extends Exception {
    public static final int AD_EXPIRED = 4;
    public static final int AD_FAILED_TO_DOWNLOAD = 11;
    public static final int AD_UNABLE_TO_PLAY = 10;
    public static final int APPLICATION_CONTEXT_REQUIRED = 7;
    public static final int CONFIGURATION_ERROR = 3;
    public static final int DIRECT_DOWNLOAD_VALIDATION_ERROR = 5;
    public static final int MISSING_REQUIRED_ARGUMENTS_FOR_INIT = 6;
    public static final int NO_AUTO_CACHED_PLACEMENT = 12;
    public static final int NO_SERVE = 1;
    public static final int OPERATION_ONGOING = 8;
    public static final int PLACEMENT_NOT_FOUND = 13;
    public static final int SERVER_RETRY_ERROR = 14;
    public static final int UNKNOWN_ERROR = 2;
    public static final int VUNGLE_NOT_INTIALIZED = 9;
    public static final int ZERO_PLACEMENTS = 0;
    @ExceptionCode
    private final int exceptionCode;

    public @interface ExceptionCode {
    }

    public VungleException(@ExceptionCode int i) {
        this.exceptionCode = i;
    }

    @ExceptionCode
    public int getExceptionCode() {
        return this.exceptionCode;
    }

    public String getLocalizedMessage() {
        switch (this.exceptionCode) {
            case 0:
                return "Invalid placement configurations. Please provide at least one valid placement identifier";
            case 1:
                return "No advertisements are available for your current bid. Please try again later.";
            case 2:
                return "Unknown Error Occurred.";
            case 3:
                return "Configuration Error Occurred. Please check your appID and placementIDs, and try again when network connectivity is available.";
            case 4:
                return "The advertisement in the cache has expired and can no longer be played. Please load another ad";
            case 5:
                return "The App for Advertisement does not exist in the App store for DirectDownload. Please load another ad";
            case 6:
                return "Please ensure all parameter for init marked as NonNull are provided, as they are essential for functioning of our SDK";
            case 7:
                return "Please provide Application context so our SDK can continue to support our API beyond Activity lifecycle";
            case 8:
                return "There is already an ongoing operation for the action you requested. Please wait until the operation finished before starting another.";
            case 9:
                return "Vungle is not initialized/no longer initialized. Please call Vungle.init() to reinitialize";
            case 10:
                return "unable to play advertisement";
            case 11:
                return "Advertisement failed to download";
            case 12:
                return "No auto-cached Placement on config";
            case 13:
                return "Placement is not valid";
            case 14:
                return "Remote Server responded with http Retry-After, SDK will retry this request.";
            default:
                throw new IllegalStateException("Unknown Exception Code");
        }
    }
}
