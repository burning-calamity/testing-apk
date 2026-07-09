package com.google.android.play.core.assetpacks;

/* JADX INFO: loaded from: classes.dex */
public abstract class AssetLocation {
    static AssetLocation a(String str, long j, long j2) {
        return new ax(str, j, j2);
    }

    public abstract long offset();

    public abstract String path();

    public abstract long size();
}
