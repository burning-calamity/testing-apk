package com.google.android.play.core.splitcompat;

import android.support.annotation.NonNull;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
final class q {
    private final File a;
    private final String b;

    q() {
    }

    q(File file, String str) {
        this();
        if (file == null) {
            throw new NullPointerException("Null splitFile");
        }
        this.a = file;
        if (str == null) {
            throw new NullPointerException("Null splitId");
        }
        this.b = str;
    }

    @NonNull
    File a() {
        return this.a;
    }

    @NonNull
    String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof q) {
            q qVar = (q) obj;
            if (this.a.equals(qVar.a()) && this.b.equals(qVar.b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        String strValueOf = String.valueOf(this.a);
        String str = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 35 + str.length());
        sb.append("SplitFileInfo{splitFile=");
        sb.append(strValueOf);
        sb.append(", splitId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
