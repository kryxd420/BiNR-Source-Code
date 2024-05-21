package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PolylineOptionsCreator")
@SafeParcelable.Reserved({1})
public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
    @SafeParcelable.Field(getter = "getColor", id = 4)
    private int color;
    @SafeParcelable.Field(getter = "getWidth", id = 3)
    private float width;
    @SafeParcelable.Field(getter = "getZIndex", id = 5)
    private float zzcr;
    @SafeParcelable.Field(getter = "isVisible", id = 6)
    private boolean zzcs;
    @SafeParcelable.Field(getter = "isClickable", id = 8)
    private boolean zzct;
    @SafeParcelable.Field(getter = "getPoints", id = 2)
    private final List<LatLng> zzdw;
    @SafeParcelable.Field(getter = "isGeodesic", id = 7)
    private boolean zzdy;
    @SafeParcelable.Field(getter = "getStartCap", id = 9)
    @NonNull
    private Cap zzeb;
    @SafeParcelable.Field(getter = "getEndCap", id = 10)
    @NonNull
    private Cap zzec;
    @SafeParcelable.Field(getter = "getJointType", id = 11)
    private int zzed;
    @Nullable
    @SafeParcelable.Field(getter = "getPattern", id = 12)
    private List<PatternItem> zzee;

    public PolylineOptions() {
        this.width = 10.0f;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzeb = new ButtCap();
        this.zzec = new ButtCap();
        this.zzed = 0;
        this.zzee = null;
        this.zzdw = new ArrayList();
    }

    @SafeParcelable.Constructor
    PolylineOptions(@SafeParcelable.Param(id = 2) List list, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) float f2, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) boolean z3, @Nullable @SafeParcelable.Param(id = 9) Cap cap, @Nullable @SafeParcelable.Param(id = 10) Cap cap2, @SafeParcelable.Param(id = 11) int i2, @Nullable @SafeParcelable.Param(id = 12) List<PatternItem> list2) {
        this.width = 10.0f;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzeb = new ButtCap();
        this.zzec = new ButtCap();
        this.zzed = 0;
        this.zzee = null;
        this.zzdw = list;
        this.width = f;
        this.color = i;
        this.zzcr = f2;
        this.zzcs = z;
        this.zzdy = z2;
        this.zzct = z3;
        if (cap != null) {
            this.zzeb = cap;
        }
        if (cap2 != null) {
            this.zzec = cap2;
        }
        this.zzed = i2;
        this.zzee = list2;
    }

    public final PolylineOptions add(LatLng latLng) {
        this.zzdw.add(latLng);
        return this;
    }

    public final PolylineOptions add(LatLng... latLngArr) {
        this.zzdw.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.zzdw.add(add);
        }
        return this;
    }

    public final PolylineOptions clickable(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolylineOptions color(int i) {
        this.color = i;
        return this;
    }

    public final PolylineOptions endCap(@NonNull Cap cap) {
        this.zzec = (Cap) Preconditions.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    public final PolylineOptions geodesic(boolean z) {
        this.zzdy = z;
        return this;
    }

    public final int getColor() {
        return this.color;
    }

    @NonNull
    public final Cap getEndCap() {
        return this.zzec;
    }

    public final int getJointType() {
        return this.zzed;
    }

    @Nullable
    public final List<PatternItem> getPattern() {
        return this.zzee;
    }

    public final List<LatLng> getPoints() {
        return this.zzdw;
    }

    @NonNull
    public final Cap getStartCap() {
        return this.zzeb;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final boolean isClickable() {
        return this.zzct;
    }

    public final boolean isGeodesic() {
        return this.zzdy;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final PolylineOptions jointType(int i) {
        this.zzed = i;
        return this;
    }

    public final PolylineOptions pattern(@Nullable List<PatternItem> list) {
        this.zzee = list;
        return this;
    }

    public final PolylineOptions startCap(@NonNull Cap cap) {
        this.zzeb = (Cap) Preconditions.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    public final PolylineOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final PolylineOptions width(float f) {
        this.width = f;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeFloat(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getColor());
        SafeParcelWriter.writeFloat(parcel, 5, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 6, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 7, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 8, isClickable());
        SafeParcelWriter.writeParcelable(parcel, 9, getStartCap(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, getEndCap(), i, false);
        SafeParcelWriter.writeInt(parcel, 11, getJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getPattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final PolylineOptions zIndex(float f) {
        this.zzcr = f;
        return this;
    }
}
