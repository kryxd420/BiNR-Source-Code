package com.unity3d.ads.device;

public interface IVolumeChangeListener {
    int getStreamType();

    void onVolumeChanged(int i);
}
