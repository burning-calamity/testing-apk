package com.google.android.play.core.assetpacks;

import android.support.annotation.Nullable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class cm {
    private byte[] a = new byte[4096];
    private int b;
    private long c;
    private long d;
    private int e;
    private int f;
    private int g;
    private boolean h;

    @Nullable
    private String i;

    public cm() {
        d();
    }

    private final int e(int i, byte[] bArr, int i2, int i3) {
        int i4 = this.b;
        if (i4 >= i) {
            return 0;
        }
        int iMin = Math.min(i3, i - i4);
        System.arraycopy(bArr, i2, this.a, this.b, iMin);
        int i5 = this.b + iMin;
        this.b = i5;
        if (i5 < i) {
            return -1;
        }
        return iMin;
    }

    public final int a(byte[] bArr, int i, int i2) {
        int iE = e(30, bArr, i, i2);
        if (iE == -1) {
            return -1;
        }
        if (this.c == -1) {
            long jD = ck.d(this.a, 0);
            this.c = jD;
            if (jD == 67324752) {
                this.h = false;
                this.d = ck.d(this.a, 18);
                this.g = ck.e(this.a, 8);
                this.e = ck.e(this.a, 26);
                int iE2 = this.e + 30 + ck.e(this.a, 28);
                this.f = iE2;
                int length = this.a.length;
                if (length < iE2) {
                    do {
                        length += length;
                    } while (length < iE2);
                    this.a = Arrays.copyOf(this.a, length);
                }
            } else {
                this.h = true;
            }
        }
        int iE3 = e(this.f, bArr, i + iE, i2 - iE);
        if (iE3 == -1) {
            return -1;
        }
        int i3 = iE + iE3;
        if (!this.h && this.i == null) {
            this.i = new String(this.a, 30, this.e);
        }
        return i3;
    }

    public final de b() {
        int i = this.b;
        int i2 = this.f;
        if (i < i2) {
            return de.a(this.i, this.d, this.g, true, Arrays.copyOf(this.a, i), this.h);
        }
        de deVarA = de.a(this.i, this.d, this.g, false, Arrays.copyOf(this.a, i2), this.h);
        d();
        return deVarA;
    }

    public final int c() {
        return this.f;
    }

    public final void d() {
        this.b = 0;
        this.e = -1;
        this.c = -1L;
        this.h = false;
        this.f = 30;
        this.d = -1L;
        this.g = -1;
        this.i = null;
    }
}
