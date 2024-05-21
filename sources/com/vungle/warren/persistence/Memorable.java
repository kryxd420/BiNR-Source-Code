package com.vungle.warren.persistence;

import android.support.annotation.NonNull;

public interface Memorable {
    @NonNull
    String getId();

    byte[] toByteArray();
}
