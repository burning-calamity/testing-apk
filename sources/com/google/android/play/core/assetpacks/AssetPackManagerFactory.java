package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.support.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class AssetPackManagerFactory {
    @NonNull
    public static synchronized AssetPackManager getInstance(@NonNull Context context) {
        return ck.j(context).a();
    }
}
