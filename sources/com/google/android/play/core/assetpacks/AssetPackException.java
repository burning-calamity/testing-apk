package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackErrorCode;

/* JADX INFO: loaded from: classes.dex */
public class AssetPackException extends com.google.android.play.core.tasks.j {

    @AssetPackErrorCode
    private final int a;

    AssetPackException(@AssetPackErrorCode int i) {
        super(String.format("Asset Pack Download Error(%d): %s", Integer.valueOf(i), com.google.android.play.core.assetpacks.model.a.a(i)));
        if (i == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.a = i;
    }

    @Override // com.google.android.play.core.tasks.j
    @AssetPackErrorCode
    public int getErrorCode() {
        return this.a;
    }
}
