package com.tapjoy.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class j extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        a(context, intent);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:9|(3:11|12|(5:14|15|16|17|28)(1:26))(4:18|19|20|29)|24|7|6) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0021 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0021 A[LOOP:0: B:6:0x0021->B:24:0x0021, LOOP_START, PHI: r3 
      PHI: (r3v2 int) = (r3v1 int), (r3v3 int) binds: [B:5:0x0020, B:24:0x0021] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:6:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0027 A[Catch:{ NameNotFoundException -> 0x005a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(android.content.Context r9, android.content.Intent r10) {
        /*
            r8 = this;
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.Class r1 = r8.getClass()
            r0.<init>(r9, r1)
            r1 = 0
            android.content.pm.PackageManager r2 = r9.getPackageManager()     // Catch:{ NameNotFoundException -> 0x005b }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ActivityInfo r0 = r2.getReceiverInfo(r0, r3)     // Catch:{ NameNotFoundException -> 0x005b }
            android.os.Bundle r0 = r0.metaData     // Catch:{ NameNotFoundException -> 0x005b }
            if (r0 == 0) goto L_0x005b
            java.util.Set r2 = r0.keySet()     // Catch:{ NameNotFoundException -> 0x005b }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ NameNotFoundException -> 0x005b }
            r3 = 0
        L_0x0021:
            boolean r4 = r2.hasNext()     // Catch:{ NameNotFoundException -> 0x005a }
            if (r4 == 0) goto L_0x005a
            java.lang.Object r4 = r2.next()     // Catch:{ NameNotFoundException -> 0x005a }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ NameNotFoundException -> 0x005a }
            java.lang.String r5 = r0.getString(r4)     // Catch:{ NameNotFoundException -> 0x005a }
            if (r5 == 0) goto L_0x0054
            java.lang.Class r4 = java.lang.Class.forName(r5)     // Catch:{  }
            java.lang.Object r4 = r4.newInstance()     // Catch:{  }
            boolean r6 = r4 instanceof android.content.BroadcastReceiver     // Catch:{  }
            if (r6 == 0) goto L_0x0021
            android.content.BroadcastReceiver r4 = (android.content.BroadcastReceiver) r4     // Catch:{  }
            android.content.Intent r6 = new android.content.Intent     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021 }
            r6.<init>(r10)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021 }
            android.content.ComponentName r7 = new android.content.ComponentName     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021 }
            r7.<init>(r9, r5)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021 }
            r6.setComponent(r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021 }
            r4.onReceive(r9, r6)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0021 }
            int r3 = r3 + 1
            goto L_0x0021
        L_0x0054:
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ NameNotFoundException -> 0x005a }
            r5[r1] = r4     // Catch:{ NameNotFoundException -> 0x005a }
            goto L_0x0021
        L_0x005a:
            r1 = r3
        L_0x005b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.j.a(android.content.Context, android.content.Intent):int");
    }
}
