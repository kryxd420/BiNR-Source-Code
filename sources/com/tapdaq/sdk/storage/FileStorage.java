package com.tapdaq.sdk.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tapdaq.sdk.helpers.TDDate;
import com.tapdaq.sdk.helpers.TDMemoryInfo;
import com.tapdaq.sdk.helpers.TLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileStorage implements FileStorageBase {
    public static final String EXT_FILE_DIRECTORY = "Android/data";
    private Context mContext;
    private Map<String, Date> mLastAccessed = new HashMap();

    public FileStorage(Context context) {
        this.mContext = context;
        initialise();
    }

    private void initialise() {
        try {
            Map<String, Date> map = (Map) new Gson().fromJson(loadFile("fileAccess", ""), new TypeToken<Map<String, Date>>() {
            }.getType());
            if (map != null) {
                this.mLastAccessed = map;
            }
            clear();
        } catch (Exception e) {
            TLog.error(e);
        }
    }

    private void update(String str) {
        if (!str.equalsIgnoreCase("fileAccess")) {
            saveFile("fileAccess", "", new Gson().toJson((Object) this.mLastAccessed), false);
        }
    }

    public void clear() {
        Date date = new Date();
        date.setTime(date.getTime() - TDDate.getDaysInMilliseconds(7));
        HashMap hashMap = new HashMap(this.mLastAccessed);
        for (String next : this.mLastAccessed.keySet()) {
            if (((Date) hashMap.get(next)).getTime() < date.getTime()) {
                deleteFile(next, "");
                hashMap.remove(next);
            }
        }
        this.mLastAccessed = hashMap;
    }

    public boolean exists(String str, String str2) {
        return getFile(str, str2).exists();
    }

    public String getPath(String str, String str2) {
        File file;
        if (!canUseExternal() || Build.VERSION.SDK_INT <= 23 || this.mContext.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            file = new File(this.mContext.getFilesDir() + "/" + str2);
        } else {
            String file2 = Environment.getExternalStorageDirectory().toString();
            file = new File(String.format(Locale.ENGLISH, "%s/%s/%s/files/%s", new Object[]{file2, EXT_FILE_DIRECTORY, this.mContext.getPackageName(), str2}));
        }
        return file.getPath();
    }

    private File getFile(String str, String str2) {
        File file;
        if (!canUseExternal() || Build.VERSION.SDK_INT <= 23 || this.mContext.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            file = new File(this.mContext.getFilesDir() + "/" + str2);
        } else {
            String file2 = Environment.getExternalStorageDirectory().toString();
            file = new File(String.format(Locale.ENGLISH, "%s/%s/%s/files/%s", new Object[]{file2, EXT_FILE_DIRECTORY, this.mContext.getPackageName(), str2}));
        }
        file.mkdirs();
        return new File(file, str);
    }

    public void saveFileAsync(String str, String str2, String str3, boolean z) {
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        new Thread(new Runnable() {
            public void run() {
                FileStorage.this.saveFile(str4, str5, str6, z2);
            }
        }).start();
    }

    public void saveFile(String str, String str2, String str3, boolean z) {
        Date date;
        Map<String, Date> map;
        String str4;
        File file = getFile(str, str2);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str3.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            if (z) {
                map = this.mLastAccessed;
                str4 = file.getPath();
                date = new Date();
                map.put(str4, date);
                update(str);
            }
        } catch (Exception e) {
            TLog.error(e);
            if (z) {
                map = this.mLastAccessed;
                str4 = file.getPath();
                date = new Date();
            }
        } catch (Throwable th) {
            if (z) {
                this.mLastAccessed.put(file.getPath(), new Date());
                update(str);
            }
            throw th;
        }
    }

    public void saveFile(String str, byte[] bArr, String str2, boolean z) {
        Date date;
        String str3;
        Map<String, Date> map;
        File file = getFile(str, str2);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (z) {
                map = this.mLastAccessed;
                str3 = file.getPath();
                date = new Date();
                map.put(str3, date);
                update(str);
            }
        } catch (Exception e) {
            TLog.error(e);
            if (z) {
                map = this.mLastAccessed;
                str3 = file.getPath();
                date = new Date();
            }
        } catch (Throwable th) {
            if (z) {
                this.mLastAccessed.put(file.getPath(), new Date());
                update(str);
            }
            throw th;
        }
    }

    public void saveImage(String str, Bitmap bitmap, boolean z) {
        String str2;
        Map<String, Date> map;
        Date date;
        if (bitmap != null) {
            File file = getFile(str, "images");
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (str.contains(".png")) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                if (z) {
                    map = this.mLastAccessed;
                    str2 = file.getPath();
                    date = new Date();
                    map.put(str2, date);
                    update(str);
                }
            } catch (Exception e) {
                TLog.error(e);
                if (z) {
                    map = this.mLastAccessed;
                    str2 = file.getPath();
                    date = new Date();
                }
            } catch (Throwable th) {
                if (z) {
                    this.mLastAccessed.put(file.getPath(), new Date());
                    update(str);
                }
                throw th;
            }
        } else {
            TLog.debug("SaveImage bitmap null");
        }
    }

    public String loadFile(String str, String str2) {
        File file = getFile(str, str2);
        if (!file.exists()) {
            return null;
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            inputStreamReader.close();
            if (this.mLastAccessed.containsKey(file.getPath())) {
                this.mLastAccessed.put(file.getPath(), new Date());
                update(str);
            }
            return sb.toString();
        } catch (IOException e) {
            TLog.error((Exception) e);
            return null;
        }
    }

    public Bitmap loadImage(String str) {
        File file = getFile(str, "images");
        Bitmap bitmap = null;
        if (file.exists()) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inJustDecodeBounds = true;
                long GetAvailableMemory = TDMemoryInfo.GetAvailableMemory(this.mContext);
                long DecodedImageSizeInBytes = ((long) TDMemoryInfo.DecodedImageSizeInBytes(new FileInputStream(file), options)) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                while (DecodedImageSizeInBytes > GetAvailableMemory && options.inSampleSize < 4) {
                    if (options.inSampleSize < 1) {
                        options.inSampleSize = 1;
                    } else {
                        options.inSampleSize *= 2;
                    }
                    TLog.debug(String.format(Locale.ENGLISH, "Decoding Image with Sample Size: %s", new Object[]{Integer.valueOf(options.inSampleSize)}));
                    long DecodedImageSizeInBytes2 = (long) TDMemoryInfo.DecodedImageSizeInBytes(new FileInputStream(file), options);
                    if (GetAvailableMemory <= DecodedImageSizeInBytes2) {
                        TLog.warning(String.format(Locale.ENGLISH, "Not enough memory to load image. %f available. %d required", new Object[]{Long.valueOf(GetAvailableMemory), Long.valueOf(DecodedImageSizeInBytes2)}));
                    }
                    DecodedImageSizeInBytes = DecodedImageSizeInBytes2;
                }
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeFile(file.getPath(), options);
            } catch (IOException e) {
                TLog.error((Exception) e);
            }
            if (this.mLastAccessed.containsKey(file.getPath())) {
                this.mLastAccessed.put(file.getPath(), new Date());
                update(str);
            }
        }
        return bitmap;
    }

    public void deleteFile(String str, String str2) {
        File file = getFile(str, str2);
        if (file.exists()) {
            file.delete();
        }
        this.mLastAccessed.remove(file.getPath());
        update(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean canUseExternal() {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            java.lang.String r2 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x001b }
            java.lang.String r3 = "mounted"
            boolean r3 = r3.equals(r2)     // Catch:{ Exception -> 0x001b }
            if (r3 == 0) goto L_0x0011
            r2 = 1
            r3 = 1
            goto L_0x0021
        L_0x0011:
            java.lang.String r3 = "mounted_ro"
            boolean r2 = r3.equals(r2)     // Catch:{ Exception -> 0x001b }
            if (r2 == 0) goto L_0x001f
            r2 = 1
            goto L_0x0020
        L_0x001b:
            r2 = move-exception
            r2.printStackTrace()
        L_0x001f:
            r2 = 0
        L_0x0020:
            r3 = 0
        L_0x0021:
            if (r2 == 0) goto L_0x0026
            if (r3 == 0) goto L_0x0026
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapdaq.sdk.storage.FileStorage.canUseExternal():boolean");
    }
}
