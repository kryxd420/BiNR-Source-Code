package com.adcolony.sdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ADCGeneratedCrypto {
    private static final byte[] a = {-45, -6, -74, 58, -77, 8, 91, Byte.MIN_VALUE, -97, 10, 95, -80, 60, 27, -69, -13, -11, -25, 110, -57, -9, -38, -122, -36, 101, -119, 41, -86, 47, 85, 125, -75};
    private static final byte[] b = {40, -40, -46, -26, -42, -70, 119, 81, -118, 58, 66, 116, -102, 59, -43, -125, 14, 23, -51, 112, -8, -112, -124, -89};

    private native byte[] nativeDecryptBase64(byte[] bArr, byte[] bArr2, byte[] bArr3, int i);

    public static byte[] decrypt(byte[] bArr) {
        try {
            ADCGeneratedCrypto aDCGeneratedCrypto = new ADCGeneratedCrypto();
            byte[] digest = MessageDigest.getInstance("SHA-512").digest(aDCGeneratedCrypto.getClass().getSimpleName().getBytes());
            byte[] bArr2 = new byte[a.length];
            byte[] bArr3 = new byte[b.length];
            for (int i = 0; i < bArr2.length; i++) {
                bArr2[i] = (byte) (a[i] ^ digest[i]);
            }
            for (int i2 = 0; i2 < bArr3.length; i2++) {
                bArr3[i2] = (byte) (b[i2] ^ digest[i2]);
            }
            if (!new String(bArr, 0, 10).equals("adc_base64")) {
                return null;
            }
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 10, bArr.length);
            return aDCGeneratedCrypto.nativeDecryptBase64(bArr2, bArr3, copyOfRange, copyOfRange.length);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
