package com.adcolony.sdk;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.adcolony.sdk.aa;
import com.tapjoy.TapjoyConstants;

class aj {
    aj() {
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        NetworkInfo networkInfo;
        Activity c = a.c();
        if (c == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                networkInfo = null;
            } else {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.getType() == 1) {
                return true;
            }
            return false;
        } catch (SecurityException e) {
            new aa.a().a("SecurityException - please ensure you added the ").a("ACCESS_NETWORK_STATE permission: ").a(e.toString()).a(aa.g);
            return false;
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving activeNetworkInfo in ").a("ADCNetwork.using_wifi(): ").a(e2.toString()).a(aa.h);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        NetworkInfo networkInfo;
        Activity c = a.c();
        if (c == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                networkInfo = null;
            } else {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
            if (networkInfo == null) {
                return false;
            }
            int type = networkInfo.getType();
            if (type == 0 || type >= 2) {
                return true;
            }
            return false;
        } catch (SecurityException e) {
            new aa.a().a("SecurityException - please ensure you added the ").a("ACCESS_NETWORK_STATE permission: ").a(e.toString()).a(aa.g);
            return false;
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving activeNetworkInfo in ").a("ADCNetwork.using_mobile(): ").a(e2.toString()).a(aa.h);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public String c() {
        if (a()) {
            return TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
        }
        return b() ? "cell" : "none";
    }
}
