package com.unity3d.player;

import android.content.pm.PackageItemInfo;

public final class f implements c {
    private static boolean a(PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:2|3|4|(2:8|9)|10|11|(1:13)|14|(5:16|17|18|(2:20|39)(2:21|(2:23|40)(2:24|41))|27)|37|28|(2:30|31)(2:32|33)) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        com.unity3d.player.e.Log(5, "Failed to get permission info for " + r7 + ", manifest likely missing custom permission declaration");
        com.unity3d.player.e.Log(5, "Permission " + r7 + " ignored");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c2, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c3, code lost:
        com.unity3d.player.e.Log(6, "Unable to query for permission: " + r12.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d9, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005f */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038 A[Catch:{ Exception -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[Catch:{ Exception -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0095 A[Catch:{ Exception -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0099 A[Catch:{ Exception -> 0x00c2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.app.Activity r12, java.lang.Runnable r13) {
        /*
            r11 = this;
            if (r12 != 0) goto L_0x0003
            return
        L_0x0003:
            android.content.pm.PackageManager r0 = r12.getPackageManager()
            r1 = 128(0x80, float:1.794E-43)
            android.content.ComponentName r2 = r12.getComponentName()     // Catch:{ Exception -> 0x0029 }
            android.content.pm.ActivityInfo r2 = r0.getActivityInfo(r2, r1)     // Catch:{ Exception -> 0x0029 }
            java.lang.String r3 = r12.getPackageName()     // Catch:{ Exception -> 0x0029 }
            android.content.pm.ApplicationInfo r3 = r0.getApplicationInfo(r3, r1)     // Catch:{ Exception -> 0x0029 }
            boolean r2 = a(r2)     // Catch:{ Exception -> 0x0029 }
            if (r2 != 0) goto L_0x0025
            boolean r2 = a(r3)     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x0029
        L_0x0025:
            r13.run()     // Catch:{ Exception -> 0x0029 }
            return
        L_0x0029:
            java.lang.String r2 = r12.getPackageName()     // Catch:{ Exception -> 0x00c2 }
            r3 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r2 = r0.getPackageInfo(r2, r3)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String[] r3 = r2.requestedPermissions     // Catch:{ Exception -> 0x00c2 }
            r4 = 0
            if (r3 != 0) goto L_0x003c
            java.lang.String[] r3 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00c2 }
            r2.requestedPermissions = r3     // Catch:{ Exception -> 0x00c2 }
        L_0x003c:
            java.util.LinkedList r3 = new java.util.LinkedList     // Catch:{ Exception -> 0x00c2 }
            r3.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String[] r2 = r2.requestedPermissions     // Catch:{ Exception -> 0x00c2 }
            int r5 = r2.length     // Catch:{ Exception -> 0x00c2 }
            r6 = 0
        L_0x0045:
            if (r6 >= r5) goto L_0x008f
            r7 = r2[r6]     // Catch:{ Exception -> 0x00c2 }
            android.content.pm.PermissionInfo r8 = r0.getPermissionInfo(r7, r1)     // Catch:{ NameNotFoundException -> 0x005f }
            int r8 = r8.protectionLevel     // Catch:{ NameNotFoundException -> 0x005f }
            r8 = r8 & 1
            if (r8 != 0) goto L_0x0054
            goto L_0x008c
        L_0x0054:
            int r8 = r12.checkCallingOrSelfPermission(r7)     // Catch:{ NameNotFoundException -> 0x005f }
            if (r8 != 0) goto L_0x005b
            goto L_0x008c
        L_0x005b:
            r3.add(r7)     // Catch:{ NameNotFoundException -> 0x005f }
            goto L_0x008c
        L_0x005f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r9 = "Failed to get permission info for "
            r8.<init>(r9)     // Catch:{ Exception -> 0x00c2 }
            r8.append(r7)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r9 = ", manifest likely missing custom permission declaration"
            r8.append(r9)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00c2 }
            r9 = 5
            com.unity3d.player.e.Log(r9, r8)     // Catch:{ Exception -> 0x00c2 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r10 = "Permission "
            r8.<init>(r10)     // Catch:{ Exception -> 0x00c2 }
            r8.append(r7)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r7 = " ignored"
            r8.append(r7)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x00c2 }
            com.unity3d.player.e.Log(r9, r7)     // Catch:{ Exception -> 0x00c2 }
        L_0x008c:
            int r6 = r6 + 1
            goto L_0x0045
        L_0x008f:
            boolean r0 = r3.isEmpty()     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x0099
            r13.run()     // Catch:{ Exception -> 0x00c2 }
            return
        L_0x0099:
            com.unity3d.player.g r0 = new com.unity3d.player.g     // Catch:{ Exception -> 0x00c2 }
            r0.<init>(r13)     // Catch:{ Exception -> 0x00c2 }
            android.os.Bundle r13 = new android.os.Bundle     // Catch:{ Exception -> 0x00c2 }
            r13.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r1 = "PermissionNames"
            java.lang.String[] r2 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00c2 }
            java.lang.Object[] r2 = r3.toArray(r2)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ Exception -> 0x00c2 }
            r13.putStringArray(r1, r2)     // Catch:{ Exception -> 0x00c2 }
            r0.setArguments(r13)     // Catch:{ Exception -> 0x00c2 }
            android.app.FragmentManager r12 = r12.getFragmentManager()     // Catch:{ Exception -> 0x00c2 }
            android.app.FragmentTransaction r12 = r12.beginTransaction()     // Catch:{ Exception -> 0x00c2 }
            r12.add(r4, r0)     // Catch:{ Exception -> 0x00c2 }
            r12.commit()     // Catch:{ Exception -> 0x00c2 }
            return
        L_0x00c2:
            r12 = move-exception
            r13 = 6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unable to query for permission: "
            r0.<init>(r1)
            java.lang.String r12 = r12.getMessage()
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            com.unity3d.player.e.Log(r13, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.f.a(android.app.Activity, java.lang.Runnable):void");
    }
}
