package com.google.android.play.core.assetpacks;

import android.support.annotation.Nullable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class de {
    private final String a;
    private final long b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final byte[] f;

    de() {
    }

    de(@Nullable String str, long j, int i, boolean z, boolean z2, @Nullable byte[] bArr) {
        this();
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = z;
        this.e = z2;
        this.f = bArr;
    }

    static de a(@Nullable String str, long j, int i, boolean z, byte[] bArr, boolean z2) {
        return new de(str, j, i, z, z2, bArr);
    }

    final boolean b() {
        if (d() == null) {
            return false;
        }
        return d().endsWith("/");
    }

    final boolean c() {
        return f() == 0;
    }

    @Nullable
    String d() {
        return this.a;
    }

    long e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof de) {
            de deVar = (de) obj;
            String str = this.a;
            if (str != null ? str.equals(deVar.d()) : deVar.d() == null) {
                if (this.b == deVar.e() && this.c == deVar.f() && this.d == deVar.g() && this.e == deVar.h()) {
                    if (Arrays.equals(this.f, deVar instanceof de ? deVar.f : deVar.i())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    int f() {
        return this.c;
    }

    boolean g() {
        return this.d;
    }

    boolean h() {
        return this.e;
    }

    public int hashCode() {
        String str = this.a;
        int iHashCode = str == null ? 0 : str.hashCode();
        long j = this.b;
        return ((((((((((iHashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.c) * 1000003) ^ (true != this.d ? 1237 : 1231)) * 1000003) ^ (true == this.e ? 1231 : 1237)) * 1000003) ^ Arrays.hashCode(this.f);
    }

    @Nullable
    byte[] i() {
        return this.f;
    }

    public String toString() {
        String str = this.a;
        long j = this.b;
        int i = this.c;
        boolean z = this.d;
        boolean z2 = this.e;
        String string = Arrays.toString(this.f);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 126 + String.valueOf(string).length());
        sb.append("ZipEntry{name=");
        sb.append(str);
        sb.append(", size=");
        sb.append(j);
        sb.append(", compressionMethod=");
        sb.append(i);
        sb.append(", isPartial=");
        sb.append(z);
        sb.append(", isEndOfArchive=");
        sb.append(z2);
        sb.append(", headerBytes=");
        sb.append(string);
        sb.append("}");
        return sb.toString();
    }
}
