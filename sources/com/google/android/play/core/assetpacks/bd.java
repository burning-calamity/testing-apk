package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class bd extends FilterInputStream {
    private final cm a;
    private byte[] b;
    private long c;
    private boolean d;
    private boolean e;

    bd(InputStream inputStream) {
        super(inputStream);
        this.a = new cm();
        this.b = new byte[4096];
        this.d = false;
        this.e = false;
    }

    private final boolean e(int i) throws IOException {
        int iF = f(this.b, 0, i);
        if (iF != i) {
            int i2 = i - iF;
            if (f(this.b, iF, i2) != i2) {
                this.a.a(this.b, 0, iF);
                return false;
            }
        }
        this.a.a(this.b, 0, i);
        return true;
    }

    private final int f(byte[] bArr, int i, int i2) throws IOException {
        return Math.max(0, super.read(bArr, i, i2));
    }

    final de a() throws IOException {
        byte[] bArr;
        if (this.c > 0) {
            do {
                bArr = this.b;
            } while (read(bArr, 0, bArr.length) != -1);
        }
        if (this.d || this.e) {
            return new de(null, -1L, -1, false, false, null);
        }
        if (!e(30)) {
            this.d = true;
            return this.a.b();
        }
        de deVarB = this.a.b();
        if (deVarB.h()) {
            this.e = true;
            return deVarB;
        }
        if (deVarB.e() == 4294967295L) {
            throw new bk("Files bigger than 4GiB are not supported.");
        }
        int iC = this.a.c() - 30;
        long j = iC;
        int length = this.b.length;
        if (j > length) {
            do {
                length += length;
            } while (length < j);
            this.b = Arrays.copyOf(this.b, length);
        }
        if (!e(iC)) {
            this.d = true;
            return this.a.b();
        }
        de deVarB2 = this.a.b();
        this.c = deVarB2.e();
        return deVarB2;
    }

    final boolean b() {
        return this.d;
    }

    final boolean c() {
        return this.e;
    }

    final long d() {
        return this.c;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.c;
        if (j <= 0 || this.d) {
            return -1;
        }
        int iF = f(bArr, i, (int) Math.min(j, i2));
        this.c -= (long) iF;
        if (iF != 0) {
            return iF;
        }
        this.d = true;
        return 0;
    }
}
