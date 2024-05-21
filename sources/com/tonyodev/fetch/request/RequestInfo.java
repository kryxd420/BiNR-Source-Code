package com.tonyodev.fetch.request;

import android.support.annotation.NonNull;
import java.util.List;

public final class RequestInfo {
    private final long downloadedBytes;
    private final int error;
    private final String filePath;
    private final long fileSize;
    private final List<Header> headers;
    private final long id;
    private final int priority;
    private final int progress;
    private final int status;
    private final String url;

    public RequestInfo(long j, int i, @NonNull String str, @NonNull String str2, int i2, long j2, long j3, int i3, @NonNull List<Header> list, int i4) {
        if (str == null) {
            throw new NullPointerException("Url cannot be null");
        } else if (str2 == null) {
            throw new NullPointerException("FilePath cannot be null");
        } else if (list != null) {
            this.id = j;
            this.status = i;
            this.url = str;
            this.filePath = str2;
            this.progress = i2;
            this.downloadedBytes = j2;
            this.fileSize = j3;
            this.error = i3;
            this.headers = list;
            this.priority = i4;
        } else {
            throw new NullPointerException("Headers cannot be null");
        }
    }

    public long getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    @NonNull
    public String getFilePath() {
        return this.filePath;
    }

    public int getProgress() {
        return this.progress;
    }

    public long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public int getError() {
        return this.error;
    }

    @NonNull
    public List<Header> getHeaders() {
        return this.headers;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Header header : this.headers) {
            sb.append(header.toString());
            sb.append(",");
        }
        if (this.headers.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "{id:" + this.id + ",status:" + this.status + ",url:" + this.url + ",filePath:" + this.filePath + ",progress:" + this.progress + ",fileSize:" + this.fileSize + ",error:" + this.error + ",headers:{" + sb.toString() + "},priority:" + this.priority + "}";
    }
}
