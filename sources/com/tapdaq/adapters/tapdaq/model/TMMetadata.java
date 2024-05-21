package com.tapdaq.adapters.tapdaq.model;

public class TMMetadata {
    private String age_rating;
    private String app_name;
    private String app_size;
    private String app_version;
    private String average_review;
    private String category;
    private String cta_text;
    private String currency;
    private String description;
    private String developer_name;
    private String icon_url;
    private String price;
    private String title;
    private String total_reviews;

    public String getIconUrl() {
        return this.icon_url;
    }

    public String getAppName() {
        return this.app_name;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCallToActionText() {
        return this.cta_text;
    }

    public String getDeveloperName() {
        return this.developer_name;
    }

    public String getAgeRating() {
        return this.age_rating;
    }

    public String getAppSize() {
        return this.app_size;
    }

    public String getAverageReview() {
        return this.average_review;
    }

    public String getTotalReviews() {
        return this.total_reviews;
    }

    public String getCategory() {
        return this.category;
    }

    public String getAppVersion() {
        return this.app_version;
    }

    public String getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }
}
