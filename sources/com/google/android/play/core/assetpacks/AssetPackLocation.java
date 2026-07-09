package com.google.android.play.core.assetpacks;

import android.support.annotation.Nullable;
import com.google.android.play.core.assetpacks.model.AssetPackStorageMethod;

/* JADX INFO: loaded from: classes.dex */
public abstract class AssetPackLocation {
    private static final AssetPackLocation a = new ay(1, null, null);

    static AssetPackLocation a() {
        return a;
    }

    static AssetPackLocation b(String str, String str2) {
        return new ay(0, str, str2);
    }

    @Nullable
    public abstract String assetsPath();

    @AssetPackStorageMethod
    public abstract int packStorageMethod();

    @Nullable
    public abstract String path();
}
