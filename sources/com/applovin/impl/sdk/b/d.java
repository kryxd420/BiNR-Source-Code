package com.applovin.impl.sdk.b;

import java.util.HashSet;

public class d<T> {
    public static final d<String> a = new d<>("com.applovin.sdk.impl.isFirstRun", String.class);
    public static final d<Integer> b = new d<>("com.applovin.sdk.last_version_code", Integer.class);
    public static final d<String> c = new d<>("com.applovin.sdk.user_id", String.class);
    public static final d<String> d = new d<>("com.applovin.sdk.device_test_group", String.class);
    public static final d<String> e = new d<>("com.applovin.sdk.variables", String.class);
    public static final d<Boolean> f = new d<>("com.applovin.sdk.compliance.has_user_consent", Boolean.class);
    public static final d<Boolean> g = new d<>("com.applovin.sdk.compliance.is_age_restricted_user", Boolean.class);
    public static final d<HashSet> h = new d<>("com.applovin.sdk.impl.postbackQueue.key", HashSet.class);
    public static final d<String> i = new d<>("com.applovin.sdk.stats", String.class);
    public static final d<String> j = new d<>("com.applovin.sdk.errors", String.class);
    public static final d<HashSet> k = new d<>("com.applovin.sdk.task.stats", HashSet.class);
    public static final d<String> l = new d<>("com.applovin.sdk.network_response_code_mapping", String.class);
    public static final d<String> m = new d<>("com.applovin.sdk.zones", String.class);
    public static final d<HashSet> n = new d<>("com.applovin.sdk.ad.stats", HashSet.class);
    public static final d<Integer> o = new d<>("com.applovin.sdk.last_video_position", Integer.class);
    public static final d<Boolean> p = new d<>("com.applovin.sdk.should_resume_video", Boolean.class);
    public static final d<String> q = new d<>("com.applovin.sdk.mediation.signal_providers", String.class);
    public static final d<String> r = new d<>("com.applovin.sdk.mediation.auto_init_adapters", String.class);
    public static final d<String> s = new d<>("com.applovin.sdk.persisted_data", String.class);
    private final String t;
    private final Class<T> u;

    public d(String str, Class<T> cls) {
        this.t = str;
        this.u = cls;
    }

    public String a() {
        return this.t;
    }

    public Class<T> b() {
        return this.u;
    }

    public String toString() {
        return "Key{name='" + this.t + '\'' + ", type=" + this.u + '}';
    }
}
