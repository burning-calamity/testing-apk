package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
final class bn extends OutputStream {
    private final cm a = new cm();
    private final File b;
    private final cz c;
    private long d;
    private long e;
    private FileOutputStream f;
    private de g;

    bn(File file, cz czVar) {
        this.b = file;
        this.c = czVar;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        int iMin;
        while (i2 > 0) {
            if (this.d == 0 && this.e == 0) {
                int iA = this.a.a(bArr, i, i2);
                if (iA == -1) {
                    return;
                }
                i += iA;
                i2 -= iA;
                this.g = this.a.b();
                if (this.g.h()) {
                    this.d = 0L;
                    this.c.m(this.g.i(), this.g.i().length);
                    this.e = this.g.i().length;
                } else if (!this.g.c() || this.g.b()) {
                    byte[] bArrI = this.g.i();
                    this.c.m(bArrI, bArrI.length);
                    this.d = this.g.e();
                } else {
                    this.c.g(this.g.i());
                    File file = new File(this.b, this.g.d());
                    file.getParentFile().mkdirs();
                    this.d = this.g.e();
                    this.f = new FileOutputStream(file);
                }
            }
            if (!this.g.b()) {
                if (this.g.h()) {
                    this.c.i(this.e, bArr, i, i2);
                    this.e += (long) i2;
                    iMin = i2;
                } else if (this.g.c()) {
                    iMin = (int) Math.min(i2, this.d);
                    this.f.write(bArr, i, iMin);
                    long j = this.d - ((long) iMin);
                    this.d = j;
                    if (j == 0) {
                        this.f.close();
                    }
                } else {
                    iMin = (int) Math.min(i2, this.d);
                    int length = this.g.i().length;
                    this.c.i((((long) length) + this.g.e()) - this.d, bArr, i, iMin);
                    this.d -= (long) iMin;
                }
                i += iMin;
                i2 -= iMin;
            }
        }
    }
}
