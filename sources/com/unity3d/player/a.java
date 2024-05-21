package com.unity3d.player;

import android.os.Build;
import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public final class a extends SSLSocketFactory {
    private static volatile SSLSocketFactory c;
    private static final Object[] d = new Object[0];
    private static final boolean e;
    private final SSLSocketFactory a;
    private final C0009a b = new C0009a();

    /* renamed from: com.unity3d.player.a$a  reason: collision with other inner class name */
    class C0009a implements HandshakeCompletedListener {
        C0009a() {
        }

        public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            SSLSession session = handshakeCompletedEvent.getSession();
            session.getCipherSuite();
            session.getProtocol();
            try {
                session.getPeerPrincipal().getName();
            } catch (SSLPeerUnverifiedException unused) {
            }
        }
    }

    static {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 20) {
            z = true;
        }
        e = z;
    }

    private a() {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
        this.a = instance.getSocketFactory();
    }

    private static Socket a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket) && e) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        }
        return socket;
    }

    public static SSLSocketFactory a() {
        synchronized (d) {
            if (c != null) {
                SSLSocketFactory sSLSocketFactory = c;
                return sSLSocketFactory;
            }
            try {
                a aVar = new a();
                c = aVar;
                return aVar;
            } catch (Exception e2) {
                e.Log(5, "CustomSSLSocketFactory: Failed to create SSLSocketFactory (" + e2.getMessage() + ")");
                return null;
            }
        }
    }

    public final Socket createSocket() {
        return a(this.a.createSocket());
    }

    public final Socket createSocket(String str, int i) {
        return a(this.a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return a(this.a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return a(this.a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
