package com.adcolony.sdk;

import java.io.IOException;
import java.io.InputStream;

class as extends InputStream {
    InputStream a;
    int b;

    as(InputStream inputStream, int i, int i2) throws IOException {
        this.a = inputStream;
        while (i > 0) {
            i -= (int) inputStream.skip((long) i);
        }
        this.b = i2;
    }

    public int available() throws IOException {
        int available = this.a.available();
        if (available <= this.b) {
            return available;
        }
        return this.b;
    }

    public void close() throws IOException {
        this.a.close();
    }

    public int read() throws IOException {
        if (this.b == 0) {
            return -1;
        }
        this.b--;
        return this.a.read();
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.b == 0) {
            return -1;
        }
        if (i2 > this.b) {
            i2 = this.b;
        }
        int read = this.a.read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        this.b -= read;
        return read;
    }

    public long skip(long j) throws IOException {
        int i = (int) j;
        if (i <= 0) {
            return 0;
        }
        if (i > this.b) {
            i = this.b;
        }
        this.b -= i;
        while (i > 0) {
            i -= (int) this.a.skip(j);
        }
        return j;
    }
}
