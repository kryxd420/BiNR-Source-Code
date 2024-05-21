package com.unity3d.ads.connectivity;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import com.unity3d.ads.properties.ClientProperties;

@TargetApi(21)
public class ConnectivityNetworkCallback extends ConnectivityManager.NetworkCallback {
    private static ConnectivityNetworkCallback _impl;

    public static void register() {
        if (_impl == null) {
            _impl = new ConnectivityNetworkCallback();
            ((ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest.Builder().build(), _impl);
        }
    }

    public static void unregister() {
        if (_impl != null) {
            ((ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")).unregisterNetworkCallback(_impl);
            _impl = null;
        }
    }

    public void onAvailable(Network network) {
        ConnectivityMonitor.connected();
    }

    public void onLost(Network network) {
        ConnectivityMonitor.disconnected();
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        ConnectivityMonitor.connectionStatusChanged();
    }

    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        ConnectivityMonitor.connectionStatusChanged();
    }
}
