package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.AvidContext;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class InternalAvidAdSessionContext {
    public static final String AVID_API_LEVEL = "2";
    public static final String AVID_STUB_MODE = "stub";
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_AD_SESSION_TYPE = "avidAdSessionType";
    public static final String CONTEXT_AVID_API_LEVEL = "avidApiLevel";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_IS_DEFERRED = "isDeferred";
    public static final String CONTEXT_MEDIA_TYPE = "mediaType";
    public static final String CONTEXT_MODE = "mode";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private String a;
    private ExternalAvidAdSessionContext b;
    private String c;
    private String d;

    public InternalAvidAdSessionContext(Context context, String str, String str2, String str3, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidContext.getInstance().init(context);
        this.a = str;
        this.b = externalAvidAdSessionContext;
        this.c = str2;
        this.d = str3;
    }

    public String getAvidAdSessionId() {
        return this.a;
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.b;
    }

    public void setAvidAdSessionContext(ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        this.b = externalAvidAdSessionContext;
    }

    public JSONObject getFullContext() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("avidAdSessionId", this.a);
            jSONObject.put("bundleIdentifier", AvidContext.getInstance().getBundleId());
            jSONObject.put("partner", AvidContext.getInstance().getPartnerName());
            jSONObject.put("partnerVersion", this.b.getPartnerVersion());
            jSONObject.put("avidLibraryVersion", AvidContext.getInstance().getAvidVersion());
            jSONObject.put(CONTEXT_AVID_AD_SESSION_TYPE, this.c);
            jSONObject.put(CONTEXT_MEDIA_TYPE, this.d);
            jSONObject.put(CONTEXT_IS_DEFERRED, this.b.isDeferred());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject getStubContext() {
        JSONObject fullContext = getFullContext();
        try {
            fullContext.put(CONTEXT_AVID_API_LEVEL, "2");
            fullContext.put(CONTEXT_MODE, AVID_STUB_MODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullContext;
    }
}
