package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.ViewCompat;
import android.widget.Toast;
import com.adcolony.sdk.aa;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class aw {
    static final int a = 128;
    static ExecutorService b = Executors.newSingleThreadExecutor();

    aw() {
    }

    static boolean a(String str) {
        Activity c = a.c();
        if (c == null) {
            return false;
        }
        try {
            c.getApplication().getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    static boolean a() {
        try {
            l a2 = a.a();
            File file = new File(a2.o().g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141");
            File file2 = new File(a2.o().g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5");
            boolean a3 = a2.j().a(file);
            boolean a4 = a2.j().a(file2);
            if (!a3 || !a4) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            new aa.a().a("Unable to delete controller or launch response.").a(aa.h);
            return false;
        }
    }

    static String b() {
        Activity c = a.c();
        if (c == null) {
            return "1.0";
        }
        try {
            return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            new aa.a().a("Failed to retrieve package info.").a(aa.h);
            return "1.0";
        }
    }

    static int c() {
        Activity c = a.c();
        if (c == null) {
            return 0;
        }
        try {
            return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            new aa.a().a("Failed to retrieve package info.").a(aa.h);
            return 0;
        }
    }

    static String d() {
        Activity c = a.c();
        if (c == null) {
            return "";
        }
        PackageManager packageManager = c.getApplication().getPackageManager();
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(c.getPackageName(), 0));
            return applicationLabel == null ? "" : applicationLabel.toString();
        } catch (Exception unused) {
            new aa.a().a("Failed to retrieve application label.").a(aa.h);
            return "";
        }
    }

    static int b(String str) {
        CRC32 crc32 = new CRC32();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            crc32.update(str.charAt(i));
        }
        return (int) crc32.getValue();
    }

    static String c(String str) {
        try {
            return bb.a(str);
        } catch (Exception unused) {
            return null;
        }
    }

    static String e() {
        return UUID.randomUUID().toString();
    }

    static JSONArray a(int i) {
        JSONArray b2 = y.b();
        for (int i2 = 0; i2 < i; i2++) {
            y.a(b2, e());
        }
        return b2;
    }

    static boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        Arrays.sort(strArr);
        Arrays.sort(strArr2);
        return Arrays.equals(strArr, strArr2);
    }

    static boolean a(Runnable runnable) {
        Activity c = a.c();
        if (c == null) {
            return false;
        }
        c.runOnUiThread(runnable);
        return true;
    }

    static double f() {
        double currentTimeMillis = (double) System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        return currentTimeMillis / 1000.0d;
    }

    static boolean d(String str) {
        if (str != null && str.length() <= 128) {
            return true;
        }
        new aa.a().a("String must be non-null and the max length is 128 characters.").a(aa.e);
        return false;
    }

    static boolean a(AudioManager audioManager) {
        if (audioManager == null) {
            new aa.a().a("isAudioEnabled() called with a null AudioManager").a(aa.h);
            return false;
        }
        try {
            if (audioManager.getStreamVolume(3) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            new aa.a().a("Exception occurred when accessing AudioManager.getStreamVolume: ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static double b(AudioManager audioManager) {
        if (audioManager == null) {
            new aa.a().a("getAudioVolume() called with a null AudioManager").a(aa.h);
            return 0.0d;
        }
        try {
            double streamVolume = (double) audioManager.getStreamVolume(3);
            double streamMaxVolume = (double) audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume == 0.0d) {
                return 0.0d;
            }
            Double.isNaN(streamVolume);
            Double.isNaN(streamMaxVolume);
            return streamVolume / streamMaxVolume;
        } catch (Exception e) {
            new aa.a().a("Exception occurred when accessing AudioManager: ").a(e.toString()).a(aa.h);
            return 0.0d;
        }
    }

    static AudioManager a(Context context) {
        if (context != null) {
            return (AudioManager) context.getSystemService("audio");
        }
        new aa.a().a("getAudioManager called with a null Context").a(aa.h);
        return null;
    }

    static void e(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    new aa.a().a(">").a(file.getAbsolutePath()).a(aa.b);
                    e(file.getAbsolutePath());
                } else {
                    new aa.a().a(file.getAbsolutePath()).a(aa.b);
                }
            }
        }
    }

    static String a(double d, int i) {
        StringBuilder sb = new StringBuilder();
        a(d, i, sb);
        return sb.toString();
    }

    static void a(double d, int i, StringBuilder sb) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            sb.append(d);
            return;
        }
        if (d < 0.0d) {
            d = -d;
            sb.append('-');
        }
        if (i == 0) {
            sb.append(Math.round(d));
            return;
        }
        long pow = (long) Math.pow(10.0d, (double) i);
        double d2 = (double) pow;
        Double.isNaN(d2);
        long round = Math.round(d * d2);
        sb.append(round / pow);
        sb.append('.');
        long j = round % pow;
        if (j == 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append('0');
            }
            return;
        }
        for (long j2 = j * 10; j2 < pow; j2 *= 10) {
            sb.append('0');
        }
        sb.append(j);
    }

    static String f(String str) {
        return str == null ? "" : URLDecoder.decode(str);
    }

    static String a(@NonNull Activity activity) {
        try {
            return activity.getPackageName();
        } catch (Exception unused) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    static String a(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    static int g(String str) {
        try {
            return (int) Long.parseLong(str, 16);
        } catch (NumberFormatException unused) {
            new aa.a().a("Unable to parse '").a(str).a("' as a color.").a(aa.f);
            return ViewCompat.MEASURED_STATE_MASK;
        }
    }

    static int b(Activity activity) {
        int identifier;
        if (activity != null && (identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE)) > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    static boolean g() {
        Activity c = a.c();
        return c != null && Build.VERSION.SDK_INT >= 24 && c.isInMultiWindowMode();
    }

    static boolean a(String str, File file) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused) {
                            new aa.a().a("Exception on closing MD5 input stream").a(aa.h);
                        }
                        throw th;
                    }
                }
                boolean equals = str.equals(String.format("%40s", new Object[]{new BigInteger(1, instance.digest()).toString(16)}).replace(' ', '0'));
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                    new aa.a().a("Exception on closing MD5 input stream").a(aa.h);
                }
                return equals;
            } catch (FileNotFoundException unused3) {
                new aa.a().a("Exception while getting FileInputStream").a(aa.h);
                return false;
            }
        } catch (NoSuchAlgorithmException unused4) {
            new aa.a().a("Exception while getting Digest").a(aa.h);
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        return new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.Date h(java.lang.String r4) {
        /*
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = "yyyy-MM-dd'T'HH:mmZ"
            r0.<init>(r1)
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyy-MM-dd'T'HH:mm:ssZ"
            r1.<init>(r2)
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyy-MM-dd"
            r2.<init>(r3)
            java.util.Date r0 = r0.parse(r4)     // Catch:{ Exception -> 0x001a }
            return r0
        L_0x001a:
            java.util.Date r0 = r1.parse(r4)     // Catch:{ Exception -> 0x001f }
            return r0
        L_0x001f:
            java.util.Date r4 = r2.parse(r4)     // Catch:{ Exception -> 0x0024 }
            return r4
        L_0x0024:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.aw.h(java.lang.String):java.util.Date");
    }

    static String a(JSONArray jSONArray) throws JSONException {
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                str = str + ",";
            }
            switch (jSONArray.getInt(i)) {
                case 1:
                    str = str + "MO";
                    break;
                case 2:
                    str = str + "TU";
                    break;
                case 3:
                    str = str + "WE";
                    break;
                case 4:
                    str = str + "TH";
                    break;
                case 5:
                    str = str + "FR";
                    break;
                case 6:
                    str = str + "SA";
                    break;
                case 7:
                    str = str + "SU";
                    break;
            }
        }
        return str;
    }

    static String b(JSONArray jSONArray) throws JSONException {
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                str = str + ",";
            }
            str = str + jSONArray.getInt(i);
        }
        return str;
    }

    static boolean a(Intent intent, boolean z) {
        try {
            Activity c = a.c();
            if (c == null) {
                return false;
            }
            AdColonyInterstitial u = a.a().u();
            if (u != null && u.g()) {
                u.h().f();
            }
            if (z) {
                c.startActivity(Intent.createChooser(intent, "Handle this via..."));
                return true;
            }
            c.startActivity(intent);
            return true;
        } catch (Exception e) {
            new aa.a().a(e.toString()).a(aa.f);
            return false;
        }
    }

    static boolean a(Intent intent) {
        return a(intent, false);
    }

    static boolean a(final String str, final int i) {
        final Activity c = a.c();
        if (c == null) {
            return false;
        }
        a((Runnable) new Runnable() {
            public void run() {
                Toast.makeText(c, str, i).show();
            }
        });
        return true;
    }

    private static void j(String str) {
        Activity c = a.c();
        if (c != null) {
            try {
                InputStream open = c.getAssets().open(str);
                FileOutputStream fileOutputStream = new FileOutputStream(a.a().o().d() + str);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        open.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return;
                    }
                }
            } catch (Exception e) {
                new aa.a().a("Failed copy hardcoded ad unit file named: ").a(str).a(" with error: ").a(e.getMessage()).a(aa.h);
            }
        }
    }

    public static void i(String str) {
        Activity c = a.c();
        if (c != null) {
            try {
                String[] list = c.getAssets().list(str);
                if (list.length == 0) {
                    j(str);
                    return;
                }
                File file = new File(a.a().o().d() + str);
                if (!file.exists()) {
                    file.mkdir();
                }
                for (int i = 0; i < list.length; i++) {
                    i(str + "/" + list[i]);
                }
            } catch (IOException e) {
                new aa.a().a("Failed copy hardcoded ad unit with error: ").a(e.getMessage()).a(aa.h);
            }
        }
    }

    static int a(ar arVar) {
        int i = 0;
        try {
            Activity c = a.c();
            if (c != null) {
                int i2 = (int) (c.getPackageManager().getPackageInfo(c.getPackageName(), 0).lastUpdateTime / 1000);
                boolean exists = new File(arVar.g() + "AppVersion").exists();
                boolean z = true;
                if (exists) {
                    if (y.c(y.c(arVar.g() + "AppVersion"), "last_update") != i2) {
                        i = 1;
                    } else {
                        z = false;
                    }
                } else {
                    i = 2;
                }
                if (z) {
                    JSONObject a2 = y.a();
                    y.b(a2, "last_update", i2);
                    y.h(a2, arVar.g() + "AppVersion");
                }
                return i;
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    static JSONArray b(Context context) {
        JSONArray b2 = y.b();
        if (context == null) {
            return b2;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions == null) {
                return b2;
            }
            JSONArray b3 = y.b();
            int i = 0;
            while (i < packageInfo.requestedPermissions.length) {
                try {
                    b3.put(packageInfo.requestedPermissions[i]);
                    i++;
                } catch (Exception unused) {
                }
            }
            return b3;
        } catch (Exception unused2) {
            return b2;
        }
    }

    static class a {
        double a;
        double b = ((double) System.currentTimeMillis());

        a(double d) {
            a(d);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            a(this.a);
        }

        /* access modifiers changed from: package-private */
        public void a(double d) {
            this.a = d;
            double currentTimeMillis = (double) System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            this.b = (currentTimeMillis / 1000.0d) + this.a;
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return c() == 0.0d;
        }

        /* access modifiers changed from: package-private */
        public double c() {
            double currentTimeMillis = (double) System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            double d = this.b - (currentTimeMillis / 1000.0d);
            if (d <= 0.0d) {
                return 0.0d;
            }
            return d;
        }

        public String toString() {
            return aw.a(c(), 2);
        }
    }
}
