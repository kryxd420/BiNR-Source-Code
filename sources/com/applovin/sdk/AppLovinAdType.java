package com.applovin.sdk;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AppLovinAdType {
    public static final AppLovinAdType INCENTIVIZED = new AppLovinAdType("VIDEOA");
    public static final AppLovinAdType NATIVE = new AppLovinAdType("NATIVE");
    public static final AppLovinAdType REGULAR = new AppLovinAdType("REGULAR");
    private final String a;

    public AppLovinAdType(String str) {
        this.a = str;
    }

    public static Set<AppLovinAdType> allTypes() {
        HashSet hashSet = new HashSet(2);
        hashSet.add(REGULAR);
        hashSet.add(INCENTIVIZED);
        return hashSet;
    }

    public static AppLovinAdType fromString(String str) {
        return str.toUpperCase(Locale.ENGLISH).equals(INCENTIVIZED.getLabel()) ? INCENTIVIZED : REGULAR;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdType appLovinAdType = (AppLovinAdType) obj;
        if (this.a != null) {
            return this.a.equals(appLovinAdType.a);
        }
        if (appLovinAdType.a == null) {
            return true;
        }
    }

    public String getLabel() {
        return this.a.toUpperCase(Locale.ENGLISH);
    }

    public int hashCode() {
        if (this.a != null) {
            return this.a.hashCode();
        }
        return 0;
    }

    public String toString() {
        return getLabel();
    }
}
