package com.google.android.play.core.assetpacks;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class cy {
    private final int a;
    private final String b;
    private final long c;
    private final long d;
    private final int e;

    cy() {
    }

    cy(int i, @Nullable String str, long j, long j2, int i2) {
        this();
        this.a = i;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = i2;
    }

    int a() {
        return this.a;
    }

    @Nullable
    String b() {
        return this.b;
    }

    long c() {
        return this.c;
    }

    long d() {
        return this.d;
    }

    int e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof cy) {
            cy cyVar = (cy) obj;
            if (this.a == cyVar.a() && ((str = this.b) != null ? str.equals(cyVar.b()) : cyVar.b() == null) && this.c == cyVar.c() && this.d == cyVar.d() && this.e == cyVar.e()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = (this.a ^ 1000003) * 1000003;
        String str = this.b;
        int iHashCode = str == null ? 0 : str.hashCode();
        long j = this.c;
        long j2 = this.d;
        return ((((((i ^ iHashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.e;
    }

    public String toString() {
        int i = this.a;
        String str = this.b;
        long j = this.c;
        long j2 = this.d;
        int i2 = this.e;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 157);
        sb.append("SliceCheckpoint{fileExtractionStatus=");
        sb.append(i);
        sb.append(", filePath=");
        sb.append(str);
        sb.append(", fileOffset=");
        sb.append(j);
        sb.append(", remainingBytes=");
        sb.append(j2);
        sb.append(", previousChunk=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
