package com.tonyodev.fetch;

import android.os.Handler;
import android.os.Looper;
import com.tapdaq.sdk.TapdaqError;
import com.tonyodev.fetch.callback.FetchCall;
import com.tonyodev.fetch.exception.DownloadInterruptedException;
import com.tonyodev.fetch.request.Header;
import com.tonyodev.fetch.request.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

final class FetchCallRunnable implements Runnable {
    private BufferedReader bufferedReader;
    private final Callback callback;
    /* access modifiers changed from: private */
    public final FetchCall<String> fetchCall;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private HttpURLConnection httpURLConnection;
    private InputStream input;
    private volatile boolean interrupted = false;
    /* access modifiers changed from: private */
    public final Request request;
    /* access modifiers changed from: private */
    public String response;

    interface Callback {
        void onDone(Request request);
    }

    FetchCallRunnable(Request request2, FetchCall<String> fetchCall2, Callback callback2) {
        if (request2 == null) {
            throw new NullPointerException("Request Cannot be null");
        } else if (fetchCall2 == null) {
            throw new NullPointerException("FetchCall cannot be null");
        } else if (callback2 != null) {
            this.request = request2;
            this.fetchCall = fetchCall2;
            this.callback = callback2;
        } else {
            throw new NullPointerException("Callback cannot be null");
        }
    }

    public void run() {
        try {
            setHttpConnectionPrefs();
            this.httpURLConnection.connect();
            int responseCode = this.httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new IllegalStateException("SSRV:" + responseCode);
            } else if (!isInterrupted()) {
                this.input = this.httpURLConnection.getInputStream();
                this.response = inputToString();
                if (!isInterrupted()) {
                    this.handler.post(new Runnable() {
                        public void run() {
                            FetchCallRunnable.this.fetchCall.onSuccess(FetchCallRunnable.this.response, FetchCallRunnable.this.request);
                        }
                    });
                }
                release();
                this.callback.onDone(this.request);
            } else {
                throw new DownloadInterruptedException("DIE", -118);
            }
        } catch (Exception e) {
            e.printStackTrace();
            final int code = ErrorUtils.getCode(e.getMessage());
            if (!isInterrupted()) {
                this.handler.post(new Runnable() {
                    public void run() {
                        FetchCallRunnable.this.fetchCall.onError(code, FetchCallRunnable.this.request);
                    }
                });
            }
        } catch (Throwable th) {
            release();
            this.callback.onDone(this.request);
            throw th;
        }
    }

    private void setHttpConnectionPrefs() throws IOException {
        this.httpURLConnection = (HttpURLConnection) new URL(this.request.getUrl()).openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.setReadTimeout(TapdaqError.HYPRMX_FAILED_TO_LOAD_AD);
        this.httpURLConnection.setConnectTimeout(TapdaqError.ADCOLONY_FAILED_TO_LOAD_AD);
        this.httpURLConnection.setUseCaches(true);
        this.httpURLConnection.setDefaultUseCaches(true);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (Header next : this.request.getHeaders()) {
            this.httpURLConnection.addRequestProperty(next.getHeader(), next.getValue());
        }
    }

    private String inputToString() throws IOException {
        StringBuilder sb = new StringBuilder();
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.input));
        while (true) {
            String readLine = this.bufferedReader.readLine();
            if (readLine != null && !isInterrupted()) {
                sb.append(readLine);
            }
        }
        if (isInterrupted()) {
            return null;
        }
        return sb.toString();
    }

    private void release() {
        try {
            if (this.input != null) {
                this.input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (this.bufferedReader != null) {
                this.bufferedReader.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (this.httpURLConnection != null) {
            this.httpURLConnection.disconnect();
        }
    }

    private boolean isInterrupted() {
        return this.interrupted;
    }

    /* access modifiers changed from: package-private */
    public synchronized void interrupt() {
        this.interrupted = true;
    }

    public Request getRequest() {
        return this.request;
    }
}
