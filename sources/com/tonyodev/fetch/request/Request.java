package com.tonyodev.fetch.request;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Request {
    private final String filePath;
    private final Map<String, String> headers;
    private int priority;
    private final String url;

    public Request(@NonNull String str) {
        this(str, generateFileName(str));
    }

    public Request(@NonNull String str, @NonNull String str2) {
        this(str, generateDirectoryName(), str2);
    }

    public Request(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.headers = new ArrayMap();
        this.priority = FetchConst.PRIORITY_NORMAL;
        if (str == null || str.isEmpty()) {
            throw new NullPointerException("Url cannot be null or empty");
        } else if (str2 == null || str2.isEmpty()) {
            throw new NullPointerException("directory path cannot be null or empty");
        } else if (str3 == null || str3.isEmpty()) {
            throw new NullPointerException("File Name cannot be null or empty");
        } else {
            String scheme = Uri.parse(str).getScheme();
            if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
                throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + str);
            }
            this.url = str;
            this.filePath = cleanFilePath(generateFilePath(str2, str3));
        }
    }

    @NonNull
    public Request addHeader(@NonNull String str, @Nullable String str2) {
        return addHeader(new Header(str, str2));
    }

    @NonNull
    public Request addHeader(@NonNull Header header) {
        if (header != null) {
            this.headers.put(header.getHeader(), header.getValue());
            return this;
        }
        throw new NullPointerException("Header cannot be null");
    }

    @NonNull
    public Request setPriority(int i) {
        this.priority = FetchConst.PRIORITY_NORMAL;
        if (i == 601) {
            this.priority = FetchConst.PRIORITY_HIGH;
        }
        return this;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    @NonNull
    public String getFilePath() {
        return this.filePath;
    }

    @NonNull
    public List<Header> getHeaders() {
        ArrayList arrayList = new ArrayList(this.headers.size());
        for (String next : this.headers.keySet()) {
            arrayList.add(new Header(next, this.headers.get(next)));
        }
        return arrayList;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Header header : getHeaders()) {
            sb.append(header.toString());
            sb.append(",");
        }
        if (this.headers.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "{url:" + this.url + " ,filePath:" + this.filePath + ",headers:{" + sb.toString() + "},priority:" + this.priority + "}";
    }

    private static String generateFileName(String str) {
        if (str != null) {
            return new Date().getTime() + "_" + Uri.parse(str).getLastPathSegment();
        }
        throw new NullPointerException("Url cannot be null");
    }

    private static String generateDirectoryName() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    }

    private static String generateFilePath(String str, String str2) {
        if (Uri.parse(str2).getPathSegments().size() != 1) {
            return str2;
        }
        return str + "/" + str2;
    }

    private static String cleanFilePath(String str) {
        return str.replace("//", "/");
    }
}
