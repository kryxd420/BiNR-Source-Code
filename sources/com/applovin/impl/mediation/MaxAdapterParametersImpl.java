package com.applovin.impl.mediation;

import android.content.Context;
import android.os.Bundle;
import com.applovin.impl.mediation.a.e;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterSignalCollectionParameters;

public class MaxAdapterParametersImpl implements MaxAdapterInitializationParameters, MaxAdapterResponseParameters, MaxAdapterSignalCollectionParameters {
    private final String a;
    private final Bundle b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final String f;

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public Bundle b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        public String f;

        public a a(com.applovin.impl.mediation.a.a aVar, Context context) {
            if (aVar != null) {
                this.a = aVar.d();
                this.f = aVar.c();
            }
            return a((e) aVar, context);
        }

        public a a(e eVar, Context context) {
            if (eVar != null) {
                this.e = eVar.v();
                this.c = eVar.b(context);
                this.d = eVar.a(context);
                this.b = eVar.x();
            }
            return this;
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public MaxAdapterParametersImpl a() {
            return new MaxAdapterParametersImpl(this);
        }

        public a b(boolean z) {
            this.d = z;
            return this;
        }
    }

    private MaxAdapterParametersImpl(a aVar) {
        if (aVar != null) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
            this.f = aVar.f;
            return;
        }
        throw new IllegalArgumentException("No builder specified");
    }

    public String getBidResponse() {
        return this.f;
    }

    public Bundle getServerParameters() {
        return this.b;
    }

    public String getThirdPartyAdPlacementId() {
        return this.a;
    }

    public boolean hasUserConsent() {
        return this.d;
    }

    public boolean isAgeRestrictedUser() {
        return this.c;
    }

    public boolean isTesting() {
        return this.e;
    }

    public String toString() {
        return "MaxAdapterParameters{thirdPartyAdPlacementId='" + this.a + '\'' + ", serverParameters=" + this.b + ", isAgeRestrictedUser=" + this.c + ", hasUserConsent=" + this.d + ", isTesting=" + this.e + ", bidResponse='" + this.f + '\'' + '}';
    }
}
