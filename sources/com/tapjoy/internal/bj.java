package com.tapjoy.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

public final class bj {
    public static String a(File file, Charset charset) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return kc.a(new InputStreamReader(fileInputStream, charset));
        } finally {
            kd.a(fileInputStream);
        }
    }

    public static void a(File file, String str) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            a((OutputStream) fileOutputStream, str);
        } finally {
            kd.a(fileOutputStream);
        }
    }

    public static void a(OutputStream outputStream, String str) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, an.c);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }

    @Nullable
    public static String a(File file) {
        try {
            return a(file, an.c);
        } catch (IOException unused) {
            return null;
        }
    }
}
