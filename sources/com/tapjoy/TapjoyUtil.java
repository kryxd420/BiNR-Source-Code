package com.tapjoy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TapjoyUtil {
    private static String a;
    private static HashMap b = new HashMap();

    public static String SHA1(String str) {
        return a("SHA-1", str);
    }

    public static String SHA256(String str) {
        return a("SHA-256", str);
    }

    private static String a(String str, String str2) {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(str2.getBytes("iso-8859-1"), 0, str2.length());
        return convertToHex(instance.digest());
    }

    public static String convertToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = (bArr[i] >>> 4) & 15;
            int i2 = 0;
            while (true) {
                if (b2 < 0 || b2 > 9) {
                    stringBuffer.append((char) ((b2 - 10) + 97));
                } else {
                    stringBuffer.append((char) (b2 + 48));
                }
                b2 = bArr[i] & 15;
                int i3 = i2 + 1;
                if (i2 > 0) {
                    break;
                }
                i2 = i3;
            }
        }
        return stringBuffer.toString();
    }

    public static Document buildDocument(String str) {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            return newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes("UTF-8")));
        } catch (Exception e) {
            TapjoyLog.e("TapjoyUtil", "buildDocument exception: " + e.toString());
            return null;
        }
    }

    public static String getNodeTrimValue(NodeList nodeList) {
        Element element = (Element) nodeList.item(0);
        String str = "";
        if (element == null) {
            return null;
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item != null) {
                str = str + item.getNodeValue();
            }
        }
        if (str == null || str.equals("")) {
            return null;
        }
        return str.trim();
    }

    public static void deleteFileOrDirectory(File file) {
        File[] listFiles;
        if (file != null) {
            if (file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                for (File deleteFileOrDirectory : listFiles) {
                    deleteFileOrDirectory(deleteFileOrDirectory);
                }
            }
            TapjoyLog.d("TapjoyUtil", "****************************************");
            TapjoyLog.d("TapjoyUtil", "deleteFileOrDirectory: " + file.getAbsolutePath());
            TapjoyLog.d("TapjoyUtil", "****************************************");
            file.delete();
        }
    }

    public static long fileOrDirectorySize(File file) {
        long j;
        long j2 = 0;
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                j = file2.length();
            } else {
                j = fileOrDirectorySize(file2);
            }
            j2 += j;
        }
        return j2;
    }

    public static void writeFileToDevice(BufferedInputStream bufferedInputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = null;
        if (view == null || view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
            return null;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getLayoutParams().width, view.getLayoutParams().height, Bitmap.Config.ARGB_8888);
            try {
                Canvas canvas = new Canvas(createBitmap);
                view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                view.draw(canvas);
                return createBitmap;
            } catch (Exception e) {
                e = e;
                bitmap = createBitmap;
                TapjoyLog.d("TapjoyUtil", "error creating bitmap: " + e.toString());
                return bitmap;
            }
        } catch (Exception e2) {
            e = e2;
            TapjoyLog.d("TapjoyUtil", "error creating bitmap: " + e.toString());
            return bitmap;
        }
    }

    public static View scaleDisplayAd(View view, int i) {
        int i2 = view.getLayoutParams().width;
        int i3 = view.getLayoutParams().height;
        TapjoyLog.d("TapjoyUtil", "wxh: " + i2 + AvidJSONUtil.KEY_X + i3);
        if (i2 > i) {
            int intValue = Double.valueOf(Double.valueOf(Double.valueOf((double) i).doubleValue() / Double.valueOf((double) i2).doubleValue()).doubleValue() * 100.0d).intValue();
            WebView webView = (WebView) view;
            webView.getSettings().setSupportZoom(true);
            webView.setPadding(0, 0, 0, 0);
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);
            webView.setInitialScale(intValue);
            view.setLayoutParams(new ViewGroup.LayoutParams(i, (i3 * i) / i2));
        }
        return view;
    }

    public static void safePut(Map map, String str, String str2, boolean z) {
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            if (z) {
                map.put(Uri.encode(str), Uri.encode(str2));
            } else {
                map.put(str, str2);
            }
        }
    }

    public static void safePut(Map map, String str, Number number) {
        if (str != null && str.length() > 0 && number != null) {
            map.put(str, number.toString());
        }
    }

    public static String convertURLParams(Map map, boolean z) {
        String str = "";
        for (Map.Entry entry : map.entrySet()) {
            if (str.length() > 0) {
                str = str + "&";
            }
            if (z) {
                str = str + Uri.encode((String) entry.getKey()) + "=" + Uri.encode((String) entry.getValue());
            } else {
                str = str + ((String) entry.getKey()) + "=" + ((String) entry.getValue());
            }
        }
        return str;
    }

    public static Map convertURLParams(String str, boolean z) {
        HashMap hashMap = new HashMap();
        String str2 = "";
        String str3 = "";
        int i = 0;
        boolean z2 = false;
        while (i < str.length() && i != -1) {
            char charAt = str.charAt(i);
            if (!z2) {
                if (charAt == '=') {
                    if (z) {
                        str2 = Uri.decode(str2);
                    }
                    str3 = str2;
                    str2 = "";
                    z2 = true;
                } else {
                    str2 = str2 + charAt;
                }
            } else if (z2) {
                if (charAt == '&') {
                    if (z) {
                        str2 = Uri.decode(str2);
                    }
                    hashMap.put(str3, str2);
                    str2 = "";
                    z2 = false;
                } else {
                    str2 = str2 + charAt;
                }
            }
            i++;
        }
        if (z2 && str2.length() > 0) {
            if (z) {
                str2 = Uri.decode(str2);
            }
            hashMap.put(str3, str2);
        }
        return hashMap;
    }

    public static String copyTextFromJarIntoString(String str) {
        return copyTextFromJarIntoString(str, (Context) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ac A[SYNTHETIC, Splitter:B:45:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b4 A[SYNTHETIC, Splitter:B:52:0x00b4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap loadBitmapFromJar(java.lang.String r7, android.content.Context r8) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            java.lang.Object r2 = getResource(r7)
            byte[] r2 = (byte[]) r2
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L_0x001c
            int r5 = r2.length
            android.graphics.BitmapFactory.decodeByteArray(r2, r3, r5, r0)
            int r5 = r2.length
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeByteArray(r2, r3, r5)
            goto L_0x001d
        L_0x001c:
            r2 = r4
        L_0x001d:
            if (r2 != 0) goto L_0x00b8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "com/tapjoy/res/"
            r2.<init>(r5)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            java.lang.Class<com.tapjoy.TapjoyUtil> r2 = com.tapjoy.TapjoyUtil.class
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            java.net.URL r2 = r2.getResource(r7)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            if (r2 != 0) goto L_0x0050
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            java.io.InputStream r2 = r8.open(r7)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            android.graphics.BitmapFactory.decodeStream(r2, r4, r0)     // Catch:{ Exception -> 0x004d, all -> 0x0049 }
            java.io.InputStream r7 = r8.open(r7)     // Catch:{ Exception -> 0x004d, all -> 0x0049 }
            goto L_0x008e
        L_0x0049:
            r7 = move-exception
            r4 = r2
            goto L_0x00b2
        L_0x004d:
            r7 = move-exception
            r8 = r2
            goto L_0x00a7
        L_0x0050:
            java.lang.String r8 = r2.getFile()     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            java.lang.String r2 = "jar:"
            boolean r2 = r8.startsWith(r2)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            if (r2 == 0) goto L_0x0061
            r2 = 4
            java.lang.String r8 = r8.substring(r2)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
        L_0x0061:
            java.lang.String r2 = "file:"
            boolean r2 = r8.startsWith(r2)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            if (r2 == 0) goto L_0x006e
            r2 = 5
            java.lang.String r8 = r8.substring(r2)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
        L_0x006e:
            java.lang.String r2 = "!"
            int r2 = r8.indexOf(r2)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            if (r2 <= 0) goto L_0x007a
            java.lang.String r8 = r8.substring(r3, r2)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
        L_0x007a:
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            r2.<init>(r8)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            java.util.jar.JarEntry r7 = r2.getJarEntry(r7)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            java.io.InputStream r8 = r2.getInputStream(r7)     // Catch:{ Exception -> 0x00a5, all -> 0x00a3 }
            android.graphics.BitmapFactory.decodeStream(r8, r4, r0)     // Catch:{ Exception -> 0x00a1 }
            java.io.InputStream r7 = r2.getInputStream(r7)     // Catch:{ Exception -> 0x00a1 }
        L_0x008e:
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r7)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            if (r7 == 0) goto L_0x00b8
            r7.close()     // Catch:{ IOException -> 0x00b8 }
            goto L_0x00b8
        L_0x0098:
            r8 = move-exception
            r4 = r7
            r7 = r8
            goto L_0x00b2
        L_0x009c:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
            goto L_0x00a7
        L_0x00a1:
            r7 = move-exception
            goto L_0x00a7
        L_0x00a3:
            r7 = move-exception
            goto L_0x00b2
        L_0x00a5:
            r7 = move-exception
            r8 = r4
        L_0x00a7:
            r7.printStackTrace()     // Catch:{ all -> 0x00b0 }
            if (r8 == 0) goto L_0x00af
            r8.close()     // Catch:{ IOException -> 0x00af }
        L_0x00af:
            return r4
        L_0x00b0:
            r7 = move-exception
            r4 = r8
        L_0x00b2:
            if (r4 == 0) goto L_0x00b7
            r4.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x00b7:
            throw r7
        L_0x00b8:
            float r7 = com.tapjoy.TapjoyConnectCore.getDeviceScreenDensityScale()
            if (r2 == 0) goto L_0x00ce
            int r8 = r0.outWidth
            float r8 = (float) r8
            float r8 = r8 * r7
            int r8 = (int) r8
            int r0 = r0.outHeight
            float r0 = (float) r0
            float r0 = r0 * r7
            int r7 = (int) r0
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createScaledBitmap(r2, r8, r7, r1)
        L_0x00ce:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyUtil.loadBitmapFromJar(java.lang.String, android.content.Context):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x009f A[SYNTHETIC, Splitter:B:36:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a7 A[SYNTHETIC, Splitter:B:43:0x00a7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String copyTextFromJarIntoString(java.lang.String r6, android.content.Context r7) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.Class<com.tapjoy.TapjoyUtil> r2 = com.tapjoy.TapjoyUtil.class
            java.lang.ClassLoader r2 = r2.getClassLoader()
            java.net.URL r2 = r2.getResource(r6)
            r3 = 0
            r4 = 0
            if (r7 == 0) goto L_0x0022
            if (r2 != 0) goto L_0x0022
            android.content.res.AssetManager r7 = r7.getAssets()     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            java.io.InputStream r6 = r7.open(r6)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            goto L_0x0059
        L_0x0022:
            java.lang.String r7 = r2.getFile()     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            java.lang.String r2 = "jar:"
            boolean r2 = r7.startsWith(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            if (r2 == 0) goto L_0x0033
            r2 = 4
            java.lang.String r7 = r7.substring(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
        L_0x0033:
            java.lang.String r2 = "file:"
            boolean r2 = r7.startsWith(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            if (r2 == 0) goto L_0x0040
            r2 = 5
            java.lang.String r7 = r7.substring(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
        L_0x0040:
            java.lang.String r2 = "!"
            int r2 = r7.indexOf(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            if (r2 <= 0) goto L_0x004c
            java.lang.String r7 = r7.substring(r3, r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
        L_0x004c:
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            r2.<init>(r7)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            java.util.jar.JarEntry r6 = r2.getJarEntry(r6)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            java.io.InputStream r6 = r2.getInputStream(r6)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
        L_0x0059:
            int r7 = r6.read(r0)     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            if (r7 <= 0) goto L_0x006c
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            java.lang.String r7 = r2.substring(r3, r7)     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            r1.append(r7)     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            goto L_0x0059
        L_0x006c:
            java.lang.String r7 = r1.toString()     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            if (r6 == 0) goto L_0x0075
            r6.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            return r7
        L_0x0076:
            r7 = move-exception
            r4 = r6
            r6 = r7
            goto L_0x00a5
        L_0x007a:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
            goto L_0x0083
        L_0x007f:
            r6 = move-exception
            goto L_0x00a5
        L_0x0081:
            r6 = move-exception
            r7 = r4
        L_0x0083:
            java.lang.String r0 = "TapjoyUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = "file exception: "
            r1.<init>(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r6.toString()     // Catch:{ all -> 0x00a3 }
            r1.append(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a3 }
            com.tapjoy.TapjoyLog.d(r0, r1)     // Catch:{ all -> 0x00a3 }
            r6.printStackTrace()     // Catch:{ all -> 0x00a3 }
            if (r7 == 0) goto L_0x00a2
            r7.close()     // Catch:{ Exception -> 0x00a2 }
        L_0x00a2:
            return r4
        L_0x00a3:
            r6 = move-exception
            r4 = r7
        L_0x00a5:
            if (r4 == 0) goto L_0x00aa
            r4.close()     // Catch:{ Exception -> 0x00aa }
        L_0x00aa:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyUtil.copyTextFromJarIntoString(java.lang.String, android.content.Context):java.lang.String");
    }

    public static void setResource(String str, Object obj) {
        b.put(str, obj);
    }

    public static Object getResource(String str) {
        return b.get(str);
    }

    public static String getRedirectDomain(String str) {
        return str != null ? str.substring(str.indexOf("//") + 2, str.lastIndexOf("/")) : "";
    }

    public static String determineMimeType(String str) {
        String str2 = "";
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.lastIndexOf(46) != -1) {
            str2 = str.substring(str.lastIndexOf(46) + 1);
        }
        if (str2.equals("css")) {
            return "text/css";
        }
        if (str2.equals("js")) {
            return "text/javascript";
        }
        return str2.equals(TJAdUnitConstants.String.HTML) ? "text/html" : "application/octet-stream";
    }

    public static Map jsonToStringMap(JSONObject jSONObject) {
        return jSONObject != JSONObject.NULL ? toStringMap(jSONObject) : new HashMap();
    }

    public static Map toStringMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.get(next).toString());
        }
        return hashMap;
    }

    public static void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static String getFileContents(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        while (!z) {
            String readLine = bufferedReader.readLine();
            boolean z2 = readLine == null;
            if (readLine != null) {
                sb.append(readLine);
            }
            z = z2;
        }
        bufferedReader.close();
        fileInputStream.close();
        return sb.toString();
    }
}
