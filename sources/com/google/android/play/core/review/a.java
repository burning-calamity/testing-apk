package com.google.android.play.core.review;

import android.app.PendingIntent;

/* JADX INFO: loaded from: classes.dex */
final class a extends ReviewInfo {
    private final PendingIntent a;

    a(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            throw new NullPointerException("Null pendingIntent");
        }
        this.a = pendingIntent;
    }

    @Override // com.google.android.play.core.review.ReviewInfo
    final PendingIntent a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReviewInfo) {
            return this.a.equals(((ReviewInfo) obj).a());
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.a);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 26);
        sb.append("ReviewInfo{pendingIntent=");
        sb.append(strValueOf);
        sb.append("}");
        return sb.toString();
    }
}
