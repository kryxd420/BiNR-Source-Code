package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.gcm.Task;

public class OneoffTask extends Task {
    public static final Parcelable.Creator<OneoffTask> CREATOR = new zzi();
    private final long zzaj;
    private final long zzak;

    public static class Builder extends Task.Builder {
        /* access modifiers changed from: private */
        public long zzaj = -1;
        /* access modifiers changed from: private */
        public long zzak = -1;

        public Builder() {
            this.isPersisted = false;
        }

        public OneoffTask build() {
            checkConditions();
            return new OneoffTask(this, (zzi) null);
        }

        /* access modifiers changed from: protected */
        public void checkConditions() {
            super.checkConditions();
            if (this.zzaj == -1 || this.zzak == -1) {
                throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
            } else if (this.zzaj >= this.zzak) {
                throw new IllegalArgumentException("Window start must be shorter than window end.");
            }
        }

        public Builder setExecutionWindow(long j, long j2) {
            this.zzaj = j;
            this.zzak = j2;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
        public Builder setPersisted(boolean z) {
            this.isPersisted = z;
            return this;
        }

        public Builder setRequiredNetwork(int i) {
            this.requiredNetworkState = i;
            return this;
        }

        public Builder setRequiresCharging(boolean z) {
            this.requiresCharging = z;
            return this;
        }

        public Builder setService(Class<? extends GcmTaskService> cls) {
            this.gcmTaskService = cls.getName();
            return this;
        }

        public Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        public Builder setUpdateCurrent(boolean z) {
            this.updateCurrent = z;
            return this;
        }
    }

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzaj = parcel.readLong();
        this.zzak = parcel.readLong();
    }

    /* synthetic */ OneoffTask(Parcel parcel, zzi zzi) {
        this(parcel);
    }

    private OneoffTask(Builder builder) {
        super((Task.Builder) builder);
        this.zzaj = builder.zzaj;
        this.zzak = builder.zzak;
    }

    /* synthetic */ OneoffTask(Builder builder, zzi zzi) {
        this(builder);
    }

    public long getWindowEnd() {
        return this.zzak;
    }

    public long getWindowStart() {
        return this.zzaj;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzaj);
        bundle.putLong("window_end", this.zzak);
    }

    public String toString() {
        String obj = super.toString();
        long windowStart = getWindowStart();
        long windowEnd = getWindowEnd();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 64);
        sb.append(obj);
        sb.append(" windowStart=");
        sb.append(windowStart);
        sb.append(" windowEnd=");
        sb.append(windowEnd);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.zzaj);
        parcel.writeLong(this.zzak);
    }
}
