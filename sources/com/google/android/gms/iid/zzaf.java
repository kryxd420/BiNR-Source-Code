package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzaf {
    private static int zzck = 0;
    private static final zzaj<Boolean> zzco = zzai.zzw().zzd("gcm_iid_use_messenger_ipc", true);
    private static String zzcp = null;
    private static boolean zzcq = false;
    private static int zzcr = 0;
    private static int zzcs = 0;
    private static BroadcastReceiver zzct = null;
    private PendingIntent zzad;
    private Messenger zzah;
    private Map<String, Object> zzcu = new ArrayMap();
    private Messenger zzcv;
    private MessengerCompat zzcw;
    private Context zzk;

    public zzaf(Context context) {
        this.zzk = context;
    }

    private static void zzd(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append("Failed to send response ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
            }
        }
    }

    private final void zzd(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.zzcu.get(str);
            this.zzcu.put(str, obj);
            zzd(obj2, obj);
        }
    }

    private static boolean zzd(PackageManager packageManager, String str) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            zzcp = applicationInfo.packageName;
            zzcs = applicationInfo.uid;
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private static boolean zzd(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return zzd(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56 + String.valueOf(str2).length());
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID", sb.toString());
        return false;
    }

    private final synchronized void zzg(Intent intent) {
        if (this.zzad == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzad = PendingIntent.getBroadcast(this.zzk, 0, intent2, 0);
        }
        intent.putExtra(TapjoyConstants.TJC_APP_PLACEMENT, this.zzad);
    }

    static String zzi(Bundle bundle) throws IOException {
        if (bundle != null) {
            String string = bundle.getString("registration_id");
            if (string == null) {
                string = bundle.getString("unregistered");
            }
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString(TJAdUnitConstants.String.VIDEO_ERROR);
            if (string2 != null) {
                throw new IOException(string2);
            }
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 29);
            sb.append("Unexpected response from GCM ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString(), new Throwable());
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private final Bundle zzj(Bundle bundle) throws IOException {
        Bundle zzk2 = zzk(bundle);
        if (zzk2 == null || !zzk2.containsKey("google.messenger")) {
            return zzk2;
        }
        Bundle zzk3 = zzk(bundle);
        if (zzk3 == null || !zzk3.containsKey("google.messenger")) {
            return zzk3;
        }
        return null;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private final android.os.Bundle zzk(android.os.Bundle r9) throws java.io.IOException {
        /*
            r8 = this;
            android.os.ConditionVariable r0 = new android.os.ConditionVariable
            r0.<init>()
            java.lang.String r1 = zzv()
            java.lang.Class r2 = r8.getClass()
            monitor-enter(r2)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r8.zzcu     // Catch:{ all -> 0x01d5 }
            r3.put(r1, r0)     // Catch:{ all -> 0x01d5 }
            monitor-exit(r2)     // Catch:{ all -> 0x01d5 }
            android.os.Messenger r2 = r8.zzah
            if (r2 != 0) goto L_0x002d
            android.content.Context r2 = r8.zzk
            zzl(r2)
            android.os.Messenger r2 = new android.os.Messenger
            com.google.android.gms.iid.zzag r3 = new com.google.android.gms.iid.zzag
            android.os.Looper r4 = android.os.Looper.getMainLooper()
            r3.<init>(r8, r4)
            r2.<init>(r3)
            r8.zzah = r2
        L_0x002d:
            java.lang.String r2 = zzcp
            if (r2 == 0) goto L_0x01cd
            android.content.Intent r2 = new android.content.Intent
            boolean r3 = zzcq
            if (r3 == 0) goto L_0x003a
            java.lang.String r3 = "com.google.iid.TOKEN_REQUEST"
            goto L_0x003c
        L_0x003a:
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTER"
        L_0x003c:
            r2.<init>(r3)
            java.lang.String r3 = zzcp
            r2.setPackage(r3)
            r2.putExtras(r9)
            r8.zzg(r2)
            java.lang.String r9 = "kid"
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "|ID|"
            r4.append(r3)
            r4.append(r1)
            java.lang.String r3 = "|"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r2.putExtra(r9, r3)
            java.lang.String r9 = "X-kid"
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "|ID|"
            r4.append(r3)
            r4.append(r1)
            java.lang.String r3 = "|"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r2.putExtra(r9, r3)
            java.lang.String r9 = "com.google.android.gsf"
            java.lang.String r3 = zzcp
            boolean r9 = r9.equals(r3)
            java.lang.String r3 = "useGsf"
            java.lang.String r3 = r2.getStringExtra(r3)
            if (r3 == 0) goto L_0x00aa
            java.lang.String r9 = "1"
            boolean r9 = r9.equals(r3)
        L_0x00aa:
            java.lang.String r3 = "InstanceID"
            r4 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r4)
            if (r3 == 0) goto L_0x00db
            java.lang.String r3 = "InstanceID"
            android.os.Bundle r5 = r2.getExtras()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r6 = java.lang.String.valueOf(r5)
            int r6 = r6.length()
            int r6 = r6 + 8
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Sending "
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            android.util.Log.d(r3, r5)
        L_0x00db:
            android.os.Messenger r3 = r8.zzcv
            if (r3 == 0) goto L_0x0102
            java.lang.String r3 = "google.messenger"
            android.os.Messenger r5 = r8.zzah
            r2.putExtra(r3, r5)
            android.os.Message r3 = android.os.Message.obtain()
            r3.obj = r2
            android.os.Messenger r5 = r8.zzcv     // Catch:{ RemoteException -> 0x00f3 }
            r5.send(r3)     // Catch:{ RemoteException -> 0x00f3 }
            goto L_0x017a
        L_0x00f3:
            java.lang.String r3 = "InstanceID"
            boolean r3 = android.util.Log.isLoggable(r3, r4)
            if (r3 == 0) goto L_0x0102
            java.lang.String r3 = "InstanceID"
            java.lang.String r5 = "Messenger failed, fallback to startService"
            android.util.Log.d(r3, r5)
        L_0x0102:
            if (r9 == 0) goto L_0x013e
            monitor-enter(r8)
            android.content.BroadcastReceiver r9 = zzct     // Catch:{ all -> 0x013b }
            if (r9 != 0) goto L_0x0139
            com.google.android.gms.iid.zzah r9 = new com.google.android.gms.iid.zzah     // Catch:{ all -> 0x013b }
            r9.<init>(r8)     // Catch:{ all -> 0x013b }
            zzct = r9     // Catch:{ all -> 0x013b }
            java.lang.String r9 = "InstanceID"
            boolean r9 = android.util.Log.isLoggable(r9, r4)     // Catch:{ all -> 0x013b }
            if (r9 == 0) goto L_0x011f
            java.lang.String r9 = "InstanceID"
            java.lang.String r3 = "Registered GSF callback receiver"
            android.util.Log.d(r9, r3)     // Catch:{ all -> 0x013b }
        L_0x011f:
            android.content.IntentFilter r9 = new android.content.IntentFilter     // Catch:{ all -> 0x013b }
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTRATION"
            r9.<init>(r3)     // Catch:{ all -> 0x013b }
            android.content.Context r3 = r8.zzk     // Catch:{ all -> 0x013b }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ all -> 0x013b }
            r9.addCategory(r3)     // Catch:{ all -> 0x013b }
            android.content.Context r3 = r8.zzk     // Catch:{ all -> 0x013b }
            android.content.BroadcastReceiver r4 = zzct     // Catch:{ all -> 0x013b }
            java.lang.String r5 = "com.google.android.c2dm.permission.SEND"
            r6 = 0
            r3.registerReceiver(r4, r9, r5, r6)     // Catch:{ all -> 0x013b }
        L_0x0139:
            monitor-exit(r8)     // Catch:{ all -> 0x013b }
            goto L_0x016f
        L_0x013b:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x013b }
            throw r9
        L_0x013e:
            java.lang.String r9 = "google.messenger"
            android.os.Messenger r3 = r8.zzah
            r2.putExtra(r9, r3)
            java.lang.String r9 = "messenger2"
            java.lang.String r3 = "1"
            r2.putExtra(r9, r3)
            com.google.android.gms.iid.MessengerCompat r9 = r8.zzcw
            if (r9 == 0) goto L_0x016b
            android.os.Message r9 = android.os.Message.obtain()
            r9.obj = r2
            com.google.android.gms.iid.MessengerCompat r3 = r8.zzcw     // Catch:{ RemoteException -> 0x015c }
            r3.send(r9)     // Catch:{ RemoteException -> 0x015c }
            goto L_0x017a
        L_0x015c:
            java.lang.String r9 = "InstanceID"
            boolean r9 = android.util.Log.isLoggable(r9, r4)
            if (r9 == 0) goto L_0x016b
            java.lang.String r9 = "InstanceID"
            java.lang.String r3 = "Messenger failed, fallback to startService"
            android.util.Log.d(r9, r3)
        L_0x016b:
            boolean r9 = zzcq
            if (r9 == 0) goto L_0x0175
        L_0x016f:
            android.content.Context r9 = r8.zzk
            r9.sendBroadcast(r2)
            goto L_0x017a
        L_0x0175:
            android.content.Context r9 = r8.zzk
            r9.startService(r2)
        L_0x017a:
            r2 = 30000(0x7530, double:1.4822E-319)
            r0.block(r2)
            java.lang.Class r9 = r8.getClass()
            monitor-enter(r9)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r8.zzcu     // Catch:{ all -> 0x01ca }
            java.lang.Object r0 = r0.remove(r1)     // Catch:{ all -> 0x01ca }
            boolean r1 = r0 instanceof android.os.Bundle     // Catch:{ all -> 0x01ca }
            if (r1 == 0) goto L_0x0192
            android.os.Bundle r0 = (android.os.Bundle) r0     // Catch:{ all -> 0x01ca }
            monitor-exit(r9)     // Catch:{ all -> 0x01ca }
            return r0
        L_0x0192:
            boolean r1 = r0 instanceof java.lang.String     // Catch:{ all -> 0x01ca }
            if (r1 == 0) goto L_0x019e
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x01ca }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01ca }
            r1.<init>(r0)     // Catch:{ all -> 0x01ca }
            throw r1     // Catch:{ all -> 0x01ca }
        L_0x019e:
            java.lang.String r1 = "InstanceID"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01ca }
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01ca }
            int r2 = r2.length()     // Catch:{ all -> 0x01ca }
            int r2 = r2 + 12
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ca }
            r3.<init>(r2)     // Catch:{ all -> 0x01ca }
            java.lang.String r2 = "No response "
            r3.append(r2)     // Catch:{ all -> 0x01ca }
            r3.append(r0)     // Catch:{ all -> 0x01ca }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x01ca }
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x01ca }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01ca }
            java.lang.String r1 = "TIMEOUT"
            r0.<init>(r1)     // Catch:{ all -> 0x01ca }
            throw r0     // Catch:{ all -> 0x01ca }
        L_0x01ca:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01ca }
            throw r0
        L_0x01cd:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r0 = "MISSING_INSTANCEID_SERVICE"
            r9.<init>(r0)
            throw r9
        L_0x01d5:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01d5 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzaf.zzk(android.os.Bundle):android.os.Bundle");
    }

    public static boolean zzk(Context context) {
        if (zzcp != null) {
            zzl(context);
        }
        return zzcq;
    }

    public static String zzl(Context context) {
        boolean z;
        if (zzcp != null) {
            return zzcp;
        }
        zzcr = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        boolean z2 = true;
        if (!PlatformVersion.isAtLeastO()) {
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (it.hasNext()) {
                    if (zzd(packageManager, it.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                        zzcq = false;
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return zzcp;
            }
        }
        Iterator<ResolveInfo> it2 = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (true) {
            if (it2.hasNext()) {
                if (zzd(packageManager, it2.next().activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                    zzcq = true;
                    break;
                }
            } else {
                z2 = false;
                break;
            }
        }
        if (z2) {
            return zzcp;
        }
        Log.w("InstanceID", "Failed to resolve IID implementation package, falling back");
        if (zzd(packageManager, "com.google.android.gms")) {
            zzcq = PlatformVersion.isAtLeastO();
            return zzcp;
        } else if (PlatformVersion.isAtLeastLollipop() || !zzd(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID", "Google Play services is missing, unable to get tokens");
            return null;
        } else {
            zzcq = false;
            return zzcp;
        }
    }

    private static int zzm(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(zzl(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private static synchronized String zzv() {
        String num;
        synchronized (zzaf.class) {
            int i = zzck;
            zzck = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzd(Bundle bundle, KeyPair keyPair) throws IOException {
        int zzm = zzm(this.zzk);
        bundle.putString("gmsv", Integer.toString(zzm));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(InstanceID.zzg(this.zzk)));
        bundle.putString("app_ver_name", InstanceID.zzh(this.zzk));
        bundle.putString("cliv", "iid-12451000");
        bundle.putString("appid", InstanceID.zzd(keyPair));
        if (zzm < 12000000 || !zzco.get().booleanValue()) {
            return zzj(bundle);
        }
        try {
            return (Bundle) Tasks.await(new zzr(this.zzk).zzd(1, bundle));
        } catch (InterruptedException | ExecutionException e) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("Error making request: ");
                sb.append(valueOf);
                Log.d("InstanceID", sb.toString());
            }
            if (!(e.getCause() instanceof zzaa) || ((zzaa) e.getCause()).getErrorCode() != 4) {
                return null;
            }
            return zzj(bundle);
        }
    }

    public final void zze(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.zzcw = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zzcv = (Messenger) parcelableExtra;
                    }
                }
                zzh((Intent) message.obj);
                return;
            }
            Log.w("InstanceID", "Dropping invalid message");
        }
    }

    public final void zzh(Intent intent) {
        String str;
        if (intent != null) {
            String action = intent.getAction();
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(action) || "com.google.android.gms.iid.InstanceID".equals(action)) {
                String stringExtra = intent.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    String stringExtra2 = intent.getStringExtra(TJAdUnitConstants.String.VIDEO_ERROR);
                    if (stringExtra2 == null) {
                        String valueOf = String.valueOf(intent.getExtras());
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
                        sb.append("Unexpected response, no error or registration id ");
                        sb.append(valueOf);
                        Log.w("InstanceID", sb.toString());
                        return;
                    }
                    if (Log.isLoggable("InstanceID", 3)) {
                        String valueOf2 = String.valueOf(stringExtra2);
                        Log.d("InstanceID", valueOf2.length() != 0 ? "Received InstanceID error ".concat(valueOf2) : new String("Received InstanceID error "));
                    }
                    String str2 = null;
                    if (stringExtra2.startsWith("|")) {
                        String[] split = stringExtra2.split("\\|");
                        if (!"ID".equals(split[1])) {
                            String valueOf3 = String.valueOf(stringExtra2);
                            Log.w("InstanceID", valueOf3.length() != 0 ? "Unexpected structured response ".concat(valueOf3) : new String("Unexpected structured response "));
                        }
                        if (split.length > 2) {
                            String str3 = split[2];
                            str = split[3];
                            if (str.startsWith(":")) {
                                str = str.substring(1);
                            }
                            str2 = str3;
                        } else {
                            str = "UNKNOWN";
                        }
                        stringExtra2 = str;
                        intent.putExtra(TJAdUnitConstants.String.VIDEO_ERROR, stringExtra2);
                    }
                    if (str2 == null) {
                        synchronized (getClass()) {
                            for (String next : this.zzcu.keySet()) {
                                Object obj = this.zzcu.get(next);
                                this.zzcu.put(next, stringExtra2);
                                zzd(obj, (Object) stringExtra2);
                            }
                        }
                        return;
                    }
                    zzd(str2, (Object) stringExtra2);
                    return;
                }
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    Bundle extras = intent.getExtras();
                    extras.putString("registration_id", group2);
                    zzd(group, (Object) extras);
                } else if (Log.isLoggable("InstanceID", 3)) {
                    String valueOf4 = String.valueOf(stringExtra);
                    Log.d("InstanceID", valueOf4.length() != 0 ? "Unexpected response string: ".concat(valueOf4) : new String("Unexpected response string: "));
                }
            } else if (Log.isLoggable("InstanceID", 3)) {
                String valueOf5 = String.valueOf(intent.getAction());
                Log.d("InstanceID", valueOf5.length() != 0 ? "Unexpected response ".concat(valueOf5) : new String("Unexpected response "));
            }
        } else if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Unexpected response: null");
        }
    }
}
