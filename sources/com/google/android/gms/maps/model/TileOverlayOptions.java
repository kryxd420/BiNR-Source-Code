package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

@SafeParcelable.Class(creator = "TileOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
    @SafeParcelable.Field(getter = "getZIndex", id = 4)
    private float zzcr;
    @SafeParcelable.Field(getter = "isVisible", id = 3)
    private boolean zzcs = true;
    @SafeParcelable.Field(getter = "getTransparency", id = 6)
    private float zzcz = 0.0f;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getTileProviderDelegate", id = 2, type = "android.os.IBinder")
    public zzaf zzeh;
    private TileProvider zzei;
    @SafeParcelable.Field(defaultValue = "true", getter = "getFadeIn", id = 5)
    private boolean zzej = true;

    public TileOverlayOptions() {
    }

    @SafeParcelable.Constructor
    TileOverlayOptions(@SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) float f2) {
        this.zzeh = zzag.zzk(iBinder);
        this.zzei = this.zzeh == null ? null : new zzs(this);
        this.zzcs = z;
        this.zzcr = f;
        this.zzej = z2;
        this.zzcz = f2;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzej = z;
        return this;
    }

    public final boolean getFadeIn() {
        return this.zzej;
    }

    public final TileProvider getTileProvider() {
        return this.zzei;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzei = tileProvider;
        this.zzeh = this.zzei == null ? null : new zzt(this, tileProvider);
        return this;
    }

    public final TileOverlayOptions transparency(float f) {
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzcz = f;
        return this;
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzeh.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final TileOverlayOptions zIndex(float f) {
        this.zzcr = f;
        return this;
    }
}
