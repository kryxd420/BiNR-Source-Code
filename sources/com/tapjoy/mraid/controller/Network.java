package com.tapjoy.mraid.controller;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.os.EnvironmentCompat;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.util.NetworkBroadcastReceiver;
import com.tapjoy.mraid.view.MraidView;

public class Network extends Abstract {
    private ConnectivityManager c;
    private int d;
    private NetworkBroadcastReceiver e;
    private IntentFilter f;

    public Network(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.c = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public String getNetwork() {
        NetworkInfo networkInfo;
        try {
            networkInfo = this.c.getActiveNetworkInfo();
        } catch (Exception e2) {
            e2.printStackTrace();
            networkInfo = null;
        }
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        if (networkInfo != null) {
            switch (AnonymousClass1.a[networkInfo.getState().ordinal()]) {
                case 1:
                    str = EnvironmentCompat.MEDIA_UNKNOWN;
                    break;
                case 2:
                    str = "offline";
                    break;
                default:
                    int type = networkInfo.getType();
                    if (type != 0) {
                        if (type == 1) {
                            str = TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
                            break;
                        }
                    } else {
                        str = "cell";
                        break;
                    }
                    break;
            }
        } else {
            str = "offline";
        }
        TapjoyLog.d("MRAID Network", "getNetwork: " + str);
        return str;
    }

    /* renamed from: com.tapjoy.mraid.controller.Network$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[NetworkInfo.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                android.net.NetworkInfo$State[] r0 = android.net.NetworkInfo.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.net.NetworkInfo$State r1 = android.net.NetworkInfo.State.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.net.NetworkInfo$State r1 = android.net.NetworkInfo.State.DISCONNECTED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.mraid.controller.Network.AnonymousClass1.<clinit>():void");
        }
    }

    public void startNetworkListener() {
        if (this.d == 0) {
            this.e = new NetworkBroadcastReceiver(this);
            this.f = new IntentFilter();
            this.f.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        this.d++;
        this.b.registerReceiver(this.e, this.f);
    }

    public void stopNetworkListener() {
        this.d--;
        if (this.d == 0) {
            this.b.unregisterReceiver(this.e);
            this.e = null;
            this.f = null;
        }
    }

    public void onConnectionChanged() {
        String str = "window.mraidview.fireChangeEvent({ network: '" + getNetwork() + "'});";
        TapjoyLog.d("MRAID Network", str);
        this.a.injectMraidJavaScript(str);
    }

    public void stopAllListeners() {
        this.d = 0;
        try {
            this.b.unregisterReceiver(this.e);
        } catch (Exception unused) {
        }
    }
}
