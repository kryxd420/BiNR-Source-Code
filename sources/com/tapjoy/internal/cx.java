package com.tapjoy.internal;

import android.webkit.WebView;
import java.util.ArrayList;
import java.util.List;

public final class cx {
    public final da a;
    final WebView b;
    public final List c = new ArrayList();
    final String d;
    public final String e;
    public final cy f;

    private cx(da daVar, String str, List list, String str2) {
        cy cyVar;
        this.a = daVar;
        this.b = null;
        this.d = str;
        if (list != null) {
            this.c.addAll(list);
            cyVar = cy.NATIVE;
        } else {
            cyVar = cy.HTML;
        }
        this.f = cyVar;
        this.e = str2;
    }

    public static cx a(da daVar, String str, List list, String str2) {
        ds.a((Object) daVar, "Partner is null");
        ds.a((Object) str, "OMID JS script content is null");
        ds.a((Object) list, "VerificationScriptResources is null");
        if (str2.length() <= 256) {
            return new cx(daVar, str, list, str2);
        }
        throw new IllegalArgumentException("CustomReferenceData is greater than 256 characters");
    }
}
