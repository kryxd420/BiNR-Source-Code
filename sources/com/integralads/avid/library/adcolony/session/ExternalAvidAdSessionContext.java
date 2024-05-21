package com.integralads.avid.library.adcolony.session;

public class ExternalAvidAdSessionContext {
    private String a;
    private boolean b;

    public ExternalAvidAdSessionContext(String str) {
        this(str, false);
    }

    public ExternalAvidAdSessionContext(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public String getPartnerVersion() {
        return this.a;
    }

    public boolean isDeferred() {
        return this.b;
    }
}
