package com.appsflyer.internal;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class af extends FilterInputStream {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private byte[] f234;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private int f235;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private final int f236;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private byte[] f237;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private int f238;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private ah f239;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private byte[] f240;

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private int[] f241;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private int f242;

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private int f243;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public af(InputStream inputStream, int[] iArr, byte[] bArr, int i, boolean z, int i2) throws IOException {
        super(inputStream);
        this.f235 = Integer.MAX_VALUE;
        this.f236 = Math.min(Math.max(i, 3), 16);
        this.f237 = new byte[8];
        this.f240 = new byte[8];
        this.f234 = new byte[8];
        this.f241 = new int[2];
        this.f243 = 8;
        this.f242 = 8;
        this.f238 = i2;
        if (i2 == 2) {
            System.arraycopy(bArr, 0, this.f240, 0, 8);
        }
        this.f239 = new ah(iArr, this.f236, true, z);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        m187();
        int i = this.f243;
        if (i >= this.f242) {
            return -1;
        }
        byte[] bArr = this.f237;
        this.f243 = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            m187();
            int i5 = this.f243;
            if (i5 >= this.f242) {
                if (i4 == i) {
                    return -1;
                }
                return i2 - (i3 - i4);
            }
            byte[] bArr2 = this.f237;
            this.f243 = i5 + 1;
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
        m187();
        return this.f242 - this.f243;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private void m186() {
        if (this.f238 == 2) {
            byte[] bArr = this.f237;
            System.arraycopy(bArr, 0, this.f234, 0, bArr.length);
        }
        byte[] bArr2 = this.f237;
        ae.m184(((bArr2[0] << 24) & ViewCompat.MEASURED_STATE_MASK) + ((bArr2[1] << 16) & 16711680) + ((bArr2[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr2[3] & 255), ((-16777216) & (bArr2[4] << 24)) + (16711680 & (bArr2[5] << 16)) + (65280 & (bArr2[6] << 8)) + (bArr2[7] & 255), false, this.f236, this.f239.f258, this.f239.f260, this.f241);
        int[] iArr = this.f241;
        int i = iArr[0];
        int i2 = iArr[1];
        byte[] bArr3 = this.f237;
        bArr3[0] = (byte) (i >> 24);
        bArr3[1] = (byte) (i >> 16);
        bArr3[2] = (byte) (i >> 8);
        bArr3[3] = (byte) i;
        bArr3[4] = (byte) (i2 >> 24);
        bArr3[5] = (byte) (i2 >> 16);
        bArr3[6] = (byte) (i2 >> 8);
        bArr3[7] = (byte) i2;
        if (this.f238 == 2) {
            for (int i3 = 0; i3 < 8; i3++) {
                byte[] bArr4 = this.f237;
                bArr4[i3] = (byte) (bArr4[i3] ^ this.f240[i3]);
            }
            byte[] bArr5 = this.f234;
            System.arraycopy(bArr5, 0, this.f240, 0, bArr5.length);
        }
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private int m187() throws IOException {
        if (this.f235 == Integer.MAX_VALUE) {
            this.f235 = ((FilterInputStream) this).in.read();
        }
        if (this.f243 == 8) {
            byte[] bArr = this.f237;
            int i = this.f235;
            bArr[0] = (byte) i;
            if (i < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i2 = 1;
            do {
                int i3 = ((FilterInputStream) this).in.read(this.f237, i2, 8 - i2);
                if (i3 <= 0) {
                    break;
                }
                i2 += i3;
            } while (i2 < 8);
            if (i2 < 8) {
                throw new IllegalStateException("unexpected block size");
            }
            m186();
            this.f235 = ((FilterInputStream) this).in.read();
            this.f243 = 0;
            this.f242 = this.f235 < 0 ? 8 - (this.f237[7] & 255) : 8;
        }
        return this.f242;
    }
}
