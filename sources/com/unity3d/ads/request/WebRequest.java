package com.unity3d.ads.request;

import com.unity3d.ads.log.DeviceLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class WebRequest {
    private String _body;
    private boolean _canceled;
    private int _connectTimeout;
    private long _contentLength;
    private Map<String, List<String>> _headers;
    private IWebRequestProgressListener _progressListener;
    private int _readTimeout;
    private String _requestType;
    private int _responseCode;
    private Map<String, List<String>> _responseHeaders;
    private URL _url;

    public enum RequestType {
        POST,
        GET,
        HEAD
    }

    public WebRequest(String str, String str2, Map<String, List<String>> map) throws MalformedURLException {
        this(str, str2, map, 30000, 30000);
    }

    public WebRequest(String str, String str2, Map<String, List<String>> map, int i, int i2) throws MalformedURLException {
        this._requestType = RequestType.GET.name();
        this._responseCode = -1;
        this._contentLength = -1;
        this._canceled = false;
        this._url = new URL(str);
        this._requestType = str2;
        this._headers = map;
        this._connectTimeout = i;
        this._readTimeout = i2;
    }

    public void cancel() {
        this._canceled = true;
    }

    public boolean isCanceled() {
        return this._canceled;
    }

    public URL getUrl() {
        return this._url;
    }

    public String getRequestType() {
        return this._requestType;
    }

    public String getBody() {
        return this._body;
    }

    public void setBody(String str) {
        this._body = str;
    }

    public String getQuery() {
        if (this._url != null) {
            return this._url.getQuery();
        }
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this._responseHeaders;
    }

    public Map<String, List<String>> getHeaders() {
        return this._headers;
    }

    public int getResponseCode() {
        return this._responseCode;
    }

    public long getContentLength() {
        return this._contentLength;
    }

    public int getConnectTimeout() {
        return this._connectTimeout;
    }

    public void setConnectTimeout(int i) {
        this._connectTimeout = i;
    }

    public int getReadTimeout() {
        return this._readTimeout;
    }

    public void setReadTimeout(int i) {
        this._readTimeout = i;
    }

    public void setProgressListener(IWebRequestProgressListener iWebRequestProgressListener) {
        this._progressListener = iWebRequestProgressListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d A[SYNTHETIC, Splitter:B:27:0x007d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long makeStreamRequest(java.io.OutputStream r18) throws com.unity3d.ads.request.NetworkIOException, java.io.IOException, java.lang.IllegalStateException {
        /*
            r17 = this;
            r1 = r17
            java.net.HttpURLConnection r2 = r17.getHttpUrlConnectionWithHeaders()
            r0 = 1
            r2.setDoInput(r0)
            java.lang.String r3 = r17.getRequestType()
            com.unity3d.ads.request.WebRequest$RequestType r4 = com.unity3d.ads.request.WebRequest.RequestType.POST
            java.lang.String r4 = r4.name()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x008a
            r2.setDoOutput(r0)
            r3 = 0
            java.io.PrintWriter r4 = new java.io.PrintWriter     // Catch:{ IOException -> 0x005a }
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x005a }
            java.io.OutputStream r6 = r2.getOutputStream()     // Catch:{ IOException -> 0x005a }
            java.lang.String r7 = "UTF-8"
            r5.<init>(r6, r7)     // Catch:{ IOException -> 0x005a }
            r4.<init>(r5, r0)     // Catch:{ IOException -> 0x005a }
            java.lang.String r0 = r17.getBody()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            if (r0 != 0) goto L_0x003c
            java.lang.String r0 = r17.getQuery()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r4.print(r0)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            goto L_0x0043
        L_0x003c:
            java.lang.String r0 = r17.getBody()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r4.print(r0)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
        L_0x0043:
            r4.flush()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r4.close()     // Catch:{ Exception -> 0x004a }
            goto L_0x008a
        L_0x004a:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = "Error closing writer"
            com.unity3d.ads.log.DeviceLog.exception(r0, r2)
            throw r2
        L_0x0052:
            r0 = move-exception
            r3 = r4
            goto L_0x007b
        L_0x0055:
            r0 = move-exception
            r3 = r4
            goto L_0x005b
        L_0x0058:
            r0 = move-exception
            goto L_0x007b
        L_0x005a:
            r0 = move-exception
        L_0x005b:
            java.lang.String r2 = "Error while writing POST params"
            com.unity3d.ads.log.DeviceLog.exception(r2, r0)     // Catch:{ all -> 0x0058 }
            com.unity3d.ads.request.NetworkIOException r2 = new com.unity3d.ads.request.NetworkIOException     // Catch:{ all -> 0x0058 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0058 }
            r4.<init>()     // Catch:{ all -> 0x0058 }
            java.lang.String r5 = "Error writing POST params: "
            r4.append(r5)     // Catch:{ all -> 0x0058 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0058 }
            r4.append(r0)     // Catch:{ all -> 0x0058 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0058 }
            r2.<init>(r0)     // Catch:{ all -> 0x0058 }
            throw r2     // Catch:{ all -> 0x0058 }
        L_0x007b:
            if (r3 == 0) goto L_0x0089
            r3.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x0089
        L_0x0081:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = "Error closing writer"
            com.unity3d.ads.log.DeviceLog.exception(r0, r2)
            throw r2
        L_0x0089:
            throw r0
        L_0x008a:
            int r0 = r2.getResponseCode()     // Catch:{ IOException | RuntimeException -> 0x0145 }
            r1._responseCode = r0     // Catch:{ IOException | RuntimeException -> 0x0145 }
            int r0 = r2.getContentLength()
            long r3 = (long) r0
            r1._contentLength = r3
            java.util.Map r0 = r2.getHeaderFields()
            if (r0 == 0) goto L_0x00a3
            java.util.Map r0 = r2.getHeaderFields()
            r1._responseHeaders = r0
        L_0x00a3:
            java.io.InputStream r0 = r2.getInputStream()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00b0
        L_0x00a8:
            r0 = move-exception
            r3 = r0
            java.io.InputStream r0 = r2.getErrorStream()
            if (r0 == 0) goto L_0x012a
        L_0x00b0:
            com.unity3d.ads.request.IWebRequestProgressListener r3 = r1._progressListener
            if (r3 == 0) goto L_0x00c7
            com.unity3d.ads.request.IWebRequestProgressListener r4 = r1._progressListener
            java.net.URL r3 = r17.getUrl()
            java.lang.String r5 = r3.toString()
            long r6 = r1._contentLength
            int r8 = r1._responseCode
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r9 = r1._responseHeaders
            r4.onRequestStart(r5, r6, r8, r9)
        L_0x00c7:
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream
            r3.<init>(r0)
            r4 = 0
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]
            r6 = 0
            r7 = r4
            r4 = 0
        L_0x00d5:
            boolean r5 = r17.isCanceled()
            if (r5 != 0) goto L_0x0121
            r5 = -1
            if (r4 == r5) goto L_0x0121
            int r4 = r3.read(r0)     // Catch:{ IOException -> 0x0104 }
            if (r4 <= 0) goto L_0x0101
            r5 = r18
            r5.write(r0, r6, r4)
            long r9 = (long) r4
            long r7 = r7 + r9
            com.unity3d.ads.request.IWebRequestProgressListener r9 = r1._progressListener
            if (r9 == 0) goto L_0x00d5
            com.unity3d.ads.request.IWebRequestProgressListener r11 = r1._progressListener
            java.net.URL r9 = r17.getUrl()
            java.lang.String r12 = r9.toString()
            long r9 = r1._contentLength
            r13 = r7
            r15 = r9
            r11.onRequestProgress(r12, r13, r15)
            goto L_0x00d5
        L_0x0101:
            r5 = r18
            goto L_0x00d5
        L_0x0104:
            r0 = move-exception
            r2 = r0
            com.unity3d.ads.request.NetworkIOException r0 = new com.unity3d.ads.request.NetworkIOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Network exception: "
            r3.append(r4)
            java.lang.String r2 = r2.getMessage()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.<init>(r2)
            throw r0
        L_0x0121:
            r5 = r18
            r2.disconnect()
            r18.flush()
            return r7
        L_0x012a:
            com.unity3d.ads.request.NetworkIOException r0 = new com.unity3d.ads.request.NetworkIOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Can't open error stream: "
            r2.append(r4)
            java.lang.String r3 = r3.getMessage()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0145:
            r0 = move-exception
            com.unity3d.ads.request.NetworkIOException r2 = new com.unity3d.ads.request.NetworkIOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Response code: "
            r3.append(r4)
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.request.WebRequest.makeStreamRequest(java.io.OutputStream):long");
    }

    public String makeRequest() throws NetworkIOException, IOException, IllegalStateException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        makeStreamRequest(byteArrayOutputStream);
        return byteArrayOutputStream.toString("UTF-8");
    }

    private HttpURLConnection getHttpUrlConnectionWithHeaders() throws NetworkIOException {
        HttpURLConnection httpURLConnection;
        if (getUrl().toString().startsWith("https://")) {
            try {
                httpURLConnection = (HttpsURLConnection) getUrl().openConnection();
            } catch (IOException e) {
                throw new NetworkIOException("Open HTTPS connection: " + e.getMessage());
            }
        } else {
            try {
                httpURLConnection = (HttpURLConnection) getUrl().openConnection();
            } catch (IOException e2) {
                throw new NetworkIOException("Open HTTP connection: " + e2.getMessage());
            }
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(getConnectTimeout());
        httpURLConnection.setReadTimeout(getReadTimeout());
        try {
            httpURLConnection.setRequestMethod(getRequestType());
            if (getHeaders() != null && getHeaders().size() > 0) {
                for (String next : getHeaders().keySet()) {
                    for (String str : getHeaders().get(next)) {
                        DeviceLog.debug("Setting header: " + next + "=" + str);
                        httpURLConnection.setRequestProperty(next, str);
                    }
                }
            }
            return httpURLConnection;
        } catch (ProtocolException e3) {
            throw new NetworkIOException("Set Request Method: " + getRequestType() + ", " + e3.getMessage());
        }
    }
}
