package com.google.android.play.core.assetpacks;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;

/* JADX INFO: loaded from: classes.dex */
public final class n implements com.google.android.play.core.internal.ce<AssetPackManager> {
    private final com.google.android.play.core.internal.ce a;
    private final com.google.android.play.core.internal.ce b;
    private final /* synthetic */ int c = 0;

    public n(com.google.android.play.core.internal.ce<i> ceVar, com.google.android.play.core.internal.ce<Context> ceVar2) {
        this.a = ceVar;
        this.b = ceVar2;
    }

    public n(com.google.android.play.core.internal.ce<Context> ceVar, com.google.android.play.core.internal.ce<au> ceVar2, byte[] bArr) {
        this.b = ceVar;
        this.a = ceVar2;
    }

    public n(com.google.android.play.core.internal.ce<Context> ceVar, com.google.android.play.core.internal.ce<bo> ceVar2, char[] cArr) {
        this.a = ceVar;
        this.b = ceVar2;
    }

    public n(com.google.android.play.core.internal.ce<au> ceVar, com.google.android.play.core.internal.ce<t> ceVar2, int[] iArr) {
        this.b = ceVar;
        this.a = ceVar2;
    }

    public n(com.google.android.play.core.internal.ce<Context> ceVar, com.google.android.play.core.internal.ce<cv> ceVar2, short[] sArr) {
        this.a = ceVar;
        this.b = ceVar2;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.google.android.play.core.assetpacks.AssetPackManager, com.google.android.play.core.assetpacks.b] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.android.play.core.assetpacks.AssetPackManager, com.google.android.play.core.assetpacks.an] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.google.android.play.core.assetpacks.AssetPackManager, com.google.android.play.core.assetpacks.au] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.android.play.core.assetpacks.AssetPackManager, com.google.android.play.core.assetpacks.cx] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ AssetPackManager a() {
        int i = this.c;
        if (i == 0) {
            Object objA = this.a.a();
            Context contextA = ((p) this.b).a();
            i iVar = (i) objA;
            com.google.android.play.core.internal.bh.h(contextA.getPackageManager(), new ComponentName(contextA.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"), 4);
            com.google.android.play.core.internal.bh.h(contextA.getPackageManager(), new ComponentName(contextA.getPackageName(), "com.google.android.play.core.assetpacks.ExtractionForegroundService"), 4);
            PlayCoreDialogWrapperActivity.a(contextA);
            com.google.android.play.core.internal.bh.k(iVar);
            return iVar;
        }
        if (i == 1) {
            return new b(((p) this.b).a(), (au) this.a.a());
        }
        if (i == 2) {
            return new an(((p) this.a).a(), (bo) this.b.a());
        }
        if (i == 3) {
            return new au(((p) this.a).a(), (cv) this.b.a());
        }
        return new cx((au) this.b.a(), com.google.android.play.core.internal.cc.c(this.a));
    }
}
