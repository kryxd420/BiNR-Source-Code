package com.tapdaq.adapters.tapdaq.model;

import java.util.List;

public class TMQueue {
    private List<TMAd> adverts;
    private String creative_type;
    private String queue_id;

    public String getQueueid() {
        return this.queue_id;
    }

    public void setCreative_type(String str) {
        if (str.contains("_SLIM")) {
            str = str.replace("_SLIM", "");
        } else if (str.contains("_FAT")) {
            str = str.replace("_FAT", "");
        }
        this.creative_type = str;
    }

    public String getCreativetype() {
        return this.creative_type;
    }

    public List<TMAd> getAdverts() {
        return this.adverts;
    }
}
