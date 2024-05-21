package com.moat.analytics.mobile.tjy;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final String EVENT_AD_VOLUME = "adVolume";
    public static final String EVENT_PLAY_HEAD = "playhead";
    public static final String EVENT_TS = "aTimeStamp";
    public static final String EVENT_TYPE = "type";
    public static final Integer TIME_UNAVAILABLE = Integer.MIN_VALUE;
    public static final Double VOLUME_UNAVAILABLE = Double.valueOf(Double.NaN);
    private Long a;
    public Integer adPlayhead;
    public Double adVolume;
    public MoatAdEventType eventType;

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, TIME_UNAVAILABLE, VOLUME_UNAVAILABLE);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, VOLUME_UNAVAILABLE);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.a = Long.valueOf(System.currentTimeMillis());
        this.eventType = moatAdEventType;
        this.adVolume = d;
        this.adPlayhead = num;
    }

    public long getTimeStamp() {
        return this.a.longValue();
    }

    public Map toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(EVENT_AD_VOLUME, this.adVolume);
        hashMap.put(EVENT_PLAY_HEAD, this.adPlayhead);
        hashMap.put(EVENT_TS, this.a);
        hashMap.put("type", this.eventType.toString());
        return hashMap;
    }
}
