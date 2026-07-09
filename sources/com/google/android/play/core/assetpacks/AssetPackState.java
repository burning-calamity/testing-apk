package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.play.core.assetpacks.model.AssetPackErrorCode;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;

/* JADX INFO: loaded from: classes.dex */
public abstract class AssetPackState {
    public static AssetPackState c(@NonNull String str, @AssetPackStatus int i, @AssetPackErrorCode int i2, long j, long j2, double d, int i3, String str2) {
        return new az(str, i, i2, j, j2, (int) Math.rint(100.0d * d), i3, str2);
    }

    static AssetPackState d(Bundle bundle, String str, bo boVar, as asVar) {
        int iA = asVar.a(bundle.getInt(com.google.android.play.core.internal.i.e("status", str)), str);
        int i = bundle.getInt(com.google.android.play.core.internal.i.e("error_code", str));
        long j = bundle.getLong(com.google.android.play.core.internal.i.e("bytes_downloaded", str));
        long j2 = bundle.getLong(com.google.android.play.core.internal.i.e("total_bytes_to_download", str));
        double dB = boVar.b(str);
        long j3 = bundle.getLong(com.google.android.play.core.internal.i.e("pack_version", str));
        long j4 = bundle.getLong(com.google.android.play.core.internal.i.e("pack_base_version", str));
        int i2 = 1;
        if (iA == 4 && j4 != 0 && j4 != j3) {
            i2 = 2;
        }
        return c(str, iA, i, j, j2, dB, i2, bundle.getString(com.google.android.play.core.internal.i.e("pack_version_tag", str), ""));
    }

    public abstract int a();

    public abstract String b();

    public abstract long bytesDownloaded();

    @AssetPackErrorCode
    public abstract int errorCode();

    public abstract String name();

    @AssetPackStatus
    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();
}
