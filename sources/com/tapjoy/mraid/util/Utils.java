package com.tapjoy.mraid.util;

import android.os.Bundle;

public class Utils {
    public static String byteToHex(byte b) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        return new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]});
    }

    public static String convert(String str) {
        try {
            byte[] bytes = str.getBytes();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                if ((bytes[i] & 128) > 0) {
                    stringBuffer.append("%" + byteToHex(bytes[i]));
                } else {
                    stringBuffer.append((char) bytes[i]);
                }
            }
            return new String(stringBuffer.toString().getBytes(), "ISO-8859-1");
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getData(String str, Bundle bundle) {
        return bundle.getString(str);
    }
}
