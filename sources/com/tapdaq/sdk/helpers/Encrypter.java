package com.tapdaq.sdk.helpers;

import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public String SHA1(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            return bytesToHex(instance.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            TLog.error(e);
            return null;
        }
    }

    private static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            cArr[i2] = hexArray[b >>> 4];
            cArr[i2 + 1] = hexArray[b & 15];
        }
        return new String(cArr);
    }

    public String Base64(String str) throws IOException {
        return Base64.encodeToString(str.getBytes("UTF-8"), 2);
    }
}
