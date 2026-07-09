package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class g implements ce<AppUpdateManager> {
    private final ce a;
    private final /* synthetic */ int b = 0;

    public g(ce<d> ceVar) {
        this.a = ceVar;
    }

    public g(ce<Context> ceVar, byte[] bArr) {
        this.a = ceVar;
    }

    public g(ce<Context> ceVar, char[] cArr) {
        this.a = ceVar;
    }

    public g(ce<Context> ceVar, short[] sArr) {
        this.a = ceVar;
    }

    public static g b(ce<Context> ceVar) {
        return new g(ceVar, (short[]) null);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.play.core.appupdate.AppUpdateManager, com.google.android.play.core.appupdate.a] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.play.core.appupdate.AppUpdateManager, com.google.android.play.core.appupdate.q] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.play.core.appupdate.AppUpdateManager, com.google.android.play.core.splitinstall.p] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ AppUpdateManager a() {
        int i = this.b;
        if (i != 0) {
            return i != 1 ? i != 2 ? new com.google.android.play.core.splitinstall.p((Context) this.a.a()) : new q(((h) this.a).a()) : new a(((h) this.a).a());
        }
        d dVar = (d) this.a.a();
        bh.k(dVar);
        return dVar;
    }
}
