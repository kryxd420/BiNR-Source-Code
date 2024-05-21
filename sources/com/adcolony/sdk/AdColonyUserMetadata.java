package com.adcolony.sdk;

import android.location.Location;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyUserMetadata {
    public static final String USER_EDUCATION_ASSOCIATES_DEGREE = "associates_degree";
    public static final String USER_EDUCATION_BACHELORS_DEGREE = "bachelors_degree";
    public static final String USER_EDUCATION_GRADE_SCHOOL = "grade_school";
    public static final String USER_EDUCATION_GRADUATE_DEGREE = "graduate_degree";
    public static final String USER_EDUCATION_HIGH_SCHOOL_DIPLOMA = "high_school_diploma";
    public static final String USER_EDUCATION_SOME_COLLEGE = "some_college";
    public static final String USER_EDUCATION_SOME_HIGH_SCHOOL = "some_high_school";
    public static final String USER_FEMALE = "female";
    public static final String USER_MALE = "male";
    public static final String USER_MARRIED = "married";
    public static final String USER_SINGLE = "single";
    static final int a = 128;
    JSONArray b = y.b();
    JSONObject c = y.a();
    Location d;

    public AdColonyUserMetadata setUserGender(@NonNull String str) {
        if (aw.d(str)) {
            setMetadata("adc_gender", str);
        }
        return this;
    }

    public String getUserGender() {
        return y.b(this.c, "adc_gender");
    }

    public AdColonyUserMetadata setUserAge(@IntRange(from = 0, to = 130) int i) {
        setMetadata("adc_age", (double) i);
        return this;
    }

    public int getUserAge() {
        return y.c(this.c, "adc_age");
    }

    public AdColonyUserMetadata setUserMaritalStatus(@NonNull String str) {
        if (aw.d(str)) {
            setMetadata("adc_marital_status", str);
        }
        return this;
    }

    public String getUserMaritalStatus() {
        return y.b(this.c, "adc_marital_status");
    }

    public AdColonyUserMetadata setUserAnnualHouseholdIncome(@IntRange(from = 0) int i) {
        setMetadata("adc_household_income", (double) i);
        return this;
    }

    public int getUserAnnualHouseholdIncome() {
        return y.c(this.c, "adc_household_income");
    }

    public AdColonyUserMetadata setUserEducation(@NonNull String str) {
        if (aw.d(str)) {
            setMetadata("adc_education", str);
        }
        return this;
    }

    public String getUserEducation() {
        return y.b(this.c, "adc_education");
    }

    public AdColonyUserMetadata setUserZipCode(@NonNull String str) {
        if (aw.d(str)) {
            setMetadata("adc_zip", str);
        }
        return this;
    }

    public String getUserZipCode() {
        return y.b(this.c, "adc_zip");
    }

    public AdColonyUserMetadata setUserLocation(@NonNull Location location) {
        this.d = location;
        setMetadata("adc_longitude", location.getLongitude());
        setMetadata("adc_latitude", location.getLatitude());
        setMetadata("adc_speed", (double) location.getSpeed());
        setMetadata("adc_altitude", location.getAltitude());
        setMetadata("adc_time", (double) location.getTime());
        setMetadata("adc_accuracy", (double) location.getAccuracy());
        return this;
    }

    public Location getUserLocation() {
        return this.d;
    }

    public AdColonyUserMetadata addUserInterest(@NonNull String str) {
        if (aw.d(str)) {
            y.a(this.b, str);
            y.a(this.c, "adc_interests", this.b);
        }
        return this;
    }

    public AdColonyUserMetadata clearUserInterests() {
        this.b = y.b();
        y.a(this.c, "adc_interests", this.b);
        return this;
    }

    public String[] getUserInterests() {
        String[] strArr = new String[this.b.length()];
        for (int i = 0; i < this.b.length(); i++) {
            strArr[i] = y.c(this.b, i);
        }
        return strArr;
    }

    public AdColonyUserMetadata setMetadata(@NonNull String str, boolean z) {
        if (aw.d(str)) {
            y.a(this.c, str, z);
        }
        return this;
    }

    public Object getMetadata(@NonNull String str) {
        return y.a(this.c, str);
    }

    public AdColonyUserMetadata setMetadata(@NonNull String str, double d2) {
        if (aw.d(str)) {
            y.a(this.c, str, d2);
        }
        return this;
    }

    public AdColonyUserMetadata setMetadata(@NonNull String str, @NonNull String str2) {
        if (aw.d(str2) && aw.d(str)) {
            y.a(this.c, str, str2);
        }
        return this;
    }
}
