package okhttp3.logging;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;

public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() {
            public void log(String str) {
                Platform.get().log(4, str, (Throwable) null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger2) {
        this.level = Level.NONE;
        this.logger = logger2;
    }

    public HttpLoggingInterceptor setLevel(Level level2) {
        if (level2 != null) {
            this.level = level2;
            return this;
        }
        throw new NullPointerException("level == null. Use Level.NONE instead.");
    }

    public Level getLevel() {
        return this.level;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        String str;
        String str2;
        Interceptor.Chain chain2 = chain;
        Level level2 = this.level;
        Request request = chain.request();
        if (level2 == Level.NONE) {
            return chain2.proceed(request);
        }
        boolean z = true;
        boolean z2 = level2 == Level.BODY;
        boolean z3 = z2 || level2 == Level.HEADERS;
        RequestBody body = request.body();
        if (body == null) {
            z = false;
        }
        Connection connection = chain.connection();
        String str3 = "--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1);
        if (!z3 && z) {
            str3 = str3 + " (" + body.contentLength() + "-byte body)";
        }
        this.logger.log(str3);
        if (z3) {
            if (z) {
                if (body.contentType() != null) {
                    this.logger.log("Content-Type: " + body.contentType());
                }
                if (body.contentLength() != -1) {
                    this.logger.log("Content-Length: " + body.contentLength());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    this.logger.log(name + ": " + headers.value(i));
                }
            }
            if (!z2 || !z) {
                this.logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                this.logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                this.logger.log("");
                if (isPlaintext(buffer)) {
                    this.logger.log(buffer.readString(charset));
                    this.logger.log("--> END " + request.method() + " (" + body.contentLength() + "-byte body)");
                } else {
                    this.logger.log("--> END " + request.method() + " (binary " + body.contentLength() + "-byte body omitted)");
                }
            }
        }
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain2.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body2 = proceed.body();
            long contentLength = body2.contentLength();
            if (contentLength != -1) {
                str = contentLength + "-byte";
            } else {
                str = "unknown-length";
            }
            Logger logger2 = this.logger;
            StringBuilder sb = new StringBuilder();
            sb.append("<-- ");
            sb.append(proceed.code());
            sb.append(' ');
            sb.append(proceed.message());
            sb.append(' ');
            sb.append(proceed.request().url());
            sb.append(" (");
            sb.append(millis);
            sb.append("ms");
            if (!z3) {
                str2 = ", " + str + " body";
            } else {
                str2 = "";
            }
            sb.append(str2);
            sb.append(')');
            logger2.log(sb.toString());
            if (z3) {
                Headers headers2 = proceed.headers();
                int size2 = headers2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    this.logger.log(headers2.name(i2) + ": " + headers2.value(i2));
                }
                if (!z2 || !HttpHeaders.hasBody(proceed)) {
                    this.logger.log("<-- END HTTP");
                } else if (bodyEncoded(proceed.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource source = body2.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer2 = source.buffer();
                    Charset charset2 = UTF8;
                    MediaType contentType2 = body2.contentType();
                    if (contentType2 != null) {
                        charset2 = contentType2.charset(UTF8);
                    }
                    if (!isPlaintext(buffer2)) {
                        this.logger.log("");
                        this.logger.log("<-- END HTTP (binary " + buffer2.size() + "-byte body omitted)");
                        return proceed;
                    }
                    if (contentLength != 0) {
                        this.logger.log("");
                        this.logger.log(buffer2.clone().readString(charset2));
                    }
                    this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                }
            }
            return proceed;
        } catch (Exception e) {
            Exception exc = e;
            this.logger.log("<-- HTTP FAILED: " + exc);
            throw exc;
        }
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0, buffer.size() < 64 ? buffer.size() : 64);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String str = headers.get("Content-Encoding");
        return str != null && !str.equalsIgnoreCase("identity");
    }
}
