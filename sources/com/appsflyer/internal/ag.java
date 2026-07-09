package com.appsflyer.internal;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ag extends FilterInputStream {

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static final short f244 = (short) ((Math.sqrt(5.0d) - 1.0d) * Math.pow(2.0d, 15.0d));

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private byte[] f245;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private int f246;

    /* JADX INFO: renamed from: ȷ, reason: contains not printable characters */
    private int f247;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private byte[] f248;

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    private int f249;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private int f250;

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private int f251;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private byte[] f252;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private int f253;

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private int f254;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private int f255;

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private int f256;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public ag(InputStream inputStream, int[] iArr, int i, byte[] bArr, int i2, int i3) throws IOException {
        super(inputStream);
        this.f246 = Integer.MAX_VALUE;
        this.f252 = new byte[8];
        this.f245 = new byte[8];
        this.f248 = new byte[8];
        this.f253 = 8;
        this.f250 = 8;
        this.f255 = Math.min(Math.max(i2, 5), 16);
        this.f256 = i3;
        if (i3 == 3) {
            System.arraycopy(bArr, 0, this.f245, 0, 8);
        }
        long j = ((((long) iArr[0]) & 4294967295L) << 32) | (4294967295L & ((long) iArr[1]));
        if (i != 0) {
            int i4 = (int) j;
            this.f254 = i4;
            this.f247 = i4 * i;
            this.f249 = i4 ^ i;
            this.f251 = (int) (j >> 32);
            return;
        }
        this.f254 = (int) j;
        long j2 = j >> 3;
        short s = f244;
        this.f247 = (int) ((((long) s) * j2) >> 32);
        this.f249 = (int) (j >> 32);
        this.f251 = (int) (j2 + ((long) s));
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        m188();
        int i = this.f253;
        if (i >= this.f250) {
            return -1;
        }
        byte[] bArr = this.f252;
        this.f253 = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            m188();
            int i5 = this.f253;
            if (i5 >= this.f250) {
                if (i4 == i) {
                    return -1;
                }
                return i2 - (i3 - i4);
            }
            byte[] bArr2 = this.f252;
            this.f253 = i5 + 1;
            bArr[i4] = bArr2[i5];
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        m188();
        return this.f250 - this.f253;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private void m189() {
        if (this.f256 == 3) {
            byte[] bArr = this.f252;
            System.arraycopy(bArr, 0, this.f248, 0, bArr.length);
        }
        byte[] bArr2 = this.f252;
        int i = ((bArr2[0] << 24) & ViewCompat.MEASURED_STATE_MASK) + ((bArr2[1] << 16) & 16711680) + ((bArr2[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr2[3] & 255);
        int i2 = ((-16777216) & (bArr2[4] << 24)) + (16711680 & (bArr2[5] << 16)) + (65280 & (bArr2[6] << 8)) + (bArr2[7] & 255);
        int i3 = 0;
        while (true) {
            int i4 = this.f255;
            if (i3 >= i4) {
                break;
            }
            short s = f244;
            i2 -= ((((i4 - i3) * s) + i) ^ ((i << 4) + this.f249)) ^ ((i >>> 5) + this.f251);
            i -= (((i2 << 4) + this.f254) ^ ((s * (i4 - i3)) + i2)) ^ ((i2 >>> 5) + this.f247);
            i3++;
        }
        byte[] bArr3 = this.f252;
        bArr3[0] = (byte) (i >> 24);
        bArr3[1] = (byte) (i >> 16);
        bArr3[2] = (byte) (i >> 8);
        bArr3[3] = (byte) i;
        bArr3[4] = (byte) (i2 >> 24);
        bArr3[5] = (byte) (i2 >> 16);
        bArr3[6] = (byte) (i2 >> 8);
        bArr3[7] = (byte) i2;
        if (this.f256 == 3) {
            for (int i5 = 0; i5 < 8; i5++) {
                byte[] bArr4 = this.f252;
                bArr4[i5] = (byte) (bArr4[i5] ^ this.f245[i5]);
            }
            byte[] bArr5 = this.f248;
            System.arraycopy(bArr5, 0, this.f245, 0, bArr5.length);
        }
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private int m188() throws IOException {
        if (this.f246 == Integer.MAX_VALUE) {
            this.f246 = ((FilterInputStream) this).in.read();
        }
        if (this.f253 == 8) {
            byte[] bArr = this.f252;
            int i = this.f246;
            bArr[0] = (byte) i;
            if (i < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i2 = 1;
            do {
                int i3 = ((FilterInputStream) this).in.read(this.f252, i2, 8 - i2);
                if (i3 <= 0) {
                    break;
                }
                i2 += i3;
            } while (i2 < 8);
            if (i2 < 8) {
                throw new IllegalStateException("unexpected block size");
            }
            m189();
            this.f246 = ((FilterInputStream) this).in.read();
            this.f253 = 0;
            this.f250 = this.f246 < 0 ? 8 - (this.f252[7] & 255) : 8;
        }
        return this.f250;
    }
}
